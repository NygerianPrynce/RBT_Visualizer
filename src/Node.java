public class Node {
    int data;
    Node left;
    Node right;
    int color; // 0 for red, 1 for black
    public Node(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
        this.color = 0;

    }
}
