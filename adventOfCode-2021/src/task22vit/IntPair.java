package task22vit;

class IntPair {
  public static IntPair of(int a, int b) {
    return new IntPair(a, b);
  }
  
  int a, b;

  private IntPair(int a, int b) {
    this.a = a;
    this.b = b;
  }
}
