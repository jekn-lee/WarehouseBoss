import java.awt.*;


import javax.swing.ImageIcon;

/**
 * Menu class to display the menu
 * @author Kevin z3418497
 *
 */
public class Menu implements Render{
	private GameBoard game;
	private Image title;
	private Image startGame;
	private Image howTo;
	private Image quitButton;
	private Image bg;

	/**
	 * Constructor of menu requires pointer to
	 * GameBoard
	 * @param game current GameBoard
	 */
	public Menu(GameBoard game){
		this.game = game;

	}
	/**
	 * Render the graphics for Menu display
	 * @param g
	 */
	public void render(Graphics g){

		ImageIcon  titleImg = new ImageIcon("title.png");
		this.title = titleImg.getImage();
		
		ImageIcon  startImg = new ImageIcon("button_start-game.png");
		this.startGame = startImg.getImage();
		
		ImageIcon  howToImg = new ImageIcon("button_how-to-play.png");
		this.howTo = howToImg.getImage();
		
		ImageIcon  quitImg = new ImageIcon("button_quit.png");
		this.quitButton = quitImg.getImage();
		
		ImageIcon  bgImg = new ImageIcon("BgImage.png");
		this.bg = bgImg.getImage();
		
		g.drawImage(this.bg, 0, 0, game.getGameBoardWidth(), game.getGameBoardHeight(), game);
		g.drawImage(this.title, 0, 40, game.getGameBoardWidth(), 230, game);
		g.drawImage(this.startGame,game.getGameBoardWidth()/2-150,game.getGameBoardHeight()/2-170,300,80,game);
		g.drawImage(this.howTo, game.getGameBoardWidth()/2-150,game.getGameBoardHeight()/2-20,300,80,game);
		g.drawImage(this.quitButton,game.getGameBoardWidth()/2-150,game.getGameBoardHeight()/2+130,300,80,game);

	}

}
