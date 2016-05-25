package org.korifey.kalgo.codeforces.round354div2;

import java.io.*;
import java.util.StringTokenizer;

public class B {

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
        int tt = scanner.nextInt();

        double[][] a = new double[n][];
        boolean[][] b = new boolean[n][];
        for (int i = 0; i < a.length; i++) {
            a[i] = new double[i+1];
            b[i] = new boolean[i+1];
        }

        int res = 0;

        double eps = 1E-8;
        for (int t=1; t<=tt; t++) {
            if (res == n*(n+1)/2) break;

            a[0][0] += 1;
            for (int i=0; i<n; i++) {
                for (int j=0; j<=i; j++) {
                    if (a[i][j] > 1-eps && !b[i][j]) {
                        b[i][j] = true;
                        res ++;
                    }

                    if (i < n-1 && a[i][j] > 1+eps) {
                        double x = a[i][j] - 1;
                        a[i][j] = 1;
                        a[i+1][j] += x/2;
                        a[i+1][j+1] += x/2;
                    }
                }
            }
        }
        out.println(res);

        //body ends

        out.close();
    }
}