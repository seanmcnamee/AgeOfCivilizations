package game;

import java.awt.Color;
import java.awt.Graphics;

public class Tile {
	private int[] yield; //The index of the 'yield' represents what it is
	private int xPos, yPos; //The x and y pos of where this tile is
	private boolean yieldable, harvesting;
	private City ownedBy; //Tells what city has authority over this tile
	private boolean capital; //If this is the actual position of the city.
	private String biome;
	
	
	public static final int TILESIZE = 2;
	
	public Tile(int x, int y, boolean yieldcapable)
	{
		xPos = x;
		yPos = y;
		yield = randomizeYield();
		yieldable = yieldcapable;
		ownedBy = null;
		capital = false;
		harvesting = false;
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
		int type = (int) (Math.random()*4);
		
		if (Math.random() < .8)
		{
			if (type == 0)
			{
				biome = "Grasslands";
				aryNums[0] = 2;
			}	else if (type == 1)
			{
				biome = "Forest";
				aryNums[1] = 2;
			}	else if (type == 2)
			{
				biome = "Plains";
				aryNums[0] = 1;
				aryNums[1] = 1;
			}	else if (type == 3)
			{
				biome = "Hill";
				aryNums[2] = 2;
			}
		}	else
		{
			if (type == 0)
			{
				biome = "Mountain";
				aryNums[2] = 4;
				aryNums[3] = 1;
			}	else if (type == 1)
			{
				biome = "Jungle";
				aryNums[0] = 2;
				aryNums[1] = 3;
			}	else if (type == 2)
			{
				biome = "Marsh";
				aryNums[0] = 4;
			}	else if (type == 3)
			{
				biome = "Desert";
			}
		}
		
		
		
		
		
		return aryNums;
		
		
		
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
		if (ownedBy != null)
		{
			if (ownedBy.getID().contains("TJ"))
			{
				g.setColor(Color.GREEN);
			}	else
			{
				g.setColor(Color.RED);
			}
			if (capital)
			{
				g.setColor(Color.GRAY);
			}
			
		}
		//System.out.println(xPos + " " + yPos);
		g.fillRect(xPos*(TILESIZE+1)*Game.SCALE, yPos*(TILESIZE+1)*Game.SCALE, TILESIZE*Game.SCALE, TILESIZE*Game.SCALE);
	}
	
	
	
	/*
	 * Getters/Setters
	 */
	public int[] getYield()	{	return yield;	}
	public boolean getYieldable()	{	return yieldable;	}
	public int getX()	{	return xPos;	}
	public int getY()	{	return yPos;	}
	public void setCity(City c)	{	ownedBy = c;	}
	public City getCity()	{	return ownedBy;	}
	public boolean isCapital()	{	return capital;	}
	public void setHarvesting(boolean b){	harvesting = b;	}
	public boolean getHarvesting()	{	return harvesting;	}
	
	public void setCapital(boolean b)	{
		capital = b;
		if (b)
		{
			harvesting = true;
			yield[0] += 2;
			//System.out.println("Tile " + xPos + ", " + yPos + " is harvestable");
		}
	}
	
	public int getYieldValue()	{
		int val = 0;
		for (int i = 0; i < yield.length; i++)
		{
			val+= yield[i];
		}
		return val;
	}
	
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
