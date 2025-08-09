package chess;
import java.util.ArrayList;
import chess.ReturnPiece.PieceFile;

/**
 * Write a description of class Bishop here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Bishop extends Piece
{
    ArrayList<FileRank> topLeft;
    ArrayList<FileRank> topRight;
    ArrayList<FileRank> bottomLeft;
    ArrayList<FileRank> bottomRight;
    public Bishop(ReturnPiece r) {
        super(r);
    }
    
    public boolean ifValidMove(PieceFile file2, int rank2, ArrayList<Piece> piecesOnBoard) {
        if (file2 == returnpiece.pieceFile && rank2 == returnpiece.pieceRank) {
            return false;
        }
        topLeft = new ArrayList<FileRank>();
        topRight = new ArrayList<FileRank>();
        bottomLeft = new ArrayList<FileRank>();
        bottomRight = new ArrayList<FileRank>();
        if (!((returnpiece.pieceFile == PieceFile.a) || (returnpiece.pieceRank == 8))) {
            generatetopleft(decrementFile(returnpiece.pieceFile), returnpiece.pieceRank+1, piecesOnBoard);
        }
        if (!((returnpiece.pieceFile == PieceFile.h) || (returnpiece.pieceRank == 8))) {
            generatetopright(incrementFile(returnpiece.pieceFile), returnpiece.pieceRank+1, piecesOnBoard);
        }
        if (!((returnpiece.pieceFile == PieceFile.a) || (returnpiece.pieceRank == 1))) {
            generatebottomleft(decrementFile(returnpiece.pieceFile), returnpiece.pieceRank-1, piecesOnBoard);
        }
        if (!((returnpiece.pieceFile == PieceFile.h) || (returnpiece.pieceRank == 1))) {
            generatebottomright(incrementFile(returnpiece.pieceFile), returnpiece.pieceRank-1, piecesOnBoard);
        }
        
        for (FileRank filerank: topLeft) {
            PieceFile file = filerank.file;
            int rank = filerank.rank;
            if (file2 == file && rank2 == rank) {
                return true;
            }
        }
        for (FileRank filerank: topRight) {
            PieceFile file = filerank.file;
            int rank = filerank.rank;
            if (file2 == file && rank2 == rank) {
                return true;
            }
        }
        for (FileRank filerank: bottomLeft) {
            PieceFile file = filerank.file;
            int rank = filerank.rank;
            if (file2 == file && rank2 == rank) {
                return true;
            }
        }
        for (FileRank filerank: bottomRight) {
            PieceFile file = filerank.file;
            int rank = filerank.rank;
            if (file2 == file && rank2 == rank) {
                return true;
            }
        }
        return false;
    }
    
    public void generatetopleft(PieceFile file, int rank, ArrayList<Piece> piecesOnBoard) {
        if (file == PieceFile.a || rank == 8) {
            Piece p = getPiece(file, rank, piecesOnBoard);
            if (p != null) {
                if (color.equals(p.color)) {
                    return;
                } else {
                    topLeft.add(new FileRank(file, rank));
                    return;
                }
            } else {
                topLeft.add(new FileRank(file, rank));
                return;
            }
        } else {
            Piece p = getPiece(file, rank, piecesOnBoard);
            if (p != null) {
                if (color.equals(p.color)) {
                    return;
                } else {
                    topLeft.add(new FileRank(file, rank));
                    return;
                }
            } else {
                topLeft.add(new FileRank(file, rank));
                generatetopleft(decrementFile(file), rank+1, piecesOnBoard);
            }
        }
    }
    
    public void generatetopright(PieceFile file, int rank, ArrayList<Piece> piecesOnBoard) {
        if (file == PieceFile.h || rank == 8) {
            Piece p = getPiece(file, rank, piecesOnBoard);
            if (p != null) {
                if (color.equals(p.color)) {
                    return;
                } else {
                    topRight.add(new FileRank(file, rank));
                    return;
                }
            } else {
                topRight.add(new FileRank(file, rank));
                return;
            }
        } else {
            Piece p = getPiece(file, rank, piecesOnBoard);
            if (p != null) {
                if (color.equals(p.color)) {
                    return;
                } else {
                    topRight.add(new FileRank(file, rank));
                    return;
                }
            } else {
                topRight.add(new FileRank(file, rank));
                generatetopright(incrementFile(file), rank+1, piecesOnBoard);
            }
        }
    }
    
    public void generatebottomleft(PieceFile file, int rank, ArrayList<Piece> piecesOnBoard) {
        if (file == PieceFile.a || rank == 1) {
            Piece p = getPiece(file, rank, piecesOnBoard);
            if (p != null) {
                if (color.equals(p.color)) {
                    return;
                } else {
                    bottomLeft.add(new FileRank(file, rank));
                    return;
                }
            } else {
                bottomLeft.add(new FileRank(file, rank));
                return;
            }
        } else {
            Piece p = getPiece(file, rank, piecesOnBoard);
            if (p != null) {
                if (color.equals(p.color)) {
                    return;
                } else {
                    bottomLeft.add(new FileRank(file, rank));
                    return;
                }
            } else {
                bottomLeft.add(new FileRank(file, rank));
                generatebottomleft(decrementFile(file), rank-1, piecesOnBoard);
            }
        }
    }
    
    public void generatebottomright(PieceFile file, int rank, ArrayList<Piece> piecesOnBoard) {
        if (file == PieceFile.h || rank == 1) {
            Piece p = getPiece(file, rank, piecesOnBoard);
            if (p != null) {
                if (color.equals(p.color)) {
                    return;
                } else {
                    bottomRight.add(new FileRank(file, rank));
                    return;
                }
            } else {
                bottomRight.add(new FileRank(file, rank));
                return;
            }
        } else {
            Piece p = getPiece(file, rank, piecesOnBoard);
            if (p != null) {
                if (color.equals(p.color)) {
                    return;
                } else {
                    bottomRight.add(new FileRank(file, rank));
                    return;
                }
            } else {
                bottomRight.add(new FileRank(file, rank));
                generatebottomright(incrementFile(file), rank-1, piecesOnBoard);
            }
        }
    }
}
