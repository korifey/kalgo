package task3;

import java.io.BufferedReader;
import java.io.FileReader;

public class Task3 {
    public static void main(String[] args) throws  Exception {
        BufferedReader inp = new BufferedReader(new FileReader("adventOfCode-2021/resources/input3.txt"));
        String s;
        int razr=0;
        long val;
        int bits[] = new int[5];
        while((s=inp.readLine()) != null) {
            if(razr == 0) {
                razr = s.trim().length();
                bits = new int[razr];
            }

            for (int i = 0; i < razr; i++) {
                if (s.charAt(i) == '1')
                    bits[i]++;
                else
                    bits[i]--;
            }
        }
        inp.close();
        int gamma=0, epsilon=0;
        for(int i=0; i<razr; i++) {
            if (bits[i] > 0) bits[i]=1;
            else if (bits[i] < 0) bits[i] = 0;
            else {
                System.out.println("bits count for bit " + i + " are equal for 0 and for 1, assuming 1!");
                bits[i] = 1;
            }
        }
        int and = 0;
        for(int i=0; i<razr; i++) {
            gamma |= bits[razr-1-i]<<i;
            and |= 1<<i;
        }


        epsilon = (~gamma) & and;
        int res = epsilon * gamma;
        System.out.println(res);
    }
}
