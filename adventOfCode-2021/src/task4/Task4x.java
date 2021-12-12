package task4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Task4x {

    public static void main(String[] args) throws Exception {
        ArrayList<Board> boards = new ArrayList<>();
        BufferedReader inp = new BufferedReader(new FileReader("adventOfCode-2021/resources/input4.txt"));
        String s;
        s = inp.readLine();
        String[] numbers = s.trim().split(",");

        while((s = inp.readLine())!=null) {
            Board b = new Board();
            for(int line=0; line<5; line++) {
                s = inp.readLine();
                if(s==null)
                    break;
                String sline[] = s.trim().split("\\s+");
                for(int i=0; i<5; i++) {
                    b.nums[line][i] = new Num(Integer.valueOf(sline[i]));
                }
            }
            showBoard(b);
            if(s != null)
                boards.add(b);
        }

        ArrayList<Long> result = new ArrayList();
        boolean[] win = new boolean[boards.size()];

        for(String snum:numbers) {
            int next = Integer.valueOf(snum);
            for(int i=0; i<boards.size(); i++) {
                Board b = boards.get(i);
                long res = round(b, next);

                if(res > 0 && !win[i]) {
                    result.add(res);
                    win[i] = true;
                }
            }
        }

        System.out.println(result.get(result.size()-1));
    }

    static void showBoard(Board b) {
        System.out.println("");
        for(int line=0; line<5; line++) {
            for(int col=0; col<5; col++) {
                System.out.printf("%3d ",b.nums[line][col].val);
            }
            System.out.println("");
        }
    }

    static long round(Board b, int num) {
        for(int line=0; line<5; line++) {
            for(int col=0; col<5; col++) {
                if(b.nums[line][col].val == num)
                    b.nums[line][col].checked = true;
            }
        }

        for(int line=0; line<5; line++) {
            int checkedcols = 0;
            for(int col=0; col<5; col++) {
                if(b.nums[line][col].checked)
                    checkedcols++;
            }
            if(checkedcols == 5) {
                return calcres(b, num);
            }
        }

        for(int col=0; col<5; col++) {
            int checkedlines = 0;
            for(int line=0; line<5; line++) {
                if(b.nums[line][col].checked)
                    checkedlines++;
            }
            if(checkedlines == 5) {
                return calcres(b, num);
            }
        }
        return -1;
    }


    static long calcres(Board b, int num) {
        long sum = 0;
        for (int line = 0; line < 5; line++) {
            for (int col = 0; col < 5; col++) {
                if (b.nums[line][col].checked == false)
                    sum += b.nums[line][col].val;
            }
        }
        return num * sum;
    }
}
