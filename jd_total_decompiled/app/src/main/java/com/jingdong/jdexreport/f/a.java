package com.jingdong.jdexreport.f;

import android.content.Context;
import com.jingdong.jdexreport.a.a.d;
import com.jingdong.jdexreport.e.b;
import com.jingdong.jdexreport.e.e;
import com.jingdong.jdexreport.einterface.InitCommonInfo;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

    /* JADX WARN: Removed duplicated region for block: B:211:0x0111  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x012e  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x015b  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x0176  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x017a  */
    /* JADX WARN: Removed duplicated region for block: B:238:0x00fc A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected int reportFromDB() {
        ArrayList<com.jingdong.jdexreport.e.d> arrayList;
        int i2;
        long j2;
        long j3;
        int i3;
        String str;
        int f2;
        com.jingdong.jdexreport.e.d dVar;
        String b;
        this.mReportNum = 0;
        if (d.c(this.mContext) || !unWifiStopFlag) {
            e a = e.a(this.mContext);
            JSONObject a2 = this.userInfoModel.a(this.mContext);
            if (a.g()) {
                Long valueOf = Long.valueOf(a.b(d.b(this.mContext)));
                com.jingdong.jdexreport.a.a.a.a(this.TAG, "reportFromDB GET COUNT:" + valueOf);
                ArrayList<com.jingdong.jdexreport.e.d> a3 = this.mDBManager.a(valueOf);
                int i4 = 1;
                if (a3.size() > 0) {
                    JSONArray jSONArray = new JSONArray();
                    long j4 = -1;
                    long j5 = -1;
                    int i5 = 0;
                    while (i5 < a3.size()) {
                        try {
                            try {
                                dVar = a3.get(i5);
                                b = dVar.b();
                                if (i5 == 0) {
                                    j4 = Long.parseLong(dVar.a());
                                }
                            } catch (JSONException unused) {
                            }
                            try {
                                if (i5 == a3.size() - 1) {
                                    j5 = Long.parseLong(dVar.a());
                                }
                                if (b == null || b.length() <= 2097152) {
                                    try {
                                        jSONArray.put(new JSONObject(b));
                                    } catch (OutOfMemoryError unused2) {
                                        return 1;
                                    }
                                }
                                i5++;
                                i4 = 1;
                            } catch (JSONException unused3) {
                                str = "";
                                j3 = j4;
                                j2 = j5;
                                i3 = 1;
                                com.jingdong.jdexreport.c.b bVar = new com.jingdong.jdexreport.c.b(30000, 10000, 3, "utf-8", "utf-8", true);
                                bVar.b(com.jingdong.jdexreport.a.a.a.b());
                                com.jingdong.jdexreport.a.a.a.a("JDExReport", "reportFromDB:" + str);
                                if (!zipandBase64Flag) {
                                }
                                f2 = bVar.f();
                                com.jingdong.jdexreport.a.a.a.a("JDExReport", "http report result:" + f2);
                                if (f2 != 0) {
                                }
                                i2 = 1;
                                if (arrayList.size() < valueOf.longValue()) {
                                }
                            }
                        } catch (OutOfMemoryError unused4) {
                            return i4;
                        }
                    }
                    a2.put("data", jSONArray);
                    str = a2.toString();
                    j3 = j4;
                    j2 = j5;
                    i3 = 0;
                    com.jingdong.jdexreport.c.b bVar2 = new com.jingdong.jdexreport.c.b(30000, 10000, 3, "utf-8", "utf-8", true);
                    bVar2.b(com.jingdong.jdexreport.a.a.a.b());
                    com.jingdong.jdexreport.a.a.a.a("JDExReport", "reportFromDB:" + str);
                    if (!zipandBase64Flag) {
                        try {
                            bVar2.a(com.jingdong.jdexreport.common.secure.b.a(com.jingdong.jdexreport.common.secure.b.b(str.getBytes())));
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    } else {
                        bVar2.a(str);
                    }
                    f2 = bVar2.f();
                    com.jingdong.jdexreport.a.a.a.a("JDExReport", "http report result:" + f2);
                    if (f2 != 0) {
                        try {
                        } catch (Exception e3) {
                            e = e3;
                            arrayList = a3;
                        }
                        if (updateStrategy(new String(bVar2.c(), "utf-8"))) {
                            arrayList = a3;
                            try {
                                this.mReportNum = this.mDBManager.a(a3, j3, j2);
                            } catch (Exception e4) {
                                e = e4;
                                e.printStackTrace();
                                i2 = i3;
                                if (arrayList.size() < valueOf.longValue()) {
                                }
                            }
                            i2 = i3;
                        } else {
                            arrayList = a3;
                        }
                    } else {
                        arrayList = a3;
                        e.a(this.mContext).f();
                    }
                    i2 = 1;
                } else {
                    arrayList = a3;
                    i2 = 0;
                }
                if (arrayList.size() < valueOf.longValue()) {
                    aligningCount();
                    return 2;
                }
                return i2;
            }
            return 2;
        }
        return 2;
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
