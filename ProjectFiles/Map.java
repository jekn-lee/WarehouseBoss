import java.util.*;

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
		for(int i = 0;i<6;i++){
			this.mapRep[0][i] = UNMOVABLE;
			this.mapRep[5][i] = UNMOVABLE;
			this.mapRep[i][0] = UNMOVABLE;
			this.mapRep[i][5] = UNMOVABLE;
		}
		this.mapRep[3][2] = BOX;
		this.mapRep[4][2] =	BOX;
		
		this.mapRep[3][4] = EMPTY_GOAL;
		this.mapRep[4][4] = EMPTY_GOAL;
	}
	/**
	 * Checks if move in given direction is possible 
	 * @param direction of move
	 * @return int depending on movement
	 */
	public int checkMove(String direction){
		int move = NO_MOVE;
		int assetAtPos = getAsset(direction, 1);
		int nextAsset = UNMOVABLE;
		
		if(assetAtPos == EMPTY){
			move = MOVE_PLAYER;
			this.setAsset(direction, PLAYER, 1);
		}else if(assetAtPos == BOX){
			nextAsset = getAsset(direction, 2);
			if(nextAsset == EMPTY){
				move = MOVE_PLAYER_BOX;
				this.setAsset(direction, BOX, 2);
				this.setAsset(direction, PLAYER, 1);
			}else if(nextAsset == EMPTY_GOAL){
				move = MOVE_PLAYER_BOX;
				this.setAsset(direction, FILLED_GOAL, 2);
				this.setAsset(direction, PLAYER, 1);
			}
		}else if(assetAtPos == FILLED_GOAL){
			nextAsset = getAsset(direction, 2);
			if(nextAsset == EMPTY){
				move = MOVE_PLAYER_BOX;
				this.setAsset(direction, BOX, 2);
				this.setAsset(direction, EMPTY_GOAL, 1);
			} else if (nextAsset == EMPTY_GOAL){
				move = MOVE_PLAYER_BOX;
				this.setAsset(direction, FILLED_GOAL, 2);
				this.setAsset(direction, EMPTY_GOAL, 1);
			} else {
				move = NO_MOVE;
			}
		}else if(assetAtPos == EMPTY_GOAL){
			move = MOVE_PLAYER;
		}
		
		if((getAsset(direction, 0) == PLAYER) && (move != NO_MOVE)){
			this.setAsset(direction, EMPTY, 0);
		}
		
		if(move != NO_MOVE){
			this.changePlayerPosition(direction);
		}
		
		return move;
	}
	/**
	 * @return 1D array representing the map
	 * for mapping onto java swing
	 */
	public ArrayList<Integer> getArrayRep(){
		ArrayList<Integer> rep = new ArrayList<Integer>();
		for(int i = 0; i<6; i++){
			for(int k = 0; k<6; k++){
				rep.add(this.mapRep[k][i]);
			}
		}
		return rep;
	}
	/**
	 * @return copy of current Map state 
	 */
	public Map copyMap(){
		Map copy = new Map();
		copy.playerPosX = this.playerPosX;
		copy.playerPosY = this.playerPosY;
		for(int i = 0; i<6; i++){
			for(int k = 0; k<6; k++){
				copy.mapRep[k][i] = this.mapRep[k][i];
			}
		}
		return copy;
	}
	/**
	 * Print the current state of the map 
	 */
	public void printMap(){
		System.out.println();
		for(int i = 0; i<6; i++){
			for(int k = 0; k<6; k++){
				String s = mapRep[k][i] + " ";
				System.out.print(s);
			}
			System.out.println();
		}
	}
	/**
	 * @param direction of movement to check for asset
	 * @param steps is the number of grid points to look at
	 * @return asset at that position
	 */
	private int getAsset(String direction, int steps){
		int assetAtPos = UNMOVABLE;
		//Always surrounded by a boundary, so never accesses 
		//unauthorised data
		switch(direction){
		case "Left":
			assetAtPos = this.mapRep[this.playerPosX-steps][this.playerPosY];
			break;
		case "Right":
			assetAtPos = this.mapRep[this.playerPosX+steps][this.playerPosY];
			break;
		case "Up":
			assetAtPos = this.mapRep[this.playerPosX][this.playerPosY-steps];
			break;
		case "Down":
			assetAtPos = this.mapRep[this.playerPosX][this.playerPosY+steps];
			break;
		}	
		return assetAtPos;
	}

	/**
	 * Set asset at given direction from player
	 * @param direction of movement
	 * @param asset to be moved
	 * @param steps is the number of block points from the playerPos
	 */
	private void setAsset(String direction, int asset, int steps){
		switch(direction){
		case "Left":
			this.mapRep[this.playerPosX-steps][this.playerPosY] = asset;
			break;
		case "Right":
			this.mapRep[this.playerPosX+steps][this.playerPosY] = asset;
			break;
		case "Up":
			this.mapRep[this.playerPosX][this.playerPosY-steps] = asset;
			break;
		case "Down":
			this.mapRep[this.playerPosX][this.playerPosY+steps] = asset;
			break;
		}
	}
	/**
	 * Private method to adjust the player position
	 * @param direction of movement
	 * PRE-CONDITION: Player movement is possible
	 */
	private void changePlayerPosition(String direction){
		switch(direction){
		case "Left":
			this.playerPosX--;
			break;
		case "Right":
			this.playerPosX++;
			break;
		case "Up":
			this.playerPosY--;
			break;
		case "Down":
			this.playerPosY++;
			break;
		}
	}
}	
