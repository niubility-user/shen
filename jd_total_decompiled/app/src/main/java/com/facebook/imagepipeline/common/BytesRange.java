package com.facebook.imagepipeline.common;

import com.facebook.common.internal.Preconditions;
import com.facebook.common.util.HashCodeUtil;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Immutable
/* loaded from: classes.dex */
public class BytesRange {
    public static final int TO_END_OF_CONTENT = Integer.MAX_VALUE;
    @Nullable
    private static Pattern sHeaderParsingRegEx;
    public final int from;
    public final int to;

    public BytesRange(int i2, int i3) {
        this.from = i2;
        this.to = i3;
    }

    public static BytesRange from(int i2) {
        Preconditions.checkArgument(i2 >= 0);
        return new BytesRange(i2, Integer.MAX_VALUE);
    }

    @Nullable
    public static BytesRange fromContentRangeHeader(@Nullable String str) {
        if (str == null) {
            return null;
        }
        if (sHeaderParsingRegEx == null) {
            sHeaderParsingRegEx = Pattern.compile("[-/ ]");
        }
        try {
            String[] split = sHeaderParsingRegEx.split(str);
            Preconditions.checkArgument(split.length == 4);
            Preconditions.checkArgument(split[0].equals("bytes"));
            int parseInt = Integer.parseInt(split[1]);
            int parseInt2 = Integer.parseInt(split[2]);
            int parseInt3 = Integer.parseInt(split[3]);
            Preconditions.checkArgument(parseInt2 > parseInt);
            Preconditions.checkArgument(parseInt3 > parseInt2);
            return parseInt2 < parseInt3 - 1 ? new BytesRange(parseInt, parseInt2) : new BytesRange(parseInt, Integer.MAX_VALUE);
        } catch (IllegalArgumentException e2) {
            throw new IllegalArgumentException(String.format(null, "Invalid Content-Range header value: \"%s\"", str), e2);
        }
    }

    public static BytesRange toMax(int i2) {
        Preconditions.checkArgument(i2 > 0);
        return new BytesRange(0, i2);
    }

    private static String valueOrEmpty(int i2) {
        return i2 == Integer.MAX_VALUE ? "" : Integer.toString(i2);
    }

    public boolean contains(@Nullable BytesRange bytesRange) {
        return bytesRange != null && this.from <= bytesRange.from && this.to >= bytesRange.to;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof BytesRange) {
            BytesRange bytesRange = (BytesRange) obj;
            return this.from == bytesRange.from && this.to == bytesRange.to;
        }
        return false;
    }

    public int hashCode() {
        return HashCodeUtil.hashCode(this.from, this.to);
    }

    public String toHttpRangeHeaderValue() {
        return String.format(null, "bytes=%s-%s", valueOrEmpty(this.from), valueOrEmpty(this.to));
    }

    public String toString() {
        return String.format(null, "%s-%s", valueOrEmpty(this.from), valueOrEmpty(this.to));
    }
}
