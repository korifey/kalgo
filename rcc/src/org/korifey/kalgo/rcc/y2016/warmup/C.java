package org.korifey.kalgo.rcc.y2016.warmup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class C {


     static class MyScanner {
        BufferedReader br;
        StringTokenizer st;

        MyScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine(){
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }

    }


    public static void main(String[] args) {
        MyScanner scanner = new MyScanner();
        int n = scanner.nextInt();
        int m = scanner.nextInt();
//        int[] a = new int[n*m];

        long[] rows = new long[n];
        long[] cols = new long[m];
        long sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                long x = scanner.nextLong();
                rows[i] += x;
                cols[j] += x;
                sum += x;
            }
        }

        int x = -1;
        long l = 0;
        for (int i=0; i<n; i++) {
            l += rows[i];
            if (l >=sum-l) {
                x = i;
                break;
            }
        }

        int y = -1;
        l = 0;
        for (int j=0; j<m; j++) {
            l += cols[j];
            if (l >=sum-l) {
                y = j;
                break;
            }
        }

        long res = 2*sum;
        for (int i=0; i<n; i++) {
            res += 2*rows[i]*Math.abs(x-i);
        }
        for (int j=0; j<m; j++) {
            res += 2*cols[j]*Math.abs(y-j);
        }

        System.out.println((x+1)+" "+(y+1)+" "+res);
    }
}
