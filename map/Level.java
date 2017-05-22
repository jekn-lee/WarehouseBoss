import java.util.ArrayList;
import java.util.Random;

public class Level {

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";	

	private static final int UP = 3; // using numpad notation	
	private static final int DOWN = 0;	
	private static final int LEFT = 1;
	private static final int RIGHT = 2;
	
	private static final int BOUNDARY = 1;
	private static final int EMPTY = 0;
	private static final int GOAL = 2;
	private static final int PLAYER = 3;
	
	private int[][] map;
	private ArrayList<Goal> goals;
	private ArrayList<Box> boxes;

	public Level(int[][] map, int nBox) {
		this.map = map;
		this.goals = new ArrayList<Goal>();
		this.boxes = new ArrayList<Box>();
		this.generateLevel(nBox, map);
	}
	
	private void generateLevel(int nBox, int[][] map) {
		Graph g = initialiseGraph(map, nBox);
		g.printGraph();
		System.out.println();
		
		System.out.println("Calculating the next pull");
		g.getState(1).nextPull(map, goals);

		//while (iterations < 50) {
			// pick a random box
			// pick a random direction
			// check it is valid
			
			// move to the space using graph search
		//}
	}
		

	private Graph initialiseGraph(int[][] map, int nBox) {
		Graph g = new Graph(map);
		State initial = new State();
		Random rnd = new Random();
		int height = this.map.length;
		int length = this.map[0].length;
		int counter = 0;
		
		// Generate random goals
		while (counter != nBox) {
			int i = 0;
			int x = rnd.nextInt(length); 							//Generate random coordinate
			int y = rnd.nextInt(height);
			if (map[x][y] == EMPTY) { 									//Space is empty - Put a goal in it
				for (i = 0; i < this.goals.size(); i++) {
					if (this.goals.get(i).getPosition().compareCoordinate(new Position(x,y))) {
						break;
					}
				}
				if (i == this.goals.size()) { //Ensure you are not placing a goal in the same space
					System.out.println("Putting goal at (" + x + "," + y + ")");
					this.goals.add(new Goal(x,y,false)); 					// Add Randomised Goal to Level object
					g.addGoals(new Position(x,y));						// Add Randomised Goal to Graph
					initial.addBox(new Position(x,y));					// Place Box in Randomised Goal in the initial State
					counter++;
				}
			}
		}
		
		// Pick a random box to move in a random direction to put a player in and finalise the initial state
		int iterations = 0;
		int dir = 0;
		while (iterations < 1) {
			int randBox = getRandBoxIndex(goals);			//Pick a Random Box Index
			dir = getRandDir();								//Pick a Random Direction
			int curX = initial.getBoxPos(randBox).getX();
			int curY = initial.getBoxPos(randBox).getY();			
			if (dir == RIGHT) {
				if (initial.checkRight(map, curX, curY) == true) {
					System.out.println("Pulling box at " + "(" + curX + "," + curY +") Right");
					Position player = new Position(curX+1, curY);	//Define player position
					initial.setPlayer(player);						//Place Player in the initial State
					iterations++;		
				}
			} else if (dir == LEFT) {
				if (initial.checkLeft(map, curX, curY) == true) {
					System.out.println("Pulling box at " + "(" + curX + "," + curY +") Left");
					Position player = new Position(curX-1, curY);
					initial.setPlayer(player);
					iterations++;					
				}
			} else if (dir == UP) {
				if (initial.checkUp(map, curX, curY) == true) {
					System.out.println("Pulling box at " + "(" + curX + "," + curY +") Up");
					Position player = new Position(curX, curY-1);
					initial.setPlayer(player);
					iterations++;					
				}				
			} else if (dir == DOWN) {
				if (initial.checkDown(map, curX, curY) == true) {
					System.out.println("Pulling box at " + "(" + curX + "," + curY +") Down");
					Position player = new Position(curX, curY+1);
					initial.setPlayer(player);
					iterations++;					
				}
			}
		}
		g.addState(initial); 				//Add initial state
		
		
		State next = new State(initial);	//Create next state where the player pulls the box
		String s = null;
		if (dir == UP) {
			s = "Up";
		} else if (dir == DOWN) {
			s = "Down";
		} else if (dir == LEFT) {
			s = "Left";
		} else if (dir == RIGHT) {
			s = "Right";
		}
		next.pullBox(s);
		g.addState(next);					//Add next state to the graph
		return g;
	}
	
		// pick a random box
		// pick a random direction
		// verify if that box can be moved in that direction
		// pull the box in that direction
		
		
		// pick another random box
		// pick another random direction
		// verify if that box can be moved in that direction
		// find the path to the position to pull that box in that direction
			// perform a graph search to get to it
				// how to detect if it isn't possible?
	
	private static int getRandBoxIndex(ArrayList<Goal> goals) {
		Random rnd = new Random();
		int pick = rnd.nextInt(goals.size());
		return pick;
	}

	private static int getRandDir() {
		Random rnd = new Random();
		int dir = rnd.nextInt(4);
		return dir;
	}
	
	private static void printMap(int[][] map, int height, int length) {
		int i, j;
		for (i = 0; i < length; i++) { // go through each row first
			for (j = 0; j < height; j++) { // go through each column
				if (map[i][j] == BOUNDARY) {
					System.out.print("[" + map[i][j] + "] ");
				} else if (map[i][j] == 0){
					System.out.print(ANSI_WHITE + "[" + map[i][j] + "] " + ANSI_RESET);
				} else if (map[i][j] == GOAL) {
					System.out.print(ANSI_CYAN + "[" + map[i][j] + "] " + ANSI_RESET);
				} else if (map[i][j] == PLAYER) {
					System.out.print(ANSI_RED + "[" + map[i][j] + "] " + ANSI_RESET);
				}
			}
			System.out.print("\n");
		}
	}
	
	private static boolean checkLeft(int[][] map, int x, int y) {
		if ((x-2) >= 0 && map[y][x-2] == EMPTY && map[y][x-1] == EMPTY) return true;
		return false;
	}
	private static boolean checkRight(int[][] map, int x, int y) {
		if ((x+2) <= (map[0].length-1) && map[y][x+2] == EMPTY && map[y][x+1] == EMPTY) return true;
		return false;
	}
	private static boolean checkUp(int[][] map, int x, int y) {
		if ((y-2) >= 0 && map[y-2][x] == EMPTY && map[y-1][x] == EMPTY) return true;
		return false;
	}
	private static boolean checkDown(int[][] map, int x, int y) {
		if ((y+2) <= (map.length-1) && map[y+2][x] == EMPTY && map[y+1][x] == EMPTY) return true;
		return false;
	}
}
