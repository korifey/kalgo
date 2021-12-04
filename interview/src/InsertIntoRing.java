class Node {
    int info;
    Node next;

    public Node(int info, Node next) {
        this.info = info;
        this.next = next;
    }

    // You are given some node of looped linked list
    // 1. "Looped" list means it has no head and no tail, just one big ring of nodes
    // 2. Loop has invariant: for (each node in loop) node.next.info >= node.info, except at most one node
    //      i.e. infos in list are non-decreasing, except zero or one place of possible violation -
    //      where node.next.info < node.info
    //
    // Correct list examples:
    // 1 -> 2 -> 3 -> 4 [ looped -> 1]
    // 2 -> 3 -> 3 [ looped -> 2]
    // 1 -> 1 -> 2 [ looped -> 1]
    // 1 [ looped itself -> 1]
    //
    // Your task is to implement method "insert", that inserts new node in such looped linked list, preserving invariant
    public void insert1(int x) {
        //find max element or return any if all elements are same
        Node n = this;
        do {
          if (n.next.info < n.info) break;
          n = n.next;
        } while (n != this);

        // if x <= min info || x >= max info
        if (x >= n.info || x <= n.next.info) {
            n.next = new Node(x, n.next);
            return;
        }

        //otherwise it must be place for x inside list
        n = n.next;
        while (x > n.next.info)
            n = n.next;

        n.next = new Node(x, n.next);
    }


    public void insert(int x) {
        Node left = this;
        Node right = this.next;

        while (right != this) {
            if ((x >= left.info && x <= right.info) ||
                    (left.info > right.info && (x >= left.info || x <= right.info))) {
                break;
            }
            left = left.next;
            right = right.next;
        }

        left.next = new Node(x, right);
    }


    public String toString() {
        StringBuilder res = new StringBuilder();
        Node min = this;
        for (Node n = this.next; n != this; n = n.next) {
            if (n.info < min.info)
                min = n;
        }

        res.append(min.info);
        for (Node n = min.next; n != min; n = n.next)
            res.append(n.info);

        return res.toString();
    }

    public static void test1() {
        Node n = new Node(1, null);
        n.next = n;
        assert n.toString().equals("1");

        n.insert(1);
        assert n.toString().equals("11");

        n.insert(0);
        assert n.toString().equals("011");

        n.insert(3);
        assert n.toString().equals("0113");

        n.insert(2);
        assert n.toString().equals("01123");

        n.insert(-1);
        assert n.toString().equals("-101123");

        n.insert(4);
        assert n.toString().equals("-1011234");
    }

    public static void main(String[] args) {
        test1();
    }
}


