import javafx.application.Application;
import javafx.application.Application.Parameters;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;


/**
 * <p>Creates a chessboard in a window on the desktop.  The ChessBoard has a JavaFXChessBoardDisplay object that determines
 * how the individual squares of the chessboard should be drawn.</p>
 *
 * <p>The chessboard uses a ChessGame object to determine how the game should be played.  The way the chessboard works
 * is as follows.  The player selects a piece by clicking on the board, and
 * and the chessboard calls the <tt>legalPieceToPlay</tt> method of the ChessGame object.
 * If the player is allowed to select the piece, the board highlights it, and the player can select another square on
 * the board.  The chessboard then calls the <tt>makeMove</tt> method of the ChessGame object.  The ChessGame is
 * responsible for determining if the move is valid, and if it is to update the game and the chessboard
 * with the results of making that move.</p>
 *
 * @author Jami Biddle
 */
public class JavaFXChessBoard extends Application implements ChessBoard{
  
  private Stage primStage;                       //Stage to be displayed
  private int numRows;                           //Number of rows on the board
  private int numColumns;                        //Numberof columns on the board
  private JavaFXChessBoardDisplay boardDisplay;  //The display rules
  private Button[][] buttons;                    //The buttons(squares) of the game
  private ChessGame game;                        //The ChessGame rules to be played
  private ChessPiece[][] pieces;                 // stores the pieces
  private ChessGame gameRules;                   // global rules for this particular game

  /**
   * 
   * Launches the game, creating the JavaFX GUI for the game. Ultimately calls start game
   * to put the pieces on the board
   * 
   * @param primaryStage    the stage of the board
   */
  public void start(Stage primaryStage) {
    
    //The borderpane and grid pane and stage to put the bottons on
    BorderPane borderPane = new BorderPane();
    GridPane squares = new GridPane();
    this.primStage = primaryStage;

    //Retrieves the type of game to launch
    java.util.List<String> commArgs = getParameters().getRaw();
    
    //If it's a chess game, it should launch european chess JavaFX and corresponding displays
    if (commArgs.get(0).equals("chess")) {
      boardDisplay = new JavaFXEuropeanChessDisplay();
      game = new EuropeanChess();
      
        numRows = 8;
        numColumns = 8;
        pieces = new ChessPiece[numRows][numColumns];
        buttons = new Button[numRows][numColumns];
        
        //loop to create the buttons
      for (int i = 0; i < 8; i++) {
        for (int j = 0; j < 8; j++) {
          buttons[i][j] = new Button();
           buttons[i][j].setOnAction(new ChessAction());
           boardDisplay.displayEmptySquare(buttons[i][j], i, j);
           squares.add(buttons[i][j], i, j);
           pieces[i][j] = null;
        }
      }
    }
    //launches the game for Xiangqi
    else if (commArgs.get(0).equals("xiangqi")) {
      boardDisplay = new JavaFXXiangqiDisplay();
      game = new Xiangqi();
      
        numRows = 10;
        numColumns = 9;
        pieces = new ChessPiece[numRows][numColumns];
        buttons = new Button[numRows][numColumns];

        //loop to create the buttons
      for (int i = 0; i < numRows; i++) {
        for (int j = 0; j < numColumns; j++) {
          buttons[i][j] = new Button();
           buttons[i][j].setOnAction(new ChessAction());
           boardDisplay.displayEmptySquare(buttons[i][j], i, j);
           squares.add(buttons[i][j], i, j);
           pieces[i][j] = null;
        }
      }
    }
    //Sets the proper size and style for the board, then sets it visible
    borderPane.setCenter(squares);
    borderPane.setPrefSize(boardDisplay.getSquareSize() * numRows, boardDisplay.getSquareSize() * numColumns);
    squares.setPrefSize(boardDisplay.getSquareSize() * numRows, boardDisplay.getSquareSize() * numColumns);
    squares.isResizable();
    squares.autosize();
    Scene scene = new Scene(borderPane);
    primaryStage.setScene(scene);            // Add the "scene" to the main window
    primaryStage.show();
    
    //puts the pieces on this board
    game.startGame(this);
  }
  
  /**
     * Retrieves the ChessGame rules
     *
     * @return the chessgame for this board
     */
  public ChessGame getGameRules() {
        return game;
    }
  
  /**
     * Returns true if there is a piece at a specific location of the board.
     *
     * @param row the row to examine
     * @param col the column to examine
     * @return true if there is a piece a this row and column and false
     * if the square is empty
     */
    public boolean hasPiece(int row, int col) {
        return (pieces[row][col] != null);
    }
    
    /**
     * Returns the chess piece at a specific location on the board.
     *
     * @param row the row for the piece
     * @param col the column for the piece
     * @return the piece at the row and column or null if there is no piece there.
     */
    public ChessPiece getPiece(int row, int col) {
        return pieces[row][col];
    }
    
    /**
     * Returns true if a particular square is threatened by an opposing piece.
     *
     * @param row    the row of the square
     * @param column the column of the square
     * @param piece  a piece of the game
     * @return true if the square can be attacked by a piece of an opposing side as the parameter piece
     */
    public boolean squareThreatened(int row, int column, ChessPiece piece) {
        for (int i = 0; i < numRows(); i++) {
            for (int j = 0; j < numColumns(); j++) {
                if (hasPiece(i, j) && getPiece(i, j).getSide() != piece.getSide() &&
                        getPiece(i, j).isLegalMove(row, column))
                    return true;
            }
        }
        return false;
    }
    
    /**
     * Returns the number of rows in the board.
     *
     * @return the number of rows
     */
    public final int numRows() {
        return buttons.length;
    }
    
    /**
     * Returns the number of columns in the board.
     *
     * @return the number of columns
     */
    public final int numColumns() {
        return buttons[0].length;
    }


    /**
     * Adds a piece to the board at the desired location.  Any piece currently
     * at that location is lost.
     *
     * @param piece the piece to add
     * @param row   the row for the piece
     * @param col   the column for the piece
     */
    public void addPiece(final ChessPiece piece, final int row, final int col) {
        // set the piece on the board, tell the piece where it is, and then use the display rules to display the square
        pieces[row][col] = piece;
        piece.setLocation(row, col);
        boardDisplay.displayFilledSquare(buttons[row][col], row, col, piece);
        /*Runnable addPiece = new Runnable() {
            public void run() {
                boardDisplay.displayFilledSquare(buttons[row][col], row, col, piece);
            }
        };
        
        addPiece.run();*/
    }
    
    /**
     * Removes a piece from the board
     *
     * @param row the row of the piece
     * @param col the column of the piece
     * @return the piece removed of null if there was no piece at that square
     */
    public ChessPiece removePiece(final int row, final int col) {
        // remove the piece from the board, use the display rules to show an empty square,
        // and run the display code on the event dispatch thread
        ChessPiece save = pieces[row][col];
        pieces[row][col] = null;
        boardDisplay.displayEmptySquare(buttons[row][col], row, col);
        return save;
    }
    

  /**
     * The code the responds when the user clicks on the game board
     * 
     * @author Harold Conamacher
     * @author Jami Biddle
     */
    public boolean firstPick = true;  // if true, we a selecting a piece
    private int pieceRow;              // remember row of selected piece
    private int pieceCol;              // remember column of selected piece
    
    private class ChessAction implements EventHandler<ActionEvent> {

        /**
         * What we do when the user chooses the piece to move.
         *
         * @param row the row of the chosen piece
         * @param col the column of the chosen piece
         */
        private void processFirstSelection(int row, int col) {
            if ((pieces[row][col] != null) &&
                    (getGameRules() == null || getGameRules().legalPieceToPlay(pieces[row][col], row, col))) {
                /*
                 * if this is the first pick and a square with a piece was picked,
                 * remember the piece's location and highlight the square.
                 */
                pieceRow = row;
                pieceCol = col;
                boardDisplay.highlightSquare(true, buttons[row][col], row, col, pieces[row][col]);
                firstPick = false;
            }
        }

        /**
         * What we do when the user chooses the square to move the piece to.
         *
         * @param row the row the piece will move to
         * @param col the column the piece will move to
         */
        private void processSecondSelection(int row, int col) {
            if (row == pieceRow && col == pieceCol)
                return;
            boolean moveMade = pieces[pieceRow][pieceCol].makeMove(pieces[pieceRow][pieceCol], row, col);

            // if the move was made or if it was not made and the user select a new piece, then reset to choose a new move
            if (moveMade || getGameRules().canChangeSelection(pieces[pieceRow][pieceCol], pieceRow, pieceCol)) {
                boardDisplay.highlightSquare(false, buttons[pieceRow][pieceCol], pieceRow, pieceCol, pieces[pieceRow][pieceCol]);
                firstPick = true;
            }
        }

        /**
         * Handle a button click.  The method alternates between selecting a piece
         * and selecting any square.  After both are selected, the piece's
         * legalMove is called, and if the move is legal, the piece is moved.
         *
         * @param e the event that triggered the method
         */
        public void handle(ActionEvent e) {
            Button b = (Button) e.getSource();
            int col = -1;
            int row = -1;

            // first find which button (board square) was clicked.
            for (int i = 0; i < buttons.length; i++) {
                for (int j = 0; j < buttons[i].length; j++) {
                    if (buttons[i][j] == b) {
                        row = i;
                        col = j;
                    }
                }
            }

            if (firstPick) {
                processFirstSelection(row, col);
            } else {
                processSecondSelection(row, col);
            }
        }
    }

  /**
   * The method to launch the program.
   * @param args  The command line arguments.  The arguments are passed on to the JavaFX application.
   */
  public static void main(String[] args) {
          Application.launch(args);
  }

}