package chess;
import java.util.*;
import java.io.*; 


/**
 * Write a description of class King here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class King extends Piece
{
    // instance variables - replace the example below with your own
    public King(ReturnPiece rp){
        super(rp);
    }
  
    
    public boolean inCheck(ArrayList <Piece> piecesOnBoard){
        for (int i = 0; i < piecesOnBoard.size(); i++){
            Piece p = piecesOnBoard.get(i); 
            if (p.color != this.color){
                if (p.ifValidMove(this.returnpiece.pieceFile, this.returnpiece.pieceRank, piecesOnBoard)){
                    //System.out.println("fuck");
                    return true; 
                }
            }
        }
        return false;
    }
    public boolean killsPiece(ReturnPiece.PieceFile file2, int rank2, ArrayList<Piece> piecesOnBoard){
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
        if ((file2 == this.returnpiece.pieceFile) && ((rank2 == (this.returnpiece.pieceRank - 1)) || (rank2 == (this.returnpiece.pieceRank + 1)))){
            possible = true; 
        }
        else if (rank2 == this.returnpiece.pieceRank){
            if (this.returnpiece.pieceFile == ReturnPiece.PieceFile.a){
                if (file2 == ReturnPiece.PieceFile.b){
                    possible = true; 
                }
            }
            else if (this.returnpiece.pieceFile == ReturnPiece.PieceFile.b){
                if ((file2 == ReturnPiece.PieceFile.c) || (file2 == ReturnPiece.PieceFile.a)){
                    possible = true; 
                }
            }
            else if (this.returnpiece.pieceFile == ReturnPiece.PieceFile.c){
                if ((file2 == ReturnPiece.PieceFile.b) || (file2 == ReturnPiece.PieceFile.d)){
                    possible = true; 
                }
            }
            else if (this.returnpiece.pieceFile == ReturnPiece.PieceFile.d){
                if ((file2 == ReturnPiece.PieceFile.c) || (file2 == ReturnPiece.PieceFile.e)){
                    possible = true; 
                }
            }
            else if (this.returnpiece.pieceFile == ReturnPiece.PieceFile.e){
                if ((file2 == ReturnPiece.PieceFile.d) || (file2 == ReturnPiece.PieceFile.f)){
                    possible = true; 
                }
            }
            else if (this.returnpiece.pieceFile == ReturnPiece.PieceFile.f){
                if ((file2 == ReturnPiece.PieceFile.e) || (file2 == ReturnPiece.PieceFile.g)){
                    possible = true; 
                }
            }
            else if (this.returnpiece.pieceFile == ReturnPiece.PieceFile.g){
                if ((file2 == ReturnPiece.PieceFile.f) || (file2 == ReturnPiece.PieceFile.h)){
                    possible = true; 
                }  
            }
            else{
                if (file2 == ReturnPiece.PieceFile.g){
                    possible = true; 
                }
            }
        }
        else{
            if (this.returnpiece.pieceFile == ReturnPiece.PieceFile.a){
                if ((file2 == ReturnPiece.PieceFile.b) && ((rank2 == this.returnpiece.pieceRank -1) || (rank2 == this.returnpiece.pieceRank +1))){
                    possible = true; 
                }
            }
            else if (this.returnpiece.pieceFile == ReturnPiece.PieceFile.b){
                if (((file2 == ReturnPiece.PieceFile.a) || (file2== ReturnPiece.PieceFile.c)) && ((rank2 == this.returnpiece.pieceRank -1) || (rank2 == this.returnpiece.pieceRank + 1))){
                    possible = true; 
                }
             }
             else if (this.returnpiece.pieceFile == ReturnPiece.PieceFile.c){
                 if (((file2 == ReturnPiece.PieceFile.b) || (file2== ReturnPiece.PieceFile.d)) && ((rank2 == this.returnpiece.pieceRank -1) || (rank2 == this.returnpiece.pieceRank + 1))){
                    possible = true; 
                }
             }
             else if (this.returnpiece.pieceFile == ReturnPiece.PieceFile.d){
                 if (((file2 == ReturnPiece.PieceFile.c) || (file2== ReturnPiece.PieceFile.e)) && ((rank2 == this.returnpiece.pieceRank -1) || (rank2 == this.returnpiece.pieceRank + 1))){
                    possible = true; 
                }
             }
             else if (this.returnpiece.pieceFile == ReturnPiece.PieceFile.e){
                 if (((file2 == ReturnPiece.PieceFile.d) || (file2== ReturnPiece.PieceFile.f)) && ((rank2 == this.returnpiece.pieceRank -1) || (rank2 == this.returnpiece.pieceRank + 1))){
                    possible = true; 
                }
             }
             else if (this.returnpiece.pieceFile == ReturnPiece.PieceFile.f){
                 if (((file2 == ReturnPiece.PieceFile.e) || (file2== ReturnPiece.PieceFile.g)) && ((rank2 == this.returnpiece.pieceRank -1) || (rank2 == this.returnpiece.pieceRank + 1))){
                    possible = true; 
                }
             }
             else if (this.returnpiece.pieceFile == ReturnPiece.PieceFile.g){
                 if (((file2 == ReturnPiece.PieceFile.f) || (file2== ReturnPiece.PieceFile.h)) && ((rank2 == this.returnpiece.pieceRank -1) || (rank2 == this.returnpiece.pieceRank + 1))){
                    possible = true; 
                }
             }
             else{
                 if ((file2 == ReturnPiece.PieceFile.g) && ((rank2 == this.returnpiece.pieceRank -1) || (rank2 == this.returnpiece.pieceRank +1))){
                    possible = true; 
                }
             }
        }
    
        for (int i = 0; i < piecesOnBoard.size(); i++){ 
            Piece p = piecesOnBoard.get(i); 
            if ((p.returnpiece.pieceFile == file2) && (p.returnpiece.pieceRank == rank2)){
                if (p.color == this.color){
                    possible = false;
                }
            }
        }
        
        // check to see if the move will put our own king in check 
        
        //if (possible == true){
            //ArrayList<Piece> secondList = new ArrayList<Piece>();
            
            //for (int i = 0; i < piecesOnBoard.size(); i++){
                //Piece pr = piecesOnBoard.get(i);
                //if (pr.returnpiece.pieceType != this.returnpiece.pieceType){
                    // exclude this piece from the second list
                    //secondList.add(pr);
               // }
            
                //if ((pr.returnpiece.pieceFile == this.returnpiece.pieceFile) && (pr.returnpiece.pieceRank == this.returnpiece.pieceRank)){
                    //ReturnPiece temp = new ReturnPiece();
                    //if (this.color == "white"){
                         //temp.pieceType = ReturnPiece.PieceType.WP; 
                    //}
                    //else{
                        //temp.pieceType = ReturnPiece.PieceType.BP; 
                    //}
                    //temp.pieceFile = file2; 
                    //temp.pieceRank = rank2;
                    //Piece newP = new Pawn(temp);
                    //secondList.add(newP); 
                //}
                //else{
                    //secondList.add(pr); 
                //}*/
            //}
            
           // ReturnPiece temp = new ReturnPiece(); 
            //temp.pieceFile = file2; 
            //temp.pieceRank = rank2; 
            //temp.pieceType = this.returnpiece.pieceType; 
           // Piece np = new King(temp); 
            //secondList.add(np); 
            
            //if (np.inCheck(secondList)){
               // possible = false; 
           // }
            //System.out.println("possible " + possible); 
            
        //} */
        
        
       
        
        if (possible == false){
            return false; 
        }
        
        return true;
    }
    
}



