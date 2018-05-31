package game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

//This is a player class. Its just an example of how these classes would work-ish
//A Player object would have to be created in the Game class and instantiated.
//tick() and render() methods would have to be called in those methods from Game
public class Player {
	
	private String userName;
	
	
	public Player(String user)
	{
		this.userName = user;
	}
	
	public void tick()
	{
		
	}
	
	public void render(Graphics g)
	{
		
	}
	
	public String getUserName() {
		return this.userName;
	}

}
