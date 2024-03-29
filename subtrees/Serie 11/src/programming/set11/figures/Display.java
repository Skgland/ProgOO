package programming.set11.figures;

import acm.program.GraphicsProgram;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by BB20101997 on 21. Jan. 2017.
 */
public class Display extends GraphicsProgram implements KeyListener, ComponentListener {

	private WrapFigure wrapFigure;
	/**
	 * b changes the boarder
	 * w changes the lineWidth
	 * s changes the spacing
	 * l changes the lineSpacing
	 * will increase the given value when holding Shift, will decrease if not
	 * */

	@Override
	public void init() {
		super.init();
		getGCanvas().addKeyListener(this);
		getGCanvas().addComponentListener(this);
	}

	public void run() {
		String text = "The alley was unusually dark and alley-like. A thick mist covered the ground,";
		text += " penetrating the air like a heavy mattress,";
		text += " but without the solid parts. He felt reminded of an ingeniously staged theatre play. But this time,";
		text += " he might not make it out in one piece, the Don would make sure of that if given half a chance.\n";
		text += "'You're late!', said a voice in the dark.\n";
		text += "It was then that he noticed that he might not have been on time.";

		wrapFigure = new WrapFigure(400, "tinlh01w.jpg", 0.46, text);
		wrapFigure.setTextFont("Garamond-bold-12");
		wrapFigure.setBorder(10);
		wrapFigure.setSpacing(10);
		update();
		setSize(wrapFigure.lineWidth, 300);
	}

	private void update() {
		removeAll();
		add(wrapFigure.getCompound());
	}

	@Override
	public void keyTyped(KeyEvent e) {
		switch(e.getKeyChar()) {
			case 'b': {
				if(wrapFigure.boarder > 0) {
					wrapFigure.setBorder(wrapFigure.boarder - 1);
				}
				break;
			}
			case 'B': {
				if(wrapFigure.boarder < wrapFigure.lineWidth / 2) {
					wrapFigure.setBorder(wrapFigure.boarder + 1);
				}
				break;
			}
			case 'w': {
				if(wrapFigure.lineWidth > 0) {
					wrapFigure.setLineWidth(wrapFigure.lineWidth - 1);
				}
				break;
			}
			case 'W': {
				wrapFigure.setLineWidth(wrapFigure.lineWidth + 1);
				break;
			}
			/*case 'i': {
				if(wrapFigure.firstLineIndent > 0) {
					wrapFigure.setFirstLineIndent(wrapFigure.firstLineIndent - 1);
					removeAll();
					add(wrapFigure.getCompound());
				}
				break;
			}
			case 'I': {
				if(wrapFigure.firstLineIndent < wrapFigure.lineWidth) {
					wrapFigure.setFirstLineIndent(wrapFigure.firstLineIndent + 1);
					removeAll();
					add(wrapFigure.getCompound());
				}
				break;
			}*/
			case 's': {
				if(wrapFigure.spacing > 0) {
					wrapFigure.setSpacing(wrapFigure.spacing - 1);
				}
				break;
			}
			case 'S': {
				if(wrapFigure.spacing < wrapFigure.lineWidth) {
					wrapFigure.setSpacing(wrapFigure.spacing + 1);
				}
				break;
			}
			case 'l': {
				if(wrapFigure.lineSpacing > 1) {
					wrapFigure.setLineSpacingFactor(wrapFigure.lineSpacing - 0.05);
				}
				break;
			}
			case 'L': {
				wrapFigure.setLineSpacingFactor(wrapFigure.lineSpacing + 0.05);
				break;
			}

		}
		update();
		setSize(wrapFigure.lineWidth, getHeight());
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void componentResized(ComponentEvent e) {
		if(wrapFigure != null) {
			wrapFigure.setLineWidth(getWidth());
			update();
		}
	}

	@Override
	public void componentMoved(ComponentEvent e) {

	}

	@Override
	public void componentShown(ComponentEvent e) {

	}

	@Override
	public void componentHidden(ComponentEvent e) {

	}
}
