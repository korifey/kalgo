//package org.korifey.kalgo.codeforces.round352div2;

import java.io.*;
import java.util.StringTokenizer;

public class C {

    private static class MyScanner {
        BufferedReader br;
        StringTokenizer st;

        MyScanner() {
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

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }

    }


    static double[] px = null;
    static double[] py = null;

    static double dist(double x, double y, int i) {
        return Math.sqrt( (x-px[i])*(x-px[i]) + (y-py[i])*(y-py[i]));
    }

    public static void main(String[] args) {
        MyScanner scanner = new MyScanner();
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        //body starts
        double ax = scanner.nextDouble();
        double ay = scanner.nextDouble();
        double bx = scanner.nextDouble();
        double by = scanner.nextDouble();
        double tx = scanner.nextDouble();
        double ty = scanner.nextDouble();

        int n = scanner.nextInt();
        px = new double[n];
        py = new double[n];

        double sum = 0;
        for (int i = 0; i < n; i++) {
            px[i] = scanner.nextDouble();
            py[i] = scanner.nextDouble();
            sum += 2*dist(tx, ty, i);
        }

        double bestDist1 = sum - dist(tx, ty, 0) + dist(ax, ay, 0);
        int best = 0;
        for (int i = 1; i < n; i++) {
            double d = sum - dist(tx, ty, i) + dist(ax, ay, i);
            if (d < bestDist1) {
                bestDist1 = d;
                best = i;
            }
        }

        double dd = bestDist1;
        for (int i = 0; i < n; i++) {
            if (i != best) {
                double d = dd - dist(tx, ty, i) + dist(bx, by, i);
                if (d < bestDist1) bestDist1 = d;
            }
        }


        double bestDist2 = sum - dist(tx, ty, 0) + dist(bx, by, 0);
        best = 0;
        for (int i = 1; i < n; i++) {
            double d = sum - dist(tx, ty, i) + dist(bx, by, i);
            if (d < bestDist2) {
                bestDist2 = d;
                best = i;
            }
        }

        dd = bestDist2;
        for (int i = 0; i < n; i++) {
            if (i != best) {
                double d = dd - dist(tx, ty, i) + dist(ax, ay, i);
                if (d < bestDist2) bestDist2 = d;
            }
        }

        double res = Math.min(bestDist1, bestDist2);
        System.out.println(res);


        //body ends

        out.close();
    }
}