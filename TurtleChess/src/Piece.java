

public class Piece {

	protected boolean hasMoved;
	
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

	
	//Behövs för Pawn:
	public boolean hasMoved(){
		return hasMoved;
	}
	
	public void setMovedTrue(){
		this.hasMoved = true;
	}
}
