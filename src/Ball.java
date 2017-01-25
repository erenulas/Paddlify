import javax.swing.JLabel;

/*	Class for the ball
 * 
 * 
 * */

public class Ball extends GameElement{
	//	if ball hits to paddle or top, then sideOrTop is 1
	//	if it hits to the right or left side, then sideOrTop is 0
	int sideOrTop=1;
	//	Constructor of ball
	Ball(int x, int y) {
		super(x, y, "O");
	}
	
	//	To move the ball
	public int move(JLabel label, int direction,JLabel paddlelabel,BrickList bricklist){
		int dir=direction;
		label.setVisible(false);
		//	if the ball hits to right side, enters here
		if(label.getX()==360)
		{
			//	if the current direction is 3 when it hits to the right side, it changes direction to 2
			if(dir==3)
				dir=2;
			//	if current one is 4, then it is changed to 1
			else if(dir==4)
				dir=1;
			sideOrTop = 0;
			System.out.println("Ball hits to the right side");
		}
		//	if the ball hits to the top, enters here
		else if(label.getY()==0)
		{
			//	if current direction is 1, then it is changed to 2
			if(dir==1)
				dir=2;
			// if current one is 4, it is changed to 3
			else if(dir==4)
				dir=3;
			// if the current one is 5, it is changed to 6
			else if(dir==5)
				dir=6;
			sideOrTop=1;
			System.out.println("Ball hits to the top");
		}
		//	if the ball hits to left side, enters here
		else if(label.getX()==0)
		{
			//	if current direction is 2, it is changed to 3
			if(dir==2)
				dir=3;
			// if current one is 1, it is changed to 4
			else if(dir==1)
				dir=4;
			sideOrTop=0;
			System.out.println("Ball hits to the left");
		}
		//	if the ball hits to the paddle, enters here
		else if(label.getX()<=paddlelabel.getX()+50 && label.getX()>=paddlelabel.getX()-10 && label.getY()+10==paddlelabel.getY()){
			//	if ball hits to the right side of the paddle
			if(paddlelabel.getX()+50 >=label.getX() && paddlelabel.getX()+30<=label.getX())
				dir=4;
			//	if it hits to the middle part of the paddle
			else if(paddlelabel.getX()+30 >label.getX() && paddlelabel.getX()+10<label.getX())
				dir=5;
			//	if it hits to the left part of the paddle
			else
				dir=1;
			sideOrTop=1;
			System.out.println("Ball hits to the paddle");
		}
		//	if the ball hits to to the bottom, enters here
		else if(label.getY() >paddlelabel.getY()){
			System.out.println("Game Over");
			return 0;
		}
		//	invokes the isHit method to check if a brick is hit
		dir = isHit(label,bricklist,dir,sideOrTop);
		
		//	Moves according to the direction
		if(dir==1){
			label.setLocation(label.getX()-10, label.getY()-10);
		}
		else if(dir==2){
			label.setLocation(label.getX()-10, label.getY()+10);
		}
		else if(dir==3){
			label.setLocation(label.getX()+10, label.getY()+10);
		}
		else if(dir==4){
			label.setLocation(label.getX()+10, label.getY()-10);
		}
		else if(dir==5){
			label.setLocation(label.getX(),label.getY()-10);
		}
		else if(dir==6){
			label.setLocation(label.getX(),label.getY()+10);
		}
		label.setVisible(true);
		return dir;
	}
	
	//	To check whether the ball hits a brick or not, and returns the new direction
	public int isHit(JLabel label,BrickList bricklist,int dir,int sideOrTop){
		int direction=dir;
		JLabel[] bricks = bricklist.labels;
		//	moves through the array of bricks
		for(int i=0;i<bricks.length;i++){
			if(direction==1){
				// if the brick isn't hit before, and the ball is in the location of the brick enters here
				if(bricks[i].isVisible() == true&&label.getX() == bricks[i].getX() && label.getY() == bricks[i].getY()){
					//	if the symbol of the brick is Y, then it is a yellow brick, and removes it, and the others around it
					if(bricks[i].getText() == "Y"){
						for(int j=0;j<bricklist.yellowbricks.length;j++){
							if(bricks[i].getX() == bricklist.yellowbricks[j].getX() && bricks[i].getY() == bricklist.yellowbricks[j].getY()){
								bricklist.yellowbricks[j].removeAround(bricklist.labels);
								System.out.println("Ball hits a yellow brick, and it is exploded");
							}
						}
					}
					else
						System.out.println("Ball hits a brick");
					//	Ball changes its direction according to the side that it's coming from(up,down,left,right), and disappears the brick
					if(sideOrTop==0)
						direction=4;
					else
						direction=2;
					bricks[i].setVisible(false);
				}
			}
			//	Same logic as the previous one
			else if(direction==2){
				if(bricks[i].isVisible() == true&&label.getX() == bricks[i].getX() && label.getY() == bricks[i].getY()){
					if(bricks[i].getText() == "Y"){
						for(int j=0;j<bricklist.yellowbricks.length;j++){
							if(bricks[i].getX() == bricklist.yellowbricks[j].getX() && bricks[i].getY() == bricklist.yellowbricks[j].getY()){
								bricklist.yellowbricks[j].removeAround(bricklist.labels);
								System.out.println("Ball hits a yellow brick, and it is exploded");
							}
						}
					}
					else
						System.out.println("Ball hits a brick");
					if(sideOrTop==0)
						direction=3;
					else
						direction=1;
					bricks[i].setVisible(false);
				}
			}
			else if(direction==3){
				if(bricks[i].isVisible() == true &&label.getX() == bricks[i].getX() && label.getY() == bricks[i].getY()){
					if(bricks[i].getText() == "Y"){
						for(int j=0;j<bricklist.yellowbricks.length;j++){
							if(bricks[i].getX() == bricklist.yellowbricks[j].getX() && bricks[i].getY() == bricklist.yellowbricks[j].getY()){
								bricklist.yellowbricks[j].removeAround(bricklist.labels);
								System.out.println("Ball hits a yellow brick, and it is exploded");
							}
						}
					}
					else
						System.out.println("Ball hits a brick");
					if(sideOrTop==0)
						direction=2;
					else
						direction=4;
					bricks[i].setVisible(false);
				}
			}
			else if(direction==4){
				if(bricks[i].isVisible() == true &&label.getX() == bricks[i].getX() && label.getY() == bricks[i].getY()){
					if(bricks[i].getText() == "Y"){
						for(int j=0;j<bricklist.yellowbricks.length;j++){
							if(bricks[i].getX() == bricklist.yellowbricks[j].getX() && bricks[i].getY() == bricklist.yellowbricks[j].getY()){
								bricklist.yellowbricks[j].removeAround(bricklist.labels);
								System.out.println("Ball hits a yellow brick, and it is exploded");
							}
						}
					}
					else
						System.out.println("Ball hits a brick");
					if(sideOrTop==0)
						direction=1;
					else
						direction=3;
					bricks[i].setVisible(false);
				}
			}
			else if(direction==5){
				if(bricks[i].isVisible() == true &&label.getX() == bricks[i].getX() && label.getY() == bricks[i].getY()){
					if(bricks[i].getText() == "Y"){
						for(int j=0;j<bricklist.yellowbricks.length;j++){
							if(bricks[i].getX() == bricklist.yellowbricks[j].getX() && bricks[i].getY() == bricklist.yellowbricks[j].getY()){
								bricklist.yellowbricks[j].removeAround(bricklist.labels);
								System.out.println("Ball hits a yellow brick, and it is exploded");
							}
						}
					}
					else
						System.out.println("Ball hits a brick");
					direction=6;
					bricks[i].setVisible(false);
				}
			}
		}
		
		return direction;
	}

}
