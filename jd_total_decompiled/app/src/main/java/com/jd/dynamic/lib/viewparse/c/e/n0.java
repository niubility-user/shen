package com.jd.dynamic.lib.viewparse.c.e;

import android.text.TextUtils;
import androidx.appcompat.widget.AppCompatTextView;
import com.jd.dynamic.DYConstants;
import com.jd.dynamic.R;
import java.util.HashMap;

/* loaded from: classes13.dex */
public class n0 extends p0<AppCompatTextView> {
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
    public void a(HashMap<String, String> hashMap, AppCompatTextView appCompatTextView) {
        String str = hashMap.get(DYConstants.DY_TEXT_SIZE);
        if (str != null) {
            float f2 = f(str, 14.0f);
            appCompatTextView.setTextSize(2, f2);
            appCompatTextView.setTag(R.id.dynamic_text_size_origin, Float.valueOf(f2));
        }
    }
}
