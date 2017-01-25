import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.*;

/*	
 * 	Main class of the paddlify game
 * 
 * 	Paddle can be moved with left arrow, right arrow, and press space to be in the same place
 * */

public class Game{

	public static void main(String[] args) throws InterruptedException, InvocationTargetException{
		
		//	Sets the GUI screen with the given width and height
		JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        panel.setSize(380, 400);
        panel.setLayout(null);
        frame.add(panel);
        frame.setSize(385, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //	Creates a BrickList instance which keeps all the bricks of the game, and adds them to the screen
        BrickList bricklist = new BrickList();
        bricklist.addBrick(panel);
        JLabel[] bricks= bricklist.labels;
        
        //	Creates a paddle, and a ball, then adds them to the screen
        Paddle paddle = new Paddle(180,320);
        JLabel labelpaddle = paddle.getElement();
        Ball ball = new Ball(200,310);
        JLabel labelball = ball.getElement();
        panel.add(labelball);
        panel.add(labelpaddle);
        
        //	Adds an invisible game over text to the screen
		JLabel gameOver = new JLabel();
		gameOver.setLocation(150, 200);
		gameOver.setText("GAME OVER");
		gameOver.setSize(200,50);
		panel.add(gameOver);
		gameOver.setVisible(false);
		
		//	Makes the screen visible
        frame.setVisible(true);
        
        //	The variable round keeps the number of rounds
        int round = 0;
        
        //	Creates a new thread, and starts it
        Runnable r = new GameThread(round,panel,labelpaddle,paddle,ball,labelball,bricklist,gameOver);
        new Thread(r).start();

	}
}

//	Class for the implementation of the thread

class GameThread implements Runnable{
	
	//Number of rounds
	int round;
	//Screen instance
	JPanel panel;
	//Paddle
	JLabel paddlelabel;
	Paddle paddle;
	//Ball
	Ball ball;
	JLabel balllabel;
	//BrickList
	JLabel[] bricks;
	BrickList bricklist;
	//Game Over Text
	JLabel gameOver;
	//Keeps if a button is pressed, zero means that a button isn't pressed.
	int isPressed=0;
	
	//	Constructor of the class, assigns all of its parameters to the class variables.
	GameThread(int round, JPanel panel, JLabel paddlelabel, Paddle paddle,Ball ball,JLabel balllabel,BrickList bricklist,JLabel gameOver){
		this.round = round;
		this.panel = panel;
		this.paddlelabel = paddlelabel;
		this.paddle = paddle;
		this.ball = ball;
		this.balllabel = balllabel;
		this.bricks = bricklist.labels;
		this.gameOver = gameOver;
		this.bricklist = bricklist;
	}
	
	
	@Override
	public void run() {
		
		//	For capturing the pressed keys
        panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "right");
        panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "left");
        panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "space");
        
        //	The action to take when the right arrow key is pressed.
        panel.getActionMap().put("right", new AbstractAction() {
        	
        	@Override
			public void actionPerformed(ActionEvent e) {
        		//	if round is not equal to -1, it means that the game isn't over.
        		if(round!=-1){
        			//	checks if it's the second round
        			if(round%2==0){
        				//	moves the paddle to right, and prints the status to console, and updates isPressed variable.
        				paddle.moveRight(paddlelabel);
        				System.out.println("Paddle is moved to right");
        				isPressed=1;
        			}
        			else
        				System.out.println("Paddle cannot be moved, it is not the second round");
        		}
        	}
        });
        
        //	The action to take when the left arrow key is pressed
        panel.getActionMap().put("left", new AbstractAction() {
        	
        	@Override
			public void actionPerformed(ActionEvent e) {
        		if(round!=-1){
        			if(round%2==0 ){
        				// moves the paddle to left, prints the status, and updates isPressed
        				paddle.moveLeft(paddlelabel);
        				System.out.println("Paddle is moved to left");
        				isPressed=1;
        			}
        			else
        				System.out.println("Paddle cannot be moved, it is not the second round");
        		}
        	}
        });
        
        // The action to take when the space key is pressed
        panel.getActionMap().put("space", new AbstractAction() {
        	
        	@Override
			public void actionPerformed(ActionEvent e) {
        		if(round!=-1){
        			if(round%2==0){
        				System.out.println("Paddle is kept at the same place");
        				isPressed=1;
        			}
        			else
        				System.out.println("Paddle cannot be moved, it is not the second round");
        		}
        	}
        });
        
        //	direction indicates in which direction the ball should move
        //	at the beginning ball moves up because direction is five
        int direction=5;
        // loop continues until the direction is 0 which means that the ball hits to the bottom
        while(direction!=0) {
        	//	if a key is pressed or the round is not the second round, ball is moved
        	if(isPressed==1 || round%2==1){
        		//	invokes the move method for the ball, and returns the direction
        		direction=ball.move(balllabel,direction,paddlelabel,bricklist);
        		try {
        			//	Thread waits for some time
        			Thread.sleep(100);
        		} catch (InterruptedException e1) {
        			// TODO Auto-generated catch block
        			e1.printStackTrace();
        		}
        		//	increases the round
        		round++;
        		//	if direction is equal to zero, then makes the game over text visible, and sets round to -1
        		if(direction==0){
        			gameOver.setVisible(true);
        			round=-1;
        		}
        		// sets is pressed to 0
        		isPressed=0;
        	}
        }
        
	}
	
	
}


