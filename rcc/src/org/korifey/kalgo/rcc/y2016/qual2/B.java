//package org.korifey.kalgo.rcc.y2016.qual2;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
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

    public static void main(String[] args) throws Exception{
        MyScanner scanner = new MyScanner();

        int ncases = scanner.nextInt();

        for (int ncase=0; ncase<ncases; ncase++) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            int[] a = new int[n];
            int[] b = new int[m];
            for (int i = 0; i < n; i++) {
                a[i] = scanner.nextInt();
            }
            for (int i = 0; i < m; i++) {
                b[i] = scanner.nextInt();
            }

            Arrays.sort(a);
            Arrays.sort(b);

            char[] s = scanner.next().toCharArray();
            int sum = 0, idxA = n-1, idxB = 0, res = 0;
            for (char c: s) {
                if (c == '+') {
                    sum += a[idxA--];
                } else {

                    if (sum >= b[idxB]) {
                        sum -= b[idxB++];
                    } else {
                        res++;
                    }

                }
            }
            System.out.println(res);
        }

    }
}
