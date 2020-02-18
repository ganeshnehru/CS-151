
public class GameManagerTester implements Tester {
    public void runTests(){
        try {
            System.out.println("============");
            System.out.println("");
            System.out.println("Testing GameManager!");
            System.out.println("");
            System.out.println("============");
            this.testGameManagerConstructor();
            this.testGameManagerSwitchPlayer();
        }
        catch(Exception e) {
            System.out.println("An error occurred testing GameManager:");
            System.out.println(e);
        }
    }

    public boolean testGameManagerConstructor(){
        System.out.println("Testing Game Manager Initializes Correctly");
        GameManager gameManager =  new GameManager();
        gameManager.initializeGame();
        System.out.println("Game Manager Initializes Correctly!");
        gameManager.closeGame();
        return true;
    }

    public boolean testGameManagerSwitchPlayer(){
        System.out.println("Testing Game Manager Switches Player Correctly");
        GameManager gameManager =  new GameManager();
        gameManager.initializeGame();

        Player currentPlayer = gameManager.currentPlayer;
        gameManager.switchPlayers();
        assert currentPlayer != gameManager.currentPlayer;

        System.out.println("Testing Game Manager Switches Player Correctly!");
        gameManager.closeGame();
        return true;
    }
}
