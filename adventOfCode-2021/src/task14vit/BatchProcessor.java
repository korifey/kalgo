package task14vit;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

// Unlike brute force processor it has some knowledge of the outcome at a certain depth.
// All we have to do is to repeat few times the result achieved previously for this depth
// multiply, add etc. and we have the batch result for depth * factor (i.e. 10 * 4).
public class BatchProcessor {
  private final String rule;
  private int factor;
  
  private Map<String, Long> ultra_deep_map;
  
  private boolean isBuilt;
  private void checkState() throws IllegalStateException {
    if (!isBuilt) 
      throw new IllegalStateException("You must call build(...) first.");
  }
  
  public BatchProcessor(String rule) {
    this.rule = Objects.requireNonNull(rule);
  }
  
  // dmap: has entries like {NN={CC=34, BB=298, BC=34, NB=308, NC=21, CN=55, BN=274}} for depth 10
  // depth: dmap's depth
  // factor: result will be of the depth equal to factor * depth
  public void build(Map<String, Map<String, Long>> deep_maps, int depth, int factor) {
    if (factor < 2)
      throw new IllegalArgumentException("factor=" + factor + " < 2");
    
    this.factor = factor;
    ultra_deep_map = new HashMap<>();
    
    ultra_deep_map.putAll(deep_maps.get(rule));
    
    for (int f = 0; f < factor - 1; ++f) {
      Map<String, Long> acc = new HashMap<>();
      
      for (Entry<String, Long> ultra_deep_map_entry : ultra_deep_map.entrySet()) {
        long mult = ultra_deep_map_entry.getValue();
        Map<String, Long> deep_map = deep_maps.get(ultra_deep_map_entry.getKey());
        
        for (Entry<String, Long> deep_map_entry : deep_map.entrySet()) {
          acc.merge(deep_map_entry.getKey(), mult * deep_map_entry.getValue(), Long::sum);
        }
      }
      
      ultra_deep_map = acc;
    }
    
    isBuilt = true;
  }
  
  public Map<String, Long> getUltraDeepMap() {
    checkState();
    return ultra_deep_map;
  }
  
  public int getFactor() {
    checkState();
    return factor;
  }
}
