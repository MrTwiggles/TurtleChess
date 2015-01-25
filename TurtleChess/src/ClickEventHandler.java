public class ClickEventHandler {

	public ClickEventHandler(Board board, Layout layout) {

		while (true) {

			SimpleWindow window = board.getWindow();

			int scale = board.getScale();
			window.waitForMouseClick();

			int x = window.getMouseX();
			int y = window.getMouseY();

			Piece piece = layout.getPiece(x / scale % scale, y / scale % scale);

			window.waitForMouseClick();

			int newX = window.getMouseX();
			int newY = window.getMouseY();

			if (piece.getLetter() != 'T') {
				layout.moveTo(piece, newX / scale % scale, newY / scale % scale);
			}

			else {
			}

			board.updateWindow();

			window.moveTo(x, y);
			window.writeText("(" + x / scale % scale + ", " + y / scale % scale
					+ ")");
		}
	}

}
