package task24;

import java.util.Objects;

class Point3d {
  long x, y, z;
  
  public Point3d(long x, long y, long z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    
    if (!(obj instanceof Point3d))
      return false;
    
    Point3d p = (Point3d) obj;
    
    return x == p.x && y == p.y && z == p.z;
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(x, y, z);
  }
}
