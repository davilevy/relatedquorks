/**
*****TABLE REQUIREMENTS ******
table ablstract class
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
///////////// below is a quick think of how the sqrls table abstract class would look


import java.io.*;
import java.util.ArrayList;

abstract class Table { 
	String tableName;
	ArrayList<String> header;
	int columnCount;

	Table(String name, String ...a) {
		this.tableName = name;
		this.header = new ArrayList<String>();
		for (String i:a)
			header.add(i);
		this.columnCount = a.length;
	}

	public void add(String ...a) {
		if (a.isEmpty())
			throw new IllegalArgumentException("Row cannot be empty\n");
		if (columnCount != a.length)
			throw new IllegalArgumentException("Usage: " + this.toString() + "\n");
		else {
			ArrayList<String> row = new ArrayList<String>();
			for (String i : a)
				row.add(i);
			TODO: there should be a way to add multiple memeber elements to the table object? if there is, then each row could be added to the table, otherwise, you would have to have an arraylist of arraylists, and add each row to the verticle arraylist...
		}
	}

	TODO: implement toString for Table header
	public String toString(){
		// This would be overloaded to either print the entire table
		// if the error is called on line 36, all that would be printed is the columns
		return " ";
	}
}
