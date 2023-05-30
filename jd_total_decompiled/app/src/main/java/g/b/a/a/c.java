package g.b.a.a;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import java.util.List;

@GwtCompatible
/* loaded from: classes12.dex */
final class c {
    private static final Joiner a = Joiner.on("");

    /* JADX WARN: Code restructure failed: missing block: B:23:0x004f, code lost:
        if (r3 != ',') goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0051, code lost:
        if (r2 >= r0) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0053, code lost:
        r2 = r2 + a(r9, r10.subSequence(r2, r0), r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0060, code lost:
        if (r10.charAt(r2) == '?') goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0066, code lost:
        if (r10.charAt(r2) != ',') goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0068, code lost:
        r2 = r2 + 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static int a(List<CharSequence> list, CharSequence charSequence, ImmutableMap.Builder<String, b> builder) {
        int length = charSequence.length();
        int i2 = 0;
        char c2 = 0;
        while (i2 < length && (c2 = charSequence.charAt(i2)) != '&' && c2 != '?' && c2 != '!' && c2 != ':' && c2 != ',') {
            i2++;
        }
        list.add(0, c(charSequence.subSequence(0, i2)));
        if (c2 == '!' || c2 == '?' || c2 == ':' || c2 == ',') {
            String join = a.join(list);
            if (join.length() > 0) {
                builder.put(join, b.a(c2));
            }
        }
        int i3 = i2 + 1;
        if (c2 != '?') {
        }
        list.remove(0);
        return i3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ImmutableMap<String, b> b(CharSequence charSequence) {
        ImmutableMap.Builder builder = ImmutableMap.builder();
        int length = charSequence.length();
        int i2 = 0;
        while (i2 < length) {
            i2 += a(Lists.newLinkedList(), charSequence.subSequence(i2, length), builder);
        }
        return builder.build();
    }

    private static CharSequence c(CharSequence charSequence) {
        return new StringBuilder(charSequence).reverse();
    }
}
