package com.meizu.cloud.pushsdk.e.d;

import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import kotlin.text.Typography;
import org.eclipse.paho.client.mqttv3.MqttTopic;

/* loaded from: classes14.dex */
public class f {

    /* renamed from: j */
    private static final char[] f15799j = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private final String a;
    private final String b;

    /* renamed from: c */
    private final String f15800c;
    private final String d;

    /* renamed from: e */
    private final int f15801e;

    /* renamed from: f */
    private final List<String> f15802f;

    /* renamed from: g */
    private final List<String> f15803g;

    /* renamed from: h */
    private final String f15804h;

    /* renamed from: i */
    private final String f15805i;

    /* loaded from: classes14.dex */
    public static final class b {
        String a;
        String d;

        /* renamed from: f */
        final List<String> f15808f;

        /* renamed from: g */
        List<String> f15809g;

        /* renamed from: h */
        String f15810h;
        String b = "";

        /* renamed from: c */
        String f15806c = "";

        /* renamed from: e */
        int f15807e = -1;

        /* loaded from: classes14.dex */
        public enum a {
            SUCCESS,
            MISSING_SCHEME,
            UNSUPPORTED_SCHEME,
            INVALID_PORT,
            INVALID_HOST
        }

        public b() {
            ArrayList arrayList = new ArrayList();
            this.f15808f = arrayList;
            arrayList.add("");
        }

        private static String e(String str, int i2, int i3) {
            String t = f.t(str, i2, i3, false);
            if (t.contains(":")) {
                InetAddress j2 = (t.startsWith("[") && t.endsWith("]")) ? j(t, 1, t.length() - 1) : j(t, 0, t.length());
                if (j2 == null) {
                    return null;
                }
                byte[] address = j2.getAddress();
                if (address.length == 16) {
                    return f(address);
                }
                throw new AssertionError();
            }
            return m.i(t);
        }

        private static String f(byte[] bArr) {
            int i2 = 0;
            int i3 = -1;
            int i4 = 0;
            int i5 = 0;
            while (i4 < bArr.length) {
                int i6 = i4;
                while (i6 < 16 && bArr[i6] == 0 && bArr[i6 + 1] == 0) {
                    i6 += 2;
                }
                int i7 = i6 - i4;
                if (i7 > i5) {
                    i3 = i4;
                    i5 = i7;
                }
                i4 = i6 + 2;
            }
            com.meizu.cloud.pushsdk.e.h.b bVar = new com.meizu.cloud.pushsdk.e.h.b();
            while (i2 < bArr.length) {
                if (i2 == i3) {
                    bVar.j(58);
                    i2 += i5;
                    if (i2 == 16) {
                        bVar.j(58);
                    }
                } else {
                    if (i2 > 0) {
                        bVar.j(58);
                    }
                    bVar.t(((bArr[i2] & 255) << 8) | (bArr[i2 + 1] & 255));
                    i2 += 2;
                }
            }
            return bVar.a();
        }

        private void g(String str, int i2, int i3, boolean z, boolean z2) {
            String s = f.s(str, i2, i3, " \"<>^`{}|/\\?#", z2, false, false, true);
            if (k(s)) {
                return;
            }
            if (n(s)) {
                m();
                return;
            }
            if (this.f15808f.get(r10.size() - 1).isEmpty()) {
                this.f15808f.set(r10.size() - 1, s);
            } else {
                this.f15808f.add(s);
            }
            if (z) {
                this.f15808f.add("");
            }
        }

        private static boolean h(String str, int i2, int i3, byte[] bArr, int i4) {
            int i5 = i4;
            while (i2 < i3) {
                if (i5 == bArr.length) {
                    return false;
                }
                if (i5 != i4) {
                    if (str.charAt(i2) != '.') {
                        return false;
                    }
                    i2++;
                }
                int i6 = i2;
                int i7 = 0;
                while (i6 < i3) {
                    char charAt = str.charAt(i6);
                    if (charAt < '0' || charAt > '9') {
                        break;
                    } else if ((i7 == 0 && i2 != i6) || (i7 = ((i7 * 10) + charAt) - 48) > 255) {
                        return false;
                    } else {
                        i6++;
                    }
                }
                if (i6 - i2 == 0) {
                    return false;
                }
                bArr[i5] = (byte) i7;
                i5++;
                i2 = i6;
            }
            return i5 == i4 + 4;
        }

        /* JADX WARN: Code restructure failed: missing block: B:165:0x0079, code lost:
            return null;
         */
        /* JADX WARN: Removed duplicated region for block: B:155:0x004f  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        private static InetAddress j(String str, int i2, int i3) {
            int i4;
            byte[] bArr = new byte[16];
            int i5 = 0;
            int i6 = -1;
            int i7 = -1;
            while (true) {
                if (i2 < i3) {
                    if (i5 != 16) {
                        int i8 = i2 + 2;
                        if (i8 <= i3 && str.regionMatches(i2, "::", 0, 2)) {
                            if (i6 == -1) {
                                i5 += 2;
                                i6 = i5;
                                if (i8 != i3) {
                                    i7 = i8;
                                    i2 = i7;
                                    int i9 = 0;
                                    while (i2 < i3) {
                                    }
                                    i4 = i2 - i7;
                                    if (i4 == 0) {
                                        break;
                                    }
                                    break;
                                }
                                break;
                            }
                            return null;
                        }
                        if (i5 != 0) {
                            if (str.regionMatches(i2, ":", 0, 1)) {
                                i2++;
                            } else if (!str.regionMatches(i2, OrderISVUtil.MONEY_DECIMAL, 0, 1) || !h(str, i7, i3, bArr, i5 - 2)) {
                                return null;
                            } else {
                                i5 += 2;
                            }
                        }
                        i7 = i2;
                        i2 = i7;
                        int i92 = 0;
                        while (i2 < i3) {
                            int o = f.o(str.charAt(i2));
                            if (o == -1) {
                                break;
                            }
                            i92 = (i92 << 4) + o;
                            i2++;
                        }
                        i4 = i2 - i7;
                        if (i4 == 0 || i4 > 4) {
                            break;
                        }
                        int i10 = i5 + 1;
                        bArr[i5] = (byte) ((i92 >>> 8) & 255);
                        i5 = i10 + 1;
                        bArr[i10] = (byte) (i92 & 255);
                    } else {
                        return null;
                    }
                } else {
                    break;
                }
            }
            if (i5 != 16) {
                if (i6 == -1) {
                    return null;
                }
                int i11 = i5 - i6;
                System.arraycopy(bArr, i6, bArr, 16 - i11, i11);
                Arrays.fill(bArr, i6, (16 - i5) + i6, (byte) 0);
            }
            try {
                return InetAddress.getByAddress(bArr);
            } catch (UnknownHostException unused) {
                throw new AssertionError();
            }
        }

        private boolean k(String str) {
            return OrderISVUtil.MONEY_DECIMAL.equals(str) || "%2e".equalsIgnoreCase(str);
        }

        private static int l(String str, int i2, int i3) {
            int parseInt;
            try {
                parseInt = Integer.parseInt(f.s(str, i2, i3, "", false, false, false, true));
            } catch (NumberFormatException unused) {
            }
            if (parseInt <= 0 || parseInt > 65535) {
                return -1;
            }
            return parseInt;
        }

        private void m() {
            if (!this.f15808f.remove(r0.size() - 1).isEmpty() || this.f15808f.isEmpty()) {
                this.f15808f.add("");
                return;
            }
            this.f15808f.set(r0.size() - 1, "");
        }

        private boolean n(String str) {
            return "..".equals(str) || "%2e.".equalsIgnoreCase(str) || ".%2e".equalsIgnoreCase(str) || "%2e%2e".equalsIgnoreCase(str);
        }

        private static int o(String str, int i2, int i3) {
            while (i2 < i3) {
                char charAt = str.charAt(i2);
                if (charAt == ':') {
                    return i2;
                }
                if (charAt != '[') {
                    i2++;
                }
                do {
                    i2++;
                    if (i2 < i3) {
                    }
                    i2++;
                } while (str.charAt(i2) != ']');
                i2++;
            }
            return i3;
        }

        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:56)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:30)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:18)
            */
        /* JADX WARN: Removed duplicated region for block: B:66:0x002d  */
        /* JADX WARN: Removed duplicated region for block: B:75:0x0043 A[SYNTHETIC] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:63:0x0028 -> B:64:0x002a). Please submit an issue!!! */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        private void p(java.lang.String r11, int r12, int r13) {
            /*
                r10 = this;
                if (r12 != r13) goto L3
                return
            L3:
                char r0 = r11.charAt(r12)
                r1 = 47
                java.lang.String r2 = ""
                r3 = 1
                if (r0 == r1) goto L1e
                r1 = 92
                if (r0 != r1) goto L13
                goto L1e
            L13:
                java.util.List<java.lang.String> r0 = r10.f15808f
                int r1 = r0.size()
                int r1 = r1 - r3
                r0.set(r1, r2)
                goto L2a
            L1e:
                java.util.List<java.lang.String> r0 = r10.f15808f
                r0.clear()
                java.util.List<java.lang.String> r0 = r10.f15808f
                r0.add(r2)
            L28:
                int r12 = r12 + 1
            L2a:
                r6 = r12
                if (r6 >= r13) goto L43
                java.lang.String r12 = "/\\"
                int r12 = com.meizu.cloud.pushsdk.e.d.m.c(r11, r6, r13, r12)
                if (r12 >= r13) goto L37
                r0 = 1
                goto L38
            L37:
                r0 = 0
            L38:
                r9 = 1
                r4 = r10
                r5 = r11
                r7 = r12
                r8 = r0
                r4.g(r5, r6, r7, r8, r9)
                if (r0 == 0) goto L2a
                goto L28
            L43:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.meizu.cloud.pushsdk.e.d.f.b.p(java.lang.String, int, int):void");
        }

        private static int q(String str, int i2, int i3) {
            if (i3 - i2 < 2) {
                return -1;
            }
            char charAt = str.charAt(i2);
            boolean z = charAt < 'a' || charAt > 'z';
            boolean z2 = charAt < 'A' || charAt > 'Z';
            if (z && z2) {
                return -1;
            }
            while (true) {
                i2++;
                if (i2 >= i3) {
                    break;
                }
                char charAt2 = str.charAt(i2);
                if (charAt2 < 'a' || charAt2 > 'z') {
                    if (charAt2 < 'A' || charAt2 > 'Z') {
                        if (charAt2 < '0' || charAt2 > '9') {
                            if (charAt2 != '+' && charAt2 != '-' && charAt2 != '.') {
                                if (charAt2 == ':') {
                                    return i2;
                                }
                            }
                        }
                    }
                }
            }
            return -1;
        }

        private static int r(String str, int i2, int i3) {
            int i4 = 0;
            while (i2 < i3) {
                char charAt = str.charAt(i2);
                if (charAt != '\\' && charAt != '/') {
                    break;
                }
                i4++;
                i2++;
            }
            return i4;
        }

        a a(f fVar, String str) {
            int c2;
            int i2;
            int a2 = m.a(str, 0, str.length());
            int h2 = m.h(str, a2, str.length());
            if (q(str, a2, h2) != -1) {
                if (str.regionMatches(true, a2, "https:", 0, 6)) {
                    this.a = "https";
                    a2 += 6;
                } else if (!str.regionMatches(true, a2, "http:", 0, 5)) {
                    return a.UNSUPPORTED_SCHEME;
                } else {
                    this.a = "http";
                    a2 += 5;
                }
            } else if (fVar == null) {
                return a.MISSING_SCHEME;
            } else {
                this.a = fVar.a;
            }
            int r = r(str, a2, h2);
            char c3 = '?';
            char c4 = '#';
            if (r >= 2 || fVar == null || !fVar.a.equals(this.a)) {
                int i3 = a2 + r;
                boolean z = false;
                boolean z2 = false;
                while (true) {
                    c2 = m.c(str, i3, h2, "@/\\?#");
                    char charAt = c2 != h2 ? str.charAt(c2) : '\uffff';
                    if (charAt == '\uffff' || charAt == c4 || charAt == '/' || charAt == '\\' || charAt == c3) {
                        break;
                    } else if (charAt == '@') {
                        if (z) {
                            i2 = c2;
                            this.f15806c += "%40" + f.s(str, i3, i2, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true);
                        } else {
                            int b = m.b(str, i3, c2, ':');
                            i2 = c2;
                            String s = f.s(str, i3, b, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true);
                            if (z2) {
                                s = this.b + "%40" + s;
                            }
                            this.b = s;
                            if (b != i2) {
                                this.f15806c = f.s(str, b + 1, i2, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true);
                                z = true;
                            }
                            z2 = true;
                        }
                        i3 = i2 + 1;
                        c3 = '?';
                        c4 = '#';
                    }
                }
                int o = o(str, i3, c2);
                int i4 = o + 1;
                this.d = e(str, i3, o);
                if (i4 < c2) {
                    int l2 = l(str, i4, c2);
                    this.f15807e = l2;
                    if (l2 == -1) {
                        return a.INVALID_PORT;
                    }
                } else {
                    this.f15807e = f.b(this.a);
                }
                if (this.d == null) {
                    return a.INVALID_HOST;
                }
                a2 = c2;
            } else {
                this.b = fVar.z();
                this.f15806c = fVar.q();
                this.d = fVar.d;
                this.f15807e = fVar.f15801e;
                this.f15808f.clear();
                this.f15808f.addAll(fVar.w());
                if (a2 == h2 || str.charAt(a2) == '#') {
                    b(fVar.y());
                }
            }
            int c5 = m.c(str, a2, h2, "?#");
            p(str, a2, c5);
            if (c5 < h2 && str.charAt(c5) == '?') {
                int b2 = m.b(str, c5, h2, '#');
                this.f15809g = f.x(f.s(str, c5 + 1, b2, " \"'<>#", true, false, true, true));
                c5 = b2;
            }
            if (c5 < h2 && str.charAt(c5) == '#') {
                this.f15810h = f.s(str, 1 + c5, h2, "", true, false, false, false);
            }
            return a.SUCCESS;
        }

        public b b(String str) {
            this.f15809g = str != null ? f.x(f.g(str, " \"'<>#", true, false, true, true)) : null;
            return this;
        }

        public b c(String str, String str2) {
            if (str != null) {
                if (this.f15809g == null) {
                    this.f15809g = new ArrayList();
                }
                this.f15809g.add(f.g(str, " \"'<>#&=", false, false, true, true));
                this.f15809g.add(str2 != null ? f.g(str2, " \"'<>#&=", false, false, true, true) : null);
                return this;
            }
            throw new IllegalArgumentException("name == null");
        }

        public f d() {
            if (this.a != null) {
                if (this.d != null) {
                    return new f(this);
                }
                throw new IllegalStateException("host == null");
            }
            throw new IllegalStateException("scheme == null");
        }

        int i() {
            int i2 = this.f15807e;
            return i2 != -1 ? i2 : f.b(this.a);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.a);
            sb.append("://");
            if (!this.b.isEmpty() || !this.f15806c.isEmpty()) {
                sb.append(this.b);
                if (!this.f15806c.isEmpty()) {
                    sb.append(':');
                    sb.append(this.f15806c);
                }
                sb.append('@');
            }
            if (this.d.indexOf(58) != -1) {
                sb.append('[');
                sb.append(this.d);
                sb.append(']');
            } else {
                sb.append(this.d);
            }
            int i2 = i();
            if (i2 != f.b(this.a)) {
                sb.append(':');
                sb.append(i2);
            }
            f.u(sb, this.f15808f);
            if (this.f15809g != null) {
                sb.append('?');
                f.l(sb, this.f15809g);
            }
            if (this.f15810h != null) {
                sb.append('#');
                sb.append(this.f15810h);
            }
            return sb.toString();
        }
    }

    private f(b bVar) {
        this.a = bVar.a;
        this.b = h(bVar.b, false);
        this.f15800c = h(bVar.f15806c, false);
        this.d = bVar.d;
        this.f15801e = bVar.i();
        this.f15802f = i(bVar.f15808f, false);
        List<String> list = bVar.f15809g;
        this.f15803g = list != null ? i(list, true) : null;
        String str = bVar.f15810h;
        this.f15804h = str != null ? h(str, false) : null;
        this.f15805i = bVar.toString();
    }

    public static int b(String str) {
        if ("http".equals(str)) {
            return 80;
        }
        return "https".equals(str) ? 443 : -1;
    }

    public static String g(String str, String str2, boolean z, boolean z2, boolean z3, boolean z4) {
        return s(str, 0, str.length(), str2, z, z2, z3, z4);
    }

    static String h(String str, boolean z) {
        return t(str, 0, str.length(), z);
    }

    private List<String> i(List<String> list, boolean z) {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            String next = it.next();
            arrayList.add(next != null ? h(next, z) : null);
        }
        return Collections.unmodifiableList(arrayList);
    }

    private static void j(com.meizu.cloud.pushsdk.e.h.b bVar, String str, int i2, int i3, String str2, boolean z, boolean z2, boolean z3, boolean z4) {
        com.meizu.cloud.pushsdk.e.h.b bVar2 = null;
        int i4 = i2;
        while (i4 < i3) {
            int codePointAt = str.codePointAt(i4);
            if (codePointAt == 43 && z3) {
                bVar.l(z ? MqttTopic.SINGLE_LEVEL_WILDCARD : "%2B");
            } else if (m(codePointAt, i4, str, i3, str2, z, z2, z3, z4)) {
                if (bVar2 == null) {
                    bVar2 = new com.meizu.cloud.pushsdk.e.h.b();
                }
                bVar2.o(codePointAt);
                while (!bVar2.x()) {
                    int y = bVar2.y() & 255;
                    bVar.j(37);
                    char[] cArr = f15799j;
                    bVar.j(cArr[(y >> 4) & 15]);
                    bVar.j(cArr[y & 15]);
                }
            } else {
                bVar.o(codePointAt);
            }
            i4 += Character.charCount(codePointAt);
        }
    }

    private static void k(com.meizu.cloud.pushsdk.e.h.b bVar, String str, int i2, int i3, boolean z) {
        int i4;
        while (i2 < i3) {
            int codePointAt = str.codePointAt(i2);
            if (codePointAt != 37 || (i4 = i2 + 2) >= i3) {
                if (codePointAt == 43 && z) {
                    bVar.j(32);
                }
                bVar.o(codePointAt);
            } else {
                int o = o(str.charAt(i2 + 1));
                int o2 = o(str.charAt(i4));
                if (o != -1 && o2 != -1) {
                    bVar.j((o << 4) + o2);
                    i2 = i4;
                }
                bVar.o(codePointAt);
            }
            i2 += Character.charCount(codePointAt);
        }
    }

    static void l(StringBuilder sb, List<String> list) {
        int size = list.size();
        for (int i2 = 0; i2 < size; i2 += 2) {
            String str = list.get(i2);
            String str2 = list.get(i2 + 1);
            if (i2 > 0) {
                sb.append(Typography.amp);
            }
            sb.append(str);
            if (str2 != null) {
                sb.append('=');
                sb.append(str2);
            }
        }
    }

    private static boolean m(int i2, int i3, String str, int i4, String str2, boolean z, boolean z2, boolean z3, boolean z4) {
        if (i2 < 32 || i2 == 127) {
            return true;
        }
        if ((i2 < 128 || !z4) && str2.indexOf(i2) == -1) {
            boolean z5 = !z || (z2 && !n(str, i3, i4));
            if (i2 == 37 && z5) {
                return true;
            }
            return i2 == 43 && z3;
        }
        return true;
    }

    private static boolean n(String str, int i2, int i3) {
        int i4 = i2 + 2;
        return i4 < i3 && str.charAt(i2) == '%' && o(str.charAt(i2 + 1)) != -1 && o(str.charAt(i4)) != -1;
    }

    public static int o(char c2) {
        if (c2 < '0' || c2 > '9') {
            char c3 = 'a';
            if (c2 < 'a' || c2 > 'f') {
                c3 = 'A';
                if (c2 < 'A' || c2 > 'F') {
                    return -1;
                }
            }
            return (c2 - c3) + 10;
        }
        return c2 - '0';
    }

    public static f p(String str) {
        b bVar = new b();
        if (bVar.a(null, str) == b.a.SUCCESS) {
            return bVar.d();
        }
        return null;
    }

    public static String s(String str, int i2, int i3, String str2, boolean z, boolean z2, boolean z3, boolean z4) {
        int i4 = i2;
        while (i4 < i3) {
            int codePointAt = str.codePointAt(i4);
            if (m(codePointAt, i4, str, i3, str2, z, z2, z3, z4)) {
                com.meizu.cloud.pushsdk.e.h.b bVar = new com.meizu.cloud.pushsdk.e.h.b();
                bVar.f(str, i2, i4);
                j(bVar, str, i4, i3, str2, z, z2, z3, z4);
                return bVar.a();
            }
            i4 += Character.charCount(codePointAt);
        }
        return str.substring(i2, i3);
    }

    public static String t(String str, int i2, int i3, boolean z) {
        for (int i4 = i2; i4 < i3; i4++) {
            char charAt = str.charAt(i4);
            boolean z2 = false;
            boolean z3 = charAt == '%';
            if (charAt == '+' && z) {
                z2 = true;
            }
            if (z3 || z2) {
                com.meizu.cloud.pushsdk.e.h.b bVar = new com.meizu.cloud.pushsdk.e.h.b();
                bVar.f(str, i2, i4);
                k(bVar, str, i4, i3, z);
                return bVar.a();
            }
        }
        return str.substring(i2, i3);
    }

    static void u(StringBuilder sb, List<String> list) {
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            sb.append('/');
            sb.append(list.get(i2));
        }
    }

    static List<String> x(String str) {
        String str2;
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        while (i2 <= str.length()) {
            int indexOf = str.indexOf(38, i2);
            if (indexOf == -1) {
                indexOf = str.length();
            }
            int indexOf2 = str.indexOf(61, i2);
            if (indexOf2 == -1 || indexOf2 > indexOf) {
                arrayList.add(str.substring(i2, indexOf));
                str2 = null;
            } else {
                arrayList.add(str.substring(i2, indexOf2));
                str2 = str.substring(indexOf2 + 1, indexOf);
            }
            arrayList.add(str2);
            i2 = indexOf + 1;
        }
        return arrayList;
    }

    public b A() {
        b bVar = new b();
        bVar.a = this.a;
        bVar.b = z();
        bVar.f15806c = q();
        bVar.d = this.d;
        bVar.f15807e = this.f15801e != b(this.a) ? this.f15801e : -1;
        bVar.f15808f.clear();
        bVar.f15808f.addAll(w());
        bVar.b(y());
        bVar.f15810h = c();
        return bVar;
    }

    public String c() {
        if (this.f15804h == null) {
            return null;
        }
        return this.f15805i.substring(this.f15805i.indexOf(35) + 1);
    }

    public boolean equals(Object obj) {
        return (obj instanceof f) && ((f) obj).f15805i.equals(this.f15805i);
    }

    public int hashCode() {
        return this.f15805i.hashCode();
    }

    public String q() {
        if (this.f15800c.isEmpty()) {
            return "";
        }
        int indexOf = this.f15805i.indexOf(64);
        return this.f15805i.substring(this.f15805i.indexOf(58, this.a.length() + 3) + 1, indexOf);
    }

    public String toString() {
        return this.f15805i;
    }

    public List<String> w() {
        int indexOf = this.f15805i.indexOf(47, this.a.length() + 3);
        String str = this.f15805i;
        int c2 = m.c(str, indexOf, str.length(), "?#");
        ArrayList arrayList = new ArrayList();
        while (indexOf < c2) {
            int i2 = indexOf + 1;
            int b2 = m.b(this.f15805i, i2, c2, '/');
            arrayList.add(this.f15805i.substring(i2, b2));
            indexOf = b2;
        }
        return arrayList;
    }

    public String y() {
        if (this.f15803g == null) {
            return null;
        }
        int indexOf = this.f15805i.indexOf(63) + 1;
        String str = this.f15805i;
        return this.f15805i.substring(indexOf, m.b(str, indexOf + 1, str.length(), '#'));
    }

    public String z() {
        if (this.b.isEmpty()) {
            return "";
        }
        int length = this.a.length() + 3;
        String str = this.f15805i;
        return this.f15805i.substring(length, m.c(str, length, str.length(), ":@"));
    }
}
