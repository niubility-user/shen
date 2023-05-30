package com.jd.dynamic.b.f;

import android.text.TextUtils;
import com.jd.dynamic.b.f.a;
import com.jd.dynamic.base.DynamicFetcher;
import com.jd.dynamic.base.DynamicMtaUtil;
import com.jd.dynamic.base.DynamicSdk;
import com.jd.dynamic.base.interfaces.IExceptionHandler;
import com.jd.dynamic.base.interfaces.INetWorkRequest;
import com.jd.dynamic.entity.Template;
import com.jd.dynamic.lib.utils.p;
import com.jd.dynamic.lib.utils.t;
import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes13.dex */
public class b extends a.b {
    private final Template b;

    /* renamed from: c */
    private final String f1740c;
    private final DynamicFetcher.GlobalConfigListener d;

    /* renamed from: g */
    private boolean f1743g;

    /* renamed from: j */
    private int f1746j;

    /* renamed from: e */
    private final long f1741e = System.nanoTime();

    /* renamed from: f */
    private long f1742f = 0;

    /* renamed from: h */
    private final Object f1744h = new Object();

    /* renamed from: i */
    private final AtomicInteger f1745i = new AtomicInteger(0);

    public b(Template template, String str, int i2, DynamicFetcher.GlobalConfigListener globalConfigListener) {
        this.b = template;
        this.f1740c = str;
        this.f1746j = i2;
        this.f1743g = i2 > 0;
        this.d = globalConfigListener;
    }

    private void h() {
        this.f1743g = false;
        this.f1745i.set(0);
    }

    private void i() {
        t.g("PreDownloadCallback", "handleRetry: execute handleRetry!!");
        Template template = this.b;
        if (template != null) {
            String n2 = com.jd.dynamic.b.e.a.a.n(this.f1740c, template.businessCode);
            Template template2 = this.b;
            File file = new File(n2, p.b(template2.fullFileUrl, template2.fileObjectKey));
            if (((Boolean) com.jd.dynamic.b.e.a.a.h(file.getAbsolutePath(), this.b).first).booleanValue()) {
                if (this.d != null) {
                    t.c("PreDownloadCallback", "downloadTemplateWithRetry()  onEnd() hit local cache and verify file success", file.getAbsolutePath());
                    DynamicFetcher.checkNotifyConfigListener(this.f1740c, this.d);
                }
                h();
                return;
            }
            if (TextUtils.isEmpty(this.b.fileMd5)) {
                Template template3 = this.b;
                DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_MISS_MD5, "md5\u6821\u9a8c\u5931\u8d25\uff0c\u670d\u52a1\u7aef\u672a\u4e0b\u53d1MD5\u5b57\u6bb5", template3.bizField, template3.systemCode, new RuntimeException());
            }
            if (file.exists()) {
                file.delete();
            }
            if (DynamicSdk.getEngine().getRequest() != null) {
                INetWorkRequest request = DynamicSdk.getEngine().getRequest();
                Template template4 = this.b;
                String str = template4.fullFileUrl;
                String l2 = com.jd.dynamic.b.e.a.a.l(this.f1740c, template4.businessCode);
                Template template5 = this.b;
                request.downloadFile(str, l2, p.b(template5.fullFileUrl, template5.fileObjectKey), this);
            }
        }
    }

    @Override // com.jd.dynamic.b.f.a.b
    protected void a() {
    }

    @Override // com.jd.dynamic.b.f.a.b
    protected void b(INetWorkRequest.ErrorResponse errorResponse) {
        this.f1742f = System.nanoTime();
        synchronized (this.f1744h) {
            if (this.f1743g && this.f1745i.getAndIncrement() != this.f1746j) {
                if (this.f1743g) {
                    t.g("PreDownloadCallback", this.f1745i.get() + "time retry ,duration==> " + (this.f1742f - this.f1741e));
                    i();
                }
                return;
            }
            t.g("PreDownloadCallback", "handleError:no retry time or not use retry!");
            if (errorResponse != null && errorResponse.errorCode == -105239) {
                t.c("this is cancel : " + errorResponse.errorMsg);
                DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_STORAGE, "cancel", "", this.f1740c, 1012, (Exception) null);
            } else if (this.f1745i.get() > 0) {
                DynamicMtaUtil.uploadDownloadTempMta(f(), d(), g(), false, true);
            } else {
                DynamicMtaUtil.uploadDownloadTempMta(f(), d(), g(), false);
            }
            h();
            DynamicFetcher.checkNotifyConfigListener(this.f1740c, this.d);
        }
    }

    @Override // com.jd.dynamic.b.f.a.b
    protected void c(File file) {
        t.g("PreDownloadCallback", "handleSuccess");
        this.f1742f = System.nanoTime();
        if (this.f1745i.get() > 0) {
            DynamicMtaUtil.uploadDownloadTempMta(f(), d(), g(), true, true);
        } else {
            DynamicMtaUtil.uploadDownloadTempMta(f(), d(), g(), true);
        }
        h();
        DynamicMtaUtil.uploadDownloadTempMta(this.f1740c, this.b, this.f1742f - this.f1741e, true);
        DynamicFetcher.checkNotifyConfigListener(this.f1740c, this.d);
    }

    @Override // com.jd.dynamic.b.f.a.b
    protected Template d() {
        return this.b;
    }

    @Override // com.jd.dynamic.b.f.a.b
    protected String f() {
        return this.f1740c;
    }

    @Override // com.jd.dynamic.b.f.a.b
    protected long g() {
        return this.f1742f - this.f1741e;
    }
}
