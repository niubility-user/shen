package com.jingdong.manto.widget.input;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import com.jingdong.manto.jsapi.coverview.CoverViewContainer;
import com.jingdong.manto.q.i;
import com.jingdong.manto.q.n;
import com.jingdong.manto.ui.f.a.a;
import com.jingdong.manto.utils.MantoThreadUtils;
import com.jingdong.manto.utils.MantoUtils;
import com.jingdong.manto.widget.MantoStatusBarUtil;
import com.jingdong.manto.widget.input.e;
import com.jingdong.manto.widget.input.z.d;
import java.lang.ref.WeakReference;

/* loaded from: classes16.dex */
public abstract class j implements com.jingdong.manto.widget.input.z.b<y>, i.c {

    /* renamed from: e  reason: collision with root package name */
    private y f14462e;

    /* renamed from: g  reason: collision with root package name */
    private int f14464g;

    /* renamed from: j  reason: collision with root package name */
    private s f14467j;

    /* renamed from: k  reason: collision with root package name */
    private volatile o f14468k;

    /* renamed from: l  reason: collision with root package name */
    private volatile p f14469l;

    /* renamed from: m  reason: collision with root package name */
    protected WeakReference<com.jingdong.manto.q.n> f14470m;

    /* renamed from: n  reason: collision with root package name */
    private com.jingdong.manto.widget.input.a0.f f14471n;
    private com.jingdong.manto.widget.input.e r;
    private com.jingdong.manto.q.i v;
    private String a = "InputInvokeHandler";
    private final Handler b = new Handler(Looper.getMainLooper());

    /* renamed from: c  reason: collision with root package name */
    private final a.InterfaceC0687a f14461c = new f();
    private final Runnable d = new g();

    /* renamed from: f  reason: collision with root package name */
    final View.OnFocusChangeListener f14463f = new h();

    /* renamed from: h  reason: collision with root package name */
    private int f14465h = -1;

    /* renamed from: i  reason: collision with root package name */
    private int f14466i = -1;
    private boolean o = false;
    private final Runnable p = new i();
    private final e.h q = new C0699j();
    private final e.i s = new k();
    private int t = 0;
    private final com.jingdong.manto.widget.input.z.e u = new com.jingdong.manto.widget.input.z.e();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            j.this.s();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class b implements View.OnClickListener {
        b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (j.this.f14462e != null) {
                if (j.this.f14462e.hasFocus()) {
                    j.this.C();
                    return;
                }
                WeakReference<com.jingdong.manto.q.n> weakReference = j.this.f14470m;
                com.jingdong.manto.q.n nVar = weakReference == null ? null : weakReference.get();
                if (nVar != null) {
                    u.a().a(nVar.s());
                }
                if (j.this.f14462e == null || view != j.this.f14462e) {
                    return;
                }
                j.this.f14462e.setFocusable(true);
                j.this.f14462e.setFocusableInTouchMode(true);
                j.this.r.b = j.this.f14462e;
                j.this.f14462e.requestFocus();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class c implements View.OnKeyListener {
        c() {
        }

        @Override // android.view.View.OnKeyListener
        public boolean onKey(View view, int i2, KeyEvent keyEvent) {
            j.this.o = 67 == i2;
            if (j.this.o) {
                j.this.b.removeCallbacks(j.this.p);
                j.this.b.postDelayed(j.this.p, 1000L);
            } else {
                j.this.p.run();
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class d implements TextView.OnEditorActionListener {
        final /* synthetic */ int a;

        d(int i2) {
            this.a = i2;
        }

        @Override // android.widget.TextView.OnEditorActionListener
        public boolean onEditorAction(TextView textView, int i2, KeyEvent keyEvent) {
            if (i2 != this.a) {
                return false;
            }
            j.this.t = 2;
            j.this.j();
            j.this.t = 0;
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class e implements n.c0 {
        e() {
        }

        @Override // com.jingdong.manto.q.n.c0
        public void onDestroy() {
            j.this.p();
        }
    }

    /* loaded from: classes16.dex */
    class f implements a.InterfaceC0687a {
        f() {
        }

        @Override // com.jingdong.manto.ui.f.a.a.InterfaceC0687a
        public final void a() {
            if (j.this.f14462e != null) {
                try {
                    j.this.u.a(j.this.f14462e.getEditableText(), false);
                } catch (Exception unused) {
                }
            }
        }

        @Override // com.jingdong.manto.ui.f.a.a.InterfaceC0687a
        public void a(String str) {
        }

        @Override // com.jingdong.manto.ui.f.a.a.InterfaceC0687a
        public void b() {
        }
    }

    /* loaded from: classes16.dex */
    class g implements Runnable {
        g() {
        }

        @Override // java.lang.Runnable
        public void run() {
            j.this.u();
        }
    }

    /* loaded from: classes16.dex */
    class h implements View.OnFocusChangeListener {
        h() {
        }

        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View view, boolean z) {
            j.this.b(z);
            if (!z || j.this.f14462e == null || j.this.r == null) {
                return;
            }
            j.this.f14462e.requestFocus();
            j.this.r.g();
        }
    }

    /* loaded from: classes16.dex */
    class i implements Runnable {
        i() {
        }

        @Override // java.lang.Runnable
        public void run() {
            j.this.o = false;
        }
    }

    /* renamed from: com.jingdong.manto.widget.input.j$j  reason: collision with other inner class name */
    /* loaded from: classes16.dex */
    class C0699j implements e.h {
        C0699j() {
        }

        @Override // com.jingdong.manto.widget.input.e.h
        public void a(boolean z) {
            if (z) {
                j.this.t = 2;
            }
            j.this.j();
            j.this.t = 0;
        }
    }

    /* loaded from: classes16.dex */
    class k implements e.i {
        k() {
        }

        @Override // com.jingdong.manto.widget.input.e.i
        public void a(int i2) {
            WeakReference<com.jingdong.manto.q.n> weakReference;
            com.jingdong.manto.q.r s;
            if (i2 == 2) {
                j.this.t = 1;
                j.this.j();
                j.this.t = 0;
                return;
            }
            if (i2 == 0 && j.this.f14462e != null) {
                j.this.f14462e.requestFocus();
                j.this.f14462e.getLocationOnScreen(new int[2]);
                if (r4[1] <= j.this.f14462e.getY() && (weakReference = j.this.f14470m) != null && weakReference.get() != null && j.this.f14470m.get().f() && (s = j.this.f14470m.get().s()) != null) {
                    s.onScrollChanged(0, (int) j.this.f14462e.getY(), 0, 0);
                }
            }
            j.this.o();
            WeakReference<com.jingdong.manto.q.n> weakReference2 = j.this.f14470m;
            if (weakReference2 == null || weakReference2.get() == null) {
                return;
            }
            com.jingdong.manto.widget.input.o.a(j.this.f14470m.get(), j.this.f14462e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class l extends com.jingdong.manto.ui.e {
        l() {
        }

        @Override // com.jingdong.manto.ui.e, android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            WeakReference<com.jingdong.manto.q.n> weakReference = j.this.f14470m;
            if (weakReference == null || weakReference.get() == null || j.this.f14462e == null) {
                return;
            }
            j.this.s();
            if (InputUtil.isComposingText(editable)) {
                return;
            }
            j.this.u.a(j.this.f14462e.getEditableText(), j.this.o);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class m implements com.jingdong.manto.widget.input.z.a {
        m() {
        }

        @Override // com.jingdong.manto.widget.input.z.a
        public void a() {
            if (j.this.f14462e != null) {
                j.this.u.a(j.this.f14462e.getEditableText(), false);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class n implements d.b {
        n() {
        }

        @Override // com.jingdong.manto.widget.input.z.d.b
        public boolean a(int i2) {
            if (i2 != 67) {
                return false;
            }
            if (TextUtils.isEmpty(j.this.f14462e.getText())) {
                j.this.v();
                return true;
            }
            return true;
        }
    }

    /* loaded from: classes16.dex */
    public interface o {
        void a(boolean z);
    }

    /* loaded from: classes16.dex */
    public interface p {
        void a(int i2, int i3);
    }

    private void A() {
        a(false);
        if (this.f14471n.J) {
            a(this.f14462e);
            w();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:24:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void B() {
        int i2;
        y yVar;
        com.jingdong.manto.widget.input.h.a(this.f14462e, this.f14471n);
        com.jingdong.manto.widget.input.a0.f fVar = this.f14471n;
        Integer num = fVar.f14412n;
        if (num != null) {
            if (num.intValue() <= 0) {
                fVar = this.f14471n;
                i2 = Integer.MAX_VALUE;
            }
            com.jingdong.manto.ui.f.a.a a2 = com.jingdong.manto.ui.f.a.a.a(this.f14462e).a(this.f14471n.f14412n.intValue());
            a2.f14289c = false;
            a2.b = 1;
            a2.a(this.f14461c);
            this.f14462e.setPasswordMode(this.f14471n.I);
            if (InputUtil.isTrue(this.f14471n.s)) {
                this.f14462e.setEnabled(true);
                this.f14462e.setClickable(true);
            } else {
                this.f14462e.setEnabled(false);
                this.f14462e.setFocusable(false);
                this.f14462e.setFocusableInTouchMode(false);
                this.f14462e.setClickable(false);
            }
            yVar = this.f14462e;
            if (yVar instanceof com.jingdong.manto.widget.input.b) {
                return;
            }
            com.jingdong.manto.widget.input.b bVar = (com.jingdong.manto.widget.input.b) yVar;
            if (this.f14471n.C != null) {
                bVar.setLineSpace(r1.intValue());
            }
            Integer num2 = this.f14471n.D;
            if (num2 != null) {
                float intValue = num2.intValue();
                if (intValue > 0.0f) {
                    bVar.a(intValue, true);
                    return;
                }
                return;
            }
            return;
        }
        i2 = 140;
        fVar.f14412n = Integer.valueOf(i2);
        com.jingdong.manto.ui.f.a.a a22 = com.jingdong.manto.ui.f.a.a.a(this.f14462e).a(this.f14471n.f14412n.intValue());
        a22.f14289c = false;
        a22.b = 1;
        a22.a(this.f14461c);
        this.f14462e.setPasswordMode(this.f14471n.I);
        if (InputUtil.isTrue(this.f14471n.s)) {
        }
        yVar = this.f14462e;
        if (yVar instanceof com.jingdong.manto.widget.input.b) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void C() {
        y yVar;
        if (InputUtil.isTrue(this.f14471n.u)) {
            l();
        }
        com.jingdong.manto.widget.input.e eVar = this.r;
        if (eVar == null || (yVar = this.f14462e) == null) {
            return;
        }
        eVar.b = yVar;
        com.jingdong.manto.widget.input.o.a(this.f14470m.get(), this.f14462e);
        D();
        if (this.f14462e.hasFocus()) {
            this.r.setConfirmViewVisible(InputUtil.isTrue(this.f14471n.v));
            this.r.g();
        }
    }

    private void D() {
        com.jingdong.manto.widget.input.e eVar = this.r;
        if (eVar != null) {
            eVar.f14448c = this.q;
            eVar.f14449e = this.s;
        }
    }

    private void a(boolean z) {
        y yVar = this.f14462e;
        if (yVar != null) {
            a(yVar.getText().toString(), this.f14462e.getSelectionEnd(), this.t == 2, z);
        }
    }

    private void b(int i2, int i3) {
        WeakReference<com.jingdong.manto.q.n> weakReference = this.f14471n.G;
        this.f14470m = weakReference;
        com.jingdong.manto.q.n nVar = weakReference == null ? null : weakReference.get();
        if (nVar == null || nVar.o() == null) {
            y();
            return;
        }
        this.f14462e = InputUtil.isTrue(this.f14471n.u) ? new com.jingdong.manto.widget.input.b(nVar.f14071i) : new com.jingdong.manto.widget.input.f(nVar.f14071i);
        B();
        this.f14462e.setText(MantoUtils.getNonNull(this.f14471n.a));
        if (InputUtil.isTrue(this.f14471n.w)) {
            s();
        }
        this.f14462e.addTextChangedListener(new l());
        y yVar = this.f14462e;
        yVar.f14503f.b = new m();
        yVar.setOnKeyUpPostImeListener(new n());
        if (a(this.f14462e, this.f14471n)) {
            if (InputUtil.isTrue(this.f14471n.u)) {
                this.f14462e.post(new a());
            } else {
                com.jingdong.manto.widget.input.h.a(this.f14462e, i2, i3);
            }
            if ("text".equalsIgnoreCase(this.f14471n.M)) {
                q();
                int i4 = this.f14471n.H;
                this.f14464g = i4;
                this.f14462e.f14508k = i4;
                this.f14462e.setOnClickListener(new b());
                com.jingdong.manto.widget.input.o.a(this.f14464g, this);
                x();
                return;
            }
        }
        y();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(boolean z) {
        if (z) {
            C();
        }
        if (this.r == null) {
            this.r = l();
        }
        if (this.f14468k != null) {
            this.f14468k.a(z);
        }
        if (this.f14462e != null && z && this.f14471n.u.booleanValue()) {
            MantoThreadUtils.post(this.d, 100);
        }
        if (this.f14462e != null && !z && !this.f14471n.J) {
            if (this.t == 0) {
                a(false);
            }
            this.f14462e.setFocusable(false);
            this.f14462e.setFocusableInTouchMode(false);
            if (this.r.getAttachedEditText() == this.f14462e) {
                this.r.d();
                this.r.a((EditText) this.f14462e);
            }
        }
        if (this.r == null || this.f14462e == null || z || !this.f14471n.J) {
            return;
        }
        A();
    }

    private void c(int i2, int i3) {
        com.jingdong.manto.widget.input.h.a(this.f14462e, i2, i3);
        if (i2 == -1 || i3 == -1 || i3 <= i2) {
            return;
        }
        u();
    }

    @Deprecated
    private void h() {
        com.jingdong.manto.widget.input.e l2;
        if (!r() || (l2 = l()) == null) {
            return;
        }
        l2.setVisibility(8);
    }

    @Deprecated
    private void i() {
        WeakReference<com.jingdong.manto.q.n> weakReference;
        if (this.f14462e == null || (weakReference = this.f14470m) == null || weakReference.get() == null) {
            return;
        }
        this.f14462e.performClick();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        boolean z = this.t == 2 && InputUtil.isTrue(this.f14471n.A);
        if (!z) {
            p();
        }
        y yVar = this.f14462e;
        if (yVar != null) {
            if (yVar.hasFocus()) {
                a(z);
            }
            if (z) {
                return;
            }
            com.jingdong.manto.widget.input.e eVar = this.r;
            if (eVar != null) {
                eVar.a((EditText) this.f14462e);
            }
            if (this.f14471n.J) {
                a(this.f14462e);
                w();
                return;
            }
            this.f14462e.setFocusable(false);
            this.f14462e.setFocusableInTouchMode(false);
        }
    }

    private s k() {
        com.jingdong.manto.q.n nVar;
        s sVar = this.f14467j;
        if (sVar != null) {
            return sVar;
        }
        WeakReference<com.jingdong.manto.q.n> weakReference = this.f14470m;
        if (weakReference == null || (nVar = weakReference.get()) == null) {
            return null;
        }
        s a2 = s.a(nVar.l());
        this.f14467j = a2;
        return a2;
    }

    private com.jingdong.manto.widget.input.e l() {
        com.jingdong.manto.q.n nVar;
        com.jingdong.manto.widget.input.e eVar = this.r;
        if (eVar != null) {
            return eVar;
        }
        WeakReference<com.jingdong.manto.q.n> weakReference = this.f14470m;
        if (weakReference == null || (nVar = weakReference.get()) == null) {
            return null;
        }
        com.jingdong.manto.widget.input.e a2 = com.jingdong.manto.widget.input.e.a(nVar.l());
        this.r = a2;
        return a2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        if (k() != null) {
            this.f14467j.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p() {
        if (l() != null) {
            this.r.d();
        } else {
            WeakReference<com.jingdong.manto.q.n> weakReference = this.f14470m;
            if (weakReference != null) {
                com.jingdong.manto.q.n nVar = weakReference.get();
                if (nVar == null) {
                    return;
                }
                com.jingdong.manto.utils.e.b(com.jingdong.manto.utils.h.a(nVar.f14071i));
            }
        }
        com.jingdong.manto.widget.input.l.a(this.f14470m).b();
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00d4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void q() {
        int statusBarHeight;
        com.jingdong.manto.widget.input.a0.f fVar;
        com.jingdong.manto.q.n nVar = this.f14470m.get();
        if (nVar != null && ViewCompat.isAttachedToWindow(nVar.l())) {
            boolean u = (nVar == null || nVar.h() == null) ? false : nVar.h().u();
            if (u) {
                try {
                    statusBarHeight = (com.jingdong.manto.utils.e.e(com.jingdong.a.g())[1] - ((int) CoverViewContainer.a(nVar.o()).bottom)) + MantoStatusBarUtil.getStatusBarHeight(nVar.h().i());
                } catch (Throwable unused) {
                }
                this.r = com.jingdong.manto.widget.input.e.a(nVar.l(), u, statusBarHeight);
                D();
                o();
                this.f14462e.a(this.f14463f);
                this.f14462e.setOnKeyListener(new c());
                com.jingdong.manto.widget.input.o.a(this.f14470m.get(), this.f14462e);
                if (this.f14471n.u.booleanValue()) {
                    com.jingdong.manto.widget.input.a0.b bVar = this.f14471n.z;
                    if (bVar == null) {
                        bVar = com.jingdong.manto.widget.input.a0.b.a();
                    }
                    int i2 = bVar.a;
                    this.f14462e.setImeOptions(i2);
                    this.f14462e.setOnEditorActionListener(new d(i2));
                } else {
                    this.f14462e.setOnEditorActionListener(null);
                    this.f14462e.setImeOptions(0);
                }
                this.r.b = this.f14462e;
                if (InputUtil.isTrue(this.f14471n.w)) {
                    this.f14462e.setFocusable(false);
                    this.f14462e.setFocusableInTouchMode(false);
                    this.r.d();
                }
                fVar = this.f14471n;
                if (fVar.E) {
                    this.r.setConfirmViewVisible(fVar.v.booleanValue());
                    this.f14462e.requestFocus();
                    this.r.g();
                    if (!(this.f14462e instanceof com.jingdong.manto.widget.input.b)) {
                        com.jingdong.manto.widget.input.l.a(this.f14470m).c();
                    }
                }
                this.f14470m.get().a(new e());
            }
            statusBarHeight = 0;
            this.r = com.jingdong.manto.widget.input.e.a(nVar.l(), u, statusBarHeight);
            D();
            o();
            this.f14462e.a(this.f14463f);
            this.f14462e.setOnKeyListener(new c());
            com.jingdong.manto.widget.input.o.a(this.f14470m.get(), this.f14462e);
            if (this.f14471n.u.booleanValue()) {
            }
            this.r.b = this.f14462e;
            if (InputUtil.isTrue(this.f14471n.w)) {
            }
            fVar = this.f14471n;
            if (fVar.E) {
            }
            this.f14470m.get().a(new e());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        y yVar = this.f14462e;
        if (yVar != null) {
            if (yVar.getLineCount() == this.f14466i && this.f14462e.g() == this.f14465h) {
                return;
            }
            Integer num = this.f14466i == -1 ? 1 : null;
            this.f14466i = this.f14462e.getLineCount();
            this.f14465h = this.f14462e.g();
            if (this.f14469l != null) {
                this.f14469l.a(this.f14466i, this.f14465h);
            }
            if (this.f14471n.u.booleanValue() && num == null) {
                t();
                u();
            }
        }
    }

    private void t() {
        if (this.f14462e != null && InputUtil.isTrue(this.f14471n.w) && InputUtil.isTrue(this.f14471n.u)) {
            ((com.jingdong.manto.widget.input.b) this.f14462e).setAutoHeight(true);
            int lineHeight = this.f14462e.getLineHeight();
            int g2 = this.f14462e.g();
            Integer num = this.f14471n.f14405g;
            int intValue = (num == null || num.intValue() <= 0) ? lineHeight : this.f14471n.f14405g.intValue();
            Integer num2 = this.f14471n.f14406h;
            int max = (num2 == null || num2.intValue() <= 0) ? Integer.MAX_VALUE : Math.max(this.f14471n.f14406h.intValue(), lineHeight);
            this.f14462e.setMinHeight(intValue);
            this.f14462e.setMaxHeight(max);
            this.f14471n.d = Integer.valueOf(Math.max(intValue, Math.min(g2, max)));
            b(this.f14462e, this.f14471n);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u() {
        com.jingdong.manto.widget.input.e eVar;
        y yVar;
        if (InputUtil.isTrue(this.f14471n.u) && (eVar = this.r) != null && eVar.isShown() && (yVar = this.f14462e) != null && yVar == this.r.getAttachedEditText()) {
            com.jingdong.manto.widget.input.l.a(this.f14470m).c();
        }
    }

    private void w() {
        this.f14462e = null;
        com.jingdong.manto.q.i iVar = this.v;
        if (iVar != null) {
            iVar.b(this);
            this.v.c();
        }
    }

    @Deprecated
    private boolean z() {
        WeakReference<com.jingdong.manto.q.n> weakReference;
        if (this.f14462e == null || (weakReference = this.f14470m) == null || weakReference.get() == null) {
            return false;
        }
        this.f14462e.destroy();
        com.jingdong.manto.widget.input.i iVar = this.f14470m.get().v;
        if (iVar == null) {
            return false;
        }
        if (this.f14462e.hasFocus()) {
            s sVar = this.f14467j;
            if (sVar != null) {
                sVar.setVisibility(8);
            }
            l();
            com.jingdong.manto.widget.input.e eVar = this.r;
            if (eVar != null) {
                eVar.setVisibility(8);
            }
        }
        iVar.a((com.jingdong.manto.widget.input.i) this.f14462e);
        return true;
    }

    @Override // com.jingdong.manto.widget.input.z.b
    public View a() {
        return this.r;
    }

    @Override // com.jingdong.manto.q.i.c
    public void a(int i2) {
    }

    public final void a(com.jingdong.manto.q.n nVar, com.jingdong.manto.widget.input.a0.f fVar, int i2, int i3) {
        this.f14471n = fVar;
        this.a += " - " + toString().substring(31);
        b(i2, i3);
        Activity i4 = nVar.h().i();
        if (i4 != null) {
            if (this.v == null) {
                this.v = new com.jingdong.manto.q.i(i4);
            }
            this.v.a(this);
        }
    }

    public void a(o oVar) {
        this.f14468k = oVar;
    }

    public void a(p pVar) {
        this.f14469l = pVar;
    }

    void a(y yVar) {
        com.jingdong.manto.widget.input.i iVar;
        if (yVar != null) {
            yVar.b(this.f14463f);
            WeakReference<com.jingdong.manto.q.n> weakReference = this.f14470m;
            com.jingdong.manto.q.n nVar = weakReference == null ? null : weakReference.get();
            if (nVar == null || (iVar = nVar.v) == null) {
                return;
            }
            iVar.a((com.jingdong.manto.widget.input.i) yVar);
        }
    }

    public void a(com.jingdong.manto.widget.input.z.g gVar) {
        this.u.b = gVar;
    }

    public abstract void a(String str, int i2, boolean z, boolean z2);

    @Override // com.jingdong.manto.widget.input.z.b
    public void a(String str, Integer num) {
        y yVar = this.f14462e;
        if (yVar != null) {
            yVar.a(str);
            Integer valueOf = Integer.valueOf(num == null ? -1 : num.intValue());
            c(valueOf.intValue(), valueOf.intValue());
            s();
        }
    }

    @Override // com.jingdong.manto.widget.input.z.b
    public boolean a(int i2, int i3) {
        i();
        c(i2, i3);
        return true;
    }

    @Override // com.jingdong.manto.widget.input.z.b
    public boolean a(com.jingdong.manto.q.n nVar) {
        WeakReference<com.jingdong.manto.q.n> weakReference;
        return (nVar == null || (weakReference = this.f14470m) == null || nVar != weakReference.get()) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean a(com.jingdong.manto.widget.input.a0.g gVar) {
        Integer num;
        com.jingdong.manto.widget.input.a0.f fVar = this.f14471n;
        if (fVar == null || this.f14462e == null) {
            return false;
        }
        fVar.a(gVar);
        if (this.f14471n.K || ((num = this.f14471n.d) != null && num.intValue() > 0)) {
            this.f14462e.setWillNotDraw(true);
            B();
            String str = this.f14471n.a;
            if (str != null) {
                this.f14462e.a(MantoUtils.getNonNull(str));
            }
            if (InputUtil.isTrue(this.f14471n.w)) {
                t();
                s();
            } else {
                b(this.f14462e, this.f14471n);
            }
            this.f14462e.setWillNotDraw(false);
            this.f14462e.invalidate();
            return true;
        }
        return false;
    }

    boolean a(y yVar, com.jingdong.manto.widget.input.a0.f fVar) {
        WeakReference<com.jingdong.manto.q.n> weakReference;
        com.jingdong.manto.widget.input.i iVar;
        return (yVar == null || (weakReference = this.f14470m) == null || weakReference.get() == null || (iVar = this.f14470m.get().v) == null || !iVar.a(this.f14470m.get().s(), yVar, fVar.f14402c.intValue(), fVar.d.intValue(), fVar.f14404f.intValue(), fVar.f14403e.intValue(), fVar.b.booleanValue())) ? false : true;
    }

    @Override // com.jingdong.manto.widget.input.z.b
    public boolean b() {
        com.jingdong.manto.widget.input.a0.f fVar = this.f14471n;
        return fVar != null && InputUtil.isTrue(fVar.B);
    }

    boolean b(y yVar, com.jingdong.manto.widget.input.a0.f fVar) {
        WeakReference<com.jingdong.manto.q.n> weakReference;
        com.jingdong.manto.widget.input.i iVar;
        return (yVar == null || (weakReference = this.f14470m) == null || weakReference.get() == null || (iVar = this.f14470m.get().v) == null || !iVar.a(this.f14470m.get().s(), yVar, fVar.f14402c.intValue(), fVar.d.intValue(), fVar.f14404f.intValue(), fVar.f14403e.intValue())) ? false : true;
    }

    @Override // com.jingdong.manto.widget.input.z.b
    public boolean d() {
        h();
        return true;
    }

    @Override // com.jingdong.manto.widget.input.z.b
    public int e() {
        Integer num;
        com.jingdong.manto.widget.input.a0.f fVar = this.f14471n;
        if (fVar == null || (num = fVar.y) == null) {
            y yVar = this.f14462e;
            return (yVar == null || !yVar.a()) ? 0 : 5;
        }
        return num.intValue();
    }

    @Override // com.jingdong.manto.widget.input.z.b
    public boolean f() {
        if (z()) {
            w();
            return true;
        }
        return false;
    }

    @Override // com.jingdong.manto.q.i.c
    public void g() {
        e.h hVar = this.q;
        if (hVar != null) {
            hVar.a(true);
        }
    }

    public final int m() {
        return this.f14464g;
    }

    @Override // com.jingdong.manto.widget.input.z.b
    /* renamed from: n  reason: merged with bridge method [inline-methods] */
    public y c() {
        return this.f14462e;
    }

    public boolean r() {
        y yVar = this.f14462e;
        return yVar != null && (yVar.isFocused() || (l() != null && l().getAttachedEditText() == this.f14462e));
    }

    public abstract void v();

    public abstract void x();

    public abstract void y();
}
