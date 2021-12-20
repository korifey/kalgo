package task16;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import static task16.MagicNumbers.*;

class Decoder {
  /**
   * Returns array of bits, where each element represents 1 bits.
   * 
   * @param hexStr
   * @return
   */
  static int[] hexStringToIntArray(String hexStr) {
    List<Integer> ints = new ArrayList<Integer>();
    
    for (char c : hexStr.toCharArray()) {
      int x = 0;
      if (c >= 'A' && c <='F') {
        x = c - 'A' + 10;
      } else if (c >= '0' && c <= '9') {
        x = c - '0';
      } else {
        throw new IllegalArgumentException("Bad hex string syntax.");
      }
      
      for (int i = 3; i >=0; --i) {
        ints.add((x & 1 << i) >> i);
      }
    }
    
    return ints.stream().mapToInt(Integer::intValue).toArray();
  }
  
  static void printBits(int[] bits) {
    Arrays.stream(bits).forEach(System.out::print);
    System.out.println();
  }
  
  static Packet parseTransmission(int[] bits) {
    Packet root = new Packet(0, -1, Optional.empty(), Optional.empty());
    
    parseAux(bits, new AtomicInteger(0), root, PacketType.NUMBER_OF_SUBPACKETS, 1);
    
    return root;
  }

  private static void parseAux(int[] bits, AtomicInteger pos, Packet parent, PacketType parentType, int parentSpecificValue) {
    while (parentSpecificValue > 0 && pos.get() < bits.length) {
      int posSnapshot = pos.get();
      int version = bitsToInt(bits, pos.getAndAdd(3), 3);
      int typeId = bitsToInt(bits, pos.getAndAdd(3), 3);
//      System.out.printf("pos=%d, parentType=%s, parSpecVal=%d | ver=%d, type=%d%n", pos.get(), parentType.name(), parentSpecificValue, version, typeId);
      
      Optional<Integer> lengthTypeId = Optional.empty();
      Optional<Long> value = Optional.empty();
      
      if (typeId != LITERAL) { // operator
        lengthTypeId = Optional.of(bits[pos.getAndIncrement()]);
        Packet packet = new Packet(version, typeId, lengthTypeId, value);
        parent.addSubpacket(packet);
        
        PacketType packetType = PacketType.of(lengthTypeId.get());
        int bitsToRead = 0;
        if (packetType == PacketType.BITS_FOR_SUBPACKETS) {
          bitsToRead = N_BITS_OF_SUBPACKETS_LENGTH_IN_BITS;
        } else if (packetType == PacketType.NUMBER_OF_SUBPACKETS) {
          bitsToRead = N_BITS_OF_NUMBER_OF_SUBPACKETS;
        }
        
        int newParentSpecificValue = bitsToInt(bits, pos.getAndAdd(bitsToRead), bitsToRead);
        
        parseAux(bits, pos, packet, packetType, newParentSpecificValue);
      } else { // LITERAL
        ParseValueResult res = parseValue(bits, pos);
        value = Optional.of(res.getValue());
        Packet packet = new Packet(version, typeId, lengthTypeId, value);
        parent.addSubpacket(packet);
      }
      
      switch (parentType) {
        case BITS_FOR_SUBPACKETS:
          parentSpecificValue -= pos.get() - posSnapshot;
          break;
        case NUMBER_OF_SUBPACKETS:
          parentSpecificValue--;
          break;
      }
    }
  }
  
  private static class ParseValueResult {
    private final long value;
    private final int bitsConsumed;

    ParseValueResult(long value, int bitsConsumed) {
      this.value = value;
      this.bitsConsumed = bitsConsumed;
//      System.out.println(value + " " + bitsConsumed);
    }

    public long getValue() {
      return value;
    }

    public int getBitsConsumed() {
      return bitsConsumed;
    }
  }
  
  private static ParseValueResult parseValue(int[] bits, AtomicInteger pos) {
    long res = 0;
    int bitsConsumed = 0;
    while(true) {
      boolean hasMore = bits[pos.getAndIncrement()] == 1;
      res = (res << 4) + bitsToInt(bits, pos.getAndAdd(4), 4);
      bitsConsumed += 5;
      
      if (!hasMore)
        break;
    }
    
    return new ParseValueResult(res, bitsConsumed);
  }
  
  private static int bitsToInt(int[] bits, int pos, int length) {
    int res = 0;
    
    for (int i = 0; i < length; ++i) {
      res = (res << 1) + bits[pos + i];
    }
    
    return res;
  }
}
