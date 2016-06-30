//package org.korifey.kalgo.codeforces.round355div2;

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
        long h = scanner.nextLong();
        long k = scanner.nextLong();

        long cur = scanner.nextLong();
        long res = 0;
        for (int i = 1; i < n; i++) {
            res += cur / k;
            cur = cur % k;

            long x = scanner.nextLong();
            if (cur + x <= h) cur+=x;
            else {
                res++;
                cur = x;
            }
        }

        System.out.println(res + cur / k + ((cur % k >0)? 1: 0));


        //body ends

        out.close();
    }
}