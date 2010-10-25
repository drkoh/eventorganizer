package com.cs446teameo.Event;

public class TextField extends EField{
}




class CompleteText extends TextField{
	TextField txtField;
	
	public CompleteText(TextField txtField){
		this.txtField = txtField;
	}
}