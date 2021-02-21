/**
 * Class for a Horse Piece
 * Extends ChessPiece
 * 
 * @author Jami Biddle
 */
public class HorsePiece extends ChessPiece{
  
  /**
   * 
   * Constructor for the horse piece, which calls the ChessPiece constructor
   * 
   * @param a ChessBoard the piece will be placed on
   * @param side the side this piece belongs to
   * @param label the one letter label used to, without graphics, identify the piece
   * @param icon Object used to create an image for the piece
   * @param row int to indicate the row on which the piece sits
   * @param column int to indicate the column a piece sits on
   */
  public HorsePiece(ChessBoard a, ChessGame.Side side, String label, Object icon, int row, int column) {
    super(a, side, "H", null, row, column);
  }
  
  /**
   * 
   * Determines whether this piece's move is a legal move, as long as it isn't a capture move.
   * 
   * @param row    the row the piece should move to
   * @param column the column the piece is trying to move to
   */
  public boolean isLegalNonCaptureMove(int row, int column) {
    //Makes sure this is a non capture move
    if (this.getChessBoard().hasPiece(row, column))
      return false;
    
    //Gets rid of moves that aren't legal b/c of movement type. Still leaves in knight moves(aka hoppng over other pieces)
    if (!((Math.abs(this.getRow() - row) + Math.abs(this.getColumn() - column)) == 3 && Math.abs(this.getColumn() - column) > 0)) {
      return false;
    }
    
    if (Math.abs(this.getRow() - row) == 2) {
      if (!this.getChessBoard().hasPiece((this.getRow() - (this.getRow() - row) / 2), this.getColumn())){
        return true;
      }
    }
      if (Math.abs(this.getColumn() - column) == 2) {
        if (!this.getChessBoard().hasPiece(this.getRow(), (this.getColumn() - (this.getColumn() - column) / 2))) {
          return true;
        }
      }
    return false;
  }
  
  /**
   * 
   * Determines whether this piece's move is a legal move, as long as it isn't a noncapture move.
   * 
   * @param row    the row the piece should move to
   * @param column the column the piece is trying to move to
   */
  public boolean isLegalCaptureMove(int row, int column) {
    //Makes sure this is a non capture move
    if (!this.getChessBoard().hasPiece(row, column))
      return false;
    
    //Gets rid of moves that aren't legal b/c of movement type. Still leaves in knight moves(aka hoppng over other pieces)
    if (!((Math.abs(this.getRow() - row) + Math.abs(this.getColumn() - column)) == 3 && Math.abs(this.getColumn() - column) > 0)) {
      return false;
    }
    if (Math.abs(this.getRow() - row) == 2) {
      if (!this.getChessBoard().hasPiece((this.getRow() - (this.getRow() - row) / 2), this.getColumn())){
        return true;
      }
    }
    if (Math.abs(this.getColumn() - column) == 2) {
      if (!this.getChessBoard().hasPiece(this.getRow(), (this.getColumn() - (this.getColumn() - column) / 2))) {
        return true;
      }
    }
    return false;
  }
  
}