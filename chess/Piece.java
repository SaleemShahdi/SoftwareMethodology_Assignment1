package chess;
import chess.ReturnPiece.PieceFile;


/**
 * Write a description of class Piece here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.ArrayList;
public abstract class Piece
{
    String color;
    ReturnPiece returnpiece;
    public Piece(ReturnPiece r) {
        this.returnpiece = r;
        this.setColor();
    }
    public void setColor() {
        String piecetypestring = returnpiece.pieceType.name();
        if (piecetypestring.charAt(0) == 'W') {
            this.color = "white";
        } else {
            this.color = "black";
        }
    }
    public abstract boolean ifValidMove(PieceFile file2, int rank2, ArrayList<Piece> piecesOnBoard);
    public boolean hasOpponentInCheck(ArrayList<Piece> piecesOnBoard) {
        String filerank = findOpposingKing(piecesOnBoard);
        String filestring = filerank.substring(0, 1);
        String rankstring = filerank.substring(1, 2);
        PieceFile file = PieceFile.valueOf(filestring);
        int rank = Integer.parseInt(rankstring);
        boolean b = ifValidMove(file, rank, piecesOnBoard);
        return b;
    }
    public boolean killsPiece(PieceFile file2, int rank2, ArrayList<Piece> piecesOnBoard) { // only called on a valid move
        Piece p = getPiece(file2, rank2, piecesOnBoard);
        if (p == null) {
            return false;
        }
        return true;
    }
    public Piece getPiece(PieceFile file, int rank, ArrayList<Piece> piecesOnBoard) {
        for (Piece piece: piecesOnBoard) {
            if ((piece.returnpiece.pieceFile == file) & (piece.returnpiece.pieceRank == rank)) {
                return piece;
            }
        }
        return null;
    }
    public String getFileRank() {
        return ""+returnpiece.pieceFile+returnpiece.pieceRank+"";
    }
    public static PieceFile incrementFile (PieceFile f) {
        switch (f) {
            case a:
                return PieceFile.b;
            case b:
                return PieceFile.c;
            case c:
                return PieceFile.d;
            case d:
                return PieceFile.e;
            case e:
                return PieceFile.f;
            case f:
                return PieceFile.g;
            case g:
                return PieceFile.h;
            case h:
                return null;
        }
        return null;
    }
    public static PieceFile decrementFile (PieceFile f) {
        switch (f) {
            case a:
                return null;
            case b:
                return PieceFile.a;
            case c:
                return PieceFile.b;
            case d:
                return PieceFile.c;
            case e:
                return PieceFile.d;
            case f:
                return PieceFile.e;
            case g:
                return PieceFile.f;
            case h:
                return PieceFile.g;
        }
        return null;
    }
    
    public String findOpposingKing(ArrayList<Piece> piecesOnBoard) {
        Piece p = null;
        String opposingColor;
        if (color.equals("white")) {
            opposingColor = "black";
        } else {
            opposingColor = "white";
        }
        for (Piece current: piecesOnBoard) {
            if (current.color.equals(opposingColor) && (current instanceof King)) {
                p = current;
                break;
            }
        }
        return p.getFileRank();
            
    }
    
    public boolean inCheck(ArrayList<Piece> piecesOnBoard) {
        return false;
    }
    
}
