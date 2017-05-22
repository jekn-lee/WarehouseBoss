import java.util.ArrayList;
import java.util.Random;

public class State {
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
	
	private Position player;
	private ArrayList<Position> boxes;
	private ArrayList<State> edges;
	
	public State() {
		this.player = null;
		this.boxes = new ArrayList<Position>();
		this.edges = new ArrayList<State>();
	}
	public State(State s) { 		//How to address the edges?
		this.player = new Position(s.player);
		this.boxes = new ArrayList<Position>();
		for (int i = 0; i < s.boxes.size(); i++) {
			this.boxes.add(new Position(s.boxes.get(i)));
		}
		this.edges = new ArrayList<State>();
	}
	
	
	public State nextPull(int[][] map, ArrayList<Goal> goals) {
		State nextPull = new State(this);
		int iterations = 0;
		int dir = 0;
		while (iterations < 1) {
			int randBox = getRandBoxIndex(goals);			//Pick a Random Box Index
			dir = getRandDir();								//Pick a Random Direction
			int curX = this.getBoxPos(randBox).getX();
			int curY = this.getBoxPos(randBox).getY();			
			if (dir == RIGHT) {
				if (this.checkRight(map, curX, curY) == true) {
					System.out.println("Pulling box at " + "(" + curX + "," + curY +") Right");
					Position player = new Position(curX+1, curY);	//Define player position
					nextPull.setPlayer(player);						//Place Player in the initial State
					iterations++;					
				}
			} else if (dir == LEFT) {
				if (this.checkLeft(map, curX, curY) == true) {
					System.out.println("Pulling box at " + "(" + curX + "," + curY +") Left");
					Position player = new Position(curX-1, curY);
					nextPull.setPlayer(player);
					iterations++;					
				}
			} else if (dir == UP) {
				if (this.checkUp(map, curX, curY) == true) {
					System.out.println("Pulling box at " + "(" + curX + "," + curY +") Up");
					Position player = new Position(curX, curY-1);
					nextPull.setPlayer(player);
					iterations++;					
				}				
			} else if (dir == DOWN) {
				if (this.checkDown(map, curX, curY) == true) {
					System.out.println("Pulling box at " + "(" + curX + "," + curY +") Down");
					Position player = new Position(curX, curY+1);
					nextPull.setPlayer(player);
					iterations++;					
				}
			}
		}
		nextPull.printState(map);
		return nextPull;
	}
	
	
	
	
	public void addBox(Position p) {
		this.boxes.add(p);
	}
	public void setPlayer(Position p) {
		this.player = p;
	}
	public void addFrontier(State s) {
		this.edges.add(s);
	}
	public Position getBoxPos(int i) {
		return this.boxes.get(i);
	}
	
	public void pullBox(String dir) {			//Player is already in position to pull the box
		int curX = this.player.getX();
		int curY = this.player.getY();
		movePlayer(dir);
		if (dir == "Left") {
			for (int i = 0; i < this.boxes.size(); i++) { //box should be to the right of the original player pos
				if (this.boxes.get(i).compareCoordinate(new Position(curX+1, curY)))
					moveBox("Left", i);
			}
		} else if (dir == "Right") {
			for (int i = 0; i < this.boxes.size(); i++) {
				if (this.boxes.get(i).compareCoordinate(new Position(curX-1, curY)))
					moveBox("Right", i);
			}
		} else if (dir == "Up") {
			for (int i = 0; i < this.boxes.size(); i++) {
				if (this.boxes.get(i).compareCoordinate(new Position(curX, curY+1)))
					moveBox("Up", i);
			}
		} else if (dir == "Down") {
			for (int i = 0; i < this.boxes.size(); i++) {
				if (this.boxes.get(i).compareCoordinate(new Position(curX, curY-1)))
					moveBox("Down", i);
			}
		}
	}
	public void movePlayer(String dir) {		// Fine
		this.player.changeCoordinate(dir);
	}
	public void moveBox(String dir, int i) {	//Move Box at index i in direction dir
		this.boxes.get(i).changeCoordinate(dir);
	}
	
	public void printMap(int[][] map) {
		int[][] temp = new int[map.length][map[0].length];
		int i, j;
		for (i = 0; i < map.length; i++) {		//Copy the template
			for (j = 0; j < map[0].length; j++) {
				temp[i][j] = map[i][j];
			}
		}
		for (i = 0; i < boxes.size(); i++) {	//Set all the boxes 
			int curX = boxes.get(i).getX();
			int curY = boxes.get(i).getY();
			temp[curY][curX] = GOAL;
		}
		int X = this.player.getX();
		int Y = this.player.getY();
		temp[Y][X] = PLAYER;					//Set the player
		printMap(temp, temp.length, temp[0].length); //Print
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
	public void printState(int[][] map) {
		System.out.print("Player at (" + this.player.getX() + "," + this.player.getY() + ")");
		for (int i = 0; i < this.boxes.size(); i++) {
			System.out.print(", Box at (" + this.boxes.get(i).getX() + "," + this.boxes.get(i).getY() + ")");			
		}
		System.out.println();
		printMap(map);
	}
	
	public void moveBoxRight(int[][] map, int i) {
		this.moveBox("Right", i);		
	}
	public void moveBoxLeft(int[][] map, int i) {
		this.moveBox("Left", i);
	}
	public void moveBoxUp(int[][] map, int i) {
		this.moveBox("Up", i);
	}
	public void moveBoxDown(int[][] map, int i) {
		this.moveBox("Down", i);
	}
	
	public boolean checkLeft(int[][] map, int x, int y) { // check adjacent 2 spaces are not a box or boundary
		if ((x-2) >= 0 && map[y][x-2] == EMPTY && map[y][x-1] == EMPTY) {
			for (int i = 0; i < this.boxes.size(); i++) {
				if (boxes.get(i).compareCoordinate(new Position(x-2,y)) == true ||
					boxes.get(i).compareCoordinate(new Position(x-1,y)) == true) {
					return false;
				}				
			}
			return true;
		}
		return false;
	}
	public boolean checkRight(int[][] map, int x, int y) {
		if ((x+2) <= (map[0].length-1) && map[y][x+2] == EMPTY && map[y][x+1] == EMPTY) {
			for (int i = 0; i < this.boxes.size(); i++) {
				if (boxes.get(i).compareCoordinate(new Position(x+2,y)) == true ||
					boxes.get(i).compareCoordinate(new Position(x+1,y)) == true) {
					return false;
				}				
			}
			return true;
		}
		return false;
	}	
	public boolean checkUp(int[][] map, int x, int y) {
		if ((y-2) >= 0 && map[y-2][x] == EMPTY && map[y-1][x] == EMPTY) {
			for (int i = 0; i < this.boxes.size(); i++) {
				if (boxes.get(i).compareCoordinate(new Position(x,y-2)) == true ||
					boxes.get(i).compareCoordinate(new Position(x,y-1)) == true) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
	public boolean checkDown(int[][] map, int x, int y) {
		if ((y+2) <= (map.length-1) && map[y+2][x] == EMPTY && map[y+1][x] == EMPTY) {
			for (int i = 0; i < this.boxes.size(); i++) {
				if (boxes.get(i).compareCoordinate(new Position(x,y+1)) == true ||
					boxes.get(i).compareCoordinate(new Position(x,y+2)) == true) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
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
}