package task14vit;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class Task14_2 {
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
        createScanner("adventOfCode-2021/resources/input14_1.txt", "resources/input14_1.txt")) {

      Map<String, String> rules = new HashMap<>();
      
      String input = sc.nextLine();
      sc.nextLine();
      
      while (sc.hasNext()) {
        String s = sc.nextLine(); // CH -> B
        
        String left = s.substring(0, 2);
        String right = s.substring(6, 7);
        
        rules.put(left, right);
      }
      
      // Printing input
      System.out.println(input);
      System.out.println();
      for (Entry<String, String> rule : rules.entrySet()) {
        rule.getKey().intern();
        rule.getValue().intern();
        System.out.printf("%s -> %s%n", rule.getKey(), rule.getValue());
      }
      System.out.println();
      
      // Solve
      Solver solver = new Solver(input, rules);
      long result = solver.solve(40);
      System.out.println();
      System.out.println("OUTPUT: " + result); // Correct answer for vitrums' input: 2875665202438
    }
  }

  Task14_2() {}
}
