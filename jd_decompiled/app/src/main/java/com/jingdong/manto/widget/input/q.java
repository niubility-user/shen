package com.jingdong.manto.widget.input;

import android.text.method.PasswordTransformationMethod;
import android.view.View;

/* loaded from: classes16.dex */
final class q extends PasswordTransformationMethod {

    /* loaded from: classes16.dex */
    final class a implements CharSequence {
        private CharSequence a;

        a(q qVar, CharSequence charSequence) {
            this.a = charSequence;
        }

        @Override // java.lang.CharSequence
        public final char charAt(int i2) {
            return '\u25cf';
        }

        @Override // java.lang.CharSequence
        public final int length() {
            return this.a.length();
        }

        @Override // java.lang.CharSequence
        public final CharSequence subSequence(int i2, int i3) {
            return this.a.subSequence(i2, i3);
        }
    }

    @Override // android.text.method.PasswordTransformationMethod, android.text.method.TransformationMethod
    public final CharSequence getTransformation(CharSequence charSequence, View view) {
        return new a(this, charSequence);
    }
}
