package task2;

import java.io.BufferedReader;
import java.io.FileReader;

public class Task2 {
    public static void main(String[] args) throws  Exception {
        BufferedReader inp = new BufferedReader(new FileReader("adventOfCode-2021/resources/input2.txt"));
        String s;
        long deep = 0;
        long distance = 0;
        long val;
        while((s=inp.readLine()) != null) {
            String[] pair = s.split("\\s+");
            val = Long.parseLong(pair[1]);
            switch (pair[0]) {
                case "up": deep -= val; break;
                case "down": deep += val; break;
                case "forward": distance += val; break;
                default:
                    System.out.println("unknown command:"+pair[0]);
                    break;
            }
        }
        inp.close();
        long res = deep*distance;
        System.out.println(res);
    }
}
