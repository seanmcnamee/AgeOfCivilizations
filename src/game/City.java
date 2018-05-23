package game;

import java.util.ArrayList;

public class City {

	//This represents what 'Tiles' the city owns
	private Tile[][] tilesOwned;
	private int xPos, yPos; //The actual position of the city.
	private int population;
	//People will be atutomatically created based on a formula that takes in excess food
	//Start
	//2 citizens and 10 food
	//pop*1.5 + 10 > food    to create another citizen
	
	private int[] yields;
	/*
	 * Food
	 * Wood
	 * Stone
	 * Gold
	 * Culture
	 */
	private int[] resources;
	/*
	 * Iron
	 * Horses
	 * Coal
	 * Oil
	 * Uranium
	 */
	private int[] buildings; //the number represents the level of the building
							//0 meaning no building
	/*
	 * Granary //Food
	 * Saw Mill //Wood
	 * Mason //Stone
	 * Trading Post //Gold (the level multiples your gold when trading)
	 * Monument //Culture
	 * 
	 */
	
	public City(int xp, int yp, Tile[][] map) //Default City
	{
		xPos = xp;
		yPos = yp;
		tilesOwned = new Tile[3][3];
		for (int x = xPos - 1; x <= xPos + 1; x++)
		{
			for (int y = yPos - 1; y <= yPos + 1; y++)
			{
				tilesOwned[x][y] = map[x][y];
				map[x][y].setCity(this);
			}
		}
		
		
		population = 2;//Start with 2 people
		yields = new int[5];
		resources = new int[5];
		buildings = new int[5];
		for (int i = 0; i < 5; i++)
		{
			yields[i] = 0;
			resources[i] = 0;
			buildings[i] = 0;
		}
		yields[0] = 10;//Start with 10 food
		
	}
	
	public void expandControl(Tile[][] map)
	{
		
		/*
		//A random tile within is picked
		Tile pickedTile = tilesOwned[(int) (Math.random()*tilesOwned.length)][(int) (Math.random()*tilesOwned[0].length)];
		if (pickedTile.getX() > xPos)
		{
			if ()
		}	else
		{
			
		}
		*/
		
		//Start at the middle and randomWalk to an edge where another tile can be created.
		
	}
	
	private Tile[][] walkToEdge(int xP, int yP, int dir)
	{
		//Set new direction (based on turning or going straight
		int newDir = dir +  ((int) ( (Math.random()*4) -2 ) );
		if (newDir < 0)
		{
			newDir += 4;
		}
		if (newDir > 3)
		{
			newDir -= 4;
		}
		//System.out.println(newDir);
		
		//Set change in x and y based on direction
		int cX = 0;
		int cY = 0;
		if (newDir == 0 || newDir == 2)
		{
			cY = newDir == 0? -1:1;
		}
		if (newDir == 1 || newDir == 3)
		{
			cX = newDir == 3? -1:1;
		}
		
		int newX = xP + cX;
		int newY = yP + cY;
		
		
		
		
		
		
		Tile[][] newOwned = new Tile[tilesOwned.length][tilesOwned[0].length];
		
		return newOwned;
	}
	
	
	public int getX()	{	return xPos;	}
	public int getY()	{	return yPos;	}
	
	
	
}
