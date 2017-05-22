import java.util.Random;
import java.util.Scanner;

public class Map {

	private int Map[][];
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
	
	public Map(int xLength, int yLength){
		this.Map = new int[yLength][xLength];
		this.xLength = xLength;
		this.yLength = yLength;
		this.xBoxLength = xLength / 3;
		this.yBoxLength = yLength / 3;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("Enter what x by y size map you want:"); 
		Scanner sc = new Scanner(System.in);
		int xLength = sc.nextInt();
		int yLength = sc.nextInt();
		if (xLength < 6 || yLength < 6){
			System.exit(0);
		}
		
		Map newMap = new Map(xLength,yLength);
		newMap.setBoundary();
		//newMap.printMap();
		newMap.setTMap();
		//newMap.printMap();
		/*
		System.out.println("Enter how many blank squares you want inserted");
		int numBlank = sc.nextInt();
		int i = 0;
		Random rand = new Random();
		while (i<numBlank){
			int xStart = rand.nextInt(xLength - 1) + 1;
			int yStart = rand.nextInt(yLength - 1) + 1;
			newMap.insertBlankSpace(xStart, yStart, newMap.xBoxLength, newMap.yBoxLength);
			i++;
		}
		newMap.printMap();
		
		System.out.println("Enter how many blank squares with boundary in the middle you want inserted");
		int numBoundary = sc.nextInt();
		sc.close();
		i = 0;
		while (i < numBoundary){
			int xBegin = rand.nextInt(xLength - 1) + 1;
			int yBegin = rand.nextInt(yLength - 1) + 1;
			newMap.insertOneBoundary(xBegin, yBegin);
			i++;
		}
		*/
		//newMap.printMap();
		//System.out.println(newMap.checkValidityColumn());
		//System.out.println(newMap.checkValidityRow());
		
		Random rand = new Random();
		if(!newMap.checkValidityColumn() || !newMap.checkValidityRow()){
			while (!newMap.checkValidityRow()){
				int xBegin = rand.nextInt(xLength - 1) + 1;
				int yBegin = rand.nextInt(yLength - 1) + 1;
				newMap.insertBlankSpace(xBegin, yBegin, newMap.xBoxLength, newMap.yBoxLength);
				newMap.insertOneBoundary(xBegin, yBegin);
			}
			while(!newMap.checkValidityColumn()){
				int xBegin = rand.nextInt(xLength - 1) + 1;
				int yBegin = rand.nextInt(yLength - 1) + 1;
				newMap.insertBlankSpace(xBegin, yBegin, newMap.xBoxLength, newMap.yBoxLength);
				newMap.insertOneBoundary(xBegin, yBegin);
			}
		}
		newMap.printMap();
		
	}
	
	public boolean checkValidityRow(){
		int x = 0;
		int y = 1;
		int sum = 0;
		while (y < (this.yLength - 1)){
			sum = 0;
			x = 0;
			while (x < (this.xLength)){	
				sum += this.Map[y][x];
				x++;
			}
			if (sum == this.xLength) return false;
			y++;
		}
		return true;
	}
	
	public boolean checkValidityColumn(){
		int y = 0;
		int x = 1;
		int sum = 0;
		while (x < (this.xLength - 1)){
			sum = 0;
			y = 0;
			while (y < (this.yLength)){	
				sum += this.Map[y][x];
				y++;
			}
			if (sum == this.yLength) return false;
			x++;
		}
		return true;
	}
	
	public void printMap(){
		int x = 0;
		int y = 0;
		while (y<this.yLength){
			x=0;
			while (x<this.xLength){
				if (this.Map[y][x] == 0){
					System.out.print(ANSI_WHITE + "["+ this.Map[y][x] + "] " + ANSI_RESET);
				} else 
				{
					System.out.print("["+ this.Map[y][x] + "] ");
				}
				x++;
			}
			System.out.println("");
			y++;
		}
		System.out.println("");
	}
	
	public void setBoundary(){
		int y = 0;
		int x = 0;
		while (y<this.yLength){
			x = 0;
			while (x<this.xLength){
				this.Map[y][x] = 1;
				x++;
			}
			y++;
		}
	}
	
	public void insertBlankSpace(int xStart, int yStart, int xLength, int yLength){
		int y = yStart;
		int x = xStart;
		
		while (y < (yStart + yLength) && y != (this.yLength-1)){
			x = xStart;
			while (x < (xStart + xLength) && x != (this.xLength-1)){
				this.Map[y][x] = 0;
				x++;
			}
			y++;
		}
	}
	
	public void insertOneBoundary(int xStart, int yStart){
		int y = yStart;
		int x = xStart;
		while (y < (yStart+this.yBoxLength) && y!=(this.yLength-1)){
			x = xStart;
			while (x < (xStart+this.xBoxLength) && x!=(this.xLength-1)){
				if (x == (xStart+(this.xBoxLength/2)) && y == (yStart+(this.yBoxLength/2))){
					this.Map[y][x] = 1;
				} else {
					this.Map[y][x] = 0;
				}
				x++;
			}
			y++;
		}
	}

	public int[][] getMap() {
		return Map;
	}

	public void setMap(int map[][]) {
		Map = map;
	}
	
	public void setTMap(){
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
					this.Map[y][x] = 0;
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
					this.Map[y][x] = 0;
				}
				y++;
			}
			x++;
		}
	}

}
