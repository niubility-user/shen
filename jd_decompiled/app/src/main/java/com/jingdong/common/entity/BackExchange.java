package com.jingdong.common.entity;

import android.text.TextUtils;
import com.jingdong.jdsdk.utils.JSONArrayPoxy;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import org.json.JSONException;

/* loaded from: classes5.dex */
public class BackExchange {
    private static final String TAG = "BackExchange";
    public String action;
    public String description;
    public Integer group;
    public String icon;
    public String name;
    public String url;

    public BackExchange(JSONObjectProxy jSONObjectProxy) {
        this.name = jSONObjectProxy.getStringOrNull("name");
        this.url = jSONObjectProxy.getStringOrNull("url");
        this.icon = jSONObjectProxy.getStringOrNull("icon");
        this.description = jSONObjectProxy.getStringOrNull("description");
        this.group = jSONObjectProxy.getIntOrNull("group");
        this.action = jSONObjectProxy.getStringOrNull("functionId");
    }

    private static boolean isAdd(BackExchange backExchange) {
        return (TextUtils.isEmpty(backExchange.url) || TextUtils.isEmpty(backExchange.action)) ? false : true;
    }

    public static ArrayList<BackExchange> toList(JSONArrayPoxy jSONArrayPoxy) {
        if (jSONArrayPoxy == null || jSONArrayPoxy.length() < 1) {
            return null;
        }
        ArrayList<BackExchange> arrayList = new ArrayList<>();
        for (int i2 = 0; i2 < jSONArrayPoxy.length(); i2++) {
            try {
                BackExchange backExchange = new BackExchange(jSONArrayPoxy.getJSONObject(i2));
                if (isAdd(backExchange)) {
                    arrayList.add(backExchange);
                }
            } catch (JSONException e2) {
                OKLog.e(TAG, e2);
            }
        }
        return arrayList;
    }
}
