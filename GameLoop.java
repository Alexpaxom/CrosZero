class GameLoop
{
	public static final int X_SIZE = 5;
	public static final int Y_SIZE = 5;
	public static final Player PLAYERS[] = {new Player('-'), new Player('X'), new Player('O')};
	private int currentPlayer = 1;
	Board mainBoard = new Board(X_SIZE, Y_SIZE, PLAYERS);
	
	GameLoop()
	{
	}
	
	public void start()
	{
		while(true)
		{
			java.util.Scanner in = new java.util.Scanner(System.in);
			
			BoardCalculator boardCalc = new BoardCalculator(PLAYERS, mainBoard, 3);
			
			int x, y;
			// board.setElement(0, 0, 1);
			// board.setElement(1, 0, 1);
			//board.setElement(1, 0, 1);
			x = in.nextInt();
			y = in.nextInt();
			in.nextLine();
			mainBoard.setElement(x, y, 1);
			mainBoard.print();
			if(boardCalc.isWinInPos(x, y, PLAYERS[1]))
			{
				System.out.println("WIN.");
				break;
			}
			
			AICrossZero ai = new AICrossZero(mainBoard, PLAYERS, 3, 3);
			AICrossZero.Move move = ai.findBestMove(2);
			mainBoard.setElement(move.x, move.y, 2); 
			mainBoard.print();
			if(boardCalc.isWinInPos(move.x, move.y, PLAYERS[2]))
			{
				System.out.println("LUSE.");
				break;
			}
		}
	}
	
	public void makeMove(int x, int y)
	{
		if(mainBoard.getElement(x, y).getName() == PLAYERS[0].getName())
		{
			mainBoard.setElement(x, y, this.getIdCurPlayer());
			setIdCurPlayer(getNextPlayerId());
		}
	}
	
	public int getNextPlayerId()
	{
		int idPlayer = (currentPlayer+1) % PLAYERS.length;
		return idPlayer == 0 ? 1 : idPlayer;
	}
	
	
	public int getIdCurPlayer()
	{
		return currentPlayer;
	}
	
	public void setIdCurPlayer(int id)
	{
		currentPlayer = id;
	}
	
	
}
