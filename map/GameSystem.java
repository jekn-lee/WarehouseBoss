import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class GameSystem {
	
	private static final int NO_MOVE = 0;	
	private static final int MOVE_PLAYER = 1;	
	private static final int MOVE_PLAYER_BOX = 2;
	
	private static final int BOUNDARY = 1;
	private static final int EMPTY = 0;
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
	public static final int LENGTH = 6; // 5 is the lower limit
	public static final int HEIGHT = 6;
	
	public static void main(String[] args) {
		/*int nBox = 2; // predefined number of boxes/goals, need to extend where you specify this and generate a random map
		
		Map m = new Map(); //current map constructor makes predefined map
		
		Player player = new Player(2,2);
		ArrayList<Goal> goals = new ArrayList<Goal>();
		ArrayList<Box> boxes = new ArrayList<Box>();
		boxes.add(new Box(3,2,false));
		boxes.add(new Box(4,2,false));
		goals.add(new Goal(3,4,false));
		goals.add(new Goal(4,4,false));
		
		Scanner sc = null;
		try {
			sc = new Scanner(new FileReader(args[0]));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Starting map:");
		m.printMap();
		while (sc.hasNextLine()) {
			String direction = sc.nextLine();
			makeMove(direction, player, m, boxes, goals);
			m.printMap();
			if (checkGameState(goals)) break;
		}
		if (checkGameState(goals)) {
			System.out.println("YOU WIN!!!");
		} else {
			System.out.println("YOU LOSE");
		}*/
		
		int[][] map = new int[HEIGHT][LENGTH]; // row, column
		int i, j;
		for (j = 0; j < LENGTH; j++) {
			map[0][j] = BOUNDARY; // set boundaries in first row
			map[HEIGHT-1][j] = BOUNDARY; // set boundaries in last row
		}
		for (i = 0; i < HEIGHT; i++) {
			map[i][0] = BOUNDARY; // set boundaries in first column
			map[i][LENGTH-1] = BOUNDARY; // set boundaries in last column
		}
		System.out.println("Initial template is:");
		printMap(map);
		
		Level lvl = new Level(map, 3);
	}
	
	private static void printMap(int[][] map) {
		int i, j;
		for (i = 0; i < HEIGHT; i++) { // go through each row first
			for (j = 0; j < LENGTH; j++) { // go through each column
				if (map[i][j] == BOUNDARY) {
					System.out.print("[" + map[i][j] + "] ");
				} else {
					System.out.print(ANSI_WHITE + "[" + map[i][j] + "] " + ANSI_RESET);
				}
			}
			System.out.print("\n");
		}
	}
	
	
	static void makeMove(String direction, Player player, Map m, ArrayList<Box> boxes, ArrayList<Goal> goals) {	
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