//package org.korifey.kalgo.codeforces.edu16;

import java.io.*;
import java.util.StringTokenizer;

public class E {

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
        long x = scanner.nextLong();
        long y = scanner.nextLong();
        long[] a = new long[n+1];
        a[1] = x;
        for (int i=2; i<=n; i++) {
            if (i%2 == 1) a[i] = a[i-1] + x;
            else a[i] = Math.min(a[i-1] + x, a[i/2] + y);
        }

        out.println(a[n]);
        //body ends

        out.close();
    }
}