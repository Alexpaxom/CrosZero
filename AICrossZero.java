class AICrossZero
{
	private BoardCalculator boardCalc;
	private Player PLAYERS[];
	private Board board;
	private int depthOfCalculation;
	
	public class Move {
		public int x = -1;
		public int y = -1;
		public long weight = 0;
		
		public void setVal(int x, int y, long weight)
		{
			this.x = x;
			this.y = y;
			this.weight = weight;
		}
		
	}
	
	//boardCalc.calcWeight(board, PLAYERS[1]);
	AICrossZero(Board board, Player pl[], int depthOfCalculation , int winCountCharacter)
	{
		this.board = board;
		PLAYERS = pl;
		boardCalc = new BoardCalculator(PLAYERS, this.board, winCountCharacter);
		this.depthOfCalculation = depthOfCalculation;
	}
	
	public int getNextPlayerId(int curentIdPlayer)
	{
		int idPlayer = (curentIdPlayer+1) % PLAYERS.length;
		return idPlayer == 0 ? 1 : idPlayer;
	}
	
	public Move findBestMove(int forPlayer)
	{
		Move mv = culcBestMove(forPlayer, forPlayer, this.depthOfCalculation);
		System.out.println(mv.x);
		System.out.println(mv.y);
		System.out.println(mv.weight);
		return mv;
	}
	
	private Move culcBestMove(int forPlayer, int curPlayer, int depth)
	{
		Move move = new Move();
		if(forPlayer != curPlayer)
			move.setVal(-1, -1, 999999999);
		else
			move.setVal(-1, -1, -999999999);
		
		if(depth == 0)
		{
			move.setVal(-1, -1, boardCalc.calcWeight(PLAYERS[forPlayer])-boardCalc.calcWeight(PLAYERS[getNextPlayerId(forPlayer)]));
			return move;
		}
		
		--depth;
		
		boolean heveMoves = false;
		
		for(int x = 0; x < board.x_size; ++x)
		{
			for(int y = 0; y < board.y_size; ++y)
			{
				if(board.getElement(x, y).getName() == PLAYERS[0].getName())
				{
					if(!heveMoves)
						heveMoves = true;
					
					board.setElement(x, y, curPlayer);
					
					if(boardCalc.isWinInPos(x, y, PLAYERS[curPlayer]))
					{
						if(forPlayer == curPlayer)
							move.setVal(x, y, boardCalc.calcWeight(PLAYERS[forPlayer])-boardCalc.calcWeight(PLAYERS[getNextPlayerId(forPlayer)]));
						else
							move.setVal(x, y, boardCalc.calcWeight(PLAYERS[forPlayer])-boardCalc.calcWeight(PLAYERS[getNextPlayerId(forPlayer)]));
						
						board.setElement(x, y, 0);
						return move;
					}
					
					Move curMove = culcBestMove(forPlayer, getNextPlayerId(curPlayer), depth);
					board.setElement(x, y, 0);
					
					if( (forPlayer == curPlayer) && (curMove.weight >  move.weight) )
					{
						// System.out.println("max: " + curMove.weight);
						move.setVal(x, y, curMove.weight);
					}
					else if( (forPlayer != curPlayer) && (curMove.weight <  move.weight) )
					{
						// System.out.println("min: " + curMove.weight);
						move.setVal(x, y, curMove.weight);
					}
				}
			}
		}
		
		if(!heveMoves)
			move.setVal(-1, -1, boardCalc.calcWeight(PLAYERS[forPlayer])-boardCalc.calcWeight(PLAYERS[getNextPlayerId(forPlayer)]));
		
		return move;
	}
}
