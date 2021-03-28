import java.util.StringTokenizer;

public class TestTokenizer {

	public static void main(String[] args) {
		String i;
		StringTokenizer test = new StringTokenizer("This, is a \"test\"");
		while(test.hasMoreTokens()) {
			if((i = test.nextToken()).contentEquals("a")) System.out.println(i);
			System.out.println(i + " " + test.countTokens() + " " + test.toString());
		}

	}

}
