class Main
{
	public static void main(String argv[])
	{
		Player PLAYERS[] = {new Player('-'), new Player('X'), new Player('O')};
		Board board = new Board(3, 3, PLAYERS);		
		java.util.Scanner in = new java.util.Scanner(System.in);
		
		BoardCalculator boardCalc = new BoardCalculator(PLAYERS, board, 3);
		
		int x, y;
		// board.setElement(0, 0, 1);
		// board.setElement(1, 0, 1);
		//board.setElement(1, 0, 1);
		while(true)
		{
			x = in.nextInt();
			y = in.nextInt();
			in.nextLine();
			board.setElement(x, y, 1);
			board.print();
			if(boardCalc.isWinInPos(x, y, PLAYERS[1]))
			{
				System.out.println("WIN.");
				break;
			}
			
			AICrossZero ai = new AICrossZero(board, PLAYERS, 3, 3);
			AICrossZero.Move move = ai.findBestMove(2);
			board.setElement(move.x, move.y, 2); 
			board.print();
			if(boardCalc.isWinInPos(move.x, move.y, PLAYERS[2]))
			{
				System.out.println("LUSE.");
				break;
			}
		}
		
		/* while(true)
		{
			System.out.flush();
			
			player = in.nextInt();
			board.print();
			board.calcWeight();
			System.out.println(board.getWeight());
			in.nextLine();
		} */
	}
}
