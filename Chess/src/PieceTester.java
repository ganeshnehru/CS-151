import java.util.ArrayList;

public class PieceTester implements Tester {
    GameManager testGameManager;
    ChessBoard chessBoard;

    public void runTests(){
        System.out.println("============");
        System.out.println("");
        System.out.println("Testing All Pieces!");
        System.out.println("");
        System.out.println("============");

        testGameManager =  new GameManager();
        testGameManager.initializeGame();
        chessBoard = testGameManager.chessBoard;
        try{
            this.testKnight();
            this.testPawn();
            this.testQueen();
            this.testRook();
            this.testKing();
            this.testBishop();
        } catch(Exception e){
            System.out.println("Testing Pieces Failed!");
        }
    }

    public boolean testKnight(){
        System.out.println("====================");
        System.out.println("Testing Piece: Knight");
        System.out.println("=====================");

        // Test constructor by creating a test Knight 
        System.out.println("Knight Constructor Works:");
        Knight testKnight = (Knight)chessBoard.createPiece(
            "KNIGHT", 
            "WHITE", 
            chessBoard.getTileAtLocation(3, 3),
            testGameManager.playerOne
        );
        System.out.println("Knight Constructor works!");

        System.out.println("Testing Get Valid Knight Moves Works:");
        ArrayList<Tile> validKnightMoves = testKnight.getValidMoves(chessBoard);
        if (
        validKnightMoves.contains(chessBoard.getTileAtLocation(1, 4)) &&
        validKnightMoves.contains(chessBoard.getTileAtLocation(2, 5)) &&
        validKnightMoves.contains(chessBoard.getTileAtLocation(4, 5)) &&
        validKnightMoves.contains(chessBoard.getTileAtLocation(5, 4)) &&
        validKnightMoves.contains(chessBoard.getTileAtLocation(5, 2))){
            System.out.println("Knight Get Valid Moves Works");
            System.out.println("Knight is Working!");
            return true;
        }
        return false;
    }

    public boolean testPawn(){
        System.out.println("====================");
        System.out.println("Testing Piece: Pawn");
        System.out.println("=====================");

        // Test constructor by creating a test Pawn 
        System.out.println("Pawn Constructor Works:");
        Pawn testPawn = (Pawn)chessBoard.createPiece(
            "PAWN", 
            "WHITE", 
            chessBoard.getTileAtLocation(3, 3),
            testGameManager.playerOne
        );
        System.out.println("Pawn Constructor works!");

        System.out.println("Testing Get Valid Pawn Moves Works:");
        ArrayList<Tile> validPawnMoves = testPawn.getValidMoves(chessBoard);
        if (validPawnMoves.contains(chessBoard.getTileAtLocation(4, 3))){
            System.out.println("Pawn Get Valid Moves Works");
            System.out.println("Pawn is Working!");
            return true;
        }
        return false;
    }

    public boolean testQueen(){
        System.out.println("====================");
        System.out.println("Testing Piece: Queen");
        System.out.println("=====================");

        // Test constructor by creating a test Queen 
        System.out.println("Queen Constructor Works:");
        Queen testQueen = (Queen)chessBoard.createPiece(
            "QUEEN", 
            "WHITE", 
            chessBoard.getTileAtLocation(3, 3),
            testGameManager.playerOne
        );
        System.out.println("Queen Constructor works!");

        System.out.println("Testing Get Valid Queen Moves Works:");
        ArrayList<Tile> validQueenMoves = testQueen.getValidMoves(chessBoard);
        System.out.println("Testing Queen can move Diagonally and Horizontally");
        if (
        validQueenMoves.contains(chessBoard.getTileAtLocation(3, 5)) &&
        validQueenMoves.contains(chessBoard.getTileAtLocation(3, 1)) &&
        validQueenMoves.contains(chessBoard.getTileAtLocation(5, 3)) &&
        validQueenMoves.contains(chessBoard.getTileAtLocation(2, 3))){
            System.out.println("Queen getValidMoves includes Horizontal and Diagonal Tiles");
            System.out.println("Queen is working!");
            return true;
        }
        return false;
    }

    public boolean testRook(){
        System.out.println("====================");
        System.out.println("Testing Piece: Rook");
        System.out.println("=====================");

        // Test constructor by creating a test Rook 
        System.out.println("Rook Constructor Works:");
        Rook testRook = (Rook)chessBoard.createPiece(
            "ROOK", 
            "WHITE", 
            chessBoard.getTileAtLocation(3, 3),
            testGameManager.playerOne
        );
        System.out.println("Rook Constructor works!");

        System.out.println("Testing Get Valid Rook Moves Works:");
        ArrayList<Tile> validRookMoves = testRook.getValidMoves(chessBoard);
        System.out.println("Testing Rook can move Horizontally");
        if (
        validRookMoves.contains(chessBoard.getTileAtLocation(3, 5)) &&
        validRookMoves.contains(chessBoard.getTileAtLocation(3, 2)) &&
        validRookMoves.contains(chessBoard.getTileAtLocation(5, 3)) &&
        validRookMoves.contains(chessBoard.getTileAtLocation(2, 3))){
            System.out.println("Rook getValidMoves includes Horizontal Tiles");
            System.out.println("Rook is working!");
            return true;
        }
        return false;
    }

    public boolean testKing(){
        System.out.println("====================");
        System.out.println("Testing Piece: King");
        System.out.println("=====================");

        // Test constructor by creating a test King 
        System.out.println("King Constructor Works:");
        King testKing = (King)chessBoard.createPiece(
            "KING", 
            "WHITE", 
            chessBoard.getTileAtLocation(3, 3),
            testGameManager.playerOne
        );
        System.out.println("King Constructor works!");

        System.out.println("Testing Get Valid King Moves Works:");
        ArrayList<Tile> validKingMoves = testKing.getValidMoves(chessBoard);
        System.out.println("Testing King can move adjacently");
        if (
        validKingMoves.contains(chessBoard.getTileAtLocation(3, 4)) &&
        validKingMoves.contains(chessBoard.getTileAtLocation(3, 2)) &&
        validKingMoves.contains(chessBoard.getTileAtLocation(4, 4)) &&
        validKingMoves.contains(chessBoard.getTileAtLocation(2, 2))){
            System.out.println("King can move adjacent squares");
            System.out.println("King is working!");
            return true;
        }
        return false;
    }

    public boolean testBishop(){
        System.out.println("====================");
        System.out.println("Testing Piece: Bishop");
        System.out.println("=====================");

        // Test constructor by creating a test Bishop 
        System.out.println("Bishop Constructor Works:");
        Bishop testBishop = (Bishop)chessBoard.createPiece(
            "BISHOP", 
            "WHITE", 
            chessBoard.getTileAtLocation(3, 3),
            testGameManager.playerOne
        );
        System.out.println("Bishop Constructor works!");

        System.out.println("Testing Get Valid Bishop Moves Works:");
        ArrayList<Tile> validBishopMoves = testBishop.getValidMoves(chessBoard);
        System.out.println("Testing Bishop can move adjacently");
        if (
        validBishopMoves.contains(chessBoard.getTileAtLocation(4, 4)) &&
        validBishopMoves.contains(chessBoard.getTileAtLocation(2, 2)) &&
        validBishopMoves.contains(chessBoard.getTileAtLocation(2, 5))){
            System.out.println("Bishop can move adjacent squares");
            System.out.println("Bishop is working!");
            return true;
        }
        return false;
    }

}
