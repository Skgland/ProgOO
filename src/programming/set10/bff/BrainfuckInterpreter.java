package programming.set10.bff;

import java.util.Arrays;

/**
 * Created by BB20101997 on 13. Jan. 2017.
 */
public class BrainfuckInterpreter {

	private char[] programm;
	private byte[] cell;

	private static final int CELL_AMMOUNT = 1024;

	public void setProgram(char[] prog){
		int tally = 0;

		//make a copy of prog so it won't be altered while or after checking for correctness
		char[] tmp = Arrays.copyOf(prog,prog.length);

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
		cell = new byte[CELL_AMMOUNT];
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
						int tally = 0;
						while(programm[i]!=']'||tally!=0){
							if(programm[i]=='['){
								tally++;
							}
							i++;
							if(programm[i]==']'){
								tally--;
							}
						}
					}
					continue;
				}
				case ']':{
					if(cell[currentCell] != 0) {
						int tally = 0;
						while(programm[i] != '['||tally!=0) {
							if(programm[i] == ']') {
								tally++;
							}
							i--;
							if(programm[i] == '[') {
								tally--;
							}
						}
					}
				}
			}
		}

		return sb.toString();
	}
}
