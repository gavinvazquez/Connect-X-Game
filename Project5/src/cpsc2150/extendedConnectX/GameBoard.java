package cpsc2150.extendedConnectX;

import cpsc2150.extendedConnectX.AbsGameBoard;
import cpsc2150.extendedConnectX.BoardPosition;

/**
 * GAVIN VAZQUEZ
 * PROJECT 4
 *  4/11/2021
 *  CPSC 2150
 *
 * @Invariant GameBoard will be sized [ROWS][COLS]
 * @Invariant You must stay within the GameBoard's indexes
 * @Invariant MIN_FOR_ALL <= ROWS < MAX_ROWS_COLS
 * @Invariant MIN_FOR_ALL <= COLS < MAX_ROWS_COLS
 * @Invariant MIN_FOR_ALL <= NUM_TO_WIN <= MAX_TO_WIN
 * @Invariant NUM_TO_WIN <= ROWS
 * @Invariant NUM_TO_WIN <= COLS
 *
 *
 */
public class GameBoard extends AbsGameBoard {


        private char[][] board;
        private int ROWS;
        private int COLS;
        private int NUM_TO_WIN;

        /**
         *Constructs GameBoard based off of user input passed in
         *
         * @param row how many rows in the board
         * @param col how many columns in the board
         * @param numToWin how many tokens in a row win the game
         *
         * @pre MIN_FOR_ALL <= row < MAX_ROWS_COLS
         * @pre MIN_FOR_ALL <= col < MAX_ROWS_COLS
         * @pre MIN_FOR_ALL <= numToWin <= MAX_TO_WIN
         * @pre numToWin <= row
         * @pre numToWin <= col
         *
         * @post ROWS = row
         * @post COLS = col
         * @post NUM_TO_WIN = numToWin
         * @post A [row][col] sized board is constructed where all values are set
         *          to a blank space.
         */
        public GameBoard(int row, int col, int numToWin) {
        ROWS = row;
        COLS = col;
        NUM_TO_WIN = numToWin;
        board = new char[ROWS][COLS];
        //SETS GAMEBOARD TO EMPTY SPACES
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                board[i][j] = ' ';
                }
            }
        }

        public void placeToken(char token, int column) {
                  if(board[getNumRows()-1][column] == ' '){
                          board[getNumRows()-1][column] = token;
                          return;
                  }
                  if(board[TOP_ROW + 1][column] != ' ' && board[TOP_ROW][column] == ' '){
                            board[TOP_ROW][column] = token;
                            return;
                    }
                  for(int i = getNumRows()-1; i >= TOP_ROW; i--){
                         if(board[i][column] == ' '){
                                 board[i][column] = token;
                                 return;
                         }
                  }
        }

        public char whatsAtPos(BoardPosition a){
                return board[a.getRow()][a.getColumn()];
        }

        public int getNumRows() {return ROWS;}

        public int getNumColumns(){return COLS;}

        public int getNumToWin(){return NUM_TO_WIN;}
}
