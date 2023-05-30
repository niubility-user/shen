package com.jd.lib.productdetail.core.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;
import com.jd.lib.productdetail.core.utils.PDUtils;

/* loaded from: classes15.dex */
public class LinedEditText extends EditText {
    private static int LENGTH_2 = 2;
    private static final String TAG = "LinedEditText";
    private int cursorPos;
    private String inputAfterText;
    private final Paint linePaint;
    private float mLineHeight;
    private float margin;
    private int paperColor;
    private boolean resetText;

    public LinedEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Paint paint = new Paint();
        this.linePaint = paint;
        paint.setColor(PDUtils.parseColor("#30875003"));
        initEditText();
        this.mLineHeight = PDUtils.dip2px(11.0f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean containsEmoji(String str) {
        int length = str.length();
        for (int i2 = 0; i2 < length; i2++) {
            if (!isEmojiCharacter(str.charAt(i2))) {
                return true;
            }
        }
        return false;
    }

    private float getMarginHight() {
        return this.mLineHeight;
    }

    private void initEditText() {
        addTextChangedListener(new TextWatcher() { // from class: com.jd.lib.productdetail.core.views.LinedEditText.1
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
                if (LinedEditText.this.resetText) {
                    return;
                }
                LinedEditText linedEditText = LinedEditText.this;
                linedEditText.cursorPos = linedEditText.getSelectionEnd();
                LinedEditText.this.inputAfterText = charSequence.toString();
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
                if (!LinedEditText.this.resetText) {
                    if (i4 >= LinedEditText.LENGTH_2) {
                        try {
                            if (LinedEditText.containsEmoji(charSequence.subSequence(LinedEditText.this.cursorPos, LinedEditText.this.cursorPos + i4).toString())) {
                                LinedEditText.this.resetText = true;
                                LinedEditText linedEditText = LinedEditText.this;
                                linedEditText.setText(linedEditText.inputAfterText);
                                Editable text = LinedEditText.this.getText();
                                if (TextUtils.isEmpty(text) || !(text instanceof Spannable)) {
                                    return;
                                }
                                Selection.setSelection(text, text.length());
                                return;
                            }
                            return;
                        } catch (Exception e2) {
                            e2.printStackTrace();
                            return;
                        }
                    }
                    return;
                }
                LinedEditText.this.resetText = false;
            }
        });
    }

    private static boolean isEmojiCharacter(char c2) {
        return c2 == 0 || c2 == '\t' || c2 == '\n' || c2 == '\r' || (c2 >= ' ' && c2 <= '\ud7ff') || ((c2 >= '\ue000' && c2 <= '\ufffd') || (c2 >= 0 && c2 <= '\uffff'));
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(this.paperColor);
        int lineCount = getLineCount();
        int height = getHeight();
        int lineHeight = getLineHeight();
        int i2 = (height / lineHeight) + 1;
        if (lineCount < i2) {
            lineCount = i2;
        }
        int compoundPaddingTop = getCompoundPaddingTop();
        for (int i3 = 0; i3 < lineCount; i3++) {
            compoundPaddingTop += lineHeight;
            this.linePaint.setStrokeWidth(1.0f);
            float f2 = compoundPaddingTop;
            canvas.drawLine(0.0f, f2 - getMarginHight(), getRight(), f2 - getMarginHight(), this.linePaint);
            canvas.save();
        }
        setPadding(((int) this.margin) + 10, 0, 0, 0);
        super.onDraw(canvas);
        canvas.restore();
    }

    @Override // android.widget.TextView
    protected void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        if (i3 != i4) {
            int selectionStart = getSelectionStart();
            setText(charSequence);
            setSelection(selectionStart);
        }
    }

    public void setLineColor(String str) {
        this.linePaint.setColor(PDUtils.parseColor(str));
    }

    public void setMarginHeight(float f2) {
        this.mLineHeight = f2;
    }
}
