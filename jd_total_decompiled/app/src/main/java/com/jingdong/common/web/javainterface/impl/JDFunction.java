package com.jingdong.common.web.javainterface.impl;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.webkit.JavascriptInterface;
import com.jd.dynamic.DYConstants;
import com.jd.lib.productdetail.core.protocol.PdLVBody;
import com.jingdong.common.lbs.jdlocation.JDLocation;
import com.jingdong.common.lbs.jdlocation.JDLocationCache;
import com.jingdong.common.lbs.jdlocation.JDLocationCacheOption;
import com.jingdong.common.web.BaseWebComponent;
import com.jingdong.common.web.WebConstants;
import com.jingdong.common.web.WebUiConstans;
import com.jingdong.common.web.javainterface.IJavaInterface;
import com.jingdong.common.web.uibinder.IWebUiBinder;
import com.jingdong.common.web.util.WebUnifiedMtaUtil;
import com.jingdong.common.web.util.WebUtils;
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
    @JavascriptInterface
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void getNALocation(String str) {
        double d;
        WebUnifiedMtaUtil.functionReport(this.webUiBinder, "JDFunction_getNALocation");
        IWebUiBinder iWebUiBinder = this.webUiBinder;
        if (iWebUiBinder == null || iWebUiBinder.getJdWebView() == null) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        double d2 = 0.0d;
        try {
            JDLocationCacheOption jDLocationCacheOption = new JDLocationCacheOption();
            jDLocationCacheOption.setBusinessId(WebConstants.LBS_ID);
            JDLocation location = JDLocationCache.getInstance().getLocation(WebUtils.getLBSOptionWithBaseSceneId(jDLocationCacheOption));
            WebUtils.reportDeprecatedLBSBridge(this.webUiBinder.getJdWebView(), "JDFunction#getNALocation");
            if (location != null) {
                double lat = location.getLat();
                try {
                    d2 = lat;
                    d = location.getLng();
                } catch (JSONException e2) {
                    e = e2;
                    d2 = lat;
                    d = 0.0d;
                    e.printStackTrace();
                    if (Log.D) {
                    }
                }
            } else {
                d = 0.0d;
            }
        } catch (JSONException e3) {
            e = e3;
            d = 0.0d;
        }
        try {
            jSONObject.put(PdLVBody.LATITUDE, d2);
            jSONObject.put(PdLVBody.LONGITUDE, d);
            jSONObject.put("type", "GCJ02");
            this.webUiBinder.getJdWebView().injectJs("javascript:" + str + "(" + jSONObject + ")");
        } catch (JSONException e4) {
            e = e4;
            e.printStackTrace();
            if (Log.D) {
            }
        }
        if (Log.D) {
            Log.d("JDFunction", " injectJs--> javascript:" + str + "(" + d + DYConstants.DY_REGEX_COMMA + d2 + ", GCJ02)");
        }
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
