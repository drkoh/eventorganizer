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
"monthly"			{output();return keyword(Parser.MONTHLY);}
"yearly"			{output();return keyword(Parser.YEARLY);}
"weekly"			{output();return keyword(Parser.WEEKLY);}
"daily"				{output();return keyword(Parser.DAILY);}
"Jan"				{output();return keyword(Parser.JAN);}
"Feb"				{output();return keyword(Parser.FEB);}
"Mar"				{output();return keyword(Parser.MAR);}
"Apr"				{output();return keyword(Parser.APR);}
"May"				{output();return keyword(Parser.MAY);}
"Jun"				{output();return keyword(Parser.JUN);}
"Jul"				{output();return keyword(Parser.JUL);}
"Aug"				{output();return keyword(Parser.AUG);}
"Sep"				{output();return keyword(Parser.SEP);}
"Oct"				{output();return keyword(Parser.OCT);}
"Nov"				{output();return keyword(Parser.NOV);}
"Dec"				{output();return keyword(Parser.DEC);}

"Mon"				{output();return keyword(Parser.MON);}
"Tue"				{output();return keyword(Parser.TUE);}
"Wed"				{output();return keyword(Parser.WED);}
"Thu"				{output();return keyword(Parser.THU);}
"Fri"				{output();return keyword(Parser.FRI);}
"Sat"				{output();return keyword(Parser.SAT);}
"Sun"				{output();return keyword(Parser.SUN);}


"y"[1-2][0-9][0-9][0-9]  {output();return Date(Parser.YEAR,getContent(yytext(),1));}
"dom"([1-9]|[1-3][0-9])		{output();return Date(Parser.DAYOFMONTH,getContent(yytext(),3));}
"h"[1-2]?[0-9]				{output();return Date(Parser.HOUR,getContent(yytext(),1));}
"min"[1-6]?[0-9]			{output();return Date(Parser.MINUTE,getContent(yytext(),3));}


"("				{output();return operator(Parser.LEFTP);}
")"				{output();return operator(Parser.RIGHTP);}
"@"				{output();return operator(Parser.AT);}
"$"				{output();return operator(Parser.TO);}
":"				{output();return operator(Parser.DIV);}
","				{return operator(Parser.COMMA);}

.					{ System.out.println(" lexer error: " +yytext() ); }