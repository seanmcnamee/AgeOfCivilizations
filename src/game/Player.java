package game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

//This is a player class. Its just an example of how these classes would work-ish
//A Player object would have to be created in the Game class and instantiated.
//tick() and render() methods would have to be called in those methods from Game
public class Player {
	private String name;
	private ArrayList<City> cities;
	UI ui;
	
	public Player(String n)
	{
		name = n;
		cities = new ArrayList<City> ();
		ui = new UI(this);
	}
	
	public void tick()
	{
		//System.out.println("Updating Player " + name);
		for (int i = 0; i < cities.size(); i++)
		{
			cities.get(i).tick();
		}
		
	}
	
	public void render(Graphics g)
	{
		ui.render(g);
	}
	
	public void createCity(Map m, int xP, int yP)
	{
		System.out.println(cities.size());
		cities.add(new City(xP, yP, m.getMap(), this));
		
	}

	//public String toString()	{	return "" + cities.size();}
	public String getName()	{	return name;	}
	public ArrayList<City> getCities()	{	return cities;	}
}
