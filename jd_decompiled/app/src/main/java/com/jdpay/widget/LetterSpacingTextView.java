package com.jdpay.widget;

import android.content.Context;
import android.text.SpannableString;
import android.text.style.ScaleXSpan;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;

/* loaded from: classes18.dex */
public class LetterSpacingTextView extends AppCompatTextView {
    private float letterSpacing;
    private CharSequence originalText;

    /* loaded from: classes18.dex */
    public static class LetterSpacing {
        public static final float NORMAL = 0.0f;
    }

    public LetterSpacingTextView(Context context) {
        super(context);
        this.letterSpacing = 0.0f;
        this.originalText = "";
    }

    public LetterSpacingTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.letterSpacing = 0.0f;
        this.originalText = "";
    }

    public LetterSpacingTextView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.letterSpacing = 0.0f;
        this.originalText = "";
    }

    private void applyLetterSpacing() {
        if (this.originalText == null) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        int i2 = 0;
        while (i2 < this.originalText.length()) {
            sb.append(this.originalText.charAt(i2));
            i2++;
            if (i2 < this.originalText.length()) {
                sb.append("\u00a0");
            }
        }
        SpannableString spannableString = new SpannableString(sb.toString());
        if (sb.toString().length() > 1) {
            for (int i3 = 1; i3 < sb.toString().length(); i3 += 2) {
                spannableString.setSpan(new ScaleXSpan((this.letterSpacing + 1.0f) / 10.0f), i3, i3 + 1, 33);
            }
        }
        super.setText(spannableString, TextView.BufferType.SPANNABLE);
    }

    @Override // android.widget.TextView
    public float getLetterSpacing() {
        return this.letterSpacing;
    }

    @Override // androidx.appcompat.widget.AppCompatTextView, android.widget.TextView
    public CharSequence getText() {
        return this.originalText;
    }

    @Override // android.widget.TextView
    public void setLetterSpacing(float f2) {
        this.letterSpacing = f2;
        applyLetterSpacing();
    }

    @Override // android.widget.TextView
    public void setText(CharSequence charSequence, TextView.BufferType bufferType) {
        this.originalText = charSequence;
        applyLetterSpacing();
    }
}
