import org.junit.*;

import static org.junit.Assert.*;

/**
 * Testing class for the Xiangqi pieces and some of the Project 5 classes which can be tested simply using JUnit
 * 
 * Programmer's Note: While this project involves JavaFX, I could not feasibly figure out how to fully test 
 * all the pieces with JavaFX board. thus, I use swing boards. Considering gameplay and GUIs should be totally
 * separate anyways, I will include all the info about how JavaFX works in the testing document. Thanks.
 * 
 * @author Jami Biddle
 */

public class Project5Tester {
  
   @Test
  public void testXiangqiKingPiece() {
    
    ChessBoard board = new SwingChessBoard(10, 9, new SwingXiangqiDisplay(), new Xiangqi());

    //Initializes testpiece, a rook to test with
    ChessPiece testpiece = new XiangqiKingPiece(board, ChessGame.Side.NORTH, "X", null, 4, 4);
    board.addPiece(testpiece, 4, 4);
    
    //These getters are checking the constructor for my Rook Piece
    
    //Makes sure the returned board is what I created, 'board'
    assertEquals("Getter for the chess board", board, testpiece.getChessBoard());
    
    //Makes sure the returned side is what it should be (North)
    assertEquals("Getter for the piece side", ChessGame.Side.NORTH, testpiece.getSide());
    
    //Makes sure the returned Label is what it should be ("R")
    assertEquals("Getter for the Label", "X", testpiece.getLabel());
    
    //Makes sure the returned Icon is what it should be (null)
    assertEquals("Getter for the icon", null, testpiece.getIcon());
    
    //Makes sure the returned row is what it should be (0)
    assertEquals("Getter for the row", 4, testpiece.getRow());
    
    //Makes sure the returned column is what it should be (1)
    assertEquals("Getter for the column", 4, testpiece.getColumn());
    
   }
   
   
  @Test
  public void testRookPiece() {
    
    ChessBoard board = new SwingChessBoard(10, 9, new SwingXiangqiDisplay(), new Xiangqi());

    //Initializes testpiece, a rook to test with
    ChessPiece testpiece = new RookPiece(board, ChessGame.Side.NORTH, "R", null, 4, 4);
    board.addPiece(testpiece, 4, 4);
    
    //These getters are checking the constructor for my Rook Piece
    
    //Makes sure the returned board is what I created, 'board'
    assertEquals("Getter for the chess board", board, testpiece.getChessBoard());
    
    //Makes sure the returned side is what it should be (North)
    assertEquals("Getter for the piece side", ChessGame.Side.NORTH, testpiece.getSide());
    
    //Makes sure the returned Label is what it should be ("R")
    assertEquals("Getter for the Label", "R", testpiece.getLabel());
    
    //Makes sure the returned Icon is what it should be (null)
    assertEquals("Getter for the icon", null, testpiece.getIcon());
    
    //Makes sure the returned row is what it should be (0)
    assertEquals("Getter for the row", 4, testpiece.getRow());
    
    //Makes sure the returned column is what it should be (1)
    assertEquals("Getter for the column", 4, testpiece.getColumn());
    
    //True Tests for the knight piece's isLegalNonCaptureMove
    assertEquals("Tests a legal up move", true, testpiece.isLegalNonCaptureMove(2, 4));
    assertEquals("Tests a legal left", true, testpiece.isLegalNonCaptureMove(4, 2));
    assertEquals("Tests a legal down move", true, testpiece.isLegalNonCaptureMove(6, 4));
    assertEquals("Tests a legal right move", true, testpiece.isLegalNonCaptureMove(4, 6));
    
    //False Tests for isLegalNonCaptureMove
    ChessPiece testCapPiece = new PawnPiece(board, ChessGame.Side.NORTH, "P", null, 1, 4);
    board.addPiece(testCapPiece, 1, 4);
    ChessPiece testCap2Piece = new PawnPiece(board, ChessGame.Side.SOUTH, "P", null, 4, 1);
    board.addPiece(testCap2Piece, 4, 1);
    
    assertEquals("Tests capture move on a pawn (Same side)", false, testpiece.isLegalNonCaptureMove(1, 4));
    assertEquals("Tests capture move on a pawn (Different side)", false, testpiece.isLegalNonCaptureMove(4, 1));
    assertEquals("Tests a non-straight move", false, testpiece.isLegalNonCaptureMove(5, 5));
    
    //Getting testCap2Piece out of the way
    testCap2Piece.makeMove(testCap2Piece, 7, 7);
    
    //Testing loop that checks if there's a piece between
    testCapPiece.makeMove(testCapPiece, 4, 5);
    testpiece.makeMove(testpiece, 4, 0);
    
    assertEquals("Tests with one piece between here and intended space", false, testpiece.isLegalNonCaptureMove(4, 7));
    
    testCapPiece.makeMove(testCapPiece, 4, 1);
    assertEquals("Tests with one piece in first space between the two", false, testpiece.isLegalNonCaptureMove(4, 7));
    
    testCapPiece.makeMove(testCapPiece, 4, 6);
    assertEquals("Tests with one piece in last space between the two", false, testpiece.isLegalNonCaptureMove(4, 7));
    
    testCap2Piece.makeMove(testCap2Piece, 4, 5);
    assertEquals("Tests with many pieces in between the two", false, testpiece.isLegalNonCaptureMove(4, 7));
    
    //Tests for isLegalCaptureMove
    
    //Testing loop that checks if there's a piece between
    testCapPiece.makeMove(testCapPiece, 4, 5);
    testpiece.makeMove(testpiece, 4, 0);
    
    assertEquals("Tests with one piece between here and intended space", false, testpiece.isLegalCaptureMove(4, 7));
    
    testCapPiece.makeMove(testCapPiece, 4, 1);
    assertEquals("Tests with one piece in first space between the two", false, testpiece.isLegalCaptureMove(4, 7));
    
    testCapPiece.makeMove(testCapPiece, 4, 6);
    assertEquals("Tests with one piece in last space between the two", false, testpiece.isLegalCaptureMove(4, 7));
    
    testCap2Piece.makeMove(testCap2Piece, 4, 5);
    assertEquals("Tests with many pieces in between the two", false, testpiece.isLegalCaptureMove(4, 7));
    
    
    ChessPiece testCap3Piece = new QueenPiece(board, ChessGame.Side.SOUTH, "Q", null, 4, 7);
    board.addPiece(testCap3Piece, 4, 7);
    ChessPiece testCap4Piece = new QueenPiece(board, ChessGame.Side.SOUTH, "Q", null, 7, 4);
    board.addPiece(testCap4Piece, 7, 4);
    ChessPiece testCap5Piece = new QueenPiece(board, ChessGame.Side.SOUTH, "Q", null, 3, 4);
    board.addPiece(testCap5Piece, 3, 4);
    
    assertEquals("Tests capture move on a pawn (Same side)", false, testpiece.isLegalCaptureMove(1, 4));
    assertEquals("Tests capture move on a pawn (Different side)", true, testpiece.isLegalCaptureMove(4, 1));
    assertEquals("Tests a non-rook move", false, testpiece.isLegalCaptureMove(5, 5));
    assertEquals("Tests a 'legal' move, but the space is empty", false, testpiece.isLegalCaptureMove(4, 0));
    assertEquals("Tests a legal up capture move", true, testpiece.isLegalCaptureMove(4, 1));
    assertEquals("Tests a legal left capture move", true, testpiece.isLegalCaptureMove(4, 7));
    assertEquals("Tests a legal down capture move", true, testpiece.isLegalCaptureMove(7, 4));
    assertEquals("Tests a legal right capture move", true, testpiece.isLegalCaptureMove(3, 4));
    
  }
  
  @Test
  public void testCannonPiece() {
    
    ChessBoard board = new SwingChessBoard(10, 9, new SwingXiangqiDisplay(), new Xiangqi());

    //Initializes testpiece, a rook to test with
    ChessPiece testpiece = new CannonPiece(board, ChessGame.Side.NORTH, "C", null, 4, 4);
    board.addPiece(testpiece, 4, 4);
    
    //These getters are checking the constructor for my Rook Piece
    
    //Makes sure the returned board is what I created, 'board'
    assertEquals("Getter for the chess board", board, testpiece.getChessBoard());
    
    //Makes sure the returned side is what it should be (North)
    assertEquals("Getter for the piece side", ChessGame.Side.NORTH, testpiece.getSide());
    
    //Makes sure the returned Label is what it should be ("R")
    assertEquals("Getter for the Label", "C", testpiece.getLabel());
    
    //Makes sure the returned Icon is what it should be (null)
    assertEquals("Getter for the icon", null, testpiece.getIcon());
    
    //Makes sure the returned row is what it should be (0)
    assertEquals("Getter for the row", 4, testpiece.getRow());
    
    //Makes sure the returned column is what it should be (1)
    assertEquals("Getter for the column", 4, testpiece.getColumn());
    
    //True Tests for the Cannon piece's isLegalNonCaptureMove
    assertEquals("Tests a legal up move", true, testpiece.isLegalNonCaptureMove(2, 4));
    assertEquals("Tests a legal left", true, testpiece.isLegalNonCaptureMove(4, 2));
    assertEquals("Tests a legal down move", true, testpiece.isLegalNonCaptureMove(6, 4));
    assertEquals("Tests a legal right move", true, testpiece.isLegalNonCaptureMove(4, 6));
    
    //False Tests for isLegalNonCaptureMove
    ChessPiece testCapPiece = new PawnPiece(board, ChessGame.Side.NORTH, "P", null, 1, 4);
    board.addPiece(testCapPiece, 1, 4);
    ChessPiece testCap2Piece = new PawnPiece(board, ChessGame.Side.SOUTH, "P", null, 4, 1);
    board.addPiece(testCap2Piece, 4, 1);
    
    assertEquals("Tests capture move on a pawn (Same side)", false, testpiece.isLegalNonCaptureMove(1, 4));
    assertEquals("Tests capture move on a pawn (Different side)", false, testpiece.isLegalNonCaptureMove(4, 1));
    assertEquals("Tests a non-straight move", false, testpiece.isLegalNonCaptureMove(5, 5));
    
    //Getting testCap2Piece out of the way
    testCap2Piece.makeMove(testCap2Piece, 7, 7);
    
    //Testing loop that checks if there's a piece between
    testCapPiece.makeMove(testCapPiece, 4, 5);
    testpiece.makeMove(testpiece, 4, 0);
    
    assertEquals("Tests with one piece between here and intended space", false, testpiece.isLegalNonCaptureMove(4, 7));
    
    testCapPiece.makeMove(testCapPiece, 4, 1);
    assertEquals("Tests with one piece in first space between the two", false, testpiece.isLegalNonCaptureMove(4, 7));
    
    testCapPiece.makeMove(testCapPiece, 4, 6);
    assertEquals("Tests with one piece in last space between the two", false, testpiece.isLegalNonCaptureMove(4, 7));
    
    testCap2Piece.makeMove(testCap2Piece, 4, 5);
    assertEquals("Tests with many pieces in between the two", false, testpiece.isLegalNonCaptureMove(4, 7));
    
    //Tests for isLegalCaptureMove
    
    //Testing loop that checks if there's a piece between
    testCapPiece.makeMove(testCapPiece, 4, 5);
    testpiece.makeMove(testpiece, 4, 0);
    
    assertEquals("Tests with one piece between here and intended space", true, testpiece.isLegalCaptureMove(4, 7));
    
    testCapPiece.makeMove(testCapPiece, 4, 1);
    assertEquals("Tests with one piece in first space between the two", true, testpiece.isLegalCaptureMove(4, 7));
    
    testCapPiece.makeMove(testCapPiece, 4, 6);
    assertEquals("Tests with one piece in last space between the two", true, testpiece.isLegalCaptureMove(4, 7));
    
    testCap2Piece.makeMove(testCap2Piece, 4, 5);
    assertEquals("Tests with many pieces in between the two", true, testpiece.isLegalCaptureMove(4, 7));
    
    
    ChessPiece testCap3Piece = new QueenPiece(board, ChessGame.Side.SOUTH, "Q", null, 4, 7);
    board.addPiece(testCap3Piece, 4, 7);
    ChessPiece testCap4Piece = new QueenPiece(board, ChessGame.Side.SOUTH, "Q", null, 7, 4);
    board.addPiece(testCap4Piece, 7, 4);
    ChessPiece testCap5Piece = new QueenPiece(board, ChessGame.Side.SOUTH, "Q", null, 3, 4);
    board.addPiece(testCap5Piece, 3, 4);
    
    assertEquals("Tests capture move on a pawn (Same side)", false, testpiece.isLegalCaptureMove(1, 4));
    assertEquals("Tests capture move on a pawn (Different side)", true, testpiece.isLegalCaptureMove(4, 1));
    assertEquals("Tests a non-rook move", false, testpiece.isLegalCaptureMove(5, 5));
    assertEquals("Tests a 'legal' move, but the space is empty", false, testpiece.isLegalCaptureMove(4, 0));
    assertEquals("Tests a legal up capture move", true, testpiece.isLegalCaptureMove(4, 1));
    assertEquals("Tests a legal left capture move", true, testpiece.isLegalCaptureMove(4, 7));
    assertEquals("Tests a legal down capture move", true, testpiece.isLegalCaptureMove(7, 4));
    assertEquals("Tests a legal right capture move", true, testpiece.isLegalCaptureMove(3, 4));
    
  }
  
  
  @Test
  //Tests the methods (other than main, see testing doc.) in EuropeanChess
    public void testEuropeanChess() {
    //Tests the getter & setter for european chess
    EuropeanChess.setTurn(ChessGame.Side.NORTH);
    assertEquals("Gets the turn", ChessGame.Side.NORTH, EuropeanChess.getTurn());
    
    //initiaalizes a piece to test on
    ChessBoard board = new SwingChessBoard(8, 8, new SwingEuropeanChessDisplay(), new EuropeanChess());
    ChessPiece testpiece = new QueenPiece(board, ChessGame.Side.WEST, "!", null, 4, 4);
    board.addPiece(testpiece, 4, 4);
    
    //Test MakeMove
    assertEquals("MakeMove for a legal Move", true, testpiece.makeMove(testpiece, 4, 5));
    assertEquals("MakeMove for an illegal Move", false, testpiece.makeMove(testpiece, 0, 6));
    
  }
}


