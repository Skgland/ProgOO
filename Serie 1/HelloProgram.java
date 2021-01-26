import acm.graphics.GLabel;
import acm.program.GraphicsProgram;

/**
 * Created by Bennet on 29.10.2016.
 */
public class HelloProgram extends GraphicsProgram {

	@Override
	public void run(){
		add(new GLabel("if(programmer.hasMovieQuote()){insertQuote(programmer.getMovieQuote());}else{insertQuote(DEFAULT_TEXT);}"),100,100);
	}

}
