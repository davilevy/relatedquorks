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

import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;

abstract class Table { 
	String tableName;
	int columnCount, rowCount;
	String[][] dataTable;

	Table(String tableName, int rowCount, int columnCount) {
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
	public String[] returnRow(int rowNumber) {
		return dataTable[rowNumber];
	}
	
	@Override
	public String toString(){
		// This would be overloaded to either print the entire table
		// if the error is called on line 37, all that would be printed is the columns
		
		//TODO Figure out if we can return this as [[all of row1], [all of row2], [all of row3], ...]
		return null;
	}
	
	/*
	public void add(String ...a) {
		if (a.isEmpty())
			throw new IllegalArgumentException("Row cannot be empty\n");
		if (columnCount != a.length)
			throw new IllegalArgumentException("Usage: " + this.toString() + "\n");
		else {
			ArrayList<String> row = new ArrayList<String>();
			for (String i : a)
				row.add(i);
			//there should be a way to add multiple memeber elements to the table object? if there is, then each row could be added to the table, otherwise, you would have to have an arraylist of arraylists, and add each row to the verticle arraylist...
		}
	}
	*/
}