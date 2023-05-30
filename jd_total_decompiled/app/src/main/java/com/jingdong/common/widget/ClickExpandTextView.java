package com.jingdong.common.widget;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import com.jingdong.corelib.utils.Log;
import com.jingdong.sdk.utils.DPIUtil;
import kotlin.text.Typography;

/* loaded from: classes12.dex */
public class ClickExpandTextView extends AppCompatTextView {
    private static final String TAG = ClickExpandTextView.class.getSimpleName();
    private String fixedText;
    private boolean isClickableSpanUnderline;
    private boolean isTextSpanUnderline;
    private ClickExpandCallback mClickExpandCallback;
    private ClickableSpan mClickableSpan;
    private int mClickableSpanColor;
    private String mClickableSpanText;
    private int mClickableSpanTextSize;
    private String mText;
    private TextPaint mTextPaint;
    private ClickableSpan mTextSpan;
    private int mTextSpanColor;
    private int mTextSpanTextSize;
    private int maxTextLineCount;
    private int rowWidth;
    private SpannableString spannableString;

    /* loaded from: classes12.dex */
    public interface ClickExpandCallback {
        void handleClickableSpanClick();

        void handleTextSpanClick();
    }

    public ClickExpandTextView(Context context) {
        this(context, null);
    }

    private ClickableSpan getClickableSpan() {
        if (this.mClickableSpan == null) {
            this.mClickableSpan = new ClickableSpan() { // from class: com.jingdong.common.widget.ClickExpandTextView.2
                @Override // android.text.style.ClickableSpan
                public void onClick(View view) {
                    if (ClickExpandTextView.this.mClickExpandCallback != null) {
                        ClickExpandTextView.this.mClickExpandCallback.handleClickableSpanClick();
                    }
                    ClickExpandTextView.this.setTextExpand();
                    if (Log.D) {
                        Log.i(ClickExpandTextView.TAG, "ClickableSpan is clicked");
                    }
                }

                @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
                public void updateDrawState(TextPaint textPaint) {
                    textPaint.setTextSize(ClickExpandTextView.this.mClickableSpanTextSize);
                    textPaint.setColor(ClickExpandTextView.this.mClickableSpanColor);
                    textPaint.setUnderlineText(ClickExpandTextView.this.isClickableSpanUnderline);
                }
            };
        }
        return this.mClickableSpan;
    }

    private ClickableSpan getTextSpan() {
        if (this.mTextSpan == null) {
            this.mTextSpan = new ClickableSpan() { // from class: com.jingdong.common.widget.ClickExpandTextView.1
                @Override // android.text.style.ClickableSpan
                public void onClick(View view) {
                    if (ClickExpandTextView.this.mClickExpandCallback != null) {
                        ClickExpandTextView.this.mClickExpandCallback.handleTextSpanClick();
                    }
                    if (Log.D) {
                        Log.i(ClickExpandTextView.TAG, "TextSpan is clicked");
                    }
                }

                @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
                public void updateDrawState(TextPaint textPaint) {
                    textPaint.setTextSize(ClickExpandTextView.this.mTextSpanTextSize);
                    textPaint.setColor(ClickExpandTextView.this.mTextSpanColor);
                    textPaint.setUnderlineText(ClickExpandTextView.this.isTextSpanUnderline);
                }
            };
        }
        return this.mTextSpan;
    }

    private void init() {
        this.fixedText = new String(new char[]{Typography.ellipsis}) + this.mClickableSpanText;
        this.rowWidth = DPIUtil.getWidth(getContext()) - DPIUtil.dip2px(getContext(), 50.0f);
        this.mTextPaint = getPaint();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTextExpand() {
        SpannableString spannableString = new SpannableString(this.mText);
        this.spannableString = spannableString;
        spannableString.setSpan(getTextSpan(), 0, this.mText.length(), 33);
        super.setText(this.spannableString);
    }

    public void setClickExpandCallback(ClickExpandCallback clickExpandCallback) {
        this.mClickExpandCallback = clickExpandCallback;
    }

    public void setClickableSpanColor(@ColorInt int i2) {
        this.mClickableSpanColor = i2;
    }

    public void setClickableSpanStyle(@ColorInt int i2, boolean z) {
        setClickableSpanColor(i2);
        setClickableSpanUnderline(z);
    }

    public void setClickableSpanText(String str) {
        this.mClickableSpanText = str;
    }

    public void setClickableSpanTextSize(int i2) {
        this.mClickableSpanTextSize = i2;
    }

    public void setClickableSpanUnderline(boolean z) {
        this.isClickableSpanUnderline = z;
    }

    public void setMaxTextLineCount(int i2) {
        this.maxTextLineCount = i2;
    }

    public void setRowWidth(int i2) {
        this.rowWidth = i2;
    }

    public void setTextAutoExpand(String str, boolean z) {
        int breakText;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.mText = str;
        if (z) {
            setTextExpand();
            return;
        }
        if (this.mTextPaint.measureText(str) > this.rowWidth * this.maxTextLineCount) {
            int i2 = 0;
            int i3 = 0;
            while (true) {
                int i4 = this.maxTextLineCount;
                if (i2 >= i4) {
                    break;
                }
                if (i2 == i4 - 1) {
                    breakText = this.mTextPaint.breakText(str.substring(i3), true, this.rowWidth - this.mTextPaint.measureText(this.fixedText), null);
                } else {
                    breakText = this.mTextPaint.breakText(str.substring(i3), true, this.rowWidth, null);
                }
                i3 += breakText;
                i2++;
            }
            String str2 = str.substring(0, i3) + this.fixedText;
            SpannableString valueOf = SpannableString.valueOf(str2);
            this.spannableString = valueOf;
            valueOf.setSpan(getTextSpan(), 0, str2.length() - this.mClickableSpanText.length(), 33);
            this.spannableString.setSpan(getClickableSpan(), str2.length() - this.mClickableSpanText.length(), str2.length(), 33);
            if (Log.D) {
                Log.i(TAG, "rectifyText: text = " + str + ", textStr = " + str2);
            }
        }
        setMovementMethod(LinkMovementMethod.getInstance());
        setHintTextColor(0);
        super.setText(this.spannableString);
    }

    public void setTextSpanColor(int i2) {
        this.mTextSpanColor = i2;
    }

    public void setTextSpanTextSize(int i2) {
        this.mTextSpanTextSize = i2;
    }

    public void setTextSpanUnderline(boolean z) {
        this.isTextSpanUnderline = z;
    }

    public ClickExpandTextView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mText = "";
        this.maxTextLineCount = 4;
        this.mClickableSpanText = "\u5168\u6587";
        this.mClickableSpanTextSize = DPIUtil.dip2px(getContext(), 14.0f);
        this.mClickableSpanColor = Color.parseColor("#FB2020");
        this.isClickableSpanUnderline = false;
        this.mTextSpanTextSize = DPIUtil.dip2px(getContext(), 14.0f);
        this.mTextSpanColor = Color.parseColor("#252525");
        this.isTextSpanUnderline = false;
        init();
    }

    public void setClickableSpanStyle(String str, @ColorInt int i2, boolean z) {
        setClickableSpanText(str);
        setClickableSpanStyle(i2, z);
    }

    public ClickExpandTextView(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mText = "";
        this.maxTextLineCount = 4;
        this.mClickableSpanText = "\u5168\u6587";
        this.mClickableSpanTextSize = DPIUtil.dip2px(getContext(), 14.0f);
        this.mClickableSpanColor = Color.parseColor("#FB2020");
        this.isClickableSpanUnderline = false;
        this.mTextSpanTextSize = DPIUtil.dip2px(getContext(), 14.0f);
        this.mTextSpanColor = Color.parseColor("#252525");
        this.isTextSpanUnderline = false;
        init();
    }
}
