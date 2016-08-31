import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Yard extends Frame {



	PaintThread paintThread = new PaintThread();
	public static final int ROWS = 50;
	public static final int COLS = 50;
	public static final int BLOCK_SIZE = 10;
	Snake snake = new Snake();
	static Egg egg = new Egg();
	static boolean rePaint = true;
	static boolean gameOver = false;
	static Font font = new Font("Î¢ÈíÑÅºÚ",1 ,15);
	static int Score; 
	Image screenImage = null;

	
	public static void main(String[] args) {
		new Yard().launch();
	}
	
	


	public void launch(){
		setTitle("Ì°³ÔÉß GO!");
		setBounds(100, 100, COLS * BLOCK_SIZE, ROWS * BLOCK_SIZE);
		setBackground(new Color(175, 164, 255));
		setVisible(true);
		setResizable(false);
	
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			
		});
		addKeyListener(new KeyMonitor());
		new Thread(paintThread).start();
	}
	
	@Override
	public void update(Graphics g) {
		if (screenImage == null){
				
			screenImage = createImage(COLS * BLOCK_SIZE,ROWS * BLOCK_SIZE);
		}
		Graphics sg = screenImage.getGraphics();
		sg.setColor(new Color(175, 164, 255));
		sg.fillRect(0,0, COLS * BLOCK_SIZE, ROWS * BLOCK_SIZE);
		paint(sg);
		g.drawImage(screenImage, 0, 0, null);
	}
	
	@Override
	public void paint(Graphics g) {
		/*
		g.setColor(Color.DARK_GRAY);
		//»­³öºáÏß
		for(int i=1; i< ROWS+1; i++ ){
			g.drawLine(0, BLOCK_SIZE * i, COLS * BLOCK_SIZE, BLOCK_SIZE * i);
		}
		//»­³öÊúÏß
		for(int i=1; i< COLS+1; i++ ){
			g.drawLine(BLOCK_SIZE * i, 0, BLOCK_SIZE * i, ROWS * BLOCK_SIZE);
		}*/

		g.setFont(font);
		g.setColor(Color.white);
		g.drawString("Snake Postion : [ " + (snake.head.rows - 2)+" , "+snake.head.cols+" ]", 25, 50);
		g.drawString("Egg Postion : [ " + ( egg.rows -2 ) + " , " + egg.cols + " ]" , 25, 67);
		g.drawString("Score : " + Score , 25, 87);
		g.drawString("Moveing count : " + snake.length , 25, 104);
		g.drawString("Up count : " + snake.up , 25, 120);
		g.drawString("Down count : " + snake.down , 25, 134);
		g.drawString("Left count : " + snake.left , 25, 148);
		g.drawString("Right count : " + snake.right , 25, 164);
		g.drawString("Key pressed count : " + (snake.left+snake.right+snake.up+snake.down) , 25, 180);

		
		snake.draw(g);
		egg.draw(g);
		snake.eat(egg);
		snake.checkDeath();
		if(gameOver){
			g.setColor(Color.yellow);
			g.setFont(new Font("Î¢ÈíÑÅºÚ", 0, 50));
			g.drawString("Game Over > <", 80 , 270);
		}	
	}
	


	
	class KeyMonitor extends KeyAdapter{

		@Override
		public void keyPressed(KeyEvent e) {
			snake.keyPressed(e);
			
		}
		
	}
	
	public class PaintThread implements Runnable{
		@Override
		public void run() {
			while(rePaint){
				repaint();
				try {
					Thread.sleep(60);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}

	
	}


}
