public class EuropeanChess implements ChessGame {

    //Stores the side who just went, i.e. it is not their turn.
    private static ChessGame.Side notTurn;

    //Getter for the turn
    public static ChessGame.Side getTurn() {
        return notTurn;
    }

    //Setter for the turn, used when someone's turn is up.
    public static void setTurn(ChessGame.Side a) {
        notTurn = a;
    }

    //Calls ChessPiece's legalPieceToPlay
    public boolean legalPieceToPlay(ChessPiece piece, int row, int column) {
        return piece.legalPieceToPlay(piece, row, column);
    }

    @Override
    //Double checks that the piece can be played and that the move is legal, then executes make move and move done
    public boolean makeMove(ChessPiece piece, int rowNext, int columnNext) {
        if ((piece.isLegalMove(rowNext, columnNext) == true) && (legalPieceToPlay(piece, rowNext, columnNext))) {
            piece.makeMove(piece, rowNext, columnNext);
            piece.moveDone();
            return true;
        }
        return false;
    }

    @Override
    //Allows players to change their selection. it seems mean to not.
    public boolean canChangeSelection(ChessPiece piece, int row, int column) {
        return true;
    }
    
    public int getNumRows(ChessBoard c) {
      return c.numRows();
    }
    
    public int getNumColumns(ChessBoard c) {
      return c.numColumns();
    }
      
    public void startGame(ChessBoard board){
      //Initializing the North side pawns
        ChessPiece pawnN1 = new PawnPiece(board, ChessGame.Side.NORTH, "P", null, 1, 0);
        board.addPiece(pawnN1, 1, 0);
        ChessPiece pawnN2 = new PawnPiece(board, ChessGame.Side.NORTH, "P", null, 1, 1);
        board.addPiece(pawnN2, 1, 1);
        ChessPiece pieceN3 = new PawnPiece(board, ChessGame.Side.NORTH, "P", null, 1, 2);
        board.addPiece(pieceN3, 1, 2);
        ChessPiece pieceN4 = new PawnPiece(board, ChessGame.Side.NORTH, "P", null, 1, 3);
        board.addPiece(pieceN4, 1, 3);
        ChessPiece pieceN5 = new PawnPiece(board, ChessGame.Side.NORTH, "P", null, 1, 4);
        board.addPiece(pieceN5, 1, 4);
        ChessPiece pieceN6 = new PawnPiece(board, ChessGame.Side.NORTH, "P", null, 1, 5);
        board.addPiece(pieceN6, 1, 5);
        ChessPiece pieceN7 = new PawnPiece(board, ChessGame.Side.NORTH, "P", null, 1, 6);
        board.addPiece(pieceN7, 1, 6);
        ChessPiece pieceN8 = new PawnPiece(board, ChessGame.Side.NORTH, "P", null, 1, 7);
        board.addPiece(pieceN8, 1, 7);

        //Initializing the north side non-pawn pieces
        ChessPiece kingN = new KingPiece(board, ChessGame.Side.NORTH, "K", null, 0, 3);
        board.addPiece(kingN, 0, 3);
        ChessPiece rookN1 = new RookPiece(board, ChessGame.Side.NORTH, "R", null, 0, 0);
        board.addPiece(rookN1, 0, 0);
        ChessPiece rookN2 = new RookPiece(board, ChessGame.Side.NORTH, "R", null, 0, 7);
        board.addPiece(rookN2, 0, 7);
        ChessPiece bishopN1 = new BishopPiece(board, ChessGame.Side.NORTH, "B", null, 0, 2);
        board.addPiece(bishopN1, 0, 2);
        ChessPiece bishopN2 = new BishopPiece(board, ChessGame.Side.NORTH, "B", null, 0, 5);
        board.addPiece(bishopN2, 0, 5);
        ChessPiece knightN1 = new KnightPiece(board, ChessGame.Side.NORTH, "K", null, 0, 1);
        board.addPiece(knightN1, 0, 1);
        ChessPiece knightN2 = new KnightPiece(board, ChessGame.Side.NORTH, "K", null, 0, 6);
        board.addPiece(knightN2, 0, 6);
        ChessPiece queenN = new QueenPiece(board, ChessGame.Side.NORTH, "Q", null, 0, 4);
        board.addPiece(queenN, 0, 4);

        //Initializing non pawn south pieces
        ChessPiece kingS = new KingPiece(board, ChessGame.Side.SOUTH, "K", null, 7, 4);
        board.addPiece(kingS, 7, 4);
        ChessPiece rookS1 = new RookPiece(board, ChessGame.Side.SOUTH, "R", null, 7, 0);
        board.addPiece(rookS1, 7, 0);
        ChessPiece rookS2 = new RookPiece(board, ChessGame.Side.SOUTH, "R", null, 7, 7);
        board.addPiece(rookS2, 7, 7);
        ChessPiece bishopS1 = new BishopPiece(board, ChessGame.Side.SOUTH, "B", null, 7, 2);
        board.addPiece(bishopS1, 7, 2);
        ChessPiece bishopS2 = new BishopPiece(board, ChessGame.Side.SOUTH, "B", null, 7, 5);
        board.addPiece(bishopS2, 7, 5);
        ChessPiece knightS1 = new KnightPiece(board, ChessGame.Side.SOUTH, "K", null, 7, 1);
        board.addPiece(knightS1, 7, 1);
        ChessPiece knightS2 = new KnightPiece(board, ChessGame.Side.SOUTH, "K", null, 7, 6);
        board.addPiece(knightS2, 7, 6);
        ChessPiece queenS = new QueenPiece(board, ChessGame.Side.SOUTH, "Q", null, 7, 3);
        board.addPiece(queenS, 7, 3);

        //Initializing South side pawns
        ChessPiece pawnS1 = new PawnPiece(board, ChessGame.Side.SOUTH, "P", null, 6, 0);
        ;
        board.addPiece(pawnS1, 6, 0);
        ChessPiece pawnS2 = new PawnPiece(board, ChessGame.Side.SOUTH, "P", null, 6, 1);
        board.addPiece(pawnS2, 6, 1);
        ChessPiece pieceS3 = new PawnPiece(board, ChessGame.Side.SOUTH, "P", null, 6, 2);
        board.addPiece(pieceS3, 6, 2);
        ChessPiece pieceS4 = new PawnPiece(board, ChessGame.Side.SOUTH, "P", null, 6, 3);
        board.addPiece(pieceS4, 6, 3);
        ChessPiece pieceS5 = new PawnPiece(board, ChessGame.Side.SOUTH, "P", null, 6, 4);
        board.addPiece(pieceS5, 6, 4);
        ChessPiece pieceS6 = new PawnPiece(board, ChessGame.Side.SOUTH, "P", null, 6, 5);
        board.addPiece(pieceS6, 6, 5);
        ChessPiece pieceS7 = new PawnPiece(board, ChessGame.Side.SOUTH, "P", null, 6, 6);
        board.addPiece(pieceS7, 6, 6);
        ChessPiece pieceS8 = new PawnPiece(board, ChessGame.Side.SOUTH, "P", null, 6, 7);
        board.addPiece(pieceS8, 6, 7);
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
            String[] typeGame = {"chess"};
            JavaFXChessBoard.main(typeGame);
          }
          
          if (args[0].equals("swing")) {
            ChessBoard board = new SwingChessBoard(8, 8, new SwingEuropeanChessDisplay(), new EuropeanChess());
            board.getGameRules().startGame(board);
          }
          
        }
        catch (Exception e) {
          e.printStackTrace();
        }
    }
}