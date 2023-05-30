package com.jingdong.app.mall.utils.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

/* loaded from: classes4.dex */
public class MyEditText extends EditText {

    /* renamed from: g  reason: collision with root package name */
    private Drawable f11868g;

    /* renamed from: h  reason: collision with root package name */
    private Rect f11869h;

    /* renamed from: i  reason: collision with root package name */
    private boolean f11870i;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a implements TextWatcher {
        a() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
            MyEditText.this.c();
        }
    }

    public MyEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f11870i = true;
        b();
    }

    private void b() {
        c();
        addTextChangedListener(new a());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        if (getText().toString().length() == 0) {
            setCompoundDrawables(null, null, null, null);
        } else {
            setCompoundDrawables(null, null, this.f11868g, null);
        }
    }

    @Override // android.widget.TextView, android.view.View
    protected void onFocusChanged(boolean z, int i2, Rect rect) {
        if (hasFocus()) {
            if (getText().toString().length() == 0) {
                setCompoundDrawables(null, null, null, null);
            } else {
                setCompoundDrawables(null, null, this.f11868g, null);
            }
        } else {
            setCompoundDrawables(null, null, null, null);
        }
        super.onFocusChanged(z, i2, rect);
    }

    @Override // android.widget.TextView, android.view.View
    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.f11870i && this.f11868g != null && motionEvent.getAction() == 1) {
            this.f11869h = this.f11868g.getBounds();
            if (((int) motionEvent.getX()) > getRight() - (this.f11869h.width() * 3)) {
                setText("");
                motionEvent.setAction(3);
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.widget.TextView
    public void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        if (drawable3 != null) {
            this.f11868g = drawable3;
        }
        super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
    }

    public MyEditText(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f11870i = true;
        b();
    }
}
