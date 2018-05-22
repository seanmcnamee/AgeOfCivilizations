package game;

import java.util.ArrayList;

public class City {

	private Tile[][] map;
	private ArrayList<Tile> generationMap;
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
	
	public City() //Default PANGEA
	{
		
	}
	
	
	public City(int type)
	{
		if (type == 0) //PANGEA
		{
			
		}	else if (type == 1)  //Two continents
		{
			
		}	else if (type == 2)
		{
			
		}
	}
	
	private void init()
	{
		generationMap = new ArrayList<Tile> ();
		population = 2;
		yields = new int[5];
		resources = new int[5];
		buildings = new int[5];
		for (int i = 0; i < 5; i++)
		{
			yields[i] = 0;
			resources[i] = 0;
			buildings[i] = 0;
		}
		
	}
	
	private void createPangea()
	{
		generationMap.add(new Tile(0, 0));
		int dir = 0;
		int mapSize = 50;
		
		pangeaGen(3000, dir, 0, 0, mapSize/2);
		
		
	}
	
	private String pangeaGen(int stepsLeft, int direction, int xInd, int yInd, int mapRad)
	{
		//Set new direction (based on turning or going straight
		int newDir = direction +  ((int) ( (Math.random()*4) -2 ) );
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
		
		int newX = xInd + cX;
		int newY = yInd + cY;
		
		//Try to keep it within a certain range...
		double dist = Math.sqrt(Math.pow(newX, 2) + Math.pow(newY, 2));
		if (dist > mapRad)
		{
			newX = 0;
			newY = 0;
			//System.out.println("Dist: " + dist);
		}
		//System.out.println("Dist: " + dist);
		
		
		if (stepsLeft > 0)
		{
			//Add Chunk
			addTile(newX, newY, true);
			return newDir + "" + pangeaGen(stepsLeft-1, newDir, newX, newY, mapRad);
		}
		return "";
	}
	
	public Tile[][] thickenMap(Tile[][] barrenMap)
	{
		Tile[][] newMap = new Tile[barrenMap.length][barrenMap[0].length];
		
		int outerborder = 10;
		//System.out.println("r l = " + newMap.length + " and c l " + newMap[0].length);
		
		//From largest altitude to 1 so that the larger ones override
		for (int r = outerborder; r < barrenMap.length - (outerborder-1); r++)
		{
			for (int c = outerborder; c < barrenMap[r].length-(outerborder-1); c++)
			{
				//newMap[r][c].setCount(10);
				//System.out.println("at (" + r + ", " + c + ")");
				//When at the proper altitude
				//System.out.println("Successfully found altitude at (" + r + ", " + c + ") +" + map[r][c].getCount() );
				
				int rad = 2; //The usual size of a given sphere..
				//System.out.println("Sphere of size " + rad + " at (" + r + ", " + c + ")");

				
				//Go all the way around and create the sphere
				for (double theta = 0; theta < Math.PI*2; theta += Math.PI/30)
				{
					for (int radius = rad; radius > 0; radius --)
					{
						int xPos = (int) (Math.cos(theta)*radius) + r;
						int yPos = (int) (Math.sin(theta)*radius) + c;
						
						//System.out.println("At (" + xPos + ", " + yPos + ") Altitude: " + toMap[xPos][yPos].getCount());
						
						newMap[xPos][yPos] = new Tile(xPos-r + barrenMap[r][c].getX(), yPos-c + barrenMap[r][c].getY());
					}
				}
					
			}
		}
		
		
		//TODO check this code below here
		//If a spot in the new map has not been set (is null) set it to barrenMap's value.
				for (int r = 0; r < barrenMap.length; r++)
				{
					for (int c = 0; c < barrenMap[r].length; c++)
					{
						if (newMap[r][c] == null)
						{
							newMap[r][c] = barrenMap[r][c];
						}
					}
				}
				
				
				return newMap;
	}
	
	
	
	//Used for map generation. Adds a chunks to the arraylist
	private boolean addTile(int x, int y, boolean addCount)
	{
		//go through each Chunk
		for (Tile t : generationMap)
		{
			if (t.getX() == x && t.getY() == y)
			{
				return false;
			}
		}
		//System.out.println("Making new Chunk!");
		if (addCount)
		{
			generationMap.add(new Tile(x, y));
		}
		return true;
	}
	
	
	
	
	
}
