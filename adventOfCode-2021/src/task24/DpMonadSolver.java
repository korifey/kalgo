package task24;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

class DpMonadSolver extends AbstractMonadSolver  {
  private List<List<Instruction>> sliceMonad() {
    List<List<Instruction>> subs = new ArrayList<>();
    List<Instruction> sub = null;
    
    for (Instruction ins : ins_list) {
      if (ins instanceof Input) {
        sub = new ArrayList<>();
        subs.add(sub);
      } else {
        sub.add(ins);
      }
    }
    
    return subs;
  }
  
  private void runSubInstructionsAndEvalNewState(List<Instruction> sub, LinkedHashMap<Point3d, Long> newStates, Point3d state, long inputDigitsSoFar, int w) {
    State s = new State(w, state.x, state.y, state.z);
    Monad.runInstructions(s, sub);
    Point3d newState = new Point3d(s.x(), s.y(), s.z());
    
    newStates.putIfAbsent(newState, inputDigitsSoFar * 10 + w);
  }
  
  @Override
  public long findHighest() {
    LinkedHashMap<Point3d, Long> states = new LinkedHashMap<>();
    states.put(new Point3d(0, 0, 0), 0L);
    List<List<Instruction>> subs = sliceMonad();
    
    int N_FIRST_DIGITS_TO_DP = 14;
    for (int dig_idx = 0; dig_idx < N_FIRST_DIGITS_TO_DP; ++dig_idx) {
      List<Instruction> sub = subs.get(dig_idx);
      LinkedHashMap<Point3d, Long> newStates = new LinkedHashMap<>();
      
      for (int w = 9; w > 0; --w) {
        for (Entry<Point3d, Long> entry : states.entrySet()) {
          Point3d state = entry.getKey();
          long inputDigitsSoFar = entry.getValue();
          
          runSubInstructionsAndEvalNewState(sub, newStates, state, inputDigitsSoFar, w);
        }
      }
      
      states = newStates;
      System.out.printf("[%d] states.size()=%d%n", dig_idx, states.size());
    }
    
    if (N_FIRST_DIGITS_TO_DP == 14) {
      return states.entrySet().stream().filter(entry -> entry.getKey().z == 0).reduce((a, b) -> a.getValue() > b.getValue() ? a : b).get().getValue();
    }
    
    return Long.MIN_VALUE; // TODO:
  }
}
