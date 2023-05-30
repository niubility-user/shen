package kotlin.text;

import kotlin.Metadata;
import kotlin.internal.InlineOnly;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0010\f\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\u001a\u001c\u0010\u0003\u001a\u00020\u0001*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\n\u00a2\u0006\u0004\b\u0003\u0010\u0004\u001a#\u0010\u0007\u001a\u00020\u0005*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00002\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0007\u0010\b\u001a\u0011\u0010\t\u001a\u00020\u0005*\u00020\u0000\u00a2\u0006\u0004\b\t\u0010\n\u00a8\u0006\u000b"}, d2 = {"", "", "other", "plus", "(CLjava/lang/String;)Ljava/lang/String;", "", "ignoreCase", "equals", "(CCZ)Z", "isSurrogate", "(C)Z", "kotlin-stdlib"}, k = 5, mv = {1, 4, 0}, xs = "kotlin/text/CharsKt")
/* loaded from: classes11.dex */
class CharsKt__CharKt extends CharsKt__CharJVMKt {
    public static final boolean equals(char c2, char c3, boolean z) {
        if (c2 == c3) {
            return true;
        }
        if (z) {
            return Character.toUpperCase(c2) == Character.toUpperCase(c3) || Character.toLowerCase(c2) == Character.toLowerCase(c3);
        }
        return false;
    }

    public static /* synthetic */ boolean equals$default(char c2, char c3, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        return equals(c2, c3, z);
    }

    public static final boolean isSurrogate(char c2) {
        return '\ud800' <= c2 && '\udfff' >= c2;
    }

    @InlineOnly
    private static final String plus(char c2, String str) {
        return String.valueOf(c2) + str;
    }
}
