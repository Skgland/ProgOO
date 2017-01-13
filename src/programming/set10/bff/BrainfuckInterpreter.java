package programming.set10.bff;

/**
 * Created by BB20101997 on 13. Jan. 2017.
 */
public class BrainfuckInterpreter {

	char[] programm;
	byte[] cell;

	public void setProgramm(String prog){
		char[] tmp = prog.toCharArray();
		int tally = 0;
		for(char c:tmp){
			if(c=='['){
				tally++;
			}else if(c==']'){
				tally--;
			}

			if(tally<0){
				throw new IllegalArgumentException("Closing a Loop before opening one!");
			}
		}

		if(tally!=0){
			throw new IllegalArgumentException("Unbalanced Loop Brackets!");
		}
		programm = tmp;
	}

	public String interpret(){
		if(programm==null)
			throw new IllegalStateException("No Program set!");
		int currentCell = 0;
		cell = new byte[256];
		StringBuilder sb = new StringBuilder();
		for(int i = 0;i<programm.length;i++){
			switch(programm[i]){
				case '<':{
					currentCell--;
					continue;
				}
				case '>':{
					currentCell++;
					continue;
				}
				case  '+':{
					cell[currentCell]++;
					continue;
				}
				case '-':{
					cell[currentCell]--;
					continue;
				}
				case '.':{
					sb.append((char)cell[currentCell]);
					continue;
				}
				case '/':{
					cell[currentCell]*=2;
					continue;
				}
				case'[':{
					if(cell[currentCell]==0){
						while(programm[i]!=']'){
							i++;
						}
					}
					continue;
				}
				case ']':{
					if(cell[currentCell] != 0) {
						while(programm[i] != '[') {
							i--;
						}
					}
				}
			}
		}
		return sb.toString();
	}

}
