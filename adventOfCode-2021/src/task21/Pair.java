package task21;

class Pair {
  long a, b;

  public Pair() {
    this(0,0);
  }
  
  public Pair(int a, int b) {
    this.a = a;
    this.b = b;
  }
  
  public Pair add(long a, long b) {
    this.a += a;
    this.b += b;
    return this;
  }
  
  public Pair add(Pair p) {
    a += p.a;
    b += p.b;
    return this;
  }
  
  public Pair mult(long x) {
    a *= x;
    b *= x;
    return this;
  }
  
  @Override
  public String toString() {
    return String.format("(%d, %d)", a, b);
  }
}
