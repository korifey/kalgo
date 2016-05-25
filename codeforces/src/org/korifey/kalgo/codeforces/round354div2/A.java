package org.korifey.kalgo.codeforces.round354div2;

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
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        //body starts
        int n = scanner.nextInt();

        int pos1 = -1, pos2 = -1;
        for (int i = 0; i < n; i++) {
            int x = scanner.nextInt();
            if (x == 1) pos1 = i;
            if (x == n) pos2 = i;
        }

        if (pos1 > pos2) {
            int s = pos1;
            pos1 = pos2;
            pos2 = s;
        }

        out.println(Math.max(pos2, n-1-pos1));




        //body ends

        out.close();
    }
}