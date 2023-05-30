package com.jd.dynamic.lib.b.a.a;

import android.text.TextUtils;
import com.jd.dynamic.base.DynamicSdk;
import com.jd.dynamic.base.DynamicTemplateEngine;
import com.jingdong.common.web.managers.WebPerfManager;
import com.jingdong.sdk.platform.business.personal.R2;
import org.json.JSONObject;

/* loaded from: classes13.dex */
public class j1 extends e1 {
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0072, code lost:
        if (r3.equals("click") == false) goto L10;
     */
    @Override // com.jd.dynamic.base.CommFunction
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object exec(DynamicTemplateEngine dynamicTemplateEngine, JSONObject jSONObject) {
        JSONObject jSONObject2;
        char c2 = 2;
        com.jd.dynamic.lib.utils.t.g("JDMtaFunction", jSONObject);
        this.mEngine = dynamicTemplateEngine;
        String str = (String) jSONObject.remove("fun");
        if (!TextUtils.isEmpty(str)) {
            String optString = jSONObject.optString("pageId");
            String optString2 = jSONObject.optString(WebPerfManager.PAGE_NAME);
            String optString3 = jSONObject.optString("pageParam");
            String optString4 = jSONObject.optString("jsonParam");
            String optString5 = jSONObject.optString("eventId");
            String optString6 = jSONObject.optString("eventParam");
            String optString7 = jSONObject.optString("nextPageName");
            try {
                jSONObject2 = new JSONObject(jSONObject.optString("params"));
            } catch (Exception unused) {
                jSONObject2 = null;
            }
            str.hashCode();
            switch (str.hashCode()) {
                case -1926005497:
                    if (str.equals("exposure")) {
                        c2 = 0;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case R2.color.c_e8d5b1 /* 3590 */:
                    if (str.equals("pv")) {
                        c2 = 1;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case 94750088:
                    break;
                default:
                    c2 = '\uffff';
                    break;
            }
            switch (c2) {
                case 0:
                    if (!TextUtils.isEmpty(optString5) && DynamicSdk.getEngine() != null && DynamicSdk.getEngine().getDynamicMta() != null) {
                        DynamicSdk.getEngine().getDynamicMta().expoMtaEventWithExtParams(DynamicSdk.getEngine().getContext(), optString5, optString6, optString, optString2, optString3, optString7, optString4, jSONObject2);
                        break;
                    }
                    break;
                case 1:
                    if (DynamicSdk.getEngine() != null && DynamicSdk.getEngine().getDynamicMta() != null) {
                        DynamicSdk.getEngine().getDynamicMta().pvMtaEventWithExtParams(DynamicSdk.getEngine().getContext(), TextUtils.isEmpty(optString2) ? this.mEngine.getActivity() : optString2, optString3, optString, jSONObject2);
                        break;
                    }
                    break;
                case 2:
                    if (!TextUtils.isEmpty(optString5) && DynamicSdk.getEngine() != null && DynamicSdk.getEngine().getDynamicMta() != null) {
                        DynamicSdk.getEngine().getDynamicMta().clickMtaEventWithExtParams(DynamicSdk.getEngine().getContext(), optString5, optString6, optString, optString2, optString3, optString7, optString4, jSONObject2);
                        break;
                    }
                    break;
            }
        }
        return null;
    }
}
