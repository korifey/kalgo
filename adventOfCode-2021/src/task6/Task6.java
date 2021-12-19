package task6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Task6 {
    static ArrayList<Integer> base = new ArrayList<>();
    static ArrayList<Integer> added;

    public static void main(String[] args) throws Exception {
        BufferedReader inp = new BufferedReader(new FileReader("adventOfCode-2021/resources/input6.txt"));
        String s;
        s = inp.readLine();
        String[] sdata = s.split(",");
        for(String svalue:sdata)
        base.add(Integer.valueOf(svalue));
        for (int i = 1; i <= 80; i++) {
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
            System.out.println(i+" done");
//            System.out.println(i + ":\t" + base);
        }
        System.out.println(base.size());
    }
}
