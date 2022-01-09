package task22vit;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static java.lang.Math.*;

public class Task22_1 {
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

  public static int MIN = -50, MAX = 50, N = MAX - MIN + 1;
  
  public static void main(String[] args) {
    try (Scanner sc =
        createScanner("adventOfCode-2021/resources/input22_1.txt", "resources/input22_1.txt")) {

      Task22_1 task = new Task22_1();
      List<Input> inputs = new ArrayList<Input>();
      
      while (sc.hasNext()) {
        inputs.add(Parser.parseInput(sc.nextLine()));
      }
      
      for (Input input : inputs) {
        System.out.println(input);
      }
      
      task.solve(inputs);
    }
  }

  Task22_1() {}

  void solve(List<Input> inputs) {
    int v[][][] = new int[N][][];
    for (int x = 0; x < N; ++x) {
      v[x] = new int[N][];
      
      for (int y = 0; y < N; ++y) {
        v[x][y] = new int[N];
      }
    }
    
    for (Input input : inputs) {
      boolean isOn = input.isOn();
      Cube c = input.cube();
      for (int x = max(c.x1, MIN); x <= min(c.x2, MAX); ++x) {
        for (int y = max(c.y1, MIN); y <= min(c.y2, MAX); ++y) {
          for (int z = max(c.z1, MIN); z <= min(c.z2, MAX); ++z) {
            v[x + MAX][y+ MAX][z + MAX] = isOn ? 1 : 0;
          }
        }
      }
    }
    
    int sum = 0;
    for (int x = MIN; x <= MAX; ++x) {
      for (int y = MIN; y <= MAX; ++y) {
        for (int z = MIN; z <= MAX; ++z) {
          sum += v[x + MAX][y + MAX][z + MAX];
        }
      }
    }
    
    System.out.println("OUTPUT: " + sum); // Correct answer for vitrums' input: 615869
  }
  
}
