package task17;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Parser {
  // target area: x=20..30, y=-10..-5
  private static final Pattern INPUT_PATTERN = Pattern.compile("target area: x=(?<xmin>-?\\d+)..(?<xmax>-?\\d+), y=(?<ymin>-?\\d+)..(?<ymax>-?\\d+)");
  
  public static Input parseInput(String s) {
    Matcher m = INPUT_PATTERN.matcher(s);
    
    if (m.matches()) {
      int x1 = Integer.parseInt(m.group("xmin"));
      int x2 = Integer.parseInt(m.group("xmax"));
      int y1 = Integer.parseInt(m.group("ymin"));
      int y2 = Integer.parseInt(m.group("ymax"));
      
      return new Input(x1, x2, y1, y2);
    } else {
      throw new IllegalArgumentException("Input string \"" + s + "\" is ill syntaxed.");
    }
  }
}
