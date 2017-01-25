import javax.swing.JLabel;
import javax.swing.JPanel;

public class BrickList{
	//	Keeps all the bricks
	JLabel[] labels = new JLabel[70];
	//	Keeps the yellow bricks
	YellowBrick[] yellowbricks = new YellowBrick[3];
	
	//Adds the bricks to the array, and add them to the screen
	public void addBrick(JPanel panel){
		int initialX =60;
		int initialY =50;
		int count=0;
		int yellow=0; 
		for(int i = initialY; i<150; i+=20){
			for(int j = initialX; j<330; j+=20){
				//	Adds the yellow bricks
				if(j%100 == 0 && i%90==0){
					YellowBrick yellowbrick = new YellowBrick(j,i,"Y");
					labels[count] = yellowbrick.getElement();
					yellowbricks[yellow] = yellowbrick;
					yellow++;
				}
				//	Adds the normal bricks
				else{
					Brick brick = new Brick(j,i,"X");
					labels[count] = brick.getElement();
				}
				panel.add(labels[count]);
				count++;
			}
			initialX = 60;
		}
		
	}

}

