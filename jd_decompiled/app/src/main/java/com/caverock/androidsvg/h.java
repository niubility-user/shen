package com.caverock.androidsvg;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Picture;
import com.caverock.androidsvg.b;
import com.coremedia.iso.boxes.TrackReferenceBox;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.jdsdk.constant.Constants;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public class h {

    /* renamed from: e */
    private static com.caverock.androidsvg.j f849e = null;

    /* renamed from: f */
    private static boolean f850f = true;
    private f0 a = null;
    private float b = 96.0f;

    /* renamed from: c */
    private b.r f851c = new b.r();
    private Map<String, l0> d = new HashMap();

    /* loaded from: classes.dex */
    public static /* synthetic */ class a {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[d1.values().length];
            a = iArr;
            try {
                iArr[d1.px.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[d1.em.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[d1.ex.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[d1.in.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[d1.cm.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                a[d1.mm.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                a[d1.pt.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                a[d1.pc.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                a[d1.percent.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class a0 extends z {
        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.caverock.androidsvg.h.z, com.caverock.androidsvg.h.n0
        public String n() {
            return "polygon";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static abstract class a1 extends y0 {

        /* renamed from: n */
        List<p> f852n;
        List<p> o;
        List<p> p;
        List<p> q;

        a1() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class b0 extends l {
        p o;
        p p;
        p q;
        p r;
        p s;
        p t;

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.caverock.androidsvg.h.n0
        public String n() {
            return "rect";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface b1 {
    }

    /* loaded from: classes.dex */
    public static class c {
        p a;
        p b;

        /* renamed from: c */
        p f854c;
        p d;

        public c(p pVar, p pVar2, p pVar3, p pVar4) {
            this.a = pVar;
            this.b = pVar2;
            this.f854c = pVar3;
            this.d = pVar4;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class c0 extends l0 implements j0 {
        @Override // com.caverock.androidsvg.h.j0
        public List<n0> getChildren() {
            return Collections.emptyList();
        }

        @Override // com.caverock.androidsvg.h.j0
        public void h(n0 n0Var) {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.caverock.androidsvg.h.n0
        public String n() {
            return "solidColor";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class c1 extends n0 implements x0 {

        /* renamed from: c */
        String f855c;
        private b1 d;

        public c1(String str) {
            this.f855c = str;
        }

        @Override // com.caverock.androidsvg.h.x0
        public b1 d() {
            return this.d;
        }

        public String toString() {
            return "TextChild: '" + this.f855c + "'";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class d extends l {
        p o;
        p p;
        p q;

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.caverock.androidsvg.h.n0
        public String n() {
            return Constants.STORY_SHARE_PAGE_CIRCLE_DETAIL;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class d0 extends l0 implements j0 {

        /* renamed from: h */
        Float f856h;

        @Override // com.caverock.androidsvg.h.j0
        public List<n0> getChildren() {
            return Collections.emptyList();
        }

        @Override // com.caverock.androidsvg.h.j0
        public void h(n0 n0Var) {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.caverock.androidsvg.h.n0
        public String n() {
            return "stop";
        }
    }

    /* loaded from: classes.dex */
    public enum d1 {
        px,
        em,
        ex,
        in,
        cm,
        mm,
        pt,
        pc,
        percent
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class e extends m implements t {
        Boolean o;

        @Override // com.caverock.androidsvg.h.m, com.caverock.androidsvg.h.n0
        String n() {
            return "clipPath";
        }
    }

    /* loaded from: classes.dex */
    public static class e0 implements Cloneable {
        f A;
        Boolean B;
        c C;
        String D;
        String E;
        String F;
        Boolean G;
        Boolean H;
        o0 I;
        Float J;
        String K;
        a L;
        String M;
        o0 N;
        Float O;
        o0 P;
        Float Q;
        i R;
        e S;

        /* renamed from: g */
        long f858g = 0;

        /* renamed from: h */
        o0 f859h;

        /* renamed from: i */
        a f860i;

        /* renamed from: j */
        Float f861j;

        /* renamed from: k */
        o0 f862k;

        /* renamed from: l */
        Float f863l;

        /* renamed from: m */
        p f864m;

        /* renamed from: n */
        c f865n;
        d o;
        Float p;
        p[] q;
        p r;
        Float s;
        f t;
        List<String> u;
        p v;
        Integer w;
        b x;
        g y;
        EnumC0013h z;

        /* loaded from: classes.dex */
        public enum a {
            NonZero,
            EvenOdd
        }

        /* loaded from: classes.dex */
        public enum b {
            Normal,
            Italic,
            Oblique
        }

        /* loaded from: classes.dex */
        public enum c {
            Butt,
            Round,
            Square
        }

        /* loaded from: classes.dex */
        public enum d {
            Miter,
            Round,
            Bevel
        }

        /* loaded from: classes.dex */
        public enum e {
            auto,
            optimizeQuality,
            optimizeSpeed
        }

        /* loaded from: classes.dex */
        public enum f {
            Start,
            Middle,
            End
        }

        /* loaded from: classes.dex */
        public enum g {
            None,
            Underline,
            Overline,
            LineThrough,
            Blink
        }

        /* renamed from: com.caverock.androidsvg.h$e0$h */
        /* loaded from: classes.dex */
        public enum EnumC0013h {
            LTR,
            RTL
        }

        /* loaded from: classes.dex */
        public enum i {
            None,
            NonScalingStroke
        }

        public static e0 a() {
            e0 e0Var = new e0();
            e0Var.f858g = -1L;
            f fVar = f.f875h;
            e0Var.f859h = fVar;
            a aVar = a.NonZero;
            e0Var.f860i = aVar;
            Float valueOf = Float.valueOf(1.0f);
            e0Var.f861j = valueOf;
            e0Var.f862k = null;
            e0Var.f863l = valueOf;
            e0Var.f864m = new p(1.0f);
            e0Var.f865n = c.Butt;
            e0Var.o = d.Miter;
            e0Var.p = Float.valueOf(4.0f);
            e0Var.q = null;
            e0Var.r = new p(0.0f);
            e0Var.s = valueOf;
            e0Var.t = fVar;
            e0Var.u = null;
            e0Var.v = new p(12.0f, d1.pt);
            e0Var.w = 400;
            e0Var.x = b.Normal;
            e0Var.y = g.None;
            e0Var.z = EnumC0013h.LTR;
            e0Var.A = f.Start;
            Boolean bool = Boolean.TRUE;
            e0Var.B = bool;
            e0Var.C = null;
            e0Var.D = null;
            e0Var.E = null;
            e0Var.F = null;
            e0Var.G = bool;
            e0Var.H = bool;
            e0Var.I = fVar;
            e0Var.J = valueOf;
            e0Var.K = null;
            e0Var.L = aVar;
            e0Var.M = null;
            e0Var.N = null;
            e0Var.O = valueOf;
            e0Var.P = null;
            e0Var.Q = valueOf;
            e0Var.R = i.None;
            e0Var.S = e.auto;
            return e0Var;
        }

        public void b(boolean z) {
            Boolean bool = Boolean.TRUE;
            this.G = bool;
            if (!z) {
                bool = Boolean.FALSE;
            }
            this.B = bool;
            this.C = null;
            this.K = null;
            this.s = Float.valueOf(1.0f);
            this.I = f.f875h;
            this.J = Float.valueOf(1.0f);
            this.M = null;
            this.N = null;
            this.O = Float.valueOf(1.0f);
            this.P = null;
            this.Q = Float.valueOf(1.0f);
            this.R = i.None;
        }

        public Object clone() throws CloneNotSupportedException {
            e0 e0Var = (e0) super.clone();
            p[] pVarArr = this.q;
            if (pVarArr != null) {
                e0Var.q = (p[]) pVarArr.clone();
            }
            return e0Var;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class e1 extends m {
        String o;
        p p;
        p q;
        p r;
        p s;

        @Override // com.caverock.androidsvg.h.m, com.caverock.androidsvg.h.n0
        String n() {
            return "use";
        }
    }

    /* loaded from: classes.dex */
    public static class f extends o0 {

        /* renamed from: h */
        static final f f875h = new f(-16777216);

        /* renamed from: i */
        static final f f876i = new f(0);

        /* renamed from: g */
        int f877g;

        public f(int i2) {
            this.f877g = i2;
        }

        public String toString() {
            return String.format("#%08x", Integer.valueOf(this.f877g));
        }
    }

    /* loaded from: classes.dex */
    public static class f0 extends r0 {
        p p;
        p q;
        p r;
        p s;
        public String t;

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.caverock.androidsvg.h.n0
        public String n() {
            return "svg";
        }
    }

    /* loaded from: classes.dex */
    public static class f1 extends r0 implements t {
        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.caverock.androidsvg.h.n0
        public String n() {
            return "view";
        }
    }

    /* loaded from: classes.dex */
    public static class g extends o0 {

        /* renamed from: g */
        private static g f878g = new g();

        private g() {
        }

        public static g a() {
            return f878g;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface g0 {
        Set<String> a();

        String b();

        void c(Set<String> set);

        void e(Set<String> set);

        Set<String> f();

        void g(Set<String> set);

        void i(Set<String> set);

        void j(String str);

        Set<String> l();

        Set<String> m();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.caverock.androidsvg.h$h */
    /* loaded from: classes.dex */
    public static class C0014h extends m implements t {
        @Override // com.caverock.androidsvg.h.m, com.caverock.androidsvg.h.n0
        String n() {
            return "defs";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static abstract class h0 extends k0 implements j0, g0 {

        /* renamed from: i */
        List<n0> f879i = new ArrayList();

        /* renamed from: j */
        Set<String> f880j = null;

        /* renamed from: k */
        String f881k = null;

        /* renamed from: l */
        Set<String> f882l = null;

        /* renamed from: m */
        Set<String> f883m = null;

        h0() {
        }

        @Override // com.caverock.androidsvg.h.g0
        public Set<String> a() {
            return null;
        }

        @Override // com.caverock.androidsvg.h.g0
        public String b() {
            return this.f881k;
        }

        @Override // com.caverock.androidsvg.h.g0
        public void c(Set<String> set) {
            this.f883m = set;
        }

        @Override // com.caverock.androidsvg.h.g0
        public void e(Set<String> set) {
            this.f880j = set;
        }

        @Override // com.caverock.androidsvg.h.g0
        public Set<String> f() {
            return this.f880j;
        }

        @Override // com.caverock.androidsvg.h.g0
        public void g(Set<String> set) {
        }

        @Override // com.caverock.androidsvg.h.j0
        public List<n0> getChildren() {
            return this.f879i;
        }

        @Override // com.caverock.androidsvg.h.j0
        public void h(n0 n0Var) throws com.caverock.androidsvg.k {
            this.f879i.add(n0Var);
        }

        @Override // com.caverock.androidsvg.h.g0
        public void i(Set<String> set) {
            this.f882l = set;
        }

        @Override // com.caverock.androidsvg.h.g0
        public void j(String str) {
            this.f881k = str;
        }

        @Override // com.caverock.androidsvg.h.g0
        public Set<String> l() {
            return this.f882l;
        }

        @Override // com.caverock.androidsvg.h.g0
        public Set<String> m() {
            return this.f883m;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class i extends l {
        p o;
        p p;
        p q;
        p r;

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.caverock.androidsvg.h.n0
        public String n() {
            return "ellipse";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static abstract class i0 extends k0 implements g0 {

        /* renamed from: i */
        Set<String> f884i = null;

        /* renamed from: j */
        String f885j = null;

        /* renamed from: k */
        Set<String> f886k = null;

        /* renamed from: l */
        Set<String> f887l = null;

        /* renamed from: m */
        Set<String> f888m = null;

        i0() {
        }

        @Override // com.caverock.androidsvg.h.g0
        public Set<String> a() {
            return this.f886k;
        }

        @Override // com.caverock.androidsvg.h.g0
        public String b() {
            return this.f885j;
        }

        @Override // com.caverock.androidsvg.h.g0
        public void c(Set<String> set) {
            this.f888m = set;
        }

        @Override // com.caverock.androidsvg.h.g0
        public void e(Set<String> set) {
            this.f884i = set;
        }

        @Override // com.caverock.androidsvg.h.g0
        public Set<String> f() {
            return this.f884i;
        }

        @Override // com.caverock.androidsvg.h.g0
        public void g(Set<String> set) {
            this.f886k = set;
        }

        @Override // com.caverock.androidsvg.h.g0
        public void i(Set<String> set) {
            this.f887l = set;
        }

        @Override // com.caverock.androidsvg.h.g0
        public void j(String str) {
            this.f885j = str;
        }

        @Override // com.caverock.androidsvg.h.g0
        public Set<String> l() {
            return this.f887l;
        }

        @Override // com.caverock.androidsvg.h.g0
        public Set<String> m() {
            return this.f888m;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static abstract class j extends l0 implements j0 {

        /* renamed from: h */
        List<n0> f889h = new ArrayList();

        /* renamed from: i */
        Boolean f890i;

        /* renamed from: j */
        Matrix f891j;

        /* renamed from: k */
        k f892k;

        /* renamed from: l */
        String f893l;

        j() {
        }

        @Override // com.caverock.androidsvg.h.j0
        public List<n0> getChildren() {
            return this.f889h;
        }

        @Override // com.caverock.androidsvg.h.j0
        public void h(n0 n0Var) throws com.caverock.androidsvg.k {
            if (n0Var instanceof d0) {
                this.f889h.add(n0Var);
                return;
            }
            throw new com.caverock.androidsvg.k("Gradient elements cannot contain " + n0Var + " elements.");
        }
    }

    /* loaded from: classes.dex */
    public interface j0 {
        List<n0> getChildren();

        void h(n0 n0Var) throws com.caverock.androidsvg.k;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public enum k {
        pad,
        reflect,
        repeat
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static abstract class k0 extends l0 {

        /* renamed from: h */
        b f895h = null;

        k0() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static abstract class l extends i0 implements n {

        /* renamed from: n */
        Matrix f896n;

        l() {
        }

        @Override // com.caverock.androidsvg.h.n
        public void k(Matrix matrix) {
            this.f896n = matrix;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class l0 extends n0 {

        /* renamed from: c */
        String f897c = null;
        Boolean d = null;

        /* renamed from: e */
        e0 f898e = null;

        /* renamed from: f */
        e0 f899f = null;

        /* renamed from: g */
        List<String> f900g = null;

        l0() {
        }

        public String toString() {
            return n();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class m extends h0 implements n {

        /* renamed from: n */
        Matrix f901n;

        @Override // com.caverock.androidsvg.h.n
        public void k(Matrix matrix) {
            this.f901n = matrix;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.caverock.androidsvg.h.n0
        public String n() {
            return "group";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class m0 extends j {

        /* renamed from: m */
        p f902m;

        /* renamed from: n */
        p f903n;
        p o;
        p p;

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.caverock.androidsvg.h.n0
        public String n() {
            return "linearGradient";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface n {
        void k(Matrix matrix);
    }

    /* loaded from: classes.dex */
    public static class n0 {
        h a;
        j0 b;

        n0() {
        }

        public String n() {
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class o extends p0 implements n {
        String o;
        p p;
        p q;
        p r;
        p s;
        Matrix t;

        @Override // com.caverock.androidsvg.h.n
        public void k(Matrix matrix) {
            this.t = matrix;
        }

        @Override // com.caverock.androidsvg.h.n0
        public String n() {
            return "image";
        }
    }

    /* loaded from: classes.dex */
    public static abstract class o0 implements Cloneable {
        o0() {
        }
    }

    /* loaded from: classes.dex */
    public static abstract class p0 extends h0 {

        /* renamed from: n */
        com.caverock.androidsvg.f f906n = null;

        p0() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class q extends l {
        p o;
        p p;
        p q;
        p r;

        @Override // com.caverock.androidsvg.h.n0
        public String n() {
            return "line";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class q0 extends j {

        /* renamed from: m */
        p f907m;

        /* renamed from: n */
        p f908n;
        p o;
        p p;
        p q;

        @Override // com.caverock.androidsvg.h.n0
        public String n() {
            return "radialGradient";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class r extends r0 implements t {
        boolean p;
        p q;
        p r;
        p s;
        p t;
        Float u;

        @Override // com.caverock.androidsvg.h.n0
        public String n() {
            return "marker";
        }
    }

    /* loaded from: classes.dex */
    public static abstract class r0 extends p0 {
        b o;

        r0() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class s extends h0 implements t {

        /* renamed from: n */
        Boolean f909n;
        Boolean o;
        p p;
        p q;
        p r;
        p s;

        @Override // com.caverock.androidsvg.h.n0
        public String n() {
            return "mask";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class s0 extends m {
        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.caverock.androidsvg.h.m, com.caverock.androidsvg.h.n0
        public String n() {
            return "switch";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface t {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class t0 extends r0 implements t {
        @Override // com.caverock.androidsvg.h.n0
        public String n() {
            return "symbol";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class u extends o0 {

        /* renamed from: g */
        String f910g;

        /* renamed from: h */
        o0 f911h;

        public u(String str, o0 o0Var) {
            this.f910g = str;
            this.f911h = o0Var;
        }

        public String toString() {
            return this.f910g + LangUtils.SINGLE_SPACE + this.f911h;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class u0 extends y0 implements x0 {

        /* renamed from: n */
        String f912n;
        private b1 o;

        @Override // com.caverock.androidsvg.h.x0
        public b1 d() {
            return this.o;
        }

        @Override // com.caverock.androidsvg.h.n0
        public String n() {
            return TrackReferenceBox.TYPE;
        }

        public void o(b1 b1Var) {
            this.o = b1Var;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class v extends l {
        w o;
        Float p;

        @Override // com.caverock.androidsvg.h.n0
        public String n() {
            return "path";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class v0 extends a1 implements x0 {
        private b1 r;

        @Override // com.caverock.androidsvg.h.x0
        public b1 d() {
            return this.r;
        }

        @Override // com.caverock.androidsvg.h.n0
        public String n() {
            return "tspan";
        }

        public void o(b1 b1Var) {
            this.r = b1Var;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class w implements x {
        private int b = 0;
        private int d = 0;
        private byte[] a = new byte[8];

        /* renamed from: c */
        private float[] f913c = new float[16];

        private void f(byte b) {
            int i2 = this.b;
            byte[] bArr = this.a;
            if (i2 == bArr.length) {
                byte[] bArr2 = new byte[bArr.length * 2];
                System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
                this.a = bArr2;
            }
            byte[] bArr3 = this.a;
            int i3 = this.b;
            this.b = i3 + 1;
            bArr3[i3] = b;
        }

        private void g(int i2) {
            float[] fArr = this.f913c;
            if (fArr.length < this.d + i2) {
                float[] fArr2 = new float[fArr.length * 2];
                System.arraycopy(fArr, 0, fArr2, 0, fArr.length);
                this.f913c = fArr2;
            }
        }

        @Override // com.caverock.androidsvg.h.x
        public void a(float f2, float f3, float f4, float f5) {
            f((byte) 3);
            g(4);
            float[] fArr = this.f913c;
            int i2 = this.d;
            int i3 = i2 + 1;
            this.d = i3;
            fArr[i2] = f2;
            int i4 = i3 + 1;
            this.d = i4;
            fArr[i3] = f3;
            int i5 = i4 + 1;
            this.d = i5;
            fArr[i4] = f4;
            this.d = i5 + 1;
            fArr[i5] = f5;
        }

        @Override // com.caverock.androidsvg.h.x
        public void b(float f2, float f3) {
            f((byte) 0);
            g(2);
            float[] fArr = this.f913c;
            int i2 = this.d;
            int i3 = i2 + 1;
            this.d = i3;
            fArr[i2] = f2;
            this.d = i3 + 1;
            fArr[i3] = f3;
        }

        @Override // com.caverock.androidsvg.h.x
        public void c(float f2, float f3, float f4, float f5, float f6, float f7) {
            f((byte) 2);
            g(6);
            float[] fArr = this.f913c;
            int i2 = this.d;
            int i3 = i2 + 1;
            this.d = i3;
            fArr[i2] = f2;
            int i4 = i3 + 1;
            this.d = i4;
            fArr[i3] = f3;
            int i5 = i4 + 1;
            this.d = i5;
            fArr[i4] = f4;
            int i6 = i5 + 1;
            this.d = i6;
            fArr[i5] = f5;
            int i7 = i6 + 1;
            this.d = i7;
            fArr[i6] = f6;
            this.d = i7 + 1;
            fArr[i7] = f7;
        }

        @Override // com.caverock.androidsvg.h.x
        public void close() {
            f((byte) 8);
        }

        @Override // com.caverock.androidsvg.h.x
        public void d(float f2, float f3, float f4, boolean z, boolean z2, float f5, float f6) {
            f((byte) ((z ? 2 : 0) | 4 | (z2 ? 1 : 0)));
            g(5);
            float[] fArr = this.f913c;
            int i2 = this.d;
            int i3 = i2 + 1;
            this.d = i3;
            fArr[i2] = f2;
            int i4 = i3 + 1;
            this.d = i4;
            fArr[i3] = f3;
            int i5 = i4 + 1;
            this.d = i5;
            fArr[i4] = f4;
            int i6 = i5 + 1;
            this.d = i6;
            fArr[i5] = f5;
            this.d = i6 + 1;
            fArr[i6] = f6;
        }

        @Override // com.caverock.androidsvg.h.x
        public void e(float f2, float f3) {
            f((byte) 1);
            g(2);
            float[] fArr = this.f913c;
            int i2 = this.d;
            int i3 = i2 + 1;
            this.d = i3;
            fArr[i2] = f2;
            this.d = i3 + 1;
            fArr[i3] = f3;
        }

        public void h(x xVar) {
            int i2;
            int i3 = 0;
            for (int i4 = 0; i4 < this.b; i4++) {
                byte b = this.a[i4];
                if (b == 0) {
                    float[] fArr = this.f913c;
                    int i5 = i3 + 1;
                    i2 = i5 + 1;
                    xVar.b(fArr[i3], fArr[i5]);
                } else if (b != 1) {
                    if (b == 2) {
                        float[] fArr2 = this.f913c;
                        int i6 = i3 + 1;
                        float f2 = fArr2[i3];
                        int i7 = i6 + 1;
                        float f3 = fArr2[i6];
                        int i8 = i7 + 1;
                        float f4 = fArr2[i7];
                        int i9 = i8 + 1;
                        float f5 = fArr2[i8];
                        int i10 = i9 + 1;
                        float f6 = fArr2[i9];
                        i3 = i10 + 1;
                        xVar.c(f2, f3, f4, f5, f6, fArr2[i10]);
                    } else if (b == 3) {
                        float[] fArr3 = this.f913c;
                        int i11 = i3 + 1;
                        int i12 = i11 + 1;
                        int i13 = i12 + 1;
                        xVar.a(fArr3[i3], fArr3[i11], fArr3[i12], fArr3[i13]);
                        i3 = i13 + 1;
                    } else if (b != 8) {
                        boolean z = (b & 2) != 0;
                        boolean z2 = (b & 1) != 0;
                        float[] fArr4 = this.f913c;
                        int i14 = i3 + 1;
                        float f7 = fArr4[i3];
                        int i15 = i14 + 1;
                        float f8 = fArr4[i14];
                        int i16 = i15 + 1;
                        float f9 = fArr4[i15];
                        int i17 = i16 + 1;
                        xVar.d(f7, f8, f9, z, z2, fArr4[i16], fArr4[i17]);
                        i3 = i17 + 1;
                    } else {
                        xVar.close();
                    }
                } else {
                    float[] fArr5 = this.f913c;
                    int i18 = i3 + 1;
                    i2 = i18 + 1;
                    xVar.e(fArr5[i3], fArr5[i18]);
                }
                i3 = i2;
            }
        }

        public boolean i() {
            return this.b == 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class w0 extends a1 implements b1, n {
        Matrix r;

        @Override // com.caverock.androidsvg.h.n
        public void k(Matrix matrix) {
            this.r = matrix;
        }

        @Override // com.caverock.androidsvg.h.n0
        public String n() {
            return "text";
        }
    }

    /* loaded from: classes.dex */
    public interface x {
        void a(float f2, float f3, float f4, float f5);

        void b(float f2, float f3);

        void c(float f2, float f3, float f4, float f5, float f6, float f7);

        void close();

        void d(float f2, float f3, float f4, boolean z, boolean z2, float f5, float f6);

        void e(float f2, float f3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface x0 {
        b1 d();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class y extends r0 implements t {
        Boolean p;
        Boolean q;
        Matrix r;
        p s;
        p t;
        p u;
        p v;
        String w;

        @Override // com.caverock.androidsvg.h.n0
        public String n() {
            return "pattern";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static abstract class y0 extends h0 {
        y0() {
        }

        @Override // com.caverock.androidsvg.h.h0, com.caverock.androidsvg.h.j0
        public void h(n0 n0Var) throws com.caverock.androidsvg.k {
            if (n0Var instanceof x0) {
                this.f879i.add(n0Var);
                return;
            }
            throw new com.caverock.androidsvg.k("Text content elements cannot contain " + n0Var + " elements.");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class z extends l {
        float[] o;

        @Override // com.caverock.androidsvg.h.n0
        public String n() {
            return "polyline";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class z0 extends y0 implements x0 {

        /* renamed from: n */
        String f914n;
        p o;
        private b1 p;

        @Override // com.caverock.androidsvg.h.x0
        public b1 d() {
            return this.p;
        }

        @Override // com.caverock.androidsvg.h.n0
        public String n() {
            return "textPath";
        }

        public void o(b1 b1Var) {
            this.p = b1Var;
        }
    }

    private String c(String str) {
        if (str.startsWith("\"") && str.endsWith("\"")) {
            str = str.substring(1, str.length() - 1).replace("\\\"", "\"");
        } else if (str.startsWith("'") && str.endsWith("'")) {
            str = str.substring(1, str.length() - 1).replace("\\'", "'");
        }
        return str.replace("\\\n", "").replace("\\A", ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
    }

    private l0 e(j0 j0Var, String str) {
        l0 e2;
        l0 l0Var = (l0) j0Var;
        if (str.equals(l0Var.f897c)) {
            return l0Var;
        }
        for (n0 n0Var : j0Var.getChildren()) {
            if (n0Var instanceof l0) {
                l0 l0Var2 = (l0) n0Var;
                if (str.equals(l0Var2.f897c)) {
                    return l0Var2;
                }
                if ((n0Var instanceof j0) && (e2 = e((j0) n0Var, str)) != null) {
                    return e2;
                }
            }
        }
        return null;
    }

    public static com.caverock.androidsvg.j g() {
        return f849e;
    }

    public static h h(InputStream inputStream) throws com.caverock.androidsvg.k {
        return new com.caverock.androidsvg.l().z(inputStream, f850f);
    }

    public static h i(Context context, int i2) throws com.caverock.androidsvg.k {
        return j(context.getResources(), i2);
    }

    public static h j(Resources resources, int i2) throws com.caverock.androidsvg.k {
        com.caverock.androidsvg.l lVar = new com.caverock.androidsvg.l();
        InputStream openRawResource = resources.openRawResource(i2);
        try {
            return lVar.z(openRawResource, f850f);
        } finally {
            try {
                openRawResource.close();
            } catch (IOException unused) {
            }
        }
    }

    public static h k(String str) throws com.caverock.androidsvg.k {
        return new com.caverock.androidsvg.l().z(new ByteArrayInputStream(str.getBytes()), f850f);
    }

    public void a(b.r rVar) {
        this.f851c.b(rVar);
    }

    public void b() {
        this.f851c.e(b.u.RenderOptions);
    }

    public List<b.p> d() {
        return this.f851c.c();
    }

    public l0 f(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        if (str.equals(this.a.f897c)) {
            return this.a;
        }
        if (this.d.containsKey(str)) {
            return this.d.get(str);
        }
        l0 e2 = e(this.a, str);
        this.d.put(str, e2);
        return e2;
    }

    public f0 l() {
        return this.a;
    }

    public boolean m() {
        return !this.f851c.d();
    }

    public Picture n(int i2, int i3) {
        return o(i2, i3, null);
    }

    public Picture o(int i2, int i3, com.caverock.androidsvg.g gVar) {
        Picture picture = new Picture();
        Canvas beginRecording = picture.beginRecording(i2, i3);
        if (gVar == null || gVar.f848f == null) {
            gVar = gVar == null ? new com.caverock.androidsvg.g() : new com.caverock.androidsvg.g(gVar);
            gVar.h(0.0f, 0.0f, i2, i3);
        }
        new com.caverock.androidsvg.i(beginRecording, this.b).O0(this, gVar);
        picture.endRecording();
        return picture;
    }

    public Picture p(com.caverock.androidsvg.g gVar) {
        p pVar;
        b bVar = (gVar == null || !gVar.f()) ? this.a.o : gVar.d;
        if (gVar != null && gVar.g()) {
            return o((int) Math.ceil(gVar.f848f.b()), (int) Math.ceil(gVar.f848f.c()), gVar);
        }
        f0 f0Var = this.a;
        p pVar2 = f0Var.r;
        if (pVar2 != null) {
            d1 d1Var = pVar2.f905h;
            d1 d1Var2 = d1.percent;
            if (d1Var != d1Var2 && (pVar = f0Var.s) != null && pVar.f905h != d1Var2) {
                return o((int) Math.ceil(pVar2.b(this.b)), (int) Math.ceil(this.a.s.b(this.b)), gVar);
            }
        }
        if (pVar2 != null && bVar != null) {
            return o((int) Math.ceil(pVar2.b(this.b)), (int) Math.ceil((bVar.d * r1) / bVar.f853c), gVar);
        }
        p pVar3 = f0Var.s;
        if (pVar3 != null && bVar != null) {
            return o((int) Math.ceil((bVar.f853c * r1) / bVar.d), (int) Math.ceil(pVar3.b(this.b)), gVar);
        }
        return o(512, 512, gVar);
    }

    public n0 q(String str) {
        if (str == null) {
            return null;
        }
        String c2 = c(str);
        if (c2.length() <= 1 || !c2.startsWith("#")) {
            return null;
        }
        return f(c2.substring(1));
    }

    public void r(String str) {
    }

    public void s(f0 f0Var) {
        this.a = f0Var;
    }

    public void t(String str) {
    }

    /* loaded from: classes.dex */
    public static class p implements Cloneable {

        /* renamed from: g */
        float f904g;

        /* renamed from: h */
        d1 f905h;

        public p(float f2, d1 d1Var) {
            this.f904g = f2;
            this.f905h = d1Var;
        }

        public float a() {
            return this.f904g;
        }

        public float b(float f2) {
            int i2 = a.a[this.f905h.ordinal()];
            if (i2 != 1) {
                switch (i2) {
                    case 4:
                        return this.f904g * f2;
                    case 5:
                        return (this.f904g * f2) / 2.54f;
                    case 6:
                        return (this.f904g * f2) / 25.4f;
                    case 7:
                        return (this.f904g * f2) / 72.0f;
                    case 8:
                        return (this.f904g * f2) / 6.0f;
                    default:
                        return this.f904g;
                }
            }
            return this.f904g;
        }

        public float c(com.caverock.androidsvg.i iVar) {
            if (this.f905h == d1.percent) {
                b a0 = iVar.a0();
                if (a0 == null) {
                    return this.f904g;
                }
                float f2 = a0.f853c;
                if (f2 == a0.d) {
                    return (this.f904g * f2) / 100.0f;
                }
                return (this.f904g * ((float) (Math.sqrt((f2 * f2) + (r7 * r7)) / 1.414213562373095d))) / 100.0f;
            }
            return e(iVar);
        }

        public float d(com.caverock.androidsvg.i iVar, float f2) {
            if (this.f905h == d1.percent) {
                return (this.f904g * f2) / 100.0f;
            }
            return e(iVar);
        }

        public float e(com.caverock.androidsvg.i iVar) {
            switch (a.a[this.f905h.ordinal()]) {
                case 1:
                    return this.f904g;
                case 2:
                    return this.f904g * iVar.Y();
                case 3:
                    return this.f904g * iVar.Z();
                case 4:
                    return this.f904g * iVar.b0();
                case 5:
                    return (this.f904g * iVar.b0()) / 2.54f;
                case 6:
                    return (this.f904g * iVar.b0()) / 25.4f;
                case 7:
                    return (this.f904g * iVar.b0()) / 72.0f;
                case 8:
                    return (this.f904g * iVar.b0()) / 6.0f;
                case 9:
                    b a0 = iVar.a0();
                    if (a0 == null) {
                        return this.f904g;
                    }
                    return (this.f904g * a0.f853c) / 100.0f;
                default:
                    return this.f904g;
            }
        }

        public float f(com.caverock.androidsvg.i iVar) {
            if (this.f905h == d1.percent) {
                b a0 = iVar.a0();
                if (a0 == null) {
                    return this.f904g;
                }
                return (this.f904g * a0.d) / 100.0f;
            }
            return e(iVar);
        }

        public boolean g() {
            return this.f904g < 0.0f;
        }

        public boolean h() {
            return this.f904g == 0.0f;
        }

        public String toString() {
            return String.valueOf(this.f904g) + this.f905h;
        }

        public p(float f2) {
            this.f904g = f2;
            this.f905h = d1.px;
        }
    }

    /* loaded from: classes.dex */
    public static class b {
        float a;
        float b;

        /* renamed from: c */
        float f853c;
        float d;

        public b(float f2, float f3, float f4, float f5) {
            this.a = f2;
            this.b = f3;
            this.f853c = f4;
            this.d = f5;
        }

        public static b a(float f2, float f3, float f4, float f5) {
            return new b(f2, f3, f4 - f2, f5 - f3);
        }

        public float b() {
            return this.a + this.f853c;
        }

        public float c() {
            return this.b + this.d;
        }

        public void d(b bVar) {
            float f2 = bVar.a;
            if (f2 < this.a) {
                this.a = f2;
            }
            float f3 = bVar.b;
            if (f3 < this.b) {
                this.b = f3;
            }
            if (bVar.b() > b()) {
                this.f853c = bVar.b() - this.a;
            }
            if (bVar.c() > c()) {
                this.d = bVar.c() - this.b;
            }
        }

        public String toString() {
            return "[" + this.a + LangUtils.SINGLE_SPACE + this.b + LangUtils.SINGLE_SPACE + this.f853c + LangUtils.SINGLE_SPACE + this.d + "]";
        }

        public b(b bVar) {
            this.a = bVar.a;
            this.b = bVar.b;
            this.f853c = bVar.f853c;
            this.d = bVar.d;
        }
    }
}
