import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Snake {

	Node head;
	Node tail;
	int length;
	int size = Yard.BLOCK_SIZE;
	int up,down,left,right;


	Random r = new Random();
	Node node = new Node(r.nextInt(Yard.ROWS - 15)+9, r.nextInt(Yard.COLS- 15)+9, Direction.randomDirection());
	

	
	public Snake() {
		this.head = node;
		this.tail = node;
		length = 1;
	}
	
	public void draw(Graphics g) {
		move();	
		for(Node node = head; node != null; node =  node.next){
			node.draw(g);
		}
	}
	
	public void addToTail() {
		Node node = null;
		switch (tail.direction) {
		case Left:
			node = new Node(tail.rows, tail.cols + 1, Direction.Left);
			break;
		case Right:
			node = new Node(tail.rows, tail.cols - 1, Direction.Right);
			break;
		case Up:
			node = new Node(tail.rows + 1 , tail.cols, Direction.Up);
			break;
		case Down:
			node = new Node(tail.rows - 1, tail.cols, Direction.Down);
			break;
		default:
			break;
		}
		tail.next = node;
		node.prev = tail;
		tail = node;
		length ++;
	}
	
	public void addToHead() {
		Node node = null;
		switch (head.direction) {
		case Left:
			node = new Node(head.rows, head.cols - 1, Direction.Left);
			break;
		case Right:
			node = new Node(head.rows, head.cols + 1, Direction.Right);
			break;
		case Up:
			node = new Node(head.rows - 1 , head.cols, Direction.Up);
			break;
		case Down:
			node = new Node(head.rows + 1, head.cols, Direction.Down);
			break;
		default:
			break;
		}
		node.next = head;
		head.prev = node;
		head = node;
		length++;
	}
	

	
	public void deleteTail() {
		if(length == 0) return;
		tail = tail.prev;
		tail.next = null;
	}
	
	public void move() {
		addToHead();
		deleteTail();
	}

	public void eat(Egg egg) {
		
		if(this.getRectangle().equals(egg.getRectangle())){
			egg.reDraw();
			this.addToTail();
			Yard.Score += 1;
		}
	
	}
	
	public Rectangle getRectangle(){
		
		return new Rectangle
				(head.getCols() * size, head.getRows() * size, size, size);
	}
	
	public void checkDeath() {
		if(head.rows < 3 || head.cols < 1 || head.rows > Yard.ROWS-2 || head.cols > Yard.COLS-2){
			
			Yard.rePaint = false;
			Yard.gameOver = true;
		}else {
			for(Node node = head.next; node != null; node = node.next){
				if(head.rows == node.rows && head.cols == node.cols ){
					Yard.rePaint = false;
					Yard.gameOver = true;
				}
			}
		}
		
	}
	
	class Node{
		
		int width = Yard.BLOCK_SIZE;
		int height = Yard.BLOCK_SIZE;
		int rows,cols;
		Node next;
		Node prev;
		Direction direction = Direction.Left;
		
		public Node(int rows, int cols, Direction direction) {
			this.rows = rows;
			this.cols = cols;
			this.direction = direction;
		}
		
		public int getRows() {
			return rows;
		}

		public int getCols() {
			return cols;
		}

	

		public void draw(Graphics g){
			Color a = new Color(255, 213, 164);

			g.setColor(a);
			g.fillOval(cols * size, rows * size , width, height);
		
			
		}
		
	}

	public void keyPressed(KeyEvent e) {
		
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			if(head.direction != Direction.Right){
				head.direction = Direction.Left;
				left++;
			}
			break;
		case KeyEvent.VK_RIGHT:
			if(head.direction != Direction.Left){
				head.direction = Direction.Right;
				right++;
			}
			break;
		case KeyEvent.VK_UP:
			if(head.direction != Direction.Down){
				head.direction = Direction.Up;
				up++;
			}
			break;
		case KeyEvent.VK_DOWN:
			if(head.direction != Direction.Up){
				head.direction = Direction.Down;
				down++;
			}
			break;
		default:
			break;
		}
		
		
		
	}

	
	
	
}
