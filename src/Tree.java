public class Tree {
    Board board; 
    Node node; // parent, original node of tree

    // initialize game tree 
    public Tree(int size) {
        this.board = new Board(size);
        this.node = new Node();
    }

    // recursion function to generate game tree 
    public void makeTree(char player, char opponent) {
        int root_utility = Minimax.makeMinTree(this.board, this.node, player, opponent);
        this.node.setUtility(root_utility);
    }

    // recursion function to generate game tree with alpha-beta pruning 
    public void makeABTree(char player, char opponent) {
        int root_utility = Minimax.makeMaxABTree(this.board, this.node, player, opponent);
        this.node.setUtility(root_utility);
    }
}
