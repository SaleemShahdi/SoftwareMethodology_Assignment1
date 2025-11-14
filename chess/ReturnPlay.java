package chess;


/**
 * Write a description of class ReturnPlay here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.ArrayList;

class ReturnPlay {
    enum Message {ILLEGAL_MOVE, DRAW, 
                  RESIGN_BLACK_WINS, RESIGN_WHITE_WINS, 
                  CHECK, CHECKMATE_BLACK_WINS,    CHECKMATE_WHITE_WINS, 
                  STALEMATE};
    
    ArrayList<ReturnPiece> piecesOnBoard;
    Message message;
}

