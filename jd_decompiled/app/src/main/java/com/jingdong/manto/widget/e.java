package com.jingdong.manto.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Spannable;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.widget.TextView;
import com.jingdong.manto.utils.MantoLog;

@SuppressLint({"AppCompatCustomView"})
/* loaded from: classes16.dex */
public class e extends TextView {
    public com.jingdong.manto.widget.l.a a;

    /* loaded from: classes16.dex */
    class a extends Spannable.Factory {
        a() {
        }

        @Override // android.text.Spannable.Factory
        public final Spannable newSpannable(CharSequence charSequence) {
            Spannable newSpannable = super.newSpannable(charSequence);
            if (e.this.a != null && !TextUtils.isEmpty(newSpannable)) {
                newSpannable.setSpan(e.this.a, 0, newSpannable.length(), 18);
            }
            return newSpannable;
        }
    }

    public e(Context context) {
        super(context);
        super.setIncludeFontPadding(false);
        super.setLineSpacing(0.0f, 1.0f);
        super.setSpannableFactory(new a());
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        MantoLog.d("coverView", "TextView: " + motionEvent.getActionMasked());
        return super.onTouchEvent(motionEvent);
    }

    public void setLineH(int i2) {
        if (this.a == null) {
            this.a = new com.jingdong.manto.widget.l.a(i2);
        }
        if (this.a.a(i2)) {
            invalidate();
        }
    }

    @Override // android.widget.TextView
    public void setText(CharSequence charSequence, TextView.BufferType bufferType) {
        if (bufferType == TextView.BufferType.NORMAL) {
            bufferType = TextView.BufferType.SPANNABLE;
        }
        super.setText(charSequence, bufferType);
    }
}
