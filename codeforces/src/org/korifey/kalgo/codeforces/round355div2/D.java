//package org.korifey.kalgo.codeforces.round355div2;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class D {

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


    static class Chest implements Comparable<Chest>{
        int r,c;
        long dist;

        public Chest(int r, int c) {
            this.r = r;
            this.c = c;
            this.dist = Long.MAX_VALUE;
        }

        @Override
        public int compareTo(Chest o) {
            return Long.compare(dist, o.dist);
        }

        public boolean update(Chest prev) {
            if (prev.dist >= dist) return false;
            long cand = prev.dist + Math.abs(r - prev.r) + Math.abs(c - prev.c);
            if (cand < dist) dist = cand;
            return true;
        }
    }

    public static void main(String[] args) {
        MyScanner scanner = new MyScanner();
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        //body starts
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int p = scanner.nextInt();

        ArrayList<Chest>[] layers = new ArrayList[p+1];
        for (int l =0; l<= p; l++) layers[l] = new ArrayList<>();

        Chest c0 = new Chest(0, 0);
        c0.dist = 0;
        layers[0].add(c0);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int x = scanner.nextInt();
                layers[x].add(new Chest(i, j));
            }
        }

        for (int l =1; l<= p; l++) {
            Collections.sort(layers[l-1]);
            cycle: for (Chest chest: layers[l]) {
                for (Chest prev: layers[l-1]) {
                    if (!chest.update(prev)) continue cycle;
                }
            }
        }



        System.out.println(layers[p].get(0).dist);

        //body ends

        out.close();
    }
}