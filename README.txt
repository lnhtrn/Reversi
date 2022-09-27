Author/Student: Linh Tran 
Student ID: 31807944
Student NetID: ltran18

This project was done by me & me only. I have no other partner.

HOW TO BUILD & RUN THE PROJECT (with Command Prompt & Windows):
1.  Go to the src/ folder.
2.  Type "javac Game.java" to build the game.
3.  Type "java Game" to run the game.
4.  The code will run 1 game of Reversi/Othello.
    If you want to play the game again, start again from step 2.

LIMITATIONS:
1.  If the player (you) does not choose the random agent (1) or the H_Minimax 
    agent, the game will run out of memory for Reversi/Othello size 6x6 and 8x8.
    That means any game with size 6x6 or 8x8 must run a random agent or a 
    H_Minimax agent.

OTHER INFORMATION
1.  For the Minimax agent (2) and the Alpha-Beta pruning Minimax agent (3), 
    I use computerMoves - playerMoves to decide the utility of the terminal 
    nodes. I don't know if that counts as using a heuristic function. 

REFERENCE:
1.  I read http://home.datacomm.ch/t_wolf/tw/misc/reversi/html/index.html 
    to get an idea of how to implement the heuristic function. I did not 
    end up using the exact heuristic function described in the blog post 
    but I thought I should give credits anyways for Academic Honesty. 
2.  The structure of the code used in this project is heavily inspired by 
    the pseudocode in the textbook. 

