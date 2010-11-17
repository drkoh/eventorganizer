package com.cs446teameo.RepeatDriver;

public class SemValue {
	public int code = -1;
	
	public int ival = -1;
	
	public DateSet dset = null;
	
	public TimeSet tset = null;
	
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
}
