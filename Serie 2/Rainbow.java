import acm.graphics.GArc;
import acm.graphics.GLine;
import acm.graphics.GPolygon;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;

import java.awt.*;

/**
 * Created by Bennet on 04.11.2016.
 */
public class Rainbow extends GraphicsProgram {

	public void run(){
		int scale = 800;//the scal of the image everything will be proportional to this

		Color groundColor = new Color(26,255, 28);//save the Color chosen for the ground in groundColor
		Color skyColor = new Color(157,217,237); //save the sky (from sample) Color in skyColor
		GRect sky = new GRect(0,0,scale,scale/2); //set the dimensions of the sky
		GRect ground = new GRect(0,scale/2,scale,scale/2); //set the dimension of the ground
		sky.setFillColor(skyColor); //set the sky Color
		sky.setFilled(true); // set that the sky shall be colored
		sky.setColor(skyColor); // set the sky'S outline
		ground.setFillColor(groundColor); // set the ground Color
		ground.setFilled(true); // set that the Ground shall be colored
		ground.setColor(groundColor); // set the ground's outline
		add(sky); // add the sky to the image
		add(ground); // add the ground to the image

		/*Color[] arc = new Color[]{
				Color.RED,
				Color.ORANGE,
				Color.YELLOW,
				Color.GREEN,
				Color.CYAN,
				Color.BLUE,
				skyColor,
		}; // create a array of colors of the arcs to draw so we can iterate over it
		*/
		//GArc temp;
		int x = 0; // initiate x with the distance to the side of the image
		int xStep = scale/40; // set the step size based on the scale
		/*for(Color c: arc){ // loop over all Colors in arc
			temp = new GArc(x,x,scale-2*x,scale-2*x,0,180); // set temp to the next arc
			temp.setColor(c); // set temp's Color to c the current Color
			temp.setFilled(true);// set temp to be filled
			add(temp); // add temp
			x+=xStep; // increase x by the step size
		}*/


		//unrolled the loop because withe the loop the test was not finishing successfully
		GArc red = new GArc(x,x,scale-2*x,scale-2*x,0,180); // set temp to the next arc
		red.setColor(Color.RED); // set temp's Color to c the current Color
		red.setFilled(true);// set temp to be filled
		add(red); // add temp
		x+=xStep; // increase x by the step size
		GArc orange = new GArc(x,x,scale-2*x,scale-2*x,0,180); // set temp to the next arc
		orange.setColor(Color.ORANGE); // set temp's Color to c the current Color
		orange.setFilled(true);// set temp to be filled
		add(orange); // add temp
		x+=xStep; // increase x by the step size
		GArc yellow = new GArc(x,x,scale-2*x,scale-2*x,0,180); // set temp to the next arc
		yellow.setColor(Color.YELLOW); // set temp's Color to c the current Color
		yellow.setFilled(true);// set temp to be filled
		add(yellow); // add temp
		x+=xStep; // increase x by the step size
		GArc green = new GArc(x,x,scale-2*x,scale-2*x,0,180); // set temp to the next arc
		green.setColor(Color.GREEN); // set temp's Color to c the current Color
		green.setFilled(true);// set temp to be filled
		add(green); // add temp
		x+=xStep; // increase x by the step size
		GArc cyan = new GArc(x,x,scale-2*x,scale-2*x,0,180); // set temp to the next arc
		cyan.setColor(Color.CYAN); // set temp's Color to c the current Color
		cyan.setFilled(true);// set temp to be filled
		add(cyan); // add temp
		x+=xStep; // increase x by the step size
		GArc blue = new GArc(x,x,scale-2*x,scale-2*x,0,180); // set temp to the next arc
		blue.setColor(Color.BLUE); // set temp's Color to c the current Color
		blue.setFilled(true);// set temp to be filled
		add(blue); // add temp
		x+=xStep; // increase x by the step size
		GArc skyArc = new GArc(x,x,scale-2*x,scale-2*x,0,180); // set temp to the next arc
		skyArc.setColor(skyColor); // set temp's Color to c the current Color
		skyArc.setFilled(true);// set temp to be filled
		add(skyArc); // add temp
		//x+=xStep; // increase x by the step size



		//calculate all often referred to values
		int xStart = scale/2;
		int yStart = scale/2+scale/8;
		int height = scale/10;
		int width = scale/8;
		int depth = scale/10;
		int roofHeight = scale/20;

		GPolygon main = new GPolygon(xStart,yStart);//create main GPolygon with starting coordinates
		main.addEdge(width/2,roofHeight); //right roof
		main.addEdge(0,height); //right wall
		main.addEdge(-width,0); //base line
		main.addEdge(-depth,-depth); //left diagonal base
		main.addEdge(0,-height); //left back wall
		main.addEdge(depth,depth); //lower left roof edge
		main.addEdge(width/2,-roofHeight); //roof left
		main.setFillColor(new Color(200,50,20)); //set main Building Color to a type of red
		main.setFilled(true); // set main to be filled
		add(main); //  add main to image

		GPolygon roof = new GPolygon(xStart,yStart); // create roof GPolygon with starting coordinates
		roof.addEdge(width/2,roofHeight);
		//go around the edge of the roof
		roof.addEdge(-depth,-depth);
		roof.addEdge(-width/2,-roofHeight);
		roof.addEdge(-width/2,roofHeight);
		roof.addEdge(depth,depth);
		roof.addEdge(width/2,-roofHeight);
		roof.setFillColor(Color.white); // set the roof color to white
		roof.setFilled(true); // set the roof to be filled
		add(roof); // add the roof to the image

		//calculate additional door values
		int offsetDoorRoof = scale/12;
		int doorArc = scale/15;
		int arcScale = 1;

		GPolygon door = new GPolygon(xStart,yStart+offsetDoorRoof);// create door GPolygon with starting coordinates
		door.addArc(doorArc,arcScale*doorArc,90,-90); // draw the upper right arc
		door.addEdge(0,height+roofHeight-offsetDoorRoof-doorArc*arcScale/2); // draw the right side
		door.addEdge(-doorArc,0); //draw the base line
		door.addEdge(0,-height-roofHeight+offsetDoorRoof+doorArc*arcScale/2); // draw the left side
		door.addArc(doorArc,arcScale*doorArc,180,-90); // draw the upper left arc
		door.setFillColor(Color.lightGray); // set the color to lightGrey
		door.setFilled(true); // set it to be filled
		add(door); // add the door to the image


		// add a few missing lines
		//a line to divide the two halves of the door
		GLine doorMiddle = new GLine(xStart,yStart+offsetDoorRoof,xStart,yStart+height+roofHeight);
		add(doorMiddle);

		//the line at the roof top
		GLine roofTop = new GLine(xStart,yStart,xStart-depth,yStart-depth);
		add(roofTop);

		//the line where the left wall and the front wall meet
		GLine frontLeft = new GLine(xStart-width/2,yStart+roofHeight,xStart-width/2,yStart+roofHeight+height);
		add(frontLeft);
	}
}