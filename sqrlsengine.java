import java.util.StringTokenizer;

/**
evaluate quork query
**/

class SqrlsEngine {
	private Stack<String> stk;
	private StringTokenizer query;


	SqrlsEngine(String argv) {
		this.stk = new Stack<String>();
		query = new StringTokenizer(argv);
		return evaluate(query);
	}
	
	private SqrlsTable evaluate(StringTokenizer argv) {
		while (argv.hasMoreTokens()) {
			
		}
	}
}