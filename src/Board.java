import java.util.Arrays;
import java.util.List;

// interface for all node/state spaces/boards in the minimax tree
interface Boards {
    // get move from location
    char getMove(int x, int y);

    // flip the move at one location 
    public void flipMove(int x, int y);

    // put a move on the board
    public void setMove(int x, int y, char player);

    // get the utility value of the board/state
    public int getUtility();

    // get the win/lose value of the board
    public void settleWin(char player);

    // print the board 
    public void printBoard();

    // get the highest value child 
    public Board getMaxChild();

    // get the lowest value child
    public Board getMinChild();

    // get the parent state/board
    public Board getParent();

    // get the number of children states
    public int getChildrenSize();
}

public class Board implements Boards {
    char[][] board;
    int size;
    Integer utility = null;

    Board parent;
    String parentMove; // to signify which move it comes from 
    List<Board> children = null;

    // initialize board
    public Board(int size) {
        this.size = size;
        board = new char[size][size];
        // fill board with empty rows
        for (char[] row: board) {
            Arrays.fill(row, '_');
        }

        // place initial moves on board 
        board[size/2][size/2] = 'X';
        board[size/2-1][size/2-1] = 'X';
        board[size/2-1][size/2] = 'O';
        board[size/2][size/2-1] = 'O';
    }

    public static Board copyBoard(Board org_board) {
        Board newboard = new Board(org_board.size);
        for (int i = 0; i < org_board.size; i++) {
            newboard.board[i] = Arrays.copyOf(org_board.board[i], org_board.size);
        }
        return newboard;
    }

    // board-related methods 
    public char getMove(int x, int y) {
        return this.board[x][y];
    }

    public void flipMove(int x, int y) {
        if (this.board[x][y] == 'X') {
            this.board[x][y] = 'O';
        } else if (this.board[x][y] == 'O') {
            this.board[x][y] = 'X';
        } else {
            // nothing happens 
        }
    }

    public void setMove(int x, int y, char player) {
        if (this.board[x][y] == '_') {
            this.board[x][y] = player;
        } else {
            // do nothing
        }
    }

    public int getUtility() {
        return this.utility;
    }

    public void settleWin(char player) {
        int playerMoves = 0;
        int opponentMoves = 0;
        char opponent = 'X';
        if (player == 'X') {
            opponent = 'O';
        }
        // count the player's total moves on board
        for (char[] row : this.board) {
            for (char square : row) {
                if (square == player) {
                    playerMoves++;
                } else if (square == opponent) {
                    opponentMoves++;
                }
            }
        }
        // settle the result 
        if (playerMoves > opponentMoves) {
            this.utility = 1;
        } else if (playerMoves == opponentMoves) {
            this.utility = 0;
        } else {
            this.utility = -1;
        }
    }

    public void printBoard() {
        System.out.print("    ");
        for (int i = 97; i < 97+this.size; i++) {
            System.out.print((char) i + " ");
        }
        System.out.println();

        int i = 1;
        for (char[] row : this.board){
            System.out.print(String.valueOf(i) + " | ");
            for (char square: row) {
                System.out.print(square + " ");
            }
            System.out.println("|");
            i++;
        }
        System.out.println();
    }

    // tree-like functions 
    // get the highest value child 
    public Board getMaxChild()  {
        int max = -1000;
        int ind = 0;
        for (Board child : this.children) {
            if (child.utility > max) {
                ind = this.children.indexOf(child);
            }
        }
        return this.children.get(ind);
    }

    // get the lowest value child
    public Board getMinChild() {
        int max = 1000;
        int ind = 0;
        for (Board child : this.children) {
            if (child.utility < max) {
                ind = this.children.indexOf(child);
            }
        }
        return this.children.get(ind);
    }

    // get the parent state/board
    public Board getParent() {
        return this.parent;
    }

    // get the number of children states
    public int getChildrenSize() {
        return this.children.size();
    }
}
