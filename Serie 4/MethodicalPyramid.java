import acm.graphics.GRect;
import acm.program.GraphicsProgram;

import java.awt.*;

/**
 * Created by Bennet on 19.11.2016.
 */
public class MethodicalPyramid extends GraphicsProgram {

	//the scale based on which this will be drawn
	public static final int SCALE = 1600;

	//run forest run
	public void run() {
		//get the amount of layers from the user
		int layers = getLayers();
		//draw each layer
		for (int i = 0; i < layers; i++) {
			drawLayer(i, layers, layerColor(i, layers));
		}
	}

	/**
	 * get the input form the user
	 */
	public int getLayers() {
		return readInt("How many Layers would you like? ");
	}

	/**
	 * Returns the color to be used for bricks in the given layer.
	 * *
	 *
	 * @param layerIndex     index of the layer whose color to return. {@code 0} is the
	 *                       bottom layer, {@code numberOfLayers - 1} is the top layer.
	 * @param numberOfLayers the number of layers in the pyramid.
	 * @return the color to be used for the given layer, or {@code null}if
	 * {@code layerIndex} is invalid.
	 */
	public Color layerColor(int layerIndex, int numberOfLayers) {
		if(layerIndex<0||layerIndex>=numberOfLayers){
			return null;
		}
		int c = 220;
		if(numberOfLayers>1) {
			c = 220 * layerIndex / (numberOfLayers - 1);//calculate the green and blue value for the current step
		}
		return new Color(255, c, c); //return the color for the current step
	}

	/**
	 * Draws the given layer with bricks filled with the given color. If
	 * {@code layerIndex} is invalid, no bricks at all should be drawn.
	 * *
	 *
	 * @param layerIndex     index of the layer to draw. {@code 0} is the bottom layer,
	 *                       {@code numberOfLayers - 1} is the top layer.
	 * @param numberOfLayers the number of layers in the pyramid.
	 * @param layerColor     color the layer's bricks should be filled with.
	 */
	public void drawLayer(int layerIndex, int numberOfLayers, Color layerColor) {
		if(layerIndex<0||layerIndex>=numberOfLayers){
			return;
		}
		//the names of the variables should be self-explanatory
		int bricksInLayer = numberOfLayers - layerIndex;
		int brickLength = SCALE / numberOfLayers;
		int brickHight = SCALE / numberOfLayers / 2;
		int offsetX = brickLength / 2 * layerIndex+10;
		int offsetY = SCALE / 2 - brickHight * (layerIndex+1)+10;

		GRect rect;
		//draw the bricks in the layer
		for (int i = 0; i < bricksInLayer; i++) {
			rect = new GRect(offsetX + brickLength * i, offsetY, brickLength, brickHight);
			rect.setFilled(true);
			rect.setFillColor(layerColor);
			//rect.setColor(layerColor);
			add(rect);
		}
	}
}