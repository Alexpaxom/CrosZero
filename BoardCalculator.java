class BoardCalculator
{
	private long weights[];
	private int winCountCharacter;
	private Player PLAYERS[];
	private Board brd;
	
	BoardCalculator(Player players[], Board brd, int winCountCharacter)
	{
		this.brd = brd;
		this.PLAYERS = players;
		int counCells = brd.x_size*brd.y_size;
		long maxLenght = brd.x_size > brd.y_size ? brd.x_size : brd.y_size;
		int minLenght = brd.x_size < brd.y_size ? brd.x_size : brd.y_size;
		weights = new long[minLenght];
		this.winCountCharacter = winCountCharacter;
		

		weights[0] = 1;
		for(int i = 1; i < weights.length; ++i)
			weights[i] = maxLenght / i * 3 * weights[i-1];
	}
	
	public boolean isWinInPos(int x, int y, Player curPlayer)
	{
		Player board[][] = brd.getBoard();
		int sequenceLength = 0; // количество символов текущего игрока в одном из 4 направлений
		
		
		int startX = x - (winCountCharacter - 1);
		startX = startX < 0 ? 0 : startX;
		int startY = y - (winCountCharacter - 1);
		startY = startY < 0 ? 0 : startY;
		
		int endX = x + winCountCharacter;
		endX = endX >= board.length ? board.length : endX;
		int endY = y + winCountCharacter;
		endY = endY >= board[0].length ? board.length : endY;
		
		
		int x1 = 0; // переменные для итерирования
		int y1 = 0;
		
		
		for(x1 = startX; x1 < endX; ++x1)
		{	
			sequenceLength = 0;
			for(y1 = startY; y1 < endY; ++y1)
			{	
				if(board[x1][y1].getName() == curPlayer.getName())
				{
					if(++sequenceLength == winCountCharacter)
						return true;
				}
				else
					sequenceLength = 0;
					
			}
		}
		
		for(y1 = startY; y1 < endY; ++y1)
		{	
			sequenceLength = 0;
			for(x1 = startX; x1 < endX; ++x1)
			{	
				if(board[x1][y1].getName() == curPlayer.getName())
				{
					if(++sequenceLength == winCountCharacter)
						return true;
				}
				else
					sequenceLength = 0;
					
			}
		}
		
		x1 = startX;
		y1 = startY;
		sequenceLength = 0;
		while(x1 < endY && y1 < endX)
		{
			if(board[x1++][y1++].getName() == curPlayer.getName())
			{
				if(++sequenceLength == winCountCharacter)
					return true;
			}
			else
				sequenceLength = 0;
					
		}
		
		x1 = endX-1;
		y1 = startY;
		sequenceLength = 0;
		while(x1 >= startX && y1 < endX)
		{
			if(board[x1--][y1++].getName() == curPlayer.getName())
			{
				if(++sequenceLength == winCountCharacter)
					return true;
			}
			else
				sequenceLength = 0;
					
		}
		return false;
	}
	
	public long calcWeight(Player curPlayer)
	{
		Player board[][] = brd.getBoard();
		long curWeight  = 0; // общий вес доски
		int sequenceLength[] = new int[4]; // количество символов текущего игрока в одном из 4 направлений
		boolean canWinHere[] = new boolean[4]; // может ли игрок собрать выграшное количество символов в ряд если пойдет по одному из направлений
		
		for(int x = 0; x < board.length; ++x)
		{
			for(int y = 0; y < board[x].length; ++y)
			{
				// количество символов текущего игрока:
				sequenceLength[0] = 0; // вверх и вправо по диогонали
				sequenceLength[1] = 0; // вправо по горизонтали
				sequenceLength[2] = 0; // вниз и вправо по диогонали
				sequenceLength[3] = 0; // вниз по вертикали
				
				// может ли игрок собрать выграшное количество символов в ряд если пойдет:
				canWinHere[0] = true; // вверх и вправо по диогонали
				canWinHere[1] = true; // вправо по горизонтали
				canWinHere[2] = true; // вниз и вправо по диогонали
				canWinHere[3] = true; // вниз по вертикали
				
				// проверяем не упрется ли игрок в стенку если пойдет по одному из направлений
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
					
				// из текущей точки смотрим сколько возможных выиграшных вариантов
				for(int i = 0; i < winCountCharacter; ++i)
				{					
					if(canWinHere[0])// вверх и вправо по диогонали
					{
						if(curPlayer.getName() == board[x-i][y+i].getName())
							++sequenceLength[0];
						else if(PLAYERS[0].getName() != board[x-i][y+i].getName())
							canWinHere[0] = false;
					}
					
					if(canWinHere[1])// вправо по горизонтали
					{
						if(curPlayer.getName() == board[x][y+i].getName())
							++sequenceLength[1];
						else if(PLAYERS[0].getName() != board[x][y+i].getName())
							canWinHere[1] = false;
					}
					
					if(canWinHere[2])// вниз и вправо по диогонали
					{
						if(curPlayer.getName() == board[x+i][y+i].getName())
							++sequenceLength[2];
						else if(PLAYERS[0].getName() != board[x+i][y+i].getName())
							canWinHere[2] = false;
					}
					
					if(canWinHere[3])// вниз по вертикали
					{
						if(curPlayer.getName() == board[x+i][y].getName())
							++sequenceLength[3];
						else if(PLAYERS[0].getName() != board[x+i][y].getName())
							canWinHere[3] = false;
					}
				}
				// добавляем к общему весу доски те которые мы рассчитали на текущем шаге
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

	public int getWinCountCharacter()
	{
		return winCountCharacter;
	}
	
}
