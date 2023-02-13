public class RBT {
    Node root;
  
    public RBT() {
        root = null;
    }
    
    public void insert(int data) {
        root = insertRecursive(root, data);
        //method that makes the root black
        root.color = 1;
    }

    public Node getCurrentNode(){
        return root;
    }

    public Node insertRecursive(Node current, int data) {
        if (current == null) {
            return new Node(data);
        }
        //if both children are red then change the color of the current node to red and the color of the children to black
        if (current.left != null && current.right != null && current.left.color == 0 && current.right.color == 0) {
            current.color = 0;
            current.left.color = 1;
            current.right.color = 1;
        }
        if (data < current.data) {
            current.left = insertRecursive(current.left, data);
            if (current.left != null && current.left.left != null && current.left.color == 0 && current.left.left.color == 0) {
                current = leftLeftRotation(current);
            } else if(current.left != null && current.left.right != null && current.left.color == 0 && current.left.right.color == 0){
                current = leftRightRotation(current);
            }

        } else if (data > current.data) {
            current.right = insertRecursive(current.right, data);
            if (current.right != null && current.right.right != null && current.right.color == 0 && current.right.right.color == 0) {
                current = rightRightRotation(current);
            } else if(current.right != null && current.right.left != null && current.right.color == 0 && current.right.left.color == 0){
                current = rightLeftRotation(current);
            }
        } else {
            // value already exists
            return current;
        }
        //method that climbs all the way up the tree and makes the root black

        return current;
    }

    public Node leftLeftRotation(Node current) {
        Node leftChild = current.left;
        current.left = leftChild.right;
        leftChild.right = current;
        leftChild.color = 1;
        current.color = 0;
        return leftChild;
    }
    //method for right right rotation
    public Node rightRightRotation(Node current) {
        Node rightChild = current.right;
        current.right = rightChild.left;
        rightChild.left = current;
        rightChild.color = 1;
        current.color = 0;
        return rightChild;
    }
    //method for left right rotation
    public Node leftRightRotation(Node current) {
        current.left = rightRightRotation(current.left);
        return leftLeftRotation(current);
    }
    //method for right left rotation
    public Node rightLeftRotation(Node current) {
        current.right = leftLeftRotation(current.right);
        return rightRightRotation(current);
    }

     

    
}
