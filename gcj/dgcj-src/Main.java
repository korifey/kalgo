
public class Main {
    public static void main(String[] args) {
        if (message.MyNodeId() > 0) return;

        int n = (int)crates.GetNumStacks();
        long[] a = new long[n];
        long sum = 0;
        for (int i=0; i<n; i++) {
            a[i] = crates.GetStackHeight(i+1);
            sum += a[i];
        }
        long rem = sum % (long)n;
        long div = sum / (long)n;
        long res = 0;
        for (int i=0; i<n-1; i++) {
            long need = div + (i<rem ? 1L: 0L);
            a[i+1] += a[i]-need;
            res += Math.abs(a[i]-need);
        }
        System.out.println(res%1000000007L);
    }
}
