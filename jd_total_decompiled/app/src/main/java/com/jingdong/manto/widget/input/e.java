package com.jingdong.manto.widget.input;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.jingdong.manto.R;
import com.jingdong.manto.utils.MantoDensityUtils;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.c0;
import com.jingdong.manto.widget.input.p;

/* loaded from: classes16.dex */
public class e extends LinearLayout implements p.a {
    private boolean a;
    public EditText b;

    /* renamed from: c  reason: collision with root package name */
    h f14448c;
    private boolean d;

    /* renamed from: e  reason: collision with root package name */
    public i f14449e;

    /* renamed from: f  reason: collision with root package name */
    private f f14450f;

    /* renamed from: g  reason: collision with root package name */
    private View f14451g;

    /* renamed from: h  reason: collision with root package name */
    private int f14452h;

    /* renamed from: i  reason: collision with root package name */
    j f14453i;

    /* renamed from: j  reason: collision with root package name */
    private c0 f14454j;

    /* loaded from: classes16.dex */
    class a implements Runnable {
        final /* synthetic */ int a;

        a(int i2) {
            this.a = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            j jVar = e.this.f14453i;
            jVar.a = this.a;
            if (jVar.a()) {
                e.this.post(this);
            } else {
                e.this.f14453i.requestLayout();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class b implements View.OnClickListener {
        b() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            e.this.b(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class c implements View.OnClickListener {
        c(e eVar) {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
        }
    }

    /* loaded from: classes16.dex */
    class d implements c0.c {
        d() {
        }

        @Override // com.jingdong.manto.utils.c0.c
        public void a() {
        }

        @Override // com.jingdong.manto.utils.c0.c
        public void b() {
        }

        @Override // com.jingdong.manto.utils.c0.c
        public void c() {
            e.this.b(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.manto.widget.input.e$e  reason: collision with other inner class name */
    /* loaded from: classes16.dex */
    public final class C0698e extends FrameLayout implements f {
        private boolean a;

        public C0698e(e eVar, Context context) {
            super(context);
            this.a = false;
            addView(LayoutInflater.from(context).inflate(R.layout.manto_input_textarea_panel_wrapper, (ViewGroup) null));
        }

        @Override // com.jingdong.manto.widget.input.e.f
        public final void a(boolean z) {
            Integer num = z != this.a ? 1 : null;
            this.a = z;
            if (num == null) {
                return;
            }
            if (Build.VERSION.SDK_INT < 18 || !isInLayout()) {
                requestLayout();
            }
        }

        @Override // android.widget.FrameLayout, android.view.View
        protected final void onMeasure(int i2, int i3) {
            if (this.a || !isShown()) {
                int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 1073741824);
                i3 = View.MeasureSpec.makeMeasureSpec(0, 1073741824);
                i2 = makeMeasureSpec;
            }
            super.onMeasure(i2, i3);
        }
    }

    /* loaded from: classes16.dex */
    public interface f {
        void a(boolean z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class g implements Runnable {
        final int a;
        final boolean b;

        g(e eVar, int i2, boolean z) {
            this.a = i2;
            this.b = z;
        }

        @Override // java.lang.Runnable
        public final void run() {
            e eVar;
            int i2;
            int i3 = e.this.f14452h;
            if (e.this.isShown()) {
                eVar = e.this;
                i2 = this.a;
            } else {
                eVar = e.this;
                i2 = 2;
            }
            eVar.f14452h = i2;
            e eVar2 = e.this;
            if (eVar2.f14449e == null || i3 == eVar2.f14452h || this.b) {
                return;
            }
            e eVar3 = e.this;
            eVar3.f14449e.a(eVar3.f14452h);
        }
    }

    /* loaded from: classes16.dex */
    public interface h {
        void a(boolean z);
    }

    /* loaded from: classes16.dex */
    public interface i {
        void a(int i2);
    }

    /* loaded from: classes16.dex */
    private static class j extends LinearLayout {
        int a;
        boolean b;

        /* renamed from: c  reason: collision with root package name */
        int f14456c;

        public j(Context context, boolean z, int i2) {
            super(context);
            this.a = -1;
            this.f14456c = i2;
        }

        boolean a() {
            return Build.VERSION.SDK_INT >= 18 ? super.isInLayout() : this.b;
        }

        @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
        protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
            this.b = true;
            super.onLayout(z, i2, i3, i4, i5);
            this.b = false;
        }

        /* JADX WARN: Code restructure failed: missing block: B:23:0x004c, code lost:
            if (r2.toLowerCase().contains("redmi") != false) goto L26;
         */
        @Override // android.widget.LinearLayout, android.view.View
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        protected void onMeasure(int i2, int i3) {
            int i4 = this.a;
            if (i4 <= 0) {
                i4 = com.jingdong.manto.utils.e.b(getContext());
            }
            try {
                String b = com.jingdong.manto.utils.q.b();
                String d = com.jingdong.manto.utils.q.d();
                boolean z = true;
                boolean z2 = (b != null && b.toLowerCase().contains("redmi7")) || (d != null && d.toLowerCase().contains("redmi7"));
                if (b == null || !b.toLowerCase().contains("redmi")) {
                    if (d != null) {
                    }
                    z = false;
                }
                if (z2) {
                    i4 += 90;
                } else if (z) {
                    i4 += 40;
                }
            } catch (Throwable unused) {
            }
            int i5 = this.f14456c;
            super.onMeasure(i2, View.MeasureSpec.makeMeasureSpec((i5 <= 0 || i5 <= i4) ? (i5 <= 0 || i5 >= i4) ? i4 : i4 - i5 : 0, 1073741824));
        }
    }

    public e(Context context, boolean z, int i2) {
        super(context);
        this.d = false;
        this.f14452h = 2;
        super.setId(R.id.mantp_input_panel);
        setOrientation(1);
        f fVar = (f) getStateView();
        this.f14450f = fVar;
        addView((View) fVar);
        j jVar = new j(context, z, i2);
        this.f14453i = jVar;
        addView(jVar);
        i();
    }

    public static e a(View view) {
        return (e) view.getRootView().findViewById(R.id.mantp_input_panel);
    }

    public static e a(View view, boolean z, int i2) {
        x b2 = x.b(view);
        com.jingdong.manto.widget.i.a aVar = b2.b;
        if (aVar == null || !(aVar instanceof p)) {
            b2.b = new p();
        }
        e a2 = a(view);
        if (a2 != null) {
            return a2;
        }
        e eVar = new e(view.getContext(), z, i2);
        b2.a(eVar);
        return eVar;
    }

    private void a(int i2) {
        post(new g(this, i2, this.d));
    }

    private void c() {
        com.jingdong.manto.widget.i.a aVar;
        x b2 = x.b(this);
        if (b2 == null || (aVar = b2.b) == null) {
            return;
        }
        ((p) aVar).f14484e = this;
    }

    private void e() {
        a(0);
    }

    private void f() {
        com.jingdong.manto.widget.i.a aVar;
        x b2 = x.b(this);
        if (b2 == null || (aVar = b2.b) == null) {
            return;
        }
        ((p) aVar).f14484e = null;
    }

    private void h() {
        e();
        EditText editText = this.b;
        if (editText == null) {
            com.jingdong.manto.utils.e.b(this);
            return;
        }
        InputMethodManager inputMethodManager = (InputMethodManager) editText.getContext().getSystemService("input_method");
        if (inputMethodManager.showSoftInput(this.b, 1)) {
            return;
        }
        inputMethodManager.showSoftInput(this.b, 2);
    }

    public void a() {
        b();
        setConfirmViewVisible(this.a);
    }

    public final void a(EditText editText) {
        if (editText == this.b) {
            this.b = null;
        }
    }

    @Override // com.jingdong.manto.widget.input.p.a
    public final void a(boolean z) {
        MantoLog.d("CustomPanel", String.format("onKeyboardStateChanged, kbShown = %b", Boolean.valueOf(z)));
        if (z) {
            setVisiablityAndListener(0);
            e();
        }
    }

    public final void b() {
        i();
    }

    protected final void b(boolean z) {
        h hVar;
        if (this.d || (hVar = this.f14448c) == null) {
            return;
        }
        this.d = true;
        hVar.a(z);
        this.d = false;
    }

    public final void d() {
        if (isShown()) {
            setVisiablityAndListener(8);
            if (!com.jingdong.manto.utils.e.a(this)) {
                InputUtil.getInputManager(this).hideSoftInputFromWindow(getWindowToken(), 0);
            }
            e();
        }
    }

    public void g() {
        h();
        a();
        if (isShown()) {
            return;
        }
        setVisiablityAndListener(0);
    }

    public EditText getAttachedEditText() {
        return this.b;
    }

    @Override // android.view.View
    public int getMinimumHeight() {
        if (getVisibility() == 0) {
            return MantoDensityUtils.dip2pixel(getContext(), 48);
        }
        return 0;
    }

    public <T extends View & f> T getStateView() {
        C0698e c0698e = new C0698e(this, getContext());
        View findViewById = c0698e.findViewById(R.id.manto_input_state_done);
        this.f14451g = findViewById;
        findViewById.setOnClickListener(new b());
        c0698e.setOnClickListener(new c(this));
        return c0698e;
    }

    public void i() {
        f fVar = this.f14450f;
        if (fVar != null) {
            fVar.a(!this.a);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        c();
        c0 c0Var = new c0(getContext().getApplicationContext());
        this.f14454j = c0Var;
        c0Var.a(new d());
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setVisiablityAndListener(8);
        EditText editText = this.b;
        if (editText != null) {
            com.jingdong.manto.utils.e.a(editText);
        } else {
            com.jingdong.manto.utils.e.a(this);
        }
        removeAllViews();
        f();
        c0 c0Var = this.f14454j;
        if (c0Var != null) {
            c0Var.c();
        }
    }

    public final void setConfirmViewVisible(boolean z) {
        this.a = z;
        View view = this.f14451g;
        if (view != null) {
            view.setVisibility(z ? 0 : 4);
        }
        i();
    }

    @Override // com.jingdong.manto.widget.input.p.a
    public final void setHeight(int i2) {
        a aVar = new a(i2);
        if (this.f14453i.a != i2) {
            aVar.run();
        }
    }

    @Override // android.view.View
    public void setId(int i2) {
    }

    final void setVisiablityAndListener(int i2) {
        if (getVisibility() != i2) {
            super.setVisibility(i2);
            if (i2 == 0) {
                c();
            } else {
                f();
            }
        }
    }

    @Override // android.view.View
    public void setVisibility(int i2) {
        if (i2 == 8) {
            if (getVisibility() != i2) {
                b(false);
            }
            d();
        } else if (i2 == 0) {
            g();
        } else {
            setVisiablityAndListener(i2);
        }
    }
}
