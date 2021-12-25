package task21;

public class DeterministicDice implements Dice {
  
  private final int min, max;
  private int score, count;
  
  public DeterministicDice(int min, int max) {
    this.min = min;
    this.max = max;
    this.score = -1;
  }
  
  public int getScore() {
    return score + min;
  }

  public int getMin() {
    return min;
  }

  public int getMax() {
    return max;
  }

  public int getCount() {
    return count;
  }

  @Override
  public int roll() {
    count++;
    score = (score + 1) % (max - min + 1);
    return getScore();
  }
}
