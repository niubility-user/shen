package com.jd.manto.center.widget;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.content.ContextCompat;
import com.jd.manto.center.R;
import com.jingdong.manto.utils.MantoDensityUtils;

/* loaded from: classes17.dex */
public class MantoSearchEditText extends AppCompatEditText {

    /* renamed from: g  reason: collision with root package name */
    private Drawable f6573g;

    /* renamed from: h  reason: collision with root package name */
    private Drawable f6574h;

    /* renamed from: i  reason: collision with root package name */
    private boolean f6575i;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class a implements TextWatcher {
        a() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            MantoSearchEditText.this.m();
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class b implements View.OnFocusChangeListener {
        b() {
        }

        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View view, boolean z) {
            MantoSearchEditText.this.f6575i = z;
            MantoSearchEditText.this.m();
        }
    }

    public MantoSearchEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    private void init() {
        this.f6573g = ContextCompat.getDrawable(getContext(), R.drawable.manto_center_widget_edittext_search);
        this.f6574h = ContextCompat.getDrawable(getContext(), R.drawable.manto_center_widget_edittext_clear);
        setPadding(getPaddingLeft() + MantoDensityUtils.dip2pixel(getContext(), 11), getPaddingTop(), getPaddingRight() + MantoDensityUtils.dip2pixel(getContext(), 11), getPaddingBottom());
        Drawable drawable = this.f6573g;
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth() + 8, this.f6573g.getIntrinsicHeight() + 8);
        Drawable drawable2 = this.f6574h;
        drawable2.setBounds(0, 0, drawable2.getIntrinsicWidth() + 8, this.f6574h.getIntrinsicHeight() + 8);
        setCompoundDrawables(this.f6573g, null, null, null);
        setCompoundDrawablePadding(MantoDensityUtils.dip2pixel(getContext(), 6));
        addTextChangedListener(new a());
        setOnFocusChangeListener(new b());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        if (!this.f6575i) {
            setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], null, getCompoundDrawables()[3]);
        } else if (!TextUtils.isEmpty(getText())) {
            setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], this.f6574h, getCompoundDrawables()[3]);
        } else {
            setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], null, getCompoundDrawables()[3]);
        }
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.f6575i) {
            if (motionEvent.getAction() == 1) {
                Rect bounds = this.f6574h.getBounds();
                int width = bounds.width();
                int height = bounds.height();
                int measuredHeight = (getMeasuredHeight() - height) >> 1;
                int measuredWidth = getMeasuredWidth() - getPaddingRight();
                boolean z = motionEvent.getX() <= ((float) measuredWidth) && motionEvent.getX() >= ((float) (measuredWidth - width));
                boolean z2 = motionEvent.getY() >= ((float) measuredHeight) && motionEvent.getY() <= ((float) (measuredHeight + height));
                if (z && z2) {
                    setError(null);
                    setText("");
                }
            }
        }
        return super.onTouchEvent(motionEvent);
    }
}
