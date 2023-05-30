package com.jingdong.manto.widget.input;

import android.graphics.Rect;
import android.text.Editable;
import android.text.Selection;
import android.view.View;
import android.widget.EditText;
import com.jingdong.manto.ui.f.a.a;
import com.jingdong.manto.widget.input.z.c;
import com.jingdong.manto.widget.input.z.d;
import java.lang.ref.WeakReference;

/* loaded from: classes16.dex */
public abstract class c<Input extends EditText & com.jingdong.manto.widget.input.z.d> extends com.jingdong.manto.ui.e implements com.jingdong.manto.widget.input.z.b {
    public com.jingdong.manto.widget.input.z.c a;
    public com.jingdong.manto.widget.input.z.h b;

    /* renamed from: c */
    public WeakReference<com.jingdong.manto.q.n> f14446c;
    public final View.OnFocusChangeListener d = new a();

    /* renamed from: e */
    private final a.InterfaceC0687a f14447e = new b();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class a implements View.OnFocusChangeListener {
        a() {
            c.this = r1;
        }

        @Override // android.view.View.OnFocusChangeListener
        public final void onFocusChange(View view, boolean z) {
            c.this.a(z);
            if (z) {
                o.a(c.this.f14446c.get(), (com.jingdong.manto.widget.input.z.d) c.this.g());
                ((com.jingdong.manto.widget.input.z.d) c.this.g()).setInputId(c.this.h());
                o.a(c.this.h(), c.this);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class b implements a.InterfaceC0687a {
        b() {
            c.this = r1;
        }

        @Override // com.jingdong.manto.ui.f.a.a.InterfaceC0687a
        public void a() {
            if (c.this.g() != null) {
                c cVar = c.this;
                cVar.a(cVar.g().getEditableText());
            }
        }

        @Override // com.jingdong.manto.ui.f.a.a.InterfaceC0687a
        public void a(String str) {
        }

        @Override // com.jingdong.manto.ui.f.a.a.InterfaceC0687a
        public void b() {
        }
    }

    public c(String str, com.jingdong.manto.q.n nVar) {
        this.f14446c = new WeakReference<>(nVar);
    }

    public static c a(String str, com.jingdong.manto.q.n nVar, com.jingdong.manto.widget.input.a0.f fVar) {
        if ("digit".equalsIgnoreCase(str) || "idcard".equalsIgnoreCase(str) || "number".equalsIgnoreCase(str)) {
            return new r(str, nVar, fVar);
        }
        return null;
    }

    public void a(Editable editable) {
        com.jingdong.manto.widget.input.z.c cVar = this.a;
        if (cVar != null) {
            cVar.a(editable == null ? "" : editable.toString(), Selection.getSelectionEnd(editable), c.a.CHANGED);
        }
    }

    protected abstract com.jingdong.manto.widget.input.a0.g a(com.jingdong.manto.widget.input.a0.g gVar);

    @Override // com.jingdong.manto.widget.input.z.b
    public final void a(String str, Integer num) {
        a(str);
        Integer valueOf = Integer.valueOf(num == null ? -1 : num.intValue());
        b(valueOf.intValue(), valueOf.intValue());
    }

    @Override // com.jingdong.manto.widget.input.z.b
    public boolean a(com.jingdong.manto.q.n nVar) {
        return nVar != null && nVar == this.f14446c.get();
    }

    public abstract boolean a(String str);

    protected abstract boolean a(boolean z);

    @Override // com.jingdong.manto.ui.e, android.text.TextWatcher
    public final void afterTextChanged(Editable editable) {
        a(editable);
    }

    @Override // com.jingdong.manto.widget.input.z.b
    public final void b(int i2) {
        com.jingdong.manto.widget.input.z.h hVar = this.b;
        if (hVar != null) {
            hVar.a(i2);
        }
    }

    public final void b(int i2, int i3) {
        h.a(g(), i2, i3);
    }

    public final void b(Editable editable) {
        com.jingdong.manto.widget.input.z.c cVar = this.a;
        if (cVar != null) {
            cVar.a(editable == null ? "" : editable.toString(), Selection.getSelectionEnd(editable), c.a.COMPLETE);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0025 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean b(com.jingdong.manto.widget.input.a0.g r3) {
        /*
            r2 = this;
            com.jingdong.manto.widget.input.a0.g r3 = r2.a(r3)
            r0 = 0
            if (r3 != 0) goto L8
            return r0
        L8:
            java.lang.Integer r1 = r3.f14412n
            if (r1 != 0) goto L15
            r1 = 140(0x8c, float:1.96E-43)
        Le:
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r3.f14412n = r1
            goto L1f
        L15:
            int r1 = r1.intValue()
            if (r1 > 0) goto L1f
            r1 = 2147483647(0x7fffffff, float:NaN)
            goto Le
        L1f:
            android.widget.EditText r1 = r2.g()
            if (r1 != 0) goto L26
            return r0
        L26:
            android.widget.EditText r1 = r2.g()
            com.jingdong.manto.ui.f.a.a r1 = com.jingdong.manto.ui.f.a.a.a(r1)
            java.lang.Integer r3 = r3.f14412n
            int r3 = r3.intValue()
            com.jingdong.manto.ui.f.a.a r3 = r1.a(r3)
            r3.f14289c = r0
            r0 = 1
            r3.b = r0
            com.jingdong.manto.ui.f.a.a$a r1 = r2.f14447e
            r3.a(r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.manto.widget.input.c.b(com.jingdong.manto.widget.input.a0.g):boolean");
    }

    @Override // com.jingdong.manto.widget.input.z.b
    public Input c() {
        return g();
    }

    @Override // com.jingdong.manto.widget.input.z.b
    public boolean f() {
        return k();
    }

    public abstract Input g();

    public abstract int h();

    public abstract Rect i();

    public final Editable j() {
        if (g() == null) {
            return null;
        }
        return g().getEditableText();
    }

    public final boolean k() {
        i iVar;
        Input g2 = g();
        if (g2 == null) {
            return false;
        }
        Input input = g2;
        input.b(this.d);
        g2.removeTextChangedListener(this);
        input.destroy();
        com.jingdong.manto.q.n nVar = this.f14446c.get();
        if (nVar == null || (iVar = nVar.v) == null) {
            return false;
        }
        iVar.a((i) g2);
        return true;
    }
}
