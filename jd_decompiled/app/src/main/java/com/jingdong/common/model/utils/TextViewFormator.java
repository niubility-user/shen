package com.jingdong.common.model.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;
import androidx.annotation.ColorInt;

/* loaded from: classes5.dex */
public class TextViewFormator {

    /* loaded from: classes5.dex */
    public interface OnTextComplete {
        void onComplete(Editable editable);
    }

    public static void format(TextView textView, int[] iArr, OnTextComplete onTextComplete) {
        format(textView, iArr, -16777216, onTextComplete);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void insertSpaceToCharSequence(Editable editable, int[] iArr, @ColorInt int i2) {
        for (SpaceSpan spaceSpan : (SpaceSpan[]) editable.getSpans(0, editable.length(), SpaceSpan.class)) {
            editable.removeSpan(spaceSpan);
        }
        int length = iArr.length;
        for (int i3 = 0; i3 < length; i3++) {
            if (editable.length() > iArr[i3]) {
                editable.setSpan(new SpaceSpan(i2), iArr[i3], iArr[i3] + 1, 17);
            }
        }
    }

    public static void format(TextView textView, final int[] iArr, @ColorInt final int i2, final OnTextComplete onTextComplete) {
        textView.addTextChangedListener(new TextWatcher() { // from class: com.jingdong.common.model.utils.TextViewFormator.1
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                int[] iArr2 = iArr;
                if (iArr2 != null) {
                    TextViewFormator.insertSpaceToCharSequence(editable, iArr2, i2);
                }
                OnTextComplete onTextComplete2 = onTextComplete;
                if (onTextComplete2 != null) {
                    onTextComplete2.onComplete(editable);
                }
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i3, int i4, int i5) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i3, int i4, int i5) {
            }
        });
        textView.setText(textView.getText());
    }
}
