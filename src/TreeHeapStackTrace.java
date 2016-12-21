import acm.graphics.GLine;
import acm.graphics.GMath;
import acm.program.GraphicsProgram;

/**
 * Part b)
 * Before subsequently calling drawTree in drawTree
 * a check if the new length would be greater than 2 could
 * remove the unnecessary calls
 *
 * Part c)
 *
 * stack: usually assigned from high addresses to low addresses this is the place where e.g.
 *        method calls store their local variables including parameters, each call even the same method
 *        will have it's own place in the stack
 *
 * heap: the place where objects "live" it grows in the opposite direction of the stack,
 *       here is where objects references point to and where the object stores it's state
 *
 * static segment: the place for anything static like static class fields
 *
 * recursion: first you should look at my explanation for recursion than you will already know all you need to know
 *
 * mutable class: a class with a state that can change after it is initialized
 *
 * wrapper class: a class that Wraps something usually refers to the classes:
 * Integer,Long,Float,Double,Boolean,Character,Short
 * which wrap the java primitive types, usually used when using Generics
 *
 * Boxing/Unboxing: the act of converting between a wrapped object to its unwrapped form (boxing unwrapped to wrapped)
 *                  (unboxing wrapped to unwrapped)
 *
 * Garbage Collector: C has memory leak while java has the Garbage Collector
 *                    if an object is determined to have no accessible reference than the object will be removed from
 *                    memory and freeing the space for other objects this
  * */
public class TreeHeapStackTrace extends GraphicsProgram {

	private static final int OBJECT_SIZE = 20;
	private static final int FRAME_SIZE = 28;
	private static final long firstAddressHeap = 0x100000;
	private static final long firstAddressStack = 0xffffff-FRAME_SIZE+1;

	private int depth = 0;
	private int objCount = 0;
	private int frameCount = 0;
	private int maxFrame = 0;

	public void run() {
		setSize(500, 350);
		drawTree(250, 350, 100, 90);   // ADJUST "TREE PARAMETERS" HERE
	}

	public void drawTree(double x0, double y0,
	                     double len, double angle) {
		println("Create drawTree() stack frame at address "+getStackAddress(depth++)+", depth "+depth);
		frameCount++;
		/*if necessary increase max depth*/
		if(depth>maxFrame){
			maxFrame = depth;
		}
		if (len > 2) {
			double x1 = x0 + len * GMath.cosDegrees(angle);
			double y1 = y0 - len * GMath.sinDegrees(angle);
			add(new GLine(x0, y0, x1, y1));
			println("Create GLine object #"+(++objCount)+" at address "+getHeapAddress(objCount-1));
			drawTree(x1, y1, len * 0.75, angle + 30);
			drawTree(x1, y1, len * 0.66, angle - 50);
		}
		println("Delete stack frame at address "+getStackAddress(depth-1)+", depth "+depth--);
		if(depth==0){
			println();
			println("HEAP:");
			println("Created "+objCount+" GLine objects,");
			println("requiring "+OBJECT_SIZE*(objCount)+" bytes of heap space,");
			println("from address "+toHexString(firstAddressHeap)+" to "+toHexString(firstAddressHeap+objCount*OBJECT_SIZE-1)+".");
			println();
			println("STACK:");
			println("Created and discarded "+frameCount+" drawTree() stack frames,");
			println("with maximal depth "+maxFrame+",");
			println("requiring "+maxFrame*FRAME_SIZE+" bytes of stack space,");
			println("from address "+toHexString(firstAddressStack-(maxFrame-1)*FRAME_SIZE)+" to "+toHexString(firstAddressStack+FRAME_SIZE-1)+".");
			//reset
			depth = 0;
			objCount = 0;
			maxFrame = 0;
			frameCount = 0;
		}
	}

	private String getHeapAddress(int objCount) {
		return toHexString(firstAddressHeap+objCount*OBJECT_SIZE);
	}

	private String getStackAddress(int depth){
		return toHexString(firstAddressStack-depth*FRAME_SIZE);
	}

	/**
	 * only works on positive numbers
	 */
	private String toHexString(long i){
		StringBuilder sb = new StringBuilder();
		long temp;
		do{
			temp = i%16;
			if(temp<10){
				sb.append(temp);
			}else{
				sb.append((char)('a'+temp-10));
			}
			i /= 16;
		}while(i>0);

		sb.append("x0");

		return sb.reverse().toString();
	}
}