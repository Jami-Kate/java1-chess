/*
 * Class for a Cannon Piece
 * Extends ChessPiece and Implements StraightMovingPiece
 * 
 * @author Jami Biddle
 */
public class CannonPiece extends ChessPiece implements StraightMovingPiece{
  /**
   * 
   * Constructor for the Cannon piece, which calls the ChessPiece constructor
   * 
   * @param a ChessBoard the piece will be placed on
   * @param side the side this piece belongs to
   * @param label the one letter label used to, without graphics, identify the piece
   * @param icon Object used to create an image for the piece
   * @param row int to indicate the row on which the piece sits
   * @param column int to indicate the column a piece sits on
   */
  public CannonPiece(ChessBoard a, ChessGame.Side side, String label, Object icon, int row, int column) {
    super(a, side, "C", null, row, column);
  }
  
  /**
   * 
   * Determines whether this piece's move is a legal move, as long as it isn't a capture move.
   * 
   * @param row    the row the piece should move to
   * @param column the column the piece is trying to move to
   */
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
                    if (this.getChessBoard().hasPiece(row, (this.getColumn() + i)))
                        piecethere = true;
                }
            }
            //Checks for left moves
            if (this.getColumn() - column > 0) {
                for (int i = 1; this.getColumn() - i > column; i = i + 1) {
                    if (this.getChessBoard().hasPiece(row, (this.getColumn() - i)))
                        piecethere = true;
                }
            }
        }
        //Checks for vertical move
        if ((this.getColumn() - column) == 0) {
            if (this.getRow() - row < 0) {
                //Goes through the spaces downwards
                for (int i = 1; (this.getRow() + i) < row; i++) {
                    if (this.getChessBoard().hasPiece((this.getRow() + i), column)) {
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
        //Not pieces there, means its a legal mov
        if (piecethere == false) {
            return true;
        }
        //Illegal move if there is a piece there
        else {
            piecethere = false;
            return false;
        }
    }
  
  /**
   * 
   * Determines whether this piece's move is a legal move, as long as it is a capture move.
   * 
   * @param row    the row the piece should move to
   * @param column the column the piece is trying to move to
   */
  public boolean isLegalCaptureMove(int row, int column) {
        if (!this.getChessBoard().hasPiece(row, column)) {
            return false;
        }
        //Makes sure it's a striaght move
        if ((this.getRow() - row) != 0 && (this.getColumn() - column) != 0) {
            return false;
        }

        int piecethere = 0;
        //Checks for Horitontal moves
        if ((this.getRow() - row) == 0) {
            //Checks for right moves
            if (this.getColumn() - column < 0) {
                for (int i = 0; this.getColumn() + i < column; i = i + 1) {
                    if (this.getChessBoard().hasPiece(row, i) == true)
                        piecethere++;
                }
            }
            //Checks for left moves
            if (this.getColumn() - column > 0) {
                for (int i = 0; this.getColumn() - i > column; i = i + 1) {
                    if (this.getChessBoard().hasPiece(row, i) == true)
                        piecethere++;
                }
            }
        }
        //Checks for vertical move
        if ((this.getColumn() - column) == 0) {
            if (this.getRow() - row < 0) {
                //Goes through the spaces downwards
                for (int i = 0; (this.getRow() + i) < row; i++) {
                    if (this.getChessBoard().hasPiece((this.getRow() + i), column) == true) {
                        piecethere++;
                    }
                }
            }
            if (this.getRow() - row > 0) {
                //Goes Through the spaces upwards
                for (int i = 0; (this.getRow() - i) > row; i++) {
                    if (this.getChessBoard().hasPiece(i, column) == true)
                        piecethere++;
                }
            }
        }
        //Only one piece there, means its a legal move
        if (piecethere == 1) {
            return true;
        }
        //Illegal move if there is a piece there
        else {
            piecethere = 0;
            return false;
        }
    }
}