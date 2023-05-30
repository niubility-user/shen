package com.jingdong.common.utils;

import android.graphics.Typeface;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.widget.EditText;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.utils.FontsUtil;

/* loaded from: classes6.dex */
public class JDCardInputTextWatcher implements TextWatcher {
    int beforeTextLength;
    private StringBuffer buffer;
    EditText editText;
    boolean isChanged;
    boolean isSettlement;
    int location;
    int onTextLength;
    int prelocation;
    int spaceNumber;
    String spaceText;
    OnTextChangeListener textChangeListener;

    /* loaded from: classes6.dex */
    public interface OnTextChangeListener {
        void onTextChange();
    }

    public JDCardInputTextWatcher(EditText editText, OnTextChangeListener onTextChangeListener) {
        this.beforeTextLength = 0;
        this.onTextLength = 0;
        this.isChanged = false;
        this.isSettlement = false;
        this.location = 0;
        this.prelocation = 0;
        this.buffer = new StringBuffer();
        this.spaceNumber = 0;
        this.spaceText = "   ";
        this.editText = editText;
        this.textChangeListener = onTextChangeListener;
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
        if (this.isChanged) {
            this.location = this.editText.getSelectionEnd();
            int i2 = 0;
            while (i2 < this.buffer.length()) {
                if (this.buffer.charAt(i2) == ' ') {
                    this.buffer.deleteCharAt(i2);
                } else {
                    i2++;
                }
            }
            int i3 = 0;
            for (int i4 = 0; i4 < this.buffer.length(); i4++) {
                if (i4 == 4 || i4 == 11 || i4 == 18) {
                    this.buffer.insert(i4, this.spaceText);
                    i3++;
                }
            }
            int i5 = this.prelocation;
            if (i5 > 0 && i5 > this.location && (i5 == 7 || i5 == 14 || i5 == 21)) {
                this.location = i5 - 3;
            }
            int i6 = i3 * 3;
            int i7 = this.spaceNumber;
            if (i6 > i7) {
                this.location += i6 - i7;
            }
            String stringBuffer = this.buffer.toString();
            if (stringBuffer != null) {
                String upperCase = stringBuffer.toUpperCase();
                if (upperCase.length() > 25) {
                    upperCase = upperCase.substring(0, 25);
                }
                if (this.location <= upperCase.length() && this.prelocation != 0) {
                    if (this.location < 0) {
                        this.location = 0;
                    }
                } else {
                    this.location = upperCase.length();
                }
                this.prelocation = this.location;
                this.editText.removeTextChangedListener(this);
                this.editText.setText(upperCase);
                Selection.setSelection(this.editText.getText(), this.location);
                this.editText.addTextChangedListener(this);
                this.isChanged = false;
            }
        }
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        if (this.buffer.length() > 0) {
            StringBuffer stringBuffer = this.buffer;
            stringBuffer.delete(0, stringBuffer.length());
        }
        this.spaceNumber = 0;
        for (int i5 = 0; i5 < charSequence.length(); i5++) {
            if (charSequence.charAt(i5) == ' ') {
                this.spaceNumber++;
            }
        }
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        EditText editText;
        int length = charSequence.length();
        this.onTextLength = length;
        if (this.isSettlement && (editText = this.editText) != null) {
            try {
                if (length == 0) {
                    editText.setTypeface(Typeface.defaultFromStyle(0));
                } else {
                    FontsUtil.changeTextFont(editText, 4099);
                }
            } catch (Exception e2) {
                if (Log.E) {
                    e2.printStackTrace();
                }
            }
        }
        this.buffer.append(charSequence.toString().replaceAll("-", ""));
        if (this.onTextLength != this.beforeTextLength && !this.isChanged) {
            this.isChanged = true;
            OnTextChangeListener onTextChangeListener = this.textChangeListener;
            if (onTextChangeListener != null) {
                onTextChangeListener.onTextChange();
                return;
            }
            return;
        }
        this.isChanged = false;
    }

    public JDCardInputTextWatcher(EditText editText, OnTextChangeListener onTextChangeListener, boolean z) {
        this.beforeTextLength = 0;
        this.onTextLength = 0;
        this.isChanged = false;
        this.isSettlement = false;
        this.location = 0;
        this.prelocation = 0;
        this.buffer = new StringBuffer();
        this.spaceNumber = 0;
        this.spaceText = "   ";
        this.editText = editText;
        this.textChangeListener = onTextChangeListener;
        this.isSettlement = z;
    }
}
