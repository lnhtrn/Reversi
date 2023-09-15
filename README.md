## CSC 242 - Reversi

Reversi is a strategy board game for two players, played on an 8×8 uncheckered board. It was invented in 1883. Othello, a variant with a fixed initial setup of the board, was patented in 1971.[^1]

### How to play 

The gameplay is explained in the [Rules](https://en.wikipedia.org/wiki/Reversi#Rules) section of the Wikipedia page. The starting position of the game looks like this:

![reversi-starting-position](https://github.com/lnhtrn/Reversi/assets/72944083/af985bd0-d08e-48ae-909c-2d82ce5b26b1)

The player can choose to play as Black or White.

### How to build & Run the project (with Command Prompt & Windows)
1.  Go to the src/ folder.
2.  Type `javac Game.java` to build the game.
3.  Type `java Game` to run the game.
4.  The code will run 1 game of Reversi/Othello. If you want to play the game again, start again from step 2.

### Limitations
1.  If the player (you) does not choose the random agent (1) or the H_Minimax agent, the game will run out of memory for Reversi/Othello size 6x6 and 8x8. That means any game with size 6x6 or 8x8 must run a random agent or a H_Minimax agent.


[^1]: [Wikipedia](https://en.wikipedia.org/wiki/Reversi)
