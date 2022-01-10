package task21;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Task21_2 {
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

      Task21_2 task = new Task21_2();
      int pos1 = parsePosition(sc.nextLine());
      int pos2 = parsePosition(sc.nextLine());
      task.play(pos1, pos2);
    }
  }

  public Task21_2() {}

  private static int parsePosition(String s) {
    return Integer.parseInt(s.substring(s.indexOf(": ") + 2));
  }

  public void play(int pos1, int pos2) {
    DiracGame game = new DiracGame(pos1, pos2);
    
    game.play();
  }
}
