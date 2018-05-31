package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class UI {

	Player player;
	boolean open;
	
	
	public UI(Player p)
	{
		player = p;
		open = true;
	}
	
	public void render(Graphics g)
	{
		//Top bar is always shown
		g.setColor(Color.CYAN);
		g.fill3DRect(-(Game.WIDTH/2*Game.SCALE), 0, (Game.WIDTH*2)*Game.SCALE, Game.HEIGHT/30*Game.SCALE, false);
		g.setColor(Color.WHITE);
		int amt = 10 / Game.SCALE;
		
		int[] y = new int[5];
		for (int i = 0; i < player.getCities().size(); i++){
			for (int l = 0; l < 5; l++)
			{
				y[l] += player.getCities().get(i).getYields()[l];
			}
		}
		
		g.setFont(new Font("TimesRoman", Font.PLAIN, 6 + (Game.SCALE*4))); 
		g.drawString("Player: " + player.getName(), (0*Game.WIDTH+1)/amt, Game.HEIGHT/36*Game.SCALE);
		g.drawString("Food: " + y[0], 1*Game.WIDTH/amt, Game.HEIGHT/36*Game.SCALE);
		g.drawString("Wood: " + y[1], 2*Game.WIDTH/amt, Game.HEIGHT/36*Game.SCALE);
		g.drawString("Stone: " + y[2], 3*Game.WIDTH/amt, Game.HEIGHT/36*Game.SCALE);
		g.drawString("Gold: " + y[3], 4*Game.WIDTH/amt, Game.HEIGHT/36*Game.SCALE);
		g.drawString("Culture: " + y[4], 5*Game.WIDTH/amt, Game.HEIGHT/36*Game.SCALE);
	
		if (open)
		{
			g.setColor(Color.CYAN);
			//g.fill3DRect(-(Game.WIDTH/2*Game.SCALE), 0, (Game.WIDTH*2)*Game.SCALE, Game.HEIGHT/20*Game.SCALE, false);

		}
	}
	
	public boolean getOpen()	{	return open;	}
}
