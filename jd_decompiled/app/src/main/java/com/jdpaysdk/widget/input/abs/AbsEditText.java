package com.jdpaysdk.widget.input.abs;

import android.content.Context;
import android.text.Editable;
import android.text.Selection;
import android.text.SpannableStringBuilder;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import com.jdpaysdk.widget.util.SpannableStringUtil;

/* loaded from: classes18.dex */
public abstract class AbsEditText extends AppCompatEditText {
    private static boolean SHOW_INPUT_ERROR;
    @Nullable
    private InputErrorListener inputErrorListener;
    @Nullable
    private View.OnFocusChangeListener onFocusChangeListener;

    /* loaded from: classes18.dex */
    public interface InputErrorListener {
        boolean onInputError(int i2, CharSequence charSequence);
    }

    public AbsEditText(@NonNull Context context) {
        super(context);
        super.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.jdpaysdk.widget.input.abs.AbsEditText.1
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view, boolean z) {
                if (!z) {
                    AbsEditText.this.setError(null);
                }
                if (AbsEditText.this.onFocusChangeListener != null) {
                    AbsEditText.this.onFocusChangeListener.onFocusChange(view, z);
                }
            }
        });
    }

    public static void setShowInputError(boolean z) {
        SHOW_INPUT_ERROR = z;
    }

    public boolean onInputError(int i2, CharSequence charSequence) {
        InputErrorListener inputErrorListener = this.inputErrorListener;
        if (inputErrorListener != null) {
            return inputErrorListener.onInputError(i2, charSequence);
        }
        return !SHOW_INPUT_ERROR;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void refreshContent() {
        post(new Runnable() { // from class: com.jdpaysdk.widget.input.abs.AbsEditText.2
            @Override // java.lang.Runnable
            public void run() {
                Editable editableText = AbsEditText.this.getEditableText();
                if (editableText != null) {
                    SpannableStringBuilder createBuilder = SpannableStringUtil.createBuilder(editableText);
                    Selection.removeSelection(editableText);
                    editableText.replace(0, editableText.length(), createBuilder);
                }
            }
        });
    }

    public void setInputErrorListener(@Nullable InputErrorListener inputErrorListener) {
        this.inputErrorListener = inputErrorListener;
    }

    @Override // android.view.View
    public void setOnFocusChangeListener(@Nullable View.OnFocusChangeListener onFocusChangeListener) {
        this.onFocusChangeListener = onFocusChangeListener;
    }

    public AbsEditText(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet, 16842862);
        super.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.jdpaysdk.widget.input.abs.AbsEditText.1
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view, boolean z) {
                if (!z) {
                    AbsEditText.this.setError(null);
                }
                if (AbsEditText.this.onFocusChangeListener != null) {
                    AbsEditText.this.onFocusChangeListener.onFocusChange(view, z);
                }
            }
        });
    }

    public AbsEditText(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        super.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.jdpaysdk.widget.input.abs.AbsEditText.1
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view, boolean z) {
                if (!z) {
                    AbsEditText.this.setError(null);
                }
                if (AbsEditText.this.onFocusChangeListener != null) {
                    AbsEditText.this.onFocusChangeListener.onFocusChange(view, z);
                }
            }
        });
    }
}
