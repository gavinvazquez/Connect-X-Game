package cpsc2150.extendedConnectX;
import cpsc2150.extendedConnectX.AbsGameBoard;
import cpsc2150.extendedConnectX.BoardPosition;
import cpsc2150.extendedConnectX.IGameBoard;

import java.util.*;
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
public class GameBoardMem extends AbsGameBoard implements IGameBoard {
    private Map<Character, List<BoardPosition>> boardMap;
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
     * @post boardMap = new HashMap<>()
     */
    public GameBoardMem(int row, int col, int numToWin) {
        ROWS = row;
        COLS = col;
        NUM_TO_WIN = numToWin;
        boardMap = new HashMap<>();
    }

    public void placeToken(char token, int column) {
        int row = getNumRows() - 1;
        BoardPosition pos = new BoardPosition(row, column);

        if (whatsAtPos(pos) == ' ') {
            if (!boardMap.containsKey(token)) {
                boardMap.put(token, new ArrayList<>());
                boardMap.get(token).add(pos);
                return;
            } else {
                boardMap.get(token).add(pos);
            }
            return;
        }

        else {
            int i;
            for (i = getNumRows() - 1; i >= 0; i--) {
                pos = new BoardPosition(i, column);
                if (whatsAtPos(pos) == ' ') {
                    break;
                }
            }
            if (!boardMap.containsKey(token)) {
                boardMap.put(token, new ArrayList<>());
                boardMap.get(token).add(pos);
                return;
            }
            else {
                boardMap.get(token).add(pos);
                return;
            }
        }
    }

    public char whatsAtPos(BoardPosition a) {
        char value = ' ';
        for (Map.Entry<Character, List<BoardPosition>> map : boardMap.entrySet()) {
            if (map.getValue().contains(a)) {
                value = map.getKey();
                return value;
            }
        }
        return value;
    }

    @Override
    public boolean isPlayerAtPos(BoardPosition a, char token) {
        ArrayList<BoardPosition> boardList = (ArrayList<BoardPosition>) boardMap.get(token);
        for (BoardPosition curr : boardList) {
            if (curr.equals(a)){
                return true;
            }
        }
        return false;
    }

    public int getNumRows() {
        return ROWS;
    }

    public int getNumColumns() {
        return COLS;
    }

    public int getNumToWin() {
        return NUM_TO_WIN;
    }
}