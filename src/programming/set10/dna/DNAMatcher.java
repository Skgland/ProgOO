package programming.set10.dna;

/**
 * Created by BB20101997 on 13. Jan. 2017.
 */
public class DNAMatcher {

	private DNAMatcher() {}

	/**
	 * Returns the index of the first position in baseDNA where candidateDNA can
	 * bind to baseDNA, if any.
	 *
	 * @param baseDNA      the base DNA string.
	 * @param candidateDNA the DNA string to try to bind to the base DNA.
	 *
	 * @return index of the first binding position or {@code -1} if candidateDNA cannot bind to baseDNA. Also returns
	 * {@code -1} if either of the DNA strings contains characters other than A, C, G, and T.
	 */
	public static int findFirstBindingPosition(String baseDNA, String candidateDNA) {

		//convert the candidate to it's opposing side
		char[] candidate = candidateDNA.toCharArray();

		for(int i = 0; i < candidate.length; i++) {
			char c = candidate[i];
			switch(c) {
				case 'A': {
					candidate[i] = 'T';
					break;
				}
				case 'T': {
					candidate[i] = 'A';
					break;
				}
				case 'G': {
					candidate[i] = 'C';
					break;
				}
				case 'C': {
					candidate[i] = 'G';
					break;
				}
				default: {
					return -1;
				}
			}
		}

		char[] base = baseDNA.toCharArray();
		for(char c:base){
			switch(c){
				case 'A':
				case 'T':
				case 'G':
				case 'C': continue;
				default: return -1;
			}
		}

		out: for(int b = 0;b<=base.length-candidate.length;b++){
			for(int offset = 0;offset<candidate.length;offset++){
				if(base[b+offset]!=candidate[offset]){
					continue out;
				}
			}
			return b;
		}
		return -1;
	}
}
