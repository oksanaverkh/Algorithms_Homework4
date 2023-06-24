public class Main {
    public static void main(String[] args) {
        Tree<Integer> tree = new Tree<>();
        tree.add(5);
        tree.add(4);
        tree.add(6);
        tree.add(5);
        System.out.println(tree.contains(5));

        System.out.println(tree);
    }
}
