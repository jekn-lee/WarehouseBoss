
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
		goals.add(new Goal(3,2,false)); // TODO
		goals.add(new Goal(4,2,false)); // TODO
		boxes.add(new Box(3,4,false)); // TODO
		boxes.add(new Box(4,4,false)); // TODO
		
		Scanner sc = null;
		try {
			sc = new Scanner(new FileReader(args[0]));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (sc.hasNextLine()) {
			String direction = sc.nextLine();
			makeMove(direction, player, m, boxes, goals);
			if (!checkGameState) break;
		}
		System.out.println("YOU WIN!!!");
	}

	void makeMove(String direction, Player player, Map m, ArrayList<Box> boxes, ArrayList<Goal> goals) {	
		Position playerPos = player.getPosition();
		int move = m.checkMove(direction);
		if (move == NO_MOVE) { //update nothing, remove later
		} else if (move == MOVE_PLAYER) { //update player position
			player.move(direction);
		} else if (move == MOVE_PLAYER_BOX) { // update player position and box position
			Position newPlayerPos = getAdjacentCoordinate(playerPos, direction);
			for (int i = 0; i < boxes.size(); i++) { // find box whose current position is in the direction of where the player wants to move
				Box curBox = boxes.get(i);
				Position originalBoxPos = curBox.getPosition();
				if (originalBoxPos.comparePosition(newPlayerPos)) {// found the box
					curBox.move(direction);
					break;
				}
			}
			player.move(direction);
			for (int i = 0; i < goals.size(); i++) {
				Goal curGoal = goals.get(i);
				if (goals.getPosition.comparePosition(curBox.getPosition())) { // moved box into goal
					curGoal.setSatisfied(true);
				//} else if (goals.getPosition.comparePosition(originalBoxPos)) { // moved box out of goal
				//	curGoal.setSatisfied(false);
				//}
			}
		}
	}

	boolean checkGameState(ArrayList<Goal> goals) {
		boolean finished = true;
		for (i = 0 i < goals.size(); i++) {
			if (goals.get(i).getSatisfied() == false) {
				finished = false;
				break;
			}
		}
		return finished;
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
