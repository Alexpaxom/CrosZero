class Board
{
	public static final int X_SIZE = 5;
	public static final int Y_SIZE = 5;
	
	public static final Player PLAYERS[] = {new Player('-'), new Player('X'), new Player('O')};
	private Player board[][];
	BoardCalculator boardCalc = new BoardCalculator(X_SIZE, Y_SIZE, PLAYERS);
	private boolean weightCalc = false;
	private int boardWeight = 0;
	
	
	Board()
	{
		board = new Player[X_SIZE][Y_SIZE];
		for(int x = 0; x < board.length; ++x)
		{
			for(int y = 0; y < board[x].length; ++y)
			{
				board[x][y] = PLAYERS[0];
			}
		}
		weightCalc = false;
	}
	
	public void print()
	{
		for(int x = 0; x < board.length; ++x)
		{
			for(int y = 0; y < board[x].length; ++y)
			{
				System.out.print(board[x][y].getName() + " ");
			}
			System.out.println();
		}
	}
	
	public void setPlayer(int x_pos, int y_pos, int playerId)
	{
		weightCalc = false;
		
		board[x_pos][y_pos] = PLAYERS[playerId];
	}
	
	public Player getPlayer(int x_pos, int y_pos)
	{
		return board[x_pos][y_pos];
	}
	
	public void calcWeight()
	{
		boardWeight = boardCalc.calcWeight(board, PLAYERS[1]);
		weightCalc = true;
	}
	
	public int getWeight()
	{
		return boardWeight;
	}
}