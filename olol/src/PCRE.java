/* Generated By:JJTree&JavaCC: Do not edit this line. PCRE.java */
public class PCRE/*@bgen(jjtree)*/implements PCRETreeConstants, PCREConstants {/*@bgen(jjtree)*/
  protected JJTPCREState jjtree = new JJTPCREState();public static void main( String[] args )
throws ParseException, TokenMgrError {
  System.out.println("oi");
while(true)
{
  PCRE parser = new PCRE( System.in ) ;

SimpleNode val = parser.Start() ;
System.out.println("Val:"+val.toString());
val.dump("");
ToNFA oi = new ToNFA(val);
}

}

  final public SimpleNode Start() throws ParseException {
                     /*@bgen(jjtree) Start */
  SimpleNode jjtn000 = new SimpleNode(JJTSTART);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      Expression();
      jj_consume_token(LF);
                         jjtree.closeNodeScope(jjtn000, true);
                         jjtc000 = false;
                        {if (true) return jjtn000;}
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

  final public void Expression() throws ParseException {
 /*@bgen(jjtree) Expression */
 SimpleNode jjtn000 = new SimpleNode(JJTEXPRESSION);
 boolean jjtc000 = true;
 jjtree.openNodeScope(jjtn000);Token t1, t2;
    try {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case CARET:
        t1 = jj_consume_token(CARET);
                jjtn000.jjtSetValue (t1.image);
        break;
      default:
        jj_la1[0] = jj_gen;
        ;
      }
      label_1:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case DOT:
        case DEC:
        case NOTDEC:
        case HWHITES:
        case NHWHITES:
        case WHITES:
        case NWHITES:
        case VWHITES:
        case NVWHITES:
        case WORD:
        case NWORD:
        case OPEN_ROUND_BRACKET:
        case OPEN_SQ_BRACKET:
        case ALTERNATE:
        case WORD_BOUND:
        case NOT_WORD_BOUND:
        case SLASH_CA_A:
        case SLASH_CA_Z:
        case SLASH_Z:
        case SLASH_CA_G:
        case SLASH:
        case SLASH_CA_C:
        case NOT_NEWLINE:
        case NEWLINE_SEQ:
        case CHARACTER:
          ;
          break;
        default:
          jj_la1[1] = jj_gen;
          break label_1;
        }
        MainExpression();
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case DOLLAR:
        t2 = jj_consume_token(DOLLAR);
                 jjtn000.jjtSetValue (t2.image);
        break;
      default:
        jj_la1[2] = jj_gen;
        ;
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

  final public void MainExpression() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ALTERNATE:
      ORexp();
      break;
    case SLASH:
    case CHARACTER:
      Chars();
      break;
    case OPEN_ROUND_BRACKET:
      ExpPRT();
      break;
    case DOT:
    case DEC:
    case NOTDEC:
    case HWHITES:
    case NHWHITES:
    case WHITES:
    case NWHITES:
    case VWHITES:
    case NVWHITES:
    case WORD:
    case NWORD:
    case SLASH_CA_C:
    case NOT_NEWLINE:
    case NEWLINE_SEQ:
      CharTypes();
      break;
    case WORD_BOUND:
    case NOT_WORD_BOUND:
    case SLASH_CA_A:
    case SLASH_CA_Z:
    case SLASH_Z:
    case SLASH_CA_G:
      AnchorAndSimpleAssertions();
      break;
    case OPEN_SQ_BRACKET:
      Range();
      break;
    default:
      jj_la1[3] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case AST:
    case PLUS:
    case INTERROGATION:
    case OPEN_BRACKET:
      Quantifier();
      break;
    default:
      jj_la1[4] = jj_gen;
      ;
    }
  }

  final public void ExpPRT() throws ParseException {
 /*@bgen(jjtree) PRT */
  SimpleNode jjtn000 = new SimpleNode(JJTPRT);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      jj_consume_token(OPEN_ROUND_BRACKET);
      Expression();
                                       SimpleNode jjtn001 = new SimpleNode(JJTCLOSE);
                                       boolean jjtc001 = true;
                                       jjtree.openNodeScope(jjtn001);
      try {
        jj_consume_token(CLOSE_ROUND_BRACKET);
      } finally {
                                       if (jjtc001) {
                                         jjtree.closeNodeScope(jjtn001, true);
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

  final public void ORexp() throws ParseException {
    jj_consume_token(ALTERNATE);
                 SimpleNode jjtn001 = new SimpleNode(JJTOR);
                 boolean jjtc001 = true;
                 jjtree.openNodeScope(jjtn001);
    try {
      Expression();
    } catch (Throwable jjte001) {
                 if (jjtc001) {
                   jjtree.clearNodeScope(jjtn001);
                   jjtc001 = false;
                 } else {
                   jjtree.popNode();
                 }
                 if (jjte001 instanceof RuntimeException) {
                   {if (true) throw (RuntimeException)jjte001;}
                 }
                 if (jjte001 instanceof ParseException) {
                   {if (true) throw (ParseException)jjte001;}
                 }
                 {if (true) throw (Error)jjte001;}
    } finally {
                 if (jjtc001) {
                   jjtree.closeNodeScope(jjtn001, true);
                 }
    }
  }

  final public void Range() throws ParseException {
 /*@bgen(jjtree) Range */
 SimpleNode jjtn000 = new SimpleNode(JJTRANGE);
 boolean jjtc000 = true;
 jjtree.openNodeScope(jjtn000);Token t1,t2,t3,t4,t5,t6;
    try {
      jj_consume_token(OPEN_SQ_BRACKET);
      label_2:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case CARET:
          ;
          break;
        default:
          jj_la1[5] = jj_gen;
          break label_2;
        }
        t1 = jj_consume_token(CARET);
      }
      label_3:
      while (true) {
        if (jj_2_1(2)) {
          t2 = jj_consume_token(CHARACTER);
          t3 = jj_consume_token(RANGE);
          t4 = jj_consume_token(CHARACTER);
        } else {
          switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
          case NUMBER:
            t2 = jj_consume_token(NUMBER);
            t3 = jj_consume_token(RANGE);
            t4 = jj_consume_token(NUMBER);
            break;
          case SLASH:
          case CHARACTER:
            Chars();
            break;
          case DOT:
          case DEC:
          case NOTDEC:
          case HWHITES:
          case NHWHITES:
          case WHITES:
          case NWHITES:
          case VWHITES:
          case NVWHITES:
          case WORD:
          case NWORD:
          case SLASH_CA_C:
          case NOT_NEWLINE:
          case NEWLINE_SEQ:
            CharTypes();
            break;
          default:
            jj_la1[6] = jj_gen;
            jj_consume_token(-1);
            throw new ParseException();
          }
        }
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case DOT:
        case DEC:
        case NOTDEC:
        case HWHITES:
        case NHWHITES:
        case WHITES:
        case NWHITES:
        case VWHITES:
        case NVWHITES:
        case WORD:
        case NWORD:
        case SLASH:
        case SLASH_CA_C:
        case NOT_NEWLINE:
        case NEWLINE_SEQ:
        case NUMBER:
        case CHARACTER:
          ;
          break;
        default:
          jj_la1[7] = jj_gen;
          break label_3;
        }
      }
      jj_consume_token(CLOSE_SQ_BRACKET);
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

  final public void Chars() throws ParseException {
 /*@bgen(jjtree) Chars */
 SimpleNode jjtn000 = new SimpleNode(JJTCHARS);
 boolean jjtc000 = true;
 jjtree.openNodeScope(jjtn000);Token t1,t2;
    try {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case CHARACTER:
        t1 = jj_consume_token(CHARACTER);
                      jjtree.closeNodeScope(jjtn000, true);
                      jjtc000 = false;
                     jjtn000.jjtSetValue (t1.image);
        break;
      case SLASH:
        t1 = jj_consume_token(SLASH);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case SLASH:
          t2 = jj_consume_token(SLASH);
          break;
        case CARET:
          t2 = jj_consume_token(CARET);
          break;
        case DOLLAR:
          t2 = jj_consume_token(DOLLAR);
          break;
        case DOT:
          t2 = jj_consume_token(DOT);
          break;
        case OPEN_SQ_BRACKET:
          t2 = jj_consume_token(OPEN_SQ_BRACKET);
          break;
        case ALTERNATE:
          t2 = jj_consume_token(ALTERNATE);
          break;
        case CLOSE_SQ_BRACKET:
          t2 = jj_consume_token(CLOSE_SQ_BRACKET);
          break;
        case OPEN_ROUND_BRACKET:
          t2 = jj_consume_token(OPEN_ROUND_BRACKET);
          break;
        case CLOSE_ROUND_BRACKET:
          t2 = jj_consume_token(CLOSE_ROUND_BRACKET);
          break;
        case INTERROGATION:
          t2 = jj_consume_token(INTERROGATION);
          break;
        case AST:
          t2 = jj_consume_token(AST);
          break;
        case PLUS:
          t2 = jj_consume_token(PLUS);
          break;
        default:
          jj_la1[8] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
  jjtree.closeNodeScope(jjtn000, true);
  jjtc000 = false;
 jjtn000.jjtSetValue (t2.image);
        break;
      default:
        jj_la1[9] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    } finally {
    if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
  }

  final public void CharTypes() throws ParseException {
 /*@bgen(jjtree) CharTypes */
 SimpleNode jjtn000 = new SimpleNode(JJTCHARTYPES);
 boolean jjtc000 = true;
 jjtree.openNodeScope(jjtn000);Token t;
    try {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case DOT:
        t = jj_consume_token(DOT);
        break;
      case SLASH_CA_C:
        t = jj_consume_token(SLASH_CA_C);
        break;
      case DEC:
        t = jj_consume_token(DEC);
        break;
      case NOTDEC:
        t = jj_consume_token(NOTDEC);
        break;
      case HWHITES:
        t = jj_consume_token(HWHITES);
        break;
      case NHWHITES:
        t = jj_consume_token(NHWHITES);
        break;
      case NOT_NEWLINE:
        t = jj_consume_token(NOT_NEWLINE);
        break;
      case NEWLINE_SEQ:
        t = jj_consume_token(NEWLINE_SEQ);
        break;
      case WHITES:
        t = jj_consume_token(WHITES);
        break;
      case NWHITES:
        t = jj_consume_token(NWHITES);
        break;
      case VWHITES:
        t = jj_consume_token(VWHITES);
        break;
      case NVWHITES:
        t = jj_consume_token(NVWHITES);
        break;
      case WORD:
        t = jj_consume_token(WORD);
        break;
      case NWORD:
        t = jj_consume_token(NWORD);
                                                                                                                                                                                                                                            jjtree.closeNodeScope(jjtn000, true);
                                                                                                                                                                                                                                            jjtc000 = false;
                                                                                                                                                                                                                                           jjtn000.jjtSetValue (t.image);
        break;
      default:
        jj_la1[10] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    } finally {
  if (jjtc000) {
    jjtree.closeNodeScope(jjtn000, true);
  }
    }
  }

  final public void AnchorAndSimpleAssertions() throws ParseException {
 /*@bgen(jjtree) AnchorAndSimpleAssertions */
 SimpleNode jjtn000 = new SimpleNode(JJTANCHORANDSIMPLEASSERTIONS);
 boolean jjtc000 = true;
 jjtree.openNodeScope(jjtn000);Token t;
    try {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case WORD_BOUND:
        t = jj_consume_token(WORD_BOUND);
        break;
      case NOT_WORD_BOUND:
        t = jj_consume_token(NOT_WORD_BOUND);
        break;
      case SLASH_CA_A:
        t = jj_consume_token(SLASH_CA_A);
        break;
      case SLASH_CA_Z:
        t = jj_consume_token(SLASH_CA_Z);
        break;
      case SLASH_Z:
        t = jj_consume_token(SLASH_Z);
        break;
      case SLASH_CA_G:
        t = jj_consume_token(SLASH_CA_G);
                                                                                                                               jjtree.closeNodeScope(jjtn000, true);
                                                                                                                               jjtc000 = false;
                                                                                                                              jjtn000.jjtSetValue (t.image);
        break;
      default:
        jj_la1[11] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    } finally {
    if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
  }

  final public void Quantifier() throws ParseException {
 /*@bgen(jjtree) Quantifier */
 SimpleNode jjtn000 = new SimpleNode(JJTQUANTIFIER);
 boolean jjtc000 = true;
 jjtree.openNodeScope(jjtn000);Token t1,t2,t3,t4,t5;
    try {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case AST:
      case PLUS:
      case INTERROGATION:
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case AST:
          t1 = jj_consume_token(AST);
          break;
        case INTERROGATION:
          t1 = jj_consume_token(INTERROGATION);
          break;
        case PLUS:
          t1 = jj_consume_token(PLUS);
          break;
        default:
          jj_la1[12] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
          jjtree.closeNodeScope(jjtn000, true);
          jjtc000 = false;
         jjtn000.jjtSetValue (t1.image);
        break;
      case OPEN_BRACKET:
        //SETVALUE NOT ENOUGH; 
          t1 = jj_consume_token(OPEN_BRACKET);
        t2 = jj_consume_token(NUMBER);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case CLOSE_BRACKET:
          t3 = jj_consume_token(CLOSE_BRACKET);
          break;
        case COMMA:
          t3 = jj_consume_token(COMMA);
          switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
          case CLOSE_BRACKET:
            t4 = jj_consume_token(CLOSE_BRACKET);
            break;
          case NUMBER:
            t4 = jj_consume_token(NUMBER);
            t5 = jj_consume_token(CLOSE_BRACKET);
            break;
          default:
            jj_la1[13] = jj_gen;
            jj_consume_token(-1);
            throw new ParseException();
          }
          break;
        default:
          jj_la1[14] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
        break;
      default:
        jj_la1[15] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    } finally {
    if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
  }

  private boolean jj_2_1(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_1(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(0, xla); }
  }

  private boolean jj_3_1() {
    if (jj_scan_token(CHARACTER)) return true;
    if (jj_scan_token(RANGE)) return true;
    return false;
  }

  /** Generated Token Manager. */
  public PCRETokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private Token jj_scanpos, jj_lastpos;
  private int jj_la;
  private int jj_gen;
  final private int[] jj_la1 = new int[16];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x0,0xa04fec50,0x0,0xa04fec50,0x4300100,0x0,0xfec50,0xfec50,0xe0f00110,0x0,0xfec50,0x0,0x300100,0x10000000,0x18000000,0x4300100,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x4,0x2388eb,0x10,0x2388eb,0x0,0x4,0x338800,0x338800,0x814,0x200800,0x38000,0xeb,0x0,0x100000,0x0,0x0,};
   }
  final private JJCalls[] jj_2_rtns = new JJCalls[1];
  private boolean jj_rescan = false;
  private int jj_gc = 0;

  /** Constructor with InputStream. */
  public PCRE(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public PCRE(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new PCRETokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 16; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jjtree.reset();
    jj_gen = 0;
    for (int i = 0; i < 16; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor. */
  public PCRE(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new PCRETokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 16; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jjtree.reset();
    jj_gen = 0;
    for (int i = 0; i < 16; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor with generated Token Manager. */
  public PCRE(PCRETokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 16; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(PCRETokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jjtree.reset();
    jj_gen = 0;
    for (int i = 0; i < 16; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  private Token jj_consume_token(int kind) throws ParseException {
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
  final private LookaheadSuccess jj_ls = new LookaheadSuccess();
  private boolean jj_scan_token(int kind) {
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
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;
  private int[] jj_lasttokens = new int[100];
  private int jj_endpos;

  private void jj_add_error_token(int kind, int pos) {
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
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[54];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 16; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 54; i++) {
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
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

  private void jj_rescan_token() {
    jj_rescan = true;
    for (int i = 0; i < 1; i++) {
    try {
      JJCalls p = jj_2_rtns[i];
      do {
        if (p.gen > jj_gen) {
          jj_la = p.arg; jj_lastpos = jj_scanpos = p.first;
          switch (i) {
            case 0: jj_3_1(); break;
          }
        }
        p = p.next;
      } while (p != null);
      } catch(LookaheadSuccess ls) { }
    }
    jj_rescan = false;
  }

  private void jj_save(int index, int xla) {
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
