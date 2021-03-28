import java.util.Stack;
import java.util.StringTokenizer;

public class SqrlsEngine {
	private Stack<String> stk;
	private StringTokenizer query;
	/** For testing will use pre built **/
	static SqrlsTable student = new SqrlsTable("student", 5,"ST#","Classif");
	static SqrlsTable enrollment = new SqrlsTable("enrollment", 9, "ST#","C#","Midterm","Final");
	static SqrlsTable course = new SqrlsTable("course", 4, "C#", "Title");

	
	SqrlsTable SqrlsEngine(String argv) {
		this.stk = new Stack<String>();
		this.query = new StringTokenizer(argv);
		return evaluate(query);
	}
	


	private SqrlsTable evaluate(StringTokenizer argv) {
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
				stk.push(tok); // this would be the opening "["
				
			}
			else stk.push(tok);
		}
		return null;
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
	}
	
	private static void dummySetup() {
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
