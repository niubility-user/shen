package com.jd.dynamic.lib.viewparse.c.e;

import android.text.SpannedString;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.widget.TextViewCompat;
import com.jd.dynamic.DYConstants;
import com.jd.dynamic.R;
import com.jd.dynamic.base.DynamicTemplateEngine;
import com.jd.dynamic.lib.utils.DPIUtil;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes13.dex */
public class m0 extends p0<AppCompatTextView> {
    private String f(String str) {
        long j2;
        try {
            j2 = Long.parseLong(str);
        } catch (Exception unused) {
            j2 = 0;
        }
        return com.jd.dynamic.lib.utils.m.i(j2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: g  reason: merged with bridge method [inline-methods] */
    public void j(AppCompatTextView appCompatTextView, List<String> list, float f2, String str) {
        if (appCompatTextView.getText() instanceof SpannedString) {
            return;
        }
        if (com.jd.dynamic.lib.utils.m.I(list)) {
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                DynamicTemplateEngine dynamicTemplateEngine = this.a;
                com.jd.dynamic.lib.utils.s.b(it.next(), dynamicTemplateEngine.currentData, dynamicTemplateEngine, appCompatTextView);
            }
        }
        TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(appCompatTextView, (int) n0.f(str, 1.0f), (int) f2, 1, 2);
    }

    private void i(HashMap<String, String> hashMap, final AppCompatTextView appCompatTextView, final List<String> list) {
        int i2 = R.id.dynamic_text_size_origin;
        final float floatValue = appCompatTextView.getTag(i2) != null ? ((Float) appCompatTextView.getTag(i2)).floatValue() : DPIUtil.px2dip(appCompatTextView.getTextSize());
        final String str = hashMap.get(DYConstants.DY_AUTO_MIN_SIZE);
        if (appCompatTextView.getLayoutParams() == null || appCompatTextView.getLayoutParams().width == -2 || appCompatTextView.getLayoutParams().height == -2) {
            appCompatTextView.post(new Runnable() { // from class: com.jd.dynamic.lib.viewparse.c.e.x
                @Override // java.lang.Runnable
                public final void run() {
                    m0.this.j(appCompatTextView, list, floatValue, str);
                }
            });
        } else {
            j(appCompatTextView, list, floatValue, str);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:135:0x0262, code lost:
        if (r13.getTag(com.jd.dynamic.R.id.dynamic_use_auto_size) != null) goto L128;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x0175, code lost:
        if (com.jd.dynamic.DYConstants.DY_JD_LIGHT.equals(r5) != false) goto L69;
     */
    /* JADX WARN: Removed duplicated region for block: B:141:0x026f  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x0273  */
    @Override // com.jd.dynamic.lib.viewparse.c.e.q0
    @androidx.annotation.RequiresApi(api = 16)
    /* renamed from: h  reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(java.util.HashMap<java.lang.String, java.lang.String> r12, androidx.appcompat.widget.AppCompatTextView r13) {
        /*
            Method dump skipped, instructions count: 658
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.lib.viewparse.c.e.m0.a(java.util.HashMap, androidx.appcompat.widget.AppCompatTextView):void");
    }
}
