package cpsc2150.extendedConnectX;
/**
 * GAVIN VAZQUEZ
 * PROJECT 4
 *  4/11/2021
 *  CPSC 2150
 *
 * @Invariant minPlayers <= totalPlayers <= maxPlayers
 * @Invariant 0 <= turnCount <= ROWS*COLS (number of spaces on board)
 * @Invariant 2 <= size of playerTokens <= maxPlayers
 *
 * Correspondence minPlayers = 2
 * Correspondence maxPlayers = 10
 */
import cpsc2150.extendedConnectX.GameBoard;
import cpsc2150.extendedConnectX.GameBoardMem;
import cpsc2150.extendedConnectX.IGameBoard;

import java.util.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GameScreen {

private static IGameBoard gameScreen;
private static char player;
private static int totalPlayers;
private static List<Character> playerTokens;
private static int turnCount;

    /**
     * This function allows the user to enter number of players, the tokens for each player,
     *  how many rows/columns for the GameBoard, how many tokens in a row required to win, and
     *  if the game will be a memory efficent game or fast game.
     *
     * @post totalPlayers = players;
     * @post playerTokens filled with input from char c
     * @post player = playerTokens.get(0);
     */
    private static void makeBoard() {

        final int minPlayers = 2;
        final int maxPlayers = 10;

        int players, rows, columns, wins = 0;
        turnCount = 0;

        GameBoard board = new GameBoard(0, 0, 0);
        Scanner s = new Scanner(System.in);

        //TOTAL PLAYERS
        System.out.println("How many players?");
        players = s.nextInt();
        totalPlayers = players;

        while (players < minPlayers) {
            System.out.println("Must be at least 2 players");
            System.out.println("How many players?");
            players = s.nextInt();
        }
        while(players > maxPlayers){
            System.out.println("Must be 10 players or fewer");
            System.out.println("How many players?");
            players = s.nextInt();
            while (players < minPlayers) {
                System.out.println("Must be at least 2 players");
                System.out.println("How many players?");
                players = s.nextInt();

            }
        }


        playerTokens = new ArrayList<>();

      s = new Scanner(System.in);
        //PLAYER TOKENS
        for(int i = 0; i < players; i++){
            System.out.println("Enter the character to represent player " + (i + 1));
            String c = s.next().toUpperCase();
            char userInput = c.charAt(0);

            while(playerTokens.contains(userInput)) {
                    System.out.println(c + " is already taken as a player token!");
                    System.out.println("Enter the character to represent player " + (i + 1));
                    c = s.next().toUpperCase();
                    userInput = c.charAt(0);
            }
            playerTokens.add(userInput);
        }

        //NUMBER OF ROWS
        System.out.println("How many rows should be on the board?");
        rows = s.nextInt();

        while (rows < board.MIN_FOR_ALL || rows > board.MAX_ROWS_COLS) {
            if (rows < board.MIN_FOR_ALL){
                System.out.println("Row cannot be less than " + board.MIN_FOR_ALL);
            }
            if (rows > board.MAX_ROWS_COLS){
                System.out.println("Row cannot be greater than " + board.MAX_ROWS_COLS);
            }
            System.out.println("How many rows should be on the board?");
            rows = s.nextInt();
        }

        //NUMBER OF COLUMNS
        System.out.println("How many columns should be on the board?");
        columns = s.nextInt();

        while (columns < board.MIN_FOR_ALL || columns > board.MAX_ROWS_COLS) {
            if (columns < board.MIN_FOR_ALL){
                System.out.println("Column cannot be less than " + board.MIN_FOR_ALL);
            }
            if(columns > board.MAX_ROWS_COLS){
                System.out.println("Column cannot be greater than " + board.MAX_ROWS_COLS);
            }
            System.out.println("How many columns should be on the board?");
            columns = s.nextInt();
        }

        //NUMBER OF WINS
        System.out.println("How many in a row to win?");
        wins = s.nextInt();

        while (wins > columns || wins > rows || wins < board.MIN_FOR_ALL || wins > board.MAX_TO_WIN) {
            if (wins < board.MIN_FOR_ALL){
                System.out.println("In a row to win must be greater than " + board.MIN_FOR_ALL + ".");
            }
            if(wins > board.MAX_TO_WIN){
                System.out.println("In a row to win must be less than " + board.MAX_TO_WIN + ".");
            }
            if (wins > columns){
                System.out.println("In a row to win cannot be greater than the number columns");
            }
            if(wins > rows){
                System.out.println("In a row to win cannot be greater than the number of rows.");
            }
            System.out.println("How many in a row to win?");
            wins = s.nextInt();
        }

        //GAME TYPE
        System.out.println("Would you like a Fast Game (F/f) or a Memory Efficient Game (M/m?)");
        String input = s.next().toUpperCase();
        char c = input.charAt(0);

        while(c != 'M' && c != 'F'){
            System.out.println("Please enter F or M");
            input = s.next().toUpperCase();
            c = input.charAt(0);
        }
        if(c == 'F'){
            gameScreen = new GameBoard(rows, columns, wins);
        }
         if(c == 'M'){
             gameScreen = new GameBoardMem(rows, columns, wins);
        }

        player = playerTokens.get(0);
    }


    public static void main(String[] args)
    {

        int userCol = 0;
        boolean stopGame = false;

        makeBoard();
        System.out.println(gameScreen);

            //RUNS LOOP UNTIL RETURNED
        while(!stopGame){

            player = playerTokens.get(turnCount % totalPlayers);

            //ASKS USER TO ENTER DESIRED COLUMN
        System.out.println("Player " + player + ", what column do you want to place your marker in?");
        Scanner s = new Scanner(System.in);
        userCol = s.nextInt();

            //CHECKS COLUMN INPUT
            while(userCol < 0 || userCol > gameScreen.getNumColumns() - 1 ||
            !gameScreen.checkIfFree(userCol)){

                //CHECKS IF COLUMN IS LESS THAN 0
                if (userCol < 0) {
                    System.out.println("Column cannot be less than 0");
                }
                //CHECKS IF COLUMN IS GREATER THAN 0
                else if (userCol > gameScreen.getNumColumns() - 1) {
                    System.out.println("Column cannot be greater than " + (gameScreen.getNumColumns() - 1));
                }
                //CHECKS IF COLUMN IS FREE
                else{
                    System.out.println("Column is full");
                }

                //REPROMTS USER FOR INPUT
                System.out.println("Player " + player + ", what column do you want to place your marker in?");
                s = new Scanner(System.in);
                userCol = s.nextInt();
            }

             //TOKEN PLACED
        gameScreen.placeToken(player, userCol);
        if(gameScreen.checkForWin(userCol) || gameScreen.checkTie()) {
            //CHECK FOR WINS
            if (gameScreen.checkForWin(userCol)) {
                System.out.println(gameScreen);
                System.out.println("Player " + player + " Won!");
                char yesOrNo = 'X';
                while (yesOrNo != 'Y' || yesOrNo != 'N' || yesOrNo != 'y' || yesOrNo != 'n') {
                    System.out.println("Would you like to play again? Y/N");
                    s = new Scanner(System.in);
                    String input = s.next();
                    yesOrNo = input.charAt(0);
                    if (yesOrNo == 'Y' || yesOrNo == 'y') {
                        turnCount = 0;
                        makeBoard();
                        break;
                    }
                    if (yesOrNo == 'N' || yesOrNo == 'n') {
                        return;

                    }
                }
            }

            //CHECKS FOR TIE
            if (gameScreen.checkTie()) {
                System.out.println(gameScreen);
                System.out.println("GAME TIED!");
                char yesOrNo = 'X';
                while (yesOrNo != 'Y' || yesOrNo != 'N' || yesOrNo != 'y' || yesOrNo != 'n') {
                    System.out.println("Would you like to play again? Y/N");
                    s = new Scanner(System.in);
                    String input = s.next();
                    yesOrNo = input.charAt(0);
                    if (yesOrNo == 'Y' || yesOrNo == 'y') {
                        turnCount = 0;
                        makeBoard();
                        break;
                    }
                    if (yesOrNo == 'N' || yesOrNo == 'n') {
                        return;
                    }
                }
            }
            System.out.println(gameScreen);
        }
        else {
            //PRINTS UPDATED GAME SCREEN
            System.out.println(gameScreen);

            //INCREMENTS TO NEXT PLAYER
            turnCount++;
            }
        }
    }
}
