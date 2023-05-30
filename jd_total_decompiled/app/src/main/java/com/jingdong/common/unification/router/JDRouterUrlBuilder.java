package com.jingdong.common.unification.router;

import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;

/* loaded from: classes6.dex */
public class JDRouterUrlBuilder {
    private Bundle bundle;
    private String defaultMethodName;
    private String moduleName;

    public JDRouterUrlBuilder() {
        this.defaultMethodName = "show";
        this.bundle = new Bundle();
    }

    public String build() {
        if (TextUtils.isEmpty(this.moduleName)) {
            return "";
        }
        StringBuilder sb = new StringBuilder("router://");
        sb.append(this.moduleName);
        sb.append("/");
        sb.append(this.defaultMethodName);
        sb.append("?");
        for (String str : this.bundle.keySet()) {
            sb.append(str);
            sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
            sb.append(String.valueOf(this.bundle.get(str)));
            sb.append(ContainerUtils.FIELD_DELIMITER);
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public JDRouterUrlBuilder putBooleanParam(String str, boolean z) {
        this.bundle.putBoolean(str, z);
        return this;
    }

    public JDRouterUrlBuilder putByteParam(String str, byte b) {
        this.bundle.putByte(str, b);
        return this;
    }

    public JDRouterUrlBuilder putCharParam(String str, char c2) {
        this.bundle.putChar(str, c2);
        return this;
    }

    public JDRouterUrlBuilder putIntParam(String str, int i2) {
        this.bundle.putInt(str, i2);
        return this;
    }

    public JDRouterUrlBuilder putLongParam(String str, long j2) {
        this.bundle.putLong(str, j2);
        return this;
    }

    public JDRouterUrlBuilder putStringParam(String str, String str2) {
        this.bundle.putString(str, str2);
        return this;
    }

    public JDRouterUrlBuilder setMethodName(String str) {
        this.defaultMethodName = str;
        return this;
    }

    public JDRouterUrlBuilder setModuleName(String str) {
        this.moduleName = str;
        return this;
    }

    public JDRouterUrlBuilder(String str) {
        this.defaultMethodName = "show";
        this.bundle = new Bundle();
        this.moduleName = str;
    }

    public JDRouterUrlBuilder(String str, String str2) {
        this.defaultMethodName = "show";
        this.bundle = new Bundle();
        this.moduleName = str;
        this.defaultMethodName = str2;
    }
}
