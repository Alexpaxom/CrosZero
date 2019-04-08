import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Field extends Group {
	// TODO
	Board board;		
	VBox field;
	HBox[] rows;
	BoardCalculator boardCalc;
	Button[][] cells;
	Player PLAYERS[];

	public Field(Board board, int winCountCharacter) {
		this(board.x_size, board.y_size);
		this.board = board;
		this.PLAYERS = board.getPlayers();
		this.boardCalc = new BoardCalculator(PLAYERS, board, winCountCharacter);
	}

	public Field(int count_colums, int count_rows/* Board */) {
		field = new VBox();
		cells = new Button[count_rows][count_colums];
		rows = new HBox[count_rows];
		for(int y = 0; y < count_rows; ++y) {
			rows[y] = new HBox();
			for(int x = 0; x < count_colums; ++x) {
				cells[y][x] = new Button(" ");
				
				cells[y][x].setPrefWidth(30);
				cells[y][x].setPrefHeight(30);
				
				cells[y][x].setOnAction(new CellHandler(y, x) );
				rows[y].getChildren().add(cells[y][x]);
			}
			field.getChildren().add(rows[y]);
		}

		getChildren().add(field);
	}

	class CellHandler implements EventHandler<ActionEvent> {
		private int x;
		private int y;

		public CellHandler(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public void handle(ActionEvent event) {
			makeMove(this.x, this.y, 1);
			board.print();
			if(boardCalc.isWinInPos(this.x, this.y, PLAYERS[1])) {
				System.out.println("WIN.");
			}
			
			AICrossZero ai = new AICrossZero(board, PLAYERS, 4, boardCalc.getWinCountCharacter());
			AICrossZero.Move move = ai.findBestMove(2);
			makeMove(move.x, move.y, 2); 
			board.print();
			if(boardCalc.isWinInPos(move.x, move.y, PLAYERS[2])) {
				System.out.println("LUSE.");
			}
		}

		private void makeMove(int x, int y, int playerId) {
			board.setElement(x, y, playerId);
			cells[x][y].setText(String.valueOf(PLAYERS[playerId].getName()));
		}

	}
}