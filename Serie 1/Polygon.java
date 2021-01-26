import acm.graphics.GLine;
import acm.program.GraphicsProgram;

/**
 * Created by Bennet on 30.10.2016.
 */
public class Polygon extends GraphicsProgram{

	public void run(){
		println("Dear User,\nThis program will draw any n-Sided Polygon (n>=3<=1.000.000).");
		int verts = readInt("Please enter the amount of sides the Polygon shall have:",3,1_000_000);
		if(verts>100){
			println("Just "+verts+" why not "+1_000_000);
		}

		double angle = (2*Math.PI)/verts;

		java.awt.Polygon p = new java.awt.Polygon();
		for(int i = 0;i<verts;i++){
			p.addPoint((int)(Math.cos(i*angle)*200+250),(int)(Math.sin(i*angle)*200+250));
		}

		for(int i = 0;i<p.npoints;i++){
			add(new GLine(p.xpoints[i],p.ypoints[i],p.xpoints[(i+1)%p.npoints],p.ypoints[(i+1)%p.npoints]));
		}
		//TODO: Draw a verts-Sided Polygon

	}

}
