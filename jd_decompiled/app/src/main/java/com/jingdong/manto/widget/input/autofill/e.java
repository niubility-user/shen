package com.jingdong.manto.widget.input.autofill;

import android.database.DataSetObserver;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Filter;
import android.widget.PopupWindow;
import com.jingdong.manto.widget.input.autofill.AutoFillListPopupWindowBase;
import com.jingdong.manto.widget.input.z.d;

/* loaded from: classes16.dex */
public class e {
    final com.jingdong.manto.widget.input.z.d a;
    public final com.jingdong.manto.widget.input.autofill.f b;

    /* renamed from: c  reason: collision with root package name */
    private final Filter.FilterListener f14440c;
    public final com.jingdong.manto.widget.input.autofill.b d;

    /* renamed from: e  reason: collision with root package name */
    int f14441e = 2;

    /* renamed from: f  reason: collision with root package name */
    public com.jingdong.manto.widget.input.autofill.a f14442f;

    /* renamed from: g  reason: collision with root package name */
    private int f14443g;

    /* renamed from: h  reason: collision with root package name */
    private int f14444h;

    /* loaded from: classes16.dex */
    class a implements d.a {
        a() {
        }

        @Override // com.jingdong.manto.widget.input.z.d.a
        public void a() {
            e.this.a();
        }
    }

    /* loaded from: classes16.dex */
    class b implements d.c {
        b() {
        }

        @Override // com.jingdong.manto.widget.input.z.d.c
        public void a() {
            e.this.a();
        }
    }

    /* loaded from: classes16.dex */
    class c implements View.OnFocusChangeListener {
        c() {
        }

        @Override // android.view.View.OnFocusChangeListener
        public final void onFocusChange(View view, boolean z) {
            e eVar = e.this;
            if (z) {
                eVar.b();
            } else if (eVar.b.r.isShowing()) {
                eVar.b.c();
                ((com.jingdong.manto.widget.input.autofill.a) eVar.b.s.getAdapter()).a();
            }
        }
    }

    /* loaded from: classes16.dex */
    class d extends com.jingdong.manto.ui.e {
        private boolean a = false;

        d() {
        }

        @Override // com.jingdong.manto.ui.e, android.text.TextWatcher
        public final void afterTextChanged(Editable editable) {
            if (!this.a || e.this.b.r.isShowing()) {
                if (!e.this.b.r.isShowing()) {
                    e.this.b();
                }
                e.this.a(editable);
            }
        }

        @Override // com.jingdong.manto.ui.e, android.text.TextWatcher
        public final void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
            this.a = e.this.b.r.isShowing();
        }
    }

    /* renamed from: com.jingdong.manto.widget.input.autofill.e$e  reason: collision with other inner class name */
    /* loaded from: classes16.dex */
    class C0696e implements Filter.FilterListener {
        C0696e() {
        }

        @Override // android.widget.Filter.FilterListener
        public final void onFilterComplete(int i2) {
            if (i2 <= 0) {
                e.this.b.c();
            } else if (e.this.b.r.isShowing()) {
                e.this.b.e();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class f extends DataSetObserver {
        final /* synthetic */ com.jingdong.manto.widget.input.autofill.b a;

        f(e eVar, com.jingdong.manto.widget.input.autofill.b bVar) {
            this.a = bVar;
        }

        @Override // android.database.DataSetObserver
        public final void onChanged() {
            if (this.a.b.r.isShowing()) {
                this.a.a(1);
            }
        }
    }

    public e(com.jingdong.manto.widget.input.z.d dVar) {
        this.a = dVar;
        com.jingdong.manto.widget.input.autofill.f fVar = new com.jingdong.manto.widget.input.autofill.f(dVar.getContext());
        this.b = fVar;
        this.d = new com.jingdong.manto.widget.input.autofill.b(dVar, fVar);
        dVar.a(new a());
        dVar.a(new b());
        dVar.a(new c());
        dVar.addTextChangedListener(new d());
        this.f14440c = new C0696e();
    }

    final void a() {
        com.jingdong.manto.widget.input.autofill.f fVar;
        int measuredWidth;
        if (this.f14441e == 1) {
            fVar = this.b;
            measuredWidth = com.jingdong.manto.utils.e.e(com.jingdong.manto.c.a())[0];
        } else {
            fVar = this.b;
            measuredWidth = this.a.getView().getMeasuredWidth();
        }
        fVar.b = measuredWidth;
        int i2 = this.f14443g;
        if (i2 != 0) {
            com.jingdong.manto.widget.input.autofill.f fVar2 = this.b;
            fVar2.d = i2;
            fVar2.b -= i2;
        }
        int i3 = this.f14444h;
        if (i3 != 0) {
            this.b.b -= i3;
        }
    }

    public final void a(PopupWindow.OnDismissListener onDismissListener) {
        this.b.r.setOnDismissListener(onDismissListener);
    }

    final void a(CharSequence charSequence) {
        com.jingdong.manto.widget.input.autofill.a aVar = this.f14442f;
        if (aVar != null) {
            aVar.getFilter().filter(charSequence, this.f14440c);
        }
    }

    final void b() {
        if (this.f14442f != null) {
            CharSequence text = this.a.getText();
            if (!TextUtils.isEmpty(text)) {
                a(text);
            }
            this.b.f14428l = this.a.getView();
            this.b.e();
            ((com.jingdong.manto.widget.input.autofill.c) this.b.s.getAdapter()).a(this);
            com.jingdong.manto.widget.input.autofill.b bVar = this.d;
            bVar.a(2);
            bVar.f14437e = Integer.MIN_VALUE;
            if (!TextUtils.isEmpty(text)) {
                bVar.f14438f = true;
            }
            AutoFillListPopupWindowBase.c cVar = bVar.b.s;
            if (cVar != null) {
                cVar.getAdapter().registerDataSetObserver(new f(this, bVar));
            }
        }
    }
}
