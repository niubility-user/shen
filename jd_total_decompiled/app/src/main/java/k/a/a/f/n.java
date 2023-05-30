package k.a.a.f;

import java.io.File;
import java.util.ArrayList;

/* loaded from: classes11.dex */
public class n implements Cloneable {

    /* renamed from: g  reason: collision with root package name */
    private c f20257g;

    /* renamed from: h  reason: collision with root package name */
    private f f20258h;

    /* renamed from: i  reason: collision with root package name */
    private j f20259i;

    /* renamed from: j  reason: collision with root package name */
    private k f20260j;

    /* renamed from: k  reason: collision with root package name */
    private boolean f20261k;

    /* renamed from: l  reason: collision with root package name */
    private File f20262l;

    /* renamed from: m  reason: collision with root package name */
    private boolean f20263m;

    public n() {
        new ArrayList();
        new ArrayList();
        this.f20257g = new c();
        this.f20258h = new f();
        this.f20259i = new j();
        this.f20260j = new k();
        this.f20263m = false;
    }

    public c a() {
        return this.f20257g;
    }

    public f b() {
        return this.f20258h;
    }

    public j c() {
        return this.f20259i;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public k d() {
        return this.f20260j;
    }

    public File e() {
        return this.f20262l;
    }

    public boolean f() {
        return this.f20261k;
    }

    public boolean g() {
        return this.f20263m;
    }

    public void h(c cVar) {
        this.f20257g = cVar;
    }

    public void i(f fVar) {
        this.f20258h = fVar;
    }

    public void j(boolean z) {
        this.f20261k = z;
    }

    public void k(j jVar) {
        this.f20259i = jVar;
    }

    public void l(k kVar) {
        this.f20260j = kVar;
    }

    public void m(boolean z) {
        this.f20263m = z;
    }

    public void n(File file) {
        this.f20262l = file;
    }
}
