package com.cs446teameo.RepeatDriver;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.GregorianCalendar;
import java.util.Vector;


public class Driver {
	private static Driver _instance = null;
	private Vector<String> errorList = null;
	
	private BufferedReader br = null;
	
	private Lexer lexer = null;
	
	private Parser parser = null;
	
	private Driver() throws FileNotFoundException{
		errorList = new Vector<String>();
		br = new BufferedReader(new InputStreamReader(new FileInputStream("debug.txt")));
		lexer = new Lexer(br);
		parser = new Parser();
		lexer.setParser(parser);
		parser.setLexer(lexer);
	}
	public TimeSet parse(String target){
		return null;
	}
	
	public String parse(TimeSet set){
		return null;
	}
	
	public static Driver getInstance() throws FileNotFoundException{
		if(_instance == null)
			_instance = new Driver();
		return _instance;
	}
	
	public void issueError(String description) {
		errorList.add(description);
	}
}
