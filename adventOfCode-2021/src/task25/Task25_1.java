package task25;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task25_1 {
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
        createScanner("adventOfCode-2021/resources/input25_1.txt", "resources/input25_1.txt")) {

      Task25_1 task = new Task25_1();
      List<List<Character>> l = new ArrayList<List<Character>>();
      while (sc.hasNext()) {
        l.add(parseLine(sc.nextLine()));
      }
      
      Seafloor sf = new Seafloor(l);
//      sf.print();
//      System.out.println("----------------");
//      System.out.println();
      
      int step = 0;
      boolean isMoving = false;
      do {
        ++step;
        isMoving = sf.tick();
//        System.out.println();
//        sf.print();
      } while (isMoving);
      
      System.out.println(step);
    }

  }

  Task25_1() {}

  private static List<Character> parseLine(String s) {
    List<Character> l = new ArrayList<Character>();
    for (char c : s.toCharArray()) {
      l.add(c);
    }
    
    return l;
  }
}
