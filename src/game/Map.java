package game;

import java.util.ArrayList;

public class Map {

	private Tile[][] map;
	private ArrayList<Tile> generationMap;
	
	public Map(int type)
	{
		init();
		if (type == 0) //PANGEA
		{
			createPangea();
		}	else if (type == 1)  //Two continents
		{
			
		}	else if (type == 2)
		{
			
		}
	}
	
	private void init()
	{
		generationMap = new ArrayList<Tile> ();
	}
	
	private void createPangea()
	{
		generationMap.add(new Tile(0, 0, true));
		int dir = 0;
		int mapSize = 50;
		
		pangeaGen(3000, dir, 0, 0, mapSize/2);
		
		map = convertToArray(generationMap);
		map = thickenMap(map);
		generationMap = null;
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
	
	
	
	
	
	
	
	
	//Used for map generation, converts the arraylist of chunks into an array of chunks
	public Tile[][] convertToArray(ArrayList<Tile> toBeConverted)
	{
		int highX = 0;
		int highY = 0;
		int lowX = 100000;
		int lowY = 100000;
		for (Tile t : toBeConverted)
		{
			if (highX < t.getX())
			{
				highX = t.getX();
			}
			if (highY < t.getY())
			{
				highY = t.getY();
			}
			
			if (lowX > t.getX())
			{
				lowX = t.getX();
			}
			if (lowY > t.getY())
			{
				lowY = t.getY();
			}
		}
		System.out.println("High and Lows found.");
		System.out.println("X : (" + lowX + ", " + highX + ")");
		System.out.println("Y : (" + lowY + ", " + highY + ")");
		System.out.println("X range: " + (highX - lowX));
		System.out.println("Y range: " + (highY - lowY));
		
		int outerborder = 5;
		
		Tile[][] newMap = new Tile[(highX-lowX) + outerborder*2][(highY-lowY) + outerborder*2];
		
		//System.out.println("X range: " + newMap.length);
		//System.out.println("Y range: " + newMap[0].length)
		
		
		//The array should be able to fit the whole area of the field as well
		//as a bit around for thickening.
		
		
		//Go through every Chunk in field and set its spot in the array
		for (Tile t : toBeConverted)
		{
			//Set the position to the center (plus a bit change for outer border)
			//Substracting the low (which is a negative num) will get you to 0. adding the outerborder
			//Keeps some room for the water.
			int newX = t.getX() - lowX + (outerborder);
			int newY = t.getY() - lowY + (outerborder);
			newMap[newX][newY] = t;
			
		}
		
		//Set the water if not yet established as a Chunk
		//Also this makes all the chunks POSITIV
		for (int r = 0; r < newMap.length; r++)
		{
			for (int c = 0; c < newMap[r].length; c++)
			{
				if (newMap[r][c] == null)
				{
					//Set as water if not yet set
					newMap[r][c] = new Tile(r + lowX - outerborder, c + lowY - outerborder, false);
					
				}
				//System.out.print(newMap[r][c].getDif() + " ");
			}
			//System.out.println("");
		}
		
		return newMap;
	}
	
	
	public Tile[][] thickenMap(Tile[][] barrenMap)
	{
		Tile[][] newMap = new Tile[barrenMap.length][barrenMap[0].length];
		
		int outerborder = 5;
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
						
						newMap[xPos][yPos] = new Tile(xPos-r + barrenMap[r][c].getX(), yPos-c + barrenMap[r][c].getY(), true);
					}
				}
					
			}
		}
		
		
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
			generationMap.add(new Tile(x, y, true));
		}
		return true;
	}
	
}
