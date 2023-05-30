package com.jd.dynamic.b.f;

import android.text.TextUtils;
import com.jd.dynamic.base.DynamicFetcher;
import com.jd.dynamic.base.DynamicMtaUtil;
import com.jd.dynamic.base.DynamicSdk;
import com.jd.dynamic.base.interfaces.IExceptionHandler;
import com.jd.dynamic.base.interfaces.INetWorkRequest;
import com.jd.dynamic.entity.Template;
import com.jd.dynamic.entity.TemplateRequest;
import com.jd.dynamic.entity.ViewNode;
import com.jd.dynamic.lib.dynamic.parser.DynamicXParser;
import com.jd.dynamic.lib.utils.m;
import com.jd.dynamic.lib.utils.p;
import com.jd.dynamic.lib.utils.t;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.sdk.platform.business.personal.R2;
import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes13.dex */
public class a extends b {
    private final TemplateRequest d;

    /* renamed from: e */
    private boolean f1735e;

    /* renamed from: g */
    private int f1737g;

    /* renamed from: i */
    private InterfaceC0069a f1739i;
    private final long b = System.nanoTime();

    /* renamed from: c */
    private long f1734c = 0;

    /* renamed from: f */
    private final AtomicInteger f1736f = new AtomicInteger(0);

    /* renamed from: h */
    private final Object f1738h = new Object();

    /* renamed from: com.jd.dynamic.b.f.a$a */
    /* loaded from: classes13.dex */
    public interface InterfaceC0069a {
        void onFail(TemplateRequest templateRequest, String str, int i2);

        void onSuccess();
    }

    /* loaded from: classes13.dex */
    public abstract class b implements INetWorkRequest.DownloadCallBack {
        private final Object a = new Object();

        /* JADX WARN: Can't wrap try/catch for region: R(13:6|(3:38|39|(11:41|9|(1:11)(1:37)|12|13|14|15|(1:17)(1:27)|18|(2:(1:23)|24)|26))|8|9|(0)(0)|12|13|14|15|(0)(0)|18|(0)|26) */
        /* JADX WARN: Code restructure failed: missing block: B:121:0x00e8, code lost:
            r13 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:122:0x00e9, code lost:
            r10 = r13;
         */
        /* JADX WARN: Removed duplicated region for block: B:104:0x0053 A[Catch: Exception -> 0x0029, TryCatch #0 {Exception -> 0x0029, blocks: (B:96:0x0021, B:102:0x002f, B:104:0x0053, B:105:0x005f, B:106:0x0064), top: B:128:0x0021 }] */
        /* JADX WARN: Removed duplicated region for block: B:106:0x0064 A[Catch: Exception -> 0x0029, TRY_LEAVE, TryCatch #0 {Exception -> 0x0029, blocks: (B:96:0x0021, B:102:0x002f, B:104:0x0053, B:105:0x005f, B:106:0x0064), top: B:128:0x0021 }] */
        /* JADX WARN: Removed duplicated region for block: B:111:0x007c  */
        /* JADX WARN: Removed duplicated region for block: B:112:0x007f  */
        /* JADX WARN: Removed duplicated region for block: B:115:0x00a8  */
        /* JADX WARN: Removed duplicated region for block: B:124:0x00ec  */
        /* JADX WARN: Removed duplicated region for block: B:125:0x00ee  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        private boolean e(File file) {
            boolean z;
            boolean z2;
            if (file == null) {
                t.g("DownloadCallbackBase", "verifyFile: file is null,so return false,verifyFile fail");
                return false;
            }
            Template d = d();
            String f2 = f();
            String absolutePath = file.getAbsolutePath();
            if (d != null) {
                try {
                } catch (Exception e2) {
                    Exception exc = e2;
                    z2 = false;
                    DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_STORAGE, "downloadTemplateWithRetry() catch error", d != null ? null : d.bizField, f2, (int) R2.attr.layout_constraintGuide_percent, exc);
                    t.e("downloadTemplateWithRetry() onEnd() error!!");
                    return z2;
                }
                if (d.isDownloadZip()) {
                    z = true;
                    t.g("DownloadCallbackBase", "verifyFile: isZipFile: " + z + " ,filePath: " + absolutePath);
                    z2 = (!z ? (Boolean) com.jd.dynamic.b.e.a.b.u(absolutePath, d.getMd5()).first : (Boolean) com.jd.dynamic.b.e.a.a.h(absolutePath, d).first).booleanValue();
                    Object[] objArr = new Object[3];
                    StringBuilder sb = new StringBuilder();
                    sb.append("downloadTemplateWithRetry()  onEnd() verify file ");
                    sb.append(!z2 ? "success" : JDReactConstant.FAILED);
                    objArr[0] = sb.toString();
                    objArr[1] = absolutePath;
                    objArr[2] = "compute md5:" + p.c(absolutePath);
                    t.c(objArr);
                    if (!z2) {
                        if (d != null && TextUtils.isEmpty(d.getMd5())) {
                            DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_MISS_MD5, "md5\u6821\u9a8c\u5931\u8d25\uff0c\u670d\u52a1\u7aef\u672a\u4e0b\u53d1MD5\u5b57\u6bb5", d.bizField, d.systemCode, new RuntimeException());
                        }
                        DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_STORAGE, "downloadTemplateWithRetry() onEnd verify file failed md5 = " + p.c(absolutePath), d.bizField, f2, (int) R2.attr.layout_constraintHeight, (Exception) null);
                        m.H(absolutePath);
                    }
                    return z2;
                }
            }
            z = false;
            t.g("DownloadCallbackBase", "verifyFile: isZipFile: " + z + " ,filePath: " + absolutePath);
            z2 = (!z ? (Boolean) com.jd.dynamic.b.e.a.b.u(absolutePath, d.getMd5()).first : (Boolean) com.jd.dynamic.b.e.a.a.h(absolutePath, d).first).booleanValue();
            Object[] objArr2 = new Object[3];
            StringBuilder sb2 = new StringBuilder();
            sb2.append("downloadTemplateWithRetry()  onEnd() verify file ");
            sb2.append(!z2 ? "success" : JDReactConstant.FAILED);
            objArr2[0] = sb2.toString();
            objArr2[1] = absolutePath;
            objArr2[2] = "compute md5:" + p.c(absolutePath);
            t.c(objArr2);
            if (!z2) {
            }
            return z2;
        }

        protected abstract void a();

        protected abstract void b(INetWorkRequest.ErrorResponse errorResponse);

        protected abstract void c(File file);

        protected abstract Template d();

        protected abstract String f();

        protected abstract long g();

        @Override // com.jd.dynamic.base.interfaces.INetWorkRequest.DownloadCallBack
        public void onError(INetWorkRequest.ErrorResponse errorResponse) {
            b(errorResponse);
            t.g("DownloadCallbackBase", "duration: " + g() + " ,Template: " + d());
            StringBuilder sb = new StringBuilder();
            sb.append("downloadTemplateWithRetry() onError error = ");
            sb.append(errorResponse == null ? "" : errorResponse.toString());
            DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_STORAGE, sb.toString(), d() == null ? null : d().bizField, f(), m.U(errorResponse != null ? errorResponse.errorMsg : null), (Exception) null);
            t.e("DownloadCallbackBase", "downloadTemplateWithRetry()  onError()", errorResponse);
        }

        @Override // com.jd.dynamic.base.interfaces.INetWorkRequest.DownloadCallBack
        public void onPause() {
        }

        @Override // com.jd.dynamic.base.interfaces.INetWorkRequest.DownloadCallBack
        public void onProgress(int i2, int i3) {
        }

        @Override // com.jd.dynamic.base.interfaces.INetWorkRequest.DownloadCallBack
        public void onStart() {
            a();
        }

        @Override // com.jd.dynamic.base.interfaces.INetWorkRequest.DownloadCallBack
        public void onSuccess(File file) {
            synchronized (this.a) {
                if (com.jd.dynamic.b.a.b.o().I()) {
                    file = m.N(file);
                }
                if (e(file)) {
                    c(file);
                } else {
                    b(new INetWorkRequest.ErrorResponse(307, "DownloadCallbackBase downloadTemplateWithRetry(),verify dynamic binary file md5 failed!"));
                }
            }
        }
    }

    public a(TemplateRequest templateRequest, int i2) {
        this.d = templateRequest;
        this.f1737g = i2;
        this.f1735e = i2 > 0;
    }

    private void i() {
        if (this.d == null) {
            t.g("DownloadCallback", "request is null!!");
            return;
        }
        t.g("DownloadCallback", "handleRetry: execute handleRetry!!");
        if (DynamicSdk.getEngine().getRequest() != null) {
            INetWorkRequest request = DynamicSdk.getEngine().getRequest();
            TemplateRequest templateRequest = this.d;
            String str = templateRequest.template.fullFileUrl;
            String l2 = com.jd.dynamic.b.e.a.a.l(templateRequest.systemCode, templateRequest.getBusinessCode());
            Template template = this.d.template;
            request.downloadFile(str, l2, p.b(template.fullFileUrl, template.fileObjectKey), this);
        }
    }

    private void j() {
        this.f1735e = false;
        this.f1736f.set(0);
    }

    @Override // com.jd.dynamic.b.f.a.b
    protected void a() {
    }

    @Override // com.jd.dynamic.b.f.a.b
    protected void b(INetWorkRequest.ErrorResponse errorResponse) {
        this.f1734c = System.nanoTime();
        synchronized (this.f1738h) {
            if (this.f1735e && this.f1736f.getAndIncrement() != this.f1737g) {
                if (this.f1735e) {
                    t.g("DownloadCallback", this.f1736f.get() + "time retry ,duration==> " + (this.f1734c - this.b));
                    i();
                }
                return;
            }
            t.g("DownloadCallback", "handleError:no retry time or not use retry!");
            if (errorResponse != null && errorResponse.errorCode == -105239) {
                t.c("this is cancel : " + errorResponse.errorMsg);
                DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_STORAGE, "cancel", "", this.d.systemCode, 1012, (Exception) null);
            } else if (this.f1736f.get() > 0) {
                DynamicMtaUtil.uploadDownloadTempMta(f(), d(), g(), false, true);
            } else {
                DynamicMtaUtil.uploadDownloadTempMta(f(), d(), g(), false);
            }
            j();
            InterfaceC0069a interfaceC0069a = this.f1739i;
            if (interfaceC0069a != null) {
                if (errorResponse == null || errorResponse.errorCode != 307) {
                    interfaceC0069a.onFail(this.d, "getTemplateFromNetWidthRetry(),request dynamic binary file failed!", 304);
                } else {
                    interfaceC0069a.onFail(this.d, errorResponse.errorMsg, 307);
                }
            }
        }
    }

    @Override // com.jd.dynamic.b.f.a.b
    protected void c(File file) {
        this.f1734c = System.nanoTime();
        if (this.f1736f.get() > 0) {
            DynamicMtaUtil.uploadDownloadTempMta(f(), d(), g(), true, true);
        } else {
            DynamicMtaUtil.uploadDownloadTempMta(f(), d(), g(), true);
        }
        j();
        if (this.d != null) {
            String absolutePath = file.getAbsolutePath();
            String bizField = this.d.getBizField();
            TemplateRequest templateRequest = this.d;
            ViewNode parseBinaryToViewNode = DynamicXParser.parseBinaryToViewNode(absolutePath, bizField, templateRequest.systemCode, templateRequest.mtaId);
            DynamicMtaUtil.appendDownloadTypeMtaStat(this.d.mtaId, 3);
            TemplateRequest templateRequest2 = this.d;
            com.jd.dynamic.b.e.a.a.f(templateRequest2.systemCode, templateRequest2.template);
            if (!((parseBinaryToViewNode == null || TextUtils.isEmpty(parseBinaryToViewNode.getViewName()) || !parseBinaryToViewNode.parseSuccess) ? false : true)) {
                DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_PARSE, "getTemplateFromNetWidthRetry parse fail", this.d.getBizField(), this.d.systemCode, (int) R2.attr.list_item_divider, (Exception) null);
                InterfaceC0069a interfaceC0069a = this.f1739i;
                if (interfaceC0069a != null) {
                    interfaceC0069a.onFail(this.d, "getTemplateFromNetWidthRetry(),faild to parse dynamic binary", 305);
                    return;
                }
                return;
            }
            TemplateRequest templateRequest3 = this.d;
            DynamicFetcher.Listener listener = templateRequest3.listener;
            if (listener != null) {
                listener.onEnd(parseBinaryToViewNode, templateRequest3.mtaId);
                InterfaceC0069a interfaceC0069a2 = this.f1739i;
                if (interfaceC0069a2 != null) {
                    interfaceC0069a2.onSuccess();
                }
            }
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
        return this.f1734c - this.b;
    }

    public void h(InterfaceC0069a interfaceC0069a) {
        this.f1739i = interfaceC0069a;
    }
}
