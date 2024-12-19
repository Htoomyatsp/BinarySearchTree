import java.util.concurrent.TimeUnit;

class Node {
    int key;
    Node left, right;

    public Node(int item) {
        key = item;
        left = right = null;
    }
}

public class BinarySearchTree {
    private Node root;

    public BinarySearchTree() {
        root = null;
    }

    // Insert a key into the BST
    public void insert(int key) {
        root = insertRec(root, key);
    }

    private Node insertRec(Node root, int key) {
        if (root == null) {
            return new Node(key);
        }

        if (key < root.key) {
            root.left = insertRec(root.left, key);
        } else if (key > root.key) {
            root.right = insertRec(root.right, key);
        }

        return root;
    }

    // Search for a key in the BST
    public boolean search(int key) {
        return searchRec(root, key);
    }

    private boolean searchRec(Node root, int key) {
        if (root == null || root.key == key) {
            return root != null;
        }

        if (key < root.key) {
            return searchRec(root.left, key);
        } else {
            return searchRec(root.right, key);
        }
    }

    // Remove a key from the BST
    public void remove(int key) {
        root = removeRec(root, key);
    }

    private Node removeRec(Node root, int key) {
        if (root == null) {
            return null;
        }

        if (key < root.key) {
            root.left = removeRec(root.left, key);
        } else if (key > root.key) {
            root.right = removeRec(root.right, key);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            root.key = minValue(root.right);

            root.right = removeRec(root.right, root.key);
        }

        return root;
    }

    private int minValue(Node root) {
        int minValue = root.key;
        while (root.left != null) {
            minValue = root.left.key;
            root = root.left;
        }
        return minValue;
    }

    // Perform InOrder traversal
    public void inOrder() {
        inOrderRec(root);
        System.out.println();
    }

    private void inOrderRec(Node root) {
        if (root != null) {
            inOrderRec(root.left);
            System.out.print(root.key + " ");
            inOrderRec(root.right);
        }
    }

    // Bonus: Draw the BST (basic representation)
    public void drawTree() {
        drawTreeRec(root, 0);
    }

    private void drawTreeRec(Node root, int space) {
        if (root == null) {
            return;
        }

        int count = 5; // Adjust this value based on the tree's structure

        drawTreeRec(root.right, space + count);

        for (int i = 0; i < space; i++) {
            System.out.print(" ");
        }
        System.out.println(root.key);

        drawTreeRec(root.left, space + count);
    }

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();

        // Task #4: Insert keys 1 to 15
        for (int i = 1; i <= 15; i++) {
            bst.insert(i);
        }

        // Task #4: Show all keys in InOrder traversal
        System.out.print("InOrder Traversal: ");
        bst.inOrder();

        // Task #5: Search(1) and Search(15) 100,000 times
        long startTime = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            bst.search(1);
        }
        long endTime = System.nanoTime();
        long duration = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
        System.out.println("Search(1) Time: " + duration + " ms");

        startTime = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            bst.search(15);
        }
        endTime = System.nanoTime();
        duration = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
        System.out.println("Search(15) Time: " + duration + " ms");

        // Task #6: Remove keys 5, 15, 1 and insert key 2
        bst.remove(5);
        bst.inOrder(); // Show all keys

        bst.remove(15);
        bst.inOrder(); // Show all keys

        bst.remove(1);
        bst.inOrder(); // Show all keys

        bst.insert(2);
        bst.inOrder(); // Show all keys

        // Task #7: Create new tree and show keys in InOrder traversal
        BinarySearchTree newTree = new BinarySearchTree();
        int[] keysToAdd = {8, 4, 12, 2, 6, 10, 14, 1, 3, 5, 7, 9, 11, 13, 15};
        for (int key : keysToAdd) {
            newTree.insert(key);
        }
        System.out.print("New Tree InOrder Traversal: ");
        newTree.inOrder();

        // Task #8: Search(1) and Search(15) 100,000 times for new tree
        startTime = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            newTree.search(1);
        }
        endTime = System.nanoTime();
        duration = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
        System.out.println("Search(1) Time for New Tree: " + duration + " ms");

        startTime = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            newTree.search(15);
        }
        endTime = System.nanoTime();
        duration = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
        System.out.println("Search(15) Time for New Tree: " + duration + " ms");

        // Task #10: Remove key 8 and show keys
        newTree.remove(8);
        System.out.print("After Removing 8: ");
        newTree.inOrder();
    }
}
