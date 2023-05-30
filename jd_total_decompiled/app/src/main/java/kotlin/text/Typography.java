package kotlin.text;

import com.jd.aips.verify.tracker.VerifyTracker;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\f\n\u0002\b+\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b+\u0010,R\u0016\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\u0004R\u0016\u0010\u0005\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0004R\u0016\u0010\u0006\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0006\u0010\u0004R\u0016\u0010\u0007\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\u0004R\u0016\u0010\b\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\b\u0010\u0004R\u0016\u0010\t\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\t\u0010\u0004R\u0016\u0010\n\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\n\u0010\u0004R\u0016\u0010\u000b\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u000b\u0010\u0004R\u0016\u0010\f\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\f\u0010\u0004R\u0016\u0010\r\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\r\u0010\u0004R\u0016\u0010\u000e\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\u0004R\u0016\u0010\u000f\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u000f\u0010\u0004R\u0016\u0010\u0010\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0010\u0010\u0004R\u0016\u0010\u0011\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\u0004R\u0016\u0010\u0012\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0012\u0010\u0004R\u0016\u0010\u0013\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\u0004R\u0016\u0010\u0014\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u0004R\u0016\u0010\u0015\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\u0004R\u0016\u0010\u0016\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u0004R\u0016\u0010\u0017\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u0004R\u0016\u0010\u0018\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\u0004R\u0016\u0010\u0019\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\u0004R\u0016\u0010\u001a\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u001a\u0010\u0004R\u0016\u0010\u001b\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u001b\u0010\u0004R\u0016\u0010\u001c\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u0004R\u0016\u0010\u001d\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u001d\u0010\u0004R\u0016\u0010\u001e\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u001e\u0010\u0004R\u0016\u0010\u001f\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u001f\u0010\u0004R\u0016\u0010 \u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b \u0010\u0004R\u0016\u0010!\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b!\u0010\u0004R\u0016\u0010\"\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\"\u0010\u0004R\u0016\u0010#\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b#\u0010\u0004R\u0016\u0010$\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b$\u0010\u0004R\u0016\u0010%\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b%\u0010\u0004R\u0016\u0010&\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b&\u0010\u0004R\u0016\u0010'\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b'\u0010\u0004R\u0016\u0010(\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b(\u0010\u0004R\u0016\u0010)\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b)\u0010\u0004R\u0016\u0010*\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b*\u0010\u0004\u00a8\u0006-"}, d2 = {"Lkotlin/text/Typography;", "", "", "rightDoubleQuote", "C", "doubleDagger", "lowSingleQuote", "ellipsis", "pound", "rightGuillemete", "amp", "ndash", "leftSingleQuote", "registered", "prime", "greaterOrEqual", "degree", "quote", "middleDot", "less", "copyright", "notEqual", "dagger", "plusMinus", "half", "lessOrEqual", "cent", "paragraph", "leftGuillemete", "bullet", "euro", "tm", "section", "nbsp", "lowDoubleQuote", "rightSingleQuote", "almostEqual", "dollar", "doublePrime", VerifyTracker.KEY_TIMES, "mdash", "greater", "leftDoubleQuote", "<init>", "()V", "kotlin-stdlib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public final class Typography {
    public static final Typography INSTANCE = new Typography();
    public static final char almostEqual = '\u2248';
    public static final char amp = '&';
    public static final char bullet = '\u2022';
    public static final char cent = '\u00a2';
    public static final char copyright = '\u00a9';
    public static final char dagger = '\u2020';
    public static final char degree = '\u00b0';
    public static final char dollar = '$';
    public static final char doubleDagger = '\u2021';
    public static final char doublePrime = '\u2033';
    public static final char ellipsis = '\u2026';
    public static final char euro = '\u20ac';
    public static final char greater = '>';
    public static final char greaterOrEqual = '\u2265';
    public static final char half = '\u00bd';
    public static final char leftDoubleQuote = '\u201c';
    public static final char leftGuillemete = '\u00ab';
    public static final char leftSingleQuote = '\u2018';
    public static final char less = '<';
    public static final char lessOrEqual = '\u2264';
    public static final char lowDoubleQuote = '\u201e';
    public static final char lowSingleQuote = '\u201a';
    public static final char mdash = '\u2014';
    public static final char middleDot = '\u00b7';
    public static final char nbsp = '\u00a0';
    public static final char ndash = '\u2013';
    public static final char notEqual = '\u2260';
    public static final char paragraph = '\u00b6';
    public static final char plusMinus = '\u00b1';
    public static final char pound = '\u00a3';
    public static final char prime = '\u2032';
    public static final char quote = '\"';
    public static final char registered = '\u00ae';
    public static final char rightDoubleQuote = '\u201d';
    public static final char rightGuillemete = '\u00bb';
    public static final char rightSingleQuote = '\u2019';
    public static final char section = '\u00a7';
    public static final char times = '\u00d7';
    public static final char tm = '\u2122';

    private Typography() {
    }
}
