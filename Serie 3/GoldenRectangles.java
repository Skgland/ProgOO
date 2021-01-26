import acm.graphics.GRect;
import acm.program.GraphicsProgram;

/**
 * Created by stu201758 on 11/13/16.
 */
public class GoldenRectangles extends GraphicsProgram{

	/**
	 * Golden Ratio
	 * */
	public static double GR = (1+Math.sqrt(5))/2;
	public void run(){
		//moved from int to double rounding errors added up and made teh test fail
		double x,y=x=10; //start a bit from the boarder of the window
		double width = readInt("Please provide width of first rectangle:\n");
		double height = (width/GR); //calculate first height
		int count = readInt("Please provide rectangle count:\n");
		GRect nextRect;

		//go through for each rectangle
		for(int i=0;i<count;i++){
			//System.err.println(x+":"+y+":"+width+":"+height);
			//create and add the next Rectangle
			nextRect = new GRect(x,y,width,height);
			add(nextRect);

			//calculate next rectangle
			switch(i%4){
				case 0:{
					//top
					x = x + width - (width = (height/GR));
					break;
				}
				case 1:{
					//right
					y = y + height - (height = (width/GR));
					break;
				}
				case 2:{
					//bottom
					width = (height/GR);
					break;
				}
				case 3:{
					//left
					height = (width/GR);
					break;
				}
			}
		}
	}
}
