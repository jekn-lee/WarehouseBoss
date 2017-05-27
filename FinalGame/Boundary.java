import java.awt.*;
//import java.util.*;
import javax.swing.*;
/**
 * Boundary class, child of Asset class
 * @author mahima
 */
public class Boundary extends AssetDis {
	private Image image;
	/**
	 * Initial size of image
	 */
	private final int STDSIZE = 50;
	/**
	 * Constructor for Boundary
	 * @param x initial position
	 * @param y initial position
	 */
	public Boundary(int x, int y){
		super(x,y);
		ImageIcon boundaryImg = new ImageIcon("hedge4.png");
		this.image = boundaryImg.getImage();
		this.setImage(this.image.getScaledInstance(STDSIZE, STDSIZE, 1));
	}
}

