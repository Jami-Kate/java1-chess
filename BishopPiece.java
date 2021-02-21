
/* Class for Bishop Pieces
 * Jami Biddle
 * Programming Project 3
 */

public class BishopPiece extends ChessPiece implements DiagonalMovingPiece {

    //Constructor for bishops, which calls the ChessPiece constructor.
    public BishopPiece(ChessBoard a, ChessGame.Side side, String label, Object icon, int row, int column) {
        super(a, side, "B", null, row, column);
    }

    //Checks for move legality if the intended square is empty
    public boolean isLegalNonCaptureMove(int row, int column) {
        //Makes sure this is not a capture move
        if (this.getChessBoard().hasPiece(row, column))
            return false;
        if ((!((this.getRow() - row) - (this.getColumn() - column) == 0) && !((this.getRow() - row) - (this.getColumn() - column) == -(2 * (this.getRow() - row))) && !((this.getRow() - row) - (this.getColumn() - column) == (2 * (this.getRow() - row))))) {
            return false;
        }
        //Flag to store whether there is a piece in the way
        boolean piecethere = false;

        //Checks to see it it is either a (down, right) or (up, left) move
        if ((this.getRow() - row) - (this.getColumn() - column) == 0) {

            //Checks for pieces for the upwards, left moves
            if ((this.getRow() - row) > 0) {
                for (int i = 1; this.getColumn() - i > column; i = i + 1) {
                    if (this.getChessBoard().hasPiece(this.getRow() - i, this.getColumn() - i) == true)
                        piecethere = true;
                }
            }
            //Checks for pieces for the downwards, right moves
            if ((this.getRow() - row) < 0) {
                for (int i = 1; this.getColumn() + i < column; i = i + 1) {
                    if (this.getChessBoard().hasPiece(this.getRow() + i, this.getColumn() + i) == true)
                        piecethere = true;
                }
            }
        }

        //Checks for pieces for the downwards, left move
        if ((this.getRow() - row) - (this.getColumn() - column) == Math.abs((2 * (this.getRow() - row)))) {
            for (int i = 1; this.getColumn() - i > column; i = i + 1) {
                if (this.getChessBoard().hasPiece(this.getRow() + i, this.getColumn() - i) == true)
                    piecethere = true;
            }
            for (int i = 1; this.getColumn() + i < column; i = i + 1) {
                if (this.getChessBoard().hasPiece(this.getRow() - i, this.getColumn() + i) == true)
                    piecethere = true;
            }
        }

        //If there's not piece there, the move is legal
        if (piecethere == false) {
            return true;
        }
        //Else the move is illegal
        else {
            piecethere = false;
            return false;
        }
    }

    //Checks for pieces in the way and double checks that the piece in the intended square is the enemy's
    public boolean isLegalCaptureMove(int row, int column) {
        //Makes sure this is a capture move
        if (!(this.getChessBoard().hasPiece(row, column)) || this.getChessBoard().getPiece(row, column).getSide() == this.getSide())
            return false;
        if ((!((this.getRow() - row) - (this.getColumn() - column) == 0) && !((this.getRow() - row) - (this.getColumn() - column) == -(2 * (this.getRow() - row))) && !((this.getRow() - row) - (this.getColumn() - column) == (2 * (this.getRow() - row))))) {
            return false;
        }

        boolean piecethere = false;

        //Checks to see it it is either a (down, right) or (up, left) move
        if ((this.getRow() - row) - (this.getColumn() - column) == 0) {
            //Checks for the upwards, left moves
            if ((this.getRow() - row) > 0) {
                for (int i = 1; this.getColumn() - i > column; i = i + 1) {
                    if (this.getChessBoard().hasPiece(this.getRow() - i, this.getColumn() - i) == true)
                        piecethere = true;
                }
            }

            //Checks for the downwards, right moves
            if ((this.getRow() - row) < 0) {
                for (int i = 1; this.getColumn() + i < column; i = i + 1) {
                    if (this.getChessBoard().hasPiece(this.getRow() + i, (this.getColumn() + i)) == true)
                        piecethere = true;
                }
            }
        }

        //Checks for the downwards, left move
        if ((this.getRow() - row) - (this.getColumn() - column) == Math.abs((2 * (this.getRow() - row)))) {
            for (int i = 1; this.getColumn() - i > column; i = i + 1) {
                if (this.getChessBoard().hasPiece(this.getRow() + i, this.getColumn() - i) == true)
                    piecethere = true;
            }

            //Checks for the upwards, right moves
            for (int i = 1; this.getColumn() + i < column; i = i + 1) {
                if (this.getChessBoard().hasPiece(this.getRow() - i, this.getColumn() + i) == true)
                    piecethere = true;
            }
        }

        //Final condition: if flag is false, the move is clear
        if (piecethere == false) {
            return true;
        }
        //Else, resets flag and returns false.
        else {
            piecethere = false;
            return false;
        }
    }
}
