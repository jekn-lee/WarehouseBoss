import java.awt.*;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
/**
 * Player class, child of Asset class
 * @author Kevin
 *
 */
public class PlayerDis extends AssetDis {
	private Image imageU;
	private Image imageR;
	private Image imageL;
	private Image imageD;

	private Image imageUr;
	private Image imageRr;
	private Image imageLr;
	private Image imageDr;

	private Image imageUl;
	private Image imageRl;
	private Image imageLl;
	private Image imageDl;



	/**
	 * Initial size of image
	 */
	private final int STDSIZE = 50;
	/**
	 * Constructor for Player
	 * @param x initial position
	 * @param y initial position
	 */
	public PlayerDis(int x, int y){
		super(x,y);

		//Load still images
		ImageIcon playerImgU = new ImageIcon("playerU.png");
		ImageIcon playerImgR = new ImageIcon("playerR.png");
		ImageIcon playerImgL = new ImageIcon("playerL.png");
		ImageIcon playerImgD = new ImageIcon("playerD.png");
		this.imageU = playerImgU.getImage();
		this.imageR = playerImgR.getImage();
		this.imageL = playerImgL.getImage();
		this.imageD = playerImgD.getImage();

		//Load right step images
		ImageIcon playerImgUr = new ImageIcon("playerUwr.png");
		ImageIcon playerImgRr = new ImageIcon("playerRwr.png");
		ImageIcon playerImgLr = new ImageIcon("playerLwr.png");
		ImageIcon playerImgDr = new ImageIcon("playerDwr.png");
		this.imageUr = playerImgUr.getImage();
		this.imageRr = playerImgRr.getImage();
		this.imageLr = playerImgLr.getImage();
		this.imageDr = playerImgDr.getImage();

		//Load left step images
		ImageIcon playerImgUl = new ImageIcon("playerUwl.png");
		ImageIcon playerImgRl = new ImageIcon("playerRwl.png");
		ImageIcon playerImgLl = new ImageIcon("playerLwl.png");
		ImageIcon playerImgDl = new ImageIcon("playerDwl.png");
		this.imageUl = playerImgUl.getImage();
		this.imageRl = playerImgRl.getImage();
		this.imageLl = playerImgLl.getImage();
		this.imageDl = playerImgDl.getImage();

		this.setImage(this.imageU.getScaledInstance(STDSIZE, STDSIZE, 1));
	}

	public void setRot(int k) {
		switch (k) {
			case 1:
				this.setImage(this.imageL.getScaledInstance(STDSIZE, STDSIZE, 1));
				break;
			case 2:
				this.setImage(this.imageU.getScaledInstance(STDSIZE, STDSIZE, 1));
				break;
			case 3:
				this.setImage(this.imageR.getScaledInstance(STDSIZE, STDSIZE, 1));
				break;
			case 4:
				this.setImage(this.imageD.getScaledInstance(STDSIZE, STDSIZE, 1));
				break;
		}
	}

	public void animate(int k, boolean leg) {
		switch (k) {
			case 1:
				if (leg) {
					this.setImage(this.imageLr.getScaledInstance(STDSIZE, STDSIZE, 1));
				} else {
					this.setImage(this.imageLl.getScaledInstance(STDSIZE, STDSIZE, 1));
				}
				break;
			case 2:
				if (leg) {
					this.setImage(this.imageUr.getScaledInstance(STDSIZE, STDSIZE, 1));
				} else {
					this.setImage(this.imageUl.getScaledInstance(STDSIZE, STDSIZE, 1));
				}
				break;
			case 3:
				if (leg) {
					this.setImage(this.imageRr.getScaledInstance(STDSIZE, STDSIZE, 1));
				} else {
					this.setImage(this.imageRl.getScaledInstance(STDSIZE, STDSIZE, 1));
				}
				break;
			case 4:
				if (leg) {
					this.setImage(this.imageDr.getScaledInstance(STDSIZE, STDSIZE, 1));
				} else {
					this.setImage(this.imageDl.getScaledInstance(STDSIZE, STDSIZE, 1));
				}
				break;
		}

	}
	/**
	 * Shift Player to 
	 * @param x units in x direction
	 * @param y units in y direction
	 */
	public void move(int x, int y){
		this.setX((this.getX() + x));
		this.setY((this.getY() + y));
	}


}

