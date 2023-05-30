package com.jingdong.common.jdreactFramework.utils.apm;

import java.util.HashMap;

/* loaded from: classes5.dex */
public class JSExceptionEntity {
    private String BName;
    private String BVersion;
    private String RNSdkVersion;
    private String appCode;
    private String chId;
    private String chf;
    private String exceptionCode;
    private String exceptionStack;
    private String exceptionType;
    private String feedback;
    private String occurTime;
    private String pageInfo;
    private transient HashMap<String, String> paramsMap = new HashMap<>();
    private String typeId;

    public String getAppCode() {
        return this.appCode;
    }

    public String getBName() {
        return this.BName;
    }

    public String getBVersion() {
        return this.BVersion;
    }

    public String getChId() {
        return this.chId;
    }

    public String getChf() {
        return this.chf;
    }

    public String getExceptionCode() {
        return this.exceptionCode;
    }

    public String getExceptionStack() {
        return this.exceptionStack;
    }

    public String getExceptionType() {
        return this.exceptionType;
    }

    public String getFeedback() {
        return this.feedback;
    }

    public String getOccurTime() {
        return this.occurTime;
    }

    public String getPageInfo() {
        return this.pageInfo;
    }

    public HashMap<String, String> getParamsMap() {
        return this.paramsMap;
    }

    public String getRNSdkVersion() {
        return this.RNSdkVersion;
    }

    public String getTypeId() {
        return this.typeId;
    }

    public void setAppCode(String str) {
        this.appCode = str;
        this.paramsMap.put("appCode", str);
    }

    public void setBName(String str) {
        this.BName = str;
        this.paramsMap.put("BName", str);
    }

    public void setBVersion(String str) {
        this.BVersion = str;
        this.paramsMap.put("BVersion", str);
    }

    public void setChId(String str) {
        this.chId = str;
        this.paramsMap.put("chId", str);
    }

    public void setChf(String str) {
        this.chf = str;
        this.paramsMap.put("chf", str);
    }

    public void setExceptionCode(String str) {
        this.exceptionCode = str;
        this.paramsMap.put("exceptionCode", str);
    }

    public void setExceptionStack(String str) {
        this.exceptionStack = str;
        this.paramsMap.put("exceptionStack", str);
    }

    public void setExceptionType(String str) {
        this.exceptionType = str;
        this.paramsMap.put("exceptionType", str);
    }

    public void setFeedback(String str) {
        this.feedback = str;
        this.paramsMap.put("feedback", str);
    }

    public void setOccurTime(String str) {
        this.occurTime = str;
        this.paramsMap.put("occurTime", str);
    }

    public void setPageInfo(String str) {
        this.pageInfo = str;
        this.paramsMap.put("pageInfo", str);
    }

    public void setRNSdkVersion(String str) {
        this.RNSdkVersion = str;
        this.paramsMap.put("RNSdkVersion", str);
    }

    public void setTypeId(String str) {
        this.typeId = str;
        this.paramsMap.put("typeId", str);
    }

    public String toString() {
        return "JSExceptionEntity{paramsMap=" + this.paramsMap + ", feedback='" + this.feedback + "', chf='" + this.chf + "', occurTime='" + this.occurTime + "', exceptionType='" + this.exceptionType + "', exceptionCode='" + this.exceptionCode + "', exceptionStack='" + this.exceptionStack + "', pageInfo='" + this.pageInfo + "', RNSdkVersion='" + this.RNSdkVersion + "', BName='" + this.BName + "', BVersion='" + this.BVersion + "', appCode='" + this.appCode + "', typeId='" + this.typeId + "', chId='" + this.chId + "'}";
    }
}
