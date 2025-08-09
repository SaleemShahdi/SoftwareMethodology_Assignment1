package chess;
import java.util.*;
import java.io.*;


/**
 * Write a description of class Pawn here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */ 
public class Pawn extends Piece
{
    // instance variables - replace the example below with your own
    public Pawn(ReturnPiece rp){
        super(rp);
    }
    public boolean inCheck(ArrayList<Piece> piecesOnBoard){
        return false; 
    }
    public boolean killsPiece (ReturnPiece.PieceFile file2, int rank2, ArrayList <Piece> piecesOnBoard){
        for (int i  = 0; i < piecesOnBoard.size(); i++){
            Piece piece = piecesOnBoard.get(i); 
            if ((piece.returnpiece.pieceFile == file2) && (piece.returnpiece.pieceRank == rank2) && (piece.color != this.color)){
                return true;
            }
        }
        return false; 
    }
    public boolean ifValidMove(ReturnPiece.PieceFile file2, int rank2, ArrayList<Piece> piecesOnBoard){
        boolean possible = false; 
        boolean enpassant = false; 
        if (file2 == this.returnpiece.pieceFile){
            // if  rank2 is 2 off from original, checks to make sure the color and initial rank are correct
            if ((rank2 == this.returnpiece.pieceRank + 2) && (this.color == "white") && (this.returnpiece.pieceRank == 2)){
                // if position is correct, checks to make sure there is nothing in the way 
                for (int i = 0; i < piecesOnBoard.size(); i++){
                    Piece p = piecesOnBoard.get(i); 
                    if ((p.returnpiece.pieceFile == this.returnpiece.pieceFile) && (p.returnpiece.pieceRank == 3)){
                        return false; 
                    }
                }
                possible = true; 
            
            }
            else if ((rank2 == this.returnpiece.pieceRank -2) && (this.color == "black") && (this.returnpiece.pieceRank == 7)){
                for (int i = 0; i < piecesOnBoard.size(); i++){
                    Piece p2 = piecesOnBoard.get(i); 
                    if ((p2.returnpiece.pieceFile == this.returnpiece.pieceFile) && (p2.returnpiece.pieceRank == 6)){
                        return false; 
                    }
                }
                possible = true; 
            
        }
        
        else if ((this.color == "white") && (rank2 == this.returnpiece.pieceRank + 1)){
            possible = true; 
        }
        else if ((this.color == "black") && (rank2 == this.returnpiece.pieceRank - 1)){
            possible = true; 
        }
        else {
            possible = false; 
        }
        
            // make sure that there are no pieces at all in the way--currently we can assume that the piece is not killing
            
            if (possible == true){
                //System.out.println("here pawn");
            for (int i = 0; i < piecesOnBoard.size(); i++){
                Piece piece = piecesOnBoard.get(i);
                //System.out.println("here " + piece.returnpiece.pieceFile + piece.returnpiece.pieceRank); 
                if ((piece.returnpiece.pieceFile == file2) && (piece.returnpiece.pieceRank == rank2)){
                    //System.out.println("error's not here");
                    possible = false; 
            }
        }
        }
        
        //System.out.println("possible: " + possible); 
    }
        else{
            if (this.returnpiece.pieceFile == ReturnPiece.PieceFile.a){
                if (this.color == "white"){
                    if ((file2 == ReturnPiece.PieceFile.b) && (rank2 == this.returnpiece.pieceRank + 1)){
                        possible = true; 
                    }
                }
                else{
                    if ((file2 == ReturnPiece.PieceFile.b) && (rank2 == this.returnpiece.pieceRank - 1)){
                        possible = true; 
                    }
                }
            }
            else if (this.returnpiece.pieceFile == ReturnPiece.PieceFile.b){
                if (this.color == "white"){
                    if (((file2 == ReturnPiece.PieceFile.a) || (file2 == ReturnPiece.PieceFile.c)) && (rank2 == this.returnpiece.pieceRank + 1)){
                        possible = true; 
                    }
                }
                else{
                    if (((file2 == ReturnPiece.PieceFile.a) || (file2 == ReturnPiece.PieceFile.c)) && (rank2 == this.returnpiece.pieceRank - 1)){
                        possible = true; 
                    }
                    
                }
                
            }
            else if (this.returnpiece.pieceFile == ReturnPiece.PieceFile.c){
                if (this.color == "white"){
                    if (((file2 == ReturnPiece.PieceFile.b) || (file2 == ReturnPiece.PieceFile.d)) && (rank2 == this.returnpiece.pieceRank + 1)){
                        possible = true; 
                    }
                }
                else{
                    if (((file2 == ReturnPiece.PieceFile.b) || (file2 == ReturnPiece.PieceFile.d)) && (rank2 == this.returnpiece.pieceRank - 1)){
                        possible = true; 
                    }
                    
                }
            }
            else if (this.returnpiece.pieceFile == ReturnPiece.PieceFile.d){
                if (this.color == "white"){
                    if (((file2 == ReturnPiece.PieceFile.c) || (file2 == ReturnPiece.PieceFile.e)) && (rank2 == this.returnpiece.pieceRank + 1)){
                        possible = true; 
                    }
                }
                else{
                    if (((file2 == ReturnPiece.PieceFile.c) || (file2 == ReturnPiece.PieceFile.e)) && (rank2 == this.returnpiece.pieceRank - 1)){
                        possible = true; 
                    }
                    
                }
            }
            else if (this.returnpiece.pieceFile == ReturnPiece.PieceFile.e){
                if (this.color == "white"){
                    if (((file2 == ReturnPiece.PieceFile.d) || (file2 == ReturnPiece.PieceFile.f)) && (rank2 == this.returnpiece.pieceRank + 1)){
                        possible = true; 
                    }
                }
                else{
                    if (((file2 == ReturnPiece.PieceFile.d) || (file2 == ReturnPiece.PieceFile.f)) && (rank2 == this.returnpiece.pieceRank - 1)){
                        possible = true; 
                    }
                    
                }
            }
            else if (this.returnpiece.pieceFile == ReturnPiece.PieceFile.f){
                if (this.color == "white"){
                    if (((file2 == ReturnPiece.PieceFile.e) || (file2 == ReturnPiece.PieceFile.g)) && (rank2 == this.returnpiece.pieceRank + 1)){
                        possible = true; 
                    }
                }
                else{
                    if (((file2 == ReturnPiece.PieceFile.e) || (file2 == ReturnPiece.PieceFile.g)) && (rank2 == this.returnpiece.pieceRank - 1)){
                        possible = true; 
                    }
                    
                }
            }
            else if (this.returnpiece.pieceFile == ReturnPiece.PieceFile.g){
                if (this.color == "white"){
                    if (((file2 == ReturnPiece.PieceFile.f) || (file2 == ReturnPiece.PieceFile.h)) && (rank2 == this.returnpiece.pieceRank + 1)){
                        possible = true; 
                    }
                }
                else{
                    if (((file2 == ReturnPiece.PieceFile.f) || (file2 == ReturnPiece.PieceFile.h)) && (rank2 == this.returnpiece.pieceRank - 1)){
                        possible = true; 
                    }
                    
                }
            }
            else{
                if (this.color == "white"){
                    if ((file2 == ReturnPiece.PieceFile.g) && (rank2 == this.returnpiece.pieceRank + 1)){
                        possible = true; 
                    }
                }
                else{
                    if ((file2 == ReturnPiece.PieceFile.g) && (rank2 == this.returnpiece.pieceRank - 1)){
                        possible = true; 
                    }
                }
                
            }
            
            
            
            // the only way the move will be valid is if it's capturing another piece
        if (possible == true){
                
            
                boolean kills = killsPiece(file2, rank2, piecesOnBoard); 
            
                if (!kills){
                    possible = false; 
                    // check for enpassant 
                    boolean nothing = true; 
                for (int j = 0; j < piecesOnBoard.size(); j++){
                    Piece p3 = piecesOnBoard.get(j); 
                    if ((p3.returnpiece.pieceFile == file2) && (p3.returnpiece.pieceRank == rank2)){
                        nothing = false; 
                    }
                }
                if (nothing == true){
                    // enpassant possible so far, check other conditions
                    if (this.color == "white"){
                        // make sure there is a black pawn directly beneath the given square 
                        boolean hasBlackPawn = false; 
                        for (int t = 0; t < piecesOnBoard.size(); t++){
                            Piece maybePawn = piecesOnBoard.get(t); 
                            if ((maybePawn.returnpiece.pieceType == ReturnPiece.PieceType.BP) && (maybePawn.returnpiece.pieceFile == file2) && (maybePawn.returnpiece.pieceRank == rank2 - 1)){
                                hasBlackPawn = true;
                                break; 
                            }
                        }
                        if (hasBlackPawn){
                            possible = true; 
                            enpassant = true; 
                        }
                        
                    }
                    else {
                        // make sure there is a white pawn directly beneath the given square
                        boolean hasWhitePawn = false; 
                        for (int t = 0; t < piecesOnBoard.size(); t++){
                            Piece maybePawn = piecesOnBoard.get(t); 
                            if ((maybePawn.returnpiece.pieceType == ReturnPiece.PieceType.WP) && (maybePawn.returnpiece.pieceFile == file2) && (maybePawn.returnpiece.pieceRank == rank2 + 1)){
                                hasWhitePawn = true;
                                break; 
                            }
                        }
                        if (hasWhitePawn){
                            possible = true; 
                            enpassant = true; 
                        }
                    }
                    
                }
            }
            else{
                
                possible= true; 
                
            }
        }
            
        }
    
        // make sure there's not a piece of our color in that spot
        if (possible == true){
            for (int i = 0; i < piecesOnBoard.size(); i++){
                Piece piece = piecesOnBoard.get(i); 
                if ((piece.returnpiece.pieceFile == file2) && (piece.returnpiece.pieceRank == rank2) && (piece.color == this.color)){
                    possible = false; 
            }
        }
    }
    
  
    
    
    
    
    // make sure that the move won't put our own king in check 
    /*if (possible == true){
        // create duplicate arraylist 
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
                if (this.color == "white"){
                     temp.pieceType = ReturnPiece.PieceType.WP; 
                }
                else{
                    temp.pieceType = ReturnPiece.PieceType.BP; 
                }
                temp.pieceFile = file2; 
                temp.pieceRank = rank2;
                Piece newP = new Pawn(temp);
                secondList.add(newP); 
            }
            else{
                secondList.add(pr); 
            }
        }
        // check to see if there's a check against the king 
        
        boolean hasCheck = k.inCheck(secondList); 
        if (hasCheck == true){
            possible = false;
        }
    } */
        
        if (possible == false){
            return false; 
        }
        
        return true;
    
    }
    

    /*public boolean hasOpponentInCheck(ArrayList<Piece> piecesBoard){
        System.out.println("move");
        return true; 
    }*/
}

