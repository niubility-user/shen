package com.jingdong.common.entity;

import android.text.TextUtils;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.sdk.oklog.OKLog;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes5.dex */
public class CompleteOrderContinue implements Serializable {
    public static final int M_URL = 2;
    public static final int NATIVE = 1;
    public static final int ORDER_CONTINE_TYPE_COUPON_CENTER = 102;
    public static final int ORDER_CONTINUE_TYPE_ALL_M = 10;
    public static final int ORDER_CONTINUE_TYPE_CHECK_ORDER = 1;
    public static final int ORDER_CONTINUE_TYPE_GUANG = 5;
    public static final int ORDER_CONTINUE_TYPE_LOTTERY = 3;
    public static final int ORDER_CONTINUE_TYPE_ORDER_TRACK = 2;
    public static final int ORDER_CONTINUE_TYPE_PHONE_VIP = 4;
    public static final int ORDER_CONTINUE_TYPE_RECHARGE = 8;
    public static final int ORDER_CONTINUE_TYPE_SECKILL = 7;
    public static final int ORDER_CONTINUE_TYPE_STORY = 101;
    public static final int ORDER_CONTINUE_TYPE_TO_SHOPPING = 6;
    private static final long serialVersionUID = 888581604789906548L;
    public int id;
    public String img;
    public String imgBig;
    public String jumpData;
    public String name;
    public String sourceValue;
    public String subType;
    public int type;
    public String url;

    public CompleteOrderContinue() {
    }

    public static ArrayList<CompleteOrderContinue> lookupAllMUrl(ArrayList<CompleteOrderContinue> arrayList) {
        ArrayList<CompleteOrderContinue> arrayList2 = new ArrayList<>();
        if (arrayList == null) {
            return arrayList2;
        }
        try {
            Iterator<CompleteOrderContinue> it = arrayList.iterator();
            while (it.hasNext()) {
                CompleteOrderContinue next = it.next();
                if (next != null && next.type == 2) {
                    arrayList2.add(next);
                }
            }
        } catch (Exception e2) {
            if (OKLog.V) {
                OKLog.v("Comment", "JSONException -->> ", e2);
            }
        }
        return arrayList2;
    }

    public static ArrayList<CompleteOrderContinue> lookupAllNative(ArrayList<CompleteOrderContinue> arrayList) {
        ArrayList<CompleteOrderContinue> arrayList2 = new ArrayList<>();
        if (arrayList == null) {
            return arrayList2;
        }
        try {
            Iterator<CompleteOrderContinue> it = arrayList.iterator();
            while (it.hasNext()) {
                CompleteOrderContinue next = it.next();
                if (next != null) {
                    arrayList2.add(next);
                }
            }
        } catch (Exception e2) {
            if (OKLog.V) {
                OKLog.v("Comment", "JSONException -->> ", e2);
            }
        }
        return arrayList2;
    }

    public static ArrayList<CompleteOrderContinue> toList(JDJSONArray jDJSONArray) {
        ArrayList<CompleteOrderContinue> arrayList = new ArrayList<>();
        if (jDJSONArray == null) {
            return arrayList;
        }
        try {
            int size = jDJSONArray.size();
            for (int i2 = 0; i2 < size; i2++) {
                arrayList.add(new CompleteOrderContinue(jDJSONArray.getJSONObject(i2)));
            }
        } catch (Exception e2) {
            if (OKLog.V) {
                OKLog.v("Comment", "JSONException -->> ", e2);
            }
        }
        return arrayList;
    }

    private void update(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null) {
            return;
        }
        this.id = jDJSONObject.optInt("id");
        this.type = jDJSONObject.optInt("type");
        this.name = jDJSONObject.optString("name");
        this.img = jDJSONObject.optString("img");
        this.imgBig = jDJSONObject.optString("imgBig");
        this.url = jDJSONObject.optString("url");
        this.sourceValue = jDJSONObject.optString("sourceValue");
        this.subType = jDJSONObject.optString("subType");
        this.jumpData = jDJSONObject.optString("jumpData");
    }

    public String getImg() {
        return TextUtils.isEmpty(this.img) ? "" : this.img;
    }

    public String getJumpData() {
        return TextUtils.isEmpty(this.jumpData) ? "" : this.jumpData;
    }

    public String getName() {
        return TextUtils.isEmpty(this.name) ? "" : this.name;
    }

    public String getSourceValue() {
        return TextUtils.isEmpty(this.sourceValue) ? "" : this.sourceValue;
    }

    public String getSubType() {
        return TextUtils.isEmpty(this.subType) ? "" : this.subType;
    }

    public String getUrl() {
        return TextUtils.isEmpty(this.url) ? "" : this.url;
    }

    public CompleteOrderContinue(JDJSONObject jDJSONObject) {
        update(jDJSONObject);
    }
}
