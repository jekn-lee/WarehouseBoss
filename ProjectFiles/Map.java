
public class Map {
	//ints identifying current state of game map
	private static final int EMPTY = 0;	
	private static final int UNMOVABLE = 1;	
	private static final int PLAYER = 2;
	private static final int BOX = 3;	
	private static final int EMPTY_GOAL = 4;	
	private static final int FILLED_GOAL = 5;	
	
	//ints returned if move possible or not
	private static final int NO_MOVE = 0;	
	private static final int MOVE_PLAYER = 1;	
	private static final int MOVE_PLAYER_BOX = 2;
	
	private int mapRep[][];
	private int playerPosX;
	private int playerPosY;
	
	/**
	 * Map Constructor
	 * Creates pre-defined map
	 */
	public Map(){
		//set up size of map
		this.mapRep = new int[6][6];
		//set up initial player position
		this.playerPosX = 2;
		this.playerPosY = 2;
		//TO-DO build map layout
		this.mapRep[this.playerPosX][this.playerPosY] = PLAYER; 
	}
	/**
	 * Checks if move in given direction is possible 
	 * @param direction of move
	 * @return int depending on movement
	 */
	public int checkMove(String direction){
		switch(direction){
		case "Left":

			break;
		case "Right":

			break;
		case "Up":

			break;
		case "Down":

			break;
		}
		return NO_MOVE;
	}
}
