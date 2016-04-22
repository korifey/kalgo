//package org.korifey.kalgo.codeforces.edu15;

import java.util.Arrays;
import java.util.Scanner;

public class D {

    private static boolean[] eratos(int n) {
        boolean[] res = new boolean[n+1];

        Arrays.fill(res, true);
        res[0] = false;
        res[1] = false;

        for (int s = 2; s*s <= n; s++) {
            if (!res[s]) continue;
            for (int i=s*s; i<=n; i+= s) {
                res[i] = false;
            }
        }
        return res;
    }

    public static void main(String[] args) {

        boolean[] prime = eratos(2000000);


        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i=0; i<n; i++) a[i] = scanner.nextInt();

        Arrays.sort(a);

        if (a.length == 1) {
                System.out.println(1);
                System.out.print(a[0]);

            return;
        }

        int idx = 0;
        while (idx<n && a[idx] == 1) idx++;

        if (idx == n) idx = n-2;
        else idx = idx-1;
        if (idx <0) idx = 0;

        for (int i=idx; i<n; i++) {
            for (int j=i+1; j<n; j++) {
                if (prime[a[i]+a[j]]) {
                    if (a [i] == 1) {
                        System.out.println(i+2);
                        for (int k=0; k<i; k++) System.out.print(1+" ");
                        System.out.print(a[i]+" ");
                        System.out.print(a[j]);
                    } else {
                        if (a[0] == 1 && a[1] == 1) {
                            System.out.println(idx+1);
                            for (int k=0; k<=idx; k++) System.out.print(1+" ");
                        } else {
                            System.out.println(2);
                            System.out.print(a[i]+" ");
                            System.out.print(a[j]);
                        }
                    }

                    return;
                }
            }
        }

        if (a[0] != 1 ) {
            System.out.println(1);
            System.out.print(a[0]);
        } else {
            idx = 0;
            while (idx<n && a[idx] == 1) idx++;
            System.out.println(idx);
            System.out.print(1);
            for (int i=1; i<idx; i++) System.out.print(" 1");
        }
    }
}
