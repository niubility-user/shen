package com.jingdong.common.entity;

import android.text.TextUtils;
import com.jingdong.jdsdk.utils.JSONArrayPoxy;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.sdk.oklog.OKLog;
import java.io.Serializable;
import java.util.ArrayList;
import org.json.JSONException;

/* loaded from: classes5.dex */
public class NewUserInfoLabelsMode implements Serializable {
    public static final int DIRECT_LABELS = 0;
    public static final String KEY_COUPON = "coupon";
    private static final String TAG = "NewUserInfoLabelsMode";
    private static final long serialVersionUID = 1359053837059263180L;
    public double amount;
    public String key;
    public String name;

    public NewUserInfoLabelsMode(JSONObjectProxy jSONObjectProxy, int i2, Object[] objArr) {
        if (i2 != 0) {
            return;
        }
        setKey(jSONObjectProxy.optString("key"));
        this.name = jSONObjectProxy.optString("name");
        this.amount = jSONObjectProxy.optDouble("amount", 0.0d);
    }

    public static ArrayList<NewUserInfoLabelsMode> toList(JSONArrayPoxy jSONArrayPoxy, int i2) {
        return toList(jSONArrayPoxy, i2, null);
    }

    public String getName() {
        if (TextUtils.isEmpty(this.name)) {
            this.name = "";
        }
        return this.name;
    }

    public void setKey(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        this.key = str;
    }

    public static ArrayList<NewUserInfoLabelsMode> toList(JSONArrayPoxy jSONArrayPoxy, int i2, Object[] objArr) {
        ArrayList<NewUserInfoLabelsMode> arrayList = new ArrayList<>();
        if (jSONArrayPoxy == null) {
            return arrayList;
        }
        for (int i3 = 0; i3 < jSONArrayPoxy.length(); i3++) {
            try {
                NewUserInfoLabelsMode newUserInfoLabelsMode = new NewUserInfoLabelsMode(jSONArrayPoxy.getJSONObject(i3), i2, objArr);
                if (!TextUtils.isEmpty(newUserInfoLabelsMode.key)) {
                    arrayList.add(newUserInfoLabelsMode);
                }
            } catch (JSONException e2) {
                OKLog.e(TAG, e2);
            }
        }
        return arrayList;
    }
}
