public class GameMaker {

	public static void main(String[] args) {

		Layout layout = new Layout();

		Board board = new Board(layout, 100);
		
		ClickEventHandler clicker = new ClickEventHandler(board, layout);
	}
}
