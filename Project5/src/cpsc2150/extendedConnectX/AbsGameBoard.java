package cpsc2150.extendedConnectX;

/**
 * GAVIN VAZQUEZ
 * PROJECT 4
 *  4/11/2021
 *  CPSC 2150
 */
public abstract class AbsGameBoard implements IGameBoard {

    /**
     * Converts the Gameboard into a formatted string which visualized the Gameboard
     *
     * @pre GameBoard has been constructed
     *
     * @post virtualBoard = a fully formatted string that shows whole GameBoard
     *
     * @return returns a formatted string that displays the current GameBoard.
     */
    @Override
    public String toString() {
        BoardPosition[] arrayOfPos= new BoardPosition[getNumColumns() * getNumRows()];
        int doubleDigits = 10;
        String virtualBoard = "";

            for (int x = LEFT_COLUMN; x < getNumColumns(); x++){
                if (x < doubleDigits) {
                    virtualBoard += "| " + x;
                }
                else {
                    virtualBoard += "|" + x;
                }
            }

            virtualBoard += "|\n";
        int counter = 0;
        for(int i = TOP_ROW; i < getNumRows(); i++){
            for(int j = LEFT_COLUMN; j < getNumColumns(); j++){
                arrayOfPos[counter] = new BoardPosition(i,j);
                virtualBoard += "| ";
                virtualBoard +=  whatsAtPos(arrayOfPos[counter]);
                counter++;
            }
            virtualBoard += "|\n";
        }
        return virtualBoard;
    }
}