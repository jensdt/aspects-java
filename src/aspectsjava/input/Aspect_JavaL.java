// $ANTLR 3.3 Nov 30, 2010 12:45:30 JavaL.g 2011-05-10 00:56:14

  package aspectsjava.input;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class Aspect_JavaL extends Lexer {
    public static final int EOF=-1;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int T__29=29;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int T__33=33;
    public static final int T__34=34;
    public static final int T__35=35;
    public static final int T__36=36;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int T__39=39;
    public static final int T__40=40;
    public static final int T__41=41;
    public static final int T__42=42;
    public static final int T__43=43;
    public static final int T__44=44;
    public static final int T__45=45;
    public static final int T__46=46;
    public static final int T__47=47;
    public static final int T__48=48;
    public static final int T__49=49;
    public static final int T__50=50;
    public static final int T__51=51;
    public static final int T__52=52;
    public static final int T__53=53;
    public static final int T__54=54;
    public static final int T__55=55;
    public static final int T__56=56;
    public static final int T__57=57;
    public static final int T__58=58;
    public static final int T__59=59;
    public static final int T__60=60;
    public static final int T__61=61;
    public static final int T__62=62;
    public static final int T__63=63;
    public static final int T__64=64;
    public static final int T__65=65;
    public static final int T__66=66;
    public static final int T__67=67;
    public static final int T__68=68;
    public static final int T__69=69;
    public static final int T__70=70;
    public static final int T__71=71;
    public static final int T__72=72;
    public static final int T__73=73;
    public static final int T__74=74;
    public static final int T__75=75;
    public static final int T__76=76;
    public static final int T__77=77;
    public static final int T__78=78;
    public static final int T__79=79;
    public static final int T__80=80;
    public static final int T__81=81;
    public static final int T__82=82;
    public static final int T__83=83;
    public static final int T__84=84;
    public static final int T__85=85;
    public static final int T__86=86;
    public static final int T__87=87;
    public static final int T__88=88;
    public static final int T__89=89;
    public static final int T__90=90;
    public static final int T__91=91;
    public static final int T__92=92;
    public static final int T__93=93;
    public static final int T__94=94;
    public static final int T__95=95;
    public static final int T__96=96;
    public static final int T__97=97;
    public static final int T__98=98;
    public static final int T__99=99;
    public static final int T__100=100;
    public static final int T__101=101;
    public static final int T__102=102;
    public static final int T__103=103;
    public static final int T__104=104;
    public static final int T__105=105;
    public static final int T__106=106;
    public static final int T__107=107;
    public static final int T__108=108;
    public static final int T__109=109;
    public static final int T__110=110;
    public static final int T__111=111;
    public static final int T__112=112;
    public static final int T__113=113;
    public static final int T__114=114;
    public static final int T__115=115;
    public static final int T__116=116;
    public static final int T__117=117;
    public static final int T__118=118;
    public static final int T__119=119;
    public static final int T__120=120;
    public static final int T__121=121;
    public static final int T__122=122;
    public static final int T__123=123;
    public static final int T__124=124;
    public static final int T__125=125;
    public static final int T__126=126;
    public static final int T__127=127;
    public static final int T__128=128;
    public static final int T__129=129;
    public static final int T__130=130;
    public static final int T__131=131;
    public static final int Identifier=4;
    public static final int ENUM=5;
    public static final int FloatingPointLiteral=6;
    public static final int CharacterLiteral=7;
    public static final int StringLiteral=8;
    public static final int HexLiteral=9;
    public static final int OctalLiteral=10;
    public static final int DecimalLiteral=11;
    public static final int ASSERT=12;
    public static final int HexDigit=13;
    public static final int IntegerTypeSuffix=14;
    public static final int Exponent=15;
    public static final int FloatTypeSuffix=16;
    public static final int EscapeSequence=17;
    public static final int UnicodeEscape=18;
    public static final int OctalEscape=19;
    public static final int Letter=20;
    public static final int JavaIDDigit=21;
    public static final int IdentifierWithWC=22;
    public static final int WS=23;
    public static final int COMMENT=24;
    public static final int LINE_COMMENT=25;
    public static final int Tokens=132;

      protected boolean enumIsKeyword = true;
      protected boolean assertIsKeyword = true;


    // delegates
    // delegators
    public AspectLexer gAspect;
    public AspectLexer gParent;

    public Aspect_JavaL() {;} 
    public Aspect_JavaL(CharStream input, AspectLexer gAspect) {
        this(input, new RecognizerSharedState(), gAspect);
    }
    public Aspect_JavaL(CharStream input, RecognizerSharedState state, AspectLexer gAspect) {
        super(input,state);

        this.gAspect = gAspect;
        gParent = gAspect;
    }
    public String getGrammarFileName() { return "JavaL.g"; }

    // $ANTLR start "HexLiteral"
    public final void mHexLiteral() throws RecognitionException {
        try {
            int _type = HexLiteral;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // JavaL.g:11:12: ( '0' ( 'x' | 'X' ) ( HexDigit )+ ( IntegerTypeSuffix )? )
            // JavaL.g:11:14: '0' ( 'x' | 'X' ) ( HexDigit )+ ( IntegerTypeSuffix )?
            {
            match('0'); 
            if ( input.LA(1)=='X'||input.LA(1)=='x' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // JavaL.g:11:28: ( HexDigit )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>='0' && LA1_0<='9')||(LA1_0>='A' && LA1_0<='F')||(LA1_0>='a' && LA1_0<='f')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // JavaL.g:11:28: HexDigit
            	    {
            	    mHexDigit(); 

            	    }
            	    break;

            	default :
            	    if ( cnt1 >= 1 ) break loop1;
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);

            // JavaL.g:11:38: ( IntegerTypeSuffix )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0=='L'||LA2_0=='l') ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // JavaL.g:11:38: IntegerTypeSuffix
                    {
                    mIntegerTypeSuffix(); 

                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "HexLiteral"

    // $ANTLR start "DecimalLiteral"
    public final void mDecimalLiteral() throws RecognitionException {
        try {
            int _type = DecimalLiteral;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // JavaL.g:13:16: ( ( '0' | '1' .. '9' ( '0' .. '9' )* ) ( IntegerTypeSuffix )? )
            // JavaL.g:13:18: ( '0' | '1' .. '9' ( '0' .. '9' )* ) ( IntegerTypeSuffix )?
            {
            // JavaL.g:13:18: ( '0' | '1' .. '9' ( '0' .. '9' )* )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0=='0') ) {
                alt4=1;
            }
            else if ( ((LA4_0>='1' && LA4_0<='9')) ) {
                alt4=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // JavaL.g:13:19: '0'
                    {
                    match('0'); 

                    }
                    break;
                case 2 :
                    // JavaL.g:13:25: '1' .. '9' ( '0' .. '9' )*
                    {
                    matchRange('1','9'); 
                    // JavaL.g:13:34: ( '0' .. '9' )*
                    loop3:
                    do {
                        int alt3=2;
                        int LA3_0 = input.LA(1);

                        if ( ((LA3_0>='0' && LA3_0<='9')) ) {
                            alt3=1;
                        }


                        switch (alt3) {
                    	case 1 :
                    	    // JavaL.g:13:34: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    break loop3;
                        }
                    } while (true);


                    }
                    break;

            }

            // JavaL.g:13:45: ( IntegerTypeSuffix )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0=='L'||LA5_0=='l') ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // JavaL.g:13:45: IntegerTypeSuffix
                    {
                    mIntegerTypeSuffix(); 

                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DecimalLiteral"

    // $ANTLR start "OctalLiteral"
    public final void mOctalLiteral() throws RecognitionException {
        try {
            int _type = OctalLiteral;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // JavaL.g:15:14: ( '0' ( '0' .. '7' )+ ( IntegerTypeSuffix )? )
            // JavaL.g:15:16: '0' ( '0' .. '7' )+ ( IntegerTypeSuffix )?
            {
            match('0'); 
            // JavaL.g:15:20: ( '0' .. '7' )+
            int cnt6=0;
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( ((LA6_0>='0' && LA6_0<='7')) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // JavaL.g:15:21: '0' .. '7'
            	    {
            	    matchRange('0','7'); 

            	    }
            	    break;

            	default :
            	    if ( cnt6 >= 1 ) break loop6;
                        EarlyExitException eee =
                            new EarlyExitException(6, input);
                        throw eee;
                }
                cnt6++;
            } while (true);

            // JavaL.g:15:32: ( IntegerTypeSuffix )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0=='L'||LA7_0=='l') ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // JavaL.g:15:32: IntegerTypeSuffix
                    {
                    mIntegerTypeSuffix(); 

                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "OctalLiteral"

    // $ANTLR start "HexDigit"
    public final void mHexDigit() throws RecognitionException {
        try {
            // JavaL.g:18:10: ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )
            // JavaL.g:18:12: ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )
            {
            if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='F')||(input.LA(1)>='a' && input.LA(1)<='f') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "HexDigit"

    // $ANTLR start "IntegerTypeSuffix"
    public final void mIntegerTypeSuffix() throws RecognitionException {
        try {
            // JavaL.g:21:19: ( ( 'l' | 'L' ) )
            // JavaL.g:21:21: ( 'l' | 'L' )
            {
            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "IntegerTypeSuffix"

    // $ANTLR start "FloatingPointLiteral"
    public final void mFloatingPointLiteral() throws RecognitionException {
        try {
            int _type = FloatingPointLiteral;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // JavaL.g:24:5: ( ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( Exponent )? ( FloatTypeSuffix )? | '.' ( '0' .. '9' )+ ( Exponent )? ( FloatTypeSuffix )? | ( '0' .. '9' )+ Exponent ( FloatTypeSuffix )? | ( '0' .. '9' )+ FloatTypeSuffix )
            int alt18=4;
            alt18 = dfa18.predict(input);
            switch (alt18) {
                case 1 :
                    // JavaL.g:24:9: ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( Exponent )? ( FloatTypeSuffix )?
                    {
                    // JavaL.g:24:9: ( '0' .. '9' )+
                    int cnt8=0;
                    loop8:
                    do {
                        int alt8=2;
                        int LA8_0 = input.LA(1);

                        if ( ((LA8_0>='0' && LA8_0<='9')) ) {
                            alt8=1;
                        }


                        switch (alt8) {
                    	case 1 :
                    	    // JavaL.g:24:10: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt8 >= 1 ) break loop8;
                                EarlyExitException eee =
                                    new EarlyExitException(8, input);
                                throw eee;
                        }
                        cnt8++;
                    } while (true);

                    match('.'); 
                    // JavaL.g:24:25: ( '0' .. '9' )*
                    loop9:
                    do {
                        int alt9=2;
                        int LA9_0 = input.LA(1);

                        if ( ((LA9_0>='0' && LA9_0<='9')) ) {
                            alt9=1;
                        }


                        switch (alt9) {
                    	case 1 :
                    	    // JavaL.g:24:26: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    break loop9;
                        }
                    } while (true);

                    // JavaL.g:24:37: ( Exponent )?
                    int alt10=2;
                    int LA10_0 = input.LA(1);

                    if ( (LA10_0=='E'||LA10_0=='e') ) {
                        alt10=1;
                    }
                    switch (alt10) {
                        case 1 :
                            // JavaL.g:24:37: Exponent
                            {
                            mExponent(); 

                            }
                            break;

                    }

                    // JavaL.g:24:47: ( FloatTypeSuffix )?
                    int alt11=2;
                    int LA11_0 = input.LA(1);

                    if ( (LA11_0=='D'||LA11_0=='F'||LA11_0=='d'||LA11_0=='f') ) {
                        alt11=1;
                    }
                    switch (alt11) {
                        case 1 :
                            // JavaL.g:24:47: FloatTypeSuffix
                            {
                            mFloatTypeSuffix(); 

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // JavaL.g:25:9: '.' ( '0' .. '9' )+ ( Exponent )? ( FloatTypeSuffix )?
                    {
                    match('.'); 
                    // JavaL.g:25:13: ( '0' .. '9' )+
                    int cnt12=0;
                    loop12:
                    do {
                        int alt12=2;
                        int LA12_0 = input.LA(1);

                        if ( ((LA12_0>='0' && LA12_0<='9')) ) {
                            alt12=1;
                        }


                        switch (alt12) {
                    	case 1 :
                    	    // JavaL.g:25:14: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt12 >= 1 ) break loop12;
                                EarlyExitException eee =
                                    new EarlyExitException(12, input);
                                throw eee;
                        }
                        cnt12++;
                    } while (true);

                    // JavaL.g:25:25: ( Exponent )?
                    int alt13=2;
                    int LA13_0 = input.LA(1);

                    if ( (LA13_0=='E'||LA13_0=='e') ) {
                        alt13=1;
                    }
                    switch (alt13) {
                        case 1 :
                            // JavaL.g:25:25: Exponent
                            {
                            mExponent(); 

                            }
                            break;

                    }

                    // JavaL.g:25:35: ( FloatTypeSuffix )?
                    int alt14=2;
                    int LA14_0 = input.LA(1);

                    if ( (LA14_0=='D'||LA14_0=='F'||LA14_0=='d'||LA14_0=='f') ) {
                        alt14=1;
                    }
                    switch (alt14) {
                        case 1 :
                            // JavaL.g:25:35: FloatTypeSuffix
                            {
                            mFloatTypeSuffix(); 

                            }
                            break;

                    }


                    }
                    break;
                case 3 :
                    // JavaL.g:26:9: ( '0' .. '9' )+ Exponent ( FloatTypeSuffix )?
                    {
                    // JavaL.g:26:9: ( '0' .. '9' )+
                    int cnt15=0;
                    loop15:
                    do {
                        int alt15=2;
                        int LA15_0 = input.LA(1);

                        if ( ((LA15_0>='0' && LA15_0<='9')) ) {
                            alt15=1;
                        }


                        switch (alt15) {
                    	case 1 :
                    	    // JavaL.g:26:10: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt15 >= 1 ) break loop15;
                                EarlyExitException eee =
                                    new EarlyExitException(15, input);
                                throw eee;
                        }
                        cnt15++;
                    } while (true);

                    mExponent(); 
                    // JavaL.g:26:30: ( FloatTypeSuffix )?
                    int alt16=2;
                    int LA16_0 = input.LA(1);

                    if ( (LA16_0=='D'||LA16_0=='F'||LA16_0=='d'||LA16_0=='f') ) {
                        alt16=1;
                    }
                    switch (alt16) {
                        case 1 :
                            // JavaL.g:26:30: FloatTypeSuffix
                            {
                            mFloatTypeSuffix(); 

                            }
                            break;

                    }


                    }
                    break;
                case 4 :
                    // JavaL.g:27:9: ( '0' .. '9' )+ FloatTypeSuffix
                    {
                    // JavaL.g:27:9: ( '0' .. '9' )+
                    int cnt17=0;
                    loop17:
                    do {
                        int alt17=2;
                        int LA17_0 = input.LA(1);

                        if ( ((LA17_0>='0' && LA17_0<='9')) ) {
                            alt17=1;
                        }


                        switch (alt17) {
                    	case 1 :
                    	    // JavaL.g:27:10: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt17 >= 1 ) break loop17;
                                EarlyExitException eee =
                                    new EarlyExitException(17, input);
                                throw eee;
                        }
                        cnt17++;
                    } while (true);

                    mFloatTypeSuffix(); 

                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "FloatingPointLiteral"

    // $ANTLR start "Exponent"
    public final void mExponent() throws RecognitionException {
        try {
            // JavaL.g:31:10: ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )
            // JavaL.g:31:12: ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+
            {
            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // JavaL.g:31:22: ( '+' | '-' )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0=='+'||LA19_0=='-') ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // JavaL.g:
                    {
                    if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
                        input.consume();

                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}


                    }
                    break;

            }

            // JavaL.g:31:33: ( '0' .. '9' )+
            int cnt20=0;
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( ((LA20_0>='0' && LA20_0<='9')) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // JavaL.g:31:34: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt20 >= 1 ) break loop20;
                        EarlyExitException eee =
                            new EarlyExitException(20, input);
                        throw eee;
                }
                cnt20++;
            } while (true);


            }

        }
        finally {
        }
    }
    // $ANTLR end "Exponent"

    // $ANTLR start "FloatTypeSuffix"
    public final void mFloatTypeSuffix() throws RecognitionException {
        try {
            // JavaL.g:34:17: ( ( 'f' | 'F' | 'd' | 'D' ) )
            // JavaL.g:34:19: ( 'f' | 'F' | 'd' | 'D' )
            {
            if ( input.LA(1)=='D'||input.LA(1)=='F'||input.LA(1)=='d'||input.LA(1)=='f' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "FloatTypeSuffix"

    // $ANTLR start "CharacterLiteral"
    public final void mCharacterLiteral() throws RecognitionException {
        try {
            int _type = CharacterLiteral;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // JavaL.g:37:5: ( '\\'' ( EscapeSequence | ~ ( '\\'' | '\\\\' ) ) '\\'' )
            // JavaL.g:37:9: '\\'' ( EscapeSequence | ~ ( '\\'' | '\\\\' ) ) '\\''
            {
            match('\''); 
            // JavaL.g:37:14: ( EscapeSequence | ~ ( '\\'' | '\\\\' ) )
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0=='\\') ) {
                alt21=1;
            }
            else if ( ((LA21_0>='\u0000' && LA21_0<='&')||(LA21_0>='(' && LA21_0<='[')||(LA21_0>=']' && LA21_0<='\uFFFF')) ) {
                alt21=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 21, 0, input);

                throw nvae;
            }
            switch (alt21) {
                case 1 :
                    // JavaL.g:37:16: EscapeSequence
                    {
                    mEscapeSequence(); 

                    }
                    break;
                case 2 :
                    // JavaL.g:37:33: ~ ( '\\'' | '\\\\' )
                    {
                    if ( (input.LA(1)>='\u0000' && input.LA(1)<='&')||(input.LA(1)>='(' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFF') ) {
                        input.consume();

                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}


                    }
                    break;

            }

            match('\''); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CharacterLiteral"

    // $ANTLR start "StringLiteral"
    public final void mStringLiteral() throws RecognitionException {
        try {
            int _type = StringLiteral;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // JavaL.g:41:5: ( '\"' ( EscapeSequence | ~ ( '\\\\' | '\"' ) )* '\"' )
            // JavaL.g:41:8: '\"' ( EscapeSequence | ~ ( '\\\\' | '\"' ) )* '\"'
            {
            match('\"'); 
            // JavaL.g:41:12: ( EscapeSequence | ~ ( '\\\\' | '\"' ) )*
            loop22:
            do {
                int alt22=3;
                int LA22_0 = input.LA(1);

                if ( (LA22_0=='\\') ) {
                    alt22=1;
                }
                else if ( ((LA22_0>='\u0000' && LA22_0<='!')||(LA22_0>='#' && LA22_0<='[')||(LA22_0>=']' && LA22_0<='\uFFFF')) ) {
                    alt22=2;
                }


                switch (alt22) {
            	case 1 :
            	    // JavaL.g:41:14: EscapeSequence
            	    {
            	    mEscapeSequence(); 

            	    }
            	    break;
            	case 2 :
            	    // JavaL.g:41:31: ~ ( '\\\\' | '\"' )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='!')||(input.LA(1)>='#' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFF') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop22;
                }
            } while (true);

            match('\"'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "StringLiteral"

    // $ANTLR start "EscapeSequence"
    public final void mEscapeSequence() throws RecognitionException {
        try {
            // JavaL.g:46:5: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' ) | UnicodeEscape | OctalEscape )
            int alt23=3;
            int LA23_0 = input.LA(1);

            if ( (LA23_0=='\\') ) {
                switch ( input.LA(2) ) {
                case '\"':
                case '\'':
                case '\\':
                case 'b':
                case 'f':
                case 'n':
                case 'r':
                case 't':
                    {
                    alt23=1;
                    }
                    break;
                case 'u':
                    {
                    alt23=2;
                    }
                    break;
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                    {
                    alt23=3;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 23, 1, input);

                    throw nvae;
                }

            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 23, 0, input);

                throw nvae;
            }
            switch (alt23) {
                case 1 :
                    // JavaL.g:46:9: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' )
                    {
                    match('\\'); 
                    if ( input.LA(1)=='\"'||input.LA(1)=='\''||input.LA(1)=='\\'||input.LA(1)=='b'||input.LA(1)=='f'||input.LA(1)=='n'||input.LA(1)=='r'||input.LA(1)=='t' ) {
                        input.consume();

                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}


                    }
                    break;
                case 2 :
                    // JavaL.g:47:9: UnicodeEscape
                    {
                    mUnicodeEscape(); 

                    }
                    break;
                case 3 :
                    // JavaL.g:48:9: OctalEscape
                    {
                    mOctalEscape(); 

                    }
                    break;

            }
        }
        finally {
        }
    }
    // $ANTLR end "EscapeSequence"

    // $ANTLR start "OctalEscape"
    public final void mOctalEscape() throws RecognitionException {
        try {
            // JavaL.g:53:5: ( '\\\\' ( '0' .. '3' ) ( '0' .. '7' ) ( '0' .. '7' ) | '\\\\' ( '0' .. '7' ) ( '0' .. '7' ) | '\\\\' ( '0' .. '7' ) )
            int alt24=3;
            int LA24_0 = input.LA(1);

            if ( (LA24_0=='\\') ) {
                int LA24_1 = input.LA(2);

                if ( ((LA24_1>='0' && LA24_1<='3')) ) {
                    int LA24_2 = input.LA(3);

                    if ( ((LA24_2>='0' && LA24_2<='7')) ) {
                        int LA24_4 = input.LA(4);

                        if ( ((LA24_4>='0' && LA24_4<='7')) ) {
                            alt24=1;
                        }
                        else {
                            alt24=2;}
                    }
                    else {
                        alt24=3;}
                }
                else if ( ((LA24_1>='4' && LA24_1<='7')) ) {
                    int LA24_3 = input.LA(3);

                    if ( ((LA24_3>='0' && LA24_3<='7')) ) {
                        alt24=2;
                    }
                    else {
                        alt24=3;}
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 24, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 24, 0, input);

                throw nvae;
            }
            switch (alt24) {
                case 1 :
                    // JavaL.g:53:9: '\\\\' ( '0' .. '3' ) ( '0' .. '7' ) ( '0' .. '7' )
                    {
                    match('\\'); 
                    // JavaL.g:53:14: ( '0' .. '3' )
                    // JavaL.g:53:15: '0' .. '3'
                    {
                    matchRange('0','3'); 

                    }

                    // JavaL.g:53:25: ( '0' .. '7' )
                    // JavaL.g:53:26: '0' .. '7'
                    {
                    matchRange('0','7'); 

                    }

                    // JavaL.g:53:36: ( '0' .. '7' )
                    // JavaL.g:53:37: '0' .. '7'
                    {
                    matchRange('0','7'); 

                    }


                    }
                    break;
                case 2 :
                    // JavaL.g:54:9: '\\\\' ( '0' .. '7' ) ( '0' .. '7' )
                    {
                    match('\\'); 
                    // JavaL.g:54:14: ( '0' .. '7' )
                    // JavaL.g:54:15: '0' .. '7'
                    {
                    matchRange('0','7'); 

                    }

                    // JavaL.g:54:25: ( '0' .. '7' )
                    // JavaL.g:54:26: '0' .. '7'
                    {
                    matchRange('0','7'); 

                    }


                    }
                    break;
                case 3 :
                    // JavaL.g:55:9: '\\\\' ( '0' .. '7' )
                    {
                    match('\\'); 
                    // JavaL.g:55:14: ( '0' .. '7' )
                    // JavaL.g:55:15: '0' .. '7'
                    {
                    matchRange('0','7'); 

                    }


                    }
                    break;

            }
        }
        finally {
        }
    }
    // $ANTLR end "OctalEscape"

    // $ANTLR start "UnicodeEscape"
    public final void mUnicodeEscape() throws RecognitionException {
        try {
            // JavaL.g:60:5: ( '\\\\' 'u' HexDigit HexDigit HexDigit HexDigit )
            // JavaL.g:60:9: '\\\\' 'u' HexDigit HexDigit HexDigit HexDigit
            {
            match('\\'); 
            match('u'); 
            mHexDigit(); 
            mHexDigit(); 
            mHexDigit(); 
            mHexDigit(); 

            }

        }
        finally {
        }
    }
    // $ANTLR end "UnicodeEscape"

    // $ANTLR start "ENUM"
    public final void mENUM() throws RecognitionException {
        try {
            int _type = ENUM;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // JavaL.g:63:5: ( 'enum' )
            // JavaL.g:63:9: 'enum'
            {
            match("enum"); 

            if (!enumIsKeyword) _type=Identifier;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ENUM"

    // $ANTLR start "ASSERT"
    public final void mASSERT() throws RecognitionException {
        try {
            int _type = ASSERT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // JavaL.g:67:5: ( 'assert' )
            // JavaL.g:67:9: 'assert'
            {
            match("assert"); 

            if (!assertIsKeyword) _type=Identifier;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ASSERT"

    // $ANTLR start "Identifier"
    public final void mIdentifier() throws RecognitionException {
        try {
            int _type = Identifier;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // JavaL.g:71:5: ( Letter ( Letter | JavaIDDigit )* )
            // JavaL.g:71:9: Letter ( Letter | JavaIDDigit )*
            {
            mLetter(); 
            // JavaL.g:71:16: ( Letter | JavaIDDigit )*
            loop25:
            do {
                int alt25=2;
                int LA25_0 = input.LA(1);

                if ( (LA25_0=='$'||(LA25_0>='0' && LA25_0<='9')||(LA25_0>='A' && LA25_0<='Z')||LA25_0=='_'||(LA25_0>='a' && LA25_0<='z')||(LA25_0>='\u00C0' && LA25_0<='\u00D6')||(LA25_0>='\u00D8' && LA25_0<='\u00F6')||(LA25_0>='\u00F8' && LA25_0<='\u1FFF')||(LA25_0>='\u3040' && LA25_0<='\u318F')||(LA25_0>='\u3300' && LA25_0<='\u337F')||(LA25_0>='\u3400' && LA25_0<='\u3D2D')||(LA25_0>='\u4E00' && LA25_0<='\u9FFF')||(LA25_0>='\uF900' && LA25_0<='\uFAFF')) ) {
                    alt25=1;
                }


                switch (alt25) {
            	case 1 :
            	    // JavaL.g:
            	    {
            	    if ( input.LA(1)=='$'||(input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z')||(input.LA(1)>='\u00C0' && input.LA(1)<='\u00D6')||(input.LA(1)>='\u00D8' && input.LA(1)<='\u00F6')||(input.LA(1)>='\u00F8' && input.LA(1)<='\u1FFF')||(input.LA(1)>='\u3040' && input.LA(1)<='\u318F')||(input.LA(1)>='\u3300' && input.LA(1)<='\u337F')||(input.LA(1)>='\u3400' && input.LA(1)<='\u3D2D')||(input.LA(1)>='\u4E00' && input.LA(1)<='\u9FFF')||(input.LA(1)>='\uF900' && input.LA(1)<='\uFAFF') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop25;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Identifier"

    // $ANTLR start "IdentifierWithWC"
    public final void mIdentifierWithWC() throws RecognitionException {
        try {
            int _type = IdentifierWithWC;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // JavaL.g:75:2: ( ( Letter | '**' ) ( Letter | JavaIDDigit | '**' )* )
            // JavaL.g:75:4: ( Letter | '**' ) ( Letter | JavaIDDigit | '**' )*
            {
            // JavaL.g:75:4: ( Letter | '**' )
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0=='$'||(LA26_0>='A' && LA26_0<='Z')||LA26_0=='_'||(LA26_0>='a' && LA26_0<='z')||(LA26_0>='\u00C0' && LA26_0<='\u00D6')||(LA26_0>='\u00D8' && LA26_0<='\u00F6')||(LA26_0>='\u00F8' && LA26_0<='\u1FFF')||(LA26_0>='\u3040' && LA26_0<='\u318F')||(LA26_0>='\u3300' && LA26_0<='\u337F')||(LA26_0>='\u3400' && LA26_0<='\u3D2D')||(LA26_0>='\u4E00' && LA26_0<='\u9FFF')||(LA26_0>='\uF900' && LA26_0<='\uFAFF')) ) {
                alt26=1;
            }
            else if ( (LA26_0=='*') ) {
                alt26=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 26, 0, input);

                throw nvae;
            }
            switch (alt26) {
                case 1 :
                    // JavaL.g:75:5: Letter
                    {
                    mLetter(); 

                    }
                    break;
                case 2 :
                    // JavaL.g:75:12: '**'
                    {
                    match("**"); 


                    }
                    break;

            }

            // JavaL.g:75:18: ( Letter | JavaIDDigit | '**' )*
            loop27:
            do {
                int alt27=4;
                int LA27_0 = input.LA(1);

                if ( (LA27_0=='$'||(LA27_0>='A' && LA27_0<='Z')||LA27_0=='_'||(LA27_0>='a' && LA27_0<='z')||(LA27_0>='\u00C0' && LA27_0<='\u00D6')||(LA27_0>='\u00D8' && LA27_0<='\u00F6')||(LA27_0>='\u00F8' && LA27_0<='\u1FFF')||(LA27_0>='\u3040' && LA27_0<='\u318F')||(LA27_0>='\u3300' && LA27_0<='\u337F')||(LA27_0>='\u3400' && LA27_0<='\u3D2D')||(LA27_0>='\u4E00' && LA27_0<='\u9FFF')||(LA27_0>='\uF900' && LA27_0<='\uFAFF')) ) {
                    alt27=1;
                }
                else if ( ((LA27_0>='0' && LA27_0<='9')) ) {
                    alt27=2;
                }
                else if ( (LA27_0=='*') ) {
                    alt27=3;
                }


                switch (alt27) {
            	case 1 :
            	    // JavaL.g:75:19: Letter
            	    {
            	    mLetter(); 

            	    }
            	    break;
            	case 2 :
            	    // JavaL.g:75:26: JavaIDDigit
            	    {
            	    mJavaIDDigit(); 

            	    }
            	    break;
            	case 3 :
            	    // JavaL.g:75:38: '**'
            	    {
            	    match("**"); 


            	    }
            	    break;

            	default :
            	    break loop27;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "IdentifierWithWC"

    // $ANTLR start "Letter"
    public final void mLetter() throws RecognitionException {
        try {
            // JavaL.g:83:5: ( '\\u0024' | '\\u0041' .. '\\u005a' | '\\u005f' | '\\u0061' .. '\\u007a' | '\\u00c0' .. '\\u00d6' | '\\u00d8' .. '\\u00f6' | '\\u00f8' .. '\\u00ff' | '\\u0100' .. '\\u1fff' | '\\u3040' .. '\\u318f' | '\\u3300' .. '\\u337f' | '\\u3400' .. '\\u3d2d' | '\\u4e00' .. '\\u9fff' | '\\uf900' .. '\\ufaff' )
            // JavaL.g:
            {
            if ( input.LA(1)=='$'||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z')||(input.LA(1)>='\u00C0' && input.LA(1)<='\u00D6')||(input.LA(1)>='\u00D8' && input.LA(1)<='\u00F6')||(input.LA(1)>='\u00F8' && input.LA(1)<='\u1FFF')||(input.LA(1)>='\u3040' && input.LA(1)<='\u318F')||(input.LA(1)>='\u3300' && input.LA(1)<='\u337F')||(input.LA(1)>='\u3400' && input.LA(1)<='\u3D2D')||(input.LA(1)>='\u4E00' && input.LA(1)<='\u9FFF')||(input.LA(1)>='\uF900' && input.LA(1)<='\uFAFF') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "Letter"

    // $ANTLR start "JavaIDDigit"
    public final void mJavaIDDigit() throws RecognitionException {
        try {
            // JavaL.g:100:5: ( '\\u0030' .. '\\u0039' | '\\u0660' .. '\\u0669' | '\\u06f0' .. '\\u06f9' | '\\u0966' .. '\\u096f' | '\\u09e6' .. '\\u09ef' | '\\u0a66' .. '\\u0a6f' | '\\u0ae6' .. '\\u0aef' | '\\u0b66' .. '\\u0b6f' | '\\u0be7' .. '\\u0bef' | '\\u0c66' .. '\\u0c6f' | '\\u0ce6' .. '\\u0cef' | '\\u0d66' .. '\\u0d6f' | '\\u0e50' .. '\\u0e59' | '\\u0ed0' .. '\\u0ed9' | '\\u1040' .. '\\u1049' )
            // JavaL.g:
            {
            if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='\u0660' && input.LA(1)<='\u0669')||(input.LA(1)>='\u06F0' && input.LA(1)<='\u06F9')||(input.LA(1)>='\u0966' && input.LA(1)<='\u096F')||(input.LA(1)>='\u09E6' && input.LA(1)<='\u09EF')||(input.LA(1)>='\u0A66' && input.LA(1)<='\u0A6F')||(input.LA(1)>='\u0AE6' && input.LA(1)<='\u0AEF')||(input.LA(1)>='\u0B66' && input.LA(1)<='\u0B6F')||(input.LA(1)>='\u0BE7' && input.LA(1)<='\u0BEF')||(input.LA(1)>='\u0C66' && input.LA(1)<='\u0C6F')||(input.LA(1)>='\u0CE6' && input.LA(1)<='\u0CEF')||(input.LA(1)>='\u0D66' && input.LA(1)<='\u0D6F')||(input.LA(1)>='\u0E50' && input.LA(1)<='\u0E59')||(input.LA(1)>='\u0ED0' && input.LA(1)<='\u0ED9')||(input.LA(1)>='\u1040' && input.LA(1)<='\u1049') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "JavaIDDigit"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // JavaL.g:117:5: ( ( ' ' | '\\r' | '\\t' | '\\u000C' | '\\n' ) )
            // JavaL.g:117:8: ( ' ' | '\\r' | '\\t' | '\\u000C' | '\\n' )
            {
            if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||(input.LA(1)>='\f' && input.LA(1)<='\r')||input.LA(1)==' ' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            _channel=HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "WS"

    // $ANTLR start "COMMENT"
    public final void mCOMMENT() throws RecognitionException {
        try {
            int _type = COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // JavaL.g:121:5: ( '/*' ( options {greedy=false; } : . )* '*/' )
            // JavaL.g:121:9: '/*' ( options {greedy=false; } : . )* '*/'
            {
            match("/*"); 

            // JavaL.g:121:14: ( options {greedy=false; } : . )*
            loop28:
            do {
                int alt28=2;
                int LA28_0 = input.LA(1);

                if ( (LA28_0=='*') ) {
                    int LA28_1 = input.LA(2);

                    if ( (LA28_1=='/') ) {
                        alt28=2;
                    }
                    else if ( ((LA28_1>='\u0000' && LA28_1<='.')||(LA28_1>='0' && LA28_1<='\uFFFF')) ) {
                        alt28=1;
                    }


                }
                else if ( ((LA28_0>='\u0000' && LA28_0<=')')||(LA28_0>='+' && LA28_0<='\uFFFF')) ) {
                    alt28=1;
                }


                switch (alt28) {
            	case 1 :
            	    // JavaL.g:121:42: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop28;
                }
            } while (true);

            match("*/"); 

            _channel=HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "COMMENT"

    // $ANTLR start "LINE_COMMENT"
    public final void mLINE_COMMENT() throws RecognitionException {
        try {
            int _type = LINE_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // JavaL.g:125:5: ( '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n' )
            // JavaL.g:125:7: '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n'
            {
            match("//"); 

            // JavaL.g:125:12: (~ ( '\\n' | '\\r' ) )*
            loop29:
            do {
                int alt29=2;
                int LA29_0 = input.LA(1);

                if ( ((LA29_0>='\u0000' && LA29_0<='\t')||(LA29_0>='\u000B' && LA29_0<='\f')||(LA29_0>='\u000E' && LA29_0<='\uFFFF')) ) {
                    alt29=1;
                }


                switch (alt29) {
            	case 1 :
            	    // JavaL.g:125:12: ~ ( '\\n' | '\\r' )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='\uFFFF') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop29;
                }
            } while (true);

            // JavaL.g:125:26: ( '\\r' )?
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0=='\r') ) {
                alt30=1;
            }
            switch (alt30) {
                case 1 :
                    // JavaL.g:125:26: '\\r'
                    {
                    match('\r'); 

                    }
                    break;

            }

            match('\n'); 
            _channel=HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LINE_COMMENT"

    public void mTokens() throws RecognitionException {
        // JavaL.g:1:8: ( HexLiteral | DecimalLiteral | OctalLiteral | FloatingPointLiteral | CharacterLiteral | StringLiteral | ENUM | ASSERT | Identifier | IdentifierWithWC | WS | COMMENT | LINE_COMMENT )
        int alt31=13;
        alt31 = dfa31.predict(input);
        switch (alt31) {
            case 1 :
                // JavaL.g:1:10: HexLiteral
                {
                mHexLiteral(); 

                }
                break;
            case 2 :
                // JavaL.g:1:21: DecimalLiteral
                {
                mDecimalLiteral(); 

                }
                break;
            case 3 :
                // JavaL.g:1:36: OctalLiteral
                {
                mOctalLiteral(); 

                }
                break;
            case 4 :
                // JavaL.g:1:49: FloatingPointLiteral
                {
                mFloatingPointLiteral(); 

                }
                break;
            case 5 :
                // JavaL.g:1:70: CharacterLiteral
                {
                mCharacterLiteral(); 

                }
                break;
            case 6 :
                // JavaL.g:1:87: StringLiteral
                {
                mStringLiteral(); 

                }
                break;
            case 7 :
                // JavaL.g:1:101: ENUM
                {
                mENUM(); 

                }
                break;
            case 8 :
                // JavaL.g:1:106: ASSERT
                {
                mASSERT(); 

                }
                break;
            case 9 :
                // JavaL.g:1:113: Identifier
                {
                mIdentifier(); 

                }
                break;
            case 10 :
                // JavaL.g:1:124: IdentifierWithWC
                {
                mIdentifierWithWC(); 

                }
                break;
            case 11 :
                // JavaL.g:1:141: WS
                {
                mWS(); 

                }
                break;
            case 12 :
                // JavaL.g:1:144: COMMENT
                {
                mCOMMENT(); 

                }
                break;
            case 13 :
                // JavaL.g:1:152: LINE_COMMENT
                {
                mLINE_COMMENT(); 

                }
                break;

        }

    }


    protected DFA18 dfa18 = new DFA18(this);
    protected DFA31 dfa31 = new DFA31(this);
    static final String DFA18_eotS =
        "\6\uffff";
    static final String DFA18_eofS =
        "\6\uffff";
    static final String DFA18_minS =
        "\2\56\4\uffff";
    static final String DFA18_maxS =
        "\1\71\1\146\4\uffff";
    static final String DFA18_acceptS =
        "\2\uffff\1\2\1\1\1\3\1\4";
    static final String DFA18_specialS =
        "\6\uffff}>";
    static final String[] DFA18_transitionS = {
            "\1\2\1\uffff\12\1",
            "\1\3\1\uffff\12\1\12\uffff\1\5\1\4\1\5\35\uffff\1\5\1\4\1"+
            "\5",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA18_eot = DFA.unpackEncodedString(DFA18_eotS);
    static final short[] DFA18_eof = DFA.unpackEncodedString(DFA18_eofS);
    static final char[] DFA18_min = DFA.unpackEncodedStringToUnsignedChars(DFA18_minS);
    static final char[] DFA18_max = DFA.unpackEncodedStringToUnsignedChars(DFA18_maxS);
    static final short[] DFA18_accept = DFA.unpackEncodedString(DFA18_acceptS);
    static final short[] DFA18_special = DFA.unpackEncodedString(DFA18_specialS);
    static final short[][] DFA18_transition;

    static {
        int numStates = DFA18_transitionS.length;
        DFA18_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA18_transition[i] = DFA.unpackEncodedString(DFA18_transitionS[i]);
        }
    }

    class DFA18 extends DFA {

        public DFA18(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 18;
            this.eot = DFA18_eot;
            this.eof = DFA18_eof;
            this.min = DFA18_min;
            this.max = DFA18_max;
            this.accept = DFA18_accept;
            this.special = DFA18_special;
            this.transition = DFA18_transition;
        }
        public String getDescription() {
            return "23:1: FloatingPointLiteral : ( ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( Exponent )? ( FloatTypeSuffix )? | '.' ( '0' .. '9' )+ ( Exponent )? ( FloatTypeSuffix )? | ( '0' .. '9' )+ Exponent ( FloatTypeSuffix )? | ( '0' .. '9' )+ FloatTypeSuffix );";
        }
    }
    static final String DFA31_eotS =
        "\1\uffff\2\15\3\uffff\3\21\5\uffff\1\30\1\15\1\21\1\uffff\4\21"+
        "\3\uffff\2\21\1\35\1\21\1\uffff\1\21\1\40\1\uffff";
    static final String DFA31_eofS =
        "\41\uffff";
    static final String DFA31_minS =
        "\1\11\2\56\3\uffff\3\44\2\uffff\1\52\2\uffff\2\56\1\44\1\uffff"+
        "\4\44\3\uffff\4\44\1\uffff\2\44\1\uffff";
    static final String DFA31_maxS =
        "\1\ufaff\1\170\1\146\3\uffff\3\ufaff\2\uffff\1\57\2\uffff\2\146"+
        "\1\ufaff\1\uffff\4\ufaff\3\uffff\4\ufaff\1\uffff\2\ufaff\1\uffff";
    static final String DFA31_acceptS =
        "\3\uffff\1\4\1\5\1\6\3\uffff\1\12\1\13\1\uffff\1\1\1\2\3\uffff"+
        "\1\11\4\uffff\1\14\1\15\1\3\4\uffff\1\7\2\uffff\1\10";
    static final String DFA31_specialS =
        "\41\uffff}>";
    static final String[] DFA31_transitionS = {
            "\2\12\1\uffff\2\12\22\uffff\1\12\1\uffff\1\5\1\uffff\1\10\2"+
            "\uffff\1\4\2\uffff\1\11\3\uffff\1\3\1\13\1\1\11\2\7\uffff\32"+
            "\10\4\uffff\1\10\1\uffff\1\7\3\10\1\6\25\10\105\uffff\27\10"+
            "\1\uffff\37\10\1\uffff\u1f08\10\u1040\uffff\u0150\10\u0170\uffff"+
            "\u0080\10\u0080\uffff\u092e\10\u10d2\uffff\u5200\10\u5900\uffff"+
            "\u0200\10",
            "\1\3\1\uffff\10\16\2\3\12\uffff\3\3\21\uffff\1\14\13\uffff"+
            "\3\3\21\uffff\1\14",
            "\1\3\1\uffff\12\17\12\uffff\3\3\35\uffff\3\3",
            "",
            "",
            "",
            "\1\24\5\uffff\1\11\5\uffff\12\23\7\uffff\32\24\4\uffff\1\24"+
            "\1\uffff\15\24\1\20\14\24\105\uffff\27\24\1\uffff\37\24\1\uffff"+
            "\u0568\24\12\22\u0086\24\12\22\u026c\24\12\22\166\24\12\22\166"+
            "\24\12\22\166\24\12\22\166\24\12\22\167\24\11\22\166\24\12\22"+
            "\166\24\12\22\166\24\12\22\u00e0\24\12\22\166\24\12\22\u0166"+
            "\24\12\22\u0fb6\24\u1040\uffff\u0150\24\u0170\uffff\u0080\24"+
            "\u0080\uffff\u092e\24\u10d2\uffff\u5200\24\u5900\uffff\u0200"+
            "\24",
            "\1\24\5\uffff\1\11\5\uffff\12\23\7\uffff\32\24\4\uffff\1\24"+
            "\1\uffff\22\24\1\25\7\24\105\uffff\27\24\1\uffff\37\24\1\uffff"+
            "\u0568\24\12\22\u0086\24\12\22\u026c\24\12\22\166\24\12\22\166"+
            "\24\12\22\166\24\12\22\166\24\12\22\167\24\11\22\166\24\12\22"+
            "\166\24\12\22\166\24\12\22\u00e0\24\12\22\166\24\12\22\u0166"+
            "\24\12\22\u0fb6\24\u1040\uffff\u0150\24\u0170\uffff\u0080\24"+
            "\u0080\uffff\u092e\24\u10d2\uffff\u5200\24\u5900\uffff\u0200"+
            "\24",
            "\1\24\5\uffff\1\11\5\uffff\12\23\7\uffff\32\24\4\uffff\1\24"+
            "\1\uffff\32\24\105\uffff\27\24\1\uffff\37\24\1\uffff\u0568\24"+
            "\12\22\u0086\24\12\22\u026c\24\12\22\166\24\12\22\166\24\12"+
            "\22\166\24\12\22\166\24\12\22\167\24\11\22\166\24\12\22\166"+
            "\24\12\22\166\24\12\22\u00e0\24\12\22\166\24\12\22\u0166\24"+
            "\12\22\u0fb6\24\u1040\uffff\u0150\24\u0170\uffff\u0080\24\u0080"+
            "\uffff\u092e\24\u10d2\uffff\u5200\24\u5900\uffff\u0200\24",
            "",
            "",
            "\1\26\4\uffff\1\27",
            "",
            "",
            "\1\3\1\uffff\10\16\2\3\12\uffff\3\3\35\uffff\3\3",
            "\1\3\1\uffff\12\17\12\uffff\3\3\35\uffff\3\3",
            "\1\24\5\uffff\1\11\5\uffff\12\23\7\uffff\32\24\4\uffff\1\24"+
            "\1\uffff\24\24\1\31\5\24\105\uffff\27\24\1\uffff\37\24\1\uffff"+
            "\u0568\24\12\22\u0086\24\12\22\u026c\24\12\22\166\24\12\22\166"+
            "\24\12\22\166\24\12\22\166\24\12\22\167\24\11\22\166\24\12\22"+
            "\166\24\12\22\166\24\12\22\u00e0\24\12\22\166\24\12\22\u0166"+
            "\24\12\22\u0fb6\24\u1040\uffff\u0150\24\u0170\uffff\u0080\24"+
            "\u0080\uffff\u092e\24\u10d2\uffff\u5200\24\u5900\uffff\u0200"+
            "\24",
            "",
            "\1\24\5\uffff\1\11\5\uffff\12\23\7\uffff\32\24\4\uffff\1\24"+
            "\1\uffff\32\24\105\uffff\27\24\1\uffff\37\24\1\uffff\u0568\24"+
            "\12\22\u0086\24\12\22\u026c\24\12\22\166\24\12\22\166\24\12"+
            "\22\166\24\12\22\166\24\12\22\167\24\11\22\166\24\12\22\166"+
            "\24\12\22\166\24\12\22\u00e0\24\12\22\166\24\12\22\u0166\24"+
            "\12\22\u0fb6\24\u1040\uffff\u0150\24\u0170\uffff\u0080\24\u0080"+
            "\uffff\u092e\24\u10d2\uffff\u5200\24\u5900\uffff\u0200\24",
            "\1\24\5\uffff\1\11\5\uffff\12\23\7\uffff\32\24\4\uffff\1\24"+
            "\1\uffff\32\24\105\uffff\27\24\1\uffff\37\24\1\uffff\u0568\24"+
            "\12\22\u0086\24\12\22\u026c\24\12\22\166\24\12\22\166\24\12"+
            "\22\166\24\12\22\166\24\12\22\167\24\11\22\166\24\12\22\166"+
            "\24\12\22\166\24\12\22\u00e0\24\12\22\166\24\12\22\u0166\24"+
            "\12\22\u0fb6\24\u1040\uffff\u0150\24\u0170\uffff\u0080\24\u0080"+
            "\uffff\u092e\24\u10d2\uffff\u5200\24\u5900\uffff\u0200\24",
            "\1\24\5\uffff\1\11\5\uffff\12\23\7\uffff\32\24\4\uffff\1\24"+
            "\1\uffff\32\24\105\uffff\27\24\1\uffff\37\24\1\uffff\u0568\24"+
            "\12\22\u0086\24\12\22\u026c\24\12\22\166\24\12\22\166\24\12"+
            "\22\166\24\12\22\166\24\12\22\167\24\11\22\166\24\12\22\166"+
            "\24\12\22\166\24\12\22\u00e0\24\12\22\166\24\12\22\u0166\24"+
            "\12\22\u0fb6\24\u1040\uffff\u0150\24\u0170\uffff\u0080\24\u0080"+
            "\uffff\u092e\24\u10d2\uffff\u5200\24\u5900\uffff\u0200\24",
            "\1\24\5\uffff\1\11\5\uffff\12\23\7\uffff\32\24\4\uffff\1\24"+
            "\1\uffff\22\24\1\32\7\24\105\uffff\27\24\1\uffff\37\24\1\uffff"+
            "\u0568\24\12\22\u0086\24\12\22\u026c\24\12\22\166\24\12\22\166"+
            "\24\12\22\166\24\12\22\166\24\12\22\167\24\11\22\166\24\12\22"+
            "\166\24\12\22\166\24\12\22\u00e0\24\12\22\166\24\12\22\u0166"+
            "\24\12\22\u0fb6\24\u1040\uffff\u0150\24\u0170\uffff\u0080\24"+
            "\u0080\uffff\u092e\24\u10d2\uffff\u5200\24\u5900\uffff\u0200"+
            "\24",
            "",
            "",
            "",
            "\1\24\5\uffff\1\11\5\uffff\12\23\7\uffff\32\24\4\uffff\1\24"+
            "\1\uffff\14\24\1\33\15\24\105\uffff\27\24\1\uffff\37\24\1\uffff"+
            "\u0568\24\12\22\u0086\24\12\22\u026c\24\12\22\166\24\12\22\166"+
            "\24\12\22\166\24\12\22\166\24\12\22\167\24\11\22\166\24\12\22"+
            "\166\24\12\22\166\24\12\22\u00e0\24\12\22\166\24\12\22\u0166"+
            "\24\12\22\u0fb6\24\u1040\uffff\u0150\24\u0170\uffff\u0080\24"+
            "\u0080\uffff\u092e\24\u10d2\uffff\u5200\24\u5900\uffff\u0200"+
            "\24",
            "\1\24\5\uffff\1\11\5\uffff\12\23\7\uffff\32\24\4\uffff\1\24"+
            "\1\uffff\4\24\1\34\25\24\105\uffff\27\24\1\uffff\37\24\1\uffff"+
            "\u0568\24\12\22\u0086\24\12\22\u026c\24\12\22\166\24\12\22\166"+
            "\24\12\22\166\24\12\22\166\24\12\22\167\24\11\22\166\24\12\22"+
            "\166\24\12\22\166\24\12\22\u00e0\24\12\22\166\24\12\22\u0166"+
            "\24\12\22\u0fb6\24\u1040\uffff\u0150\24\u0170\uffff\u0080\24"+
            "\u0080\uffff\u092e\24\u10d2\uffff\u5200\24\u5900\uffff\u0200"+
            "\24",
            "\1\24\5\uffff\1\11\5\uffff\12\23\7\uffff\32\24\4\uffff\1\24"+
            "\1\uffff\32\24\105\uffff\27\24\1\uffff\37\24\1\uffff\u0568\24"+
            "\12\22\u0086\24\12\22\u026c\24\12\22\166\24\12\22\166\24\12"+
            "\22\166\24\12\22\166\24\12\22\167\24\11\22\166\24\12\22\166"+
            "\24\12\22\166\24\12\22\u00e0\24\12\22\166\24\12\22\u0166\24"+
            "\12\22\u0fb6\24\u1040\uffff\u0150\24\u0170\uffff\u0080\24\u0080"+
            "\uffff\u092e\24\u10d2\uffff\u5200\24\u5900\uffff\u0200\24",
            "\1\24\5\uffff\1\11\5\uffff\12\23\7\uffff\32\24\4\uffff\1\24"+
            "\1\uffff\21\24\1\36\10\24\105\uffff\27\24\1\uffff\37\24\1\uffff"+
            "\u0568\24\12\22\u0086\24\12\22\u026c\24\12\22\166\24\12\22\166"+
            "\24\12\22\166\24\12\22\166\24\12\22\167\24\11\22\166\24\12\22"+
            "\166\24\12\22\166\24\12\22\u00e0\24\12\22\166\24\12\22\u0166"+
            "\24\12\22\u0fb6\24\u1040\uffff\u0150\24\u0170\uffff\u0080\24"+
            "\u0080\uffff\u092e\24\u10d2\uffff\u5200\24\u5900\uffff\u0200"+
            "\24",
            "",
            "\1\24\5\uffff\1\11\5\uffff\12\23\7\uffff\32\24\4\uffff\1\24"+
            "\1\uffff\23\24\1\37\6\24\105\uffff\27\24\1\uffff\37\24\1\uffff"+
            "\u0568\24\12\22\u0086\24\12\22\u026c\24\12\22\166\24\12\22\166"+
            "\24\12\22\166\24\12\22\166\24\12\22\167\24\11\22\166\24\12\22"+
            "\166\24\12\22\166\24\12\22\u00e0\24\12\22\166\24\12\22\u0166"+
            "\24\12\22\u0fb6\24\u1040\uffff\u0150\24\u0170\uffff\u0080\24"+
            "\u0080\uffff\u092e\24\u10d2\uffff\u5200\24\u5900\uffff\u0200"+
            "\24",
            "\1\24\5\uffff\1\11\5\uffff\12\23\7\uffff\32\24\4\uffff\1\24"+
            "\1\uffff\32\24\105\uffff\27\24\1\uffff\37\24\1\uffff\u0568\24"+
            "\12\22\u0086\24\12\22\u026c\24\12\22\166\24\12\22\166\24\12"+
            "\22\166\24\12\22\166\24\12\22\167\24\11\22\166\24\12\22\166"+
            "\24\12\22\166\24\12\22\u00e0\24\12\22\166\24\12\22\u0166\24"+
            "\12\22\u0fb6\24\u1040\uffff\u0150\24\u0170\uffff\u0080\24\u0080"+
            "\uffff\u092e\24\u10d2\uffff\u5200\24\u5900\uffff\u0200\24",
            ""
    };

    static final short[] DFA31_eot = DFA.unpackEncodedString(DFA31_eotS);
    static final short[] DFA31_eof = DFA.unpackEncodedString(DFA31_eofS);
    static final char[] DFA31_min = DFA.unpackEncodedStringToUnsignedChars(DFA31_minS);
    static final char[] DFA31_max = DFA.unpackEncodedStringToUnsignedChars(DFA31_maxS);
    static final short[] DFA31_accept = DFA.unpackEncodedString(DFA31_acceptS);
    static final short[] DFA31_special = DFA.unpackEncodedString(DFA31_specialS);
    static final short[][] DFA31_transition;

    static {
        int numStates = DFA31_transitionS.length;
        DFA31_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA31_transition[i] = DFA.unpackEncodedString(DFA31_transitionS[i]);
        }
    }

    class DFA31 extends DFA {

        public DFA31(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 31;
            this.eot = DFA31_eot;
            this.eof = DFA31_eof;
            this.min = DFA31_min;
            this.max = DFA31_max;
            this.accept = DFA31_accept;
            this.special = DFA31_special;
            this.transition = DFA31_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( HexLiteral | DecimalLiteral | OctalLiteral | FloatingPointLiteral | CharacterLiteral | StringLiteral | ENUM | ASSERT | Identifier | IdentifierWithWC | WS | COMMENT | LINE_COMMENT );";
        }
    }
 

}