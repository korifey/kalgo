// Sample input 2, in Java.
public class winning_move {
  public winning_move() {
  }

  public static long GetNumPlayers() {
    return 8L;
  }

  public static long GetSubmission(long playernum) {
    switch ((int)playernum) {
      case 0: return 4L;
      case 1: return 2L;
      case 2: return 1L;
      case 3: return 3L;
      case 4: return 4L;
      case 5: return 1L;
      case 6: return 2L;
      case 7: return 2L;
      default: throw new IllegalArgumentException("Invalid argument");
    }
  }
}