package task21;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Task21_1 {
  private static final Scanner createScanner(String... fileNames) {
    for (String fileName : fileNames) {
      try {
        Scanner sc = new Scanner(new File(fileName));
        return sc;
      } catch (FileNotFoundException e) {
        // TODO: handle exception
      }
    }

    return null;
  }

  public static void main(String[] args) {
    try (Scanner sc =
        createScanner("adventOfCode-2021/resources/input21.txt", "resources/input21.txt")) {
      // testDiterministicDice();
      // System.out.println();
      // testSampleInput8Moves();
      // System.out.println();

      Task21_1 task = new Task21_1();
      int pos1 = parsePosition(sc.nextLine());
      int pos2 = parsePosition(sc.nextLine());
      task.play(pos1, pos2);
    }

  }

  public Task21_1() {}

  private static int parsePosition(String s) {
    return Integer.parseInt(s.substring(s.indexOf(": ") + 2));
  }

  private static void testDiterministicDice() {
    DeterministicDice dd = new DeterministicDice(5, 10);

    for (int i = 0; i < 12; ++i) {
      System.out.println(dd.roll());
    }
    System.out.println();
  }

  private static void testSampleInput8Moves() {
    Dice dice = new DeterministicDice(1, 100);

    Player p1 = new Player(1, 10, 4, 1);
    Player p2 = new Player(1, 10, 8, 2);

    PlayerStatistics ps1 = new PlayerStatistics(p1);
    PlayerStatistics ps2 = new PlayerStatistics(p2);

    List<PlayerStatistics> psList = Arrays.asList(ps1, ps2);
    int nPlayers = psList.size();
    int turn = 0;

    for (int i = 0; i < 4 * nPlayers; ++i) {
      PlayerStatistics ps = psList.get(turn++ % nPlayers);
      System.out.printf("Player %d: score=%d%n", ps.getPlayer().getId(),
          ps.playRoundAndGetTotalScore(dice));
    }
  }

  public void play(int pos1, int pos2) {
    Dice dice = new DeterministicDice(1, 100);
    Player p1 = new Player(1, 10, pos1, 1);
    Player p2 = new Player(1, 10, pos2, 2);

    DeterministicGame game = new DeterministicGame(p1, p2, dice);
    game.play();
  }
}
