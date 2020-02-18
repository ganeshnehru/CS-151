import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 * Represents a tile on a chessboard. 
 * 
 */
public class Tile extends JPanel
{
	//Defined variables.
    public Piece piece; // Null if empty
    private Color tileColor; 
    private String notation = "";
    private int row;
    private int col;

    public Tile()
    {
        super();
        this.piece = null;
    }

    //Constructor
    public Tile(Color color, int row, int col)
    {
        super();
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.piece = null;
        this.row = row;
        this.col = col;
        this.tileColor = color;
        this.setBackground(color);

        // TODO: please comment what this does
        this.notation = Character.toString((char)(97+row));
        this.notation += (char)(col+48+1);
    }

    public int getRow(){
        return this.row;
    }

    public int getCol(){
        return this.col;
    }

    public Color  getTileColor()
    {
    	return this.tileColor;
    }

    public void setDefaultColor(){
        this.setBackground(this.tileColor);
    }
    
    public void setColor(Color color)
    {
       this.setBackground(color);
    }

    //Returns a removed piece.
    public Piece removePiece()
    {
        Piece p = this.piece;
        this.remove(this.piece);
        this.piece = null;
        return p;
    }
    
    //Sets a piece.
    public void setPiece(Piece p)
    {
    	this.piece = p;
    }

    public Piece getPiece(){
        return this.piece;
    }

    public void highlightSelf(){
        this.setBackground(new Color(80, 80, 200));
    }

    //Checks if there is a piece present.
    public boolean isEmpty(){
        return this.piece == null;
    }

    // Boolean check if this tile is under attack by opposite player
    public boolean isUnderAttack(ChessBoard chessBoard, Player opposingPlayer){
        ArrayList<Piece> enemyPieces = opposingPlayer.getPieces();
        boolean isAttacked = false;

        Piece originalPiece = this.getPiece();
        if (originalPiece != null)
            this.removePiece();

        for (Piece enemyPiece : enemyPieces){
            if (enemyPiece instanceof King){
                // TODO: handle edge case, so we dont get recursive loop
                continue;
            }
            if (enemyPiece instanceof Pawn){
                // Handle edge case where pawns would only be able to attack 
                // the diagonal piece if the King was to move there
                Pawn enemyPawn = (Pawn)enemyPiece;
                if (enemyPawn.getAttackMoves(chessBoard).contains(this)){
                    isAttacked = true;
                }
                continue;
            }
            if (enemyPiece.getValidMoves(chessBoard).contains(this)){
                isAttacked = true;
            }
        }

        // Add back the temporarily removed piece
        if (originalPiece != null){
            this.setPiece(originalPiece);
            originalPiece.movePiece(this);
        }

        return isAttacked;
    }

    final Color LIGHT_BROWN = new Color(153, 102, 0);
    final Color DARK_BROWN = new Color(102, 51, 0);
}
