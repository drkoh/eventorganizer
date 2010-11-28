%%
%public
%class Lexer
%unicode
%intwrap
	
%{
int getContent(String src,int head){
	return Integer.parseInt(src.substring(head));
}
void output(){
	if(yytext()!=null)
		System.out.println("text:" + yytext());
}

%}

NEWLINE				= (\r|\n|\r\n)
WHITESPACE			= ([ \t]+)
%%




{WHITESPACE}		{ /* Just ignore */    }
{NEWLINE}			{ /* Just ignore */    }		
"monthly"			{return keyword(Parser.MONTHLY);}
"yearly"			{return keyword(Parser.YEARLY);}
"weekly"			{return keyword(Parser.WEEKLY);}

"Jan"				{return keyword(Parser.JAN);}
"Feb"				{return keyword(Parser.FEB);}
"Mar"				{return keyword(Parser.MAR);}
"Apr"				{return keyword(Parser.APR);}
"May"				{return keyword(Parser.MAY);}
"Jun"				{return keyword(Parser.JUN);}
"Jul"				{return keyword(Parser.JUL);}
"Aug"				{return keyword(Parser.AUG);}
"Sep"				{return keyword(Parser.SEP);}
"Oct"				{return keyword(Parser.OCT);}
"Nov"				{return keyword(Parser.NOV);}
"Dec"				{return keyword(Parser.DEC);}

"Mon"				{return keyword(Parser.MON);}
"Tue"				{return keyword(Parser.TUE);}
"Wed"				{return keyword(Parser.WED);}
"Thu"				{return keyword(Parser.THU);}
"Fri"				{return keyword(Parser.FRI);}
"Sat"				{return keyword(Parser.SAT);}
"Sun"				{return keyword(Parser.SUN);}


"y"[1-2][0-9][0-9][0-9]  {return Date(Parser.YEAR,getContent(yytext(),1));}
"dom"([1-9]|[1-3][0-9])		{return Date(Parser.DAYOFMONTH,getContent(yytext(),3));}
"h"[1-2]?[0-9]				{output();return Date(Parser.HOUR,getContent(yytext(),1));}
"min"[1-6]?[0-9]			{output();return Date(Parser.MINUTE,getContent(yytext(),3));}


"("				{output();return operator(Parser.LEFTP);}
")"				{output();return operator(Parser.RIGHTP);}
"@"				{output();return operator(Parser.AT);}
"_"				{output();return operator(Parser.TO);}
":"				{output();return operator(Parser.DIV);}
","				{return operator(Parser.COMMA);}

.					{ System.out.println(" lexer error: " +yytext() ); }