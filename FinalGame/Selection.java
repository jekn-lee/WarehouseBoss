import java.awt.*;

import javax.swing.ImageIcon;

/**
 * Created by kevin
 */

public class Selection implements Render{
    private GameBoard game;
	private Image title;
	private Image bg;
	private Image op1;
	private Image op2;
	private Image op3;
	private Image random;
	private Image home;

    /**
     * Constructor of selection requires pointer to
     * GameBoard
     * @param game current GameBoard
     */
    public Selection(GameBoard game){
        this.game = game;

    }
    /**
     * Render the graphics for Selection display
     * @param g
     */
    public void render(Graphics g){

		ImageIcon  titleImg = new ImageIcon("select_predif.png");
		this.title = titleImg.getImage();
		
		ImageIcon  op1Img = new ImageIcon("option1.png");
		this.op1 = op1Img.getImage();
		
		ImageIcon  op2Img = new ImageIcon("option2.png");
		this.op2 = op2Img.getImage();
		
		ImageIcon  op3Img = new ImageIcon("option3.png");
		this.op3 = op3Img.getImage();
		
		ImageIcon  bgImg = new ImageIcon("BgImage.png");
		this.bg = bgImg.getImage();
        
        ImageIcon ranImg = new ImageIcon("button_random.png");
        this.random = ranImg.getImage();
        
        ImageIcon homeImg = new ImageIcon("home_icon.png");
        this.home = homeImg.getImage();

		g.drawImage(this.bg, 0, 0, game.getGameBoardWidth(), game.getGameBoardHeight(), game);
		g.drawImage(this.title, 0, 40, game.getGameBoardWidth(), 230, game);
		g.drawImage(this.op1, 50, game.getGameBoardHeight()/2-200, 200, 200, game);
		g.drawImage(this.op2, 300, game.getGameBoardHeight()/2-200, 200, 200, game);
		g.drawImage(this.op3, 550, game.getGameBoardHeight()/2-200, 200, 200, game);
		g.drawImage(this.random,game.getGameBoardWidth()/2-150,game.getGameBoardHeight()/2+130,300,80,game);
		g.drawImage(this.home,50,675,75,75, game);

    }

}


