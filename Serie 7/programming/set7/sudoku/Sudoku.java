package programming.set7.sudoku;

/**
 * @author Bennet Blessmann
 *         Created on 10.12.2016.
 */
public class Sudoku extends NumberBoard {

	/**
	 * All iLearn-Sudokus are 9*9
	 * */
	public Sudoku() {
		super(9, 9);
	}

	/**
	 * only insert valid numbers and EMPTY
	 * */
	@Override
	public void setValueAt(int row, int col, int value) {
		if (value==EMPTY||(value>0&&value<10)){
			super.setValueAt(row, col, value);
		}
	}

	/**
	 * check if there is no error in the Sodoku,
	 * */
	public boolean isValid(){
		return validRows()&&valideColumns()&&vaildSubs();
	}

	/**
	 * check if all 3*3 Sub squares are valid
	 * */
	private boolean vaildSubs() {
		boolean[] contains;
		int temp;
		for(int i = 0;i<9;i++){
			contains = new boolean[9];
			for(int ii = 0;ii<9;ii++){
				temp = getValueAt(3*(i%3)+ii%3,(i/3)*3+ii/3);
				if(temp!=EMPTY){
					if(contains[temp-1]){
						return false;
					}
					else{
						contains[temp-1] = true;
					}
				}

			}
		}
		return true;
	}

	/**
	 * check if all columns are valid
	 * */
	private boolean valideColumns() {
		boolean[] contains;
		int temp;
		for(int i = 0;i<9;i++){
			contains = new boolean[9];
			for(int ii = 0;ii<9;ii++){
				temp = getValueAt(ii,i);
				if(temp!=EMPTY){
					if(contains[temp-1]){
						return false;
					}
					else{
						contains[temp-1] = true;
					}
				}

			}
		}
		return true;
	}

	/**
	 * check if all rows are valid
	 * */
	private boolean validRows() {
		boolean[] contains;
		int temp;
		for(int i = 0;i<9;i++){
			contains = new boolean[9];
			for(int ii = 0;ii<9;ii++){
				temp = getValueAt(i,ii);
				if(temp!=EMPTY){
					if(contains[temp-1]){
						return false;
					}
					else{
						contains[temp-1] = true;
					}
				}

			}
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(9*9*2);
		int temp;
		for(int i = 0;i<9;i++ ){
			for(int ii = 0; ii< 9;ii++){
				temp = getValueAt(i,ii);
				if(temp == -1){
					sb.append(' ');
				}else{
					sb.append(temp);
				}
				if(ii==8){
					sb.append('\n');
				}else{
					sb.append(' ');
				}
			}
		}
		return sb.toString();
	}
}