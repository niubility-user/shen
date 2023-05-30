package com.jingdong.app.mall.global;

import android.content.Context;
import android.text.TextUtils;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.unification.router.CallBackListener;
import com.jingdong.common.unification.router.CallBackWithReturnListener;
import com.jingdong.common.unification.router.builder.RouterEntry;
import com.jingdong.common.utils.BackForegroundWatcher;
import com.jingdong.common.utils.JDPrivacyHelper;
import com.jingdong.common.web.IRouterParams;
import com.jingdong.jdsdk.JdSdk;
import java.io.File;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class PasteStateRouterImpl {
    private static String a = "spstate";
    private static JSONObject b;

    private static JSONObject a() {
        if (!JDPrivacyHelper.isAcceptPrivacy(JdSdk.getInstance().getApplicationContext())) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(a, "1");
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            return jSONObject;
        }
        JSONObject jSONObject2 = b;
        if (jSONObject2 != null && jSONObject2.has(a)) {
            return b;
        }
        String a2 = b.a(new File(JdSdk.getInstance().getApplicationContext().getFilesDir(), "jdPasState"));
        if (!"0".equals(a2) && !"1".equals(a2)) {
            a2 = "0";
        }
        JSONObject jSONObject3 = new JSONObject();
        try {
            jSONObject3.put(a, a2);
        } catch (JSONException e3) {
            e3.printStackTrace();
        }
        b = jSONObject3;
        return jSONObject3;
    }

    private static void b(JSONObject jSONObject) {
        if (jSONObject == null || !jSONObject.has(a)) {
            return;
        }
        String optString = jSONObject.optString(a, "0");
        String str = ("0".equals(optString) || "1".equals(optString)) ? optString : "0";
        if (b == null) {
            b = new JSONObject();
        }
        try {
            b.put(a, str);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        b.b(str, new File(JdSdk.getInstance().getApplicationContext().getFilesDir(), "jdPasState"));
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0037, code lost:
        if (java.lang.Integer.parseInt(r2.trim()) != 0) goto L16;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static boolean getClipPasteStatusValue() {
        boolean z = false;
        if (JDPrivacyHelper.isAcceptPrivacy(JdSdk.getInstance().getApplicationContext()) && BackForegroundWatcher.getInstance().isAppForeground()) {
            try {
                JSONObject a2 = a();
                if (a2 != null) {
                    String optString = a2.optString(a, "0");
                    if (!TextUtils.isEmpty(optString)) {
                    }
                }
                z = true;
                return z;
            } catch (Exception e2) {
                e2.printStackTrace();
                return true;
            }
        }
        return false;
    }

    public static JSONObject pasteState(IRouterParams iRouterParams) {
        JSONObject jSONObject = new JSONObject();
        if (iRouterParams == null) {
            return jSONObject;
        }
        try {
            jSONObject = new JSONObject(iRouterParams.getRouterParam());
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        if (jSONObject.length() == 0) {
            return a();
        }
        b(jSONObject);
        return jSONObject;
    }

    public void getClipPasteStatusValue(Context context, JDJSONObject jDJSONObject, RouterEntry routerEntry) {
        if (routerEntry == null || routerEntry.callBackListener == null) {
            return;
        }
        try {
            boolean clipPasteStatusValue = getClipPasteStatusValue();
            CallBackListener callBackListener = routerEntry.callBackListener;
            if (callBackListener instanceof CallBackWithReturnListener) {
                ((CallBackWithReturnListener) callBackListener).onComplete(clipPasteStatusValue ? "0" : "1");
            } else {
                callBackListener.onComplete();
            }
        } catch (Exception unused) {
            routerEntry.callBackListener.onError(703);
        }
    }
}
