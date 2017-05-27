import java.awt.*;

import javax.swing.ImageIcon;
/**
 * Goal class, child of Asset class
 * @author mahima
 *
 */
public class GoalDis extends AssetDis{
	private Image image;
	/**
	 * Initial size of image 
	 */
	private final int STDSIZE = 50;
	/**
	 * Constructor for Goal
	 * @param x initial position
	 * @param y initial position
	 */
	public GoalDis(int x, int y){
		super(x,y);
		
		ImageIcon GoalImg = new ImageIcon("goal2.png");
		this.image = GoalImg.getImage();
		this.setImage(this.image.getScaledInstance(STDSIZE, STDSIZE, 1));
	}
}
