package com.tencent.smtt.sdk;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.database.table.SignUpTable;
import com.meizu.cloud.pushsdk.notification.model.AdvanceSetting;
import com.tencent.smtt.sdk.TbsDownloadConfig;
import com.tencent.smtt.sdk.TbsLogReport;
import com.tencent.smtt.utils.Apn;
import com.tencent.smtt.utils.FileUtil;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.Timer;
import com.tencent.smtt.utils.g;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.TimerTask;
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

    /* JADX WARN: Removed duplicated region for block: B:245:0x00a5 A[Catch: Exception -> 0x02ab, TryCatch #0 {Exception -> 0x02ab, blocks: (B:234:0x006e, B:237:0x0082, B:240:0x008b, B:243:0x0095, B:245:0x00a5, B:246:0x00aa, B:248:0x00b7, B:269:0x0143, B:273:0x014d, B:275:0x0155, B:277:0x0176, B:278:0x017b, B:280:0x01c6, B:281:0x01cb, B:285:0x01e3, B:289:0x01ef, B:293:0x01fe, B:294:0x0201, B:298:0x023f, B:302:0x024a, B:303:0x0261, B:305:0x0276, B:307:0x0280, B:309:0x0284, B:312:0x028e, B:314:0x0292, B:315:0x0297, B:317:0x029f, B:319:0x02a6, B:250:0x00c5, B:253:0x00dc, B:255:0x00e8, B:257:0x00f9, B:259:0x0108, B:260:0x0112, B:262:0x0130, B:251:0x00d0, B:241:0x008f), top: B:323:0x006e }] */
    /* JADX WARN: Removed duplicated region for block: B:248:0x00b7 A[Catch: Exception -> 0x02ab, TryCatch #0 {Exception -> 0x02ab, blocks: (B:234:0x006e, B:237:0x0082, B:240:0x008b, B:243:0x0095, B:245:0x00a5, B:246:0x00aa, B:248:0x00b7, B:269:0x0143, B:273:0x014d, B:275:0x0155, B:277:0x0176, B:278:0x017b, B:280:0x01c6, B:281:0x01cb, B:285:0x01e3, B:289:0x01ef, B:293:0x01fe, B:294:0x0201, B:298:0x023f, B:302:0x024a, B:303:0x0261, B:305:0x0276, B:307:0x0280, B:309:0x0284, B:312:0x028e, B:314:0x0292, B:315:0x0297, B:317:0x029f, B:319:0x02a6, B:250:0x00c5, B:253:0x00dc, B:255:0x00e8, B:257:0x00f9, B:259:0x0108, B:260:0x0112, B:262:0x0130, B:251:0x00d0, B:241:0x008f), top: B:323:0x006e }] */
    /* JADX WARN: Removed duplicated region for block: B:249:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:268:0x0142  */
    /* JADX WARN: Removed duplicated region for block: B:270:0x0147  */
    /* JADX WARN: Removed duplicated region for block: B:275:0x0155 A[Catch: Exception -> 0x02ab, TryCatch #0 {Exception -> 0x02ab, blocks: (B:234:0x006e, B:237:0x0082, B:240:0x008b, B:243:0x0095, B:245:0x00a5, B:246:0x00aa, B:248:0x00b7, B:269:0x0143, B:273:0x014d, B:275:0x0155, B:277:0x0176, B:278:0x017b, B:280:0x01c6, B:281:0x01cb, B:285:0x01e3, B:289:0x01ef, B:293:0x01fe, B:294:0x0201, B:298:0x023f, B:302:0x024a, B:303:0x0261, B:305:0x0276, B:307:0x0280, B:309:0x0284, B:312:0x028e, B:314:0x0292, B:315:0x0297, B:317:0x029f, B:319:0x02a6, B:250:0x00c5, B:253:0x00dc, B:255:0x00e8, B:257:0x00f9, B:259:0x0108, B:260:0x0112, B:262:0x0130, B:251:0x00d0, B:241:0x008f), top: B:323:0x006e }] */
    /* JADX WARN: Removed duplicated region for block: B:280:0x01c6 A[Catch: Exception -> 0x02ab, TryCatch #0 {Exception -> 0x02ab, blocks: (B:234:0x006e, B:237:0x0082, B:240:0x008b, B:243:0x0095, B:245:0x00a5, B:246:0x00aa, B:248:0x00b7, B:269:0x0143, B:273:0x014d, B:275:0x0155, B:277:0x0176, B:278:0x017b, B:280:0x01c6, B:281:0x01cb, B:285:0x01e3, B:289:0x01ef, B:293:0x01fe, B:294:0x0201, B:298:0x023f, B:302:0x024a, B:303:0x0261, B:305:0x0276, B:307:0x0280, B:309:0x0284, B:312:0x028e, B:314:0x0292, B:315:0x0297, B:317:0x029f, B:319:0x02a6, B:250:0x00c5, B:253:0x00dc, B:255:0x00e8, B:257:0x00f9, B:259:0x0108, B:260:0x0112, B:262:0x0130, B:251:0x00d0, B:241:0x008f), top: B:323:0x006e }] */
    /* JADX WARN: Removed duplicated region for block: B:283:0x01e0  */
    /* JADX WARN: Removed duplicated region for block: B:284:0x01e2  */
    /* JADX WARN: Removed duplicated region for block: B:287:0x01ec  */
    /* JADX WARN: Removed duplicated region for block: B:288:0x01ee  */
    /* JADX WARN: Removed duplicated region for block: B:292:0x01fd  */
    /* JADX WARN: Removed duplicated region for block: B:296:0x023b  */
    /* JADX WARN: Removed duplicated region for block: B:312:0x028e A[Catch: Exception -> 0x02ab, TryCatch #0 {Exception -> 0x02ab, blocks: (B:234:0x006e, B:237:0x0082, B:240:0x008b, B:243:0x0095, B:245:0x00a5, B:246:0x00aa, B:248:0x00b7, B:269:0x0143, B:273:0x014d, B:275:0x0155, B:277:0x0176, B:278:0x017b, B:280:0x01c6, B:281:0x01cb, B:285:0x01e3, B:289:0x01ef, B:293:0x01fe, B:294:0x0201, B:298:0x023f, B:302:0x024a, B:303:0x0261, B:305:0x0276, B:307:0x0280, B:309:0x0284, B:312:0x028e, B:314:0x0292, B:315:0x0297, B:317:0x029f, B:319:0x02a6, B:250:0x00c5, B:253:0x00dc, B:255:0x00e8, B:257:0x00f9, B:259:0x0108, B:260:0x0112, B:262:0x0130, B:251:0x00d0, B:241:0x008f), top: B:323:0x006e }] */
    /* JADX WARN: Removed duplicated region for block: B:314:0x0292 A[Catch: Exception -> 0x02ab, TryCatch #0 {Exception -> 0x02ab, blocks: (B:234:0x006e, B:237:0x0082, B:240:0x008b, B:243:0x0095, B:245:0x00a5, B:246:0x00aa, B:248:0x00b7, B:269:0x0143, B:273:0x014d, B:275:0x0155, B:277:0x0176, B:278:0x017b, B:280:0x01c6, B:281:0x01cb, B:285:0x01e3, B:289:0x01ef, B:293:0x01fe, B:294:0x0201, B:298:0x023f, B:302:0x024a, B:303:0x0261, B:305:0x0276, B:307:0x0280, B:309:0x0284, B:312:0x028e, B:314:0x0292, B:315:0x0297, B:317:0x029f, B:319:0x02a6, B:250:0x00c5, B:253:0x00dc, B:255:0x00e8, B:257:0x00f9, B:259:0x0108, B:260:0x0112, B:262:0x0130, B:251:0x00d0, B:241:0x008f), top: B:323:0x006e }] */
    /* JADX WARN: Removed duplicated region for block: B:317:0x029f A[Catch: Exception -> 0x02ab, TryCatch #0 {Exception -> 0x02ab, blocks: (B:234:0x006e, B:237:0x0082, B:240:0x008b, B:243:0x0095, B:245:0x00a5, B:246:0x00aa, B:248:0x00b7, B:269:0x0143, B:273:0x014d, B:275:0x0155, B:277:0x0176, B:278:0x017b, B:280:0x01c6, B:281:0x01cb, B:285:0x01e3, B:289:0x01ef, B:293:0x01fe, B:294:0x0201, B:298:0x023f, B:302:0x024a, B:303:0x0261, B:305:0x0276, B:307:0x0280, B:309:0x0284, B:312:0x028e, B:314:0x0292, B:315:0x0297, B:317:0x029f, B:319:0x02a6, B:250:0x00c5, B:253:0x00dc, B:255:0x00e8, B:257:0x00f9, B:259:0x0108, B:260:0x0112, B:262:0x0130, B:251:0x00d0, B:241:0x008f), top: B:323:0x006e }] */
    /* JADX WARN: Removed duplicated region for block: B:319:0x02a6 A[Catch: Exception -> 0x02ab, TRY_LEAVE, TryCatch #0 {Exception -> 0x02ab, blocks: (B:234:0x006e, B:237:0x0082, B:240:0x008b, B:243:0x0095, B:245:0x00a5, B:246:0x00aa, B:248:0x00b7, B:269:0x0143, B:273:0x014d, B:275:0x0155, B:277:0x0176, B:278:0x017b, B:280:0x01c6, B:281:0x01cb, B:285:0x01e3, B:289:0x01ef, B:293:0x01fe, B:294:0x0201, B:298:0x023f, B:302:0x024a, B:303:0x0261, B:305:0x0276, B:307:0x0280, B:309:0x0284, B:312:0x028e, B:314:0x0292, B:315:0x0297, B:317:0x029f, B:319:0x02a6, B:250:0x00c5, B:253:0x00dc, B:255:0x00e8, B:257:0x00f9, B:259:0x0108, B:260:0x0112, B:262:0x0130, B:251:0x00d0, B:241:0x008f), top: B:323:0x006e }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static JSONObject a(boolean z, boolean z2, boolean z3) {
        int i2;
        int g2;
        Object a2;
        TbsLog.i(LOGTAG, "[TbsDownloader.postJsonData]isQuery: " + z + " forDecoupleCore is " + z3);
        TbsDownloadConfig tbsDownloadConfig = TbsDownloadConfig.getInstance(f17740c);
        String b2 = b(f17740c);
        String e2 = com.tencent.smtt.utils.b.e(f17740c);
        String d2 = com.tencent.smtt.utils.b.d(f17740c);
        String g3 = com.tencent.smtt.utils.b.g(f17740c);
        String id = TimeZone.getDefault().getID();
        String str = id != null ? id : "";
        try {
            if (((TelephonyManager) f17740c.getSystemService(SignUpTable.TB_COLUMN_PHONE)) != null) {
                id = AdvanceSetting.CLEAR_NOTIFICATION;
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        if (id == null) {
            id = "";
        }
        JSONObject jSONObject = new JSONObject();
        if (l.a(f17740c).c("tpatch_num") >= 5) {
            i2 = 0;
        } else if (!TbsShareManager.isThirdPartyApp(f17740c)) {
            jSONObject.put("REQUEST_TPATCH", 1);
            jSONObject.put("TIMEZONEID", str);
            jSONObject.put("COUNTRYISO", id);
            if (com.tencent.smtt.utils.b.c()) {
                jSONObject.put("REQUEST_64", 1);
            }
            jSONObject.put("PROTOCOLVERSION", 1);
            if (TbsShareManager.isThirdPartyApp(f17740c)) {
                g2 = z3 ? m.a().g(f17740c) : m.a().k(f17740c);
                if (g2 == 0 && m.a().j(f17740c)) {
                    g2 = -1;
                    if ("com.tencent.mobileqq".equals(f17740c.getApplicationInfo().packageName)) {
                        TbsPVConfig.releaseInstance();
                        if (TbsPVConfig.getInstance(f17740c).getLocalCoreVersionMoreTimes() == 1) {
                            g2 = m.a().g(f17740c);
                        }
                    }
                }
                TbsLog.i(LOGTAG, "[TbsDownloader.postJsonData] tbsLocalVersion=" + g2 + " isDownloadForeground=" + z2);
                if (z2 && !m.a().j(f17740c)) {
                    g2 = 0;
                }
            } else {
                g2 = m.a().k(f17740c);
            }
            jSONObject.put("FUNCTION", !z ? 2 : g2 == 0 ? 0 : 1);
            if (TbsShareManager.isThirdPartyApp(f17740c)) {
                JSONArray e4 = e();
                jSONObject.put("TBSVLARR", e4);
                f17747k = e4.toString();
                tbsDownloadConfig.mSyncMap.put(TbsDownloadConfig.TbsConfigKey.KEY_LAST_THIRDAPP_SENDREQUEST_COREVERSION, e4.toString());
                tbsDownloadConfig.commit();
                if (QbSdk.f17722c) {
                    jSONObject.put("THIRDREQ", 1);
                }
            }
            jSONObject.put("APPN", f17740c.getPackageName());
            jSONObject.put("APPVN", a(tbsDownloadConfig.mPreferences.getString(TbsDownloadConfig.TbsConfigKey.KEY_APP_VERSIONNAME, null)));
            jSONObject.put("APPVC", tbsDownloadConfig.mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_APP_VERSIONCODE, 0));
            jSONObject.put("APPMETA", a(tbsDownloadConfig.mPreferences.getString(TbsDownloadConfig.TbsConfigKey.KEY_APP_METADATA, null)));
            jSONObject.put("TBSSDKV", 44286);
            jSONObject.put("TBSV", g2);
            if (g2 == 0) {
                jSONObject.put("ISFORCE", true);
            }
            f17748l = "" + g2;
            jSONObject.put("DOWNLOADDECOUPLECORE", !z3 ? 1 : 0);
            tbsDownloadConfig.mSyncMap.put(TbsDownloadConfig.TbsConfigKey.KEY_DOWNLOADDECOUPLECORE, Integer.valueOf(!z3 ? 1 : 0));
            tbsDownloadConfig.commit();
            if (g2 != 0) {
                jSONObject.put("TBSBACKUPV", 0);
            }
            jSONObject.put("CPU", f17741e);
            jSONObject.put("UA", b2);
            jSONObject.put("IMSI", a(e2));
            jSONObject.put("IMEI", a(d2));
            jSONObject.put("ANDROID_ID", a(g3));
            jSONObject.put(jd.wjlogin_sdk.util.e.d, com.tencent.smtt.utils.b.c(f17740c));
            if (!TbsShareManager.isThirdPartyApp(f17740c)) {
                jSONObject.put("STATUS", (g2 == 0 || QbSdk.a(f17740c, g2)) ? 0 : 1);
                int f2 = m.a().f(f17740c);
                jSONObject.put("TBSDV", g2 % 10000);
                jSONObject.put("TBSBACKUPV", f2);
            }
            if ((QbSdk.isEnableSensitiveApi() || (a2 = QbSdk.a(f17740c, "can_unlzma", (Bundle) null)) == null || !(a2 instanceof Boolean)) ? false : ((Boolean) a2).booleanValue() ? !TbsDownloadConfig.getInstance(f17740c).mPreferences.getBoolean(TbsDownloadConfig.TbsConfigKey.KEY_FULL_PACKAGE, false) : false) {
                jSONObject.put("REQUEST_LZMA", 1);
            }
            if (getOverSea(f17740c)) {
                jSONObject.put("OVERSEA", 1);
            }
            if (z2) {
                jSONObject.put("DOWNLOAD_FOREGROUND", 1);
            }
            TbsLog.i(LOGTAG, "[TbsDownloader.postJsonData] jsonData=" + jSONObject.toString());
            return jSONObject;
        } else {
            i2 = 2;
        }
        jSONObject.put("REQUEST_TPATCH", i2);
        jSONObject.put("TIMEZONEID", str);
        jSONObject.put("COUNTRYISO", id);
        if (com.tencent.smtt.utils.b.c()) {
        }
        jSONObject.put("PROTOCOLVERSION", 1);
        if (TbsShareManager.isThirdPartyApp(f17740c)) {
        }
        jSONObject.put("FUNCTION", !z ? 2 : g2 == 0 ? 0 : 1);
        if (TbsShareManager.isThirdPartyApp(f17740c)) {
        }
        jSONObject.put("APPN", f17740c.getPackageName());
        jSONObject.put("APPVN", a(tbsDownloadConfig.mPreferences.getString(TbsDownloadConfig.TbsConfigKey.KEY_APP_VERSIONNAME, null)));
        jSONObject.put("APPVC", tbsDownloadConfig.mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_APP_VERSIONCODE, 0));
        jSONObject.put("APPMETA", a(tbsDownloadConfig.mPreferences.getString(TbsDownloadConfig.TbsConfigKey.KEY_APP_METADATA, null)));
        jSONObject.put("TBSSDKV", 44286);
        jSONObject.put("TBSV", g2);
        if (g2 == 0) {
        }
        f17748l = "" + g2;
        jSONObject.put("DOWNLOADDECOUPLECORE", !z3 ? 1 : 0);
        tbsDownloadConfig.mSyncMap.put(TbsDownloadConfig.TbsConfigKey.KEY_DOWNLOADDECOUPLECORE, Integer.valueOf(!z3 ? 1 : 0));
        tbsDownloadConfig.commit();
        if (g2 != 0) {
        }
        jSONObject.put("CPU", f17741e);
        jSONObject.put("UA", b2);
        jSONObject.put("IMSI", a(e2));
        jSONObject.put("IMEI", a(d2));
        jSONObject.put("ANDROID_ID", a(g3));
        jSONObject.put(jd.wjlogin_sdk.util.e.d, com.tencent.smtt.utils.b.c(f17740c));
        if (!TbsShareManager.isThirdPartyApp(f17740c)) {
        }
        if ((QbSdk.isEnableSensitiveApi() || (a2 = QbSdk.a(f17740c, "can_unlzma", (Bundle) null)) == null || !(a2 instanceof Boolean)) ? false : ((Boolean) a2).booleanValue() ? !TbsDownloadConfig.getInstance(f17740c).mPreferences.getBoolean(TbsDownloadConfig.TbsConfigKey.KEY_FULL_PACKAGE, false) : false) {
        }
        if (getOverSea(f17740c)) {
        }
        if (z2) {
        }
        TbsLog.i(LOGTAG, "[TbsDownloader.postJsonData] jsonData=" + jSONObject.toString());
        return jSONObject;
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
            /* JADX WARN: Removed duplicated region for block: B:105:0x0073  */
            /* JADX WARN: Removed duplicated region for block: B:119:? A[RETURN, SYNTHETIC] */
            @Override // java.lang.Thread, java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public void run() {
                FileOutputStream fileOutputStream;
                final FileChannel fileChannel;
                Runnable runnable2;
                final Timer c2;
                FileLock fileLock = null;
                try {
                    try {
                        c2 = QbSdk.c();
                        fileOutputStream = FileUtil.b(context, true, "tbs_download_lock_file");
                    } catch (Exception e2) {
                        e = e2;
                        fileOutputStream = null;
                        fileChannel = null;
                    } catch (Throwable th) {
                        th = th;
                        fileOutputStream = null;
                        fileChannel = null;
                    }
                    if (fileOutputStream == null || c2 == null) {
                        fileChannel = null;
                        FileUtil.a(fileLock, fileOutputStream);
                        TbsLog.i(TbsDownloader.LOGTAG, "release wait download lock");
                        FileUtil.a(fileChannel);
                        FileUtil.a(fileOutputStream);
                        runnable2 = runnable;
                        if (runnable2 != null) {
                            runnable2.run();
                            return;
                        }
                        return;
                    }
                    try {
                        fileChannel = fileOutputStream.getChannel();
                    } catch (Exception e3) {
                        e = e3;
                        fileChannel = null;
                    } catch (Throwable th2) {
                        th = th2;
                        fileChannel = null;
                        FileUtil.a(fileLock, fileOutputStream);
                        TbsLog.i(TbsDownloader.LOGTAG, "release wait download lock");
                        FileUtil.a(fileChannel);
                        FileUtil.a(fileOutputStream);
                        throw th;
                    }
                    try {
                        new java.util.Timer().schedule(new TimerTask() { // from class: com.tencent.smtt.sdk.TbsDownloader.3.1
                            {
                                AnonymousClass3.this = this;
                            }

                            @Override // java.util.TimerTask, java.lang.Runnable
                            public void run() {
                                FileChannel fileChannel2 = fileChannel;
                                if (fileChannel2 == null || !fileChannel2.isOpen()) {
                                    return;
                                }
                                try {
                                    fileChannel.close();
                                    c2.onTimeOut();
                                    TbsLog.i(TbsDownloader.LOGTAG, "wait download process lock timeout");
                                } catch (IOException e4) {
                                    e4.printStackTrace();
                                }
                            }
                        }, c2.timeOut);
                        TbsLog.i(TbsDownloader.LOGTAG, "start waiting other process Download finished");
                        fileLock = fileChannel.lock();
                        TbsLog.i(TbsDownloader.LOGTAG, "wait lock success");
                    } catch (Exception e4) {
                        e = e4;
                        TbsLog.w(TbsDownloader.LOGTAG, "MultiProcessSyncInit exception: " + Log.getStackTraceString(e));
                        FileUtil.a(fileLock, fileOutputStream);
                        TbsLog.i(TbsDownloader.LOGTAG, "release wait download lock");
                        FileUtil.a(fileChannel);
                        FileUtil.a(fileOutputStream);
                        runnable2 = runnable;
                        if (runnable2 != null) {
                        }
                    }
                    FileUtil.a(fileLock, fileOutputStream);
                    TbsLog.i(TbsDownloader.LOGTAG, "release wait download lock");
                    FileUtil.a(fileChannel);
                    FileUtil.a(fileOutputStream);
                    runnable2 = runnable;
                    if (runnable2 != null) {
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
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

    /* JADX WARN: Removed duplicated region for block: B:167:0x0239  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static boolean a(Context context, boolean z, boolean z2) {
        String str;
        boolean z3;
        String str2;
        TbsDownloadConfig tbsDownloadConfig = TbsDownloadConfig.getInstance(context);
        if (z) {
            str = "needSendRequest true download foreground, stack: " + Log.getStackTraceString(new Throwable());
        } else {
            String string = tbsDownloadConfig.mPreferences.getString(TbsDownloadConfig.TbsConfigKey.KEY_APP_VERSIONNAME, null);
            int i2 = tbsDownloadConfig.mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_APP_VERSIONCODE, 0);
            String string2 = tbsDownloadConfig.mPreferences.getString(TbsDownloadConfig.TbsConfigKey.KEY_APP_METADATA, null);
            String a2 = com.tencent.smtt.utils.b.a(f17740c);
            int b2 = com.tencent.smtt.utils.b.b(f17740c);
            String a3 = com.tencent.smtt.utils.b.a(f17740c, TBS_METADATA);
            TbsLog.i(LOGTAG, "[TbsDownloader.needSendQueryRequest] appVersionName=" + a2 + " oldAppVersionName=" + string + " appVersionCode=" + b2 + " oldAppVersionCode=" + i2 + " appMetadata=" + a3 + " oldAppVersionMetadata=" + string2);
            long currentTimeMillis = System.currentTimeMillis();
            long j2 = tbsDownloadConfig.mPreferences.getLong(TbsDownloadConfig.TbsConfigKey.KEY_LAST_CHECK, 0L);
            StringBuilder sb = new StringBuilder();
            sb.append("[TbsDownloader.needSendQueryRequest] timeLastCheck=");
            sb.append(j2);
            sb.append(" timeNow=");
            sb.append(currentTimeMillis);
            TbsLog.i(LOGTAG, sb.toString());
            if (z2) {
                boolean contains = tbsDownloadConfig.mPreferences.contains(TbsDownloadConfig.TbsConfigKey.KEY_LAST_CHECK);
                TbsLog.i(LOGTAG, "[TbsDownloader.needSendQueryRequest] hasLaskCheckKey=" + contains);
                if (contains && j2 == 0) {
                    j2 = currentTimeMillis;
                }
            }
            long j3 = tbsDownloadConfig.mPreferences.getLong(TbsDownloadConfig.TbsConfigKey.KEY_LAST_REQUEST_SUCCESS, 0L);
            long j4 = tbsDownloadConfig.mPreferences.getLong(TbsDownloadConfig.TbsConfigKey.KEY_COUNT_REQUEST_FAIL_IN_24HOURS, 0L);
            long retryInterval = tbsDownloadConfig.getRetryInterval();
            TbsLog.i(LOGTAG, "retryInterval = " + retryInterval + " s");
            TbsPVConfig.releaseInstance();
            int emergentCoreVersion = TbsPVConfig.getInstance(f17740c).getEmergentCoreVersion();
            int i3 = TbsDownloadConfig.getInstance(f17740c).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0);
            long j5 = currentTimeMillis - j2;
            long j6 = retryInterval * 1000;
            if (j5 > j6) {
                str = "needSendRequest  true  #1";
            } else if (emergentCoreVersion > m.a().g(f17740c) && emergentCoreVersion > i3) {
                str = "emergentCoreVersion is " + emergentCoreVersion + " getTbsCoreInstalledVerInNolock is " + m.a().g(f17740c) + " responseCoreVersion is " + i3;
            } else if (!TbsShareManager.isThirdPartyApp(f17740c) || j3 <= 0 || currentTimeMillis - j3 <= j6 || j4 >= 11) {
                if (TbsShareManager.isThirdPartyApp(f17740c) && QbSdk.getTbsVersion(f17740c) == 0 && !d()) {
                    TbsLog.i(LOGTAG, "needSendRequest  true  #4");
                    m.a().d(f17740c);
                    str2 = null;
                    z3 = true;
                    if (!z3) {
                    }
                    return z3;
                }
                if (a2 == null || b2 == 0 || a3 == null) {
                    if (TbsShareManager.isThirdPartyApp(f17740c)) {
                        str2 = "timeNow - timeLastCheck is " + j5 + " sendRequestWithSameHostCoreVersion() is " + d() + " appVersionName is " + a2 + " appVersionCode is " + b2 + " appMetadata is " + a3 + " oldAppVersionName is " + string + " oldAppVersionCode is " + i2 + " oldAppVersionMetadata is " + string2;
                        z3 = false;
                        if (!z3 && TbsShareManager.isThirdPartyApp(f17740c)) {
                            TbsLogReport.TbsLogInfo tbsLogInfo = TbsLogReport.getInstance(f17740c).tbsLogInfo();
                            tbsLogInfo.setErrorCode(-119);
                            tbsLogInfo.setFailDetail(str2);
                            TbsLogReport.getInstance(f17740c).eventReport(TbsLogReport.EventType.TYPE_DOWNLOAD, tbsLogInfo);
                        }
                        return z3;
                    }
                } else if (!a2.equals(string) || b2 != i2 || !a3.equals(string2)) {
                    str = "needSendRequest  true  #5";
                }
                str2 = null;
                z3 = false;
                if (!z3) {
                    TbsLogReport.TbsLogInfo tbsLogInfo2 = TbsLogReport.getInstance(f17740c).tbsLogInfo();
                    tbsLogInfo2.setErrorCode(-119);
                    tbsLogInfo2.setFailDetail(str2);
                    TbsLogReport.getInstance(f17740c).eventReport(TbsLogReport.EventType.TYPE_DOWNLOAD, tbsLogInfo2);
                }
                return z3;
            } else {
                str = "needSendRequest  true  #3";
            }
        }
        TbsLog.i(LOGTAG, str);
        str2 = null;
        z3 = true;
        if (!z3) {
        }
        return z3;
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

    /* JADX WARN: Removed duplicated region for block: B:125:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x00a4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String b(Context context) {
        String str;
        String str2;
        String replaceAll;
        if (TextUtils.isEmpty(b)) {
            Locale locale = Locale.getDefault();
            StringBuffer stringBuffer = new StringBuffer();
            String str3 = Build.VERSION.RELEASE;
            try {
                str3 = new String(str3.getBytes("UTF-8"), "ISO8859-1");
            } catch (Exception unused) {
            }
            if (str3 != null && str3.length() > 0) {
                stringBuffer.append(str3);
            } else {
                stringBuffer.append("1.0");
            }
            stringBuffer.append("; ");
            String language = locale.getLanguage();
            if (language != null) {
                stringBuffer.append(language.toLowerCase());
                String country = locale.getCountry();
                if (country != null) {
                    stringBuffer.append("-");
                    str = country.toLowerCase();
                }
                if ("REL".equals(Build.VERSION.CODENAME)) {
                    String a2 = com.tencent.smtt.utils.s.a(context);
                    try {
                        a2 = new String(a2.getBytes("UTF-8"), "ISO8859-1");
                    } catch (Exception unused2) {
                    }
                    if (a2 == null) {
                        stringBuffer.append("; ");
                    } else if (a2.length() > 0) {
                        stringBuffer.append("; ");
                        stringBuffer.append(a2);
                    }
                }
                str2 = Build.ID;
                if (str2 == null) {
                    str2 = "";
                }
                replaceAll = str2.replaceAll("[\u4e00-\u9fa5]", "");
                if (replaceAll == null) {
                    if (replaceAll.length() > 0) {
                        stringBuffer.append(" Build/");
                    }
                    String format = String.format("Mozilla/5.0 (Linux; U; Android %s) AppleWebKit/533.1 (KHTML, like Gecko)Version/4.0 Mobile Safari/533.1", stringBuffer);
                    b = format;
                    return format;
                }
                stringBuffer.append(" Build/");
                replaceAll = "00";
                stringBuffer.append(replaceAll);
                String format2 = String.format("Mozilla/5.0 (Linux; U; Android %s) AppleWebKit/533.1 (KHTML, like Gecko)Version/4.0 Mobile Safari/533.1", stringBuffer);
                b = format2;
                return format2;
            }
            str = "en";
            stringBuffer.append(str);
            if ("REL".equals(Build.VERSION.CODENAME)) {
            }
            str2 = Build.ID;
            if (str2 == null) {
            }
            replaceAll = str2.replaceAll("[\u4e00-\u9fa5]", "");
            if (replaceAll == null) {
            }
            stringBuffer.append(replaceAll);
            String format22 = String.format("Mozilla/5.0 (Linux; U; Android %s) AppleWebKit/533.1 (KHTML, like Gecko)Version/4.0 Mobile Safari/533.1", stringBuffer);
            b = format22;
            return format22;
        }
        return b;
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
