import org.junit.*;

import static org.junit.Assert.*;

public class ChessTester {

    @Test
    public void testChessPiece() {
        //Create a new chess board and piece to test on
        ChessBoard board = new SwingChessBoard(8, 8, new SwingEuropeanChessDisplay(), new EuropeanChess());

        //ChessPiece (of type RookPiece) that I can test the getter/setter methods on
        //If all the getters work properly, we can also say the constructor likely worked properly as well.
        ChessPiece testpiece = new RookPiece(board, ChessGame.Side.NORTH, "R", null, 0, 1);

        //Makes sure the returned board is what I created, 'board'
        assertEquals("Getter for the chess board", board, testpiece.getChessBoard());

        //Makes sure the returned side is what it should be (North)
        assertEquals("Getter for the piece side", ChessGame.Side.NORTH, testpiece.getSide());

        //Makes sure the returned Label is what it should be ("R")
        assertEquals("Getter for the Label", "R", testpiece.getLabel());

        //Makes sure the returned Icon is what it should be (null)
        assertEquals("Getter for the icon", null, testpiece.getIcon());

        //Makes sure the returned row is what it should be (0)
        assertEquals("Getter for the row", 0, testpiece.getRow());

        //Makes sure the returned column is what it should be (1)
        assertEquals("Getter for the column", 1, testpiece.getColumn());

        //Using setLocation, which i will test with the getters that follow
        testpiece.setLocation(6, 7);

        //Makes sure the setLocation did, in fact, change the location of the pieces.
        assertEquals("Getter for the row", 6, testpiece.getRow());
        assertEquals("Getter for the column", 7, testpiece.getColumn());


        //Tests make move, using a wrong move, then a right move
        //Simply checks that it could or could not call the make move of the piece type properly
        assertEquals("Make move: diagonal move on a rook", false, testpiece.makeMove(testpiece, 5, 6));
        assertEquals("Make move: upwards move on a rook", true, testpiece.makeMove(testpiece, 6, 7));

        //Tests isLegalPieceToPlay
        EuropeanChess.setTurn(ChessGame.Side.EAST);
        assertEquals("Tests legal piece to play: true", true, testpiece.legalPieceToPlay(testpiece, 6, 5));
        testpiece.makeMove(testpiece, 6, 5);
        assertEquals("Tests legal piece to play: false", false, testpiece.legalPieceToPlay(testpiece, 5, 4));

    }

    @Test
    public void testKnightPiece() {
        //Board to test on
        ChessBoard board = new SwingChessBoard(8, 8, new SwingEuropeanChessDisplay(), new EuropeanChess());
        //Initializes testpiece, a knight to test with
        ChessPiece testpiece = new KnightPiece(board, ChessGame.Side.NORTH, "N", null, 4, 4);
        board.addPiece(testpiece, 4, 4);
        //These getters are checking the constructor for my Knight Piece

        //Makes sure the returned board is what I created, 'board'
        assertEquals("Getter for the chess board", board, testpiece.getChessBoard());

        //Makes sure the returned side is what it should be (North)
        assertEquals("Getter for the piece side", ChessGame.Side.NORTH, testpiece.getSide());

        //Makes sure the returned Label is what it should be ("R")
        assertEquals("Getter for the Label", "N", testpiece.getLabel());

        //Makes sure the returned Icon is what it should be (null)
        assertEquals("Getter for the icon", null, testpiece.getIcon());

        //Makes sure the returned row is what it should be (0)
        assertEquals("Getter for the row", 4, testpiece.getRow());

        //Makes sure the returned column is what it should be (1)
        assertEquals("Getter for the column", 4, testpiece.getColumn());

        //True Tests for the knight piece's isLegalNonCaptureMove
        assertEquals("Tests a legal up, to the left move", true, testpiece.isLegalNonCaptureMove(2, 5));
        assertEquals("Tests a legal left, up move", true, testpiece.isLegalNonCaptureMove(3, 6));
        assertEquals("Tests a legal left, down move", true, testpiece.isLegalNonCaptureMove(5, 6));
        assertEquals("Tests a legal down, left move", true, testpiece.isLegalNonCaptureMove(6, 5));
        assertEquals("Tests a legal down, right move", true, testpiece.isLegalNonCaptureMove(6, 3));
        assertEquals("Tests a legal right, down move", true, testpiece.isLegalNonCaptureMove(5, 2));
        assertEquals("Tests a legal right, up move", true, testpiece.isLegalNonCaptureMove(3, 2));
        assertEquals("Tests a legal up, right move", true, testpiece.isLegalNonCaptureMove(2, 3));

        //False Tests for isLegalNonCaptureMove
        ChessPiece testCapPiece = new KnightPiece(board, ChessGame.Side.NORTH, "N", null, 3, 2);
        board.addPiece(testCapPiece, 3, 2);
        ChessPiece testCap2Piece = new KnightPiece(board, ChessGame.Side.SOUTH, "N", null, 5, 6);
        board.addPiece(testCap2Piece, 5, 6);

        assertEquals("Tests capture move on another knight (Same side)", false, testpiece.isLegalNonCaptureMove(3, 2));
        assertEquals("Tests capture move on another knight (Different side)", false, testpiece.isLegalNonCaptureMove(5, 6));
        assertEquals("Tests a non-knight move", false, testpiece.isLegalNonCaptureMove(5, 5));

        //Tests for isLegalCaptureMove
        assertEquals("Tests capture move on another knight (Same side)", false, testpiece.isLegalCaptureMove(3, 2));
        assertEquals("Tests capture move on another knight (Different side)", true, testpiece.isLegalCaptureMove(5, 6));
        assertEquals("Tests a non-knight move", false, testpiece.isLegalNonCaptureMove(5, 5));
        assertEquals("Tests a 'legal' move, but the space is empty", false, testpiece.isLegalCaptureMove(2, 3));

        //Tests is legal move
        assertEquals("LegalMove for a legal Non Cap Move", true, testpiece.isLegalMove(6, 3));
        assertEquals("LegalMove for a legal Cap Move", true, testpiece.isLegalMove(2, 3));
        assertEquals("LegalMove for an illegal Move", false, testpiece.isLegalMove(0, 0));

        //Test MakeMove
        assertEquals("MakeMove for a legal Move", true, testpiece.makeMove(testpiece, 2, 3));
        assertEquals("MakeMove for an illegal Move", false, testpiece.makeMove(testpiece, 0, 0));

        //Test MoveDone (which returns void, so I'm getting the turn to make sure it has the same value as this piece)
        testpiece.moveDone();
        assertEquals("Getting the turn to confirm moveDone", ChessGame.Side.NORTH, EuropeanChess.getTurn());
    }

    @Test
    public void testRookPiece() {
        //Board to test on
        ChessBoard board = new SwingChessBoard(8, 8, new SwingEuropeanChessDisplay(), new EuropeanChess());
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


        ChessPiece testCap3Piece = new PawnPiece(board, ChessGame.Side.SOUTH, "P", null, 4, 7);
        board.addPiece(testCap3Piece, 4, 7);
        ChessPiece testCap4Piece = new PawnPiece(board, ChessGame.Side.SOUTH, "P", null, 7, 4);
        board.addPiece(testCap4Piece, 7, 4);
        ChessPiece testCap5Piece = new PawnPiece(board, ChessGame.Side.SOUTH, "P", null, 3, 4);
        board.addPiece(testCap5Piece, 3, 4);

        assertEquals("Tests capture move on a pawn (Same side)", false, testpiece.isLegalCaptureMove(1, 4));
        assertEquals("Tests capture move on a pawn (Different side)", true, testpiece.isLegalCaptureMove(4, 1));
        assertEquals("Tests a non-rook move", false, testpiece.isLegalCaptureMove(5, 5));
        assertEquals("Tests a 'legal' move, but the space is empty", false, testpiece.isLegalCaptureMove(4, 0));
        assertEquals("Tests a legal up capture move", true, testpiece.isLegalCaptureMove(4, 1));
        assertEquals("Tests a legal left capture move", true, testpiece.isLegalCaptureMove(4, 7));
        assertEquals("Tests a legal down capture move", true, testpiece.isLegalCaptureMove(7, 4));
        assertEquals("Tests a legal right capture move", true, testpiece.isLegalCaptureMove(3, 4));

        //Getting rid of excess pieces
        board.removePiece(3, 4);
        board.removePiece(7, 4);
        board.removePiece(4, 7);

        //Tests is legal move
        assertEquals("LegalMove for a legal Non Cap Move", true, testpiece.isLegalMove(4, 5));
        assertEquals("LegalMove for a legal Cap Move", true, testpiece.isLegalMove(4, 1));
        assertEquals("LegalMove for an illegal Move", false, testpiece.isLegalMove(7, 0));

        //Test MakeMove
        assertEquals("MakeMove for a legal Move", true, testpiece.makeMove(testpiece, 4, 1));
        assertEquals("MakeMove for an illegal Move", false, testpiece.makeMove(testpiece, 7, 0));

        //Test MoveDone (which returns void, so I'm getting EC's turn to make sure that occured.
        testpiece.moveDone();
        assertEquals("Getting the turn to confirm moveDone", ChessGame.Side.NORTH, EuropeanChess.getTurn());

    }


    @Test
    public void testBishopPiece() {
        //Board to test on
        ChessBoard board = new SwingChessBoard(8, 8, new SwingEuropeanChessDisplay(), new EuropeanChess());
        //Initializes testpiece, a bishop to test with
        ChessPiece testpiece = new BishopPiece(board, ChessGame.Side.NORTH, "B", null, 4, 4);
        board.addPiece(testpiece, 4, 4);

        //These getters are checking the constructor for my Bishop Piece

        //Makes sure the returned board is what I created, 'board'
        assertEquals("Getter for the chess board", board, testpiece.getChessBoard());

        //Makes sure the returned side is what it should be (North)
        assertEquals("Getter for the piece side", ChessGame.Side.NORTH, testpiece.getSide());

        //Makes sure the returned Label is what it should be ("B")
        assertEquals("Getter for the Label", "B", testpiece.getLabel());

        //Makes sure the returned Icon is what it should be (null)
        assertEquals("Getter for the icon", null, testpiece.getIcon());

        //Makes sure the returned row is what it should be (0)
        assertEquals("Getter for the row", 4, testpiece.getRow());

        //Makes sure the returned column is what it should be (1)
        assertEquals("Getter for the column", 4, testpiece.getColumn());

        //True Tests for the bishop piece's isLegalNonCaptureMove
        assertEquals("Tests a legal down, right move", true, testpiece.isLegalNonCaptureMove(5, 5));
        assertEquals("Tests a legal up, left move", true, testpiece.isLegalNonCaptureMove(3, 3));
        assertEquals("Tests a legal up right move", true, testpiece.isLegalNonCaptureMove(3, 5));
        assertEquals("Tests a legal down, left move", true, testpiece.isLegalNonCaptureMove(5, 3));

        //False Tests for isLegalNonCaptureMove
        ChessPiece testCapPiece = new QueenPiece(board, ChessGame.Side.NORTH, "Q", null, 7, 7);
        board.addPiece(testCapPiece, 7, 7);
        ChessPiece testCap2Piece = new PawnPiece(board, ChessGame.Side.SOUTH, "P", null, 0, 0);
        board.addPiece(testCap2Piece, 0, 0);

        assertEquals("Tests capture move on a queen (Same side)", false, testpiece.isLegalNonCaptureMove(7, 7));
        assertEquals("Tests capture move on a pawn (Different side)", false, testpiece.isLegalNonCaptureMove(0, 0));
        assertEquals("Tests a non-diagonal move", false, testpiece.isLegalNonCaptureMove(4, 6));


        //Testing loop that checks if there's a piece between
        testpiece.makeMove(testCapPiece, 3, 3);
        testCapPiece.makeMove(testCapPiece, 5, 5);

        assertEquals("Tests with one piece between here and intended space", false, testpiece.isLegalNonCaptureMove(7, 7));

        testCapPiece.makeMove(testCapPiece, 4, 4);
        assertEquals("Tests with one piece in first space between the two", false, testpiece.isLegalNonCaptureMove(7, 7));

        testCapPiece.makeMove(testCapPiece, 6, 6);
        assertEquals("Tests with one piece in last space between the two", false, testpiece.isLegalNonCaptureMove(7, 7));

        testCap2Piece.makeMove(testCap2Piece, 5, 5);
        assertEquals("Tests with many pieces in between the two", false, testpiece.isLegalNonCaptureMove(7, 7));

        //Tests for isLegalCaptureMove

        //Testing loop that checks if there's a piece between
        testpiece.makeMove(testpiece, 3, 3);
        testCapPiece.makeMove(testCapPiece, 5, 5);

        assertEquals("Tests with one piece between here and intended space", false, testpiece.isLegalNonCaptureMove(7, 7));

        testCapPiece.makeMove(testCapPiece, 4, 4);
        assertEquals("Tests with one piece in first space between the two", false, testpiece.isLegalNonCaptureMove(7, 7));

        testCapPiece.makeMove(testCapPiece, 6, 6);
        assertEquals("Tests with one piece in last space between the two", false, testpiece.isLegalNonCaptureMove(7, 7));

        testCap2Piece.makeMove(testCap2Piece, 5, 5);
        assertEquals("Tests with many pieces in between the two", false, testpiece.isLegalNonCaptureMove(7, 7));

        board.removePiece(5, 5);
        board.removePiece(6, 6);

        //Adding piece
        ChessPiece testCap3Piece = new QueenPiece(board, ChessGame.Side.SOUTH, "Q", null, 5, 5);
        board.addPiece(testCap3Piece, 5, 5);

        testpiece.makeMove(testpiece, 4, 4);
        assertEquals("Tests capture move on a pawn (Same side)", false, testpiece.isLegalCaptureMove(1, 4));
        assertEquals("Tests capture move on a queen (Different side)", true, testpiece.isLegalCaptureMove(5, 5));
        assertEquals("Tests a non-Bishop move", false, testpiece.isLegalCaptureMove(7, 5));
        assertEquals("Tests a 'legal' move, but the space is empty", false, testpiece.isLegalCaptureMove(4, 4));

        //test legal isLegalCaptureMove
        testCap3Piece.makeMove(testCap3Piece, 5, 1);
        testCap3Piece.makeMove(testCap3Piece, 1, 1);
        assertEquals("Tests a legal up, left capture move", true, testpiece.isLegalCaptureMove(1, 1));
        testCap3Piece.makeMove(testCap3Piece, 7, 1);
        assertEquals("Tests a legal down, left capture move", true, testpiece.isLegalCaptureMove(7, 1));
        testCap3Piece.makeMove(testCap3Piece, 7, 7);
        assertEquals("Tests a legal down, right capture move", true, testpiece.isLegalCaptureMove(7, 7));
        testCap3Piece.makeMove(testCap3Piece, 1, 7);
        assertEquals("Tests a legal up, right capture move", true, testpiece.isLegalCaptureMove(1, 7));

        //Tests is legal move
        assertEquals("LegalMove for a legal Non Cap Move", true, testpiece.isLegalMove(5, 5));
        assertEquals("LegalMove for a legal Cap Move", true, testpiece.isLegalMove(1, 7));
        assertEquals("LegalMove for an illegal Move", false, testpiece.isLegalMove(7, 0));

        //Test MakeMove
        assertEquals("MakeMove for a legal Move", true, testpiece.makeMove(testpiece, 5, 5));
        assertEquals("MakeMove for an illegal Move", false, testpiece.makeMove(testpiece, 4, 0));

        //Test MoveDone (which returns void, so I'm getting EC's turn to make sure that occured.
        testpiece.moveDone();
        assertEquals("Getting the turn to confirm moveDone", ChessGame.Side.NORTH, EuropeanChess.getTurn());
    }

    @Test
    public void testQueenPiece() {
        //Board to test on
        ChessBoard board = new SwingChessBoard(8, 8, new SwingEuropeanChessDisplay(), new EuropeanChess());
        //Initializes testpiece, a knight to test with
        ChessPiece testpiece = new QueenPiece(board, ChessGame.Side.NORTH, "Q", null, 4, 4);
        board.addPiece(testpiece, 4, 4);

        //These getters are checking the constructor for my Queen Piece

        //Makes sure the returned board is what I created, 'board'
        assertEquals("Getter for the chess board", board, testpiece.getChessBoard());

        //Makes sure the returned side is what it should be (North)
        assertEquals("Getter for the piece side", ChessGame.Side.NORTH, testpiece.getSide());

        //Makes sure the returned Label is what it should be ("Q")
        assertEquals("Getter for the Label", "Q", testpiece.getLabel());

        //Makes sure the returned Icon is what it should be (null)
        assertEquals("Getter for the icon", null, testpiece.getIcon());

        //Makes sure the returned row is what it should be (0)
        assertEquals("Getter for the row", 4, testpiece.getRow());

        //Makes sure the returned column is what it should be (1)
        assertEquals("Getter for the column", 4, testpiece.getColumn());


        //True Tests for the queen piece's isLegalNonCaptureMove
        assertEquals("Tests a legal down, right move", true, testpiece.isLegalNonCaptureMove(5, 5));
        assertEquals("Tests a legal up, left move", true, testpiece.isLegalNonCaptureMove(3, 3));
        assertEquals("Tests a legal up right move", true, testpiece.isLegalNonCaptureMove(3, 5));
        assertEquals("Tests a legal down, left move", true, testpiece.isLegalNonCaptureMove(5, 3));
        assertEquals("Tests a legal up move", true, testpiece.isLegalNonCaptureMove(2, 4));
        assertEquals("Tests a legal left", true, testpiece.isLegalNonCaptureMove(4, 2));
        assertEquals("Tests a legal down move", true, testpiece.isLegalNonCaptureMove(6, 4));
        assertEquals("Tests a legal right move", true, testpiece.isLegalNonCaptureMove(4, 6));

        //False Tests for isLegalNonCaptureMove
        ChessPiece testCapPiece = new QueenPiece(board, ChessGame.Side.NORTH, "Q", null, 7, 7);
        board.addPiece(testCapPiece, 7, 7);
        ChessPiece testCap2Piece = new QueenPiece(board, ChessGame.Side.SOUTH, "Q", null, 0, 0);
        board.addPiece(testCap2Piece, 0, 0);

        assertEquals("Tests capture move on a queen (Same side)", false, testpiece.isLegalNonCaptureMove(7, 7));
        assertEquals("Tests capture move on a queen (Different side)", false, testpiece.isLegalNonCaptureMove(0, 0));
        assertEquals("Tests a non queen move", false, testpiece.isLegalNonCaptureMove(5, 6));

        //Testing loop that checks if there's a piece between
        testpiece.makeMove(testpiece, 3, 3);
        testCapPiece.makeMove(testCapPiece, 5, 5);

        assertEquals("Tests with one piece between here and intended space", false, testpiece.isLegalNonCaptureMove(7, 7));

        testCapPiece.makeMove(testCapPiece, 4, 4);
        assertEquals("Tests with one piece in first space between the two", false, testpiece.isLegalNonCaptureMove(7, 7));

        testCapPiece.makeMove(testCapPiece, 6, 6);
        assertEquals("Tests with one piece in last space between the two", false, testpiece.isLegalNonCaptureMove(7, 7));

        testCap2Piece.makeMove(testCap2Piece, 5, 5);
        assertEquals("Tests with many pieces in between the two", false, testpiece.isLegalNonCaptureMove(7, 7));


        assertEquals("Tests capture move on a queen (Same side)", false, testpiece.isLegalCaptureMove(6, 6));
        assertEquals("Tests capture move on a queen (Different side)", true, testpiece.isLegalCaptureMove(0, 0));
        assertEquals("Tests a non-queen move", false, testpiece.isLegalCaptureMove(7, 5));
        assertEquals("Tests a 'legal' move, but the space is empty", false, testpiece.isLegalCaptureMove(3, 3));

        //test legal isLegalCaptureMove
        testCap2Piece.makeMove(testCap2Piece, 1, 1);
        assertEquals("Tests a legal up, left capture move", true, testpiece.isLegalCaptureMove(1, 1));
        testCap2Piece.makeMove(testCap2Piece, 1, 3);
        assertEquals("Tests a legal up capture move", true, testpiece.isLegalCaptureMove(1, 3));
        testCap2Piece.makeMove(testCap2Piece, 1, 5);
        assertEquals("Tests a legal up, right capture move", true, testpiece.isLegalCaptureMove(1, 5));
        testCap2Piece.makeMove(testCap2Piece, 3, 5);
        assertEquals("Tests a legal right capture move", true, testpiece.isLegalCaptureMove(3, 5));
        testCap2Piece.makeMove(testCap2Piece, 5, 5);
        assertEquals("Tests a legal down, right capture move", true, testpiece.isLegalCaptureMove(5, 5));
        testCap2Piece.makeMove(testCap2Piece, 5, 3);
        assertEquals("Tests a legal down capture move", true, testpiece.isLegalCaptureMove(5, 3));
        testCap2Piece.makeMove(testCap2Piece, 5, 1);
        assertEquals("Tests a legal down, left capture move", true, testpiece.isLegalCaptureMove(5, 1));
        testCap2Piece.makeMove(testCap2Piece, 3, 1);
        assertEquals("Tests a legal down capture move", true, testpiece.isLegalCaptureMove(3, 1));

        //Tests is legal move
        assertEquals("LegalMove for a legal Non Cap Move", true, testpiece.isLegalMove(4, 4));
        assertEquals("LegalMove for a legal Cap Move", true, testpiece.isLegalMove(3, 1));
        assertEquals("LegalMove for an illegal Move", false, testpiece.isLegalMove(7, 0));

        //Test MakeMove
        assertEquals("MakeMove for a legal Move", true, testpiece.makeMove(testpiece, 5, 5));
        assertEquals("MakeMove for an illegal Move", false, testpiece.makeMove(testpiece, 4, 0));

        //Test MoveDone (which returns void, so I'm getting EC's turn to make sure that occured.
        testpiece.moveDone();
        assertEquals("Getting the turn to confirm moveDone", ChessGame.Side.NORTH, EuropeanChess.getTurn());

    }

    @Test
    public void testKingPiece() {
        //Board to test on
        ChessBoard board = new SwingChessBoard(8, 8, new SwingEuropeanChessDisplay(), new EuropeanChess());
        //Initializes testpiece, a king to test with
        ChessPiece testpiece = new KingPiece(board, ChessGame.Side.NORTH, "K", null, 4, 4);
        board.addPiece(testpiece, 4, 4);

        //These getters are checking the constructor for my King Piece

        //Makes sure the returned board is what I created, 'board'
        assertEquals("Getter for the chess board", board, testpiece.getChessBoard());

        //Makes sure the returned side is what it should be (North)
        assertEquals("Getter for the piece side", ChessGame.Side.NORTH, testpiece.getSide());

        //Makes sure the returned Label is what it should be ("K")
        assertEquals("Getter for the Label", "K", testpiece.getLabel());

        //Makes sure the returned Icon is what it should be (null)
        assertEquals("Getter for the icon", null, testpiece.getIcon());

        //Makes sure the returned row is what it should be (0)
        assertEquals("Getter for the row", 4, testpiece.getRow());

        //Makes sure the returned column is what it should be (1)
        assertEquals("Getter for the column", 4, testpiece.getColumn());

        //True Tests for the king piece's isLegalNonCaptureMove
        assertEquals("Tests a legal down, right move", true, testpiece.isLegalNonCaptureMove(5, 5));
        assertEquals("Tests a legal up, left move", true, testpiece.isLegalNonCaptureMove(3, 3));
        assertEquals("Tests a legal up right move", true, testpiece.isLegalNonCaptureMove(3, 5));
        assertEquals("Tests a legal down, left move", true, testpiece.isLegalNonCaptureMove(5, 3));
        assertEquals("Tests a legal up move", true, testpiece.isLegalNonCaptureMove(3, 4));
        assertEquals("Tests a legal left", true, testpiece.isLegalNonCaptureMove(4, 3));
        assertEquals("Tests a legal down move", true, testpiece.isLegalNonCaptureMove(5, 4));
        assertEquals("Tests a legal right move", true, testpiece.isLegalNonCaptureMove(4, 5));

        //False Tests for isLegalNonCaptureMove
        ChessPiece testCapPiece = new QueenPiece(board, ChessGame.Side.NORTH, "Q", null, 3, 3);
        board.addPiece(testCapPiece, 3, 3);
        ChessPiece testCap2Piece = new QueenPiece(board, ChessGame.Side.SOUTH, "Q", null, 5, 5);
        board.addPiece(testCap2Piece, 5, 5);

        //assertEquals("Tests capture move on a queen (Same side)", false, testpiece.isLegalNonCaptureMove(3,3));
        //assertEquals("Tests capture move on a queen (Different side)", false, testpiece.isLegalNonCaptureMove(5,5));
        assertEquals("Tests a non king move", false, testpiece.isLegalNonCaptureMove(5, 7));

        assertEquals("Tests capture move on a queen (Same side)", false, testpiece.isLegalCaptureMove(3, 3));
        assertEquals("Tests capture move on a queen (Different side)", true, testpiece.isLegalCaptureMove(5, 5));
        assertEquals("Tests a non-king move", false, testpiece.isLegalCaptureMove(7, 5));
        assertEquals("Tests a 'legal' move, but the space is empty", false, testpiece.isLegalCaptureMove(4, 3));

        //True Tests for the king piece's isLegalCaptureMove
        //Getting this piece out of the way
        testCapPiece.makeMove(testCapPiece, 0, 0);

        assertEquals("Tests a legal down, right capture move", true, testpiece.isLegalCaptureMove(5, 5));
        testCap2Piece.makeMove(testCap2Piece, 4, 5);
        assertEquals("Tests a legal right capture move", true, testpiece.isLegalCaptureMove(4, 5));
        testCap2Piece.makeMove(testCap2Piece, 3, 5);
        assertEquals("Tests a legal up right capture move", true, testpiece.isLegalCaptureMove(3, 5));
        testCap2Piece.makeMove(testCap2Piece, 3, 4);
        assertEquals("Tests a legal up capture move", true, testpiece.isLegalCaptureMove(3, 4));
        testCap2Piece.makeMove(testCap2Piece, 3, 3);
        assertEquals("Tests a legal up, left capture move", true, testpiece.isLegalCaptureMove(3, 3));
        testCap2Piece.makeMove(testCap2Piece, 4, 3);
        assertEquals("Tests a legal left capture ", true, testpiece.isLegalCaptureMove(4, 3));
        testCap2Piece.makeMove(testCap2Piece, 5, 3);
        assertEquals("Tests a legal down, left capture move", true, testpiece.isLegalCaptureMove(5, 3));
        testCap2Piece.makeMove(testCap2Piece, 5, 4);
        assertEquals("Tests a legal down capture move", true, testpiece.isLegalCaptureMove(5, 4));

        //Tests is legal move
        assertEquals("LegalMove for a legal Non Cap Move", true, testpiece.isLegalMove(4, 5));
        assertEquals("LegalMove for a legal Cap Move", true, testpiece.isLegalMove(5, 4));
        assertEquals("LegalMove for an illegal Move", false, testpiece.isLegalMove(7, 0));

        //Test MakeMove
        assertEquals("MakeMove for a legal Move", true, testpiece.makeMove(testpiece, 5, 5));
        assertEquals("MakeMove for an illegal Move", false, testpiece.makeMove(testpiece, 4, 0));

        //Test MoveDone (which returns void, so I'm getting EC's turn to make sure that occured.)
        testpiece.moveDone();
        assertEquals("Getting the turn to confirm moveDone", ChessGame.Side.NORTH, EuropeanChess.getTurn());

    }

    @Test
    public void testPawnPiece() {
        //Board to test on
        ChessBoard board = new SwingChessBoard(8, 8, new SwingEuropeanChessDisplay(), new EuropeanChess());
        //Initializes testpiece, a pawn to test with
        ChessPiece testpieceN = new PawnPiece(board, ChessGame.Side.NORTH, "P", null, 0, 4);
        board.addPiece(testpieceN, 0, 4);

        //These getters are checking the constructor for my Pawn Piece

        //Makes sure the returned board is what I created, 'board'
        assertEquals("Getter for the chess board", board, testpieceN.getChessBoard());

        //Makes sure the returned side is what it should be (North)
        assertEquals("Getter for the piece side", ChessGame.Side.NORTH, testpieceN.getSide());

        //Makes sure the returned Label is what it should be ("P")
        assertEquals("Getter for the Label", "P", testpieceN.getLabel());

        //Makes sure the returned Icon is what it should be (null)
        assertEquals("Getter for the icon", null, testpieceN.getIcon());

        //Makes sure the returned row is what it should be (0)
        assertEquals("Getter for the row", 0, testpieceN.getRow());

        //Makes sure the returned column is what it should be (1)
        assertEquals("Getter for the column", 4, testpieceN.getColumn());

        //Testing the north side piece's functions

        //Testing is legal move
        assertEquals("Tests first move by 2", true, testpieceN.isLegalNonCaptureMove(2, 4));
        assertEquals("Tests first move by 1", true, testpieceN.isLegalNonCaptureMove(1, 4));
        //Moving it so I can test the non-first moves
        testpieceN.makeMove(testpieceN, 1, 4);
        assertEquals("Tests second move by 2", false, testpieceN.isLegalNonCaptureMove(3, 4));
        assertEquals("Tests a move by 1", true, testpieceN.isLegalNonCaptureMove(2, 4));

        //Testing capture moves on isLegalCaptureMove
        ChessPiece testCapPiece = new QueenPiece(board, ChessGame.Side.NORTH, "Q", null, 2, 5);
        board.addPiece(testCapPiece, 2, 5);
        ChessPiece testCap2Piece = new QueenPiece(board, ChessGame.Side.SOUTH, "Q", null, 2, 3);
        board.addPiece(testCap2Piece, 2, 3);

        //False tests for isLegalCaptureMove
        assertEquals("Tests capture move on a queen (Same side)", false, testpieceN.isLegalCaptureMove(2, 5));
        assertEquals("Tests capture move on a queen (Different side)", true, testpieceN.isLegalCaptureMove(2, 3));
        assertEquals("Tests a non pawn move", false, testpieceN.isLegalNonCaptureMove(5, 7));

        //Tests is legal move
        assertEquals("LegalMove for a legal Non Cap Move", true, testpieceN.isLegalMove(2, 4));
        assertEquals("LegalMove for a legal Cap Move", true, testpieceN.isLegalMove(2, 3));
        assertEquals("LegalMove for an illegal Move", false, testpieceN.isLegalMove(7, 0));

        //Test MakeMove
        assertEquals("MakeMove for a legal Move", true, testpieceN.makeMove(testpieceN, 2, 4));
        assertEquals("MakeMove for an illegal Move", false, testpieceN.makeMove(testpieceN, 4, 0));

        //Test MoveDone (which returns void, so I'm getting EC's turn to make sure that occured.
        testpieceN.moveDone();
        assertEquals("Getting the turn to confirm moveDone", ChessGame.Side.NORTH, EuropeanChess.getTurn());


        //Testing the south side piece's functions
        ChessPiece testpieceS = new PawnPiece(board, ChessGame.Side.SOUTH, "P", null, 7, 4);
        board.addPiece(testpieceS, 7, 4);
        //Testing is legal move
        assertEquals("Tests first move by 2", true, testpieceS.isLegalNonCaptureMove(5, 4));
        assertEquals("Tests first move by 1", true, testpieceS.isLegalNonCaptureMove(6, 4));
        //Moving it so I can test the non-first moves
        testpieceS.makeMove(testpieceS, 6, 4);
        assertEquals("Tests second move by 2", false, testpieceS.isLegalNonCaptureMove(4, 4));
        assertEquals("Tests a move by 1", true, testpieceS.isLegalNonCaptureMove(5, 4));

        //Testing capture moves on isLegalCaptureMove
        testCapPiece.makeMove(testCapPiece, 5, 5);
        testCap2Piece.makeMove(testCap2Piece, 5, 3);

        //False tests for isLegalCaptureMove
        assertEquals("Tests capture move on a queen (Same side)", false, testpieceS.isLegalCaptureMove(5, 3));
        assertEquals("Tests capture move on a queen (Different side)", true, testpieceS.isLegalCaptureMove(5, 5));
        assertEquals("Tests a non pawn move", false, testpieceS.isLegalNonCaptureMove(0, 7));

        //Tests is legal move
        assertEquals("LegalMove for a legal Non Cap Move", true, testpieceS.isLegalMove(5, 4));
        assertEquals("LegalMove for a legal Cap Move", true, testpieceS.isLegalMove(5, 5));
        assertEquals("LegalMove for an illegal Move", false, testpieceS.isLegalMove(0, 0));

        //Test MakeMove
        assertEquals("MakeMove for a legal Move", true, testpieceS.makeMove(testpieceS, 5, 4));
        assertEquals("MakeMove for an illegal Move", false, testpieceS.makeMove(testpieceS, 3, 0));

        //Test MoveDone (which returns void, so I'm getting EC's turn to make sure that occured.
        testpieceS.moveDone();
        assertEquals("Getting the turn to confirm moveDone", ChessGame.Side.SOUTH, EuropeanChess.getTurn());


        //Testing the east side piece's functions
        ChessPiece testpieceE = new PawnPiece(board, ChessGame.Side.EAST, "P", null, 4, 7);
        board.addPiece(testpieceE, 4, 7);
        //Testing is legal move
        assertEquals("Tests first move by 2", true, testpieceE.isLegalNonCaptureMove(4, 5));
        assertEquals("Tests first move by 1", true, testpieceE.isLegalNonCaptureMove(4, 6));
        //Moving it so I can test the non-first moves
        testpieceE.makeMove(testpieceE, 4, 6);
        assertEquals("Tests second move by 2", false, testpieceE.isLegalNonCaptureMove(4, 4));
        assertEquals("Tests a move by 1", true, testpieceE.isLegalNonCaptureMove(4, 5));

        //Testing capture moves on isLegalCaptureMove
        testCapPiece.makeMove(testCapPiece, 5, 5);
        board.removePiece(3, 5);
        ChessPiece testCap3Piece = new QueenPiece(board, ChessGame.Side.EAST, "Q", null, 3, 5);
        board.addPiece(testCap3Piece, 3, 5);

        //False tests for isLegalCaptureMove
        assertEquals("Tests capture move on a queen (Same side)", false, testpieceE.isLegalCaptureMove(3, 5));
        assertEquals("Tests capture move on a queen (Different side)", true, testpieceE.isLegalCaptureMove(5, 5));
        assertEquals("Tests a non pawn move", false, testpieceE.isLegalNonCaptureMove(0, 7));

        //Tests is legal move
        assertEquals("LegalMove for a legal Non Cap Move", true, testpieceE.isLegalMove(4, 5));
        assertEquals("LegalMove for a legal Cap Move", true, testpieceE.isLegalMove(5, 5));
        assertEquals("LegalMove for an illegal Move", false, testpieceE.isLegalMove(0, 0));

        //Test MakeMove
        assertEquals("MakeMove for a legal Move", true, testpieceE.makeMove(testpieceE, 4, 5));
        assertEquals("MakeMove for an illegal Move", false, testpieceE.makeMove(testpieceE, 3, 0));

        //Test MoveDone (which returns void, so I'm getting EC's turn to make sure that occured.
        testpieceE.moveDone();
        assertEquals("Getting the turn to confirm moveDone", ChessGame.Side.EAST, EuropeanChess.getTurn());

        //Testing the west side piece's functions

        //Initializes testpieceW, a pawn to test with
        ChessPiece testpieceW = new PawnPiece(board, ChessGame.Side.WEST, "P", null, 4, 0);
        board.addPiece(testpieceW, 4, 0);

        //Testing is legal move
        assertEquals("Tests first move by 2", true, testpieceW.isLegalNonCaptureMove(4, 2));
        assertEquals("Tests first move by 1", true, testpieceW.isLegalNonCaptureMove(4, 1));
        //Moving it so I can test the non-first moves
        testpieceW.makeMove(testpieceW, 4, 1);
        assertEquals("Tests second move by 2", false, testpieceW.isLegalNonCaptureMove(4, 3));
        assertEquals("Tests a move by 1", true, testpieceW.isLegalNonCaptureMove(4, 2));

        //Testing capture moves on isLegalCaptureMove
        ChessPiece testCap4Piece = new QueenPiece(board, ChessGame.Side.WEST, "Q", null, 5, 2);
        board.addPiece(testCap4Piece, 5, 2);
        ChessPiece testCap5Piece = new QueenPiece(board, ChessGame.Side.EAST, "Q", null, 3, 2);
        board.addPiece(testCap5Piece, 3, 2);

        //False tests for isLegalCaptureMove
        assertEquals("Tests capture move on a queen (Same side)", false, testpieceW.isLegalCaptureMove(5, 2));
        assertEquals("Tests capture move on a queen (Different side)", true, testpieceW.isLegalCaptureMove(3, 2));
        assertEquals("Tests a non pawn move", false, testpieceW.isLegalNonCaptureMove(5, 7));

        //Tests is legal move
        assertEquals("LegalMove for a legal Non Cap Move", true, testpieceW.isLegalMove(4, 2));
        assertEquals("LegalMove for a legal Cap Move", true, testpieceW.isLegalMove(3, 2));
        assertEquals("LegalMove for an illegal Move", false, testpieceW.isLegalMove(0, 7));

        //Test MakeMove
        assertEquals("MakeMove for a legal Move", true, testpieceW.makeMove(testpieceW, 4, 2));
        assertEquals("MakeMove for an illegal Move", false, testpieceW.makeMove(testpieceW, 0, 4));

        //Test MoveDone (which returns void, so I'm getting EC's turn to make sure that occured.
        testpieceW.moveDone();
        assertEquals("Getting the turn to confirm moveDone", ChessGame.Side.WEST, EuropeanChess.getTurn());

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


  