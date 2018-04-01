class BoardCalculator
{
	private int weights[];
	private int winCountCharacter = 3;
	private Player PLAYERS[];
	
	BoardCalculator(int size_x, int size_y, Player players[])
	{
		this.PLAYERS = players;
		int counCells = size_x*size_y;
		int maxLenght = size_x > size_y ? size_x : size_y;
		int minLenght = size_x < size_y ? size_x : size_y;
		weights = new int[minLenght];
		
		/*
		weights[0] = int(maxLenght/2)*3;
		for(int i = 1; i < weights.length; ++i)
			weights[i] = int(maxLenght/(2+i))*3*weights[i-1];
		*/
		
		weights[0] = counCells;
		for(int i = 1; i < weights.length; ++i)
			weights[i] = counCells*weights[i-1];
	}
	
	public int calcWeight(Player board[][], Player curPlayer)
	{
		int curWeight  = 0;
		int sequenceLength[] = new int[4];
		boolean canWinHere[] = new boolean[4];
		
		for(int x = 0; x < board.length; ++x)
		{
			for(int y = 0; y < board[x].length; ++y)
			{
				sequenceLength[0] = 0;
				sequenceLength[1] = 0;
				sequenceLength[2] = 0;
				sequenceLength[3] = 0;
				
				canWinHere[0] = true;
				canWinHere[1] = true;
				canWinHere[2] = true;
				canWinHere[3] = true;
				
				if(x -(winCountCharacter-1) < 0)
					canWinHere[0] = false;
				
				if(y + (winCountCharacter) > board[x].length)
				{
					canWinHere[0] = false;
					canWinHere[1] = false;
					canWinHere[2] = false;
				}
				if(x + (winCountCharacter) > board.length)
				{
					canWinHere[2] = false;
					canWinHere[3] = false;
				}
					
				
				for(int i = 0; i < winCountCharacter; ++i)
				{					
					if(canWinHere[0])
					{
						if(curPlayer.getName() == board[x-i][y+i].getName())
							++sequenceLength[0];
						else if(PLAYERS[0].getName() != board[x-i][y+i].getName())
							canWinHere[0] = false;
					}
					
					if(canWinHere[1])
					{
						if(curPlayer.getName() == board[x][y+i].getName())
							++sequenceLength[1];
						else if(PLAYERS[0].getName() != board[x][y+i].getName())
							canWinHere[1] = false;
					}
					
					if(canWinHere[2])
					{
						if(curPlayer.getName() == board[x+i][y+i].getName())
							++sequenceLength[2];
						else if(PLAYERS[0].getName() != board[x+i][y+i].getName())
							canWinHere[2] = false;
					}
					
					if(canWinHere[3])
					{
						if(curPlayer.getName() == board[x+i][y].getName())
							++sequenceLength[3];
						else if(PLAYERS[0].getName() != board[x+i][y].getName())
							canWinHere[3] = false;
					}
				}
				
				if(canWinHere[0] && sequenceLength[0] > 0)
					curWeight += weights[sequenceLength[0] - 1];
				if(canWinHere[1] && sequenceLength[1] > 0)
					curWeight += weights[sequenceLength[1] - 1];
				if(canWinHere[2] && sequenceLength[2] > 0)
					curWeight += weights[sequenceLength[2] - 1];
				if(canWinHere[3] && sequenceLength[3] > 0)
					curWeight += weights[sequenceLength[3] - 1];
			}
		}
		
		return curWeight;
	}
	
}