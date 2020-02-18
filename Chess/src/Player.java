import java.util.ArrayList;

/**
 * Player class that represents a player in a game of chess
 */
public class Player
{
    private String name;
    private String playerColor;
    private ArrayList<Piece> pieces;
    private Piece myKing;
    public boolean isChecked = false;

    public Player(String name, String playerColor)
    {
        this.name = name;
        this.playerColor = playerColor;
        this.pieces = new ArrayList<Piece>();
    }

    public Player(String name, String playerColor, ArrayList<Piece> pieces)
    {
        this.name = name;
        this.playerColor = playerColor;
        this.pieces = pieces;
    }

    public String getName(){
        return this.name;
    }

    public String getPlayerColor(){
        return this.playerColor;
    }

    public void addPiece(Piece piece){
        this.pieces.add(piece);
        if (piece instanceof King){
            this.myKing = piece;
        }
    }

    public void removePiece(Piece pieceToRemove){
        this.pieces.remove(pieceToRemove);
    }

    public ArrayList<Piece> getPieces(){
        return this.pieces;
    }

    public Piece getKing(){
        return this.myKing;
    }
}