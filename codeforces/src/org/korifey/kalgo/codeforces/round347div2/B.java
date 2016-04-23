package org.korifey.kalgo.codeforces.round347div2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class B {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        s.useDelimiter("\\s+=\\s+|\\s*\\n");
        String left = s.next();
        String right = s.next();
        int n = Integer.parseInt(right);

        int plusCount = 1 + left.length() - left.replaceAll("\\+", "").length();
        int minusCount =  left.length() - left.replaceAll("\\-", "").length();

//        System.out.println(plusCount+" "+minusCount);
        if (n * plusCount - minusCount < n ||
                plusCount - n * minusCount > n) {
            System.out.println("Impossible");
        } else {
            System.out.println("Possible");
            boolean sign;
            LinkedList<Integer> res = new LinkedList<>();

            sign =  (plusCount - minusCount <= n);  //add plus


            int sum = (n + minusCount - plusCount) * (sign ? 1 : -1);
            while (sum > 0) {
                int x = Math.min(n, sum + 1);
                sum -= x-1;
                res.add(x);
            }




            StringBuilder builder = new StringBuilder();
            boolean plus = true;
            for (char c: left.toCharArray()) {
                if (c == '?') {
                    if (res.isEmpty() || sign != plus) builder.append("1");
                    else builder.append(res.removeFirst());
                } else {
                    if (c == '+') plus = true;
                    if (c == '-') plus = false;
                    builder.append(c);
                }

            }
            System.out.println(builder.toString() + " = "+right);
        }
    }
}
