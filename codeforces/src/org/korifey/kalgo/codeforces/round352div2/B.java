package org.korifey.kalgo.codeforces.round352div2;

import java.io.*;
import java.util.HashSet;
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

        int n  = scanner.nextInt();
        String s = scanner.next();
        if (n > 26) {
            System.out.println(-1);
            return;
        }
        HashSet<Character> set = new HashSet<>();
        for (int i=0; i<n; i++) set.add(s.charAt(i));

        System.out.println(n-set.size());

        //body ends

        out.close();
    }
}