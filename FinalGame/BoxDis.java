import java.awt.*;

import javax.swing.ImageIcon;
/**
 * Box class, child of Asset class
 * @author mahima
 */
public class BoxDis extends AssetDis{
	private Image image;
	/**
	 * Initial size of image 
	 */
	private final int STDSIZE = 50;
	/**
	 * Constructor for Box
	 * @param x initial position
	 * @param y initial position
	 */
	public BoxDis(int x, int y){
		super(x,y);
		
		ImageIcon boxImg = new ImageIcon("flower.png");
		this.image = boxImg.getImage();
		this.setImage(this.image.getScaledInstance(STDSIZE, STDSIZE, 1));
	}
	/**
	 * Shift Box to 
	 * @param x units in x direction
	 * @param y units in y direction
	 */
	public void move(int x, int y){
		this.setX((this.getX() + x));
		this.setY((this.getY() + y));
	}
}


