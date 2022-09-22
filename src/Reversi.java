import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// class of moves/actions for states 
public class Reversi {

    // translate string move to array 
    public static int[] moveStringToInt(String move) {
        move = move.toLowerCase();
        int x = Character.getNumericValue(move.charAt(0)) - 1;
        int y = move.charAt(1) - 97;
        return new int[] {x, y};
    }

    // translate array move to string 
    public static String moveIntToString(int x, int y) {
        String move = String.valueOf(x + 1);
        move += (char) (y+97);
        return move;
    }

    // function to check if move is valid
    // input: board object, location of next move (x,y), player (O or X)
    // output: List (moveValid, board), moveValid = true if move exists
    public static List<Object> moveCheck(Board board, int x, int y, char player) {
        boolean moveValid = false;
        Board new_Board = Board.copyBoard(board); 
        int x_new = 0;
        int y_new = 0;

        char opponent;
        if (player == 'X') {
            opponent = 'O';
        } else {
            opponent = 'X';
        }

        // check horizontal left
        y_new = y;
        List<Integer> flipmoves = new ArrayList<Integer>();
        while (y_new-1 >= 0) {
            y_new = y_new-1;
            if (new_Board.getMove(x, y_new) == opponent) {
                flipmoves.add(y_new);
            }
            if (new_Board.getMove(x, y_new) == player && flipmoves.size() > 0) {
                moveValid = true;
                // flip all opponent's moves 
                for (Integer move : flipmoves) {
                    new_Board.flipMove(x, move);
                }
            }
        }

        // check diagonal up left 
        y_new = y;
        x_new = x;
        List<Integer[]> dia_flipmoves = new ArrayList<Integer[]>();
        while (y_new-1 >= 0 && x_new+1 < new_Board.size) {
            y_new = y_new-1;
            x_new = x_new+1;
            if (new_Board.getMove(x_new, y_new) == opponent) {
                dia_flipmoves.add(new Integer[] {x_new, y_new});
            }
            if (new_Board.getMove(x_new, y_new) == player && dia_flipmoves.size() > 0) {
                moveValid = true;
                // flip all opponent's moves 
                for (Integer[] moves : dia_flipmoves) {
                    new_Board.flipMove(moves[0], moves[1]);
                }
            }
        }

        // check diagonal down left 
        y_new = y;
        x_new = x;
        dia_flipmoves = new ArrayList<Integer[]>();
        while (y_new-1 >= 0 && x_new-1 >= 0) {
            y_new = y_new-1;
            x_new = x_new-1;
            if (new_Board.getMove(x_new, y_new) == opponent) {
                dia_flipmoves.add(new Integer[] {x_new, y_new});
            }
            if (new_Board.getMove(x_new, y_new) == player && dia_flipmoves.size() > 0) {
                moveValid = true;
                // flip all opponent's moves 
                for (Integer[] moves : dia_flipmoves) {
                    new_Board.flipMove(moves[0], moves[1]);
                }
            }
        }

        // check horizontal right
        y_new = y;
        flipmoves = new ArrayList<Integer>();
        while (y_new+1 < new_Board.size) {
            y_new = y_new+1;
            if (new_Board.getMove(x, y_new) == opponent) {
                flipmoves.add(y_new);
            }
            if (new_Board.getMove(x, y_new) == player && flipmoves.size() > 0) {
                moveValid = true;
                // flip all opponent's moves 
                for (Integer move : flipmoves) {
                    new_Board.flipMove(x, move);
                }
            }
        }

        // check vertical up 
        x_new = x;
        flipmoves = new ArrayList<Integer>();
        while (x_new+1 < new_Board.size) {
            x_new = x_new+1;
            if (new_Board.getMove(x_new, y) == opponent) {
                flipmoves.add(x_new);
            }
            if (new_Board.getMove(x_new, y) == player && flipmoves.size() > 0) {
                moveValid = true;
                // flip all opponent's moves 
                for (Integer move : flipmoves) {
                    new_Board.flipMove(move, y);
                }
            }
        }

        // check vertical down
        x_new = x;
        flipmoves = new ArrayList<Integer>();
        while (x_new-1 >= 0) {
            x_new = x_new-1;
            if (new_Board.getMove(x_new, y) == opponent) {
                flipmoves.add(x_new);
            }
            if (new_Board.getMove(x_new, y) == player && flipmoves.size() > 0) {
                moveValid = true;
                // flip all opponent's moves 
                for (Integer move : flipmoves) {
                    new_Board.flipMove(move, y);
                }
            }
        }

        // check diagonal up right
        y_new = y;
        x_new = x;
        dia_flipmoves = new ArrayList<Integer[]>();
        while (y_new+1 < new_Board.size && x_new+1 < new_Board.size) {
            y_new = y_new+1;
            x_new = x_new+1;
            if (new_Board.getMove(x_new, y_new) == opponent) {
                dia_flipmoves.add(new Integer[] {x_new, y_new});
            }
            if (new_Board.getMove(x_new, y_new) == player && dia_flipmoves.size() > 0) {
                moveValid = true;
                // flip all opponent's moves 
                for (Integer[] moves : dia_flipmoves) {
                    new_Board.flipMove(moves[0], moves[1]);
                }
            }
        }

        // check diagonal down right
        y_new = y;
        x_new = x;
        dia_flipmoves = new ArrayList<Integer[]>();
        while (y_new+1 < new_Board.size && x_new-1 >= 0) {
            y_new = y_new+1;
            x_new = x_new-1;
            if (new_Board.getMove(x_new, y_new) == opponent) {
                dia_flipmoves.add(new Integer[] {x_new, y_new});
            }
            if (new_Board.getMove(x_new, y_new) == player && dia_flipmoves.size() > 0) {
                moveValid = true;
                // flip all opponent's moves 
                for (Integer[] moves : dia_flipmoves) {
                    new_Board.flipMove(moves[0], moves[1]);
                }
            }
        }

        if (moveValid) {
            new_Board.setMove(x, y, player);
            new_Board.parentMove = moveIntToString(x, y);
            new_Board.parent = board;
        }

        return Arrays.asList(moveValid, new_Board);
    }


    // function to generate new children states (valid moves after one state)
    // input: board object 
    public static List<Board> getChildrenStates(Board board, char player) {
        List<Board> children = new ArrayList<>();

        // check all empty cells in board
        for (int i = 0; i < board.size; i++) {
            for (int j = 0; j < board.size; j++) {
                // if cell empty
                if (board.getMove(i, j) == '_') {
                    // check move at cell
                    List<Object> result = moveCheck(board, i, j, player);
                    // if move valid, add to children list
                    if ((Boolean) result.get(0)) {
                        children.add((Board) result.get(1));
                    }
                }
            }
        }
        return children;
    } 

}
