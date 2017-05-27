import java.util.Random;

public class Background {

	private int mapRep[][];
	private int xLength;
	private int yLength;
	private int xBoxLength;
	private int yBoxLength;
	
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
	
	public Background(int xLength, int yLength){
		this.mapRep = new int[yLength][xLength];
		this.xLength = xLength;
		this.yLength = yLength;
		this.xBoxLength = xLength / 3;
		this.yBoxLength = yLength / 3;
		
		this.setBoundary();
		this.setTMap();
		
		Random rand = new Random();
		if(!this.checkValidityColumn() || !this.checkValidityRow()){
			while (!this.checkValidityRow()){
				int xBegin = rand.nextInt(xLength - 1) + 1;
				int yBegin = rand.nextInt(yLength - 1) + 1;
				this.insertBlankSpace(xBegin, yBegin, this.getxBoxLength(), this.getyBoxLength());
				this.insertOneBoundary(xBegin, yBegin);
			}
			while(!this.checkValidityColumn()){
				int xBegin = rand.nextInt(xLength - 1) + 1;
				int yBegin = rand.nextInt(yLength - 1) + 1;
				this.insertBlankSpace(xBegin, yBegin, this.getxBoxLength(), this.getyBoxLength());
				this.insertOneBoundary(xBegin, yBegin);
			}
		}
	}
	
	private boolean checkValidityRow(){
		int x = 0;
		int y = 1;
		int sum = 0;
		while (y < (this.yLength - 1)){
			sum = 0;
			x = 0;
			while (x < (this.xLength)){	
				sum += this.mapRep[y][x];
				x++;
			}
			if (sum == this.xLength) return false;
			y++;
		}
		return true;
	}
	
	private boolean checkValidityColumn(){
		int y = 0;
		int x = 1;
		int sum = 0;
		while (x < (this.xLength - 1)){
			sum = 0;
			y = 0;
			while (y < (this.yLength)){	
				sum += this.mapRep[y][x];
				y++;
			}
			if (sum == this.yLength) return false;
			x++;
		}
		return true;
	}
	
	private void printMap(){
		int x = 0;
		int y = 0;
		while (y<this.yLength){
			x=0;
			while (x<this.xLength){
				if (this.mapRep[y][x] == 0){
					System.out.print(ANSI_WHITE + "["+ this.mapRep[y][x] + "] " + ANSI_RESET);
				} else 
				{
					System.out.print("["+ this.mapRep[y][x] + "] ");
				}
				x++;
			}
			System.out.println("");
			y++;
		}
		System.out.println("");
	}
	
	private void setBoundary(){
		int y = 0;
		int x = 0;
		while (y<this.yLength){
			x = 0;
			while (x<this.xLength){
				this.mapRep[y][x] = 1;
				x++;
			}
			y++;
		}
	}
	
	private void insertBlankSpace(int xStart, int yStart, int xLength, int yLength){
		int y = yStart;
		int x = xStart;
		
		while (y < (yStart + yLength) && y != (this.yLength-1)){
			x = xStart;
			while (x < (xStart + xLength) && x != (this.xLength-1)){
				this.mapRep[y][x] = 0;
				x++;
			}
			y++;
		}
	}
	
	private void insertOneBoundary(int xStart, int yStart){
		int y = yStart;
		int x = xStart;
		while (y < (yStart+this.yBoxLength) && y!=(this.yLength-1)){
			x = xStart;
			while (x < (xStart+this.xBoxLength) && x!=(this.xLength-1)){
				if (x == (xStart+(this.xBoxLength/2)) && y == (yStart+(this.yBoxLength/2))){
					this.mapRep[y][x] = 1;
				} else {
					this.mapRep[y][x] = 0;
				}
				x++;
			}
			y++;
		}
	}

	public int[][] getMap() {
		return mapRep;
	}

	public void setMap(int map[][]) {
		this.mapRep = map;
	}
	
	public int getxLength() {
		return xLength;
	}

	public void setxLength(int xLength) {
		this.xLength = xLength;
	}

	public int getyLength() {
		return yLength;
	}

	public void setyLength(int yLength) {
		this.yLength = yLength;
	}

	public int getxBoxLength() {
		return xBoxLength;
	}

	public void setxBoxLength(int xBoxLength) {
		this.xBoxLength = xBoxLength;
	}

	public int getyBoxLength() {
		return yBoxLength;
	}

	public void setyBoxLength(int yBoxLength) {
		this.yBoxLength = yBoxLength;
	}
	
	private void setTMap(){
		int width = this.xLength / 6;
		int height = this.yLength / 6;
		int xHalf = (this.xLength / 2) - (width/2);
		int yHalf = (this.yLength / 2) - (height/2);
		
		int x = 2;
		int y = 2;
		
		while (y < (this.yLength - 2)){
			x = 1;
			while (x < (this.xLength - 2)){
				if (x >= xHalf && x <= (xHalf + width)){
					this.mapRep[y][x] = 0;
				}
				x++;
			}
			y++;
		}
		
		x = 2;
		
		while (x < (this.xLength - 2)){
			y = 2;
			while (y < (this.yLength - 2)){
				if (y >= yHalf && y <= (yHalf + height)){
					this.mapRep[y][x] = 0;
				}
				y++;
			}
			x++;
		}
	}
	
	private int[][] mapPadding(){
		int x = 0;
		int y = 0;
		int[][] map = new int[15][15];
		
		while (y < 15){
			x = 0;
			while (x < 15){
				map[y][x] = 1;
				x++;
			}
			y++;
		}
		
		return map;
	}
	
	public Background addPaddingEasy(){
		Background newMap = new Background(15, 15);
		newMap.mapRep = this.mapPadding();
		int x = 3;
		int y = 3;
		while (y < 10){
			x = 3;
			while (x < 10){
				newMap.mapRep[y][x] = this.mapRep[y-3][x-3];
				x++;
			}
			y++;
		}
		return newMap;
	}
	
	public Background addPaddingMedium(){
		Background newMap = new Background(15,15);
		newMap.mapRep = this.mapPadding();
		int x = 2;
		int y = 2;
		while (y < 12){
			x = 2;
			while (x < 12){
				newMap.mapRep[y][x] = this.mapRep[y-2][x-2];
				x++;
			}
			y++;
		}
		return newMap;
	}
	
	public Background addPaddingHard(){
		Background newMap = new Background(15,15);
		newMap.mapRep = this.mapPadding();
		int x = 1;
		int y = 4;
		while (y < 11){
			x = 1;
			while (x < 13){
				newMap.mapRep[y][x] = this.mapRep[y-4][x-1];
				x++;
			}
			y++;
		}
		return newMap;
	}
}

