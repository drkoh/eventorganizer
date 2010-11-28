package com.cs446teameo.repeater;

import java.io.IOException;


public abstract class BaseParser {
	private Lexer lexer;
	protected TimeSet tset;
	public BaseParser(){
		lexer = null;
		tset = null;
	}
	public void setLexer(Lexer lexer){
		this.lexer = lexer;
	}
	
	void yyerror(String msg) {
	//	Driver.getInstance().issueError(msg));
	}
	
	int yylex() throws IOException {
		Integer token = lexer.yylex();
		if(token!= null)
			return token;
		return -1;
	}
	abstract int yyparse() throws IOException;
	
	public TimeSet parseFile() throws IOException {
		yyparse();
		return tset;
	}
	public void show(String field){
		System.out.println("enter field" + field);
	}
}
