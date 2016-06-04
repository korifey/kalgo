
public class MainTest {
    public static void main(String[] args) {
        if (message.MyNodeId() == 0) {
            int sum = 0;
            for (int i=1; i<message.NumberOfNodes(); i++) {
                message.Receive(i);
                sum += message.GetInt(i);
            }
            System.out.println(sum);
        } else {
            message.PutInt(0, message.MyNodeId());
            message.Send(0);
        }
    }
}
