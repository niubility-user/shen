package com.xiaomi.push;

import com.jingdong.common.utils.LangUtils;
import java.nio.ByteBuffer;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/* loaded from: classes11.dex */
public final class o8 {
    private static final Comparator a = new a();

    /* loaded from: classes11.dex */
    private static class a implements Comparator {
        private a() {
        }

        @Override // java.util.Comparator
        public int compare(Object obj, Object obj2) {
            if (obj == null && obj2 == null) {
                return 0;
            }
            if (obj == null) {
                return -1;
            }
            if (obj2 == null) {
                return 1;
            }
            return obj instanceof List ? o8.g((List) obj, (List) obj2) : obj instanceof Set ? o8.i((Set) obj, (Set) obj2) : obj instanceof Map ? o8.h((Map) obj, (Map) obj2) : obj instanceof byte[] ? o8.l((byte[]) obj, (byte[]) obj2) : o8.d((Comparable) obj, (Comparable) obj2);
        }
    }

    public static int a(byte b, byte b2) {
        if (b < b2) {
            return -1;
        }
        return b2 < b ? 1 : 0;
    }

    public static int b(int i2, int i3) {
        if (i2 < i3) {
            return -1;
        }
        return i3 < i2 ? 1 : 0;
    }

    public static int c(long j2, long j3) {
        if (j2 < j3) {
            return -1;
        }
        return j3 < j2 ? 1 : 0;
    }

    public static int d(Comparable comparable, Comparable comparable2) {
        return comparable.compareTo(comparable2);
    }

    public static int e(String str, String str2) {
        return str.compareTo(str2);
    }

    public static int f(ByteBuffer byteBuffer, byte[] bArr, int i2) {
        int remaining = byteBuffer.remaining();
        System.arraycopy(byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), bArr, i2, remaining);
        return remaining;
    }

    public static int g(List list, List list2) {
        int b = b(list.size(), list2.size());
        if (b != 0) {
            return b;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            int compare = a.compare(list.get(i2), list2.get(i2));
            if (compare != 0) {
                return compare;
            }
        }
        return 0;
    }

    public static int h(Map map, Map map2) {
        int b = b(map.size(), map2.size());
        if (b != 0) {
            return b;
        }
        Comparator comparator = a;
        TreeMap treeMap = new TreeMap(comparator);
        treeMap.putAll(map);
        Iterator it = treeMap.entrySet().iterator();
        TreeMap treeMap2 = new TreeMap(comparator);
        treeMap2.putAll(map2);
        Iterator it2 = treeMap2.entrySet().iterator();
        while (it.hasNext() && it2.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Map.Entry entry2 = (Map.Entry) it2.next();
            Comparator comparator2 = a;
            int compare = comparator2.compare(entry.getKey(), entry2.getKey());
            if (compare != 0) {
                return compare;
            }
            int compare2 = comparator2.compare(entry.getValue(), entry2.getValue());
            if (compare2 != 0) {
                return compare2;
            }
        }
        return 0;
    }

    public static int i(Set set, Set set2) {
        int b = b(set.size(), set2.size());
        if (b != 0) {
            return b;
        }
        Comparator comparator = a;
        TreeSet treeSet = new TreeSet(comparator);
        treeSet.addAll(set);
        TreeSet treeSet2 = new TreeSet(comparator);
        treeSet2.addAll(set2);
        Iterator it = treeSet.iterator();
        Iterator it2 = treeSet2.iterator();
        while (it.hasNext() && it2.hasNext()) {
            int compare = a.compare(it.next(), it2.next());
            if (compare != 0) {
                return compare;
            }
        }
        return 0;
    }

    public static int j(short s, short s2) {
        if (s < s2) {
            return -1;
        }
        return s2 < s ? 1 : 0;
    }

    public static int k(boolean z, boolean z2) {
        return Boolean.valueOf(z).compareTo(Boolean.valueOf(z2));
    }

    public static int l(byte[] bArr, byte[] bArr2) {
        int b = b(bArr.length, bArr2.length);
        if (b != 0) {
            return b;
        }
        for (int i2 = 0; i2 < bArr.length; i2++) {
            int a2 = a(bArr[i2], bArr2[i2]);
            if (a2 != 0) {
                return a2;
            }
        }
        return 0;
    }

    public static String m(byte b) {
        return Integer.toHexString((b | 256) & 511).toUpperCase().substring(1);
    }

    public static ByteBuffer n(ByteBuffer byteBuffer) {
        return p(byteBuffer) ? byteBuffer : ByteBuffer.wrap(q(byteBuffer));
    }

    public static void o(ByteBuffer byteBuffer, StringBuilder sb) {
        byte[] array = byteBuffer.array();
        int arrayOffset = byteBuffer.arrayOffset();
        int limit = byteBuffer.limit();
        int i2 = limit - arrayOffset > 128 ? arrayOffset + 128 : limit;
        for (int i3 = arrayOffset; i3 < i2; i3++) {
            if (i3 > arrayOffset) {
                sb.append(LangUtils.SINGLE_SPACE);
            }
            sb.append(m(array[i3]));
        }
        if (limit != i2) {
            sb.append("...");
        }
    }

    public static boolean p(ByteBuffer byteBuffer) {
        return byteBuffer.hasArray() && byteBuffer.position() == 0 && byteBuffer.arrayOffset() == 0 && byteBuffer.remaining() == byteBuffer.capacity();
    }

    public static byte[] q(ByteBuffer byteBuffer) {
        if (p(byteBuffer)) {
            return byteBuffer.array();
        }
        byte[] bArr = new byte[byteBuffer.remaining()];
        f(byteBuffer, bArr, 0);
        return bArr;
    }
}
