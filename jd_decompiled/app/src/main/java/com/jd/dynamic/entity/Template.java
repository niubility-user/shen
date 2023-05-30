package com.jd.dynamic.entity;

import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import java.io.Serializable;

/* loaded from: classes13.dex */
public class Template implements Serializable {
    private static final long serialVersionUID = 102;
    public String bizField;
    public String businessCode;
    public String businessName;
    private long enterTime;
    private String enterUUID;
    public String fileMd5;
    public String fileObjectKey;
    public String fullFileUrl;
    public String isGray;
    public boolean isNewApi;
    public boolean isUseJs;
    public boolean isUseZip;
    public String pckVersion;
    public String systemCode;
    public String templateId;
    public String version;
    public String zipFileMd5;
    public String zipFileUrl;

    public Template() {
        this.fullFileUrl = "";
        this.isGray = "0";
        this.isNewApi = false;
        this.isUseJs = false;
        this.isUseZip = false;
        this.enterTime = 0L;
        this.enterUUID = "";
    }

    public Template(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10) {
        this.fullFileUrl = "";
        this.isGray = "0";
        this.isNewApi = false;
        this.isUseJs = false;
        this.isUseZip = false;
        this.enterTime = 0L;
        this.enterUUID = "";
        this.templateId = str;
        this.bizField = str2;
        this.businessCode = str3;
        this.businessName = str4;
        this.version = str5;
        this.fileMd5 = str6;
        this.fileObjectKey = str7;
        this.fullFileUrl = str8;
        this.zipFileMd5 = str9;
        this.zipFileUrl = str10;
    }

    public static Template create(TemplateRequest templateRequest) {
        Template template = new Template();
        if (templateRequest != null) {
            template.systemCode = templateRequest.systemCode;
            template.bizField = templateRequest.getBizField();
            template.businessCode = templateRequest.getBusinessCode();
        }
        return template;
    }

    public static Template create(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        Template template = new Template();
        template.templateId = str;
        template.bizField = str2;
        template.businessCode = str3;
        template.businessName = str4;
        template.version = str5;
        template.fileMd5 = str6;
        template.fileObjectKey = str7;
        template.fullFileUrl = str8;
        return template;
    }

    public String getDownloadFileName() {
        return TextUtils.isEmpty(this.zipFileUrl) ? this.fileObjectKey : this.pckVersion;
    }

    public String getDownloadUrl() {
        return TextUtils.isEmpty(this.zipFileUrl) ? this.fullFileUrl : this.zipFileUrl;
    }

    public long getEnterTime() {
        return this.enterTime;
    }

    public String getEnterUUID() {
        return this.enterUUID;
    }

    public String getMd5() {
        return TextUtils.isEmpty(this.zipFileUrl) ? this.fileMd5 : this.zipFileMd5;
    }

    public String getTemplateType() {
        return this.isUseJs ? "zip-js" : (isDownloadZip() || this.isUseZip) ? "zip" : !TextUtils.isEmpty(this.fullFileUrl) ? "bin" : "unknown";
    }

    public boolean isDownloadZip() {
        return !TextUtils.isEmpty(this.zipFileUrl);
    }

    public boolean isNewValid() {
        return (TextUtils.isEmpty(this.templateId) || TextUtils.isEmpty(this.businessCode) || TextUtils.isEmpty(this.businessName) || TextUtils.isEmpty(this.version) || ((TextUtils.isEmpty(this.fileObjectKey) || TextUtils.isEmpty(this.fullFileUrl)) && TextUtils.isEmpty(this.zipFileUrl))) ? false : true;
    }

    public boolean isValid() {
        return (TextUtils.isEmpty(this.templateId) || TextUtils.isEmpty(this.businessCode) || TextUtils.isEmpty(this.businessName) || TextUtils.isEmpty(this.version) || TextUtils.isEmpty(this.fileObjectKey) || TextUtils.isEmpty(this.fullFileUrl)) ? false : true;
    }

    public void setEnterTime(long j2) {
        this.enterTime = j2;
    }

    public void setEnterUUID(String str) {
        this.enterUUID = str;
    }

    public String toString() {
        return "[" + this.templateId + DYConstants.DY_REGEX_COMMA + this.bizField + DYConstants.DY_REGEX_COMMA + this.businessCode + DYConstants.DY_REGEX_COMMA + this.businessName + DYConstants.DY_REGEX_COMMA + this.version + DYConstants.DY_REGEX_COMMA + this.fileMd5 + DYConstants.DY_REGEX_COMMA + this.fileObjectKey + DYConstants.DY_REGEX_COMMA + this.fullFileUrl + DYConstants.DY_REGEX_COMMA + this.zipFileMd5 + DYConstants.DY_REGEX_COMMA + this.zipFileUrl + "]";
    }
}
