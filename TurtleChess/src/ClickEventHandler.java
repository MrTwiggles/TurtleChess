public class ClickEventHandler {
	
	private int realX, realY, boardX, boardY, scale;
	private SimpleWindow window;

	public ClickEventHandler(Board board, Layout layout) {

		while (true) {

			scale = board.getScale();
			window = board.getWindow();
			
			EffectGraphics.setFramework(board, layout);
			
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

			int newX = window.getMouseX();
			int newY = window.getMouseY();


			layout.moveTo(piece, newX / scale % scale, newY / scale % scale);
			

			board.updateWindow();
		}
	}

}
