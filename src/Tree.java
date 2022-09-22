public class Tree {
    Board board; // parent, original node of tree

    // initialize game tree 
    public Tree(int size) {
        this.board = new Board(size);
    }

    // make tree 
    public void makeTree(char max, char min, Boolean max_layer) {
        Minimax.makeMinimaxTree(this.board, max, min, max_layer);
    }

    // calculate tree utility 
    public void calculateUtilityTree() {
        Minimax.calculateUtility(this.board, true);
    }
}
