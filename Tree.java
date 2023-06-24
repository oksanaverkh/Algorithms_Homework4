import java.util.ArrayList;
import java.util.Comparator;

public class Tree<V extends Comparable<V>> {
    Node root;

    public boolean add(V value) {
        if (root != null) {
            boolean result = addNode(root, value);
            root = balance(root);
            root.color = Tree.Node.Color.Black;
            return result;
        } else {
            root = new Node();
            root.color = Tree.Node.Color.Black;
            root.value = value;
            return true;
        }
    }

    public boolean addNode(Node node, V value) {
        if (node.value.equals(value)) {
            return false;
        } else {
            if (node.value.compareTo(value) > 0) {
                if (node.left != null) {
                    boolean result = addNode(node.left, value);
                    node.left = balance(node.left);
                    return result;
                } else {
                    node.left = new Node();
                    node.left.color = Tree.Node.Color.Red;
                    node.left.value = value;
                    return true;
                }
            } else {
                if (node.right != null) {
                    boolean result = addNode(node.right, value);
                    node.right = balance(node.right);
                    return result;
                } else {
                    node.right = new Node();
                    node.right.color = Tree.Node.Color.Red;
                    node.right.value = value;
                    return true;
                }
            }
        }
    }

    private Node balance(Node node) {
        Node result = node;
        boolean needBalance;
        do {
            needBalance = false;
            if (result.right != null && result.right.color.equals(Tree.Node.Color.Red) &&
                    (result.left == null || result.left.color.equals(Tree.Node.Color.Black))) {
                needBalance = true;
                result = rightTurn(result);
            }
            if (result.left != null && result.left.color.equals(Tree.Node.Color.Red) &&
                    result.left.left != null && result.left.left.color.equals(Tree.Node.Color.Red)) {
                needBalance = true;
                result = leftTurn(result);
            }
            if (result.left != null && result.left.color.equals(Tree.Node.Color.Red) &&
                    result.right != null && result.right.color.equals(Tree.Node.Color.Red)) {
                needBalance = true;
                changeColor(result);
            }

        } while (needBalance);
        return result;
    }

    public void print() {
        if (root != null) {
            System.out.println("Root: " + root);
            if (root.left != null) {
                System.out.println("Left child: " + root.left);
            }
            if (root.right != null) {
                System.out.println("Right child: " + root.right);
            }
        } else {
            System.out.println("Empty tree");
        }
    }

    private Node rightTurn(Node node) {
        Node right = node.right;
        Node between = right.left;
        right.left = node;
        node.right = between;
        right.color = node.color;
        node.color = Tree.Node.Color.Red;
        return right;
    }

    private Node leftTurn(Node node) {
        Node left = node.left;
        Node between = left.right;
        left.right = node;
        node.left = between;
        left.color = node.color;
        node.color = Tree.Node.Color.Red;
        return left;
    }

    private void changeColor(Node node) {
        node.right.color = Tree.Node.Color.Black;
        node.left.color = Tree.Node.Color.Black;
        node.color = Tree.Node.Color.Red;
    }

    public boolean contains(V value) {
        Node node = root;
        while (node != null) {
            if (node.value.equals(value)) {
                return true;
            }
            if (node.value.compareTo(value) > 0) {
                node = node.left;

            } else {
                node = node.right;
            }
        }
        return false;
    }

    private class Node {
        private V value;
        private Node left;
        private Node right;
        private Color color;

        private enum Color {
            Red, Black;
        }

        @Override
        public String toString() {
            return "Node{value = " + value + ", color = " + color + "}";
        }
    }

}
