/*Interface for all straight-moving pieces.
 * Jami Biddle
 * Programming Project 3
 */

public interface StraightMovingPiece {
    //Method stubs to be overridden
    boolean isLegalNonCaptureMove(int row, int column);

    boolean isLegalCaptureMove(int row, int column);

}
