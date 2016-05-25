package org.korifey.kalgo.codeforces.round354div2;

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
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        char[] s = scanner.next().toCharArray();

        int max = Math.max(getMax(n, k, s, 'a', 'b'), getMax(n, k, s, 'b', 'a'));

        out.println(max);
        //body ends

        out.close();
    }

    private static int getMax(int n, int k, char[] s, char c1, char c2) {
        int max = 0;
        int p1 = 0;
        int p2 = 0;
        int d = 0;
        for (p2=0; p2<n; p2++) {
            if (s[p2] == c2) d++;
            if (d == k + 1) break;
        }

        max = p2 - p1;
        while (p2 < n) {
            while (p1 < n && s[p1] == c1) p1++;
            p1++;

            p2 ++;
            while (p2 < n && s[p2] == c1) p2++;
            max = Math.max(max, p2 - p1);
        }
        return max;
    }
}