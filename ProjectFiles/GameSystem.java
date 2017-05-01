
public class GameSystem {
	
	private static final int NO_MOVE = 0;	
	private static final int MOVE_PLAYER = 1;	
	private static final int MOVE_PLAYER_BOX = 2;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int nBox = 2; // predefined number of boxes/goals, need to extend where you specify this and generate a random map
		Map m = new Map(); //current map constructor makes predefined map
		Player player = new Player();
		ArrayList<Goal> goals = new ArrayList<Goal>;
		ArrayList<Box> boxes = new ArrayList<Box>;
		goals.add(new Goal(3,2)); // TODO
		goals.add(new Goal(4,2)); // TODO
		boxes.add(new Box(3,4)); // TODO
		boxes.add(new Box(4,4)); // TODO
		
		Position playerPos = player.getPosition();
		String direction = "Right";
		int move = m.checkMove(direction);
		if (move == NO_MOVE) { //update nothing, remove later
		} else if (move == MOVE_PLAYER) { //update player position
			player.move(direction);
		} else if (move == MOVE_PLAYER_BOX) { // update player position and box position
			for (int i = 0; i < boxes.size(); i++) { // find box whose current position is in the direction of where the player wants to move
				Box cur = boxes.get(i);
				if (cur.getPosition.comparePosition(getAdjacentCoordinate(playerPos, direction))) // found the box
					break;
			}
			player.move(direction);
			cur.move(direction);
			// TODO: checking if box has been moved to a goal
		}
	}

	private Position getAdjacentCoordinate(Position pos, String direction) {
		int x = pos.getX();
		int y = pos.getY();
		switch(direction) {
		case "Left":
			x--;
			break;
		case "Right":
			x++;
			break;
		case "Up":
			y--;
			break;
		case "Down":
			y++;
			break;
		}
		return new Position(x,y);
	}
}
