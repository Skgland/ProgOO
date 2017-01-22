package programming.set11.patchwork;

/**
 * Created by BB20101997 on 21. Jan. 2017.
 */

import acm.graphics.*;
import acm.util.RandomGenerator;

import java.awt.*;

public enum DefaultPatches implements IPatchGenerator {

	TRIANGLE { //the TRIANGLE version uses different starting points compared to the POLYGON enum
		@Override
		public void generatePatch(Patch patch) {
			int   side  = patch.SIDE_LENGTH;
			GRect gRect = new GRect(side, side);
			DefaultPatches.setRandomColor(gRect);
			patch.add(gRect);
			GPolygon gPolygon;
			GPoint   p0 = new GPoint(side / 2, 0), p1 = new GPoint(0, side), p2 = new GPoint(side, side);
			GPoint   p0New, p1New, p2New;
			for(int i = 0; i < 5; i++) {
				gPolygon = new GPolygon();
				gPolygon.addVertex(p0.getX(), p0.getY());
				gPolygon.addVertex(p1.getX(), p1.getY());
				gPolygon.addVertex(p2.getX(), p2.getY());
				DefaultPatches.setRandomColor(gPolygon);
				patch.add(gPolygon);

				p0New = new GPoint((p0.getX() + p1.getX()) / 2, (p0.getY() + p1.getY()) / 2);
				p1New = new GPoint((p1.getX() + p2.getX()) / 2, (p1.getY() + p2.getY()) / 2);
				p2New = new GPoint((p2.getX() + p0.getX()) / 2, (p2.getY() + p0.getY()) / 2);

				p0 = p0New;
				p1 = p1New;
				p2 = p2New;
			}
		}
	}, POLYGON {
		@Override
		public void generatePatch(Patch patch) {
			int side = patch.SIDE_LENGTH;

			GRect gRect = new GRect(side, side);
			DefaultPatches.setRandomColor(gRect);
			patch.add(gRect);

			GPolygon  gPolygon;
			final int vertexes = R_GEN.nextInt(5, 10); //<-- can't use four because that's one of the example patches
			GPoint[]  gPoints  = new GPoint[vertexes];
			GPoint[]  newGPoints;

			for(int i = 0; i < vertexes; i++) {
				gPoints[i] = new GPoint((Math.sin(i * 2 * Math.PI / vertexes)) * side / 2, (Math.cos(i * 2 * Math.PI / vertexes)) * side / 2);
			}

			for(int i = 0; i < vertexes * 1.5; i++) {
				gPolygon = new GPolygon();
				for(int p = 0; p < vertexes; p++) {
					gPolygon.addVertex(gPoints[p].getX() + side / 2, gPoints[p].getY() + side / 2);
				}
				DefaultPatches.setRandomColor(gPolygon);
				patch.add(gPolygon);

				newGPoints = new GPoint[vertexes];

				for(int n = 0; n < vertexes; n++) {
					newGPoints[n] = new GPoint((gPoints[n].getX() + gPoints[(n + 1) % vertexes].getX()) / 2, (gPoints[n].getY() + gPoints[(n + 1) % vertexes].getY()) / 2);
				}

				gPoints = newGPoints;
			}

		}
	},

	CIRCLES_UP_ON_CIRCLES {
		@Override
		public void generatePatch(Patch patch) {
			int   side  = patch.SIDE_LENGTH;
			GOval gOval;
			GRect gRect = new GRect(side, side);
			DefaultPatches.setRandomColor(gRect);
			patch.add(gRect);
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
	}, SPIRAL_RECTANGLES {
		@Override
		public void generatePatch(Patch patch) {
			int side = patch.SIDE_LENGTH;
			int n    = R_GEN.nextInt(3, 9);
			n = n >= 5 ? n + 1 : n;//to skip 5 because that would be one of the examples
			int   width = side / (2 * n + 1);
			GRect gRect;
			for(int i = n - 1; i >= 0; i--) {
				gRect = new GRect(side - width * (i * 2 + 1), width);
				DefaultPatches.setRandomColor(gRect);
				patch.add(gRect, i * width, i * width);

				gRect = new GRect(side - width * (i * 2 + 1), width);
				DefaultPatches.setRandomColor(gRect);
				patch.add(gRect, (i + 1) * width, side - (i + 1) * width);

				gRect = new GRect(width, side - width * (i * 2 + 1));
				DefaultPatches.setRandomColor(gRect);
				patch.add(gRect, i * width, (i + 1) * width);

				gRect = new GRect(width, side - width * (i * 2 + 1));
				DefaultPatches.setRandomColor(gRect);
				patch.add(gRect, side - (i + 1) * width, i * width);
			}
		}
	}, MULTI_VERT_POLY {
		@Override
		public void generatePatch(Patch patch) {
			int side = patch.SIDE_LENGTH;

			GRect gRect = new GRect(side, side);
			DefaultPatches.setRandomColor(gRect);
			patch.add(gRect);

			GPolygon gPolygon;
			double   radius  = side / 2;
			int      vertexes;
			GPoint[] gPoints = new GPoint[]{new GPoint(-radius, -radius), new GPoint(-radius, radius), new GPoint(radius, radius), new GPoint(radius, -radius)}; //init with hole patch size

			double midX, midY;

			//generate inner polygons
			for(int i = 0; i < 5; i++) {
				vertexes = R_GEN.nextInt(3, 12);
				midX = (gPoints[0].getX() + gPoints[1].getX()) / 2;
				midY = (gPoints[0].getY() + gPoints[1].getY()) / 2;
				radius = Math.sqrt(midX * midX + midY * midY);

				gPoints = new GPoint[vertexes];
				gPolygon = new GPolygon();
				for(int p = 0; p < vertexes; p++) {
					gPoints[p] = new GPoint((Math.sin((i % 2 == 0 ? 0 : Math.PI) + p * 2 * Math.PI / vertexes)) * radius, (Math.cos((i % 2 == 0 ? 0 : Math.PI) + p * 2 * Math.PI / vertexes)) * radius);
					gPolygon.addVertex(gPoints[p].getX() + side / 2, gPoints[p].getY() + side / 2);
				}
				DefaultPatches.setRandomColor(gPolygon);
				patch.add(gPolygon);
			}
		}
	};

	private static final RandomGenerator R_GEN = new RandomGenerator();

	private static <T extends GObject & GFillable> void setRandomColor(T go) {
		assert go != null : "Der Parameter go darf nicht null sein!";
		Color color = R_GEN.nextColor();
		go.setFilled(true);
		go.setFillColor(color);
	}
}