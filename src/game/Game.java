package game;


import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;


//Our main class is 'Game'. Canvas is the GUI type screen that is created, and
//allows for the game to be ran (run() and all those methods that are the brains)
public class Game extends Canvas implements Runnable{

	//Just sorta something, I always ignore it
	private static final long serialVersionUID = 1L;

	
	//The size and scaling of the game. SCALE is VERY useful (change to make EVERYTHING it bigger/smaller)
	public static final int WIDTH = 300;
	public static final int HEIGHT = WIDTH/12 * 9;
	public static final int SCALE = 4;
	public final String NAME = "Age of Civilizations";
	public static int updateCount = 180;
	//The JFrame is the GUI itself that everything goes onto
	private JFrame frame;
	
	//Used to see if the game is still going on
	public boolean running = false;
	
	//This is the 'sprite sheet' that is used for all loaded images. Rather than many images, all are
	//in one sheet.
	
	Map field;
	ArrayList<Player> players;
	
	public Game() //This is the constructor for this class. Here, we set up the GUI
	{
		//Most of this stuff does what it says... If you want to see what it does,
		//mess around with it a bit (but put it back when you're done)
		
		setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		
		//Create the GUI itself
		frame = new JFrame(NAME);
		
		//Allow for trapclose (X button)
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		frame.add(this, BorderLayout.CENTER);
		frame.pack();
		
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	
	public void init()//This is called when the game first starts to run.
					//It sets up the inital stuff for the game
	{
		
		//Load in the spritesheet (saved in res folder)
		//BufferedImageLoader loader = new BufferedImageLoader();
		//try {
		//	spriteSheet = loader.loadImage("/sprite_sheet.png");
		//} catch (IOException e) {
		//	e.printStackTrace();
		//}
		
		//Used for KeyInput (uses another class to do this)
		requestFocus();
		addKeyListener(new KeyInput(this));
		
		//Uses the SpriteSheet class to get a specific image from the sprite sheet
		//SpriteSheet ss = new SpriteSheet(spriteSheet);
		
		//This would return an image at the top left that is 1 'unit' x 1 'unit'
		//See SpriteSheet class for more info
		//ss.grabImage(0, 0, 1, 1); 
		
		field = new Map(1);
		players = new ArrayList<Player> ();
		players.add(new Player("Sean"));
		//players.add(new Player("TJ"));
		players.get(0).createCity(field, 15, 15);
		//players.get(0).createCity(field, 50, 20);
		//players.get(1).createCity(field, 15, 20);
	}
	
	public synchronized void start() //This is what's first called from the main method.
									//It makes sure running is true and creates a new 'Thread'
									//so that Runnable can begin
	{
		running = true;
		new Thread(this).start();
	}
	
	public synchronized void stop()//Just stops the game by making running false...
	{
		running = false;
	}
	
	
	public void run() //This is what runs the game
	{
		init();
		
		//Sets the current time, and the amount of nanoseconds that should pass per tick
		//This prevents super fast computers from having game speed be super fast (process quicker)
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000D/60D;
		double delta = 0;
		
		//Used to print out ticks per second (should be 60) and FPS
		int ticks = 0;
		int frames = 0;
		long lastTimer = System.currentTimeMillis();
		
		
		
		while (running)	//This loop itself if the brain that
		{
			long now = System.nanoTime();
			delta += (now - lastTime) / nsPerTick;
			lastTime = now;
			
			//Allows things to be printed out constantly without overprocessing the game
			while (delta >= 1) 
			{
				ticks++;
				tick(); //Gameplay (brain)
				delta --;
			}
			
			
			frames++;
			render();//Gameplay (display)
			
			//Prints out FPS and TPS dta
			if (System.currentTimeMillis() - lastTimer >= 1000)
			{
				lastTimer += 1000;
				
				//the more stuff moving in the game, the less the FPS will be
				System.out.println("fps: " + frames + ", tps: " + ticks);
				frames = 0;
				ticks = 0;
			}
			
		}
	}
	
	
	public void tick() //Update Game Logic
	{
		//Here you could call object.tick(), with a tick method in each of the classes you have for
		//The game like (Player, Monster, Bullet, Obstacle, etc.)
		//If certain methods need it, you can even have certain classes take in parameters
		updateCount --;
		if (updateCount <= 0)
		{
			for (int i = 0; i < players.size(); i++)
			{
				players.get(i).tick();
				for (int l = 0; l < players.get(i).getCities().size(); l++)
				{
					if (players.get(i).getCities().get(l).getPop()*1.5 + 10 < players.get(i).getCities().get(l).getYields()[0])
					{
						players.get(i).getCities().get(l).expandControl(field.getMap());
					}
				}
			}
			updateCount = 180;
		}
		
		
	}

	public void render() //Update Game Display
	{
		
		//Printing to screen
		BufferStrategy bs = getBufferStrategy(); 
		if (bs == null)
		{
			createBufferStrategy(3); //three buffers (usually 'doubleBuffer, this is 'TripleBuffer'
			return;
		}
		
		//Everything drawn will go through Graphics or some type of Graphics
		Graphics g = bs.getDrawGraphics();
		//////////////  Start of Drawing Stuff to Screen	////////////////////////
		
		//Here you would call object.render(g), passing in the graphics so it can be printed
		//In that object's class, but go onto the screen here. 
		//Text can even be printed out with g.drawString(), (just have to set up a Font/Color)
		
		g.setColor(Color.BLACK);//Go ahead and change this color (like CYAN or WHITE, etc)
		g.fillRect(0, 0, getWidth(), getHeight());//This just fills the screen in that color
		field.render(g);
		players.get(0).render(g);
		
		//////////// End of Drawing Stuff to screen		////////////////////////
		
		
		g.dispose();
		bs.show();
	}
	
	//When a key is preesed down, this is called
	public void keyPressed(KeyEvent e)
	{
		//players.get(0).getCities().get(0).expandControl(field.getMap());
		//players.get(0).getCities().get(1).expandControl(field.getMap());
		//players.get(1).getCities().get(0).expandControl(field.getMap());
		//int key = e.getKeyCode();
	}
	
	//When the key it finished being pressed, this is called
	public void keyReleased(KeyEvent e)
	{
		//int key = e.getKeyCode();
	}
	
	//Main method of the program
	public static void main(String[] args)
	{
		new Game().start();
	}
	

	
}
