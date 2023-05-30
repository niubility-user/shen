package com.jingdong.union.dependency;

import android.content.Context;
import android.os.Bundle;
import java.util.HashMap;

/* loaded from: classes12.dex */
public class b implements IAdvertUtils, IJumpDispatchCallBack, ILoginUser, IMtaUtils, IUnionExceptionReport, IWebUa, IOaid, IJdAdvertUtils, IUuid, IAndroidId, IDensity {
    @Override // com.jingdong.union.dependency.IAndroidId
    public String getAndroidId() {
        return "";
    }

    @Override // com.jingdong.union.dependency.IDensity
    public float getDensity() {
        return 2.0f;
    }

    @Override // com.jingdong.union.dependency.IUuid
    public String getEufv() {
        return "";
    }

    @Override // com.jingdong.union.dependency.base.IBaseAdvertUtils
    public String getJda() {
        return null;
    }

    @Override // com.jingdong.union.dependency.base.IBaseAdvertUtils
    public String getJdv() {
        return null;
    }

    @Override // com.jingdong.union.dependency.IOaid
    public String getOaid() {
        return "";
    }

    @Override // com.jingdong.union.dependency.ILoginUser
    public String getPin() {
        return null;
    }

    @Override // com.jingdong.union.dependency.IJdAdvertUtils
    public String getSe() {
        return null;
    }

    @Override // com.jingdong.union.dependency.IWebUa
    public String getUa() {
        return null;
    }

    @Override // com.jingdong.union.dependency.base.IBaseAdvertUtils
    public String getUnpl() {
        return null;
    }

    @Override // com.jingdong.union.dependency.IUuid
    public String getUuid() {
        return "";
    }

    @Override // com.jingdong.union.dependency.IJumpDispatchCallBack
    public void onDispatch(Context context, String str, String str2, Bundle bundle, String str3) {
    }

    @Override // com.jingdong.union.dependency.IJumpDispatchCallBack
    public void onFaile(Context context, String str) {
    }

    @Override // com.jingdong.union.dependency.IUnionExceptionReport
    public void report(Context context, HashMap<String, String> hashMap) {
    }

    @Override // com.jingdong.union.dependency.IMtaUtils
    public void sendCommonData(Context context, String str, String str2, String str3, Object obj, String str4, String str5, String str6, Bundle bundle) {
    }

    @Override // com.jingdong.union.dependency.base.IBaseAdvertUtils
    public void setJda(String str) {
    }

    @Override // com.jingdong.union.dependency.base.IBaseAdvertUtils
    public void setJdv(String str) {
    }

    @Override // com.jingdong.union.dependency.IAdvertUtils
    public void setSiUnpl(String str) {
    }

    @Override // com.jingdong.union.dependency.IAdvertUtils
    public void setUnpl(String str) {
    }

    @Override // com.jingdong.union.dependency.IJdAdvertUtils
    public void setUnplJdaJdv(String str, String str2, String str3, int i2) {
    }
}
