package com.jd.manto.d;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.jd.manto.router.RouterProxyActivity;
import com.jingdong.common.unification.router.CallBackWithReturnListener;
import com.jingdong.common.unification.router.JDRouter;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.manto.sdk.api.IRouter;
import org.json.JSONObject;

/* loaded from: classes17.dex */
public class a0 implements IRouter {
    static {
        a0.class.toString();
    }

    private void a(Context context, String str, IRouter.CallBack callBack) {
        JDRouter build = JDRouter.build(context, str);
        build.callBackListener((CallBackWithReturnListener) new a(this, callBack));
        build.open();
    }

    @Override // com.jingdong.manto.sdk.api.IRouter
    public void jumpTo(Context context, JSONObject jSONObject, IRouter.CallBack callBack) {
        if (context == null) {
            try {
                context = JdSdk.getInstance().getApplicationContext();
            } catch (Throwable th) {
                th.printStackTrace();
                callBack.onFail(-1);
                return;
            }
        }
        String trim = jSONObject.optString("url").trim();
        if (!TextUtils.isEmpty(trim) && trim.startsWith("router://")) {
            if (jSONObject.optBoolean("isMethod", false)) {
                a(context, trim, callBack);
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putString("url", trim);
            RouterProxyActivity.v(context, bundle, callBack);
            return;
        }
        callBack.onFail(-1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class a implements CallBackWithReturnListener {
        final /* synthetic */ IRouter.CallBack a;

        a(a0 a0Var, IRouter.CallBack callBack) {
            this.a = callBack;
        }

        @Override // com.jingdong.common.unification.router.CallBackWithReturnListener
        public void onComplete(Object obj) {
            Bundle bundle = new Bundle();
            if (obj instanceof String) {
                bundle.putString("result", (String) obj);
            } else if (obj instanceof Boolean) {
                bundle.putBoolean("result", ((Boolean) obj).booleanValue());
            } else if (obj instanceof Number) {
                if (obj instanceof Integer) {
                    bundle.putInt("result", ((Integer) obj).intValue());
                } else {
                    bundle.putDouble("result", ((Double) obj).doubleValue());
                }
            }
            IRouter.CallBack callBack = this.a;
            if (callBack != null) {
                callBack.onSuccess(bundle);
            }
        }

        @Override // com.jingdong.common.unification.router.CallBackListener
        public void onError(int i2) {
            IRouter.CallBack callBack = this.a;
            if (callBack != null) {
                callBack.onFail(i2);
            }
        }

        @Override // com.jingdong.common.unification.router.CallBackListener
        public void onComplete() {
            IRouter.CallBack callBack = this.a;
            if (callBack != null) {
                callBack.onSuccess(new Bundle());
            }
        }
    }
}
