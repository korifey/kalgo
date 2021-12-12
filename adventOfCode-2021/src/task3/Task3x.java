package task3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Task3x {
    int razr;

    public static void main(String[] args) throws Exception {
        ArrayList<String> source = new ArrayList<String>();
        BufferedReader inp = new BufferedReader(new FileReader("adventOfCode-2021/resources/input3x.txt"));
        String s;
        int razr = 0;
        long val;
        int bits[];
        while ((s = inp.readLine()) != null) {
            if (razr == 0) {
                razr = s.trim().length();
            }
            source.add(s.trim());
        }
        inp.close();

        ArrayList<String> oxygen = new ArrayList<>(source);
        for (int bit = 0; bit < razr; bit++) {
            oxygen = filterMax(oxygen, bit);
            if (oxygen.size() == 1)
                break;
        }
        int oxy = 0;
        int and = 0;
        for(int i=0; i<razr; i++) {
            oxy |= (oxygen.get(0).charAt(razr-1-i)-'0')<<i;
            and |= 1<<i;
        }

        ArrayList<String> co2 = new ArrayList<>(source);
        for (int bit = 0; bit < razr; bit++) {
            co2 = filterMin(co2, bit);
            if (co2.size() == 1)
                break;
        }
        int co = 0;
        for(int i=0; i<razr; i++) {
            co |= (co2.get(0).charAt(razr-1-i)-'0')<<i;
        }

        System.out.println("res="+(oxy*co));

    }

    static ArrayList<String> filterMax(ArrayList<String> from, int bit) {
        int count =0;
        char needvalue;
        for(String s:from) {
            if(s.charAt(bit) == '1')
                count++;
            else
                count--;
        }
        needvalue = (count>=0)?'1':'0';
        ArrayList<String> res = new ArrayList<>();
        for(String s:from) {
            if(s.charAt(bit) == needvalue)
                res.add(s);
        }
        return res;
    }

    static ArrayList<String> filterMin(ArrayList<String> from, int bit) {
        int count =0;
        char needvalue;
        for(String s:from) {
            if(s.charAt(bit) == '1')
                count++;
            else
                count--;
        }
        needvalue = (count>=0)?'0':'1';
        ArrayList<String> res = new ArrayList<>();
        for(String s:from) {
            if(s.charAt(bit) == needvalue)
                res.add(s);
        }
        return res;
    }

}
