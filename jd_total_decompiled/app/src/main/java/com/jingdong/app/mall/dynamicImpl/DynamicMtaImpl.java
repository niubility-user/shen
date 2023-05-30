package com.jingdong.app.mall.dynamicImpl;

import android.content.Context;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jd.dynamic.base.interfaces.IDynamicMta;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import java.util.HashMap;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class DynamicMtaImpl implements IDynamicMta {
    private Gson a = new Gson();

    private HashMap<String, String> a(JSONObject jSONObject) {
        if (jSONObject == null || jSONObject == null) {
            return null;
        }
        try {
            return (HashMap) this.a.fromJson(jSONObject.toString(), new TypeToken<HashMap<String, String>>(this) { // from class: com.jingdong.app.mall.dynamicImpl.DynamicMtaImpl.1
            }.getType());
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // com.jd.dynamic.base.interfaces.IDynamicMta
    public void clickMtaEvent(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        clickMtaEventWithExtParams(context, str, str2, str3, str4, str5, str6, str7, null);
    }

    @Override // com.jd.dynamic.base.interfaces.IDynamicMta
    public void clickMtaEventWithExtParams(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, JSONObject jSONObject) {
        JDMtaUtils.sendClickDataWithExt(context, str, str2, "", str3, str4, str5, str6, str7 == null ? "" : str7.toString(), "", "", "", "", a(jSONObject));
    }

    @Override // com.jd.dynamic.base.interfaces.IDynamicMta
    public void expoMtaEvent(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        expoMtaEventWithExtParams(context, str, str2, str3, str4, str5, str6, str7, null);
    }

    @Override // com.jd.dynamic.base.interfaces.IDynamicMta
    public void expoMtaEventWithExtParams(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, JSONObject jSONObject) {
        JDMtaUtils.sendExposureDataWithExt(context, str, str2, str3, str4, str5, str7 == null ? "" : str7.toString(), "", "", "", a(jSONObject));
    }

    @Override // com.jd.dynamic.base.interfaces.IDynamicMta
    public String getMtaValue(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        str.hashCode();
        char c2 = '\uffff';
        switch (str.hashCode()) {
            case 105063:
                if (str.equals("jda")) {
                    c2 = 0;
                    break;
                }
                break;
            case 105084:
                if (str.equals("jdv")) {
                    c2 = 1;
                    break;
                }
                break;
            case 3594837:
                if (str.equals("unpl")) {
                    c2 = 2;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                return JDMtaUtils.getJda();
            case 1:
                return JDMtaUtils.getJdv();
            case 2:
                return JDMtaUtils.getUnpl();
            default:
                return "";
        }
    }

    @Override // com.jd.dynamic.base.interfaces.IDynamicMta
    public void pvMtaEvent(Context context, Object obj, String str, String str2) {
        pvMtaEventWithExtParams(context, obj, str, str2, null);
    }

    @Override // com.jd.dynamic.base.interfaces.IDynamicMta
    public void pvMtaEventWithExtParams(Context context, Object obj, String str, String str2, JSONObject jSONObject) {
        JDMtaUtils.sendPagePv(context, obj, str, str2, "", "", "", "", a(jSONObject));
    }
}
