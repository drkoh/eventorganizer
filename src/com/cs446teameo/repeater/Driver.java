package com.cs446teameo.repeater;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringBufferInputStream;
import java.util.GregorianCalendar;
import java.util.Vector;


public class Driver {
	private static Driver _instance = null;
	private Vector<String> errorList = null;
	
	private BufferedReader br = null;
	private InputStream is = null;
	private Lexer lexer = null;
	
	private Parser parser = null;
	
	private Driver() throws FileNotFoundException{
		errorList = new Vector<String>();
	//	br = new BufferedReader(new InputStreamReader(new FileInputStream("debug.txt")));
	//	lexer = new Lexer(br);
		parser = new Parser();
		lexer.setParser(parser);
		parser.setLexer(lexer);
		is = null;
	//	lexer = new Lexer(is);
	}
	public static Driver getInstance() throws FileNotFoundException{
		if(_instance == null)
			_instance = new Driver();
		return _instance;
	}
	
	public void issueError(String description) {
		errorList.add(description);
	}
	
	
	public void setString(String s){
		is = new StringBufferInputStream(s);
		lexer = new Lexer(is);
		parser = new Parser();
		lexer.setParser(parser);
		parser.setLexer(lexer);
	}
	
	public TimeSet parse() throws FileNotFoundException, IOException{
		return Driver.getInstance().parser.parseFile();
	}
	
	public static void main(String[] args) throws IOException{
		Driver driver = Driver.getInstance();
		TimeSet tset = driver.parser.parseFile();
		System.out.println(tset.toString());
		return;
	}
	
}
