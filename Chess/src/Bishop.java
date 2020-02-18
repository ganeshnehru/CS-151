import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.util.ArrayList;

/**
 * Piece 
 */
public class Bishop extends Piece
{
    public Bishop(String imageLocation, String color, Tile tile)
    {   
        super(imageLocation, color, tile);
    }

    /**
     * Given a boardstate, calculate all valid moves for this piece.
     * The Bishop can move diagonally
     * @param  board [description]
     * @return Arraylist of valid movess
     */    
    public ArrayList<Tile> getValidMoves(ChessBoard board)
    {       
        return this.getValidDiagonalMoves(board);
    }
}
