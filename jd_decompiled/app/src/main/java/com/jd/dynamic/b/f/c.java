package com.jd.dynamic.b.f;

import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jd.dynamic.b.f.a;
import com.jd.dynamic.base.DynamicMtaUtil;
import com.jd.dynamic.base.DynamicSdk;
import com.jd.dynamic.base.NewDynamicFetcher;
import com.jd.dynamic.base.interfaces.IExceptionHandler;
import com.jd.dynamic.base.interfaces.INetWorkRequest;
import com.jd.dynamic.entity.Template;
import com.jd.dynamic.lib.utils.m;
import com.jd.dynamic.lib.utils.o;
import com.jd.dynamic.lib.utils.p;
import com.jd.dynamic.lib.utils.t;
import java.io.File;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes13.dex */
public class c extends a.b {
    private final Template b;

    /* renamed from: c  reason: collision with root package name */
    private final String f1747c;
    private final NewDynamicFetcher.GlobalConfigListener d;

    /* renamed from: g  reason: collision with root package name */
    private boolean f1750g;

    /* renamed from: j  reason: collision with root package name */
    private int f1753j;

    /* renamed from: e  reason: collision with root package name */
    private final long f1748e = System.nanoTime();

    /* renamed from: f  reason: collision with root package name */
    private long f1749f = 0;

    /* renamed from: h  reason: collision with root package name */
    private final Object f1751h = new Object();

    /* renamed from: i  reason: collision with root package name */
    private final AtomicInteger f1752i = new AtomicInteger(0);

    public c(Template template, String str, int i2, NewDynamicFetcher.GlobalConfigListener globalConfigListener) {
        this.b = template;
        this.f1747c = str;
        this.f1753j = i2;
        this.f1750g = i2 > 0;
        this.d = globalConfigListener;
    }

    private void h() {
        this.f1750g = false;
        this.f1752i.set(0);
    }

    private void i() {
        t.g("PreZipDownloadCallback", "handleRetry: execute handleRetry!!");
        Template template = this.b;
        if (template != null) {
            if (TextUtils.equals(template.templateId, "150")) {
                t.g("downloadTemplate-nyh", "150");
            }
            File file = new File(com.jd.dynamic.b.e.a.b.p(this.f1747c, this.b.businessCode), p.b(this.b.getDownloadUrl(), this.b.getDownloadFileName()));
            if (((Boolean) com.jd.dynamic.b.e.a.b.h(file.getAbsolutePath(), this.b).first).booleanValue()) {
                if (this.d != null) {
                    t.c("downloadTemplate()  onEnd() hit local cache and verify file success", file.getAbsolutePath());
                    NewDynamicFetcher.checkNotifyConfigListener(this.f1747c, this.d);
                    return;
                }
                return;
            }
            if (TextUtils.isEmpty(this.b.getMd5())) {
                Template template2 = this.b;
                DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_MISS_MD5, "md5\u6821\u9a8c\u5931\u8d25\uff0czip\u7248\u672c\u53f7\u5df2\u6539\u53d8", template2.bizField, template2.systemCode, new RuntimeException());
            }
            if (file.exists()) {
                o.q(file);
            }
            if (DynamicSdk.getEngine().getRequest() != null) {
                String downloadUrl = this.b.getDownloadUrl();
                String b = p.b(downloadUrl, this.b.getDownloadFileName());
                if (this.b.isDownloadZip()) {
                    b = b + ".zip";
                }
                if (DynamicSdk.getEngine().getRequest() != null) {
                    DynamicSdk.getEngine().getRequest().downloadFile(downloadUrl, com.jd.dynamic.b.e.a.b.n(this.f1747c, this.b.businessCode), b, this);
                }
            }
        }
    }

    @Override // com.jd.dynamic.b.f.a.b
    protected void a() {
    }

    @Override // com.jd.dynamic.b.f.a.b
    protected void b(INetWorkRequest.ErrorResponse errorResponse) {
        this.f1749f = System.nanoTime();
        synchronized (this.f1751h) {
            if (this.f1750g && this.f1752i.getAndIncrement() != this.f1753j) {
                if (this.f1750g) {
                    t.g("PreZipDownloadCallback", this.f1752i.get() + "time retry ,duration==> " + (this.f1749f - this.f1748e));
                    i();
                }
                return;
            }
            t.g("PreZipDownloadCallback", "handleError:no retry time or not use retry!");
            if (errorResponse != null && errorResponse.errorCode == -105239) {
                t.c("this is cancel : " + errorResponse.errorMsg);
                DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_STORAGE, "cancel", "", this.f1747c, 1012, (Exception) null);
            } else if (this.f1752i.get() > 0) {
                DynamicMtaUtil.uploadDownloadTempMta(f(), d(), g(), false, true);
            } else {
                DynamicMtaUtil.uploadDownloadTempMta(f(), d(), g(), false);
            }
            h();
            NewDynamicFetcher.checkNotifyConfigListener(this.f1747c, this.d);
        }
    }

    @Override // com.jd.dynamic.b.f.a.b
    protected void c(File file) {
        t.g("PreZipDownloadCallback", "handleSuccess");
        this.f1749f = System.nanoTime();
        if (this.f1752i.get() > 0) {
            DynamicMtaUtil.uploadDownloadTempMta(f(), d(), g(), true, true);
        } else {
            DynamicMtaUtil.uploadDownloadTempMta(f(), d(), g(), true);
        }
        h();
        DynamicMtaUtil.uploadDownloadTempMta(this.f1747c, this.b, this.f1749f - this.f1748e, true);
        String absolutePath = file.getAbsolutePath();
        if (!TextUtils.isEmpty(absolutePath) && absolutePath.endsWith(".zip")) {
            try {
                long nanoTime = System.nanoTime();
                List<File> c2 = o.c(absolutePath, o.i(absolutePath));
                m.H(absolutePath);
                DynamicMtaUtil.uploadUnZipTempMta(this.f1747c, this.b, System.nanoTime() - nanoTime, c2 != null, false);
            } catch (Exception e2) {
                Template template = this.b;
                DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_UNZIP, "downloadTemplateZipWithRetry()  unzip catch error", template == null ? null : template.bizField, this.f1747c, e2);
                Object[] objArr = new Object[1];
                StringBuilder sb = new StringBuilder();
                sb.append("downloadTemplateZipWithRetry-nyh onEnd() unzip error!!   ");
                Template template2 = this.b;
                sb.append(template2 == null ? DYConstants.DY_NULL_STR : template2.templateId);
                objArr[0] = sb.toString();
                t.e(objArr);
            }
        }
        NewDynamicFetcher.checkNotifyConfigListener(this.f1747c, this.d);
    }

    @Override // com.jd.dynamic.b.f.a.b
    protected Template d() {
        return this.b;
    }

    @Override // com.jd.dynamic.b.f.a.b
    protected String f() {
        return this.f1747c;
    }

    @Override // com.jd.dynamic.b.f.a.b
    protected long g() {
        return this.f1749f - this.f1748e;
    }
}
