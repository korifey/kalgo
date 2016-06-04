
public class MainOops {
    public static void main(String[] args) {
        if (oops.GetN() == 0) {
            System.out.println(0);
            return;
        }

        long min = Long.MAX_VALUE;
        long max = Long.MIN_VALUE;

        for (int i=message.MyNodeId(); i<oops.GetN(); i+=message.NumberOfNodes()) {
            long xx = oops.GetNumber(i);
            if (xx > max) max = xx;
            if (xx < min) min = xx;
        }



        if (message.MyNodeId() == 0) {
            for (int i = 1; i < message.NumberOfNodes(); i++) {
                message.Receive(i);
                long min1 = message.GetLL(i);
                long max1 = message.GetLL(i);

                if (min1 < min) min = min1;
                if (max1 > max) max = max1;
            }
            System.out.println(max-min);
        } else {
            message.PutLL(0, min);
            message.PutLL(0, max);
            message.Send(0);
        }

    }
}
