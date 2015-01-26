public class ClickEventHandler {

	private int realX, realY, boardX, boardY, scale;
	private int newRealX, newRealY, newBoardX, newBoardY;
	private SimpleWindow window;

	public ClickEventHandler(Board board, Layout layout) {

		while (true) {

			scale = board.getScale();
			window = board.getWindow();

			EffectGraphics.setPointer(board);
			Rules.setPointer(board);

			window.waitForMouseClick();

			realX = window.getMouseX();
			realY = window.getMouseY();

			boardX = realX / scale % scale;
			boardY = realY / scale % scale;

			Piece piece = layout.getPiece(boardX, boardY);

			if (piece.getLetter() != 'T') {
				EffectGraphics.shadeActive(boardX, boardY);
			} else {
				EffectGraphics.shadeError(boardX, boardY);
			}

			window.waitForMouseClick();

			newRealX = window.getMouseX();
			newRealY = window.getMouseY();
			
			newBoardX = newRealX / scale % scale;
			newBoardY = newRealY / scale % scale;

			if (Rules.isLegal(boardX, boardY, newBoardX, newBoardY)) {
				layout.moveTo(piece, newBoardX, newBoardY);
			} else {
				EffectGraphics.shadeError(boardX, boardY);
			}

			board.updateWindow();
		}
	}
}
