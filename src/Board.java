import java.util.*;

// interface for all node/state spaces/boards in the minimax tree
interface Boards {
    // copy board 
    // public Board copyBoard(Board org_board);

    // get move from location
    public char getMove(int x, int y);

    // flip the move at one location 
    public void flipMove(int x, int y);

    // put a move on the board
    public void setMove(int x, int y, char player);

    // get the win/lose value of the board
    public char countWin();

    // print the board 
    public void printBoard();

    // get the board size 
    public int getBoardSize();
}

public class Board implements Boards {
    char[][] board;
    private int size;

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

    public char[][] copyBoardChar() {
        char[][] newboard = new char[this.size][this.size];
        for (int i = 0; i < this.size; i++) {
            newboard[i] = Arrays.copyOf(this.board[i], this.size);
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

    public int getUtilityWin(char player, char opponent) {
        int player_count = 0;
        int opponent_count = 0;

        // count the player's total moves on board
        for (char[] row : this.board) {
            for (char square : row) {
                if (square == player) {
                    player_count++;
                } else if (square == opponent) {
                    opponent_count++;
                }
            }
        }

        return opponent_count-player_count;
    }

    public char countWin() {
        int x_count = 0;
        int o_count = 0;

        // count the player's total moves on board
        for (char[] row : this.board) {
            for (char square : row) {
                if (square == 'X') {
                    x_count++;
                } else if (square == 'O') {
                    o_count++;
                }
            }
        }

        if (x_count > o_count) {
            return 'X';
        } else if (x_count < o_count) {
            return 'O';
        } else {
            return '_';
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

    public int getBoardSize() {
        return this.size;
    }
}