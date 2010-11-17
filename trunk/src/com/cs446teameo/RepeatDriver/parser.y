%{
import java.util.*;
%}

%Jclass Parser
%Jextends BaseParser
%Jsemantic SemValue
%Jnorun
%Jnodebug
%Jnoconstruct

%token YEAR   MONTH  DAYOFMONTH   WEEKOFMONTH  DAYOFWEEK 
%token HOUR MINUTE
%token MERGE  DIFF  JOINT LEFTP RIGHTP AT TO DIV

%left  MERGE DIFF
%left  JOINT  
%nonassoc ')'

%start TimeSet
 
%%
TimeSet			:   TimeOfDay AT DateSet
					{
						show("TimeSet");
						tset = new TimeSet($3.dset,$1.tm);
					}
				;

DateSet			:	DateSet MERGE DateSet
					{
						show("DateSet");
						$$.dset = new NestDateSet($1.dset,$3.dset,Parser.MERGE);
					}
				|	DateSet DIFF DateSet
					{
						show("DateSet");
						$$.dset = new NestDateSet($1.dset,$3.dset,Parser.DIFF);
					}
				|	DateSet JOINT DateSet
					{
						show("DateSet");
						$$.dset = new NestDateSet($1.dset,$3.dset,Parser.JOINT);
					}
				| 	LEFTP DateSet RIGHTP
					{
					show("DateSet");
						$$ = $2;
					}
				|	SingleSet
					{
					show("DateSet");
						$$.dset = $1.dset;
					}
				;
SingleSet		:	YEAR MONTH DAYOFMONTH
					{
					show("SingleSet");
						$$.dset = new DateView($1.ival,$2.ival,$3.ival);
					}
				|	YEAR MONTH WEEKOFMONTH DAYOFWEEK
					{
					show("SinglesSet");
						$$.dset = new WeekView($1.ival,$2.ival,$3.ival,$4.ival);
					}
				;
TimeOfDay		:	HOUR DIV MINUTE TO HOUR DIV MINUTE
					{
					show("TImeoFday");
						$$.tm = new Time();
						$$.tm.set($1.ival,$3.ival,$5.ival,$7.ival);
					}
				;				

%%