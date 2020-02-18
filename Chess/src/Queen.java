import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 * Behaviours for the Queen chess piece
 */
public class Queen extends Piece
{

    /**
     * Constructor for Queen
     */
    public Queen(String imageLocation, String color, Tile tile)
    {   
        super(imageLocation, color, tile);
    }

    /**
     * Given a boardstate, calculate all valid moves for this piece.
     * The Queen can move in all directions vertically and diagonally.
     * @param  board [description]
     * @return Arraylist of valid movess
     */
    public ArrayList<Tile> getValidMoves(ChessBoard board)
    {       
        ArrayList<Tile> validMoves = new ArrayList<Tile>();
        ArrayList<Tile> diagonalMoves = this.getValidDiagonalMoves(board);
        ArrayList<Tile> horizontalAndVerticalMoves = this.getValidHorizontalAndVerticalMoves(board);
        validMoves.addAll(diagonalMoves);
        validMoves.addAll(horizontalAndVerticalMoves);
        return validMoves;
    }
}
