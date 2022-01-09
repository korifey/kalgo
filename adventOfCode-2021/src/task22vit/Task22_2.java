package task22vit;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Task22_2 {
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
        createScanner("adventOfCode-2021/resources/input22_1.txt", "resources/input22_1.txt")) {

      Task22_2 task = new Task22_2();
      List<Input> inputs = new ArrayList<Input>();
      
      while (sc.hasNext()) {
        inputs.add(Parser.parseInput(sc.nextLine()));
      }
      
      LinkedList<Cube> from = new LinkedList<Cube>();
      
      for (Input input : inputs) {
        System.out.println(input);
        
        Cube b = input.cube();
        LinkedList<Cube> to = new LinkedList<Cube>();
        
        if (from.isEmpty()) {
          if (input.isOn()) {
            to.add(b);
          }
        } else {
          for (Cube a : from) {
            if (input.isOn()) {
//              to.addAll(Cube.union(a, b));
              to.addAll(Cube.substraction(a, b));
            } else {
              to.addAll(Cube.substraction(a, b));
            }
          }
          
          if (input.isOn()) {
            to.add(b);
          }
        }
        
        int fromSize = from.size();

//        from = Cube.reduce(to);
        from = to;

//        System.out.printf("from.size()=%d to.size()=%d reduced=%d%n%n", fromSize, to.size(), from.size());
      }
      
      System.out.println("OUTPUT: " + task.countCoresTurnedOn(from)); // Correct answer for vitrums' input: 1323862415207825
    } // end of try
  }

  Task22_2() {}
  
  private long countCoresTurnedOn(List<Cube> cubes) {
    return cubes.stream().map(Cube::volume).reduce(Long::sum).get(); 
  }
}
