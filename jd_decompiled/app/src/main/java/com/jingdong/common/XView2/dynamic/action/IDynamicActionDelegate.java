package com.jingdong.common.XView2.dynamic.action;

import android.content.Context;
import android.view.View;
import com.jd.framework.json.JDJSONObject;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public interface IDynamicActionDelegate {
    void requestFunc(String str, JSONObject jSONObject, JSONObject jSONObject2);

    void requestUrl(String str, JDJSONObject jDJSONObject, JDJSONObject jDJSONObject2);

    void showDialogTitle(String str, String str2, String str3, String str4, View.OnClickListener onClickListener, View.OnClickListener onClickListener2);

    void showToastTitle(Context context, String str, String str2, int i2);
}
