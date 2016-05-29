package org.korifey.kalgo.rcc.y2016.qual1;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B {
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


    public static void main(String[] args) {

        MyScanner scanner = new MyScanner();
        int ncases = scanner.nextInt();
        for (int casen=0; casen<ncases; casen++) {
            int n = scanner.nextInt();
            long h = scanner.nextLong();
            long[] a = new long[n];
            for (int i=0; i<n; i++) {
                a[i] = scanner.nextLong();
            }

            int res = 0;
            long acc = 0;


            boolean f = scanner.nextInt() == 1;
            if (!f) res+=1;

            for (int i=1; i<n-1; i++) {
                f = scanner.nextInt() == 1;
                if (f) {
                    acc = 0;
                } else {
                    if (a[i]+acc >= h) {
                        res++;
                        acc = 0;
                    } else {
                        acc += a[i];
                    }
                }
            }

            if (n > 1) {
                f = scanner.nextInt() == 1;
                if (!f) res+=1;
            }

            System.out.println(res);
        }
    }
}
