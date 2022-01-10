package task14vit;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

class Solver {
  private final String input;
  private final Map<String, String> rules;

  public Solver(String input, Map<String, String> rules) {
    this.input = input;
    this.rules = rules;
  }

  private Map<String, Map<String, Long>> deep_maps = new HashMap<>();
  private Map<String, Map<String, Long>> ultra_deep_maps = new HashMap<>();
  private long result;
  
  // LOL. My solution is an overkill. The initial brute force depth could be left equal to 1 step xDDD.
  // However, if the program starts to throttle, tune the depth up a bit, and it will fly.
  // Obey the invariant though:
  //    targetDepth == depth * factor
  public long solve(int targetDepth) {
//    int depth = 10;
//    int factor = 4;
    
    int depth = 1;
    int factor = targetDepth;

    if (targetDepth != depth * factor)
      throw new AssertionError(
          "The solution is only for the case of " + depth * factor + " steps.");

    buildDeepMaps(depth);
    buildUltraDeepMaps(depth, factor);
    useDeepMapsForInputRules();

    return result;
  }

  // Do depth steps
  private void buildDeepMaps(int depth) {
    // for (String rule : Arrays.asList("NN")) {
    for (String rule : rules.keySet()) {
      BruteforceProcessor bfProc = new BruteforceProcessor(rule);
      bfProc.build(rules, depth);
      deep_maps.put(rule, bfProc.getDeepMap());
    }
  }

  // Use the results of depth steps and repeat factor times
  private void buildUltraDeepMaps(int depth, int factor) {
    for (String rule : rules.keySet()) {
      BatchProcessor batchProc = new BatchProcessor(rule);
      batchProc.build(deep_maps, depth, factor);
      ultra_deep_maps.put(rule, batchProc.getUltraDeepMap());
    }
  }

  // Divide the input into pairs of symbols as adjacent rules. Then use the results of
  // depth * factor steps (aka target depth). Calculate the result
  private void useDeepMapsForInputRules() {
    List<Map<String, Long>> maps = new ArrayList<>();

    for (int i = 0; i < input.length() - 1; ++i) {
      String rule = input.substring(i, i + 2);
      maps.add(ultra_deep_maps.get(rule));
    }

    Map<String, Long> rulesToCounts = maps.stream().map(Map::entrySet).flatMap(Set::stream)
        .collect(Collectors.toMap(Entry::getKey, Entry::getValue, Long::sum));
    
    // Count characters
    Map<Character, Long> charsToCounts = new HashMap<>();
    for (Entry<String, Long> entry : rulesToCounts.entrySet()) {
      String rule = entry.getKey();
      long value = entry.getValue();
      
      for (int i = 0; i < 2; ++i) {
        charsToCounts.merge(rule.charAt(i), value, Long::sum);
      }
    }
    
    // Don't forget to count twice the first and the last char of the input string before div by 2
    charsToCounts.merge(input.charAt(0), 1L, Long::sum);
    charsToCounts.merge(input.charAt(input.length() - 1), 1L, Long::sum);
    
    charsToCounts.entrySet().stream().forEach(entry -> charsToCounts.put(entry.getKey(), entry.getValue() / 2));

    // Just sort the (char, count) by count
    List<Entry<Character, Long>> ascended = charsToCounts.entrySet().stream().sorted(Comparator.comparingLong(Entry::getValue)).collect(Collectors.toList());
    Entry<Character, Long> min = ascended.get(0);
    Entry<Character, Long> max = ascended.get(ascended.size() - 1);
    
    System.out.printf("max: '%c' [%18d]%nmin: '%c' [%18d]%ndiff:    [%18d]%n", max.getKey(), max.getValue(), min.getKey(), min.getValue(), max.getValue() - min.getValue());
    
    result = max.getValue() - min.getValue(); // min[N:802701318456] max[F:3678366520894] diff:[2875665202438]
  }
}
