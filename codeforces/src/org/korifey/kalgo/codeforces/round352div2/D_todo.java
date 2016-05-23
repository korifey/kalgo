package org.korifey.kalgo.codeforces.round352div2;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class D_todo {

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
        long k = scanner.nextLong();

        long[] a = new long[n];
        long sum = 0;
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
            sum += a[i];
        }
        Arrays.sort(a);

        long diff = 0;
        for (long aElem : a) {
            long dd = aElem * n - sum;
            if (dd > 0) diff += dd;
        }

        if (k*n >= diff) {
            System.out.println(diff % n == 0 ? "0" : "1");
            return;
        }

        //todo


        //body ends

        out.close();
    }
}