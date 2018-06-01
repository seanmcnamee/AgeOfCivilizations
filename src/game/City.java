package game;

import java.awt.Graphics;
import java.util.ArrayList;

public class City {

	//This represents what 'Tiles' the city owns
	private Tile[][] tilesOwned;
	private int population;
	private String ID;
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
	
	public City(int xp, int yp, Tile[][] map, Player p)
	{
		map[xp][yp].setCapital(true);
		tilesOwned = new Tile[map.length][map[0].length];
		for (int x = xp - 1; x <= xp + 1; x++)
		{
			for (int y = yp - 1; y <= yp + 1; y++)
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
		ID = p.getName() + p.getCities().size();
		System.out.println(ID);
	}
	
	
	
	public void expandControl(Tile[][] map)
	{
		
		ArrayList<Tile> possibles = getPossibleExpansions(map);
		
		if (possibles != null && possibles.size() > 0)
		{
			ArrayList<Tile> largest = new ArrayList<Tile> ();
			
			Tile toAdd = possibles.get(0);
			for (int i = 0; i < possibles.size(); i++)
			{
				if (possibles.get(i).getYieldValue() > toAdd.getYieldValue())
				{
					toAdd = possibles.get(i);
				}
			}
			
			for (int i = 0; i < possibles.size(); i++)
			{
				if (possibles.get(i).getYieldValue() == toAdd.getYieldValue())
				{
					largest.add(possibles.get(i));
				}
			}
			
			toAdd = largest.get((int) (Math.random()*largest.size()));
			
			map[toAdd.getX()][toAdd.getY()].setCity(this);
			tilesOwned[toAdd.getX()][toAdd.getY()] = map[toAdd.getX()][toAdd.getY()];
			
		}
		
	}
	
	//Getters / Setters
	//public int getX()	{	return xPos;	}
	//public int getY()	{	return yPos;	}
	public String getID()	{	return ID;	}
	public int[] getYields()	{	return yields;	}
	public int getPop(){	return population;	}
	
	
	//Tick and Render
	public void tick()
	{
		System.out.println(ID + " yielding...");
		for (int x = 0; x < tilesOwned.length; x++)
		{
			for (int y = 0; y < tilesOwned[x].length; y++)
			{
				if (tilesOwned[x][y] != null && tilesOwned[x][y].getHarvesting())
				{
					for (int i = 0; i < 5; i++)
					{
						yields[i] += tilesOwned[x][y].getYield()[i];
					}
				}
			}
		}
		yields[0] += 2;
		System.out.println("Pop: " + population);
		yields[0] -= population;
		
			//pop*1.5 + 10 > food
	}
	
	
	/*
	 * Helpers methods for expanding a City's control Tile.
	 */
	private ArrayList<Tile> getPossibleExpansions(Tile[][] map)
	{
		ArrayList<Tile> possibles = new ArrayList<Tile> ();
		
		for (int x = 1; x < map.length-1; x++)
		{
			for (int y = 1; y < map[x].length-1; y++)
			{
				if (tilesOwned[x][y] != null)
				{
					
					//TODO fix this to make it up down left right only
					for (int c = -1; c <= 1; c += 2)
					{
						for (int w = 0; w < 2; w++)
						{
							int cX = 0;
							int cY = 0;
							if (w == 0)
							{
								cX = c;
							}	else
							{
								cY = c;
							}
							
							if (tilesOwned[x+cX][y+cY] == null && map[x+cX][y+cY].getYieldable() && (map[x+cX][y+cY].getCity() == null))
							{
								possibles = addPossible(map[x+cX][y+cY], possibles);
							}
						}
					}
				}
			}
		}
		return possibles;
	}
	
	private ArrayList<Tile> addPossible(Tile t, ArrayList<Tile> possibles)
	{
		boolean shouldAdd = true;
		for (int i = 0; i < possibles.size(); i++)
		{
			if (possibles.get(i) == t)
			{
				shouldAdd = false;
			}
		}
		if (shouldAdd)
		{
			possibles.add(t);
		}
		return possibles;
	}
	
	
	
}
