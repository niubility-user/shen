package com.jingdong.jdsdk.network.db.entry;

import android.text.TextUtils;
import com.jingdong.jdsdk.config.Configuration;

/* loaded from: classes14.dex */
public class UnExcuteFunction {
    public static final int FALSE = 0;
    public static final int TRUE = 1;
    private String callBack;
    private String functionId;
    private String host;
    private int id;
    private int ifNeedLodingModel;
    private int ifNeedNotifyUser;
    private String jsonParams;
    private String md5;

    public UnExcuteFunction(int i2, String str, int i3, int i4, String str2, String str3, String str4, String str5) {
        this.id = i2;
        this.functionId = str;
        this.ifNeedNotifyUser = i3;
        this.ifNeedLodingModel = i4;
        this.jsonParams = str2;
        this.callBack = str3;
        this.host = str4;
        this.md5 = str5;
    }

    public String getCallBack() {
        String str = this.callBack;
        return str == null ? "" : str;
    }

    public String getFunctionId() {
        String str = this.functionId;
        return str == null ? "" : str;
    }

    public String getHost() {
        if (TextUtils.isEmpty(this.host)) {
            this.host = Configuration.getProperty("host");
        }
        return this.host;
    }

    public int getId() {
        return this.id;
    }

    public int getIfNeedLodingModel() {
        return this.ifNeedLodingModel;
    }

    public int getIfNeedNotifyUser() {
        return this.ifNeedNotifyUser;
    }

    public String getJsonParams() {
        String str = this.jsonParams;
        return str == null ? "" : str;
    }

    public String getMd5() {
        return this.md5;
    }

    public boolean isIfNeedLodingModel() {
        return this.ifNeedLodingModel == 1;
    }

    public boolean isIfNeedNotifyUser() {
        return this.ifNeedNotifyUser == 1;
    }

    public void setCallBack(String str) {
        this.callBack = str;
    }

    public void setFunctionId(String str) {
        this.functionId = str;
    }

    public void setHost(String str) {
        this.host = str;
    }

    public void setId(int i2) {
        this.id = i2;
    }

    public void setIfNeedLodingModel(int i2) {
        this.ifNeedLodingModel = i2;
    }

    public void setIfNeedNotifyUser(int i2) {
        this.ifNeedNotifyUser = i2;
    }

    public void setJsonParams(String str) {
        this.jsonParams = str;
    }

    public void setMd5(String str) {
        this.md5 = str;
    }

    public UnExcuteFunction() {
    }
}
