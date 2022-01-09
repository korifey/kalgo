package task22vit;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Parser {
  // on x=-54112..-39298,y=-85059..-49293,z=-27449..7877
  // off x=967..23432,y=45373..81175,z=27513..53682
  private static final Pattern PATTERN =
      Pattern.compile("(?<mode>on|off) x=(?<xmin>-?\\d+)..(?<xmax>-?\\d+),y=(?<ymin>-?\\d+)..(?<ymax>-?\\d+),z=(?<zmin>-?\\d+)..(?<zmax>-?\\d+)");

  public static Input parseInput(String s) {
    Matcher m = PATTERN.matcher(s);
    
    if (m.matches()) {
      boolean mode = "on".equals(m.group("mode")) ? true : false;
      int x1 = Integer.parseInt(m.group("xmin"));
      int x2 = Integer.parseInt(m.group("xmax"));
      int y1 = Integer.parseInt(m.group("ymin"));
      int y2 = Integer.parseInt(m.group("ymax"));
      int z1 = Integer.parseInt(m.group("zmin"));
      int z2 = Integer.parseInt(m.group("zmax"));
      
      return new Input(mode, Cube.of(x1, x2, y1, y2, z1, z2));
    } else {
      throw new IllegalArgumentException("Input string \"" + s + "\" is ill syntaxed.");
    }
  }
}
