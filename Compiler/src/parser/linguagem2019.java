/* Generated By:JavaCC: Do not edit this line. linguagem2019.java */
package parser;

public class linguagem2019 implements linguagem2019Constants {
    final static String Version = "linguagem2019 Compiler - Version 1.0 - 2019";
    static private int[] jj_la1_0;

    static public String im(int x) {
        int k;
        String s;
        s = tokenImage[x];
        k = s.lastIndexOf("\u005c"");
        try {
            s = s.substring(1, k);
        } catch (StringIndexOutOfBoundsException e) {

        }
        return s;
    }

    static {
        jj_la1_init_0();
    }

    final private int[] jj_la1 = new int[2];
    /**
     * Generated Token Manager.
     */
    public linguagem2019TokenManager token_source;
    /**
     * Current token.
     */
    public Token token;
    /**
     * Next token.
     */
    public Token jj_nt;
    SimpleCharStream jj_input_stream;
    private int jj_ntk;
    private int jj_gen;
    private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
    private int[] jj_expentry;
    private int jj_kind = -1;
    private int trace_indent = 0;
    private boolean trace_enabled = true;

    /**
     * Constructor with InputStream.
     */
    public linguagem2019(java.io.InputStream stream) {
        this(stream, null);
    }

    /**
     * Constructor with InputStream and supplied encoding
     */
    public linguagem2019(java.io.InputStream stream, String encoding) {
        try {
            jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1);
        } catch (java.io.UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        token_source = new linguagem2019TokenManager(jj_input_stream);
        token = new Token();
        jj_ntk = -1;
        jj_gen = 0;
        for (int i = 0; i < 2; i++) jj_la1[i] = -1;
    }

    /**
     * Constructor.
     */
    public linguagem2019(java.io.Reader stream) {
        jj_input_stream = new SimpleCharStream(stream, 1, 1);
        token_source = new linguagem2019TokenManager(jj_input_stream);
        token = new Token();
        jj_ntk = -1;
        jj_gen = 0;
        for (int i = 0; i < 2; i++) jj_la1[i] = -1;
    }

    /**
     * Constructor with generated Token Manager.
     */
    public linguagem2019(linguagem2019TokenManager tm) {
        token_source = tm;
        token = new Token();
        jj_ntk = -1;
        jj_gen = 0;
        for (int i = 0; i < 2; i++) jj_la1[i] = -1;
    }

    public static void main(String[] args) throws ParseException {
        String filename = "";
        linguagem2019 parser;
        int i;
        System.out.println(Version);
        filename = args[args.length-1];
        try {
            parser = new linguagem2019(new java.io.FileInputStream(filename));
        } catch (java.io.FileNotFoundException e) {
            System.out.println("File not found");
            return;
        }
        if (parser.token_source.foundLexError() != 0)
            System.out.println(parser.token_source.foundLexError() + " Lexical error found");
        else
            System.out.println("Lexical analysis finished successfully");
    }

    private static void jj_la1_init_0() {
        jj_la1_0 = new int[]{0x13ff000, 0x13ff000,};
    }

    final public void Start() throws ParseException {
        trace_call("Start");
        try {
            label_1:
            while (true) {
                switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
                    case RESERVEDWORD:
                    case IDENTIFIER:
                    case ARITHMETIC:
                    case RELACIONAL:
                    case LOGIC:
                    case INTEGER:
                    case REAL:
                    case STRING:
                    case BOOLEAN:
                    case TYPE:
                    case SPECIALSYMBOL:
                        break;
                    default:
                        jj_la1[0] = jj_gen;
                        break label_1;
                }
                switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
                    case RESERVEDWORD:
                        jj_consume_token(RESERVEDWORD);
                        break;
                    case ARITHMETIC:
                        jj_consume_token(ARITHMETIC);
                        break;
                    case RELACIONAL:
                        jj_consume_token(RELACIONAL);
                        break;
                    case IDENTIFIER:
                        jj_consume_token(IDENTIFIER);
                        break;
                    case LOGIC:
                        jj_consume_token(LOGIC);
                        break;
                    case INTEGER:
                        jj_consume_token(INTEGER);
                        break;
                    case REAL:
                        jj_consume_token(REAL);
                        break;
                    case STRING:
                        jj_consume_token(STRING);
                        break;
                    case BOOLEAN:
                        jj_consume_token(BOOLEAN);
                        break;
                    case TYPE:
                        jj_consume_token(TYPE);
                        break;
                    case SPECIALSYMBOL:
                        jj_consume_token(SPECIALSYMBOL);
                        break;
                    default:
                        jj_la1[1] = jj_gen;
                        jj_consume_token(-1);
                        throw new ParseException();
                }
            }
            jj_consume_token(0);
        } finally {
            trace_return("Start");
        }
    }

    /**
     * Reinitialise.
     */
    public void ReInit(java.io.InputStream stream) {
        ReInit(stream, null);
    }

    /**
     * Reinitialise.
     */
    public void ReInit(java.io.InputStream stream, String encoding) {
        try {
            jj_input_stream.ReInit(stream, encoding, 1, 1);
        } catch (java.io.UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        token_source.ReInit(jj_input_stream);
        token = new Token();
        jj_ntk = -1;
        jj_gen = 0;
        for (int i = 0; i < 2; i++) jj_la1[i] = -1;
    }

    /**
     * Reinitialise.
     */
    public void ReInit(java.io.Reader stream) {
        jj_input_stream.ReInit(stream, 1, 1);
        token_source.ReInit(jj_input_stream);
        token = new Token();
        jj_ntk = -1;
        jj_gen = 0;
        for (int i = 0; i < 2; i++) jj_la1[i] = -1;
    }

    /**
     * Reinitialise.
     */
    public void ReInit(linguagem2019TokenManager tm) {
        token_source = tm;
        token = new Token();
        jj_ntk = -1;
        jj_gen = 0;
        for (int i = 0; i < 2; i++) jj_la1[i] = -1;
    }

    private Token jj_consume_token(int kind) throws ParseException {
        Token oldToken;
        if ((oldToken = token).next != null) token = token.next;
        else token = token.next = token_source.getNextToken();
        jj_ntk = -1;
        if (token.kind == kind) {
            jj_gen++;
            trace_token(token, "");
            return token;
        }
        token = oldToken;
        jj_kind = kind;
        throw generateParseException();
    }

    /**
     * Get the next Token.
     */
    final public Token getNextToken() {
        if (token.next != null) token = token.next;
        else token = token.next = token_source.getNextToken();
        jj_ntk = -1;
        jj_gen++;
        trace_token(token, " (in getNextToken)");
        return token;
    }

    /**
     * Get the specific Token.
     */
    final public Token getToken(int index) {
        Token t = token;
        for (int i = 0; i < index; i++) {
            if (t.next != null) t = t.next;
            else t = t.next = token_source.getNextToken();
        }
        return t;
    }

    private int jj_ntk() {
        if ((jj_nt = token.next) == null)
            return (jj_ntk = (token.next = token_source.getNextToken()).kind);
        else
            return (jj_ntk = jj_nt.kind);
    }

    /**
     * Generate ParseException.
     */
    public ParseException generateParseException() {
        jj_expentries.clear();
        boolean[] la1tokens = new boolean[27];
        if (jj_kind >= 0) {
            la1tokens[jj_kind] = true;
            jj_kind = -1;
        }
        for (int i = 0; i < 2; i++) {
            if (jj_la1[i] == jj_gen) {
                for (int j = 0; j < 32; j++) {
                    if ((jj_la1_0[i] & (1 << j)) != 0) {
                        la1tokens[j] = true;
                    }
                }
            }
        }
        for (int i = 0; i < 27; i++) {
            if (la1tokens[i]) {
                jj_expentry = new int[1];
                jj_expentry[0] = i;
                jj_expentries.add(jj_expentry);
            }
        }
        int[][] exptokseq = new int[jj_expentries.size()][];
        for (int i = 0; i < jj_expentries.size(); i++) {
            exptokseq[i] = jj_expentries.get(i);
        }
        return new ParseException(token, exptokseq, tokenImage);
    }

    /**
     * Enable tracing.
     */
    final public void enable_tracing() {
        trace_enabled = true;
    }

    /**
     * Disable tracing.
     */
    final public void disable_tracing() {
        trace_enabled = false;
    }

    private void trace_call(String s) {
        if (trace_enabled) {
            for (int i = 0; i < trace_indent; i++) {
                System.out.print(" ");
            }
            System.out.println("Call:   " + s);
        }
        trace_indent = trace_indent + 2;
    }

    private void trace_return(String s) {
        trace_indent = trace_indent - 2;
        if (trace_enabled) {
            for (int i = 0; i < trace_indent; i++) {
                System.out.print(" ");
            }
            System.out.println("Return: " + s);
        }
    }

    private void trace_token(Token t, String where) {
        if (trace_enabled) {
            for (int i = 0; i < trace_indent; i++) {
                System.out.print(" ");
            }
            System.out.print("Consumed token: <" + tokenImage[t.kind]);
            if (t.kind != 0 && !tokenImage[t.kind].equals("\"" + t.image + "\"")) {
                System.out.print(": \"" + t.image + "\"");
            }
            System.out.println(" at line " + t.beginLine + " column " + t.beginColumn + ">" + where);
        }
    }

    private void trace_scan(Token t1, int t2) {
        if (trace_enabled) {
            for (int i = 0; i < trace_indent; i++) {
                System.out.print(" ");
            }
            System.out.print("Visited token: <" + tokenImage[t1.kind]);
            if (t1.kind != 0 && !tokenImage[t1.kind].equals("\"" + t1.image + "\"")) {
                System.out.print(": \"" + t1.image + "\"");
            }
            System.out.println(" at line " + t1.beginLine + " column " + t1.beginColumn + ">; Expected token: <" + tokenImage[t2] + ">");
    }
  }

}