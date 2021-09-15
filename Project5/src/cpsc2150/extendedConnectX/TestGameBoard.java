package cpsc2150.extendedConnectX;
import cpsc2150.extendedConnectX.BoardPosition;
import cpsc2150.extendedConnectX.GameBoard;
import cpsc2150.extendedConnectX.IGameBoard;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 * GAVIN VAZQUEZ
 * PROJECT 4
 *  4/11/2021
 *  CPSC 2150
 */

public class TestGameBoard {

    private IGameBoard GameFactory(int row, int col, int numToWin){
        return new GameBoard(row, col, numToWin);
    }

    private String GameBoardString(char [][] gameBoard) {
        String s = "";
        for (int i = 0; i < gameBoard[0].length; i++) {
            if (i < 10) {
                s += "| " + i;
            } else {
                s += "|" + i;
            }
        }

        s += "|\n";

        for (int i = gameBoard.length - 1; i >= 0; i--) {
            for (int j = 0; j < gameBoard[0].length; j++) {
                BoardPosition pos = new BoardPosition(i, j);
                s = s + "| " + gameBoard[i][j];
            }
            s += "|\n";
        }
        return s;
    }

    @Test
    public void testConstructor_check_max_values() {
        int row = 100;
        int col = 100;
        int num_to_win = 25;

        char[][] gameBoard = new char[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                gameBoard[i][j] = ' ';
            }
        }

        IGameBoard gb = GameFactory(row, col, num_to_win);
        assertEquals(gb.getNumToWin(), num_to_win);
        assertEquals(gb.toString(), (GameBoardString(gameBoard)));
    }

    @Test
    public void testConstructor_check_min_values() {
        int row = 3;
        int col = 3;
        int num_to_win = 3;

        char[][] gameBoard = new char[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                gameBoard[i][j] = ' ';
            }
        }
        IGameBoard gb = GameFactory(row, col, num_to_win);

        assertEquals(gb.getNumToWin(), num_to_win);
        assertEquals(gb.toString(), (GameBoardString(gameBoard)));
    }

    @Test
    public void testConstructor_routine() {
        int row = 5;
        int col = 8;
        int num_to_win = 5;

        char[][] gameBoard = new char[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                gameBoard[i][j] = ' ';
            }
        }
        IGameBoard gb = GameFactory(row, col, num_to_win);

        assertEquals(gb.getNumToWin(), num_to_win);
        assertEquals(gb.toString(), (GameBoardString(gameBoard)));
    }

    @Test
    public void testPlaceToken_routine_column() {
        int row = 3;
        int col = 3;
        int num_to_win = 3;
        char[][] gameBoard = new char[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                gameBoard[i][j] = ' ';
            }
        }
        IGameBoard gb = GameFactory(row, col, num_to_win);

        gb.placeToken('X', 1);

        gameBoard[0][1] = 'X';
        assertEquals(gb.toString(), (GameBoardString(gameBoard)));
    }

    @Test
    public void testPlaceToken_in_bottom_right() {
        int row = 5;
        int col = 5;
        int num_to_win = 4;
        char[][] gameBoard = new char[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                gameBoard[i][j] = ' ';
            }
        }
        IGameBoard gb = GameFactory(row, col, num_to_win);

        gb.placeToken('X', 4);

        gameBoard[0][4] = 'X';

        assertEquals(gb.toString(), (GameBoardString(gameBoard)));
    }

    @Test
    public void testPlaceToken_in_almost_full_column() {
        int row = 100;
        int col = 100;
        int num_to_win = 25;
        char[][] gameBoard = new char[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                gameBoard[i][j] = ' ';
            }
        }
        IGameBoard gb = GameFactory(row, col, num_to_win);
        for (int i = 0; i < row - 1; i++) {
            if (i % 2 == 0) {
                gb.placeToken('O', 99);
                gameBoard[i][99] = 'O';
            } else {
                gb.placeToken('X', 99);
                gameBoard[i][99] = 'X';
            }
        }

        gb.placeToken('O', 99);

        gameBoard[99][99] = 'O';

        assertEquals(gb.toString(), (GameBoardString(gameBoard)));
    }

    @Test
    public void testPlaceToken_middle_of_board() {
        int row = 5;
        int col = 5;
        int num_to_win = 3;

        char[][] gameBoard = new char[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                gameBoard[i][j] = ' ';
            }
        }
        IGameBoard gb = GameFactory(row, col, num_to_win);
        for (int i = 0; i < 2 ; i++) {
            if (i % 2 == 0) {
                gb.placeToken('X', 2);
                gameBoard[i][2] = 'X';
            } else {
                gb.placeToken('O', 2);
                gameBoard[i][2] = 'O';
            }
        }

        gameBoard[2][2] = 'X';
        gb.placeToken('X', 2);

        assertEquals(gb.toString(), (GameBoardString(gameBoard)));
    }

    @Test
    public void testPlaceToken_half_full() {
        int row = 4;
        int col = 4;
        int num_to_win = 4;

        char[][] gameBoard = new char[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                gameBoard[i][j] = ' ';
            }
        }
        IGameBoard gb = GameFactory(row, col, num_to_win);

        gb.placeToken('X', 0);
        gameBoard[0][0] = 'X';
        gb.placeToken('X', 0);
        gameBoard[1][0] = 'X';

        BoardPosition pos = new BoardPosition(1,0);
        assertEquals(gb.toString(), (GameBoardString(gameBoard)));
    }
    @Test
    public void testCheckIfFree_full_column() {
        int row = 3;
        int col = 3;
        int num_to_win = 3;
        IGameBoard gb = GameFactory(row, col, num_to_win);

        gb.placeToken('X', 2);
        gb.placeToken('O', 2);
        gb.placeToken('X', 2);

        assertEquals(gb.checkIfFree(2), false);
    }

    @Test
    public void testCheckIfFree_column_available() {
        int row = 5;
        int col = 5;
        int num_to_win = 5;
        IGameBoard gb = GameFactory(row, col, num_to_win);

        gb.placeToken('X', 0);

        assertEquals(gb.checkIfFree(0), true);
    }

    @Test
    public void testCheckIfFree_last_column_avalible() {
        int row = 5;
        int col = 5;
        int num_to_win = 5;
        IGameBoard gb = GameFactory(row, col, num_to_win);

        gb.placeToken('X', 3);
        gb.placeToken('X', 3);
        gb.placeToken('X', 3);
        gb.placeToken('X', 3);


        assertEquals(gb.checkIfFree(3), true);
    }

    @Test
    public void testCheckHorizWin_invalid_win(){
        int row = 3;
        int col = 3;
        int num_to_win = 3;
        IGameBoard gb = GameFactory(row, col, num_to_win);

        gb.placeToken('O', 0);
        gb.placeToken('X', 2);
        gb.placeToken('0', 1);

        BoardPosition pos = new BoardPosition(2, 1);
        assertEquals(gb.checkHorizWin(pos, 'O'), false);
    }

    @Test
    public void testCheckHorizWin_bottom_right_win(){
        int row = 3;
        int col = 3;
        int num_to_win = 3;
        IGameBoard gb = GameFactory(row, col, num_to_win);

        gb.placeToken('X', 0);
        gb.placeToken('O', 0);
        gb.placeToken('X', 1);
        gb.placeToken('O', 0);
        gb.placeToken('X', 2);

        BoardPosition pos = new BoardPosition(2, 2);
        assertEquals(gb.checkHorizWin(pos, 'X'), true);
    }

    @Test
    public void testCheckHorizWin_lower_left_corner_win(){
        int row = 3;
        int col = 3;
        int num_to_win = 3;
        IGameBoard gb = GameFactory(row, col, num_to_win);

        gb.placeToken('X', 2);
        gb.placeToken('X', 1);
        gb.placeToken('X', 0);

        BoardPosition pos = new BoardPosition(2, 2);
        assertEquals(gb.checkHorizWin(pos, 'X'), true);
    }

    @Test
    public void testCheckHorizWin_inbetween_tokens(){
        int row = 5;
        int col = 5;
        int num_to_win = 3;
        IGameBoard gb = GameFactory(row, col, num_to_win);

        gb.placeToken('X', 3);
        gb.placeToken('X', 1);
        gb.placeToken('X', 2);

        BoardPosition pos = new BoardPosition(4, 2);
        assertTrue(gb.checkHorizWin(pos, 'X'));
    }

    @Test
    public void testCheckVertWin_invalid_win_below(){
        int row = 5;
        int col = 5;
        int num_to_win = 3;
        IGameBoard gb = GameFactory(row, col, num_to_win);

        gb.placeToken('X', 1);
        gb.placeToken('X', 1);
        gb.placeToken('O', 1);

        BoardPosition pos = new BoardPosition(2, 1);
        assertEquals(gb.checkHorizWin(pos, 'O'), false);
    }

    @Test
    public void testCheckVertWin_full_vert_column_win() {
        int row = 5;
        int col = 5;
        int num_to_win = 5;
        IGameBoard gb = GameFactory(row, col, num_to_win);

        gb.placeToken('X', 0);
        gb.placeToken('X', 0);
        gb.placeToken('X', 0);
        gb.placeToken('X', 0);
        gb.placeToken('X', 0);

        BoardPosition pos = new BoardPosition(0, 0);
        assertEquals(gb.checkVertWin(pos, 'X'), true);
    }

    @Test
    public void testCheckVertWin_middle_of_board_win(){
        int row = 5;
        int col = 5;
        int num_to_win = 3;
        IGameBoard gb = GameFactory(row, col, num_to_win);

        gb.placeToken('O', 3);
        gb.placeToken('X', 3);
        gb.placeToken('X', 3);
        gb.placeToken('X', 3);

        BoardPosition pos = new BoardPosition(1, 3);
        assertEquals(gb.checkVertWin(pos, 'X'), true);
    }

    @Test
    public void testCheckVertWin_invalid_win_broken_up_sequence(){
        int row = 6;
        int col = 8;
        int num_to_win = 4;
        IGameBoard gb = GameFactory(row, col, num_to_win);

        gb.placeToken('X', 4);
        gb.placeToken('O', 4);
        gb.placeToken('X', 4);
        gb.placeToken('X', 4);
        gb.placeToken('X', 4);

        BoardPosition pos = new BoardPosition(4, 3);
        assertEquals(gb.checkVertWin(pos, 'X'), false);
    }

    @Test
    public void testCheckDiagWin_bottom_left_win(){
        int row = 4;
        int col = 4;
        int num_to_win = 3;
        IGameBoard gb = GameFactory(row, col, num_to_win);

        gb.placeToken('X', 2);
        gb.placeToken('O', 2);
        gb.placeToken('X', 2);
        gb.placeToken('O', 1);
        gb.placeToken('X', 1);
        gb.placeToken('X', 0);

        BoardPosition pos = new BoardPosition(3, 0);
        assertEquals(gb.checkDiagWin(pos, 'X'), true);
    }

    @Test
    public void testCheckDiagWin_top_right_win(){
        int row = 4;
        int col = 4;
        int num_to_win = 3;
        IGameBoard gb = GameFactory(row, col, num_to_win);

        gb.placeToken('X', 0);
        gb.placeToken('O', 1);
        gb.placeToken('X', 1);
        gb.placeToken('X', 2);
        gb.placeToken('O', 2);
        gb.placeToken('X', 2);

        BoardPosition pos = new BoardPosition(0, 3);
        assertEquals(gb.checkDiagWin(pos, 'X'), true);
    }

    @Test
    public void testDiagWin_placed_diagonal_to_opponent_win(){
        int row = 5;
        int col = 5;
        int num_to_win = 3;
        IGameBoard gb = GameFactory(row, col, num_to_win);

        gb.placeToken('O', 4);
        gb.placeToken('O', 4);
        gb.placeToken('O', 4);
        gb.placeToken('O', 4);
        gb.placeToken('X', 3);
        gb.placeToken('X', 3);
        gb.placeToken('X', 2);
        gb.placeToken('X', 2);
        gb.placeToken('X', 1);
        gb.placeToken('X', 3);

        BoardPosition pos = new BoardPosition(2, 3);
        assertEquals(gb.checkDiagWin(pos, 'X'), true);
    }
    @Test
    public void testDiagWin_win_from_all_directions() {
        int row = 5;
        int col = 5;
        int num_to_win = 3;
        IGameBoard gb = GameFactory(row, col, num_to_win);

        gb.placeToken('X', 1);
        gb.placeToken('O', 2);
        gb.placeToken('X', 3);
        gb.placeToken('O', 3);
        gb.placeToken('X', 3);
        gb.placeToken('O', 1);
        gb.placeToken('X', 1);
        gb.placeToken('X', 2);
        gb.placeToken('X', 0);

        BoardPosition pos = new BoardPosition(3, 2);
        assertEquals(gb.checkDiagWin(pos, 'X'), true);

    }

    @Test
    public void testDiagWin_middle_right(){
        int row = 5;
        int col = 7;
        int num_to_win = 3;
        IGameBoard gb = GameFactory(row, col, num_to_win);

        gb.placeToken('X', 1);
        gb.placeToken('O', 1);
        gb.placeToken('X', 1);
        gb.placeToken('O', 2);
        gb.placeToken('X', 2);
        gb.placeToken('X', 3);

        BoardPosition pos = new BoardPosition(4, 3);
        assertEquals(gb.checkDiagWin(pos, 'X'), true);
    }

    @Test
    public void testDiagWin_upper_left(){
        int row = 12;
        int col = 12;
        int num_to_win = 3;

        IGameBoard gb = GameFactory(row, col, num_to_win);

        gb.placeToken('O', 2);
        gb.placeToken('X', 2);
        gb.placeToken('O', 3);
        gb.placeToken('X', 3);
        gb.placeToken('X', 4);
        gb.placeToken('X', 2);

        BoardPosition pos = new BoardPosition(9, 2);
        assertEquals(gb.checkDiagWin(pos, 'X'), true);
    }

    @Test
    public void testDiagWin_tally_reset_in_all_directions(){
        int row = 5;
        int col = 5;
        int num_to_win = 4;
        IGameBoard gb = GameFactory(row, col, num_to_win);

        gb.placeToken('X', 1);
        gb.placeToken('X', 1);
        gb.placeToken('X', 1);
        gb.placeToken('X', 1);
        gb.placeToken('X', 3);
        gb.placeToken('X', 3);
        gb.placeToken('X', 3);
        gb.placeToken('X', 3);
        gb.placeToken('X', 2);
        gb.placeToken('X', 2);
        gb.placeToken('X', 2);

        BoardPosition pos = new BoardPosition(2, 2);
        assertEquals(gb.checkDiagWin(pos, 'X'), false);
    }

    @Test
    public void testCheckTie_token_on_board() {
        int row = 3;
        int col = 3;
        int num_to_win = 3;
        IGameBoard gb = GameFactory(row, col, num_to_win);

        gb.placeToken('X', 0);

        assertEquals(gb.checkTie(), false);
    }

    @Test
    public void testCheckTie_full_board() {
        int row = 3;
        int col = 3;
        int num_to_win = 3;
        IGameBoard gb = GameFactory(row, col, num_to_win);

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                gb.placeToken('X', j);
            }
        }

        assertEquals(gb.checkTie(), true);
    }

    @Test
    public void testCheckTie_col_full(){
        int row = 3;
        int col = 3;
        int num_to_win = 3;
        IGameBoard gb = GameFactory(row, col, num_to_win);

        gb.placeToken('O', 0);
        gb.placeToken('O', 0);
        gb.placeToken('X', 0);

        assertEquals(gb.checkTie(), false);
    }

    @Test
    public void testCheckTie_all_but_one(){
        int row = 5;
        int col = 5;
        int num_to_win = 4;
        IGameBoard gb = GameFactory(row, col, num_to_win);

        for (int i = 0; i < row -1; i++) {
            for (int j = 0; j < col; j++) {
                gb.placeToken('X', j);
            }
        }

        assertEquals(gb.checkTie(), false);
    }

    @Test
    public void testIsPlayerAtPos_fullColumn(){
        int row = 3;
        int col = 3;
        int num_to_win = 3;
        IGameBoard gb = GameFactory(row, col, num_to_win);

        gb.placeToken('X', 0);
        gb.placeToken('X', 0);
        gb.placeToken('X', 0);

        BoardPosition pos = new BoardPosition(0,0);
        assertEquals(gb.isPlayerAtPos(pos, 'X'), true);
    }

    @Test
    public void testIsPlayerAtPos_bottom_left_pos() {
        int row = 3;
        int col = 3;
        int num_to_win = 3;
        IGameBoard gb = GameFactory(row, col, num_to_win);

        gb.placeToken('X', 0);

        BoardPosition pos = new BoardPosition(2, 0);
        assertEquals(gb.isPlayerAtPos(pos, 'X'), true);
    }

    @Test
    public void testIsPlayerAtPos_between_tokens(){
        int row = 3;
        int col = 3;
        int num_to_win = 3;
        IGameBoard gb = GameFactory(row, col, num_to_win);

        gb.placeToken('X', 2);
        gb.placeToken('O', 1);
        gb.placeToken('X', 0);

        BoardPosition pos = new BoardPosition(2, 1);
        assertEquals(gb.isPlayerAtPos(pos, 'O'), true);
    }

    @Test
    public void testIsPlayerAtPos_upper_right_pos(){
        int row = 3;
        int col = 3;
        int num_to_win = 3;
        IGameBoard gb = GameFactory(row, col, num_to_win);

        gb.placeToken('X', 2);
        gb.placeToken('O', 2);
        gb.placeToken('X', 2);

        BoardPosition pos = new BoardPosition(0, 2);
        assertEquals(gb.isPlayerAtPos(pos, 'X'), true);
    }

    @Test
    public void testIsPlayerAtPos_bottom_right(){
        int row = 4;
        int col = 3;
        int num_to_win = 3;

        IGameBoard gb = GameFactory(row, col, num_to_win);

        gb.placeToken('X', 0);
        gb.placeToken('O', 1);
        gb.placeToken('X', 2);
        gb.placeToken('O', 1);
        gb.placeToken('X', 2);
        gb.placeToken('O', 2);
        gb.placeToken('X', 1);

        BoardPosition pos = new BoardPosition(3, 2);
        assertEquals(gb.isPlayerAtPos(pos, 'X'), true);
    }

    @Test
    public void testwhatsAtPos_empty_board() {
        int row = 3;
        int col = 3;
        int num_to_win = 3;
        IGameBoard gb = GameFactory(row, col, num_to_win);
        BoardPosition pos = new BoardPosition(2, 1);

        assertEquals(gb.whatsAtPos(pos), ' ');
    }

    @Test
    public void testwhatsAtPos_middle_row(){
        int row = 5;
        int col = 5;
        int num_to_win = 4;

        IGameBoard gb = GameFactory(row, col, num_to_win);
        gb.placeToken('X', 3);
        gb.placeToken('O', 1);
        gb.placeToken('X', 2);
        gb.placeToken('O', 1);
        gb.placeToken('X', 2);

        BoardPosition pos = new BoardPosition(3, 1);
        assertEquals(gb.whatsAtPos(pos), 'O');
    }

    @Test
    public void testwhatsAtPos_next_to_token(){
        int row = 3;
        int col = 3;
        int num_to_win = 3;
        IGameBoard gb = GameFactory(row, col, num_to_win);

        gb.placeToken('X', 1);
        gb.placeToken('O', 1);
        gb.placeToken('X', 1);
        gb.placeToken('O', 2);

        BoardPosition pos = new BoardPosition(0, 1);
        assertEquals(gb.whatsAtPos(pos), 'X');
    }

    @Test
    public void testwhatsAtPos_upper_right(){
        int row = 3;
        int col = 3;
        int num_to_win = 3;
        IGameBoard gb = GameFactory(row, col, num_to_win);

        gb.placeToken('X', 2);
        gb.placeToken('O', 1);
        gb.placeToken('X', 1);
        gb.placeToken('O', 2);
        gb.placeToken('X', 1);
        gb.placeToken('O', 2);

        BoardPosition pos = new BoardPosition(0, 2);
        assertEquals(gb.whatsAtPos(pos), 'O');
    }

    @Test
    public void testwhatsAtPos_lower_left(){
        int row = 3;
        int col = 3;
        int num_to_win = 3;
        IGameBoard gb = GameFactory(row, col, num_to_win);

        gb.placeToken('X', 0);

        BoardPosition pos = new BoardPosition(2, 0);
        assertEquals(gb.whatsAtPos(pos),'X');
    }
}

