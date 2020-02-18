import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
 
/**
* GameManager handles the rendering and maintaining the state of the game. 
* It extends JFrame and implements the MouseListener interface to allow for player actions. 
*/
public class GameManager extends JFrame implements MouseListener, MouseMotionListener
{   
    MainMenu mainMenu;
    Help help;

    // The entire game is drawn on top of this layeredPanel
	Player currentPlayer = null;
    JLayeredPane layeredPane;
    ChessBoard chessBoard;
    Piece selectedPiece = null;
    Tile selectedTile = null;
    Player playerOne = new Player("Player One", WHITE );
    Player playerTwo = new Player("Player Two", BLACK );

    /**
     * Initializes the game with a Graphics, Chessboard, pieces, and currentplayer.
     */
    public void initializeGame(){
        Dimension boardSize = new Dimension(600, 600);

        this.graphicsSetup(boardSize);

        // Initialize Chessboard
        chessBoard = new ChessBoard(boardSize, playerOne, playerTwo);
        chessBoard.initializeChessBoard();
        chessBoard.initializeChessPieces();
        currentPlayer = playerOne;
        layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
        chessBoard.setVisible(false);

        // Initialize Help Screen
        help = new Help();
        layeredPane.add(help.frame, JLayeredPane.DEFAULT_LAYER);
        help.setVisible(false);

        // Initialize Main Menu
        mainMenu = new MainMenu( chessBoard, help);
        layeredPane.add(mainMenu.frame, JLayeredPane.DEFAULT_LAYER);
        help.setMenu(mainMenu);

        mainMenu.setVisible(true);
    }


    /**
     * Perform necessary actions to setup a jframe to render the game
     * @param boardSize
     */
    public void graphicsSetup(Dimension boardSize){
        // Initialize Layered Pane where everything will be drawn
        layeredPane = new JLayeredPane();
        getContentPane().add(layeredPane);
        layeredPane.setPreferredSize(boardSize);

        // Listen to Mouse actions
        layeredPane.addMouseListener(this);
        layeredPane.addMouseMotionListener(this);
        
        // More configuration
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.pack();
        this.setResizable(true);
        this.setLocationRelativeTo( null );
        this.setVisible(true);
    }

    /**
     * Handles a mouse click on the Chessboard. Primary way of how a player interacts with
     * the Game/Chessboard. Players can click on pieces/tiles to move chess pieces. 
     * When a player clicks on a piece, it highlights the piece's available moves.
     * Each time a player makes a move, the move is validated before it is allowed.
     * @param e Java mouse event
     */
    public void mousePressed(MouseEvent e)
    {   
        Component clickedElement = chessBoard.findComponentAt(e.getX(), e.getY());
        
        // Handle user clicking on a piece
        if (clickedElement instanceof Piece){
            Piece clickedPiece =  (Piece)clickedElement;
            selectedTile = clickedPiece.getTile();

            // Handle attacks (when):
            //   (1) Player has already selected a piece 
            //   (2) Player clicks on a piece that is not theirs
            if (this.selectedPiece != null){
            	//Deselection
            	if(clickedPiece.color == currentPlayer.getPlayerColor())
            	{
            		selectedPiece = null;
            		selectedTile = null;
            		chessBoard.removeAllHighlights();
            	}            	
                if(clickedPiece.color != currentPlayer.getPlayerColor())
                {
                    if (this.selectedPiece.getValidMoves(chessBoard).contains(selectedTile)){                        
                        Player opponent = currentPlayer == playerOne ? playerTwo : playerOne;
                        opponent.removePiece(clickedPiece);
                        selectedTile.removePiece();
                        selectedPiece.movePiece(selectedTile);
                        chessBoard.repaint();

                        this.checkAndMakePromotions(selectedPiece);
                        this.switchPlayers();
                        selectedPiece = null;
                        selectedTile = null;
                        chessBoard.removeAllHighlights();
                        this.makeBoardstateChecks();
                        chessBoard.repaint();

                    }
                }
                return;
            }

            if (clickedPiece.color == currentPlayer.getPlayerColor()){
                this.selectedPiece = clickedPiece;
                chessBoard.highlightAvailableMoves(clickedPiece);
                selectedPiece.getTile().highlightSelf();
            }

            return;
        }

        // Handle user clicked on an empty tile
        if (clickedElement instanceof Tile){
            chessBoard.removeAllHighlights();
            selectedTile = (Tile)clickedElement;

            // Handle moving a piece:
            if (this.selectedPiece != null){
                // The piece is the current players color
                if (this.selectedPiece.getColor() == currentPlayer.getPlayerColor()){
                    

                    // Otherwise, the move is a valid one
                    if (this.selectedPiece.getValidMoves(chessBoard).contains(selectedTile)){

                        // Validate that the move would put the opponent out of check
                        Tile originalTile = this.selectedPiece.getTile();
                        this.selectedPiece.movePiece(selectedTile);  
                        this.checkAndMakePromotions(selectedPiece);

                        
                        if (chessBoard.isChecked(currentPlayer)){
                            // Player is still checked after the move. Don't allow it
                            // (by reverting the move)
                            this.selectedPiece.movePiece(originalTile);                            
                            selectedPiece = null;
                            selectedTile = null;
                            return; // Move wouldnt put opponent out of check. Ignore
                        }
    
                        // Selected a piece and clicked on empty tile -> Move piece to that tile
                        this.selectedPiece.movePiece(selectedTile);  
                        this.checkAndMakePromotions(selectedPiece);
                        
                        this.selectedPiece = null;
                        this.switchPlayers();
                        this.makeBoardstateChecks();
                    }
                }
            }
        }
        // Player clicking did not do anything. Reset state
        selectedPiece = null;
        selectedTile = null;
    }

    /**
     * Make checks for the following conditions:
     *  - Player is put in check
     *  - Player is put in checkmate
     *  - Player is checkmated
     * and handle the corresponding logic for those conditions
     */
    public void makeBoardstateChecks(){
        playerOne.isChecked = chessBoard.isChecked(playerOne) ? true : false;
        if (playerOne.isChecked){
            playerOne.getKing().getTile().setColor(RED);
        }
        else {
            playerOne.getKing().getTile().setDefaultColor();
        }

        playerTwo.isChecked = chessBoard.isChecked(playerTwo) ? true : false;
        if (playerTwo.isChecked){
            playerTwo.getKing().getTile().setColor(RED);
        } else {
            playerTwo.getKing().getTile().setDefaultColor();
        }

        if (chessBoard.isCheckmated(playerOne)){
            JOptionPane.showMessageDialog(null, "Checkmate! Player Two Wins! Thanks for playing.", "Checkmate!", JOptionPane.ERROR_MESSAGE);
            System.out.println("===============");
            System.out.println("CHECKMATE!");
            System.out.println("Player Two Wins");

        }
        if (chessBoard.isCheckmated(playerTwo)){
            JOptionPane.showMessageDialog(null, "Checkmate! Player One Wins! Thanks for playing.","Checkmate!", JOptionPane.ERROR_MESSAGE);
            System.out.println("===============");
            System.out.println("CHECKMATE!");
            System.out.println("Player One Wins");
        }
    }

    public void checkAndMakePromotions(Piece piece){
        if(piece instanceof Pawn)
        {
            String color = currentPlayer.getPlayerColor();
            if((color == "BLACK" && selectedTile.getRow() == 7) ||
                (color == "WHITE" && selectedTile.getRow() == 0))
            {
                currentPlayer.removePiece(piece);
                selectedTile.removePiece();
                Piece queen = (Queen)chessBoard.createPiece("QUEEN", color, selectedTile, currentPlayer);
                selectedTile.add(queen);
                queen.movePiece(selectedTile);
                selectedTile.validate();
            }
        }
    }

    /** 
    * Closes the ChessGame 
    */
    public void closeGame(){
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    /**
    * Switches Players
    */
    public void switchPlayers(){
        this.currentPlayer = currentPlayer == playerOne ? playerTwo : playerOne;
    }

    public static void main(String[] args)
    {   
        GameManager gameManager = new GameManager();
        gameManager.initializeGame();

    }

    private static final String BLACK = "BLACK";
    private static final String WHITE = "WHITE";
    private static final Color RED = new Color(180, 40, 40);
    
    public void mouseDragged(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
    public void mouseClicked(MouseEvent e){}
    public void mouseMoved(MouseEvent e) {}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
}