package ChessModel;

public class Chess {
	private int color;//1-white，0-black,-1-null
	private boolean placed = false;
	
	public Chess(int color, boolean placed) {
		this.color = color;
		this.placed = placed;
	}
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public boolean isPlaced() {
		return placed;
	}
	public void setPlaced(boolean placed) {
		this.placed = placed;
	}
}
