package com.jd.dynamic.lib.viewparse.c.e;

import android.text.TextUtils;
import android.widget.EditText;
import com.jd.dynamic.DYConstants;
import java.util.HashMap;

/* loaded from: classes13.dex */
public class e0 extends p0<EditText> {
    public static float f(String str, float f2) {
        if (TextUtils.isEmpty(str)) {
            return f2;
        }
        try {
            return Float.parseFloat(str);
        } catch (NumberFormatException unused) {
            return f2;
        }
    }

    @Override // com.jd.dynamic.lib.viewparse.c.e.q0
    /* renamed from: g  reason: merged with bridge method [inline-methods] */
    public void a(HashMap<String, String> hashMap, EditText editText) {
        String str = hashMap.get(DYConstants.DY_TEXT_SIZE);
        if (str != null) {
            editText.setTextSize(2, f(str, 14.0f));
        }
    }
}
