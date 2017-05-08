import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TestGameDisplayMain implements KeyListener{
	private GameSystem test = new GameSystem();
	private TestGameDisplay ex = new TestGameDisplay();
	
	public static void main(String[] args) {
		//GameSystem test = new GameSystem();

        EventQueue.invokeLater(() -> {
        	ex.displayCurrent(this.test.getNewMap());
            ex.setVisible(true);
        });
    }

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int code = e.getKeyCode();
		if (code == KeyEvent.VK_DOWN){
			this.ex.displayCurrent(test.getCurrMap("Down"));
		} else if (code == KeyEvent.VK_UP){
			this.ex.displayCurrent(test.getCurrMap("Up"));
		} else if (code == KeyEvent.VK_LEFT){
			this.ex.displayCurrent(test.getCurrMap("Left"));
		} else if (code == KeyEvent.VK_RIGHT){
			this.ex.displayCurrent(test.getCurrMap("Right"));
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
