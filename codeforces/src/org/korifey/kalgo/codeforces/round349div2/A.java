package org.korifey.kalgo.codeforces.round349div2;

import java.util.Scanner;

public class A {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double d = scanner.nextDouble();
        double h = scanner.nextDouble();
        double v = scanner.nextDouble();
        double e = scanner.nextDouble();

        double x = 4*v-Math.PI*d*d*e;

        if (x <= 0) System.out.println("NO");
        else {
            System.out.println("YES");
            double res = Math.PI*d*d*h/x;
            System.out.println(res);
        }

    }
}
