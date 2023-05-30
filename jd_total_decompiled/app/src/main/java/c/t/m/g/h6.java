package c.t.m.g;

import java.util.Iterator;

/* loaded from: classes.dex */
public class h6 {
    public final String a;

    public h6(String str) {
        b(str);
        this.a = str;
    }

    public static h6 e(String str) {
        return new h6(str);
    }

    public final String a(Iterable<?> iterable) {
        return c(iterable.iterator());
    }

    public final String b(String str) {
        str.getClass();
        return str;
    }

    public final String c(Iterator<?> it) {
        StringBuilder sb = new StringBuilder();
        d(sb, it);
        return sb.toString();
    }

    public final StringBuilder d(StringBuilder sb, Iterator<?> it) {
        Object next;
        if (it.hasNext() && (next = it.next()) != null) {
            sb.append(next.toString());
        }
        while (it.hasNext()) {
            Object next2 = it.next();
            if (next2 != null) {
                sb.append(this.a);
                sb.append(next2.toString());
            }
        }
        return sb;
    }
}
