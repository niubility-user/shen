package com.tencent.smtt.sdk;

import android.content.Context;
import android.util.Log;
import com.tencent.smtt.utils.TbsLog;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/* loaded from: classes9.dex */
public abstract class TbsBaseConfig {
    public static final String TAG = "TbsBaseConfig";
    Map<String, String> a;
    private Context b;

    private static File a(Context context, String str) {
        m.a();
        File o = m.o(context);
        if (o == null) {
            return null;
        }
        File file = new File(o, str);
        if (file.exists()) {
            return file;
        }
        try {
            file.createNewFile();
            return file;
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public void clear() {
        this.a.clear();
        commit();
    }

    public synchronized void commit() {
        writeTbsDownloadInfo();
    }

    public abstract String getConfigFileName();

    public Map<String, String> getPVCLocal() {
        return this.a;
    }

    public void init(Context context) {
        this.a = new HashMap();
        Context applicationContext = context.getApplicationContext();
        this.b = applicationContext;
        if (applicationContext == null) {
            this.b = context;
        }
        refreshSyncMap(context);
    }

    public synchronized void refreshSyncMap(Context context) {
        BufferedInputStream bufferedInputStream;
        Throwable th;
        String str;
        String str2;
        File a;
        try {
            a = a(this.b, getConfigFileName());
            TbsLog.i(TAG, "refreshSyncMap propFile is " + a);
        } catch (Throwable th2) {
            bufferedInputStream = null;
            th = th2;
        }
        if (a == null) {
            return;
        }
        this.a.clear();
        bufferedInputStream = new BufferedInputStream(new FileInputStream(a));
        try {
            Properties properties = new Properties();
            properties.load(bufferedInputStream);
            for (String str3 : properties.stringPropertyNames()) {
                this.a.put(str3, properties.getProperty(str3));
            }
            TbsLog.i(TAG, "refreshSyncMap pv mSyncMap is " + this.a.toString());
        } catch (Throwable th3) {
            th = th3;
            TbsLog.i(TAG, "refreshSyncMap stack is " + Log.getStackTraceString(th));
            if (bufferedInputStream != null) {
                try {
                    bufferedInputStream.close();
                } catch (Exception e2) {
                    str = TAG;
                    str2 = "refreshSyncMap stack is " + Log.getStackTraceString(e2);
                    TbsLog.i(str, str2);
                }
            }
        }
        try {
            bufferedInputStream.close();
        } catch (Exception e3) {
            str = TAG;
            str2 = "refreshSyncMap stack is " + Log.getStackTraceString(e3);
            TbsLog.i(str, str2);
        }
    }

    public void update(Map<String, String> map) {
        this.a.clear();
        this.a.putAll(map);
        commit();
    }

    public synchronized void writeTbsDownloadInfo() {
        Throwable th;
        BufferedOutputStream bufferedOutputStream;
        String str;
        String str2;
        File a;
        Properties properties;
        TbsLog.i(TAG, "writeTbsDownloadInfo #1");
        BufferedInputStream bufferedInputStream = null;
        try {
            a = a(this.b, getConfigFileName());
            TbsLog.i(TAG, "writeTbsDownloadInfo propFile is " + a);
        } catch (Throwable th2) {
            th = th2;
            bufferedOutputStream = null;
        }
        if (a == null) {
            return;
        }
        BufferedInputStream bufferedInputStream2 = new BufferedInputStream(new FileInputStream(a));
        try {
            properties = new Properties();
            properties.load(bufferedInputStream2);
            properties.clear();
            Set<String> keySet = this.a.keySet();
            TbsLog.i(TAG, "writeTbsDownloadInfo mSyncMap.size() is " + this.a.size());
            for (String str3 : keySet) {
                String str4 = this.a.get(str3);
                properties.setProperty(str3, "" + ((Object) str4));
                TbsLog.i(TAG, "writeTbsDownloadInfo key is " + str3 + " value is " + ((Object) str4));
            }
            this.a.clear();
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(a));
        } catch (Throwable th3) {
            th = th3;
            bufferedOutputStream = null;
        }
        try {
            properties.store(bufferedOutputStream, (String) null);
            TbsLog.i(TAG, "writeTbsDownloadInfo end ");
            try {
                bufferedInputStream2.close();
            } catch (Exception e2) {
                TbsLog.i(TAG, "writeTbsDownloadInfo stack is " + Log.getStackTraceString(e2));
            }
        } catch (Throwable th4) {
            th = th4;
            bufferedInputStream = bufferedInputStream2;
            TbsLog.i(TAG, "writeTbsDownloadInfo stack is " + Log.getStackTraceString(th));
            if (bufferedInputStream != null) {
                try {
                    bufferedInputStream.close();
                } catch (Exception e3) {
                    TbsLog.i(TAG, "writeTbsDownloadInfo stack is " + Log.getStackTraceString(e3));
                }
            }
            if (bufferedOutputStream != null) {
                try {
                    bufferedOutputStream.close();
                } catch (Exception e4) {
                    str = TAG;
                    str2 = "writeTbsDownloadInfo stack is " + Log.getStackTraceString(e4);
                    TbsLog.i(str, str2);
                }
            }
        }
        try {
            bufferedOutputStream.close();
        } catch (Exception e5) {
            str = TAG;
            str2 = "writeTbsDownloadInfo stack is " + Log.getStackTraceString(e5);
            TbsLog.i(str, str2);
        }
    }
}
