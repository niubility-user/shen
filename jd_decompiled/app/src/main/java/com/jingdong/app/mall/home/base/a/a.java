package com.jingdong.app.mall.home.base.a;

import com.jingdong.jdsdk.network.toolbox.HttpSetting;

/* loaded from: classes4.dex */
public class a {
    public static HttpSetting a(String str) {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setReferer("Image_JDAppHome");
        httpSetting.setUrl(str);
        httpSetting.setEffect(0);
        return httpSetting;
    }
}
