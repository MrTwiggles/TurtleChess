import java.awt.Color;

public class Board {

	private SimpleWindow window;
	private Turtle turtle;
	private int scale;
	private Layout layout;

	public Board(Layout layout, int scale) {

		window = new SimpleWindow(scale * 8, scale * 8, "Chess");
		turtle = new Turtle(window, 0, 0);
		this.scale = scale;
		this.layout = layout;

		turtle.penDown();

		drawGrid();
		placePieces(layout);

	}

	private void placePieces(Layout layout) {

		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {

				placePiece(layout.getPiece(x, y), x * scale + scale / 2, y
						* scale + scale / 2);
			}
		}
	}

	private void placePiece(Piece piece, int i, int j) {

		Color B = new Color(100, 0, 0);
		Color K = new Color(100, 100, 0);
		Color k = new Color(100, 100, 100);
		Color P = new Color(0, 100, 0);
		Color Q = new Color(0, 100, 100);
		Color R = new Color(0, 0, 100);
		Color T = new Color(0, 0, 0, 0);

		turtle.jumpTo(i, j);
		window.setLineWidth(20);

		switch (piece.getLetter()) {
		case 'B':
			window.setLineColor(B);
			break;
		case 'K':
			window.setLineColor(K);
			break;
		case 'k':
			window.setLineColor(k);
			break;
		case 'P':
			window.setLineColor(P);
			break;
		case 'Q':
			window.setLineColor(Q);
			break;
		case 'R':
			window.setLineColor(R);
			break;
		case 'T':
			window.setLineColor(T);
			break;
		default:
			break;
		}

		turtle.forward(20);
	}

	public void updateWindow() {
		window.clear();
		drawGrid();
		placePieces(layout);
	}

	public int getScale() {
		return scale;
	}

	public SimpleWindow getWindow() {
		return window;
	}

	private void drawGrid() {

		window.setLineWidth(1);
		window.setLineColor(new Color(0, 0, 0));
		turtle.jumpTo(0, 0);
		turtle.turnNorth();

		for (int k = 1; k <= 4; k++) {
			turtle.right(90);
			turtle.forward(scale * 8 - 1);
		}

		turtle.right(180);
		window.setLineWidth(scale);

		Color dark = new Color(100, 100, 100, 100);
		Color light = new Color(100, 100, 100, 0);

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {

				if ((i + j) % 2 > 0) {
					window.setLineColor(dark);
				} else {
					window.setLineColor(light);
				}

				turtle.jumpTo(i * scale + scale / 2, j * scale);
				turtle.forward(scale);
			}
		}
	}
}
