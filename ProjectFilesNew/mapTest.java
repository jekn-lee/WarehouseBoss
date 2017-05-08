import java.util.ArrayList;
import java.util.Iterator;

public class mapTest {

	public static void main(String[] args) {
		Map testMap = new Map();
		testMap.printMap();
		ArrayList<Integer> mapRep = testMap.getArrayRep();
		Iterator<Integer> iter1 = mapRep.iterator();
		while(iter1.hasNext()){
			System.out.print(iter1.next());
		}
		System.out.println("Movement in empty Spaces");
		testMap.checkMove("Up");
		testMap.printMap();
		testMap.checkMove("Down");
		testMap.printMap();
		testMap.checkMove("Left");
		testMap.printMap();
		System.out.println("Movement into Boundary");
		testMap.checkMove("Left");
		testMap.printMap();
		testMap.checkMove("Right");
		testMap.printMap();
		System.out.println("Movement into double box");
		testMap.checkMove("Right");
		testMap.printMap();
		System.out.println("Move box");
		testMap.checkMove("Up");
		testMap.printMap();
		testMap.checkMove("Right");
		testMap.printMap();
		testMap.checkMove("Down");
		testMap.printMap();
		System.out.println("Move box into empty_goal");
		testMap.checkMove("Down");
		testMap.printMap();	
		System.out.println("Move box out of filled_goal");
		testMap.checkMove("Right");
		testMap.printMap();
		testMap.checkMove("Down");
		testMap.printMap();
		testMap.checkMove("Left");
		testMap.printMap();	
		System.out.println("Player returns onto map");
		testMap.checkMove("Left");
		testMap.printMap();		
		
		Map copy = testMap.copyMap();
		copy.printMap();
		
		mapRep = testMap.getArrayRep();
		Iterator<Integer> iter = mapRep.iterator();
		while(iter.hasNext()){
			System.out.print(iter.next());
		}
	}

}
