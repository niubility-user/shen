package com.jingdong.manto.widget.input;

import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import com.jingdong.manto.utils.MantoDensityUtils;
import com.jingdong.manto.widget.input.z.d;

/* loaded from: classes16.dex */
public final class b extends y {

    /* renamed from: n  reason: collision with root package name */
    private com.jingdong.manto.widget.l.a f14445n;
    private final InputFilter o;
    private float p;
    float q;
    private float r;
    private boolean s;

    /* loaded from: classes16.dex */
    class a implements InputFilter {
        a() {
        }

        @Override // android.text.InputFilter
        public CharSequence filter(CharSequence charSequence, int i2, int i3, Spanned spanned, int i4, int i5) {
            if (TextUtils.isEmpty(charSequence) || b.this.f14445n == null) {
                return null;
            }
            (charSequence instanceof Spannable ? (Spannable) charSequence : new SpannableStringBuilder(charSequence)).setSpan(b.this.f14445n, 0, charSequence.length(), 18);
            return charSequence;
        }
    }

    /* renamed from: com.jingdong.manto.widget.input.b$b  reason: collision with other inner class name */
    /* loaded from: classes16.dex */
    class C0697b extends Spannable.Factory {
        C0697b() {
        }

        @Override // android.text.Spannable.Factory
        public final Spannable newSpannable(CharSequence charSequence) {
            Spannable newSpannable = super.newSpannable(charSequence);
            if (b.this.f14445n != null && !TextUtils.isEmpty(newSpannable)) {
                newSpannable.setSpan(b.this.f14445n, 0, newSpannable.length(), 18);
            }
            return newSpannable;
        }
    }

    /* loaded from: classes16.dex */
    class c implements d.c {
        c() {
        }

        @Override // com.jingdong.manto.widget.input.z.d.c
        public void a() {
            b.this.l();
        }
    }

    /* loaded from: classes16.dex */
    class d implements View.OnLongClickListener {
        d() {
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            return b.this.k();
        }
    }

    public b(Context context) {
        super(context);
        this.o = new a();
        this.p = 0.0f;
        this.q = 1.2f;
        this.r = getTextSize();
        this.s = false;
        super.setSingleLine(false);
        super.setLineSpacing(0.0f, 1.0f);
        super.setVerticalScrollbarPosition(2);
        super.setSpannableFactory(new C0697b());
        super.a(new c());
        super.setOnLongClickListener(new d());
        setAutoHeight(false);
        a(0.0f, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(float f2, boolean z) {
        if (f2 <= 0.0f) {
            f2 = (this.q * this.r) + this.p;
        }
        com.jingdong.manto.widget.l.a aVar = this.f14445n;
        if (aVar == null || aVar.a(f2)) {
            this.f14445n = new com.jingdong.manto.widget.l.a(f2);
            if (z) {
                if (hasFocus()) {
                    invalidate();
                    return;
                }
                j();
                setText(getEditableText());
                i();
            }
        }
    }

    @Override // com.jingdong.manto.widget.input.z.d
    public final boolean a() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.jingdong.manto.widget.input.y
    public final Editable b(Editable editable) {
        Editable b = super.b(editable);
        if (this.f14445n != null && !TextUtils.isEmpty(b)) {
            b.setSpan(this.f14445n, 0, b.length(), 18);
        }
        return b;
    }

    @Override // android.view.View
    public final boolean canScrollVertically(int i2) {
        if (g() <= getHeight()) {
            return false;
        }
        return super.canScrollVertically(i2);
    }

    @Override // com.jingdong.manto.widget.input.y
    public final boolean f() {
        return false;
    }

    @Override // android.widget.TextView
    public final int getLineHeight() {
        com.jingdong.manto.widget.l.a aVar = this.f14445n;
        return aVar != null ? aVar.a : super.getLineHeight();
    }

    @Override // com.jingdong.manto.widget.input.y
    public final void h() {
        InputUtil.getInputManager(this).restartInput(this);
    }

    public final boolean k() {
        if (!this.s) {
            g();
            getMeasuredHeight();
        }
        return (isFocusable() || e()) ? false : true;
    }

    protected final void l() {
        int measuredWidth;
        int minHeight;
        if (this.s) {
            if (getMeasuredHeight() > getMaxHeight()) {
                measuredWidth = getMeasuredWidth();
                minHeight = getMaxHeight();
            } else if (getMeasuredHeight() >= getMinHeight() || getMinHeight() <= 0) {
                return;
            } else {
                measuredWidth = getMeasuredWidth();
                minHeight = getMinHeight();
            }
            setMeasuredDimension(measuredWidth, minHeight);
        }
    }

    @Override // android.view.View
    public final boolean performHapticFeedback(int i2, int i3) {
        return super.performHapticFeedback(i2, i3);
    }

    public final void setAutoHeight(boolean z) {
        this.s = z;
        setVerticalScrollBarEnabled(!z);
    }

    @Override // android.widget.TextView
    public final void setFilters(InputFilter[] inputFilterArr) {
        if (this.o != null) {
            int i2 = 0;
            if (inputFilterArr == null) {
                inputFilterArr = new InputFilter[0];
            }
            InputFilter[] inputFilterArr2 = new InputFilter[inputFilterArr.length + 1];
            while (i2 < inputFilterArr.length) {
                inputFilterArr2[i2] = inputFilterArr[i2];
                i2++;
            }
            inputFilterArr2[i2] = this.o;
            inputFilterArr = inputFilterArr2;
        }
        super.setFilters(inputFilterArr);
    }

    @Override // android.widget.TextView
    public final void setGravity(int i2) {
        super.setGravity((i2 & (-81) & (-17)) | 48);
    }

    @Override // com.jingdong.manto.widget.input.y, android.widget.TextView
    public final void setInputType(int i2) {
        super.setInputType(i2 | 131072);
    }

    public final void setLineHeight(float f2) {
        if (f2 > 0.0f) {
            a(f2, true);
        }
    }

    public final void setLineSpace(float f2) {
        setLineSpacing(f2, this.q);
    }

    @Override // android.widget.TextView
    public final void setLineSpacing(float f2, float f3) {
        this.p = f2;
        this.q = f3;
        a(0.0f, true);
    }

    @Override // com.jingdong.manto.widget.input.y, android.widget.TextView
    public final void setSingleLine(boolean z) {
    }

    @Override // android.widget.TextView
    public final void setTextSize(int i2, float f2) {
        super.setTextSize(i2, f2);
        this.r = TypedValue.applyDimension(i2, f2, MantoDensityUtils.getDM());
        a(0.0f, true);
    }
}
