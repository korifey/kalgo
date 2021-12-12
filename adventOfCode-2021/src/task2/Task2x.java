package task2;

import java.io.BufferedReader;
import java.io.FileReader;

public class Task2x {
    public static void main(String[] args) throws  Exception {
        BufferedReader inp = new BufferedReader(new FileReader("adventOfCode-2021/resources/input2.txt"));
        String s;
        long aim = 0;
        long deep = 0;
        long distance = 0;
        long val;
        while((s=inp.readLine()) != null) {
            String[] pair = s.split("\\s+");
            val = Long.parseLong(pair[1]);
            switch (pair[0]) {
                case "up": aim -= val; break;
                case "down": aim += val; break;
                case "forward":
                    deep += val*aim;
                    distance += val;
                break;
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
