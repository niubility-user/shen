package com.jd.dynamic.lib.viewparse.c.e;

import android.graphics.Color;
import android.text.TextUtils;
import androidx.appcompat.widget.AppCompatTextView;
import com.jd.dynamic.DYConstants;
import com.jd.dynamic.base.DynamicSdk;
import com.jd.dynamic.base.interfaces.IExceptionHandler;
import java.util.HashMap;

/* loaded from: classes13.dex */
public class l0 extends p0<AppCompatTextView> {
    @Override // com.jd.dynamic.lib.viewparse.c.e.q0
    /* renamed from: f  reason: merged with bridge method [inline-methods] */
    public void a(HashMap<String, String> hashMap, AppCompatTextView appCompatTextView) {
        if (hashMap.containsKey(DYConstants.DY_TEXT_COLOR)) {
            String str = hashMap.get(DYConstants.DY_TEXT_COLOR);
            if (!TextUtils.isEmpty(str) && str.startsWith("#")) {
                try {
                    appCompatTextView.setTextColor(Color.parseColor(str));
                } catch (Exception e2) {
                    DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_BIND, "AndroidTextColorParse parse textColor catch error", b(), d(), e2);
                }
            }
        }
    }
}
