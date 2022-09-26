import java.util.*;

public class Minimax {

    // recursive function to make tree 

    // recursion function to generate game tree 
    public static void makeMinimaxTree(Board org_board, Node root_node, char player, char opponent) {
        int root_utility = Minimax.makeMaxTree(org_board, root_node, player, opponent);
        root_node.setUtility(root_utility);
    }

    // recursion function to generate game tree with Alpha Beta pruning 
    public static void makeMinimaxABTree(Board org_board, Node root_node, char player, char opponent) {
        int root_utility = Minimax.makeMaxABTree(org_board, root_node, -10000, 10000, player, opponent);
        root_node.setUtility(root_utility);
    }

    // recursion function to generate game tree with Alpha Beta pruning and Heuristic function 
    public static void makeHMinimaxTree(Board org_board, Node root_node, char player, char opponent) {
        int root_utility = Minimax.makeHMaxTree(org_board, root_node, -10000, 10000, player, opponent);
        root_node.setUtility(root_utility);
    }

    public static int makeMaxTree(Board org_board, Node node, char player, char opponent) {
        int utility = -10000;

        // System.out.println("Make Max Tree");

        // find children nodes of this state
        List<Node> children = Reversi.getChildrenStates(org_board, node, opponent);

        // if this is the terminal node
        if (children.size() == 0) {
            // if this is the end node then settle the result
            /*char winner = org_board.countWin();
            if (winner == opponent) {
                utility = 1;
            } else if (winner == player) {
                utility = -1;
            } else {
                utility = 0;
            }
            */
            utility = org_board.getUtilityWin(player, opponent);
            node.setUtility(utility);
            // System.out.println("Utility " + utility + " at this board state:");
            // org_board.printBoard();
            return utility;
        }
        
        // if there is valid moves
        node.children = children;
        for (Node child: children) {
            Board newBoard = Reversi.makeMove(org_board, child.parentMove[0], child.parentMove[1], opponent);
            int new_utility = makeMinTree(newBoard, child, player, opponent);
            if (new_utility > utility) {
                utility = new_utility;
            }
        }
        node.setUtility(utility);

        return utility;
    }

    public static int makeMinTree(Board org_board, Node node, char player, char opponent) {
        int utility = 10000;

        // System.out.println("Make Min Tree");

        // find children nodes of this state
        List<Node> children = Reversi.getChildrenStates(org_board, node, player);

        // if this is the terminal node 
        if (children.size() == 0) {
            // if this is the end node then settle the result
            /*char winner = org_board.countWin();
            if (winner == opponent) {
                utility = 1;
            } else if (winner == player) {
                utility = -1;
            } else {
                utility = 0;
            }
            */
            utility = org_board.getUtilityWin(player, opponent);
            node.setUtility(utility);
            // System.out.println("Utility " + utility + " at this board state:");
            // org_board.printBoard();
            return utility;
        }

        // if there is valid moves
        node.children = children;
        for (Node child: children) {
            Board newBoard = Reversi.makeMove(org_board, child.parentMove[0], child.parentMove[1], player);
            int new_utility = makeMaxTree(newBoard, child, player, opponent);
            if (new_utility < utility) {
                utility = new_utility;
            }
        }
        node.setUtility(utility);

        return utility;
    }
    
    public static int makeMaxABTree(Board org_board, Node node, int alpha, int beta, char player, char opponent) {
        int utility = -10000;

        // System.out.println("Make Max Tree");

        // find children nodes of this state
        List<Node> children = Reversi.getChildrenStates(org_board, node, opponent);

        // if this is the terminal node
        if (children.size() == 0) {
            utility = org_board.getUtilityWin(player, opponent);
            node.setUtility(utility);
            // System.out.println("Utility " + utility + " at this board state:");
            // org_board.printBoard();
            return utility;
        }
        
        // if there is valid moves
        node.children = children;
        for (Node child: children) {
            Board newBoard = Reversi.makeMove(org_board, child.parentMove[0], child.parentMove[1], opponent);
            int new_utility = makeMinABTree(newBoard, child, alpha, beta, player, opponent);
            if (new_utility > utility) {
                utility = new_utility;
            }
            if (alpha < new_utility) {
                alpha = new_utility;
            }
            if (beta <= alpha) {
                break;
            }
        }
        node.setUtility(utility);

        return utility;
    }

    public static int makeMinABTree(Board org_board, Node node, int alpha, int beta, char player, char opponent) {
        int utility = 10000;

        // System.out.println("Make Min Tree");

        // find children nodes of this state
        List<Node> children = Reversi.getChildrenStates(org_board, node, player);

        // if this is the terminal node 
        if (children.size() == 0) {
            // if this is the end node then settle the result
            utility = org_board.getUtilityWin(player, opponent);
            node.setUtility(utility);
            // System.out.println("Utility " + utility + " at this board state:");
            // org_board.printBoard();
            return utility;
        }

        // if there is valid moves
        node.children = children;
        for (Node child: children) {
            Board newBoard = Reversi.makeMove(org_board, child.parentMove[0], child.parentMove[1], player);
            int new_utility = makeMaxABTree(newBoard, child, alpha, beta, player, opponent);
            if (new_utility < utility) {
                utility = new_utility;
            }
            if (beta > new_utility) {
                beta = new_utility;
            }
            if (beta <= alpha) {
                break;
            }
        }
        node.setUtility(utility);

        return utility;
    }

    public static int makeHMaxTree(Board org_board, Node node, int alpha, int beta, char player, char opponent) {
        int utility = -10000;

        // System.out.println("Make Max Tree");

        // find children nodes of this state
        List<Node> children = Reversi.getChildrenStates(org_board, node, opponent);

        // if this is the terminal node
        if (children.size() == 0) {
            utility = org_board.getUtilityWin(player, opponent);
            node.setUtility(utility);
            // System.out.println("Utility " + utility + " at this board state:");
            // org_board.printBoard();
            return utility;
        }
        
        // if there is valid moves
        node.children = children;
        for (Node child: children) {
            Board newBoard = Reversi.makeMove(org_board, child.parentMove[0], child.parentMove[1], opponent);
            int new_utility = makeMinABTree(newBoard, child, alpha, beta, player, opponent);
            if (new_utility > utility) {
                utility = new_utility;
            }
            if (alpha < new_utility) {
                alpha = new_utility;
            }
            if (beta <= alpha) {
                break;
            }
        }
        node.setUtility(utility);

        return utility;
    }

    public static int makeHMinTree(Board org_board, Node node, int alpha, int beta, char player, char opponent) {
        int utility = 10000;

        // System.out.println("Make Min Tree");

        // find children nodes of this state
        List<Node> children = Reversi.getChildrenStates(org_board, node, player);

        // if this is the terminal node 
        if (children.size() == 0) {
            // if this is the end node then settle the result
            utility = org_board.getUtilityWin(player, opponent);
            node.setUtility(utility);
            // System.out.println("Utility " + utility + " at this board state:");
            // org_board.printBoard();
            return utility;
        }

        // if there is valid moves
        node.children = children;
        for (Node child: children) {
            Board newBoard = Reversi.makeMove(org_board, child.parentMove[0], child.parentMove[1], player);
            int new_utility = makeMaxABTree(newBoard, child, alpha, beta, player, opponent);
            if (new_utility < utility) {
                utility = new_utility;
            }
            if (beta > new_utility) {
                beta = new_utility;
            }
            if (beta <= alpha) {
                break;
            }
        }
        node.setUtility(utility);

        return utility;
    }


}
