import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
/**
 * KeyInput class to detect key presses/releases
 * @author mahima
 *
 */
public class KeyInput extends KeyAdapter{
	//Pointer to game for updating
	GameBoard game;
	/**
	 * Constructor for KeyBoard input
	 * @param game currently running
	 */
	public KeyInput(GameBoard game){
		this.game = game;
	}
	/**
	 * Action when key is pressed
	 */
	public void keyPressed(KeyEvent e){

		try {
			this.game.keyPressed(e);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}
	/**
	 * Action when key is released
	 */
	public void keyReleased(KeyEvent e){
		this.game.keyReleased(e);
	}
}

