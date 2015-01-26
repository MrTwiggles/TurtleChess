public final class Rules {

	private static Layout layout;

	private Rules() {

	}

	public static void setPointer(Board board) {

		layout = board.getLayout();
	}

	public static boolean isLegal(int x, int y, int newX, int newY) {
		
		boolean color = layout.getPiece(x, y).getColor();
		
		if (color == layout.getPiece(newX, newY).getColor())
			System.out.println(color + " " + layout.getPiece(newX, newY).getColor());

		return true;

	}
	
	private static boolean isEmpty (int x, int y) {

		return layout.getPiece(x, y).getLetter() == 'T' ? true : false;
	}
	
	private static boolean isOppnonet (int x, int y, int newX, int newY) {

		if (!isEmpty(x, y)) {
			if (layout.getPiece(x, y).getColor() != layout.getPiece(newX, newY).getColor())
				return true;
			else
				return false;
		}
		return false;
	}
	
	private static boolean canPass (int x, int y, int newX, int newY) {
		return false;
	}
	
	private static boolean inRange (int x, int y, int newX, int newY) {
		return false;
	}

}
