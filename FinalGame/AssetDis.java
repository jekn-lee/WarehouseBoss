import java.awt.Image;
//import java.util.*;
//import javax.swing.*;
/**
 * Parent class for all assets in the game
 * Encapsulate the functionality of an asset
 * @author mahima 14/05/17
 */
public class AssetDis {
	private final int STDSIZE = 50;
	
	private int x;
	private int y;
	private int imageHeight;
	private int imageWidth;
	private Image image;
	/**
	 * Asset contructor
	 * @param x position
	 * @param y position
	 */
	public AssetDis(int x, int y){
		this.x = x;
		this.y = y;
		this.imageHeight = STDSIZE;
		this.imageWidth = STDSIZE;
	}
	/**
	 * Check for left collision with input asset
	 * @param other asset
	 * @return boolean true if colliding
	 */
	public boolean isLeftCollision(AssetDis other){
		if(((this.x - this.imageWidth) == other.x) &&
			(this.y == other.y)){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * Check for right collision with input asset
	 * @param other asset
	 * @return boolean true if colliding
	 */
	public boolean isRightCollision(AssetDis other){
		if(((this.x + this.imageWidth) == other.x) &&
			(this.y == other.y)){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * Check for top collision with input asset
	 * @param other asset
	 * @return boolean true if colliding 
	 */
	public boolean isTopCollision(AssetDis other){
		if(((this.y - this.imageHeight) == other.y) &&
			(this.x == other.x)){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * Check for bottom collision with input asset
	 * @param other asset
	 * @return boolean true if colliding
	 */
	public boolean isBottomCollision(AssetDis other){
		if(((this.y + this.imageHeight) == other.y) &&
			(this.x == other.x)){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * Implement all getters and setters
	 */
	/**
	 * @return image of this asset
	 */
	public Image getImage(){
		return this.image;
	}
	/**
	 * Set the Image of asset to
	 * @param img Image
	 */
	public void setImage(Image img){
		this.image = img;
	}
	/**
	 * Alter the size of the asset image
	 * @param width of image
	 * @param height of image
	 */
	public void scaleImage(int width, int height){
		this.imageHeight = height;
		this.imageWidth = width;
		this.image = this.image.getScaledInstance(width, height, 1);
	}
	/**
	 * @return x position
	 */
    public int getX() {
        return this.x;
    }
    /**
     * @return y position
     */
    public int getY() {
        return this.y;
    }
    /**
     * Set x position
     * @param x position
     */
    public void setX(int x) {
        this.x = x;
    }
    /**
     * Set y position
     * @param y position
     */
    public void setY(int y) {
        this.y = y;
    }
}


