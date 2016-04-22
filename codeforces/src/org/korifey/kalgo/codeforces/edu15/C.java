package org.korifey.kalgo.codeforces.edu15;

import java.util.Scanner;

public class C {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();

        char prev = ' ';
        char[] arr = new char[] {'a','b','c'};
        for (int i=0; i < s.length(); i++) {

            char c = s.charAt(i);
            if (prev != c) {
                System.out.print(c);
                prev = c;
            }
            else {
                for (char c1: arr) {
                    if (c1 != prev && (i + 1 >= s.length() || s.charAt(i+1) != c1)) {
                        System.out.print(c1);
                        prev = c1;
                        break;
                    }
                }

            }
        }
    }
}
