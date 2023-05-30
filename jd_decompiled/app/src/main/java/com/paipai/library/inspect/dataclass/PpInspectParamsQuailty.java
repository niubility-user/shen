package com.paipai.library.inspect.dataclass;

/* loaded from: classes9.dex */
public class PpInspectParamsQuailty implements PpInspectParamsAware {
    private final String inspectDesc;
    private final int qualityId;

    public PpInspectParamsQuailty(int i2, String str) {
        this.qualityId = i2;
        this.inspectDesc = str;
    }

    public String getInspectDesc() {
        return this.inspectDesc;
    }

    public int getQualityId() {
        return this.qualityId;
    }
}
