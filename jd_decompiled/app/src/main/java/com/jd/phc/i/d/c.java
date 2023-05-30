package com.jd.phc.i.d;

import java.io.IOException;

/* loaded from: classes17.dex */
public class c extends IOException implements b {
    private static final long serialVersionUID = 1;
    private d mError;

    public c(d dVar) {
        super(dVar.toString());
        this.mError = dVar;
    }

    @Override // com.jd.phc.i.d.b
    public d getErrorCode() {
        return this.mError;
    }
}
