package com.jd.dynamic.b.f;

import android.text.TextUtils;
import com.jd.dynamic.b.f.a;
import com.jd.dynamic.base.DynamicFetcher;
import com.jd.dynamic.base.DynamicMtaUtil;
import com.jd.dynamic.base.DynamicSdk;
import com.jd.dynamic.base.interfaces.IExceptionHandler;
import com.jd.dynamic.base.interfaces.INetWorkRequest;
import com.jd.dynamic.entity.ResultEntity;
import com.jd.dynamic.entity.Template;
import com.jd.dynamic.entity.TemplateRequest;
import com.jd.dynamic.entity.ViewNode;
import com.jd.dynamic.lib.dynamic.parser.NewDynamicXParser;
import com.jd.dynamic.lib.utils.m;
import com.jd.dynamic.lib.utils.o;
import com.jd.dynamic.lib.utils.p;
import com.jd.dynamic.lib.utils.t;
import com.jingdong.sdk.platform.business.personal.R2;
import java.io.File;
import java.util.List;
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
    */
    protected void c(File file) {
        TemplateRequest templateRequest;
        a aVar;
        ViewNode viewNode;
        this.f1754c = System.nanoTime();
        boolean z = true;
        if (this.f1756f.get() > 0) {
            DynamicMtaUtil.uploadDownloadTempMta(f(), d(), g(), true, true);
        } else {
            DynamicMtaUtil.uploadDownloadTempMta(f(), d(), g(), true);
        }
        j();
        ResultEntity resultEntity = new ResultEntity();
        String absolutePath = file.getAbsolutePath();
        boolean z2 = false;
        try {
            if (this.d.template.isDownloadZip()) {
                try {
                    long nanoTime = System.nanoTime();
                    String i2 = o.i(absolutePath);
                    List<File> c2 = o.c(absolutePath, i2);
                    m.H(absolutePath);
                    try {
                        long nanoTime2 = System.nanoTime();
                        TemplateRequest templateRequest2 = this.d;
                        DynamicMtaUtil.uploadUnZipTempMta(templateRequest2.systemCode, templateRequest2.template, nanoTime2 - nanoTime, c2 != null, false);
                        absolutePath = i2;
                    } catch (Exception e2) {
                        e = e2;
                        absolutePath = i2;
                        DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_UNZIP, "unzip catched exception", this.d.getBizField(), this.d.systemCode, e);
                        t.e("ZipDownloadCallback", "getTemplateZipFromNetWithRetry(),faild to unzip");
                        String bizField = this.d.getBizField();
                        TemplateRequest templateRequest3 = this.d;
                        resultEntity = NewDynamicXParser.parseBinaryToResultEntity(absolutePath, bizField, templateRequest3.systemCode, templateRequest3.mtaId);
                        DynamicMtaUtil.appendDownloadTypeMtaStat(this.d.mtaId, 3);
                        TemplateRequest templateRequest4 = this.d;
                        com.jd.dynamic.b.e.a.b.e(templateRequest4.systemCode, templateRequest4.template);
                        if (resultEntity != null) {
                        }
                        z = false;
                        z2 = z;
                        templateRequest = this.d;
                        if (templateRequest == null) {
                        }
                    }
                } catch (Exception e3) {
                    e = e3;
                }
            }
            String bizField2 = this.d.getBizField();
            TemplateRequest templateRequest32 = this.d;
            resultEntity = NewDynamicXParser.parseBinaryToResultEntity(absolutePath, bizField2, templateRequest32.systemCode, templateRequest32.mtaId);
            DynamicMtaUtil.appendDownloadTypeMtaStat(this.d.mtaId, 3);
            TemplateRequest templateRequest42 = this.d;
            com.jd.dynamic.b.e.a.b.e(templateRequest42.systemCode, templateRequest42.template);
            if (resultEntity != null && (viewNode = resultEntity.viewNode) != null && !TextUtils.isEmpty(viewNode.getViewName())) {
            }
            z = false;
            z2 = z;
        } catch (Exception e4) {
            DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_PARSE, "getTemplateZipFromNetWithRetry catched exception", this.d.getBizField(), this.d.systemCode, (int) R2.attr.liteMode, e4);
            t.e("ZipDownloadCallback", "getTemplateZipFromNetWithRetry(),faild to parse dynamic binary");
        }
        templateRequest = this.d;
        if (templateRequest == null) {
            if (z2 && !templateRequest.isProvideLowVersion) {
                a aVar2 = this.f1759i;
                if (aVar2 != null) {
                    aVar2.onSuccess(templateRequest.listener, resultEntity, templateRequest.mtaId);
                    return;
                }
                return;
            }
            DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_PARSE, "getTemplateZipFromNetWithRetry parse fail", this.d.getBizField() + "-" + this.d.getVersion(), this.d.systemCode, (int) R2.attr.list_item_divider, (Exception) null);
            TemplateRequest templateRequest5 = this.d;
            if (templateRequest5.isProvideLowVersion || (aVar = this.f1759i) == null) {
                return;
            }
            aVar.onFail(templateRequest5, "getTemplateZipFromNetWithRetry(),faild to parse dynamic binary", 305);
        }
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
