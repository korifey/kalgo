// Sample input 3, in Java.
public class crates {
  public crates() {
  }

  public static long GetNumStacks() {
    return 1L;
  }

  public static long GetStackHeight(long i) {
    switch ((int)i) {
      case 1: return 10L;
      case 2: return 50L;
      case 3: return 0L;
      case 4: return 50L;
      default: throw new IllegalArgumentException("Invalid argument");
    }
  }
}