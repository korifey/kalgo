package org.korifey.kalgo.codeforces.round360div2;

import java.io.*;
import java.util.*;

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


    static class Vertex {
        int mark;
        ArrayList<Integer> other = new ArrayList<>();
    }

    public static void main(String[] args) {
        MyScanner scanner = new MyScanner();
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        Vertex[] v = new Vertex[n];
        for (int i = 0; i < n; i++) {
            v[i] = new Vertex();
        }

        for (int i = 0; i < m; i++) {
            int x = scanner.nextInt() - 1;
            int y = scanner.nextInt() - 1;
            v[x].other.add(y);
            v[y].other.add(x);
        }

        Queue<Vertex> queue = new LinkedList<>();
        for (int i=0; i<n; i++) {
            if (v[i].mark == 0 && !v[i].other.isEmpty()) {
                queue.offer(v[i]);
                v[i].mark = 1;
            }
            while (!queue.isEmpty()) {
                Vertex x = queue.poll();
                for (int o: x.other) {
                    Vertex y = v[o];
                    if (y.mark > 0 ) {
                        if (y.mark == x.mark) {
                            System.out.println(-1);
                            return;
                        }
                    } else {
                        y.mark = (x.mark == 1) ? 2 : 1;
                        queue.offer(y);
                    }
                }
            }
        }

        long c1 = Arrays.stream(v).filter((Vertex vv) -> vv.mark == 1).count();
        long c2 = Arrays.stream(v).filter((Vertex vv) -> vv.mark == 2).count();

        System.out.println(c1);
        int cc = 0;
        for (int i=0; i<n; i++) if (v[i].mark == 1) {
            System.out.print(i+1);
            cc++;
            if (cc == c1) System.out.println();
            else System.out.print(' ');
        };

        System.out.println(c2);
        cc = 0;
        for (int i=0; i<n; i++) if (v[i].mark == 2) {
            System.out.print(i+1);
            cc++;
            if (cc == c2) System.out.println();
            else System.out.print(' ');
        };
    }
}