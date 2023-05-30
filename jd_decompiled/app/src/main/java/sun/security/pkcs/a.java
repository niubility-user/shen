package sun.security.pkcs;

import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.jingdong.sdk.platform.business.personal.R2;
import java.io.IOException;
import jd.wjlogin_sdk.util.ReplyCode;
import m.a.b.h;
import m.a.b.i;
import m.a.b.j;

/* loaded from: classes11.dex */
public class a {

    /* renamed from: c  reason: collision with root package name */
    private static int[] f20471c;
    private static int[] d;

    /* renamed from: e  reason: collision with root package name */
    private static int[] f20472e;

    /* renamed from: f  reason: collision with root package name */
    private static int[] f20473f;

    /* renamed from: g  reason: collision with root package name */
    private static int[] f20474g;

    /* renamed from: h  reason: collision with root package name */
    private static int[] f20475h;

    /* renamed from: i  reason: collision with root package name */
    private static int[] f20476i;

    /* renamed from: j  reason: collision with root package name */
    private static int[] f20477j;

    /* renamed from: k  reason: collision with root package name */
    private static int[] f20478k;

    /* renamed from: l  reason: collision with root package name */
    private static final int[] f20479l;

    /* renamed from: m  reason: collision with root package name */
    private static final int[] f20480m;

    /* renamed from: n  reason: collision with root package name */
    public static j f20481n;
    public static j o;
    public static j p;
    j a;
    i b;

    static {
        int[] iArr = {1, 2, R2.attr.exTabIndicatorHeight, 113549, 1, 7};
        f20471c = iArr;
        d = new int[]{1, 2, R2.attr.exTabIndicatorHeight, 113549, 1, 7, 1};
        f20472e = new int[]{1, 2, R2.attr.exTabIndicatorHeight, 113549, 1, 7, 2};
        f20473f = new int[]{1, 2, R2.attr.exTabIndicatorHeight, 113549, 1, 7, 3};
        f20474g = new int[]{1, 2, R2.attr.exTabIndicatorHeight, 113549, 1, 7, 4};
        f20475h = new int[]{1, 2, R2.attr.exTabIndicatorHeight, 113549, 1, 7, 5};
        f20476i = new int[]{1, 2, R2.attr.exTabIndicatorHeight, 113549, 1, 7, 6};
        f20477j = new int[]{2, 16, R2.attr.exTabIndicatorHeight, 1, 113730, 2, 5};
        f20478k = new int[]{1, 2, R2.attr.exTabIndicatorHeight, 113549, 1, 9, 16, 1, 4};
        int[] iArr2 = {1, 2, R2.attr.exTabIndicatorHeight, 1113549, 1, 7, 2};
        f20479l = iArr2;
        int[] iArr3 = {1, 2, R2.attr.exTabIndicatorHeight, 1113549, 1, 7, 1};
        f20480m = iArr3;
        j.newInternal(iArr);
        j.newInternal(d);
        f20481n = j.newInternal(f20472e);
        j.newInternal(f20473f);
        j.newInternal(f20474g);
        j.newInternal(f20475h);
        j.newInternal(f20476i);
        o = j.newInternal(iArr2);
        j.newInternal(iArr3);
        p = j.newInternal(f20477j);
        j.newInternal(f20478k);
    }

    public a(j jVar, i iVar) {
        this.a = jVar;
        this.b = iVar;
    }

    public void a(h hVar) throws IOException {
        h hVar2 = new h();
        hVar2.p(this.a);
        if (this.b != null) {
            h hVar3 = new h();
            this.b.d(hVar3);
            hVar2.h(new i(ReplyCode.reply0xa0, hVar3.toByteArray()));
        }
        hVar.y((byte) 48, hVar2);
    }

    public i b() {
        return this.b;
    }

    public byte[] c() throws IOException {
        i iVar = this.b;
        if (iVar == null) {
            return null;
        }
        return new m.a.b.g(iVar.D()).l();
    }

    public String toString() {
        return ("Content Info Sequence\n\tContent type: " + this.a + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE) + "\tContent: " + this.b;
    }

    public a(m.a.b.g gVar) throws IOException, e {
        this(gVar, false);
    }

    public a(m.a.b.g gVar, boolean z) throws IOException, e {
        i[] m2 = gVar.m(2);
        this.a = new m.a.b.g(m2[0].D()).k();
        if (z) {
            this.b = m2[1];
        } else if (m2.length > 1) {
            this.b = new m.a.b.g(m2[1].D()).o(1, true)[0];
        }
    }
}
