import java.util.*;

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
    public static String moveIntToString(Integer[] moves) {
        String move = String.valueOf(moves[0] + 1);
        move += (char) (moves[1] + 97);
        return move;
    }

    // function to check if move is valid
    // input: board object, location of next move (x,y), player (O or X)
    // output: List (moveValid, board), moveValid = true if move exists

    // this function defines the transition from state to state

    public static Integer[] moveCheck(Board board, int x, int y, char player) {
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
        boolean hasMove = false;
        while (y_new-1 >= 0) {
            y_new = y_new-1;
            if (new_Board.getMove(x, y_new) == '_') {
                break;
            }
            if (new_Board.getMove(x, y_new) == opponent) {
                hasMove = true;
            }
            if (new_Board.getMove(x, y_new) == player) {
                if (hasMove) {
                    moveValid = true;
                    int moveValidInt = (moveValid) ? 1 : 0;
                    return new Integer[] {moveValidInt, x, y};
                }
                break;
            }
        }

        // check horizontal right
        y_new = y;
        hasMove = false;
        while (y_new+1 < new_Board.getBoardSize()) {
            y_new = y_new+1;
            if (new_Board.getMove(x, y_new) == '_') {
                break;
            }
            if (new_Board.getMove(x, y_new) == opponent) {
                hasMove = true;
            }
            if (new_Board.getMove(x, y_new) == player) {
                if (hasMove) {
                    moveValid = true;
                    int moveValidInt = (moveValid) ? 1 : 0;
                    return new Integer[] {moveValidInt, x, y};
                }
                break;
            }
        }

        // check vertical up 
        x_new = x;
        hasMove = false;
        while (x_new+1 < new_Board.getBoardSize()) {
            x_new = x_new+1;
            if (new_Board.getMove(x_new, y) == '_') {
                break;
            }
            if (new_Board.getMove(x_new, y) == opponent) {
                hasMove = true;
            }
            if (new_Board.getMove(x_new, y) == player) {
                if (hasMove) {
                    moveValid = true;
                    int moveValidInt = (moveValid) ? 1 : 0;
                    return new Integer[] {moveValidInt, x, y};
                }
                break;
            }
        }

        // check vertical down
        x_new = x;
        hasMove = false;
        while (x_new-1 >= 0) {
            x_new = x_new-1;
            if (new_Board.getMove(x_new, y) == '_') {
                break;
            }
            if (new_Board.getMove(x_new, y) == opponent) {
                hasMove = true;
            }
            if (new_Board.getMove(x_new, y) == player) {
                if (hasMove) {
                    moveValid = true;
                    int moveValidInt = (moveValid) ? 1 : 0;
                    return new Integer[] {moveValidInt, x, y};
                }
                break;
            }
        }

        // check diagonal down left 
        y_new = y;
        x_new = x;
        hasMove = false;
        while (y_new-1 >= 0 && x_new+1 < new_Board.getBoardSize()) {
            y_new = y_new-1;
            x_new = x_new+1;
            if (new_Board.getMove(x_new, y_new) == '_') {
                break;
            }
            if (new_Board.getMove(x_new, y_new) == opponent) {
                hasMove = true;
            }
            if (new_Board.getMove(x_new, y_new) == player) {
                if (hasMove) {
                    moveValid = true;
                    int moveValidInt = (moveValid) ? 1 : 0;
                    return new Integer[] {moveValidInt, x, y};
                }
                break;
            }
        }

        // check diagonal up left 
        y_new = y;
        x_new = x;
        hasMove = false;
        while (y_new-1 >= 0 && x_new-1 >= 0) {
            y_new = y_new-1;
            x_new = x_new-1;
            if (new_Board.getMove(x_new, y_new) == '_') {
                break;
            }
            if (new_Board.getMove(x_new, y_new) == opponent) {
                hasMove = true;
            }
            if (new_Board.getMove(x_new, y_new) == player) {
                if (hasMove) {
                    moveValid = true;
                    int moveValidInt = (moveValid) ? 1 : 0;
                    return new Integer[] {moveValidInt, x, y};
                }
                break;
            }
        }

        // check diagonal down right
        y_new = y;
        x_new = x;
        hasMove = false;
        while (y_new+1 < new_Board.getBoardSize() && x_new+1 < new_Board.getBoardSize()) {
            y_new = y_new+1;
            x_new = x_new+1;
            if (new_Board.getMove(x_new, y_new) == '_') {
                break;
            }
            if (new_Board.getMove(x_new, y_new) == opponent) {
                hasMove = true;
            }
            if (new_Board.getMove(x_new, y_new) == player) {
                if (hasMove) {
                    moveValid = true;
                    int moveValidInt = (moveValid) ? 1 : 0;
                    return new Integer[] {moveValidInt, x, y};
                }
                break;
            }
        }

        // check diagonal up right
        y_new = y;
        x_new = x;
        hasMove = false;
        while (y_new+1 < new_Board.getBoardSize() && x_new-1 >= 0) {
            y_new = y_new+1;
            x_new = x_new-1;
            if (new_Board.getMove(x_new, y_new) == '_') {
                break;
            }
            if (new_Board.getMove(x_new, y_new) == opponent) {
                hasMove = true;
            }
            if (new_Board.getMove(x_new, y_new) == player) {
                if (hasMove) {
                    moveValid = true;
                    int moveValidInt = (moveValid) ? 1 : 0;
                    return new Integer[] {moveValidInt, x, y};
                }
                break;
            }
        }

        int moveValidInt = (moveValid) ? 1 : 0;
        return new Integer[] {moveValidInt, x, y};
    }


    // function to generate new children states (valid moves after one state)
    // input: board object 

    // this function creates the transition (child nodes) from state to state 

    public static List<Node> getChildrenStates(Board board, Node node, char player) {
        List<Node> children = new ArrayList<>();

        // check all empty cells in board
        for (int i = 0; i < board.getBoardSize(); i++) {
            for (int j = 0; j < board.getBoardSize(); j++) {
                // if cell empty
                if (board.getMove(i, j) == '_') {
                    // check move at cell
                    Integer[] result = moveCheck(board, i, j, player);
                    // if move valid, add to children list
                    if (result[0] == 1) {
                        children.add(new Node(node, new Integer[] {result[1], result[2]}, player));
                    }
                }
            }
        }
        return children;
    } 

    // function to execute a move 
    
    public static Board makeMove(Board board, int x, int y, char player) {
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
            if (new_Board.getMove(x, y_new) == '_') {
                break;
            }
            if (new_Board.getMove(x, y_new) == opponent) {
                flipmoves.add(y_new);
            }
            if (new_Board.getMove(x, y_new) == player) {
                if (flipmoves.size() > 0) {
                    // flip all opponent's moves 
                    for (Integer move : flipmoves) {
                        new_Board.flipMove(x, move);
                    }
                }
                break;
            }
        }

        // check horizontal right
        y_new = y;
        flipmoves = new ArrayList<Integer>();
        while (y_new+1 < new_Board.getBoardSize()) {
            y_new = y_new+1;
            if (new_Board.getMove(x, y_new) == '_') {
                break;
            }
            if (new_Board.getMove(x, y_new) == opponent) {
                flipmoves.add(y_new);
            }
            if (new_Board.getMove(x, y_new) == player) {
                if (flipmoves.size() > 0) {
                    // flip all opponent's moves 
                    for (Integer move : flipmoves) {
                        new_Board.flipMove(x, move);
                    }
                }
                break;
            }
        }

        // check vertical up 
        x_new = x;
        flipmoves = new ArrayList<Integer>();
        while (x_new+1 < new_Board.getBoardSize()) {
            x_new = x_new+1;
            if (new_Board.getMove(x_new, y) == '_') {
                break;
            }
            if (new_Board.getMove(x_new, y) == opponent) {
                flipmoves.add(x_new);
            }
            if (new_Board.getMove(x_new, y) == player) {
                if (flipmoves.size() > 0) {
                    // flip all opponent's moves 
                    for (Integer move : flipmoves) {
                        new_Board.flipMove(move, y);
                    }
                }
                break;
            }
        }

        // check vertical down
        x_new = x;
        flipmoves = new ArrayList<Integer>();
        while (x_new-1 >= 0) {
            x_new = x_new-1;
            if (new_Board.getMove(x_new, y) == '_') {
                break;
            }
            if (new_Board.getMove(x_new, y) == opponent) {
                flipmoves.add(x_new);
            }
            if (new_Board.getMove(x_new, y) == player) {
                if (flipmoves.size() > 0) {
                    // flip all opponent's moves 
                    for (Integer move : flipmoves) {
                        new_Board.flipMove(move, y);
                    }
                }
                break;
            }
        }

        // check diagonal down left 
        y_new = y;
        x_new = x;
        List<Integer[]> dia_flipmoves = new ArrayList<Integer[]>();
        while (y_new-1 >= 0 && x_new+1 < new_Board.getBoardSize()) {
            y_new = y_new-1;
            x_new = x_new+1;
            if (new_Board.getMove(x_new, y_new) == '_') {
                break;
            }
            if (new_Board.getMove(x_new, y_new) == opponent) {
                dia_flipmoves.add(new Integer[] {x_new, y_new});
            }
            if (new_Board.getMove(x_new, y_new) == player) {
                if (dia_flipmoves.size() > 0) {
                    // flip all opponent's moves 
                    for (Integer[] moves : dia_flipmoves) {
                        new_Board.flipMove(moves[0], moves[1]);
                    }
                }
                break;
            }
        }

        // check diagonal up left 
        y_new = y;
        x_new = x;
        dia_flipmoves = new ArrayList<Integer[]>();
        while (y_new-1 >= 0 && x_new-1 >= 0) {
            y_new = y_new-1;
            x_new = x_new-1;
            if (new_Board.getMove(x_new, y_new) == '_') {
                break;
            }
            if (new_Board.getMove(x_new, y_new) == opponent) {
                dia_flipmoves.add(new Integer[] {x_new, y_new});
            }
            if (new_Board.getMove(x_new, y_new) == player) {
                if (dia_flipmoves.size() > 0) {
                    // flip all opponent's moves 
                    for (Integer[] moves : dia_flipmoves) {
                        new_Board.flipMove(moves[0], moves[1]);
                    }
                }
                break;
            }
        }

        // check diagonal down right
        y_new = y;
        x_new = x;
        dia_flipmoves = new ArrayList<Integer[]>();
        while (y_new+1 < new_Board.getBoardSize() && x_new+1 < new_Board.getBoardSize()) {
            y_new = y_new+1;
            x_new = x_new+1;
            if (new_Board.getMove(x_new, y_new) == '_') {
                break;
            }
            if (new_Board.getMove(x_new, y_new) == opponent) {
                dia_flipmoves.add(new Integer[] {x_new, y_new});
            }
            if (new_Board.getMove(x_new, y_new) == player) {
                if (dia_flipmoves.size() > 0) {
                    // flip all opponent's moves 
                    for (Integer[] moves : dia_flipmoves) {
                        new_Board.flipMove(moves[0], moves[1]);
                    }
                }
                break;
            }
        }

        // check diagonal up right
        y_new = y;
        x_new = x;
        dia_flipmoves = new ArrayList<Integer[]>();
        while (y_new+1 < new_Board.getBoardSize() && x_new-1 >= 0) {
            y_new = y_new+1;
            x_new = x_new-1;
            if (new_Board.getMove(x_new, y_new) == '_') {
                break;
            }
            if (new_Board.getMove(x_new, y_new) == opponent) {
                dia_flipmoves.add(new Integer[] {x_new, y_new});
            }
            if (new_Board.getMove(x_new, y_new) == player) {
                if (dia_flipmoves.size() > 0) {
                    // flip all opponent's moves 
                    for (Integer[] moves : dia_flipmoves) {
                        new_Board.flipMove(moves[0], moves[1]);
                    }
                }
                break;
            }
        }

        new_Board.setMove(x, y, player);

        return new_Board;
    }

}
