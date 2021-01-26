import acm.graphics.GLine;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;

/**
 * Created by Bennet on 29.10.2016.
 */
public class Architecture extends GraphicsProgram{

	public static final int OFFSET_X = 10, OFFSET_Y = 10;
	public static final int HOUSE_HIGHT = 200;//everything is scaled relative to this so it technically is the overall scale

	public static final double HOUSE_WIDTH_SCALE = 15e-1;//to House hight

	public static final double DOOR_WIDTH_PROP = 2e-1; //to House width
	public static final double DOOR_HIGHT_PROP = 6e-1; //to House hight

	public static final double WINDOW_SCALE = 5e-1; //to door hight
	public static final int DOOR_KNOB_OFFSET = 2;
	public static final double DOOR_KNOP_DIAMETER_SCALE = 1e-1; //to door size
	public static final double ROOF_SCALE = 5e-1; //to house hight
	public static final double ROOF_OVERHANG_SCALE = 5e-2; //0 means no overhang

	public void run(){

		//Start Calculating by applying scales
		int houseWidth =(int) (HOUSE_HIGHT* HOUSE_WIDTH_SCALE);

		int doorWidth = (int)(DOOR_WIDTH_PROP*houseWidth);
		int doorHight = (int)(DOOR_HIGHT_PROP*HOUSE_HIGHT);
		int knobDiam = (int) (DOOR_KNOP_DIAMETER_SCALE*doorWidth);

		int winSideLength = (int) (doorHight* WINDOW_SCALE);

		double roofHight = HOUSE_HIGHT*ROOF_SCALE;
		double roofLenghtNoOverhang = Math.sqrt(Math.pow((houseWidth/2.0),2)+Math.pow(roofHight,2));
		double roofProp = (roofLenghtNoOverhang*(ROOF_OVERHANG_SCALE+1))/roofLenghtNoOverhang;
		int roofXDif =(int) (houseWidth/2*roofProp);
		int roofYDif =(int) (roofHight*roofProp);

		//Calculate Coordinates
		int roofXStart = OFFSET_X+roofXDif;
		int roofYStart = OFFSET_Y;

		int houseX = roofXStart-houseWidth/2;
		int houseY = (int) (OFFSET_Y+roofHight);

		int doorX = houseX+(houseWidth-doorWidth)/2;
		int doorY = houseY+HOUSE_HIGHT-doorHight;

		int knobX = doorX+DOOR_KNOB_OFFSET;
		int knobY = doorY+(doorHight-knobDiam)/2;

		int leftWinX = (houseWidth-doorWidth-2*winSideLength)/4+houseX;
		int rightWinX = houseX+houseWidth-(houseWidth-doorWidth-2*winSideLength)/4 - winSideLength;

		add(new GRect(houseX,houseY,houseWidth,HOUSE_HIGHT)); //Main House
		add(new GRect(doorX,doorY,doorWidth,doorHight)); //Door
		add(new GOval(knobX,knobY,knobDiam,knobDiam));//Doorknob
		add(new GRect(leftWinX,doorY,winSideLength,winSideLength)); //left Window
		add(new GRect(rightWinX,doorY,winSideLength,winSideLength)); //right Window
		add(new GLine(roofXStart,roofYStart,roofXStart-roofXDif,roofYStart+roofYDif)); //left Roof halve
		add(new GLine(roofXStart,roofYStart,roofXStart+roofXDif,roofYStart+roofYDif)); //right Roof halve
	}

}
