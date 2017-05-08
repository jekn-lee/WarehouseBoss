import java.awt.EventQueue;

public class TestGameDisplayMain {
	
	public static void main(String[] args) {
		GameSystem test = new GameSystem();

        EventQueue.invokeLater(() -> {
            TestGameDisplay ex = new TestGameDisplay(test.getCurrMap("Left"));
            ex.setVisible(true);
        });
    }
}
