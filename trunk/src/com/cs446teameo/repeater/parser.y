%{
import java.util.*;
%}

%Jclass Parser
%Jextends BaseParser
%Jsemantic SemValue
%Jnorun
%Jnodebug
%Jnoconstruct

%token YEARLY  MONTHLY WEEKLY DAYOFMONTH YEAR
%token HOUR MINUTE
%token JAN FEB MAR APR MAY JUN JUL AUG SEP OCT NOV DEC
%token MON TUE WED THU FRI SAT SUN
%token LEFTP RIGHTP AT TO DIV COMMA



%start TimeSet
 
%%
TimeSet			:   Timeofday AT RepeatSet 
					{
						show("TimeSet");
						RepeatSet st = null;
						if($3.ival == 0)
							st	= new Anniversary();
						else if($3.ival == 1)
							st = new Monthly();
						else
							st = new Weekly();
						st.setField(RepeatSet.TIME_OF_DAY,$1.cal);
						st.setField(RepeatSet.DATE,$3.cal);
						tset = st;
					}
				|	SingleSet
					{
						show("TimeSet");
						tset = $1.tset;
					}
					
				;

SingleSet		:	HOUR DIV MINUTE AT LEFTP MONTH COMMA DAYOFMONTH COMMA YEAR RIGHTP TO HOUR DIV MINUTE AT LEFTP MONTH COMMA DAYOFMONTH COMMA YEAR RIGHTP
					{
						show("SingleSet");
						SingleSet st = new SingleSet();
						GregorianCalendar start = new GregorianCalendar($10.ival,$6.ival,$8.ival,$1.ival,$3.ival);
						GregorianCalendar end = new GregorianCalendar($22.ival,$18.ival,$20.ival,$13.ival,$15.ival);
						st.setField(SingleSet.START,start);
						st.setField(SingleSet.END,end);
						$$.tset = st;
					}
				;
				
				
Timeofday		:	HOUR DIV MINUTE TO HOUR DIV MINUTE
					{
						show("Timeofday");
						$$.cal = new Vector<Integer>();
						$$.cal.add($1.ival);
						$$.cal.add($3.ival);
						$$.cal.add($5.ival);
						$$.cal.add($7.ival);
					}
				;				
				
RepeatSet		:	YEARLY DIV MONTH COMMA DAYOFMONTH
					{
						show("RepeatSet");
						$$.cal = new Vector<Integer>();
						$$.cal.add($3.ival);
						$$.cal.add($5.ival);
						$$.ival = 0;
					}
				|	MONTHLY DIV DAYOFMONTH
					{
						show("RepeatSet");
						$$.cal = new Vector<Integer>();
						$$.cal.add($3.ival);
						$$.ival = 1;
					}
				|	WEEKLY DIV WEEKLIST
					{
						show("RepeatSet");
						$$.cal = $3.cal;
						$$.ival = 2;
					}
				;
				
WEEKLIST		:	WEEKLIST SDAY
					{
						show("WEEKLIST");
						$$.cal.add($2.ival);
					}
				|	SDAY
					{
						show("WEEKLIST");
						$$.cal = new Vector<Integer>();
						$$.cal.add($1.ival);
					}
				;
				
MONTH			:	JAN
					{
						$$.ival = 0;
					}
				|	FEB
					{
						$$.ival = 1;
					}
				|	MAR
					{
						$$.ival = 2;
					}
				|	APR
					{
						$$.ival = 3;
					}
				|	MAY
					{
						$$.ival = 4;
					}
				|	JUN
					{
						$$.ival = 5;
					}
				|	JUL
					{
						$$.ival = 6;
					}
				|	AUG
					{
						$$.ival = 7;
					}
				|	SEP
					{
						$$.ival = 8;
					}
				|	OCT
					{
						$$.ival = 9;
					}
				|	NOV
					{
						$$.ival = 10;
					}
				|	DEC
					{
						$$.ival = 11;
					}
				;
				
SDAY			:	MON
					{
						$$.ival = GregorianCalendar.MONDAY;
					}
				|	TUE
					{
						$$.ival = GregorianCalendar.TUESDAY;
					}
				|	WED
					{
						$$.ival = GregorianCalendar.WEDNESDAY;
					}
				|	THU
					{
						$$.ival = GregorianCalendar.THURSDAY;
					}
				|   FRI
					{
						$$.ival = GregorianCalendar.FRIDAY;
					}
				|	SAT
					{
						$$.ival = GregorianCalendar.SATURDAY;
					}
				|	SUN
					{
						$$.ival = GregorianCalendar.SUNDAY;
					}
				;
%%