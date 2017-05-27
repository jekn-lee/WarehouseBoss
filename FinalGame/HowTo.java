import java.awt.*;

import javax.swing.ImageIcon;

/**
 * Created by kevin on 5/22/2017.
 */

public class HowTo implements Render{
    GameBoard game;
	private Image rules;
	private Image home;
	private Image startB;

    public Rectangle back;
    /**
     * Constructor of menu requires pointer to
     * GameBoard
     * @param game current GameBoard
     */
    public HowTo(GameBoard game){
        this.game = game;

    }
    /**
     * Render the graphics for Menu display
     * @param g
     */
    public void render(Graphics g){
		ImageIcon  rulesImg = new ImageIcon("howTo.png");
		this.rules = rulesImg.getImage();
		
        ImageIcon homeImg = new ImageIcon("home_icon.png");
        this.home = homeImg.getImage();
        
        ImageIcon startImg = new ImageIcon("button_start.png");
        this.startB = startImg.getImage();

		g.drawImage(this.rules, 0, 0, game.getGameBoardWidth(), game.getGameBoardHeight(), game);
		g.drawImage(this.startB,game.getGameBoardWidth()/2+150,675,200,80,game);
		g.drawImage(this.home,50,675,75,75, game);

    }

}

