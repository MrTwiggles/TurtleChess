public class Layout {
	private Piece[][] layout;

	public Layout() {

		layout = new Piece[8][8];

		initialArrangement();
	}

	private void initialArrangement() {

		layout[0][0] = new Rook(0, 0, true);
		layout[0][1] = new Knight(0, 1, true);
		layout[0][2] = new Bishop(0, 2, true);
		layout[0][3] = new Queen(0, 3, true);
		layout[0][4] = new King(0, 4, true);
		layout[0][5] = new Bishop(0, 5, true);
		layout[0][6] = new Knight(0, 6, true);
		layout[0][7] = new Rook(0, 7, true);

		layout[7][0] = new Rook(7, 0, false);
		layout[7][1] = new Knight(7, 1, false);
		layout[7][2] = new Bishop(7, 2, false);
		layout[7][3] = new Queen(7, 3, false);
		layout[7][4] = new King(7, 4, false);
		layout[7][5] = new Bishop(7, 5, false);
		layout[7][6] = new Knight(7, 6, false);
		layout[7][7] = new Rook(7, 7, false);

		for (int i = 1; i < 8; i += 5) {
			for (int j = 0; j < 8; j++) {

				if (i == 1)
					layout[i][j] = new Pawn(i, j, true);
				else
					layout[i][j] = new Pawn(i, j, false);
			}
		}

		for (int i = 2; i < 6; i++) {
			for (int j = 0; j < 8; j++) {

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
