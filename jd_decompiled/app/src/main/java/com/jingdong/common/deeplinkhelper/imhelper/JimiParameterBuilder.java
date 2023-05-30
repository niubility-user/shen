package com.jingdong.common.deeplinkhelper.imhelper;

import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.common.login.LoginUserBase;

/* loaded from: classes5.dex */
public class JimiParameterBuilder {
    public static final String ACTION_BROADCAST_START_PLUGIN_JIMI = "com.jd.start.jd.plugin.jimi";
    public static final String SOURCE_MOBILE = "app";
    public static final String SOURCE_MOBILE_IM = "app_im";
    public static final String SOURCE_MOBILE_IM_ABSENT = "app_im_absent";
    public static final String SOURCE_MOBILE_KANJIA = "jdapp_product_kanjia";
    public static final String SOURCE_MOBILE_PRODUCT = "app_product";
    private String mAction;
    private String mPin;
    private String mSkuID;
    private String mSource;
    private String mVenderID;

    private JimiParameterBuilder(String str) {
        this.mPin = str;
    }

    public static JimiParameterBuilder convertFromWebParameter(Bundle bundle) {
        JimiParameterBuilder generateWithPin = generateWithPin();
        if (generateWithPin == null) {
            return null;
        }
        if (bundle == null) {
            return generateWithPin;
        }
        generateWithPin.addAction(bundle.getString("action"));
        generateWithPin.addSource(bundle.getString("source"));
        generateWithPin.addSkuID(bundle.getString("sku"));
        generateWithPin.addVenderID(bundle.getString("venderID"));
        return generateWithPin;
    }

    public static JimiParameterBuilder generateWithPin() {
        String loginUserName = LoginUserBase.getLoginUserName();
        if (TextUtils.isEmpty(loginUserName)) {
            return null;
        }
        return new JimiParameterBuilder(loginUserName);
    }

    public JimiParameterBuilder addAction(String str) {
        this.mAction = str;
        return this;
    }

    public JimiParameterBuilder addSkuID(String str) {
        this.mSkuID = str;
        return this;
    }

    public JimiParameterBuilder addSource(String str) {
        this.mSource = str;
        return this;
    }

    public JimiParameterBuilder addVenderID(String str) {
        this.mVenderID = str;
        return this;
    }

    public Bundle getAllParameters() {
        Bundle bundle = new Bundle();
        String str = this.mAction;
        if (str != null) {
            bundle.putString("action", str);
        }
        String str2 = this.mSource;
        if (str2 != null) {
            bundle.putString("source", str2);
        }
        String str3 = this.mPin;
        if (str3 != null) {
            bundle.putString("pin", str3);
        }
        String str4 = this.mSkuID;
        if (str4 != null) {
            bundle.putString("sku", str4);
        }
        String str5 = this.mVenderID;
        if (str5 != null) {
            bundle.putString("venderID", str5);
        }
        return bundle;
    }

    public String toString() {
        return "action=" + this.mAction + ", source=" + this.mSource + ", pin=" + this.mPin + ", skuID=" + this.mSkuID + ", venderID=" + this.mVenderID;
    }
}
