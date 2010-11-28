package com.cs446teameo.repeater;

public class BaseLexer{
	Parser parser;
	public BaseLexer(){
		parser = null;
	}
	public void setParser(Parser parser){
		this.parser  =parser;
	}
	
	protected void setSemantic(SemValue v) {
		parser.yylval = v;
	}

	protected int Date(int field,int val) {
		setSemantic(SemValue.createDate(field,val));
		return field;
	}

	protected int operator(int code) {
		setSemantic(SemValue.createOperator(code));
		return code;
	}
	
	protected int keyword(int code){
		setSemantic(SemValue.createKeyword(code));
		return code;
	}
	
	protected void issueError(String code) {
		//Driver.getDriver().issueError(error);
	}
	
}
