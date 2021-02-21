/*
 * Class for a Soldier Piece
 * Extends ChessPiece and Implements StraightMovingPiece
 * 
 * @author Jami Biddle
 */
public class SoldierPiece extends ChessPiece implements StraightMovingPiece{

  
  /**
   * 
   * Constructor for the Soldier piece, which calls the ChessPiece constructor
   * 
   * @param a ChessBoard the piece will be placed on
   * @param side the side this piece belongs to
   * @param label the one letter label used to, without graphics, identify the piece
   * @param icon Object used to create an image for the piece
   * @param row int to indicate the row on which the piece sits
   * @param column int to indicate the column a piece sits on
   */
  public SoldierPiece(ChessBoard a, ChessGame.Side side, String label, Object icon, int row, int column) {
    super(a, side, "S", null, row, column);
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
    
    if (this.getSide() == ChessGame.Side.NORTH) {
      //Else, it checks if it is only one down (from the north)
      if ((this.getColumn() - column) == 0 && (this.getRow() - row) == -1) {
        return true;
      }
      
      if (this.getRow() > 4) {
        if ((this.getRow() - row) == 0 && Math.abs(this.getColumn() - column) == 1) {
          return true;
        }
      }
    }
    
    if (this.getSide() == ChessGame.Side.SOUTH) {
      //Else, it checks if it is only one up
      if ((this.getColumn() - column) == 0 && (this.getRow() - row) == 1) {
        return true;
      }
      
      if (this.getRow() < 5){
        if ((this.getRow() - row) == 0 && Math.abs(this.getColumn() - column) == 1) {
          return true;
        }
      }
    }
    
    if (this.getSide() == ChessGame.Side.WEST) {
      //Else, it checks if it is only one right
      if ((this.getRow() - row) == 0 && (this.getColumn() - column) == 1) {
        return true;
      }
      
      if (this.getColumn() < 5){
        if ((this.getColumn() - column) == 0 && Math.abs(this.getRow() - row) == 1) {
          return true;
        }
      }
    }
    
    if (this.getSide() == ChessGame.Side.EAST) {
      //Else, it checks if it is only one right
      if ((this.getRow() - row) == 0 && (this.getColumn() - column) == -1) {
        return true;
      }
      
      if (this.getColumn() > 4){
        if ((this.getColumn() - column) == 0 && Math.abs(this.getRow() - row) == 1) {
          return true;
        }
      }
    }
    return false;
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
    
    if (this.getSide() == ChessGame.Side.NORTH) {
      //Else, it checks if it is only one down (from the north)
      if ((this.getColumn() - column) == 0 && (this.getRow() - row) == -1) {
        return true;
      }
      
      if (this.getRow() > 4) {
        if ((this.getRow() - row) == 0 && Math.abs(this.getColumn() - column) == 1) {
          return true;
        }
      }
    }
    
    if (this.getSide() == ChessGame.Side.SOUTH) {
      //Else, it checks if it is only one up
      if ((this.getColumn() - column) == 0 && (this.getRow() - row) == 1) {
        return true;
      }
      
      if (this.getRow() < 5){
        if ((this.getRow() - row) == 0 && Math.abs(this.getColumn() - column) == 1) {
          return true;
        }
      }
    }
    
    if (this.getSide() == ChessGame.Side.WEST) {
      //Else, it checks if it is only one right
      if ((this.getRow() - row) == 0 && (this.getColumn() - column) == 1) {
        return true;
      }
      
      if (this.getColumn() < 5){
        if ((this.getColumn() - column) == 0 && Math.abs(this.getRow() - row) == 1) {
          return true;
        }
      }
    }
    
    if (this.getSide() == ChessGame.Side.EAST) {
      //Else, it checks if it is only one left
      if ((this.getRow() - row) == 0 && (this.getColumn() - column) == -1) {
        return true;
      }
      
      if (this.getColumn() > 4){
        if ((this.getColumn() - column) == 0 && Math.abs(this.getRow() - row) == 1) {
          return true;
        }
      }
    }
    return false;
  } 
}