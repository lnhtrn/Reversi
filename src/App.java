import java.util.List;
import java.util.Scanner;

import javax.swing.plaf.synth.SynthEditorPaneUI;

public class App {
    public static void main(String[] args) throws Exception {

        /* 
        // Test function 
        Board test = new Board(4);
        test.board = new char[][] {
            "____".toCharArray(), 
            "_XO_".toCharArray(),
            "_OX_".toCharArray(),
            "OXXX".toCharArray(),
        };

        test.printBoard();

        List<Object> result = Reversi.moveCheck(test, 0, 2, 'X');
        Board newTest = (Board) result.get(1);

        if ((Boolean) result.get(0)) {
            System.out.println("There is move!");
        } else {
            System.out.println("There is no move :(");
        }

        newTest.printBoard();

        */ 

        System.out.println("Welcome to Reversi! \n");

        // initialize game
        Scanner gameScanner = new Scanner(System.in); 
        System.out.println("Choose the board size: 4, 6, or 8.");
        int boardsize = gameScanner.nextLine().charAt(0) - '0';

        System.out.println("Initialize new empty board: \n");

        System.out.println("Choose to play as X or O.");
        System.out.println("If fail to choose, the player will be automatically selected as X.");
        System.out.println("I will play as:");
        char player = gameScanner.nextLine().charAt(0);
        player = Character.toUpperCase(player);
        char opponent = 'O';
        if (player == 'O') {
            opponent = 'X';
        }

        System.out.println("Player will play as " + player + " and computer will play as " + opponent + ".\n");

        System.out.println("Type Y to play first, N to let the computer plays first.");
        Boolean max_layer = false;  
        char max_valid = gameScanner.nextLine().charAt(0);
        if (max_valid == 'N') {
            max_layer = true; // if computer plays first, root node is the max layer
        }
        
        // set up the tree 
        Tree newgame = new Tree(boardsize);
        newgame.board.printBoard();

        newgame.makeTree(player, opponent, max_layer);
        
        newgame.calculateUtilityTree();
        
        Board currentBoard = newgame.board;
        // System.out.println("Number of possible next moves: " + String.valueOf(currentBoard.getChildrenSize()));

        System.out.println("START GAME! \n");

        if (max_layer) {
            // if computer plays first, plays first move 
            currentBoard = currentBoard.getMaxChild();
            System.out.println("Computer plays " + currentBoard.parentMove + ".");
            currentBoard.printBoard();
        }

        // start game 
        Boolean playerMove = true;
        // while there are still valid moves
        while (currentBoard.children != null) {
            if (playerMove) {
                System.out.println("Type your next move (format 1a, 2b, 3c...):");
                String move = gameScanner.nextLine();
                Boolean movefound = false;
                for (Board child : currentBoard.children) {
                    // System.out.println("Test move " + child.parentMove + ".");
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

        // when there are no move, settle the result
        int win = currentBoard.getUtility();
        if (!playerMove) {
            win = -win;
        }
        if (win == 1) {
            System.out.println("The player has won!");
        } else if (win == -1) {
            System.out.println("The computer has won!");
        } else {
            System.out.println("The game ends in a draw.");
        }

        char winner = currentBoard.countWin();
        if (player == winner) {
            System.out.println("The player has won!");
        } else if (opponent == winner) {
            System.out.println("The computer has won!");
        } else {
            System.out.println("The game ends in a draw.");
        }

        currentBoard.printBoardInfo();

    }
}
