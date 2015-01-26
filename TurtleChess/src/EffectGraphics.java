import java.awt.Color;


public final class EffectGraphics {
	
	private static int scale;
	private static Layout boardLayout;
	private static SimpleWindow window;
	private static Turtle turtle;
	
	private EffectGraphics () {
		
	}
	
	public static void setFramework (Board board, Layout layout) {
		
		scale = board.getScale();
		boardLayout = layout;
		window = board.getWindow();
		turtle = new Turtle(window, 0, 0);
		turtle.penDown();
		
		window.setLineWidth(scale);
		
	}
	
	public static void shadeActive (int boardX, int boardY) {
		
		window.setLineColor(new Color(0, 0, 255, 80));
		
		turtle.jumpTo(scale * boardX, scale * boardY + scale / 2);
		turtle.right(90);
		turtle.forward(scale);
		
		window.setLineWidth(1);
		
		turtle.penUp();
		
		turtle.left(180);
		turtle.forward(scale);
		turtle.right(90);
		turtle.forward(scale/2);
		
		turtle.penDown();
		
		for (int k = 1; k <= 4; k++) {
			turtle.right(90);
			turtle.forward(scale - 1);
		}
	}

	public static void shadeError(int boardX, int boardY) {
		
		window.setLineColor(new Color(255, 0, 0, 80));
		
		turtle.jumpTo(scale * boardX, scale * boardY + scale / 2);
		turtle.right(90);
		turtle.forward(scale);
		
		window.setLineWidth(1);
		
		turtle.penUp();
		
		turtle.left(180);
		turtle.forward(scale);
		turtle.right(90);
		turtle.forward(scale/2);
		
		turtle.penDown();
		
		for (int k = 1; k <= 4; k++) {
			turtle.right(90);
			turtle.forward(scale - 1);
		}
	}
}
