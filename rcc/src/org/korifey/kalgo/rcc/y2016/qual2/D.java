//package org.korifey.kalgo.rcc.y2016.qual2;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;

public class D {
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



    static int[][] memo;
    static int[][] a;
    static int n;

    static int f(int k, int mask) {
        if (memo[k][mask] != 0) return memo[k][mask];

        int best = 100;
        for (int i=0; i<n; i++) {
            if ((mask & (1 << i)) != 0) {
                int probe = f ((k+1)%3, mask ^ (1 << i));
                int j = 0;
                for (; j<n; j++) {
                    if (a[k][j] == probe) break;
                }
                if (j < best) best = j;
            }
        }
        memo[k][mask] = a[k][best];
        return memo[k][mask];
    }

    public static void main(String[] args) throws Exception{
        MyScanner scanner = new MyScanner();

        int ncases = scanner.nextInt();
        for (int ncase=0; ncase<ncases; ncase++) {
            n = scanner.nextInt();



            memo = new int[3][];
            a = new int[3][];
            for (int i = 0; i < 3; i++) {
                a[i] = new int[n];
                memo[i] = new int[1 << n];
            }

            for (int i = 0; i < 3; i++) {
                for (int k=0; k<n; k++) {
                    memo[i][1<<k] = k+1;
                    a[i][k] = scanner.nextInt();
                }
            }

            int res = f(0, (1<<n)-1);
            System.out.println(res);
        }
    }
}
