package org.korifey.kalgo.codeforces.edu15;

import java.util.Scanner;

public class A {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in).useDelimiter("(:|\\s)+");
        scanner.nextInt();
        int ta = scanner.nextInt();
        int b = scanner.nextInt();
        int tb = scanner.nextInt();

        int hh = scanner.nextInt();
        int mm = scanner.nextInt();

        int sa = hh*60 + mm;
        int ea = sa + ta;

        int res = 0;
        for (int sb=5*60; sb <= 23*60 + 59; sb+= b) {
            int eb = sb + tb;

            if (eb > sa && ea > sb) res++;
        }
        System.out.print(res);

    }
}
