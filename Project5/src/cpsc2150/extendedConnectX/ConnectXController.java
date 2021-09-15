package cpsc2150.extendedConnectX;

/**
 * The controller class will handle communication between our View and our Model (IGameBoard)
 * <p>
 * This is where you will write code
 * <p>
 * You will need to include your IGameBoard interface
 * and both of the IGameBoard implementations from Project 4
 * If your code was correct you will not need to make any changes to your IGameBoard implementation class
 */
public class ConnectXController {

    //our current game that is being played
    private final IGameBoard curGame;

    //The screen that provides our view
    private final ConnectXView screen;

    public static final int MAX_PLAYERS = 10;
    //our play tokens are hard coded. We could make a screen to get those from the user, but

    private final int numPlayers;
    private final char[] tokens = new char[] {'X', 'O', 'M', 'P', 'Y', 'W', 'M', 'A', 'U', 'T'};
    private int player;
    private boolean stopGame;

    /**
     * @param model the board implementation
     * @param view  the screen that is shown
     * @post the controller will respond to actions on the view using the model.
     */
    ConnectXController(IGameBoard model, ConnectXView view, int np) {
        this.curGame = model;
        this.screen = view;
        numPlayers = np;

    }

    /**
     * @param col the column of the activated button
     * @post will allow the player to place a token in the column if it is not full, otherwise it will display an error
     * and allow them to pick again. Will check for a win as well. If a player wins it will allow for them to play another
     * game hitting any button
     */
    public void processButtonClick(int col) {
        int row = 0;
        BoardPosition pos;

        //Starts a new game
        if(stopGame){this.newGame();}

        else{
            if(!curGame.checkIfFree(col)){
                screen.setMessage("This column is full, please pick again.");
                return;
            }

            for(int i = curGame.getNumRows() - 1; i > 0; i--){
                pos = new BoardPosition(i, col);
                if(curGame.whatsAtPos(pos) == ' '){
                    row = i;
                    break;
                }
            }

            BoardPosition p = new BoardPosition((curGame.getNumRows() - (row + 1)), col);

            curGame.placeToken(tokens[player], col);

            screen.setMarker(p.getRow(), col, tokens[player]);

            //Check if player won
            if(curGame.checkForWin(col)){
                stopGame = true;
                screen.setMessage(tokens[player] + " won! Press a button to play again!");
            }

            //Check if players tied
            else if(curGame.checkTie()){
                stopGame = true;
                screen.setMessage("Tied game! Press a button to play again!");
            }

            //Turn counter
            else{
                player++;
                //resets the player count
                if(numPlayers == player){ player = 0;}

                screen.setMessage("It is " + tokens[player] + "'s turn!");
            }
        }

    }

    /**
     * This method will start a new game by returning to the setup screen and controller
     */
    private void newGame()
    {
        //close the current screen
        screen.dispose();
        //start back at the set up menu
        SetupView screen = new SetupView();
        SetupController controller = new SetupController(screen);
        screen.registerObserver(controller);
    }
}