
public class Layout {
	private Piece[][] layout;

	public Layout() {

		initialArrangement();
	}

	private void initialArrangement() {

		layout = new Piece[8][8];

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {

				// Bönder
				if (i == 1) {
					layout[i][j] = new Pawn(i, j, true);
				}
				if (i == 6) {
					layout[i][j] = new Pawn(i, j, false);
				}
				// Torn
				if ((i == 0 && j == 0) || (i == 0 && j == 7)
						|| (i == 7 && j == 0) || (i == 7 && j == 7)) {
					if (i == 0) {
						layout[i][j] = new Rook(i, j, true);
					} else {
						layout[i][j] = new Rook(i, j, false);
					}
				}
				// Hästar
				if ((i == 0 && j == 1) || (i == 0 && j == 6)
						|| (i == 7 && j == 1) || (i == 7 && j == 6)) {
					if (i == 0) {
						layout[i][j] = new Knight(i, j, true);
					} else {
						layout[i][j] = new Knight(i, j, false);
					}
				}
				// Löpare
				if ((i == 0 && j == 2) || (i == 0 && j == 5)
						|| (i == 7 && j == 2) || (i == 7 && j == 5)) {
					if (i == 0) {
						layout[i][j] = new Bishop(i, j, true);
					} else {
						layout[i][j] = new Bishop(i, j, false);
					}
				}
				// Dam
				if ((i == 0 && j == 4) || (i == 7 && j == 3)) {
					if (i == 0) {
						layout[i][j] = new Queen(i, j, true);
					} else {
						layout[i][j] = new Queen(i, j, false);
					}
				}
				// Knug
				if ((i == 0 && j == 3) || (i == 7 && j == 4)) {
					if (i == 0) {
						layout[i][j] = new King(i, j, true);
					} else {
						layout[i][j] = new King(i, j, false);
					}
				}

				if (i != 1 && i != 0 && i != 7 & i != 6)
					layout[i][j] = new Piece();
			}
		}
	}

	public void moveTo(Piece piece, int x, int y) {
		layout[x][y] = piece;
		layout[piece.getX()][piece.getY()] = new Piece();
		piece.setPos(x, y);
	}

	public Piece getPiece(int x, int y) {
		return layout[x][y];
	}
}
