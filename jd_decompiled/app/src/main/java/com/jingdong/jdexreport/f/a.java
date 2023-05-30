package com.jingdong.jdexreport.f;

import android.content.Context;
import com.jingdong.jdexreport.a.a.d;
import com.jingdong.jdexreport.e.b;
import com.jingdong.jdexreport.e.e;
import com.jingdong.jdexreport.einterface.InitCommonInfo;

/* loaded from: classes.dex */
public abstract class a implements Runnable {
    private static boolean unWifiStopFlag = false;
    public static boolean zipandBase64Flag = true;
    protected Context mContext;
    protected final com.jingdong.jdexreport.b.a mDBManager;
    protected b userInfoModel;
    protected final String TAG = a.class.getSimpleName();
    protected volatile boolean stopThreadFlag = false;
    private int mReportNum = 0;

    public a(Context context, InitCommonInfo initCommonInfo) {
        Context applicationContext = context.getApplicationContext();
        this.mContext = applicationContext;
        if (applicationContext == null) {
            this.mContext = context;
        }
        this.mDBManager = com.jingdong.jdexreport.b.a.a(this.mContext);
        this.userInfoModel = b.a(initCommonInfo);
        if (initCommonInfo.zipFlag == 1) {
            zipandBase64Flag = true;
        } else {
            zipandBase64Flag = false;
        }
    }

    public static void closeUnWifiReport() {
        unWifiStopFlag = true;
    }

    public static void openUnWifiReport() {
        unWifiStopFlag = false;
    }

    public static void openZipandBase64() {
        zipandBase64Flag = true;
    }

    public abstract void aligningCount();

    public abstract void onDealFail(int i2);

    public abstract void onDealSuccess(int i2);

    public abstract void onNullDataReport();

    public void reportDemonMain() {
        e a = e.a(this.mContext);
        if (0 != Long.valueOf(a.b(d.b(this.mContext))).longValue() && a.g()) {
            int reportFromDB = com.jingdong.jdexreport.a.a.a.a(this.mContext) ? reportFromDB() : 2;
            if (reportFromDB == 0) {
                onDealSuccess(this.mReportNum);
                this.mReportNum = 0;
            } else if (2 == reportFromDB) {
                onNullDataReport();
                this.mReportNum = 0;
            } else if (1 == reportFromDB) {
                onDealFail(this.mReportNum);
                this.mReportNum = 0;
            }
            threadWait();
            return;
        }
        threadWait();
    }

    /* JADX WARN: Removed duplicated region for block: B:126:0x0111  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x012e  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x015b  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x0176  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x017a  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x00fc A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected int reportFromDB() {
        /*
            Method dump skipped, instructions count: 380
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.jdexreport.f.a.reportFromDB():int");
    }

    @Override // java.lang.Runnable
    public void run() {
        while (!this.stopThreadFlag) {
            reportDemonMain();
        }
    }

    public void stopThread() {
        this.stopThreadFlag = true;
    }

    public void threadWait() {
        try {
            synchronized (this) {
                wait();
            }
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        }
    }

    protected boolean updateStrategy(String str) {
        e a = e.a(this.mContext);
        boolean d = a.d(str);
        if (!a.g()) {
            try {
                Thread.sleep(800L);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
            this.mDBManager.a();
        }
        return d;
    }
}
