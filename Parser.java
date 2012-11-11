/* Generated By:JJTree&JavaCC: Do not edit this line. Parser.java */
import java.util.ArrayList;
import java.util.List;

public class Parser/*@bgen(jjtree)*/implements ParserTreeConstants, ParserConstants {/*@bgen(jjtree)*/
  protected static JJTParserState jjtree = new JJTParserState();public static void main(String args[]) throws ParseException {
   Parser parser = new Parser (System.in);
   parser.prog();
   ASTprog root = (ASTprog)jjtree.rootNode();
   Object data = (Object)false;
   PrintVisitor print = new PrintVisitor();
   print.visit(root, data);
 }

  static final public String atom() throws ParseException {
  Token n; String l, h;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case INTEGER:
      l = integer();
                  {if (true) return l;}
      break;
    case SYMBOL:
      l = symbol();
                 {if (true) return l;}
      break;
    case LET:
      l = let();
              {if (true) return l;}
      break;
    case LAMBDA:
      l = lambda();
                 {if (true) return l;}
      break;
    case ADD:
      l = addition();
                   {if (true) return l;}
      break;
    case SUB:
      l = subtraction();
                      {if (true) return l;}
      break;
    case MUL:
      l = multiplication();
                         {if (true) return l;}
      break;
    case DIV:
      l = division();
                   {if (true) return l;}
      break;
    default:
      jj_la1[0] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  static final public String integer() throws ParseException {
 /*@bgen(jjtree) integer */
 ASTinteger jjtn000 = new ASTinteger(null, JJTINTEGER);
 boolean jjtc000 = true;
 jjtree.openNodeScope(jjtn000);Token n;
    try {
      n = jj_consume_token(INTEGER);
                       jjtn000.setNum(Integer.parseInt(n.image));
          jjtree.closeNodeScope(jjtn000, true);
          jjtc000 = false;
          {if (true) return "(num " + n.image+")";}
    } finally {
          if (jjtc000) {
            jjtree.closeNodeScope(jjtn000, true);
          }
    }
    throw new Error("Missing return statement in function");
  }

  static final public String symbol() throws ParseException {
 /*@bgen(jjtree) symbol */
 ASTsymbol jjtn000 = new ASTsymbol(null, JJTSYMBOL);
 boolean jjtc000 = true;
 jjtree.openNodeScope(jjtn000);Token n;
    try {
      n = jj_consume_token(SYMBOL);
                      jjtn000.setName(n.image);
          jjtree.closeNodeScope(jjtn000, true);
          jjtc000 = false;
          {if (true) return "(id '" + n.image+")";}
    } finally {
          if (jjtc000) {
            jjtree.closeNodeScope(jjtn000, true);
          }
    }
    throw new Error("Missing return statement in function");
  }

  static final public String addition() throws ParseException {
 /*@bgen(jjtree) Addition */
 ASTAddition jjtn000 = new ASTAddition(null, JJTADDITION);
 boolean jjtc000 = true;
 jjtree.openNodeScope(jjtn000);Token n;
    try {
      n = jj_consume_token(ADD);
          jjtree.closeNodeScope(jjtn000, true);
          jjtc000 = false;
         {if (true) return "add";}
    } finally {
          if (jjtc000) {
            jjtree.closeNodeScope(jjtn000, true);
          }
    }
    throw new Error("Missing return statement in function");
  }

  static final public String subtraction() throws ParseException {
 /*@bgen(jjtree) Subtraction */
 ASTSubtraction jjtn000 = new ASTSubtraction(null, JJTSUBTRACTION);
 boolean jjtc000 = true;
 jjtree.openNodeScope(jjtn000);Token n;
    try {
      n = jj_consume_token(SUB);
          jjtree.closeNodeScope(jjtn000, true);
          jjtc000 = false;
         {if (true) return "sub";}
    } finally {
          if (jjtc000) {
            jjtree.closeNodeScope(jjtn000, true);
          }
    }
    throw new Error("Missing return statement in function");
  }

  static final public String multiplication() throws ParseException {
 /*@bgen(jjtree) Multiplication */
 ASTMultiplication jjtn000 = new ASTMultiplication(null, JJTMULTIPLICATION);
 boolean jjtc000 = true;
 jjtree.openNodeScope(jjtn000);Token n;
    try {
      n = jj_consume_token(MUL);
          jjtree.closeNodeScope(jjtn000, true);
          jjtc000 = false;
         {if (true) return "mul";}
    } finally {
          if (jjtc000) {
            jjtree.closeNodeScope(jjtn000, true);
          }
    }
    throw new Error("Missing return statement in function");
  }

  static final public String division() throws ParseException {
 /*@bgen(jjtree) Division */
 ASTDivision jjtn000 = new ASTDivision(null, JJTDIVISION);
 boolean jjtc000 = true;
 jjtree.openNodeScope(jjtn000);Token n;
    try {
      n = jj_consume_token(DIV);
          jjtree.closeNodeScope(jjtn000, true);
          jjtc000 = false;
         {if (true) return "div";}
    } finally {
          if (jjtc000) {
            jjtree.closeNodeScope(jjtn000, true);
          }
    }
    throw new Error("Missing return statement in function");
  }

  static final public String list() throws ParseException {
 String s;
 String ret="";
  ret+="(";
    jj_consume_token(LPAR);
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case LPAR:
      case INTEGER:
      case LAMBDA:
      case LET:
      case ADD:
      case SUB:
      case MUL:
      case DIV:
      case SYMBOL:
        ;
        break;
      default:
        jj_la1[1] = jj_gen;
        break label_1;
      }
      if (jj_2_1(2)) {
        s = list();
                              ret += s;
      } else {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case INTEGER:
        case LAMBDA:
        case LET:
        case ADD:
        case SUB:
        case MUL:
        case DIV:
        case SYMBOL:
          s = atom();
                  ret += s;
          break;
        default:
          jj_la1[2] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
    }
    jj_consume_token(RPAR);
   ret+=")";
   {if (true) return ret;}
    throw new Error("Missing return statement in function");
  }

  static final public String let() throws ParseException {
 /*@bgen(jjtree) FunctionApplication */
 ASTFunctionApplication jjtn000 = new ASTFunctionApplication(null, JJTFUNCTIONAPPLICATION);
 boolean jjtc000 = true;
 jjtree.openNodeScope(jjtn000);String a, b, c; Token c1, c2; String s = ""; String t = "";
    try {
      jj_consume_token(LET);
      jj_consume_token(LPAR);
      jj_consume_token(LPAR);
      //A
        a = symbol();
                s += "(app(fun '"+a;
      if (jj_2_2(2)) {
        b = list();
                            t += b;
      } else {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case INTEGER:
        case LAMBDA:
        case LET:
        case ADD:
        case SUB:
        case MUL:
        case DIV:
        case SYMBOL:
          b = atom();
                 t += b;
          break;
        default:
          jj_la1[3] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
      jj_consume_token(RPAR);
      jj_consume_token(RPAR);
      //C
        c = list();
                s += c+"))";
   jjtree.closeNodeScope(jjtn000, true);
   jjtc000 = false;
   {if (true) return s + t;}
    } catch (Throwable jjte000) {
    if (jjtc000) {
      jjtree.clearNodeScope(jjtn000);
      jjtc000 = false;
    } else {
      jjtree.popNode();
    }
    if (jjte000 instanceof RuntimeException) {
      {if (true) throw (RuntimeException)jjte000;}
    }
    if (jjte000 instanceof ParseException) {
      {if (true) throw (ParseException)jjte000;}
    }
    {if (true) throw (Error)jjte000;}
    } finally {
    if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
    throw new Error("Missing return statement in function");
  }

  static final public String lambda() throws ParseException {
 /*@bgen(jjtree) Function */
 ASTFunction jjtn000 = new ASTFunction(null, JJTFUNCTION);
 boolean jjtc000 = true;
 jjtree.openNodeScope(jjtn000);String a, b;
    try {
      jj_consume_token(LAMBDA);
      jj_consume_token(LPAR);
      a = atom();
      jj_consume_token(RPAR);
      if (jj_2_3(2)) {
        b = list();
      } else {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case INTEGER:
        case LAMBDA:
        case LET:
        case ADD:
        case SUB:
        case MUL:
        case DIV:
        case SYMBOL:
          b = atom();
          break;
        default:
          jj_la1[4] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
     jjtree.closeNodeScope(jjtn000, true);
     jjtc000 = false;
    {if (true) return "(fun '"+a+b+")";}
    } catch (Throwable jjte000) {
   if (jjtc000) {
     jjtree.clearNodeScope(jjtn000);
     jjtc000 = false;
   } else {
     jjtree.popNode();
   }
   if (jjte000 instanceof RuntimeException) {
     {if (true) throw (RuntimeException)jjte000;}
   }
   if (jjte000 instanceof ParseException) {
     {if (true) throw (ParseException)jjte000;}
   }
   {if (true) throw (Error)jjte000;}
    } finally {
   if (jjtc000) {
     jjtree.closeNodeScope(jjtn000, true);
   }
    }
    throw new Error("Missing return statement in function");
  }

  static final public void prog() throws ParseException {
 /*@bgen(jjtree) prog */
 ASTprog jjtn000 = new ASTprog(null, JJTPROG);
 boolean jjtc000 = true;
 jjtree.openNodeScope(jjtn000);String s;
    try {
      label_2:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case LPAR:
        case INTEGER:
        case LAMBDA:
        case LET:
        case ADD:
        case SUB:
        case MUL:
        case DIV:
        case SYMBOL:
          ;
          break;
        default:
          jj_la1[5] = jj_gen;
          break label_2;
        }
        if (jj_2_4(2)) {
          s = list();
                                  System.out.println(s);
        } else {
          switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
          case INTEGER:
          case LAMBDA:
          case LET:
          case ADD:
          case SUB:
          case MUL:
          case DIV:
          case SYMBOL:
            s = atom();
                  System.out.println(s);
            break;
          default:
            jj_la1[6] = jj_gen;
            jj_consume_token(-1);
            throw new ParseException();
          }
        }
      }
    } catch (Throwable jjte000) {
   if (jjtc000) {
     jjtree.clearNodeScope(jjtn000);
     jjtc000 = false;
   } else {
     jjtree.popNode();
   }
   if (jjte000 instanceof RuntimeException) {
     {if (true) throw (RuntimeException)jjte000;}
   }
   if (jjte000 instanceof ParseException) {
     {if (true) throw (ParseException)jjte000;}
   }
   {if (true) throw (Error)jjte000;}
    } finally {
   if (jjtc000) {
     jjtree.closeNodeScope(jjtn000, true);
   }
    }
  }

  static private boolean jj_2_1(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_1(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(0, xla); }
  }

  static private boolean jj_2_2(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_2(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(1, xla); }
  }

  static private boolean jj_2_3(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_3(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(2, xla); }
  }

  static private boolean jj_2_4(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_4(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(3, xla); }
  }

  static private boolean jj_3R_15() {
    if (jj_scan_token(INTEGER)) return true;
    return false;
  }

  static private boolean jj_3R_5() {
    if (jj_3R_6()) return true;
    return false;
  }

  static private boolean jj_3R_4() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_1()) {
    jj_scanpos = xsp;
    if (jj_3R_5()) return true;
    }
    return false;
  }

  static private boolean jj_3_1() {
    if (jj_3R_3()) return true;
    return false;
  }

  static private boolean jj_3R_3() {
    if (jj_scan_token(LPAR)) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_4()) { jj_scanpos = xsp; break; }
    }
    if (jj_scan_token(RPAR)) return true;
    return false;
  }

  static private boolean jj_3R_14() {
    if (jj_3R_22()) return true;
    return false;
  }

  static private boolean jj_3_3() {
    if (jj_3R_3()) return true;
    return false;
  }

  static private boolean jj_3R_22() {
    if (jj_scan_token(DIV)) return true;
    return false;
  }

  static private boolean jj_3R_13() {
    if (jj_3R_21()) return true;
    return false;
  }

  static private boolean jj_3R_12() {
    if (jj_3R_20()) return true;
    return false;
  }

  static private boolean jj_3R_11() {
    if (jj_3R_19()) return true;
    return false;
  }

  static private boolean jj_3R_10() {
    if (jj_3R_18()) return true;
    return false;
  }

  static private boolean jj_3R_9() {
    if (jj_3R_17()) return true;
    return false;
  }

  static private boolean jj_3R_18() {
    if (jj_scan_token(LAMBDA)) return true;
    return false;
  }

  static private boolean jj_3R_8() {
    if (jj_3R_16()) return true;
    return false;
  }

  static private boolean jj_3R_6() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_7()) {
    jj_scanpos = xsp;
    if (jj_3R_8()) {
    jj_scanpos = xsp;
    if (jj_3R_9()) {
    jj_scanpos = xsp;
    if (jj_3R_10()) {
    jj_scanpos = xsp;
    if (jj_3R_11()) {
    jj_scanpos = xsp;
    if (jj_3R_12()) {
    jj_scanpos = xsp;
    if (jj_3R_13()) {
    jj_scanpos = xsp;
    if (jj_3R_14()) return true;
    }
    }
    }
    }
    }
    }
    }
    return false;
  }

  static private boolean jj_3R_7() {
    if (jj_3R_15()) return true;
    return false;
  }

  static private boolean jj_3R_21() {
    if (jj_scan_token(MUL)) return true;
    return false;
  }

  static private boolean jj_3R_20() {
    if (jj_scan_token(SUB)) return true;
    return false;
  }

  static private boolean jj_3_2() {
    if (jj_3R_3()) return true;
    return false;
  }

  static private boolean jj_3R_19() {
    if (jj_scan_token(ADD)) return true;
    return false;
  }

  static private boolean jj_3R_17() {
    if (jj_scan_token(LET)) return true;
    return false;
  }

  static private boolean jj_3R_16() {
    if (jj_scan_token(SYMBOL)) return true;
    return false;
  }

  static private boolean jj_3_4() {
    if (jj_3R_3()) return true;
    return false;
  }

  static private boolean jj_initialized_once = false;
  /** Generated Token Manager. */
  static public ParserTokenManager token_source;
  static SimpleCharStream jj_input_stream;
  /** Current token. */
  static public Token token;
  /** Next token. */
  static public Token jj_nt;
  static private int jj_ntk;
  static private Token jj_scanpos, jj_lastpos;
  static private int jj_la;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[7];
  static private int[] jj_la1_0;
  static {
      jj_la1_init_0();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x1fe00,0x1fe80,0x1fe00,0x1fe00,0x1fe00,0x1fe80,0x1fe00,};
   }
  static final private JJCalls[] jj_2_rtns = new JJCalls[4];
  static private boolean jj_rescan = false;
  static private int jj_gc = 0;

  /** Constructor with InputStream. */
  public Parser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public Parser(java.io.InputStream stream, String encoding) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser.  ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new ParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 7; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jjtree.reset();
    jj_gen = 0;
    for (int i = 0; i < 7; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor. */
  public Parser(java.io.Reader stream) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new ParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 7; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jjtree.reset();
    jj_gen = 0;
    for (int i = 0; i < 7; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor with generated Token Manager. */
  public Parser(ParserTokenManager tm) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 7; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(ParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jjtree.reset();
    jj_gen = 0;
    for (int i = 0; i < 7; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  static private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      if (++jj_gc > 100) {
        jj_gc = 0;
        for (int i = 0; i < jj_2_rtns.length; i++) {
          JJCalls c = jj_2_rtns[i];
          while (c != null) {
            if (c.gen < jj_gen) c.first = null;
            c = c.next;
          }
        }
      }
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }

  static private final class LookaheadSuccess extends java.lang.Error { }
  static final private LookaheadSuccess jj_ls = new LookaheadSuccess();
  static private boolean jj_scan_token(int kind) {
    if (jj_scanpos == jj_lastpos) {
      jj_la--;
      if (jj_scanpos.next == null) {
        jj_lastpos = jj_scanpos = jj_scanpos.next = token_source.getNextToken();
      } else {
        jj_lastpos = jj_scanpos = jj_scanpos.next;
      }
    } else {
      jj_scanpos = jj_scanpos.next;
    }
    if (jj_rescan) {
      int i = 0; Token tok = token;
      while (tok != null && tok != jj_scanpos) { i++; tok = tok.next; }
      if (tok != null) jj_add_error_token(kind, i);
    }
    if (jj_scanpos.kind != kind) return true;
    if (jj_la == 0 && jj_scanpos == jj_lastpos) throw jj_ls;
    return false;
  }


/** Get the next Token. */
  static final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  static final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  static private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  static private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  static private int[] jj_expentry;
  static private int jj_kind = -1;
  static private int[] jj_lasttokens = new int[100];
  static private int jj_endpos;

  static private void jj_add_error_token(int kind, int pos) {
    if (pos >= 100) return;
    if (pos == jj_endpos + 1) {
      jj_lasttokens[jj_endpos++] = kind;
    } else if (jj_endpos != 0) {
      jj_expentry = new int[jj_endpos];
      for (int i = 0; i < jj_endpos; i++) {
        jj_expentry[i] = jj_lasttokens[i];
      }
      jj_entries_loop: for (java.util.Iterator<?> it = jj_expentries.iterator(); it.hasNext();) {
        int[] oldentry = (int[])(it.next());
        if (oldentry.length == jj_expentry.length) {
          for (int i = 0; i < jj_expentry.length; i++) {
            if (oldentry[i] != jj_expentry[i]) {
              continue jj_entries_loop;
            }
          }
          jj_expentries.add(jj_expentry);
          break jj_entries_loop;
        }
      }
      if (pos != 0) jj_lasttokens[(jj_endpos = pos) - 1] = kind;
    }
  }

  /** Generate ParseException. */
  static public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[18];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 7; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 18; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    jj_endpos = 0;
    jj_rescan_token();
    jj_add_error_token(0, 0);
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  static final public void enable_tracing() {
  }

  /** Disable tracing. */
  static final public void disable_tracing() {
  }

  static private void jj_rescan_token() {
    jj_rescan = true;
    for (int i = 0; i < 4; i++) {
    try {
      JJCalls p = jj_2_rtns[i];
      do {
        if (p.gen > jj_gen) {
          jj_la = p.arg; jj_lastpos = jj_scanpos = p.first;
          switch (i) {
            case 0: jj_3_1(); break;
            case 1: jj_3_2(); break;
            case 2: jj_3_3(); break;
            case 3: jj_3_4(); break;
          }
        }
        p = p.next;
      } while (p != null);
      } catch(LookaheadSuccess ls) { }
    }
    jj_rescan = false;
  }

  static private void jj_save(int index, int xla) {
    JJCalls p = jj_2_rtns[index];
    while (p.gen > jj_gen) {
      if (p.next == null) { p = p.next = new JJCalls(); break; }
      p = p.next;
    }
    p.gen = jj_gen + xla - jj_la; p.first = token; p.arg = xla;
  }

  static final class JJCalls {
    int gen;
    Token first;
    int arg;
    JJCalls next;
  }

}
