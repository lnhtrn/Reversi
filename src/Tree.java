public class Tree {
    Board board; 
    Node node; // parent, original node of tree

    // initialize game tree 
    public Tree(int size) {
        this.board = new Board(size);
        this.node = new Node();
    }
}
