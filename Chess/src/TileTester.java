import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class TileTester implements Tester {
    public void runTests(){
        try {
            System.out.println("============");
            System.out.println("");
            System.out.println("Testing Tile");
            System.out.println("");
            System.out.println("============");
            this.testTile();
        }
        catch(Exception e) {
            System.out.println("An error occurred testing GameManager:");
            System.out.println(e);
        }
    }

    public void testTile(){
        Color LIGHT_BROWN = new Color(153, 102, 0);
        Color DARK_BROWN = new Color(102, 51, 0);

        System.out.println("Testing Tile constructors: ");
        Tile whiteTile = new Tile(LIGHT_BROWN, 4, 4);
        Tile blackTile = new Tile(DARK_BROWN, 3, 3);
        System.out.println("Tile constructors works!");

        System.out.println("Testing getters and setters");
        if (whiteTile.getRow() != 4) return;
        if (whiteTile.getCol() != 4) return;
        if (blackTile.getCol() != 3) return;
        if (blackTile.getCol() != 3) return;
        System.out.println("Tile getCol and getRow methods work");

        System.out.println("Test removing and placing pieces on tiles works");
        
        Queen testPiece = new Queen("", "WHITE", whiteTile);
        whiteTile.setPiece(testPiece);
        if (!(testPiece != whiteTile.getPiece())) return;


        blackTile.setPiece(testPiece);
        if (!(testPiece != blackTile.getPiece())) return;
        System.out.println("Removing and Placing Pieces works!");

        System.out.println("Tile works!");
        System.out.println("");
    }
    
}
