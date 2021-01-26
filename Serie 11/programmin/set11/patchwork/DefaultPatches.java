package programming.set11.patchwork;

/**
 * Created by BB20101997 on 21. Jan. 2017.
 */

import acm.graphics.*;
import acm.util.RandomGenerator;

import java.awt.*;

public enum DefaultPatches implements IPatchGenerator {

	//Liek Polygon but only triangles
	TRIANGLE { //the TRIANGLE version uses different starting points compared to the POLYGON enum

		@Override
		public void generatePatch(Patch patch) {
			int   side  = patch.SIDE_LENGTH;
			//add a rectangle to fill the background
			GRect gRect = new GRect(side, side);
			DefaultPatches.setRandomColor(gRect);
			patch.add(gRect);

			//create the first triangle
			GPolygon gPolygon;
			GPoint   p0 = new GPoint(side / 2, 0), p1 = new GPoint(0, side), p2 = new GPoint(side, side);
			GPoint   p0New, p1New, p2New;
			//generate 5 nested triangles
			for(int i = 0; i < 5; i++) {
				//gen polygon
				gPolygon = new GPolygon();
				gPolygon.addVertex(p0.getX(), p0.getY());
				gPolygon.addVertex(p1.getX(), p1.getY());
				gPolygon.addVertex(p2.getX(), p2.getY());
				DefaultPatches.setRandomColor(gPolygon);
				patch.add(gPolygon);

				//generate next polygons points
				p0New = new GPoint((p0.getX() + p1.getX()) / 2, (p0.getY() + p1.getY()) / 2);
				p1New = new GPoint((p1.getX() + p2.getX()) / 2, (p1.getY() + p2.getY()) / 2);
				p2New = new GPoint((p2.getX() + p0.getX()) / 2, (p2.getY() + p0.getY()) / 2);

				//replace old with new
				p0 = p0New;
				p1 = p1New;
				p2 = p2New;
			}
		}
	},
	//a random polygon inception
	POLYGON {
		@Override
		public void generatePatch(Patch patch) {
			int side = patch.SIDE_LENGTH;

			//add background
			GRect gRect = new GRect(side, side);
			DefaultPatches.setRandomColor(gRect);
			patch.add(gRect);

			//get a random vertex count
			final int vertexes = R_GEN.nextInt(5, 10); //<-- can't use four because that's one of the example patches
			GPolygon  gPolygon;
			GPoint[]  gPoints  = new GPoint[vertexes];
			GPoint[]  newGPoints;

			//generate points of first polygon
			for(int i = 0; i < vertexes; i++) {
				gPoints[i] = new GPoint((Math.sin(i * 2 * Math.PI / vertexes)) * side / 2, (Math.cos(i * 2 * Math.PI / vertexes)) * side / 2);
			}

			//generate vertexes*1.5 polygons
			for(int i = 0; i < vertexes * 1.5; i++) {
				gPolygon = new GPolygon();
				for(int p = 0; p < vertexes; p++) {
					gPolygon.addVertex(gPoints[p].getX() + side / 2, gPoints[p].getY() + side / 2);
				}
				DefaultPatches.setRandomColor(gPolygon);
				patch.add(gPolygon);

				newGPoints = new GPoint[vertexes];

				//generate the points of the next polygon
				for(int n = 0; n < vertexes; n++) {
					newGPoints[n] = new GPoint((gPoints[n].getX() + gPoints[(n + 1) % vertexes].getX()) / 2, (gPoints[n].getY() + gPoints[(n + 1) % vertexes].getY()) / 2);
				}

				//replace old points with new points
				gPoints = newGPoints;
			}

		}
	},
	//like polygon but with transparency and always the same color
	POLYGON_TRANS {
		@Override
		public void generatePatch(Patch patch) {
			int side = patch.SIDE_LENGTH;

			//add background
			GRect gRect = new GRect(side, side);
			DefaultPatches.setRandomColor(gRect);
			patch.add(gRect);

			//get vertex count
			final int vertexes = R_GEN.nextInt(5, 10); //<-- can't use four because that's one of the example patches
			GPolygon  gPolygon;
			GPoint[]  gPoints  = new GPoint[vertexes];
			GPoint[]  newGPoints;
			//get color for polygons
			Color     color    = new Color(R_GEN.nextColor().getRGB() & 0x33FFFFFF, true);

			//gen points of first polygon
			for(int i = 0; i < vertexes; i++) {
				gPoints[i] = new GPoint((Math.sin(i * 2 * Math.PI / vertexes)) * side / 2, (Math.cos(i * 2 * Math.PI / vertexes)) * side / 2);
			}

			//generate polygons
			for(int i = 0; i < vertexes * 1.5; i++) {
				gPolygon = new GPolygon();
				for(int p = 0; p < vertexes; p++) {
					gPolygon.addVertex(gPoints[p].getX() + side / 2, gPoints[p].getY() + side / 2);
				}
				gPolygon.setFilled(true);
				gPolygon.setFillColor(color);
				gPolygon.setColor(R_GEN.nextColor());
				patch.add(gPolygon);

				newGPoints = new GPoint[vertexes];

				//generate points of next polygon
				for(int n = 0; n < vertexes; n++) {
					newGPoints[n] = new GPoint((gPoints[n].getX() + gPoints[(n + 1) % vertexes].getX()) / 2, (gPoints[n].getY() + gPoints[(n + 1) % vertexes].getY()) / 2);
				}

				//replace old points with new points
				gPoints = newGPoints;
			}

		}
	},
	//like polygon but with different vertex count per polygon
	MULTI_VERT_POLY {
		@Override
		public void generatePatch(Patch patch) {
			int side = patch.SIDE_LENGTH;

			//add background
			GRect gRect = new GRect(side, side);
			DefaultPatches.setRandomColor(gRect);
			patch.add(gRect);

			GPolygon gPolygon;
			double   radius  = side / 2;
			int      vertexes;

			//init to hole patch size
			GPoint[] gPoints = new GPoint[]{new GPoint(-radius, -radius), new GPoint(-radius, radius), new GPoint(radius, radius), new GPoint(radius, -radius)};

			double midX, midY;

			//generate inner polygons
			for(int i = 0; i < 5; i++) {
				//get vertex count
				vertexes = R_GEN.nextInt(3, 12);
				//generate new radius
				midX = (gPoints[0].getX() + gPoints[1].getX()) / 2;
				midY = (gPoints[0].getY() + gPoints[1].getY()) / 2;
				radius = Math.sqrt(midX * midX + midY * midY);

				gPoints = new GPoint[vertexes];
				gPolygon = new GPolygon();
				for(int p = 0; p < vertexes; p++) {
					//generate new points
					gPoints[p] = new GPoint((Math.sin((i % 2 == 0 ? 0 : Math.PI) + p * 2 * Math.PI / vertexes)) * radius, (Math.cos((i % 2 == 0 ? 0 : Math.PI) + p * 2 * Math.PI / vertexes)) * radius);
					//add new point to polygon
					gPolygon.addVertex(gPoints[p].getX() + side / 2, gPoints[p].getY() + side / 2);
				}
				DefaultPatches.setRandomColor(gPolygon);
				patch.add(gPolygon);//add polygon
			}
		}
	},
	//transparent version of MULT_VERT_POLY
	MULTI_VERT_POLY_TRANS {
		@Override
		public void generatePatch(Patch patch) {
			int side = patch.SIDE_LENGTH;

			//add background
			GRect gRect = new GRect(side, side);
			DefaultPatches.setRandomColor(gRect);
			patch.add(gRect);

			GPolygon gPolygon;
			double   radius  = side / 2;
			int      vertexes;
			//init to hole patch size
			GPoint[] gPoints = new GPoint[]{new GPoint(-radius, -radius), new GPoint(-radius, radius), new GPoint(radius, radius), new GPoint(radius, -radius)};

			double midX, midY;
			//generate random color
			Color  color = R_GEN.nextColor();
			//set transparency
			color = new Color(color.getRGB() & 0x33FFFFFF, true);

			//generate inner polygons
			for(int i = 0; i < 5; i++) {
				//get vertex count
				vertexes = R_GEN.nextInt(3, 12);
				//generate new radius
				midX = (gPoints[0].getX() + gPoints[1].getX()) / 2;
				midY = (gPoints[0].getY() + gPoints[1].getY()) / 2;
				radius = Math.sqrt(midX * midX + midY * midY);

				gPoints = new GPoint[vertexes];
				gPolygon = new GPolygon();
				for(int p = 0; p < vertexes; p++) {
					//gen new points for polygon
					gPoints[p] = new GPoint((Math.sin((i % 2 == 0 ? 0 : Math.PI) + p * 2 * Math.PI / vertexes)) * radius, (Math.cos((i % 2 == 0 ? 0 : Math.PI) + p * 2 * Math.PI / vertexes)) * radius);
					//add new point to polygon
					gPolygon.addVertex(gPoints[p].getX() + side / 2, gPoints[p].getY() + side / 2);
				}
				//set color and add to patch
				gPolygon.setFilled(true);
				gPolygon.setFillColor(color);
				gPolygon.setColor(R_GEN.nextColor());
				patch.add(gPolygon);
			}
		}
	},
	//the name says everything
	CIRCLES_UP_ON_CIRCLES {
		@Override
		public void generatePatch(Patch patch) {
			int   side  = patch.SIDE_LENGTH;
			GOval gOval;
			//add background
			GRect gRect = new GRect(side, side);
			DefaultPatches.setRandomColor(gRect);
			patch.add(gRect);

			//generate the circles and add them to the patch
			for(int i = 10; i >= 1; i--) {
				for(int x = 0; x < i; x++) {
					for(int y = 0; y < i; y++) {
						gOval = new GOval(side / i / 2, side / i / 2);
						DefaultPatches.setRandomColor(gOval);
						patch.add(gOval, (x + 0.25) * side / i, (y + 0.25) * side / i);
					}
				}
			}
		}
	},
	//not realy a spiral but yeah
	SPIRAL_RECTANGLES {
		@Override
		public void generatePatch(Patch patch) {
			int side = patch.SIDE_LENGTH;
			int n    = R_GEN.nextInt(3, 9);
			n = n >= 5 ? n + 1 : n;//to skip 5 because that would be one of the examples
			int   width = side / (2 * n + 1);
			GRect gRect;

			//add n layers of
			for(int i = n - 1; i >= 0; i--) {
				//top
				gRect = new GRect(side - width * (i * 2 + 1), width);
				DefaultPatches.setRandomColor(gRect);
				patch.add(gRect, i * width, i * width);

				//bottom
				gRect = new GRect(side - width * (i * 2 + 1), width);
				DefaultPatches.setRandomColor(gRect);
				patch.add(gRect, (i + 1) * width, side - (i + 1) * width);

				//left
				gRect = new GRect(width, side - width * (i * 2 + 1));
				DefaultPatches.setRandomColor(gRect);
				patch.add(gRect, i * width, (i + 1) * width);

				//right
				gRect = new GRect(width, side - width * (i * 2 + 1));
				DefaultPatches.setRandomColor(gRect);
				patch.add(gRect, side - (i + 1) * width, i * width);
			}
		}
	};

	private static final RandomGenerator R_GEN = new RandomGenerator();

	/**
	 * sets a Random FillColor on the given @param go
	 */
	private static <T extends GObject & GFillable> void setRandomColor(T go) {
		assert go != null : "Der Parameter go darf nicht null sein!";
		Color color = R_GEN.nextColor();
		go.setFilled(true);
		go.setFillColor(color);
	}
}