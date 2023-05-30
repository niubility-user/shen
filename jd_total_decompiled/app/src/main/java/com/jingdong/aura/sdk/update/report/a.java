package com.jingdong.aura.sdk.update.report;

/* loaded from: classes4.dex */
public final class a implements IReporter {
    private IReporter a;

    public a(IReporter iReporter) {
        this.a = iReporter;
    }

    @Override // com.jingdong.aura.sdk.update.report.IReporter
    public final void onException(String str, int i2, String str2, String str3, Throwable th) {
        IReporter iReporter = this.a;
        if (iReporter == null) {
            return;
        }
        try {
            iReporter.onException(str, i2, str2, str3, th);
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
    }

    @Override // com.jingdong.aura.sdk.update.report.IReporter
    public final void onTrace(String str, String str2, int i2, String str3, String str4) {
        IReporter iReporter = this.a;
        if (iReporter == null) {
            return;
        }
        try {
            iReporter.onTrace(str, str2, i2, str3, str4);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.jingdong.aura.sdk.update.report.IReporter
    public final void onTrace(String str, String str2, String str3) {
        IReporter iReporter = this.a;
        if (iReporter == null) {
            return;
        }
        try {
            iReporter.onTrace(str, str2, str3);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
