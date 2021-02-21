import javax.swing.JButton;

/* Abstract Class ChessPiece
 * Allows for different types of chess piece to share mothods for all pieces, but doen't allow a generic
 * "ChessPiece" to be created
 */

//Jami Biddle
//Programming Project 3 & 5
//ChessPiece abstract class

public abstract class ChessPiece {

    //Initializes the private fields that every piece needs
    private ChessBoard currentGame;
    private ChessGame.Side side;
    private String label;
    private Object icon;
    private int row;
    private int column;
    //Stores whether the first move has passed
    private boolean firstMove;

    //initializes the chess piece, with ability to input the board it's on, the side, the label, the icon, the row, & the column.
    public ChessPiece(ChessBoard a, ChessGame.Side side, String label, Object icon, int row, int column) {
        this.currentGame = a;
        this.side = side;
        this.label = label;
        this.icon = icon;
        this.row = row;
        this.column = column;
    }

    //Method that returns the side of the chess piece
    public ChessGame.Side getSide() {
        return this.side;
    }

    //Getter for the Chess Board on which the game is being played
    public ChessBoard getChessBoard() {
        return this.currentGame;
    }

    //Returns the string label for the piece
    public String getLabel() {
        return label;
    }

    //Returns the icon for the piece, stored as an Object
    public Object getIcon() {
        return icon;
    }

    //Getter for the row the piece is on
    public int getRow() {
        return this.row;
    }

    //Getter for the column the piece is on
    public int getColumn() {
        return this.column;
    }

    //Sets the currect location, row & column, of the piece
    public void setLocation(int row, int column) {
        this.row = row;
        this.column = column;

    }

    //Gets the first move, which indicates if the piece has been moved
    public boolean getFirstMove() {
        return this.firstMove;
    }

    //Sets the first move. Because first move should only be counted once, it will only be able to set to true.
    public void setFirstMove(boolean b) {
        this.firstMove = b;
    }

    //Checks for legal capture and legal non capture move; if either is legal, it returns true.
    public boolean isLegalMove(int row, int column) {
        if (this.isLegalNonCaptureMove(row, column) || this.isLegalCaptureMove(row, column))
            return true;
        return false;
    }

    //Does the move making. Adds and removes necessary pieces, set new locations. behaves differently for cap/non cap moves
    public boolean makeMove(ChessPiece piece, int row, int column) {
        if (this.isLegalNonCaptureMove(row, column)) {
            this.getChessBoard().removePiece(this.getRow(), this.getColumn());
            this.setLocation(row, column);
            this.getChessBoard().addPiece(piece, row, column);
            this.moveDone();
            return true;
        }

        if (this.isLegalCaptureMove(row, column)) {
            this.getChessBoard().removePiece(row, column);
            this.getChessBoard().removePiece(piece.getRow(), piece.getColumn());
            this.setLocation(row, column);
            this.getChessBoard().addPiece(piece, row, column);
            this.moveDone();
            return true;
        }
        return false;
    }

    //For every piece, it makes the last turn the same side as whatever just played and sets first move to true if needed
    public void moveDone() {
        if (this.getFirstMove() == false)
            this.setFirstMove(true);
        EuropeanChess.setTurn(this.getSide());
    }

    //Double checks that this piece is able to be played, i.e. it is not on the team of whoever just played
    public boolean legalPieceToPlay(ChessPiece piece, int row, int column) {
        if (EuropeanChess.getTurn() != piece.getSide())
            return true;
        return false;
    }

    //Method stubs to be overridden.

    public abstract boolean isLegalNonCaptureMove(int row, int column);

    public abstract boolean isLegalCaptureMove(int row, int column);

}

