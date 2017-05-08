import java.awt.EventQueue;

public class TestGameDisplayMain{
	
	
	public static void main(String[] args) {
		//GameSystem test = new GameSystem();

		GameSystem test = new GameSystem();
		TestGameDisplay ex = new TestGameDisplay();
        EventQueue.invokeLater(() -> {
        	ex.displayCurrent(test.getNewMap());
            ex.setVisible(true);
        });
       
    }

}
