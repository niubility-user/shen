package com.tencent.smtt.sdk;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jd.dynamic.DYConstants;
import com.tencent.smtt.sdk.TbsDownloadConfig;
import com.tencent.smtt.sdk.TbsLogReport;
import com.tencent.smtt.utils.Apn;
import com.tencent.smtt.utils.FileUtil;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.g;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileLock;
import java.util.Map;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;
import tv.danmaku.ijk.media.player.IMediaPlayer;

/* loaded from: classes9.dex */
public class TbsDownloader {
    public static final boolean DEBUG_DISABLE_DOWNLOAD = false;
    public static boolean DOWNLOAD_OVERSEA_TBS = false;
    public static final String LOGTAG = "TbsDownload";
    public static final String TBS_METADATA = "com.tencent.mm.BuildInfo.CLIENT_VERSION";
    static boolean a = false;
    private static String b = null;

    /* renamed from: c */
    private static Context f17740c = null;
    private static Handler d = null;

    /* renamed from: e */
    private static String f17741e = null;

    /* renamed from: f */
    private static final Object f17742f = new byte[0];

    /* renamed from: g */
    private static k f17743g = null;

    /* renamed from: h */
    private static HandlerThread f17744h = null;

    /* renamed from: i */
    private static int f17745i = 0;

    /* renamed from: j */
    private static boolean f17746j = false;

    /* renamed from: k */
    private static String f17747k = "";

    /* renamed from: l */
    private static String f17748l = "";

    /* renamed from: m */
    private static boolean f17749m = false;

    /* renamed from: n */
    private static boolean f17750n = false;
    private static JSONObject o = null;
    private static JSONObject p = null;
    private static boolean q = false;
    private static int r = 0;
    private static int s = 0;
    private static JSONObject t = null;
    private static long u = -1;

    /* loaded from: classes9.dex */
    public interface TbsDownloaderCallback {
        void onNeedDownloadFinish(boolean z, int i2);
    }

    private static String a(String str) {
        return str == null ? "" : str;
    }

    /* JADX WARN: Removed duplicated region for block: B:136:0x00a5 A[Catch: Exception -> 0x02ab, TryCatch #0 {Exception -> 0x02ab, blocks: (B:125:0x006e, B:128:0x0082, B:131:0x008b, B:134:0x0095, B:136:0x00a5, B:137:0x00aa, B:139:0x00b7, B:160:0x0143, B:164:0x014d, B:166:0x0155, B:168:0x0176, B:169:0x017b, B:171:0x01c6, B:172:0x01cb, B:176:0x01e3, B:180:0x01ef, B:184:0x01fe, B:185:0x0201, B:189:0x023f, B:193:0x024a, B:194:0x0261, B:196:0x0276, B:198:0x0280, B:200:0x0284, B:203:0x028e, B:205:0x0292, B:206:0x0297, B:208:0x029f, B:210:0x02a6, B:141:0x00c5, B:144:0x00dc, B:146:0x00e8, B:148:0x00f9, B:150:0x0108, B:151:0x0112, B:153:0x0130, B:142:0x00d0, B:132:0x008f), top: B:214:0x006e }] */
    /* JADX WARN: Removed duplicated region for block: B:139:0x00b7 A[Catch: Exception -> 0x02ab, TryCatch #0 {Exception -> 0x02ab, blocks: (B:125:0x006e, B:128:0x0082, B:131:0x008b, B:134:0x0095, B:136:0x00a5, B:137:0x00aa, B:139:0x00b7, B:160:0x0143, B:164:0x014d, B:166:0x0155, B:168:0x0176, B:169:0x017b, B:171:0x01c6, B:172:0x01cb, B:176:0x01e3, B:180:0x01ef, B:184:0x01fe, B:185:0x0201, B:189:0x023f, B:193:0x024a, B:194:0x0261, B:196:0x0276, B:198:0x0280, B:200:0x0284, B:203:0x028e, B:205:0x0292, B:206:0x0297, B:208:0x029f, B:210:0x02a6, B:141:0x00c5, B:144:0x00dc, B:146:0x00e8, B:148:0x00f9, B:150:0x0108, B:151:0x0112, B:153:0x0130, B:142:0x00d0, B:132:0x008f), top: B:214:0x006e }] */
    /* JADX WARN: Removed duplicated region for block: B:140:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x0142  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x0147  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x0155 A[Catch: Exception -> 0x02ab, TryCatch #0 {Exception -> 0x02ab, blocks: (B:125:0x006e, B:128:0x0082, B:131:0x008b, B:134:0x0095, B:136:0x00a5, B:137:0x00aa, B:139:0x00b7, B:160:0x0143, B:164:0x014d, B:166:0x0155, B:168:0x0176, B:169:0x017b, B:171:0x01c6, B:172:0x01cb, B:176:0x01e3, B:180:0x01ef, B:184:0x01fe, B:185:0x0201, B:189:0x023f, B:193:0x024a, B:194:0x0261, B:196:0x0276, B:198:0x0280, B:200:0x0284, B:203:0x028e, B:205:0x0292, B:206:0x0297, B:208:0x029f, B:210:0x02a6, B:141:0x00c5, B:144:0x00dc, B:146:0x00e8, B:148:0x00f9, B:150:0x0108, B:151:0x0112, B:153:0x0130, B:142:0x00d0, B:132:0x008f), top: B:214:0x006e }] */
    /* JADX WARN: Removed duplicated region for block: B:171:0x01c6 A[Catch: Exception -> 0x02ab, TryCatch #0 {Exception -> 0x02ab, blocks: (B:125:0x006e, B:128:0x0082, B:131:0x008b, B:134:0x0095, B:136:0x00a5, B:137:0x00aa, B:139:0x00b7, B:160:0x0143, B:164:0x014d, B:166:0x0155, B:168:0x0176, B:169:0x017b, B:171:0x01c6, B:172:0x01cb, B:176:0x01e3, B:180:0x01ef, B:184:0x01fe, B:185:0x0201, B:189:0x023f, B:193:0x024a, B:194:0x0261, B:196:0x0276, B:198:0x0280, B:200:0x0284, B:203:0x028e, B:205:0x0292, B:206:0x0297, B:208:0x029f, B:210:0x02a6, B:141:0x00c5, B:144:0x00dc, B:146:0x00e8, B:148:0x00f9, B:150:0x0108, B:151:0x0112, B:153:0x0130, B:142:0x00d0, B:132:0x008f), top: B:214:0x006e }] */
    /* JADX WARN: Removed duplicated region for block: B:174:0x01e0  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x01e2  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x01ec  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x01ee  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x01fd  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x023b  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x028e A[Catch: Exception -> 0x02ab, TryCatch #0 {Exception -> 0x02ab, blocks: (B:125:0x006e, B:128:0x0082, B:131:0x008b, B:134:0x0095, B:136:0x00a5, B:137:0x00aa, B:139:0x00b7, B:160:0x0143, B:164:0x014d, B:166:0x0155, B:168:0x0176, B:169:0x017b, B:171:0x01c6, B:172:0x01cb, B:176:0x01e3, B:180:0x01ef, B:184:0x01fe, B:185:0x0201, B:189:0x023f, B:193:0x024a, B:194:0x0261, B:196:0x0276, B:198:0x0280, B:200:0x0284, B:203:0x028e, B:205:0x0292, B:206:0x0297, B:208:0x029f, B:210:0x02a6, B:141:0x00c5, B:144:0x00dc, B:146:0x00e8, B:148:0x00f9, B:150:0x0108, B:151:0x0112, B:153:0x0130, B:142:0x00d0, B:132:0x008f), top: B:214:0x006e }] */
    /* JADX WARN: Removed duplicated region for block: B:205:0x0292 A[Catch: Exception -> 0x02ab, TryCatch #0 {Exception -> 0x02ab, blocks: (B:125:0x006e, B:128:0x0082, B:131:0x008b, B:134:0x0095, B:136:0x00a5, B:137:0x00aa, B:139:0x00b7, B:160:0x0143, B:164:0x014d, B:166:0x0155, B:168:0x0176, B:169:0x017b, B:171:0x01c6, B:172:0x01cb, B:176:0x01e3, B:180:0x01ef, B:184:0x01fe, B:185:0x0201, B:189:0x023f, B:193:0x024a, B:194:0x0261, B:196:0x0276, B:198:0x0280, B:200:0x0284, B:203:0x028e, B:205:0x0292, B:206:0x0297, B:208:0x029f, B:210:0x02a6, B:141:0x00c5, B:144:0x00dc, B:146:0x00e8, B:148:0x00f9, B:150:0x0108, B:151:0x0112, B:153:0x0130, B:142:0x00d0, B:132:0x008f), top: B:214:0x006e }] */
    /* JADX WARN: Removed duplicated region for block: B:208:0x029f A[Catch: Exception -> 0x02ab, TryCatch #0 {Exception -> 0x02ab, blocks: (B:125:0x006e, B:128:0x0082, B:131:0x008b, B:134:0x0095, B:136:0x00a5, B:137:0x00aa, B:139:0x00b7, B:160:0x0143, B:164:0x014d, B:166:0x0155, B:168:0x0176, B:169:0x017b, B:171:0x01c6, B:172:0x01cb, B:176:0x01e3, B:180:0x01ef, B:184:0x01fe, B:185:0x0201, B:189:0x023f, B:193:0x024a, B:194:0x0261, B:196:0x0276, B:198:0x0280, B:200:0x0284, B:203:0x028e, B:205:0x0292, B:206:0x0297, B:208:0x029f, B:210:0x02a6, B:141:0x00c5, B:144:0x00dc, B:146:0x00e8, B:148:0x00f9, B:150:0x0108, B:151:0x0112, B:153:0x0130, B:142:0x00d0, B:132:0x008f), top: B:214:0x006e }] */
    /* JADX WARN: Removed duplicated region for block: B:210:0x02a6 A[Catch: Exception -> 0x02ab, TRY_LEAVE, TryCatch #0 {Exception -> 0x02ab, blocks: (B:125:0x006e, B:128:0x0082, B:131:0x008b, B:134:0x0095, B:136:0x00a5, B:137:0x00aa, B:139:0x00b7, B:160:0x0143, B:164:0x014d, B:166:0x0155, B:168:0x0176, B:169:0x017b, B:171:0x01c6, B:172:0x01cb, B:176:0x01e3, B:180:0x01ef, B:184:0x01fe, B:185:0x0201, B:189:0x023f, B:193:0x024a, B:194:0x0261, B:196:0x0276, B:198:0x0280, B:200:0x0284, B:203:0x028e, B:205:0x0292, B:206:0x0297, B:208:0x029f, B:210:0x02a6, B:141:0x00c5, B:144:0x00dc, B:146:0x00e8, B:148:0x00f9, B:150:0x0108, B:151:0x0112, B:153:0x0130, B:142:0x00d0, B:132:0x008f), top: B:214:0x006e }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static org.json.JSONObject a(boolean r17, boolean r18, boolean r19) {
        /*
            Method dump skipped, instructions count: 708
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsDownloader.a(boolean, boolean, boolean):org.json.JSONObject");
    }

    private static void a(int i2) {
        File file = new File(m.o(f17740c), "tbs_switch_disable_" + i2);
        if (file.exists()) {
            return;
        }
        try {
            TbsLog.i(LOGTAG, "setTbsCoreDisabledBySwitch status: " + file.createNewFile());
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public static void a(final Context context, final Runnable runnable) {
        Thread thread = new Thread() { // from class: com.tencent.smtt.sdk.TbsDownloader.3
            /* JADX WARN: Removed duplicated region for block: B:65:0x0073  */
            /* JADX WARN: Removed duplicated region for block: B:79:? A[RETURN, SYNTHETIC] */
            @Override // java.lang.Thread, java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void run() {
                /*
                    r10 = this;
                    java.lang.String r0 = "release wait download lock"
                    java.lang.String r1 = "TbsDownload"
                    r2 = 0
                    com.tencent.smtt.utils.Timer r3 = com.tencent.smtt.sdk.QbSdk.c()     // Catch: java.lang.Throwable -> L4f java.lang.Exception -> L53
                    android.content.Context r4 = r1     // Catch: java.lang.Throwable -> L4f java.lang.Exception -> L53
                    r5 = 1
                    java.lang.String r6 = "tbs_download_lock_file"
                    java.io.FileOutputStream r4 = com.tencent.smtt.utils.FileUtil.b(r4, r5, r6)     // Catch: java.lang.Throwable -> L4f java.lang.Exception -> L53
                    if (r4 == 0) goto L41
                    if (r3 == 0) goto L41
                    java.nio.channels.FileChannel r5 = r4.getChannel()     // Catch: java.lang.Throwable -> L3b java.lang.Exception -> L3e
                    java.util.Timer r6 = new java.util.Timer     // Catch: java.lang.Exception -> L39 java.lang.Throwable -> L77
                    r6.<init>()     // Catch: java.lang.Exception -> L39 java.lang.Throwable -> L77
                    com.tencent.smtt.sdk.TbsDownloader$3$1 r7 = new com.tencent.smtt.sdk.TbsDownloader$3$1     // Catch: java.lang.Exception -> L39 java.lang.Throwable -> L77
                    r7.<init>()     // Catch: java.lang.Exception -> L39 java.lang.Throwable -> L77
                    int r3 = r3.timeOut     // Catch: java.lang.Exception -> L39 java.lang.Throwable -> L77
                    long r8 = (long) r3     // Catch: java.lang.Exception -> L39 java.lang.Throwable -> L77
                    r6.schedule(r7, r8)     // Catch: java.lang.Exception -> L39 java.lang.Throwable -> L77
                    java.lang.String r3 = "start waiting other process Download finished"
                    com.tencent.smtt.utils.TbsLog.i(r1, r3)     // Catch: java.lang.Exception -> L39 java.lang.Throwable -> L77
                    java.nio.channels.FileLock r2 = r5.lock()     // Catch: java.lang.Exception -> L39 java.lang.Throwable -> L77
                    java.lang.String r3 = "wait lock success"
                    com.tencent.smtt.utils.TbsLog.i(r1, r3)     // Catch: java.lang.Exception -> L39 java.lang.Throwable -> L77
                    goto L42
                L39:
                    r3 = move-exception
                    goto L56
                L3b:
                    r3 = move-exception
                    r5 = r2
                    goto L78
                L3e:
                    r3 = move-exception
                    r5 = r2
                    goto L56
                L41:
                    r5 = r2
                L42:
                    com.tencent.smtt.utils.FileUtil.a(r2, r4)
                    com.tencent.smtt.utils.TbsLog.i(r1, r0)
                    com.tencent.smtt.utils.FileUtil.a(r5)
                    com.tencent.smtt.utils.FileUtil.a(r4)
                    goto L6f
                L4f:
                    r3 = move-exception
                    r4 = r2
                    r5 = r4
                    goto L78
                L53:
                    r3 = move-exception
                    r4 = r2
                    r5 = r4
                L56:
                    java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L77
                    r6.<init>()     // Catch: java.lang.Throwable -> L77
                    java.lang.String r7 = "MultiProcessSyncInit exception: "
                    r6.append(r7)     // Catch: java.lang.Throwable -> L77
                    java.lang.String r3 = android.util.Log.getStackTraceString(r3)     // Catch: java.lang.Throwable -> L77
                    r6.append(r3)     // Catch: java.lang.Throwable -> L77
                    java.lang.String r3 = r6.toString()     // Catch: java.lang.Throwable -> L77
                    com.tencent.smtt.utils.TbsLog.w(r1, r3)     // Catch: java.lang.Throwable -> L77
                    goto L42
                L6f:
                    java.lang.Runnable r0 = r2
                    if (r0 == 0) goto L76
                    r0.run()
                L76:
                    return
                L77:
                    r3 = move-exception
                L78:
                    com.tencent.smtt.utils.FileUtil.a(r2, r4)
                    com.tencent.smtt.utils.TbsLog.i(r1, r0)
                    com.tencent.smtt.utils.FileUtil.a(r5)
                    com.tencent.smtt.utils.FileUtil.a(r4)
                    goto L86
                L85:
                    throw r3
                L86:
                    goto L85
                */
                throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsDownloader.AnonymousClass3.run():void");
            }
        };
        thread.setName("tbsSyncWaiter");
        thread.start();
    }

    private static void a(boolean z, TbsDownloaderCallback tbsDownloaderCallback, boolean z2) {
        TbsLog.i(LOGTAG, "[TbsDownloader.queryConfig]");
        d.removeMessages(100);
        Message obtain = Message.obtain(d, 100);
        if (tbsDownloaderCallback != null) {
            obtain.obj = tbsDownloaderCallback;
        }
        obtain.arg1 = 0;
        obtain.arg1 = z ? 1 : 0;
        obtain.arg2 = z2 ? 1 : 0;
        obtain.sendToTarget();
    }

    public static boolean a(Context context) {
        return TbsDownloadConfig.getInstance(context).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_DOWNLOADDECOUPLECORE, 0) == 1;
    }

    public static boolean a(Context context, int i2) {
        return Build.VERSION.SDK_INT > 28 && context.getApplicationInfo().targetSdkVersion > 28 && i2 > 0 && i2 < 45114;
    }

    private static boolean a(Context context, boolean z) {
        int i2;
        TbsDownloadConfig tbsDownloadConfig = TbsDownloadConfig.getInstance(context);
        if (Build.VERSION.SDK_INT < 8) {
            i2 = -102;
        } else {
            String string = tbsDownloadConfig.mPreferences.getString(TbsDownloadConfig.TbsConfigKey.KEY_DEVICE_CPUABI, null);
            f17741e = string;
            if (TextUtils.isEmpty(string) || com.tencent.smtt.utils.b.a(f17741e)) {
                return true;
            }
            TbsLog.e(LOGTAG, "can not support x86 devices!!");
            i2 = -104;
        }
        tbsDownloadConfig.setDownloadInterruptCode(i2);
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:110:0x0239  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static boolean a(android.content.Context r23, boolean r24, boolean r25) {
        /*
            Method dump skipped, instructions count: 607
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsDownloader.a(android.content.Context, boolean, boolean):boolean");
    }

    @TargetApi(11)
    private static boolean a(String str, int i2, boolean z, boolean z2, boolean z3) throws Exception {
        int i3;
        JSONObject jSONObject;
        String str2;
        boolean z4;
        Integer num;
        String str3;
        String str4;
        int tbsVersion;
        TbsLog.i(LOGTAG, "[TbsDownloader.readResponse] response=" + str + ";isNeedInstall=" + z3);
        TbsDownloadConfig tbsDownloadConfig = TbsDownloadConfig.getInstance(f17740c);
        if (TextUtils.isEmpty(str)) {
            tbsDownloadConfig.setDownloadInterruptCode(jd.wjweblogin.d.c.q);
            TbsLog.i(LOGTAG, "[TbsDownloader.readResponse] return #1,response is empty...");
            return false;
        }
        JSONObject jSONObject2 = new JSONObject(str);
        int i4 = jSONObject2.getInt("RET");
        if (i4 != 0) {
            tbsDownloadConfig.setDownloadInterruptCode(jd.wjweblogin.d.c.s);
            TbsLog.i(LOGTAG, "[TbsDownloader.readResponse] return #2,returnCode=" + i4);
            return false;
        }
        int i5 = jSONObject2.getInt("RESPONSECODE");
        String string = jSONObject2.getString("DOWNLOADURL");
        String optString = jSONObject2.optString("URLLIST", "");
        int i6 = jSONObject2.getInt("TBSAPKSERVERVERSION");
        if (QbSdk.b(f17740c, i6)) {
            tbsDownloadConfig.setDownloadInterruptCode(-126);
            return false;
        }
        int i7 = jSONObject2.getInt("DOWNLOADMAXFLOW") * 2;
        int i8 = jSONObject2.getInt("DOWNLOAD_MIN_FREE_SPACE");
        int i9 = jSONObject2.getInt("DOWNLOAD_SUCCESS_MAX_RETRYTIMES");
        int i10 = jSONObject2.getInt("DOWNLOAD_FAILED_MAX_RETRYTIMES");
        long j2 = jSONObject2.getLong("DOWNLOAD_SINGLE_TIMEOUT");
        long j3 = jSONObject2.getLong("TBSAPKFILESIZE");
        long optLong = jSONObject2.optLong("RETRY_INTERVAL", 0L);
        int optInt = jSONObject2.optInt("FLOWCTR", -1);
        int optInt2 = jSONObject2.optInt("USEX5", 1);
        h();
        g();
        if (optInt2 == 0 && (tbsVersion = QbSdk.getTbsVersion(f17740c)) != 0) {
            a(tbsVersion);
        }
        try {
            if (!TextUtils.isEmpty(string)) {
                string.contains("" + i6);
            }
            i3 = i10;
        } catch (Throwable th) {
            StringBuilder sb = new StringBuilder();
            i3 = i10;
            sb.append("throwable is ");
            sb.append(Log.getStackTraceString(th));
            TbsLog.i(LOGTAG, sb.toString());
        }
        int optInt3 = jSONObject2.optInt("USEBBACKUPVER", 0);
        try {
            optInt3 = jSONObject2.getInt("USEBBACKUPVER");
        } catch (Exception unused) {
        }
        tbsDownloadConfig.mSyncMap.put(TbsDownloadConfig.TbsConfigKey.KEY_USE_BACKUP_VERSION, Integer.valueOf(optInt3));
        String optString2 = jSONObject2.optString("PKGMD5", null);
        int optInt4 = jSONObject2.optInt("RESETX5", 0);
        jSONObject2.optInt("UPLOADLOG", 0);
        jSONObject2.optInt("RESETDECOUPLECORE", 0);
        String optString3 = jSONObject2.optString("SETTOKEN", "");
        boolean z5 = jSONObject2.optInt("RESETTOKEN", 0) != 0;
        boolean z6 = jSONObject2.optInt("ENABLE_LOAD_RENAME_FILE_LOCK_WAIT", 1) != 0;
        int optInt5 = jSONObject2.optInt("RESETTODECOUPLECORE", 0);
        synchronized (f17742f) {
            if (z5) {
                jSONObject = jSONObject2;
                str2 = optString2;
                tbsDownloadConfig.mSyncMap.put(TbsDownloadConfig.TbsConfigKey.KEY_DESkEY_TOKEN, "");
            } else {
                str2 = optString2;
                jSONObject = jSONObject2;
            }
            if (!TextUtils.isEmpty(optString3) && optString3.length() == 96) {
                tbsDownloadConfig.mSyncMap.put(TbsDownloadConfig.TbsConfigKey.KEY_DESkEY_TOKEN, optString3 + ContainerUtils.FIELD_DELIMITER + com.tencent.smtt.utils.h.c());
            }
        }
        if (optInt4 == 1) {
            tbsDownloadConfig.setDownloadInterruptCode(IMediaPlayer.MEDIA_ERROR_TIMED_OUT);
            QbSdk.reset(f17740c, optInt5 == 1);
            str3 = LOGTAG;
            str4 = "[TbsDownloader.readResponse] return #3,needResetTbs=1,isQuery=" + z;
        } else {
            if (!z6) {
                tbsDownloadConfig.setTbsCoreLoadRenameFileLockWaitEnable(z6);
            }
            long j4 = TbsDownloadConfig.DEFAULT_RETRY_INTERVAL_SEC;
            if (optInt == 1) {
                if (optLong > 604800) {
                    optLong = 604800;
                }
                if (optLong > 0) {
                    j4 = optLong;
                }
                TbsLog.w(LOGTAG, "Download is Flowed, next download request is " + j4 + "s later");
                if (TbsShareManager.isThirdPartyApp(f17740c)) {
                    tbsDownloadConfig.mSyncMap.put(TbsDownloadConfig.TbsConfigKey.KEY_NEEDDOWNLOAD, Boolean.FALSE);
                    tbsDownloadConfig.commit();
                    tbsDownloadConfig.setDownloadInterruptCode(TbsCommonCode.DOWNLOAD_FLOW_CANCEL);
                    return false;
                }
            }
            if (getRetryIntervalInSeconds() >= 0) {
                j4 = getRetryIntervalInSeconds();
            }
            tbsDownloadConfig.mSyncMap.put(TbsDownloadConfig.TbsConfigKey.KEY_RETRY_INTERVAL, Long.valueOf(j4));
            if (TextUtils.isEmpty(string) && TbsShareManager.isThirdPartyApp(f17740c)) {
                tbsDownloadConfig.mSyncMap.put(TbsDownloadConfig.TbsConfigKey.KEY_NEEDDOWNLOAD, Boolean.FALSE);
                tbsDownloadConfig.commit();
                tbsDownloadConfig.setDownloadInterruptCode(-124);
                str3 = LOGTAG;
                str4 = "[TbsDownloader.readResponse] blank url,current app is third app...";
            } else {
                TbsLog.i(LOGTAG, "in response responseCode is " + i5);
                if (i5 == 0) {
                    tbsDownloadConfig.mSyncMap.put(TbsDownloadConfig.TbsConfigKey.KEY_RESPONSECODE, Integer.valueOf(i5));
                    tbsDownloadConfig.mSyncMap.put(TbsDownloadConfig.TbsConfigKey.KEY_NEEDDOWNLOAD, Boolean.FALSE);
                    tbsDownloadConfig.setDownloadInterruptCode(-111);
                    tbsDownloadConfig.commit();
                    TbsLog.i(LOGTAG, "[TbsDownloader.readResponse] return #5,responseCode=0");
                    return false;
                }
                int i11 = TbsDownloadConfig.getInstance(f17740c).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0);
                if (i11 > i6) {
                    f17743g.b();
                    m.a().l(f17740c);
                }
                if (TbsShareManager.isThirdPartyApp(f17740c)) {
                    z4 = false;
                } else {
                    int d2 = m.a().d(f17740c, 0);
                    z4 = d2 >= i6;
                    TbsLog.i(LOGTAG, "tmpCoreVersion is " + d2 + " tbsDownloadVersion is" + i6);
                }
                if (i2 >= i6 || TextUtils.isEmpty(string) || z4) {
                    tbsDownloadConfig.mSyncMap.put(TbsDownloadConfig.TbsConfigKey.KEY_NEEDDOWNLOAD, Boolean.FALSE);
                    tbsDownloadConfig.commit();
                    tbsDownloadConfig.setDownloadInterruptCode(TextUtils.isEmpty(string) ? -124 : i6 <= 0 ? -125 : i2 >= i6 ? -127 : -112);
                    TbsLog.i(LOGTAG, "version error or downloadUrl empty ,return ahead tbsLocalVersion=" + i2 + " tbsDownloadVersion=" + i6 + " tbsLastDownloadVersion=" + i11 + " downloadUrl=" + string);
                    return false;
                }
                if (string.equals(tbsDownloadConfig.mPreferences.getString(TbsDownloadConfig.TbsConfigKey.KEY_TBSDOWNLOADURL, null))) {
                    num = 0;
                } else {
                    f17743g.b();
                    num = 0;
                    tbsDownloadConfig.mSyncMap.put(TbsDownloadConfig.TbsConfigKey.KEY_DOWNLOAD_FAILED_RETRYTIMES, null);
                    tbsDownloadConfig.mSyncMap.put(TbsDownloadConfig.TbsConfigKey.KEY_DOWNLOAD_SUCCESS_RETRYTIMES, null);
                }
                tbsDownloadConfig.mSyncMap.put(TbsDownloadConfig.TbsConfigKey.KEY_TBS_DOWNLOAD_V, Integer.valueOf(i6));
                TbsLog.i(LOGTAG, "put KEY_TBS_DOWNLOAD_V is " + i6);
                if (i6 > 0) {
                    tbsDownloadConfig.mSyncMap.put(TbsDownloadConfig.TbsConfigKey.KEY_TBS_DOWNLOAD_V_TYPE, num);
                }
                tbsDownloadConfig.mSyncMap.put(TbsDownloadConfig.TbsConfigKey.KEY_TBSDOWNLOADURL, string);
                tbsDownloadConfig.mSyncMap.put(TbsDownloadConfig.TbsConfigKey.KEY_DOWNLOADURL_LIST, optString);
                tbsDownloadConfig.mSyncMap.put(TbsDownloadConfig.TbsConfigKey.KEY_RESPONSECODE, Integer.valueOf(i5));
                tbsDownloadConfig.mSyncMap.put(TbsDownloadConfig.TbsConfigKey.KEY_DOWNLOAD_MAXFLOW, Integer.valueOf(i7));
                tbsDownloadConfig.mSyncMap.put(TbsDownloadConfig.TbsConfigKey.KEY_DOWNLOAD_MIN_FREE_SPACE, Integer.valueOf(i8));
                tbsDownloadConfig.mSyncMap.put(TbsDownloadConfig.TbsConfigKey.KEY_DOWNLOAD_SUCCESS_MAX_RETRYTIMES, Integer.valueOf(i9));
                tbsDownloadConfig.mSyncMap.put(TbsDownloadConfig.TbsConfigKey.KEY_DOWNLOAD_FAILED_MAX_RETRYTIMES, Integer.valueOf(i3));
                tbsDownloadConfig.mSyncMap.put(TbsDownloadConfig.TbsConfigKey.KEY_DOWNLOAD_SINGLE_TIMEOUT, Long.valueOf(j2));
                tbsDownloadConfig.mSyncMap.put(TbsDownloadConfig.TbsConfigKey.KEY_TBSAPKFILESIZE, Long.valueOf(j3));
                tbsDownloadConfig.commit();
                if (str2 != null) {
                    tbsDownloadConfig.mSyncMap.put(TbsDownloadConfig.TbsConfigKey.KEY_TBSAPK_MD5, str2);
                }
                if (z2 || !z3 || !m.a().b(f17740c, i6)) {
                    if (!z) {
                        tbsDownloadConfig.setDownloadInterruptCode(-216);
                    }
                    Map<String, Object> map = tbsDownloadConfig.mSyncMap;
                    Boolean bool = Boolean.TRUE;
                    map.put(TbsDownloadConfig.TbsConfigKey.KEY_NEEDDOWNLOAD, bool);
                    TbsLog.i(LOGTAG, "[TbsDownloader.readResponse] ##9 set needDownload=true");
                    if (jSONObject.optInt("stop_pre_oat", 0) == 1) {
                        tbsDownloadConfig.mSyncMap.put(TbsDownloadConfig.TbsConfigKey.KEY_STOP_PRE_OAT, bool);
                    }
                    tbsDownloadConfig.commit();
                    return true;
                }
                tbsDownloadConfig.setDownloadInterruptCode(-213);
                tbsDownloadConfig.mSyncMap.put(TbsDownloadConfig.TbsConfigKey.KEY_NEEDDOWNLOAD, Boolean.FALSE);
                str3 = LOGTAG;
                str4 = "[TbsDownloader.readResponse] ##6 set needDownload=false";
            }
        }
        TbsLog.i(str3, str4);
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:75:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x00a4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String b(android.content.Context r7) {
        /*
            java.lang.String r0 = "ISO8859-1"
            java.lang.String r1 = "UTF-8"
            java.lang.String r2 = com.tencent.smtt.sdk.TbsDownloader.b
            boolean r2 = android.text.TextUtils.isEmpty(r2)
            if (r2 != 0) goto Lf
            java.lang.String r7 = com.tencent.smtt.sdk.TbsDownloader.b
            return r7
        Lf:
            java.util.Locale r2 = java.util.Locale.getDefault()
            java.lang.StringBuffer r3 = new java.lang.StringBuffer
            r3.<init>()
            java.lang.String r4 = android.os.Build.VERSION.RELEASE
            java.lang.String r5 = new java.lang.String     // Catch: java.lang.Exception -> L25
            byte[] r6 = r4.getBytes(r1)     // Catch: java.lang.Exception -> L25
            r5.<init>(r6, r0)     // Catch: java.lang.Exception -> L25
            r4 = r5
            goto L26
        L25:
        L26:
            java.lang.String r5 = "1.0"
            if (r4 != 0) goto L2e
        L2a:
            r3.append(r5)
            goto L37
        L2e:
            int r6 = r4.length()
            if (r6 <= 0) goto L2a
            r3.append(r4)
        L37:
            java.lang.String r4 = "; "
            r3.append(r4)
            java.lang.String r5 = r2.getLanguage()
            if (r5 == 0) goto L59
            java.lang.String r5 = r5.toLowerCase()
            r3.append(r5)
            java.lang.String r2 = r2.getCountry()
            if (r2 == 0) goto L5e
            java.lang.String r5 = "-"
            r3.append(r5)
            java.lang.String r2 = r2.toLowerCase()
            goto L5b
        L59:
            java.lang.String r2 = "en"
        L5b:
            r3.append(r2)
        L5e:
            java.lang.String r2 = android.os.Build.VERSION.CODENAME
            java.lang.String r5 = "REL"
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L8a
            java.lang.String r7 = com.tencent.smtt.utils.s.a(r7)
            java.lang.String r2 = new java.lang.String     // Catch: java.lang.Exception -> L77
            byte[] r1 = r7.getBytes(r1)     // Catch: java.lang.Exception -> L77
            r2.<init>(r1, r0)     // Catch: java.lang.Exception -> L77
            r7 = r2
            goto L78
        L77:
        L78:
            if (r7 != 0) goto L7e
            r3.append(r4)
            goto L8a
        L7e:
            int r0 = r7.length()
            if (r0 <= 0) goto L8a
            r3.append(r4)
            r3.append(r7)
        L8a:
            java.lang.String r7 = android.os.Build.ID
            java.lang.String r0 = ""
            if (r7 != 0) goto L91
            r7 = r0
        L91:
            java.lang.String r1 = "[\u4e00-\u9fa5]"
            java.lang.String r7 = r7.replaceAll(r1, r0)
            java.lang.String r0 = " Build/"
            if (r7 != 0) goto La4
            r3.append(r0)
            java.lang.String r7 = "00"
        La0:
            r3.append(r7)
            goto Lae
        La4:
            int r1 = r7.length()
            if (r1 <= 0) goto Lae
            r3.append(r0)
            goto La0
        Lae:
            r7 = 1
            java.lang.Object[] r7 = new java.lang.Object[r7]
            r0 = 0
            r7[r0] = r3
            java.lang.String r0 = "Mozilla/5.0 (Linux; U; Android %s) AppleWebKit/533.1 (KHTML, like Gecko)Version/4.0 Mobile Safari/533.1"
            java.lang.String r7 = java.lang.String.format(r0, r7)
            com.tencent.smtt.sdk.TbsDownloader.b = r7
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsDownloader.b(android.content.Context):java.lang.String");
    }

    public static boolean b(final boolean z, boolean z2, boolean z3, boolean z4) {
        final TbsDownloadConfig tbsDownloadConfig = TbsDownloadConfig.getInstance(f17740c);
        Map<String, Object> map = QbSdk.o;
        if (map != null && map.containsKey(QbSdk.KEY_SET_SENDREQUEST_AND_UPLOAD) && QbSdk.o.get(QbSdk.KEY_SET_SENDREQUEST_AND_UPLOAD).equals(DYConstants.DY_FALSE)) {
            TbsLog.i(LOGTAG, "[TbsDownloader.sendRequest] -- SET_SENDREQUEST_AND_UPLOAD is false");
            tbsDownloadConfig.setDownloadInterruptCode(-131);
            return false;
        }
        TbsLog.i(LOGTAG, "[TbsDownloader.sendRequest]isQuery: " + z + " forDecoupleCore is " + z3);
        if (m.a().c(f17740c)) {
            TbsLog.i(LOGTAG, "[TbsDownloader.sendRequest] -- isTbsLocalInstalled!");
            tbsDownloadConfig.setDownloadInterruptCode(-132);
            return false;
        }
        if (f17741e == null) {
            String a2 = com.tencent.smtt.utils.b.a();
            f17741e = a2;
            tbsDownloadConfig.mSyncMap.put(TbsDownloadConfig.TbsConfigKey.KEY_DEVICE_CPUABI, a2);
            tbsDownloadConfig.commit();
        }
        if (!TextUtils.isEmpty(f17741e) && !com.tencent.smtt.utils.b.a(f17741e)) {
            tbsDownloadConfig.setDownloadInterruptCode(-104);
            TbsLog.i(LOGTAG, "TbsDownloader sendRequest cpu is invalid:" + f17741e);
            return false;
        }
        tbsDownloadConfig.mSyncMap.put(TbsDownloadConfig.TbsConfigKey.KEY_APP_VERSIONNAME, com.tencent.smtt.utils.b.a(f17740c));
        tbsDownloadConfig.mSyncMap.put(TbsDownloadConfig.TbsConfigKey.KEY_APP_VERSIONCODE, Integer.valueOf(com.tencent.smtt.utils.b.b(f17740c)));
        tbsDownloadConfig.commit();
        JSONObject a3 = a(z, z2, z3);
        int optInt = a3.optInt("TBSV", -1);
        if (optInt != -1) {
            long currentTimeMillis = System.currentTimeMillis();
            if (TbsShareManager.isThirdPartyApp(f17740c)) {
                tbsDownloadConfig.mSyncMap.put(TbsDownloadConfig.TbsConfigKey.KEY_COUNT_REQUEST_FAIL_IN_24HOURS, Long.valueOf(currentTimeMillis - tbsDownloadConfig.mPreferences.getLong(TbsDownloadConfig.TbsConfigKey.KEY_REQUEST_FAIL, 0L) < tbsDownloadConfig.getRetryInterval() * 1000 ? tbsDownloadConfig.mPreferences.getLong(TbsDownloadConfig.TbsConfigKey.KEY_COUNT_REQUEST_FAIL_IN_24HOURS, 0L) + 1 : 1L));
            }
            tbsDownloadConfig.mSyncMap.put(TbsDownloadConfig.TbsConfigKey.KEY_REQUEST_FAIL, Long.valueOf(currentTimeMillis));
            tbsDownloadConfig.mSyncMap.put(TbsDownloadConfig.TbsConfigKey.KEY_APP_VERSIONNAME, com.tencent.smtt.utils.b.a(f17740c));
            tbsDownloadConfig.mSyncMap.put(TbsDownloadConfig.TbsConfigKey.KEY_APP_VERSIONCODE, Integer.valueOf(com.tencent.smtt.utils.b.b(f17740c)));
            tbsDownloadConfig.mSyncMap.put(TbsDownloadConfig.TbsConfigKey.KEY_APP_METADATA, com.tencent.smtt.utils.b.a(f17740c, TBS_METADATA));
            tbsDownloadConfig.commit();
        }
        if (optInt != -1 || z3) {
            try {
                String d2 = com.tencent.smtt.utils.o.a(f17740c).d();
                TbsLog.i(LOGTAG, "[TbsDownloader.sendRequest] postUrl=" + d2);
                if (z) {
                    o = a3;
                } else {
                    p = a3;
                }
                if (z) {
                    return true;
                }
                try {
                    String a4 = com.tencent.smtt.utils.g.a(d2, a3.toString().getBytes("utf-8"), new g.a() { // from class: com.tencent.smtt.sdk.TbsDownloader.2
                        @Override // com.tencent.smtt.utils.g.a
                        public void a(int i2) {
                            TbsDownloadConfig tbsDownloadConfig2;
                            int i3;
                            tbsDownloadConfig.mSyncMap.put(TbsDownloadConfig.TbsConfigKey.KEY_LAST_CHECK, Long.valueOf(System.currentTimeMillis()));
                            tbsDownloadConfig.commit();
                            TbsLog.i(TbsDownloader.LOGTAG, "[TbsDownloader.sendRequest] httpResponseCode=" + i2);
                            if (TbsShareManager.isThirdPartyApp(TbsDownloader.f17740c) && i2 == 200) {
                                tbsDownloadConfig.mSyncMap.put(TbsDownloadConfig.TbsConfigKey.KEY_LAST_REQUEST_SUCCESS, Long.valueOf(System.currentTimeMillis()));
                                tbsDownloadConfig.mSyncMap.put(TbsDownloadConfig.TbsConfigKey.KEY_REQUEST_FAIL, 0L);
                                tbsDownloadConfig.mSyncMap.put(TbsDownloadConfig.TbsConfigKey.KEY_COUNT_REQUEST_FAIL_IN_24HOURS, 0L);
                                tbsDownloadConfig.commit();
                            }
                            if (i2 >= 300) {
                                if (z) {
                                    tbsDownloadConfig2 = tbsDownloadConfig;
                                    i3 = jd.wjweblogin.d.c.o;
                                } else {
                                    tbsDownloadConfig2 = tbsDownloadConfig;
                                    i3 = -207;
                                }
                                tbsDownloadConfig2.setDownloadInterruptCode(i3);
                            }
                        }
                    }, false);
                    if (!TextUtils.isEmpty(a4) && a4.contains("HttpError")) {
                        TbsLog.i(LOGTAG, "Cfg Request error: " + a4);
                        TbsLogReport.TbsLogInfo tbsLogInfo = TbsLogReport.getInstance(f17740c).tbsLogInfo();
                        tbsLogInfo.setErrorCode(-129);
                        tbsLogInfo.setFailDetail(a4);
                        TbsLogReport.getInstance(f17740c).eventReport(TbsLogReport.EventType.TYPE_DOWNLOAD, tbsLogInfo);
                    }
                    return a(a4, optInt, z, z2, z4);
                } catch (Throwable th) {
                    th = th;
                    TbsLog.i(LOGTAG, "sendrequest return false " + Log.getStackTraceString(th));
                    th.printStackTrace();
                    tbsDownloadConfig.setDownloadInterruptCode(jd.wjweblogin.d.c.f20058m);
                    return false;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } else {
            tbsDownloadConfig.setDownloadInterruptCode(-113);
        }
        return false;
    }

    private static synchronized void c() {
        synchronized (TbsDownloader.class) {
            if (f17744h == null) {
                f17744h = TbsHandlerThread.getInstance();
                try {
                    f17743g = new k(f17740c);
                    d = new Handler(f17744h.getLooper()) { // from class: com.tencent.smtt.sdk.TbsDownloader.1
                        @Override // android.os.Handler
                        public void handleMessage(Message message) {
                            int i2 = message.what;
                            if (i2 == 109) {
                                if (TbsDownloader.f17743g != null) {
                                    TbsDownloader.f17743g.e();
                                }
                            } else if (i2 == 110) {
                                if (TbsDownloader.f17740c != null) {
                                    TbsDownloadConfig.getInstance(TbsDownloader.f17740c).saveDownloadInterruptCode();
                                }
                            } else {
                                switch (i2) {
                                    case 100:
                                        boolean z = message.arg1 == 1;
                                        boolean b2 = TbsDownloader.b(true, false, false, message.arg2 == 1);
                                        Object obj = message.obj;
                                        if (obj != null && (obj instanceof TbsDownloaderCallback)) {
                                            TbsLog.i(TbsDownloader.LOGTAG, "needDownload-onNeedDownloadFinish needStartDownload=" + b2);
                                            String str = (TbsDownloader.f17740c == null || TbsDownloader.f17740c.getApplicationContext() == null || TbsDownloader.f17740c.getApplicationContext().getApplicationInfo() == null) ? "" : TbsDownloader.f17740c.getApplicationContext().getApplicationInfo().packageName;
                                            if (b2 && !z) {
                                                if ("com.tencent.mm".equals(str) || "com.tencent.mobileqq".equals(str)) {
                                                    TbsLog.i(TbsDownloader.LOGTAG, "needDownload-onNeedDownloadFinish in mm or QQ callback needStartDownload = " + b2);
                                                }
                                            }
                                            ((TbsDownloaderCallback) message.obj).onNeedDownloadFinish(b2, TbsDownloadConfig.getInstance(TbsDownloader.f17740c).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0));
                                        }
                                        if (TbsShareManager.isThirdPartyApp(TbsDownloader.f17740c) && b2) {
                                            TbsDownloader.startDownload(TbsDownloader.f17740c);
                                            return;
                                        }
                                        return;
                                    case 101:
                                        TbsDownloadConfig tbsDownloadConfig = TbsDownloadConfig.getInstance(TbsDownloader.f17740c);
                                        if (Apn.getApnType(TbsDownloader.f17740c) != 3 && !QbSdk.canDownloadWithoutWifi()) {
                                            TbsLog.i(TbsDownloader.LOGTAG, "not wifi,no need send request");
                                            tbsDownloadConfig.setDownloadInterruptCode(-220);
                                            QbSdk.f17732n.onDownloadFinish(111);
                                            QbSdk.f17732n.onInstallFinish(243);
                                            return;
                                        }
                                        TbsLog.i(TbsDownloader.LOGTAG, "---getting download file lock...");
                                        FileOutputStream b3 = FileUtil.b(TbsDownloader.f17740c, true, "tbs_download_lock_file");
                                        if (b3 == null) {
                                            tbsDownloadConfig.setDownloadInterruptCode(-204);
                                            QbSdk.f17732n.onDownloadFinish(153);
                                            QbSdk.f17732n.onInstallFinish(243);
                                            TbsLog.w(TbsDownloader.LOGTAG, "download file-lock file io exception");
                                            return;
                                        }
                                        FileLock a2 = FileUtil.a(TbsDownloader.f17740c, b3);
                                        if (a2 == null) {
                                            TbsLog.i(TbsDownloader.LOGTAG, "download file-lock locked, core is downloading");
                                            tbsDownloadConfig.setDownloadInterruptCode(-203);
                                            FileUtil.a(b3);
                                            if (QbSdk.c() != null) {
                                                TbsDownloader.a(TbsDownloader.f17740c, new Runnable() { // from class: com.tencent.smtt.sdk.TbsDownloader.1.1
                                                    {
                                                        AnonymousClass1.this = this;
                                                    }

                                                    @Override // java.lang.Runnable
                                                    public void run() {
                                                        QbSdk.f17732n.onDownloadFinish(177);
                                                        QbSdk.f17732n.onInstallFinish(243);
                                                    }
                                                });
                                                return;
                                            }
                                            QbSdk.f17732n.onDownloadFinish(177);
                                            QbSdk.f17732n.onInstallFinish(243);
                                            return;
                                        }
                                        boolean z2 = message.arg1 == 1;
                                        if (TbsDownloader.b(false, z2, false, true)) {
                                            if (z2 && m.a().b(TbsDownloader.f17740c, TbsDownloadConfig.getInstance(TbsDownloader.f17740c).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0))) {
                                                TbsLog.i(TbsDownloader.LOGTAG, "needStartDownload, but try local install core firstly");
                                                QbSdk.f17732n.onDownloadFinish(122);
                                                QbSdk.f17732n.onInstallFinish(243);
                                                tbsDownloadConfig.setDownloadInterruptCode(-213);
                                            } else if (tbsDownloadConfig.mPreferences.getBoolean(TbsDownloadConfig.TbsConfigKey.KEY_NEEDDOWNLOAD, false)) {
                                                TbsDownloadConfig.getInstance(TbsDownloader.f17740c).setDownloadInterruptCode(-215);
                                                TbsLog.i(TbsDownloader.LOGTAG, "start download in apk downloader...");
                                                TbsDownloader.f17743g.a(z2, false);
                                            } else {
                                                tbsDownloadConfig.setDownloadInterruptCode(-133);
                                                QbSdk.f17732n.onDownloadFinish(154);
                                            }
                                            TbsLog.i(TbsDownloader.LOGTAG, "------freeDownloadFileLock...");
                                            FileUtil.a(a2, b3);
                                            return;
                                        }
                                        int currentDownloadInterruptCode = TbsDownloadConfig.getInstance(TbsDownloader.f17740c).getCurrentDownloadInterruptCode();
                                        TbsLog.i(TbsDownloader.LOGTAG, "No need to download, code is " + currentDownloadInterruptCode);
                                        QbSdk.f17732n.onDownloadFinish(currentDownloadInterruptCode);
                                        QbSdk.f17732n.onInstallFinish(243);
                                        TbsLog.i(TbsDownloader.LOGTAG, "------freeDownloadFileLock...");
                                        FileUtil.a(a2, b3);
                                        return;
                                    case 102:
                                        TbsLog.i(TbsDownloader.LOGTAG, "[TbsDownloader.handleMessage] MSG_REPORT_DOWNLOAD_STAT");
                                        int k2 = m.a().k(TbsDownloader.f17740c);
                                        TbsLog.i(TbsDownloader.LOGTAG, "[TbsDownloader.handleMessage] localTbsVersion=" + k2);
                                        TbsDownloader.f17743g.b(k2);
                                        TbsLogReport.getInstance(TbsDownloader.f17740c).dailyReport();
                                        return;
                                    case 103:
                                        TbsLog.i(TbsDownloader.LOGTAG, "[TbsDownloader.handleMessage] MSG_CONTINUEINSTALL_TBSCORE");
                                        if (message.arg1 == 0) {
                                            m.a().a((Context) message.obj, true);
                                            return;
                                        }
                                        return;
                                    default:
                                        return;
                                }
                            }
                        }
                    };
                } catch (Exception e2) {
                    f17746j = true;
                    TbsLog.e(LOGTAG, "TbsApkDownloader init has Exception, " + Log.getStackTraceString(e2));
                }
            }
        }
    }

    @TargetApi(11)
    public static void c(Context context) {
        TbsDownloadConfig.getInstance(context).clear();
        TbsLogReport.getInstance(context).clear();
        k.b(context);
        int i2 = Build.VERSION.SDK_INT;
        (i2 >= 11 ? context.getSharedPreferences("tbs_extension_config", 4) : context.getSharedPreferences("tbs_extension_config", 0)).edit().clear().commit();
        (i2 >= 11 ? context.getSharedPreferences("tbs_preloadx5_check_cfg_file", 4) : context.getSharedPreferences("tbs_preloadx5_check_cfg_file", 0)).edit().clear().commit();
    }

    private static boolean d() {
        try {
            return TbsDownloadConfig.getInstance(f17740c).mPreferences.getString(TbsDownloadConfig.TbsConfigKey.KEY_LAST_THIRDAPP_SENDREQUEST_COREVERSION, "").equals(e().toString());
        } catch (Exception unused) {
            return false;
        }
    }

    private static JSONArray e() {
        if (TbsShareManager.isThirdPartyApp(f17740c)) {
            JSONArray jSONArray = new JSONArray();
            int tbsVersion = QbSdk.getTbsVersion(f17740c);
            if (tbsVersion > 0) {
                jSONArray.put(tbsVersion);
            }
            return jSONArray;
        }
        return null;
    }

    private static boolean f() {
        int i2;
        TbsDownloadConfig tbsDownloadConfig = TbsDownloadConfig.getInstance(f17740c);
        if (tbsDownloadConfig.mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_DOWNLOAD_SUCCESS_RETRYTIMES, 0) >= tbsDownloadConfig.getDownloadSuccessMaxRetrytimes()) {
            TbsLog.i(LOGTAG, "[TbsDownloader.needStartDownload] out of success retrytimes", true);
            i2 = -115;
        } else if (tbsDownloadConfig.mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_DOWNLOAD_FAILED_RETRYTIMES, 0) >= tbsDownloadConfig.getDownloadFailedMaxRetrytimes()) {
            TbsLog.i(LOGTAG, "[TbsDownloader.needStartDownload] out of failed retrytimes", true);
            i2 = -116;
        } else if (FileUtil.b(f17740c)) {
            if (System.currentTimeMillis() - tbsDownloadConfig.mPreferences.getLong(TbsDownloadConfig.TbsConfigKey.KEY_TBSDOWNLOAD_STARTTIME, 0L) <= 86400000) {
                long j2 = tbsDownloadConfig.mPreferences.getLong(TbsDownloadConfig.TbsConfigKey.KEY_TBSDOWNLOAD_FLOW, 0L);
                TbsLog.i(LOGTAG, "[TbsDownloader.needStartDownload] downloadFlow=" + j2);
                if (j2 >= tbsDownloadConfig.getDownloadMaxflow()) {
                    TbsLog.i(LOGTAG, "[TbsDownloader.needStartDownload] failed because you exceeded max flow!", true);
                    i2 = -120;
                }
            }
            return true;
        } else {
            TbsLog.i(LOGTAG, "[TbsDownloader.needStartDownload] local rom freespace limit", true);
            i2 = -117;
        }
        tbsDownloadConfig.setDownloadInterruptCode(i2);
        return false;
    }

    private static void g() {
        File o2 = m.o(f17740c);
        if (o2 == null) {
            return;
        }
        File[] listFiles = o2.listFiles();
        Pattern compile = Pattern.compile("tbs_switch_disable_(.*)");
        for (File file : listFiles) {
            if (compile.matcher(file.getName()).find() && file.isFile() && file.exists() && file.canRead()) {
                TbsLog.i(LOGTAG, "clearTbsCoreDisableFlagFiles: " + file.getName() + "; res: " + file.delete());
            }
        }
    }

    public static String getBackupFileName(boolean z) {
        return getBackupFileName(z, 0);
    }

    public static String getBackupFileName(boolean z, int i2) {
        boolean c2 = i2 == 64 ? true : i2 == 32 ? false : com.tencent.smtt.utils.b.c();
        return z ? c2 ? "x5.tbs.decouple.64" : "x5.tbs.decouple" : c2 ? "x5.tbs.org.64" : "x5.tbs.org";
    }

    public static int getCoreShareDecoupleCoreVersion() {
        return m.a().f(f17740c);
    }

    public static int getCoreShareDecoupleCoreVersionByContext(Context context) {
        return m.a().f(context);
    }

    public static int getNextPostInterval(Context context) {
        TbsDownloadConfig tbsDownloadConfig = TbsDownloadConfig.getInstance(context);
        long j2 = tbsDownloadConfig.mPreferences.getLong(TbsDownloadConfig.TbsConfigKey.KEY_LAST_CHECK, 0L);
        return Math.max((int) (((tbsDownloadConfig.getRetryInterval() * 1000) - (System.currentTimeMillis() - j2)) / 1000), 0);
    }

    public static synchronized boolean getOverSea(Context context) {
        boolean z;
        synchronized (TbsDownloader.class) {
            if (!f17750n) {
                f17750n = true;
                TbsDownloadConfig tbsDownloadConfig = TbsDownloadConfig.getInstance(context);
                if (tbsDownloadConfig.mPreferences.contains(TbsDownloadConfig.TbsConfigKey.KEY_IS_OVERSEA)) {
                    f17749m = tbsDownloadConfig.mPreferences.getBoolean(TbsDownloadConfig.TbsConfigKey.KEY_IS_OVERSEA, false);
                    TbsLog.i(LOGTAG, "[TbsDownloader.getOverSea]  first called. sOverSea = " + f17749m);
                }
                TbsLog.i(LOGTAG, "[TbsDownloader.getOverSea]  sOverSea = " + f17749m);
            }
            z = f17749m;
        }
        return z;
    }

    public static long getRetryIntervalInSeconds() {
        return u;
    }

    public static HandlerThread getsTbsHandlerThread() {
        return f17744h;
    }

    private static void h() {
        File file = new File(m.o(f17740c), "switch_disable_check");
        if (file.exists()) {
            return;
        }
        try {
            TbsLog.i(LOGTAG, "addSwitchDisableCheckFlag status: " + file.createNewFile());
        } catch (IOException e2) {
            TbsLog.i(LOGTAG, "" + e2);
        }
    }

    private static void i() {
        Handler handler = d;
        if (handler != null) {
            handler.obtainMessage(110).sendToTarget();
        }
    }

    public static boolean isDownloadForeground() {
        k kVar = f17743g;
        return kVar != null && kVar.c();
    }

    public static synchronized boolean isDownloading() {
        boolean z;
        synchronized (TbsDownloader.class) {
            TbsLog.i(LOGTAG, "[TbsDownloader.isDownloading] is " + a);
            z = a;
        }
        return z;
    }

    public static boolean isTbsCoreDisabledBySwitch(Context context, int i2) {
        return new File(m.o(context), "tbs_switch_disable_" + i2).exists();
    }

    public static boolean needDownload(Context context, boolean z) {
        return needDownload(context, z, false, true, null);
    }

    public static boolean needDownload(Context context, boolean z, boolean z2, TbsDownloaderCallback tbsDownloaderCallback) {
        return needDownload(context, z, z2, true, tbsDownloaderCallback);
    }

    public static boolean needDownload(Context context, boolean z, boolean z2, boolean z3, TbsDownloaderCallback tbsDownloaderCallback) {
        int i2;
        TbsShareManager.mHasQueried = true;
        Context applicationContext = context.getApplicationContext();
        f17740c = applicationContext;
        TbsDownloadConfig tbsDownloadConfig = TbsDownloadConfig.getInstance(applicationContext);
        tbsDownloadConfig.setDownloadInterruptCode(-100);
        TbsLog.initIfNeed(context);
        TbsLog.i(LOGTAG, "needDownload,process=" + QbSdk.getCurrentProcessName(context) + "stack=" + Log.getStackTraceString(new Throwable()));
        m.a().b(context, f.a == 0);
        int b2 = m.a().b(context);
        TbsLog.i(LOGTAG, "[TbsDownloader.needDownload],renameRet=" + b2);
        if (b2 != 0) {
            TbsLogReport.TbsLogInfo tbsLogInfo = TbsLogReport.getInstance(context).tbsLogInfo();
            tbsLogInfo.a = 129;
            tbsLogInfo.setFailDetail("code=2" + b2);
        }
        if (b2 < 0) {
            TbsLog.i(LOGTAG, "[TbsDownloader.needDownload],needReNameFile=" + b2);
            tbsDownloadConfig.setDownloadInterruptCode(-128);
            if (tbsDownloaderCallback != null) {
                tbsDownloaderCallback.onNeedDownloadFinish(false, 0);
            }
            return false;
        }
        TbsLog.i(LOGTAG, "[TbsDownloader.needDownload] oversea=" + z + ",isDownloadForeground=" + z2);
        if (m.a) {
            TbsLog.i(LOGTAG, "[TbsDownloader.needDownload]#1 Static Installing, return false");
            tbsDownloadConfig.setDownloadInterruptCode(-130);
            if (tbsDownloaderCallback != null) {
                tbsDownloaderCallback.onNeedDownloadFinish(false, 0);
            }
            return false;
        } else if (!a(f17740c, z)) {
            TbsLog.i(LOGTAG, "[TbsDownloader.needDownload]#2 Not shouldDoNeedDownload, return false");
            if (tbsDownloaderCallback != null) {
                tbsDownloaderCallback.onNeedDownloadFinish(false, 0);
            }
            return false;
        } else {
            c();
            if (f17746j) {
                tbsDownloadConfig.setDownloadInterruptCode(jd.wjweblogin.d.c.f20056k);
                TbsLog.i(LOGTAG, "[TbsDownloader.needDownload]#3 TbsApkDownloader init Exception, return false");
                if (tbsDownloaderCallback != null) {
                    tbsDownloaderCallback.onNeedDownloadFinish(false, 0);
                }
                return false;
            }
            boolean a2 = a(f17740c, z2, false);
            TbsLog.i(LOGTAG, "[TbsDownloader.needDownload],needSendRequest=" + a2);
            if (a2) {
                a(z2, tbsDownloaderCallback, z3);
                i2 = -114;
            } else {
                i2 = TbsCommonCode.DOWNLOAD_NO_NEED_REQUEST;
            }
            tbsDownloadConfig.setDownloadInterruptCode(i2);
            d.removeMessages(102);
            Message.obtain(d, 102).sendToTarget();
            boolean contains = tbsDownloadConfig.mPreferences.contains(TbsDownloadConfig.TbsConfigKey.KEY_NEEDDOWNLOAD);
            TbsLog.i(LOGTAG, "[TbsDownloader.needDownload] hasNeedDownloadKey=" + contains);
            boolean z4 = (contains || TbsShareManager.isThirdPartyApp(context)) ? tbsDownloadConfig.mPreferences.getBoolean(TbsDownloadConfig.TbsConfigKey.KEY_NEEDDOWNLOAD, false) : true;
            TbsLog.i(LOGTAG, "[TbsDownloader.needDownload]#4,needDownload=" + z4 + ",hasNeedDownloadKey=" + contains);
            if (!z4) {
                int k2 = m.a().k(f17740c);
                TbsLog.i(LOGTAG, "[TbsDownloader.needDownload]#7,tbsLocalVersion=" + k2 + ",needSendRequest=" + a2);
                if (a2 || k2 <= 0) {
                    d.removeMessages(103);
                    if (k2 <= 0 && !a2) {
                        Message.obtain(d, 103, 0, 0, f17740c).sendToTarget();
                    }
                }
            } else if (f()) {
                TbsLog.i(LOGTAG, "[TbsDownloader.needDownload] NEEDDOWNLOAD_WILL_STARTDOWNLOAD");
            } else {
                TbsLog.i(LOGTAG, "[TbsDownloader.needDownload]#5,set needDownload = false");
                z4 = false;
            }
            if (!a2 && tbsDownloaderCallback != null) {
                tbsDownloaderCallback.onNeedDownloadFinish(z4, 0);
            }
            i();
            return z4;
        }
    }

    public static boolean needDownloadDecoupleCore() {
        int i2;
        if (TbsShareManager.isThirdPartyApp(f17740c) || a(f17740c)) {
            return false;
        }
        return System.currentTimeMillis() - TbsDownloadConfig.getInstance(f17740c).mPreferences.getLong(TbsDownloadConfig.TbsConfigKey.KEY_LAST_DOWNLOAD_DECOUPLE_CORE, 0L) >= TbsDownloadConfig.getInstance(f17740c).getRetryInterval() * 1000 && (i2 = TbsDownloadConfig.getInstance(f17740c).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_DECOUPLECOREVERSION, 0)) > 0 && i2 != m.a().f(f17740c) && TbsDownloadConfig.getInstance(f17740c).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0) != i2;
    }

    public static void pauseDownload() {
        TbsLog.i(LOGTAG, "called pauseDownload,downloader=" + f17743g);
        k kVar = f17743g;
        if (kVar != null) {
            kVar.d();
        }
    }

    public static void resumeDownload() {
        TbsLog.i(LOGTAG, "called resumeDownload,downloader=" + f17743g);
        Handler handler = d;
        if (handler != null) {
            handler.removeMessages(109);
            d.sendEmptyMessage(109);
        }
    }

    public static void setAppContext(Context context) {
        if (context == null || context.getApplicationContext() == null) {
            return;
        }
        f17740c = context.getApplicationContext();
    }

    public static void setRetryIntervalInSeconds(Context context, long j2) {
        if (context == null) {
            return;
        }
        if (context.getApplicationInfo().packageName.equals("com.tencent.qqlive")) {
            u = j2;
        }
        TbsLog.i(LOGTAG, "mRetryIntervalInSeconds is " + u);
    }

    @Deprecated
    public static boolean startDecoupleCoreIfNeeded() {
        return false;
    }

    public static void startDownload(Context context) {
        startDownload(context, false);
    }

    public static synchronized void startDownload(Context context, boolean z) {
        synchronized (TbsDownloader.class) {
            int i2 = 1;
            if (TbsShareManager.isThirdPartyApp(context)) {
                int i3 = f17745i + 1;
                f17745i = i3;
                if (i3 > 1) {
                    TbsLog.w(LOGTAG, "[Warning] for privacy security, TBS Only allow startDownload 1 times each process");
                    QbSdk.f17732n.onDownloadFinish(127);
                    return;
                }
            }
            if (QbSdk.d()) {
                TbsLog.w(LOGTAG, "[warning] using private CDN mode, default downloader can not startDownload.");
                QbSdk.f17732n.onDownloadFinish(135);
                return;
            }
            Context applicationContext = context.getApplicationContext();
            f17740c = applicationContext;
            TbsLog.initIfNeed(applicationContext);
            TbsDownloadConfig tbsDownloadConfig = TbsDownloadConfig.getInstance(f17740c);
            tbsDownloadConfig.setDownloadInterruptCode(-200);
            TbsLog.i(LOGTAG, "[TbsDownloader.startDownload] sAppContext=" + f17740c);
            if (m.a) {
                tbsDownloadConfig.setDownloadInterruptCode(-130);
                QbSdk.f17732n.onDownloadFinish(151);
                return;
            }
            m.a().b(context, f.a == 0);
            int b2 = m.a().b(context);
            TbsLog.i(LOGTAG, "[TbsDownloader.needDownload],renameRet=" + b2);
            if (b2 < 0) {
                tbsDownloadConfig.setDownloadInterruptCode(-128);
                QbSdk.f17732n.onDownloadFinish(152);
                TbsLog.i(LOGTAG, "[TbsDownloader.needDownload],needReNameFile=" + b2);
                return;
            }
            a = true;
            if (Build.VERSION.SDK_INT < 8) {
                tbsDownloadConfig.setDownloadInterruptCode(-201);
                QbSdk.f17732n.onDownloadFinish(150);
                return;
            }
            c();
            if (f17746j) {
                tbsDownloadConfig.setDownloadInterruptCode(-202);
                QbSdk.f17732n.onDownloadFinish(121);
                return;
            }
            if (z) {
                stopDownload();
            }
            d.removeMessages(101);
            d.removeMessages(100);
            Message obtain = Message.obtain(d, 101, QbSdk.f17732n);
            if (!z) {
                i2 = 0;
            }
            obtain.arg1 = i2;
            obtain.sendToTarget();
        }
    }

    public static void stopDownload() {
        if (f17746j) {
            return;
        }
        TbsLog.i(LOGTAG, "[TbsDownloader.stopDownload]");
        k kVar = f17743g;
        if (kVar != null) {
            kVar.a();
        }
        Handler handler = d;
        if (handler != null) {
            handler.removeMessages(100);
            d.removeMessages(101);
            d.removeMessages(108);
        }
    }
}
