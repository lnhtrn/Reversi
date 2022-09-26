import java.util.*;

// interface for all node/state spaces/nodes in the minimax tree
interface Nodes {

    // get & set player 
    public char getPlayer();

    public void setPlayer(char player);

    // get the utility value of the node/state
    public int getUtility();

    // print the node 
    public void printNodeInfo();

    // get the highest value child 
    public Node getMaxChild();

    // get the lowest value child
    public Node getMinChild();

    // get the parent state/node
    public Node getParent();

    // get the number of children states
    public int getChildrenSize();

    // set the utility 
    public void setUtility(int new_utility);
}

public class Node implements Nodes {
    private Integer utility = 0;
    private char player;

    Node parent; 
    Integer[] parentMove; // signify which move it comes from 
    List<Node> children = new ArrayList<>();

    // initialize node
    public Node() {}

    public Node(Node parent, Integer[] parentMove, char player) {
        this.parent = parent;
        this.parentMove = parentMove;
        this.player = player;
    }

    public char getPlayer() {
        return player;
    }

    public void setPlayer(char player) {
        this.player = player;
    }

    // node-related methods 
    public int getUtility() {
        return this.utility;
    }

    public void printNodeInfo() {
        /*char[][] node;
        int size;
        Integer utility = null;

        Node parent;
        String parentMove; // to signify which move it comes from 
        List<Node> children = null; */
        
        System.out.println("The utility of this node is " + utility.toString() + ".");
        System.out.println("Play " + Reversi.moveIntToString(parentMove) + " to get to this node.");
    }

    // tree-like functions 
    // get the highest value child 
    public Node getMaxChild()  {
        int max = -10000;
        int ind = 0;
        for (Node child : this.children) {
            if (child.getUtility() > max) {
                max = child.getUtility();
                ind = this.children.indexOf(child);
            }
        }
        return this.children.get(ind);
    }

    // get the lowest value child
    public Node getMinChild() {
        int min = 10000;
        int ind = 0;
        for (Node child : this.children) {
            if (child.getUtility() < min) {
                min = child.getUtility();
                ind = this.children.indexOf(child);
            }
        }
        return this.children.get(ind);
    }

    // get the parent state/node
    public Node getParent() {
        return this.parent;
    }

    // get the number of children states
    public int getChildrenSize() {
        return this.children.size();
    }

    public void setUtility(int new_utility) {
        this.utility = new_utility;
    }
}
