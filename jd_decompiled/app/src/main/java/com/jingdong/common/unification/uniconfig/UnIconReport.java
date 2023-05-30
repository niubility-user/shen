package com.jingdong.common.unification.uniconfig;

import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes6.dex */
public class UnIconReport {
    private static final String TAG = "UnIconReport";
    private static UnIconReport report;
    private int downloadSize;
    private long startDownload;

    private UnIconReport() {
    }

    public static UnIconReport getInstance() {
        UnIconReport unIconReport;
        UnIconReport unIconReport2 = report;
        if (unIconReport2 != null) {
            return unIconReport2;
        }
        synchronized (UnIconReport.class) {
            if (report == null) {
                report = new UnIconReport();
            }
            unIconReport = report;
        }
        return unIconReport;
    }

    public void endDownload() {
        long currentTimeMillis = System.currentTimeMillis();
        if (OKLog.D) {
            OKLog.d(TAG, "end download:" + (currentTimeMillis - this.startDownload));
        }
    }

    public void startDownload(int i2) {
        this.startDownload = System.currentTimeMillis();
        this.downloadSize = i2;
        if (OKLog.D) {
            OKLog.d(TAG, "start download size:" + i2);
        }
    }
}
