
/**
* TestRunner is responsible for running the entire test suite for the Chess Application
*/
public class TestRunner {

    public static void main(String[] args)
    {
            PieceTester pieceTester = new PieceTester();
            pieceTester.runTests();

            GameManagerTester gameManagerTester = new GameManagerTester();
            gameManagerTester.runTests();

            TileTester tileTester = new TileTester();
            tileTester.runTests();

            System.out.println("Finished Running Tests");
    }
}
