package com.jd.dynamic.lib.b.a.a;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import com.jd.dynamic.base.CommFunction;
import com.jd.dynamic.base.DynamicSdk;
import com.jd.dynamic.base.DynamicTemplateEngine;
import com.jd.dynamic.base.interfaces.ICancelLogin;
import com.jd.dynamic.lib.b.a.a.l1;
import com.jingdong.common.login.LoginConstans;
import java.util.Iterator;
import org.json.JSONObject;
import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;

/* loaded from: classes13.dex */
public class l1 extends e1 {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes13.dex */
    public class a implements ICancelLogin {
        final /* synthetic */ JSONObject a;

        a(JSONObject jSONObject) {
            this.a = jSONObject;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void a(String str, String str2) {
            com.jd.dynamic.lib.utils.s.b(str2, str, ((CommFunction) l1.this).mEngine, l1.this.mTargetView);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void b(String str, String str2) {
            com.jd.dynamic.lib.utils.s.b(str2, str, ((CommFunction) l1.this).mEngine, l1.this.mTargetView);
        }

        @Override // com.jd.dynamic.base.interfaces.ICancelLogin
        public void onCancel(final String str) {
            Observable.from(com.jd.dynamic.lib.utils.s.i(this.a.optString("success"))).forEach(new Action1() { // from class: com.jd.dynamic.lib.b.a.a.f0
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    l1.a.this.b(str, (String) obj);
                }
            });
        }

        @Override // com.jd.dynamic.base.interfaces.ICancelLogin
        public void onSuccess(final String str) {
            Observable.from(com.jd.dynamic.lib.utils.s.i(this.a.optString("success"))).forEach(new Action1() { // from class: com.jd.dynamic.lib.b.a.a.g0
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    l1.a.this.a(str, (String) obj);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes13.dex */
    public class b implements ICancelLogin {
        final /* synthetic */ com.jd.dynamic.a.f a;

        b(l1 l1Var, com.jd.dynamic.a.f fVar) {
            this.a = fVar;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void a(com.jd.dynamic.a.f fVar, String str) {
            if (fVar != null) {
                fVar.a(str);
                fVar.q();
            }
        }

        @Override // com.jd.dynamic.base.interfaces.ICancelLogin
        public void onCancel(String str) {
        }

        @Override // com.jd.dynamic.base.interfaces.ICancelLogin
        public void onSuccess(final String str) {
            Scheduler.Worker createWorker = AndroidSchedulers.mainThread().createWorker();
            final com.jd.dynamic.a.f fVar = this.a;
            createWorker.schedule(new Action0() { // from class: com.jd.dynamic.lib.b.a.a.h0
                @Override // rx.functions.Action0
                public final void call() {
                    l1.b.a(com.jd.dynamic.a.f.this, str);
                }
            });
        }
    }

    private String c() {
        return (DynamicSdk.getEngine().getAppRouter() == null || !DynamicSdk.getEngine().getAppRouter().isHasLogin()) ? "0" : "1";
    }

    private void d(Activity activity, JSONObject jSONObject) {
        String str;
        if (DynamicSdk.getEngine().getAppRouter() == null || jSONObject == null) {
            return;
        }
        JSONObject optJSONObject = jSONObject.optJSONObject("params");
        Bundle bundle = new Bundle();
        if (optJSONObject != null) {
            str = optJSONObject.optString("calltag");
            JSONObject optJSONObject2 = optJSONObject.optJSONObject("extras");
            if (optJSONObject2 != null) {
                Iterator<String> keys = optJSONObject2.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    String optString = optJSONObject2.optString(next);
                    if (!TextUtils.isEmpty(optString)) {
                        bundle.putString(next, optString);
                    }
                }
            }
        } else {
            str = "";
        }
        DynamicSdk.getEngine().getAppRouter().startLoginActivity(activity, bundle, new a(jSONObject), str);
    }

    public Object b(Activity activity, JSONObject jSONObject, com.jd.dynamic.a.f fVar) {
        String str;
        if (DynamicSdk.getEngine().getAppRouter() == null || jSONObject == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        JSONObject optJSONObject = jSONObject.optJSONObject("params");
        if (optJSONObject != null) {
            str = optJSONObject.optString("calltag");
            JSONObject optJSONObject2 = optJSONObject.optJSONObject("extras");
            if (optJSONObject2 != null) {
                Iterator<String> keys = optJSONObject2.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    String optString = optJSONObject2.optString(next);
                    if (!TextUtils.isEmpty(optString)) {
                        bundle.putString(next, optString);
                    }
                }
            }
        } else {
            str = "";
        }
        DynamicSdk.getEngine().getAppRouter().startLoginActivity(activity, bundle, new b(this, fVar), str);
        return null;
    }

    @Override // com.jd.dynamic.base.CommFunction
    public Object exec(DynamicTemplateEngine dynamicTemplateEngine, JSONObject jSONObject) {
        this.mEngine = dynamicTemplateEngine;
        if (dynamicTemplateEngine == null) {
            return null;
        }
        Activity activity = dynamicTemplateEngine.getActivity();
        String str = (String) jSONObject.remove("fun");
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        str.hashCode();
        if (str.equals(LoginConstans.FREGMENT_LOGIN_FLAG)) {
            d(activity, jSONObject);
        } else if (str.equals("isLoggedIn")) {
            return c();
        }
        return null;
    }
}
