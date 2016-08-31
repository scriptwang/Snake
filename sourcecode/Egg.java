import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;



public class Egg {

	int rows,cols;
	int size = Yard.BLOCK_SIZE;
	static Random ran = new Random();

	
	
	public Egg(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
	}

	public Egg(){
		
		this(ran.nextInt(Yard.ROWS-5)+4, ran.nextInt(Yard.COLS-5)+4);
	}


	public void draw(Graphics g){
		g.setColor(Color.yellow);
		g.fillOval(cols * size, rows * size, size, size);
	}



	public Rectangle getRectangle() {
		return new Rectangle(cols * size, rows * size, size, size);
		
	}

	public int[] getPos() {
		int[] pos = new int[2];
		pos[0] = rows;
		pos[1] = cols;
		return pos;
		
	}

	public void reDraw() {
		
		rows = ran.nextInt(Yard.ROWS-5)+4;
		cols = ran.nextInt(Yard.COLS-5)+4;
	}
	
	
}
