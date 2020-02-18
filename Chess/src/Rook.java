import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 * Piece 
 */
public class Rook extends Piece
{
    public Rook(String imageLocation, String color, Tile tile)
    {   
        super(imageLocation, color, tile);
    }
    
    /**
     * Given a boardstate, calculate all valid moves for this piece.
     * The Rook can move horizontally and Vertically
     * @param  board [description]
     * @return Arraylist of valid movess
     */
    public ArrayList<Tile> getValidMoves(ChessBoard board)
    {       
        return getValidHorizontalAndVerticalMoves(board);
    }
}
