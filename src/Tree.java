public class Tree {
    Board board; // parent, original node of tree

    // initialize game tree 
    public Tree(int size) {
        this.board = new Board(size);
    }

    // make tree 
    public void makeTree(char player, char opponent, Boolean max_layer) {
        if (max_layer) {
            // if computer plays first, root node is the max layer
            Minimax.makeMinimaxTree(this.board, opponent, player, true);
        } else {
            // meaning the computer (root node) is not the max layer
            Minimax.makeMinimaxTree(this.board, opponent, player, false);
        }

    }

    // calculate tree utility 
    public void calculateUtilityTree() {
        Minimax.calculateUtility(this.board, true);
    }
}
