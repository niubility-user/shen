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
public class HomeIconModel implements Serializable {
    public static final String CLASS_NAME_AIRLINE = "AirLineActivity";
    public static final String CLASS_NAME_APPCENTER = "AppCenterActivity";
    public static final String CLASS_NAME_FAVOLIST = "JshopFavoListActivity";
    public static final String CLASS_NAME_LOTTERY = "LotteryActivity";
    public static final String CLASS_NAME_ORDERLIST = "MyOrderListActivity";
    public static final String CLASS_NAME_PHONECHARGE = "PhoneChargeActivity";
    public static final String CTYPE_NATIVE_RECHARGE = "1";
    public static final String TYPE_COLLECT = "7";
    public static final String TYPE_FUN_M = "2";
    public static final String TYPE_M = "5";
    public static final String TYPE_NATIVE = "100";
    public static final String TYPE_NATIVE_LOTTERY = "9";
    public static final String TYPE_ORDER_LIST = "10";
    public static final String TYPE_SIGN = "8";
    public static final String TYPE_URL = "101";
    public static boolean hasNativeLottery = false;
    private static final long serialVersionUID = -5296809390406251124L;
    public String cType;
    public String className;
    public String classParam;
    public boolean flag;
    public String foldFlag;
    public String functionId;
    public String icon;
    public String isShare;
    public String mTitle;
    private View.OnClickListener onClickListener;
    public String param;
    public String sourceValue;
    public String title;
    public String type;
    public String url;

    public HomeIconModel() {
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

    public static ArrayList<HomeIconModel> toList(JSONArrayPoxy jSONArrayPoxy, boolean z) {
        ArrayList<HomeIconModel> arrayList = null;
        if (jSONArrayPoxy == null) {
            return null;
        }
        if (z) {
            hasNativeLottery = false;
        }
        try {
            ArrayList<HomeIconModel> arrayList2 = new ArrayList<>();
            for (int i2 = 0; i2 < jSONArrayPoxy.length(); i2++) {
                try {
                    HomeIconModel homeIconModel = new HomeIconModel(jSONArrayPoxy.getJSONObject(i2));
                    if (z && "9".equals(homeIconModel.type)) {
                        hasNativeLottery = true;
                        if (OKLog.D) {
                            OKLog.d("HomeLayout", "hasNativeLottery:" + hasNativeLottery);
                        }
                    }
                    arrayList2.add(homeIconModel);
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

    public HomeIconModel(JSONObjectProxy jSONObjectProxy) {
        this.type = jSONObjectProxy.optString("type");
        this.title = jSONObjectProxy.optString("title");
        this.icon = jSONObjectProxy.optString("icon");
        this.url = jSONObjectProxy.optString("url");
        setFoldFlag(jSONObjectProxy.optString("foldFlag"));
        this.cType = jSONObjectProxy.optString("ctype");
        this.functionId = jSONObjectProxy.optString("functionId");
        this.sourceValue = jSONObjectProxy.optString("sourceValue");
        this.isShare = jSONObjectProxy.optString("isShare");
        this.mTitle = jSONObjectProxy.optString("mtitle");
        this.className = jSONObjectProxy.optString("className");
        this.param = jSONObjectProxy.optString("param");
        this.classParam = jSONObjectProxy.optString("classParam");
        stringToBoolean();
    }
}
