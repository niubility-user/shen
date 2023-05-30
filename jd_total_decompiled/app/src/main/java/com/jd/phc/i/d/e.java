package com.jd.phc.i.d;

import java.io.IOException;

/* loaded from: classes17.dex */
public class e extends IOException implements b {
    private static final long serialVersionUID = 1;
    private d mErrorCode;

    public e(d dVar) {
        super(dVar.toString());
        this.mErrorCode = dVar;
    }

    @Override // com.jd.phc.i.d.b
    public d getErrorCode() {
        return this.mErrorCode;
    }

    public e(int i2, String str) {
        super("[R" + i2 + "]" + str);
        d dVar = d.STATE_ERROR;
        StringBuilder sb = new StringBuilder();
        sb.append("R");
        sb.append(i2);
        this.mErrorCode = dVar.setErrorCode(sb.toString()).setDesc(str);
    }
}
