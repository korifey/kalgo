//package org.korifey.kalgo.rcc.y2016.qual2;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class A {
    public static class MyScanner {
        BufferedReader br;
        StringTokenizer st;

        public MyScanner() {
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

        String nextLine(){
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }

    }

    public static void main(String[] args) throws Exception{
        MyScanner scanner = new MyScanner();

        int ncases = scanner.nextInt();

        for (int ncase=0; ncase<ncases; ncase++) {
            int n  = scanner.nextInt();
            int q  = scanner.nextInt();
            boolean[] a =  new boolean[n];

            for (int i=0; i<q; i++) {

                String[] ss = scanner.nextLine().split(" ");
                int p = Integer.parseInt(ss[1]);
                if (ss[0].equals("-")) {
                    a[p-1] = true;
                    a[n-p] = true;
                } else {
                    int j = 0, acc = 0;
                    while (acc < p) {
                        while (a[j]) {
                            j++;
                        }
                        j++;
                        acc++;
                    }
                    System.out.println(j);
                }
            }

        }

    }
}
