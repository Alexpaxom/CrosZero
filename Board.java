class Board
{
	private Player PLAYERS[];
	private Player board[][];

	public int x_size = 0;
	public int y_size = 0;
	
	
	Board(int x_size, int y_size, Player players[])
	{
		this.x_size = x_size;
		this.y_size = y_size;
		
		board = new Player[this.x_size][this.y_size];
		PLAYERS = players;
		
		for(int x = 0; x < board.length; ++x)
		{
			for(int y = 0; y < board[x].length; ++y)
			{
				board[x][y] = PLAYERS[0];
			}
		}
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
	
	public void setElement(int x_pos, int y_pos, int playerId)
	{
		board[x_pos][y_pos] = PLAYERS[playerId];
	}
	
	public Player getElement(int x_pos, int y_pos)
	{
		return board[x_pos][y_pos];
	}
	
	
	public Player[][] getBoard()
	{
		return board;
	}

	public Player[] getPlayers()
	{
		return PLAYERS;
	}
}
