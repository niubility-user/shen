package com.jdpaysdk.widget.input.method;

import android.graphics.Rect;
import android.text.Editable;
import android.text.GetChars;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.TransformationMethod;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jdpaysdk.widget.util.ArrayUtils;

/* loaded from: classes18.dex */
public class PasswordTransformationMethod implements TransformationMethod {
    @NonNull
    private final PasswordSpannedString passwordSpannedString;

    /* loaded from: classes18.dex */
    public interface ModeSwitchController {
        boolean showPlaint();
    }

    /* loaded from: classes18.dex */
    private static class PasswordSpannedString implements Spanned, GetChars {
        private static final char DOT = '\u2022';
        @Nullable
        private final ModeSwitchController controller;
        @NonNull
        private final TextView textView;

        public PasswordSpannedString(@NonNull TextView textView, @Nullable ModeSwitchController modeSwitchController) {
            this.textView = textView;
            this.controller = modeSwitchController;
        }

        @Override // java.lang.CharSequence
        public char charAt(int i2) {
            ModeSwitchController modeSwitchController = this.controller;
            if (modeSwitchController == null || !modeSwitchController.showPlaint()) {
                return '\u2022';
            }
            CharSequence text = this.textView.getText();
            if (text != null) {
                return text.charAt(i2);
            }
            throw new IndexOutOfBoundsException("text is null");
        }

        @Override // android.text.GetChars
        public final void getChars(int i2, int i3, char[] cArr, int i4) {
            ModeSwitchController modeSwitchController = this.controller;
            if (modeSwitchController == null || !modeSwitchController.showPlaint()) {
                for (int i5 = i2; i5 < i3; i5++) {
                    cArr[(i5 - i2) + i4] = '\u2022';
                }
                return;
            }
            CharSequence text = this.textView.getText();
            if (text instanceof GetChars) {
                ((GetChars) text).getChars(i2, i3, cArr, i4);
            }
        }

        @Override // android.text.Spanned
        public int getSpanEnd(Object obj) {
            Editable editableText = this.textView.getEditableText();
            if (editableText != null) {
                return editableText.getSpanEnd(obj);
            }
            return -1;
        }

        @Override // android.text.Spanned
        public int getSpanFlags(Object obj) {
            Editable editableText = this.textView.getEditableText();
            if (editableText != null) {
                return editableText.getSpanFlags(obj);
            }
            return 0;
        }

        @Override // android.text.Spanned
        public int getSpanStart(Object obj) {
            Editable editableText = this.textView.getEditableText();
            if (editableText != null) {
                return editableText.getSpanStart(obj);
            }
            return -1;
        }

        @Override // android.text.Spanned
        public <T> T[] getSpans(int i2, int i3, Class<T> cls) {
            Editable editableText = this.textView.getEditableText();
            if (editableText != null) {
                return (T[]) editableText.getSpans(i2, i3, cls);
            }
            return (T[]) ArrayUtils.emptyArray(cls);
        }

        @Override // java.lang.CharSequence
        public int length() {
            return this.textView.length();
        }

        @Override // android.text.Spanned
        public int nextSpanTransition(int i2, int i3, Class cls) {
            Editable editableText = this.textView.getEditableText();
            return editableText != null ? editableText.nextSpanTransition(i2, i3, cls) : i3;
        }

        @Override // java.lang.CharSequence
        @NonNull
        public CharSequence subSequence(int i2, int i3) {
            ModeSwitchController modeSwitchController = this.controller;
            if (modeSwitchController != null && modeSwitchController.showPlaint()) {
                CharSequence text = this.textView.getText();
                return text == null ? "" : text.subSequence(i2, i3);
            }
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            int i4 = i3 - i2;
            for (int i5 = 0; i5 < i4; i5++) {
                spannableStringBuilder.append((CharSequence) String.valueOf('\u2022'));
            }
            return spannableStringBuilder;
        }

        @Override // java.lang.CharSequence
        public final String toString() {
            CharSequence text = this.textView.getText();
            if (text == null) {
                return "";
            }
            ModeSwitchController modeSwitchController = this.controller;
            if (modeSwitchController != null && modeSwitchController.showPlaint()) {
                return text.toString();
            }
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            int length = text.length();
            for (int i2 = 0; i2 < length; i2++) {
                spannableStringBuilder.append((CharSequence) String.valueOf('\u2022'));
            }
            return spannableStringBuilder.toString();
        }
    }

    public PasswordTransformationMethod(TextView textView, @Nullable ModeSwitchController modeSwitchController) {
        this.passwordSpannedString = new PasswordSpannedString(textView, modeSwitchController);
    }

    @Override // android.text.method.TransformationMethod
    public CharSequence getTransformation(CharSequence charSequence, View view) {
        return this.passwordSpannedString;
    }

    @Override // android.text.method.TransformationMethod
    public void onFocusChanged(View view, CharSequence charSequence, boolean z, int i2, Rect rect) {
    }
}
