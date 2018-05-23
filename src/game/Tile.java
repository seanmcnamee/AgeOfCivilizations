package game;

import java.awt.Color;
import java.awt.Graphics;

public class Tile {
	private int[] yield; //The index of the 'yield' represents what it is
	private int xPos, yPos; //The x and y pos of where this tile is
	private boolean yieldable;
	private City ownedBy; //Tells what city has authority over this tile
	
	
	public static final int TILESIZE = 10;
	
	public Tile(int x, int y, boolean yieldcapable)
	{
		xPos = x;
		yPos = y;
		yield = randomizeYield();
		yieldable = yieldcapable;
		ownedBy = null;
	}
	
	private int[] randomizeYield()
	{
		/*
		 * Food
		 * Wood
		 * Stone
		 * Gold
		 * Culture
		 */
		int[] aryNums = { 0, 0, 0, 0, 0 };
		return aryNums;
		//TODO actually randomize Yields.
	}
	
	/*
	 * Ticking and Rendering the Tile
	 */
	public void tick()
	{
		
	}
	
	public void render(Graphics g)
	{
		g.setColor(Color.BLUE);
		//System.out.println(xPos + " " + yPos);
		g.fillRect(xPos*(TILESIZE+1), yPos*(TILESIZE+1), TILESIZE, TILESIZE);
	}
	
	
	
	/*
	 * Getters/Setters
	 */
	public int[] getYield()	{	return yield;	}
	public boolean getYieldable()	{	return yieldable;	}
	public int getX()	{	return xPos;	}
	public int getY()	{	return yPos;	}
	public void setCity(City c)	{	ownedBy = c;	}
	
	
	public String toString()
	{
		String what = "";
		
		if (yield[0] > 0)
		{
			what += "Food: " + yield[0] + " ";
		}
		if (yield[1] > 0)
		{
			what += "Wood: " + yield[1] + " ";
		}
		if (yield[2] > 0)
		{
			what += "Stone: " + yield[2] + " ";
		}
		if (yield[3] > 0)
		{
			what += "Gold: " + yield[3] + " ";
		}
		if (yield[4] > 0)
		{
			what = "Culture: " + yield[4];
		}

		return what;
	}
}