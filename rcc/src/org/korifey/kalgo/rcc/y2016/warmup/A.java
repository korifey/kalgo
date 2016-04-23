package org.korifey.kalgo.rcc.y2016.warmup;

import java.util.Scanner;

public class A {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        boolean[] ff = new boolean[256];
        for (char c: s.toCharArray()) ff[(int)c] = true;

        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            String p = scanner.next();
            int a = 0, b = 0;
            for (int j=0; j<p.length(); j++) {
                if (s.charAt(j) == p.charAt(j)) a++;
                else if (ff[p.charAt(j)]) b++;
            }
            System.out.println(a+" "+b);
        }
    }
}
