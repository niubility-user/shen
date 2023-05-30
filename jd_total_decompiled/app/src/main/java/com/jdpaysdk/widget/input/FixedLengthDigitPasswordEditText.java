package com.jdpaysdk.widget.input;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.InputFilter;
import android.text.SpannableStringBuilder;
import android.text.method.KeyListener;
import android.text.style.ReplacementSpan;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jdpaysdk.widget.input.abs.AbsEditText;
import com.jdpaysdk.widget.input.fiilter.abs.AbsInputFilter;
import com.jdpaysdk.widget.input.fiilter.abs.AbsMaxInputFilter;
import com.jdpaysdk.widget.input.listener.NumKeyListener;
import com.jdpaysdk.widget.input.listener.abs.AbsKeyListener;
import com.jdpaysdk.widget.input.method.PasswordTransformationMethod;
import com.tencent.wcdb.database.SQLiteDatabase;

/* loaded from: classes18.dex */
public class FixedLengthDigitPasswordEditText extends AbsEditText {
    private boolean canFitTextSize;
    @NonNull
    private final PasswordStyle defaultPasswordStyle;
    private final int fakeHeightSize;
    private final TextView fakeTextView;
    private final int fakeWidthSize;
    private boolean needFitTextSize;
    private boolean needReSize;
    private boolean onlyAcceptNumber;
    private float originTextSize;
    private int passwordLength;
    private PasswordStyle passwordStyle;

    /* loaded from: classes18.dex */
    public static class DefaultPasswordStyle implements PasswordStyle {
        private final int halfStrokeWidth;
        private final Paint linePaint;
        private final int strokeWidth;

        public DefaultPasswordStyle() {
            this(1, Color.parseColor("#EBEBEB"));
        }

        @Override // com.jdpaysdk.widget.input.FixedLengthDigitPasswordEditText.PasswordStyle
        public void drawBackground(Canvas canvas, int i2, int i3, int i4) {
            int i5 = this.halfStrokeWidth;
            canvas.drawRect(i5, i5, i2 - i5, i3 - i5, this.linePaint);
            int passwordLetterWidth = getPasswordLetterWidth(i2, i4);
            int i6 = this.halfStrokeWidth + passwordLetterWidth;
            int i7 = i3 - this.strokeWidth;
            int i8 = i4 - 1;
            for (int i9 = 0; i9 < i8; i9++) {
                float f2 = i6;
                canvas.drawLine(f2, this.strokeWidth, f2, i7, this.linePaint);
                i6 += passwordLetterWidth;
            }
        }

        @Override // com.jdpaysdk.widget.input.FixedLengthDigitPasswordEditText.PasswordStyle
        public void drawPassword(Canvas canvas, float f2, float f3, int i2, Paint paint) {
            canvas.drawCircle(f2, f3, i2 >> 4, paint);
        }

        @Override // com.jdpaysdk.widget.input.FixedLengthDigitPasswordEditText.PasswordStyle
        public int getPasswordLetterWidth(int i2, int i3) {
            int i4 = i2 - this.strokeWidth;
            return i3 != 0 ? i4 / i3 : i4;
        }

        public DefaultPasswordStyle(int i2, @ColorInt int i3) {
            Paint paint = new Paint();
            this.linePaint = paint;
            this.halfStrokeWidth = i2;
            int i4 = i2 << 1;
            this.strokeWidth = i4;
            paint.setStrokeWidth(i4);
            paint.setColor(i3);
            paint.setStyle(Paint.Style.STROKE);
        }
    }

    /* loaded from: classes18.dex */
    private static class PasswordSpan extends ReplacementSpan {
        @NonNull
        private final Callback callback;
        private int letterWidth;

        /* loaded from: classes18.dex */
        public interface Callback {
            void draw(Canvas canvas, float f2, float f3, int i2, Paint paint);

            int getLetterWidth();
        }

        public PasswordSpan(@NonNull Callback callback) {
            this.callback = callback;
        }

        @Override // android.text.style.ReplacementSpan
        public void draw(@NonNull Canvas canvas, CharSequence charSequence, int i2, int i3, float f2, int i4, int i5, int i6, @NonNull Paint paint) {
            float f3 = (i6 - i4) >> 1;
            int i7 = i3 - i2;
            float f4 = f2 + (this.letterWidth >> 1);
            for (int i8 = 0; i8 < i7; i8++) {
                this.callback.draw(canvas, f4, f3, this.letterWidth, paint);
                f4 += this.letterWidth;
            }
        }

        @Override // android.text.style.ReplacementSpan
        public int getSize(@NonNull Paint paint, CharSequence charSequence, int i2, int i3, @Nullable Paint.FontMetricsInt fontMetricsInt) {
            int letterWidth = this.callback.getLetterWidth();
            this.letterWidth = letterWidth;
            return letterWidth * (i3 - i2);
        }
    }

    /* loaded from: classes18.dex */
    private class PasswordSpanCallback implements PasswordSpan.Callback {
        private PasswordSpanCallback() {
        }

        @Override // com.jdpaysdk.widget.input.FixedLengthDigitPasswordEditText.PasswordSpan.Callback
        public void draw(Canvas canvas, float f2, float f3, int i2, Paint paint) {
            if (FixedLengthDigitPasswordEditText.this.passwordStyle != null) {
                FixedLengthDigitPasswordEditText.this.passwordStyle.drawPassword(canvas, f2, f3, i2, paint);
            } else {
                FixedLengthDigitPasswordEditText.this.defaultPasswordStyle.drawPassword(canvas, f2, f3, i2, paint);
            }
        }

        @Override // com.jdpaysdk.widget.input.FixedLengthDigitPasswordEditText.PasswordSpan.Callback
        public int getLetterWidth() {
            return FixedLengthDigitPasswordEditText.this.passwordStyle != null ? FixedLengthDigitPasswordEditText.this.passwordStyle.getPasswordLetterWidth(FixedLengthDigitPasswordEditText.this.getWidth(), FixedLengthDigitPasswordEditText.this.passwordLength) : FixedLengthDigitPasswordEditText.this.defaultPasswordStyle.getPasswordLetterWidth(FixedLengthDigitPasswordEditText.this.getWidth(), FixedLengthDigitPasswordEditText.this.passwordLength);
        }
    }

    /* loaded from: classes18.dex */
    public interface PasswordStyle {
        void drawBackground(Canvas canvas, int i2, int i3, int i4);

        void drawPassword(Canvas canvas, float f2, float f3, int i2, Paint paint);

        int getPasswordLetterWidth(int i2, int i3);
    }

    /* loaded from: classes18.dex */
    private static class PayPasswordInputFilter extends AbsInputFilter {
        @NonNull
        private final PasswordSpan.Callback passwordSpanCallback;

        public PayPasswordInputFilter(@NonNull PasswordSpan.Callback callback) {
            this.passwordSpanCallback = callback;
        }

        @Override // com.jdpaysdk.widget.input.fiilter.abs.AbsInputFilter
        protected CharSequence realFilter(@NonNull SpannableStringBuilder spannableStringBuilder, @NonNull SpannableStringBuilder spannableStringBuilder2, int i2, int i3) {
            int i4 = 0;
            for (PasswordSpan passwordSpan : (PasswordSpan[]) spannableStringBuilder.getSpans(0, spannableStringBuilder.length(), PasswordSpan.class)) {
                spannableStringBuilder.removeSpan(passwordSpan);
            }
            int length = spannableStringBuilder.length();
            while (i4 < length) {
                int i5 = i4 + 1;
                spannableStringBuilder.setSpan(new PasswordSpan(this.passwordSpanCallback), i4, i5, 33);
                i4 = i5;
            }
            return spannableStringBuilder;
        }
    }

    public FixedLengthDigitPasswordEditText(@NonNull Context context) {
        super(context);
        this.defaultPasswordStyle = new DefaultPasswordStyle();
        TextView textView = new TextView(getContext());
        this.fakeTextView = textView;
        this.fakeWidthSize = View.MeasureSpec.makeMeasureSpec(0, 1073741824);
        this.fakeHeightSize = View.MeasureSpec.makeMeasureSpec(SQLiteDatabase.ENABLE_WRITE_AHEAD_LOGGING, Integer.MIN_VALUE);
        this.needReSize = true;
        this.passwordLength = 6;
        this.needFitTextSize = true;
        this.onlyAcceptNumber = true;
        textView.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        textView.setIncludeFontPadding(false);
        textView.setPadding(0, 0, 0, 0);
        setGravity(8388627);
        setIncludeFontPadding(false);
        setPadding(0, 0, 0, 0);
        setBackground(null);
        KeyListener keyListener = new AbsKeyListener(this) { // from class: com.jdpaysdk.widget.input.FixedLengthDigitPasswordEditText.1
            private final NumKeyListener numKeyListener;

            {
                this.numKeyListener = new NumKeyListener(FixedLengthDigitPasswordEditText.this);
            }

            @Override // com.jdpaysdk.widget.input.listener.abs.AbsKeyListener
            public boolean accept(int i2, int i3, int i4, @NonNull SpannableStringBuilder spannableStringBuilder, @NonNull SpannableStringBuilder spannableStringBuilder2, int i5) {
                if (FixedLengthDigitPasswordEditText.this.onlyAcceptNumber) {
                    return this.numKeyListener.accept(i2, i3, i4, spannableStringBuilder, spannableStringBuilder2, i5);
                }
                return true;
            }

            @Override // com.jdpaysdk.widget.input.listener.abs.AbsKeyListener, android.text.method.KeyListener
            public int getInputType() {
                return 2;
            }

            @Override // com.jdpaysdk.widget.input.listener.abs.AbsKeyListener
            public void onInputError(int i2, CharSequence charSequence) {
            }
        };
        setKeyListener(keyListener);
        InputFilter[] filters = getFilters();
        int length = filters.length + 3;
        InputFilter[] inputFilterArr = new InputFilter[length];
        System.arraycopy(filters, 0, inputFilterArr, 2, filters.length);
        inputFilterArr[0] = keyListener;
        inputFilterArr[1] = new AbsMaxInputFilter() { // from class: com.jdpaysdk.widget.input.FixedLengthDigitPasswordEditText.2
            @Override // com.jdpaysdk.widget.input.fiilter.abs.AbsMaxInputFilter
            protected int getMaxLength() {
                return FixedLengthDigitPasswordEditText.this.passwordLength;
            }
        };
        inputFilterArr[length - 1] = new PayPasswordInputFilter(new PasswordSpanCallback());
        setFilters(inputFilterArr);
        setTransformationMethod(new PasswordTransformationMethod(this, null));
    }

    private int findLargestTextSize(int i2) {
        int i3 = i2 + 1;
        do {
            i3--;
            this.fakeTextView.setTextSize(0, i3);
            try {
                this.fakeTextView.measure(this.fakeWidthSize, this.fakeHeightSize);
            } catch (Exception e2) {
                e2.printStackTrace();
                if (i3 <= i2) {
                    i2 = i3;
                }
                return i2;
            }
        } while (this.fakeTextView.getMeasuredHeight() > i2);
        return i3;
    }

    private void fitSize(int i2) {
        this.originTextSize = getTextSize();
        if (this.needFitTextSize) {
            super.setTextSize(0, findLargestTextSize(i2));
        }
        this.canFitTextSize = true;
    }

    private void onSelfMeasure(int i2, int i3) {
        int passwordLetterWidth;
        int mode = View.MeasureSpec.getMode(i2);
        int mode2 = View.MeasureSpec.getMode(i3);
        int size = View.MeasureSpec.getSize(i2);
        int size2 = View.MeasureSpec.getSize(i3);
        if (mode == 1073741824) {
            if (mode2 == 1073741824) {
                superMeasure(i2, i3);
                fitSize(size2);
            } else {
                int i4 = this.passwordLength;
                if (i4 != 0) {
                    int i5 = size / i4;
                    exactlyMeasure(size, i5);
                    fitSize(i5);
                } else {
                    superMeasure(i2, i3);
                    fitSize(getMeasuredHeight());
                }
            }
        } else if (mode2 == 1073741824) {
            exactlyMeasure(Math.min(this.passwordLength * size2, size), size2);
            fitSize(size2);
        } else {
            superMeasure(i2, i3);
            int measuredHeight = getMeasuredHeight();
            exactlyMeasure(Math.min(this.passwordLength * measuredHeight, size), measuredHeight);
        }
        int measuredWidth = getMeasuredWidth();
        PasswordStyle passwordStyle = this.passwordStyle;
        if (passwordStyle != null) {
            passwordLetterWidth = passwordStyle.getPasswordLetterWidth(measuredWidth, this.passwordLength);
        } else {
            passwordLetterWidth = this.defaultPasswordStyle.getPasswordLetterWidth(measuredWidth, this.passwordLength);
        }
        int i6 = passwordLetterWidth != 0 ? (measuredWidth - (passwordLetterWidth * this.passwordLength)) >> 1 : 0;
        setPadding(i6, getPaddingTop(), i6, getPaddingBottom());
    }

    private void superMeasure(int i2, int i3) {
        this.needReSize = false;
        measure(i2, i3);
        setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight());
    }

    public final void exactlyMeasure(int i2, int i3) {
        superMeasure(View.MeasureSpec.makeMeasureSpec(i2, 1073741824), View.MeasureSpec.makeMeasureSpec(i3, 1073741824));
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        PasswordStyle passwordStyle = this.passwordStyle;
        if (passwordStyle != null) {
            passwordStyle.drawBackground(canvas, getWidth(), getHeight(), this.passwordLength);
        } else {
            this.defaultPasswordStyle.drawBackground(canvas, getWidth(), getHeight(), this.passwordLength);
        }
        super.onDraw(canvas);
    }

    @Override // android.widget.TextView, android.view.View
    protected void onMeasure(int i2, int i3) {
        if (this.needReSize) {
            onSelfMeasure(i2, i3);
            return;
        }
        super.onMeasure(i2, i3);
        this.needReSize = true;
    }

    public void setNeedFitTextSize(boolean z) {
        this.needFitTextSize = z;
        if (z) {
            if (this.canFitTextSize) {
                fitSize(getMaxHeight());
                return;
            }
            return;
        }
        float f2 = this.originTextSize;
        if (f2 != 0.0f) {
            super.setTextSize(f2);
        }
    }

    public void setOnlyAcceptNumber(boolean z) {
        this.onlyAcceptNumber = z;
    }

    public void setPasswordLength(int i2) {
        this.passwordLength = i2;
    }

    public void setPasswordStyle(PasswordStyle passwordStyle) {
        this.passwordStyle = passwordStyle;
    }

    @Override // android.widget.TextView
    public void setTextSize(int i2, float f2) {
        super.setTextSize(i2, f2);
        this.originTextSize = getTextSize();
    }

    public FixedLengthDigitPasswordEditText(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.defaultPasswordStyle = new DefaultPasswordStyle();
        TextView textView = new TextView(getContext());
        this.fakeTextView = textView;
        this.fakeWidthSize = View.MeasureSpec.makeMeasureSpec(0, 1073741824);
        this.fakeHeightSize = View.MeasureSpec.makeMeasureSpec(SQLiteDatabase.ENABLE_WRITE_AHEAD_LOGGING, Integer.MIN_VALUE);
        this.needReSize = true;
        this.passwordLength = 6;
        this.needFitTextSize = true;
        this.onlyAcceptNumber = true;
        textView.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        textView.setIncludeFontPadding(false);
        textView.setPadding(0, 0, 0, 0);
        setGravity(8388627);
        setIncludeFontPadding(false);
        setPadding(0, 0, 0, 0);
        setBackground(null);
        KeyListener keyListener = new AbsKeyListener(this) { // from class: com.jdpaysdk.widget.input.FixedLengthDigitPasswordEditText.1
            private final NumKeyListener numKeyListener;

            {
                this.numKeyListener = new NumKeyListener(FixedLengthDigitPasswordEditText.this);
            }

            @Override // com.jdpaysdk.widget.input.listener.abs.AbsKeyListener
            public boolean accept(int i2, int i3, int i4, @NonNull SpannableStringBuilder spannableStringBuilder, @NonNull SpannableStringBuilder spannableStringBuilder2, int i5) {
                if (FixedLengthDigitPasswordEditText.this.onlyAcceptNumber) {
                    return this.numKeyListener.accept(i2, i3, i4, spannableStringBuilder, spannableStringBuilder2, i5);
                }
                return true;
            }

            @Override // com.jdpaysdk.widget.input.listener.abs.AbsKeyListener, android.text.method.KeyListener
            public int getInputType() {
                return 2;
            }

            @Override // com.jdpaysdk.widget.input.listener.abs.AbsKeyListener
            public void onInputError(int i2, CharSequence charSequence) {
            }
        };
        setKeyListener(keyListener);
        InputFilter[] filters = getFilters();
        int length = filters.length + 3;
        InputFilter[] inputFilterArr = new InputFilter[length];
        System.arraycopy(filters, 0, inputFilterArr, 2, filters.length);
        inputFilterArr[0] = keyListener;
        inputFilterArr[1] = new AbsMaxInputFilter() { // from class: com.jdpaysdk.widget.input.FixedLengthDigitPasswordEditText.2
            @Override // com.jdpaysdk.widget.input.fiilter.abs.AbsMaxInputFilter
            protected int getMaxLength() {
                return FixedLengthDigitPasswordEditText.this.passwordLength;
            }
        };
        inputFilterArr[length - 1] = new PayPasswordInputFilter(new PasswordSpanCallback());
        setFilters(inputFilterArr);
        setTransformationMethod(new PasswordTransformationMethod(this, null));
    }

    public FixedLengthDigitPasswordEditText(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.defaultPasswordStyle = new DefaultPasswordStyle();
        TextView textView = new TextView(getContext());
        this.fakeTextView = textView;
        this.fakeWidthSize = View.MeasureSpec.makeMeasureSpec(0, 1073741824);
        this.fakeHeightSize = View.MeasureSpec.makeMeasureSpec(SQLiteDatabase.ENABLE_WRITE_AHEAD_LOGGING, Integer.MIN_VALUE);
        this.needReSize = true;
        this.passwordLength = 6;
        this.needFitTextSize = true;
        this.onlyAcceptNumber = true;
        textView.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        textView.setIncludeFontPadding(false);
        textView.setPadding(0, 0, 0, 0);
        setGravity(8388627);
        setIncludeFontPadding(false);
        setPadding(0, 0, 0, 0);
        setBackground(null);
        KeyListener keyListener = new AbsKeyListener(this) { // from class: com.jdpaysdk.widget.input.FixedLengthDigitPasswordEditText.1
            private final NumKeyListener numKeyListener;

            {
                this.numKeyListener = new NumKeyListener(FixedLengthDigitPasswordEditText.this);
            }

            @Override // com.jdpaysdk.widget.input.listener.abs.AbsKeyListener
            public boolean accept(int i22, int i3, int i4, @NonNull SpannableStringBuilder spannableStringBuilder, @NonNull SpannableStringBuilder spannableStringBuilder2, int i5) {
                if (FixedLengthDigitPasswordEditText.this.onlyAcceptNumber) {
                    return this.numKeyListener.accept(i22, i3, i4, spannableStringBuilder, spannableStringBuilder2, i5);
                }
                return true;
            }

            @Override // com.jdpaysdk.widget.input.listener.abs.AbsKeyListener, android.text.method.KeyListener
            public int getInputType() {
                return 2;
            }

            @Override // com.jdpaysdk.widget.input.listener.abs.AbsKeyListener
            public void onInputError(int i22, CharSequence charSequence) {
            }
        };
        setKeyListener(keyListener);
        InputFilter[] filters = getFilters();
        int length = filters.length + 3;
        InputFilter[] inputFilterArr = new InputFilter[length];
        System.arraycopy(filters, 0, inputFilterArr, 2, filters.length);
        inputFilterArr[0] = keyListener;
        inputFilterArr[1] = new AbsMaxInputFilter() { // from class: com.jdpaysdk.widget.input.FixedLengthDigitPasswordEditText.2
            @Override // com.jdpaysdk.widget.input.fiilter.abs.AbsMaxInputFilter
            protected int getMaxLength() {
                return FixedLengthDigitPasswordEditText.this.passwordLength;
            }
        };
        inputFilterArr[length - 1] = new PayPasswordInputFilter(new PasswordSpanCallback());
        setFilters(inputFilterArr);
        setTransformationMethod(new PasswordTransformationMethod(this, null));
    }
}
