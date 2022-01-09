package task22vit;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import static java.lang.Math.*;

class Cube {
  public static Cube of(int x1, int x2, int y1, int y2, int z1, int z2) {
    if (x1 <= x2 && y1 <= y2 && z1 <= z2) {
      return new Cube(x1, x2, y1, y2, z1, z2);
    } else {
      return null;
    }
  }
  
  final int x1, x2;
  final int y1, y2;
  final int z1, z2;
  
  private Cube(int x1, int x2, int y1, int y2, int z1, int z2) {
    this.x1 = x1;
    this.x2 = x2;
    this.y1 = y1;
    this.y2 = y2;
    this.z1 = z1;
    this.z2 = z2;
  }

  public int x1() {
    return x1;
  }

  public int x2() {
    return x2;
  }

  public int y1() {
    return y1;
  }

  public int y2() {
    return y2;
  }

  public int z1() {
    return z1;
  }

  public int z2() {
    return z2;
  }
  
  public static Cube intersect(Cube a, Cube b) {
    return Cube.of(max(a.x1, b.x1), min(a.x2, b.x2), max(a.y1, b.y1), min(a.y2, b.y2), max(a.z1, b.z1), min(a.z2, b.z2));
  }
  
  public static List<Cube> substraction(Cube a, Cube b) {
    LinkedList<Cube> cubes = new LinkedList<Cube>();
    Cube q = intersect(a, b);
    
    if (q == null) {
      cubes.add(a);
    } else if (!a.equals(q)) {
      cubes = reduce(a.substractSubcube(q));
    }
    
    return cubes;
  }
  
  // Returns cubes forming the following set: this \ c, where c is a sub-cube of this.
  //
  // Note: the result of this operation is not shortened. I.e. run reduce(...) to combine
  // adjacent cubes and therefore reduce the overall number of cubes.
  private LinkedList<Cube> substractSubcube(Cube c) {
    LinkedList<Cube> subs = new LinkedList<>();
    
    IntPair[] xps = {IntPair.of(x1, c.x1 - 1), IntPair.of(c.x1, c.x2), IntPair.of(c.x2 + 1, x2)};
    IntPair[] yps = {IntPair.of(y1, c.y1 - 1), IntPair.of(c.y1, c.y2), IntPair.of(c.y2 + 1, y2)};
    IntPair[] zps = {IntPair.of(z1, c.z1 - 1), IntPair.of(c.z1, c.z2), IntPair.of(c.z2 + 1, z2)};
    
    for (IntPair xp : xps) {
      for (IntPair yp : yps) {
        for (IntPair zp : zps) {
          if (xp.a == c.x1 && xp.b == c.x2 && yp.a == c.y1 && yp.b == c.y2 && zp.a == c.z1 && zp.b == c.z2)
            continue;
          
          Cube sub = Cube.of(xp.a, xp.b, yp.a, yp.b, zp.a, zp.b);
          if (sub != null)
            subs.add(sub);
        }
      }
    }
    
    return subs;
  }
  
  public static LinkedList<Cube> reduce(LinkedList<Cube> cubes) {
    return new Reducer(cubes).reduce();
  }
  
  public long volume() {
    long width = x2 - x1 + 1;
    long depth = y2 - y1 + 1;
    long height = z2 - z1 + 1;
    return width * depth * height;
  }
  
  @Override
  public String toString() {
    return String.format("(x=%d..%d, y=%d..%d, z=%d..%d)", x1, x2, y1, y2, z1, z2);
  }
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    
    if (!(obj instanceof Cube))
      return false;
    
    Cube c = (Cube) obj;
    return x1 == c.x1 && x2 == c.x2 && y1 == c.y1 && y2 == c.y2 && z1 == c.z1 && z2 == c.z2;
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(x1, x2, y1, y2, z1, z2);
  }
}
