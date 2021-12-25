package task24;

import java.util.stream.LongStream;

class State {
  private static long[] powers = LongStream.range(0, 14).map(i -> 14 - i - 1).map(pow -> (long)Math.pow(10, pow)).toArray();
  private int digitIdx;
  private long digits;
  private long w, x, y, z;
  
  public State(long digits) {
    this.digits = digits;
  }
  
  public long getAndSet(Variable v, long value) {
    long res;
    switch (v) {
      case w:
        res = w; w = value;
        break;
      case x:
        res = x; x = value;
        break;
      case y:
        res = y; y = value;
        break;
      case z:
        res = z; z = value;
        break;
      default:
        throw new IllegalArgumentException("Unsupported variable " + v.name());
    }
    
    return res;
  }
  
  public long extractDigit() {
    long d = digits / powers[digitIdx];
    digits = digits % powers[digitIdx++];
    
    return d;
  }
  
  public long get(Variable v) {
    switch (v) {
      case w:
        return w;
      case x:
        return x;
      case y:
        return y;
      case z:
        return z;
      default:
        throw new IllegalArgumentException("Unsupported variable " + v.name());
    }
  }
  
  public long w() {
    return w;
  }
  
  public long x() {
    return x;
  }
  
  public long y() {
    return y;
  }
  
  public long z() {
    return z;
  }
}
