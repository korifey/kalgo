package task21;

import java.util.stream.IntStream;

public class Player {
  private final int min, max;
  private int score;
  private final int id;
  
  public Player(int min, int max, int score, int name) {
    this.min = min;
    this.max = max;
    this.score = score - min;
    this.id = name;
  }

  public int getScore() {
    return score + min;
  }

  public void setScore(int score) {
    this.score = score;
  }
  
  public int getMin() {
    return min;
  }

  public int getMax() {
    return max;
  }

  public int getId() {
    return id;
  }

  public int rollAndGetScore(Dice dice) {
    int roll = dice.roll();
    score = (score + roll) % (max - min + 1);
//    System.out.println("score=" + getScore() + " roll=" + roll);
    return getScore();
  }
  
  public int rollNTimesAndGetScore(Dice dice, int n) {
//    return IntStream.range(0, n).reduce(0, (acc, x) -> acc + rollAndGetScore(d));
    for (int i = 0; i < n; ++i) {
      rollAndGetScore(dice);
    }
    
    return getScore();
  }
  
  public int roll3TimesAndGetScore(Dice dice) {
    return rollNTimesAndGetScore(dice, 3);
  }
}
