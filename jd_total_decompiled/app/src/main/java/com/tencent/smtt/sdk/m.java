package com.tencent.smtt.sdk;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import com.jingdong.common.utils.LangUtils;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.TbsDownloadConfig;
import com.tencent.smtt.utils.FileUtil;
import com.tencent.smtt.utils.TbsLog;
import dalvik.system.DexClassLoader;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileLock;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public class m {

    /* renamed from: c  reason: collision with root package name */
    private static m f17846c;

    /* renamed from: e  reason: collision with root package name */
    private FileLock f17853e;

    /* renamed from: f  reason: collision with root package name */
    private FileOutputStream f17854f;
    private QbSdk.a o;

    /* renamed from: h  reason: collision with root package name */
    private static final ReentrantLock f17847h = new ReentrantLock();

    /* renamed from: i  reason: collision with root package name */
    private static final Lock f17848i = new ReentrantLock();

    /* renamed from: k  reason: collision with root package name */
    private static FileLock f17849k = null;

    /* renamed from: l  reason: collision with root package name */
    private static final ThreadLocal<Integer> f17850l = new ThreadLocal<Integer>() { // from class: com.tencent.smtt.sdk.m.1
        @Override // java.lang.ThreadLocal
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public Integer initialValue() {
            return 0;
        }
    };

    /* renamed from: m  reason: collision with root package name */
    private static Handler f17851m = null;
    static boolean a = false;
    static final FileFilter b = new FileFilter() { // from class: com.tencent.smtt.sdk.m.2
        @Override // java.io.FileFilter
        public boolean accept(File file) {
            String name = file.getName();
            if (name == null || name.endsWith(".jar_is_first_load_dex_flag_file")) {
                return false;
            }
            int i2 = Build.VERSION.SDK_INT;
            if (i2 < 21 || !name.endsWith(".dex")) {
                if (i2 < 26 || !name.endsWith(".prof")) {
                    return i2 < 26 || !name.equals("oat");
                }
                return false;
            }
            return false;
        }
    };

    /* renamed from: n  reason: collision with root package name */
    private static int f17852n = 0;
    private int d = 0;

    /* renamed from: g  reason: collision with root package name */
    private boolean f17855g = false;

    /* renamed from: j  reason: collision with root package name */
    private boolean f17856j = false;
    private int p = -1;

    private m() {
        if (f17851m == null) {
            f17851m = new Handler(TbsHandlerThread.getInstance().getLooper()) { // from class: com.tencent.smtt.sdk.m.3
                @Override // android.os.Handler
                public void handleMessage(Message message) {
                    QbSdk.setTBSInstallingStatus(true);
                    int i2 = message.what;
                    if (i2 == 1) {
                        TbsLog.i("TbsInstaller", "TbsInstaller--handleMessage--MSG_INSTALL_TBS_CORE");
                        Object[] objArr = (Object[]) message.obj;
                        m.this.b((Context) objArr[0], (String) objArr[1], ((Integer) objArr[2]).intValue());
                    } else if (i2 == 2) {
                        TbsLog.i("TbsInstaller", "TbsInstaller--handleMessage--MSG_COPY_TBS_CORE");
                        Object[] objArr2 = (Object[]) message.obj;
                        m.this.a((Context) objArr2[0], (Context) objArr2[1], ((Integer) objArr2[2]).intValue());
                    } else if (i2 == 3) {
                        TbsLog.i("TbsInstaller", "TbsInstaller--handleMessage--MSG_INSTALL_TBS_CORE_EX");
                        Object[] objArr3 = (Object[]) message.obj;
                        m.this.b((Context) objArr3[0], (Bundle) objArr3[1]);
                    } else if (i2 != 4) {
                    } else {
                        TbsLog.i("TbsInstaller", "TbsInstaller--handleMessage--MSG_UNZIP_TBS_CORE");
                        Object[] objArr4 = (Object[]) message.obj;
                        m.this.a((Context) objArr4[0], (File) objArr4[1], ((Integer) objArr4[2]).intValue());
                        QbSdk.setTBSInstallingStatus(false);
                        super.handleMessage(message);
                    }
                }
            };
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized m a() {
        m mVar;
        synchronized (m.class) {
            if (f17846c == null) {
                synchronized (m.class) {
                    if (f17846c == null) {
                        f17846c = new m();
                    }
                }
            }
            mVar = f17846c;
        }
        return mVar;
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:30:0x008f -> B:58:0x00a9). Please submit an issue!!! */
    private void a(int i2, String str, Context context) {
        BufferedInputStream bufferedInputStream;
        BufferedOutputStream bufferedOutputStream;
        new File(str).delete();
        TbsLog.i("TbsInstaller", "Local tbs apk(" + str + ") is deleted!", true);
        File file = new File(QbSdk.getTbsFolderDir(context), "core_unzip_tmp");
        if (file.canRead()) {
            File file2 = new File(file, "tbs.conf");
            Properties properties = new Properties();
            BufferedOutputStream bufferedOutputStream2 = null;
            try {
                try {
                    bufferedInputStream = new BufferedInputStream(new FileInputStream(file2));
                    try {
                        properties.load(bufferedInputStream);
                        bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file2));
                    } catch (Throwable th) {
                        th = th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    bufferedInputStream = null;
                }
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            try {
                properties.setProperty("tbs_local_installation", DYConstants.DY_TRUE);
                properties.store(bufferedOutputStream, (String) null);
                TbsLog.i("TbsInstaller", "TBS_LOCAL_INSTALLATION is set!", true);
                try {
                    bufferedOutputStream.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
                bufferedInputStream.close();
            } catch (Throwable th3) {
                th = th3;
                bufferedOutputStream2 = bufferedOutputStream;
                try {
                    th.printStackTrace();
                    if (bufferedOutputStream2 != null) {
                        try {
                            bufferedOutputStream2.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    if (bufferedInputStream != null) {
                        bufferedInputStream.close();
                    }
                } catch (Throwable th4) {
                    if (bufferedOutputStream2 != null) {
                        try {
                            bufferedOutputStream2.close();
                        } catch (IOException e5) {
                            e5.printStackTrace();
                        }
                    }
                    if (bufferedInputStream != null) {
                        try {
                            bufferedInputStream.close();
                        } catch (IOException e6) {
                            e6.printStackTrace();
                        }
                    }
                    throw th4;
                }
            }
        }
    }

    public static void a(Context context) {
        String str;
        if (r(context)) {
            return;
        }
        if (a(context, "core_unzip_tmp")) {
            TbsCoreLoadStat.getInstance().a(context, 417, new Throwable("TMP_TBS_UNZIP_FOLDER_NAME"));
            str = "TbsInstaller-UploadIfTempCoreExistConfError INFO_TEMP_CORE_EXIST_CONF_ERROR TMP_TBS_UNZIP_FOLDER_NAME";
        } else if (a(context, "core_share_backup_tmp")) {
            TbsCoreLoadStat.getInstance().a(context, 417, new Throwable("TMP_BACKUP_TBSCORE_FOLDER_NAME"));
            str = "TbsInstaller-UploadIfTempCoreExistConfError INFO_TEMP_CORE_EXIST_CONF_ERROR TMP_BACKUP_TBSCORE_FOLDER_NAME";
        } else if (!a(context, "core_copy_tmp")) {
            return;
        } else {
            TbsCoreLoadStat.getInstance().a(context, 417, new Throwable("TMP_TBS_COPY_FOLDER_NAME"));
            str = "TbsInstaller-UploadIfTempCoreExistConfError INFO_TEMP_CORE_EXIST_CONF_ERROR TMP_TBS_COPY_FOLDER_NAME";
        }
        TbsLog.e("TbsInstaller", str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:106:0x02b6 A[Catch: all -> 0x047a, Exception -> 0x047f, TryCatch #16 {Exception -> 0x047f, all -> 0x047a, blocks: (B:65:0x021e, B:67:0x022a, B:69:0x0233, B:84:0x028c, B:106:0x02b6, B:107:0x02c4, B:109:0x02c7, B:111:0x02d3, B:113:0x02df, B:115:0x02eb, B:117:0x02f1, B:120:0x02fe, B:123:0x0312, B:125:0x0318, B:126:0x0334, B:129:0x0366, B:132:0x037e, B:135:0x03a8, B:127:0x0361, B:88:0x0292, B:99:0x02aa, B:103:0x02b0), top: B:205:0x021e }] */
    /* JADX WARN: Removed duplicated region for block: B:131:0x037c A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:139:0x03c4 A[Catch: Exception -> 0x049c, all -> 0x04b6, TryCatch #8 {Exception -> 0x049c, blocks: (B:63:0x01eb, B:137:0x03b6, B:139:0x03c4, B:140:0x03cc, B:142:0x03d9, B:144:0x03fc, B:149:0x0421, B:145:0x0403, B:141:0x03d0, B:154:0x0445, B:159:0x044e, B:158:0x044b, B:160:0x044f, B:166:0x0487, B:170:0x04a1), top: B:200:0x01e7 }] */
    /* JADX WARN: Removed duplicated region for block: B:141:0x03d0 A[Catch: Exception -> 0x049c, all -> 0x04b6, TryCatch #8 {Exception -> 0x049c, blocks: (B:63:0x01eb, B:137:0x03b6, B:139:0x03c4, B:140:0x03cc, B:142:0x03d9, B:144:0x03fc, B:149:0x0421, B:145:0x0403, B:141:0x03d0, B:154:0x0445, B:159:0x044e, B:158:0x044b, B:160:0x044f, B:166:0x0487, B:170:0x04a1), top: B:200:0x01e7 }] */
    /* JADX WARN: Removed duplicated region for block: B:144:0x03fc A[Catch: Exception -> 0x049c, all -> 0x04b6, TryCatch #8 {Exception -> 0x049c, blocks: (B:63:0x01eb, B:137:0x03b6, B:139:0x03c4, B:140:0x03cc, B:142:0x03d9, B:144:0x03fc, B:149:0x0421, B:145:0x0403, B:141:0x03d0, B:154:0x0445, B:159:0x044e, B:158:0x044b, B:160:0x044f, B:166:0x0487, B:170:0x04a1), top: B:200:0x01e7 }] */
    /* JADX WARN: Removed duplicated region for block: B:145:0x0403 A[Catch: Exception -> 0x049c, all -> 0x04b6, TRY_LEAVE, TryCatch #8 {Exception -> 0x049c, blocks: (B:63:0x01eb, B:137:0x03b6, B:139:0x03c4, B:140:0x03cc, B:142:0x03d9, B:144:0x03fc, B:149:0x0421, B:145:0x0403, B:141:0x03d0, B:154:0x0445, B:159:0x044e, B:158:0x044b, B:160:0x044f, B:166:0x0487, B:170:0x04a1), top: B:200:0x01e7 }] */
    /* JADX WARN: Removed duplicated region for block: B:194:0x02aa A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:196:0x0445 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @TargetApi(11)
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void a(Context context, Context context2, int i2) {
        File file;
        int c2;
        int b2;
        Properties properties;
        Throwable th;
        BufferedInputStream bufferedInputStream;
        BufferedInputStream bufferedInputStream2;
        boolean z;
        boolean z2;
        TbsLogReport tbsLogReport;
        String str;
        int i3;
        int i4;
        SharedPreferences sharedPreferences;
        File file2;
        BufferedInputStream bufferedInputStream3;
        TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-524);
        if (c(context2)) {
            return;
        }
        TbsLog.i("TbsInstaller", "TbsInstaller-copyTbsCoreInThread start!  tbsCoreTargetVer is " + i2);
        if ((Build.VERSION.SDK_INT >= 11 ? context2.getSharedPreferences("tbs_preloadx5_check_cfg_file", 4) : context2.getSharedPreferences("tbs_preloadx5_check_cfg_file", 0)).getInt("tbs_precheck_disable_version", -1) == i2) {
            TbsLog.e("TbsInstaller", "TbsInstaller-copyTbsCoreInThread -- version:" + i2 + " is disabled by preload_x5_check!");
            TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-525);
        } else if (!p(context2)) {
            TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-526);
        } else {
            Lock lock = f17848i;
            boolean tryLock = lock.tryLock();
            TbsLog.i("TbsInstaller", "TbsInstaller-copyTbsCoreInThread #1 locked is " + tryLock);
            if (!tryLock) {
                c();
                TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-538);
                return;
            }
            ReentrantLock reentrantLock = f17847h;
            reentrantLock.lock();
            try {
                try {
                    c2 = l.a(context2).c("copy_core_ver");
                    b2 = l.a(context2).b("copy_status");
                } catch (Throwable th2) {
                    th = th2;
                    f17847h.unlock();
                    f17848i.unlock();
                    c();
                    throw th;
                }
            } catch (Exception e2) {
                e = e2;
                file = null;
            }
            if (c2 == i2) {
                QbSdk.f17732n.onInstallFinish(220);
                TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-528);
                reentrantLock.unlock();
                lock.unlock();
                c();
                return;
            }
            int g2 = g(context2);
            TbsLog.i("TbsInstaller", "TbsInstaller-copyTbsCoreInThread tbsCoreInstalledVer=" + g2);
            if (g2 == i2) {
                QbSdk.f17732n.onInstallFinish(220);
                TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-528);
                TbsLog.i("TbsInstaller", "TbsInstaller-copyTbsCoreInThread return have same version is " + g2);
                reentrantLock.unlock();
                lock.unlock();
                c();
                return;
            }
            int b3 = l.a(context2).b();
            if ((b3 > 0 && i2 > b3) || (c2 > 0 && i2 > c2)) {
                l(context2);
            }
            if (b2 == 3 && g2 > 0 && (i2 > g2 || i2 == 88888888)) {
                l(context2);
                TbsLog.i("TbsInstaller", "TbsInstaller-copyTbsCoreInThread -- update TBS.....", true);
                b2 = -1;
            }
            if (!FileUtil.b(context2)) {
                long a2 = com.tencent.smtt.utils.s.a();
                long downloadMinFreeSpace = TbsDownloadConfig.getInstance(context2).getDownloadMinFreeSpace();
                TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-529);
                TbsLogReport.getInstance(context2).setInstallErrorCode(210, "rom is not enough when copying tbs core! curAvailROM=" + a2 + ",minReqRom=" + downloadMinFreeSpace);
                reentrantLock.unlock();
                lock.unlock();
                c();
            } else if (b2 > 0) {
                TbsLog.i("TbsInstaller", "TbsInstaller-copyTbsCoreInThread return have copied is " + f(context2));
                reentrantLock.unlock();
                lock.unlock();
                c();
            } else {
                if (b2 == 0) {
                    int c3 = l.a(context2).c("copy_retry_num");
                    if (c3 > 2) {
                        TbsLogReport.getInstance(context2).setInstallErrorCode(211, "exceed copy retry num!");
                        TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-530);
                        reentrantLock.unlock();
                        lock.unlock();
                        c();
                        return;
                    }
                    l.a(context2).a("copy_retry_num", c3 + 1);
                }
                File n2 = n(context);
                File e3 = e(context2, 1);
                try {
                    if (n2 == null || e3 == null) {
                        if (n2 == null) {
                            TbsLogReport.getInstance(context2).setInstallErrorCode(213, "src-dir is null when copying tbs core!");
                            TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-535);
                        }
                        if (e3 == null) {
                            TbsLogReport.getInstance(context2).setInstallErrorCode(214, "dst-dir is null when copying tbs core!");
                            TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-536);
                        }
                    } else {
                        l.a(context2).a(i2, 0);
                        com.tencent.smtt.utils.q qVar = new com.tencent.smtt.utils.q();
                        qVar.a(n2);
                        long currentTimeMillis = System.currentTimeMillis();
                        TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-551);
                        boolean a3 = FileUtil.a(n2, e3, b);
                        StringBuilder sb = new StringBuilder();
                        sb.append("TbsInstaller-copyTbsCoreInThread time=");
                        try {
                            sb.append(System.currentTimeMillis() - currentTimeMillis);
                            TbsLog.i("TbsInstaller", sb.toString());
                            if (a3) {
                                qVar.b(n2);
                                if (!qVar.a()) {
                                    TbsLog.i("TbsInstaller", "TbsInstaller-copyTbsCoreInThread copy-verify fail!");
                                    FileUtil.a(e3, true);
                                    TbsLogReport.getInstance(context2).setInstallErrorCode(213, "TbsCopy-Verify fail after copying tbs core!");
                                    TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-531);
                                    reentrantLock.unlock();
                                    lock.unlock();
                                    c();
                                    return;
                                }
                                try {
                                    try {
                                        file2 = new File(e3, "1");
                                        properties = new Properties();
                                    } catch (Exception e4) {
                                        e = e4;
                                        properties = null;
                                    }
                                    try {
                                        if (file2.exists()) {
                                            BufferedInputStream bufferedInputStream4 = new BufferedInputStream(new FileInputStream(file2));
                                            try {
                                                properties.load(bufferedInputStream4);
                                                bufferedInputStream3 = bufferedInputStream4;
                                                z = true;
                                            } catch (Exception e5) {
                                                e = e5;
                                                bufferedInputStream2 = bufferedInputStream4;
                                                try {
                                                    e.printStackTrace();
                                                    if (bufferedInputStream2 != null) {
                                                    }
                                                    z = true;
                                                    if (z) {
                                                    }
                                                    z2 = true;
                                                    TbsLog.i("TbsInstaller", "copyTbsCoreInThread - md5_check_success:" + z2);
                                                    if (!z) {
                                                    }
                                                    TbsLog.i("TbsInstaller", "TbsInstaller-copyTbsCoreInThread success!");
                                                    TbsLog.i("TbsInstaller", "setTmpFolderCoreToRead call #04");
                                                    e(context2, true);
                                                    l.a(context2).a(i2, 1);
                                                    if (this.f17856j) {
                                                    }
                                                    tbsLogReport.setInstallErrorCode(i3, str);
                                                    TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-533);
                                                    TbsLog.i("TbsInstaller", "TbsInstaller-copyTbsCoreInThread success -- version:" + i2);
                                                    if (Build.VERSION.SDK_INT >= 11) {
                                                    }
                                                    try {
                                                        SharedPreferences.Editor edit = sharedPreferences.edit();
                                                        edit.putInt("tbs_preload_x5_counter", i4);
                                                        edit.putInt("tbs_preload_x5_recorder", i4);
                                                        edit.putInt("tbs_preload_x5_version", i2);
                                                        edit.commit();
                                                    } catch (Throwable th3) {
                                                        TbsLog.e("TbsInstaller", "Init tbs_preload_x5_counter#2 exception:" + Log.getStackTraceString(th3));
                                                    }
                                                    f17847h.unlock();
                                                    f17848i.unlock();
                                                    c();
                                                } catch (Throwable th4) {
                                                    th = th4;
                                                    bufferedInputStream = bufferedInputStream2;
                                                    if (bufferedInputStream != null) {
                                                        try {
                                                            bufferedInputStream.close();
                                                        } catch (IOException e6) {
                                                            e6.printStackTrace();
                                                        }
                                                    }
                                                    throw th;
                                                }
                                            } catch (Throwable th5) {
                                                th = th5;
                                                bufferedInputStream = bufferedInputStream4;
                                                if (bufferedInputStream != null) {
                                                }
                                                throw th;
                                            }
                                        } else {
                                            z = false;
                                            bufferedInputStream3 = null;
                                        }
                                        if (bufferedInputStream3 != null) {
                                            try {
                                                bufferedInputStream3.close();
                                            } catch (IOException e7) {
                                                e7.printStackTrace();
                                            }
                                        }
                                    } catch (Exception e8) {
                                        e = e8;
                                        bufferedInputStream2 = null;
                                        e.printStackTrace();
                                        if (bufferedInputStream2 != null) {
                                            try {
                                                bufferedInputStream2.close();
                                            } catch (IOException e9) {
                                                e9.printStackTrace();
                                            }
                                        }
                                        z = true;
                                        if (z) {
                                        }
                                        z2 = true;
                                        TbsLog.i("TbsInstaller", "copyTbsCoreInThread - md5_check_success:" + z2);
                                        if (!z) {
                                        }
                                        TbsLog.i("TbsInstaller", "TbsInstaller-copyTbsCoreInThread success!");
                                        TbsLog.i("TbsInstaller", "setTmpFolderCoreToRead call #04");
                                        e(context2, true);
                                        l.a(context2).a(i2, 1);
                                        if (this.f17856j) {
                                        }
                                        tbsLogReport.setInstallErrorCode(i3, str);
                                        TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-533);
                                        TbsLog.i("TbsInstaller", "TbsInstaller-copyTbsCoreInThread success -- version:" + i2);
                                        if (Build.VERSION.SDK_INT >= 11) {
                                        }
                                        SharedPreferences.Editor edit2 = sharedPreferences.edit();
                                        edit2.putInt("tbs_preload_x5_counter", i4);
                                        edit2.putInt("tbs_preload_x5_recorder", i4);
                                        edit2.putInt("tbs_preload_x5_version", i2);
                                        edit2.commit();
                                        f17847h.unlock();
                                        f17848i.unlock();
                                        c();
                                    }
                                    if (z) {
                                        File[] listFiles = e3.listFiles();
                                        TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-552);
                                        for (File file3 : listFiles) {
                                            if (!"1".equals(file3.getName()) && !file3.getName().endsWith(".dex") && !"tbs.conf".equals(file3.getName()) && !file3.isDirectory() && !file3.getName().endsWith(".prof")) {
                                                String a4 = com.tencent.smtt.utils.a.a(file3);
                                                String property = properties.getProperty(file3.getName(), "");
                                                if (property.equals("") || !a4.equals(property)) {
                                                    TbsLog.e("TbsInstaller", "md5_check_failure for (" + file3.getName() + ") targetMd5:" + property + ", realMd5:" + a4);
                                                    z2 = false;
                                                    break;
                                                }
                                                TbsLog.i("TbsInstaller", "md5_check_success for (" + file3.getName() + ")");
                                            }
                                        }
                                    }
                                    z2 = true;
                                    TbsLog.i("TbsInstaller", "copyTbsCoreInThread - md5_check_success:" + z2);
                                    if (!z && !z2) {
                                        TbsLog.e("TbsInstaller", "copyTbsCoreInThread - md5 incorrect -> delete destTmpDir!");
                                        FileUtil.a(e3, true);
                                        TbsLogReport.getInstance(context2).setInstallErrorCode(213, "TbsCopy-Verify md5 fail after copying tbs core!");
                                        TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-532);
                                        f17847h.unlock();
                                        f17848i.unlock();
                                        c();
                                        return;
                                    }
                                    TbsLog.i("TbsInstaller", "TbsInstaller-copyTbsCoreInThread success!");
                                    TbsLog.i("TbsInstaller", "setTmpFolderCoreToRead call #04");
                                    e(context2, true);
                                    l.a(context2).a(i2, 1);
                                    if (this.f17856j) {
                                        tbsLogReport = TbsLogReport.getInstance(context2);
                                        str = "continueInstallWithout core success";
                                        i3 = 220;
                                    } else {
                                        tbsLogReport = TbsLogReport.getInstance(context2);
                                        str = "success";
                                        i3 = 220;
                                    }
                                    tbsLogReport.setInstallErrorCode(i3, str);
                                    TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-533);
                                    TbsLog.i("TbsInstaller", "TbsInstaller-copyTbsCoreInThread success -- version:" + i2);
                                    if (Build.VERSION.SDK_INT >= 11) {
                                        sharedPreferences = context2.getSharedPreferences("tbs_preloadx5_check_cfg_file", 4);
                                        i4 = 0;
                                    } else {
                                        i4 = 0;
                                        sharedPreferences = context2.getSharedPreferences("tbs_preloadx5_check_cfg_file", 0);
                                    }
                                    SharedPreferences.Editor edit22 = sharedPreferences.edit();
                                    edit22.putInt("tbs_preload_x5_counter", i4);
                                    edit22.putInt("tbs_preload_x5_recorder", i4);
                                    edit22.putInt("tbs_preload_x5_version", i2);
                                    edit22.commit();
                                } catch (Throwable th6) {
                                    th = th6;
                                    bufferedInputStream = null;
                                }
                            } else {
                                TbsLog.i("TbsInstaller", "TbsInstaller-copyTbsCoreInThread fail!");
                                l.a(context2).a(i2, 2);
                                FileUtil.a(e3, false);
                                TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-534);
                                TbsLogReport.getInstance(context2).setInstallErrorCode(212, "copy fail!");
                            }
                        } catch (Exception e10) {
                            e = e10;
                            file = e3;
                            TbsLogReport.getInstance(context2).setInstallErrorCode(215, e.toString());
                            TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-537);
                            try {
                                FileUtil.a(file, false);
                                l.a(context2).a(0, -1);
                            } catch (Exception e11) {
                                TbsLog.e("TbsInstaller", "[TbsInstaller-copyTbsCoreInThread] delete dstTmpDir throws exception:" + e11.getMessage() + DYConstants.DY_REGEX_COMMA + e11.getCause());
                            }
                            f17847h.unlock();
                            f17848i.unlock();
                            c();
                        } catch (Throwable th7) {
                            th = th7;
                            f17847h.unlock();
                            f17848i.unlock();
                            c();
                            throw th;
                        }
                    }
                } catch (Exception e12) {
                    e = e12;
                }
                f17847h.unlock();
                f17848i.unlock();
                c();
            }
        }
    }

    private boolean a(Context context, File file, boolean z) {
        TbsDownloadConfig tbsDownloadConfig;
        int i2;
        TbsLog.i("TbsInstaller", "TbsInstaller-unzipTbs start isDecoupleCore is " + z);
        if (FileUtil.c(file)) {
            try {
                File tbsFolderDir = QbSdk.getTbsFolderDir(context);
                File file2 = z ? new File(tbsFolderDir, "core_share_decouple") : new File(tbsFolderDir, "core_unzip_tmp");
                if (file2.exists() && !TbsDownloader.a(context)) {
                    FileUtil.b(file2);
                }
            } catch (Throwable th) {
                TbsLog.e("TbsInstaller", "TbsInstaller-unzipTbs -- delete unzip folder if exists exception" + Log.getStackTraceString(th));
            }
            File e2 = z ? e(context, 2) : e(context, 0);
            if (e2 != null) {
                try {
                    FileUtil.a(e2);
                    if (z) {
                        FileUtil.a(e2, true);
                    }
                    boolean a2 = FileUtil.a(file, e2);
                    if (a2) {
                        a2 = a(e2, context);
                    }
                    if (z) {
                        for (String str : e2.list()) {
                            File file3 = new File(e2, str);
                            if (file3.getName().endsWith(".dex")) {
                                file3.delete();
                            }
                        }
                        try {
                            new File(o(context), "x5.tbs").delete();
                        } catch (Exception unused) {
                        }
                    }
                    if (a2) {
                        TbsLog.i("TbsInstaller", "setTmpFolderCoreToRead call #05");
                        e(context, true);
                    } else {
                        FileUtil.b(e2);
                        TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-522);
                        TbsLog.e("TbsInstaller", "copyFileIfChanged -- delete tmpTbsCoreUnzipDir#1! exist:" + e2.exists());
                    }
                    return a2;
                } catch (IOException e3) {
                    TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-523);
                    TbsLogReport.getInstance(context).setInstallErrorCode(206, e3);
                    if ((e2 != null && e2.exists()) && e2 != null) {
                        try {
                            FileUtil.b(e2);
                            TbsLog.e("TbsInstaller", "copyFileIfChanged -- delete tmpTbsCoreUnzipDir#2! exist:" + e2.exists());
                        } catch (Throwable th2) {
                            TbsLog.e("TbsInstaller", "copyFileIfChanged -- delete tmpTbsCoreUnzipDir#2! exception:" + Log.getStackTraceString(th2));
                        }
                    }
                    return false;
                } catch (Exception e4) {
                    TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-523);
                    TbsLogReport.getInstance(context).setInstallErrorCode(207, e4);
                    if ((e2 != null && e2.exists()) && e2 != null) {
                        try {
                            FileUtil.b(e2);
                            TbsLog.e("TbsInstaller", "copyFileIfChanged -- delete tmpTbsCoreUnzipDir#2! exist:" + e2.exists());
                        } catch (Throwable th3) {
                            TbsLog.e("TbsInstaller", "copyFileIfChanged -- delete tmpTbsCoreUnzipDir#2! exception:" + Log.getStackTraceString(th3));
                        }
                    }
                    return false;
                } finally {
                    TbsLog.i("TbsInstaller", "TbsInstaller-unzipTbs done");
                }
            }
            TbsLogReport.getInstance(context).setInstallErrorCode(205, "tmp unzip dir is null!");
            tbsDownloadConfig = TbsDownloadConfig.getInstance(context);
            i2 = -521;
        } else {
            TbsLogReport.getInstance(context).setInstallErrorCode(204, "apk is invalid!");
            tbsDownloadConfig = TbsDownloadConfig.getInstance(context);
            i2 = -520;
        }
        tbsDownloadConfig.setInstallInterruptCode(i2);
        return false;
    }

    static boolean a(Context context, String str) {
        StringBuilder sb;
        String str2;
        File file = new File(QbSdk.getTbsFolderDir(context), str);
        if (!file.exists()) {
            sb = new StringBuilder();
            str2 = "#1# ";
        } else if (new File(file, "tbs.conf").exists()) {
            TbsLog.i("TbsInstaller", "isPrepareTbsCore", "#3# " + str);
            return true;
        } else {
            sb = new StringBuilder();
            str2 = "#2# ";
        }
        sb.append(str2);
        sb.append(str);
        TbsLog.i("TbsInstaller", "isPrepareTbsCore", sb.toString());
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0152 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private boolean a(File file, Context context) {
        Properties properties;
        Exception e2;
        BufferedInputStream bufferedInputStream;
        boolean z;
        boolean z2;
        TbsLog.i("TbsInstaller", "finalCheckForTbsCoreValidity - " + file + ", " + context);
        BufferedInputStream bufferedInputStream2 = null;
        try {
            try {
                File file2 = new File(file, "1");
                properties = new Properties();
                try {
                    if (file2.exists()) {
                        bufferedInputStream = new BufferedInputStream(new FileInputStream(file2));
                        try {
                            try {
                                properties.load(bufferedInputStream);
                                bufferedInputStream2 = bufferedInputStream;
                                z = true;
                            } catch (Throwable th) {
                                th = th;
                                bufferedInputStream2 = bufferedInputStream;
                                if (bufferedInputStream2 != null) {
                                }
                                throw th;
                            }
                        } catch (Exception e3) {
                            e2 = e3;
                            e2.printStackTrace();
                            if (bufferedInputStream != null) {
                                try {
                                    bufferedInputStream.close();
                                } catch (IOException e4) {
                                    e4.printStackTrace();
                                }
                            }
                            z = true;
                            TbsLog.i("TbsInstaller", "finalCheckForTbsCoreValidity - need_check:" + z);
                            if (z) {
                            }
                            z2 = true;
                            TbsLog.i("TbsInstaller", "finalCheckForTbsCoreValidity - md5_check_success:" + z2);
                            if (z) {
                            }
                            TbsLog.i("TbsInstaller", "finalCheckForTbsCoreValidity success!");
                            return true;
                        }
                    } else {
                        z = false;
                    }
                    if (bufferedInputStream2 != null) {
                        try {
                            bufferedInputStream2.close();
                        } catch (IOException e5) {
                            e5.printStackTrace();
                        }
                    }
                } catch (Exception e6) {
                    bufferedInputStream = null;
                    e2 = e6;
                }
            } catch (Exception e7) {
                properties = null;
                e2 = e7;
                bufferedInputStream = null;
            }
            TbsLog.i("TbsInstaller", "finalCheckForTbsCoreValidity - need_check:" + z);
            if (z) {
                for (File file3 : file.listFiles()) {
                    if (!"1".equals(file3.getName()) && !file3.getName().endsWith(".dex") && !"tbs.conf".equals(file3.getName()) && !file3.isDirectory() && !file3.getName().endsWith(".prof")) {
                        String a2 = com.tencent.smtt.utils.a.a(file3);
                        String property = properties.getProperty(file3.getName(), "");
                        if (property.equals("") || !property.equals(a2)) {
                            TbsLog.e("TbsInstaller", "md5_check_failure for (" + file3.getName() + ") targetMd5:" + property + ", realMd5:" + a2);
                            z2 = false;
                            break;
                        }
                        TbsLog.i("TbsInstaller", "md5_check_success for (" + file3.getName() + ")");
                    }
                }
            }
            z2 = true;
            TbsLog.i("TbsInstaller", "finalCheckForTbsCoreValidity - md5_check_success:" + z2);
            if (z || z2) {
                TbsLog.i("TbsInstaller", "finalCheckForTbsCoreValidity success!");
                return true;
            }
            TbsLog.e("TbsInstaller", "finalCheckForTbsCoreValidity - Verify failed after unzipping!");
            return false;
        } catch (Throwable th2) {
            th = th2;
            if (bufferedInputStream2 != null) {
                try {
                    bufferedInputStream2.close();
                } catch (IOException e8) {
                    e8.printStackTrace();
                }
            }
            throw th;
        }
    }

    public static void b() {
        f17850l.set(0);
    }

    private boolean b(Context context, File file) {
        return a(context, file, false);
    }

    private int c(Context context, Bundle bundle) {
        try {
            Bundle a2 = QbSdk.a(context, bundle);
            TbsLog.i("TbsInstaller", "tpatch finished,ret is" + a2);
            int i2 = a2.getInt("patch_result");
            if (i2 != 0) {
                String string = bundle.getString("new_apk_location");
                if (!TextUtils.isEmpty(string)) {
                    FileUtil.b(new File(string));
                }
                TbsLogReport.getInstance(context).setInstallErrorCode(i2, "tpatch fail,patch error_code=" + i2);
                return 1;
            }
            String string2 = bundle.getString("new_apk_location");
            int i3 = bundle.getInt("new_core_ver");
            int a3 = a(new File(string2));
            TbsLog.i("TbsInstaller", "doTpatch version is " + i3 + " patchVersion is " + a3);
            if (i3 == a3) {
                TbsLog.i("TbsInstaller", "Tpatch success!");
                TbsLogReport.getInstance(context).setInstallErrorCode(236, "");
                return 0;
            }
            TbsLog.i("TbsInstaller", "version not equals!!!" + i3 + "patchVersion:" + a3);
            TbsLogReport.getInstance(context).setInstallErrorCode(240, "version=" + i3 + ",patchVersion=" + a3);
            return 1;
        } catch (Exception e2) {
            e2.printStackTrace();
            TbsLogReport.getInstance(context).setInstallErrorCode(239, "patch exception" + Log.getStackTraceString(e2));
            return 1;
        }
    }

    private boolean c(Context context, File file) {
        try {
            File[] listFiles = file.listFiles(new FileFilter() { // from class: com.tencent.smtt.sdk.m.6
                @Override // java.io.FileFilter
                public boolean accept(File file2) {
                    return file2.getName().endsWith(".jar");
                }
            });
            int length = listFiles.length;
            if (Build.VERSION.SDK_INT < 16 && context.getPackageName() != null && context.getPackageName().equalsIgnoreCase(TbsConfig.APP_DEMO)) {
                try {
                    Thread.sleep(Final.FIVE_SECOND);
                } catch (Exception unused) {
                }
            }
            ClassLoader classLoader = context.getClassLoader();
            for (int i2 = 0; i2 < length; i2++) {
                TbsLog.i("TbsInstaller", "jarFile: " + listFiles[i2].getAbsolutePath());
                new DexClassLoader(listFiles[i2].getAbsolutePath(), file.getAbsolutePath(), null, classLoader);
            }
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            TbsLogReport.getInstance(context).setInstallErrorCode(209, e2.toString());
            TbsLog.i("TbsInstaller", "TbsInstaller-doTbsDexOpt done");
            return false;
        }
    }

    private synchronized boolean c(Context context, boolean z) {
        TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromTpatch");
        boolean z2 = false;
        if (p(context)) {
            ReentrantLock reentrantLock = f17847h;
            boolean tryLock = reentrantLock.tryLock();
            TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromTpatch Locked =" + tryLock);
            if (tryLock) {
                int b2 = l.a(context).b("tpatch_status");
                int a2 = a(false, context);
                TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromTpatch copyStatus =" + b2);
                TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromTpatch tbsCoreInstalledVer =" + a2);
                if (b2 == 1) {
                    if (a2 == 0) {
                        TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromTpatch tbsCoreInstalledVer = 0", true);
                    } else if (z) {
                        TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromTpatch tbsCoreInstalledVer != 0", true);
                    }
                    u(context);
                    z2 = true;
                }
                reentrantLock.unlock();
            }
            c();
            return z2;
        }
        return false;
    }

    private boolean d(Context context, File file) {
        try {
            File file2 = new File(file, "tbs_sdk_extension_dex.jar");
            File file3 = new File(file, "tbs_sdk_extension_dex.dex");
            new DexClassLoader(file2.getAbsolutePath(), file.getAbsolutePath(), null, context.getClassLoader());
            String a2 = d.a(context, file3.getAbsolutePath());
            if (TextUtils.isEmpty(a2)) {
                TbsLogReport.getInstance(context).setInstallErrorCode(226, "can not find oat command");
                return false;
            }
            for (File file4 : file.listFiles(new FileFilter() { // from class: com.tencent.smtt.sdk.m.7
                @Override // java.io.FileFilter
                public boolean accept(File file5) {
                    return file5.getName().endsWith(".jar");
                }
            })) {
                String substring = file4.getName().substring(0, r5.getName().length() - 4);
                Runtime.getRuntime().exec("/system/bin/dex2oat " + a2.replaceAll("tbs_sdk_extension_dex", substring) + " --dex-location=" + a().n(context) + File.separator + substring + ".jar").waitFor();
            }
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            TbsLogReport.getInstance(context).setInstallErrorCode(226, e2);
            return false;
        }
    }

    private synchronized boolean d(Context context, boolean z) {
        if (context != null) {
            if ("com.tencent.mm".equals(context.getApplicationContext().getApplicationInfo().packageName)) {
                TbsLogReport.getInstance(context).setInstallErrorCode(229, LangUtils.SINGLE_SPACE);
            }
        }
        TbsLog.i("TbsInstaller", "enableTbsCoreFromUnzip", "canRenameTmpDir =" + z);
        TbsLog.i("TbsInstaller", "enableTbsCoreFromUnzip", "#1#");
        boolean z2 = false;
        try {
        } catch (Exception e2) {
            QbSdk.a(context, "TbsInstaller::enableTbsCoreFromUnzip Exception: " + e2);
            e2.printStackTrace();
        }
        if (p(context)) {
            TbsLog.i("TbsInstaller", "enableTbsCoreFromUnzip", "#2# getInstallFileLock Success!!");
            ReentrantLock reentrantLock = f17847h;
            boolean tryLock = reentrantLock.tryLock();
            TbsLog.i("TbsInstaller", "enableTbsCoreFromUnzip", "locked=" + tryLock);
            if (tryLock) {
                try {
                    int c2 = l.a(context).c();
                    TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromUnzip installStatus=" + c2);
                    int a2 = a(false, context);
                    if (c2 == 2) {
                        TbsLog.i("TbsInstaller", "enableTbsCoreFromUnzip", "#4# In Rename Logic");
                        if (a2 == 0) {
                            TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromUnzip tbsCoreInstalledVer = 0", false);
                        } else if (z) {
                            TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromUnzip tbsCoreInstalledVer != 0", false);
                        }
                        t(context);
                        z2 = true;
                    }
                    reentrantLock.unlock();
                } catch (Throwable th) {
                    f17847h.unlock();
                    throw th;
                }
            }
            c();
            return z2;
        }
        return false;
    }

    private void e(Context context, boolean z) {
        if (context == null) {
            TbsLogReport.getInstance(context).setInstallErrorCode(225, "setTmpFolderCoreToRead context is null");
            TbsLog.i("TbsInstaller", "setTmpFolderCoreToRead inner return #01");
            return;
        }
        try {
            File file = new File(QbSdk.getTbsFolderDir(context), "tmp_folder_core_to_read.conf");
            if (!z) {
                TbsLog.i("TbsInstaller", "setTmpFolderCoreToRead inner tmp file delete #01");
                FileUtil.b(file);
            } else if (file.exists()) {
                TbsLog.i("TbsInstaller", "setTmpFolderCoreToRead inner tmp file already exist #01");
            } else {
                TbsLog.i("TbsInstaller", "setTmpFolderCoreToRead inner tmp file create #01");
                file.createNewFile();
            }
        } catch (Exception e2) {
            TbsLogReport.getInstance(context).setInstallErrorCode(225, "setTmpFolderCoreToRead Exception message is " + e2.getMessage() + " Exception cause is " + e2.getCause());
            TbsLog.i("TbsInstaller", "setTmpFolderCoreToRead inner exception #01");
        }
    }

    private void f(Context context, int i2) {
        TbsLog.i("TbsInstaller", "proceedTpatchStatus,result=" + i2);
        if (i2 != 0) {
            return;
        }
        TbsLog.i("TbsInstaller", "setTmpFolderCoreToRead call #03");
        e(context, true);
        l.a(context).b(TbsDownloadConfig.getInstance(context).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0), 1);
    }

    private boolean g(Context context, int i2) {
        File e2;
        boolean z;
        TbsLog.i("TbsInstaller", "TbsInstaller-doTbsDexOpt start - dirMode: " + i2);
        boolean z2 = false;
        try {
            if (i2 != 0) {
                if (i2 == 1) {
                    e2 = e(context, 1);
                } else if (i2 != 2) {
                    TbsLog.e("TbsInstaller", "doDexoptOrDexoat mode error: " + i2);
                    return false;
                } else {
                    e2 = n(context);
                }
            } else if (TbsDownloader.a(context)) {
                return true;
            } else {
                e2 = e(context, 0);
            }
            String property = System.getProperty("java.vm.version");
            z = property != null && property.startsWith("2");
            boolean z3 = Build.VERSION.SDK_INT == 23;
            boolean z4 = TbsDownloadConfig.getInstance(context).mPreferences.getBoolean(TbsDownloadConfig.TbsConfigKey.KEY_STOP_PRE_OAT, false);
            if (z && z3 && !z4) {
                z2 = true;
            }
        } catch (Exception e3) {
            e3.printStackTrace();
            TbsLogReport.getInstance(context).setInstallErrorCode(209, e3.toString());
        }
        if (z2 && d(context, e2)) {
            TbsLog.i("TbsInstaller", "doTbsDexOpt -- doDexoatForArtVm");
            return true;
        } else if (!z) {
            TbsLog.i("TbsInstaller", "doTbsDexOpt -- doDexoptForDavlikVM");
            return c(context, e2);
        } else {
            TbsLog.i("TbsInstaller", "doTbsDexOpt -- is ART mode, skip!");
            TbsLog.i("TbsInstaller", "TbsInstaller-doTbsDexOpt done");
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static File o(Context context) {
        File file = new File(QbSdk.getTbsFolderDir(context), "core_private");
        if (file.isDirectory() || file.mkdir()) {
            return file;
        }
        return null;
    }

    private int q(Context context) {
        boolean z = l.a(context).d() == 1;
        boolean a2 = TbsDownloader.a(context);
        return z ? a2 ? 234 : 221 : a2 ? 233 : 200;
    }

    private static boolean r(Context context) {
        String str;
        if (context == null) {
            str = "#1#";
        } else {
            try {
                if (new File(QbSdk.getTbsFolderDir(context), "tmp_folder_core_to_read.conf").exists()) {
                    TbsLog.i("TbsInstaller", "getTmpFolderCoreToRead", "#2#");
                    return true;
                }
                TbsLog.i("TbsInstaller", "getTmpFolderCoreToRead", "#3#");
                return false;
            } catch (Exception unused) {
                str = "#4#";
            }
        }
        TbsLog.i("TbsInstaller", "getTmpFolderCoreToRead", str);
        return true;
    }

    private boolean s(Context context) {
        TbsLog.i("TbsInstaller", "Tbsinstaller getTbsCoreRenameFileLock #1 ");
        FileLock e2 = FileUtil.e(context);
        f17849k = e2;
        if (e2 == null) {
            TbsLog.i("TbsInstaller", "getTbsCoreRenameFileLock## failed!");
            return false;
        }
        TbsLog.i("TbsInstaller", "Tbsinstaller getTbsCoreRenameFileLock true ");
        return true;
    }

    private void t(Context context) {
        TbsLog.i("TbsInstaller", "TbsInstaller--generateNewTbsCoreFromUnzip");
        if (!s(context)) {
            TbsLog.i("TbsInstaller", "get rename fileLock#4 ## failed!");
            return;
        }
        try {
            v(context);
            w(context);
            TbsLog.i("TbsInstaller", "after renameTbsCoreShareDir");
            TbsLog.i("TbsInstaller", "is thirdapp and not chmod");
            l.a(context).a(0);
            l.a(context).b(0);
            l.a(context).d(0);
            l.a(context).a("incrupdate_retry_num", 0);
            l.a(context).c(0, 3);
            l.a(context).a("");
            l.a(context).a("tpatch_num", 0);
            l.a(context).c(-1);
            f17850l.set(0);
            f17852n = 0;
        } catch (Throwable th) {
            th.printStackTrace();
            TbsLogReport.getInstance(context).setInstallErrorCode(219, "exception when renameing from unzip:" + th.toString());
            TbsLog.e("TbsInstaller", "TbsInstaller--generateNewTbsCoreFromUnzip Exception", true);
        }
        e(context);
    }

    private void u(Context context) {
        TbsLog.i("TbsInstaller", "TbsInstaller--generateNewTbsCoreFromTpatch");
        if (!s(context)) {
            TbsLog.i("TbsInstaller", "get rename fileLock#4 ## failed!");
            return;
        }
        try {
            v(context);
            x(context);
            l.a(context).b(0, -1);
            l.a(context).a("tpatch_num", 0);
            f17850l.set(0);
        } catch (Exception e2) {
            e2.printStackTrace();
            TbsLogReport.getInstance(context).setInstallErrorCode(242, "exception when renameing from tpatch:" + e2.toString());
        }
        e(context);
    }

    private void v(Context context) {
        TbsLog.i("TbsInstaller", "TbsInstaller--deleteOldCore");
        FileUtil.a(n(context), false, true);
    }

    private void w(Context context) {
        TbsLog.i("TbsInstaller", "TbsInstaller--renameShareDir");
        File e2 = e(context, 0);
        File n2 = n(context);
        if (e2 == null || n2 == null) {
            TbsLog.i("TbsInstaller", "renameTbsCoreShareDir return,tmpTbsCoreUnzipDir=" + e2 + "tbsSharePath=" + n2);
            return;
        }
        boolean renameTo = e2.renameTo(n2);
        if (renameTo && this.o != null) {
            this.o.a(n2.listFiles(new FileFilter() { // from class: com.tencent.smtt.sdk.m.4
                @Override // java.io.FileFilter
                public boolean accept(File file) {
                    return file.getName().contains(".so");
                }
            }));
        }
        TbsLog.i("TbsInstaller", "renameTbsCoreShareDir rename success=" + renameTo);
        e(context, false);
    }

    private void x(Context context) {
        TbsLog.i("TbsInstaller", "TbsInstaller--renameTbsTpatchCoreDir");
        File e2 = e(context, 5);
        File n2 = n(context);
        if (e2 == null || n2 == null) {
            return;
        }
        if (e2.renameTo(n2) && this.o != null) {
            this.o.a(n2.listFiles(new FileFilter() { // from class: com.tencent.smtt.sdk.m.5
                @Override // java.io.FileFilter
                public boolean accept(File file) {
                    return file.getName().contains(".so");
                }
            }));
        }
        TbsLog.i("TbsInstaller", "setTmpFolderCoreToRead call #09");
        e(context, false);
    }

    private void y(Context context) {
        TbsLog.i("TbsInstaller", "TbsInstaller--clearNewTbsCore");
        File e2 = e(context, 0);
        if (e2 != null) {
            FileUtil.a(e2, false);
        }
        l.a(context).c(0, 5);
        l.a(context).c(-1);
        QbSdk.a(context, "TbsInstaller::clearNewTbsCore forceSysWebViewInner!");
    }

    int a(File file) {
        BufferedInputStream bufferedInputStream = null;
        try {
            TbsLog.i("TbsInstaller", "TbsInstaller--getTbsVersion  tbsShareDir is " + file);
            File file2 = new File(file, "tbs.conf");
            if (file2.exists()) {
                Properties properties = new Properties();
                BufferedInputStream bufferedInputStream2 = new BufferedInputStream(new FileInputStream(file2));
                try {
                    properties.load(bufferedInputStream2);
                    bufferedInputStream2.close();
                    String property = properties.getProperty("tbs_core_version");
                    if (property == null) {
                        try {
                            bufferedInputStream2.close();
                        } catch (IOException unused) {
                        }
                        return 0;
                    }
                    int parseInt = Integer.parseInt(property);
                    try {
                        bufferedInputStream2.close();
                    } catch (IOException unused2) {
                    }
                    return parseInt;
                } catch (Exception unused3) {
                    bufferedInputStream = bufferedInputStream2;
                    if (bufferedInputStream != null) {
                        try {
                            bufferedInputStream.close();
                        } catch (IOException unused4) {
                        }
                    }
                    return 0;
                } catch (Throwable th) {
                    th = th;
                    bufferedInputStream = bufferedInputStream2;
                    if (bufferedInputStream != null) {
                        try {
                            bufferedInputStream.close();
                        } catch (IOException unused5) {
                        }
                    }
                    throw th;
                }
            }
            return 0;
        } catch (Exception unused6) {
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public int a(boolean z, Context context) {
        if (z || f17850l.get().intValue() <= 0) {
            f17850l.set(Integer.valueOf(g(context)));
        }
        return f17850l.get().intValue();
    }

    File a(Context context, int i2, boolean z) {
        String str;
        String str2;
        File tbsFolderDir = QbSdk.getTbsFolderDir(context);
        switch (i2) {
            case 0:
                str = "core_unzip_tmp";
                break;
            case 1:
                str = "core_copy_tmp";
                break;
            case 2:
                str = "core_unzip_tmp_decouple";
                break;
            case 3:
                str = "core_share_backup";
                break;
            case 4:
                str = "core_share_backup_tmp";
                break;
            case 5:
                str = "tpatch_tmp";
                break;
            case 6:
                str = "tpatch_decouple_tmp";
                break;
            default:
                str = "";
                break;
        }
        TbsLog.i("TbsInstaller", "type=" + i2 + "needMakeDir=" + z + "folder=" + str);
        File file = new File(tbsFolderDir, str);
        if (!file.isDirectory()) {
            if (z) {
                str2 = file.mkdir() ? "getCoreDir,no need mkdir" : "getCoreDir,mkdir false";
            }
            TbsLog.i("TbsInstaller", str2);
            return null;
        }
        return file;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public File a(Context context, File file) {
        File file2 = new File(file, "core_share_decouple");
        if (file2.isDirectory() || file2.mkdir()) {
            return file2;
        }
        return null;
    }

    public void a(Context context, int i2) {
        TbsLog.i("TbsInstaller", "setTmpFolderCoreToRead call #01 ");
        e(context, true);
        l.a(context).c(i2, 2);
    }

    void a(Context context, Bundle bundle) {
        if (bundle == null || context == null) {
            return;
        }
        Object[] objArr = {context, bundle};
        Message message = new Message();
        message.what = 3;
        message.obj = objArr;
        f17851m.sendMessage(message);
    }

    public void a(Context context, File file, int i2) {
        FileOutputStream b2 = FileUtil.b(context, true, "core_unzip.lock");
        FileLock a2 = FileUtil.a(context, b2);
        if (a2 == null) {
            TbsLog.i("TbsInstaller", "can not get Core unzip FileLock,skip!!!");
            return;
        }
        TbsLog.i("TbsInstaller", "unzipTbsCoreToThirdAppTmpInThread #1");
        boolean a3 = a(context, file, false);
        TbsLog.i("TbsInstaller", "unzipTbsCoreToThirdAppTmpInThread result is " + a3);
        if (a3) {
            a().a(context, i2);
        }
        FileUtil.a(a2, b2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(Context context, String str, int i2) {
        TbsLog.i("TbsInstaller", "installTbsCore Path: " + str + ";Ver: " + i2 + "\npn: " + context.getApplicationInfo().processName + "\npid: " + Process.myPid() + "\ntn: " + Thread.currentThread().getName());
        Object[] objArr = {context, str, Integer.valueOf(i2)};
        Message message = new Message();
        message.what = 1;
        message.obj = objArr;
        f17851m.sendMessage(message);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(Context context, boolean z) {
        int c2;
        int b2;
        String d;
        int c3;
        int b3;
        boolean z2 = true;
        if (z) {
            this.f17856j = true;
        }
        TbsLog.i("TbsInstaller", "TbsInstaller-continueInstallTbsCore currentProcessName=" + context.getApplicationInfo().processName);
        TbsLog.i("TbsInstaller", "TbsInstaller-continueInstallTbsCore currentProcessId=" + Process.myPid());
        TbsLog.i("TbsInstaller", "TbsInstaller-continueInstallTbsCore currentThreadName=" + Thread.currentThread().getName());
        if (p(context)) {
            ReentrantLock reentrantLock = f17847h;
            if (reentrantLock.tryLock()) {
                try {
                    c2 = l.a(context).c();
                    b2 = l.a(context).b();
                    d = l.a(context).d("install_apk_path");
                    c3 = l.a(context).c("copy_core_ver");
                    b3 = l.a(context).b("copy_status");
                    reentrantLock.unlock();
                } catch (Throwable th) {
                    f17847h.unlock();
                    throw th;
                }
            } else {
                d = null;
                c2 = -1;
                b2 = 0;
                c3 = 0;
                b3 = -1;
            }
            c();
            TbsLog.i("TbsInstaller", "TbsInstaller-continueInstallTbsCore installStatus=" + c2);
            TbsLog.i("TbsInstaller", "TbsInstaller-continueInstallTbsCore tbsCoreInstallVer=" + b2);
            TbsLog.i("TbsInstaller", "TbsInstaller-continueInstallTbsCore tbsApkPath=" + d);
            TbsLog.i("TbsInstaller", "TbsInstaller-continueInstallTbsCore tbsCoreCopyVer=" + c3);
            TbsLog.i("TbsInstaller", "TbsInstaller-continueInstallTbsCore copyStatus=" + b3);
            int i2 = TbsDownloadConfig.getInstance(context).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_RESPONSECODE, 0);
            if (i2 != 1 && i2 != 2 && i2 != 4) {
                z2 = false;
            }
            if (!z2 && i2 != 0 && i2 != 5) {
                Bundle bundle = new Bundle();
                bundle.putInt("operation", 10001);
                a(context, bundle);
            }
            if (c2 > -1 && c2 < 2) {
                a(context, d, b2);
            }
            if (b3 == 0) {
                b(context, c3);
            }
        }
    }

    public final void a(QbSdk.a aVar) {
        this.o = aVar;
    }

    public int b(Context context) {
        if (r(context)) {
            if (a(context, "core_unzip_tmp")) {
                return -1;
            }
            if (a(context, "core_share_backup_tmp")) {
                return -2;
            }
            if (a(context, "core_copy_tmp")) {
                return -3;
            }
            return a(context, "tpatch_tmp") ? -4 : 1;
        }
        return 0;
    }

    int b(Context context, String str) {
        PackageInfo packageArchiveInfo = context.getPackageManager().getPackageArchiveInfo(str, 0);
        if (packageArchiveInfo != null) {
            return packageArchiveInfo.versionCode;
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:100:0x0287  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00f2 A[Catch: all -> 0x0238, Exception -> 0x023a, TryCatch #1 {Exception -> 0x023a, blocks: (B:20:0x00bd, B:25:0x00c7, B:27:0x00cd, B:39:0x00e8, B:41:0x00f2, B:43:0x010f, B:44:0x0117, B:46:0x011d, B:47:0x0125, B:49:0x012b, B:50:0x0133, B:56:0x0161, B:58:0x016f, B:60:0x017c, B:67:0x01b1, B:69:0x01b9, B:74:0x01ed, B:80:0x01ff, B:82:0x0219), top: B:107:0x00bd }] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0161 A[Catch: all -> 0x0238, Exception -> 0x023a, TRY_ENTER, TryCatch #1 {Exception -> 0x023a, blocks: (B:20:0x00bd, B:25:0x00c7, B:27:0x00cd, B:39:0x00e8, B:41:0x00f2, B:43:0x010f, B:44:0x0117, B:46:0x011d, B:47:0x0125, B:49:0x012b, B:50:0x0133, B:56:0x0161, B:58:0x016f, B:60:0x017c, B:67:0x01b1, B:69:0x01b9, B:74:0x01ed, B:80:0x01ff, B:82:0x0219), top: B:107:0x00bd }] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x01e5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void b(Context context, Bundle bundle) {
        boolean z;
        int c2;
        TbsDownloadConfig tbsDownloadConfig;
        int i2;
        TbsLog.i("TbsInstaller", "TbsInstaller installLocalTbsCoreExInThreadthread " + Thread.currentThread().getName() + Log.getStackTraceString(new Throwable()));
        if (c(context)) {
            tbsDownloadConfig = TbsDownloadConfig.getInstance(context);
            i2 = -539;
        } else {
            TbsLog.i("TbsInstaller", "TbsInstaller-installLocalTesCoreExInThread");
            if (bundle == null || context == null) {
                return;
            }
            if (!FileUtil.b(context)) {
                long a2 = com.tencent.smtt.utils.s.a();
                long downloadMinFreeSpace = TbsDownloadConfig.getInstance(context).getDownloadMinFreeSpace();
                TbsLogReport.getInstance(context).setInstallErrorCode(210, "rom is not enough when patching tbs core! curAvailROM=" + a2 + ",minReqRom=" + downloadMinFreeSpace);
                tbsDownloadConfig = TbsDownloadConfig.getInstance(context);
                i2 = -540;
            } else if (p(context)) {
                Lock lock = f17848i;
                boolean tryLock = lock.tryLock();
                TbsLog.i("TbsInstaller", "TbsInstaller-installLocalTesCoreExInThread locked=" + tryLock);
                if (!tryLock) {
                    TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-547);
                    c();
                    return;
                }
                int i3 = TbsDownloadConfig.getInstance(context).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_RESPONSECODE, 0);
                int i4 = 2;
                try {
                    try {
                        QbSdk.setTBSInstallingStatus(true);
                    } catch (Throwable th) {
                        th = th;
                        f17848i.unlock();
                        c();
                        if (i3 == 5) {
                            f(context, i4);
                        }
                        QbSdk.setTBSInstallingStatus(false);
                        throw th;
                    }
                } catch (Exception e2) {
                    e = e2;
                }
                if (i3 != 5 && i3 != 3) {
                    if (g(context) > 0 && l.a(context).d() != 1) {
                        if (i3 != 1 && i3 != 2 && i3 != 4) {
                            z = false;
                            if (!z && i3 != 0) {
                                c2 = l.a(context).c("incrupdate_retry_num");
                                if (c2 <= 5) {
                                    TbsLog.i("TbsInstaller", "TbsInstaller-installLocalTesCoreExInThread exceed incrupdate num");
                                    String string = bundle.getString("old_apk_location");
                                    String string2 = bundle.getString("new_apk_location");
                                    String string3 = bundle.getString("diff_file_location");
                                    if (!TextUtils.isEmpty(string)) {
                                        FileUtil.b(new File(string));
                                    }
                                    if (!TextUtils.isEmpty(string2)) {
                                        FileUtil.b(new File(string2));
                                    }
                                    if (!TextUtils.isEmpty(string3)) {
                                        FileUtil.b(new File(string3));
                                    }
                                    TbsDownloadConfig.getInstance(context).mSyncMap.put(TbsDownloadConfig.TbsConfigKey.KEY_NEEDDOWNLOAD, Boolean.TRUE);
                                    TbsDownloadConfig.getInstance(context).commit();
                                    TbsLogReport.getInstance(context).setInstallErrorCode(224, "incrUpdate exceed retry max num");
                                    lock.unlock();
                                    c();
                                    if (i3 == 5) {
                                        f(context, 2);
                                    }
                                    QbSdk.setTBSInstallingStatus(false);
                                    return;
                                }
                                l.a(context).a("incrupdate_retry_num", c2 + 1);
                                File o = o(context);
                                if (o != null && new File(o, "x5.tbs").exists()) {
                                    TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-550);
                                    Bundle a3 = QbSdk.a(context, bundle);
                                    if (a3 == null) {
                                        try {
                                            try {
                                                TbsLogReport.getInstance(context).setInstallErrorCode(228, "result null : " + bundle.getInt("new_core_ver"));
                                                i4 = 1;
                                            } catch (Throwable th2) {
                                                th = th2;
                                                i4 = 1;
                                                f17848i.unlock();
                                                c();
                                                if (i3 == 5) {
                                                }
                                                QbSdk.setTBSInstallingStatus(false);
                                                throw th;
                                            }
                                        } catch (Exception e3) {
                                            e = e3;
                                            TbsLog.i("TbsInstaller", "installLocalTbsCoreExInThread exception:" + Log.getStackTraceString(e));
                                            e.printStackTrace();
                                            TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-543);
                                            TbsLogReport.getInstance(context).setInstallErrorCode(218, e.toString());
                                            f17848i.unlock();
                                            c();
                                            if (i3 == 5) {
                                                f(context, 1);
                                            }
                                            QbSdk.setTBSInstallingStatus(false);
                                            return;
                                        }
                                    } else {
                                        i4 = a3.getInt("patch_result");
                                        if (i4 != 0) {
                                            TbsLogReport.getInstance(context).setInstallErrorCode(228, "result " + i4 + " : " + bundle.getInt("new_core_ver"));
                                        }
                                    }
                                }
                            }
                            lock.unlock();
                            c();
                            if (i3 == 5) {
                                f(context, i4);
                            }
                            QbSdk.setTBSInstallingStatus(false);
                            return;
                        }
                        z = true;
                        if (!z) {
                            c2 = l.a(context).c("incrupdate_retry_num");
                            if (c2 <= 5) {
                            }
                        }
                        lock.unlock();
                        c();
                        if (i3 == 5) {
                        }
                        QbSdk.setTBSInstallingStatus(false);
                        return;
                    }
                    QbSdk.setTBSInstallingStatus(false);
                    lock.unlock();
                    c();
                    if (i3 == 5) {
                        f(context, 2);
                    }
                    QbSdk.setTBSInstallingStatus(false);
                    return;
                }
                int c3 = c(context, bundle);
                TbsLog.i("TbsInstaller", "TbsInstaller-installLocalTesCoreExInThread doTpatch result is " + c3);
                if (c3 == 1) {
                    l.a(context).a("tpatch_num", l.a(context).c("tpatch_num") + 1);
                }
                lock.unlock();
                c();
                if (i3 == 5) {
                    f(context, c3);
                }
                QbSdk.setTBSInstallingStatus(false);
                return;
            } else {
                tbsDownloadConfig = TbsDownloadConfig.getInstance(context);
                i2 = -541;
            }
        }
        tbsDownloadConfig.setInstallInterruptCode(i2);
    }

    /* JADX WARN: Removed duplicated region for block: B:243:0x0476 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @TargetApi(11)
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    void b(Context context, String str, int i2) {
        boolean z;
        int i3;
        int i4;
        int i5;
        SharedPreferences sharedPreferences;
        TbsLogReport tbsLogReport;
        int q;
        String str2;
        String str3;
        boolean z2;
        TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-501);
        if (c(context)) {
            TbsLog.i("TbsInstaller", "isTbsLocalInstalled --> no installation!", true);
            TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-502);
            QbSdk.f17732n.onInstallFinish(-502);
            return;
        }
        TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCoreInThread tbsApkPath=" + str);
        TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCoreInThread tbsCoreTargetVer=" + i2);
        TbsLog.i("TbsInstaller", "TbsInstaller-continueInstallTbsCore currentProcessName=" + context.getApplicationInfo().processName);
        TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCoreInThread currentProcessId=" + Process.myPid());
        TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCoreInThread currentThreadName=" + Thread.currentThread().getName());
        int i6 = Build.VERSION.SDK_INT;
        if ((i6 >= 11 ? context.getSharedPreferences("tbs_preloadx5_check_cfg_file", 4) : context.getSharedPreferences("tbs_preloadx5_check_cfg_file", 0)).getInt("tbs_precheck_disable_version", -1) == i2) {
            TbsLog.e("TbsInstaller", "TbsInstaller-installTbsCoreInThread -- version:" + i2 + " is disabled by preload_x5_check!");
            TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-503);
            QbSdk.f17732n.onInstallFinish(-503);
        } else if (!FileUtil.b(context)) {
            long a2 = com.tencent.smtt.utils.s.a();
            long downloadMinFreeSpace = TbsDownloadConfig.getInstance(context).getDownloadMinFreeSpace();
            TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-504);
            String str4 = "rom is not enough when installing tbs core! curAvailROM=" + a2 + ",minReqRom=" + downloadMinFreeSpace;
            TbsLogReport.getInstance(context).setInstallErrorCode(210, str4);
            TbsLog.i("TbsInstaller", str4);
            QbSdk.f17732n.onInstallFinish(210);
        } else if (!p(context)) {
            TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-505);
            TbsLog.i("TbsInstaller", "getInstalling file lock failed,return!");
            QbSdk.f17732n.onInstallFinish(-505);
        } else {
            Lock lock = f17848i;
            boolean tryLock = lock.tryLock();
            TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCoreInThread locked =" + tryLock);
            if (!tryLock) {
                TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-519);
                c();
                return;
            }
            TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-507);
            ReentrantLock reentrantLock = f17847h;
            reentrantLock.lock();
            try {
                int c2 = l.a(context).c("copy_core_ver");
                int b2 = l.a(context).b();
                TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCoreInThread tbsCoreCopyVer =" + c2);
                TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCoreInThread tbsCoreInstallVer =" + b2);
                TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCoreInThread tbsCoreTargetVer =" + i2);
                if ((b2 > 0 && i2 > b2) || (c2 > 0 && i2 > c2)) {
                    l(context);
                }
                int c3 = l.a(context).c();
                int g2 = g(context);
                TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCoreInThread installStatus1=" + c3);
                TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCoreInThread tbsCoreInstalledVer=" + g2);
                if (c3 < 0 || c3 >= 2) {
                    if (c3 == 3 && g2 >= 0 && (i2 > g2 || i2 == 88888888)) {
                        l(context);
                        TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCoreInThread -- update TBS.....", true);
                        c3 = -1;
                    }
                    z = false;
                } else {
                    TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCoreInThread -- retry.....", true);
                    z = true;
                }
                TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-508);
                TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCoreInThread installStatus2=" + c3);
                if (c3 < 1) {
                    TbsLog.i("TbsInstaller", "STEP 2/2 begin installation.....", true);
                    TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-509);
                    if (z) {
                        int c4 = l.a(context).c("unzip_retry_num");
                        if (c4 > 10) {
                            TbsLogReport.getInstance(context).setInstallErrorCode(201, "exceed unzip retry num!");
                            y(context);
                            TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-510);
                            try {
                                reentrantLock.unlock();
                                lock.unlock();
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                            try {
                                c();
                                return;
                            } catch (Exception e3) {
                                e3.printStackTrace();
                                return;
                            }
                        }
                        l.a(context).b(c4 + 1);
                    }
                    if (str == null) {
                        str3 = l.a(context).d("install_apk_path");
                        if (str3 == null) {
                            TbsLogReport.getInstance(context).setInstallErrorCode(202, "apk path is null!");
                            TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-511);
                            try {
                                reentrantLock.unlock();
                                lock.unlock();
                            } catch (Exception e4) {
                                e4.printStackTrace();
                            }
                            try {
                                c();
                                return;
                            } catch (Exception e5) {
                                e5.printStackTrace();
                                return;
                            }
                        }
                    } else {
                        str3 = str;
                    }
                    TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCoreInThread apkPath =" + str3);
                    i4 = b(context, str3);
                    if (i4 == 0) {
                        TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-512);
                        TbsLogReport.getInstance(context).setInstallErrorCode(203, "apk version is 0!");
                        try {
                            reentrantLock.unlock();
                            lock.unlock();
                        } catch (Exception e6) {
                            e6.printStackTrace();
                        }
                        try {
                            c();
                            return;
                        } catch (Exception e7) {
                            e7.printStackTrace();
                            return;
                        }
                    }
                    l.a(context).a("install_apk_path", str3);
                    l.a(context).c(i4, 0);
                    TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-548);
                    if (!b(context, new File(str3))) {
                        TbsLogReport.getInstance(context).setInstallErrorCode(207, "unzipTbsApk failed");
                        try {
                            reentrantLock.unlock();
                            lock.unlock();
                        } catch (Exception e8) {
                            e8.printStackTrace();
                        }
                        try {
                            c();
                            return;
                        } catch (Exception e9) {
                            e9.printStackTrace();
                            return;
                        }
                    }
                    if (z) {
                        int b3 = l.a(context).b("unlzma_status");
                        if (b3 > 5) {
                            TbsLogReport.getInstance(context).setInstallErrorCode(223, "exceed unlzma retry num!");
                            TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-553);
                            y(context);
                            k.b(context);
                            Map<String, Object> map = TbsDownloadConfig.getInstance(context).mSyncMap;
                            Boolean bool = Boolean.TRUE;
                            map.put(TbsDownloadConfig.TbsConfigKey.KEY_NEEDDOWNLOAD, bool);
                            TbsDownloadConfig.getInstance(context).mSyncMap.put(TbsDownloadConfig.TbsConfigKey.KEY_FULL_PACKAGE, bool);
                            TbsDownloadConfig.getInstance(context).commit();
                            try {
                                reentrantLock.unlock();
                                lock.unlock();
                            } catch (Exception e10) {
                                e10.printStackTrace();
                            }
                            try {
                                c();
                                return;
                            } catch (Exception e11) {
                                e11.printStackTrace();
                                return;
                            }
                        }
                        l.a(context).d(b3 + 1);
                    }
                    TbsLog.i("TbsInstaller", "unlzma begin");
                    int i7 = TbsDownloadConfig.getInstance().mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_RESPONSECODE, 0);
                    if (g(context) != 0) {
                        Object a3 = QbSdk.a(context, "can_unlzma", (Bundle) null);
                        if ((a3 == null || !(a3 instanceof Boolean)) ? false : ((Boolean) a3).booleanValue()) {
                            Bundle bundle = new Bundle();
                            bundle.putInt("responseCode", i7);
                            bundle.putString("unzip_temp_path", TbsDownloader.a(context) ? m(context).getAbsolutePath() : e(context, 0).getAbsolutePath());
                            Object a4 = QbSdk.a(context, "unlzma", bundle);
                            if (a4 == null) {
                                TbsLog.i("TbsInstaller", "unlzma return null");
                                TbsLogReport.getInstance(context).setInstallErrorCode(222, "unlzma is null");
                            } else {
                                if (!(a4 instanceof Boolean)) {
                                    if (!(a4 instanceof Bundle)) {
                                        if (a4 instanceof Throwable) {
                                            TbsLog.i("TbsInstaller", "unlzma failure because Throwable" + Log.getStackTraceString((Throwable) a4));
                                            TbsLogReport.getInstance(context).setInstallErrorCode(222, (Throwable) a4);
                                        }
                                    }
                                    z2 = true;
                                } else if (((Boolean) a4).booleanValue()) {
                                    TbsLog.i("TbsInstaller", "unlzma success");
                                    z2 = true;
                                } else {
                                    TbsLog.i("TbsInstaller", "unlzma return false");
                                    TbsLogReport.getInstance(context).setInstallErrorCode(222, "unlzma return false");
                                }
                                if (!z2) {
                                    try {
                                        reentrantLock.unlock();
                                        lock.unlock();
                                    } catch (Exception e12) {
                                        e12.printStackTrace();
                                    }
                                    try {
                                        c();
                                        return;
                                    } catch (Exception e13) {
                                        e13.printStackTrace();
                                        return;
                                    }
                                }
                            }
                            z2 = false;
                            if (!z2) {
                            }
                        }
                    }
                    TbsLog.i("TbsInstaller", "unlzma finished");
                    l.a(context).c(i4, 1);
                    i3 = 2;
                } else {
                    i3 = 2;
                    i4 = 0;
                }
                if (c3 < i3) {
                    if (z) {
                        int c5 = l.a(context).c("dexopt_retry_num");
                        if (c5 > 10) {
                            TbsLogReport.getInstance(context).setInstallErrorCode(208, "exceed dexopt retry num!");
                            TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-514);
                            y(context);
                            try {
                                reentrantLock.unlock();
                                lock.unlock();
                            } catch (Exception e14) {
                                e14.printStackTrace();
                            }
                            try {
                                c();
                                return;
                            } catch (Exception e15) {
                                e15.printStackTrace();
                                return;
                            }
                        }
                        l.a(context).a(c5 + 1);
                    }
                    TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-549);
                    if (!g(context, 0)) {
                        TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-515);
                        try {
                            reentrantLock.unlock();
                            lock.unlock();
                        } catch (Exception e16) {
                            e16.printStackTrace();
                        }
                        try {
                            c();
                            return;
                        } catch (Exception e17) {
                            e17.printStackTrace();
                            return;
                        }
                    }
                    l.a(context).c(i4, 2);
                    TbsLog.i("TbsInstaller", "STEP 2/2 installation completed! you can restart!", true);
                    TbsLog.i("TbsInstaller", "STEP 2/2 installation completed! you can restart! version:" + i2);
                    TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-516);
                    if (i6 >= 11) {
                        sharedPreferences = context.getSharedPreferences("tbs_preloadx5_check_cfg_file", 4);
                        i5 = 0;
                    } else {
                        i5 = 0;
                        sharedPreferences = context.getSharedPreferences("tbs_preloadx5_check_cfg_file", 0);
                    }
                    SharedPreferences.Editor edit = sharedPreferences.edit();
                    edit.putInt("tbs_preload_x5_counter", i5);
                    edit.putInt("tbs_preload_x5_recorder", i5);
                    edit.putInt("tbs_preload_x5_version", i2);
                    edit.commit();
                    TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-517);
                    if (i2 == 88888888) {
                        a(i2, str, context);
                    }
                    if (this.f17856j) {
                        tbsLogReport = TbsLogReport.getInstance(context);
                        q = q(context);
                        str2 = "continueInstallWithout core success";
                    } else {
                        if (str != null) {
                            FileUtil.b(new File(str));
                        }
                        tbsLogReport = TbsLogReport.getInstance(context);
                        q = q(context);
                        str2 = "success";
                    }
                    tbsLogReport.setInstallErrorCode(q, str2);
                } else if (c3 == i3) {
                    if (str != null) {
                        FileUtil.b(new File(str));
                    }
                    QbSdk.f17732n.onInstallFinish(200);
                }
                try {
                    f17847h.unlock();
                    f17848i.unlock();
                } catch (Exception e18) {
                    e18.printStackTrace();
                }
                try {
                    c();
                } catch (Exception e19) {
                    e19.printStackTrace();
                }
            } catch (Throwable th) {
                try {
                    f17847h.unlock();
                    f17848i.unlock();
                } catch (Exception e20) {
                    e20.printStackTrace();
                }
                try {
                    c();
                } catch (Exception e21) {
                    e21.printStackTrace();
                }
                throw th;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(Context context, boolean z) {
        if (QbSdk.b) {
            return;
        }
        if (Build.VERSION.SDK_INT < 8) {
            TbsLog.e("TbsInstaller", "android version < 2.1 no need install X5 core", true);
            return;
        }
        TbsLog.i("TbsInstaller", "installTbsCoreIfNeeded", "#1# check local x5core prepared to install");
        if (r(context)) {
            TbsLog.i("TbsInstaller", "installTbsCoreIfNeeded", "#2# try to install tbs core from tmp dir");
            TbsLog.i("TbsInstaller", (a(context, "core_unzip_tmp") && d(context, z)) ? "TbsInstaller-installTbsCoreIfNeeded, enableTbsCoreFromUnzip !!" : (a(context, "tpatch_tmp") && c(context, z)) ? "TbsInstaller-installTbsCoreIfNeeded, enableTbsCoreFromTpatch !!" : "TbsInstaller-installTbsCoreIfNeeded, error !!");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean b(Context context, int i2) {
        if (TbsDownloader.getOverSea(context)) {
            return false;
        }
        TbsLog.i("TbsInstaller", "TbsInstaller-installLocalTbsCore targetTbsCoreVer=" + i2);
        TbsLog.i("TbsInstaller", "TbsInstaller-continueInstallTbsCore currentProcessName=" + context.getApplicationInfo().processName);
        TbsLog.i("TbsInstaller", "TbsInstaller-installLocalTbsCore currentProcessId=" + Process.myPid());
        TbsLog.i("TbsInstaller", "TbsInstaller-installLocalTbsCore currentThreadName=" + Thread.currentThread().getName());
        Context c2 = c(context, i2);
        if (c2 == null) {
            TbsLog.i("TbsInstaller", "TbsInstaller--installLocalTbsCore copy from null");
            return false;
        }
        Object[] objArr = {c2, context, Integer.valueOf(i2)};
        Message message = new Message();
        message.what = 2;
        message.obj = objArr;
        f17851m.sendMessage(message);
        return true;
    }

    @Deprecated
    public Context c(Context context, int i2) {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void c() {
        int i2 = this.d;
        if (i2 <= 0) {
            TbsLog.i("TbsInstaller", "releaseTbsInstallingFileLock currentTbsFileLockStackCount=" + this.d + "call stack:" + Log.getStackTraceString(new Throwable()));
        } else if (i2 > 1) {
            TbsLog.i("TbsInstaller", "releaseTbsInstallingFileLock with skip");
            this.d--;
        } else {
            if (i2 == 1) {
                TbsLog.i("TbsInstaller", "releaseTbsInstallingFileLock without skip");
                FileUtil.a(this.f17853e, this.f17854f);
                this.d = 0;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean c(Context context) {
        File file = new File(n(context), "tbs.conf");
        boolean z = false;
        if (!file.exists()) {
            return false;
        }
        Properties properties = new Properties();
        BufferedInputStream bufferedInputStream = null;
        try {
            BufferedInputStream bufferedInputStream2 = new BufferedInputStream(new FileInputStream(file));
            try {
                properties.load(bufferedInputStream2);
                boolean booleanValue = Boolean.valueOf(properties.getProperty("tbs_local_installation", DYConstants.DY_FALSE)).booleanValue();
                if (booleanValue) {
                    try {
                        if (System.currentTimeMillis() - file.lastModified() > 259200000) {
                            z = true;
                        }
                    } catch (Throwable th) {
                        th = th;
                        z = booleanValue;
                        bufferedInputStream = bufferedInputStream2;
                        try {
                            th.printStackTrace();
                            return z;
                        } finally {
                            if (bufferedInputStream != null) {
                                try {
                                    bufferedInputStream.close();
                                } catch (IOException e2) {
                                    e2.printStackTrace();
                                }
                            }
                        }
                    }
                }
                TbsLog.i("TbsInstaller", "TBS_LOCAL_INSTALLATION is:" + booleanValue + " expired=" + z);
                boolean z2 = booleanValue & (!z);
                try {
                    bufferedInputStream2.close();
                    return z2;
                } catch (IOException e3) {
                    e3.printStackTrace();
                    return z2;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int d(Context context, int i2) {
        return a(e(context, i2));
    }

    public void d(Context context) {
        BufferedInputStream bufferedInputStream;
        BufferedOutputStream bufferedOutputStream;
        try {
            File file = new File(n(context), "tbs.conf");
            Properties properties = new Properties();
            BufferedOutputStream bufferedOutputStream2 = null;
            try {
                bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                try {
                    properties.load(bufferedInputStream);
                    bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
                } catch (Throwable unused) {
                }
            } catch (Throwable unused2) {
                bufferedInputStream = null;
            }
            try {
                properties.setProperty("tbs_local_installation", DYConstants.DY_FALSE);
                properties.store(bufferedOutputStream, (String) null);
                try {
                    bufferedOutputStream.close();
                } catch (IOException unused3) {
                }
            } catch (Throwable unused4) {
                bufferedOutputStream2 = bufferedOutputStream;
                if (bufferedOutputStream2 != null) {
                    try {
                        bufferedOutputStream2.close();
                    } catch (IOException unused5) {
                    }
                }
                if (bufferedInputStream == null) {
                    return;
                }
                bufferedInputStream.close();
            }
            bufferedInputStream.close();
        } catch (Throwable unused6) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public File e(Context context, int i2) {
        return a(context, i2, true);
    }

    public void e(Context context) {
        FileLock fileLock = f17849k;
        if (fileLock != null) {
            FileUtil.a(context, fileLock);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int f(Context context) {
        BufferedInputStream bufferedInputStream = null;
        try {
            File file = new File(m(context), "tbs.conf");
            if (file.exists()) {
                Properties properties = new Properties();
                BufferedInputStream bufferedInputStream2 = new BufferedInputStream(new FileInputStream(file));
                try {
                    properties.load(bufferedInputStream2);
                    bufferedInputStream2.close();
                    String property = properties.getProperty("tbs_core_version");
                    if (property == null) {
                        try {
                            bufferedInputStream2.close();
                        } catch (IOException unused) {
                        }
                        return 0;
                    }
                    int parseInt = Integer.parseInt(property);
                    try {
                        bufferedInputStream2.close();
                    } catch (IOException unused2) {
                    }
                    return parseInt;
                } catch (Exception unused3) {
                    bufferedInputStream = bufferedInputStream2;
                    if (bufferedInputStream != null) {
                        try {
                            bufferedInputStream.close();
                        } catch (IOException unused4) {
                        }
                    }
                    return 0;
                } catch (Throwable th) {
                    th = th;
                    bufferedInputStream = bufferedInputStream2;
                    if (bufferedInputStream != null) {
                        try {
                            bufferedInputStream.close();
                        } catch (IOException unused5) {
                        }
                    }
                    throw th;
                }
            }
            return 0;
        } catch (Exception unused6) {
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int g(Context context) {
        BufferedInputStream bufferedInputStream = null;
        try {
            try {
                File file = new File(n(context), "tbs.conf");
                if (file.exists()) {
                    TbsLog.i("TbsInstaller", "getTbsCoreInstalledVerInNolock tbsPropFile is " + file.getAbsolutePath());
                    Properties properties = new Properties();
                    BufferedInputStream bufferedInputStream2 = new BufferedInputStream(new FileInputStream(file));
                    try {
                        properties.load(bufferedInputStream2);
                        bufferedInputStream2.close();
                        String property = properties.getProperty("tbs_core_version");
                        if (property == null) {
                            try {
                                bufferedInputStream2.close();
                            } catch (IOException e2) {
                                TbsLog.i("TbsInstaller", "TbsInstaller--getTbsCoreInstalledVerInNolock IOException=" + e2.toString());
                            }
                            return 0;
                        }
                        int parseInt = Integer.parseInt(property);
                        if (f17852n == 0) {
                            f17852n = parseInt;
                        }
                        try {
                            bufferedInputStream2.close();
                        } catch (IOException e3) {
                            TbsLog.i("TbsInstaller", "TbsInstaller--getTbsCoreInstalledVerInNolock IOException=" + e3.toString());
                        }
                        return parseInt;
                    } catch (Exception e4) {
                        e = e4;
                        bufferedInputStream = bufferedInputStream2;
                        TbsLog.i("TbsInstaller", "TbsInstaller--getTbsCoreInstalledVerInNolock Exception=" + e.toString());
                        if (bufferedInputStream != null) {
                            try {
                                bufferedInputStream.close();
                            } catch (IOException e5) {
                                TbsLog.i("TbsInstaller", "TbsInstaller--getTbsCoreInstalledVerInNolock IOException=" + e5.toString());
                            }
                        }
                        return 0;
                    } catch (Throwable th) {
                        th = th;
                        bufferedInputStream = bufferedInputStream2;
                        if (bufferedInputStream != null) {
                            try {
                                bufferedInputStream.close();
                            } catch (IOException e6) {
                                TbsLog.i("TbsInstaller", "TbsInstaller--getTbsCoreInstalledVerInNolock IOException=" + e6.toString());
                            }
                        }
                        throw th;
                    }
                }
                return 0;
            } catch (Exception e7) {
                e = e7;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int h(Context context) {
        int i2 = f17852n;
        return i2 != 0 ? i2 : g(context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void i(Context context) {
        if (f17852n != 0) {
            return;
        }
        f17852n = g(context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean j(Context context) {
        return new File(n(context), "tbs.conf").exists();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int k(Context context) {
        if (!p(context)) {
            return -1;
        }
        ReentrantLock reentrantLock = f17847h;
        boolean tryLock = reentrantLock.tryLock();
        TbsLog.i("TbsInstaller", "TbsInstaller--getTbsCoreInstalledVerWithLock locked=" + tryLock);
        if (!tryLock) {
            c();
            return 0;
        }
        BufferedInputStream bufferedInputStream = null;
        try {
            try {
                File file = new File(n(context), "tbs.conf");
                if (!file.exists()) {
                    try {
                        if (reentrantLock.isHeldByCurrentThread()) {
                            reentrantLock.unlock();
                        }
                    } catch (Throwable th) {
                        TbsLog.e("TbsInstaller", "TbsRenameLock.unlock exception: " + th);
                    }
                    c();
                    return 0;
                }
                Properties properties = new Properties();
                BufferedInputStream bufferedInputStream2 = new BufferedInputStream(new FileInputStream(file));
                try {
                    properties.load(bufferedInputStream2);
                    bufferedInputStream2.close();
                    String property = properties.getProperty("tbs_core_version");
                    if (property == null) {
                        try {
                            bufferedInputStream2.close();
                        } catch (IOException e2) {
                            TbsLog.i("TbsInstaller", "TbsInstaller--getTbsCoreInstalledVerWithLock IOException=" + e2.toString());
                        }
                        try {
                            ReentrantLock reentrantLock2 = f17847h;
                            if (reentrantLock2.isHeldByCurrentThread()) {
                                reentrantLock2.unlock();
                            }
                        } catch (Throwable th2) {
                            TbsLog.e("TbsInstaller", "TbsRenameLock.unlock exception: " + th2);
                        }
                        c();
                        return 0;
                    }
                    ThreadLocal<Integer> threadLocal = f17850l;
                    threadLocal.set(Integer.valueOf(Integer.parseInt(property)));
                    int intValue = threadLocal.get().intValue();
                    try {
                        bufferedInputStream2.close();
                    } catch (IOException e3) {
                        TbsLog.i("TbsInstaller", "TbsInstaller--getTbsCoreInstalledVerWithLock IOException=" + e3.toString());
                    }
                    try {
                        ReentrantLock reentrantLock3 = f17847h;
                        if (reentrantLock3.isHeldByCurrentThread()) {
                            reentrantLock3.unlock();
                        }
                    } catch (Throwable th3) {
                        TbsLog.e("TbsInstaller", "TbsRenameLock.unlock exception: " + th3);
                    }
                    c();
                    return intValue;
                } catch (Exception e4) {
                    e = e4;
                    bufferedInputStream = bufferedInputStream2;
                    TbsLog.i("TbsInstaller", "TbsInstaller--getTbsCoreInstalledVerWithLock Exception=" + e.toString());
                    if (bufferedInputStream != null) {
                        try {
                            bufferedInputStream.close();
                        } catch (IOException e5) {
                            TbsLog.i("TbsInstaller", "TbsInstaller--getTbsCoreInstalledVerWithLock IOException=" + e5.toString());
                        }
                    }
                    try {
                        ReentrantLock reentrantLock4 = f17847h;
                        if (reentrantLock4.isHeldByCurrentThread()) {
                            reentrantLock4.unlock();
                        }
                    } catch (Throwable th4) {
                        TbsLog.e("TbsInstaller", "TbsRenameLock.unlock exception: " + th4);
                    }
                    c();
                    return 0;
                } catch (Throwable th5) {
                    th = th5;
                    bufferedInputStream = bufferedInputStream2;
                    if (bufferedInputStream != null) {
                        try {
                            bufferedInputStream.close();
                        } catch (IOException e6) {
                            TbsLog.i("TbsInstaller", "TbsInstaller--getTbsCoreInstalledVerWithLock IOException=" + e6.toString());
                        }
                    }
                    try {
                        ReentrantLock reentrantLock5 = f17847h;
                        if (reentrantLock5.isHeldByCurrentThread()) {
                            reentrantLock5.unlock();
                        }
                    } catch (Throwable th6) {
                        TbsLog.e("TbsInstaller", "TbsRenameLock.unlock exception: " + th6);
                    }
                    c();
                    throw th;
                }
            } catch (Throwable th7) {
                th = th7;
            }
        } catch (Exception e7) {
            e = e7;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void l(Context context) {
        TbsLog.i("TbsInstaller", "TbsInstaller--cleanStatusAndTmpDir");
        l.a(context).a(0);
        l.a(context).b(0);
        l.a(context).d(0);
        l.a(context).a("incrupdate_retry_num", 0);
        if (TbsDownloader.a(context)) {
            return;
        }
        l.a(context).c(0, -1);
        l.a(context).a("");
        l.a(context).a("copy_retry_num", 0);
        l.a(context).c(-1);
        l.a(context).a(0, -1);
        FileUtil.a(e(context, 0), true);
        FileUtil.a(e(context, 1), true);
    }

    File m(Context context) {
        File file = new File(QbSdk.getTbsFolderDir(context), "core_share_decouple");
        if (file.isDirectory() || file.mkdir()) {
            return file;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public File n(Context context) {
        File file = new File(QbSdk.getTbsFolderDir(context), "core_share");
        if (file.isDirectory() || file.mkdir()) {
            return file;
        }
        TbsLog.i("TbsInstaller", "getTbsCoreShareDir,mkdir false");
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized boolean p(Context context) {
        if (this.d > 0) {
            TbsLog.i("TbsInstaller", "getTbsInstallingFileLock success,is cached= true");
            this.d++;
            return true;
        }
        FileOutputStream b2 = FileUtil.b(context, true, "tbslock.txt");
        this.f17854f = b2;
        if (b2 == null) {
            TbsLog.i("TbsInstaller", "getTbsInstallingFileLock get install fos failed");
            return false;
        }
        FileLock a2 = FileUtil.a(context, b2);
        this.f17853e = a2;
        if (a2 == null) {
            TbsLog.i("TbsInstaller", "getTbsInstallingFileLock tbsFileLockFileLock == null");
            return false;
        }
        TbsLog.i("TbsInstaller", "getTbsInstallingFileLock success,is cached= false");
        this.d++;
        return true;
    }
}
