package org.korifey.kalgo.codeforces.edu16;

import java.io.*;
import java.util.Arrays;
import java.util.Random;
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

        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }


//        int n= 300000;
//        int [] a = new int[n];
//        Random r = new Random();
//        for (int i = 0; i < n; i++) {
//            a[i] = r.nextInt(Integer.MAX_VALUE);
//        }

        Arrays.sort(a);
        out.println(a[(a.length-1)/2]);



        //body ends

        out.close();
    }
}