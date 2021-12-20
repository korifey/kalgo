package task16;

public enum PacketType {
  ROOT(-100),
  VALUE(-1),
  BITS_FOR_SUBPACKETS(0),
  NUMBER_OF_SUBPACKETS(1);
  
  private final int bit;
  
  private PacketType(int bit) {
    this.bit = bit;
  }
  
  public int getBit() {
    return bit;
  }

  public static PacketType of(int bit) {
    for (PacketType pt : PacketType.values()) {
      if (pt.bit == bit) {
        return pt;
      }
    }
    
    return null;
  }
}
