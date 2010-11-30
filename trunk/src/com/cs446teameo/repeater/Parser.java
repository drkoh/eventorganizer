package com.cs446teameo.repeater;

import java.io.IOException;
import java.util.*;
//#line 8 "Parser.java"




public class Parser
             extends BaseParser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//## **user defined:SemValue
String   yytext;//user variable to return contextual strings
SemValue yyval; //used to return semantic vals from action routines
SemValue yylval;//the 'lval' (result) I got from yylex()
SemValue valstk[] = new SemValue[YYSTACKSIZE];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
final void val_init()
{
  yyval=new SemValue();
  yylval=new SemValue();
  valptr=-1;
}
final void val_push(SemValue val)
{
  try {
    valptr++;
    valstk[valptr]=val;
  }
  catch (ArrayIndexOutOfBoundsException e) {
    int oldsize = valstk.length;
    int newsize = oldsize*2;
    SemValue[] newstack = new SemValue[newsize];
    System.arraycopy(valstk,0,newstack,0,oldsize);
    valstk = newstack;
    valstk[valptr]=val;
  }
}
final SemValue val_pop()
{
  return valstk[valptr--];
}
final void val_drop(int cnt)
{
  valptr -= cnt;
}
final SemValue val_peek(int relative)
{
  return valstk[valptr-relative];
}
final SemValue dup_yyval(SemValue val)
{
  return val;
}
//#### end semantic value section ####
public final static short YEARLY=257;
public final static short MONTHLY=258;
public final static short WEEKLY=259;
public final static short DAILY=260;
public final static short DAYOFMONTH=261;
public final static short YEAR=262;
public final static short HOUR=263;
public final static short MINUTE=264;
public final static short JAN=265;
public final static short FEB=266;
public final static short MAR=267;
public final static short APR=268;
public final static short MAY=269;
public final static short JUN=270;
public final static short JUL=271;
public final static short AUG=272;
public final static short SEP=273;
public final static short OCT=274;
public final static short NOV=275;
public final static short DEC=276;
public final static short MON=277;
public final static short TUE=278;
public final static short WED=279;
public final static short THU=280;
public final static short FRI=281;
public final static short SAT=282;
public final static short SUN=283;
public final static short LEFTP=284;
public final static short RIGHTP=285;
public final static short AT=286;
public final static short TO=287;
public final static short DIV=288;
public final static short COMMA=289;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    0,    3,    1,    2,    2,    2,    2,    5,    5,
    4,    4,    4,    4,    4,    4,    4,    4,    4,    4,
    4,    4,    6,    6,    6,    6,    6,    6,    6,
};
final static short yylen[] = {                            2,
    3,    1,   23,    7,    5,    3,    3,    1,    2,    1,
    1,    1,    1,    1,    1,    1,    1,    1,    1,    1,
    1,    1,    1,    1,    1,    1,    1,    1,    1,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    2,    0,    0,    0,    0,    0,    0,
    8,    1,    0,    0,    0,    0,    0,    0,    0,   11,
   12,   13,   14,   15,   16,   17,   18,   19,   20,   21,
   22,    0,    6,   23,   24,   25,   26,   27,   28,   29,
    0,   10,    0,    0,    0,    9,    0,    4,    5,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    3,
};
final static short yydgoto[] = {                          2,
    3,   12,    4,   32,   41,   42,
};
final static short yysindex[] = {                      -250,
 -260,    0, -259,    0, -235, -236, -261, -258, -257, -256,
    0,    0, -251, -229, -264, -226, -263, -264, -252,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0, -249,    0,    0,    0,    0,    0,    0,    0,    0,
 -263,    0, -247, -227, -223,    0, -222,    0,    0, -246,
 -218, -240, -241, -216, -239, -214, -238, -233, -264, -237,
 -208, -234, -206, -231,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   57,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,    0,    0,  -18,    0,   17,
};
final static int YYTABLESIZE=58;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         43,
   20,   21,   22,   23,   24,   25,   26,   27,   28,   29,
   30,   31,    1,   34,   35,   36,   37,   38,   39,   40,
    8,    9,   10,   11,   13,   14,    6,    5,    7,   15,
   16,   17,   18,   19,   33,   44,   48,   49,   50,   45,
   60,   47,   51,   52,   53,   54,   55,   58,   56,   57,
   59,   61,   62,   65,   63,   64,    7,   46,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         18,
  265,  266,  267,  268,  269,  270,  271,  272,  273,  274,
  275,  276,  263,  277,  278,  279,  280,  281,  282,  283,
  257,  258,  259,  260,  286,  287,  286,  288,  264,  288,
  288,  288,  284,  263,  261,  288,  264,  261,  261,  289,
   59,  289,  289,  262,  285,  287,  263,  286,  288,  264,
  284,  289,  261,  285,  289,  262,    0,   41,
};
}
final static short YYFINAL=2;
final static short YYMAXTOKEN=289;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"YEARLY","MONTHLY","WEEKLY","DAILY","DAYOFMONTH","YEAR","HOUR",
"MINUTE","JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV",
"DEC","MON","TUE","WED","THU","FRI","SAT","SUN","LEFTP","RIGHTP","AT","TO",
"DIV","COMMA",
};
final static String yyrule[] = {
"$accept : TimeSet",
"TimeSet : Timeofday AT RepeatSet",
"TimeSet : SingleSet",
"SingleSet : HOUR DIV MINUTE AT LEFTP MONTH COMMA DAYOFMONTH COMMA YEAR RIGHTP TO HOUR DIV MINUTE AT LEFTP MONTH COMMA DAYOFMONTH COMMA YEAR RIGHTP",
"Timeofday : HOUR DIV MINUTE TO HOUR DIV MINUTE",
"RepeatSet : YEARLY DIV MONTH COMMA DAYOFMONTH",
"RepeatSet : MONTHLY DIV DAYOFMONTH",
"RepeatSet : WEEKLY DIV WEEKLIST",
"RepeatSet : DAILY",
"WEEKLIST : WEEKLIST SDAY",
"WEEKLIST : SDAY",
"MONTH : JAN",
"MONTH : FEB",
"MONTH : MAR",
"MONTH : APR",
"MONTH : MAY",
"MONTH : JUN",
"MONTH : JUL",
"MONTH : AUG",
"MONTH : SEP",
"MONTH : OCT",
"MONTH : NOV",
"MONTH : DEC",
"SDAY : MON",
"SDAY : TUE",
"SDAY : WED",
"SDAY : THU",
"SDAY : FRI",
"SDAY : SAT",
"SDAY : SUN",
};

//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse() throws IOException
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    //if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      //if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        //if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          //if (yydebug)
          //  yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        //if (yydebug)
          //debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      //if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            //if (yydebug)
              //debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            //if (yydebug)
              //debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        //if (yydebug)
          //{
          //yys = null;
          //if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          //if (yys == null) yys = "illegal-symbol";
          //debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          //}
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    //if (yydebug)
      //debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 1:
//#line 24 "parser.y"
{
						show("TimeSet");
						RepeatSet st = null;
						if(val_peek(0).ival == 0)
							st	= new Anniversary();
						else if(val_peek(0).ival == 1)
							st = new Monthly();
						else if(val_peek(0).ival == 2)
							st = new Weekly();
						else
							st = new Daily();
						st.setField(RepeatSet.TIME_OF_DAY,val_peek(2).cal);
						if(val_peek(0).ival < 3)
							st.setField(RepeatSet.DATE,val_peek(0).cal);
						tset = st;
					}
break;
case 2:
//#line 41 "parser.y"
{
						show("TimeSet");
						tset = val_peek(0).tset;
					}
break;
case 3:
//#line 49 "parser.y"
{
						show("SingleSet");
						SingleSet st = new SingleSet();
						GregorianCalendar start = new GregorianCalendar(val_peek(13).ival,val_peek(17).ival,val_peek(15).ival,val_peek(22).ival,val_peek(20).ival);
						GregorianCalendar end = new GregorianCalendar(val_peek(1).ival,val_peek(5).ival,val_peek(3).ival,val_peek(10).ival,val_peek(8).ival);
						st.setField(SingleSet.START,start);
						st.setField(SingleSet.END,end);
						yyval.tset = st;
					}
break;
case 4:
//#line 62 "parser.y"
{
						show("Timeofday");
						yyval.cal = new ArrayList<Integer>();
						yyval.cal.add(val_peek(6).ival);
						yyval.cal.add(val_peek(4).ival);
						yyval.cal.add(val_peek(2).ival);
						yyval.cal.add(val_peek(0).ival);
					}
break;
case 5:
//#line 73 "parser.y"
{
						show("RepeatSet");
						yyval.cal = new ArrayList<Integer>();
						yyval.cal.add(val_peek(2).ival);
						yyval.cal.add(val_peek(0).ival);
						yyval.ival = 0;
					}
break;
case 6:
//#line 81 "parser.y"
{
						show("RepeatSet");
						yyval.cal = new ArrayList<Integer>();
						yyval.cal.add(val_peek(0).ival);
						yyval.ival = 1;
					}
break;
case 7:
//#line 88 "parser.y"
{
						show("RepeatSet");
						yyval.cal = val_peek(0).cal;
						yyval.ival = 2;
					}
break;
case 8:
//#line 94 "parser.y"
{
						show("RepeatSet");
						yyval.ival = 3;
					}
break;
case 9:
//#line 101 "parser.y"
{
						show("WEEKLIST");
						yyval.cal.add(val_peek(0).ival);
					}
break;
case 10:
//#line 106 "parser.y"
{
						show("WEEKLIST");
						yyval.cal = new ArrayList<Integer>();
						yyval.cal.add(val_peek(0).ival);
					}
break;
case 11:
//#line 114 "parser.y"
{
						yyval.ival = 0;
					}
break;
case 12:
//#line 118 "parser.y"
{
						yyval.ival = 1;
					}
break;
case 13:
//#line 122 "parser.y"
{
						yyval.ival = 2;
					}
break;
case 14:
//#line 126 "parser.y"
{
						yyval.ival = 3;
					}
break;
case 15:
//#line 130 "parser.y"
{
						yyval.ival = 4;
					}
break;
case 16:
//#line 134 "parser.y"
{
						yyval.ival = 5;
					}
break;
case 17:
//#line 138 "parser.y"
{
						yyval.ival = 6;
					}
break;
case 18:
//#line 142 "parser.y"
{
						yyval.ival = 7;
					}
break;
case 19:
//#line 146 "parser.y"
{
						yyval.ival = 8;
					}
break;
case 20:
//#line 150 "parser.y"
{
						yyval.ival = 9;
					}
break;
case 21:
//#line 154 "parser.y"
{
						yyval.ival = 10;
					}
break;
case 22:
//#line 158 "parser.y"
{
						yyval.ival = 11;
					}
break;
case 23:
//#line 164 "parser.y"
{
						yyval.ival = GregorianCalendar.MONDAY;
					}
break;
case 24:
//#line 168 "parser.y"
{
						yyval.ival = GregorianCalendar.TUESDAY;
					}
break;
case 25:
//#line 172 "parser.y"
{
						yyval.ival = GregorianCalendar.WEDNESDAY;
					}
break;
case 26:
//#line 176 "parser.y"
{
						yyval.ival = GregorianCalendar.THURSDAY;
					}
break;
case 27:
//#line 180 "parser.y"
{
						yyval.ival = GregorianCalendar.FRIDAY;
					}
break;
case 28:
//#line 184 "parser.y"
{
						yyval.ival = GregorianCalendar.SATURDAY;
					}
break;
case 29:
//#line 188 "parser.y"
{
						yyval.ival = GregorianCalendar.SUNDAY;
					}
break;
//#line 596 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    //if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      //if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        //if (yydebug)
          //yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      //if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
//## The -Jnorun option was used ##
//## end of method run() ########################################



//## Constructors ###############################################
//## The -Jnoconstruct option was used ##
//###############################################################



}
//################### END OF CLASS ##############################
