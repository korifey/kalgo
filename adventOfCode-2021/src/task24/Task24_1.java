package task24;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task24_1 {
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
        createScanner("adventOfCode-2021/resources/input24_1.txt", "resources/input24_1.txt")) {

      Task24_1 task = new Task24_1();
      
      List<Instruction> ins_list = new ArrayList<Instruction>();
      while (sc.hasNext()) {
        ins_list.add(Parser.parseInput(sc.nextLine()));
      }
      
      Monad monad = new Monad(ins_list);
      monad.printInstructions();
      System.out.printf("OUTPUT: %d%n", monad.findHighest());
    }
  }

  Task24_1() {}

  private static void testExtractDigit() {
    State s = new State(12345678912345L);
    for (int i = 0; i < 14; ++i) {
      System.out.println(s.extractDigit());
    }
  }
  
}
