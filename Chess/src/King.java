import java.util.ArrayList;

/**
 * Piece 
 */
public class King extends Piece
{
    Player opposingPlayer;

    public King(String imageLocation, String color, Tile tile, Player oppPlayer)
    {   
        super(imageLocation, color, tile);
        this.opposingPlayer = oppPlayer;
    }

    /**
    *  Return ArrayList of Tiles piece can move to
    *  King is a unique piece for movement rules
    *  - King can move all directions one space
    *  - King can never move on a tile under attack by an enemy piece
    *  - King can perform a "castling" maneuver if it never moved from its original
    * place and a rook is on its original space.
    * @param  board [description]
    * @return       [description]
    */
    public ArrayList<Tile> getValidMoves(ChessBoard board)
    {   
        int row = this.getRow();
        int col = this.getCol();

        ArrayList<Tile> validMoves = new ArrayList<Tile>();
        int[][] offsets = {
            {-1, -1},
            {-1, 0},
            {-1, 1},
            {0, -1},
            {0, 1},
            {1, -1},
            {1, 0},
            {1, 1}
        };

        for (int[] offset : offsets) {
            if (validSpot(board, row, col, row+offset[0], col+offset[1] )){
                Tile tileToCheck = board.getTileAtLocation(row+offset[0],col+offset[1]);

                // Temporarily remove any piece on the tile we are going to check
                Piece originalPiece = tileToCheck.getPiece();
                if (originalPiece != null)
                    tileToCheck.removePiece();

                // If the tile is not under attack, add it as a valid move!
                if (tileToCheck.isUnderAttack(board, opposingPlayer) == false)
                    validMoves.add(tileToCheck);

                // Add back the temporarily removed piece
                if (originalPiece != null){
                    tileToCheck.setPiece(originalPiece);
                    originalPiece.movePiece(tileToCheck);
                }
            }
        }
        return validMoves;
    }
}
