package com.jingdong.common.entity;

import android.text.TextUtils;
import android.view.View;
import com.jingdong.jdsdk.utils.JSONArrayPoxy;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.sdk.oklog.OKLog;
import java.io.Serializable;
import java.util.ArrayList;
import org.json.JSONException;

/* loaded from: classes5.dex */
public class HomeLayout implements Serializable {
    public static final String CTYPE_NATIVE_RECHARGE = "1";
    public static final String TYPE_COLLECT = "7";
    public static final String TYPE_M = "5";
    public static final String TYPE_NATIVE_LOTTERY = "9";
    public static final String TYPE_ORDER_LIST = "10";
    public static final String TYPE_SIGN = "8";
    public static boolean hasNativeLottery = false;
    private static final long serialVersionUID = -5296809390406251124L;
    public String cType;
    public boolean flag;
    public String foldFlag;
    public String functionId;
    public String icon;
    private View.OnClickListener onClickListener;
    public String title;
    public String type;
    public String url;

    public HomeLayout() {
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    private void stringToBoolean() {
        if (TextUtils.isEmpty(this.foldFlag)) {
            return;
        }
        this.flag = this.foldFlag.equals("1");
    }

    public static ArrayList<HomeLayout> toList(JSONArrayPoxy jSONArrayPoxy, boolean z) {
        ArrayList<HomeLayout> arrayList = null;
        if (jSONArrayPoxy == null) {
            return null;
        }
        if (z) {
            hasNativeLottery = false;
        }
        try {
            ArrayList<HomeLayout> arrayList2 = new ArrayList<>();
            for (int i2 = 0; i2 < jSONArrayPoxy.length(); i2++) {
                try {
                    HomeLayout homeLayout = new HomeLayout(jSONArrayPoxy.getJSONObject(i2));
                    if (z && "9".equals(homeLayout.type)) {
                        hasNativeLottery = true;
                        if (OKLog.D) {
                            OKLog.d("HomeLayout", "hasNativeLottery:" + hasNativeLottery);
                        }
                    }
                    arrayList2.add(homeLayout);
                } catch (JSONException e2) {
                    e = e2;
                    arrayList = arrayList2;
                    if (OKLog.V) {
                        OKLog.v("HomeLayout", e.getMessage());
                    }
                    return arrayList;
                }
            }
            return arrayList2;
        } catch (JSONException e3) {
            e = e3;
        }
    }

    public View.OnClickListener getOnClickListener() {
        return this.onClickListener;
    }

    public boolean isGoTONativeRecharge() {
        return "1".equals(this.cType);
    }

    public void setFoldFlag(String str) {
        this.foldFlag = str;
        stringToBoolean();
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public HomeLayout(JSONObjectProxy jSONObjectProxy) {
        this.type = jSONObjectProxy.getStringOrNull("type");
        this.title = jSONObjectProxy.getStringOrNull("title");
        this.icon = jSONObjectProxy.getStringOrNull("icon");
        this.url = jSONObjectProxy.getStringOrNull("url");
        this.foldFlag = jSONObjectProxy.getStringOrNull("foldFlag");
        this.functionId = jSONObjectProxy.getStringOrNull("functionId");
        this.cType = jSONObjectProxy.getStringOrNull("ctype");
        stringToBoolean();
    }
}
