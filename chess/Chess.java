package chess;

/* Shahrez Ahmad and Mihika Pradhan */
import java.util.ArrayList;
public class Chess {
    enum Player { white, black }
    /**
     * Plays the next move for whichever player has the turn.
     * 
     * @param move String for next move, e.g. "a2 a3"
     * 
     * @return A ReturnPlay instance that contains the result of the move.
     *         See the section "The Chess class" in the assignment description for details of
     *         the contents of the returned ReturnPlay instance.
     */

    public static ArrayList<ReturnPiece> piecesOnBoard;
    public static ArrayList<Piece> piecesOnBoard2;
    public static Player player; 
    public static ReturnPlay.Message message; 

    public static ReturnPlay play(String move) {
        //currently assumes that all strings passed will have the correct format 
        boolean isInCheck = false;
        boolean checkmate = false;
        String color;
        if (player == Player.white) {
            color = "white";
        } else {
            color = "black";
        }
        String[] moves = move.split("\\s+"); 
        String space1 = new String(); 
        String space2 = new String(); 
        String pawnP = null; 
        boolean draw = false; 

        ReturnPlay rP = new ReturnPlay();
        rP.piecesOnBoard = piecesOnBoard;

        if (moves.length == 1){
            if (moves[0].equals("resign")){
                if (player == Player.white){
                    rP.message = ReturnPlay.Message.RESIGN_BLACK_WINS;
                }
                else{
                    rP.message = ReturnPlay.Message.RESIGN_WHITE_WINS;
                }
                return rP; 
            }
        }
        else if (moves.length == 4){
            if (moves[0].equals("")){
                space1 = moves[1]; 
                space2 = moves[2]; 
                if (moves[3].equals("draw?")){
                    draw = true; 
                }
                else{
                    draw = false; 
                    pawnP = moves[3]; 
                }
            }
            else if (moves[3].equals("")){
                space1 = moves[0]; 
                space2 = moves[1]; 
                if (moves[2].equals("draw?")){
                    draw = true; 
                }
                else{
                    draw = false; 
                    pawnP = moves[2];
                }
            }
            else{
                space1 = moves[0];
                space2 = moves[1]; 
                pawnP = moves[2]; 
                draw = true; 
            }
        }
        else if (moves.length == 5){
            //System.out.println("here"); 
            if (moves[0].equals("")){
                //System.out.println("this far"); 
                space1 = moves[1]; 
                space2 = moves[2]; 
                pawnP = moves[3]; 
                draw = true; 
            }
            else{
                space1 = moves[0]; 
                space2 = moves[1]; 
                pawnP = moves[2]; 
                draw = true; 
            }
        }
        else if (moves.length == 3){
            if (moves[0].equals("")){
                space1 = moves[1];
                space2 = moves[2];
            }
            else if (moves[2].equals("")){
                space1 = moves[0];
                space2 = moves[1];
            }
            else{ 
                if (moves[2].equals("draw?")){
                    space1 = moves[0];
                    space2 = moves[1];
                    draw = true; 
                }
                else{
                    space1 = moves[0];
                    space2 = moves[1];
                    pawnP = moves[2];
                }
            }
        }
        else{
            space1 = moves[0];
            space2 = moves[1];
        } 
        char file1 = space1.charAt(0);
        char file2 = space2.charAt(0);
        String f1 = String.valueOf(file1);
        String f2 = String.valueOf(file2);

        ReturnPiece.PieceFile newFile1 = ReturnPiece.PieceFile.valueOf(f1);
        ReturnPiece.PieceFile newFile2 = ReturnPiece.PieceFile.valueOf(f2);
        int rank1 = Character.getNumericValue(space1.charAt(1));
        int rank2 = Character.getNumericValue(space2.charAt(1));

        if ((newFile1 == newFile2) && (rank1 == rank2)){
            rP.message = ReturnPlay.Message.ILLEGAL_MOVE; 
            return rP; 
        }

        boolean found = false; 
        for (int i = 0; i < piecesOnBoard2.size(); i++){
            Piece p = piecesOnBoard2.get(i); 
            if ((p.returnpiece.pieceFile == newFile1) && (p.returnpiece.pieceRank == rank1)){
                found = true; 
                String c = player.toString(); 
                if (!c.equals(p.color)){
                    rP.message = ReturnPlay.Message.ILLEGAL_MOVE;
                    return rP; 
                }

                // check for castling first 
                boolean castle = false;
                boolean notCheck = false; 

                if ((space1.equals("e1")) && (space2.equals("g1"))){
                    // white kingside castling 
                    castle = true; 

                    // make sure piece is king
                    if (p.returnpiece.pieceType == ReturnPiece.PieceType.WK){
                        // make sure the king is not in check
                        boolean checked = p.inCheck(piecesOnBoard2); 
                        if (checked == false){
                            // make sure h1 is a rook 
                            boolean isRook = false; 
                            boolean hasInBetween = false; 
                            Piece maybeRook = piecesOnBoard2.get(0); 
                            for (int one = 0; one < piecesOnBoard2.size(); one++){
                                maybeRook = piecesOnBoard2.get(one); 
                                if ((maybeRook.returnpiece.pieceType == ReturnPiece.PieceType.WR) && (maybeRook.returnpiece.pieceFile == ReturnPiece.PieceFile.h) && (maybeRook.returnpiece.pieceRank == 1)){
                                    isRook = true; 
                                    break; 
                                }
                            }

                            if (isRook){
                                // make sure there are no pieces between
                                for (int two = 0; two < piecesOnBoard2.size(); two++){
                                    Piece something = piecesOnBoard2.get(two);
                                    if (something.returnpiece.pieceRank == 1){
                                        if ((something.returnpiece.pieceFile == ReturnPiece.PieceFile.f) || (something.returnpiece.pieceFile == ReturnPiece.PieceFile.g)){
                                            hasInBetween = true; 
                                            break; 
                                        }
                                    }

                                }
                                if (!hasInBetween){
                                    // all conditions for castling have been met, so we can update 
                                    p.returnpiece.pieceFile = ReturnPiece.PieceFile.g; 
                                    maybeRook.returnpiece.pieceFile = ReturnPiece.PieceFile.f; 
                                    // would i even have to find the returnpiece objects? idts 
                                    // check to see if the king is now in check 
                                    checked = p.inCheck(piecesOnBoard2); 
                                    if (checked == true){
                                        //if the king is checked, the move is illegal and must be reverted
                                        p.returnpiece.pieceFile = ReturnPiece.PieceFile.e;
                                        maybeRook.returnpiece.pieceFile = ReturnPiece.PieceFile.h; 
                                        rP.message = ReturnPlay.Message.ILLEGAL_MOVE;
                                        return rP; 
                                    }
                                    break; 

                                }
                            }

                        }
                    }

                    
                    // make sure there are no pieces between 

                }
                if ((space1.equals("e1")) && (space2.equals("c1"))){
                    // white queenside castling 
                    castle = true; 

                    // make sure piece is king
                    if (p.returnpiece.pieceType == ReturnPiece.PieceType.WK){
                        // make sure the king is not in check
                        boolean checked = p.inCheck(piecesOnBoard2); 
                        if (checked == false){
                            // make sure h1 is a rook 
                            boolean isRook = false; 
                            boolean hasInBetween = false; 
                            Piece maybeRook = piecesOnBoard2.get(0); 
                            for (int one = 0; one < piecesOnBoard2.size(); one++){
                                maybeRook = piecesOnBoard2.get(one); 
                                if ((maybeRook.returnpiece.pieceType == ReturnPiece.PieceType.WR) && (maybeRook.returnpiece.pieceFile == ReturnPiece.PieceFile.a) && (maybeRook.returnpiece.pieceRank == 1)){
                                    isRook = true; 
                                    break; 
                                }
                            }

                            if (isRook){
                                // make sure there are no pieces between
                                for (int two = 0; two < piecesOnBoard2.size(); two++){
                                    Piece something = piecesOnBoard2.get(two);
                                    if (something.returnpiece.pieceRank == 1){
                                        if ((something.returnpiece.pieceFile == ReturnPiece.PieceFile.b) || (something.returnpiece.pieceFile == ReturnPiece.PieceFile.c) || (something.returnpiece.pieceFile == ReturnPiece.PieceFile.d)){
                                            hasInBetween = true; 
                                            break; 
                                        }
                                    }

                                }
                                if (!hasInBetween){
                                    // all conditions for castling have been met, so we can update 
                                    p.returnpiece.pieceFile = ReturnPiece.PieceFile.c; 
                                    maybeRook.returnpiece.pieceFile = ReturnPiece.PieceFile.d; 
                                    // would i even have to find the returnpiece objects? idts 
                                    // check to see if the king is now in check 
                                    checked = p.inCheck(piecesOnBoard2); 
                                    if (checked == true){
                                        //if the king is checked, the move is illegal and must be reverted
                                        p.returnpiece.pieceFile = ReturnPiece.PieceFile.e;
                                        maybeRook.returnpiece.pieceFile = ReturnPiece.PieceFile.a; 
                                        rP.message = ReturnPlay.Message.ILLEGAL_MOVE;
                                        return rP; 
                                    }
                                    break; 

                                }
                            }

                        }
                    }

                }
                if ((space1.equals("e8")) && (space2.equals("g8"))){
                    // black kingside castling

                    castle = true; 

                    // make sure piece is king
                    if (p.returnpiece.pieceType == ReturnPiece.PieceType.BK){
                        // make sure the king is not in check
                        boolean checked = p.inCheck(piecesOnBoard2); 
                        if (checked == false){
                            // make sure h1 is a rook 
                            boolean isRook = false; 
                            boolean hasInBetween = false; 
                            Piece maybeRook = piecesOnBoard2.get(0); 
                            for (int one = 0; one < piecesOnBoard2.size(); one++){
                                maybeRook = piecesOnBoard2.get(one); 
                                if ((maybeRook.returnpiece.pieceType == ReturnPiece.PieceType.BR) && (maybeRook.returnpiece.pieceFile == ReturnPiece.PieceFile.h) && (maybeRook.returnpiece.pieceRank == 8)){
                                    isRook = true; 
                                    break; 
                                }
                            }

                            if (isRook){
                                // make sure there are no pieces between
                                for (int two = 0; two < piecesOnBoard2.size(); two++){
                                    Piece something = piecesOnBoard2.get(two);
                                    if (something.returnpiece.pieceRank == 8){
                                        if ((something.returnpiece.pieceFile == ReturnPiece.PieceFile.f) || (something.returnpiece.pieceFile == ReturnPiece.PieceFile.g)){
                                            hasInBetween = true; 
                                            break; 
                                        }
                                    }

                                }
                                if (!hasInBetween){
                                    // all conditions for castling have been met, so we can update 
                                    p.returnpiece.pieceFile = ReturnPiece.PieceFile.g; 
                                    maybeRook.returnpiece.pieceFile = ReturnPiece.PieceFile.f; 
                                    // would i even have to find the returnpiece objects? idts 
                                    // check to see if the king is now in check 
                                    checked = p.inCheck(piecesOnBoard2); 
                                    if (checked == true){
                                        //if the king is checked, the move is illegal and must be reverted
                                        p.returnpiece.pieceFile = ReturnPiece.PieceFile.e;
                                        maybeRook.returnpiece.pieceFile = ReturnPiece.PieceFile.h; 
                                        rP.message = ReturnPlay.Message.ILLEGAL_MOVE;
                                        return rP; 
                                    }
                                    break; 

                                }
                            }

                        }
                    }

                    // make sure there are no pieces between 
                }
                if ((space1.equals("e8")) && (space2.equals("c8"))){
                    // black queenside castling

                    castle = true; 

                    // make sure piece is king
                    if (p.returnpiece.pieceType == ReturnPiece.PieceType.BK){
                        // make sure the king is not in check
                        boolean checked = p.inCheck(piecesOnBoard2); 
                        if (checked == false){
                            // make sure a1 is a rook 
                            boolean isRook = false; 
                            boolean hasInBetween = false; 
                            Piece maybeRook = piecesOnBoard2.get(0); 
                            for (int one = 0; one < piecesOnBoard2.size(); one++){
                                maybeRook = piecesOnBoard2.get(one); 
                                if ((maybeRook.returnpiece.pieceType == ReturnPiece.PieceType.BR) && (maybeRook.returnpiece.pieceFile == ReturnPiece.PieceFile.a) && (maybeRook.returnpiece.pieceRank == 8)){
                                    isRook = true; 
                                    break; 
                                }
                            }

                            if (isRook){
                                // make sure there are no pieces between
                                for (int two = 0; two < piecesOnBoard2.size(); two++){
                                    Piece something = piecesOnBoard2.get(two);
                                    if (something.returnpiece.pieceRank == 8){
                                        if ((something.returnpiece.pieceFile == ReturnPiece.PieceFile.b) || (something.returnpiece.pieceFile == ReturnPiece.PieceFile.c) || (something.returnpiece.pieceFile == ReturnPiece.PieceFile.d)){
                                            hasInBetween = true; 
                                            break; 
                                        }
                                    }

                                }
                                if (!hasInBetween){
                                    // all conditions for castling have been met, so we can update 
                                    p.returnpiece.pieceFile = ReturnPiece.PieceFile.c; 
                                    maybeRook.returnpiece.pieceFile = ReturnPiece.PieceFile.d; 
                                    // check to see if the king is now in check 
                                    checked = p.inCheck(piecesOnBoard2); 
                                    if (checked == true){
                                        //if the king is checked, the move is illegal and must be reverted
                                        p.returnpiece.pieceFile = ReturnPiece.PieceFile.e;
                                        maybeRook.returnpiece.pieceFile = ReturnPiece.PieceFile.a; 
                                        rP.message = ReturnPlay.Message.ILLEGAL_MOVE;
                                        return rP; 
                                    }

                                    break; 

                                }
                            }

                        }
                    }

                }

                
                
                if (!p.ifValidMove(newFile2, rank2, piecesOnBoard2)){
                    rP.message = ReturnPlay.Message.ILLEGAL_MOVE;
                    return rP; 
                }
                
                ArrayList<Piece> temp = miniPlay(newFile2, rank2, piecesOnBoard2, p);
                String tempcolor;
                if (player == Player.white) {
                    tempcolor = "black";
                } else {
                    tempcolor = "white";
                }
                
                if (determineCheck(tempcolor, temp)) {
                    rP.message = ReturnPlay.Message.ILLEGAL_MOVE;
                    return rP; 
                }
                
                
                

                // checks to see if it will put our own king in check

                // create duplicate arraylist 
                /*Piece kMaybe = piecesOnBoard2.get(0); 
                ArrayList<Piece> secondList = new ArrayList<Piece>();

                for (int l = 0; l < piecesOnBoard2.size(); l++){
                Piece prMore = piecesOnBoard2.get(l); 
                if ((player == Player.white) && (prMore.returnpiece.pieceType == ReturnPiece.PieceType.WK)){
                kMaybe = prMore; 
                }
                if ((player == Player.black) && (prMore.returnpiece.pieceType == ReturnPiece.PieceType.BK)){
                kMaybe = prMore; 
                }

                if ((prMore.returnpiece.pieceFile != p.returnpiece.pieceFile) && (prMore.returnpiece.pieceRank != p.returnpiece.pieceRank)){
                // if it's not the same piece, add it to the second list
                secondList.add(prMore); 
                }
                else{
                ReturnPiece temp = new ReturnPiece(); 
                temp.pieceType = p.returnpiece.pieceType; 
                temp.pieceFile = p.returnpiece.pieceFile;
                temp.pieceRank = p.returnpiece.pieceRank; 
                Piece newPiece = null;
                if ((temp.pieceType == ReturnPiece.PieceType.WB) || (temp.pieceType == ReturnPiece.PieceType.BB)){
                newPiece = new Bishop(temp);
                }
                else if ((temp.pieceType == ReturnPiece.PieceType.WQ) || (temp.pieceType == ReturnPiece.PieceType.BQ)){
                newPiece = new Queen(temp);
                }
                else if ((temp.pieceType == ReturnPiece.PieceType.WP) || (temp.pieceType == ReturnPiece.PieceType.BP)){
                newPiece = new Pawn(temp);
                }
                else if ((temp.pieceType == ReturnPiece.PieceType.WK) || (temp.pieceType == ReturnPiece.PieceType.BK)){
                newPiece = new King(temp);
                }
                else if ((temp.pieceType == ReturnPiece.PieceType.WN) || (temp.pieceType == ReturnPiece.PieceType.BN)){
                newPiece = new Knight(temp);
                }
                else{
                newPiece = new Rook(temp);
                }
                if (newPiece != null){
                secondList.add(newPiece);
                }

                }
                }

                boolean hasCheck = kMaybe.inCheck(secondList); 
                if (hasCheck == true){
                // move will put own king in check
                rP.message = ReturnPlay.Message.ILLEGAL_MOVE;
                return rP; 
                }*/

                
                // checks for enpassant 
                if (p.returnpiece.pieceType == ReturnPiece.PieceType.WP){
                    if ((newFile2 != p.returnpiece.pieceFile) && (!p.killsPiece(newFile2, rank2, piecesOnBoard2))){
                        // sees that the newFile is not the same as current but no piece is being overtaken in the newFile 
                        for (int w = 0; w < piecesOnBoard2.size(); w++){
                            Piece kPawn = piecesOnBoard2.get(w); 
                            if ((kPawn.returnpiece.pieceType == ReturnPiece.PieceType.BP) && (kPawn.returnpiece.pieceFile == newFile2) && (kPawn.returnpiece.pieceRank == rank2 -1)){
                                piecesOnBoard2.remove(kPawn); 
                            }
                        }
                        for (int x = 0; x < piecesOnBoard.size(); x++){
                            ReturnPiece kp = piecesOnBoard.get(x); 
                            if ((kp.pieceType == ReturnPiece.PieceType.BP) && (kp.pieceFile == newFile2) && (kp.pieceRank == rank2 -1)){
                                piecesOnBoard.remove(kp); 
                            }
                        }
                    }
                }
                if (p.returnpiece.pieceType == ReturnPiece.PieceType.BP){
                    if ((newFile2 != p.returnpiece.pieceFile) && (!p.killsPiece(newFile2, rank2, piecesOnBoard2))){
                        // sees that the newFile is not the same as current but no piece is being overtaken in the newFile 
                        for (int w = 0; w < piecesOnBoard2.size(); w++){
                            Piece kPawn = piecesOnBoard2.get(w); 
                            if ((kPawn.returnpiece.pieceType == ReturnPiece.PieceType.WP) && (kPawn.returnpiece.pieceFile == newFile2) && (kPawn.returnpiece.pieceRank == rank2 +1)){
                                piecesOnBoard2.remove(kPawn); 
                            }
                        }
                        for (int x = 0; x < piecesOnBoard.size(); x++){
                            ReturnPiece kp = piecesOnBoard.get(x); 
                            if ((kp.pieceType == ReturnPiece.PieceType.WP) && (kp.pieceFile == newFile2) && (kp.pieceRank == rank2 +1)){
                                piecesOnBoard.remove(kp); 
                            }
                        }
                    }
                }

                // check to see if it kills a piece on the other side
                if (p.killsPiece(newFile2, rank2, piecesOnBoard2)){
                    //get piece being killed
                    for (int j = 0; j < piecesOnBoard2.size(); j++){
                        Piece deadP = piecesOnBoard2.get(j); 
                        if ((deadP.returnpiece.pieceFile == newFile2) && (deadP.returnpiece.pieceRank == rank2)){
                            piecesOnBoard2.remove(j); 

                        }

                    }

                    for (int k = 0; k < piecesOnBoard.size(); k++){
                        ReturnPiece deadPiece = piecesOnBoard.get(k); 
                        if ((deadPiece.pieceFile == newFile2) && (deadPiece.pieceRank == rank2)){
                            piecesOnBoard.remove(k); 

                        }

                    }
                }
                // update the piece's position 
                p.returnpiece.pieceFile = newFile2; 
                p.returnpiece.pieceRank = rank2; 
                for (int m = 0; m < piecesOnBoard.size();m++){
                    ReturnPiece returnP = piecesOnBoard.get(m); 
                    if ((returnP.pieceFile == newFile1) && (returnP.pieceRank == rank1)){
                        returnP.pieceFile = newFile2; 
                        returnP.pieceRank = rank2; 
                    }
                }

                //deal with pawn promotion 
                if (((rank2 == 1) || (rank2 == 8)) && ((p.returnpiece.pieceType == ReturnPiece.PieceType.WP) || (p.returnpiece.pieceType == ReturnPiece.PieceType.BP))){
                    //System.out.println("here for some reason"); 
                    //System.out.println("this is rank2: " + rank2); 
                    for (int t = 0; t < piecesOnBoard.size(); t++){
                        ReturnPiece anotherPiece = piecesOnBoard.get(t); 
                        if ((anotherPiece.pieceFile == newFile2) && (anotherPiece.pieceRank == rank2)){
                            piecesOnBoard.remove(anotherPiece); 
                            break;
                        }
                    }
                    if (pawnP != null){
                        if (player == Player.white){
                            if (pawnP.equals("N")){
                                // create white knight

                                ReturnPiece nWN = new ReturnPiece(); 
                                nWN.pieceType = ReturnPiece.PieceType.WN; 
                                nWN.pieceFile = newFile2; 
                                nWN.pieceRank = rank2; 
                                piecesOnBoard.add(nWN); 
                                Piece newWKnight = new Knight(nWN);
                                piecesOnBoard2.remove(p); 
                                piecesOnBoard2.add(newWKnight); 

                            }
                            else if (pawnP.equals("Q")){
                                ReturnPiece nWQ = new ReturnPiece(); 
                                nWQ.pieceType = ReturnPiece.PieceType.WQ; 
                                nWQ.pieceFile = newFile2; 
                                nWQ.pieceRank = rank2; 
                                piecesOnBoard.add(nWQ); 
                                Piece newWQueen = new Queen(nWQ);
                                piecesOnBoard2.remove(p); 
                                piecesOnBoard2.add(newWQueen);
                            }
                            else if (pawnP.equals("R")){
                                // create white rook 
                                ReturnPiece nWR = new ReturnPiece(); 
                                nWR.pieceType = ReturnPiece.PieceType.WR; 
                                nWR.pieceFile = newFile2; 
                                nWR.pieceRank = rank2; 
                                piecesOnBoard.add(nWR); 
                                Piece newWRook = new Rook(nWR);
                                piecesOnBoard2.remove(p); 
                                piecesOnBoard2.add(newWRook);
                            }
                            else {
                                // create white bishop 
                                ReturnPiece nWB = new ReturnPiece(); 
                                nWB.pieceType = ReturnPiece.PieceType.WB; 
                                nWB.pieceFile = newFile2; 
                                nWB.pieceRank = rank2; 
                                piecesOnBoard.add(nWB); 
                                Piece newWBishop = new Bishop(nWB);
                                piecesOnBoard2.remove(p); 
                                piecesOnBoard2.add(newWBishop);
                            }
                        }
                        else{
                            if (pawnP.equals("N")){
                                // create black knight
                                ReturnPiece nBN = new ReturnPiece(); 
                                nBN.pieceType = ReturnPiece.PieceType.BN; 
                                nBN.pieceFile = newFile2; 
                                nBN.pieceRank = rank2; 
                                piecesOnBoard.add(nBN); 
                                Piece newBKnight = new Knight(nBN);
                                piecesOnBoard2.remove(p); 
                                piecesOnBoard2.add(newBKnight); 
                            }
                            else if (pawnP.equals("Q")){
                                ReturnPiece nBQ = new ReturnPiece(); 
                                nBQ.pieceType = ReturnPiece.PieceType.BQ; 
                                nBQ.pieceFile = newFile2; 
                                nBQ.pieceRank = rank2; 
                                piecesOnBoard.add(nBQ); 
                                Piece newBQueen = new Queen(nBQ);
                                piecesOnBoard2.remove(p); 
                                piecesOnBoard2.add(newBQueen);
                            }
                            else if (pawnP.equals("R")){
                                // create black rook 
                                ReturnPiece nBR = new ReturnPiece(); 
                                nBR.pieceType = ReturnPiece.PieceType.BR; 
                                nBR.pieceFile = newFile2; 
                                nBR.pieceRank = rank2; 
                                piecesOnBoard.add(nBR); 
                                Piece newBRook = new Rook(nBR);
                                piecesOnBoard2.remove(p); 
                                piecesOnBoard2.add(newBRook);
                            }
                            else {
                                // create black bishop 
                                ReturnPiece nBB = new ReturnPiece(); 
                                nBB.pieceType = ReturnPiece.PieceType.BB; 
                                nBB.pieceFile = newFile2; 
                                nBB.pieceRank = rank2; 
                                piecesOnBoard.add(nBB); 
                                Piece newBBishop = new Bishop(nBB);
                                piecesOnBoard2.remove(p); 
                                piecesOnBoard2.add(newBBishop);
                            }

                        }
                    }
                    else {
                        if (player == Player.white){
                            // create white queen 
                            ReturnPiece nWQ = new ReturnPiece(); 
                            nWQ.pieceType = ReturnPiece.PieceType.WQ; 
                            nWQ.pieceFile = newFile2; 
                            nWQ.pieceRank = rank2; 
                            piecesOnBoard.add(nWQ); 
                            Piece newWQueen = new Queen(nWQ);
                            piecesOnBoard2.remove(p); 
                            piecesOnBoard2.add(newWQueen);

                        }
                        else{
                            // create black queen 
                            ReturnPiece nBQ = new ReturnPiece(); 
                            nBQ.pieceType = ReturnPiece.PieceType.BQ; 
                            nBQ.pieceFile = newFile2; 
                            nBQ.pieceRank = rank2; 
                            piecesOnBoard.add(nBQ); 
                            Piece newBQueen = new Queen(nBQ);
                            piecesOnBoard2.remove(p); 
                            piecesOnBoard2.add(newBQueen);
                        }
                    }
                }

                break; 

            }
        }

        if (found == false){
            // if there was no piece at that file and rank, return illegal move
            rP.message = ReturnPlay.Message.ILLEGAL_MOVE; 
            return rP; 
        }

       

        isInCheck = determineCheck(color, piecesOnBoard2);
        if (isInCheck == true) {
            if (determineCheckmate(color, piecesOnBoard2)) {
                if (color.equals("white")) {
                    rP.message = ReturnPlay.Message.CHECKMATE_WHITE_WINS;
                } else {
                    rP.message = ReturnPlay.Message.CHECKMATE_BLACK_WINS;
                }
            } else {
                rP.message = ReturnPlay.Message.CHECK;

            }

        }        

        if (player == Player.white){
            player = Player.black;
        }
        else{
            player = Player.white;
        }

        if (draw == true){
            rP.message = ReturnPlay.Message.DRAW;
        }
        rP.piecesOnBoard = piecesOnBoard; 
        return rP;

    }

    public static String [] breakString(String s) {
        int i = 0;
        while (i <= s.length() - 1) {
            char temp = s.charAt(i);
            if (temp == ' ') {
                break;
            }
        }
        String result [] = new String[2];
        result[0] = s.substring(0, i);
        result[1] = s.substring(i+1, s.length());
        return result;
    }

    public static boolean determineCheck(String color, ArrayList<Piece> piecesOnBoard2) {
        for (Piece p: piecesOnBoard2) {
            if (p.color.equals(color)) {
                if (p.hasOpponentInCheck(piecesOnBoard2)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean determineCheckmate(String color, ArrayList<Piece> piecesOnBoard2) {
        for (int rank = 8; rank >= 1; rank--) {
            for (ReturnPiece.PieceFile file = ReturnPiece.PieceFile.a; file.compareTo(ReturnPiece.PieceFile.h) <= 0; file = Piece.incrementFile(file)) {
                for (Piece p: piecesOnBoard2) {
                    if (p.ifValidMove(file, rank, piecesOnBoard2) && p.color.equals(color) == false) {
                        ArrayList<Piece> temp = miniPlay(file, rank, piecesOnBoard2, p);
                        if (determineCheck(color, temp) == false) {
                            return false;
                        }
                    }
                }
                if (file == ReturnPiece.PieceFile.h) {
                    break;
                }
            }
        }
        return true;
    }

    public static ArrayList<Piece> miniPlay(ReturnPiece.PieceFile file, int rank, ArrayList<Piece> piecesOnBoard, Piece q) {
        ArrayList<Piece> newPiecesOnBoard = new ArrayList<Piece>();
        for (Piece current: piecesOnBoard) {
            ReturnPiece r = new ReturnPiece();
            r.pieceType = current.returnpiece.pieceType;
            r.pieceFile = current.returnpiece.pieceFile;
            r.pieceRank = current.returnpiece.pieceRank;
            Piece p = getPieceFromReturnPiece(r);
            newPiecesOnBoard.add(p);
        }
        if (q.killsPiece(file, rank, newPiecesOnBoard)) {
            for (int j = 0; j < newPiecesOnBoard.size(); j++){
                Piece deadP = piecesOnBoard2.get(j); 
                if ((deadP.returnpiece.pieceFile == file) && (deadP.returnpiece.pieceRank == rank)){
                    newPiecesOnBoard.remove(j);
                    break;
                }

            }
        }
        int position = 0;
        while (position < newPiecesOnBoard.size()) {
            if ((newPiecesOnBoard.get(position).returnpiece.pieceFile == q.returnpiece.pieceFile) && (newPiecesOnBoard.get(position).returnpiece.pieceRank == q.returnpiece.pieceRank)) {
                break;
            }
            position++;
        }
        newPiecesOnBoard.get(position).returnpiece.pieceFile = file;
        newPiecesOnBoard.get(position).returnpiece.pieceRank = rank;
        return newPiecesOnBoard;

    }

    public static Piece getPieceFromReturnPiece(ReturnPiece r) {
        String name = r.pieceType.name();
        char type = name.charAt(1);
        switch(type) {
                case'P':
                return new Pawn(r);
                case'R':
                return new Rook(r);
                case'N':
                return new Knight(r);
                case'B':
                return new Bishop(r);
                case'Q':
                return new Queen(r);
                case'K':
                return new King(r);
        }
        return null;

    }

    /**
     * This method should reset the game, and start from scratch.
     */
    public static void start() {
        /* FILL IN THIS METHOD */
        //rP = new ReturnPlay();
        player = Player.white; 
        piecesOnBoard = new ArrayList<ReturnPiece>();
        ReturnPiece wn1 = new ReturnPiece();
        ReturnPiece wn2 = new ReturnPiece();
        ReturnPiece wk = new ReturnPiece (); 
        ReturnPiece wq = new ReturnPiece (); 
        ReturnPiece wb1 = new ReturnPiece(); 
        ReturnPiece wb2 = new ReturnPiece(); 
        ReturnPiece wr1 = new ReturnPiece(); 
        ReturnPiece wr2 = new ReturnPiece(); 
        ReturnPiece p1 = new ReturnPiece();
        ReturnPiece p2 = new ReturnPiece();
        ReturnPiece p3 = new ReturnPiece();
        ReturnPiece p4 = new ReturnPiece();
        ReturnPiece p5 = new ReturnPiece();
        ReturnPiece p6 = new ReturnPiece();
        ReturnPiece p7 = new ReturnPiece();
        ReturnPiece p8 = new ReturnPiece();

        p1.pieceType = ReturnPiece.PieceType.WP; 
        p1.pieceFile = ReturnPiece.PieceFile.a; 
        p1.pieceRank = 2; 
        p2.pieceType = ReturnPiece.PieceType.WP; 
        p3.pieceType = ReturnPiece.PieceType.WP; 
        p4.pieceType = ReturnPiece.PieceType.WP; 
        p5.pieceType = ReturnPiece.PieceType.WP; 
        p6.pieceType = ReturnPiece.PieceType.WP; 
        p7.pieceType = ReturnPiece.PieceType.WP; 
        p8.pieceType = ReturnPiece.PieceType.WP; 
        p2.pieceFile = ReturnPiece.PieceFile.b; 
        p3.pieceFile = ReturnPiece.PieceFile.c; 
        p4.pieceFile = ReturnPiece.PieceFile.d; 
        p5.pieceFile = ReturnPiece.PieceFile.e; 
        p6.pieceFile = ReturnPiece.PieceFile.f; 
        p7.pieceFile = ReturnPiece.PieceFile.g; 
        p8.pieceFile = ReturnPiece.PieceFile.h; 
        p2.pieceRank = 2;
        p3.pieceRank = 2; 
        p4.pieceRank = 2; 
        p5.pieceRank = 2; 
        p6.pieceRank = 2; 
        p7.pieceRank = 2; 
        p8.pieceRank = 2; 

        wr1.pieceType = ReturnPiece.PieceType.WR;
        wr2.pieceType = ReturnPiece.PieceType.WR;
        wb1.pieceType = ReturnPiece.PieceType.WB;
        wb2.pieceType = ReturnPiece.PieceType.WB;
        wn1.pieceType = ReturnPiece.PieceType.WN;
        wn2.pieceType = ReturnPiece.PieceType.WN;
        wk.pieceType = ReturnPiece.PieceType.WK;
        wq.pieceType = ReturnPiece.PieceType.WQ;

        wr1.pieceFile = ReturnPiece.PieceFile.a; 
        wb1.pieceFile = ReturnPiece.PieceFile.c; 
        wn1.pieceFile = ReturnPiece.PieceFile.b; 
        wq.pieceFile = ReturnPiece.PieceFile.d; 
        wk.pieceFile = ReturnPiece.PieceFile.e; 
        wb2.pieceFile = ReturnPiece.PieceFile.f; 
        wn2.pieceFile = ReturnPiece.PieceFile.g; 
        wr2.pieceFile = ReturnPiece.PieceFile.h; 

        wr1.pieceRank = 1; 
        wr2.pieceRank = 1; 
        wb1.pieceRank = 1; 
        wb2.pieceRank = 1; 
        wn1.pieceRank = 1; 
        wn2.pieceRank = 1; 
        wk.pieceRank = 1; 
        wq.pieceRank = 1; 

        //piecesOnBoard = new ArrayList <ReturnPiece>(); 
        piecesOnBoard.add(wk); 
        piecesOnBoard.add(wq); 
        piecesOnBoard.add(wn1);
        piecesOnBoard.add(wn2); 
        piecesOnBoard.add(wb1); 
        piecesOnBoard.add(wb2); 
        piecesOnBoard.add(wr1); 
        piecesOnBoard.add(wr2); 
        piecesOnBoard.add(p1); 
        piecesOnBoard.add(p2); 
        piecesOnBoard.add(p3); 
        piecesOnBoard.add(p4); 
        piecesOnBoard.add(p5); 
        piecesOnBoard.add(p6); 
        piecesOnBoard.add(p7); 
        piecesOnBoard.add(p8); 

        ReturnPiece bn1 = new ReturnPiece();
        ReturnPiece bn2 = new ReturnPiece();
        ReturnPiece bk = new ReturnPiece (); 
        ReturnPiece bq = new ReturnPiece (); 
        ReturnPiece bb1 = new ReturnPiece(); 
        ReturnPiece bb2 = new ReturnPiece(); 
        ReturnPiece br1 = new ReturnPiece(); 
        ReturnPiece br2 = new ReturnPiece(); 
        ReturnPiece b1 = new ReturnPiece();
        ReturnPiece b2 = new ReturnPiece();
        ReturnPiece b3 = new ReturnPiece();
        ReturnPiece b4 = new ReturnPiece();
        ReturnPiece b5 = new ReturnPiece();
        ReturnPiece b6 = new ReturnPiece();
        ReturnPiece b7 = new ReturnPiece();
        ReturnPiece b8 = new ReturnPiece();

        b1.pieceType = ReturnPiece.PieceType.BP; 
        b1.pieceFile = ReturnPiece.PieceFile.a; 
        b1.pieceRank = 7; 
        b2.pieceType = ReturnPiece.PieceType.BP; 
        b3.pieceType = ReturnPiece.PieceType.BP; 
        b4.pieceType = ReturnPiece.PieceType.BP; 
        b5.pieceType = ReturnPiece.PieceType.BP; 
        b6.pieceType = ReturnPiece.PieceType.BP; 
        b7.pieceType = ReturnPiece.PieceType.BP; 
        b8.pieceType = ReturnPiece.PieceType.BP; 
        b2.pieceFile = ReturnPiece.PieceFile.b; 
        b3.pieceFile = ReturnPiece.PieceFile.c; 
        b4.pieceFile = ReturnPiece.PieceFile.d; 
        b5.pieceFile = ReturnPiece.PieceFile.e; 
        b6.pieceFile = ReturnPiece.PieceFile.f; 
        b7.pieceFile = ReturnPiece.PieceFile.g; 
        b8.pieceFile = ReturnPiece.PieceFile.h; 
        b2.pieceRank = 7;
        b3.pieceRank = 7; 
        b4.pieceRank = 7; 
        b5.pieceRank = 7; 
        b6.pieceRank = 7; 
        b7.pieceRank = 7; 
        b8.pieceRank = 7;

        br1.pieceType = ReturnPiece.PieceType.BR;
        br2.pieceType = ReturnPiece.PieceType.BR;
        bb1.pieceType = ReturnPiece.PieceType.BB;
        bb2.pieceType = ReturnPiece.PieceType.BB;
        bn1.pieceType = ReturnPiece.PieceType.BN;
        bn2.pieceType = ReturnPiece.PieceType.BN;
        bk.pieceType = ReturnPiece.PieceType.BK;
        bq.pieceType = ReturnPiece.PieceType.BQ;

        br1.pieceFile = ReturnPiece.PieceFile.a; 
        bb1.pieceFile = ReturnPiece.PieceFile.c; 
        bn1.pieceFile = ReturnPiece.PieceFile.b; 
        bq.pieceFile = ReturnPiece.PieceFile.d; 
        bk.pieceFile = ReturnPiece.PieceFile.e; 
        bb2.pieceFile = ReturnPiece.PieceFile.f; 
        bn2.pieceFile = ReturnPiece.PieceFile.g; 
        br2.pieceFile = ReturnPiece.PieceFile.h; 

        br1.pieceRank = 8; 
        br2.pieceRank = 8; 
        bb1.pieceRank = 8; 
        bb2.pieceRank = 8; 
        bn1.pieceRank = 8; 
        bn2.pieceRank = 8; 
        bk.pieceRank = 8; 
        bq.pieceRank = 8; 

        piecesOnBoard.add(bk); 
        piecesOnBoard.add(bq); 
        piecesOnBoard.add(bn1);
        piecesOnBoard.add(bn2); 
        piecesOnBoard.add(bb1); 
        piecesOnBoard.add(bb2); 
        piecesOnBoard.add(br1); 
        piecesOnBoard.add(br2); 
        piecesOnBoard.add(b1); 
        piecesOnBoard.add(b2); 
        piecesOnBoard.add(b3); 
        piecesOnBoard.add(b4); 
        piecesOnBoard.add(b5); 
        piecesOnBoard.add(b6); 
        piecesOnBoard.add(b7); 
        piecesOnBoard.add(b8);

        piecesOnBoard2 = new ArrayList<Piece>();
        Piece whiteKing = new King(wk);
        piecesOnBoard2.add(whiteKing); 
        Piece blackKing = new King(bk); 
        piecesOnBoard2.add(blackKing); 

        Piece wPawn1 = new Pawn(p1); 
        Piece wPawn2 = new Pawn(p2);
        Piece wPawn3 = new Pawn(p3); 
        Piece wPawn4 = new Pawn(p4); 
        Piece wPawn5 = new Pawn(p5); 
        Piece wPawn6 = new Pawn(p6); 
        Piece wPawn7 = new Pawn(p7); 
        Piece wPawn8 = new Pawn(p8); 
        piecesOnBoard2.add(wPawn1);
        piecesOnBoard2.add(wPawn2); 
        piecesOnBoard2.add(wPawn3); 
        piecesOnBoard2.add(wPawn4); 
        piecesOnBoard2.add(wPawn5);
        piecesOnBoard2.add(wPawn6);
        piecesOnBoard2.add(wPawn7);
        piecesOnBoard2.add(wPawn8);

        Piece bPawn1 = new Pawn(b1);
        piecesOnBoard2.add(bPawn1);
        Piece bPawn2 = new Pawn(b2); 
        Piece bPawn3 = new Pawn(b3);
        Piece bPawn4 = new Pawn(b4); 
        Piece bPawn5 = new Pawn(b5); 
        Piece bPawn6 = new Pawn(b6); 
        Piece bPawn7 = new Pawn(b7); 
        Piece bPawn8 = new Pawn(b8); 
        piecesOnBoard2.add(bPawn2); 
        piecesOnBoard2.add(bPawn3); 
        piecesOnBoard2.add(bPawn4);
        piecesOnBoard2.add(bPawn5); 
        piecesOnBoard2.add(bPawn6);
        piecesOnBoard2.add(bPawn7);
        piecesOnBoard2.add(bPawn8); 

        Piece whiteQueen = new Queen(wq); 
        Piece blackQueen = new Queen(bq); 
        piecesOnBoard2.add(whiteQueen); 
        piecesOnBoard2.add(blackQueen); 

        Piece whiteRook1 = new Rook(wr1); 
        Piece whiteRook2 = new Rook(wr2); 
        Piece blackRook1 = new Rook(br1); 
        Piece blackRook2 = new Rook(br2); 
        piecesOnBoard2.add(whiteRook1); 
        piecesOnBoard2.add(whiteRook2); 
        piecesOnBoard2.add(blackRook1);
        piecesOnBoard2.add(blackRook2); 

        Piece whiteBishop1 = new Bishop(wb1); 
        Piece whiteBishop2 = new Bishop(wb2); 
        Piece blackBishop1 = new Bishop(bb1); 
        Piece blackBishop2 = new Bishop(bb2); 
        piecesOnBoard2.add(blackBishop1); 
        piecesOnBoard2.add(blackBishop2); 
        piecesOnBoard2.add(whiteBishop1); 
        piecesOnBoard2.add(whiteBishop2); 

        Piece whiteKnight1 = new Knight(wn1); 
        Piece whiteKnight2 = new Knight(wn2); 
        Piece blackKnight1 = new Knight(bn1); 
        Piece blackKnight2 = new Knight(bn2); 
        piecesOnBoard2.add(whiteKnight2); 
        piecesOnBoard2.add(whiteKnight1); 
        piecesOnBoard2.add(blackKnight1);
        piecesOnBoard2.add(blackKnight2); 

        message = null; 

        
    }

}


