package org.korifey.kalgo.codeforces.round354div2;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
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

    static int[][] a;
    static int n;
    static int m;

    static int rotate(int x, int r) {
        switch (r) {
            case 0: return x;
            case 1: return (x >> 1) | ((x&1) << 3);
            case 2: return (x >> 2) | ((x&3) << 2);
            case 3: return (x >> 3) | ((x&7) << 1);
        }
        throw new IllegalArgumentException(r+"");
    }

    static boolean passable(int i, int j, int direction, int r) {
        int x = rotate(a[i][j], r);
        int y;


        switch (direction) {
            case 0:
                if (j == 0) return false;
                y = rotate(a[i][j-1], r);

                return ((x & (0b1000)) != 0) && ((y & (0b0010)) != 0);
            case 1:
                if (i == 0) return false;
                y = rotate(a[i-1][j], r);

                return ((x & (0b0100)) != 0) && ((y & (0b0001)) != 0);
            case 2:
                if (j == m-1) return false;
                y = rotate(a[i][j+1], r);

                return ((x & (0b0010)) != 0) && ((y & (0b1000)) != 0);

            case 3:
                if (i == n-1) return false;
                y = rotate(a[i+1][j], r);

                return ((x & (0b0001)) != 0) && ((y & (0b0100)) != 0);
        }

        throw new IllegalArgumentException(r+"");
    }


    public static void main(String[] args) {
        MyScanner scanner = new MyScanner();
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        //body starts
        n = scanner.nextInt();
        m = scanner.nextInt();


        a = new int[n][m];
        for (int i = 0; i < n; i++) {

            String s = scanner.next();
            for (int j = 0; j < s.length(); j++) {
                switch (s.charAt(j)) {
                    case '+' : a[i][j] = 0b1111; break;
                    case '-' : a[i][j] = 0b1010; break;
                    case '|' : a[i][j] = 0b0101; break;
                    case '^' : a[i][j] = 0b0100; break;
                    case '>' : a[i][j] = 0b0010; break;
                    case '<' : a[i][j] = 0b1000; break;
                    case 'v' : a[i][j] = 0b0001; break;
                    case 'L' : a[i][j] = 0b0111; break;
                    case 'R' : a[i][j] = 0b1101; break;
                    case 'U' : a[i][j] = 0b1011; break;
                    case 'D' : a[i][j] = 0b1110; break;
                    case '*' : a[i][j] = 0b0000; break;
                    default: throw new IllegalArgumentException(""+s.charAt(j));
                }
            }
        }

        int xs = scanner.nextInt()-1;
        int ys = scanner.nextInt()-1;
        int xe = scanner.nextInt()-1;
        int ye = scanner.nextInt()-1;

        int spos = xs << 10 | ys;
        int fpos = xe << 10 | ye;

        int maxstate = 1 << 24;
        int mask = (1 << 20) - 1;
        int res = 0;
        boolean[] visited = new boolean[maxstate];
        ArrayList<Integer> q1 = new ArrayList<>();
        ArrayList<Integer> q2 = new ArrayList<>();
        q1.add(spos);

        while (q1.size() > 0) {
            for (int pos: q1) {
                if (visited[pos]) continue;
                visited[pos] = true;

                if ((pos & mask) == fpos) {
                    System.out.println(res);
                    return;
                }

                int i = (pos >> 10) & ((1<<10) - 1);
                int j = pos & ((1<<10) - 1);
                int r = pos >> 20;

                q2.add((((r + 1) & 3) << 20)  + (i << 10) + j);
                if (passable(i, j, 0, r)) q2.add(pos - 1);
                if (passable(i, j, 1, r)) q2.add(pos - (1 << 10));
                if (passable(i, j, 2, r)) q2.add(pos + 1);
                if (passable(i, j, 3, r)) q2.add(pos + (1 << 10));
            }

            res++;
            q1.clear();
            q1.addAll(q2);
            q2.clear();
        }
        System.out.println(-1);

        //body ends


    }
}