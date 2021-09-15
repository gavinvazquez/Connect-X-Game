package cpsc2150.extendedConnectX;
/**
 * GAVIN VAZQUEZ
 * PROJECT 4
 *  4/11/2021
 *  CPSC 2150
 *
 *
 * @Invariant 0 <= row < MAX_ROWS_COLS
 *           0 <= col < MAX_ROWS_COLS
 *
 */
public class BoardPosition {

    private int row, col;

    /**
     * Assigns the row and col to BoardPosition
     *
     * @param a represents the row position
     * @param b represents the column position
     *
     * @pre a has to be > 0
     * @pre b has to be > 0
     *
     * @post constructs a board position
     *
     */
    public BoardPosition(int a, int b){
        this.row = a;
        this.col = b;
    }

    @Override
    /**
     * Tells if the two board positions are equal
     * @param a is the instance of Board Position being compared to
     *        BoardPosition b
     *
     * @pre a is a valid object
     *
     * @return returns true if objects are equal and false if not
     */
    public boolean equals(Object a){
        if (a instanceof BoardPosition) {
            BoardPosition b = (BoardPosition) a;
            if (b.getRow() == this.row && b.getColumn() == this.col) {
                return true;
            }
            else {
                return false;
            }
        }
        return false;
    }

    /**
     * Gives the current row
     *
     * @post getRow = row
     *
     * @return the row
     */
    public int getRow(){
        return row;
    }

    /**
     * Gives the current col
     *
     * @post getColumn = col
     *
     * @return the column
     *
     */
    public int getColumn(){
        return col;
    }

    @Override
    /**
     *
     * Puts the BoardPosition into a string
     *
     * @pre GameBoard had been initalized
     *
     * @post toString = string that holds [row][col]
     *
     * @return a string in the format [row][col]
     *
     */
    public String toString(){
        return "[" +row +"]" +  "[" +col +"]";
    }
}
