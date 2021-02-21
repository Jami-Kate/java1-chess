
/* Class for Knight Pieces
 * Jami Biddle
 * Programming Project 3
 */

public class KnightPiece extends ChessPiece {
    //Contructor for knights, calls ChessPiece constructor
    public KnightPiece(ChessBoard a, ChessGame.Side side, String label, Object icon, int row, int column) {
        super(a, side, "N", null, row, column);
    }

    //Checks for legal capture moves
    public boolean isLegalCaptureMove(int row, int column) {
        //If there's a piece and it's not on the same side
        if (this.getChessBoard().hasPiece(row, column) && this.getChessBoard().getPiece(row, column).getSide() != this.getSide()) {
            //This is my logic for knight moves. it works.
            if ((Math.abs(this.getRow() - row) + Math.abs(this.getColumn() - column)) == 3 && Math.abs(this.getColumn() - column) > 0)
                return true;
        }
        return false;
    }

    //Checks for non capture moves, i.e. no piece present
    public boolean isLegalNonCaptureMove(int row, int column) {
        //makes sure there's no piece
        if (this.getChessBoard().hasPiece(row, column) == false) {
            //Makes sure the move is a legal one
            if ((Math.abs(this.getRow() - row) + Math.abs(this.getColumn() - column)) == 3 && Math.abs(this.getColumn() - column) > 0) {
                return true;
            }
        }
        //if not, it returns false
        return false;
    }

}