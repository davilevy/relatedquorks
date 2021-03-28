
public class SqrlsTable {
	private int rows;
	private int columns;
	private int nextRow;
	private String[][] table;
	private String name;
	
	// constructor requires a name, a count of the rows to be entered, and a list of column headers
	SqrlsTable(String name, int rows, String ...a){
		this.name = name;
		this.rows = rows + 1;
		this.columns = a.length;
		this.nextRow = 0;
		table = new String[this.rows][this.columns];
		addRow(a);
	}
	
	public void addRow(String ...a) {
		if (a.length != columns) {
			throw new IllegalArgumentException("Invalid number of elements");
		}
		for(int i = 0; i < a.length; i++) {
			table[this.nextRow][i] = a[i];
		}
		this.nextRow++;
	}
	
	public boolean containsColumn(String a) {
		// assume that the column is not in the table
		boolean found = false;
		
		// only if the column is found, is the boolean set to true
		for (int i = 0; i < columns; i++) {
			if(table[0][i].equalsIgnoreCase(a)) found = true;
		}
		
		return found;
	}
	
	// project is supposed to return from a given table specified columns
	public String[][] project(String ...a) {
		// first validate that the selected columns are in the table
		for (String i : a) {
			if (!containsColumn(i)) throw new IllegalArgumentException("column " + i + " not found");
		}
		// placeholder for the result set
		String[][] result = new String[rows][a.length];
		// placeholder to record the column number for the given names
		int[] col = new int[a.length];
		// record column number for given names
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < columns; j++) {
				if (table[0][j].equalsIgnoreCase(a[i])) col[i] = j;
			}
		}
		// for each row copy column information to result set
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < a.length; c++) {
				result[r][c] = table[r][col[c]];
			}
		}
		return result;
	}
	
	// filter is supposed to return from a given table only those rows whose column matches the criteria
	public String[][] filter(String col, String val) {
		// placeholder for result set
		String[][] result = new String[rows][columns];
		// identify and record column of criteria
		int colNum = findColNumber(col);
		int nextRow = 1;
		// copy table headers to result set
		for(int c = 0; c < columns; c++) {
			result[0][c] = table[0][c];
		}
		// for each row in table, check if column matches criteria 
		for (int r = 1; r < rows; r++) {
			// if a match is found, that row is copied to result set, and the nextRow tracker is incremented by one
			if (table[r][colNum].equalsIgnoreCase(val)) {
				copyRow(table,result,r,nextRow);
				nextRow++;
			}
		}
		return result;
	}
	
	// reusable method to copy an entire row from one table to another
	public void copyRow(String[][] from, String[][] to, int fRow, int tRow) {
		for (int c = 0; c < columns; c++) {
			to[tRow][c] = from[fRow][c];
		}
	}
	
	// reusable method to find the column number of a given header
	public int findColNumber(String header) {
		int colNum = -1;
		for (int c = 0; c < columns; c++)
			if (table[0][c].equalsIgnoreCase(header))
				colNum = c;
		if(colNum < 0) throw new IllegalArgumentException("Column not found");
		return colNum;
	}
	
	
	// overloaded toString method to arrange a table to be printed in a well laid out manner
	public String toString() {
		String r = name + "\n";
		r += "rows: " + rows + " columns: " + columns + "\n";
		for (int j = 0; j < rows; j++) {
			for (int i = 0; i < columns; i++) {
				r += table[j][i] + " ";
			}
			r += "\n";
		}
		return r;
	}

}
