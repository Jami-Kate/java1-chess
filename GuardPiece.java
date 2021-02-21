/*
 * Class for a Guard Piece
 * Extends ChessPiece and Implements DiagonalMovingPiece
 * 
 * @author Jami Biddle
 */
public class GuardPiece extends ChessPiece implements DiagonalMovingPiece {
  
  //Set the top row bound for the piece
  private int rowTopLimit;
  //Set the left column bound for the piece
  private int colLeftLimit;
  //Set the bottom row bound for the piece
  private int rowBotLimit;
  //Set the right column bound for the piece
  private int colRightLimit;
  
  /**
   * 
   * Constructor for guard piece, which calls the ChessPiece constructor and assigns the
   * bounds for the piece according to Xiangqi chess rules
   * 
   * @param a ChessBoard the piece will be placed on
   * @param side the side this piece belongs to
   * @param label the one letter label used to, without graphics, identify the piece
   * @param icon Object used to create an image for the piece
   * @param row int to indicate the row on which the piece sits
   * @param column int to indicate the column a piece sits on
   */
  public GuardPiece(ChessBoard a, ChessGame.Side side, String label, Object icon, int row, int column) {
    super(a, side, "G", null, row, column);
    
    //Sets the bounds corredponding to the side, this one being North
    if (this.getSide() == ChessGame.Side.NORTH)  {
      rowTopLimit = 0;
      rowBotLimit = 2;
      colLeftLimit = 3;
      colRightLimit = 5;
    }
    
    //Sets the bounds corredponding to the side, this one being South
    if (this.getSide() == ChessGame.Side.SOUTH)  {
      rowTopLimit = 7;
      rowBotLimit = 9;
      colLeftLimit = 3;
      colRightLimit = 5;
    }
    
    //Sets the bounds corredponding to the side, this one being East
    if (this.getSide() == ChessGame.Side.EAST)  {
      rowTopLimit = 3;
      rowBotLimit = 5;
      colLeftLimit = 7;
      colRightLimit = 9;
    }
    
    //Sets the bounds corredponding to the side, this one being West
    if (this.getSide() == ChessGame.Side.WEST)  {
      rowTopLimit = 3;
      rowBotLimit = 5;
      colLeftLimit = 0;
      colRightLimit = 2;
    }
  }
  
  /**
   * 
   * Determines whether this piece's move is a legal move, as long as it isn't a capture move.
   * 
   * @param row    the row the piece should move to
   * @param column the column the piece is trying to move to
   */
  public boolean isLegalNonCaptureMove(int row, int column) {
    if (this.getChessBoard().hasPiece(row, column))
      return false;
    
    if (rowTopLimit > row || row > rowBotLimit) {
      return false;
    }
    if (colLeftLimit > column || column > colRightLimit) {   
      return false;
    }
    if ((Math.abs(this.getRow() - row) == 1 && Math.abs(this.getColumn() - column) == 1)
       ) {
      return true;
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
    if (rowTopLimit > row || row > rowBotLimit)
      return false;
    
    if (colLeftLimit > column || column > colRightLimit)
      return false;
    
    if ((this.getRow() - row == 0 && Math.abs(this.getColumn() - column) == 1) ||
        (this.getColumn() - column == 0 && Math.abs(this.getRow() - row) == 1)
       ){
      return true;
    }
    return false;
  }
  
}