package com.jd.dynamic.lib.b.a.a;

import android.text.TextUtils;
import com.jd.dynamic.base.DynamicTemplateEngine;
import com.jd.dynamic.lib.viewparse.BaseFrameLayout;
import org.json.JSONObject;
import rx.Observable;
import rx.functions.Action1;

/* loaded from: classes13.dex */
public class h1 extends e1 {
    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(String str, final JSONObject jSONObject, final DynamicTemplateEngine dynamicTemplateEngine) {
        Observable.from(com.jd.dynamic.lib.utils.s.i(str)).forEach(new Action1() { // from class: com.jd.dynamic.lib.b.a.a.i
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                h1.this.c(jSONObject, dynamicTemplateEngine, (String) obj);
            }
        }, new Action1() { // from class: com.jd.dynamic.lib.b.a.a.f
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                h1.b((Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void b(Throwable th) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void c(JSONObject jSONObject, DynamicTemplateEngine dynamicTemplateEngine, String str) {
        com.jd.dynamic.lib.utils.s.b(str, jSONObject, dynamicTemplateEngine, this.mTargetView);
    }

    @Override // com.jd.dynamic.base.CommFunction
    public Object exec(final DynamicTemplateEngine dynamicTemplateEngine, final JSONObject jSONObject) {
        this.mEngine = dynamicTemplateEngine;
        if (jSONObject != null) {
            String optString = jSONObject.optString("delayTime");
            final String optString2 = jSONObject.optString("onSuccess");
            if (TextUtils.isEmpty(optString) || TextUtils.isEmpty(optString2)) {
                return null;
            }
            long j2 = 0;
            try {
                j2 = Long.parseLong(optString);
            } catch (Exception unused) {
            }
            BaseFrameLayout.getMyHandler().postDelayed(new Runnable() { // from class: com.jd.dynamic.lib.b.a.a.h
                @Override // java.lang.Runnable
                public final void run() {
                    h1.this.a(optString2, jSONObject, dynamicTemplateEngine);
                }
            }, j2);
            return null;
        }
        return null;
    }
}
