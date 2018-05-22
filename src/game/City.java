package game;

import java.util.ArrayList;

public class City {

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
	
	public City() //Default City
	{
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
	
	
	
	
	
	
	
}
