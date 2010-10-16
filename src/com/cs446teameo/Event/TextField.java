package com.cs446teameo.Event;

public class TextField extends EField{
}






class SimpleText extends TextField{
	String content;
	int fontSize;
	int fontColor;
	
	//For the prototype, we just have to implement this simple version
	//If you guys want to add some other features
	//just add it is okay
	
	public SimpleText(String content,int fontSize,int fontColor){
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