package com.jingdong.sdk.oklog.reporter;

import java.util.HashMap;

/* loaded from: classes.dex */
public abstract class AbsLogReporter {
    public HashMap<String, String> getAdditionalData() {
        return null;
    }

    public boolean isReportable(int i2) {
        return true;
    }

    public void report(HashMap<String, String> hashMap) {
    }
}
