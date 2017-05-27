import java.awt.*;

import javax.swing.ImageIcon;

/**
 * Created by kevin on 5/22/2017.
 */

public class Win implements Render{
    private GameBoard game;
	private Image title;
	private Image tryAgain;
	private Image bg;
	private Image home;

   // public Rectangle home;
    public Rectangle replay;
    /**
     * Constructor of menu requires pointer to
     * GameBoard
     * @param game current GameBoard
     */
    public Win(GameBoard game){
        this.game = game;

    }
    /**
     * Render the graphics for Menu display
     * @param g
     */
    public void render(Graphics g){
		ImageIcon  titleImg = new ImageIcon("days_work.png");
		this.title = titleImg.getImage();
		
		ImageIcon  againImg = new ImageIcon("button_try-again.png");
		this.tryAgain = againImg.getImage();
		
		ImageIcon  bgImg = new ImageIcon("BgImage.png");
		this.bg = bgImg.getImage();
		
        ImageIcon homeImg = new ImageIcon("home_icon.png");
        this.home = homeImg.getImage();
        
		g.drawImage(this.bg, 0, 0, game.getGameBoardWidth(), game.getGameBoardHeight(), game);
		g.drawImage(this.title, 0, 40, game.getGameBoardWidth(), 230, game);
		g.drawImage(this.tryAgain,game.getGameBoardWidth()/2-150,game.getGameBoardHeight()/2,300,80,game);
		g.drawImage(this.home,50,675,75,75, game);

        Font fnt0 = new Font("arial", Font.BOLD, 50);
        g.setFont(fnt0);
        g.setColor(Color.white);
        
        String finalScore = "Score: " + game.getScore();
        g.drawString(finalScore, game.getGameBoardWidth()/2 -115 ,350);

    }
}
