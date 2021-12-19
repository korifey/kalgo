package task6x;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;


public class Task6x {

    ArrayList<Integer>[] digits = new ArrayList[9];

    public Task6x() throws Exception {
        ArrayList<Integer> init = new ArrayList<>();
        ArrayList<Integer> base;
        ArrayList<Integer> added;

        ArrayList<Integer> init2;
        ArrayList<Integer> base2;
        ArrayList<Integer> added2;

        fillDigits();
        System.out.println("digits filled");

        long startTime = System.currentTimeMillis();
        BufferedReader inp = new BufferedReader(new FileReader("adventOfCode-2021/resources/input6x.txt"));
        String s;
        s = inp.readLine();
        String[] sdata = s.split(",");
        long total = 0;
        int tmpindicator = 0;
        System.out.println("initial fishes:" + sdata.length);
        for (String svalue : sdata)
            init.add(Integer.valueOf(svalue));

        for (int first = 0; first < init.size(); first++) {
            base = digits[init.get(first)];
//            System.out.println("first: " + first + "; base size:" + base.size());
            for (int second = 0; second < base.size(); second++) {
                base2 = new ArrayList<>();
                base2 = digits[base.get(second)];
                total += base2.size();
//                if ((second % 2000) == 0)
//                    System.out.println("second: " + second);
            }
//            tmpindicator++;
//            System.out.println("done " + tmpindicator + "; left" + (sdata.length - tmpindicator));
//            System.out.println("seconds passed from begin:" + (System.currentTimeMillis() - startTime) / 1000);
        }
        System.out.println(total);
        // 1609314870967
    }

    public static void main(String[] args) throws Exception {
        new Task6x();
    }

    void fillDigits() {
        for(int i=0; i<=8; i++) {
            digits[i] = get128(i);
        }
    }

    ArrayList<Integer> get128(int n) {
        ArrayList<Integer> base;
        ArrayList<Integer> added;
        base = new ArrayList<>();
        base.add(n);
        for (int i = 1; i <= 128; i++) {
            added = new ArrayList<>();
            for (int el = 0; el < base.size(); el++) {
                if (base.get(el) == 0) {
                    added.add(8);
                    base.set(el, 6);
                } else {
                    base.set(el, base.get(el) - 1);
                }
            }
            base.addAll(added);
        }
        return base;
    }
}
