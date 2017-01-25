import javax.swing.JLabel;

/*	Class for paddle
 * 
 *
 * */

public class Paddle extends GameElement{

	Paddle(int x, int y) {
		super(x, y, "DDDDDD");
	}
	
	//	Moves the paddle right as long as it doesn't exceed to the most right side of the screen
	public void moveRight(JLabel label){
		if(label.getX()<=310)
		{
			label.setVisible(false);
			label.setLocation(label.getX() + 10,label.getY());
			label.setVisible(true);
		}
	}
	
	//	Moves the paddle left as long as it doesn't exceed to the most left of the screen
	public void moveLeft(JLabel label){
		if(label.getX()>0)
		{
			label.setVisible(false);
			label.setLocation(label.getX() - 10,label.getY());
			label.setVisible(true);	
		}
	}
}
