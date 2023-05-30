package com.jd.lib.cashier.sdk.core.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import com.jd.lib.cashier.sdk.R;

/* loaded from: classes14.dex */
public class CashierEditText extends EditText implements View.OnFocusChangeListener, TextWatcher {

    /* renamed from: g  reason: collision with root package name */
    private Drawable f2979g;

    /* renamed from: h  reason: collision with root package name */
    private boolean f2980h;

    /* renamed from: i  reason: collision with root package name */
    private boolean f2981i;

    /* renamed from: j  reason: collision with root package name */
    private int f2982j;

    /* renamed from: k  reason: collision with root package name */
    private a f2983k;

    /* loaded from: classes14.dex */
    public interface a {
        void afterTextChanged(Editable editable);

        void onFocusChange(View view, boolean z);

        void onTextChanged(CharSequence charSequence, int i2, int i3, int i4);
    }

    public CashierEditText(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842862);
    }

    private void a() {
        Drawable drawable = getCompoundDrawables()[2];
        this.f2979g = drawable;
        if (drawable == null) {
            this.f2979g = getResources().getDrawable(R.drawable.lib_cashier_sdk_delete_selector);
        }
        Drawable drawable2 = this.f2979g;
        drawable2.setBounds(0, 0, drawable2.getIntrinsicWidth(), this.f2979g.getIntrinsicHeight());
        if (!TextUtils.isEmpty(getText())) {
            b(getText().length() > 0);
        } else {
            b(false);
        }
        setOnFocusChangeListener(this);
        addTextChangedListener(this);
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
        a aVar = this.f2983k;
        if (aVar != null) {
            aVar.afterTextChanged(editable);
        }
    }

    public void b(boolean z) {
        setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], z ? this.f2979g : null, getCompoundDrawables()[3]);
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
    }

    @Override // android.view.View.OnFocusChangeListener
    public void onFocusChange(View view, boolean z) {
        this.f2980h = z;
        if (z) {
            b(getText().length() > 0);
        } else {
            b(false);
        }
        a aVar = this.f2983k;
        if (aVar != null) {
            aVar.onFocusChange(view, z);
        }
    }

    @Override // android.widget.TextView
    protected void onSelectionChanged(int i2, int i3) {
        super.onSelectionChanged(i2, i3);
        boolean z = this.f2981i;
        this.f2981i = true;
    }

    @Override // android.widget.TextView, android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        if (this.f2980h) {
            b(charSequence.length() > 0);
        }
        a aVar = this.f2983k;
        if (aVar != null) {
            aVar.onTextChanged(charSequence, i2, i3, i4);
        }
        if (charSequence.length() > this.f2982j) {
            this.f2981i = false;
        } else {
            this.f2981i = true;
        }
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1 && getCompoundDrawables()[2] != null) {
            if (motionEvent.getX() > ((float) (getWidth() - getTotalPaddingRight())) && motionEvent.getX() < ((float) (getWidth() - getPaddingRight()))) {
                setText("");
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    public CashierEditText(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        a();
    }
}
