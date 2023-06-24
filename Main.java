public class Main {
    public static void main(String[] args) {
        Tree<Integer> tree = new Tree<>();
        tree.add(5);
        tree.add(4);
        tree.add(6);
        tree.add(2);
        tree.add(70);
        tree.add(8);
        tree.add(1110);
        tree.add(9);
        tree.add(45);
        tree.add(15);
        System.out.println(tree.contains(5));

        tree.print();
       
    }
}
