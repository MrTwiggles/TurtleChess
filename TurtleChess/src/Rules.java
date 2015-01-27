public final class Rules {

	private static Layout layout;

	private Rules() {

	}

	public static void setPointer(Board board) {

		layout = board.getLayout();
	}

	public static boolean isLegal(int x, int y, int newX, int newY) {
		
		if (isEmpty(Layout.getPiece(newX, newY)) || (isOpponent(Layout.getPiece(x, y), Layout.getPiece(newX, newY)))){
			switch (Layout.getPiece(x, y).getLetter()){
			case 'P':
				if (!Layout.getPiece(x, y).hasMoved()){
					if (Layout.getPiece(x, y).getColor() && y == newY && newX <= x + 2){
						Layout.getPiece(x, y).setMovedTrue();
						return true;
					} else if (!Layout.getPiece(x, y).getColor() && y == newY && newX <= x - 2){
						Layout.getPiece(x, y).setMovedTrue();
						return true;
					}
				} else {
					if (Layout.getPiece(x, y).getColor() && y == newY && newX <= x + 1){
						return true;
					} else if (!Layout.getPiece(x, y).getColor() && y == newY && newX <= x - 1){
						return true;
					}
				}
				break;
				
			case 'R':
				if (y == newY || x == newX){
					return true;
				}
				break;
				
			case 'B':
				if (Math.abs(x - newX) == Math.abs(y - newY)){
					return true;
				}
				break;
				
			case 'Q':
				if ((y == newY || x == newX) || Math.abs(x - newX) == Math.abs(y - newY)){
					return true;
				}
				break;
				
			case 'K':
				if (Math.abs(x - newX) <= 1 && Math.abs(y - newY) <= 1){
					return true;
				}
				break;
				
			case 'k':
				if (Math.abs(newX - x) == 2 && Math.abs(newY - y) == 1){
					return true;
				} else if (Math.abs(newX - x) == 1 && Math.abs(newY - y) == 2){
					return true;
				}
				break;
			}
		}
		return false;

	}
	
	private static boolean isEmpty (Piece target) {

		return target.getLetter() == 'T' ? true : false;
	}
	
	private static boolean isOpponent (Piece shooter, Piece target) {

		if (!isEmpty(shooter)) {
			if (shooter.getColor() != target.getColor())
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
