import javafx.scene.control.Button;
import javafx.scene.layout.Region;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.layout.CornerRadii;
import javafx.geometry.Insets;


/**
 * Rules for how we want a board to display for a game of European chess
 *
 * @author Jami Biddle
 */
public class JavaFXEuropeanChessDisplay implements JavaFXChessBoardDisplay {


    private int rowsNeeded;
    private int columnsNeeded;

   Background fill1 = new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY));
   Background fill2 = new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY));
   
   
    /* The color of the SOUTH player */
    public static BackgroundFill southPlayerColor = new BackgroundFill(Color.YELLOW, new CornerRadii(5.0, true), new Insets(5.0));

    /* The color of the NORTH player */
    public static BackgroundFill northPlayerColor = new BackgroundFill(Color.GREEN, new CornerRadii(5.0, true), new Insets(5.0));

    /* The color of the EAST player */
    public static BackgroundFill eastPlayerColor = new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY);

    /* The color of the WEST player */
    public static BackgroundFill westPlayerColor = new BackgroundFill(Color.GRAY, new CornerRadii(5.0, true), new Insets(5.0));

    /**
     * The color used to highlight a square
     */
    public static BackgroundFill highlightColor = new BackgroundFill(Color.BLUE, new CornerRadii(5.0, true), new Insets(1.0));
   
    /**
     * Display a square that has no piece on it.  Will produce a red/black checkerboard.
     *
     * @param button the button that is used for the chessboard square
     * @param row    the row of this square on the board
     * @param column the column of this square on the board
     */
    public void displayEmptySquare(Button button, int row, int column) {
        button.setBackground(((row + column) % 2 == 0 ? fill1 : fill2));
        button.setText(null);
        button.setGraphic(null);
        button.autosize();
    }
    
    /**
     * Display a square that has a piece on it.
     *
     * @param button the button that is used for the chessboard square
     * @param row    the row of this square on the board
     * @param column the column of this square on the board
     * @param piece  the piece that is on this square
     */
    public void displayFilledSquare(Button button, int row, int column, ChessPiece piece) {
        BackgroundFill pieceColor;
        BackgroundFill savedBack = button.getBackground().getFills().get(0);
        switch (piece.getSide()) {
            case NORTH:
                pieceColor = northPlayerColor;
                break;
            case SOUTH:
                pieceColor = southPlayerColor;
                break;
            case EAST:
                pieceColor = eastPlayerColor;
                break;
            default:
                pieceColor = westPlayerColor;
        }
        Background finalBG = new Background(savedBack, pieceColor);
        button.setBackground(finalBG);
        button.setText(piece.getLabel());
        button.setPrefSize(java.awt.Toolkit.getDefaultToolkit().getScreenSize().width / 40, java.awt.Toolkit.getDefaultToolkit().getScreenSize().width / 40);
        //button.setGraphic(piece.getIcon());
    }
    
    /**
     * Highlight a square of the board.
     *
     * @param highlight do we want the highlight on (true) or off (false)?
     * @param button    the button that is used for the chessboard square
     * @param row       the row of this square on the board
     * @param column    the column of this square on the board
     * @param piece     the piece (if any) that is on this square
     */
    public void highlightSquare(boolean highlight, Button button, int row, int column, ChessPiece piece) {
        if (highlight) {
            BackgroundFill savedBack = button.getBackground().getFills().get(0);
            Background finalBG = new Background(savedBack, highlightColor);
            button.setBackground(finalBG);
            button.setText(piece.getLabel());
        } else if (piece == null)
            displayEmptySquare(button, row, column);
        else
            displayFilledSquare(button, row, column, piece);
    }

    /**
   * Returns the number of rows on a chess board
   *
   * @return int The number of rows on the chess board
   */
    public int getRowsNeeded(){
        return rowsNeeded;
    }
    
    /**
   * Returns the number of columns on a chess board
   *
   * @return int The number of columns on the chess board
   */
    public int getColumnsNeeded(){
        return columnsNeeded;
    }
}