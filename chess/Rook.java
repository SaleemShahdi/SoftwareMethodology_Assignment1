package chess;
import java.util.ArrayList;
import chess.ReturnPiece.PieceFile;

/**
 * Write a description of class Pawn here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Rook extends Piece
{
    public Rook(ReturnPiece r) {
        super(r);
    }

    public boolean ifValidMove(PieceFile file2, int rank2, ArrayList<Piece> piecesOnBoard) {
        if ((rank2 != returnpiece.pieceRank) && (file2 != returnpiece.pieceFile)) {
            return false;
        }
        if ((rank2 == returnpiece.pieceRank) && (file2 == returnpiece.pieceFile)) {
            return false;
        }
        if (returnpiece.pieceFile == file2) {
            int rankmin;
            int rankmax;
            if (rank2 < returnpiece.pieceRank) {
                rankmax = returnpiece.pieceRank - 1;
                rankmin = rankmax;
                Piece p = getPiece(file2, rankmin, piecesOnBoard);
                while (p == null && rankmin >= 2) {
                    rankmin = rankmin - 1;
                    p = getPiece(file2, rankmin, piecesOnBoard);
                }
                if (p == null && rankmin == 1) { // no pieces on the rook's path moving downward
                    return true;
                }
                if (rankmin == rankmax) {
                    if (p.color.equals(color)) {
                        return false;
                    } else {
                        if (rank2 == rankmin) {
                            return true;
                        } else {
                            return false;
                        }
                    }
                } else {
                    if (p.color.equals(color)) {
                        rankmin = rankmin + 1;
                    }
                    if (rank2 <= rankmax && rank2 >= rankmin) {
                        return true;
                    }
                    return false;
                }
            } else {
                rankmin = returnpiece.pieceRank + 1;
                rankmax = rankmin;
                Piece p = getPiece(file2, rankmax, piecesOnBoard);
                while (p == null && rankmax <= 7) {
                    rankmax = rankmax + 1;
                    p = getPiece(file2, rankmax, piecesOnBoard);
                }
                if (p == null && rankmax == 8) { // no pieces on the rook's path moving upward
                    return true;
                }
                if (rankmin == rankmax) {
                    if (p.color.equals(color)) {
                        return false;
                    } else {
                        if (rank2 == rankmin) {
                            return true;
                        } else {
                            return false;
                        }
                    }
                } else {
                    if (p.color.equals(color)) {
                        rankmax = rankmax - 1;
                    }
                    if (rank2 <= rankmax && rank2 >= rankmin) {
                        return true;
                    }
                    return false;
                }
            }
        } else if (returnpiece.pieceRank == rank2) {
            PieceFile filemax;
            PieceFile filemin;
            if (file2.compareTo(returnpiece.pieceFile) < 0) {
                filemax = decrementFile(returnpiece.pieceFile);
                filemin = filemax;
                Piece p = getPiece(filemin, rank2, piecesOnBoard);
                while (p == null && filemin.compareTo(PieceFile.b) >= 0) {
                    filemin = decrementFile(filemin);
                    p = getPiece(filemin, rank2, piecesOnBoard);
                }
                if (p == null && filemin == PieceFile.a) { // no pieces on the rook's path moving to the left
                    return true;
                }
                if (filemin == filemax) {
                    if (p.color.equals(color)) {
                        return false;
                    } else {
                        if (file2 == filemin) {
                            return true;
                        } else {
                            return false;
                        }
                    }
                } else {
                    if (p.color.equals(color)) {
                        //filemin = filemin + 1;
                        filemin = incrementFile(filemin);
                    }
                    if (file2.compareTo(filemax) <= 0 && file2.compareTo(filemin) >= 0) {
                        return true;
                    }
                    return false;
                }
            } else {
                filemin = incrementFile(returnpiece.pieceFile);
                filemax = filemin;
                Piece p = getPiece(filemax, rank2, piecesOnBoard);
                while (p == null && filemax.compareTo(PieceFile.g) <= 0) {
                    filemax = incrementFile(filemax);
                    p = getPiece(filemax, rank2, piecesOnBoard);
                }
                if (p == null && filemax == PieceFile.h) { // no pieces on the rook's path moving to the right
                    return true;
                }
                if (filemin == filemax) {
                    if (p.color.equals(color)) {
                        return false;
                    } else {
                        if (file2 == filemin) {
                            return true;
                        } else {
                            return false;
                        }
                    }
                } else {
                    if (p.color.equals(color)) {
                        //filemax = filemax - 1;
                        filemax = decrementFile(filemax);
                    }
                    if (file2.compareTo(filemax) <= 0 && file2.compareTo(filemin) >= 0) {
                        return true;
                    }
                    return false;
                }
            }
        }
        return false;

    }

}
