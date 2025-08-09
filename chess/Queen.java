package chess;
import java.util.ArrayList;
import chess.ReturnPiece.PieceFile;

/**
 * Write a description of class Queen here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Queen extends Piece
{
    Rook rook;
    Bishop bishop;
    public Queen(ReturnPiece r) {
        super(r);
        rook = new Rook(r);
        bishop = new Bishop(r);
    }
    
    public boolean ifValidMove(PieceFile file2, int rank2, ArrayList<Piece> piecesOnBoard) {
        if ((rook.ifValidMove(file2, rank2, piecesOnBoard) == true) || (bishop.ifValidMove(file2, rank2, piecesOnBoard) == true)) {
            return true;
        }
        return false;
    }
    
}
