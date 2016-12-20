package programming.set8.watermark;

import acm.graphics.GImage;
import acm.program.GraphicsProgram;

import java.awt.*;

/**
 * @author Bennet Blessmann
 *         Created on 20.12.2016.
 */
public class Watermark extends GraphicsProgram {

	/**
	 * Width of display area
	 */
	public static final int WIDTH = 640;
	/**
	 * Height of the displaz area
	 */
	public static final int HEIGHT = 480;
	/**
	 * Name of the image
	 */
	public static final String IMAGE_NAME = "nina101w.jpg";
	/**
	 * Name of the watermark image
	 */
	public static final String WATERMARK_IMAGE_NAME = "trollface.jpg";
	/**
	 * Pixel blend mask value
	 */
	public static final int PIXEL_BLEND_MASK = 0x80ffffff;


	public void run(){
		GImage orig = new GImage(IMAGE_NAME);
		GImage water = new GImage(WATERMARK_IMAGE_NAME);
		add(createWatermarkGImage(createScaledGImage(orig,WIDTH,HEIGHT),createScaledGImage(water,WIDTH,HEIGHT)));
	}

	/**
	 * Creates a copy of a given GImage with new proportions.
	 *
	 * @param image  the source image
	 * @param width  the width of the new image
	 * @param height the height of the new image
	 * @return the new GImage object.
	 */
	public GImage createScaledGImage(GImage image, double width, double height) {
		return new GImage(image.getImage().getScaledInstance((int)width,(int)height, Image.SCALE_DEFAULT));
	}

	/**
	 * Creates a copy of a given GImage and adds a watermark to it.
	 *
	 * @param image     the source image
	 * @param watermark the image that's used as watermark
	 * @return the new GImage including the watermark.
	 */

	public GImage createWatermarkGImage(GImage image, GImage watermark) {
		int[][] ipa = image.getPixelArray();
		int[][] wpa = watermark.getPixelArray();

		int curMask;
		//ohne alpha channel
		final int invert = 0xffffff;
		for(int i = 0;i<wpa.length&&i<ipa.length;i++){
			for(int ii = 0;ii<wpa[i].length&&ii<ipa[i].length;ii++){
				curMask = wpa[i][ii];
				if((curMask|invert)!=curMask) {
					ipa[i][ii] = convertARGBtoRGB(ipa[i][ii]&((curMask^invert)&PIXEL_BLEND_MASK))^invert;
				}
			}
		}

		return new GImage(ipa);
	}

	/**
	 * Converts a color value of the ARGB domain to a color value of the RGB domain.
	 * The alpha channel is removed via normal blending mode (assuming a white background).
	 * ( new = old ∗ alpha + 255.0 ∗ (1.0 − alpha ) )
	 *
	 * @param argbValue the source color value
	 * @return the color in the RGB domain.
	 */
	public static int convertARGBtoRGB(int argbValue) {
		int b = (int)((argbValue&0xFF)*((double)(argbValue>>24)/0xff)+0xff*(1.0-((double)(argbValue>>24)/0xff)));
		int g = (int)(((argbValue>>8)&0xFF)*((double)(argbValue>>24)/0xff)+0xff*(1.0-((double)(argbValue>>24)/0xff)));
		int r = (int)(((argbValue>>16)&0xFF)*((double)(argbValue>>24)/0xff)+0xff*(1.0-((double)(argbValue>>24)/0xff)));
		return 0xff000000|(r<<16)|(g<<8)|b;
	}


}
