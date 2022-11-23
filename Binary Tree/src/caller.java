public class caller {
    public static void main(String[] args) {

        binaryTree a = new binaryTree();
        a.insert(0);
        a.insert(12);
        a.insert(14);
        a.insert(40);
        a.insert(56);

        a.insert(11);
        a.insert(16);
        a.insert(17);
        a.insert(19);
        a.insert(21);


        a.find(12);
        a.find(40);
        a.find(17);
        a.find(21);

        a.delete(16);
        a.find(16);




    }
}
