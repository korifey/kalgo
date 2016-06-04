
public class MainRps {
    static class Pair {
        char c;
        long num;

        public Pair(char c, long num) {
            this.c = c;
            this.num = num;
        }
    }

    public static Pair solve(char[] a, long[] nums) {
        int lim = a.length;

        while (lim != 1) {
            for (int i=0; i<lim; i+= 2) {
                char x = a[i];
                char y = a[i+1];

                char r;
                switch (x) {
                    case 'P': if (y == 'S') r = 'S'; else r = 'P';
                        break;
                    case 'S': if (y == 'R') r = 'R'; else r = 'S';
                        break;
                    case 'R': if (y == 'P') r = 'P'; else r = 'R';
                        break;
                    default: {
                        boolean flag = true;
                        while (flag) message.Send(99);
                        throw new IllegalArgumentException(x+"");
                    }
                }
                if (a[i] != r) nums[i/2] = nums[i+1];
                else nums[i/2] = nums[i];

                a[i/2] = r;
            }
            lim /= 2;
        }
        return new Pair(a[0], nums[0]);
    }

    public static void main(String[] args) {

        if (message.MyNodeId() >= 64) return;

        if (rps.GetN() < 6) {
            if (message.MyNodeId() != 0) return;

            int n = 1<< rps.GetN();
            char[] a = new char[n];
            long[] nums = new long[n];
            for (int i = 0; i< n; i++) {
                a[i] = rps.GetFavoriteMove(i);
                nums[i] = i;
            }

            System.out.println(solve(a, nums).num);

        } else {

            int perNode = 1 << (rps.GetN() - 6);
            long start = message.MyNodeId() * (long)perNode;

            char[] a = new char[perNode];
            long[] nums = new long[perNode];
            for (int i = 0; i < perNode; i++) {
                a[i] = rps.GetFavoriteMove(start + i);
                nums[i] = start+i;
            }

            if (message.MyNodeId() != 0)
            {
                message.PutLL(0, solve(a, nums).num);
                message.Send(0);
            } else {
                char[] b = new char[64];
                long[] numsNew = new long[64];
                Pair p0 = solve(a, nums);
                b[0] = p0.c;
                numsNew[0] = p0.num;

                for (int i = 1; i < 64; i++) {
                    message.Receive(i);
                    numsNew[i] = message.GetLL(i);
                    b[i] = rps.GetFavoriteMove(numsNew[i]);
                }

                System.out.println(solve(b, numsNew).num);
            }
        }

    }
}
