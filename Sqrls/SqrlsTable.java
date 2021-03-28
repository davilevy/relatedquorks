
public class SqrlsTable {
	private int rows;
	private int columns;
	private int nextRow;
	private String[][] table;
	private String name;
	
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
		boolean found = false;
		
		for (int i = 0; i < columns; i++) {
			if(table[0][i].equalsIgnoreCase(a)) found = true;
		}
		
		return found;
	}
	
	public String[][] project(String ...a) {
		for (String i : a) {
			if (!containsColumn(i)) throw new IllegalArgumentException("column " + i + " not found");
		}
		String[][] result = new String[rows][a.length];
		int[] col = new int[a.length];
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < columns; j++) {
				if (table[0][j].equalsIgnoreCase(a[i])) col[i] = j;
			}
		}
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < a.length; c++) {
				result[r][c] = table[r][col[c]];
			}
		}
		return result;
	}
	
	public String[][] filter(String col, String val) {
		String[][] result = new String[rows][columns];
		int colNum = findColNumber(col);
		int nextRow = 1;
		for(int c = 0; c < columns; c++) {
			result[0][c] = table[0][c];
		}
		for (int r = 1; r < rows; r++) {
			if (table[r][colNum].equalsIgnoreCase(val)) {
				copyRow(table,result,r,nextRow);
				nextRow++;
			}
		}
		return result;
	}
	
	public void copyRow(String[][] from, String[][] to, int fRow, int tRow) {
		for (int c = 0; c < columns; c++) {
			to[tRow][c] = from[fRow][c];
		}
	}
	
	public int findColNumber(String header) {
		int colNum = -1;
		for (int c = 0; c < columns; c++)
			if (table[0][c].equalsIgnoreCase(header))
				colNum = c;
		if(colNum < 0) throw new IllegalArgumentException("Column not found");
		return colNum;
	}
	
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
