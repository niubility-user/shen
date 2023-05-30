package com.jd.android.sdk.oaid;

import android.text.TextUtils;

/* loaded from: classes.dex */
public class OaidInfo {
    private String oaid;

    public OaidInfo() {
        this.oaid = "";
    }

    public OaidInfo(String str) {
        this.oaid = str;
    }

    public String getOAID() {
        return this.oaid;
    }

    public boolean isOAIDValid() {
        return !TextUtils.isEmpty(this.oaid);
    }

    public void setOAID(String str) {
        if (TextUtils.isEmpty(str) || "NO".equals(str)) {
            this.oaid = "";
        } else {
            this.oaid = str;
        }
    }

    public void setOAIDValid(boolean z) {
    }
}
