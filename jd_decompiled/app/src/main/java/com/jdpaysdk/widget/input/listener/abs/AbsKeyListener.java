package com.jdpaysdk.widget.input.listener.abs;

import android.text.Editable;
import android.text.InputFilter;
import android.text.Selection;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.BaseKeyListener;
import android.view.KeyEvent;
import android.view.View;
import androidx.annotation.NonNull;
import com.jdpaysdk.widget.input.abs.AbsEditText;
import com.jdpaysdk.widget.util.SpannableStringUtil;

/* loaded from: classes18.dex */
public abstract class AbsKeyListener extends BaseKeyListener implements InputFilter {
    @NonNull
    protected final AbsEditText editText;

    public AbsKeyListener(@NonNull AbsEditText absEditText) {
        this.editText = absEditText;
    }

    private int getUnicodeChar(CharSequence charSequence, @NonNull KeyEvent keyEvent) {
        int metaState = keyEvent.getMetaState();
        if (keyEvent.getKeyCharacterMap().getModifierBehavior() == 1) {
            metaState |= BaseKeyListener.getMetaState(charSequence);
        }
        return keyEvent.getUnicodeChar(metaState);
    }

    private void onError(int i2, CharSequence charSequence) {
        if (!this.editText.isFocused() || this.editText.onInputError(i2, charSequence)) {
            return;
        }
        onInputError(i2, charSequence);
    }

    public abstract boolean accept(int i2, int i3, int i4, @NonNull SpannableStringBuilder spannableStringBuilder, @NonNull SpannableStringBuilder spannableStringBuilder2, int i5);

    @Override // android.text.InputFilter
    public final CharSequence filter(CharSequence charSequence, int i2, int i3, Spanned spanned, int i4, int i5) {
        int i6 = i4;
        int i7 = 0;
        SpannableStringBuilder createBuilder = SpannableStringUtil.createBuilder(spanned, 0, i6);
        SpannableStringBuilder createBuilder2 = SpannableStringUtil.createBuilder(spanned, i5, spanned.length());
        int i8 = 0;
        int i9 = 0;
        while (i8 < i6) {
            i9 = Character.codePointAt(spanned, i8);
            i8 += Character.charCount(i9);
        }
        SpannableStringBuilder createBuilder3 = SpannableStringUtil.createBuilder(charSequence, i2, i3);
        int length = spanned.length() - (i5 - i6);
        int i10 = i9;
        while (i7 < createBuilder3.length()) {
            int codePointAt = Character.codePointAt(createBuilder3, i7);
            int charCount = Character.charCount(codePointAt);
            if (accept(i6, codePointAt, i10, createBuilder, createBuilder2, length)) {
                int i11 = charCount + i7;
                createBuilder.append((CharSequence) createBuilder3, i7, i11);
                i6++;
                length++;
                i10 = codePointAt;
                i7 = i11;
            } else {
                int i12 = charCount + i7;
                onError(i6, createBuilder3.subSequence(i7, i12));
                createBuilder3.delete(i7, i12);
            }
        }
        return createBuilder3;
    }

    public abstract int getInputType();

    public abstract void onInputError(int i2, CharSequence charSequence);

    @Override // android.text.method.BaseKeyListener, android.text.method.MetaKeyKeyListener, android.text.method.KeyListener
    public boolean onKeyDown(View view, Editable editable, int i2, KeyEvent keyEvent) {
        int i3;
        int selectionStart = Selection.getSelectionStart(editable);
        int selectionEnd = Selection.getSelectionEnd(editable);
        int min = Math.min(selectionStart, selectionEnd);
        int max = Math.max(selectionStart, selectionEnd);
        int i4 = 0;
        if (min < 0) {
            Selection.setSelection(editable, 0);
            max = 0;
            min = 0;
        }
        if (keyEvent != null) {
            i3 = getUnicodeChar(editable, keyEvent);
            i4 = keyEvent.getRepeatCount();
        } else {
            i3 = 0;
        }
        if (i4 == 0) {
            if (i3 != 0) {
                if (min != max) {
                    Selection.setSelection(editable, max);
                }
                editable.replace(min, max, String.valueOf((char) i3));
                BaseKeyListener.adjustMetaAfterKeypress(editable);
                return true;
            }
        } else if (i3 == 48 && i4 == 1 && min == max && max > 0) {
            int i5 = min - 1;
            if (editable.charAt(i5) == '0') {
                editable.replace(i5, max, String.valueOf('+'));
                BaseKeyListener.adjustMetaAfterKeypress(editable);
                return true;
            }
        }
        BaseKeyListener.adjustMetaAfterKeypress(editable);
        return super.onKeyDown(view, editable, i2, keyEvent);
    }
}
