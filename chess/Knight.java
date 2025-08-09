package chess;
import java.util.*;
import java.io.*;

/**
 * Write a description of class Knight here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Knight extends Piece
{
    public Knight (ReturnPiece r){
        super(r);
    }
    public boolean inCheck (ArrayList<Piece> piecesOnBoard, ArrayList <ReturnPiece> piecesOnBoardR){
        return false; 
    }
    public boolean killsPiece (ReturnPiece.PieceFile file2, int rank2, ArrayList <Piece> piecesOnBoard){
        for (int i  = 0; i < piecesOnBoard.size(); i++){
            Piece piece = piecesOnBoard.get(i); 
            if ((piece.returnpiece.pieceFile == file2) && (piece.returnpiece.pieceRank == rank2)){
                return true;
            }
        }
        return false; 
    }
    public boolean ifValidMove(ReturnPiece.PieceFile file2, int rank2, ArrayList<Piece> piecesOnBoard){
        boolean possible = true; 
        if ((rank2 == this.returnpiece.pieceRank + 2) || (rank2 == this.returnpiece.pieceRank - 2)){
            if (this.returnpiece.pieceFile == ReturnPiece.PieceFile.a){
                if (file2 != ReturnPiece.PieceFile.b){
                    possible = false; 
                }
            }
            else if (this.returnpiece.pieceFile == ReturnPiece.PieceFile.h){
                if (file2 != ReturnPiece.PieceFile.g){
                    possible = false; 
                }
            }
            else if (this.returnpiece.pieceFile == ReturnPiece.PieceFile.b){
                if ((file2 != ReturnPiece.PieceFile.a) && (file2 != ReturnPiece.PieceFile.c)){
                    possible = false; 
                }
            }
            else if (this.returnpiece.pieceFile == ReturnPiece.PieceFile.c){
                if ((file2 != ReturnPiece.PieceFile.b) && (file2 != ReturnPiece.PieceFile.d)){
                    possible = false; 
                }
            }
            else if (this.returnpiece.pieceFile == ReturnPiece.PieceFile.d){
                if ((file2 != ReturnPiece.PieceFile.c) && (file2 != ReturnPiece.PieceFile.e)){
                    possible = false; 
                }
            }
            else if (this.returnpiece.pieceFile == ReturnPiece.PieceFile.e){
                if ((file2 != ReturnPiece.PieceFile.d) && (file2 != ReturnPiece.PieceFile.f)){
                    possible = false; 
                }
            }
            else if (this.returnpiece.pieceFile == ReturnPiece.PieceFile.f){
                if ((file2 != ReturnPiece.PieceFile.e) && (file2 != ReturnPiece.PieceFile.g)){
                    possible = false; 
                    
                }
            }
            else{
                //System.out.println("made it here"); 
                if ((file2 != ReturnPiece.PieceFile.f) && (file2 != ReturnPiece.PieceFile.h)){
                    possible = false; 
                    //System.out.println("this is possible: " + possible); 
                }
            }
            
        }
        else if ((rank2 == this.returnpiece.pieceRank + 1) || (rank2 == this.returnpiece.pieceRank -1)){
            if (this.returnpiece.pieceFile == ReturnPiece.PieceFile.a){
                if (file2 != ReturnPiece.PieceFile.c){
                    possible = false; 
                }
            }
            else if (this.returnpiece.pieceFile == ReturnPiece.PieceFile.h){
                if (file2 != ReturnPiece.PieceFile.f){
                    possible = false; 
                }
            }
            else if (this.returnpiece.pieceFile == ReturnPiece.PieceFile.b){
                if (file2 != ReturnPiece.PieceFile.d){
                    possible = false; 
                }
            }
            else if (this.returnpiece.pieceFile == ReturnPiece.PieceFile.c){
                if ((file2 != ReturnPiece.PieceFile.a) && (file2 != ReturnPiece.PieceFile.e)) {
                    possible = false; 
                }
            }
            else if (this.returnpiece.pieceFile == ReturnPiece.PieceFile.d){
                if ((file2 != ReturnPiece.PieceFile.b) && (file2 != ReturnPiece.PieceFile.f)) {
                    possible = false; 
                }
            }
            else if (this.returnpiece.pieceFile == ReturnPiece.PieceFile.e){
                if ((file2 != ReturnPiece.PieceFile.c) && (file2 != ReturnPiece.PieceFile.g)) {
                    possible = false; 
                }
            }
            else if (this.returnpiece.pieceFile == ReturnPiece.PieceFile.f){
                if ((file2 != ReturnPiece.PieceFile.d) && (file2 != ReturnPiece.PieceFile.h)) {
                    possible = false; 
                }
            }
            else if (this.returnpiece.pieceFile == ReturnPiece.PieceFile.g){
                if (file2 != ReturnPiece.PieceFile.e){
                    possible = false; 
                }
            }
        }
        else {
            return false; 
        }
        
        
        if (possible == true){
            // check to make sure a piece of the same color is not on that square
            for (int lol = 0; lol < piecesOnBoard.size(); lol++){
                Piece smth = piecesOnBoard.get(lol); 
                if ((smth.color == this.color) && (smth.returnpiece.pieceFile == file2) && (smth.returnpiece.pieceRank == rank2)){
                    possible = false; 
                    break; 
                }
            }
        }
        
        
        /*if (possible == true){
            Piece k = piecesOnBoard.get(0); 
            ArrayList<Piece> secondList = new ArrayList<Piece>();
            for (int i = 0; i < piecesOnBoard.size(); i++){
                Piece pr = piecesOnBoard.get(i);
                if ((this.color == "white") && (pr.returnpiece.pieceType == ReturnPiece.PieceType.WK)){
                    k = pr; 
                }
                if ((this.color == "black") && (pr.returnpiece.pieceType == ReturnPiece.PieceType.BK)){
                    k = pr; 
                }
            
                if ((pr.returnpiece.pieceFile == this.returnpiece.pieceFile) && (pr.returnpiece.pieceRank == this.returnpiece.pieceRank)){
                    ReturnPiece temp = new ReturnPiece();
                    temp.pieceType = this.returnpiece.pieceType; 
                    temp.pieceFile = file2; 
                    temp.pieceRank = rank2;
                    Piece newP = new Pawn(temp);
                    secondList.add(newP); 
                }
                else{
                    secondList.add(pr); 
                }
            }
            
            if (k.inCheck(secondList)){
                possible = false; 
            }
        } */
        
        if  (possible == false){
            //System.out.println("I'm coming back false"); 
            return false; 
        }
        
        
        return true;
    }
    public boolean hasOpponentInCheck(ArrayList<Piece> piecesBoard){
        for (int i = 0; i < piecesBoard.size(); i++){
            Piece p = piecesBoard.get(i); 
            if (this.color == "white"){
                // get the opposite king
                if (p.returnpiece.pieceType == ReturnPiece.PieceType.BK){
                    // compare to see if knight could validly move to location
                    if (ifValidMove(p.returnpiece.pieceFile, p.returnpiece.pieceRank, piecesBoard)){
                        return true;
                    }
                    else{
                        return false; 
                    }
                }
            }
            else{
                if (p.returnpiece.pieceType == ReturnPiece.PieceType.WK){
                    // compare to see if knight could validly move to location
                    if (ifValidMove(p.returnpiece.pieceFile, p.returnpiece.pieceRank, piecesBoard)){
                        return true;
                    }
                    else{
                        return false; 
                    }
                }
                
            }
            
        }
        
        return true; 
    }

}


