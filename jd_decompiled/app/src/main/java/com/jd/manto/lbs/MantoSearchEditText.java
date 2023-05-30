package com.jd.manto.lbs;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import androidx.core.content.ContextCompat;
import com.jd.manto.map.R;
import com.jingdong.manto.utils.MantoDensityUtils;

/* loaded from: classes17.dex */
public class MantoSearchEditText extends EditText {
    private Drawable clearDrawable;
    private boolean hasFocused;
    private Drawable searchDrawable;

    public MantoSearchEditText(Context context) {
        this(context, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleFocusChangeLogic() {
        if (!this.hasFocused) {
            setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], null, getCompoundDrawables()[3]);
        } else if (!TextUtils.isEmpty(getText())) {
            setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], this.clearDrawable, getCompoundDrawables()[3]);
        } else {
            setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], null, getCompoundDrawables()[3]);
        }
    }

    private void init() {
        this.searchDrawable = ContextCompat.getDrawable(getContext(), R.drawable.map_widget_edittext_search);
        this.clearDrawable = ContextCompat.getDrawable(getContext(), R.drawable.map_widget_edittext_clear);
        setPadding(getPaddingLeft() + MantoDensityUtils.dip2pixel(getContext(), 11), getPaddingTop(), getPaddingRight() + MantoDensityUtils.dip2pixel(getContext(), 11), getPaddingBottom());
        Drawable drawable = this.searchDrawable;
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), this.searchDrawable.getIntrinsicHeight());
        Drawable drawable2 = this.clearDrawable;
        drawable2.setBounds(0, 0, drawable2.getIntrinsicWidth(), this.clearDrawable.getIntrinsicHeight());
        setCompoundDrawables(this.searchDrawable, null, null, null);
        setCompoundDrawablePadding(MantoDensityUtils.dip2pixel(getContext(), 6));
        addTextChangedListener(new TextWatcher() { // from class: com.jd.manto.lbs.MantoSearchEditText.1
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                MantoSearchEditText.this.handleFocusChangeLogic();
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
            }
        });
        setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.jd.manto.lbs.MantoSearchEditText.2
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view, boolean z) {
                MantoSearchEditText.this.hasFocused = z;
                MantoSearchEditText.this.handleFocusChangeLogic();
            }
        });
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.hasFocused) {
            if (motionEvent.getAction() == 1) {
                Rect bounds = this.clearDrawable.getBounds();
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

    public MantoSearchEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }
}
