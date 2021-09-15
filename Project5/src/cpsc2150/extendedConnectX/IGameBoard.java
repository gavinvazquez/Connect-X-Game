package cpsc2150.extendedConnectX;

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
 * Correspondence ROWS = getNumRows();
 * Correspondence COLS = getNumCols();
 * Correspondence NUM_TO_WIN = getNumToWin();
 */
public interface IGameBoard {

    public static final int TOP_ROW = 0;
    public static final int LEFT_COLUMN = 0;
    public static final int MAX_TO_WIN = 25;
    public static final int MIN_FOR_ALL = 3;
    public static final int MAX_ROWS_COLS = 100;

    /**
     * Checks if the space in given column is free
     *
     * @param column is the column the user wants to place token
     *
     * @pre column must be <= MAX_ROWS_COLS and >= TOP_ROW
     *
     * @post checkIfFree = true if column is able to accept another
     * checkIfFree = false if column not able to accept
     *
     * @return will return true when space is avalible and false otherwise
     */
    default boolean checkIfFree(int column) {
        BoardPosition currSpot = new BoardPosition(TOP_ROW, column);
        if(whatsAtPos(currSpot)== ' '){
            return true;
        }
        return false;
    }

    /**
     * Checks if the player has won the game
     *
     * @param column is the column where the token was placed
     *
     * @pre LEFT_COLUMN <= column < MAX_ROWS_COLS, token is in column
     * @post checkForWin = true if the last token placed won the game.
     * checkForWin = false otherwise
     *
     * @return returns true if the last token placed won the game.
     * Returns false otherwise
     *
     */
    default boolean checkForWin(int column) {

        int currRow = 0;
        char currToken = 'N';
        for(int i = TOP_ROW; i < getNumRows(); i++){
            BoardPosition currSpot = new BoardPosition(i, column);

            if(whatsAtPos(currSpot) != ' '){
                currRow = i;
                currToken = whatsAtPos(currSpot);
                break;
            }
        }

        BoardPosition currPos = new BoardPosition(currRow,column);

        if(checkHorizWin(currPos, currToken)){
            return true;
        }
        if(checkVertWin(currPos, currToken)){
            return true;
        }
        if(checkDiagWin(currPos, currToken)){
            return true;
        }
        return false;
    }


    /**
     * Checks if all the top row is filled which results in a tie
     *
     * @pre game board has been created
     * checkWin = false
     * @post checkTie = true if game resulted in a tie
     * checkTie = false if the game is not a tie
     *
     * @return returns true if the game board results in a tie
     * returns false otherwise
     *
     */
    default boolean checkTie() {
        for (int i = TOP_ROW; i <= getNumColumns() - 1; i++) {
            BoardPosition currSpot = new BoardPosition(TOP_ROW, i);
            if (whatsAtPos(currSpot) == ' ') {
                return false;
            }
        }
        return true;
    }

    /**
     * Places token in desired column
     *
     * @param token the token placed on board
     * @param column is the column the user wants to place token
     *
     * @pre LEFT_COLUMN <= column < MAX_ROWS_COLS
     * @pre checkIfFree = true
     *
     * @post token is placed at lowest value for column
     *
     */
    public void placeToken ( char token, int column);

    /**
     * Checks if the player has won with NUM_TO_WIN in a row horizontially
     *
     * @param a instance of Board position that references the last space marked
     * @param token the token placed on the board
     *
     * @pre BoardPosition is valid
     *
     * @post checkHorizWin = true if the token placed won horizonatally
     * checkHorizWin = false otherwise
     *
     * @return will return true if the token at BoardPosition has won horizontally
     *  and false otherwise
     *
     */
    default boolean checkHorizWin (BoardPosition a,char token){
        int tokensToRight = 0;
        int tokensToLeft = 0;
        //CHECKS TO THE RIGHT
        for (int i = a.getColumn(); i < getNumColumns(); i++) {
            BoardPosition currSpot = new BoardPosition(a.getRow(), i);
            if (isPlayerAtPos(currSpot, token)) {
                tokensToRight++;
            } else {
                break;
            }
        }
        //CHECKS TO THE LEFT
        for (int i = a.getColumn(); i >= LEFT_COLUMN; i--) {
            BoardPosition currSpot = new BoardPosition(a.getRow(), i);
            if (isPlayerAtPos(currSpot, token)) {
                tokensToLeft++;
            } else {
                break;
            }
        }

        if ((tokensToRight + tokensToLeft) == getNumToWin() + 1) {
            return true;
        } else return false;

    }

    /**
     * Checks if the player has won with NUM_TO_WIN tokens in a row vertically
     *
     * @param a instance of Board position that references the last space marked
     * @param token the token placed on the board
     *
     * @pre BoardPosition is valid
     *
     * @post checkVertWin = true if the token placed won vertically
     * checkVertWin = false otherwise
     *
     * @return will return true if the token at BoardPosition has won vertically
     *  and false otherwise
     *
     */
    default boolean checkVertWin (BoardPosition a,char token){
        int totalTokens = 0;
        //CHECKS STRAIGHT DOWN
        for (int i = a.getRow(); i < getNumRows(); i++) {
            BoardPosition currSpot = new BoardPosition(i, a.getColumn());
            if (isPlayerAtPos(currSpot, token)) {
                totalTokens++;
                if (totalTokens == getNumToWin()) {
                    return true;
                }
            } else {
                break;
            }
        }
        return false;
    }

    /**
     * Checks if the player has won with NUM_TO_WIN in a row diagonally
     *
     * @param a instance of Board position that references the last space marked
     * @param token the token placed on the board
     *
     * @pre BoardPosition is valid
     *
     * @post checkDiagWin = true if the token placed won diagonally
     * checkDiagWin = false otherwise
     *
     * @return will return true if the token at BoardPosition has won diagonally
     *  and false otherwise
     *
     */
    default boolean checkDiagWin (BoardPosition a,char token){
        int diagToTheLeft = 0;
        int diagToTheRight = 0;
        int r = a.getRow();
        int c = a.getColumn();

        //CHECK UP TO THE LEFT
        while (r >= TOP_ROW && c > LEFT_COLUMN) {
            BoardPosition currSpot = new BoardPosition(r, c);
            if (isPlayerAtPos(currSpot, token)) {
                diagToTheLeft++;
                if (diagToTheLeft == getNumToWin()) {
                    return true;
                }

            }
            else {
                break;
            }
            r--;
            c--;
        }

        r = a.getRow();
        c = a.getColumn();
        //CHECK DOWN TO THE RIGHT
        while (r < getNumRows() - 1 && c < getNumColumns() - 1) {
            r++;
            c++;
            BoardPosition currSpot = new BoardPosition(r, c);
            if (isPlayerAtPos(currSpot, token)) {
                diagToTheLeft++;
                if (diagToTheLeft == getNumToWin()) {
                    return true;
                }
            } else {
                break;
            }
        }

        r = a.getRow();
        c = a.getColumn();
        //CHECK UP TO THE RIGHT

        while (r >= TOP_ROW && c < getNumColumns() - 1) {
            BoardPosition currSpot = new BoardPosition(r, c);
            if (isPlayerAtPos(currSpot, token)) {
                diagToTheRight++;
                if (diagToTheRight == getNumToWin()) {
                    return true;
                }

            }
            else {
                break;
            }
            r--;
            c++;
        }

        r = a.getRow();
        c = a.getColumn();
        //CHECK DOWN TO THE LEFT
        while (r < getNumRows() - 1 && c > LEFT_COLUMN) {
            r++;
            c--;
            BoardPosition currSpot = new BoardPosition(r, c);
            if (isPlayerAtPos(currSpot, token)) {
                diagToTheRight++;
                if (diagToTheRight == getNumToWin()) {
                    return true;
                }
            } else {
                break;
            }
        }
        return false;
    }

    /**
     * Tells which char is at the BoardPosition passed in
     *
     * @param a instance of BoardPosition that holds contents of space on board
     *
     * @pre BoardPosition is valid
     *
     * @post whatsAtPos = given char
     *
     * @return returns the char that is at BoardPosition a
     *
     */
    char whatsAtPos(BoardPosition a);

    /**
     * Checks if players token is specific BoardPosition
     *
     * @param a instance of BoardPosition that holds contents of space on board
     *
     * @pre BoardPosition and player token are valid
     * @post isPlayerAtPos = true if token is equal to players token
     *       isPlayerAtPos = false if token is not equal to players token
     *
     * @return returns true if given token is found at BoardPosition
     * and returns false otherwise
     *
     */
    default boolean isPlayerAtPos (BoardPosition a,char token){
        return whatsAtPos(a) == token;
    }

    /**
     * Tells how many rows are on the GameBoard
     *
     * @return the maxiumim number of rows
     * @post getNumRows = ROWS
     */
    int getNumRows();

    /**
     * Tells how many columns are on the GameBoard
     *
     * @return the maxiumum number of columns
     * @post getNumColumns = COLS
     */
    int getNumColumns();

    /**
     * Tells how many tokens are required to win
     *
     * @return the number of chips in a row to win
     * @post getNumToWin = NUM_TO_WIN
     */
    int getNumToWin();

}
