package com.jingdong.common;

import android.text.TextUtils;
import androidx.annotation.Keep;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jd.xbridge.base.IBridgeCallback;
import com.jd.xbridge.base.IBridgePlugin;
import com.jd.xbridge.base.IBridgeWebView;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.corelib.utils.Log;

@Keep
/* loaded from: classes5.dex */
public class SwitchQueryBridge implements IBridgePlugin {
    @Override // com.jd.xbridge.base.IBridgePlugin
    public boolean execute(IBridgeWebView iBridgeWebView, String str, String str2, IBridgeCallback iBridgeCallback) {
        str.hashCode();
        if (str.equals("getValue")) {
            String value = getValue(str2);
            if (TextUtils.isEmpty(value)) {
                iBridgeCallback.onError("please check your params !");
                return true;
            }
            iBridgeCallback.onSuccess(value);
            return true;
        }
        return false;
    }

    public String getValue(String str) {
        JDJSONObject jDJSONObject;
        String str2;
        Log.d("SwitchQueryBridge", "getValue:" + str);
        String str3 = null;
        try {
            jDJSONObject = JDJSON.parseObject(str);
        } catch (Exception unused) {
            jDJSONObject = null;
            str2 = null;
        }
        if (jDJSONObject != null) {
            try {
                str2 = jDJSONObject.containsKey("type") ? jDJSONObject.getString("type") : null;
                try {
                    if (jDJSONObject.containsKey("key")) {
                        str3 = jDJSONObject.getString("key");
                    }
                } catch (Exception unused2) {
                }
            } catch (Exception unused3) {
            }
            if (!TextUtils.isEmpty(str3) || TextUtils.isEmpty(str2)) {
                return "";
            }
            String string = jDJSONObject.containsKey("defaultValue") ? jDJSONObject.getString("defaultValue") : "";
            str2.hashCode();
            char c2 = '\uffff';
            int i2 = 0;
            switch (str2.hashCode()) {
                case -891985903:
                    if (str2.equals("string")) {
                        c2 = 0;
                        break;
                    }
                    break;
                case 104431:
                    if (str2.equals("int")) {
                        c2 = 1;
                        break;
                    }
                    break;
                case 64711720:
                    if (str2.equals("boolean")) {
                        c2 = 2;
                        break;
                    }
                    break;
            }
            switch (c2) {
                case 0:
                    return SwitchQueryFetcher.getSwitchStringValue(str3, string);
                case 1:
                    try {
                        i2 = Integer.parseInt(string);
                    } catch (Exception unused4) {
                    }
                    return String.valueOf(SwitchQueryFetcher.getSwitchIntValue(str3, i2));
                case 2:
                    return SwitchQueryFetcher.getSwitchBooleanValue(str3, "1".equals(string)) ? "1" : "0";
                default:
                    return "";
            }
        }
        str2 = null;
        if (TextUtils.isEmpty(str3)) {
        }
        return "";
    }
}
