package com.cs446teameo.RepeatDriver;
import java.util.Date;


public class NestDateSet extends DateSet{
	DateSet first;
	DateSet second;
	int operation;
	public NestDateSet(DateSet first, DateSet second,int op) {
		this.first = first;
		this.second = second;
		operation = op;
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		char rag= ' ';
		switch(operation){
			case Parser.DIFF: rag = '-';break;
			case Parser.MERGE: rag = '+';break;
			case Parser.JOINT: rag = '*';break;
			default: System.out.println("error");return null;
		}
		return "(" + first.toString() + " " + rag + " " + second.toString() + ")"; 
	}
	@Override
	protected Date nextTriggerAfter(Date date) {
		// TODO Auto-generated method stub
		Date next = date;
		if(operation == Parser.MERGE){
			Date f = first.nextTriggerAfter(date);
			Date s = second.nextTriggerAfter(date);
			if(f == null && s == null)
				return null;
			else if(f != null && s == null)
				return f;
			else if(f == null)
				return s;
			else{
				if(f.after(s))
					return s;
				else return f;
			}
		}else if(operation == Parser.JOINT){
			while((next = first.nextTriggerAfter(next)) != null){
				if(second.contain(date))
					return next;
			}
			return null;
		}else if(operation == Parser.DIFF){
			while((next = first.nextTriggerAfter(next)) != null){
				if(!second.contain(date))
					return next;
			}
			return null;
		}
		return null;
	}
	@Override
	protected boolean contain(Date date) {
		// TODO Auto-generated method stub
		if(operation == Parser.MERGE)
			return first.contain(date) || second.contain(date);
		else if (operation == Parser.DIFF)
			return first.contain(date) && !second.contain(date);
		else return first.contain(date) && second.contain(date);
	}
}
