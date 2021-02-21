
/* Class for Rook Pieces
 * Jami Biddle
 * Programming Project 3
 */

public class RookPiece extends ChessPiece implements StraightMovingPiece {
    //Initializes a rook piece, which calls the chesspiece constructor
    public RookPiece(ChessBoard a, ChessGame.Side side, String label, Object icon, int row, int column) {
        super(a, side, "R", null, row, column);
    }

    //Marks whether a given move is legal or not, for non captures
    public boolean isLegalNonCaptureMove(int row, int column) {
        if (this.getChessBoard().hasPiece(row, column)) {
            return false;
        }
        //Makes sure it's a striaght move
        if ((this.getRow() - row) != 0 && (this.getColumn() - column) != 0) {
            return false;
        }

        boolean piecethere = false;
        //Checks for Horitontal moves
        if ((this.getRow() - row) == 0) {
            //Checks for right moves
            if (this.getColumn() - column < 0) {
                for (int i = 1; this.getColumn() + i < column; i = i + 1) {
                    if (this.getChessBoard().hasPiece(row, i) == true)
                        piecethere = true;
                }
            }
            //Checks for left moves
            if (this.getColumn() - column > 0) {
                for (int i = 1; this.getColumn() - i > column; i = i + 1) {
                    if (this.getChessBoard().hasPiece(row, i) == true)
                        piecethere = true;
                }
            }
        }
        //Checks for vertical move
        if ((this.getColumn() - column) == 0) {
            if (this.getRow() - row < 0) {
                //Goes through the spaces downwards
                for (int i = 1; (this.getRow() + i) < row; i++) {
                    if (this.getChessBoard().hasPiece((this.getRow() + i), column) == true) {
                        piecethere = true;
                    }
                }
            }
            if (this.getRow() - row > 0) {
                //Goes Through the spaces upwards
                for (int i = 1; (this.getRow() - i) > row; i++) {
                    if (this.getChessBoard().hasPiece(i, column) == true)
                        piecethere = true;
                }
            }
        }
        //Not pieces there, means its a legal move
        if (piecethere == false) {
            return true;
        }
        //Illegal move if there is a piece there
        else {
            piecethere = false;
            return false;
        }
    }

    //Checks the legality of capture moves
    public boolean isLegalCaptureMove(int row, int column) {
        //Makes sure there is an enemy piece to capture
        if (!(this.getChessBoard().hasPiece(row, column)) || this.getChessBoard().getPiece(row, column).getSide() == this.getSide())
            return false;
        //Makes sure the move is a straight move
        if ((this.getRow() - row) != 0 && (this.getColumn() - column) != 0)
            return false;

        boolean piecethere = false;
        //Checks for Horitontal moves
        if ((this.getRow() - row) == 0) {
            //Checks for right moves
            if (this.getColumn() - column < 0) {
                for (int i = 1; this.getColumn() + i > column; i = i + 1) {
                    if (this.getChessBoard().hasPiece(row, i) == true)
                        piecethere = true;
                }
            }
            //Checks for left moves
            if (this.getColumn() - column > 0) {
                for (int i = 1; this.getColumn() - i < column; i = i + 1) {
                    if (this.getChessBoard().hasPiece(row, i) == true)
                        piecethere = true;
                }
            }
        }
        //Checks for vertical move
        if ((this.getColumn() - column) == 0) {
            if (this.getRow() - row < 0) {
                //Goes through the spaces downwards
                for (int i = 1; (this.getRow() + i) < row; i++) {
                    if (this.getChessBoard().hasPiece((this.getRow() + i), column) == true) {
                        piecethere = true;
                    }
                }
            }
            if (this.getRow() - row > 0) {
                //Goes Through the spaces upwards
                for (int i = 1; (this.getRow() - i) > row; i++) {
                    if (this.getChessBoard().hasPiece(i, column) == true)
                        piecethere = true;
                }
            }
        }
        //If there's a piece there, it returns false
        if (piecethere == false) {
            return true;
        }
        //Otherwise it returns true
        else {
            piecethere = false;
            return false;
        }
    }
}