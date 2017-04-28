
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
		this.mapRep[2][3] = BOX;
		this.mapRep[2][4] =	BOX;
		
		this.mapRep[4][3] = EMPTY_GOAL;
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
			//need to adjust map
		}else if(assetAtPos == BOX){
			nextAsset = getAsset(direction, 2);
			if(nextAsset == EMPTY){
				//move box and player
			}else if(nextAsset == EMPTY_GOAL){
				//change empty goal to filled
				//move box, box becomes filled goal
				//move player 
			}else{
				move = NO_MOVE;
			}
		}else if(assetAtPos == FILLED_GOAL){
			
		}
		return move;
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
			assetAtPos = this.mapRep[this.playerPosX+steps][this.playerPosY];
			break;
		case "Right":
			assetAtPos = this.mapRep[this.playerPosX-steps][this.playerPosY];
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
	 * adjust the asset placement on the map
	 * @param direction of movement
	 * @param asset to be moved
	 * @param steps is the number of block points from the playerPos
	 */
	private void moveAsset(String direction, int asset, int steps){
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
	}
}
