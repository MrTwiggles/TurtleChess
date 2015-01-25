public class Turtle {
	private SimpleWindow w;
	private double x;
	private double y;
	private boolean pen;
	private double direction = (Math.PI / 2);

	public Turtle(SimpleWindow w, double x, double y) {
		this.w = w;
		this.x = x;
		this.y = y;
	}

	public void penDown() {
		pen = true;
	}

	public void penUp() {
		pen = false;
	}

	public void forward(int n) {
		if (pen)
			w.moveTo((int) Math.round(x), (int) Math.round(y));
		x += n * Math.cos(direction);
		y -= n * Math.sin(direction);
		if (pen)
			w.lineTo((int) Math.round(x), (int) Math.round(y));
	}

	public void left(int beta) {
		this.direction += beta * (Math.PI / 180);
	}
	
	public void right(int beta) {
		this.direction -= beta * (Math.PI / 180);
	}

	public void jumpTo(int newX, int newY) {
		x = newX;
		y = newY;
	}

	public void turnNorth() {
		direction = Math.PI / 2;
	}

	public int getX() {
		return (int) x;
	}

	public int getY() {
		return (int) y;
	}

	public int getDirection() {
		return (int) Math.toDegrees(direction);

	}
}