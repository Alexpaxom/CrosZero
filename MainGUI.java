import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class MainGUI extends Application {

	Player PLAYERS[] = {new Player('-'), new Player('X'), new Player('O')};

	public MainGUI(){
	}

	//Player PLAYERS[] = {new Player('-'), new Player('X'), new Player('O')};
	//Board board = new Board(3, 3, PLAYERS);		
	//java.util.Scanner in = new java.util.Scanner(System.in);
	
	//BoardCalculator boardCalc = new BoardCalculator(board.x_size, board.y_size, PLAYERS, board);
	
	//int x, y;

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Первая приложуха на JavaFX");
		
		Board board = new Board(5, 5, PLAYERS);

		BorderPane mainPane = new BorderPane();
		mainPane.setCenter(new Field(board, 3));

		Scene mainScene = new Scene(mainPane, 800, 800);

		primaryStage.setScene(mainScene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}

