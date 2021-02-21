/* Class for King Pieces
 * Jami Biddle
 * Programming Project 3
 */

public class KingPiece extends ChessPiece implements StraightMovingPiece, DiagonalMovingPiece {

    //Constructor for king piece, calls ChessPiece's constructor
    public KingPiece(ChessBoard a, ChessGame.Side side, String label, Object icon, int row, int column) {
        super(a, side, "K", null, row, column);
    }

    //Figures out if the castle move is legal
    public boolean Castle(int row, int column) {
        //Enters the tests for the north and south castle moves
        if (this.getSide() == ChessGame.Side.NORTH || this.getSide() == ChessGame.Side.SOUTH) {
            //If the king hasn't moved
            if (this.getFirstMove() == false) {
                //and if the piece there is a rook and it hasn't moved
                if (this.getChessBoard().getPiece(row, column).getFirstMove() == false && this.getChessBoard().getPiece(row, column).getLabel().equals("R")) {
                    //Stores the rook for easy calling
                    ChessPiece toRook = this.getChessBoard().getPiece(row, column);

                    //Checks to the left
                    if (toRook.getColumn() < this.getColumn()) {
                        //Loop that checks through spots between the king and rook
                        for (int i = 1; this.getColumn() + i > column; i = i + 1) {
                            //Checks if there are pieces there and if not, whether the square is threatened. Either being true won't enter conditional
                            if (!(this.getChessBoard().hasPiece(row, i)) || !(this.getChessBoard().squareThreatened(row, i, this))) {
                                //Moves the two pieces to their new locations
                                this.getChessBoard().removePiece(toRook.getRow(), toRook.getColumn());
                                this.getChessBoard().removePiece(this.getRow(), this.getColumn());
                                this.getChessBoard().addPiece(toRook, this.getRow(), (this.getColumn() - 1));
                                this.getChessBoard().addPiece(this, this.getRow(), (this.getColumn() - 2));
                                this.setLocation(this.getRow(), (this.getColumn() - 2));
                                toRook.setLocation(this.getRow(), (this.getColumn() - 1));
                                return true;
                            }
                        }
                    }
                    //Checks right of the king
                    if (toRook.getColumn() > this.getColumn()) {
                        //Loop that checks through spots between the king and rook
                        for (int i = 1; this.getColumn() - i < column; i = i + 1) {
                            //Checks if there are pieces there and if not, whether the square is threatened. Either being true won't enter conditional
                            if (!(this.getChessBoard().hasPiece(row, i)) || !(this.getChessBoard().squareThreatened(row, i, this))) {
                                this.getChessBoard().removePiece(toRook.getRow(), toRook.getColumn());
                                this.getChessBoard().removePiece(this.getRow(), this.getColumn());
                                this.getChessBoard().addPiece(toRook, this.getRow(), (this.getColumn() + 1));
                                this.getChessBoard().addPiece(this, this.getRow(), (this.getColumn() + 2));
                                this.setLocation(this.getRow(), (this.getColumn() + 2));
                                toRook.setLocation(this.getRow(), (this.getColumn() + 1));
                                return true;
                            }
                        }
                    }
                }
            }
        }
        //Checks through castling for the east and west pieces
        if (this.getSide() == ChessGame.Side.EAST || this.getSide() == ChessGame.Side.WEST) {
            //Checks that the king hasn't moved
            if (this.getFirstMove() == false) {
                //Makes sure the other piece is a rook that hasn't moved
                if (this.getChessBoard().getPiece(row, column).getFirstMove() == false && this.getChessBoard().getPiece(row, column).getLabel().equals("R")) {
                    //Stores the rook for easy calling
                    ChessPiece toRook = this.getChessBoard().getPiece(row, column);

                    //Checks castling to the bottom
                    if (toRook.getRow() < this.getRow()) {
                        //Loops thru spots in between
                        for (int i = 1; this.getRow() + i > row; i = i + 1) {
                            //Checks to see if there's a piece there or if the square is threatened
                            if (!(this.getChessBoard().hasPiece(i, column)) || !(this.getChessBoard().squareThreatened(i, column, this))) {
                                //Moves the pieces to where they need to go
                                this.getChessBoard().removePiece(toRook.getRow(), toRook.getColumn());
                                this.getChessBoard().removePiece(this.getRow(), this.getColumn());
                                this.getChessBoard().addPiece(toRook, (this.getRow() - 1), this.getColumn());
                                this.getChessBoard().addPiece(this, (this.getRow() - 2), this.getColumn());
                                this.setLocation((this.getRow() - 2), this.getColumn());
                                toRook.setLocation((this.getRow() - 2), this.getColumn());
                                return true;
                            }
                        }
                    }

                    //Checks upwards
                    if (toRook.getRow() > this.getRow()) {
                        //Checks through spot in between
                        for (int i = 1; this.getRow() - i < row; i = i + 1) {
                            //Checks to see if there's a piece there or if the square is threatened
                            if (!(this.getChessBoard().hasPiece(i, column)) || !(this.getChessBoard().squareThreatened(i, column, this))) {
                                //Castles
                                this.getChessBoard().removePiece(toRook.getRow(), toRook.getColumn());
                                this.getChessBoard().removePiece(this.getRow(), this.getColumn());
                                this.getChessBoard().addPiece(toRook, (this.getRow() + 1), this.getColumn());
                                this.getChessBoard().addPiece(this, (this.getRow() + 2), this.getColumn());
                                this.setLocation((this.getRow() + 2), this.getColumn());
                                toRook.setLocation((this.getRow() + 2), this.getColumn());
                                return true;
                            }
                        }
                    }
                }
            }
        }
        //if it can't castle, it returns false.
        return false;
    }

    public boolean isLegalNonCaptureMove(int row, int column) {
        if ((this.getChessBoard().hasPiece(row, column)) && this.getFirstMove()) {
            return false;
        }
        if (this.getChessBoard().hasPiece(row, column)) {
            //Checks for castling
            this.Castle(row, column);
            //return false, b/c if it's true then Castle() already moved the pieces
            return false;
        }

        //Start of the normal moves for kings

        boolean moveokay = false;
        //Checks for an upwards move
        if (((this.getRow() - row) == 1) && (this.getColumn() - column) == 0) {
            moveokay = true;
        }
        //Checks for a downwards move
        if ((this.getRow() - row) == -1 && (this.getColumn() - column) == 0) {
            moveokay = true;
        }

        //Checks for a left move
        if (((this.getColumn() - column) == 1) && (this.getRow() - row) == 0) {
            moveokay = true;
        }
        //Checks for a right move
        if ((this.getColumn() - column) == -1 && ((this.getRow() - row) == 0)) {
            moveokay = true;
        }
        //Checks for an up, left move
        if ((this.getColumn() - column) == -1 && ((this.getRow() - row) == -1)) {
            moveokay = true;
        }
        //Checks for a down, right move
        if ((this.getColumn() - column) == 1 && ((this.getRow() - row) == 1)) {
            moveokay = true;
        }
        //Checks for a left, down move
        if ((this.getColumn() - column) == -1 && ((this.getRow() - row) == 1)) {
            moveokay = true;
        }

        //Checks for an up, left move
        if ((this.getColumn() - column) == 1 && ((this.getRow() - row) == -1)) {
            moveokay = true;
        }
        //If the move is okay, then it returns true
        if (moveokay) {
            moveokay = false;
            return true;
        }
        //else, false.
        return false;
    }


    //Is legal capture move: runs through to make sure it's a capture move & makes sure that move is actually possible
    public boolean isLegalCaptureMove(int row, int column) {
        //Makes sure the space has a piece
        if (!(this.getChessBoard().hasPiece(row, column)))
            return false;
        //Makes sure that piece is on a different side
        if (this.getChessBoard().getPiece(row, column).getSide() == this.getSide())
            return false;

        //runs through the 8 kinds of move, just as in legal non capture move
        boolean moveokay = false;

        if (((this.getRow() - row) == 1) && (this.getColumn() - column) == 0) {
            moveokay = true;
        }

        if ((this.getRow() - row) == -1 && (this.getColumn() - column) == 0) {
            moveokay = true;
        }

        if (((this.getColumn() - column) == 1) && (this.getRow() - row) == 0) {
            moveokay = true;
        }

        if ((this.getColumn() - column) == -1 && ((this.getRow() - row) == 0)) {
            moveokay = true;
        }

        if ((this.getColumn() - column) == -1 && ((this.getRow() - row) == -1)) {
            moveokay = true;
        }

        if ((this.getColumn() - column) == 1 && ((this.getRow() - row) == 1)) {
            moveokay = true;
        }

        if ((this.getColumn() - column) == -1 && ((this.getRow() - row) == 1)) {
            moveokay = true;
        }

        if ((this.getColumn() - column) == 1 && ((this.getRow() - row) == -1)) {
            moveokay = true;
        }
        //Returns true for an okay move
        if (moveokay = true) {
            moveokay = false;
            return true;
        }
        //False for a not okay move
        else return false;
    }
}
