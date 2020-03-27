package snakeGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.awt.Font;

import javax.swing.Timer;


import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Gameplay extends JPanel implements KeyListener, ActionListener{
	
	private boolean play = false;
	private int[] xlength = new int[750];
	private int[] ylength = new int[750];
	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;
	
	private ImageIcon titleImage;
	
	private ImageIcon leftmouth;
	private ImageIcon rightmouth;
	private ImageIcon upmouth;
	private ImageIcon downmouth;
	
	private int lengthofsnake = 3;
	
	private ImageIcon snakeimage;
	
	private int move = 0;
	
	private int score = 0;
	
	private int []  dotxposition = {25, 50, 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325
	                                , 350, 375, 400, 425, 450, 475, 500, 525, 550, 575, 600, 625, 650, 675, 700, 725, 750, 775, 800, 825, 850};
	
	private int []  dotyposition = {75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325
            						, 350, 375, 400, 425, 450, 475, 500, 525, 550, 575, 600, 625};
	
	private ImageIcon dotimage;
	private Random random = new Random();
	private int xpos = random.nextInt(34);
	private int ypos = random.nextInt(23);
	
	//timer class to manage snake movement
	private Timer timer;
	private int delay = 100;
	
	public Gameplay()
	{
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
	}
	
	public void paint(Graphics g)
	{
		if(move == 0)
		{
			xlength[2] = 50;
			xlength[1] = 75;
			xlength[0] = 100;
			
			ylength[2] = 100;
			ylength[1] = 100;
			ylength[0] = 100;
		}
		//draw title image border
		g.setColor(Color.white);
		g.drawRect(24, 10, 850, 55);
		
		//draw the title image
		titleImage = new ImageIcon("snaketitle.jpg");
		titleImage.paintIcon(this, g, 25, 11);
		
		//draw border for game
		g.setColor(Color.white);
		g.drawRect(24, 10, 850, 557);
		
		//draw background for game
		g.setColor(Color.black);
		g.fillRect(25, 75, 850, 575);
		
		//draw score
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.PLAIN, 14));
		g.drawString("Scores: "+score, 780, 30);
		
		//draw length of snake
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.PLAIN, 14));
		g.drawString("Length: "+lengthofsnake, 780, 50);
		
		rightmouth = new ImageIcon("rightmouth.png");
		rightmouth.paintIcon(this, g, xlength[0], ylength[0]);
		
		for (int x = 0; x < lengthofsnake; x++)
		{
			if(x == 0 && right) 
			{
				rightmouth = new ImageIcon("rightmouth.png");
				rightmouth.paintIcon(this, g, xlength[x], ylength[x]);
			}
			if(x == 0 && left) 
			{
				leftmouth = new ImageIcon("leftmouth.png");
				leftmouth.paintIcon(this, g, xlength[x], ylength[x]);
			}
			if(x == 0 && up) 
			{
				upmouth = new ImageIcon("upmouth.png");
				upmouth.paintIcon(this, g, xlength[x], ylength[x]);
			}
			if(x == 0 && down) 
			{
				downmouth = new ImageIcon("downmouth.png");
				downmouth.paintIcon(this, g, xlength[x], ylength[x]);
			}
			
			if(x != 0)
			{
				snakeimage = new ImageIcon("snakeimage.png");
				snakeimage.paintIcon(this, g, xlength[x], ylength[x]);
			}		
		}
		
		dotimage = new ImageIcon("dot.png");
		
		if((dotxposition[xpos] == xlength[0] && dotyposition[ypos] == ylength[0]))
		{
			score++;
			lengthofsnake++;
			xpos = random.nextInt(34);
			ypos = random.nextInt(23);
		}
		
		dotimage.paintIcon(this, g, dotxposition[xpos], dotyposition[ypos]);
		
		for(int j = 1; j < lengthofsnake; j++) 
		{
			if(xlength[j] == xlength[0] && ylength[j] == ylength[0])
			{
				right = false;
				left = false;
				up = false;
				down = false;
				
				g.setColor(Color.red);
				g.setFont(new Font("arial", Font.BOLD, 50));
				g.drawString("Game Over", 300, 300);
				
				g.setFont(new Font("arial", Font.BOLD, 20));
				g.drawString("Press spacebar to restart!", 310, 340);
			}
		}
		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		if(right)
		{
			for(int i = lengthofsnake - 1; i >=0; i--)
			{
				ylength[i+1] = ylength[i];
			}
			for(int i = lengthofsnake; i >= 0; i--)
			{
				if(i == 0)
				{
					xlength[i] = xlength[i] + 25;
				}
				else
				{
					xlength[i] = xlength[i-1];
				}
				
				if(xlength[i] > 850)
				{
					xlength[i] = 25;
				}
			}
			repaint();
		}
		
		if(left)
		{
			for(int i = lengthofsnake - 1; i >=0; i--)
			{
				ylength[i+1] = ylength[i];
			}
			for(int i = lengthofsnake; i >= 0; i--)
			{
				if(i == 0)
				{
					xlength[i] = xlength[i] - 25;
				}
				else
				{
					xlength[i] = xlength[i-1];
				}
				
				if(xlength[i] < 25)
				{
					xlength[i] = 850;
				}
			}
			repaint();
		}
		
		if(up)
		{
			for(int i = lengthofsnake - 1; i >=0; i--)
			{
				xlength[i+1] = xlength[i];
			}
			for(int i = lengthofsnake; i >= 0; i--)
			{
				if(i == 0)
				{
					ylength[i] = ylength[i] - 25;
				}
				else
				{
					ylength[i] = ylength[i-1];
				}
				
				if(ylength[i] < 75)
				{
					ylength[i] = 625;
				}
			}
			repaint();
		}
		
		if(down)
		{
			for(int i = lengthofsnake - 1; i >=0; i--)
			{
				xlength[i+1] = xlength[i];
			}
			for(int i = lengthofsnake; i >= 0; i--)
			{
				if(i == 0)
				{
					ylength[i] = ylength[i] + 25;
				}
				else
				{
					ylength[i] = ylength[i-1];
				}
				
				if(ylength[i] > 625)
				{
					ylength[i] = 75;
				}
			}
			repaint();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		if(e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			move = 0;
			score = 0;
			lengthofsnake = 3;
			repaint();
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) 
		{
			move++;
			right = true;
			if(!left)
			{
				right = true;
			}
			else
			{
				right = false;
				left = true;
			}
			
			up = false;
			down = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT) 
		{
			move++;
			left = true;
			if(!right)
			{
				left = true;
			}
			else
			{
				left = false;
				right = true;
			}
			
			up = false;
			down = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_UP) 
		{
			move++;
			up = true;
			if(!down)
			{
				up = true;
			}
			else
			{
				up = false;
				down = true;
			}
			
			left = false;
			right = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_DOWN) 
		{
			move++;
			down = true;
			if(!up)
			{
				down = true;
			}
			else
			{
				down = false;
				up = true;
			}
			
			left = false;
			right = false;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
