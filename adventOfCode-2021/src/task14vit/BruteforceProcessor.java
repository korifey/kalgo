package task14vit;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// True to the spirit of brute force it generates an output string for the given depth (aka number of steps) and
// also counts the rules (pairs like "NB") in that string, then populates the map with {"NB"=44, "BB"=241, ...}.
class BruteforceProcessor {
  private final String rule;
  private int depth;
  
  private StringBuilder sb;
  private Map<String, Long> deep_map;
  
  private boolean isBuilt;
  private void checkState() throws IllegalStateException {
    if (!isBuilt) 
      throw new IllegalStateException("You must call build(...) first.");
  }
  
  public BruteforceProcessor(String rule) {
    this.rule = Objects.requireNonNull(rule);
  }
  
  public void build(Map<String, String> rules, int depth) {
    if (depth <= 0)
      throw new IllegalArgumentException("depth=" + depth);
    
    this.depth = depth;
    deep_map = new HashMap<>();
    sb = new StringBuilder(rule);

    for (int h = 0; h < depth; ++h) {
      int len = sb.length();
      int j = 0; // counts added symbols
      int i = 0; // (i + j) points at the next symbol from original string
      while (i < len - 1) {
        String left = sb.substring(i + j, i + j + 2);
        String right = rules.get(left);
        if (right != null) {
          sb.insert(i + j + 1, right);
          ++j;
        }
        
        ++i;
      }
    }
    
    for (int i = 0; i < sb.length() - 1; ++i) {
      deep_map.merge(sb.substring(i, i + 2), 1L, Long::sum);
    }
    
    isBuilt = true;
  }
  
  public String getStringOutput() {
    checkState();
    return sb.toString();
  }
  
  public Map<String, Long> getDeepMap() {
    checkState();
    return deep_map;
  }
  
  public int getDepth() {
    checkState();
    return depth;
  }
  
  public String getRule() {
    return rule;
  }
}
