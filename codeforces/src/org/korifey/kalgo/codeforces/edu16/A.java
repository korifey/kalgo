package org.korifey.kalgo.codeforces.edu16;

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
        String s = scanner.nextLine();
        int v = s.charAt(0)-'a' + 1;
        int h = s.charAt(1) - '0';

        int res = 8;
        if (v == 1 || v == 8) {
            if (h == 1 || h == 8) out.println(3);
            else out.println(5);
        } else {
            if (h == 1 || h == 8) out.println(5);
            else out.println(8);
        }



        //body ends

        out.close();
    }
}