package com.jd.dynamic.base;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Pair;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.gson.Gson;
import com.jd.dynamic.DYConstants;
import com.jd.dynamic.b.f.d;
import com.jd.dynamic.base.DynamicFetcher;
import com.jd.dynamic.base.interfaces.IExceptionHandler;
import com.jd.dynamic.base.interfaces.INetWorkRequest;
import com.jd.dynamic.entity.MtaTimePair;
import com.jd.dynamic.entity.QueryTemplatesEntity;
import com.jd.dynamic.entity.ResultEntity;
import com.jd.dynamic.entity.Template;
import com.jd.dynamic.entity.TemplateRequest;
import com.jd.dynamic.entity.ViewNode;
import com.jd.dynamic.lib.dynamic.parser.NewDynamicXParser;
import com.jd.dynamic.lib.error.DowngradeException;
import com.jd.dynamic.lib.error.DynamicException;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.sdk.platform.business.personal.R2;
import java.io.File;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
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
public class NewDynamicFetcher {
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
    public interface GlobalConfigListener extends DynamicFetcher.GlobalConfigListener {
        @Override // com.jd.dynamic.base.DynamicFetcher.GlobalConfigListener
        void onEnd(boolean z);

        @Override // com.jd.dynamic.base.DynamicFetcher.GlobalConfigListener
        void onError(Exception exc);

        @Override // com.jd.dynamic.base.DynamicFetcher.GlobalConfigListener
        void onStart();
    }

    @Keep
    /* loaded from: classes13.dex */
    public interface Listener extends DynamicFetcher.Listener {
        @Override // com.jd.dynamic.base.DynamicFetcher.Listener
        void onEnd(ViewNode viewNode, String str);

        @Override // com.jd.dynamic.base.DynamicFetcher.Listener
        void onError(Exception exc);

        @Override // com.jd.dynamic.base.DynamicFetcher.Listener
        void onStart();
    }

    @Keep
    /* loaded from: classes13.dex */
    public static abstract class Listener2 implements Listener {
        public void onEnd(ResultEntity resultEntity, String str) {
        }

        @Override // com.jd.dynamic.base.NewDynamicFetcher.Listener, com.jd.dynamic.base.DynamicFetcher.Listener
        @Deprecated
        public void onEnd(ViewNode viewNode, String str) {
        }
    }

    @Keep
    /* loaded from: classes13.dex */
    public interface ListenerWithLocalError extends Listener {
        void onLocalTemplateError(Exception exc);
    }

    static {
        ThreadFactory threadFactory = new ThreadFactory() { // from class: com.jd.dynamic.base.NewDynamicFetcher.1

            /* renamed from: g */
            private final AtomicInteger f1916g = new AtomicInteger(1);

            @Override // java.util.concurrent.ThreadFactory
            public Thread newThread(Runnable runnable) {
                return new Thread(runnable, "DynamicFetcher#" + this.f1916g.getAndIncrement());
            }
        };
        sThreadFactory = threadFactory;
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 1000L, TimeUnit.MILLISECONDS, new SynchronousQueue(), threadFactory);
        sExecutor = threadPoolExecutor;
        threadPoolExecutor.allowCoreThreadTimeOut(true);
    }

    private NewDynamicFetcher() {
    }

    public static void callOnEnd(DynamicFetcher.Listener listener, ResultEntity resultEntity, String str) {
        if (listener == null || resultEntity == null) {
            return;
        }
        if (listener instanceof Listener2) {
            ((Listener2) listener).onEnd(resultEntity, str);
        } else {
            listener.onEnd(resultEntity.viewNode, str);
        }
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

    public static void downloadTemplate(final Template template, final String str, final GlobalConfigListener globalConfigListener) {
        if (TextUtils.equals(template.templateId, "150")) {
            com.jd.dynamic.lib.utils.t.g("downloadTemplate-nyh", "150");
        }
        File file = new File(com.jd.dynamic.b.e.a.b.p(str, template.businessCode), com.jd.dynamic.lib.utils.p.b(template.getDownloadUrl(), template.getDownloadFileName()));
        if (((Boolean) com.jd.dynamic.b.e.a.b.h(file.getAbsolutePath(), template).first).booleanValue()) {
            if (globalConfigListener != null) {
                com.jd.dynamic.lib.utils.t.c("downloadTemplate()  onEnd() hit local cache and verify file success", file.getAbsolutePath());
                checkNotifyConfigListener(str, globalConfigListener);
                return;
            }
            return;
        }
        if (file.exists()) {
            com.jd.dynamic.lib.utils.o.q(file);
        }
        if (DynamicSdk.getEngine().getRequest() != null) {
            String downloadUrl = template.getDownloadUrl();
            String k2 = com.jd.dynamic.b.a.b.o().I() ? com.jd.dynamic.lib.utils.m.k(template) : com.jd.dynamic.lib.utils.p.b(downloadUrl, template.getDownloadFileName());
            if (template.isDownloadZip()) {
                k2 = k2 + ".zip";
            }
            DynamicSdk.getEngine().getRequest().downloadFile(downloadUrl, com.jd.dynamic.b.e.a.b.n(str, template.businessCode), k2, new INetWorkRequest.DownloadCallBack() { // from class: com.jd.dynamic.base.NewDynamicFetcher.7
                long a = System.nanoTime();
                long b = 0;

                /* renamed from: c */
                AtomicInteger f1930c = new AtomicInteger(0);

                @Override // com.jd.dynamic.base.interfaces.INetWorkRequest.DownloadCallBack
                public void onError(INetWorkRequest.ErrorResponse errorResponse) {
                    this.b = System.nanoTime();
                    StringBuilder sb = new StringBuilder();
                    sb.append("downloadTemplate() onError error = ");
                    sb.append(errorResponse == null ? "" : errorResponse.toString());
                    String sb2 = sb.toString();
                    Template template2 = template;
                    DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_STORAGE, sb2, template2 == null ? null : template2.bizField, str, com.jd.dynamic.lib.utils.m.U(errorResponse == null ? null : errorResponse.errorMsg), null, com.jd.dynamic.lib.utils.m.q(template.pckVersion, null));
                    if (errorResponse == null || errorResponse.errorCode != -105239) {
                        DynamicMtaUtil.uploadDownloadTempMta(str, template, this.b - this.a, false);
                    } else {
                        com.jd.dynamic.lib.utils.t.c("this is cancel : " + errorResponse.errorMsg);
                        DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_STORAGE, "cancel", "", str, 1012, null, com.jd.dynamic.lib.utils.m.q(template.pckVersion, null));
                    }
                    com.jd.dynamic.lib.utils.t.e("downloadTemplate()  onError()", errorResponse);
                    NewDynamicFetcher.checkNotifyConfigListener(str, globalConfigListener);
                }

                @Override // com.jd.dynamic.base.interfaces.INetWorkRequest.DownloadCallBack
                public void onPause() {
                }

                @Override // com.jd.dynamic.base.interfaces.INetWorkRequest.DownloadCallBack
                public void onProgress(int i2, int i3) {
                }

                @Override // com.jd.dynamic.base.interfaces.INetWorkRequest.DownloadCallBack
                public void onStart() {
                    this.f1930c.getAndIncrement();
                }

                @Override // com.jd.dynamic.base.interfaces.INetWorkRequest.DownloadCallBack
                public void onSuccess(File file2) {
                    this.b = System.nanoTime();
                    int i2 = this.f1930c.get();
                    if (i2 > 1) {
                        DynamicSdk.handException("callBack", "downloadTemplate onStart \u56de\u8c03\u4e86" + i2 + "\u6b21", template.bizField, str, null);
                    }
                    DynamicMtaUtil.uploadDownloadTempMta(str, template, this.b - this.a, true);
                    try {
                        if (com.jd.dynamic.b.a.b.o().I()) {
                            file2 = com.jd.dynamic.lib.utils.m.N(file2);
                        }
                        String absolutePath = file2.getAbsolutePath();
                        Pair<Boolean, String> u = com.jd.dynamic.b.e.a.b.u(absolutePath, template.getMd5());
                        Object[] objArr = new Object[3];
                        StringBuilder sb = new StringBuilder();
                        sb.append("downloadTemplate-nyh  onEnd() verify file ");
                        sb.append(template.templateId);
                        sb.append("    ");
                        sb.append(((Boolean) u.first).booleanValue() ? "success" : JDReactConstant.FAILED);
                        objArr[0] = sb.toString();
                        objArr[1] = absolutePath;
                        objArr[2] = "compute md5:" + com.jd.dynamic.lib.utils.p.c(absolutePath);
                        com.jd.dynamic.lib.utils.t.c(objArr);
                        if (!((Boolean) u.first).booleanValue()) {
                            String str2 = "downloadTemplate() onEnd verify file failed reason = " + ((String) u.second) + " url:" + template.getDownloadUrl();
                            Template template2 = template;
                            DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_STORAGE, str2, template2.bizField, str, R2.attr.layout_constraintHeight, null, com.jd.dynamic.lib.utils.m.q(template2.pckVersion, null));
                            com.jd.dynamic.lib.utils.m.H(absolutePath);
                        } else if (!TextUtils.isEmpty(absolutePath) && absolutePath.endsWith(".zip")) {
                            try {
                                long nanoTime = System.nanoTime();
                                List<File> c2 = com.jd.dynamic.lib.utils.o.c(absolutePath, com.jd.dynamic.lib.utils.o.i(absolutePath));
                                com.jd.dynamic.lib.utils.m.H(absolutePath);
                                DynamicMtaUtil.uploadUnZipTempMta(str, template, System.nanoTime() - nanoTime, c2 != null, false);
                            } catch (Exception e2) {
                                Template template3 = template;
                                DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_UNZIP, "downloadTemplate()  unzip catch error", template3 == null ? null : template3.bizField, str, e2);
                                com.jd.dynamic.lib.utils.t.e("downloadTemplate-nyh onEnd() unzip error!!   " + template.templateId);
                            }
                        }
                    } catch (Exception e3) {
                        Template template4 = template;
                        DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_STORAGE, "downloadTemplate() catch error", template4 == null ? null : template4.bizField, str, R2.attr.layout_constraintGuide_percent, e3, com.jd.dynamic.lib.utils.m.q(template4.pckVersion, null));
                        com.jd.dynamic.lib.utils.t.e("downloadTemplate-nyh onEnd() error!!   " + template.templateId);
                    }
                    NewDynamicFetcher.checkNotifyConfigListener(str, globalConfigListener);
                }
            });
        }
    }

    public static void downloadTemplateWithRetry(Template template, String str, GlobalConfigListener globalConfigListener) {
        if (TextUtils.equals(template.templateId, "150")) {
            com.jd.dynamic.lib.utils.t.g("downloadTemplate-nyh", "150");
        }
        File file = new File(com.jd.dynamic.b.e.a.b.p(str, template.businessCode), com.jd.dynamic.lib.utils.p.b(template.getDownloadUrl(), template.getDownloadFileName()));
        if (((Boolean) com.jd.dynamic.b.e.a.b.h(file.getAbsolutePath(), template).first).booleanValue()) {
            if (globalConfigListener != null) {
                com.jd.dynamic.lib.utils.t.c("downloadTemplate()  onEnd() hit local cache and verify file success", file.getAbsolutePath());
                checkNotifyConfigListener(str, globalConfigListener);
                return;
            }
            return;
        }
        if (file.exists()) {
            com.jd.dynamic.lib.utils.o.q(file);
        }
        if (DynamicSdk.getEngine().getRequest() != null) {
            String downloadUrl = template.getDownloadUrl();
            String k2 = com.jd.dynamic.b.a.b.o().I() ? com.jd.dynamic.lib.utils.m.k(template) : com.jd.dynamic.lib.utils.p.b(downloadUrl, template.getDownloadFileName());
            if (template.isDownloadZip()) {
                k2 = k2 + ".zip";
            }
            DynamicSdk.getEngine().getRequest().downloadFile(downloadUrl, com.jd.dynamic.b.e.a.b.n(str, template.businessCode), k2, new com.jd.dynamic.b.f.c(template, str, com.jd.dynamic.b.a.b.o().J(), globalConfigListener));
        }
    }

    @NonNull
    public static GlobalConfigListener getGlobalConfigListener(final String str) {
        return new GlobalConfigListener() { // from class: com.jd.dynamic.base.NewDynamicFetcher.3
            @Override // com.jd.dynamic.base.NewDynamicFetcher.GlobalConfigListener, com.jd.dynamic.base.DynamicFetcher.GlobalConfigListener
            public void onEnd(boolean z) {
                try {
                    com.jd.dynamic.b.e.a.b.j();
                    synchronized (NewDynamicFetcher.objLock1) {
                        ArrayList arrayList = (ArrayList) NewDynamicFetcher.pendingTempReqList.get(str);
                        Iterator it = arrayList.iterator();
                        while (it.hasNext()) {
                            NewDynamicFetcher.internalRequestCommon((TemplateRequest) it.next());
                        }
                        arrayList.clear();
                        NewDynamicFetcher.pendingTempReqList.remove(str);
                        NewDynamicFetcher.sBunchRequestStatus.put(str, Boolean.FALSE);
                    }
                } catch (Exception e2) {
                    DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_DOWNLOAD, "GlobalConfigListener onEnd catched Exception", null, str, e2);
                    com.jd.dynamic.lib.utils.t.e(NewDynamicFetcher.TAG, "fetcher inner globalConfigListener  onEnd() error!!!", com.jd.dynamic.lib.utils.t.d(e2));
                }
            }

            @Override // com.jd.dynamic.base.NewDynamicFetcher.GlobalConfigListener, com.jd.dynamic.base.DynamicFetcher.GlobalConfigListener
            public void onError(Exception exc) {
                try {
                    synchronized (NewDynamicFetcher.objLock1) {
                        ConcurrentHashMap concurrentHashMap = NewDynamicFetcher.sFirstRequestMap;
                        String str2 = str;
                        Boolean bool = Boolean.FALSE;
                        concurrentHashMap.put(str2, bool);
                        NewDynamicFetcher.sBunchRequestStatus.put(str, bool);
                        Iterator it = ((ArrayList) NewDynamicFetcher.pendingTempReqList.get(str)).iterator();
                        while (it.hasNext()) {
                            NewDynamicFetcher.tryLoadAssetsTemp((TemplateRequest) it.next(), "getGlobalConfigListener onError,load assets failed again!!!", 300, 1002);
                        }
                        NewDynamicFetcher.pendingTempReqList.remove(str);
                    }
                } catch (Exception unused) {
                    DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_DOWNLOAD, "GlobalConfigListener onError catched Exception", null, str, exc);
                    com.jd.dynamic.lib.utils.t.e(NewDynamicFetcher.TAG, "fetcher inner globalConfigListener  onError() error!!!", com.jd.dynamic.lib.utils.t.d(exc));
                }
            }

            @Override // com.jd.dynamic.base.NewDynamicFetcher.GlobalConfigListener, com.jd.dynamic.base.DynamicFetcher.GlobalConfigListener
            public void onStart() {
            }
        };
    }

    /* JADX WARN: Removed duplicated region for block: B:76:0x00df  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static boolean getTemplateFromAssets(com.jd.dynamic.entity.TemplateRequest r16, int r17) {
        /*
            Method dump skipped, instructions count: 347
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.base.NewDynamicFetcher.getTemplateFromAssets(com.jd.dynamic.entity.TemplateRequest, int):boolean");
    }

    @Nullable
    public static ViewNode getTemplateFromCache(String str, String str2, InputStream inputStream) {
        ViewNode viewNode;
        ViewNode viewNode2 = null;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        String startMtaStat = DynamicMtaUtil.startMtaStat(DynamicSdk.getEngine().getAppType(), str, "", str2, false);
        TemplateRequest templateRequest = new TemplateRequest(startMtaStat, str, str2, "", null, inputStream, null);
        if (!com.jd.dynamic.b.e.a.b.s(templateRequest.systemCode)) {
            com.jd.dynamic.b.e.a.b.g(templateRequest.systemCode);
        }
        templateRequest.template = !TextUtils.isEmpty(templateRequest.getBizField()) ? com.jd.dynamic.b.e.a.b.a(templateRequest.systemCode, templateRequest.getBizField()) : null;
        ResultEntity v = com.jd.dynamic.b.e.a.b.v(com.jd.dynamic.b.e.a.b.r(str, str2));
        if (v != null && (viewNode = v.viewNode) != null && !TextUtils.isEmpty(viewNode.getViewName()) && v.viewNode.parseSuccess) {
            DynamicMtaUtil.startLoadTemplate(str, str2, startMtaStat);
            return v.viewNode;
        }
        Template template = templateRequest.template;
        File file = new File(com.jd.dynamic.b.e.a.b.m(templateRequest.systemCode) + File.separator + templateRequest.getBusinessCode(), com.jd.dynamic.lib.utils.p.b(template.fullFileUrl, template.fileObjectKey));
        if (file.exists() && file.isFile()) {
            if (((Boolean) com.jd.dynamic.b.e.a.b.h(file.getAbsolutePath(), templateRequest.template).first).booleanValue()) {
                viewNode2 = NewDynamicXParser.parseBinaryToViewNode(file.getAbsolutePath(), false, templateRequest.getBizField(), templateRequest.systemCode, startMtaStat);
            } else {
                file.delete();
            }
        }
        return (viewNode2 == null || TextUtils.isEmpty(viewNode2.getViewName()) || !viewNode2.parseSuccess) ? getTemplateFromLocal(inputStream, str, str2) : viewNode2;
    }

    private static boolean getTemplateFromDisk(TemplateRequest templateRequest) {
        File file;
        ViewNode viewNode;
        if (templateRequest.template.isDownloadZip()) {
            file = getZipTemplateFromDisk(templateRequest);
        } else {
            String b = com.jd.dynamic.lib.utils.p.b(templateRequest.template.getDownloadUrl(), templateRequest.template.getDownloadFileName());
            if (b == null) {
                return false;
            }
            file = new File(com.jd.dynamic.b.e.a.b.m(templateRequest.systemCode) + File.separator + templateRequest.getBusinessCode(), b);
        }
        if (file != null && file.exists() && file.length() >= 0) {
            Pair<Boolean, String> h2 = com.jd.dynamic.b.e.a.b.h(file.getAbsolutePath(), templateRequest.template);
            Boolean bool = (Boolean) h2.first;
            if (!bool.booleanValue() && templateRequest.isUseLowVersion && templateRequest.template.isDownloadZip()) {
                templateRequest.isProvideLowVersion = true;
                bool = Boolean.TRUE;
            }
            ResultEntity resultEntity = null;
            if (bool.booleanValue()) {
                resultEntity = NewDynamicXParser.parseBinaryToResultEntity(file.getAbsolutePath(), templateRequest.getBizField(), templateRequest.systemCode, templateRequest.mtaId);
            } else {
                DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_PARSE, "getTemplateFromDisk verified fail cache file len = " + file.length() + " reason:" + ((String) h2.second) + " url\uff1a" + templateRequest.template.getDownloadUrl(), templateRequest.getBizField(), templateRequest.systemCode, R2.attr.list_top_line_selector, null, com.jd.dynamic.lib.utils.m.q(templateRequest.getZipVersion(), null));
                StringBuilder sb = new StringBuilder();
                sb.append("compute md5:");
                sb.append(com.jd.dynamic.lib.utils.p.c(file.getAbsolutePath()));
                com.jd.dynamic.lib.utils.t.g("getTemplateFromDisk()  verify file failed!!!", file.getAbsolutePath(), sb.toString());
                com.jd.dynamic.lib.utils.o.q(file);
            }
            if (resultEntity == null || (viewNode = resultEntity.viewNode) == null || TextUtils.isEmpty(viewNode.getViewName()) || !resultEntity.viewNode.parseSuccess) {
                templateRequest.isProvideLowVersion = false;
                return false;
            }
            com.jd.dynamic.lib.utils.t.g("getTemplateFromDisk()  hit catch return!!!", file.getAbsolutePath());
            DynamicMtaUtil.appendDownloadTypeMtaStat(templateRequest.mtaId, 2);
            callOnEnd(templateRequest.listener, resultEntity, templateRequest.mtaId);
            return true;
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
        if (inputStream == null || (parseBinaryToViewNode = NewDynamicXParser.parseBinaryToViewNode(inputStream, str2, str, null)) == null || TextUtils.isEmpty(parseBinaryToViewNode.getViewName())) {
            return null;
        }
        return parseBinaryToViewNode;
    }

    private static boolean getTemplateFromMem(TemplateRequest templateRequest) {
        ViewNode viewNode;
        MtaTimePair mtaTimePair = new MtaTimePair();
        mtaTimePair.startRecord();
        ResultEntity v = com.jd.dynamic.b.e.a.b.v(com.jd.dynamic.b.e.a.b.r(templateRequest.systemCode, templateRequest.getBizField()));
        mtaTimePair.endRecord();
        if (v == null || (viewNode = v.viewNode) == null || ((!TextUtils.isEmpty(viewNode.getViewName())) == false || !v.viewNode.parseSuccess)) {
            return false;
        }
        com.jd.dynamic.lib.utils.t.g("getTemplateFromMem()  hit catch return!!!", templateRequest.systemCode + File.separator + templateRequest.getBusinessCode());
        DynamicMtaUtil.appendGetTemplateEnd(templateRequest.mtaId);
        DynamicMtaUtil.appendCreateModelMtaStat(templateRequest.mtaId, mtaTimePair);
        DynamicMtaUtil.appendDownloadTypeMtaStat(templateRequest.mtaId, 1);
        DynamicMtaUtil.startLoadTemplate(templateRequest.systemCode, templateRequest.getBusinessCode(), templateRequest.mtaId);
        callOnEnd(templateRequest.listener, v, templateRequest.mtaId);
        return true;
    }

    private static void getTemplateFromNet(final TemplateRequest templateRequest) {
        if (templateRequest == null || DynamicSdk.getEngine().getRequest() == null) {
            return;
        }
        String downloadUrl = templateRequest.template.getDownloadUrl();
        String k2 = com.jd.dynamic.b.a.b.o().I() ? com.jd.dynamic.lib.utils.m.k(templateRequest.template) : com.jd.dynamic.lib.utils.p.b(downloadUrl, templateRequest.template.getDownloadFileName());
        if (templateRequest.template.isDownloadZip()) {
            k2 = k2 + ".zip";
        }
        DynamicSdk.getEngine().getRequest().downloadFile(downloadUrl, com.jd.dynamic.b.e.a.b.n(templateRequest.systemCode, templateRequest.getBusinessCode()), k2, new INetWorkRequest.DownloadCallBack() { // from class: com.jd.dynamic.base.NewDynamicFetcher.4
            final long a = System.nanoTime();
            long b = 0;

            @Override // com.jd.dynamic.base.interfaces.INetWorkRequest.DownloadCallBack
            public void onError(INetWorkRequest.ErrorResponse errorResponse) {
                long nanoTime = System.nanoTime();
                this.b = nanoTime;
                if (errorResponse == null || errorResponse.errorCode != -105239) {
                    TemplateRequest templateRequest2 = templateRequest;
                    DynamicMtaUtil.uploadDownloadTempMta(templateRequest2.systemCode, templateRequest2.template, nanoTime - this.a, false);
                } else {
                    com.jd.dynamic.lib.utils.t.c("this is cancel : " + errorResponse.errorMsg);
                    String bizField = templateRequest.getBizField();
                    TemplateRequest templateRequest3 = templateRequest;
                    DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_STORAGE, "cancel", bizField, templateRequest3.systemCode, 1012, null, com.jd.dynamic.lib.utils.m.q(templateRequest3.getZipVersion(), null));
                }
                StringBuilder sb = new StringBuilder();
                sb.append("getTemplateFromNet onError error =");
                sb.append(errorResponse == null ? null : errorResponse.toString());
                DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_STORAGE, sb.toString(), templateRequest.getBizField(), templateRequest.systemCode, com.jd.dynamic.lib.utils.m.U(errorResponse == null ? null : errorResponse.errorMsg), null, com.jd.dynamic.lib.utils.m.q(templateRequest.getZipVersion(), null));
                com.jd.dynamic.lib.utils.t.e(NewDynamicFetcher.TAG, "getTemplateFromNet(),request dynamic binary file failed!");
                TemplateRequest templateRequest4 = templateRequest;
                if (templateRequest4.isProvideLowVersion) {
                    return;
                }
                NewDynamicFetcher.tryLoadAssetsTemp(templateRequest4, "getTemplateFromNet(),request dynamic binary file failed!", 304, 1008);
            }

            @Override // com.jd.dynamic.base.interfaces.INetWorkRequest.DownloadCallBack
            public void onPause() {
            }

            @Override // com.jd.dynamic.base.interfaces.INetWorkRequest.DownloadCallBack
            public void onProgress(int i2, int i3) {
            }

            @Override // com.jd.dynamic.base.interfaces.INetWorkRequest.DownloadCallBack
            public void onStart() {
            }

            /* JADX WARN: Removed duplicated region for block: B:101:0x0156 A[Catch: Exception -> 0x01bb, TryCatch #0 {Exception -> 0x01bb, blocks: (B:72:0x0020, B:74:0x002a, B:76:0x0037, B:80:0x0060, B:82:0x0098, B:84:0x00df, B:96:0x0118, B:97:0x0134, B:99:0x0146, B:100:0x0152, B:110:0x0191, B:112:0x01a3, B:114:0x01a7, B:116:0x01b1, B:101:0x0156, B:103:0x015a, B:104:0x0167, B:106:0x0171, B:107:0x017e, B:109:0x0184, B:75:0x0033), top: B:131:0x0020 }] */
            /* JADX WARN: Removed duplicated region for block: B:112:0x01a3 A[Catch: Exception -> 0x01bb, TryCatch #0 {Exception -> 0x01bb, blocks: (B:72:0x0020, B:74:0x002a, B:76:0x0037, B:80:0x0060, B:82:0x0098, B:84:0x00df, B:96:0x0118, B:97:0x0134, B:99:0x0146, B:100:0x0152, B:110:0x0191, B:112:0x01a3, B:114:0x01a7, B:116:0x01b1, B:101:0x0156, B:103:0x015a, B:104:0x0167, B:106:0x0171, B:107:0x017e, B:109:0x0184, B:75:0x0033), top: B:131:0x0020 }] */
            /* JADX WARN: Removed duplicated region for block: B:124:0x01fe  */
            /* JADX WARN: Removed duplicated region for block: B:129:0x0212  */
            /* JADX WARN: Removed duplicated region for block: B:137:? A[RETURN, SYNTHETIC] */
            /* JADX WARN: Removed duplicated region for block: B:99:0x0146 A[Catch: Exception -> 0x01bb, TryCatch #0 {Exception -> 0x01bb, blocks: (B:72:0x0020, B:74:0x002a, B:76:0x0037, B:80:0x0060, B:82:0x0098, B:84:0x00df, B:96:0x0118, B:97:0x0134, B:99:0x0146, B:100:0x0152, B:110:0x0191, B:112:0x01a3, B:114:0x01a7, B:116:0x01b1, B:101:0x0156, B:103:0x015a, B:104:0x0167, B:106:0x0171, B:107:0x017e, B:109:0x0184, B:75:0x0033), top: B:131:0x0020 }] */
            @Override // com.jd.dynamic.base.interfaces.INetWorkRequest.DownloadCallBack
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void onSuccess(java.io.File r22) {
                /*
                    Method dump skipped, instructions count: 538
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.base.NewDynamicFetcher.AnonymousClass4.onSuccess(java.io.File):void");
            }
        });
    }

    private static void getTemplateFromNetWithRetry(TemplateRequest templateRequest) {
        if (templateRequest == null || DynamicSdk.getEngine().getRequest() == null) {
            return;
        }
        String downloadUrl = templateRequest.template.getDownloadUrl();
        String k2 = com.jd.dynamic.b.a.b.o().I() ? com.jd.dynamic.lib.utils.m.k(templateRequest.template) : com.jd.dynamic.lib.utils.p.b(downloadUrl, templateRequest.template.getDownloadFileName());
        if (templateRequest.template.isDownloadZip()) {
            k2 = k2 + ".zip";
        }
        com.jd.dynamic.b.f.d dVar = new com.jd.dynamic.b.f.d(templateRequest, com.jd.dynamic.b.a.b.o().J());
        dVar.h(new d.a() { // from class: com.jd.dynamic.base.NewDynamicFetcher.5
            @Override // com.jd.dynamic.b.f.d.a
            public void onFail(TemplateRequest templateRequest2, String str, int i2) {
                com.jd.dynamic.lib.utils.t.g(NewDynamicFetcher.TAG, "zip template display fail: !" + str);
                NewDynamicFetcher.tryLoadAssetsTemp(templateRequest2, str, i2, 1009);
            }

            @Override // com.jd.dynamic.b.f.d.a
            public void onSuccess(DynamicFetcher.Listener listener, ResultEntity resultEntity, String str) {
                com.jd.dynamic.lib.utils.t.g(NewDynamicFetcher.TAG, "zip template display success!");
                NewDynamicFetcher.callOnEnd(listener, resultEntity, str);
            }
        });
        DynamicSdk.getEngine().getRequest().downloadFile(downloadUrl, com.jd.dynamic.b.e.a.b.n(templateRequest.systemCode, templateRequest.getBusinessCode()), k2, dVar);
    }

    private static File getZipTemplateFromDisk(TemplateRequest templateRequest) {
        File[] listFiles;
        File file = new File(com.jd.dynamic.b.e.a.b.m(templateRequest.systemCode) + File.separator + templateRequest.getBusinessCode());
        File file2 = null;
        if (file.exists() && file.isDirectory() && (listFiles = file.listFiles()) != null && listFiles.length >= 1) {
            if (listFiles.length == 1) {
                return listFiles[0];
            }
            String str = null;
            for (File file3 : listFiles) {
                String k2 = com.jd.dynamic.lib.utils.o.k(file3.getAbsolutePath());
                if (TextUtils.isEmpty(str) || zipVersionNew(k2, str)) {
                    file2 = file3;
                    str = k2;
                } else {
                    com.jd.dynamic.lib.utils.o.q(file3);
                }
            }
        }
        return file2;
    }

    public static void internalRequestCommon(TemplateRequest templateRequest) {
        try {
            int i2 = templateRequest.fromNet ? 3 : 0;
            if (!com.jd.dynamic.b.e.a.b.s(templateRequest.systemCode)) {
                if (!templateRequest.fromNet) {
                    i2 = 2;
                }
                com.jd.dynamic.b.e.a.b.g(templateRequest.systemCode);
                if (!DynamicSdk.getEngine().isFetchAtFirstRequest()) {
                    DynamicSdk.getEngine().newFetchTemplates(null, false, templateRequest.systemCode);
                }
            } else if (!templateRequest.fromNet) {
                i2 = 1;
            }
            Template a = !TextUtils.isEmpty(templateRequest.getBizField()) ? com.jd.dynamic.b.e.a.b.a(templateRequest.systemCode, templateRequest.getBizField()) : null;
            if ((a == null || !a.isNewValid()) && !TextUtils.isEmpty(templateRequest.getBusinessCode())) {
                a = com.jd.dynamic.b.e.a.b.i(templateRequest.systemCode, templateRequest.getBusinessCode());
            }
            if (a == null) {
                a = Template.create(templateRequest);
                a.isNewApi = true;
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
        if (templateRequest == null) {
            return;
        }
        String dynamicStatus = DynamicSdk.getDynamicStatus();
        if (TextUtils.equals(dynamicStatus, "0")) {
            DynamicFetcher.Listener listener = templateRequest.listener;
            if (listener != null) {
                listener.onError(new DowngradeException("\u52a8\u6001\u5316SDK\u964d\u7ea7\u4e0d\u53ef\u7528"));
            }
            Template create = Template.create(templateRequest);
            create.isNewApi = true;
            DynamicMtaUtil.uploadDowngradeMta(create, "3", dynamicStatus, null, null);
            return;
        }
        Template template = templateRequest.template;
        if (template == null || !template.isNewValid()) {
            tryLoadAssetsTemp(templateRequest, "internalRequestDynamic() \u4f20\u53c2\u5b58\u5728\u7a7a\u5b57\u6bb5\uff0c\u5e76\u4e14\u52a0\u8f7dassets\u6a21\u677f\u5931\u8d25!", 301, 1003);
        } else if (getTemplateFromMem(templateRequest)) {
        } else {
            if (!getTemplateFromDisk(templateRequest) || templateRequest.isProvideLowVersion) {
                if (!TextUtils.equals(dynamicStatus, "1")) {
                    Template create2 = Template.create(templateRequest);
                    create2.isNewApi = true;
                    DynamicMtaUtil.uploadDowngradeMta(create2, "3", dynamicStatus, null, null);
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
                Template create3 = Template.create(templateRequest);
                create3.isNewApi = true;
                DynamicMtaUtil.uploadDowngradeMta(create3, "3", dynamicStatus, f2.b, d ? "0" : b ? "1" : "2");
                tryLoadAssetsTemp(templateRequest, templateRequest.systemCode + "\u964d\u7ea7\u4e0d\u53ef\u7528\uff0c\u5e76\u4e14\u52a0\u8f7dassets\u6a21\u677f\u5931\u8d25!", 301, 1004);
            }
        }
    }

    @Deprecated
    public static void requestDynamicConfig(String str, String str2, Listener listener, Activity activity) {
        requestDynamicConfig(DynamicSdk.getEngine().getAppType(), str, str2, DynamicSdk.getEngine().getHost(), listener, activity);
    }

    @Deprecated
    public static void requestDynamicConfig(String str, String str2, String str3, String str4, Listener listener, Activity activity) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3) && !TextUtils.isEmpty(str4)) {
            requestDynamicConfigCommon(str2, "", str3, null, null, listener, true);
            return;
        }
        com.jd.dynamic.lib.utils.t.e(TAG, "requestDynamicConfig(),\u5b58\u5728\u7a7a\u53c2\u6570\uff01\uff01\uff01");
        if (listener != null) {
            listener.onError(new DynamicException("requestDynamicConfig(),\u5b58\u5728\u7a7a\u53c2\u6570\uff01\uff01\uff01", 301));
        }
    }

    @Deprecated
    public static void requestDynamicConfigByBizField(String str, String str2, String str3, Listener listener) {
        requestDynamicConfigCommon(str, str2, "", str3, null, listener, true);
    }

    public static void requestDynamicConfigByBizField(boolean z, String str, String str2, String str3, Listener listener) {
        requestDynamicConfigCommon(str, str2, "", str3, null, listener, z);
    }

    public static void requestDynamicConfigByBizFieldWithLocalStream(String str, String str2, InputStream inputStream, Listener listener) {
        requestDynamicConfigCommon(str, str2, "", null, inputStream, listener, true);
    }

    @Deprecated
    public static void requestDynamicConfigByBizFiled(String str, String str2, Listener listener) {
        requestDynamicConfigCommon(str, str2, "", null, null, listener, true);
    }

    @Deprecated
    public static void requestDynamicConfigByBizFiled(String str, String str2, String str3, Listener listener) {
        requestDynamicConfigByBizField(str, str2, str3, listener);
    }

    @Deprecated
    public static void requestDynamicConfigByBusinessCode(String str, String str2, Listener listener) {
        requestDynamicConfigCommon(str, "", str2, null, null, listener, true);
    }

    @Deprecated
    public static void requestDynamicConfigByBusinessCode(String str, String str2, String str3, Listener listener) {
        requestDynamicConfigCommon(str, "", str2, str3, null, listener, true);
    }

    private static void requestDynamicConfigCommon(final String str, final String str2, String str3, String str4, InputStream inputStream, final Listener listener, boolean z) {
        ViewNode viewNode;
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
                String startMtaStat = DynamicMtaUtil.startMtaStat(DynamicSdk.getEngine().getAppType(), str, str3, str2, false);
                Template tempByMtaId = DynamicMtaUtil.getTempByMtaId(startMtaStat);
                if (tempByMtaId != null) {
                    tempByMtaId.isNewApi = true;
                }
                DynamicMtaUtil.startGetTemplate(str, str2, startMtaStat);
                final TemplateRequest templateRequest = new TemplateRequest(startMtaStat, str, str2, str3, str4, inputStream, listener);
                templateRequest.isUseLowVersion = z;
                DynamicSdk.getEngine().getHost();
                if (com.jd.dynamic.b.a.b.o().N() && tryLoadAssetsTemp(templateRequest, "\u5f3a\u5236\u4f7f\u7528\u9884\u7f6e\u6a21\u677f", 300, -1000)) {
                    return;
                }
                try {
                    if (!com.jd.dynamic.b.e.a.b.l(str)) {
                        Template a = com.jd.dynamic.b.e.a.b.a(str, str2);
                        DynamicMtaUtil.updateTemplate(startMtaStat, a);
                        String str7 = (!TextUtils.isEmpty(str3) || a == null) ? str3 : a.businessCode;
                        if (!TextUtils.isEmpty(str7)) {
                            MtaTimePair mtaTimePair = new MtaTimePair();
                            mtaTimePair.startRecord();
                            ResultEntity v = com.jd.dynamic.b.e.a.b.v(com.jd.dynamic.b.e.a.b.r(str, str2));
                            mtaTimePair.endRecord();
                            if (v != null && (viewNode = v.viewNode) != null && !TextUtils.isEmpty(viewNode.getViewName()) && v.viewNode.parseSuccess) {
                                com.jd.dynamic.lib.utils.t.g("getTemplateFromMem() [without thread create] hit catch return!!!", str + File.separator + str7);
                                DynamicMtaUtil.appendGetTemplateEnd(startMtaStat);
                                DynamicMtaUtil.appendCreateModelMtaStat(startMtaStat, mtaTimePair);
                                DynamicMtaUtil.updateTempListSource(startMtaStat, 1);
                                DynamicMtaUtil.appendDownloadTypeMtaStat(startMtaStat, 1);
                                DynamicMtaUtil.startLoadTemplate(str, str2, startMtaStat);
                                callOnEnd(listener, v, startMtaStat);
                                return;
                            }
                        }
                    } else if (!DynamicSdk.getEngine().isFetchAtFirstRequest()) {
                        DynamicSdk.getEngine().newFetchTemplates(null, false, str);
                    }
                    templateRequest.task = sExecutor.submit(new Callable<Object>() { // from class: com.jd.dynamic.base.NewDynamicFetcher.2
                        /* JADX WARN: Removed duplicated region for block: B:72:0x004b A[Catch: all -> 0x0102, TryCatch #1 {Exception -> 0x010b, blocks: (B:58:0x0008, B:60:0x0012, B:61:0x0016, B:98:0x0105, B:62:0x0017, B:64:0x0031, B:66:0x003b, B:72:0x004b, B:74:0x0066, B:76:0x006c, B:92:0x00fb, B:93:0x0100, B:79:0x0076, B:81:0x0091, B:83:0x00a5, B:85:0x00be, B:87:0x00ca, B:89:0x00dc, B:91:0x00de, B:84:0x00ab), top: B:103:0x0008 }] */
                        /* JADX WARN: Removed duplicated region for block: B:81:0x0091 A[Catch: all -> 0x0102, TryCatch #1 {Exception -> 0x010b, blocks: (B:58:0x0008, B:60:0x0012, B:61:0x0016, B:98:0x0105, B:62:0x0017, B:64:0x0031, B:66:0x003b, B:72:0x004b, B:74:0x0066, B:76:0x006c, B:92:0x00fb, B:93:0x0100, B:79:0x0076, B:81:0x0091, B:83:0x00a5, B:85:0x00be, B:87:0x00ca, B:89:0x00dc, B:91:0x00de, B:84:0x00ab), top: B:103:0x0008 }] */
                        /* JADX WARN: Removed duplicated region for block: B:84:0x00ab A[Catch: all -> 0x0102, TryCatch #1 {Exception -> 0x010b, blocks: (B:58:0x0008, B:60:0x0012, B:61:0x0016, B:98:0x0105, B:62:0x0017, B:64:0x0031, B:66:0x003b, B:72:0x004b, B:74:0x0066, B:76:0x006c, B:92:0x00fb, B:93:0x0100, B:79:0x0076, B:81:0x0091, B:83:0x00a5, B:85:0x00be, B:87:0x00ca, B:89:0x00dc, B:91:0x00de, B:84:0x00ab), top: B:103:0x0008 }] */
                        @Override // java.util.concurrent.Callable
                        /*
                            Code decompiled incorrectly, please refer to instructions dump.
                            To view partially-correct code enable 'Show inconsistent code' option in preferences
                        */
                        public java.lang.Object call() {
                            /*
                                Method dump skipped, instructions count: 291
                                To view this dump change 'Code comments level' option to 'DEBUG'
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.base.NewDynamicFetcher.AnonymousClass2.call():java.lang.Object");
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
        if (com.jd.dynamic.b.e.a.b.l(str2) || globalConfigListener == null) {
            if (DynamicSdk.getEngine().getRequest() != null) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("appType", str);
                    jSONObject.put(DYConstants.DYN_PRV_SYSCODE_KEY, str2);
                    jSONObject.put("enableZip", "1");
                } catch (Exception unused) {
                }
                DynamicSdk.getEngine().getRequest().requestWithHost(IExceptionHandler.DynamicExceptionData.TYPE_QUERY_TEMPLATES, jSONObject.toString(), DynamicSdk.getEngine().getHost(), new INetWorkRequest.ResponseCallBack() { // from class: com.jd.dynamic.base.NewDynamicFetcher.6
                    final long a = System.nanoTime();
                    long b = 0;

                    /* renamed from: c */
                    AtomicInteger f1924c = new AtomicInteger(0);

                    @Override // com.jd.dynamic.base.interfaces.INetWorkRequest.ResponseCallBack
                    public void onError(INetWorkRequest.ErrorResponse errorResponse) {
                        this.b = System.nanoTime();
                        StringBuilder sb = new StringBuilder();
                        sb.append("requestTemplateConfigs() onError error = ");
                        sb.append(errorResponse == null ? "" : errorResponse.toString());
                        DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_QUERY_TEMPLATES, sb.toString(), (String) null, str2, com.jd.dynamic.lib.utils.m.U(errorResponse == null ? null : errorResponse.errorMsg), (Exception) null);
                        if (errorResponse == null || errorResponse.errorCode != -105239) {
                            DynamicMtaUtil.uploadQueryTempsMta(str2, this.b - this.a, false, true);
                        } else {
                            com.jd.dynamic.lib.utils.t.c("this is onCancel.");
                            DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_QUERY_TEMPLATES, "cancel", "", str2, 1012, (Exception) null);
                        }
                        GlobalConfigListener globalConfigListener2 = globalConfigListener;
                        if (globalConfigListener2 != null) {
                            globalConfigListener2.onError(new Exception("errorCode = " + errorResponse.errorCode + ",errorMsg = " + errorResponse.errorMsg));
                        }
                    }

                    @Override // com.jd.dynamic.base.interfaces.INetWorkRequest.ResponseCallBack
                    public void onStart() {
                        GlobalConfigListener globalConfigListener2 = globalConfigListener;
                        if (globalConfigListener2 != null) {
                            globalConfigListener2.onStart();
                        }
                        this.f1924c.getAndIncrement();
                    }

                    @Override // com.jd.dynamic.base.interfaces.INetWorkRequest.ResponseCallBack
                    public void onSuccess(JSONObject jSONObject2) {
                        String str3;
                        boolean z3;
                        GlobalConfigListener globalConfigListener2;
                        int i2 = this.f1924c.get();
                        QueryTemplatesEntity queryTemplatesEntity = null;
                        if (i2 > 1) {
                            DynamicSdk.handException("callBack", "requestTemplateConfigs onStart \u56de\u8c03\u4e86" + i2 + "\u6b21", null, str2, null);
                        }
                        this.b = System.nanoTime();
                        ArrayList<Template> arrayList = new ArrayList();
                        try {
                            if (TextUtils.equals("0", jSONObject2.optString("code"))) {
                                queryTemplatesEntity = (QueryTemplatesEntity) new Gson().fromJson(jSONObject2.toString(), QueryTemplatesEntity.class);
                                com.jd.dynamic.b.e.a.b.c(str2, System.currentTimeMillis() + (Long.parseLong(queryTemplatesEntity.getExpireTime()) * 1000));
                                for (Template template : queryTemplatesEntity.getData()) {
                                    template.isNewApi = true;
                                    if (template.isNewValid()) {
                                        template.systemCode = str2;
                                        if ((!z2 || !g2.b(template.bizField)) && (z2 || g2.e(template.bizField) || g2.d(template.bizField))) {
                                            String str4 = dynamicStatus;
                                            com.jd.dynamic.b.d.a aVar = g2;
                                            DynamicMtaUtil.uploadDowngradeMta(template, "3", str4, aVar.b, DynamicMtaUtil.getDowngradeTemplateType(aVar, template.bizField));
                                        }
                                        arrayList.add(template);
                                    }
                                }
                                com.jd.dynamic.b.e.a.b.d(str2, queryTemplatesEntity);
                                DynamicMtaUtil.uploadQueryTempsMta(str2, this.b - this.a, true, true);
                            } else {
                                DynamicMtaUtil.uploadQueryTempsMta(str2, this.b - this.a, false, true);
                            }
                            if (!z) {
                                GlobalConfigListener globalConfigListener3 = globalConfigListener;
                                if (globalConfigListener3 != null) {
                                    globalConfigListener3.onEnd(false);
                                }
                                com.jd.dynamic.b.e.a.b.k(str2, queryTemplatesEntity);
                                return;
                            }
                            com.jd.dynamic.b.e.a.b.k(str2, queryTemplatesEntity);
                            if (!com.jd.dynamic.lib.utils.m.I(arrayList) || NewDynamicFetcher.sDownloadNumMap.contains(str2)) {
                                z3 = true;
                            } else {
                                NewDynamicFetcher.sDownloadNumMap.put(str2, Integer.valueOf(arrayList.size()));
                                for (Template template2 : arrayList) {
                                    com.jd.dynamic.lib.utils.t.c("downloadTemplates() template:" + template2);
                                    if (com.jd.dynamic.b.a.b.o().K()) {
                                        NewDynamicFetcher.downloadTemplateWithRetry(template2, str2, globalConfigListener);
                                    } else {
                                        NewDynamicFetcher.downloadTemplate(template2, str2, globalConfigListener);
                                    }
                                }
                                z3 = false;
                            }
                            if (!z3 || (globalConfigListener2 = globalConfigListener) == null) {
                                return;
                            }
                            globalConfigListener2.onError(new DynamicException("has no valid templates  or current " + str2 + "`s templates are downloading!!!"));
                        } catch (Exception e2) {
                            DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_QUERY_TEMPLATES, "requestTemplateConfigs() queryTemplate failed", (String) null, str2, (int) R2.attr.layout_constraintGuide_percent, e2);
                            Object[] objArr = new Object[3];
                            objArr[0] = NewDynamicFetcher.TAG;
                            objArr[1] = "requestTemplateConfigs() queryTemplate failed";
                            StringBuilder sb = new StringBuilder();
                            sb.append("httpResponse::");
                            sb.append(jSONObject2 == null ? DYConstants.DY_NULL_STR : jSONObject2.toString());
                            objArr[2] = sb.toString();
                            com.jd.dynamic.lib.utils.t.e(objArr);
                            if (globalConfigListener != null) {
                                StringBuilder sb2 = new StringBuilder("requestTemplateConfigs() queryTemplate failed,");
                                if (jSONObject2 == null) {
                                    str3 = "httpResponse:null";
                                } else if (jSONObject2.toString() != null) {
                                    sb2.append("httpResponse:");
                                    sb2.append(jSONObject2);
                                    globalConfigListener.onError(new DynamicException(sb2.toString(), e2, 306));
                                } else {
                                    str3 = "httpResponse:empty";
                                }
                                sb2.append(str3);
                                globalConfigListener.onError(new DynamicException(sb2.toString(), e2, 306));
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

    public static boolean tryLoadAssetsTemp(TemplateRequest templateRequest, String str, int i2, int i3) {
        try {
            boolean templateFromAssets = getTemplateFromAssets(templateRequest, i3);
            if (!templateFromAssets) {
                DynamicFetcher.Listener listener = templateRequest.listener;
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

    private static boolean zipVersionNew(String str, String str2) {
        if (TextUtils.equals(str, str2) || TextUtils.isEmpty(str)) {
            return false;
        }
        if (TextUtils.isEmpty(str2)) {
            return true;
        }
        String[] split = str.split(OrderISVUtil.MONEY_DECIMAL);
        String[] split2 = str2.split(OrderISVUtil.MONEY_DECIMAL);
        if (split != null && split2 != null && split.length == split2.length) {
            int length = split.length;
            for (int i2 = 0; i2 < length; i2++) {
                String str3 = split[i2];
                String str4 = split2[i2];
                if (TextUtils.isEmpty(str3)) {
                    str3 = "0";
                }
                if (TextUtils.isEmpty(str4)) {
                    str4 = "0";
                }
                if (!TextUtils.equals(str3, str4)) {
                    if (str3.length() == str4.length()) {
                        if (str3.compareTo(str4) <= 0) {
                            return false;
                        }
                    } else if (str3.length() <= str4.length()) {
                        return false;
                    }
                    return true;
                }
            }
        }
        return false;
    }
}
