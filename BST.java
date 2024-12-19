public class BST {
    // internal node class to represent tree nodes
    static class Node{
        int data;
        Node left; // left child
        Node right; // right child
        public Node(int data) {
            this.data = data;
            left = null;
            right = null;
        }
    }

    // node to represent root of tree
    private Node root;

    // constructor
    public BST() {
        // initialize data
        root = null;
    }

    // add integer element to tree
    public void insert(int element) {
        // create a node to insert
        Node node = new Node(element);
        // insert element at root node if root node is null
        if(root == null) {
            root = node;
        }
        else {
            insert(root, node);
        }
    }

    private void insert(Node root, Node node) {
        // compare element of node with root
        if(root.data > node.data) {
            // look left side
            if(root.left == null) {
                // insert node as left child
                root.left = node;
            }
            else {
                insert(root.left, node);
            }
        }
        else {
            // look right side
            if(root.right == null) {
                // insert node as right child
                root.right = node;
            }
            else {
                insert(root.right, node);
            }
        }
    }

    // print tree in-order
    public void inorder() {
        inorder(root);
        System.out.println();
    }

    private void inorder(Node root) {
        if(root == null) {
            return;
        }
        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }

    // search tree for given element and return true if element found
    public void search(int element) {
        search(root, element);
    }

    private boolean search(Node root, int element) {
        if(root == null) {
            return false;
        }
        // check root node
        if(root.data > element) {
            // search left side
            return search(root.left, element);
        }
        else if(root.data < element) {
            // search right side
            return search(root.right, element);
        }
        else {
            return true;
        }
    }

    // removes element from tree if its present
    public void remove(int element) {
        root = remove(root, element);
    }

    private Node remove(Node root, int element) {
        if(root == null) {
            return null;
        }
        // check root node
        if(root.data > element) {
            // remove node from left side
            root.left = remove(root.left, element);
        }
        else if(root.data < element) {
            // remove node from right side
            root.right = remove(root.right, element);
        }
        else {
            // root node is need to be removed
            Node left = root.left;
            // assign right node as root node
            root = root.right;
            // reinsert left node
            if(left != null) {
                insert(root, left);
            }
        }
        return root;
    }
}
class Main {

    // main method to run program
    public static void main(String[] args) {
        // create tree
        BST tree = new BST();
        // insert elements
        for (int i = 1; i <= 15; i++) {
            tree.insert(i);
        }
        // print tree
        System.out.println("Printing tree: InOrder traversal");
        tree.inorder();
        System.out.println("Searching element 1 and 15 for 100000 times");

        // Record CPU time for search(1)
        long startTimeSearch1 = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            tree.search(1);
        }
        long endTimeSearch1 = System.nanoTime();
        double timeSearch1 = (endTimeSearch1 - startTimeSearch1) / 1000000.0;
        System.out.println("Time taken for search(1): " + timeSearch1 + " milliseconds");

        // Record CPU time for search(15)
        long startTimeSearch15 = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            tree.search(15);
        }
        long endTimeSearch15 = System.nanoTime();
        double timeSearch15 = (endTimeSearch15 - startTimeSearch15) / 1000000.0;
        System.out.println("Time taken for search(15): " + timeSearch15 + " milliseconds");

        // Calculate and print combined time
        double combinedTime = timeSearch1 + timeSearch15;
        System.out.println("Combined time for both searches: " + combinedTime + " milliseconds");

        System.out.println("Removing keys");
        // remove 5
        tree.remove(5);
        tree.inorder();
        // remove 15
        tree.remove(15);
        tree.inorder();
        // remove 1
        tree.remove(1);
        tree.inorder();
        // insert key 2
        tree.insert(2);
        tree.inorder();

        System.out.println();
        System.out.println("Creating a new tree with elements: 8, 4, 12, 2, 6, 10, 14, 1, 3, 5, 7, 9, 11, 13, 15"); // create second tree
        BST tree2 = new BST();
        // insert elements
        // in this order: 8, 4, 12, 2, 6, 10, 14, 1, 3, 5, 7, 9, 11, 13, 15
        tree2.insert(8);
        tree2.insert(4);
        tree2.insert(12);
        tree2.insert(2);
        tree2.insert(6);
        tree2.insert(10);
        tree2.insert(14);
        tree2.insert(1);
        tree2.insert(3);
        tree2.insert(5);
        tree2.insert(7);
        tree2.insert(9);
        tree2.insert(11);
        tree2.insert(13);
        tree2.insert(15);
        // print tree
        System.out.println("Printing tree: InOrder traversal");
        tree2.inorder();
        System.out.println("Searching element 1 and 15 for 100000 times");

        // Record CPU time for search(1) in the second tree
        long startTime = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            tree2.search(1);
        }
       long endTime = System.nanoTime();
        double time2Search1 = (endTime - startTime) / 1000000.0;
        System.out.println("Time taken for search(1) in the second tree: " + time2Search1 + " milliseconds");

        // Record CPU time for search(15) in the second tree
        startTime = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            tree2.search(15);
        }
        endTime = System.nanoTime();
        double time2Search15 = (endTime - startTime) / 1000000.0;
        System.out.println("Time taken for search(15) in the second tree: " + time2Search15 + " milliseconds");

        double combinedTime2 = timeSearch1 + timeSearch15;
        System.out.println("Combined time for both searches: " + combinedTime2 + " milliseconds");

        // Calculate and print the difference in search time between the two trees
        System.out.println("Difference in search time between the two trees: " + (timeSearch1 - time2Search1) + " milliseconds");

        System.out.println("Removing keys");
        tree2.remove(8);
        tree2.inorder();
    }
}
