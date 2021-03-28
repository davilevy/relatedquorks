import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;

public class SqrlsEngine {
	private static Stack<String> stk;
	private StringTokenizer query;
	/** For testing will use pre built **/
	static SqrlsTable student = new SqrlsTable("student", 5,"ST#","Classif");
	static SqrlsTable enrollment = new SqrlsTable("enrollment", 9, "ST#","C#","Midterm","Final");
	static SqrlsTable course = new SqrlsTable("course", 4, "C#", "Title");
	static Map<String, SqrlsTable> vars = new HashMap<String, SqrlsTable>();
	

	// constructor would be called by applet and given a string to evaluate, i.e. Enrollment[ST# = 100]
	String[][] SqrlsEngine(String argv) {
		this.stk = new Stack<String>();
		this.query = new StringTokenizer(argv);
		return evaluate(query);
	}
	


	static private String[][] evaluate(StringTokenizer argv) {
		 String[][] result = new String[1][1]; 
		while (argv.hasMoreTokens()) {
			String tok = argv.nextToken();
			if (tok.equalsIgnoreCase("(")) evaluate(argv); // This would indicate a sub query to be evaluated first
			else if (tok.equals("[")) { // v1, this would be indicative of a filter, projection, or a union
				/**
				 * Allowed formats for [ is:
				 * tableName[Col = "val"]
				 * tableName[Col, Col, Col]
				 * tableName[Col = Col]tableName
				 */
				
				String arguments = "";
				Stack<String> parameters = new Stack<String>();
				while ((arguments = argv.nextToken()) != "]") parameters.push(tok);
				String[][] parms = new String[parameters.size()][2];
				for(int i = 0; i < parameters.size(); i++) {
					String p = parameters.pop();
					if (p.equals("=")) {
						parms[i][0] = "equals";
						parms[i][1] = p;
						System.out.println(p);
					} else if (p.substring(p.length() - 1).equals(",")) {
						parms[i][0] = "column";
						parms[i][1] = p.substring(0,p.length() - 1);
						System.out.println(p);
					} else if (p.substring(p.length() - 1).equals("\"")) {
						parms[i][0] = "value";
						parms[i][1] = p;
						System.out.println(p);
					}
				}
				// now that the operands have been identified, apply to table
				if (parms[1][0].equals("equals")) {
					return vars.get(stk.pop()).filter(parms[0][1], parms[2][1]);
				}
			}
			else stk.push(tok);
		}
		return result;
	}	
	
	public static void main(String[] args) {
		dummySetup();
		System.out.println("hello");
		System.out.println(student.toString());
		System.out.println(enrollment.toString());
		System.out.println(course.toString());
		if (enrollment.containsColumn("ST#")) System.out.println("found");
		if (!enrollment.containsColumn("zzzzz")) System.out.println("not found");
		String[][] testProject = enrollment.project("ST#","Final");
		for(int r = 0; r < 10; r++) {
			for (int c = 0; c < 2; c++) {
				System.out.print(testProject[r][c] + " ");
			}
			System.out.println();
		}
		String[][] testFilter = enrollment.filter("ST#", "100");
		for(int r = 0; r < 10; r++) {
			for (int c = 0; c < 4; c++) {
				System.out.print(testFilter[r][c] + " ");
			}
			System.out.println();
		}
		StringTokenizer testeval = new StringTokenizer("Enrollment[ST#=100]");
		System.out.println(testeval.toString());
		String[][] eval = evaluate(testeval);
	}
	
	
	// for testing purposes assign values to tables
	private static void dummySetup() {
		vars.put("student",student);
		vars.put("enrollment", enrollment);
		vars.put("course", course);
		student.addRow("100","FR");
		student.addRow("200","SO");
		student.addRow("300","JR");
		student.addRow("400","SR");
		student.addRow("500","FR");
		enrollment.addRow("100","CS 1713","95","80");
		enrollment.addRow("100","MAT 1224","100","100");
		enrollment.addRow("100","ENG 1043","85","85");
		enrollment.addRow("100","HIS 1033","80","85");
		enrollment.addRow("200","CS 1713","91","90");
		enrollment.addRow("200","MAT 1224","50","80");
		enrollment.addRow("300","CS 1713","65","80");
		enrollment.addRow("500","CS 1713","100","80");
		enrollment.addRow("500","HIS 1033","95","90");
		course.addRow("CS  1713","Intro to CS");
		course.addRow("MAT 1224","Calculus I");
		course.addRow("ENG 1043","English I");
		course.addRow("HIS 1033","History of the World Part I");
	}
}
