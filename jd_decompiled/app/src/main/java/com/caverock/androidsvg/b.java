package com.caverock.androidsvg;

import com.caverock.androidsvg.h;
import com.caverock.androidsvg.l;
import com.facebook.react.uimanager.events.TouchesHelper;
import com.jingdong.common.search.FilterConstant;
import com.tencent.smtt.sdk.ProxyConfig;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class b {
    private f a;
    private u b;

    /* renamed from: c */
    private boolean f829c;

    /* loaded from: classes.dex */
    public static /* synthetic */ class a {
        static final /* synthetic */ int[] a;
        static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[j.values().length];
            b = iArr;
            try {
                iArr[j.first_child.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[j.last_child.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                b[j.only_child.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                b[j.first_of_type.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                b[j.last_of_type.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                b[j.only_of_type.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                b[j.root.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                b[j.empty.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                b[j.nth_child.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                b[j.nth_last_child.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                b[j.nth_of_type.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                b[j.nth_last_of_type.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                b[j.not.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                b[j.target.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                b[j.lang.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                b[j.link.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                b[j.visited.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                b[j.hover.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                b[j.active.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                b[j.focus.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                b[j.enabled.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                b[j.disabled.ordinal()] = 22;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                b[j.checked.ordinal()] = 23;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                b[j.indeterminate.ordinal()] = 24;
            } catch (NoSuchFieldError unused24) {
            }
            int[] iArr2 = new int[c.values().length];
            a = iArr2;
            try {
                iArr2[c.EQUALS.ordinal()] = 1;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                a[c.INCLUDES.ordinal()] = 2;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                a[c.DASHMATCH.ordinal()] = 3;
            } catch (NoSuchFieldError unused27) {
            }
        }
    }

    /* renamed from: com.caverock.androidsvg.b$b */
    /* loaded from: classes.dex */
    public static class C0012b {
        public final String a;
        final c b;

        /* renamed from: c */
        public final String f830c;

        C0012b(String str, c cVar, String str2) {
            this.a = str;
            this.b = cVar;
            this.f830c = str2;
        }
    }

    /* loaded from: classes.dex */
    public enum c {
        EXISTS,
        EQUALS,
        INCLUDES,
        DASHMATCH
    }

    /* loaded from: classes.dex */
    public static class d extends l.i {

        /* loaded from: classes.dex */
        public static class a {
            public int a;
            public int b;

            a(int i2, int i3) {
                this.a = i2;
                this.b = i3;
            }
        }

        d(String str) {
            super(str.replaceAll("(?s)/\\*.*?\\*/", ""));
        }

        private int C(int i2) {
            if (i2 < 48 || i2 > 57) {
                int i3 = 65;
                if (i2 < 65 || i2 > 70) {
                    i3 = 97;
                    if (i2 < 97 || i2 > 102) {
                        return -1;
                    }
                }
                return (i2 - i3) + 10;
            }
            return i2 - 48;
        }

        private a D() throws com.caverock.androidsvg.a {
            com.caverock.androidsvg.d dVar;
            a aVar;
            if (h()) {
                return null;
            }
            int i2 = this.b;
            if (f('(')) {
                A();
                int i3 = 1;
                if (g("odd")) {
                    aVar = new a(2, 1);
                } else {
                    if (g("even")) {
                        aVar = new a(2, 0);
                    } else {
                        int i4 = (!f('+') && f('-')) ? -1 : 1;
                        com.caverock.androidsvg.d c2 = com.caverock.androidsvg.d.c(this.a, this.b, this.f952c, false);
                        if (c2 != null) {
                            this.b = c2.a();
                        }
                        if (f('n') || f('N')) {
                            if (c2 == null) {
                                c2 = new com.caverock.androidsvg.d(1L, this.b);
                            }
                            A();
                            boolean f2 = f('+');
                            if (!f2 && (f2 = f('-'))) {
                                i3 = -1;
                            }
                            if (f2) {
                                A();
                                dVar = com.caverock.androidsvg.d.c(this.a, this.b, this.f952c, false);
                                if (dVar != null) {
                                    this.b = dVar.a();
                                } else {
                                    this.b = i2;
                                    return null;
                                }
                            } else {
                                dVar = null;
                            }
                            int i5 = i3;
                            i3 = i4;
                            i4 = i5;
                        } else {
                            dVar = c2;
                            c2 = null;
                        }
                        aVar = new a(c2 == null ? 0 : i3 * c2.d(), dVar != null ? i4 * dVar.d() : 0);
                    }
                }
                A();
                if (f(')')) {
                    return aVar;
                }
                this.b = i2;
                return null;
            }
            return null;
        }

        private String E() {
            if (h()) {
                return null;
            }
            String q = q();
            return q != null ? q : H();
        }

        private List<String> G() throws com.caverock.androidsvg.a {
            if (h()) {
                return null;
            }
            int i2 = this.b;
            if (f('(')) {
                A();
                ArrayList arrayList = null;
                do {
                    String H = H();
                    if (H == null) {
                        this.b = i2;
                        return null;
                    }
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(H);
                    A();
                } while (z());
                if (f(')')) {
                    return arrayList;
                }
                this.b = i2;
                return null;
            }
            return null;
        }

        private List<s> K() throws com.caverock.androidsvg.a {
            List<t> list;
            List<g> list2;
            if (h()) {
                return null;
            }
            int i2 = this.b;
            if (f('(')) {
                A();
                List<s> L = L();
                if (L == null) {
                    this.b = i2;
                    return null;
                } else if (!f(')')) {
                    this.b = i2;
                    return null;
                } else {
                    Iterator<s> it = L.iterator();
                    while (it.hasNext() && (list = it.next().a) != null) {
                        Iterator<t> it2 = list.iterator();
                        while (it2.hasNext() && (list2 = it2.next().d) != null) {
                            Iterator<g> it3 = list2.iterator();
                            while (it3.hasNext()) {
                                if (it3.next() instanceof k) {
                                    return null;
                                }
                            }
                        }
                    }
                    return L;
                }
            }
            return null;
        }

        public List<s> L() throws com.caverock.androidsvg.a {
            a aVar = null;
            if (h()) {
                return null;
            }
            ArrayList arrayList = new ArrayList(1);
            s sVar = new s(aVar);
            while (!h() && M(sVar)) {
                if (z()) {
                    arrayList.add(sVar);
                    sVar = new s(aVar);
                }
            }
            if (!sVar.f()) {
                arrayList.add(sVar);
            }
            return arrayList;
        }

        /* JADX WARN: Multi-variable type inference failed */
        private void O(s sVar, t tVar) throws com.caverock.androidsvg.a {
            g hVar;
            h hVar2;
            String H = H();
            if (H != null) {
                j fromString = j.fromString(H);
                a aVar = null;
                switch (a.b[fromString.ordinal()]) {
                    case 1:
                        hVar = new h(0, 1, true, false, null);
                        sVar.b();
                        break;
                    case 2:
                        hVar = new h(0, 1, false, false, null);
                        sVar.b();
                        break;
                    case 3:
                        hVar = new m(false, null);
                        sVar.b();
                        break;
                    case 4:
                        hVar = new h(0, 1, true, true, tVar.b);
                        sVar.b();
                        break;
                    case 5:
                        hVar = new h(0, 1, false, true, tVar.b);
                        sVar.b();
                        break;
                    case 6:
                        hVar = new m(true, tVar.b);
                        sVar.b();
                        break;
                    case 7:
                        hVar = new n(aVar);
                        sVar.b();
                        break;
                    case 8:
                        hVar = new i(aVar);
                        sVar.b();
                        break;
                    case 9:
                    case 10:
                    case 11:
                    case 12:
                        boolean z = fromString == j.nth_child || fromString == j.nth_of_type;
                        boolean z2 = fromString == j.nth_of_type || fromString == j.nth_last_of_type;
                        a D = D();
                        if (D != null) {
                            h hVar3 = new h(D.a, D.b, z, z2, tVar.b);
                            sVar.b();
                            hVar2 = hVar3;
                            hVar = hVar2;
                            break;
                        } else {
                            throw new com.caverock.androidsvg.a("Invalid or missing parameter section for pseudo class: " + H);
                        }
                        break;
                    case 13:
                        List<s> K = K();
                        if (K != null) {
                            k kVar = new k(K);
                            sVar.b = kVar.b();
                            hVar2 = kVar;
                            hVar = hVar2;
                            break;
                        } else {
                            throw new com.caverock.androidsvg.a("Invalid or missing parameter section for pseudo class: " + H);
                        }
                    case 14:
                        hVar = new o(aVar);
                        sVar.b();
                        break;
                    case 15:
                        G();
                        hVar = new l(H);
                        sVar.b();
                        break;
                    case 16:
                    case 17:
                    case 18:
                    case 19:
                    case 20:
                    case 21:
                    case 22:
                    case 23:
                    case 24:
                        hVar = new l(H);
                        sVar.b();
                        break;
                    default:
                        throw new com.caverock.androidsvg.a("Unsupported pseudo class: " + H);
                }
                tVar.b(hVar);
                return;
            }
            throw new com.caverock.androidsvg.a("Invalid pseudo class");
        }

        private int P() {
            int i2;
            if (h()) {
                return this.b;
            }
            int i3 = this.b;
            int charAt = this.a.charAt(i3);
            if (charAt == 45) {
                charAt = a();
            }
            if ((charAt < 65 || charAt > 90) && ((charAt < 97 || charAt > 122) && charAt != 95)) {
                i2 = i3;
            } else {
                int a2 = a();
                while (true) {
                    if ((a2 < 65 || a2 > 90) && ((a2 < 97 || a2 > 122) && !((a2 >= 48 && a2 <= 57) || a2 == 45 || a2 == 95))) {
                        break;
                    }
                    a2 = a();
                }
                i2 = this.b;
            }
            this.b = i3;
            return i2;
        }

        String F() {
            int C;
            if (h()) {
                return null;
            }
            char charAt = this.a.charAt(this.b);
            if (charAt == '\'' || charAt == '\"') {
                StringBuilder sb = new StringBuilder();
                this.b++;
                int intValue = l().intValue();
                while (intValue != -1 && intValue != charAt) {
                    if (intValue == 92) {
                        intValue = l().intValue();
                        if (intValue != -1) {
                            if (intValue != 10 && intValue != 13 && intValue != 12) {
                                int C2 = C(intValue);
                                if (C2 != -1) {
                                    for (int i2 = 1; i2 <= 5 && (C = C((intValue = l().intValue()))) != -1; i2++) {
                                        C2 = (C2 * 16) + C;
                                    }
                                    sb.append((char) C2);
                                }
                            } else {
                                intValue = l().intValue();
                            }
                        }
                    }
                    sb.append((char) intValue);
                    intValue = l().intValue();
                }
                return sb.toString();
            }
            return null;
        }

        String H() {
            int P = P();
            int i2 = this.b;
            if (P == i2) {
                return null;
            }
            String substring = this.a.substring(i2, P);
            this.b = P;
            return substring;
        }

        String I() {
            char charAt;
            int C;
            StringBuilder sb = new StringBuilder();
            while (!h() && (charAt = this.a.charAt(this.b)) != '\'' && charAt != '\"' && charAt != '(' && charAt != ')' && !k(charAt) && !Character.isISOControl((int) charAt)) {
                this.b++;
                if (charAt == '\\') {
                    if (!h()) {
                        String str = this.a;
                        int i2 = this.b;
                        this.b = i2 + 1;
                        charAt = str.charAt(i2);
                        if (charAt != '\n' && charAt != '\r' && charAt != '\f') {
                            int C2 = C(charAt);
                            if (C2 != -1) {
                                for (int i3 = 1; i3 <= 5 && !h() && (C = C(this.a.charAt(this.b))) != -1; i3++) {
                                    this.b++;
                                    C2 = (C2 * 16) + C;
                                }
                                sb.append((char) C2);
                            }
                        }
                    }
                }
                sb.append(charAt);
            }
            if (sb.length() == 0) {
                return null;
            }
            return sb.toString();
        }

        String J() {
            if (h()) {
                return null;
            }
            int i2 = this.b;
            int charAt = this.a.charAt(i2);
            int i3 = i2;
            while (charAt != -1 && charAt != 59 && charAt != 125 && charAt != 33 && !j(charAt)) {
                if (!k(charAt)) {
                    i3 = this.b + 1;
                }
                charAt = a();
            }
            if (this.b > i2) {
                return this.a.substring(i2, i3);
            }
            this.b = i2;
            return null;
        }

        /* JADX WARN: Removed duplicated region for block: B:118:0x0036  */
        /* JADX WARN: Removed duplicated region for block: B:119:0x003c  */
        /* JADX WARN: Removed duplicated region for block: B:125:0x0053  */
        /* JADX WARN: Removed duplicated region for block: B:181:0x0130  */
        /* JADX WARN: Removed duplicated region for block: B:183:0x0135  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        boolean M(com.caverock.androidsvg.b.s r11) throws com.caverock.androidsvg.a {
            /*
                Method dump skipped, instructions count: 312
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.caverock.androidsvg.b.d.M(com.caverock.androidsvg.b$s):boolean");
        }

        String N() {
            if (h()) {
                return null;
            }
            int i2 = this.b;
            if (g("url(")) {
                A();
                String F = F();
                if (F == null) {
                    F = I();
                }
                if (F == null) {
                    this.b = i2;
                    return null;
                }
                A();
                if (h() || g(")")) {
                    return F;
                }
                this.b = i2;
                return null;
            }
            return null;
        }
    }

    /* loaded from: classes.dex */
    public enum e {
        DESCENDANT,
        CHILD,
        FOLLOWS
    }

    /* loaded from: classes.dex */
    public enum f {
        all,
        aural,
        braille,
        embossed,
        handheld,
        print,
        projection,
        screen,
        speech,
        tty,
        tv
    }

    /* loaded from: classes.dex */
    public interface g {
        boolean a(q qVar, h.l0 l0Var);
    }

    /* loaded from: classes.dex */
    public static class h implements g {
        private int a;
        private int b;

        /* renamed from: c */
        private boolean f835c;
        private boolean d;

        /* renamed from: e */
        private String f836e;

        h(int i2, int i3, boolean z, boolean z2, String str) {
            this.a = i2;
            this.b = i3;
            this.f835c = z;
            this.d = z2;
            this.f836e = str;
        }

        @Override // com.caverock.androidsvg.b.g
        public boolean a(q qVar, h.l0 l0Var) {
            int i2;
            int i3;
            String n2 = (this.d && this.f836e == null) ? l0Var.n() : this.f836e;
            h.j0 j0Var = l0Var.b;
            if (j0Var != null) {
                Iterator<h.n0> it = j0Var.getChildren().iterator();
                i2 = 0;
                i3 = 0;
                while (it.hasNext()) {
                    h.l0 l0Var2 = (h.l0) it.next();
                    if (l0Var2 == l0Var) {
                        i2 = i3;
                    }
                    if (n2 == null || l0Var2.n().equals(n2)) {
                        i3++;
                    }
                }
            } else {
                i2 = 0;
                i3 = 1;
            }
            int i4 = this.f835c ? i2 + 1 : i3 - i2;
            int i5 = this.a;
            if (i5 == 0) {
                return i4 == this.b;
            }
            int i6 = this.b;
            if ((i4 - i6) % i5 == 0) {
                return Integer.signum(i4 - i6) == 0 || Integer.signum(i4 - this.b) == Integer.signum(this.a);
            }
            return false;
        }

        public String toString() {
            String str = this.f835c ? "" : "last-";
            return this.d ? String.format("nth-%schild(%dn%+d of type <%s>)", str, Integer.valueOf(this.a), Integer.valueOf(this.b), this.f836e) : String.format("nth-%schild(%dn%+d)", str, Integer.valueOf(this.a), Integer.valueOf(this.b));
        }
    }

    /* loaded from: classes.dex */
    public static class i implements g {
        private i() {
        }

        @Override // com.caverock.androidsvg.b.g
        public boolean a(q qVar, h.l0 l0Var) {
            return !(l0Var instanceof h.j0) || ((h.j0) l0Var).getChildren().size() == 0;
        }

        public String toString() {
            return "empty";
        }

        /* synthetic */ i(a aVar) {
            this();
        }
    }

    /* loaded from: classes.dex */
    public enum j {
        target,
        root,
        nth_child,
        nth_last_child,
        nth_of_type,
        nth_last_of_type,
        first_child,
        last_child,
        first_of_type,
        last_of_type,
        only_child,
        only_of_type,
        empty,
        not,
        lang,
        link,
        visited,
        hover,
        active,
        focus,
        enabled,
        disabled,
        checked,
        indeterminate,
        UNSUPPORTED;
        

        /* renamed from: g */
        private static final Map<String, j> f837g = new HashMap();

        static {
            for (j jVar : values()) {
                if (jVar != UNSUPPORTED) {
                    f837g.put(jVar.name().replace('_', '-'), jVar);
                }
            }
        }

        public static j fromString(String str) {
            j jVar = f837g.get(str);
            return jVar != null ? jVar : UNSUPPORTED;
        }
    }

    /* loaded from: classes.dex */
    public static class k implements g {
        private List<s> a;

        k(List<s> list) {
            this.a = list;
        }

        @Override // com.caverock.androidsvg.b.g
        public boolean a(q qVar, h.l0 l0Var) {
            Iterator<s> it = this.a.iterator();
            while (it.hasNext()) {
                if (b.l(qVar, it.next(), l0Var)) {
                    return false;
                }
            }
            return true;
        }

        int b() {
            Iterator<s> it = this.a.iterator();
            int i2 = Integer.MIN_VALUE;
            while (it.hasNext()) {
                int i3 = it.next().b;
                if (i3 > i2) {
                    i2 = i3;
                }
            }
            return i2;
        }

        public String toString() {
            return "not(" + this.a + ")";
        }
    }

    /* loaded from: classes.dex */
    public static class l implements g {
        private String a;

        l(String str) {
            this.a = str;
        }

        @Override // com.caverock.androidsvg.b.g
        public boolean a(q qVar, h.l0 l0Var) {
            return false;
        }

        public String toString() {
            return this.a;
        }
    }

    /* loaded from: classes.dex */
    public static class m implements g {
        private boolean a;
        private String b;

        public m(boolean z, String str) {
            this.a = z;
            this.b = str;
        }

        @Override // com.caverock.androidsvg.b.g
        public boolean a(q qVar, h.l0 l0Var) {
            int i2;
            String n2 = (this.a && this.b == null) ? l0Var.n() : this.b;
            h.j0 j0Var = l0Var.b;
            if (j0Var != null) {
                Iterator<h.n0> it = j0Var.getChildren().iterator();
                i2 = 0;
                while (it.hasNext()) {
                    h.l0 l0Var2 = (h.l0) it.next();
                    if (n2 == null || l0Var2.n().equals(n2)) {
                        i2++;
                    }
                }
            } else {
                i2 = 1;
            }
            return i2 == 1;
        }

        public String toString() {
            return this.a ? String.format("only-of-type <%s>", this.b) : String.format("only-child", new Object[0]);
        }
    }

    /* loaded from: classes.dex */
    public static class n implements g {
        private n() {
        }

        @Override // com.caverock.androidsvg.b.g
        public boolean a(q qVar, h.l0 l0Var) {
            return l0Var.b == null;
        }

        public String toString() {
            return "root";
        }

        /* synthetic */ n(a aVar) {
            this();
        }
    }

    /* loaded from: classes.dex */
    public static class o implements g {
        private o() {
        }

        @Override // com.caverock.androidsvg.b.g
        public boolean a(q qVar, h.l0 l0Var) {
            return qVar != null && l0Var == qVar.a;
        }

        public String toString() {
            return TouchesHelper.TARGET_KEY;
        }

        /* synthetic */ o(a aVar) {
            this();
        }
    }

    /* loaded from: classes.dex */
    public static class p {
        s a;
        h.e0 b;

        /* renamed from: c */
        u f839c;

        p(s sVar, h.e0 e0Var, u uVar) {
            this.a = null;
            this.b = null;
            this.a = sVar;
            this.b = e0Var;
            this.f839c = uVar;
        }

        public String toString() {
            return String.valueOf(this.a) + " {...} (src=" + this.f839c + ")";
        }
    }

    /* loaded from: classes.dex */
    public static class q {
        h.l0 a;

        public String toString() {
            h.l0 l0Var = this.a;
            return l0Var != null ? String.format("<%s id=\"%s\">", l0Var.n(), this.a.f897c) : "";
        }
    }

    /* loaded from: classes.dex */
    public static class r {
        private List<p> a = null;

        void a(p pVar) {
            if (this.a == null) {
                this.a = new ArrayList();
            }
            for (int i2 = 0; i2 < this.a.size(); i2++) {
                if (this.a.get(i2).a.b > pVar.a.b) {
                    this.a.add(i2, pVar);
                    return;
                }
            }
            this.a.add(pVar);
        }

        public void b(r rVar) {
            if (rVar.a == null) {
                return;
            }
            if (this.a == null) {
                this.a = new ArrayList(rVar.a.size());
            }
            Iterator<p> it = rVar.a.iterator();
            while (it.hasNext()) {
                a(it.next());
            }
        }

        public List<p> c() {
            return this.a;
        }

        public boolean d() {
            List<p> list = this.a;
            return list == null || list.isEmpty();
        }

        public void e(u uVar) {
            List<p> list = this.a;
            if (list == null) {
                return;
            }
            Iterator<p> it = list.iterator();
            while (it.hasNext()) {
                if (it.next().f839c == uVar) {
                    it.remove();
                }
            }
        }

        public int f() {
            List<p> list = this.a;
            if (list != null) {
                return list.size();
            }
            return 0;
        }

        public String toString() {
            if (this.a == null) {
                return "";
            }
            StringBuilder sb = new StringBuilder();
            Iterator<p> it = this.a.iterator();
            while (it.hasNext()) {
                sb.append(it.next().toString());
                sb.append('\n');
            }
            return sb.toString();
        }
    }

    /* loaded from: classes.dex */
    public static class t {
        e a;
        String b;

        /* renamed from: c */
        List<C0012b> f840c = null;
        List<g> d = null;

        t(e eVar, String str) {
            this.a = null;
            this.b = null;
            this.a = eVar == null ? e.DESCENDANT : eVar;
            this.b = str;
        }

        void a(String str, c cVar, String str2) {
            if (this.f840c == null) {
                this.f840c = new ArrayList();
            }
            this.f840c.add(new C0012b(str, cVar, str2));
        }

        void b(g gVar) {
            if (this.d == null) {
                this.d = new ArrayList();
            }
            this.d.add(gVar);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            e eVar = this.a;
            if (eVar == e.CHILD) {
                sb.append("> ");
            } else if (eVar == e.FOLLOWS) {
                sb.append("+ ");
            }
            String str = this.b;
            if (str == null) {
                str = ProxyConfig.MATCH_ALL_SCHEMES;
            }
            sb.append(str);
            List<C0012b> list = this.f840c;
            if (list != null) {
                for (C0012b c0012b : list) {
                    sb.append('[');
                    sb.append(c0012b.a);
                    int i2 = a.a[c0012b.b.ordinal()];
                    if (i2 == 1) {
                        sb.append('=');
                        sb.append(c0012b.f830c);
                    } else if (i2 == 2) {
                        sb.append("~=");
                        sb.append(c0012b.f830c);
                    } else if (i2 == 3) {
                        sb.append("|=");
                        sb.append(c0012b.f830c);
                    }
                    sb.append(']');
                }
            }
            List<g> list2 = this.d;
            if (list2 != null) {
                Iterator<g> it = list2.iterator();
                while (it.hasNext()) {
                    sb.append(':');
                    sb.append(it.next());
                }
            }
            return sb.toString();
        }
    }

    /* loaded from: classes.dex */
    public enum u {
        Document,
        RenderOptions
    }

    public b(u uVar) {
        this(f.screen, uVar);
    }

    private static int a(List<h.j0> list, int i2, h.l0 l0Var) {
        int i3 = 0;
        if (i2 < 0) {
            return 0;
        }
        h.j0 j0Var = list.get(i2);
        h.j0 j0Var2 = l0Var.b;
        if (j0Var != j0Var2) {
            return -1;
        }
        Iterator<h.n0> it = j0Var2.getChildren().iterator();
        while (it.hasNext()) {
            if (it.next() == l0Var) {
                return i3;
            }
            i3++;
        }
        return -1;
    }

    public static boolean b(String str, f fVar) {
        d dVar = new d(str);
        dVar.A();
        return c(h(dVar), fVar);
    }

    private static boolean c(List<f> list, f fVar) {
        for (f fVar2 : list) {
            if (fVar2 == f.all || fVar2 == fVar) {
                return true;
            }
        }
        return false;
    }

    private void e(r rVar, d dVar) throws com.caverock.androidsvg.a {
        String H = dVar.H();
        dVar.A();
        if (H != null) {
            if (!this.f829c && H.equals(FilterConstant.SELECT_KEY_MEDIA)) {
                List<f> h2 = h(dVar);
                if (dVar.f('{')) {
                    dVar.A();
                    if (c(h2, this.a)) {
                        this.f829c = true;
                        rVar.b(j(dVar));
                        this.f829c = false;
                    } else {
                        j(dVar);
                    }
                    if (!dVar.h() && !dVar.f('}')) {
                        throw new com.caverock.androidsvg.a("Invalid @media rule: expected '}' at end of rule set");
                    }
                } else {
                    throw new com.caverock.androidsvg.a("Invalid @media rule: missing rule set");
                }
            } else if (!this.f829c && H.equals("import")) {
                String N = dVar.N();
                if (N == null) {
                    N = dVar.F();
                }
                if (N != null) {
                    dVar.A();
                    List<f> h3 = h(dVar);
                    if (!dVar.h() && !dVar.f(';')) {
                        throw new com.caverock.androidsvg.a("Invalid @media rule: expected '}' at end of rule set");
                    }
                    if (com.caverock.androidsvg.h.g() != null && c(h3, this.a)) {
                        com.caverock.androidsvg.h.g().b(N);
                        throw null;
                    }
                } else {
                    throw new com.caverock.androidsvg.a("Invalid @import rule: expected string or url()");
                }
            } else {
                p("Ignoring @%s rule", H);
                o(dVar);
            }
            dVar.A();
            return;
        }
        throw new com.caverock.androidsvg.a("Invalid '@' rule");
    }

    public static List<String> f(String str) {
        d dVar = new d(str);
        ArrayList arrayList = null;
        while (!dVar.h()) {
            String r2 = dVar.r();
            if (r2 != null) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(r2);
                dVar.A();
            }
        }
        return arrayList;
    }

    private h.e0 g(d dVar) throws com.caverock.androidsvg.a {
        h.e0 e0Var = new h.e0();
        do {
            String H = dVar.H();
            dVar.A();
            if (dVar.f(':')) {
                dVar.A();
                String J = dVar.J();
                if (J != null) {
                    dVar.A();
                    if (dVar.f('!')) {
                        dVar.A();
                        if (dVar.g("important")) {
                            dVar.A();
                        } else {
                            throw new com.caverock.androidsvg.a("Malformed rule set: found unexpected '!'");
                        }
                    }
                    dVar.f(';');
                    com.caverock.androidsvg.l.S0(e0Var, H, J);
                    dVar.A();
                    if (dVar.h()) {
                        break;
                    }
                } else {
                    throw new com.caverock.androidsvg.a("Expected property value");
                }
            } else {
                throw new com.caverock.androidsvg.a("Expected ':'");
            }
        } while (!dVar.f('}'));
        return e0Var;
    }

    private static List<f> h(d dVar) {
        String w;
        ArrayList arrayList = new ArrayList();
        while (!dVar.h() && (w = dVar.w()) != null) {
            try {
                arrayList.add(f.valueOf(w));
            } catch (IllegalArgumentException unused) {
            }
            if (!dVar.z()) {
                break;
            }
        }
        return arrayList;
    }

    private boolean i(r rVar, d dVar) throws com.caverock.androidsvg.a {
        List L = dVar.L();
        if (L == null || L.isEmpty()) {
            return false;
        }
        if (dVar.f('{')) {
            dVar.A();
            h.e0 g2 = g(dVar);
            dVar.A();
            Iterator it = L.iterator();
            while (it.hasNext()) {
                rVar.a(new p((s) it.next(), g2, this.b));
            }
            return true;
        }
        throw new com.caverock.androidsvg.a("Malformed rule block: expected '{'");
    }

    private r j(d dVar) {
        r rVar = new r();
        while (!dVar.h()) {
            try {
                if (!dVar.g("<!--") && !dVar.g("-->")) {
                    if (dVar.f('@')) {
                        e(rVar, dVar);
                    } else if (!i(rVar, dVar)) {
                        break;
                    }
                }
            } catch (com.caverock.androidsvg.a e2) {
                String str = "CSS parser terminated early due to error: " + e2.getMessage();
            }
        }
        return rVar;
    }

    private static boolean k(q qVar, s sVar, int i2, List<h.j0> list, int i3, h.l0 l0Var) {
        t e2 = sVar.e(i2);
        if (n(qVar, e2, list, i3, l0Var)) {
            e eVar = e2.a;
            if (eVar == e.DESCENDANT) {
                if (i2 == 0) {
                    return true;
                }
                while (i3 >= 0) {
                    if (m(qVar, sVar, i2 - 1, list, i3)) {
                        return true;
                    }
                    i3--;
                }
                return false;
            } else if (eVar == e.CHILD) {
                return m(qVar, sVar, i2 - 1, list, i3);
            } else {
                int a2 = a(list, i3, l0Var);
                if (a2 <= 0) {
                    return false;
                }
                return k(qVar, sVar, i2 - 1, list, i3, (h.l0) l0Var.b.getChildren().get(a2 - 1));
            }
        }
        return false;
    }

    public static boolean l(q qVar, s sVar, h.l0 l0Var) {
        ArrayList arrayList = new ArrayList();
        for (h.j0 j0Var = l0Var.b; j0Var != null; j0Var = ((h.n0) j0Var).b) {
            arrayList.add(0, j0Var);
        }
        int size = arrayList.size() - 1;
        if (sVar.g() == 1) {
            return n(qVar, sVar.e(0), arrayList, size, l0Var);
        }
        return k(qVar, sVar, sVar.g() - 1, arrayList, size, l0Var);
    }

    private static boolean m(q qVar, s sVar, int i2, List<h.j0> list, int i3) {
        t e2 = sVar.e(i2);
        h.l0 l0Var = (h.l0) list.get(i3);
        if (n(qVar, e2, list, i3, l0Var)) {
            e eVar = e2.a;
            if (eVar == e.DESCENDANT) {
                if (i2 == 0) {
                    return true;
                }
                while (i3 > 0) {
                    i3--;
                    if (m(qVar, sVar, i2 - 1, list, i3)) {
                        return true;
                    }
                }
                return false;
            } else if (eVar == e.CHILD) {
                return m(qVar, sVar, i2 - 1, list, i3 - 1);
            } else {
                int a2 = a(list, i3, l0Var);
                if (a2 <= 0) {
                    return false;
                }
                return k(qVar, sVar, i2 - 1, list, i3, (h.l0) l0Var.b.getChildren().get(a2 - 1));
            }
        }
        return false;
    }

    private static boolean n(q qVar, t tVar, List<h.j0> list, int i2, h.l0 l0Var) {
        List<String> list2;
        String str = tVar.b;
        if (str == null || str.equals(l0Var.n().toLowerCase(Locale.US))) {
            List<C0012b> list3 = tVar.f840c;
            if (list3 != null) {
                for (C0012b c0012b : list3) {
                    String str2 = c0012b.a;
                    str2.hashCode();
                    if (!str2.equals("id")) {
                        if (!str2.equals("class") || (list2 = l0Var.f900g) == null || !list2.contains(c0012b.f830c)) {
                            return false;
                        }
                    } else if (!c0012b.f830c.equals(l0Var.f897c)) {
                        return false;
                    }
                }
            }
            List<g> list4 = tVar.d;
            if (list4 != null) {
                Iterator<g> it = list4.iterator();
                while (it.hasNext()) {
                    if (!it.next().a(qVar, l0Var)) {
                        return false;
                    }
                }
                return true;
            }
            return true;
        }
        return false;
    }

    private void o(d dVar) {
        int i2 = 0;
        while (!dVar.h()) {
            int intValue = dVar.l().intValue();
            if (intValue == 59 && i2 == 0) {
                return;
            }
            if (intValue == 123) {
                i2++;
            } else if (intValue == 125 && i2 > 0 && i2 - 1 == 0) {
                return;
            }
        }
    }

    private static void p(String str, Object... objArr) {
        String.format(str, objArr);
    }

    public r d(String str) {
        d dVar = new d(str);
        dVar.A();
        return j(dVar);
    }

    public b(f fVar, u uVar) {
        this.a = null;
        this.b = null;
        this.f829c = false;
        this.a = fVar;
        this.b = uVar;
    }

    /* loaded from: classes.dex */
    public static class s {
        List<t> a;
        int b;

        private s() {
            this.a = null;
            this.b = 0;
        }

        void a(t tVar) {
            if (this.a == null) {
                this.a = new ArrayList();
            }
            this.a.add(tVar);
        }

        void b() {
            this.b += 1000;
        }

        void c() {
            this.b++;
        }

        void d() {
            this.b += 1000000;
        }

        t e(int i2) {
            return this.a.get(i2);
        }

        boolean f() {
            List<t> list = this.a;
            return list == null || list.isEmpty();
        }

        int g() {
            List<t> list = this.a;
            if (list == null) {
                return 0;
            }
            return list.size();
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            Iterator<t> it = this.a.iterator();
            while (it.hasNext()) {
                sb.append(it.next());
                sb.append(' ');
            }
            sb.append('[');
            sb.append(this.b);
            sb.append(']');
            return sb.toString();
        }

        /* synthetic */ s(a aVar) {
            this();
        }
    }
}
