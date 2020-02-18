import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 * Piece 
 */
public class Knight extends Piece
{
    public Knight(String imageLocation, String color, Tile tile)
    {   
        super(imageLocation, color, tile);
    }

    /**
     * Given a boardstate, calculate all valid moves for this piece.
     * The Knight can move in an L shape
     * @param  board [description]
     * @return Arraylist of valid movess
     */    
    public ArrayList<Tile> getValidMoves(ChessBoard board)
    {       
        ArrayList<Tile> validMoves = new ArrayList<Tile>();
        int row = this.getRow();
        int col = this.getCol();

        int[][] offsets = {
            {-2, 1},
            {-1, 2},
            {1, 2},
            {2, 1},
            {2, -1},
            {1, -2},
            {-1, -2},
            {-2, -1}
        };
        
        for (int[] offset : offsets) {
            if (validSpot(board, row, col, row+offset[0], col+offset[1] )){
                validMoves.add(board.getTileAtLocation(row+offset[0],col+offset[1]));
            }
        }
        return validMoves;
    }
}
