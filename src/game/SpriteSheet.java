package game;

import java.awt.image.BufferedImage;

public class SpriteSheet {

	private BufferedImage image;
	
	public SpriteSheet(BufferedImage img)
	{
		image = img;
	}
	
	//this returns an image of designated size and at certain position within the spritesheet.
	public BufferedImage grabImage(int row, int col, int width, int height)
	{
		//square is the size of each side of a 'box' in the spritesheet
		//for example, a 4x4 spritesheet with square 8 would be 32pixels x 32pixels
		//So square should technically be the smallest size of a picture you use in your game,
		//But other things should be multiples of that size.
		//The top left image is at 0,0 and moves like an array does in rows and collums
		
		int square = 8;
		BufferedImage img = image.getSubimage((row*square),(col*square),square*width,square*height);
		return img;
	}
}
