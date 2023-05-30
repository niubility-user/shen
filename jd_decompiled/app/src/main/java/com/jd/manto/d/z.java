package com.jd.manto.d;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.jd.manto.navigate.NavigateProxyActivity;
import com.jingdong.manto.jsapi.refact.JsApiScanCode;
import com.jingdong.manto.sdk.api.INavigate;
import com.jingdong.sdk.oklog.OKLog;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes17.dex */
public class z implements INavigate {

    /* renamed from: g  reason: collision with root package name */
    public static final String f6675g = z.class.toString();

    public static boolean a(String str) {
        try {
            return Uri.parse(str).getScheme().equalsIgnoreCase("openapp.jdmobile");
        } catch (Exception unused) {
            return false;
        }
    }

    @Override // com.jingdong.manto.sdk.api.INavigate
    public void navigateTo(Context context, String str) {
        try {
            String trim = new JSONObject(str).optString("url").trim();
            if (TextUtils.isEmpty(trim)) {
                if (OKLog.D) {
                    OKLog.d(f6675g, "=> url is empty");
                }
            } else if (Pattern.compile(JsApiScanCode.REGEX).matcher(trim).matches()) {
                NavigateProxyActivity.A(context, trim);
            } else if (OKLog.D) {
                OKLog.d(f6675g, "=> not openapp string");
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
            if (OKLog.D) {
                OKLog.d(f6675g, "=> JSONException" + e2.getMessage());
            }
        }
    }
}
