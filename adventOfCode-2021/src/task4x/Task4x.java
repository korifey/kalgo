package task4x;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

class Num {
    int val;
    boolean checked = false;
    public Num(int val) {
        this.val=val;
    }
}

class Board {
    long res=0;
    int step=-1;
    boolean won=false;
    public Num[][] nums = new Num[5][5];
}

public class Task4x {

    public static void main(String[] args) throws Exception {
        ArrayList<Board> boards = new ArrayList<>();
        BufferedReader inp = new BufferedReader(new FileReader("adventOfCode-2021/resources/input4x.txt"));
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



        for(int step=0; step<numbers.length; step++) {
            int next = Integer.valueOf(numbers[step]);
            for(int i=0; i<boards.size(); i++) {
                Board b = boards.get(i);
                if(!b.won) {
                    round(b, next, step);
//                    System.out.println("\nДоска "+i+"; next="+next);
//                    showCheckedBoard(b);
                }
            }
        }
        int step = -1;
        Board last=null;
        int lasti = 0;
        for(int i=0; i<boards.size(); i++) {
            Board b = boards.get(i);
            if(b.won && b.step > step) {
                last = b;
                lasti = i;
                step=b.step;
//                System.out.println("\n finish: board "+i);
//                showCheckedBoard(b);
            }
        }
        System.out.println(last.res);
    }


    static long round(Board b, int num, int step) {
        for (int line = 0; line < 5; line++) {
            for (int col = 0; col < 5; col++) {
                if (b.nums[line][col].val == num) {
                    b.nums[line][col].checked = true;
                    check(b, num, line, col, step);
                }
            }
        }
        return -1;
    }

    static long check(Board b, int num, int line, int col, int step) {
        int checkedcols = 0;
        for(int x=0; x<5; x++) {
            if(b.nums[line][x].checked)
                checkedcols++;
        }
        if(checkedcols == 5) {
            b.res = calcres(b, num);
            b.step = step;
            b.won = true;
        }

        int checkedlines = 0;
        for(int y=0; y<5; y++) {
            if(b.nums[y][col].checked)
                checkedlines++;
        }
        if(checkedlines == 5) {
            b.res = calcres(b, num);
            b.step = step;
            b.won = true;
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

    static void showBoard(Board b) {
        System.out.println("");
        for(int line=0; line<5; line++) {
            for(int col=0; col<5; col++) {
                System.out.printf("%3d ",b.nums[line][col].val);
            }
            System.out.println("");
        }
    }

    static void showCheckedBoard(Board b) {
        System.out.println("");
        for(int line=0; line<5; line++) {
            for(int col=0; col<5; col++) {
                if(b.nums[line][col].checked)
                    System.out.printf("%3d ",b.nums[line][col].val);
                else
                    System.out.printf("    ");
            }
            System.out.println("");
        }
    }

}
