package com.jingdong.manto.widget.input;

import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.Selection;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
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
    */
    public static <Input extends EditText & com.jingdong.manto.widget.input.z.d> void a(Input input, com.jingdong.manto.widget.input.a0.g gVar) {
        String str;
        Input input2;
        Typeface create;
        Drawable colorDrawable;
        if (input == null || gVar == null) {
            return;
        }
        Float f2 = gVar.f14410l;
        if (f2 != null && f2.floatValue() != input.getTextSize()) {
            input.setTextSize(gVar.f14410l.floatValue());
        }
        Integer num = gVar.f14409k;
        if (num != null) {
            input.setTextColor(num.intValue());
        }
        if (gVar.f14408j != null) {
            colorDrawable = (input.getBackground() != null && (input.getBackground() instanceof ColorDrawable) && ((ColorDrawable) input.getBackground()).getColor() == gVar.f14408j.intValue()) ? null : new ColorDrawable(gVar.f14408j.intValue());
            if (!TextUtils.isEmpty(gVar.o)) {
                SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(gVar.o);
                int length = gVar.o.length();
                spannableStringBuilder.setSpan(new StyleSpan(com.jingdong.manto.widget.input.a0.c.a(gVar.p).a), 0, length, 18);
                if (gVar.r != null) {
                    spannableStringBuilder.setSpan(new ForegroundColorSpan(gVar.r.intValue()), 0, length, 18);
                }
                if (gVar.q != null) {
                    spannableStringBuilder.setSpan(new AbsoluteSizeSpan(gVar.q.intValue(), false), 0, length, 18);
                }
                input.setHint(spannableStringBuilder);
            }
            str = gVar.f14411m;
            if (str != null && (create = Typeface.create("sans-serif", com.jingdong.manto.widget.input.a0.c.a(str).a)) != null) {
                input.setTypeface(create);
            }
            com.jingdong.manto.widget.input.a0.d a = com.jingdong.manto.widget.input.a0.d.a(gVar.f14407i);
            input2 = input;
            if (input2 != null) {
                if (a == com.jingdong.manto.widget.input.a0.d.LEFT) {
                    input2.c();
                } else if (a == com.jingdong.manto.widget.input.a0.d.RIGHT) {
                    input2.b();
                } else {
                    input2.d();
                }
            }
            input.setVisibility(InputUtil.isTrue(gVar.t) ? 8 : 0);
            input2.setFixed(InputUtil.isTrue(gVar.x));
        }
        input.setBackground(colorDrawable);
        if (!TextUtils.isEmpty(gVar.o)) {
        }
        str = gVar.f14411m;
        if (str != null) {
            input.setTypeface(create);
        }
        com.jingdong.manto.widget.input.a0.d a2 = com.jingdong.manto.widget.input.a0.d.a(gVar.f14407i);
        input2 = input;
        if (input2 != null) {
        }
        input.setVisibility(InputUtil.isTrue(gVar.t) ? 8 : 0);
        input2.setFixed(InputUtil.isTrue(gVar.x));
    }
}
