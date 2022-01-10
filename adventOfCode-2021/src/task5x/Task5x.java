package task5x;

import java.io.BufferedReader;
import java.io.FileReader;

public class Task5x {
    static int field[][] = new int[1000][1000];

    public static void main(String[] args) throws Exception {
        BufferedReader inp = new BufferedReader(new FileReader("adventOfCode-2021/resources/input5.txt"));
        String s="";
        try {
            while ((s = inp.readLine()) != null) {
                if (s.trim().length() == 0)
                    continue;
                String[] s3 = s.split("\\s+");
                String[] pair1 = s3[0].trim().split(",");
                String[] pair2 = s3[2].trim().split(",");
                int x1 = Integer.valueOf(pair1[0]);
                int y1 = Integer.valueOf(pair1[1]);
                int x2 = Integer.valueOf(pair2[0]);
                int y2 = Integer.valueOf(pair2[1]);

                if (x1 == x2) {
                    if (y1 > y2) {
                        int z = y1;
                        y1 = y2;
                        y2 = z;
                    }
                    for (int y = y1; y <= y2; y++) {
                        field[x1][y]++;
                    }
                } else {
                    if (y1 == y2) {
                        if (x1 > x2) {
                            int z = x1;
                            x1 = x2;
                            x2 = z;
                        }
                        for (int x = x1; x <= x2; x++) {
                            field[x][y1]++;
                        }
                    } else {
                        if (x1 > x2) {
                            int zx = x1;
                            int zy = y1;
                            x1 = x2;
                            x2 = zx;
                            y1 = y2;
                            y2 = zy;
                        }
                        int step = 1;
                        if (y2 < y1)
                            step = -1;

                        int x=x1, y=y1;
                        while(x <= x2) {
                            field[x][y]++;
                            y += step;
                            x += 1;
                        }
/*
                        if (y != y2) {
                            System.out.println(s + " : "+ x1+","+y1+" -> "+x2+","+y2+" : y("+y+") != y2("+y2+")");
                        }
*/
                    }
                }
            }
            inp.close();
            int count = 0;
            for (int x = 0; x < 1000; x++) {
                for (int y = 0; y < 1000; y++) {
                    if (field[x][y] > 1)
                        count++;
                }
            }
            System.out.println(count);
        } catch (Exception e) {
            System.out.println("line: "+s);
            e.printStackTrace();
        }

    }
}
