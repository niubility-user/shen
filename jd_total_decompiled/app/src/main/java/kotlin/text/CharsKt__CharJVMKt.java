package kotlin.text;

import ..;
import com.jd.dynamic.DYConstants;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.internal.InlineOnly;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0010\f\n\u0002\u0010\u000b\n\u0002\b\u0014\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0014\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u0087\b\u00a2\u0006\u0004\b\u0002\u0010\u0003\u001a\u0014\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\u0087\b\u00a2\u0006\u0004\b\u0004\u0010\u0003\u001a\u0014\u0010\u0005\u001a\u00020\u0001*\u00020\u0000H\u0087\b\u00a2\u0006\u0004\b\u0005\u0010\u0003\u001a\u0014\u0010\u0006\u001a\u00020\u0001*\u00020\u0000H\u0087\b\u00a2\u0006\u0004\b\u0006\u0010\u0003\u001a\u0014\u0010\u0007\u001a\u00020\u0001*\u00020\u0000H\u0087\b\u00a2\u0006\u0004\b\u0007\u0010\u0003\u001a\u0014\u0010\b\u001a\u00020\u0001*\u00020\u0000H\u0087\b\u00a2\u0006\u0004\b\b\u0010\u0003\u001a\u0014\u0010\t\u001a\u00020\u0001*\u00020\u0000H\u0087\b\u00a2\u0006\u0004\b\t\u0010\u0003\u001a\u0014\u0010\n\u001a\u00020\u0001*\u00020\u0000H\u0087\b\u00a2\u0006\u0004\b\n\u0010\u0003\u001a\u0011\u0010\u000b\u001a\u00020\u0001*\u00020\u0000\u00a2\u0006\u0004\b\u000b\u0010\u0003\u001a\u0014\u0010\f\u001a\u00020\u0001*\u00020\u0000H\u0087\b\u00a2\u0006\u0004\b\f\u0010\u0003\u001a\u0014\u0010\r\u001a\u00020\u0001*\u00020\u0000H\u0087\b\u00a2\u0006\u0004\b\r\u0010\u0003\u001a\u0014\u0010\u000e\u001a\u00020\u0000*\u00020\u0000H\u0087\b\u00a2\u0006\u0004\b\u000e\u0010\u000f\u001a\u0014\u0010\u0010\u001a\u00020\u0000*\u00020\u0000H\u0087\b\u00a2\u0006\u0004\b\u0010\u0010\u000f\u001a\u0014\u0010\u0011\u001a\u00020\u0001*\u00020\u0000H\u0087\b\u00a2\u0006\u0004\b\u0011\u0010\u0003\u001a\u0014\u0010\u0012\u001a\u00020\u0000*\u00020\u0000H\u0087\b\u00a2\u0006\u0004\b\u0012\u0010\u000f\u001a\u0014\u0010\u0013\u001a\u00020\u0001*\u00020\u0000H\u0087\b\u00a2\u0006\u0004\b\u0013\u0010\u0003\u001a\u0014\u0010\u0014\u001a\u00020\u0001*\u00020\u0000H\u0087\b\u00a2\u0006\u0004\b\u0014\u0010\u0003\u001a\u001f\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0015\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u0016H\u0000\u00a2\u0006\u0004\b\u0018\u0010\u0019\u001a\u0017\u0010\u001a\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0016H\u0001\u00a2\u0006\u0004\b\u001a\u0010\u001b\"\u0017\u0010\u001f\u001a\u00020\u001c*\u00020\u00008F@\u0006\u00a2\u0006\u0006\u001a\u0004\b\u001d\u0010\u001e\"\u0017\u0010#\u001a\u00020 *\u00020\u00008F@\u0006\u00a2\u0006\u0006\u001a\u0004\b!\u0010\"\u00a8\u0006$"}, d2 = {"", "", "isDefined", "(C)Z", "isLetter", "isLetterOrDigit", "isDigit", "isIdentifierIgnorable", "isISOControl", "isJavaIdentifierPart", "isJavaIdentifierStart", "isWhitespace", "isUpperCase", "isLowerCase", "toUpperCase", "(C)C", "toLowerCase", "isTitleCase", "toTitleCase", "isHighSurrogate", "isLowSurrogate", DYConstants.DY_CHAR, "", "radix", "digitOf", "(CI)I", "checkRadix", "(I)I", "Lkotlin/text/CharCategory;", "getCategory", "(C)Lkotlin/text/CharCategory;", "category", "Lkotlin/text/CharDirectionality;", "getDirectionality", "(C)Lkotlin/text/CharDirectionality;", "directionality", "kotlin-stdlib"}, k = 5, mv = {1, 4, 0}, xs = "kotlin/text/CharsKt")
/* loaded from: classes11.dex */
public class CharsKt__CharJVMKt {
    @PublishedApi
    public static int checkRadix(int i2) {
        if (2 > i2 || 36 < i2) {
            throw new IllegalArgumentException("radix " + i2 + " was not in valid range " + new (2, 36));
        }
        return i2;
    }

    public static final int digitOf(char c2, int i2) {
        return Character.digit((int) c2, i2);
    }

    @NotNull
    public static final CharCategory getCategory(char c2) {
        return CharCategory.INSTANCE.valueOf(Character.getType(c2));
    }

    @NotNull
    public static final CharDirectionality getDirectionality(char c2) {
        return CharDirectionality.INSTANCE.valueOf(Character.getDirectionality(c2));
    }

    @InlineOnly
    private static final boolean isDefined(char c2) {
        return Character.isDefined(c2);
    }

    @InlineOnly
    private static final boolean isDigit(char c2) {
        return Character.isDigit(c2);
    }

    @InlineOnly
    private static final boolean isHighSurrogate(char c2) {
        return Character.isHighSurrogate(c2);
    }

    @InlineOnly
    private static final boolean isISOControl(char c2) {
        return Character.isISOControl(c2);
    }

    @InlineOnly
    private static final boolean isIdentifierIgnorable(char c2) {
        return Character.isIdentifierIgnorable(c2);
    }

    @InlineOnly
    private static final boolean isJavaIdentifierPart(char c2) {
        return Character.isJavaIdentifierPart(c2);
    }

    @InlineOnly
    private static final boolean isJavaIdentifierStart(char c2) {
        return Character.isJavaIdentifierStart(c2);
    }

    @InlineOnly
    private static final boolean isLetter(char c2) {
        return Character.isLetter(c2);
    }

    @InlineOnly
    private static final boolean isLetterOrDigit(char c2) {
        return Character.isLetterOrDigit(c2);
    }

    @InlineOnly
    private static final boolean isLowSurrogate(char c2) {
        return Character.isLowSurrogate(c2);
    }

    @InlineOnly
    private static final boolean isLowerCase(char c2) {
        return Character.isLowerCase(c2);
    }

    @InlineOnly
    private static final boolean isTitleCase(char c2) {
        return Character.isTitleCase(c2);
    }

    @InlineOnly
    private static final boolean isUpperCase(char c2) {
        return Character.isUpperCase(c2);
    }

    public static final boolean isWhitespace(char c2) {
        return Character.isWhitespace(c2) || Character.isSpaceChar(c2);
    }

    @InlineOnly
    private static final char toLowerCase(char c2) {
        return Character.toLowerCase(c2);
    }

    @InlineOnly
    private static final char toTitleCase(char c2) {
        return Character.toTitleCase(c2);
    }

    @InlineOnly
    private static final char toUpperCase(char c2) {
        return Character.toUpperCase(c2);
    }
}
