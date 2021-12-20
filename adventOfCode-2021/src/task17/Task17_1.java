package task17;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import task17.ProbeLauncher.HighestYResult;

public class Task17_1 {
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
    try (Scanner sc = createScanner("adventOfCode-2021/resources/input17_1.txt", "resources/input17_1.txt")) {
      Task17_1 task = new Task17_1();
      
      while (sc.hasNext()) {
        String s = sc.nextLine();
        
        Input input = Parser.parseInput(s);
//        System.out.println(s);
//        input.print();
        
        ProbeLauncher probeLauncher = new ProbeLauncher(input);
        HighestYResult res = probeLauncher.findHighestY();
//        System.out.println(res);
        System.out.println(res.getY());
      }
    }
    
  }

  public Task17_1() {}
}
