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
"y"[1-2][0-9][0-9][0-9]  {output();return Date(Parser.YEAR,getContent(yytext(),1));}
"e_y"						{output();return Date(Parser.YEAR,-1);}
"m"([1-9]|1[0-2])				{output();return Date(Parser.MONTH,getContent(yytext(),1));}
"e_m"						{output();return Date(Parser.MONTH,-1);}
"dom"([1-9]|[1-3][0-9])		{output();return Date(Parser.DAYOFMONTH,getContent(yytext(),3));}
"e_dom"						{output();return Date(Parser.DAYOFMONTH,-1)}
"w"[1-5]		{output();return Date(Parser.WEEKOFMONTH,getContent(yytext(),1));}
"e_w"			{output();return Date(Parser.WEEKOFMONTH,-1);}
"dow"[1-7]			{output();return Date(Parser.DAYOFWEEK,getContent(yytext(),3));}
"e_dow"			{output();return Date(Parser.DAYOFWEEK,-1);}
"h"[1-2]?[0-9]				{output();return Date(Parser.HOUR,getContent(yytext(),1));}
"min"[1-6]?[0-9]			{output();return Date(Parser.MINUTE,getContent(yytext(),3));}
"+"				{output();return operator(Parser.MERGE);}
"-"				{output();return operator(Parser.DIFF);}
"*"				{output();return operator(Parser.JOINT);}
"("				{output();return operator(Parser.LEFTP);}
")"				{output();return operator(Parser.RIGHTP);}
"@"				{output();return operator(Parser.AT);}
"_"				{output();return operator(Parser.TO);}
":"				{output();return operator(Parser.DIV);}
.					{ System.out.println(" lexer error: " +yytext() ); 		}