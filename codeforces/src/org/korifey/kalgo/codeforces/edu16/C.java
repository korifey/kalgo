package org.korifey.kalgo.codeforces.edu16;

import java.io.*;
import java.util.StringTokenizer;

public class C {

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

        int[][] b = new int[50][50];
        int n = scanner.nextInt();

        int c = n / 2;
        for (int i=0; i<n; i++) {
            b[c][i] = b[i][c] = 1;
        }
        int f = 1;
        for (int k = 0; k <= c; k ++) {
            for (int i=0; i+c+k<n; i++) {
                b[c+k][c+k+i] = f;
                b[c+k+i][c+k] = f;

                b[c-k][c-k-i] = f;
                b[c-k-i][c-k] = f;

                b[c-k][c+k+i] = f;
                b[c-k-i][c+k] = f;

                b[c+k][c-k-i] = f;
                b[c+k+i][c-k] = f;
            }
            f = 1 - f;
        }


        int odd = -1, even = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (b[i][j] == 0) {
                    out.print(even+=2);
                } else {
                    out.print(odd+=2);
                }
                out.print(' ');

            }
            out.println();
        }

        //body ends

        out.close();
    }
}