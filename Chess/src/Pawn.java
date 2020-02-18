import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 * Piece 
 */
public class Pawn extends Piece
{
    private Tile startingTile;

    public Pawn(String imageLocation, String color, Tile tile)
    {   
        super(imageLocation, color, tile);
        this.startingTile = tile;
    }

    // Return ArrayList of Tiles piece can move to
    // Pawn Movement Rules:
    //   - Black Pawns can only move down
    //   - White Pawns can only move up
    //   - If a pawn is on its starting tile, it can move 2 spaces
    //   - If opponent's piece is diagonally in front of it, it can move
    //   diagonally to attack it
    public ArrayList<Tile> getValidMoves(ChessBoard board)
    {       
        ArrayList<Tile> validMoves = new ArrayList<Tile>();
        int row = this.getRow();
        int col = this.getCol();
        boolean notBlockedInFront = false;

        // Pawn direction is 1 if BLACK and -1 if WHITE
        int pawnDirection = this.getColor() == "BLACK" ? 1 : -1;

        // 1) Add the tile directly in front of the pawn if able
        if (validSpot(board, row, col, row + pawnDirection, col)){
            if (board.getPieceAtLocation(row+pawnDirection, col) == null){
                validMoves.add(board.getTileAtLocation(row + pawnDirection, col));
                notBlockedInFront = true;
            }
        }

        // 2) If pawn is on starting position, add the piece two pieces in front if able
        if (this.startingTile == this.getTile()){
            if (validSpot(board, row, col, row + pawnDirection*2, col)){
                if (notBlockedInFront && board.getPieceAtLocation(row+pawnDirection*2, col) == null){
                    validMoves.add(board.getTileAtLocation(row + pawnDirection*2, col));
                }
            }
        }

        // (3) Add diagonal attack moves if able
        if (validSpot(board, row, col, row + pawnDirection, col-1 )){
            Piece maybePiece = board.getPieceAtLocation(row + pawnDirection, col-1);
            if (maybePiece != null && maybePiece.getColor() != this.getColor()){
                validMoves.add(board.getTileAtLocation(row + pawnDirection, col-1));
            }
        }
        if (validSpot(board, row, col, row + pawnDirection, col+1 )){
            Piece maybePiece = board.getPieceAtLocation(row + pawnDirection, col+1);
            if (maybePiece != null && maybePiece.getColor() != this.getColor()){
                validMoves.add(board.getTileAtLocation(row + pawnDirection, col+1));
            }
        }
        
        return validMoves;
    }


    // Get the diagonal forward attack moves
    public ArrayList<Tile> getAttackMoves(ChessBoard board){
        ArrayList<Tile> attackMoves = new ArrayList<Tile>();
        int row = this.getRow();
        int col = this.getCol();
        int pawnDirection = this.getColor() == "BLACK" ? 1 : -1;
        
        if (validSpot(board, row, col, row + pawnDirection, col+1 )){
            attackMoves.add(board.getTileAtLocation(row + pawnDirection, col+1));
        }
        if (validSpot(board, row, col, row + pawnDirection, col-1)){
            attackMoves.add(board.getTileAtLocation(row + pawnDirection, col-1));
        }
        return attackMoves;
    }
}
