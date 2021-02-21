import javafx.stage.Stage;


/*
 * Class for Xiangqi. any xiangqi chess will be run through here 
 * Implements ChessGame
 * 
 * @author Jami Biddle
 */
public class Xiangqi implements ChessGame {
  
  //Field for the side who just played (i.e. it is not this side's turn)
  private static ChessGame.Side notTurn;
  
  /**
   * 
   * Getter method for not turn, the side whose turn it is not
   */
  public static ChessGame.Side getTurn() {
    return notTurn;
  }
  
  /**
   * 
   * Setter for the turn, used when someone's turn is up.
   * 
   * @param a the ChessGame.Side who just played
   */
  public static void setTurn(ChessGame.Side a) {
    notTurn = a;
  }
  
  /**
   * 
   * Calls the piece's legal piece to play
   * 
   * @param piece  the piece on which legalPieceToPlay is called
   * @param row    the row the piece is meant to move to
   * @param column the column the piece is meant to move to
   */
  public boolean legalPieceToPlay(ChessPiece piece, int row, int column) {
    if (!piece.getLabel().equals("X"))
      return piece.legalPieceToPlay(piece, row, column);
    else {
      int endRow = getNumRows(piece.getChessBoard());
      int endColumn = getNumColumns(piece.getChessBoard());
      
      //Checks for right moves
      for (int i = 1; column + i < endColumn; i = i + 1) {
        if (piece.getChessBoard().hasPiece(row, i) == true &&
            (piece.getChessBoard().getPiece(row, i).getLabel().equals("X")))
          return false;
        
      }
      
      //Checks for left moves
      for (int i = 1; column - i > 0; i = i + 1) {
        if (piece.getChessBoard().hasPiece(row, i) == true &&
            (piece.getChessBoard().getPiece(row, i).getLabel().equals("X")))
          return false;
      }
      
      
      //Checks for vertical move
      
      //Goes through the spaces downwards
      for (int i = 1; (row + i) < endRow; i++) {
        if (piece.getChessBoard().hasPiece(i, column) == true &&
            (piece.getChessBoard().getPiece(i, column).getLabel().equals("X")))
          return false;
      }
      
      //Goes Through the spaces upwards
      for (int i = 1; (row - i) > row; i++) {
        if (piece.getChessBoard().hasPiece(i, column) == true)
          if (piece.getChessBoard().hasPiece(i, column) == true &&
              (piece.getChessBoard().getPiece(i, column).getLabel().equals("X")))
          return false;
        
      }
    }
    return piece.legalPieceToPlay(piece, row, column);
  }
  
  /**
   * 
   * Makes sure the piece can be moved, and if so calls piece's makeMove() and moveDone()
   * 
   * @param piece  the piece on which legalPieceToPlay is called
   * @param row    the row the piece is meant to move to
   * @param column the column the piece is meant to move to
   */
  @Override
  public boolean makeMove(ChessPiece piece, int rowNext, int columnNext) {
    if ((piece.isLegalMove(rowNext, columnNext) == true) && (legalPieceToPlay(piece, rowNext, columnNext))) {
      piece.makeMove(piece, rowNext, columnNext);
      piece.moveDone();
      return true;
    }
    return false;
  }
  
  @Override
  /**
   * 
   * Allows the player to change their selected piece
   * 
   * @param piece  the piece trying to be moved
   * @param row    the row the piece is meant to move to
   * @param column the column the piece is meant to move to
   */
    public boolean canChangeSelection(ChessPiece piece, int row, int column) {
    return true;
  }
  
  public int getNumRows(ChessBoard c) {
    return c.numRows();
  }
  
  public int getNumColumns(ChessBoard c) {
    return c.numColumns();
  }
  
  /**
   * Put the pieces on the inputted ChessBoard
   * 
   * @param board  the board on which to put the pieces
   *
   */
  public void startGame(ChessBoard board){
    //Initialize the north pieces
    ChessPiece soldierN1 = new SoldierPiece(board, ChessGame.Side.NORTH, "S", null, 3, 0);
    board.addPiece(soldierN1, 3, 0);
    ChessPiece soldierN2 = new SoldierPiece(board, ChessGame.Side.NORTH, "S", null, 3, 2);
    board.addPiece(soldierN2, 3, 2);
    ChessPiece soldierN3 = new SoldierPiece(board, ChessGame.Side.NORTH, "S", null, 3, 4);
    board.addPiece(soldierN3, 3, 4);
    ChessPiece soldierN4 = new SoldierPiece(board, ChessGame.Side.NORTH, "S", null, 3, 6);
    board.addPiece(soldierN4, 3, 6);
    ChessPiece soldierN5 = new SoldierPiece(board, ChessGame.Side.NORTH, "S", null, 3, 8);
    board.addPiece(soldierN5, 3, 8);
    ChessPiece cannonN1 = new CannonPiece(board, ChessGame.Side.NORTH, "C", null, 2, 1);
    board.addPiece(cannonN1, 2, 1);
    ChessPiece cannonN2 = new CannonPiece(board, ChessGame.Side.NORTH, "C", null, 2, 7);
    board.addPiece(cannonN2, 2, 7);
    ChessPiece rookN1 = new RookPiece(board, ChessGame.Side.NORTH, "R", null, 0, 0);
    board.addPiece(rookN1, 0, 0);
    ChessPiece rookN2 = new RookPiece(board, ChessGame.Side.NORTH, "R", null, 0, 8);
    board.addPiece(rookN2, 0, 8);
    ChessPiece horseN1 = new HorsePiece(board, ChessGame.Side.NORTH, "H", null, 0, 1);
    board.addPiece(horseN1, 0, 1);
    ChessPiece horseN2 = new HorsePiece(board, ChessGame.Side.NORTH, "H", null, 0, 7);
    board.addPiece(horseN2, 0, 7);
    ChessPiece elephantN1 = new ElephantPiece(board, ChessGame.Side.NORTH, "E", null, 0, 2);
    board.addPiece(elephantN1, 0, 2);
    ChessPiece elephantN2 = new ElephantPiece(board, ChessGame.Side.NORTH, "E", null, 0, 6);
    board.addPiece(elephantN2, 0, 6);
    ChessPiece guardN1 = new GuardPiece(board, ChessGame.Side.NORTH, "G", null, 0, 3);
    board.addPiece(guardN1, 0, 3);
    ChessPiece guardN2 = new GuardPiece(board, ChessGame.Side.NORTH, "G", null, 0, 5);
    board.addPiece(guardN2, 0, 5);
    ChessPiece xiangqiKingN1 = new XiangqiKingPiece(board, ChessGame.Side.NORTH, "X", null, 0, 4);
    board.addPiece(xiangqiKingN1, 0, 4);
    
    //Initialize the South Side Pieces
     ChessPiece soldierS1 = new SoldierPiece(board, ChessGame.Side.SOUTH, "S", null, 6, 0);
    board.addPiece(soldierS1, 6, 0);
    ChessPiece soldierS2 = new SoldierPiece(board, ChessGame.Side.SOUTH, "S", null, 6, 2);
    board.addPiece(soldierS2, 6, 2);
    ChessPiece soldierS3 = new SoldierPiece(board, ChessGame.Side.SOUTH, "S", null, 6, 4);
    board.addPiece(soldierS3, 6, 4);
    ChessPiece soldierS4 = new SoldierPiece(board, ChessGame.Side.SOUTH, "S", null, 6, 6);
    board.addPiece(soldierS4, 6, 6);
    ChessPiece soldierS5 = new SoldierPiece(board, ChessGame.Side.SOUTH, "S", null, 6, 8);
    board.addPiece(soldierS5, 6, 8);
    ChessPiece cannonS1 = new CannonPiece(board, ChessGame.Side.SOUTH, "C", null, 7, 1);
    board.addPiece(cannonS1, 7, 1);
    ChessPiece cannonS2 = new CannonPiece(board, ChessGame.Side.SOUTH, "C", null, 7, 7);
    board.addPiece(cannonS2, 7, 7);
    ChessPiece rookS1 = new RookPiece(board, ChessGame.Side.SOUTH, "R", null, 9, 0);
    board.addPiece(rookS1, 9, 0);
    ChessPiece rookS2 = new RookPiece(board, ChessGame.Side.SOUTH, "R", null, 9, 8);
    board.addPiece(rookS2, 9, 8);
    ChessPiece horseS1 = new HorsePiece(board, ChessGame.Side.SOUTH, "H", null, 9, 1);
    board.addPiece(horseS1, 9, 1);
    ChessPiece horseS2 = new HorsePiece(board, ChessGame.Side.SOUTH, "H", null, 9, 7);
    board.addPiece(horseS2, 9, 7);
    ChessPiece elephantS1 = new ElephantPiece(board, ChessGame.Side.SOUTH, "E", null, 9, 2);
    board.addPiece(elephantS1, 9, 2);
    ChessPiece elephantS2 = new ElephantPiece(board, ChessGame.Side.SOUTH, "E", null, 9, 6);
    board.addPiece(elephantS2, 9, 6);
    ChessPiece guardS1 = new GuardPiece(board, ChessGame.Side.SOUTH, "G", null, 9, 3);
    board.addPiece(guardS1, 9, 3);
    ChessPiece guardS2 = new GuardPiece(board, ChessGame.Side.SOUTH, "G", null, 9, 5);
    board.addPiece(guardS2, 9, 5);
    ChessPiece xiangqiKingS1 = new XiangqiKingPiece(board, ChessGame.Side.SOUTH, "X", null, 9, 4);
    board.addPiece(xiangqiKingS1, 9, 4);
  }
  
  /**
   * 
   * Main function, to launch a game of xiangqi
   * 
   * @param args either "swing" or "javafx, depended on the wanted GUI
   */
  public static void main(String[] args) {
    try {
      
      if (args[0].equals("javafx")) {
        String[] typeGame = {"xiangqi"};
        JavaFXChessBoard.main(typeGame);
      }
      
      if (args[0].equals("swing")) {
        ChessBoard board = new SwingChessBoard(10, 9, new SwingXiangqiDisplay(), new Xiangqi());
        board.getGameRules().startGame(board);
      }
      
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}