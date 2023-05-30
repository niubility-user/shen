package com.jingdong.manto.jsapi.refact;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.jsapi.openmodule.AbstractMantoModule;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.jsapi.openmodule.JsApiMethod;
import com.jingdong.manto.jsapi.openmodule.MantoResultCallBack;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class JsApiScanCode extends AbstractMantoModule {
    public static final String JSAPI_NAME = "scanCode";
    private static final String MODULE = "ScanCode";
    public static final String REGEX = "openapp\\.jdmobile://virtual\\?params=([\\s\\S]+)";
    public static final int REQUEST_CODE = 65535;

    public Bundle OnResult(Intent intent, int i2) {
        String str;
        Bundle bundle = new Bundle();
        if (i2 != -1) {
            str = i2 != 0 ? "0" : "-1";
        } else {
            String stringExtra = intent.getStringExtra("result");
            String stringExtra2 = intent.getStringExtra("type");
            bundle.putString("charSet", "utf-8");
            if (TextUtils.isEmpty(stringExtra)) {
                stringExtra = "";
            } else {
                Matcher matcher = Pattern.compile(REGEX).matcher(stringExtra);
                if (matcher.matches()) {
                    try {
                        new JSONObject(matcher.group(1)).optString("appId").trim();
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                }
            }
            bundle.putString("result", stringExtra);
            bundle.putString("rawData", Base64.encodeToString(stringExtra.getBytes(), 0));
            bundle.putString("scanType", stringExtra2);
            str = "1";
        }
        bundle.putString(IMantoBaseModule.ERROR_CODE, str);
        return bundle;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public final String getModuleName() {
        return MODULE;
    }

    public int getRequestCode() {
        return 65535;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule, com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public final void handleMethod(String str, MantoCore mantoCore, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
        scan(mantoCore.getActivity(), bundle, mantoResultCallBack);
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule, com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public final Bundle handleMethodSync(String str, MantoCore mantoCore, Bundle bundle) {
        return new Bundle();
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule, com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public final Bundle handleResult(String str, MantoCore mantoCore, Intent intent, int i2, int i3) {
        return i3 == getRequestCode() ? OnResult(intent, i2) : super.handleResult(str, mantoCore, intent, i2, i3);
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public final Bundle initData(String str, MantoCore mantoCore, JSONObject jSONObject) {
        String[] strArr;
        JSONArray optJSONArray = jSONObject.optJSONArray("scanType");
        if (optJSONArray != null) {
            ArrayList arrayList = new ArrayList();
            for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                arrayList.add(optJSONArray.optString(i2));
            }
            strArr = (String[]) arrayList.toArray(new String[arrayList.size()]);
        } else {
            strArr = null;
        }
        boolean optBoolean = jSONObject.optBoolean("onlyFromCamera", false);
        Bundle bundle = new Bundle();
        bundle.putStringArray("scanType", strArr);
        bundle.putBoolean("onlyFromCamera", optBoolean);
        bundle.putInt("requestCode", 65535);
        return bundle;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule
    protected final void injectJsApiMethod(List<JsApiMethod> list) {
        list.add(new JsApiMethod(JSAPI_NAME, 2));
    }

    public void scan(Activity activity, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
    }
}
