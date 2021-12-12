package task08;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Task08_2 {
  public static void main(String[] args) {
    try (Scanner sc = new Scanner(new File("resources/input08_1.txt"))) {
      Task08_2 task = new Task08_2();

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

      // task.print();
      // testGeneratePermutations();
      // task.testTaskInExplanation();

      task.calcSumOfAllFours();
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  private List<List<String>> ten = new ArrayList<List<String>>();
  private List<List<String>> four = new ArrayList<List<String>>();

  public Task08_2() {}

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

  // Calculates the answer
  public void calcSumOfAllFours() {
    int res = 0;

    for (int i = 0; i < ten.size(); ++i) {
      int[] perm = evalCorrectPermutation(ten.get(i));
      int value = decodeFourDigits(four.get(i), perm);
      res += value;
    }

    System.out.println(res);
  }

  public void testTaskInExplanation() {
    List<String> ten = Arrays.asList("acedgfb", "cdfbe", "gcdfa", "fbcad", "dab", "cefabd",
        "cdfgeb", "eafb", "cagedb", "ab");
    int[] perm = evalCorrectPermutation(ten);
    System.out.println(Arrays.stream(perm).boxed().collect(Collectors.toList()));

    List<String> four = Arrays.asList("cdfeb", "fcadb", "cdfeb", "cdbaf");
    int value = decodeFourDigits(four, perm);
    System.out.println(value);
  }

  public static void testGeneratePermutations() {
    List<int[]> perms = generatePermutations(3);
    for (int[] perm : perms) {
      System.out.println(Arrays.stream(perm).boxed().collect(Collectors.toList()));
    }
  }

  private int decodeFourDigits(List<String> four, int[] perm) {
    int res = 0;
    for (int i = 0; i < four.size(); ++i) {
      int[] display = getDisplayForStringUsingPermutation(four.get(i), perm);
      int d;
      for (d = 0; d < DIGS.length; ++d) {
        if (Arrays.equals(DIGS[d], display))
          break;
      }

      res = res * 10 + d;
    }

    return res;
  }

  // .aaaa.
  // b....c
  // b....c
  // .dddd.
  // e....f
  // e....f
  // .gggg.

  /**
   * Returns 7-char string with the semantic according to the pic above ^ filling positions in the
   * reading order.
   * 
   * @param ten list of ten digits, e.g. [be, cfbegad, cbdgef, fgaecd, cgeb, fdcge, agebfd, fecdb,
   *        fabcd, edb]
   * @return correct permutation (aka decoded) 7-char string, where each char corresponds to the
   *         7-dig on the picture at the index of this char
   */
  private int[] evalCorrectPermutation(List<String> ten) {
    List<int[]> perms = generatePermutations(7);
    int[] found = null;
    perm_label: for (int[] perm : perms) {
      for (String s : ten) {
        int[] dig = getDisplayForStringUsingPermutation(s, perm);
        if (!Arrays.stream(DIGS).anyMatch(it -> Arrays.equals(it, dig))) {
          continue perm_label;
        }
      }

      found = perm;
      break;
    }

    return found;
  }

  private int[] getDisplayForStringUsingPermutation(String s, int[] perm) {
    int[] res = new int[perm.length];
    for (int i = 0; i < s.length(); ++i) {
      char c = s.charAt(i);
      int code = c - 'a';
      int pos = perm[code]; // pos in 7-seg
      res[pos] = 1;
    }

    return res;
  }

  // Corresponds to 0 permutation, i.e. "abcdefg"
  private static final int[][] DIGS = {
      //
      new int[] {1, 1, 1, 0, 1, 1, 1}, // 0
      new int[] {0, 0, 1, 0, 0, 1, 0}, // 1
      new int[] {1, 0, 1, 1, 1, 0, 1}, // 2
      new int[] {1, 0, 1, 1, 0, 1, 1}, // 3
      new int[] {0, 1, 1, 1, 0, 1, 0}, // 4
      new int[] {1, 1, 0, 1, 0, 1, 1}, // 5
      new int[] {1, 1, 0, 1, 1, 1, 1}, // 6
      new int[] {1, 0, 1, 0, 0, 1, 0}, // 7
      new int[] {1, 1, 1, 1, 1, 1, 1}, // 8
      new int[] {1, 1, 1, 1, 0, 1, 1}, // 9
  };

  public static List<int[]> generatePermutations(int n) {
    List<int[]> perms = new ArrayList<int[]>();

    aux(perms, new int[n], 0, IntStream.range(0, n).boxed().collect(Collectors.toList()));

    return perms;
  }

  private static void aux(List<int[]> acc, int[] res, int depth, List<Integer> pool) {
    if (depth == res.length) {
      acc.add(res.clone());
      return;
    }

    for (int i = 0; i < res.length - depth; ++i) {
      int x = pool.get(i);
      res[depth] = x;
      List<Integer> newPool = pool.stream().filter(it -> it != x).collect(Collectors.toList());
      aux(acc, res, depth + 1, newPool);
    }
  }
}
