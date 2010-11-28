package com.cs446teameo.repeater;

import java.util.GregorianCalendar;
import java.util.Vector;


public class SemValue {
	public int code = -1;
	
	public int ival = -1;
	
	public TimeSet tset = null;
	
	public Vector<Integer> cal = null;
	
	public Time tm = null;
	
	public SemValue(){}
	
	
	public static SemValue createDate(int field,int val) {
		SemValue v = new SemValue();
		v.ival = val;
		v.code = field;
		return v;
	}
	
	public static SemValue createOperator(int val) {
		SemValue v = new SemValue();
		v.code = val;
		return v;
	}
	public static SemValue createKeyword(int val){
		SemValue v = new SemValue();
		v.code = val;
		return v;
	}
}
