package com.jd.dynamic.lib.b.a.a;

import android.text.TextUtils;
import android.view.View;
import com.jd.dynamic.DYConstants;
import com.jd.dynamic.base.DynamicTemplateEngine;
import org.json.JSONObject;
import rx.Observable;
import rx.functions.Action1;

/* loaded from: classes13.dex */
public class f1 extends e1 {
    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(DynamicTemplateEngine dynamicTemplateEngine, String str) {
        View view = this.mTargetView;
        com.jd.dynamic.lib.utils.s.b(str, view, dynamicTemplateEngine, view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void b(Throwable th) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void c(DynamicTemplateEngine dynamicTemplateEngine, String str) {
        View view = this.mTargetView;
        com.jd.dynamic.lib.utils.s.b(str, view, dynamicTemplateEngine, view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void d(Throwable th) {
    }

    @Override // com.jd.dynamic.base.CommFunction
    public Object exec(final DynamicTemplateEngine dynamicTemplateEngine, JSONObject jSONObject) {
        String str;
        Observable from;
        Action1 action1;
        Action1<Throwable> action12;
        this.mEngine = dynamicTemplateEngine;
        if (dynamicTemplateEngine != null && (str = (String) jSONObject.remove("fun")) != null && "if".equals(str)) {
            String optString = jSONObject.optString("condition");
            String optString2 = jSONObject.optString(DYConstants.DY_TRUE);
            String optString3 = jSONObject.optString(DYConstants.DY_FALSE);
            if (TextUtils.equals(optString, DYConstants.DY_TRUE)) {
                from = Observable.from(com.jd.dynamic.lib.utils.s.i(optString2));
                action1 = new Action1() { // from class: com.jd.dynamic.lib.b.a.a.d
                    @Override // rx.functions.Action1
                    public final void call(Object obj) {
                        f1.this.c(dynamicTemplateEngine, (String) obj);
                    }
                };
                action12 = new Action1() { // from class: com.jd.dynamic.lib.b.a.a.c
                    @Override // rx.functions.Action1
                    public final void call(Object obj) {
                        f1.d((Throwable) obj);
                    }
                };
            } else if (TextUtils.equals(optString, DYConstants.DY_FALSE)) {
                from = Observable.from(com.jd.dynamic.lib.utils.s.i(optString3));
                action1 = new Action1() { // from class: com.jd.dynamic.lib.b.a.a.b
                    @Override // rx.functions.Action1
                    public final void call(Object obj) {
                        f1.this.a(dynamicTemplateEngine, (String) obj);
                    }
                };
                action12 = new Action1() { // from class: com.jd.dynamic.lib.b.a.a.e
                    @Override // rx.functions.Action1
                    public final void call(Object obj) {
                        f1.b((Throwable) obj);
                    }
                };
            }
            from.forEach(action1, action12);
        }
        return null;
    }
}
