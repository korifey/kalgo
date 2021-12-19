package task16;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Task16_2 {
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
    try (Scanner sc = createScanner("adventOfCode-2021/resources/input16_1.txt", "resources/input16_1.txt")) {
      Task16_2 task = new Task16_2();

      
      while (sc.hasNext()) {
        String hex = sc.nextLine();
        
        int[] bits = Decoder.hexStringToIntArray(hex);

        Packet p = Decoder.parseTransmission(bits);
//        
//        System.out.println(hex);
//        Decoder.printBits(bits);
//        p.print();
        
        System.out.println(p.iterator().next().eval());
        System.out.println();
      }

      //      task.print();
//      System.out.println(task.calcUniqueNumbersInInput());
    }
    
  }

  public Task16_2() {}

  
}
