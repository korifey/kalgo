//package org.korifey.kalgo.rcc.y2016.qual2;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;

public class C {
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


    static String s;
    static int k;

    static LinkedHashSet<String> set = new LinkedHashSet<>();
    static boolean rec(int l, int p) {
        if (l == k) {
            String x = s.substring(p);
            if (set.contains(x)) return false;
            set.add(x);
            return true;
        }
        for (int i=p+1; i<s.length(); i++) {
            String x = s.substring(p, i);
            if (set.contains(x)) continue;
            set.add(x);
            if (rec(l+1, i)) return true;
            set.remove(x);
        }
        return false;
    }

    public static void main(String[] args) throws Exception{
        MyScanner scanner = new MyScanner();

        int ncases = scanner.nextInt();
        for (int ncase=0; ncase<ncases; ncase++) {
            s = scanner.next();
            k = scanner.nextInt();
            set.clear();

            if (!rec(1, 0)) System.out.println("NO");
            else {
                System.out.println("YES");
                for (String res: set) {
                    System.out.println(res);
                }
            }
        }
    }
}
