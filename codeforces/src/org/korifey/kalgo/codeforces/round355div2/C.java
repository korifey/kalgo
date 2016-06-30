//package org.korifey.kalgo.codeforces.round355div2;

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
        char[] cc = scanner.next().toCharArray();
        long res = 1;
        for (char c: cc) {
            int x;
            if (c >='0' && c <= '9') x = c - '0';
            else if (c >= 'A' && c <= 'Z') x = c - 'A' + 10;
            else if (c >= 'a' && c <= 'z') x = c - 'a' + 36;
            else if (c == '-') x = 62;
            else if (c == '_') x = 63;
            else throw new IllegalArgumentException();

            for (int i=0; i<6; i++) {
                if ((x & 1) == 0) res *= 3;
                x /= 2;
            }
            res %= 1000000007;
        }
        System.out.println(res);

        //body ends

        out.close();
    }
}