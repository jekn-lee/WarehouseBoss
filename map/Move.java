
public class Move {
	private Box boxMoved;
	private Position playerPos;
	private Position boxPos;
	
	public Move(Box b, Position playerPos, Position boxPos) {
		this.boxMoved = b;
		this.playerPos = playerPos;
		this.boxPos = boxPos;
	}
}
