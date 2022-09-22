import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to Reversi! Initialize new empty board: \n");

        // initialize game
        Scanner gameScanner = new Scanner(System.in); 
        System.out.println("Choose to play as X or Y.");
        System.out.println("If fail to choose, the player will be automatically selected as X.");
        System.out.println("I will play as:");
        char player = gameScanner.nextLine().charAt(0);
        if (player != 'X' && player != 'O') {
            player = 'X';
        }
        char opponent = 'O';
        if (player == 'O') {
            opponent = 'X';
        }

        System.out.println("Type Y to play first, N to let the computer plays first.");
        Boolean max_layer = false; // meaning the player (root node) is not the max layer 
        char max_valid = gameScanner.nextLine().charAt(0);
        if (max_valid == 'N') {
            max_layer = true; // if computer plays first, root node is the max layer
        }
        
        // set up the tree 
        Tree newgame = new Tree(4);
        newgame.board.printBoard();

        if (max_layer) {
            newgame.makeTree(player, opponent, max_layer);
        } else {
            newgame.makeTree(opponent, player, max_layer);
        }
        
        newgame.calculateUtilityTree();
        
        Board currentBoard = newgame.board;
        System.out.println("Number of possible next moves: " + String.valueOf(currentBoard.getChildrenSize()));

        if (max_layer) {
            // if computer plays first, plays first move 
            currentBoard = currentBoard.getMaxChild();
            System.out.println("Computer plays " + currentBoard.parentMove + ".");
            currentBoard.printBoard();
        }

        // start game 
        Boolean playerMove = true;
        while (currentBoard.children != null) {
            if (playerMove) {
                System.out.println("Type your next move (format 1a, 2b, 3c...):");
                String move = gameScanner.nextLine();
                Boolean movefound = false;
                for (Board child : currentBoard.children) {
                    System.out.println("Test move " + child.parentMove + ".");
                    if (move.equals(child.parentMove)) {
                        System.out.println("Player executes move " + move + ".");
                        child.printBoard();
                        currentBoard = child;
                        playerMove = false;
                        movefound = true;
                        break;
                    }
                }
                if (!movefound) {
                    System.out.println("No valid move found.");
                }
            } else {
                currentBoard = currentBoard.getMaxChild();
                System.out.println("Computer plays " + currentBoard.parentMove + ".");
                currentBoard.printBoard();
                playerMove = true;
            }
        }

        // when winning
        if (playerMove) {
            System.out.println("The computer has won!");
        } else {
            System.out.println("The player has won!");
        }
    }
}
