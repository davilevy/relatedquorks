/**
*****TABLE REQUIREMENTS ******
table abstract class
  column requirements - table should be able to implement as many columns or as few columns as requested
  row requirements
  table name uniqueness
**/
/**
*****REQUIRED METHODS *******
using the 'dot' (table.column) should check first if the column exists, and return a hard error/fail if the column does not exist
  if table is a struct, and the columns are a member of the struct, the dot method is already built in, and the struct should be able to handle as many columns as requested
if the table does not contain the requested information, it would return a soft error/fail, a null 
**/

/*
 * TODO:
 * Set created tables to protected access modifiers
 * Finish overriding the toString method
 * Possibly create a method to return an entire column or set of columns
 * Set up scanner I/O for testing in main method and slowly convert to web page I/O
 * Double check all the access modifiers
 * Maybe make return rows return a table of it's own of just the results
 * Maybe overload constructor to create a table from returned rows (is that even possible or can this only be handled by an outside method?)
 * Make all SqrlsTable instances: protected SqrlsTable myTable = new SqrlsTable();
 */

import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.lang.StringBuilder;

public class SqrlsTable { 
	String tableName;
	int columnCount, rowCount;
	String[][] dataTable;
	
	//TODO possibly over load constructor to allow for new table to be made from returned rows
	SqrlsTable(String tableName, int rowCount, int columnCount) {
		this.tableName = tableName;
		this.rowCount = rowCount;
		this.columnCount = columnCount;
		dataTable = new String[rowCount][columnCount];
	}
	
	//Method for searching a column and finding the row numbers of matching rows
	private ArrayList<Integer> searchColumn(String columnName, String val) {
		int columnNumber = findColumn(columnName);
		ArrayList<Integer> matchingRows = new ArrayList<Integer>();
		//Function to search every row to the requested column and record the row number of matches
		for(int i=0; i<rowCount; i++) {
			if(val.equals(dataTable[i][columnNumber])) {
				matchingRows.add(i);
			}
		}
		return matchingRows;
	}
	
	//Method that attempts to find a requested column
	private int findColumn(String columnName) {
		for(int i=0; i<columnCount; i++) {
			if(columnName.equals(dataTable[0][i])) {
				//If the column is found. Return the column number and stop the loop
				return i;
			}
		}
		//Terminate the program if the column could not be found
		throw new IllegalArgumentException("Column does not exist\n");
	}
	
	//Method that returns the requested row as a String array
	public String[] returnRows(int rowNumber) {
		return dataTable[rowNumber];
	}
	
	@Override
	public String toString(){
		// This would be overloaded to either print the entire table
		//TODO Figure out if we can return this as [[all of row1], [all of row2], [all of row3], ...]
		StringBuilder str = new StringBuilder();
		
		return str.toString();
	}
}