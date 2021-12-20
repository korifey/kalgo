package task16;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;
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
    System.out.printf("%s%d: [ver:%d, type:%d, lenBit:%d, val:%d]%n", ident, depth, version, typeId,
        lengthTypeId.orElse(-1), value.orElse(-1L));
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

  public long eval() {
    if ((typeId) == LITERAL)
      return value.get();

    Stream<Long> stream = subpackets.stream().map(Packet::eval);

    switch (typeId) {
      case SUM:
        return stream.reduce(Long::sum).get();
      case PRODUCT:
        return stream.reduce((a, b) -> a * b).get();
      case MINIMUM:
        return stream.reduce(Long::min).get();
      case MAXIMUM:
        return stream.reduce(Long::max).get();
      case GREATER_THAN: {
        Iterator<Packet> it = iterator();
        Packet a = it.next();
        Packet b = it.next();
        return a.eval() > b.eval() ? 1L : 0;
      }
      case LESS_THAN: {
        Iterator<Packet> it = iterator();
        Packet a = it.next();
        Packet b = it.next();
        return a.eval() < b.eval() ? 1L : 0;
      }
      case EQUAL_TO: {
        Iterator<Packet> it = iterator();
        Packet a = it.next();
        Packet b = it.next();
        return a.eval() == b.eval() ? 1L : 0;
      }
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
