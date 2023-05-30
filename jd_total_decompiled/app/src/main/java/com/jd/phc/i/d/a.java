package com.jd.phc.i.d;

/* loaded from: classes.dex */
public class a extends RuntimeException implements b {
    private static final long serialVersionUID = 1;
    private d mError;

    public a(d dVar) {
        super(dVar.toString());
        this.mError = dVar;
    }

    @Override // com.jd.phc.i.d.b
    public d getErrorCode() {
        return this.mError;
    }
}
