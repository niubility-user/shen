package com.jingdong.manto.widget.input;

import android.graphics.Rect;
import android.view.View;
import android.widget.EditText;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoUtils;
import com.jingdong.manto.widget.input.InputUtil;
import com.jingdong.manto.widget.input.s;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes16.dex */
public final class r extends c<t> {

    /* renamed from: f  reason: collision with root package name */
    final int f14485f;

    /* renamed from: g  reason: collision with root package name */
    int f14486g;

    /* renamed from: h  reason: collision with root package name */
    t f14487h;

    /* renamed from: i  reason: collision with root package name */
    s f14488i;

    /* renamed from: j  reason: collision with root package name */
    com.jingdong.manto.widget.input.a0.g f14489j;

    /* renamed from: k  reason: collision with root package name */
    boolean f14490k;

    /* renamed from: l  reason: collision with root package name */
    boolean f14491l;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class a implements s.b {
        a() {
        }

        @Override // com.jingdong.manto.widget.input.s.b
        public final void a() {
            r rVar = r.this;
            rVar.b(rVar.j());
            r.this.a(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public r(String str, com.jingdong.manto.q.n nVar, com.jingdong.manto.widget.input.a0.f fVar) {
        super(str, nVar);
        this.f14486g = 0;
        t tVar = new t(nVar.f14071i);
        this.f14487h = tVar;
        InputUtil.a.b(tVar);
        this.f14485f = fVar.H;
        this.f14486g = MantoUtils.getInt(o.a.get(str), 0);
    }

    private s l() {
        s sVar = this.f14488i;
        if (sVar != null) {
            return sVar;
        }
        s a2 = s.a(this.f14487h);
        this.f14488i = a2;
        return a2;
    }

    private boolean n() {
        t tVar = this.f14487h;
        if (tVar == null) {
            return false;
        }
        if (tVar.isFocused()) {
            return true;
        }
        l().isShown();
        EditText editText = this.f14488i.d;
        return l() != null && l().isShown() && this.f14488i.d == this.f14487h;
    }

    @Override // com.jingdong.manto.widget.input.z.b
    public final View a() {
        l();
        return this.f14488i;
    }

    @Override // com.jingdong.manto.widget.input.c
    public final com.jingdong.manto.widget.input.a0.g a(com.jingdong.manto.widget.input.a0.g gVar) {
        t tVar;
        com.jingdong.manto.widget.input.a0.g gVar2 = this.f14489j;
        if (gVar2 == null) {
            this.f14489j = gVar;
            if (InputUtil.isTrue(gVar.F) && (tVar = this.f14487h) != null) {
                tVar.setPasswordMode(true);
            }
        } else {
            gVar2.a(gVar);
        }
        t tVar2 = this.f14487h;
        if (tVar2 == null) {
            return null;
        }
        h.a(tVar2, this.f14489j);
        return this.f14489j;
    }

    @Override // com.jingdong.manto.widget.input.z.b
    public final boolean a(int i2, int i3) {
        if (this.f14487h == null) {
            return false;
        }
        s b = s.b(this.f14446c.get().l());
        this.f14488i = b;
        if (b == null) {
            return false;
        }
        this.f14491l = true;
        com.jingdong.manto.q.n nVar = this.f14446c.get();
        if (nVar != null && nVar.s() != null) {
            u.a().a(nVar.s());
        }
        this.f14488i.a.setXMode(this.f14486g);
        s sVar = this.f14488i;
        t tVar = this.f14487h;
        if (tVar != null) {
            if (sVar.d != tVar) {
                sVar.a();
            }
            sVar.setInputEditText(tVar);
            InputUtil.a.a(sVar.d);
            sVar.d.requestFocus();
            sVar.setVisibility(0);
        }
        this.f14488i.f14492c = new a();
        b(i2, i3);
        l.a(this.f14446c).c();
        this.f14491l = false;
        return true;
    }

    @Override // com.jingdong.manto.widget.input.c
    public final boolean a(String str) {
        t tVar = this.f14487h;
        if (tVar == null) {
            return false;
        }
        tVar.a(str);
        return true;
    }

    @Override // com.jingdong.manto.widget.input.c
    protected final boolean a(boolean z) {
        MantoLog.d("NumberInputComponent", String.format("[input_switch] onFocusChanged hasFocus %b, isFocused %b", Boolean.valueOf(z), Boolean.valueOf(n())));
        if (z) {
            if (!this.f14491l && !n()) {
                this.f14491l = true;
                MantoLog.e("NumberInputComponent", "onFocusChanged:   show keyboard========");
                a(-2, -2);
                this.f14491l = false;
            }
        } else if (!this.f14490k && n()) {
            this.f14490k = true;
            b(j());
            d();
            k();
            this.f14490k = false;
            this.f14487h = null;
        }
        return true;
    }

    @Override // com.jingdong.manto.widget.input.z.b
    public boolean b() {
        com.jingdong.manto.widget.input.a0.g gVar = this.f14489j;
        return gVar != null && InputUtil.isTrue(gVar.B);
    }

    @Override // com.jingdong.manto.widget.input.z.b
    public final boolean d() {
        if (l() == null) {
            return false;
        }
        s sVar = this.f14488i;
        sVar.setVisibility(8);
        sVar.a();
        MantoLog.d("NumberInputComponent", String.format("[input_switch] disableInputFocus %s", this.f14487h));
        t tVar = this.f14487h;
        if (tVar != null) {
            tVar.setFocusable(false);
            this.f14487h.setFocusableInTouchMode(false);
            this.f14487h.setEnabled(false);
        }
        com.jingdong.manto.q.n nVar = this.f14446c.get();
        if (nVar != null && nVar.s() != null) {
            u.a().c(nVar.s());
        }
        l.a(this.f14446c).b();
        return true;
    }

    @Override // com.jingdong.manto.widget.input.z.b
    public final int e() {
        Integer num;
        com.jingdong.manto.widget.input.a0.g gVar = this.f14489j;
        if (gVar == null || (num = gVar.y) == null) {
            return 0;
        }
        return num.intValue();
    }

    @Override // com.jingdong.manto.widget.input.c
    public final int h() {
        return this.f14485f;
    }

    @Override // com.jingdong.manto.widget.input.c
    public final Rect i() {
        return new Rect(this.f14489j.f14404f.intValue(), this.f14489j.f14403e.intValue(), this.f14489j.f14404f.intValue() + this.f14489j.f14402c.intValue(), this.f14489j.f14403e.intValue() + this.f14489j.d.intValue());
    }

    @Override // com.jingdong.manto.widget.input.c
    /* renamed from: m  reason: merged with bridge method [inline-methods] */
    public t g() {
        return this.f14487h;
    }
}
