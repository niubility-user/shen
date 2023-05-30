package com.jingdong.sdk.baseinfo.c;

import com.jd.dynamic.DYConstants;
import java.util.Collection;
import java.util.Iterator;

/* loaded from: classes.dex */
public final class e {

    /* loaded from: classes7.dex */
    public static class a<T> implements b<T> {
        @Override // com.jingdong.sdk.baseinfo.c.e.b
        public final String toString(T t) {
            return String.valueOf(t);
        }
    }

    /* loaded from: classes.dex */
    public interface b<T> {
        String toString(T t);
    }

    public static <T> String a(Collection<T> collection, b<T> bVar) {
        return a(collection, bVar, DYConstants.DY_REGEX_COMMA);
    }

    public static <T> String a(Collection<T> collection, b<T> bVar, String str) {
        if (collection == null || collection.size() == 0) {
            return "";
        }
        if (bVar == null) {
            bVar = new a<>();
        }
        StringBuilder sb = new StringBuilder();
        Iterator<T> it = collection.iterator();
        while (it.hasNext()) {
            T next = it.next();
            sb.append(next == null ? DYConstants.DY_NULL_STR : bVar.toString(next));
            sb.append(str);
        }
        if (sb.length() > 0) {
            sb.delete(sb.length() - str.length(), sb.length());
        }
        return sb.toString();
    }
}
