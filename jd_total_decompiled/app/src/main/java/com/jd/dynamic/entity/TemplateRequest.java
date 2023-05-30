package com.jd.dynamic.entity;

import android.text.TextUtils;
import com.jd.dynamic.base.DynamicFetcher;
import java.io.InputStream;
import java.util.concurrent.Future;

/* loaded from: classes13.dex */
public class TemplateRequest {
    private String bizField;
    private String businessCode;
    public DynamicFetcher.Listener listener;
    public String localTemp;
    public InputStream localTempStream;
    public String mtaId;
    public String systemCode;
    public Future task;
    public Template template;
    public String version;
    public boolean fromNet = false;
    public boolean isUseLowVersion = true;
    public boolean isProvideLowVersion = false;

    public TemplateRequest(String str, String str2, String str3, String str4, String str5, InputStream inputStream, DynamicFetcher.Listener listener) {
        this.mtaId = str;
        this.systemCode = str2;
        this.bizField = str3;
        this.businessCode = str4;
        this.localTemp = str5;
        this.localTempStream = inputStream;
        this.listener = listener;
    }

    public String getBizField() {
        Template template;
        if (TextUtils.isEmpty(this.bizField) && (template = this.template) != null) {
            this.bizField = template.bizField;
        }
        return this.bizField;
    }

    public String getBusinessCode() {
        Template template;
        if (TextUtils.isEmpty(this.businessCode) && (template = this.template) != null) {
            this.businessCode = template.businessCode;
        }
        return this.businessCode;
    }

    public String getVersion() {
        Template template;
        if (TextUtils.isEmpty(this.version) && (template = this.template) != null) {
            this.version = template.version;
        }
        return this.version;
    }

    public String getZipVersion() {
        Template template = this.template;
        return template != null ? template.pckVersion : "";
    }
}
