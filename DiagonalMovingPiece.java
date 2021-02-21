/*Interface for all diagonal-moving pieces.
 * Jami Biddle
 * Programming Project 3
 * I was told by a TA that this is for "organizational purposes"
 * and that i should only have method stubs here.
 */

public interface DiagonalMovingPiece {

    //Method stubs to be inherited.
    boolean isLegalNonCaptureMove(int row, int column);

    boolean isLegalCaptureMove(int row, int column);

}
