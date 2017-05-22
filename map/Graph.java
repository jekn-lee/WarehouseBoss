import java.util.ArrayList;
import java.util.LinkedList;

public class Graph {
	private int[][] map;
	private ArrayList<Position> goals;
	LinkedList<State> states; //nodes

	public Graph(int[][] map) {
		this.map = map; // this is static
		this.goals = new ArrayList<Position>();
		this.states = new LinkedList<State>();
	}
	
	public void addGoals(Position g) {
		this.goals.add(g);
	}
	
	public void addState(State s) {
		this.states.add(s);
	}
	
	public void printGraph() {
		System.out.println("~~~~~Printing the graph~~~~~");
		for (int i = 0; i < this.states.size(); i++) {
			this.states.get(i).printState(map);
		}
		System.out.print("The goals are located at:");
		for (int i = 0; i < this.goals.size(); i++) {
			int curX = this.goals.get(i).getX();
			int curY = this.goals.get(i).getY();
			System.out.print(" (" + curX + "," + curY + ")");
		}
		System.out.println();
	}
	
	public State getState(int i) {
		return this.states.get(i);
	}	
	// we have our initial state
	// Pick a random box and direction until you find a valid combination
	// Is the player in the position to pull the box in that direction
	// Yes: perform the pull
	// No:  move the player to that position
}
