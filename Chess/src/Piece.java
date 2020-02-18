import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.util.ArrayList;

/**
 * Piece, ?: Should this be an abstract class instead of an interface?
 */
public abstract class Piece extends JLabel
{
    String color;
    Tile currentTile;
    String imageLocation;
    boolean hasMoved;
    
    public Piece(String imageLocation, String color, Tile tile) {
    	super();
        this.setIcon(new ImageIcon(imageLocation));
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setVerticalAlignment(JLabel.CENTER);
        this.imageLocation = imageLocation;
        this.color = color;
        this.currentTile = tile;
        this.currentTile.setPiece(this);
        this.currentTile.add(this);
        this.hasMoved = false;
    }
    
    public String getColor(){
        return this.color;
    }

    public int getRow(){
        return this.currentTile.getRow();
    }

    public int getCol(){
        return this.currentTile.getCol();
    }

    public Tile getTile(){
        return this.currentTile;
    }

    // Move piece to a tile
    public void movePiece(Tile destinationTile){
        // Set previous tile to empty
        this.currentTile.setPiece(null);
        this.currentTile.remove(this);
        this.currentTile.repaint();      

        // Move the piece to the new tile
        this.currentTile = destinationTile;
        this.currentTile.setPiece(this);
        this.currentTile.add(this);
        this.setIcon(new ImageIcon(this.imageLocation));
        this.hasMoved = true;
    }

    // Return ArrayList of Tiles piece can move to
    public ArrayList<Tile> getValidMoves(ChessBoard board)
    {         
        // Should be impllemented by child class
        ArrayList<Tile> validMoves = new ArrayList<Tile>();
        return validMoves;
    }
    

    /**
     * Validate a single move
     * By default, all pieces cannot make a move if
     *  - Destination is same as current location
     *  - Destination is a King
     *  - Destination holds a same color piece
     *  - Destination is outside the board
     *  
     * Additional spot validation needs to be done on a piece-by-piece basis.
     * 
     * @param  board  Chessboard object holding state of the chessboard
     * @param  rowStart piece's initial chess row
     * @param  colStart piece's initial chess column
     * @param  rowEnd   row to move the chess piece to
     * @param  colEnd   column to move the chess piece to
     * @return true if valid, else false
     */
    public boolean validSpot(ChessBoard board, int rowStart, int colStart, int rowEnd, int colEnd)
    {   

        // Validate destination is different than current location
    	if(colStart == colEnd && rowStart == rowEnd)
    	{
    		return false;
    	}

        // Validate not outside of chessboard
    	if(colEnd > 7 || colEnd < 0 || rowEnd > 7 || rowEnd < 0)
    	{
    		return false;
    	}

        // Validate destination tile does not contain a piece of same color
        // and destination tile does not contain a King!
        Piece destinationPiece = board.getPieceAtLocation(rowEnd, colEnd);
        if (destinationPiece != null){
            if (destinationPiece.getColor() == this.getColor()){
                return false;
            }
            if (destinationPiece instanceof King){
                return false;
            }
        }
    	return true;
    }
    

    // Given a Board, return an ArrayList of all Tiles diagonal from the
    // current piece.
    public ArrayList<Tile> getValidDiagonalMoves(ChessBoard board)
    {       
        int row = this.getRow();
        int col = this.getCol();
        ArrayList<Tile> validMoves = new ArrayList<Tile>();


        // Add all valid northwest moves to array of valid moves
        int destRow = row - 1;
        int destCol = col - 1;
        while (validSpot(board, row, col, destRow, destCol)){
            Tile currTile = board.getTileAtLocation(destRow, destCol);
            validMoves.add(currTile);

            // If we run into enemy piece, we added it as a valid move, but
            // dont allow further movement in direction
            Piece currPiece = board.getPieceAtLocation(destRow, destCol);
            if (currPiece != null && currPiece.getColor() != this.getColor()){
                break;
            }
            destRow--;
            destCol--;
        }

        // Add all valid northeast moves to array of valid moves
        destRow = row - 1;
        destCol = col + 1;
        while (validSpot(board, row, col, destRow, destCol)){
            Tile currTile = board.getTileAtLocation(destRow, destCol);
            validMoves.add(currTile);

            // If we run into enemy piece, we added it as a valid move, but
            // dont allow further movement in direction
            Piece currPiece = board.getPieceAtLocation(destRow, destCol);
            if (currPiece != null && currPiece.getColor() != this.getColor()){
                break;
            }
            destRow--;
            destCol++;
        }

        // Add all valid southwest moves to array of valid moves
        destRow = row + 1;
        destCol = col - 1;
        while (validSpot(board, row, col, destRow, destCol)){
            Tile currTile = board.getTileAtLocation(destRow, destCol);
            validMoves.add(currTile);

            // If we run into enemy piece, we added it as a valid move, but
            // dont allow further movement in direction
            Piece currPiece = board.getPieceAtLocation(destRow, destCol);
            if (currPiece != null && currPiece.getColor() != this.getColor()){
                break;
            }
            destRow++;
            destCol--;
        }

        // Add all valid southeast moves to array of valid moves
        destRow = row + 1;
        destCol = col + 1;
        while (validSpot(board, row, col, destRow, destCol)){
            Tile currTile = board.getTileAtLocation(destRow, destCol);
            validMoves.add(currTile);

            // If we run into enemy piece, we added it as a valid move, but
            // dont allow further movement in direction
            Piece currPiece = board.getPieceAtLocation(destRow, destCol);
            if (currPiece != null && currPiece.getColor() != this.getColor()){
                break;
            }
            destRow++;
            destCol++;
        }
        return validMoves;
    }    

    public ArrayList<Tile> getValidHorizontalAndVerticalMoves(ChessBoard board)
    {       
        int row = this.getRow();
        int col = this.getCol();
        ArrayList<Tile> validMoves = new ArrayList<Tile>();


        // Add all valid left moves to array of valid moves
        int destRow = row;
        int destCol = col - 1;
        while (validSpot(board, row, col, destRow, destCol)){
            Tile currTile = board.getTileAtLocation(destRow, destCol);
            validMoves.add(currTile);
            // If we run into enemy piece, we added it as a valid move, but
            // dont allow further movement in direction
            Piece currPiece = board.getPieceAtLocation(destRow, destCol);
            if (currPiece != null && currPiece.getColor() != this.getColor()){
                break;
            }
            destCol--;
        }

        // Add all valid right moves to array of valid moves
        destRow = row;
        destCol = col + 1;
        while (validSpot(board, row, col, destRow, destCol)){
            Tile currTile = board.getTileAtLocation(destRow, destCol);
            validMoves.add(currTile);
            // If we run into enemy piece, we added it as a valid move, but
            // dont allow further movement in direction
            Piece currPiece = board.getPieceAtLocation(destRow, destCol);
            if (currPiece != null && currPiece.getColor() != this.getColor()){
                break;
            }
            destCol++;
        }

        // Add all valid upwards moves to valid moves
        destRow = row - 1;
        destCol = col;
        while (validSpot(board, row, col, destRow, destCol)){
            Tile currTile = board.getTileAtLocation(destRow, destCol);
            validMoves.add(currTile);
            // If we run into enemy piece, we added it as a valid move, but
            // dont allow further movement in direction
            Piece currPiece = board.getPieceAtLocation(destRow, destCol);
            if (currPiece != null && currPiece.getColor() != this.getColor()){
                break;
            }
            destRow--;
        }

        // Add all valid down moves to valid moves
        destRow = row + 1;
        destCol = col;
        while (validSpot(board, row, col, destRow, destCol)){
            Tile currTile = board.getTileAtLocation(destRow, destCol);
            validMoves.add(currTile);
            // If we run into enemy piece, we added it as a valid move, but
            // dont allow further movement in direction
            Piece currPiece = board.getPieceAtLocation(destRow, destCol);
            if (currPiece != null && currPiece.getColor() != this.getColor()){
                break;
            }
            destRow++;
        }
        return validMoves;
    }    
}

