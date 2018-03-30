class Board
{
	public static final int X_SIZE = 3;
	public static final int Y_SIZE = 3;
	int board[][];
	
	Board()
	{
		board = new int[X_SIZE][Y_SIZE];
	}
	
	public void print()
	{
		for(int x = 0; x < board.length; ++x)
		{
			for(int y = 0; y < board[x].length; ++y)
			{
				System.out.print(board[x][y] + " ");
			}
			System.out.println();
		}
	}
}