package org.korifey.kalgo.codeforces.round349div2;

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

    static int to(char c1, char c2, char c3) {
        return ((c1-'a'+1)<<10) + ((c2-'a'+1) << 5) + (c3-'a'+1);
    }

    static int to(char c1, char c2) {
        return ((c1-'a'+1) << 10) + ((c2-'a'+1) << 5);
    }

    static char[] s;
    static int tail = 0;
    static boolean check(int i) {
        if (i == s.length - 1) return true;
        if (i == s.length - 4 && s[i] == s[i+2] && s[i+1] == s[i+3]) return true;
        int x = s.length - i;
        if (x > 5 && tail >= x && x % 5 != 0 && (x - 2)%5 != 0 && (x - 3)%5 != 0) return true;
        return false;
    }

    public static void main(String[] args) {
        MyScanner scanner = new MyScanner();
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        s = scanner.next().toCharArray();

        boolean [] b = new boolean[32768];
        int n = s.length;

        tail = 1;
        for (int i= n-2; i>=5; i--)
            if (s[i] == s[n-1]) tail++;
            else break;

        for (int i=5; i <= n-2; i++) {
            if (check(i+2)) continue;
            b[to(s[i], s[i+1])] = true;
        }

        for (int i=5; i <= n-3; i++) {
            if (check(i+3)) continue;
            b[to(s[i], s[i+1], s[i+2])] = true;
        }

        int res = 0;
        for (int i=0; i<b.length; i++) {
            if (b[i]) res++;
        }

        System.out.println(res);

        for (int i=0; i<b.length; i++) {
            if (b[i]) {
                int x = i;
                int c1 = (x >> 10) + 'a'-1;
                out.write(c1);

                int c2 = ((x >> 5) & 31) +'a'-1;
                out.write(c2);

                x &= 31;
                if (x > 0) {
                    int c3 = x+'a'-1;
                    out.write(c3);
                }
                out.println();
            }
        }

        out.close();
    }
}
