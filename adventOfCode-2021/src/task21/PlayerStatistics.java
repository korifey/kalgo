package task21;

public class PlayerStatistics {
  private final Player p;
  private int totalScore = 0;
  
  public PlayerStatistics(Player p) {
    this.p = p;
  }
  
  public int getTotalScore() {
    return totalScore;
  }

  public Player getPlayer() {
    return p;
  }

  public int playRoundAndGetTotalScore(Dice dice) {
    totalScore += p.roll3TimesAndGetScore(dice);
    return getTotalScore();
  }

}
