package com.jd.dynamic.b.f;

import com.jd.dynamic.b.f.a;
import com.jd.dynamic.base.DynamicFetcher;
import com.jd.dynamic.base.DynamicMtaUtil;
import com.jd.dynamic.base.DynamicSdk;
import com.jd.dynamic.base.interfaces.IExceptionHandler;
import com.jd.dynamic.base.interfaces.INetWorkRequest;
import com.jd.dynamic.entity.ResultEntity;
import com.jd.dynamic.entity.Template;
import com.jd.dynamic.entity.TemplateRequest;
import com.jd.dynamic.lib.utils.p;
import com.jd.dynamic.lib.utils.t;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes13.dex */
public class d extends a.b {
    private final TemplateRequest d;

    /* renamed from: e  reason: collision with root package name */
    private boolean f1755e;

    /* renamed from: g  reason: collision with root package name */
    private int f1757g;

    /* renamed from: i  reason: collision with root package name */
    private a f1759i;
    private final long b = System.nanoTime();

    /* renamed from: c  reason: collision with root package name */
    private long f1754c = 0;

    /* renamed from: f  reason: collision with root package name */
    private final AtomicInteger f1756f = new AtomicInteger(0);

    /* renamed from: h  reason: collision with root package name */
    private final Object f1758h = new Object();

    /* loaded from: classes13.dex */
    public interface a {
        void onFail(TemplateRequest templateRequest, String str, int i2);

        void onSuccess(DynamicFetcher.Listener listener, ResultEntity resultEntity, String str);
    }

    public d(TemplateRequest templateRequest, int i2) {
        this.d = templateRequest;
        this.f1757g = i2;
        this.f1755e = i2 > 0;
    }

    private void i() {
        if (this.d == null) {
            t.g("ZipDownloadCallback", "request is null");
            return;
        }
        t.g("ZipDownloadCallback", "handleRetry: execute handleRetry!!");
        if (DynamicSdk.getEngine().getRequest() != null) {
            String downloadUrl = this.d.template.getDownloadUrl();
            String b = p.b(downloadUrl, this.d.template.getDownloadFileName());
            if (this.d.template.isDownloadZip()) {
                b = b + ".zip";
            }
            INetWorkRequest request = DynamicSdk.getEngine().getRequest();
            TemplateRequest templateRequest = this.d;
            request.downloadFile(downloadUrl, com.jd.dynamic.b.e.a.b.n(templateRequest.systemCode, templateRequest.getBusinessCode()), b, this);
        }
    }

    private void j() {
        this.f1755e = false;
        this.f1756f.set(0);
    }

    @Override // com.jd.dynamic.b.f.a.b
    protected void a() {
    }

    @Override // com.jd.dynamic.b.f.a.b
    protected void b(INetWorkRequest.ErrorResponse errorResponse) {
        this.f1754c = System.nanoTime();
        synchronized (this.f1758h) {
            if (this.f1755e && this.f1756f.getAndIncrement() != this.f1757g) {
                if (this.f1755e) {
                    t.g("ZipDownloadCallback", this.f1756f.get() + "time retry ,duration==> " + (this.f1754c - this.b));
                    i();
                }
                return;
            }
            t.g("ZipDownloadCallback", "handleError:no retry time or not use retry!");
            if (errorResponse != null && errorResponse.errorCode == -105239) {
                t.c("this is cancel : " + errorResponse.errorMsg);
                DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_STORAGE, "cancel", "", this.d.systemCode, 1012, (Exception) null);
            } else if (this.f1756f.get() > 0) {
                DynamicMtaUtil.uploadDownloadTempMta(f(), d(), g(), false, true);
            } else {
                DynamicMtaUtil.uploadDownloadTempMta(f(), d(), g(), false);
            }
            j();
            a aVar = this.f1759i;
            if (aVar != null) {
                if (errorResponse == null || errorResponse.errorCode != 307) {
                    TemplateRequest templateRequest = this.d;
                    if (!templateRequest.isProvideLowVersion) {
                        aVar.onFail(templateRequest, "getTemplateZipFromNetWithRetry(),request dynamic binary file failed!", 304);
                    }
                } else {
                    aVar.onFail(this.d, errorResponse.errorMsg, 307);
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x00cc, code lost:
        if (r5.viewNode.parseSuccess == false) goto L29;
     */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00f4  */
    /* JADX WARN: Removed duplicated region for block: B:53:? A[RETURN, SYNTHETIC] */
    @Override // com.jd.dynamic.b.f.a.b
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void c(java.io.File r21) {
        /*
            Method dump skipped, instructions count: 324
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.b.f.d.c(java.io.File):void");
    }

    @Override // com.jd.dynamic.b.f.a.b
    protected Template d() {
        TemplateRequest templateRequest = this.d;
        if (templateRequest == null) {
            return null;
        }
        return templateRequest.template;
    }

    @Override // com.jd.dynamic.b.f.a.b
    protected String f() {
        TemplateRequest templateRequest = this.d;
        return templateRequest == null ? "" : templateRequest.systemCode;
    }

    @Override // com.jd.dynamic.b.f.a.b
    protected long g() {
        return this.f1754c - this.b;
    }

    public void h(a aVar) {
        this.f1759i = aVar;
    }
}
