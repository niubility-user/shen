package com.jd.dynamic.base;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Pair;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.gson.Gson;
import com.jd.dynamic.DYConstants;
import com.jd.dynamic.b.f.a;
import com.jd.dynamic.base.interfaces.IExceptionHandler;
import com.jd.dynamic.base.interfaces.INetWorkRequest;
import com.jd.dynamic.entity.MtaTimePair;
import com.jd.dynamic.entity.QueryTemplatesEntity;
import com.jd.dynamic.entity.ResultEntity;
import com.jd.dynamic.entity.Template;
import com.jd.dynamic.entity.TemplateRequest;
import com.jd.dynamic.entity.ViewNode;
import com.jd.dynamic.lib.dynamic.parser.DynamicXParser;
import com.jd.dynamic.lib.error.DowngradeException;
import com.jd.dynamic.lib.error.DynamicException;
import com.jingdong.sdk.platform.business.personal.R2;
import java.io.File;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONObject;

@Keep
/* loaded from: classes13.dex */
public class DynamicFetcher {
    private static final String CODE = "code";
    private static final String TAG = "DynamicFetcher";
    private static ThreadPoolExecutor sExecutor;
    private static final ThreadFactory sThreadFactory;
    private static final ConcurrentHashMap<String, Integer> sDownloadNumMap = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, Boolean> sFirstRequestMap = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, Boolean> sBunchRequestStatus = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, ArrayList<TemplateRequest>> pendingTempReqList = new ConcurrentHashMap<>();
    private static final Object objLock1 = new Object();

    @Keep
    /* loaded from: classes13.dex */
    public interface GlobalConfigListener {
        void onEnd(boolean z);

        void onError(Exception exc);

        void onStart();
    }

    @Keep
    /* loaded from: classes13.dex */
    public interface Listener {
        void onEnd(ViewNode viewNode, String str);

        void onError(Exception exc);

        void onStart();
    }

    @Keep
    /* loaded from: classes13.dex */
    public interface ListenerWithLocalError extends Listener {
        void onLocalTemplateError(Exception exc);
    }

    static {
        ThreadFactory threadFactory = new ThreadFactory() { // from class: com.jd.dynamic.base.DynamicFetcher.1

            /* renamed from: g  reason: collision with root package name */
            private final AtomicInteger f1828g = new AtomicInteger(1);

            @Override // java.util.concurrent.ThreadFactory
            public Thread newThread(Runnable runnable) {
                return new Thread(runnable, "DynamicFetcher#" + this.f1828g.getAndIncrement());
            }
        };
        sThreadFactory = threadFactory;
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 1000L, TimeUnit.MILLISECONDS, new SynchronousQueue(), threadFactory);
        sExecutor = threadPoolExecutor;
        threadPoolExecutor.allowCoreThreadTimeOut(true);
    }

    private DynamicFetcher() {
    }

    public static void cancelTmepRequest(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        try {
            ConcurrentHashMap<String, ArrayList<TemplateRequest>> concurrentHashMap = pendingTempReqList;
            if (com.jd.dynamic.lib.utils.m.J(concurrentHashMap)) {
                ArrayList<TemplateRequest> arrayList = concurrentHashMap.get(str);
                ArrayList arrayList2 = new ArrayList();
                if (com.jd.dynamic.lib.utils.m.I(arrayList)) {
                    Iterator<TemplateRequest> it = arrayList.iterator();
                    while (it.hasNext()) {
                        TemplateRequest next = it.next();
                        if (next != null && str2.equals(next.getBizField())) {
                            arrayList2.add(next);
                            Future future = next.task;
                            if (future != null) {
                                future.cancel(true);
                                com.jd.dynamic.lib.utils.t.e(TAG, "successfully cancel query template task[mtaid:" + next.mtaId + ",sysmtedcode:" + str + ",bizfiled:" + str2);
                            }
                            DynamicMtaUtil.removeMta(next.mtaId);
                        }
                    }
                    if (arrayList2.size() > 0) {
                        arrayList.removeAll(arrayList2);
                    }
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void checkNotifyConfigListener(String str, GlobalConfigListener globalConfigListener) {
        ConcurrentHashMap<String, Integer> concurrentHashMap = sDownloadNumMap;
        Integer num = concurrentHashMap.get(str);
        if (num != null) {
            if (num.intValue() != 1) {
                concurrentHashMap.put(str, Integer.valueOf(num.intValue() - 1));
                return;
            }
            concurrentHashMap.remove(str);
            if (globalConfigListener != null) {
                globalConfigListener.onEnd(false);
            }
        }
    }

    public static void downloadFileUseTempName(INetWorkRequest iNetWorkRequest, final Template template, final String str, final GlobalConfigListener globalConfigListener) {
        com.jd.dynamic.lib.utils.t.e("DY_LOG Use RENAME file request is : " + iNetWorkRequest + " template : " + template + " syscode: " + str + " listener: " + globalConfigListener);
        if (iNetWorkRequest == null) {
            return;
        }
        iNetWorkRequest.downloadFile(template.fullFileUrl, com.jd.dynamic.b.e.a.a.l(str, template.businessCode), com.jd.dynamic.lib.utils.m.k(template), new INetWorkRequest.DownloadCallBack() { // from class: com.jd.dynamic.base.DynamicFetcher.8
            final long a = System.nanoTime();
            long b = 0;

            /* renamed from: c  reason: collision with root package name */
            long f1843c = 0;
            AtomicInteger d = new AtomicInteger(0);

            @Override // com.jd.dynamic.base.interfaces.INetWorkRequest.DownloadCallBack
            public void onError(INetWorkRequest.ErrorResponse errorResponse) {
                this.b = System.nanoTime();
                StringBuilder sb = new StringBuilder();
                sb.append("downloadTemplate() onError error = ");
                sb.append(errorResponse == null ? "" : errorResponse.toString());
                String sb2 = sb.toString();
                Template template2 = Template.this;
                DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_STORAGE, sb2, template2 == null ? null : template2.bizField, str, com.jd.dynamic.lib.utils.m.U(errorResponse != null ? errorResponse.errorMsg : null), (Exception) null);
                if (errorResponse == null || errorResponse.errorCode != -105239) {
                    String str2 = str;
                    Template template3 = Template.this;
                    long j2 = this.b;
                    long j3 = this.a;
                    DynamicMtaUtil.uploadDownloadTempMtaWithStart(str2, template3, j2 - j3, this.f1843c - j3, false);
                } else {
                    com.jd.dynamic.lib.utils.t.c("this is cancel : " + errorResponse.errorMsg);
                    DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_STORAGE, "cancel", "", str, 1012, (Exception) null);
                }
                com.jd.dynamic.lib.utils.t.e("downloadTemplate()  onError()", errorResponse);
                DynamicFetcher.checkNotifyConfigListener(str, globalConfigListener);
            }

            @Override // com.jd.dynamic.base.interfaces.INetWorkRequest.DownloadCallBack
            public void onPause() {
            }

            @Override // com.jd.dynamic.base.interfaces.INetWorkRequest.DownloadCallBack
            public void onProgress(int i2, int i3) {
            }

            @Override // com.jd.dynamic.base.interfaces.INetWorkRequest.DownloadCallBack
            public void onStart() {
                this.d.getAndIncrement();
                this.f1843c = System.nanoTime();
            }

            @Override // com.jd.dynamic.base.interfaces.INetWorkRequest.DownloadCallBack
            public void onSuccess(File file) {
                this.b = System.nanoTime();
                int i2 = this.d.get();
                if (i2 > 1) {
                    DynamicSdk.handException("callBack", "downloadTemplate onStart \u56de\u8c03\u4e86" + i2 + "\u6b21", Template.this.bizField, str, null);
                }
                String str2 = str;
                Template template2 = Template.this;
                long j2 = this.b;
                DynamicMtaUtil.uploadDownloadTempMtaWithStart(str2, template2, j2 - this.a, this.f1843c - j2, true);
                try {
                    String absolutePath = com.jd.dynamic.lib.utils.m.N(file).getAbsolutePath();
                    Pair<Boolean, String> h2 = com.jd.dynamic.b.e.a.a.h(absolutePath, Template.this);
                    if (!((Boolean) h2.first).booleanValue()) {
                        DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_STORAGE, "getTemplateFromDisk verified fail cache file reason: " + ((String) h2.second) + " url: " + Template.this.fullFileUrl, Template.this.bizField, str, (int) R2.attr.layout_constraintHeight, (Exception) null);
                        com.jd.dynamic.lib.utils.m.H(absolutePath);
                    }
                } catch (Exception e2) {
                    Template template3 = Template.this;
                    DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_STORAGE, "downloadTemplate() catch error", template3 != null ? template3.bizField : null, str, (int) R2.attr.layout_constraintGuide_percent, e2);
                    com.jd.dynamic.lib.utils.t.e("downloadTemplate() onEnd() error!!");
                }
                DynamicFetcher.checkNotifyConfigListener(str, globalConfigListener);
            }
        });
    }

    private static void downloadFileUseTempNameWithRetry(INetWorkRequest iNetWorkRequest, Template template, String str, GlobalConfigListener globalConfigListener) {
        com.jd.dynamic.lib.utils.t.e("DY_LOG Use RENAME file request is : " + iNetWorkRequest + " template : " + template + " syscode: " + str + " listener: " + globalConfigListener);
        if (iNetWorkRequest == null) {
            return;
        }
        iNetWorkRequest.downloadFile(template.fullFileUrl, com.jd.dynamic.b.e.a.a.l(str, template.businessCode), com.jd.dynamic.lib.utils.m.k(template), new com.jd.dynamic.b.f.b(template, str, com.jd.dynamic.b.a.b.o().J(), globalConfigListener));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void downloadTemplate(final Template template, final String str, final GlobalConfigListener globalConfigListener) {
        File file = new File(com.jd.dynamic.b.e.a.a.n(str, template.businessCode), com.jd.dynamic.lib.utils.p.b(template.fullFileUrl, template.fileObjectKey));
        if (((Boolean) com.jd.dynamic.b.e.a.a.h(file.getAbsolutePath(), template).first).booleanValue()) {
            if (globalConfigListener != null) {
                com.jd.dynamic.lib.utils.t.c("downloadTemplate()  onEnd() hit local cache and verify file success", file.getAbsolutePath());
                checkNotifyConfigListener(str, globalConfigListener);
                return;
            }
            return;
        }
        if (file.exists()) {
            file.delete();
        }
        if (com.jd.dynamic.b.a.b.o().I()) {
            downloadFileUseTempName(DynamicSdk.getEngine().getRequest(), template, str, globalConfigListener);
        } else if (DynamicSdk.getEngine().getRequest() != null) {
            DynamicSdk.getEngine().getRequest().downloadFile(template.fullFileUrl, com.jd.dynamic.b.e.a.a.l(str, template.businessCode), com.jd.dynamic.lib.utils.p.b(template.fullFileUrl, template.fileObjectKey), new INetWorkRequest.DownloadCallBack() { // from class: com.jd.dynamic.base.DynamicFetcher.9
                final long a = System.nanoTime();
                long b = 0;

                /* renamed from: c  reason: collision with root package name */
                long f1847c = 0;
                AtomicInteger d = new AtomicInteger(0);

                @Override // com.jd.dynamic.base.interfaces.INetWorkRequest.DownloadCallBack
                public void onError(INetWorkRequest.ErrorResponse errorResponse) {
                    this.b = System.nanoTime();
                    StringBuilder sb = new StringBuilder();
                    sb.append("downloadTemplate() onError error = ");
                    sb.append(errorResponse == null ? "" : errorResponse.toString());
                    String sb2 = sb.toString();
                    Template template2 = Template.this;
                    DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_STORAGE, sb2, template2 == null ? null : template2.bizField, str, com.jd.dynamic.lib.utils.m.U(errorResponse != null ? errorResponse.errorMsg : null), (Exception) null);
                    if (errorResponse == null || errorResponse.errorCode != -105239) {
                        String str2 = str;
                        Template template3 = Template.this;
                        long j2 = this.b;
                        long j3 = this.a;
                        DynamicMtaUtil.uploadDownloadTempMtaWithStart(str2, template3, j2 - j3, this.f1847c - j3, false);
                    } else {
                        com.jd.dynamic.lib.utils.t.c("this is cancel : " + errorResponse.errorMsg);
                        DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_STORAGE, "cancel", "", str, 1012, (Exception) null);
                    }
                    com.jd.dynamic.lib.utils.t.e("downloadTemplate()  onError()", errorResponse);
                    DynamicFetcher.checkNotifyConfigListener(str, globalConfigListener);
                }

                @Override // com.jd.dynamic.base.interfaces.INetWorkRequest.DownloadCallBack
                public void onPause() {
                }

                @Override // com.jd.dynamic.base.interfaces.INetWorkRequest.DownloadCallBack
                public void onProgress(int i2, int i3) {
                }

                @Override // com.jd.dynamic.base.interfaces.INetWorkRequest.DownloadCallBack
                public void onStart() {
                    this.f1847c = System.nanoTime();
                    this.d.getAndIncrement();
                }

                @Override // com.jd.dynamic.base.interfaces.INetWorkRequest.DownloadCallBack
                public void onSuccess(File file2) {
                    this.b = System.nanoTime();
                    int i2 = this.d.get();
                    if (i2 > 1) {
                        DynamicSdk.handException("callBack", "downloadTemplate onStart \u56de\u8c03\u4e86" + i2 + "\u6b21", Template.this.bizField, str, null);
                    }
                    String str2 = str;
                    Template template2 = Template.this;
                    long j2 = this.b;
                    long j3 = this.a;
                    DynamicMtaUtil.uploadDownloadTempMtaWithStart(str2, template2, j2 - j3, this.f1847c - j3, true);
                    try {
                        String absolutePath = file2.getAbsolutePath();
                        Pair<Boolean, String> h2 = com.jd.dynamic.b.e.a.a.h(absolutePath, Template.this);
                        if (!((Boolean) h2.first).booleanValue()) {
                            DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_STORAGE, "getTemplateFromDisk verified fail cache file reason: " + ((String) h2.second) + " url: " + Template.this.fullFileUrl, Template.this.bizField, str, (int) R2.attr.layout_constraintHeight, (Exception) null);
                            com.jd.dynamic.lib.utils.m.H(absolutePath);
                        }
                    } catch (Exception e2) {
                        Template template3 = Template.this;
                        DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_STORAGE, "downloadTemplate() catch error", template3 != null ? template3.bizField : null, str, (int) R2.attr.layout_constraintGuide_percent, e2);
                        com.jd.dynamic.lib.utils.t.e("downloadTemplate() onEnd() error!!");
                    }
                    DynamicFetcher.checkNotifyConfigListener(str, globalConfigListener);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void downloadTemplateWithRetry(Template template, String str, GlobalConfigListener globalConfigListener) {
        File file = new File(com.jd.dynamic.b.e.a.a.n(str, template.businessCode), com.jd.dynamic.lib.utils.p.b(template.fullFileUrl, template.fileObjectKey));
        if (((Boolean) com.jd.dynamic.b.e.a.a.h(file.getAbsolutePath(), template).first).booleanValue()) {
            if (globalConfigListener != null) {
                com.jd.dynamic.lib.utils.t.c("downloadTemplateWithRetry()  onEnd() hit local cache and verify file success", file.getAbsolutePath());
                checkNotifyConfigListener(str, globalConfigListener);
                return;
            }
            return;
        }
        if (file.exists()) {
            file.delete();
        }
        if (com.jd.dynamic.b.a.b.o().I()) {
            downloadFileUseTempNameWithRetry(DynamicSdk.getEngine().getRequest(), template, str, globalConfigListener);
        } else if (DynamicSdk.getEngine().getRequest() != null) {
            DynamicSdk.getEngine().getRequest().downloadFile(template.fullFileUrl, com.jd.dynamic.b.e.a.a.l(str, template.businessCode), com.jd.dynamic.lib.utils.p.b(template.fullFileUrl, template.fileObjectKey), new com.jd.dynamic.b.f.b(template, str, com.jd.dynamic.b.a.b.o().J(), globalConfigListener));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @NonNull
    public static GlobalConfigListener getGlobalConfigListener(final String str) {
        return new GlobalConfigListener() { // from class: com.jd.dynamic.base.DynamicFetcher.3
            @Override // com.jd.dynamic.base.DynamicFetcher.GlobalConfigListener
            public void onEnd(boolean z) {
                try {
                    com.jd.dynamic.b.e.a.a.b();
                    synchronized (DynamicFetcher.objLock1) {
                        ArrayList arrayList = (ArrayList) DynamicFetcher.pendingTempReqList.get(str);
                        Iterator it = arrayList.iterator();
                        while (it.hasNext()) {
                            DynamicFetcher.internalRequestCommon((TemplateRequest) it.next());
                        }
                        arrayList.clear();
                        DynamicFetcher.pendingTempReqList.remove(str);
                        DynamicFetcher.sBunchRequestStatus.put(str, Boolean.FALSE);
                    }
                } catch (Exception e2) {
                    DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_DOWNLOAD, "GlobalConfigListener onEnd catched Exception", null, str, e2);
                    com.jd.dynamic.lib.utils.t.e(DynamicFetcher.TAG, "fetcher inner globalConfigListener  onEnd() error!!!", com.jd.dynamic.lib.utils.t.d(e2));
                }
            }

            @Override // com.jd.dynamic.base.DynamicFetcher.GlobalConfigListener
            public void onError(Exception exc) {
                try {
                    synchronized (DynamicFetcher.objLock1) {
                        ConcurrentHashMap concurrentHashMap = DynamicFetcher.sFirstRequestMap;
                        String str2 = str;
                        Boolean bool = Boolean.FALSE;
                        concurrentHashMap.put(str2, bool);
                        DynamicFetcher.sBunchRequestStatus.put(str, bool);
                        Iterator it = ((ArrayList) DynamicFetcher.pendingTempReqList.get(str)).iterator();
                        while (it.hasNext()) {
                            DynamicFetcher.tryLoadAssetsTemp((TemplateRequest) it.next(), "getGlobalConfigListener onError,load assets failed again!!!", 300, 1002);
                        }
                        DynamicFetcher.pendingTempReqList.remove(str);
                    }
                } catch (Exception unused) {
                    DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_DOWNLOAD, "GlobalConfigListener onError catched Exception", null, str, exc);
                    com.jd.dynamic.lib.utils.t.e(DynamicFetcher.TAG, "fetcher inner globalConfigListener  onError() error!!!", com.jd.dynamic.lib.utils.t.d(exc));
                }
            }

            @Override // com.jd.dynamic.base.DynamicFetcher.GlobalConfigListener
            public void onStart() {
            }
        };
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x00b7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static boolean getTemplateFromAssets(TemplateRequest templateRequest, int i2) {
        Exception exc;
        boolean z;
        boolean z2;
        ViewNode templateFromLocal;
        Listener listener;
        try {
        } catch (Exception e2) {
            exc = e2;
            z = false;
        }
        if (com.jd.dynamic.b.i.a.e(templateRequest.systemCode, templateRequest.getBizField())) {
            ResultEntity a = com.jd.dynamic.b.i.a.a(templateRequest.systemCode, templateRequest.getBizField(), i2, templateRequest.mtaId, i2 != 1007);
            if (com.jd.dynamic.lib.utils.m.F(a)) {
                DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_LOCAL_BACKUP, i2 + "-\u4f7f\u7528App\u5185\u7f6e\u6a21\u677f\u8fdb\u884c\u52a0\u8f7d", templateRequest.getBizField(), templateRequest.systemCode, i2, new RuntimeException());
                try {
                    com.jd.dynamic.lib.utils.t.g("getTemplateFromAssets()  use App assets template file!!!", templateRequest.getBizField());
                    DynamicMtaUtil.appendDownloadTypeMtaStat(templateRequest.mtaId, 4);
                    Listener listener2 = templateRequest.listener;
                    if (listener2 != null) {
                        listener2.onEnd(a.viewNode, templateRequest.mtaId);
                    }
                    z2 = true;
                } catch (Exception e3) {
                    exc = e3;
                    z = true;
                    DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_LOCAL_BACKUP_ERROR, i2 + "-App\u5185\u7f6e\u6a21\u677f\u52a0\u8f7d\u5f02\u5e38", templateRequest.getBizField(), templateRequest.systemCode, i2, exc);
                    z2 = z;
                    if (!z2) {
                    }
                    return z2;
                }
                if (!z2) {
                    if (TextUtils.isEmpty(templateRequest.localTemp)) {
                        InputStream inputStream = templateRequest.localTempStream;
                        if (inputStream != null && (templateFromLocal = getTemplateFromLocal(inputStream, templateRequest.systemCode, templateRequest.getBizField(), templateRequest.mtaId)) != null && !TextUtils.isEmpty(templateFromLocal.getViewName())) {
                            com.jd.dynamic.lib.utils.t.g("getTemplateFromAssets()  use local default template stream!!!", templateRequest.localTempStream);
                            DynamicMtaUtil.appendDownloadTypeMtaStat(templateRequest.mtaId, 4);
                            listener = templateRequest.listener;
                            if (listener == null) {
                                return true;
                            }
                            listener.onEnd(templateFromLocal, templateRequest.mtaId);
                            return true;
                        }
                    } else {
                        templateFromLocal = DynamicXParser.parseBinaryToViewNode(templateRequest.localTemp, true, templateRequest.getBizField(), templateRequest.systemCode, templateRequest.mtaId);
                        if (templateFromLocal != null && !TextUtils.isEmpty(templateFromLocal.getViewName())) {
                            com.jd.dynamic.lib.utils.t.g("getTemplateFromAssets()  use local default template!!!", templateRequest.localTemp);
                            DynamicMtaUtil.appendDownloadTypeMtaStat(templateRequest.mtaId, 4);
                            listener = templateRequest.listener;
                            if (listener == null) {
                                return true;
                            }
                            listener.onEnd(templateFromLocal, templateRequest.mtaId);
                            return true;
                        }
                    }
                }
                return z2;
            }
            DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_LOCAL_BACKUP_ERROR, i2 + "-App\u5185\u7f6e\u6a21\u677f\u52a0\u8f7d\u5931\u8d25", templateRequest.getBizField(), templateRequest.systemCode, i2, new RuntimeException());
        }
        z2 = false;
        if (!z2) {
        }
        return z2;
    }

    @Nullable
    public static ViewNode getTemplateFromCache(String str, String str2, InputStream inputStream) {
        ViewNode viewNode = null;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        String startMtaStat = DynamicMtaUtil.startMtaStat(DynamicSdk.getEngine().getAppType(), str, "", str2, false);
        TemplateRequest templateRequest = new TemplateRequest(startMtaStat, str, str2, "", null, inputStream, null);
        if (!com.jd.dynamic.b.e.a.a.p(templateRequest.systemCode)) {
            com.jd.dynamic.b.e.a.a.c(templateRequest.systemCode);
        }
        templateRequest.template = !TextUtils.isEmpty(templateRequest.getBizField()) ? com.jd.dynamic.b.e.a.a.a(templateRequest.systemCode, templateRequest.getBizField()) : null;
        ViewNode q = com.jd.dynamic.b.e.a.a.q(com.jd.dynamic.b.e.a.a.o(str, str2));
        if (q != null && !TextUtils.isEmpty(q.getViewName()) && q.parseSuccess) {
            DynamicMtaUtil.startLoadTemplate(str, str2, startMtaStat);
            return q;
        }
        Template template = templateRequest.template;
        File file = new File(com.jd.dynamic.b.e.a.a.k(templateRequest.systemCode) + File.separator + templateRequest.getBusinessCode(), com.jd.dynamic.lib.utils.p.b(template.fullFileUrl, template.fileObjectKey));
        if (file.exists() && file.isFile()) {
            if (((Boolean) com.jd.dynamic.b.e.a.a.h(file.getAbsolutePath(), templateRequest.template).first).booleanValue()) {
                viewNode = DynamicXParser.parseBinaryToViewNode(file.getAbsolutePath(), templateRequest.getBizField(), templateRequest.systemCode, startMtaStat);
            } else {
                file.delete();
            }
        }
        return (viewNode == null || TextUtils.isEmpty(viewNode.getViewName()) || !viewNode.parseSuccess) ? getTemplateFromLocal(inputStream, str, str2) : viewNode;
    }

    private static boolean getTemplateFromDisk(TemplateRequest templateRequest) {
        ViewNode viewNode;
        Template template = templateRequest.template;
        File file = new File(com.jd.dynamic.b.e.a.a.k(templateRequest.systemCode) + File.separator + templateRequest.getBusinessCode(), com.jd.dynamic.lib.utils.p.b(template.fullFileUrl, template.fileObjectKey));
        if (file.exists() && file.isFile() && file.length() > 0) {
            Pair<Boolean, String> h2 = com.jd.dynamic.b.e.a.a.h(file.getAbsolutePath(), templateRequest.template);
            if (((Boolean) h2.first).booleanValue()) {
                viewNode = DynamicXParser.parseBinaryToViewNode(file.getAbsolutePath(), templateRequest.getBizField(), templateRequest.systemCode, templateRequest.mtaId);
                if (viewNode == null && !TextUtils.isEmpty(viewNode.getViewName()) && viewNode.parseSuccess) {
                    com.jd.dynamic.lib.utils.t.g("getTemplateFromDisk()  hit catch return!!!", file.getAbsolutePath());
                    DynamicMtaUtil.appendDownloadTypeMtaStat(templateRequest.mtaId, 2);
                    Listener listener = templateRequest.listener;
                    if (listener != null) {
                        listener.onEnd(viewNode, templateRequest.mtaId);
                        return true;
                    }
                    return true;
                }
                return false;
            }
            DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_PARSE, "getTemplateFromDisk verified fail cache file len = " + file.length() + " reason: " + ((String) h2.second) + " url: " + templateRequest.template.fullFileUrl, templateRequest.getBizField(), templateRequest.systemCode, (int) R2.attr.list_top_line_selector, (Exception) null);
            StringBuilder sb = new StringBuilder();
            sb.append("compute md5:");
            sb.append(com.jd.dynamic.lib.utils.p.c(file.getAbsolutePath()));
            com.jd.dynamic.lib.utils.t.g("getTemplateFromDisk()  verify file failed!!!", file.getAbsolutePath(), sb.toString());
            file.delete();
        }
        viewNode = null;
        if (viewNode == null) {
        }
        return false;
    }

    @Nullable
    public static ViewNode getTemplateFromLocal(InputStream inputStream, String str, String str2) {
        return getTemplateFromLocal(inputStream, str, str2, null);
    }

    @Nullable
    private static ViewNode getTemplateFromLocal(InputStream inputStream, String str, String str2, String str3) {
        ViewNode parseBinaryToViewNode;
        if (inputStream == null || (parseBinaryToViewNode = DynamicXParser.parseBinaryToViewNode(inputStream, str2, str, (String) null)) == null || TextUtils.isEmpty(parseBinaryToViewNode.getViewName())) {
            return null;
        }
        return parseBinaryToViewNode;
    }

    private static boolean getTemplateFromMem(TemplateRequest templateRequest) {
        MtaTimePair mtaTimePair = new MtaTimePair();
        mtaTimePair.startRecord();
        ViewNode q = com.jd.dynamic.b.e.a.a.q(com.jd.dynamic.b.e.a.a.o(templateRequest.systemCode, templateRequest.getBizField()));
        mtaTimePair.endRecord();
        if (q == null || TextUtils.isEmpty(q.getViewName()) || !q.parseSuccess) {
            return false;
        }
        com.jd.dynamic.lib.utils.t.g("getTemplateFromMem()  hit catch return!!!", templateRequest.systemCode + File.separator + templateRequest.getBusinessCode());
        DynamicMtaUtil.appendGetTemplateEnd(templateRequest.mtaId);
        DynamicMtaUtil.appendCreateModelMtaStat(templateRequest.mtaId, mtaTimePair);
        DynamicMtaUtil.appendDownloadTypeMtaStat(templateRequest.mtaId, 1);
        if (templateRequest.listener != null) {
            DynamicMtaUtil.startLoadTemplate(templateRequest.systemCode, templateRequest.getBusinessCode(), templateRequest.mtaId);
            templateRequest.listener.onEnd(q, templateRequest.mtaId);
        }
        return true;
    }

    private static void getTemplateFromNet(final TemplateRequest templateRequest) {
        if (templateRequest == null) {
            return;
        }
        if (com.jd.dynamic.b.a.b.o().I()) {
            getTemplateFromNetUseTempName(templateRequest);
        } else if (DynamicSdk.getEngine().getRequest() != null) {
            INetWorkRequest request = DynamicSdk.getEngine().getRequest();
            String str = templateRequest.template.fullFileUrl;
            String l2 = com.jd.dynamic.b.e.a.a.l(templateRequest.systemCode, templateRequest.getBusinessCode());
            Template template = templateRequest.template;
            request.downloadFile(str, l2, com.jd.dynamic.lib.utils.p.b(template.fullFileUrl, template.fileObjectKey), new INetWorkRequest.DownloadCallBack() { // from class: com.jd.dynamic.base.DynamicFetcher.5
                final long a = System.nanoTime();
                long b = 0;

                /* renamed from: c  reason: collision with root package name */
                long f1836c = 0;

                @Override // com.jd.dynamic.base.interfaces.INetWorkRequest.DownloadCallBack
                public void onError(INetWorkRequest.ErrorResponse errorResponse) {
                    long nanoTime = System.nanoTime();
                    this.f1836c = nanoTime;
                    if (errorResponse == null || errorResponse.errorCode != -105239) {
                        TemplateRequest templateRequest2 = TemplateRequest.this;
                        String str2 = templateRequest2.systemCode;
                        Template template2 = templateRequest2.template;
                        long j2 = this.a;
                        DynamicMtaUtil.uploadDownloadTempMtaWithStart(str2, template2, nanoTime - j2, this.b - j2, false);
                    } else {
                        com.jd.dynamic.lib.utils.t.c("this is cancel : " + errorResponse.errorMsg);
                        DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_STORAGE, "cancel", "", TemplateRequest.this.systemCode, 1012, (Exception) null);
                    }
                    StringBuilder sb = new StringBuilder();
                    sb.append("getTemplateFromNet onError error =");
                    sb.append(errorResponse == null ? null : errorResponse.toString());
                    DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_STORAGE, sb.toString(), TemplateRequest.this.getBizField(), TemplateRequest.this.systemCode, com.jd.dynamic.lib.utils.m.U(errorResponse != null ? errorResponse.errorMsg : null), (Exception) null);
                    com.jd.dynamic.lib.utils.t.e(DynamicFetcher.TAG, "getTemplateFromNet(),request dynamic binary file failed!");
                    DynamicFetcher.tryLoadAssetsTemp(TemplateRequest.this, "getTemplateFromNet(),request dynamic binary file failed!", 304, 1008);
                }

                @Override // com.jd.dynamic.base.interfaces.INetWorkRequest.DownloadCallBack
                public void onPause() {
                }

                @Override // com.jd.dynamic.base.interfaces.INetWorkRequest.DownloadCallBack
                public void onProgress(int i2, int i3) {
                }

                @Override // com.jd.dynamic.base.interfaces.INetWorkRequest.DownloadCallBack
                public void onStart() {
                    this.b = System.nanoTime();
                }

                /* JADX WARN: Removed duplicated region for block: B:30:0x010e  */
                /* JADX WARN: Removed duplicated region for block: B:33:0x0118  */
                @Override // com.jd.dynamic.base.interfaces.INetWorkRequest.DownloadCallBack
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                */
                public void onSuccess(File file) {
                    String absolutePath;
                    Pair<Boolean, String> h2;
                    String str2;
                    String bizField;
                    String str3;
                    long nanoTime = System.nanoTime();
                    this.f1836c = nanoTime;
                    TemplateRequest templateRequest2 = TemplateRequest.this;
                    String str4 = templateRequest2.systemCode;
                    Template template2 = templateRequest2.template;
                    long j2 = this.a;
                    DynamicMtaUtil.uploadDownloadTempMtaWithStart(str4, template2, nanoTime - j2, this.b - j2, true);
                    boolean z = false;
                    ViewNode viewNode = null;
                    try {
                        absolutePath = file.getAbsolutePath();
                        h2 = com.jd.dynamic.b.e.a.a.h(absolutePath, TemplateRequest.this.template);
                    } catch (Exception e2) {
                        DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_PARSE, "getTemplateFromNet catched exception", TemplateRequest.this.getBizField(), TemplateRequest.this.systemCode, (int) R2.attr.liteMode, e2);
                        com.jd.dynamic.lib.utils.t.e(DynamicFetcher.TAG, "getTemplateFromNet(),faild to parse dynamic binary");
                    }
                    if (!((Boolean) h2.first).booleanValue()) {
                        com.jd.dynamic.lib.utils.m.H(absolutePath);
                        DynamicFetcher.tryLoadAssetsTemp(TemplateRequest.this, "getTemplateFromNet(),verify dynamic binary file md5 failed!", 307, 1006);
                        DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_STORAGE, "getTemplateFromDisk verified fail cache file reason: " + ((String) h2.second) + " url: " + TemplateRequest.this.template.fullFileUrl, TemplateRequest.this.getBizField(), TemplateRequest.this.systemCode, (int) R2.attr.layout_constraintHeight, (Exception) null);
                        return;
                    }
                    String absolutePath2 = file.getAbsolutePath();
                    String bizField2 = TemplateRequest.this.getBizField();
                    TemplateRequest templateRequest3 = TemplateRequest.this;
                    viewNode = DynamicXParser.parseBinaryToViewNode(absolutePath2, bizField2, templateRequest3.systemCode, templateRequest3.mtaId);
                    if (viewNode == null) {
                        str2 = "view node is null";
                        bizField = TemplateRequest.this.getBizField();
                        str3 = TemplateRequest.this.systemCode;
                    } else if (!TextUtils.isEmpty(viewNode.getViewName())) {
                        if (!viewNode.parseSuccess) {
                            str2 = "view node parse error ";
                            bizField = TemplateRequest.this.getBizField();
                            str3 = TemplateRequest.this.systemCode;
                        }
                        DynamicMtaUtil.appendDownloadTypeMtaStat(TemplateRequest.this.mtaId, 3);
                        TemplateRequest templateRequest4 = TemplateRequest.this;
                        com.jd.dynamic.b.e.a.a.f(templateRequest4.systemCode, templateRequest4.template);
                        z = viewNode == null && !TextUtils.isEmpty(viewNode.getViewName()) && viewNode.parseSuccess;
                        TemplateRequest templateRequest5 = TemplateRequest.this;
                        if (z) {
                            DynamicFetcher.tryLoadAssetsTemp(templateRequest5, "getTemplateFromNet(),faild to parse dynamic binary", 305, 1007);
                            return;
                        }
                        Listener listener = templateRequest5.listener;
                        if (listener != null) {
                            listener.onEnd(viewNode, templateRequest5.mtaId);
                            return;
                        }
                        return;
                    } else {
                        str2 = "view node name is empty ";
                        bizField = TemplateRequest.this.getBizField();
                        str3 = TemplateRequest.this.systemCode;
                    }
                    DynamicFetcher.reportParseFileError(str2, bizField, str3);
                    DynamicMtaUtil.appendDownloadTypeMtaStat(TemplateRequest.this.mtaId, 3);
                    TemplateRequest templateRequest42 = TemplateRequest.this;
                    com.jd.dynamic.b.e.a.a.f(templateRequest42.systemCode, templateRequest42.template);
                    z = viewNode == null && !TextUtils.isEmpty(viewNode.getViewName()) && viewNode.parseSuccess;
                    TemplateRequest templateRequest52 = TemplateRequest.this;
                    if (z) {
                    }
                }
            });
        }
    }

    private static void getTemplateFromNetUseTempName(final TemplateRequest templateRequest) {
        INetWorkRequest request = DynamicSdk.getEngine().getRequest();
        if (request == null) {
            return;
        }
        request.downloadFile(templateRequest.template.fullFileUrl, com.jd.dynamic.b.e.a.a.l(templateRequest.systemCode, templateRequest.getBusinessCode()), com.jd.dynamic.lib.utils.m.k(templateRequest.template), new INetWorkRequest.DownloadCallBack() { // from class: com.jd.dynamic.base.DynamicFetcher.4
            final long a = System.nanoTime();
            long b = 0;

            /* renamed from: c  reason: collision with root package name */
            long f1835c = 0;

            @Override // com.jd.dynamic.base.interfaces.INetWorkRequest.DownloadCallBack
            public void onError(INetWorkRequest.ErrorResponse errorResponse) {
                long nanoTime = System.nanoTime();
                this.f1835c = nanoTime;
                if (errorResponse == null || errorResponse.errorCode != -105239) {
                    TemplateRequest templateRequest2 = TemplateRequest.this;
                    String str = templateRequest2.systemCode;
                    Template template = templateRequest2.template;
                    long j2 = this.a;
                    DynamicMtaUtil.uploadDownloadTempMtaWithStart(str, template, nanoTime - j2, this.b - j2, false);
                } else {
                    com.jd.dynamic.lib.utils.t.c("this is cancel : " + errorResponse.errorMsg);
                    DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_STORAGE, "cancel", "", TemplateRequest.this.systemCode, 1012, (Exception) null);
                }
                StringBuilder sb = new StringBuilder();
                sb.append("getTemplateFromNet onError error =");
                sb.append(errorResponse == null ? null : errorResponse.toString());
                DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_STORAGE, sb.toString(), TemplateRequest.this.getBizField(), TemplateRequest.this.systemCode, com.jd.dynamic.lib.utils.m.U(errorResponse != null ? errorResponse.errorMsg : null), (Exception) null);
                com.jd.dynamic.lib.utils.t.e(DynamicFetcher.TAG, "getTemplateFromNet(),request dynamic binary file failed!");
                DynamicFetcher.tryLoadAssetsTemp(TemplateRequest.this, "getTemplateFromNet(),request dynamic binary file failed!", 304, 1008);
            }

            @Override // com.jd.dynamic.base.interfaces.INetWorkRequest.DownloadCallBack
            public void onPause() {
            }

            @Override // com.jd.dynamic.base.interfaces.INetWorkRequest.DownloadCallBack
            public void onProgress(int i2, int i3) {
            }

            @Override // com.jd.dynamic.base.interfaces.INetWorkRequest.DownloadCallBack
            public void onStart() {
                this.b = System.nanoTime();
            }

            /* JADX WARN: Removed duplicated region for block: B:30:0x010e  */
            /* JADX WARN: Removed duplicated region for block: B:33:0x0118  */
            @Override // com.jd.dynamic.base.interfaces.INetWorkRequest.DownloadCallBack
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public void onSuccess(File file) {
                String absolutePath;
                Pair<Boolean, String> h2;
                String str;
                String bizField;
                String str2;
                long nanoTime = System.nanoTime();
                this.f1835c = nanoTime;
                TemplateRequest templateRequest2 = TemplateRequest.this;
                String str3 = templateRequest2.systemCode;
                Template template = templateRequest2.template;
                long j2 = this.a;
                DynamicMtaUtil.uploadDownloadTempMtaWithStart(str3, template, nanoTime - j2, this.b - j2, true);
                boolean z = false;
                ViewNode viewNode = null;
                try {
                    absolutePath = com.jd.dynamic.lib.utils.m.N(file).getAbsolutePath();
                    h2 = com.jd.dynamic.b.e.a.a.h(absolutePath, TemplateRequest.this.template);
                } catch (Exception e2) {
                    DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_PARSE, "getTemplateFromNet catched exception", TemplateRequest.this.getBizField(), TemplateRequest.this.systemCode, (int) R2.attr.liteMode, e2);
                    com.jd.dynamic.lib.utils.t.e(DynamicFetcher.TAG, "getTemplateFromNet(),faild to parse dynamic binary");
                }
                if (!((Boolean) h2.first).booleanValue()) {
                    com.jd.dynamic.lib.utils.m.H(absolutePath);
                    DynamicFetcher.tryLoadAssetsTemp(TemplateRequest.this, "getTemplateFromNet(),verify dynamic binary file md5 failed!", 307, 1006);
                    DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_STORAGE, "getTemplateFromDisk verified fail cache file  reason: " + ((String) h2.second) + " url: " + TemplateRequest.this.template.fullFileUrl, TemplateRequest.this.getBizField(), TemplateRequest.this.systemCode, (int) R2.attr.layout_constraintHeight, (Exception) null);
                    return;
                }
                String bizField2 = TemplateRequest.this.getBizField();
                TemplateRequest templateRequest3 = TemplateRequest.this;
                viewNode = DynamicXParser.parseBinaryToViewNode(absolutePath, bizField2, templateRequest3.systemCode, templateRequest3.mtaId);
                if (viewNode == null) {
                    str = "view node is null";
                    bizField = TemplateRequest.this.getBizField();
                    str2 = TemplateRequest.this.systemCode;
                } else if (!TextUtils.isEmpty(viewNode.getViewName())) {
                    if (!viewNode.parseSuccess) {
                        str = "view node parse error ";
                        bizField = TemplateRequest.this.getBizField();
                        str2 = TemplateRequest.this.systemCode;
                    }
                    DynamicMtaUtil.appendDownloadTypeMtaStat(TemplateRequest.this.mtaId, 3);
                    TemplateRequest templateRequest4 = TemplateRequest.this;
                    com.jd.dynamic.b.e.a.a.f(templateRequest4.systemCode, templateRequest4.template);
                    z = viewNode == null && !TextUtils.isEmpty(viewNode.getViewName()) && viewNode.parseSuccess;
                    TemplateRequest templateRequest5 = TemplateRequest.this;
                    if (z) {
                        DynamicFetcher.tryLoadAssetsTemp(templateRequest5, "getTemplateFromNet(),faild to parse dynamic binary", 305, 1007);
                        return;
                    }
                    Listener listener = templateRequest5.listener;
                    if (listener != null) {
                        listener.onEnd(viewNode, templateRequest5.mtaId);
                        return;
                    }
                    return;
                } else {
                    str = "view node name is empty ";
                    bizField = TemplateRequest.this.getBizField();
                    str2 = TemplateRequest.this.systemCode;
                }
                DynamicFetcher.reportParseFileError(str, bizField, str2);
                DynamicMtaUtil.appendDownloadTypeMtaStat(TemplateRequest.this.mtaId, 3);
                TemplateRequest templateRequest42 = TemplateRequest.this;
                com.jd.dynamic.b.e.a.a.f(templateRequest42.systemCode, templateRequest42.template);
                z = viewNode == null && !TextUtils.isEmpty(viewNode.getViewName()) && viewNode.parseSuccess;
                TemplateRequest templateRequest52 = TemplateRequest.this;
                if (z) {
                }
            }
        });
    }

    private static void getTemplateFromNetWithRetry(TemplateRequest templateRequest) {
        if (templateRequest == null) {
            return;
        }
        int J = com.jd.dynamic.b.a.b.o().J();
        com.jd.dynamic.lib.utils.t.g(TAG, "getTemplateFromNetWithRetry: retry count: " + J);
        com.jd.dynamic.b.f.a aVar = new com.jd.dynamic.b.f.a(templateRequest, J);
        aVar.h(new a.InterfaceC0069a() { // from class: com.jd.dynamic.base.DynamicFetcher.6
            @Override // com.jd.dynamic.b.f.a.InterfaceC0069a
            public void onFail(TemplateRequest templateRequest2, String str, int i2) {
                com.jd.dynamic.lib.utils.t.g(DynamicFetcher.TAG, "onFail msg: " + str + " ,errCode: " + i2);
                DynamicFetcher.tryLoadAssetsTemp(templateRequest2, str, i2, 1009);
            }

            @Override // com.jd.dynamic.b.f.a.InterfaceC0069a
            public void onSuccess() {
                com.jd.dynamic.lib.utils.t.g(DynamicFetcher.TAG, "success!");
            }
        });
        if (DynamicSdk.getEngine().getRequest() != null) {
            DynamicSdk.getEngine().getRequest().downloadFile(templateRequest.template.fullFileUrl, com.jd.dynamic.b.e.a.a.l(templateRequest.systemCode, templateRequest.getBusinessCode()), com.jd.dynamic.lib.utils.m.k(templateRequest.template), aVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void internalRequestCommon(TemplateRequest templateRequest) {
        try {
            int i2 = templateRequest.fromNet ? 3 : 0;
            if (!com.jd.dynamic.b.e.a.a.p(templateRequest.systemCode)) {
                if (!templateRequest.fromNet) {
                    i2 = 2;
                }
                com.jd.dynamic.b.e.a.a.c(templateRequest.systemCode);
                if (!DynamicSdk.getEngine().isFetchAtFirstRequest()) {
                    DynamicSdk.getEngine().fetchTemplates(null, false, templateRequest.systemCode);
                }
            } else if (!templateRequest.fromNet) {
                i2 = 1;
            }
            Template a = !TextUtils.isEmpty(templateRequest.getBizField()) ? com.jd.dynamic.b.e.a.a.a(templateRequest.systemCode, templateRequest.getBizField()) : null;
            if ((a == null || !a.isValid()) && !TextUtils.isEmpty(templateRequest.getBusinessCode())) {
                a = com.jd.dynamic.b.e.a.a.i(templateRequest.systemCode, templateRequest.getBusinessCode());
            }
            if (a == null) {
                a = Template.create(templateRequest);
            }
            DynamicMtaUtil.updateTempListSource(templateRequest.mtaId, i2);
            DynamicMtaUtil.updateTemplate(templateRequest.mtaId, a);
            templateRequest.template = a;
            internalRequestDynamic(templateRequest);
        } catch (Exception e2) {
            if (templateRequest != null) {
                DynamicMtaUtil.startLoadTemplate(templateRequest.systemCode, templateRequest.getBizField(), templateRequest.mtaId);
            }
            DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_DOWNLOAD, "internalRequestCommon catched Exception", templateRequest == null ? null : templateRequest.getBizField(), templateRequest != null ? templateRequest.systemCode : null, e2);
            com.jd.dynamic.lib.utils.t.e(TAG, "internalRequestCommon error", com.jd.dynamic.lib.utils.t.d(e2));
        }
    }

    private static void internalRequestDynamic(TemplateRequest templateRequest) {
        String dynamicStatus = DynamicSdk.getDynamicStatus();
        if (templateRequest == null) {
            return;
        }
        if (TextUtils.equals(dynamicStatus, "0")) {
            Listener listener = templateRequest.listener;
            if (listener != null) {
                listener.onError(new DowngradeException("\u52a8\u6001\u5316SDK\u964d\u7ea7\u4e0d\u53ef\u7528"));
            }
            DynamicMtaUtil.uploadDowngradeMta(Template.create(templateRequest), "3", dynamicStatus, null, null);
            return;
        }
        Template template = templateRequest.template;
        if (template == null || !template.isValid()) {
            tryLoadAssetsTemp(templateRequest, "internalRequestDynamic() \u4f20\u53c2\u5b58\u5728\u7a7a\u5b57\u6bb5\uff0c\u5e76\u4e14\u52a0\u8f7dassets\u6a21\u677f\u5931\u8d25!", 301, 1003);
        } else if (getTemplateFromMem(templateRequest) || getTemplateFromDisk(templateRequest)) {
        } else {
            if (!TextUtils.equals(dynamicStatus, "1")) {
                DynamicMtaUtil.uploadDowngradeMta(Template.create(templateRequest), "3", dynamicStatus, null, null);
                tryLoadAssetsTemp(templateRequest, "\u52a8\u6001\u5316SDK\u964d\u7ea7\u4e0d\u53ef\u7528\uff0c\u5e76\u4e14\u52a0\u8f7dassets\u6a21\u677f\u5931\u8d25!", 301, 1005);
                return;
            }
            com.jd.dynamic.b.d.a f2 = com.jd.dynamic.b.d.a.f(templateRequest.systemCode);
            boolean d = f2.d(templateRequest.getBizField());
            boolean e2 = f2.e(templateRequest.getBizField());
            boolean b = f2.b(templateRequest.getBizField());
            if ((TextUtils.equals("1", f2.b) && !d && !e2) || (!TextUtils.equals("1", f2.b) && b)) {
                if (com.jd.dynamic.b.a.b.o().K()) {
                    getTemplateFromNetWithRetry(templateRequest);
                    return;
                } else {
                    getTemplateFromNet(templateRequest);
                    return;
                }
            }
            DynamicMtaUtil.uploadDowngradeMta(Template.create(templateRequest), "3", dynamicStatus, f2.b, d ? "0" : b ? "1" : "2");
            tryLoadAssetsTemp(templateRequest, templateRequest.systemCode + "\u964d\u7ea7\u4e0d\u53ef\u7528\uff0c\u5e76\u4e14\u52a0\u8f7dassets\u6a21\u677f\u5931\u8d25!", 301, 1004);
        }
    }

    public static void reportParseFileError(String str, String str2, String str3) {
        DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_PARSE, str + " -> getTemplateFromNet parse fail", str2, str3, (int) R2.attr.list_item_divider, (Exception) null);
    }

    @Deprecated
    public static void requestDynamicConfig(String str, String str2, Listener listener, Activity activity) {
        requestDynamicConfig(DynamicSdk.getEngine().getAppType(), str, str2, DynamicSdk.getEngine().getHost(), listener, activity);
    }

    @Deprecated
    public static void requestDynamicConfig(String str, String str2, String str3, String str4, Listener listener, Activity activity) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3) && !TextUtils.isEmpty(str4)) {
            requestDynamicConfigCommon(str2, "", str3, null, null, listener);
            return;
        }
        com.jd.dynamic.lib.utils.t.e(TAG, "requestDynamicConfig(),\u5b58\u5728\u7a7a\u53c2\u6570\uff01\uff01\uff01");
        if (listener != null) {
            listener.onError(new DynamicException("requestDynamicConfig(),\u5b58\u5728\u7a7a\u53c2\u6570\uff01\uff01\uff01", 301));
        }
    }

    @Deprecated
    public static void requestDynamicConfigByBizField(String str, String str2, String str3, Listener listener) {
        requestDynamicConfigCommon(str, str2, "", str3, null, listener);
    }

    public static void requestDynamicConfigByBizFieldWithLocalStream(String str, String str2, InputStream inputStream, Listener listener) {
        requestDynamicConfigCommon(str, str2, "", null, inputStream, listener);
    }

    @Deprecated
    public static void requestDynamicConfigByBizFiled(String str, String str2, Listener listener) {
        requestDynamicConfigCommon(str, str2, "", null, null, listener);
    }

    @Deprecated
    public static void requestDynamicConfigByBizFiled(String str, String str2, String str3, Listener listener) {
        requestDynamicConfigByBizField(str, str2, str3, listener);
    }

    @Deprecated
    public static void requestDynamicConfigByBusinessCode(String str, String str2, Listener listener) {
        requestDynamicConfigCommon(str, "", str2, null, null, listener);
    }

    @Deprecated
    public static void requestDynamicConfigByBusinessCode(String str, String str2, String str3, Listener listener) {
        requestDynamicConfigCommon(str, "", str2, str3, null, listener);
    }

    private static void requestDynamicConfigCommon(final String str, final String str2, String str3, String str4, InputStream inputStream, final Listener listener) {
        DowngradeException downgradeException;
        String str5;
        String str6;
        final String dynamicStatus = DynamicSdk.getDynamicStatus();
        if (TextUtils.equals(dynamicStatus, "0")) {
            if (listener != null) {
                listener.onError(new DowngradeException("\u52a8\u6001\u5316SDK\u964d\u7ea7\u4e0d\u53ef\u7528"));
            }
            str5 = null;
            str6 = null;
        } else {
            final com.jd.dynamic.b.d.a g2 = com.jd.dynamic.b.d.a.g(str);
            if (TextUtils.equals(g2.b, "0") && !g2.b(str2) && !g2.e(str2)) {
                if (listener != null) {
                    downgradeException = new DowngradeException(str + "\u964d\u7ea7\u4e0d\u53ef\u7528", 501);
                    listener.onError(downgradeException);
                }
                str5 = g2.b;
                str6 = "0";
            } else if (!g2.d(str2)) {
                if ((TextUtils.isEmpty(str) || (TextUtils.isEmpty(str2) && TextUtils.isEmpty(str3))) && listener != null) {
                    listener.onError(new DynamicException("requestDynamicConfigCommon(),\u5b58\u5728\u7a7a\u53c2\u6570\uff01\uff01\uff01", 301));
                }
                String str7 = str3;
                String startMtaStat = DynamicMtaUtil.startMtaStat(DynamicSdk.getEngine().getAppType(), str, str7, str2, false);
                DynamicMtaUtil.startGetTemplate(str, str2, startMtaStat);
                final TemplateRequest templateRequest = new TemplateRequest(startMtaStat, str, str2, str3, str4, inputStream, listener);
                DynamicSdk.getEngine().getHost();
                if (com.jd.dynamic.b.a.b.o().N() && tryLoadAssetsTemp(templateRequest, "\u5f3a\u5236\u4f7f\u7528\u9884\u7f6e\u6a21\u677f", 300, -1000)) {
                    return;
                }
                try {
                    if (!com.jd.dynamic.b.e.a.a.j(str)) {
                        Template a = com.jd.dynamic.b.e.a.a.a(str, str2);
                        DynamicMtaUtil.updateTemplate(startMtaStat, a);
                        if (TextUtils.isEmpty(str3) && a != null) {
                            str7 = a.businessCode;
                        }
                        if (!TextUtils.isEmpty(str7)) {
                            MtaTimePair mtaTimePair = new MtaTimePair();
                            mtaTimePair.startRecord();
                            ViewNode q = com.jd.dynamic.b.e.a.a.q(com.jd.dynamic.b.e.a.a.o(str, str2));
                            mtaTimePair.endRecord();
                            if (q != null && !TextUtils.isEmpty(q.getViewName()) && q.parseSuccess) {
                                com.jd.dynamic.lib.utils.t.g("getTemplateFromMem() [without thread create] hit catch return!!!", str + File.separator + str7);
                                DynamicMtaUtil.appendGetTemplateEnd(startMtaStat);
                                DynamicMtaUtil.appendCreateModelMtaStat(startMtaStat, mtaTimePair);
                                DynamicMtaUtil.updateTempListSource(startMtaStat, 1);
                                DynamicMtaUtil.appendDownloadTypeMtaStat(startMtaStat, 1);
                                if (listener != null) {
                                    DynamicMtaUtil.startLoadTemplate(str, str2, startMtaStat);
                                    listener.onEnd(q, startMtaStat);
                                    return;
                                }
                                return;
                            }
                        }
                    } else if (!DynamicSdk.getEngine().isFetchAtFirstRequest()) {
                        DynamicSdk.getEngine().fetchTemplates(null, false, str);
                    }
                    templateRequest.task = sExecutor.submit(new Callable<Object>() { // from class: com.jd.dynamic.base.DynamicFetcher.2
                        /* JADX WARN: Removed duplicated region for block: B:20:0x004b A[Catch: all -> 0x0100, TryCatch #1 {Exception -> 0x0109, blocks: (B:6:0x0008, B:8:0x0012, B:9:0x0016, B:46:0x0103, B:10:0x0017, B:12:0x0031, B:14:0x003b, B:20:0x004b, B:22:0x0064, B:24:0x006a, B:40:0x00f9, B:41:0x00fe, B:27:0x0074, B:29:0x008f, B:31:0x00a3, B:33:0x00bc, B:35:0x00c8, B:37:0x00da, B:39:0x00dc, B:32:0x00a9), top: B:51:0x0008 }] */
                        /* JADX WARN: Removed duplicated region for block: B:29:0x008f A[Catch: all -> 0x0100, TryCatch #1 {Exception -> 0x0109, blocks: (B:6:0x0008, B:8:0x0012, B:9:0x0016, B:46:0x0103, B:10:0x0017, B:12:0x0031, B:14:0x003b, B:20:0x004b, B:22:0x0064, B:24:0x006a, B:40:0x00f9, B:41:0x00fe, B:27:0x0074, B:29:0x008f, B:31:0x00a3, B:33:0x00bc, B:35:0x00c8, B:37:0x00da, B:39:0x00dc, B:32:0x00a9), top: B:51:0x0008 }] */
                        /* JADX WARN: Removed duplicated region for block: B:32:0x00a9 A[Catch: all -> 0x0100, TryCatch #1 {Exception -> 0x0109, blocks: (B:6:0x0008, B:8:0x0012, B:9:0x0016, B:46:0x0103, B:10:0x0017, B:12:0x0031, B:14:0x003b, B:20:0x004b, B:22:0x0064, B:24:0x006a, B:40:0x00f9, B:41:0x00fe, B:27:0x0074, B:29:0x008f, B:31:0x00a3, B:33:0x00bc, B:35:0x00c8, B:37:0x00da, B:39:0x00dc, B:32:0x00a9), top: B:51:0x0008 }] */
                        @Override // java.util.concurrent.Callable
                        /*
                            Code decompiled incorrectly, please refer to instructions dump.
                        */
                        public Object call() {
                            boolean z;
                            Listener listener2 = Listener.this;
                            if (listener2 != null) {
                                listener2.onStart();
                            }
                            try {
                                if (DynamicSdk.getEngine().isFetchAtFirstRequest()) {
                                    synchronized (DynamicFetcher.objLock1) {
                                        Boolean bool = (Boolean) DynamicFetcher.sFirstRequestMap.get(str);
                                        if (TextUtils.equals("1", g2.b) && !g2.d(str2) && !g2.e(str2)) {
                                            z = false;
                                            if (z) {
                                                Template create = Template.create(templateRequest);
                                                String str8 = dynamicStatus;
                                                com.jd.dynamic.b.d.a aVar = g2;
                                                DynamicMtaUtil.uploadDowngradeMta(create, "3", str8, aVar.b, DynamicMtaUtil.getDowngradeTemplateType(aVar, str2));
                                            }
                                            if ((bool == null && bool.booleanValue() && !com.jd.dynamic.b.e.a.a.j(str)) || z) {
                                                DynamicFetcher.internalRequestCommon(templateRequest);
                                            } else {
                                                templateRequest.fromNet = true;
                                                ConcurrentHashMap concurrentHashMap = DynamicFetcher.sFirstRequestMap;
                                                String str9 = str;
                                                Boolean bool2 = Boolean.TRUE;
                                                concurrentHashMap.put(str9, bool2);
                                                if (DynamicFetcher.pendingTempReqList.containsKey(str)) {
                                                    ArrayList arrayList = new ArrayList();
                                                    arrayList.add(templateRequest);
                                                    DynamicFetcher.pendingTempReqList.put(str, arrayList);
                                                } else {
                                                    ArrayList arrayList2 = (ArrayList) DynamicFetcher.pendingTempReqList.get(str);
                                                    if (!arrayList2.contains(templateRequest)) {
                                                        arrayList2.add(templateRequest);
                                                    }
                                                }
                                                if (!DynamicFetcher.sBunchRequestStatus.containsKey(str) && ((Boolean) DynamicFetcher.sBunchRequestStatus.get(str)).booleanValue()) {
                                                    return null;
                                                }
                                                DynamicFetcher.sBunchRequestStatus.put(str, bool2);
                                                DynamicSdk.getEngine().fetchTemplates(DynamicFetcher.getGlobalConfigListener(str), false, str);
                                            }
                                        }
                                        z = true;
                                        if (z) {
                                        }
                                        if (bool == null) {
                                        }
                                        templateRequest.fromNet = true;
                                        ConcurrentHashMap concurrentHashMap2 = DynamicFetcher.sFirstRequestMap;
                                        String str92 = str;
                                        Boolean bool22 = Boolean.TRUE;
                                        concurrentHashMap2.put(str92, bool22);
                                        if (DynamicFetcher.pendingTempReqList.containsKey(str)) {
                                        }
                                        if (!DynamicFetcher.sBunchRequestStatus.containsKey(str)) {
                                        }
                                        DynamicFetcher.sBunchRequestStatus.put(str, bool22);
                                        DynamicSdk.getEngine().fetchTemplates(DynamicFetcher.getGlobalConfigListener(str), false, str);
                                    }
                                } else {
                                    DynamicFetcher.internalRequestCommon(templateRequest);
                                }
                            } catch (Exception e2) {
                                DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_DOWNLOAD, "requestDynamicConfigCommon subTask catched Exception", str2, str, e2);
                                DynamicFetcher.tryLoadAssetsTemp(templateRequest, "requestDynamicConfigCommon onError,load assets failed again!!!", 300, 1000);
                            }
                            return null;
                        }
                    });
                    return;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_DOWNLOAD, "requestDynamicConfigCommon catched Exception", str2, str, e2);
                    tryLoadAssetsTemp(templateRequest, "requestDynamicConfigCommon onError,load assets failed again!!!", 300, 1001);
                    return;
                }
            } else {
                if (listener != null) {
                    downgradeException = new DowngradeException(str2 + "\u964d\u7ea7\u4e0d\u53ef\u7528", 502);
                    listener.onError(downgradeException);
                }
                str5 = g2.b;
                str6 = "0";
            }
        }
        DynamicMtaUtil.uploadDowngradeTemplateMta(str, str2, str3, dynamicStatus, str5, str6);
    }

    public static void requestTemplateConfigs(String str, final String str2, final boolean z, final GlobalConfigListener globalConfigListener) {
        final String dynamicStatus = DynamicSdk.getDynamicStatus();
        if (!TextUtils.equals(dynamicStatus, "1")) {
            if (globalConfigListener != null) {
                globalConfigListener.onError(new DowngradeException("SDK \u964d\u7ea7\u4e0d\u53ef\u7528"));
            }
            DynamicMtaUtil.uploadDowngradeQueryMta(str2, dynamicStatus, null);
            return;
        }
        final com.jd.dynamic.b.d.a g2 = com.jd.dynamic.b.d.a.g(str2);
        final boolean z2 = !TextUtils.equals(g2.b, "1");
        if (z2 && !com.jd.dynamic.lib.utils.m.I(g2.f1728c)) {
            if (globalConfigListener != null) {
                globalConfigListener.onError(new DowngradeException(str2 + " \u964d\u7ea7\u4e0d\u53ef\u7528", 501));
            }
            DynamicMtaUtil.uploadDowngradeQueryMta(str2, dynamicStatus, g2.b);
            return;
        }
        DynamicSdk.getEngine().getHost();
        if (com.jd.dynamic.b.e.a.a.j(str2) || globalConfigListener == null) {
            if (DynamicSdk.getEngine().getRequest() != null) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("appType", str);
                    jSONObject.put(DYConstants.DYN_PRV_SYSCODE_KEY, str2);
                } catch (Exception unused) {
                }
                DynamicSdk.getEngine().getRequest().requestWithHost(IExceptionHandler.DynamicExceptionData.TYPE_QUERY_TEMPLATES, jSONObject.toString(), DynamicSdk.getEngine().getHost(), new INetWorkRequest.ResponseCallBack() { // from class: com.jd.dynamic.base.DynamicFetcher.7
                    final long a = System.nanoTime();
                    long b = 0;

                    /* renamed from: c  reason: collision with root package name */
                    AtomicInteger f1837c = new AtomicInteger(0);

                    @Override // com.jd.dynamic.base.interfaces.INetWorkRequest.ResponseCallBack
                    public void onError(INetWorkRequest.ErrorResponse errorResponse) {
                        this.b = System.nanoTime();
                        StringBuilder sb = new StringBuilder();
                        sb.append("requestTemplateConfigs() onError error = ");
                        sb.append(errorResponse == null ? "" : errorResponse.toString());
                        DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_QUERY_TEMPLATES, sb.toString(), (String) null, str2, com.jd.dynamic.lib.utils.m.U(errorResponse == null ? null : errorResponse.errorMsg), (Exception) null);
                        if (errorResponse == null || errorResponse.errorCode != -105239) {
                            DynamicMtaUtil.uploadQueryTempsMta(str2, this.b - this.a, false, false);
                        } else {
                            com.jd.dynamic.lib.utils.t.c("this is onCancel.");
                            DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_QUERY_TEMPLATES, "cancel", "", str2, 1012, (Exception) null);
                        }
                        GlobalConfigListener globalConfigListener2 = GlobalConfigListener.this;
                        if (globalConfigListener2 != null) {
                            globalConfigListener2.onError(new Exception("errorCode = " + errorResponse.errorCode + ",errorMsg = " + errorResponse.errorMsg));
                        }
                    }

                    @Override // com.jd.dynamic.base.interfaces.INetWorkRequest.ResponseCallBack
                    public void onStart() {
                        GlobalConfigListener globalConfigListener2 = GlobalConfigListener.this;
                        if (globalConfigListener2 != null) {
                            globalConfigListener2.onStart();
                        }
                        this.f1837c.getAndIncrement();
                    }

                    @Override // com.jd.dynamic.base.interfaces.INetWorkRequest.ResponseCallBack
                    public void onSuccess(JSONObject jSONObject2) {
                        String str3;
                        boolean z3;
                        GlobalConfigListener globalConfigListener2;
                        int i2 = this.f1837c.get();
                        if (i2 > 1) {
                            DynamicSdk.handException("callBack", "requestTemplateConfigs onStart \u56de\u8c03\u4e86" + i2 + "\u6b21", null, str2, null);
                        }
                        this.b = System.nanoTime();
                        ArrayList<Template> arrayList = new ArrayList();
                        try {
                            if (TextUtils.equals("0", jSONObject2.optString("code"))) {
                                QueryTemplatesEntity queryTemplatesEntity = (QueryTemplatesEntity) new Gson().fromJson(jSONObject2.toString(), QueryTemplatesEntity.class);
                                com.jd.dynamic.b.e.a.a.d(str2, System.currentTimeMillis() + (Long.parseLong(queryTemplatesEntity.getExpireTime()) * 1000));
                                for (Template template : queryTemplatesEntity.getData()) {
                                    if (template.isValid()) {
                                        template.systemCode = str2;
                                        if ((!z2 || !g2.b(template.bizField)) && (z2 || g2.e(template.bizField) || g2.d(template.bizField))) {
                                            String str4 = dynamicStatus;
                                            com.jd.dynamic.b.d.a aVar = g2;
                                            DynamicMtaUtil.uploadDowngradeMta(template, "3", str4, aVar.b, DynamicMtaUtil.getDowngradeTemplateType(aVar, template.bizField));
                                        }
                                        arrayList.add(template);
                                    }
                                }
                                com.jd.dynamic.b.e.a.a.e(str2, queryTemplatesEntity);
                                DynamicMtaUtil.uploadQueryTempsMta(str2, this.b - this.a, true, false);
                            } else {
                                DynamicMtaUtil.uploadQueryTempsMta(str2, this.b - this.a, false, false);
                            }
                            if (!z) {
                                GlobalConfigListener globalConfigListener3 = GlobalConfigListener.this;
                                if (globalConfigListener3 != null) {
                                    globalConfigListener3.onEnd(false);
                                    return;
                                }
                                return;
                            }
                            if (!com.jd.dynamic.lib.utils.m.I(arrayList) || DynamicFetcher.sDownloadNumMap.contains(str2)) {
                                z3 = true;
                            } else {
                                DynamicFetcher.sDownloadNumMap.put(str2, Integer.valueOf(arrayList.size()));
                                for (Template template2 : arrayList) {
                                    com.jd.dynamic.lib.utils.t.c("downloadTemplates() template:" + template2);
                                    if (com.jd.dynamic.b.a.b.o().K()) {
                                        DynamicFetcher.downloadTemplateWithRetry(template2, str2, GlobalConfigListener.this);
                                    } else {
                                        DynamicFetcher.downloadTemplate(template2, str2, GlobalConfigListener.this);
                                    }
                                }
                                z3 = false;
                            }
                            if (!z3 || (globalConfigListener2 = GlobalConfigListener.this) == null) {
                                return;
                            }
                            globalConfigListener2.onError(new DynamicException("has no valid templates  or current" + str2 + "`s templates are downloading!!!"));
                        } catch (Exception e2) {
                            DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_QUERY_TEMPLATES, "requestTemplateConfigs() queryTemplate failed", (String) null, str2, (int) R2.attr.layout_constraintGuide_percent, e2);
                            Object[] objArr = new Object[3];
                            objArr[0] = DynamicFetcher.TAG;
                            objArr[1] = "requestTemplateConfigs() queryTemplate failed";
                            StringBuilder sb = new StringBuilder();
                            sb.append("httpResponse::");
                            sb.append(jSONObject2 == null ? DYConstants.DY_NULL_STR : jSONObject2.toString());
                            objArr[2] = sb.toString();
                            com.jd.dynamic.lib.utils.t.e(objArr);
                            if (GlobalConfigListener.this != null) {
                                StringBuilder sb2 = new StringBuilder("requestTemplateConfigs() queryTemplate failed,");
                                if (jSONObject2 == null) {
                                    str3 = "httpResponse:null";
                                } else if (jSONObject2.toString() != null) {
                                    sb2.append("httpResponse:");
                                    sb2.append(jSONObject2);
                                    GlobalConfigListener.this.onError(new DynamicException(sb2.toString(), e2, 306));
                                } else {
                                    str3 = "httpResponse:empty";
                                }
                                sb2.append(str3);
                                GlobalConfigListener.this.onError(new DynamicException(sb2.toString(), e2, 306));
                            }
                        }
                    }
                });
                return;
            }
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("requestTemplateConfigs() appType:");
        sb.append(str);
        sb.append(",systemCode:");
        sb.append(str2);
        sb.append("\u7f13\u5b58\u5728\u6709\u6548\u671f\u4e2d(\u5230\u671f\u65f6\u95f4\uff1a");
        sb.append(DateFormat.getInstance().format(new Date(com.jd.dynamic.lib.utils.v.a(str2 + "-nextFetchTime", 0L))));
        sb.append(")\uff0c\u8fd4\u56de\uff01");
        com.jd.dynamic.lib.utils.t.c(sb.toString());
        globalConfigListener.onEnd(true);
    }

    public static void reset() {
        synchronized (objLock1) {
            sFirstRequestMap.clear();
            sBunchRequestStatus.clear();
            pendingTempReqList.clear();
        }
        sDownloadNumMap.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean tryLoadAssetsTemp(TemplateRequest templateRequest, String str, int i2, int i3) {
        try {
            boolean templateFromAssets = getTemplateFromAssets(templateRequest, i3);
            if (!templateFromAssets) {
                Listener listener = templateRequest.listener;
                if (listener != null) {
                    listener.onError(new DynamicException(str, i2));
                }
                DynamicMtaUtil.appendDownloadTypeMtaStat(templateRequest.mtaId, 0);
                DynamicMtaUtil.uploadMta(DynamicSdk.getEngine().getContext(), templateRequest.mtaId, null);
            }
            Object[] objArr = new Object[1];
            StringBuilder sb = new StringBuilder();
            sb.append("[bizField:");
            sb.append(templateRequest.getBizField());
            sb.append(",businessCode:");
            sb.append(templateRequest.getBusinessCode());
            sb.append("]\u4ece\u5185\u5b58\u3001\u78c1\u76d8\u3001\u7f51\u7edc\u52a0\u8f7d\u5931\u8d25\uff01\uff01\uff01\u4ece\u672c\u5730assets\u8def\u5f84\uff08");
            sb.append(templateRequest.localTemp);
            sb.append("\uff09\u52a0\u8f7d");
            sb.append(templateFromAssets ? "\u6210\u529f" : "\u5931\u8d25");
            objArr[0] = sb.toString();
            com.jd.dynamic.lib.utils.t.c(objArr);
            return templateFromAssets;
        } catch (Exception e2) {
            com.jd.dynamic.lib.utils.t.e(TAG, "tryLoadAssetsTemp() error  ", com.jd.dynamic.lib.utils.t.d(e2));
            DynamicMtaUtil.appendDownloadTypeMtaStat(templateRequest.mtaId, 0);
            DynamicMtaUtil.uploadMta(DynamicSdk.getEngine().getContext(), templateRequest.mtaId, null);
            return false;
        }
    }
}
