package org.korifey.kalgo.rcc.y2016.qual;


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
        Scanner scanner = new Scanner(System.in);

        int ncases = scanner.nextInt();

        for (int xx=0;xx<ncases;xx++) {
            int n00 = scanner.nextInt();
            int n01 = scanner.nextInt();
            int n10 = scanner.nextInt();
            int n11 = scanner.nextInt();

            if (Math.abs(n10 - n01) > 1) {
                System.out.print("impossible");
            } else if (n10 == 0 && n01 == 0) {
                if (n00 > 0 && n11 > 0) {
                    System.out.print("impossible");
                } else if (n00 == 0 && n11 == 0) {
                    System.out.print("");
                } else {
                    if (n00 > 0) for (int i = 0; i <= n00; i++) System.out.print('0');
                    if (n11 > 0) for (int i = 0; i <= n11; i++) System.out.print('1');
                }
            } else {

                if (n10 == n01) {
                    for (int i = 0; i <= n00; i++) System.out.print('0');
                    for (int i = 0; i <= n11; i++) System.out.print('1');
                    System.out.print('0');
                    for (int i = 1; i < n10; i++) {
                        System.out.print("10");
                    }
                } else if (n01 > n10) {
                    for (int i = 0; i <= n00; i++) System.out.print('0');
                    for (int i = 0; i <= n11; i++) System.out.print('1');
                    for (int i = 0; i < n10; i++) System.out.print("01");
                } else {
                    for (int i = 0; i <= n11; i++) System.out.print('1');
                    for (int i = 0; i <= n00; i++) System.out.print('0');
                    for (int i = 0; i < n01; i++) System.out.print("10");
                }
            }
            System.out.println();
        }
    }
}
