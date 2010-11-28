package com.cs446teameo.repeater;

public class Lexer extends BaseLexer{
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 65536;
	private final int YY_EOF = 65537;

int getContent(String src,int head){
	return Integer.parseInt(src.substring(head));
}
void output(){
	if(yytext()!=null)
		System.out.println("text:" + yytext());
}
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private boolean yy_at_bol;
	private int yy_lexical_state;

	public Lexer (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	public Lexer (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private Lexer () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;
	}

	private boolean yy_eof_done = false;
	private final int YYINITIAL = 0;
	private final int yy_state_dtrans[] = {
		0
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private int yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_end () {
		if (yy_buffer_end > yy_buffer_start &&
		    '\n' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
		if (yy_buffer_end > yy_buffer_start &&
		    '\r' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
	}
	private boolean yy_last_was_cr=false;
	private void yy_mark_start () {
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
		yy_at_bol = (yy_buffer_end > yy_buffer_start) &&
		            ('\r' == yy_buffer[yy_buffer_end-1] ||
		             '\n' == yy_buffer[yy_buffer_end-1] ||
		             2028/*LS*/ == yy_buffer[yy_buffer_end-1] ||
		             2029/*PS*/ == yy_buffer[yy_buffer_end-1]);
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
	private int[][] unpackFromString(int size1, int size2, String st) {
		int colonIndex = -1;
		String lengthString;
		int sequenceLength = 0;
		int sequenceInteger = 0;

		int commaIndex;
		String workString;

		int res[][] = new int[size1][size2];
		for (int i= 0; i < size1; i++) {
			for (int j= 0; j < size2; j++) {
				if (sequenceLength != 0) {
					res[i][j] = sequenceInteger;
					sequenceLength--;
					continue;
				}
				commaIndex = st.indexOf(',');
				workString = (commaIndex==-1) ? st :
					st.substring(0, commaIndex);
				st = st.substring(commaIndex+1);
				colonIndex = workString.indexOf(':');
				if (colonIndex == -1) {
					res[i][j]=Integer.parseInt(workString);
					continue;
				}
				lengthString =
					workString.substring(colonIndex+1);
				sequenceLength=Integer.parseInt(lengthString);
				workString=workString.substring(0,colonIndex);
				sequenceInteger=Integer.parseInt(workString);
				res[i][j] = sequenceInteger;
				sequenceLength--;
			}
		}
		return res;
	}
	private int yy_acpt[] = {
		/* 0 */ YY_NOT_ACCEPT,
		/* 1 */ YY_NO_ANCHOR,
		/* 2 */ YY_NO_ANCHOR,
		/* 3 */ YY_NO_ANCHOR,
		/* 4 */ YY_NO_ANCHOR,
		/* 5 */ YY_NO_ANCHOR,
		/* 6 */ YY_NO_ANCHOR,
		/* 7 */ YY_NO_ANCHOR,
		/* 8 */ YY_NO_ANCHOR,
		/* 9 */ YY_NO_ANCHOR,
		/* 10 */ YY_NO_ANCHOR,
		/* 11 */ YY_NO_ANCHOR,
		/* 12 */ YY_NO_ANCHOR,
		/* 13 */ YY_NO_ANCHOR,
		/* 14 */ YY_NO_ANCHOR,
		/* 15 */ YY_NO_ANCHOR,
		/* 16 */ YY_NO_ANCHOR,
		/* 17 */ YY_NO_ANCHOR,
		/* 18 */ YY_NO_ANCHOR,
		/* 19 */ YY_NO_ANCHOR,
		/* 20 */ YY_NO_ANCHOR,
		/* 21 */ YY_NO_ANCHOR,
		/* 22 */ YY_NO_ANCHOR,
		/* 23 */ YY_NO_ANCHOR,
		/* 24 */ YY_NO_ANCHOR,
		/* 25 */ YY_NO_ANCHOR,
		/* 26 */ YY_NO_ANCHOR,
		/* 27 */ YY_NO_ANCHOR,
		/* 28 */ YY_NO_ANCHOR,
		/* 29 */ YY_NO_ANCHOR,
		/* 30 */ YY_NO_ANCHOR,
		/* 31 */ YY_NO_ANCHOR,
		/* 32 */ YY_NO_ANCHOR,
		/* 33 */ YY_NO_ANCHOR,
		/* 34 */ YY_NO_ANCHOR,
		/* 35 */ YY_NO_ANCHOR,
		/* 36 */ YY_NO_ANCHOR,
		/* 37 */ YY_NOT_ACCEPT,
		/* 38 */ YY_NO_ANCHOR,
		/* 39 */ YY_NO_ANCHOR,
		/* 40 */ YY_NO_ANCHOR,
		/* 41 */ YY_NO_ANCHOR,
		/* 42 */ YY_NO_ANCHOR,
		/* 43 */ YY_NOT_ACCEPT,
		/* 44 */ YY_NO_ANCHOR,
		/* 45 */ YY_NOT_ACCEPT,
		/* 46 */ YY_NO_ANCHOR,
		/* 47 */ YY_NOT_ACCEPT,
		/* 48 */ YY_NO_ANCHOR,
		/* 49 */ YY_NOT_ACCEPT,
		/* 50 */ YY_NO_ANCHOR,
		/* 51 */ YY_NOT_ACCEPT,
		/* 52 */ YY_NO_ANCHOR,
		/* 53 */ YY_NOT_ACCEPT,
		/* 54 */ YY_NO_ANCHOR,
		/* 55 */ YY_NOT_ACCEPT,
		/* 56 */ YY_NO_ANCHOR,
		/* 57 */ YY_NOT_ACCEPT,
		/* 58 */ YY_NO_ANCHOR,
		/* 59 */ YY_NOT_ACCEPT,
		/* 60 */ YY_NO_ANCHOR,
		/* 61 */ YY_NOT_ACCEPT,
		/* 62 */ YY_NO_ANCHOR,
		/* 63 */ YY_NOT_ACCEPT,
		/* 64 */ YY_NO_ANCHOR,
		/* 65 */ YY_NOT_ACCEPT,
		/* 66 */ YY_NO_ANCHOR,
		/* 67 */ YY_NOT_ACCEPT,
		/* 68 */ YY_NOT_ACCEPT,
		/* 69 */ YY_NOT_ACCEPT,
		/* 70 */ YY_NOT_ACCEPT,
		/* 71 */ YY_NOT_ACCEPT,
		/* 72 */ YY_NOT_ACCEPT,
		/* 73 */ YY_NOT_ACCEPT,
		/* 74 */ YY_NOT_ACCEPT,
		/* 75 */ YY_NOT_ACCEPT,
		/* 76 */ YY_NOT_ACCEPT,
		/* 77 */ YY_NOT_ACCEPT,
		/* 78 */ YY_NOT_ACCEPT,
		/* 79 */ YY_NOT_ACCEPT,
		/* 80 */ YY_NOT_ACCEPT,
		/* 81 */ YY_NOT_ACCEPT,
		/* 82 */ YY_NOT_ACCEPT,
		/* 83 */ YY_NOT_ACCEPT,
		/* 84 */ YY_NOT_ACCEPT,
		/* 85 */ YY_NOT_ACCEPT,
		/* 86 */ YY_NOT_ACCEPT,
		/* 87 */ YY_NOT_ACCEPT,
		/* 88 */ YY_NOT_ACCEPT,
		/* 89 */ YY_NO_ANCHOR,
		/* 90 */ YY_NO_ANCHOR,
		/* 91 */ YY_NOT_ACCEPT,
		/* 92 */ YY_NOT_ACCEPT
	};
	private int yy_cmap[] = unpackFromString(1,65538,
"45:9,1,3,45:2,2,45:18,1,45:7,39,40,45:2,44,45:3,35,34:2,37,38:3,36:3,43,45:" +
"5,41,20,45:2,29,45,17,45:3,16,45:2,19,27,25,45:3,24,30,45:2,31,45:7,42,45,1" +
"2,18,26,32,11,45,23,8,33,45,15,9,4,6,5,21,45,13,45,7,22,28,14,45,10,45:6541" +
"4,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,93,
"0,1,2,3,4,1:6,5,1:19,6,7,1:4,8,1:5,9,10,11,12,13,14,15,16,17,18,19,20,21,22" +
",23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47" +
",48,49,50,51,52,53,54,55,56,57,58")[0];

	private int yy_nxt[][] = unpackFromString(59,46,
"1,2,3,38,4,39:3,44,39,46,39:3,48,39,50,52,39,54,56,39:3,58,60,39,62,39,64,6" +
"6,90,89,39:6,5,6,7,8,9,10,39,-1:47,2,-1:47,38,-1:47,37,-1:27,87,-1:46,40:5," +
"-1:41,41:5,-1:41,42:5,-1:13,76,-1:51,78,-1:67,11,40:4,-1:41,88:5,-1:18,43,-" +
"1:22,45,-1:22,79,-1:45,47,-1:40,12,-1:51,49,-1:9,51,-1:29,13,-1:2,14,-1:47," +
"53,-1,55,-1:50,15,-1:32,57,-1:6,59,-1:66,16,-1:33,61,63,-1:29,17,-1:50,65,6" +
"7,-1:9,68,-1:33,18,-1:2,19,-1:58,69,-1:32,20,-1:37,70,-1:63,21,-1:33,71,-1:" +
"55,22,-1:32,72,-1:13,73,-1:30,23,-1:44,24,-1:46,25,-1:66,26,-1:43,27,-1:41," +
"28,-1:34,29,-1:66,30,-1:17,80,-1:48,81,-1:72,31,41:2,31:2,-1:20,82,-1:47,91" +
",-1:64,32,-1,42,32,42,-1:15,92,-1:46,84,-1:70,33:5,-1:17,34,-1:45,35,-1:45," +
"36,-1:41,77,-1:73,83:5,-1:12,75,-1:51,74,-1:43,85,-1:45,86,-1:36");

	public java.lang.Integer yylex ()
		throws java.io.IOException {
		int yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			if (yy_initial && yy_at_bol) yy_lookahead = YY_BOL;
			else yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			if (YY_EOF == yy_lookahead && true == yy_initial) {
				return null;
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_move_end();
					}
					yy_to_mark();
					switch (yy_last_accept_state) {
					case 1:
						
					case -2:
						break;
					case 2:
						{ /* Just ignore */    }
					case -3:
						break;
					case 3:
						{ /* Just ignore */    }
					case -4:
						break;
					case 4:
						{ System.out.println(" lexer error: " +yytext() ); }
					case -5:
						break;
					case 5:
						{output();return operator(Parser.LEFTP);}
					case -6:
						break;
					case 6:
						{output();return operator(Parser.RIGHTP);}
					case -7:
						break;
					case 7:
						{output();return operator(Parser.AT);}
					case -8:
						break;
					case 8:
						{output();return operator(Parser.TO);}
					case -9:
						break;
					case 9:
						{output();return operator(Parser.DIV);}
					case -10:
						break;
					case 10:
						{return operator(Parser.COMMA);}
					case -11:
						break;
					case 11:
						{output();return Date(Parser.HOUR,getContent(yytext(),1));}
					case -12:
						break;
					case 12:
						{output();return keyword(Parser.JAN);}
					case -13:
						break;
					case 13:
						{output();return keyword(Parser.JUN);}
					case -14:
						break;
					case 14:
						{output();return keyword(Parser.JUL);}
					case -15:
						break;
					case 15:
						{output();return keyword(Parser.FEB);}
					case -16:
						break;
					case 16:
						{output();return keyword(Parser.FRI);}
					case -17:
						break;
					case 17:
						{output();return keyword(Parser.MON);}
					case -18:
						break;
					case 18:
						{output();return keyword(Parser.MAY);}
					case -19:
						break;
					case 19:
						{output();return keyword(Parser.MAR);}
					case -20:
						break;
					case 20:
						{output();return keyword(Parser.APR);}
					case -21:
						break;
					case 21:
						{output();return keyword(Parser.AUG);}
					case -22:
						break;
					case 22:
						{output();return keyword(Parser.SEP);}
					case -23:
						break;
					case 23:
						{output();return keyword(Parser.SAT);}
					case -24:
						break;
					case 24:
						{output();return keyword(Parser.SUN);}
					case -25:
						break;
					case 25:
						{output();return keyword(Parser.OCT);}
					case -26:
						break;
					case 26:
						{output();return keyword(Parser.NOV);}
					case -27:
						break;
					case 27:
						{output();return keyword(Parser.DEC);}
					case -28:
						break;
					case 28:
						{output();return keyword(Parser.THU);}
					case -29:
						break;
					case 29:
						{output();return keyword(Parser.TUE);}
					case -30:
						break;
					case 30:
						{output();return keyword(Parser.WED);}
					case -31:
						break;
					case 31:
						{output();return Date(Parser.MINUTE,getContent(yytext(),3));}
					case -32:
						break;
					case 32:
						{output();return Date(Parser.DAYOFMONTH,getContent(yytext(),3));}
					case -33:
						break;
					case 33:
						{output();return Date(Parser.YEAR,getContent(yytext(),1));}
					case -34:
						break;
					case 34:
						{output();return keyword(Parser.YEARLY);}
					case -35:
						break;
					case 35:
						{output();return keyword(Parser.WEEKLY);}
					case -36:
						break;
					case 36:
						{output();return keyword(Parser.MONTHLY);}
					case -37:
						break;
					case 38:
						{ /* Just ignore */    }
					case -38:
						break;
					case 39:
						{ System.out.println(" lexer error: " +yytext() ); }
					case -39:
						break;
					case 40:
						{output();return Date(Parser.HOUR,getContent(yytext(),1));}
					case -40:
						break;
					case 41:
						{output();return Date(Parser.MINUTE,getContent(yytext(),3));}
					case -41:
						break;
					case 42:
						{output();return Date(Parser.DAYOFMONTH,getContent(yytext(),3));}
					case -42:
						break;
					case 44:
						{ System.out.println(" lexer error: " +yytext() ); }
					case -43:
						break;
					case 46:
						{ System.out.println(" lexer error: " +yytext() ); }
					case -44:
						break;
					case 48:
						{ System.out.println(" lexer error: " +yytext() ); }
					case -45:
						break;
					case 50:
						{ System.out.println(" lexer error: " +yytext() ); }
					case -46:
						break;
					case 52:
						{ System.out.println(" lexer error: " +yytext() ); }
					case -47:
						break;
					case 54:
						{ System.out.println(" lexer error: " +yytext() ); }
					case -48:
						break;
					case 56:
						{ System.out.println(" lexer error: " +yytext() ); }
					case -49:
						break;
					case 58:
						{ System.out.println(" lexer error: " +yytext() ); }
					case -50:
						break;
					case 60:
						{ System.out.println(" lexer error: " +yytext() ); }
					case -51:
						break;
					case 62:
						{ System.out.println(" lexer error: " +yytext() ); }
					case -52:
						break;
					case 64:
						{ System.out.println(" lexer error: " +yytext() ); }
					case -53:
						break;
					case 66:
						{ System.out.println(" lexer error: " +yytext() ); }
					case -54:
						break;
					case 89:
						{ System.out.println(" lexer error: " +yytext() ); }
					case -55:
						break;
					case 90:
						{ System.out.println(" lexer error: " +yytext() ); }
					case -56:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
						yy_mark_end();
					}
				}
			}
		}
	}
}