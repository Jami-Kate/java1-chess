/* Class for Queen Pieces
 * Jami Biddle
 * Programming Project 3
 */

public class QueenPiece extends ChessPiece implements DiagonalMovingPiece, StraightMovingPiece {

    //initializes a queen piece by calling the ChessPiece Constructor
    public QueenPiece(ChessBoard a, ChessGame.Side side, String label, Object icon, int row, int column) {
        super(a, side, "Q", null, row, column);
    }

    //Tests if it's a legal non capture move
    public boolean isLegalNonCaptureMove(int row, int column) {
        //Makes sure this is not a capture move
        if (this.getChessBoard().hasPiece(row, column)) {
            return false;
        }
        //Checks for diagonal. if not, checks for straight move. if neither, returns false.
        if ((!((this.getRow() - row) - (this.getColumn() - column) == 0) && !((this.getRow() - row) - (this.getColumn() - column) == -(2 * (this.getRow() - row))) && !((this.getRow() - row) - (this.getColumn() - column) == (2 * (this.getRow() - row))))) {
            if ((this.getRow() - row) != 0 && (this.getColumn() - column) != 0) {
                return false;
            }
        }

        boolean piecethere = false;
        //Checks to see it it is either a down, right or up, left move
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
                    if (this.getChessBoard().hasPiece(this.getRow() + i, this.getColumn() + i) == true)
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
            //Goes through spaces upwards
            if ((this.getRow() - row) > 0) {
                for (int i = 1; (this.getRow() - i) > row; i++) {
                    if (this.getChessBoard().hasPiece((this.getRow() - i), column) == true)
                        piecethere = true;
                }
            }
        }

        //If there aren't any pieces in between, it returns true
        if (piecethere == false) {
            return true;
        }

        //If not, there must be a piece there.
        else {
            piecethere = false;
            return false;
        }

    }


    public boolean isLegalCaptureMove(int row, int column) {
        if (!(this.getChessBoard().hasPiece(row, column)) || this.getChessBoard().getPiece(row, column).getSide() == this.getSide())
            return false;
        //Checks for diagonal. if not, checks for straight move. if neither, returns false.
        if ((!((this.getRow() - row) - (this.getColumn() - column) == 0) && !((this.getRow() - row) - (this.getColumn() - column) == -(2 * (this.getRow() - row))) && !((this.getRow() - row) - (this.getColumn() - column) == (2 * (this.getRow() - row))))) {
            if ((this.getRow() - row) != 0 && (this.getColumn() - column) != 0) {
                return false;
            }
        }

        boolean piecethere = false;
        //Checks to see it it is either a down, right or up, left move
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
                    if (this.getChessBoard().hasPiece(this.getRow() + i, this.getColumn() + i) == true)
                        piecethere = true;
                }
            }
        }

        //Checks for the downwards, left move
        if ((this.getRow() - row) - (this.getColumn() - column) == (2 * (this.getRow() - row))) {
            for (int i = 1; this.getColumn() - i > column; i = i + 1) {
                if (this.getChessBoard().hasPiece(this.getRow() + i, this.getColumn() - i) == true)
                    piecethere = true;
            }
            for (int i = 1; this.getColumn() + i < column; i = i + 1) {
                if (this.getChessBoard().hasPiece(this.getRow() - i, this.getColumn() + i) == true)
                    piecethere = true;
            }
        }


        //Checks for the upwards, right moves
        if ((this.getRow() - row) - (this.getColumn() - column) == (2 * (this.getRow() - row))) {
            for (int i = 1; this.getColumn() + i < column; i = i + 1) {
                if (this.getChessBoard().hasPiece(this.getRow() - i, this.getColumn() + i) == true) {
                    piecethere = true;
                }
            }
        }

        if ((this.getRow() - row) == 0) {
            //Checks for right moves
            if (this.getColumn() - column < 0) {
                for (int i = 1; this.getColumn() + i > column; i = i + 1) {
                    if (this.getChessBoard().hasPiece(row, (this.getColumn() + i)) == true)
                        piecethere = true;
                }
            }
            //Checks for left moves
            if (this.getColumn() - column > 0) {
                for (int i = 1; this.getColumn() - i < column; i = i + 1) {
                    if (this.getChessBoard().hasPiece(row, (this.getColumn() - i)) == true)
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
                    if (this.getChessBoard().hasPiece((this.getRow() - i), column) == true)
                        piecethere = true;
                }
            }
        }

        if (piecethere == false) {
            return true;
        } else {
            piecethere = false;
            return false;
        }

    }
}