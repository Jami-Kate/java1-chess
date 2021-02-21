import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.SwingUtilities;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * <p>Creates a chessboard in a window on the desktop.  The ChessBoard has a ChessBoardDisplay object that determines
 * how the individual squares of the chessboard should be drawn.</p>
 *
 * <p>The chessboard uses a ChessGame object to determine how the game should be played.  The way the chessboard works
 * is as follows.  The player selects a piece by clicking on the board, and
 * and the chessboard calls the <tt>legalPieceToPlay</tt> method of the ChessGame object.
 * If the player is allowed to select the piece, the board highlights it, and the player can select another square on
 * the board.  The chessboard then calls the <tt>makeMove</tt> method of the ChessGame object.  The ChessGame is
 * responsible for determining if the move is valid, and if it is to update the game and the chessboard
 * with the results of making that move.</p>
 *
 * @author Jami Biddle
 */
public interface ChessBoard {
  
  /**
   * Returns the rules of the game.
   *
   * @return ChessGame the rules of the game
   */
  public ChessGame getGameRules();
  
  public abstract void addPiece(ChessPiece piece, int row, int column);
  
  
  /**
   * Removes a piece from the board
   *
   * @param row the row of the piece
   * @param col the column of the piece
   * @return the piece removed of null if there was no piece at that square
   */
  public abstract ChessPiece removePiece(final int row, final int col);
  /**
   * Returns true if there is a piece at a specific location of the board.
   *
   * @param row the row to examine
   * @param col the column to examine
   * @return true if there is a piece a this row and column and false
   * if the square is empty
   */
  public boolean hasPiece(int row, int col);
  
  /**
   * Returns the chess piece at a specific location on the board.
   *
   * @param row the row for the piece
   * @param col the column for the piece
   * @return the piece at the row and column or null if there is no piece there.
   */
  public ChessPiece getPiece(int row, int col);
  
  
  /**
   * Returns true if a particular square is threatened by an opposing piece.
   *
   * @param row    the row of the square
   * @param column the column of the square
   * @param piece  a piece of the game
   * @return true if the square can be attacked by a piece of an opposing side as the parameter piece
   */
  public boolean squareThreatened(int row, int column, ChessPiece piece);
  
  /**
   * Returns the number of rows on a chess board
   *
   * @return int The number of rows on the chess board
   */
  public abstract int numRows();
  
  /**
   * Returns the number of columns on a chess board
   *
   * @return int The number of columns on the chess board
   */
  public abstract int numColumns();
}


