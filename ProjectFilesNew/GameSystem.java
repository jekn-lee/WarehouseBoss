import java.util.ArrayList;

public class GameSystem {
	
	private static final int NO_MOVE = 0;	
	private static final int MOVE_PLAYER = 1;	
	private static final int MOVE_PLAYER_BOX = 2;
	private Map m;
	private Player player;
	private ArrayList<Goal> goals;
	private ArrayList<Box> boxes;
	
	//public static void main(String[] args) {
		// TODO Auto-generated method stub
		//int nBox = 2; // predefined number of boxes/goals, need to extend where you specify this and generate a random map
	public GameSystem(){
		this.m = new Map();
		this.player = new Player(2,2);
		this.goals = new ArrayList<Goal>();
		this.boxes = new ArrayList<Box>();
		this.boxes.add(new Box(3,2,false));
		this.boxes.add(new Box(4,2,false));
		this.goals.add(new Goal(3,4,false));
		this.goals.add(new Goal(4,4,false));
	}
	
	public ArrayList<Integer> getCurrMap(String move){ //current map constructor makes predefined map
		
		makeMove(move, this.player, this.m, this.boxes, this.goals);
		ArrayList<Integer> rep = new ArrayList<Integer>();
		rep = this.m.getArrayRep();
		return rep;
	}

	private void makeMove(String direction, Player player, Map m, ArrayList<Box> boxes, ArrayList<Goal> goals) {	
		Position playerPos = player.getPosition();
		System.out.println("Player initially at (" + playerPos.getX() + "," + playerPos.getY() +")"); //TODO
		int move = m.checkMove(direction);
		
		System.out.println(direction);
		if (move == NO_MOVE) { //update nothing, remove later
			System.out.println("Invalid move");
		} else if (move == MOVE_PLAYER) { //update player position
			System.out.println("Moved player only"); //TODO
			Position newPlayerPos = getAdjacentCoordinate(playerPos, direction); // creating a new position object here
			System.out.println("Player moved to (" + newPlayerPos.getX() + "," + newPlayerPos.getY() +")"); //TODO
			player.move(direction);
		} else if (move == MOVE_PLAYER_BOX) { // update player position and box position
			System.out.println("Moved player and box"); //TODO
			Position newPlayerPos = getAdjacentCoordinate(playerPos, direction);
			System.out.println("Player moved to (" + newPlayerPos.getX() + "," + newPlayerPos.getY() +")"); //TODO
			Box curBox = null;
			Position originalBoxPos = null;
			for (int i = 0; i < boxes.size(); i++) { // find box whose current position is in the direction of where the player wants to move
				curBox = boxes.get(i);
				originalBoxPos = curBox.clonePosition();
				if (originalBoxPos.compareCoordinate(newPlayerPos)) {// found the box
					System.out.println("Found box to move at (" + newPlayerPos.getX() + "," + newPlayerPos.getY() +")"); //TODO
					curBox.move(direction); //TODO
					break;
				}
			}
			player.move(direction);
			for (int i = 0; i < goals.size(); i++) {
				Goal curGoal = goals.get(i);
				if (curGoal.getPosition().compareCoordinate(originalBoxPos)) { // moved box out of goal
					System.out.println("Set goal at " + "(" + curGoal.getPosition().getX() + "," + curGoal.getPosition().getY() + ") as not satisfied"); //TODO
					curGoal.setSatisfied(false);
				}
			}
			for (int i = 0; i < goals.size(); i++) {
				Goal curGoal = goals.get(i);
				if (curGoal.getPosition().compareCoordinate(curBox.getPosition())) { // moved box into goal
					System.out.println("Set goal at " + "(" + curGoal.getPosition().getX() + "," + curGoal.getPosition().getY() + ") as satisfied"); //TODO
					curGoal.setSatisfied(true);
				}
			}
		}
	}

	static boolean checkGameState(ArrayList<Goal> goals) {
		//boolean finished = true;
		int counter = 0;
		for (int i = 0; i < goals.size(); i++) {
			if (goals.get(i).getSatisfied() == true) {
				counter++;
				//finished = false;
				//break;
			}
		}
		//System.out.println(counter);
		if (counter == goals.size()) {
			return true;
		}
		return false;
	}

	static Position getAdjacentCoordinate(Position pos, String direction) {
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