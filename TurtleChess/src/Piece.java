import se.lth.cs.window.SimpleWindow;

public class Piece {

	protected int x;
	protected int y;
	protected boolean color;
	protected char letter = 'T';

	public Piece() {
		// TODO Auto-generated constructor stub
	}

	public void setPos(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public char getLetter() {
		return letter;
	}

	public boolean getColor() {
		return color;
	}
}
