package com.jingdong.sdk.lib.puppetlayout.ylayout.model;

import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.sdk.lib.puppetlayout.g.b;
import com.jingdong.sdk.lib.puppetlayout.ylayout.DynamicHelper;
import com.jingdong.sdk.lib.puppetlayout.ylayout.ParentHandler;
import com.jingdong.sdk.lib.puppetlayout.ylayout.PuppetContext;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes8.dex */
public class Action implements Cloneable {
    public static final String ACTION_CLIENT_ANDROID = "android";
    public static final String ActionType_Anchor = "Anchor";
    public static final String ActionType_BottomPop = "BottomPop";
    public static final String ActionType_COMMON = "Action";
    public static final String ActionType_COUNTDOWN = "Countdown";
    public static final String ActionType_CallBack = "CallBack";
    public static final String ActionType_EXPO = "Exposure";
    public static final String ActionType_H5 = "H5";
    public static final String ActionType_None = "None";
    public static final String ActionType_ORDER = "Order";
    public static final String ActionType_OpenApp = "OpenApp";
    public static final String ActionType_Point = "Point";
    public static final String ActionType_Pop = "Pop";
    public static final String ActionType_Request = "Request";
    public static final String ActionType_Router = "Router";
    public static final String PARAM_KEY_TYPE = "actionType";
    public static final String PARAM_KEY_URL = "url";
    public String key = ActionType_None;
    public String value = null;
    public HashMap<String, String> cachedDynamicValues = null;
    public HashMap<String, String> params = new HashMap<>();
    private HashMap<String, String> dynamicParams = new HashMap<>();
    private DynamicHelper dynamicHelper = new DynamicHelper();
    public String type = null;
    public int actionType = 0;

    private HashMap<String, String> cloneMap(HashMap<String, String> hashMap) {
        HashMap<String, String> hashMap2 = new HashMap<>();
        if (hashMap != null && hashMap.size() != 0) {
            for (Map.Entry<String, String> entry : hashMap.entrySet()) {
                if (entry != null && entry.getKey() != null) {
                    hashMap2.put(entry.getKey(), entry.getValue());
                }
            }
        }
        return hashMap2;
    }

    public void compute(PuppetContext puppetContext, JDJSONObject jDJSONObject) {
        String valueFromData;
        ParentHandler parentHandler;
        if (this.dynamicParams.size() == 0) {
            return;
        }
        for (Map.Entry<String, String> entry : this.dynamicParams.entrySet()) {
            if (entry != null && entry.getValue() != null) {
                String value = entry.getValue();
                HashMap hashMap = new HashMap();
                for (String str : DynamicHelper.getDynamicList(null, value)) {
                    if (str.startsWith("parent.") && (parentHandler = puppetContext.parentHandler) != null) {
                        try {
                            valueFromData = parentHandler.getRealValue(str);
                        } catch (Exception e2) {
                            if (b.a) {
                                e2.printStackTrace();
                            }
                            valueFromData = null;
                        }
                    } else {
                        valueFromData = DynamicHelper.getValueFromData(puppetContext, str, jDJSONObject);
                    }
                    if (valueFromData != null) {
                        hashMap.put(str, valueFromData);
                    }
                }
                if (hashMap.size() > 0) {
                    this.params.put(entry.getKey(), DynamicHelper.replaceAllValue(value, hashMap));
                }
            }
        }
    }

    public String getActionType() {
        HashMap<String, String> hashMap = this.params;
        return hashMap != null ? hashMap.get("actionType") : "";
    }

    public String getHiddenType() {
        HashMap<String, String> hashMap = this.params;
        return (hashMap == null || hashMap.size() <= 0) ? "" : this.params.get("hiddenType");
    }

    public boolean isClientAndroid() {
        HashMap<String, String> hashMap = this.params;
        return hashMap == null || TextUtils.isEmpty(hashMap.get("client")) || "android".equals(this.params.get("client"));
    }

    public boolean isCountdownType() {
        HashMap<String, String> hashMap = this.params;
        return hashMap != null && "Countdown".equals(hashMap.get("actionType"));
    }

    public boolean isExpoType() {
        HashMap<String, String> hashMap = this.params;
        return hashMap != null && ActionType_EXPO.equals(hashMap.get("actionType"));
    }

    public void parse(String str, String str2, String str3) {
        if (DynamicHelper.isDynamic(str)) {
            this.dynamicParams.put("actionType", str);
        } else {
            this.params.put("actionType", str);
        }
        if (str3 != null) {
            this.params.put("client", str3);
        }
        String[] split = str2.split(DYConstants.DY_REGEX_COMMA);
        if (split.length < 2) {
            if (split.length == 1) {
                String[] split2 = split[0].split(":", 2);
                if (split2.length != 2 || TextUtils.isEmpty(split2[0]) || TextUtils.isEmpty(split2[1])) {
                    return;
                }
                if (DynamicHelper.isDynamic(split2[1])) {
                    this.dynamicParams.put(split2[0], split2[1]);
                    return;
                } else {
                    this.params.put(split2[0], split2[1]);
                    return;
                }
            }
            return;
        }
        for (String str4 : split) {
            if (!TextUtils.isEmpty(str4)) {
                String[] split3 = str4.split(":", 2);
                if (split3.length == 2 && !TextUtils.isEmpty(split3[0]) && !TextUtils.isEmpty(split3[1])) {
                    if (DynamicHelper.isDynamic(split3[1])) {
                        this.dynamicParams.put(split3[0], split3[1]);
                    } else {
                        this.params.put(split3[0], split3[1]);
                    }
                }
            }
        }
    }

    /* renamed from: clone */
    public Action m25clone() {
        Action action;
        CloneNotSupportedException e2;
        try {
            action = (Action) super.clone();
            try {
                action.params = cloneMap(this.params);
                action.dynamicParams = cloneMap(this.dynamicParams);
            } catch (CloneNotSupportedException e3) {
                e2 = e3;
                if (b.a) {
                    e2.printStackTrace();
                }
                return action;
            }
        } catch (CloneNotSupportedException e4) {
            action = null;
            e2 = e4;
        }
        return action;
    }
}
