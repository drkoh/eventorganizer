package com.cs446teameo.Event;

public class TextField extends EField{
}






class SimpleText extends TextField{
	String content = "";
	int fontSize = 12;
	String fontColor = "#000000";
	
	//For the prototype, we just have to implement this simple version
	//If you guys want to add some other features
	//just add it is okay
	
	public SimpleText(String content){
		this.content = content;
	}
	
	public SimpleText(String content,int fontSize,String fontColor){
		this.content = content;
		this.fontSize = fontSize;
		this.fontColor = fontColor;
	}
}



class CompleteText extends TextField{
	TextField txtField;
	
	public CompleteText(TextField txtField){
		this.txtField = txtField;
	}
}