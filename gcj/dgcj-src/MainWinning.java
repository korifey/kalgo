import java.util.Arrays;

/**
 * Created by User on 5/29/2016.
 */
public class MainWinning {
    public static void main(String[] args) {
        if (message.MyNodeId() > 0) return;
        int n = (int)winning_move.GetNumPlayers();

        long[] a = new long[n];
        for (int i=0; i<n; i++) {
            a[i] = winning_move.GetSubmission(i);
        }
        Arrays.sort(a);
        int i=0;
        while (i < n) {
            long x = a[i++];
            if (i == n || a[i] != x) {
                System.out.println(x);
                return;
            } else {
                while (i < n && a[i] == x) i++;
            }
        }
        System.out.println(0);
    }
}
