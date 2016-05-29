//package org.korifey.kalgo.yandex.y2016.warmup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by User on 5/10/2016.
 */
public class AlphabeticalEMail {
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

    public static void main(String[] args) {
        MyScanner scanner = new MyScanner();
        String s = scanner.next();

        String vowels = "aeiouy";
        char vow = 0, con = 0;
        for (char c: s.toCharArray()) {
            if (vowels.indexOf(c) >= 0) {
                if (vow == 0) vow = c;
            } else {
                if (con == 0) con = c;
            }
        }

        if (vow != 0 && con != 0) {
            System.out.println(vow < con ? "Consonant" : "Vowel");
        } else if (vow > 0) {
            System.out.println("Vowel");
        } else {
            System.out.println("Consonant");
        }

    }
}
