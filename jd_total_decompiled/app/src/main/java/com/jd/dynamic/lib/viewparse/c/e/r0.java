package com.jd.dynamic.lib.viewparse.c.e;

import androidx.appcompat.widget.AppCompatTextView;
import com.jd.dynamic.DYConstants;
import com.jd.dynamic.base.DynamicSdk;
import com.jd.dynamic.base.interfaces.IExceptionHandler;
import java.util.HashMap;

/* loaded from: classes13.dex */
public class r0 extends p0<AppCompatTextView> {
    @Override // com.jd.dynamic.lib.viewparse.c.e.q0
    /* renamed from: f  reason: merged with bridge method [inline-methods] */
    public void a(HashMap<String, String> hashMap, AppCompatTextView appCompatTextView) {
        if (hashMap.containsKey(DYConstants.DY_REPEATLIMIT)) {
            int i2 = -1;
            try {
                i2 = Integer.parseInt(hashMap.get(DYConstants.DY_REPEATLIMIT));
            } catch (Exception e2) {
                DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_BIND, "MarqueeTextParse parse repeatLimit catch error", b(), d(), e2);
            }
            appCompatTextView.setMarqueeRepeatLimit(i2);
        }
    }
}
