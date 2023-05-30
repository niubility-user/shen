package com.jd.phc.i.d;

import java.io.IOException;

/* loaded from: classes.dex */
public class f extends IOException implements b {
    private static final long serialVersionUID = 1;
    private d mErrorCode;

    public f(d dVar) {
        super(dVar.toString());
        this.mErrorCode = dVar;
    }

    @Override // com.jd.phc.i.d.b
    public d getErrorCode() {
        return this.mErrorCode;
    }

    public f(int i2, String str) {
        super("[S" + i2 + "]" + str);
        d dVar = d.SERVER_ERROR;
        StringBuilder sb = new StringBuilder();
        sb.append("S");
        sb.append(i2);
        this.mErrorCode = dVar.setErrorCode(sb.toString()).setDesc(str);
    }
}
