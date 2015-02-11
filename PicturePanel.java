import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Math;
import java.awt.geom.*;

public class PicturePanel extends JPanel {
	
	private Color backgroundcolor;
	private Color color1;
	private Color color2;
	private int xloc1, yloc1;
	private int xloc2, yloc2;
	private int dimx, dimy;
	private int enemyx, enemyy;
	private int enemyvolx, enemyvoly;
	private boolean enemystatus;
	public Timer timer;
	public int points1; //how many points player 1 has
	public int points2; //player 2's points

	public PicturePanel(Color c) {
		generalSetup(backgroundcolor);
		enemyvolx = -7;
		enemyvoly = 5;
	}

	public PicturePanel() {
		generalSetup(Color.white);
		enemyvolx = -7;
		enemyvoly = 5;
	}

	public void generalSetup(Color c) {
		backgroundcolor = c;
		dimx = 400;
		dimy = 400;
		setPreferredSize(new Dimension(dimy,dimy));
		xloc1 = 10;
		yloc1 = 150;
		xloc2 = dimx-20;
		yloc2 = 150;
		enemystatus = true;
		enemyx = dimx/2;
		enemyy = 35;
		points1=0;
		points2=0;

		setupTimer();
	}

	public void setupTimer() {
		int delay = 40; //for ultra smooth 25fps
		ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timerActions();
				timer.restart();
			}
		};
		timer = new Timer(delay, taskPerformer);
		timer.setInitialDelay(delay);
		timer.start();
	}

	public void timerActions() {
		if(enemyx>(dimx-4)){ //if the ball goes out of bounds reset the position and volocity velocity, and add points to the correct player.
			enemyx = dimx/2;
			enemyy = 35;
			enemyvoly = 5;
			enemyvolx = -7;
			points1++;
		}

		if(enemyx<4){
			enemyx = dimx/2;
			enemyy = 35;
			enemyvoly = 5;
			enemyvolx = 7;
			points2++;
		}

		if(enemyy>(dimy-4)||enemyy<4){ //if the ball hits the top or bottom it bounces back with reversed velocity 
			enemyvoly=enemyvoly*(-1);
		}

		if(enemyx<xloc1+10) { //if the ball hits a paddle, reverse the x velocity, and set the y velocity to the distance from the center of the paddle. This allows for controlling of the ball with the paddle.
			if(enemyy>yloc1 && enemyy<yloc1+70){
				enemyvolx=enemyvolx*(-1);
				if(enemyy>yloc1+35){
					enemyvoly=(int) Math.sqrt((enemyx-(xloc1+5))*(enemyx-(xloc1+5))+(enemyy-(yloc1+35))*(enemyy-(yloc1+35))) / 3;
				}
				if(enemyy<yloc1+35){
					enemyvoly=(int) Math.sqrt((enemyx-(xloc1+5))*(enemyx-(xloc1+5))+(enemyy-(yloc1+35))*(enemyy-(yloc1+35))) / (-3);
				}
			}
		}

		if(enemyx>xloc2) {
			if(enemyy>yloc2 && enemyy<yloc2+70){
				enemyvolx=enemyvolx*(-1);
				if(enemyy>yloc2+35){
					enemyvoly=(int) Math.sqrt((enemyx-(xloc2+5))*(enemyx-(xloc2+5))+(enemyy-(yloc2+35))*(enemyy-(yloc2+35))) / 3;
				}
				if(enemyy<yloc2+35){
					enemyvoly=(int) Math.sqrt((enemyx-(xloc2+5))*(enemyx-(xloc2+5))+(enemyy-(yloc2+35))*(enemyy-(yloc2+35))) / (-3);
				}
			}
		}
		
		enemyx+=enemyvolx;
		enemyy+=enemyvoly;

		repaint();
	}

	public void moveUp() {
		if (yloc1>-50){ //if statements to ensure that paddle does not run away.
			yloc1-=20;
		}
		repaint();
	}

	public void moveDown() {
		if (yloc1<dimy-50){
			yloc1+=20;
		}
		repaint();
	}

	public void moveS() {
		if (yloc2<dimy-50){
			yloc2+=20;
		}
		repaint();
	}

	public void moveW() {
		if (yloc2>-50){
			yloc2-=20;	
		}
		repaint();
	}

	public void displayEnemy(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;

		Shape enemyCircle = new Ellipse2D.Double(enemyx - 4, enemyy - 4, 2.0 * 4, 2.0 * 4); //creates the ball as a circle
    	g2d.setColor(Color.white);
    	g2d.draw(enemyCircle);
    	g2d.fill(enemyCircle);

	}

	public void reset() {
		generalSetup(backgroundcolor);
		repaint();
	}

	public void paintComponent(Graphics g) {

		Graphics2D g2 = (Graphics2D) g; //2d for circles

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); //high quality graphics

		super.paintComponent(g);

		int width = getWidth();
		int height = getHeight();

		setBackground(Color.black);

		Shape player1 = new Rectangle(xloc1, yloc1, 10, 70);
    	g2.setColor(Color.white);
    	g2.draw(player1);
    	g2.fill(player1);

    	Shape player2 = new Rectangle(xloc2, yloc2, 10, 70);
    	g2.setColor(Color.white);
    	g2.draw(player2);
    	g2.fill(player2);

    	if (points1==0){ //draws the scoring with black and white rectangles based on the points variables.
    		g2.setColor(Color.white);
    		g2.fillRect(170, 15, 15, 25);
    		g2.setColor(Color.black);
    		g2.fillRect(175, 20, 5, 15);
    	} else if (points1==1){
			g2.setColor(Color.white);
    		g2.fillRect(180, 15, 5, 25);
    	} else if (points1==2){
    		g2.setColor(Color.white);
    		g2.fillRect(170, 15, 15, 25);
    		g2.setColor(Color.black);
    		g2.fillRect(170, 20, 10, 5);
    		g2.fillRect(175, 30, 10, 5);
    	} else if (points1==3){
    		g2.setColor(Color.white);
    		g2.fillRect(170, 15, 15, 25);
    		g2.setColor(Color.black);
    		g2.fillRect(170, 20, 10, 5);
    		g2.fillRect(170, 30, 10, 5);
    	} else if (points1==4){
    		g2.setColor(Color.white);
    		g2.fillRect(170, 15, 15, 25);
    		g2.setColor(Color.black);
    		g2.fillRect(175, 15, 5, 10);
    		g2.fillRect(170, 30, 10, 10);
    	} else{
    		g2.setColor(Color.white);
    		g2.fillRect(170, 15, 15, 25);
    		g2.setColor(Color.black);
    		g2.fillRect(175, 20, 10, 5);
    		g2.fillRect(170, 30, 10, 5);
    	} 


    	if (points2==0){
    		g2.setColor(Color.white);
    		g2.fillRect(210, 15, 15, 25);
    		g2.setColor(Color.black);
    		g2.fillRect(215, 20, 5, 15);
    	} else if (points2==1){
    		g2.setColor(Color.white);
    		g2.fillRect(220, 15, 5, 25);
    	} else if (points2==2){
    		g2.setColor(Color.white);
    		g2.fillRect(210, 15, 15, 25);
    		g2.setColor(Color.black);
    		g2.fillRect(210, 20, 10, 5);
    		g2.fillRect(215, 30, 10, 5);
    	} else if (points2==3){
    		g2.setColor(Color.white);
    		g2.fillRect(210, 15, 15, 25);
    		g2.setColor(Color.black);
    		g2.fillRect(210, 20, 10, 5);
    		g2.fillRect(210, 30, 10, 5);
    	} else if (points2==4){
    		g2.setColor(Color.white);
    		g2.fillRect(210, 15, 15, 25);
    		g2.setColor(Color.black);
    		g2.fillRect(215, 15, 5, 10);
    		g2.fillRect(210, 30, 10, 10);
    	} else{
    		g2.setColor(Color.white);
    		g2.fillRect(210, 15, 15, 25);
    		g2.setColor(Color.black);
    		g2.fillRect(215, 20, 10, 5);
    		g2.fillRect(210, 30, 10, 5);
    	}

    	displayEnemy(g); //display enemy last so it is on top of the scoring

	}
}
