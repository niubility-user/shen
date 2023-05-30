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
        /* JADX WARN: Code restructure failed: missing block: B:77:0x00e8, code lost:
            r13 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:78:0x00e9, code lost:
            r10 = r13;
         */
        /* JADX WARN: Removed duplicated region for block: B:60:0x0053 A[Catch: Exception -> 0x0029, TryCatch #0 {Exception -> 0x0029, blocks: (B:52:0x0021, B:58:0x002f, B:60:0x0053, B:61:0x005f, B:62:0x0064), top: B:84:0x0021 }] */
        /* JADX WARN: Removed duplicated region for block: B:62:0x0064 A[Catch: Exception -> 0x0029, TRY_LEAVE, TryCatch #0 {Exception -> 0x0029, blocks: (B:52:0x0021, B:58:0x002f, B:60:0x0053, B:61:0x005f, B:62:0x0064), top: B:84:0x0021 }] */
        /* JADX WARN: Removed duplicated region for block: B:67:0x007c  */
        /* JADX WARN: Removed duplicated region for block: B:68:0x007f  */
        /* JADX WARN: Removed duplicated region for block: B:71:0x00a8  */
        /* JADX WARN: Removed duplicated region for block: B:80:0x00ec  */
        /* JADX WARN: Removed duplicated region for block: B:81:0x00ee  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private boolean e(java.io.File r13) {
            /*
                Method dump skipped, instructions count: 261
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.b.f.a.b.e(java.io.File):boolean");
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
