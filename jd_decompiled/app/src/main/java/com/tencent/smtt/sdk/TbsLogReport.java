package com.tencent.smtt.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.jd.dynamic.DYConstants;
import com.tencent.smtt.sdk.TbsDownloadConfig;
import com.tencent.smtt.utils.Base64;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.g;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;

/* loaded from: classes9.dex */
public class TbsLogReport {
    private static TbsLogReport a;
    private Handler b;

    /* renamed from: c */
    private final Map<EventType, Boolean> f17752c;
    private final Context d;

    /* renamed from: e */
    private boolean f17753e = false;

    /* loaded from: classes9.dex */
    public enum EventType {
        TYPE_DOWNLOAD(0),
        TYPE_INSTALL(1),
        TYPE_LOAD(2),
        TYPE_CDN_DOWNLOAD_STAT(3),
        TYPE_COOKIE_DB_SWITCH(4),
        TYPE_PV_UPLOAD_STAT(5),
        TYPE_CORE_LOAD_PERFORMANCE(6),
        TYPE_CORE_PROTECT_RESET(7);
        
        int a;

        EventType(int i2) {
            this.a = i2;
        }
    }

    /* loaded from: classes9.dex */
    public static class TbsLogInfo implements Cloneable {
        int a;
        private long b;

        /* renamed from: c */
        private String f17754c;
        private String d;

        /* renamed from: e */
        private int f17755e;

        /* renamed from: f */
        private int f17756f;

        /* renamed from: g */
        private int f17757g;

        /* renamed from: h */
        private int f17758h;

        /* renamed from: i */
        private String f17759i;

        /* renamed from: j */
        private int f17760j;

        /* renamed from: k */
        private int f17761k;

        /* renamed from: l */
        private long f17762l;

        /* renamed from: m */
        private long f17763m;

        /* renamed from: n */
        private int f17764n;
        private String o;
        private String p;
        private long q;

        private TbsLogInfo() {
            resetArgs();
        }

        protected Object clone() {
            try {
                return super.clone();
            } catch (CloneNotSupportedException unused) {
                return this;
            }
        }

        public int getDownFinalFlag() {
            return this.f17761k;
        }

        public void resetArgs() {
            this.b = 0L;
            this.f17754c = null;
            this.d = null;
            this.f17755e = 0;
            this.f17756f = 0;
            this.f17757g = 0;
            this.f17758h = 2;
            this.f17759i = "unknown";
            this.f17760j = 0;
            this.f17761k = 2;
            this.f17762l = 0L;
            this.f17763m = 0L;
            this.f17764n = 1;
            this.a = 0;
            this.o = null;
            this.p = null;
            this.q = 0L;
        }

        public void setApn(String str) {
            this.f17759i = str;
        }

        public void setCheckErrorDetail(String str) {
            setErrorCode(108);
            this.o = str;
        }

        public void setDownConsumeTime(long j2) {
            this.f17763m += j2;
        }

        public void setDownFinalFlag(int i2) {
            this.f17761k = i2;
        }

        public void setDownloadCancel(int i2) {
            this.f17757g = i2;
        }

        public void setDownloadSize(long j2) {
            this.q += j2;
        }

        public void setDownloadUrl(String str) {
            if (this.f17754c != null) {
                str = this.f17754c + ";" + str;
            }
            this.f17754c = str;
        }

        public void setErrorCode(int i2) {
            if (i2 != 100 && i2 != 110 && i2 != 120 && i2 != 111 && i2 < 400) {
                TbsLog.i(TbsDownloader.LOGTAG, "error occured, errorCode:" + i2, true);
            }
            if (i2 == 111) {
                TbsLog.i(TbsDownloader.LOGTAG, "you are not in wifi, downloading stoped", true);
            }
            this.a = i2;
        }

        public void setEventTime(long j2) {
            this.b = j2;
        }

        public void setFailDetail(String str) {
            if (str == null) {
                return;
            }
            if (str.length() > 1024) {
                str = str.substring(0, 1024);
            }
            this.p = str;
        }

        public void setFailDetail(Throwable th) {
            if (th == null) {
                this.p = "";
                return;
            }
            String stackTraceString = Log.getStackTraceString(th);
            if (stackTraceString.length() > 1024) {
                stackTraceString = stackTraceString.substring(0, 1024);
            }
            this.p = stackTraceString;
        }

        public void setHttpCode(int i2) {
            this.f17755e = i2;
        }

        public void setNetworkChange(int i2) {
            this.f17764n = i2;
        }

        public void setNetworkType(int i2) {
            this.f17760j = i2;
        }

        public void setPatchUpdateFlag(int i2) {
            this.f17756f = i2;
        }

        public void setPkgSize(long j2) {
            this.f17762l = j2;
        }

        public void setResolveIp(String str) {
            this.d = str;
        }

        public void setUnpkgFlag(int i2) {
            this.f17758h = i2;
        }

        public String toString() {
            return "TbsLogInfo{mEventTime=" + this.b + ", mResolveIp='" + this.d + "', mHttpCode=" + this.f17755e + ", mDownloadCancel=" + this.f17757g + ", mNetworkType=" + this.f17760j + ", mDownConsumeTime=" + this.f17763m + ", mErrorCode=" + this.a + ", mCheckErrorDetail='" + this.o + "', mFailDetail='" + this.p + "'}";
        }
    }

    private TbsLogReport(Context context) {
        this.b = null;
        this.d = context.getApplicationContext();
        this.f17752c = TbsPVConfig.getInstance(context).getLogReportSwitchMap();
        this.b = new Handler(TbsHandlerThread.getInstance().getLooper()) { // from class: com.tencent.smtt.sdk.TbsLogReport.1
            {
                TbsLogReport.this = this;
            }

            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i2 = message.what;
                if (i2 != 600) {
                    if (i2 == 601) {
                        TbsLogReport.this.b();
                        return;
                    }
                    return;
                }
                Object obj = message.obj;
                if (obj instanceof TbsLogInfo) {
                    try {
                        int i3 = message.arg1;
                        TbsLogReport.this.a(i3, (TbsLogInfo) obj);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
        };
    }

    private String a(int i2) {
        return i2 + "|";
    }

    private String a(long j2) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date(j2));
        } catch (Exception unused) {
            return null;
        }
    }

    private String a(String str) {
        StringBuilder sb = new StringBuilder();
        if (str == null) {
            str = "";
        }
        sb.append(str);
        sb.append("|");
        return sb.toString();
    }

    private JSONArray a() {
        String string = d().getString("tbs_tbslogreport_upload", null);
        if (string != null) {
            try {
                string = new String(Base64.a(string, 2));
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        if (string == null) {
            return new JSONArray();
        }
        try {
            JSONArray jSONArray = new JSONArray(string);
            if (jSONArray.length() <= 5) {
                return jSONArray;
            }
            JSONArray jSONArray2 = new JSONArray();
            int length = jSONArray.length();
            while (true) {
                length--;
                if (length < jSONArray.length() - 5) {
                    return jSONArray2;
                }
                jSONArray2.put(jSONArray.get(length));
            }
        } catch (Exception unused) {
            return new JSONArray();
        }
    }

    public void a(int i2, TbsLogInfo tbsLogInfo) {
        Map<String, Object> map = QbSdk.o;
        if (map != null && map.containsKey(QbSdk.KEY_SET_SENDREQUEST_AND_UPLOAD) && QbSdk.o.get(QbSdk.KEY_SET_SENDREQUEST_AND_UPLOAD).equals(DYConstants.DY_FALSE)) {
            TbsLog.i("TbsLogReport", "[TbsLogReport.sendLogReportRequest] -- SET_SENDREQUEST_AND_UPLOAD is false");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(a(i2));
        sb.append(a(""));
        sb.append(a(com.tencent.smtt.utils.l.a(this.d)));
        sb.append(a(m.a().g(this.d)));
        sb.append(a(""));
        String packageName = this.d.getPackageName();
        sb.append(a(packageName));
        sb.append("com.tencent.mm".equals(packageName) ? a(com.tencent.smtt.utils.b.a(this.d, TbsDownloader.TBS_METADATA)) : a(com.tencent.smtt.utils.b.b(this.d)));
        sb.append(a(a(tbsLogInfo.b)));
        sb.append(a(tbsLogInfo.f17754c));
        sb.append(a(tbsLogInfo.d));
        sb.append(a(tbsLogInfo.f17755e));
        sb.append(a(tbsLogInfo.f17756f));
        sb.append(a(tbsLogInfo.f17757g));
        sb.append(a(tbsLogInfo.f17758h));
        sb.append(a(tbsLogInfo.f17759i));
        sb.append(a(tbsLogInfo.f17760j));
        sb.append(a(tbsLogInfo.f17761k));
        sb.append(b(tbsLogInfo.q));
        sb.append(b(tbsLogInfo.f17762l));
        sb.append(b(tbsLogInfo.f17763m));
        sb.append(a(tbsLogInfo.f17764n));
        sb.append(a(tbsLogInfo.a));
        sb.append(a(tbsLogInfo.o));
        sb.append(a(tbsLogInfo.p));
        sb.append(a(TbsDownloadConfig.getInstance(this.d).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0)));
        sb.append(a(com.tencent.smtt.utils.b.g(this.d)));
        sb.append(a("44286"));
        sb.append(false);
        SharedPreferences d = d();
        JSONArray a2 = a();
        a2.put(sb.toString());
        SharedPreferences.Editor edit = d.edit();
        String jSONArray = a2.toString();
        try {
            jSONArray = Base64.encodeToString(jSONArray.getBytes(), 2);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        edit.putString("tbs_tbslogreport_upload", jSONArray);
        edit.commit();
        if (this.f17753e) {
            b();
        }
    }

    private void a(int i2, TbsLogInfo tbsLogInfo, EventType eventType) {
        tbsLogInfo.setErrorCode(i2);
        tbsLogInfo.setEventTime(System.currentTimeMillis());
        QbSdk.f17732n.onInstallFinish(i2);
        eventReport(eventType, tbsLogInfo);
    }

    private void a(int i2, String str) {
        TbsLogInfo tbsLogInfo = tbsLogInfo();
        tbsLogInfo.setErrorCode(i2);
        tbsLogInfo.setEventTime(System.currentTimeMillis());
        tbsLogInfo.setFailDetail(str);
        eventReport(EventType.TYPE_LOAD, tbsLogInfo);
    }

    private String b(long j2) {
        return j2 + "|";
    }

    public void b() {
        String str;
        String str2;
        Map<String, Object> map = QbSdk.o;
        if (map != null && map.containsKey(QbSdk.KEY_SET_SENDREQUEST_AND_UPLOAD) && QbSdk.o.get(QbSdk.KEY_SET_SENDREQUEST_AND_UPLOAD).equals(DYConstants.DY_FALSE)) {
            str = "TbsLogReport";
            str2 = "[TbsLogReport.sendLogReportRequest] -- SET_SENDREQUEST_AND_UPLOAD is false";
        } else {
            str = TbsDownloader.LOGTAG;
            TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloadStat.reportDownloadStat]");
            JSONArray a2 = a();
            if (a2 != null && a2.length() != 0) {
                TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloadStat.reportDownloadStat] jsonArray:" + a2);
                try {
                    TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloadStat.reportDownloadStat] response:" + com.tencent.smtt.utils.g.a(com.tencent.smtt.utils.o.a(this.d).c(), a2.toString().getBytes("utf-8"), new g.a() { // from class: com.tencent.smtt.sdk.TbsLogReport.2
                        {
                            TbsLogReport.this = this;
                        }

                        @Override // com.tencent.smtt.utils.g.a
                        public void a(int i2) {
                            TbsLog.i(TbsDownloader.LOGTAG, "[TbsApkDownloadStat.reportDownloadStat] onHttpResponseCode:" + i2);
                            if (i2 < 300) {
                                TbsLogReport.this.c();
                            }
                        }
                    }, true) + " testcase: -1");
                    return;
                } catch (Throwable th) {
                    th.printStackTrace();
                    return;
                }
            }
            str2 = "[TbsApkDownloadStat.reportDownloadStat] no data";
        }
        TbsLog.i(str, str2);
    }

    public void c() {
        SharedPreferences.Editor edit = d().edit();
        edit.remove("tbs_tbslogreport_upload");
        edit.commit();
    }

    private SharedPreferences d() {
        return this.d.getSharedPreferences("tbs_event_stat", 4);
    }

    public static TbsLogReport getInstance(Context context) {
        if (a == null) {
            synchronized (TbsLogReport.class) {
                if (a == null) {
                    a = new TbsLogReport(context);
                }
            }
        }
        return a;
    }

    public void clear() {
        try {
            SharedPreferences.Editor edit = d().edit();
            edit.clear();
            edit.commit();
        } catch (Exception unused) {
        }
    }

    public void dailyReport() {
        this.b.sendEmptyMessage(601);
    }

    public void eventReport(EventType eventType, TbsLogInfo tbsLogInfo) {
        TbsLog.i("TbsLogReport", "[TbsLogReport.eventRepost] " + eventType + ": " + tbsLogInfo);
        Boolean bool = this.f17752c.get(eventType);
        if (bool == null || !bool.booleanValue()) {
            return;
        }
        TbsLog.i("TbsLogReport", "[TbsLogReport.eventRepost] needReport!");
        try {
            Message obtainMessage = this.b.obtainMessage();
            obtainMessage.what = 600;
            obtainMessage.arg1 = eventType.a;
            obtainMessage.obj = (TbsLogInfo) tbsLogInfo.clone();
            this.b.sendMessage(obtainMessage);
        } catch (Throwable th) {
            TbsLog.w("TbsLogReport", "[TbsLogReport.eventReport] error, message=" + th.getMessage());
        }
    }

    public boolean getShouldUploadEventReport() {
        return this.f17753e;
    }

    public void setInstallErrorCode(int i2, String str) {
        setInstallErrorCode(i2, str, EventType.TYPE_INSTALL);
    }

    public void setInstallErrorCode(int i2, String str, EventType eventType) {
        if (i2 != 200 && i2 != 220 && i2 != 221) {
            TbsLog.i(TbsDownloader.LOGTAG, "error occured in installation, errorCode:" + i2, true);
        }
        TbsLogInfo tbsLogInfo = tbsLogInfo();
        tbsLogInfo.setFailDetail(str);
        a(i2, tbsLogInfo, eventType);
    }

    public void setInstallErrorCode(int i2, Throwable th) {
        TbsLogInfo tbsLogInfo = tbsLogInfo();
        tbsLogInfo.setFailDetail(th);
        a(i2, tbsLogInfo, EventType.TYPE_INSTALL);
    }

    public void setLoadErrorCode(int i2, Throwable th) {
        String str;
        if (th != null) {
            str = "msg: " + th.getMessage() + "; err: " + th + "; cause: " + Log.getStackTraceString(th.getCause());
            if (str.length() > 1024) {
                str = str.substring(0, 1024);
            }
        } else {
            str = "NULL";
        }
        a(i2, str);
    }

    public void setShouldUploadEventReport(boolean z) {
        this.f17753e = z;
    }

    public TbsLogInfo tbsLogInfo() {
        return new TbsLogInfo();
    }
}
