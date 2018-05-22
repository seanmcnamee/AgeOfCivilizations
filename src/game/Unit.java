package game;

public class Unit {
	private int health;
	//Depending on the health, you have more or less people inside your unit.
	//Healing up a unit can actually bring those people bacl
	private double xPos, yPos, targetX, targetY;
	private int builds;
	private boolean isMilitia;
	
	
	private final int maxSoldiers = 6;
	private final double speed = .3; //In tiles per tick
	
	
	//Units are created by converting citizens to soldiers.
	//Units can either be militia or a professional army. (fromCitizen)
	 
	public void tick()
	{
		
	}
	
	public void moveTo(double x, double y)
	{
		targetX = x;
		targetY = y;
	}
}
