import java.util.ArrayList;
import java.util.List;

public class Minimax {
    
    // recursion function to generate game tree 
    public static void makeMinimaxTree(Board org_board, char maxPlayer, char minPlayer, boolean max_layer) {
        // find current player of layer:
        char currentPlayer;
        if (max_layer) {
            currentPlayer = maxPlayer;
        } else {
            currentPlayer = minPlayer;
        }

        // find children nodes of this state
        List<Board> children = Reversi.getChildrenStates(org_board, currentPlayer);

        if (children.size() == 0) {
            // if this is the end node then settle the result
            org_board.settleWin(currentPlayer);
        } else {
            // if not end note, generate new branch 
            org_board.children = children;
            for (Board child: children) {
                makeMinimaxTree(child, maxPlayer, minPlayer, !max_layer);
            }
        }
    }
    
    // method to calculate utility of tree (no heuristic) 
    public static Integer calculateUtility(Board org_board, Boolean max_layer) {
        // if board has utility, return the utility
        if (org_board.utility == null) {
            List<Integer> children_utility = new ArrayList<Integer>();
            for (Board child : org_board.children) {
                children_utility.add(calculateUtility(child, !max_layer));
            }
            // find the min or max value of children layers 
            // if max_layer => get max value, if not then min value
            Integer utility = 0;
            for (Integer child_utility: children_utility) {
                if (max_layer && child_utility > utility) {
                    utility = child_utility;
                } else if (!max_layer && child_utility < utility) {
                    utility = child_utility;
                }
            }
            org_board.utility = utility;
        }

        return org_board.utility;
    }
}
