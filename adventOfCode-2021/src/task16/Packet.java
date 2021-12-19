package task16;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import static task16.MagicNumbers.*;

class Packet implements Iterable<Packet> {
  private final int version;
  private final int typeId;
  private final Optional<Integer> lengthTypeId;
  private final Optional<Long> value;
  private final List<Packet> subpackets = new ArrayList<Packet>();

  public Packet(int version, int typeId, Optional<Integer> lengthTypeId, Optional<Long> value) {
    super();
    this.version = version;
    this.typeId = typeId;
    this.lengthTypeId = lengthTypeId;
    this.value = value;
  }

  public void print() {
    print(0, "");
  }
  
  private void print(int depth, String ident) {
    System.out.printf("%s%d: [ver:%d, type:%d, lenBit:%d, val:%d]%n", ident, depth, version, typeId, lengthTypeId.orElse(-1), value.orElse(-1L));
    for (Packet sub : this) {
      sub.print(depth + 1, ident + "  ");
    }
  }
  
  public int sumVersions() {
    AtomicInteger res = new AtomicInteger(0);
    doSumVersions(res);
    return res.get();
  }
  
  private void doSumVersions(AtomicInteger acc) {
    acc.addAndGet(version);
    for (Packet sub : this) {
      sub.doSumVersions(acc);
    }
  }
  
  static final int SUM = 0;
  static final int PRODUCT = 1;
  static final int MINIMUM = 2;
  static final int MAXIMUM = 3;
  static final int LITERAL = 4;
  static final int GREATER_THAN = 5;
  static final int LESS_THAN = 6;
  static final int EQUAL_TO = 7;
  
  public long eval() {
    if ((typeId) == LITERAL)
      return value.get();
    
    switch (typeId) {
      case SUM:
        long sum = 0;
        for (Packet sub : this) {
          sum += sub.eval();
        }
        return sum;
      case PRODUCT:
        long product = 1;
        for (Packet sub : this) {
          product *= sub.eval();
        }
        return product;
      case MINIMUM:
        long min = Long.MAX_VALUE;
        return subpackets.stream().map(Packet::eval).reduce(Long::min).get();
      case MAXIMUM:
        long max = Long.MIN_VALUE;
        return subpackets.stream().map(Packet::eval).reduce(Long::max).get();
      case GREATER_THAN:
        Iterator<Packet> it1 = iterator();
        Packet sub11 = it1.next();
        Packet sub12 = it1.next();
        return sub11.eval() > sub12.eval() ? 1L : 0;
      case LESS_THAN:
        Iterator<Packet> it2 = iterator();
        Packet sub21 = it2.next();
        Packet sub22 = it2.next();
        return sub21.eval() < sub22.eval() ? 1L : 0;
      case EQUAL_TO:
        Iterator<Packet> it3 = iterator();
        Packet sub31 = it3.next();
        Packet sub32 = it3.next();
        return sub31.eval() == sub32.eval() ? 1L : 0;
        default:
          return 0;
    }
  }
  
  public void addSubpacket(Packet p) {
    subpackets.add(p);
  }
  
  public Iterator<Packet> iterator() {
    return subpackets.iterator();
  }

  public int getVersion() {
    return version;
  }

  public int getTypeId() {
    return typeId;
  }

  public Optional<Integer> getLengthTypeId() {
    return lengthTypeId;
  }

  public Optional<Long> getValue() {
    return value;
  }
}
