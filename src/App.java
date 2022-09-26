import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        /*
        // Test function 
        Tree game = new Tree(4);
        Board current = game.board;
        current.board = new char[][] {
            "____".toCharArray(), 
            "_XO_".toCharArray(),
            "_OX_".toCharArray(),
            "____".toCharArray(),
        };

        current.printBoard();

        // Reversi.getChildrenStates(current, node, 'X');

        current = Reversi.makeMove(current, 3, 1, 'X');

        current.printBoard();

        
        List<Board> available = Reversi.getChildrenStates(game.board, 'O');
        
        for (Board child: available) {
            child.printBoard();
        }

        System.out.println("Print the Max tree:");
        
        Tree.makeMaxTree(game.board, 'X', 'O');
        
        for (Board child: game.board.children) {
            child.printBoard();
        } 

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

        
        // set up the tree 
        Tree newgame = new Tree(boardsize);
        newgame.board.printBoard();

        // newgame.makeTree(player, opponent);
        
        Board gameBoard = newgame.board;
        Node currentNode = newgame.node;
        // System.out.println("Number of possible next moves: " + String.valueOf(currentBoard.getChildrenSize()));

        System.out.println("START GAME! \n");

        // generate possible next moves for player
        currentNode.children = Reversi.getChildrenStates(gameBoard, currentNode, player);

        // for (Board child: newgame.board.children) { child.printBoard(); }

        // start game 
        Boolean playerMove = true;
        // while there are still valid moves
        while (currentNode.getChildrenSize() > 0) {
            if (playerMove) {
                System.out.println("There are " + String.valueOf(currentNode.getChildrenSize()) + " possible valid moves.");
                System.out.println("Type your next move (format 1a, 2b, 3c...):");
                String move = gameScanner.nextLine();
                Boolean movefound = false;
                for (Node child : currentNode.children) {
                    String childMove = Reversi.moveIntToString(child.parentMove);
                    if (move.equals(childMove)) {
                        System.out.println("Player executes move " + move + ".");
                        gameBoard = Reversi.makeMove(gameBoard, child.parentMove[0], child.parentMove[1], player);
                        gameBoard.printBoard();
                        currentNode = child;
                        playerMove = false;
                        movefound = true;
                        currentNode.children = Reversi.getChildrenStates(gameBoard, currentNode, opponent);
                        System.out.println("There are " + String.valueOf(currentNode.getChildrenSize()) + " possible valid moves for the computer.");
                        break;
                    }
                }
                if (!movefound) {
                    System.out.println("No valid move found.");
                }
            } else {
                System.out.println("The computer will make its next move.");
                long start = System.nanoTime();
                Minimax.makeMinimaxTree(gameBoard, currentNode, player, opponent);
                for (Node child: currentNode.children) {
                    child.printNodeInfo();
                }
                currentNode = currentNode.getMaxChild();
                currentNode.printNodeInfo();
                long finish = System.nanoTime();
                long timeElapsed = (finish - start)/1000000;
                System.out.println("Time elapsed (in miliseconds): " + timeElapsed);
                String move = Reversi.moveIntToString(currentNode.parentMove);
                System.out.println("Computer plays " + move + ".");
                gameBoard = Reversi.makeMove(gameBoard, currentNode.parentMove[0], currentNode.parentMove[1], opponent);
                gameBoard.printBoard();
                playerMove = true;
                currentNode.children = Reversi.getChildrenStates(gameBoard, currentNode, player);
            }
        }

        // when there are no move, settle the result
        // get utility 
        if (playerMove) {
            System.out.println("Player has no valid move. Game ends.");
            Minimax.makeMinTree(gameBoard, currentNode, player, opponent); 
        } else {
            System.out.println("Computer has no valid move. Game ends.");
            Minimax.makeMaxTree(gameBoard, currentNode, player, opponent);
        }

        // anounce winner
        int win = currentNode.getUtility();
        if (win == 1) {
            System.out.println("The computer has won!");
        } else if (win == -1) {
            System.out.println("The player has won!");
        } else {
            System.out.println("The game ends in a draw.");
        }

        //*/
    }
}
