package com.tencent.smtt.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.text.TextUtils;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Properties;

/* loaded from: classes9.dex */
public class o {

    /* renamed from: c  reason: collision with root package name */
    private static o f17978c;
    private Context a;
    private File b = null;
    private String d = "https://log.tbs.qq.com/ajax?c=pu&v=2&k=";

    /* renamed from: e  reason: collision with root package name */
    private String f17979e = "https://log.tbs.qq.com/ajax?c=pu&tk=";

    /* renamed from: f  reason: collision with root package name */
    private String f17980f = "https://log.tbs.qq.com/ajax?c=dl&k=";

    /* renamed from: g  reason: collision with root package name */
    private String f17981g = "https://cfg.imtt.qq.com/tbs?v=2&mk=";

    /* renamed from: h  reason: collision with root package name */
    private String f17982h = "https://log.tbs.qq.com/ajax?c=ul&v=2&k=";

    @TargetApi(11)
    private o(Context context) {
        this.a = null;
        TbsLog.w("TbsCommonConfig", "TbsCommonConfig constructing...");
        this.a = context.getApplicationContext();
        f();
    }

    public static synchronized o a() {
        o oVar;
        synchronized (o.class) {
            oVar = f17978c;
        }
        return oVar;
    }

    public static synchronized o a(Context context) {
        o oVar;
        synchronized (o.class) {
            if (f17978c == null) {
                f17978c = new o(context);
            }
            oVar = f17978c;
        }
        return oVar;
    }

    private synchronized void f() {
        File g2;
        BufferedInputStream bufferedInputStream = null;
        try {
            g2 = g();
        } catch (Throwable th) {
            th = th;
        }
        if (g2 == null) {
            TbsLog.e("TbsCommonConfig", "Config file is null, default values will be applied");
            return;
        }
        BufferedInputStream bufferedInputStream2 = new BufferedInputStream(new FileInputStream(g2));
        try {
            Properties properties = new Properties();
            properties.load(bufferedInputStream2);
            String property = properties.getProperty("pv_post_url", "");
            if (!"".equals(property)) {
                this.d = property;
            }
            String property2 = properties.getProperty("tbs_download_stat_post_url", "");
            if (!"".equals(property2)) {
                this.f17980f = property2;
            }
            String property3 = properties.getProperty("tbs_downloader_post_url", "");
            TbsLog.i("TbsCommonConfig", "KEY_TBS_DOWNLOADER_POST_URL is " + property3);
            if (!"".equals(property3)) {
                this.f17981g = property3;
            }
            String property4 = properties.getProperty("tbs_log_post_url", "");
            if (!"".equals(property4)) {
                this.f17982h = property4;
            }
            String property5 = properties.getProperty("pv_post_url_tk", "");
            if (!"".equals(property5)) {
                this.f17979e = property5;
            }
        } catch (Throwable th2) {
            th = th2;
            bufferedInputStream = bufferedInputStream2;
            StringWriter stringWriter = new StringWriter();
            th.printStackTrace(new PrintWriter(stringWriter));
            TbsLog.e("TbsCommonConfig", "exceptions occurred1:" + stringWriter.toString());
            if (bufferedInputStream != null) {
                try {
                    bufferedInputStream.close();
                } catch (IOException e2) {
                    e = e2;
                    e.printStackTrace();
                }
            }
        }
        try {
            bufferedInputStream2.close();
        } catch (IOException e3) {
            e = e3;
            e.printStackTrace();
        }
    }

    private File g() {
        File file = null;
        try {
            if (this.b == null) {
                if (!TextUtils.isEmpty(this.a.getApplicationContext().getApplicationInfo().packageName)) {
                    this.b = new File(FileUtil.a(this.a, 8));
                }
                File file2 = this.b;
                if (file2 == null || !file2.isDirectory()) {
                    return null;
                }
            }
            TbsLog.i("TbsCommonConfig", "mTbsConfigDir is " + this.b.getAbsolutePath());
            File file3 = new File(this.b, "tbsnet.conf");
            if (!file3.exists()) {
                TbsLog.e("TbsCommonConfig", "Get file(" + file3.getCanonicalPath() + ") failed!");
                return null;
            }
            try {
                TbsLog.w("TbsCommonConfig", "pathc:" + file3.getCanonicalPath());
                return file3;
            } catch (Throwable th) {
                th = th;
                file = file3;
                StringWriter stringWriter = new StringWriter();
                th.printStackTrace(new PrintWriter(stringWriter));
                TbsLog.e("TbsCommonConfig", "exceptions occurred2:" + stringWriter.toString());
                return file;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public String b() {
        return this.d;
    }

    public String c() {
        return this.f17980f;
    }

    public String d() {
        return this.f17981g;
    }

    public String e() {
        return this.f17979e;
    }
}
