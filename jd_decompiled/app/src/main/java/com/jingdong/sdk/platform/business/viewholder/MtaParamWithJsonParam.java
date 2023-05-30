package com.jingdong.sdk.platform.business.viewholder;

import com.jingdong.sdk.platform.mta.MtaParams;

/* loaded from: classes10.dex */
public class MtaParamWithJsonParam extends MtaParams {
    public String jsonParam;

    public MtaParamWithJsonParam(String str, String str2, String str3, String str4, String str5) {
        super(str, str2, str3, str4);
        this.jsonParam = str5;
    }

    public MtaParamWithJsonParam(String str, String str2, String str3, String str4) {
        super(str, str2, str3);
        this.jsonParam = str4;
    }

    public MtaParamWithJsonParam(String str, String str2, String str3) {
        super(str, str2);
        this.jsonParam = str3;
    }

    public MtaParamWithJsonParam(String str, String str2) {
        super(str);
        this.jsonParam = str2;
    }
}
