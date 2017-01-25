import javax.swing.JLabel;

/*	Class for the yellow brick which is an enhancement that destroys the bricks around itself when it is hit
 * 
 * 
 * 
 * */

public class YellowBrick extends Brick{
	
	YellowBrick(int x, int y, String symbol) {
		super(x, y, symbol);
	}
	
	//	finds the brick on the right to destroy
	public JLabel getUpperRight(JLabel[] bricks){
		for(int i=0;i<bricks.length;i++){
			if((bricks[i].getX() == getX()+20) && (bricks[i].getY()==getY()-20)){
				return bricks[i];
			}
		}
		return getElement();
	}
	
	//	finds the brick above to destroy
	public JLabel getUp(JLabel[] bricks){
		for(int i=0;i<bricks.length;i++){
			if(bricks[i].getX() == getX() && bricks[i].getY()==getY()-20){
				return bricks[i];
			}
		}
		return getElement();	
	}
	
	//	finds the brick on the upper left to destroy
	public JLabel getUpperLeft(JLabel[] bricks){
		for(int i=0;i<bricks.length;i++){
			if(bricks[i].getX() == getX()-20 && bricks[i].getY()==getY()-20){
				return bricks[i];
			}
		}
		return getElement();
	}
	//	finds the brick on the left to destroy
	public JLabel getLeft(JLabel[] bricks){
		for(int i=0;i<bricks.length;i++){
			if(bricks[i].getX() == getX()-20 && bricks[i].getY() == getY()){
				return bricks[i];
			}
		}
		return getElement();
	}
	//	finds the brick on the lower left to destroy
	public JLabel getLowerLeft(JLabel[] bricks){
		for(int i=0;i<bricks.length;i++){
			if(bricks[i].getX() == getX()-20 && bricks[i].getY()==getY()+20){
				return bricks[i];
			}
		}
		return getElement();
	}
	//	finds the brick below to destroy
	public JLabel getDown(JLabel[] bricks){
		for(int i=0;i<bricks.length;i++){
			if(bricks[i].getX() == getX()&&bricks[i].getY()==getY()+20){
				return bricks[i];
			}
		}
		return getElement();
	}
	//	finds the brick on the lower right to destroy
	public JLabel getLowerRight(JLabel[] bricks){
		for(int i=0;i<bricks.length;i++){
			if(bricks[i].getX() == getX()+20 && bricks[i].getY()==getY()+20){
				return bricks[i];
			}
		}
		return getElement();
	}
	//	finds the brick on the right to destroy
	public JLabel getRight(JLabel[] bricks){
		for(int i=0;i<bricks.length;i++){
			if(bricks[i].getX() == getX()+20 && bricks[i].getY() == getY()){
				return bricks[i];
			}
		}
		return getElement();
	}
	
	//	removes the bricks around itself
	public void removeAround(JLabel[] bricks){
		JLabel label=getUpperRight(bricks);
		if(label.getX() != getX() && label.getY() !=getY()){
			label.setVisible(false);
		}
		
		label=getUp(bricks);
		if(label.getY() != getY()){
			label.setVisible(false);
		}
		
		label =getUpperLeft(bricks);
		if(label.getX() != getX() && label.getY() != getY()){
			label.setVisible(false);
		}
		
		label = getLeft(bricks);
		if(label.getX() != getX()){
			label.setVisible(false);
		}
		
		label = getLowerLeft(bricks);
		if(label.getX() != getX() && label.getY() != getY()){
			label.setVisible(false);
		}
		
		label = getDown(bricks);
		if(label.getY() != getY()){
			label.setVisible(false);
		}
		
		label = getLowerRight(bricks);
		if(label.getX() != getX() && label.getY() != getY()){
			label.setVisible(false);
		}
		
		label = getRight(bricks);
		if(label.getX() != getX()){
			label.setVisible(false);
		}
	}

}
