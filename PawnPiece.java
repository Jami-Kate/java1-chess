/*Jami Biddle
 * Class for a Pawn Piece
 */

public class PawnPiece extends ChessPiece {

    //Stores whether the pawn has been swapped out
    private boolean swapped = false;

    //Setter for swapped
    public void setSwapped(boolean a) {
        this.swapped = a;
    }

    //Getter for swapped (and it's a fun name)
    public boolean getSwapped() {
        return this.swapped;
    }

    //Constructor for pawn pieces, calls ChessPiece's constructor
    public PawnPiece(ChessBoard a, ChessGame.Side side, String label, Object icon, int row, int column) {
        super(a, side, "P", null, row, column);
    }

    //Checks for legal non captue moves, which is directionally based.
    public boolean isLegalNonCaptureMove(int row, int column) {

        //Makes sure the space is empty
        if (this.getChessBoard().hasPiece(row, column))
            return false;

        //Checks for legal moves if the piece is on the north side
        if (this.getSide() == ChessGame.Side.NORTH) {
            //Checks if the first move has been made, if not, allows it to be 2 away
            if (this.getFirstMove() == false) {
                if ((this.getColumn() - column) == 0 && (this.getRow() - row) == -2) {
                    return true;
                }
            }
            //Else, it checks if it is only one down (from the north)
            if ((this.getColumn() - column) == 0 && (this.getRow() - row) == -1) {
                return true;
            }
        }

        //Checks for legal moves if the piece is on the south side
        if (this.getSide() == ChessGame.Side.SOUTH) {
            //Checks if the first move has been made, if not, allows it to be 2 away
            if (this.getFirstMove() == false) {
                if ((this.getColumn() - column) == 0 && (this.getRow() - row) == 2) {
                    return true;
                }
            }
            //Else, it checks if it is only one up (from the south)
            if ((this.getColumn() - column) == 0 && (this.getRow() - row) == 1) {
                return true;
            }
        }

        //Checks for legal moves if the piece is on the east side
        if (this.getSide() == ChessGame.Side.EAST) {
            //Checks if the first move has been made, if not, allows it to be 2 away
            if (this.getFirstMove() == false) {
                if ((this.getColumn() - column) == 2 && (this.getRow() - row) == 0) {
                    return true;
                }
            }
            //Else, it checks if it is only one to the left (from the east)
            if ((this.getColumn() - column) == 1 && (this.getRow() - row) == 0) {
                return true;
            }
        }

        //Checks for legal moves if the piece is on the west side
        if (this.getSide() == ChessGame.Side.WEST) {
            //Checks if the first move has been made, if not, allows it to be 2 away
            if (this.getFirstMove() == false) {
                if ((this.getColumn() - column) == -2 && (this.getRow() - row) == 0) {
                    return true;
                }
            }
            //Else, it checks if it is only one to the right (from the west)
            if ((this.getColumn() - column) == -1 && (this.getRow() - row) == 0) {
                return true;
            }
        }
        //If none work, the the move isn't legal
        return false;
    }

    //Checks if it's a legal capture move
    public boolean isLegalCaptureMove(int row, int column) {
        //Makes sure there's a piece in the spot not on the same team
        if (!(this.getChessBoard().hasPiece(row, column)) || this.getChessBoard().getPiece(row, column).getSide() == this.getSide())
            return false;

        //Checks legality of the move (both diagonals) for north
        if (this.getSide() == ChessGame.Side.NORTH) {
            if (((this.getColumn() - column) == 1 || (this.getColumn() - column) == -1) && (this.getRow() - row) == -1)
                return true;
        }

        //Checks legality of the move (both diagonals) for south
        if (this.getSide() == ChessGame.Side.SOUTH) {
            if (((this.getColumn() - column) == 1 || (this.getColumn() - column) == -1) && (this.getRow() - row) == 1)
                return true;
        }

        //Checks legality of the move (both diagonals) for east
        if (this.getSide() == ChessGame.Side.EAST) {
            if (((this.getRow() - row) == 1 || (this.getRow() - row) == -1) && (this.getColumn() - column) == 1)
                return true;
        }

        //Checks legality of the move (both diagonals) for west
        if (this.getSide() == ChessGame.Side.WEST) {
            if (((this.getRow() - row) == 1 || (this.getRow() - row) == -1) && (this.getColumn() - column) == -1)
                return true;
        }
        //If the above conditions aren't met, it's not a legal move.
        return false;
    }

    //Executes a swap of the pieces at the end of the board, to any non king piece
    public void pawnSwap() {
        //Checks if it's a north piece at the opposite end
        if ((this.getSide() == ChessGame.Side.NORTH) && (this.getRow() == 7)) {
            //Prompts the user for an input, with the examples of how to capitalize etc. (Title Case)
            String newPiece = javax.swing.JOptionPane.showInputDialog("Queen, Bishop, Knight, or Rook?");
            //Checks if the input is for a queen
            if (newPiece.equals("Queen")) {
                //Replaces the pawn with a new queen piece
                ChessPiece newQueen = new QueenPiece(this.getChessBoard(), ChessGame.Side.NORTH, "Q", null, this.getRow(), this.getColumn());
                this.getChessBoard().removePiece(this.getRow(), this.getColumn());
                this.getChessBoard().addPiece(newQueen, this.getRow(), this.getColumn());
                this.setSwapped(true);
            }

            //Checks if the input is for a bishop
            if (newPiece.equals("Bishop")) {
                //Replaces the pawn with a new bishop piece
                this.getChessBoard().removePiece(this.getRow(), this.getColumn());
                ChessPiece newBishop = new BishopPiece(this.getChessBoard(), ChessGame.Side.NORTH, "B", null, this.getRow(), this.getColumn());
                this.getChessBoard().addPiece(newBishop, this.getRow(), this.getColumn());
                this.setSwapped(true);
            }

            //Checks if the input is for a knight
            if (newPiece.equals("Knight")) {
                //Replaces the pawn with a new knight piece
                this.getChessBoard().removePiece(this.getRow(), this.getColumn());
                ChessPiece newKnight = new KnightPiece(this.getChessBoard(), ChessGame.Side.NORTH, "K", null, this.getRow(), this.getColumn());
                this.getChessBoard().addPiece(newKnight, this.getRow(), this.getColumn());
                this.setSwapped(true);
            }

            //Checks if the input is for a rook
            if (newPiece.equals("Rook")) {
                //Replaces the pawn with a new rook piece
                this.getChessBoard().removePiece(this.getRow(), this.getColumn());
                ChessPiece newRook = new RookPiece(this.getChessBoard(), ChessGame.Side.NORTH, "R", null, this.getRow(), this.getColumn());
                this.getChessBoard().addPiece(newRook, this.getRow(), this.getColumn());
                this.setSwapped(true);
            }

        }

        //Checks if it's a south piece at the opposite end
        if ((this.getSide() == ChessGame.Side.SOUTH) && (this.getRow() == 0)) {
            //Prompts the user for an input, with the examples of how to capitalize etc. (Title Case)
            String newPiece = javax.swing.JOptionPane.showInputDialog("Queen, Bishop, Knight, or Rook?");
            //Checks if the input is for a queen
            if (newPiece.equals("Queen")) {
                //Replaces the pawn with a new queen piece
                ChessPiece newQueen = new QueenPiece(this.getChessBoard(), ChessGame.Side.SOUTH, "Q", null, this.getRow(), this.getColumn());
                this.getChessBoard().removePiece(this.getRow(), this.getColumn());
                this.getChessBoard().addPiece(newQueen, this.getRow(), this.getColumn());
                this.setSwapped(true);
            }

            //Checks if the input is for a bishop
            if (newPiece.equals("Bishop")) {
                //Replaces the pawn with a new bishop piece
                this.getChessBoard().removePiece(this.getRow(), this.getColumn());
                ChessPiece newBishop = new BishopPiece(this.getChessBoard(), ChessGame.Side.SOUTH, "B", null, this.getRow(), this.getColumn());
                this.getChessBoard().addPiece(newBishop, this.getRow(), this.getColumn());
                this.setSwapped(true);
            }

            //Checks if the input is for a knight
            if (newPiece.equals("Knight")) {
                //Replaces the pawn with a new knight piece
                this.getChessBoard().removePiece(this.getRow(), this.getColumn());
                ChessPiece newKnight = new KnightPiece(this.getChessBoard(), ChessGame.Side.SOUTH, "K", null, this.getRow(), this.getColumn());
                this.getChessBoard().addPiece(newKnight, this.getRow(), this.getColumn());
                this.setSwapped(true);
            }

            //Checks if the input is for a rook
            if (newPiece.equals("Rook")) {
                //Replaces the pawn with a new rook piece
                this.getChessBoard().removePiece(this.getRow(), this.getColumn());
                ChessPiece newRook = new RookPiece(this.getChessBoard(), ChessGame.Side.SOUTH, "R", null, this.getRow(), this.getColumn());
                this.getChessBoard().addPiece(newRook, this.getRow(), this.getColumn());
                this.setSwapped(true);
            }

        }

        //Checks if it's an east piece at the opposite end
        if ((this.getSide() == ChessGame.Side.EAST) && (this.getColumn() == 0)) {
            //Prompts the user for an input, with the examples of how to capitalize etc. (Title Case)
            String newPiece = javax.swing.JOptionPane.showInputDialog("Queen, Bishop, Knight, or Rook?");
            //Checks if the input is for a queen
            if (newPiece.equals("Queen")) {
                //Replaces the pawn with a new queen piece
                ChessPiece newQueen = new QueenPiece(this.getChessBoard(), ChessGame.Side.EAST, "Q", null, this.getRow(), this.getColumn());
                this.getChessBoard().removePiece(this.getRow(), this.getColumn());
                this.getChessBoard().addPiece(newQueen, this.getRow(), this.getColumn());
                this.setSwapped(true);
            }

            //Checks if the input is for a bishop
            if (newPiece.equals("Bishop")) {
                //Replaces the pawn with a new bishop piece
                this.getChessBoard().removePiece(this.getRow(), this.getColumn());
                ChessPiece newBishop = new BishopPiece(this.getChessBoard(), ChessGame.Side.EAST, "B", null, this.getRow(), this.getColumn());
                this.getChessBoard().addPiece(newBishop, this.getRow(), this.getColumn());
                this.setSwapped(true);
            }

            //Checks if the input is for a knight
            if (newPiece.equals("Knight")) {
                //Replaces the pawn with a new knight piece
                this.getChessBoard().removePiece(this.getRow(), this.getColumn());
                ChessPiece newKnight = new KnightPiece(this.getChessBoard(), ChessGame.Side.EAST, "K", null, this.getRow(), this.getColumn());
                this.getChessBoard().addPiece(newKnight, this.getRow(), this.getColumn());
                this.setSwapped(true);
            }

            //Checks if the input is for a rook
            if (newPiece.equals("Rook")) {
                //Replaces the pawn with a new rook piece
                this.getChessBoard().removePiece(this.getRow(), this.getColumn());
                ChessPiece newRook = new RookPiece(this.getChessBoard(), ChessGame.Side.EAST, "R", null, this.getRow(), this.getColumn());
                this.getChessBoard().addPiece(newRook, this.getRow(), this.getColumn());
                this.setSwapped(true);
            }

        }

        //Checks if it's a west piece at the opposite end
        if ((this.getSide() == ChessGame.Side.WEST) && (this.getColumn() == 7)) {
            //Prompts the user for an input, with the examples of how to capitalize etc. (Title Case)
            String newPiece = javax.swing.JOptionPane.showInputDialog("Queen, Bishop, Knight, or Rook?");
            //Checks if the input is for a queen
            if (newPiece.equals("Queen")) {
                //Replaces the pawn with a new queen piece
                ChessPiece newQueen = new QueenPiece(this.getChessBoard(), ChessGame.Side.WEST, "Q", null, this.getRow(), this.getColumn());
                this.getChessBoard().removePiece(this.getRow(), this.getColumn());
                this.getChessBoard().addPiece(newQueen, this.getRow(), this.getColumn());
                this.setSwapped(true);
            }

            //Checks if the input is for a bishop
            if (newPiece.equals("Bishop")) {
                //Replaces the pawn with a new bishop piece
                this.getChessBoard().removePiece(this.getRow(), this.getColumn());
                ChessPiece newBishop = new BishopPiece(this.getChessBoard(), ChessGame.Side.WEST, "B", null, this.getRow(), this.getColumn());
                this.getChessBoard().addPiece(newBishop, this.getRow(), this.getColumn());
                this.setSwapped(true);
            }

            //Checks if the input is for a knight
            if (newPiece.equals("Knight")) {
                //Replaces the pawn with a new knight piece
                this.getChessBoard().removePiece(this.getRow(), this.getColumn());
                ChessPiece newKnight = new KnightPiece(this.getChessBoard(), ChessGame.Side.WEST, "K", null, this.getRow(), this.getColumn());
                this.getChessBoard().addPiece(newKnight, this.getRow(), this.getColumn());
                this.setSwapped(true);
            }

            //Checks if the input is for a rook
            if (newPiece.equals("Rook")) {
                //Replaces the pawn with a new rook piece
                this.getChessBoard().removePiece(this.getRow(), this.getColumn());
                ChessPiece newRook = new RookPiece(this.getChessBoard(), ChessGame.Side.WEST, "R", null, this.getRow(), this.getColumn());
                this.getChessBoard().addPiece(newRook, this.getRow(), this.getColumn());
                this.setSwapped(true);
            }

        }
    }

    //Overrides moveDone to include pawn swapping
    @Override
    public void moveDone() {
        //Makes sure first move is marked as done, switches the turn, initiates pawn swap if it is even slightly possible
        if (this.getFirstMove() == false)
            this.setFirstMove(true);
        EuropeanChess.setTurn(this.getSide());

        if ((this.getRow() == 7) || (this.getRow() == 0) || (this.getColumn() == 0) || (this.getColumn() == 7)) {
            if (!(this.getSwapped()))
                this.pawnSwap();
        }
    }
}
