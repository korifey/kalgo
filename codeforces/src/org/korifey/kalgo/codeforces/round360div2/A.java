package org.korifey.kalgo.codeforces.round360div2;

import java.io.*;
import java.util.StringTokenizer;

public class A {

    private static class MyScanner {
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

        String nextLine() {
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
        int d = scanner.nextInt();
        int cur = 0, max = 0;
        for (int i = 0; i < d; i++) {
            boolean win = scanner.next().contains("0");
            if (win) cur++;
            else {
                if (cur > max) max = cur;
                cur = 0;
            }
        }
        if (cur > max) max = cur;
        System.out.println(max);
    }
}