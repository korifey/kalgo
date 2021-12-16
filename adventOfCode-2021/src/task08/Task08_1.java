package task08;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Task08_1 {
  public static void main(String[] args) {
    try (Scanner sc = new Scanner(new File("adventOfCode-2021/resources/input08_1.txt"))) {
      Task08_1 task = new Task08_1();

      while (sc.hasNext()) {
        // Split each string in two halves
        String s, s_ten, s_four;
        s = sc.nextLine();
        int delIdx = s.indexOf('|');
        s_ten = s.substring(0, delIdx);
        s_four = s.substring(delIdx, s.length());
        
        // Fill values
        StringTokenizer st;
        List<String> ten, four;
        
        // Fill ten
        st = new StringTokenizer(s_ten, " |");
        ten = new ArrayList<String>();
        while (st.hasMoreElements()) {
          ten.add(st.nextToken());
        }

        // Fill four
        st = new StringTokenizer(s_four, " |");
        four = new ArrayList<String>();
        while (st.hasMoreElements()) {
          four.add(st.nextToken());
        }

        task.addRecord(ten, four);
      }

//      task.print();
      System.out.println(task.calcUniqueNumbersInInput());
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  private List<List<String>> ten = new ArrayList<List<String>>();
  private List<List<String>> four = new ArrayList<List<String>>();

  public Task08_1() {}

  public void addRecord(List<String> allTen, List<String> onlyFour) {
    this.ten.add(allTen);
    this.four.add(onlyFour);
  }

  public void print() {
    for (int i = 0; i < ten.size(); ++i) {
      System.out.println(ten.get(i));
      System.out.println(four.get(i));
    }
  }

  public long calcUniqueNumbersInInput() {
    Stream<String> displayed = four.stream().flatMap(List::stream);

    Predicate<String> isUnique = s -> {
      switch (s.length()) {
        // [[fallthrough]]
        case 2:
        case 3:
        case 4:
        case 7:
          return true;
        default:
          return false;
      }
    };

    return displayed.filter(isUnique).count();
  }

}
