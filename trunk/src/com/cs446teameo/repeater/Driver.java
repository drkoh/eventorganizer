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
	//	parser = new Parser();
	//	lexer.setParser(parser);
	//	parser.setLexer(lexer);
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
	
	
	@SuppressWarnings("deprecation")
	public void setString(String s){
		//Log.i("repeater", ((Boolean)(s == null)).toString());
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
	//	for(int i= 0 ; i < 3;i++){
	//		System.out.println("the " +  i + " time");
			driver.setString("h16:min40@ (Dec,dom1,y2010) $ h16:min45@ (Dec,dom1,y2010)");
			TimeSet tset = driver.parse();
			System.out.println(tset.toString());
			System.out.println(tset.nextTrigger().getTime().toLocaleString());
	//	}
		return;
	}
	
}
