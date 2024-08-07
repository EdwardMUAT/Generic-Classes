class TreeNode<T extends Comparable<T>> {
    T key;
    TreeNode<T> left, right;

    public TreeNode(T key) {
        this.key = key;
        this.left = this.right = null;
    }
}

class BinarySearchTree<T extends Comparable<T>> {
    private TreeNode<T> root;

    public BinarySearchTree() {
        this.root = null;
    }

    public void insert(T key) {
        root = insertRec(root, key);
    }

    private TreeNode<T> insertRec(TreeNode<T> root, T key) {
        if (root == null) {
            root = new TreeNode<>(key);
            return root;
        }

        if (key.compareTo(root.key) < 0) {
            root.left = insertRec(root.left, key);
        } else if (key.compareTo(root.key) > 0) {
            root.right = insertRec(root.right, key);
        }

        return root;
    }

    public boolean search(T key) {
        return searchRec(root, key) != null;
    }

    private TreeNode<T> searchRec(TreeNode<T> root, T key) {
        if (root == null || root.key.compareTo(key) == 0) {
            return root;
        }

        if (key.compareTo(root.key) < 0) {
            return searchRec(root.left, key);
        }

        return searchRec(root.right, key);
    }

    public void delete(T key) {
        root = deleteRec(root, key);
    }

    private TreeNode<T> deleteRec(TreeNode<T> root, T key) {
        if (root == null) {
            return root;
        }

        if (key.compareTo(root.key) < 0) {
            root.left = deleteRec(root.left, key);
        } else if (key.compareTo(root.key) > 0) {
            root.right = deleteRec(root.right, key);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            root.key = minValue(root.right);
            root.right = deleteRec(root.right, root.key);
        }

        return root;
    }

    private T minValue(TreeNode<T> root) {
        T minValue = root.key;
        while (root.left != null) {
            minValue = root.left.key;
            root = root.left;
        }
        return minValue;
    }

    public void inorder() {
        inorderRec(root);
        System.out.println();
    }

    private void inorderRec(TreeNode<T> root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.print(root.key + " ");
            inorderRec(root.right);
        }
    }

    public void preorder() {
        preorderRec(root);
        System.out.println();
    }

    private void preorderRec(TreeNode<T> root) {
        if (root != null) {
            System.out.print(root.key + " ");
            preorderRec(root.left);
            preorderRec(root.right);
        }
    }

    public void postorder() {
        postorderRec(root);
        System.out.println();
    }

    private void postorderRec(TreeNode<T> root) {
        if (root != null) {
            postorderRec(root.left);
            postorderRec(root.right);
            System.out.print(root.key + " ");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.insert(27);
        bst.insert(14);
        bst.insert(35);
        bst.insert(10);
        bst.insert(19);
        bst.insert(31);
        bst.insert(42);

        System.out.print("Inorder traversal: ");
        bst.inorder();

        System.out.print("Preorder traversal: ");
        bst.preorder();

        System.out.print("Postorder traversal: ");
        bst.postorder();

        System.out.println("Search for 14: " + bst.search(14));
        System.out.println("Search for 100: " + bst.search(100));

        bst.delete(14);
        System.out.print("Inorder traversal after deleting 14: ");
        bst.inorder();
    }
}
