import javax.swing.JLabel;

/*
 * Base for all game elements such as ball, paddle, brick
 * 
 * 
 * */

public class GameElement {
	
	//	x, and y keeps the location
	private int x;
	private int y;
	//	symbol keeps the text that represents the element
	String symbol;
	
	//Constructor
	GameElement(int x, int y, String symbol){
		this.x = x;
		this.y = y;
		this.symbol = symbol;
	}
	
	//Converts the instances to labels so that they can be added to the screen
	public JLabel getElement(){
		JLabel label = new JLabel();
		label.setLocation(x, y);
		label.setText(symbol);
		if(symbol.length() == 1)
			label.setSize(20, 20);
		else
			label.setSize(100, 20);
		return label;
	}
	//	Getter and setter methods
	public String getSymbol(){
		return symbol;
	}
	public void setSymbol(String symbol){
		this.symbol=symbol;
	}
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	
}
