package com.jingdong.common.web.javainterface.impl;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.webkit.JavascriptInterface;
import com.jingdong.common.web.BaseWebComponent;
import com.jingdong.common.web.WebUiConstans;
import com.jingdong.common.web.javainterface.IJavaInterface;
import com.jingdong.common.web.uibinder.IWebUiBinder;
import com.jingdong.common.web.util.WebUnifiedMtaUtil;
import com.jingdong.corelib.utils.Log;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class JDFunction extends BaseWebComponent implements IJavaInterface {
    private Context context;
    private String jDUerId;

    public JDFunction(Context context, IWebUiBinder iWebUiBinder) {
        super(iWebUiBinder);
        this.context = context;
    }

    @JavascriptInterface
    public void configLayerType(String str) {
        WebUnifiedMtaUtil.functionReport(this.webUiBinder, "JDFunction_configLayerType");
        if ("0".equals(str)) {
            this.webUiBinder.getJdWebView().setLayerType(1, null);
        } else {
            this.webUiBinder.getJdWebView().setLayerType(2, null);
        }
    }

    @JavascriptInterface
    public void getInfo(String str) {
        WebUnifiedMtaUtil.functionReport(this.webUiBinder, "JDFunction_getInfo");
        if (this.webUiBinder.getJdWebView() != null) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("jDUerId", this.jDUerId);
                this.webUiBinder.getJdWebView().injectJs("javascript:" + str + "(" + jSONObject + ")");
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            if (Log.D) {
                Log.d("JDFunction", " injectJs--> javascript:" + str + "(" + this.jDUerId + ")");
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:34:? A[RETURN, SYNTHETIC] */
    @android.webkit.JavascriptInterface
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void getNALocation(java.lang.String r12) {
        /*
            r11 = this;
            java.lang.String r0 = "("
            com.jingdong.common.web.uibinder.IWebUiBinder r1 = r11.webUiBinder
            java.lang.String r2 = "JDFunction_getNALocation"
            com.jingdong.common.web.util.WebUnifiedMtaUtil.functionReport(r1, r2)
            com.jingdong.common.web.uibinder.IWebUiBinder r1 = r11.webUiBinder
            if (r1 == 0) goto Lbb
            com.jingdong.common.web.ui.JDWebView r1 = r1.getJdWebView()
            if (r1 == 0) goto Lbb
            org.json.JSONObject r1 = new org.json.JSONObject
            r1.<init>()
            r2 = 0
            com.jingdong.common.lbs.jdlocation.JDLocationCacheOption r4 = new com.jingdong.common.lbs.jdlocation.JDLocationCacheOption     // Catch: org.json.JSONException -> L89
            r4.<init>()     // Catch: org.json.JSONException -> L89
            java.lang.String r5 = "83c69c801bff4f6b245be7ec94db00c6"
            r4.setBusinessId(r5)     // Catch: org.json.JSONException -> L89
            com.jingdong.common.lbs.jdlocation.JDLocationCacheOption r4 = com.jingdong.common.web.util.WebUtils.getLBSOptionWithBaseSceneId(r4)     // Catch: org.json.JSONException -> L89
            com.jingdong.common.lbs.jdlocation.JDLocationCache r5 = com.jingdong.common.lbs.jdlocation.JDLocationCache.getInstance()     // Catch: org.json.JSONException -> L89
            com.jingdong.common.lbs.jdlocation.JDLocation r4 = r5.getLocation(r4)     // Catch: org.json.JSONException -> L89
            com.jingdong.common.web.uibinder.IWebUiBinder r5 = r11.webUiBinder     // Catch: org.json.JSONException -> L89
            com.jingdong.common.web.ui.JDWebView r5 = r5.getJdWebView()     // Catch: org.json.JSONException -> L89
            java.lang.String r6 = "JDFunction#getNALocation"
            com.jingdong.common.web.util.WebUtils.reportDeprecatedLBSBridge(r5, r6)     // Catch: org.json.JSONException -> L89
            if (r4 == 0) goto L4e
            double r5 = r4.getLat()     // Catch: org.json.JSONException -> L89
            double r2 = r4.getLng()     // Catch: org.json.JSONException -> L49
            r9 = r2
            r2 = r5
            r4 = r9
            goto L4f
        L49:
            r1 = move-exception
            r9 = r2
            r2 = r5
            r4 = r9
            goto L8b
        L4e:
            r4 = r2
        L4f:
            java.lang.String r6 = "latitude"
            r1.put(r6, r2)     // Catch: org.json.JSONException -> L87
            java.lang.String r6 = "longitude"
            r1.put(r6, r4)     // Catch: org.json.JSONException -> L87
            java.lang.String r6 = "type"
            java.lang.String r7 = "GCJ02"
            r1.put(r6, r7)     // Catch: org.json.JSONException -> L87
            com.jingdong.common.web.uibinder.IWebUiBinder r6 = r11.webUiBinder     // Catch: org.json.JSONException -> L87
            com.jingdong.common.web.ui.JDWebView r6 = r6.getJdWebView()     // Catch: org.json.JSONException -> L87
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: org.json.JSONException -> L87
            r7.<init>()     // Catch: org.json.JSONException -> L87
            java.lang.String r8 = "javascript:"
            r7.append(r8)     // Catch: org.json.JSONException -> L87
            r7.append(r12)     // Catch: org.json.JSONException -> L87
            r7.append(r0)     // Catch: org.json.JSONException -> L87
            r7.append(r1)     // Catch: org.json.JSONException -> L87
            java.lang.String r1 = ")"
            r7.append(r1)     // Catch: org.json.JSONException -> L87
            java.lang.String r1 = r7.toString()     // Catch: org.json.JSONException -> L87
            r6.injectJs(r1)     // Catch: org.json.JSONException -> L87
            goto L8e
        L87:
            r1 = move-exception
            goto L8b
        L89:
            r1 = move-exception
            r4 = r2
        L8b:
            r1.printStackTrace()
        L8e:
            boolean r1 = com.jingdong.corelib.utils.Log.D
            if (r1 == 0) goto Lbb
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r6 = " injectJs--> javascript:"
            r1.append(r6)
            r1.append(r12)
            r1.append(r0)
            r1.append(r4)
            java.lang.String r12 = ","
            r1.append(r12)
            r1.append(r2)
            java.lang.String r12 = ", GCJ02)"
            r1.append(r12)
            java.lang.String r12 = r1.toString()
            java.lang.String r0 = "JDFunction"
            com.jingdong.corelib.utils.Log.d(r0, r12)
        Lbb:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.web.javainterface.impl.JDFunction.getNALocation(java.lang.String):void");
    }

    @Override // com.jingdong.common.web.javainterface.IJavaInterface
    public String getName() {
        return WebUiConstans.JavaInterfaceNames.JDFUNCTION;
    }

    @JavascriptInterface
    public void scanMobikeQRCode() {
        WebUnifiedMtaUtil.functionReport(this.webUiBinder, "JDFunction_scanMobikeQRCode");
        if (this.webUiBinder.getJdWebView() != null) {
            Intent intent = new Intent();
            intent.setData(Uri.parse("openApp.jdMobile://virtual?params={\"category\":\"jump\",\"des\":\"native_scan\"}"));
            this.context.startActivity(intent);
            if (Log.D) {
                Log.d("JDFunction", " injectJs--> ");
            }
        }
    }

    @JavascriptInterface
    public void setJDUerId(String str) {
        WebUnifiedMtaUtil.functionReport(this.webUiBinder, "JDFunction_setJDUerId");
        this.jDUerId = str;
    }

    public JDFunction() {
    }
}
