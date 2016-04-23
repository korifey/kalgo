package org.korifey.kalgo.codeforces.round347div2;

import java.util.Scanner;

public class A {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        s.useDelimiter("\\s+");
        String s1 = s.next();
        String s2 = s.next();
        if (s1.equals(s2)) System.out.print(s1);
        else System.out.print("1");
    }
}
