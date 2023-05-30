package com.jingdong.manto.widget.input;

import android.text.Selection;
import android.widget.EditText;
import com.jingdong.manto.utils.MantoLog;

/* loaded from: classes16.dex */
public class h {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static <Input extends EditText & com.jingdong.manto.widget.input.z.d> void a(Input input, int i2, int i3) {
        if (input == null || input.getEditableText() == null) {
            MantoLog.w("InputCommStyleHelper", String.format("applySelection, invalid input %s", input));
            return;
        }
        if (i2 <= -2) {
            i2 = input.getSelectionStart();
        } else if (i2 == -1 || i2 > input.getText().length()) {
            i2 = input.getText().length();
        }
        if (i3 <= -2) {
            i3 = input.getSelectionEnd();
        } else if (i3 == -1 || i3 > input.getText().length()) {
            i3 = input.getText().length();
        }
        if (i2 > i3) {
            i2 = i3;
        }
        Selection.removeSelection(input.getEditableText());
        input.setSelection(i2, i3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00e7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static <Input extends android.widget.EditText & com.jingdong.manto.widget.input.z.d> void a(Input r6, com.jingdong.manto.widget.input.a0.g r7) {
        /*
            if (r6 == 0) goto Lf5
            if (r7 == 0) goto Lf5
            java.lang.Float r0 = r7.f14410l
            if (r0 == 0) goto L1d
            float r0 = r0.floatValue()
            float r1 = r6.getTextSize()
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 == 0) goto L1d
            java.lang.Float r0 = r7.f14410l
            float r0 = r0.floatValue()
            r6.setTextSize(r0)
        L1d:
            java.lang.Integer r0 = r7.f14409k
            if (r0 == 0) goto L28
            int r0 = r0.intValue()
            r6.setTextColor(r0)
        L28:
            java.lang.Integer r0 = r7.f14408j
            if (r0 != 0) goto L31
            r0 = 0
        L2d:
            r6.setBackground(r0)
            goto L5d
        L31:
            android.graphics.drawable.Drawable r0 = r6.getBackground()
            if (r0 == 0) goto L51
            android.graphics.drawable.Drawable r0 = r6.getBackground()
            boolean r0 = r0 instanceof android.graphics.drawable.ColorDrawable
            if (r0 == 0) goto L51
            android.graphics.drawable.Drawable r0 = r6.getBackground()
            android.graphics.drawable.ColorDrawable r0 = (android.graphics.drawable.ColorDrawable) r0
            int r0 = r0.getColor()
            java.lang.Integer r1 = r7.f14408j
            int r1 = r1.intValue()
            if (r0 == r1) goto L5d
        L51:
            android.graphics.drawable.ColorDrawable r0 = new android.graphics.drawable.ColorDrawable
            java.lang.Integer r1 = r7.f14408j
            int r1 = r1.intValue()
            r0.<init>(r1)
            goto L2d
        L5d:
            java.lang.String r0 = r7.o
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            r1 = 0
            if (r0 != 0) goto Lac
            android.text.SpannableStringBuilder r0 = new android.text.SpannableStringBuilder
            java.lang.String r2 = r7.o
            r0.<init>(r2)
            java.lang.String r2 = r7.o
            int r2 = r2.length()
            android.text.style.StyleSpan r3 = new android.text.style.StyleSpan
            java.lang.String r4 = r7.p
            com.jingdong.manto.widget.input.a0.c r4 = com.jingdong.manto.widget.input.a0.c.a(r4)
            int r4 = r4.a
            r3.<init>(r4)
            r4 = 18
            r0.setSpan(r3, r1, r2, r4)
            java.lang.Integer r3 = r7.r
            if (r3 == 0) goto L97
            android.text.style.ForegroundColorSpan r3 = new android.text.style.ForegroundColorSpan
            java.lang.Integer r5 = r7.r
            int r5 = r5.intValue()
            r3.<init>(r5)
            r0.setSpan(r3, r1, r2, r4)
        L97:
            java.lang.Integer r3 = r7.q
            if (r3 == 0) goto La9
            android.text.style.AbsoluteSizeSpan r3 = new android.text.style.AbsoluteSizeSpan
            java.lang.Integer r5 = r7.q
            int r5 = r5.intValue()
            r3.<init>(r5, r1)
            r0.setSpan(r3, r1, r2, r4)
        La9:
            r6.setHint(r0)
        Lac:
            java.lang.String r0 = r7.f14411m
            if (r0 == 0) goto Lc1
            com.jingdong.manto.widget.input.a0.c r0 = com.jingdong.manto.widget.input.a0.c.a(r0)
            int r0 = r0.a
            java.lang.String r2 = "sans-serif"
            android.graphics.Typeface r0 = android.graphics.Typeface.create(r2, r0)
            if (r0 == 0) goto Lc1
            r6.setTypeface(r0)
        Lc1:
            java.lang.String r0 = r7.f14407i
            com.jingdong.manto.widget.input.a0.d r0 = com.jingdong.manto.widget.input.a0.d.a(r0)
            r2 = r6
            com.jingdong.manto.widget.input.z.d r2 = (com.jingdong.manto.widget.input.z.d) r2
            if (r2 == 0) goto Ldf
            com.jingdong.manto.widget.input.a0.d r3 = com.jingdong.manto.widget.input.a0.d.LEFT
            if (r0 != r3) goto Ld4
            r2.c()
            goto Ldf
        Ld4:
            com.jingdong.manto.widget.input.a0.d r3 = com.jingdong.manto.widget.input.a0.d.RIGHT
            if (r0 != r3) goto Ldc
            r2.b()
            goto Ldf
        Ldc:
            r2.d()
        Ldf:
            java.lang.Boolean r0 = r7.t
            boolean r0 = com.jingdong.manto.widget.input.InputUtil.isTrue(r0)
            if (r0 == 0) goto Le9
            r1 = 8
        Le9:
            r6.setVisibility(r1)
            java.lang.Boolean r6 = r7.x
            boolean r6 = com.jingdong.manto.widget.input.InputUtil.isTrue(r6)
            r2.setFixed(r6)
        Lf5:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.manto.widget.input.h.a(android.widget.EditText, com.jingdong.manto.widget.input.a0.g):void");
    }
}
