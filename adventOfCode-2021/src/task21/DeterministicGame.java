package task21;

import java.util.Arrays;
import java.util.List;

public class DeterministicGame {
  private final Player p1, p2;
  private final Dice dice;
  
  public DeterministicGame(Player p1, Player p2, Dice dice) {
    this.p1 = p1;
    this.p2 = p2;
    this.dice = dice;
  }
  
  public void play() {
    PlayerStatistics ps1 = new PlayerStatistics(p1);
    PlayerStatistics ps2 = new PlayerStatistics(p2);

    List<PlayerStatistics> psList = Arrays.asList(ps1, ps2);
    int nPlayers = psList.size();
    int turn = 0;

    while (true) {
      PlayerStatistics ps = psList.get(turn++ % nPlayers);
      ps.playRoundAndGetTotalScore(dice);
      
      if (isGameOver(ps)) {
        PlayerStatistics loserStatistics = psList.get(turn++ % nPlayers);
//        System.out.printf("Loser's score is %d. Dice rolled %d times.%n", loserStatistics.getTotalScore(), dice.getCount());
        System.out.println(loserStatistics.getTotalScore() * dice.getCount());
        break;
      }
    }
  }
  
  private boolean isGameOver(PlayerStatistics ps) {
    return ps.getTotalScore() >= 1000;
  }
}
