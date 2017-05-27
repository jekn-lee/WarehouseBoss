import java.awt.*;

import javax.swing.ImageIcon;

/**
 * Created by kevin
 */

public class Difficulty implements Render {
    private GameBoard game;
	private Image title;
	private Image easyB;
	private Image mediumB;
	private Image hardB;
	private Image bg;
	private Image home;

    /**
     * Constructor of menu requires pointer to
     * GameBoard
     * @param game current GameBoard
     */
    public Difficulty(GameBoard game){
        this.game = game;

    }
    /**
     * Render the graphics for Menu display
     * @param g
     */
    public void render(Graphics g){

		ImageIcon  titleImg = new ImageIcon("choose_difficulty.png");
		this.title = titleImg.getImage();
		
		ImageIcon  easyImg = new ImageIcon("button_beginner.png");
		this.easyB = easyImg.getImage();
		
		ImageIcon  medImg = new ImageIcon("button_intermediate.png");
		this.mediumB = medImg.getImage();
		
		ImageIcon  hardImg = new ImageIcon("button_expert.png");
		this.hardB = hardImg.getImage();
		
		ImageIcon  bgImg = new ImageIcon("BgImage.png");
		this.bg = bgImg.getImage();
		
        ImageIcon homeImg = new ImageIcon("home_icon.png");
        this.home = homeImg.getImage();

		g.drawImage(this.bg, 0, 0, game.getGameBoardWidth(), game.getGameBoardHeight(), game);
		g.drawImage(this.title, 0, 40, game.getGameBoardWidth(), 230, game);
		g.drawImage(this.easyB,game.getGameBoardWidth()/2-150,game.getGameBoardHeight()/2-170,300,80,game);
		g.drawImage(this.mediumB, game.getGameBoardWidth()/2-150,game.getGameBoardHeight()/2-20,300,80,game);
		g.drawImage(this.hardB,game.getGameBoardWidth()/2-150,game.getGameBoardHeight()/2+130,300,80,game);
		g.drawImage(this.home,50,675,75,75, game);

    }

}

