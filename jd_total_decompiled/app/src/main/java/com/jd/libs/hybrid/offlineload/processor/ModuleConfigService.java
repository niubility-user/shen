package com.jd.libs.hybrid.offlineload.processor;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.jd.dynamic.base.interfaces.IExceptionHandler;
import com.jd.libs.hybrid.HybridSDK;
import com.jd.libs.hybrid.base.HybridBase;
import com.jd.libs.hybrid.base.HybridSettings;
import com.jd.libs.hybrid.base.engine.ConfigEngine;
import com.jd.libs.hybrid.base.entity.IInterfaceCheck;
import com.jd.libs.hybrid.base.util.DatabaseExecutors;
import com.jd.libs.hybrid.base.util.HybridUrlUtils;
import com.jd.libs.hybrid.base.util.Log;
import com.jd.libs.hybrid.offlineload.OfflineLoadController;
import com.jd.libs.hybrid.offlineload.db.BuildInDataStore;
import com.jd.libs.hybrid.offlineload.db.OfflineDataStore;
import com.jd.libs.hybrid.offlineload.db.TestOfflineDataStore;
import com.jd.libs.hybrid.offlineload.entity.Module;
import com.jd.libs.hybrid.offlineload.entity.OfflineFiles;
import com.jd.libs.hybrid.offlineload.entity.OfflineModule;
import com.jd.libs.hybrid.offlineload.entity.TestModule;
import com.jd.libs.hybrid.offlineload.processor.ModuleConfigService;
import com.jd.libs.hybrid.offlineload.processor.ModuleInstallService;
import com.jd.libs.hybrid.offlineload.utils.GraySwitch;
import com.jd.libs.hybrid.offlineload.utils.ModuleHelper;
import com.jd.libs.hybrid.offlineload.utils.OfflineExceptionUtils;
import com.jd.libs.hybrid.offlineload.utils.OfflineFileHelper;
import com.jd.libs.hybrid.offlineload.utils.OfflineMtaUtils;
import com.jd.libs.xwin.http.BreakPointHelper;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.mp4parser.aspectj.lang.JoinPoint;

/* loaded from: classes16.dex */
public class ModuleConfigService {
    public static final int CONFIG_FROM_BUILDIN = 1;
    public static final int CONFIG_FROM_NET = 0;

    /* renamed from: c  reason: collision with root package name */
    private static Integer f6078c = 0;
    private static final AtomicBoolean d = new AtomicBoolean(false);

    /* renamed from: e  reason: collision with root package name */
    private static final Object f6079e = new Object();
    private final AtomicBoolean a = new AtomicBoolean(false);
    private final Object b = new Object();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jd.libs.hybrid.offlineload.processor.ModuleConfigService$2  reason: invalid class name */
    /* loaded from: classes16.dex */
    public class AnonymousClass2 implements ConfigEngine.Callback<String> {
        final /* synthetic */ Handler a;
        final /* synthetic */ Runnable b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ OfflineModule f6083c;
        final /* synthetic */ OfflineFiles d;

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ OfflineLoadController.NetConfigCallback f6084e;

        /* renamed from: f  reason: collision with root package name */
        final /* synthetic */ String f6085f;

        AnonymousClass2(Handler handler, Runnable runnable, OfflineModule offlineModule, OfflineFiles offlineFiles, OfflineLoadController.NetConfigCallback netConfigCallback, String str) {
            this.a = handler;
            this.b = runnable;
            this.f6083c = offlineModule;
            this.d = offlineFiles;
            this.f6084e = netConfigCallback;
            this.f6085f = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void b(OfflineModule offlineModule, OfflineModule offlineModule2) {
            try {
                synchronized (ModuleConfigService.this.b) {
                    if (ModuleConfigService.this.a.get()) {
                        Log.d("ModuleConfigService", "[Offline-file](config) wait for on the fly installation complete before downloading new file.");
                        ModuleConfigService.this.b.wait();
                    }
                }
            } catch (Exception e2) {
                Log.e("ModuleConfigService", e2);
                OfflineExceptionUtils.reportConfigError(JoinPoint.SYNCHRONIZATION_LOCK, "requestLatestConfigForSingle#lock", offlineModule.getAppid(), e2);
            }
            ModuleConfigService.this.onFetchLatestSingleFromNet(offlineModule2);
        }

        @Override // com.jd.libs.hybrid.base.engine.ConfigEngine.Callback
        public void onFail(int i2, String str) {
            try {
                this.a.removeCallbacks(this.b);
                boolean z = false;
                if (-4 == i2) {
                    if (this.f6083c.hasBuildIn()) {
                        if (Log.isDebug()) {
                            Log.xLogD("ModuleConfigService", "\u9879\u76ee(id:" + this.f6083c.getAppid() + ")\u6ca1\u6709\u70ed\u66f4\u65b0\u914d\u7f6e\uff0c\u4fdd\u6301\u5f53\u524d\u72b6\u6001\u3002");
                            Log.d("ModuleConfigService", "[Offline-file](config) No hotfix config for id: " + this.f6083c.getAppid() + ", stay in current page.");
                        }
                        OfflineLoadController.NetConfigCallback netConfigCallback = this.f6084e;
                        if (netConfigCallback != null) {
                            netConfigCallback.onNetworkCallback(null, false, false);
                            return;
                        }
                        return;
                    }
                    if (Log.isDebug()) {
                        Log.xLogD("ModuleConfigService", "\u9879\u76ee(id:" + this.f6083c.getAppid() + ")\u5df2\u4e0b\u7ebf\uff0c\u5c06\u91cd\u65b0\u52a0\u8f7d\u7ebf\u4e0aH5\u3002");
                        Log.d("ModuleConfigService", "[Offline-file](config) Latest config is not longer valid for id: " + this.f6083c.getAppid() + ", delete it.");
                    }
                    ModuleHelper.deleteModule(this.f6083c);
                    OfflineLoadController.NetConfigCallback netConfigCallback2 = this.f6084e;
                    if (netConfigCallback2 != null) {
                        netConfigCallback2.onNetworkCallback(null, false, true);
                        return;
                    }
                    return;
                }
                int fileVersion = this.d.isAvailable() ? this.d.getFileVersion() : -1;
                int minFileVerInt = this.d.getMinFileVerInt();
                if (fileVersion != -1 && fileVersion < minFileVerInt) {
                    z = true;
                }
                if (Log.isDebug()) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("[Offline-file](config) Fail to fetch latest config for id: ");
                    sb.append(this.f6083c.getAppid());
                    sb.append(", Local file ver = ");
                    sb.append(fileVersion);
                    sb.append(", minFileVer = ");
                    sb.append(minFileVerInt);
                    sb.append(z ? ", need to reload online page" : ", stay in current page");
                    Log.e("ModuleConfigService", sb.toString());
                    String str2 = "\u83b7\u53d6\u9879\u76ee(id:" + this.f6083c.getAppid() + ")\u7684\u7ebf\u4e0a\u6700\u65b0\u914d\u7f6e\u6570\u636e\u5931\u8d25\u3002";
                    if (fileVersion != -1) {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append(str2);
                        sb2.append("\u672c\u5730\u6587\u4ef6\u7248\u672c");
                        sb2.append(fileVersion);
                        sb2.append("\uff0c\u6700\u4f4e\u8981\u6c42\u7248\u672c");
                        sb2.append(minFileVerInt);
                        sb2.append(z ? "\u3002\u4e0d\u7b26\u5408\u6700\u4f4e\u7248\u672c\u8981\u6c42\uff0c\u91cd\u65b0\u52a0\u8f7d\u7ebf\u4e0aH5\u3002" : "\u3002\u7b26\u5408\u6700\u4f4e\u7248\u672c\u8981\u6c42\uff0c\u7ee7\u7eed\u4f7f\u7528\u672c\u5730\u79bb\u7ebf\u5305\u3002");
                        str2 = sb2.toString();
                    }
                    Log.xLogE("ModuleConfigService", str2);
                }
                OfflineLoadController.NetConfigCallback netConfigCallback3 = this.f6084e;
                if (netConfigCallback3 != null) {
                    netConfigCallback3.onNetworkCallback(null, true, z);
                }
                OfflineExceptionUtils.reportConfigError("\u5c0f\u63a5\u53e3\u5931\u8d25", "requestLatestConfigForSingle-onFail", this.f6083c.getAppid(), "H5 url: " + this.f6085f + ", " + str);
            } catch (Exception e2) {
                Log.e("ModuleConfigService", e2);
                OfflineExceptionUtils.reportConfigError(OfflineExceptionUtils.ERR_MSG_CODE, "requestLatestConfigForSingle-onFail", this.f6083c.getAppid(), e2);
            }
        }

        @Override // com.jd.libs.hybrid.base.engine.ConfigEngine.Callback
        public void onSuccess(String str) {
            try {
                final OfflineModule offlineModule = new OfflineModule();
                try {
                    offlineModule.fromJson(new JSONObject(str));
                    if (!offlineModule.useful()) {
                        onFail(-3, "Config is illegal.");
                        return;
                    }
                    this.a.removeCallbacks(this.b);
                    if (Log.isDebug()) {
                        Log.d("ModuleConfigService", "[Offline-file](config) Succeed to fetch latest config, id:" + this.f6083c.getAppid() + ", config: " + str);
                        StringBuilder sb = new StringBuilder();
                        sb.append("\u6210\u529f\u83b7\u53d6\u9879\u76ee(id:");
                        sb.append(this.f6083c.getAppid());
                        sb.append(")\u7684\u7ebf\u4e0a\u6700\u65b0\u914d\u7f6e\u6570\u636e\uff1a");
                        Log.xLogD("ModuleConfigService", sb.toString(), str);
                    }
                    if (Log.isDebug()) {
                        Log.d("JINJIAJUJJJ", "oldVersion=" + this.d.getExtendedVersion() + "  newVersion=" + offlineModule.getExtendedVersion());
                    }
                    if (ModuleHelper.hasNewModuleVersion(offlineModule, this.f6083c)) {
                        int fileVersion = this.d.isAvailable() ? this.d.getFileVersion() : -1;
                        int minFileVerInt = offlineModule.getMinFileVerInt();
                        boolean z = fileVersion != -1 && fileVersion < minFileVerInt;
                        if (Log.isDebug()) {
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append("[Offline-file](config) Fetched a newer config version, Local file ver = ");
                            sb2.append(fileVersion);
                            sb2.append("\uff0cminFileVer = ");
                            sb2.append(minFileVerInt);
                            sb2.append(z ? ", need to reload online page" : ", stay in current page");
                            Log.d("ModuleConfigService", sb2.toString());
                            String str2 = "\u9879\u76ee(id:" + offlineModule.getAppid() + ")\u7ebf\u4e0a\u5b58\u5728\u66f4\u65b0\u914d\u7f6e\u7248\u672c\u3002";
                            if (fileVersion != -1) {
                                StringBuilder sb3 = new StringBuilder();
                                sb3.append(str2);
                                sb3.append("\u672c\u5730\u6587\u4ef6\u7248\u672c");
                                sb3.append(fileVersion);
                                sb3.append("\uff0c\u6700\u4f4e\u8981\u6c42\u7248\u672c");
                                sb3.append(minFileVerInt);
                                sb3.append(z ? "\u3002\u4e0d\u7b26\u5408\u6700\u4f4e\u7248\u672c\u8981\u6c42\uff0c\u91cd\u65b0\u52a0\u8f7d\u7ebf\u4e0aH5\u3002" : "\u3002\u7b26\u5408\u6700\u4f4e\u7248\u672c\u8981\u6c42\uff0c\u7ee7\u7eed\u4f7f\u7528\u672c\u5730\u79bb\u7ebf\u5305\u3002");
                                str2 = sb3.toString();
                            }
                            Log.xLogD("ModuleConfigService", str2);
                        }
                        OfflineLoadController.NetConfigCallback netConfigCallback = this.f6084e;
                        if (netConfigCallback != null) {
                            netConfigCallback.onNetworkCallback(ModuleConfigService.this.u(false, offlineModule, null), false, z);
                        }
                        DatabaseExecutors databaseExecutors = DatabaseExecutors.getInstance();
                        final OfflineModule offlineModule2 = this.f6083c;
                        databaseExecutors.runOnIoThread(new Runnable() { // from class: com.jd.libs.hybrid.offlineload.processor.a
                            @Override // java.lang.Runnable
                            public final void run() {
                                ModuleConfigService.AnonymousClass2.this.b(offlineModule2, offlineModule);
                            }
                        });
                        return;
                    }
                    if (Log.isDebug()) {
                        Log.d("ModuleConfigService", "[Offline-file](config) Ignore latest config because its version(" + offlineModule.getModuleCode() + ") <= current's(" + this.d.getModuleVersion() + "), id: " + this.f6083c.getAppid());
                        StringBuilder sb4 = new StringBuilder();
                        sb4.append("\u9879\u76ee(id:");
                        sb4.append(this.f6083c.getAppid());
                        sb4.append(")");
                        Log.xLogD("ModuleConfigService", sb4.toString() + "\u7ebf\u4e0a\u914d\u7f6e\u7248\u672c\u4e0e\u672c\u5730\u7248\u672c\u76f8\u540c\u6216\u66f4\u4f4e(v:" + offlineModule.getModuleCode() + ")\uff0c\u65e0\u9700\u53d8\u52a8\u3002");
                    }
                    OfflineLoadController.NetConfigCallback netConfigCallback2 = this.f6084e;
                    if (netConfigCallback2 != null) {
                        netConfigCallback2.onNetworkCallback(null, false, false);
                    }
                    OfflineModule offlineModule3 = (OfflineModule) OfflineDataStore.getInstance().get(offlineModule.getAppid());
                    OfflineModule removeDownloadModule = OfflineDataStore.getInstance().removeDownloadModule(offlineModule);
                    if (offlineModule.isDownloadDegraded() != offlineModule3.isDownloadDegraded()) {
                        offlineModule3.setDownloadDegraded(offlineModule.isDownloadDegraded());
                        ModuleHelper.saveModule(offlineModule3);
                        if (!offlineModule.isDownloadDegraded() && removeDownloadModule == null) {
                            removeDownloadModule = offlineModule3;
                        }
                    }
                    if (removeDownloadModule != null) {
                        ModuleHelper.resetUrl(removeDownloadModule);
                        if ("1".equals(HybridBase.getInstance().getSetting(HybridSDK.SWITCH_UPDATE_A))) {
                            offlineModule3.downloadFrom = "checkUpdate";
                            ModuleInstallService.installModule(offlineModule3, null);
                            return;
                        }
                        removeDownloadModule.downloadFrom = "checkUpdate-old";
                        ModuleInstallService.installModule(removeDownloadModule, null);
                    }
                } catch (Exception e2) {
                    Log.e("ModuleConfigService", e2);
                    onFail(-3, "Fail to convert config into json: " + e2);
                }
            } catch (Exception e3) {
                Log.e("ModuleConfigService", e3);
                OfflineExceptionUtils.reportConfigError(OfflineExceptionUtils.ERR_MSG_CODE, "requestLatestConfigForSingle-onSuccess", this.f6083c.getAppid(), e3);
            }
        }
    }

    /* loaded from: classes16.dex */
    public static class ChangeInfo<T> {
        public Map<String, T> addList;
        public Map<String, T> deleteList;
        public Map<String, T> downloadList;
        public Map<String, T> updateList;
    }

    public static void deleteAllWithoutBuildIn() {
        f(0);
        OfflineDataStore.getInstance().classifyDownloads(null);
    }

    public static void dispatchDownload(String str) {
        int i2;
        boolean z;
        try {
            if ("S".equals(str)) {
                synchronized (f6078c) {
                    i2 = 0;
                    z = true;
                    if (f6078c.intValue() == 2) {
                        f6078c = 3;
                    } else if (f6078c.intValue() == 0) {
                        f6078c = 1;
                        z = false;
                    }
                }
                if (z) {
                    List<OfflineModule> sLevelListAndRemove = OfflineDataStore.getInstance().getSLevelListAndRemove();
                    StringBuilder sb = new StringBuilder();
                    sb.append("download S level zip, size:");
                    if (sLevelListAndRemove != null) {
                        i2 = sLevelListAndRemove.size();
                    }
                    sb.append(i2);
                    Log.d(sb.toString());
                    if (sLevelListAndRemove == null || sLevelListAndRemove.size() <= 0) {
                        return;
                    }
                    Collections.sort(sLevelListAndRemove);
                    ModuleInstallService.installModulesFromNet(sLevelListAndRemove, null);
                    return;
                }
                return;
            }
            List<OfflineModule> aLevelListAndRemove = OfflineDataStore.getInstance().getALevelListAndRemove(str);
            if (aLevelListAndRemove == null || aLevelListAndRemove.size() <= 0) {
                return;
            }
            Collections.sort(aLevelListAndRemove);
            Log.d("download A level zip, size:" + aLevelListAndRemove.size());
            ModuleInstallService.installModulesFromNet(aLevelListAndRemove, null);
        } catch (Exception e2) {
            Log.e("ModuleConfigService", e2);
            OfflineExceptionUtils.reportConfigError("error", "dispatchDownload-" + str, (String) null, e2);
        }
    }

    private void e(String str, OfflineModule offlineModule, OfflineFiles offlineFiles, OfflineLoadController.NetConfigCallback<OfflineFiles> netConfigCallback, Handler handler, Runnable runnable) {
        HybridBase.getInstance().getConfigById(offlineModule.getAppid(), new AnonymousClass2(handler, runnable, offlineModule, offlineFiles, netConfigCallback, str));
    }

    private static void f(int i2) {
        Map<String, OfflineModule> all = OfflineDataStore.getInstance().getAll();
        if (all == null || all.isEmpty()) {
            return;
        }
        HashMap hashMap = new HashMap();
        for (OfflineModule offlineModule : all.values()) {
            boolean hasBuildIn = offlineModule.hasBuildIn();
            if ((i2 == 0 && !hasBuildIn) || (1 == i2 && hasBuildIn)) {
                hashMap.put(offlineModule.getAppid(), offlineModule);
            }
        }
        ModuleHelper.deleteModules(hashMap);
    }

    private void g(OfflineModule offlineModule, OfflineModule offlineModule2) {
        boolean z;
        OfflineModule removeDownloadModule = OfflineDataStore.getInstance().removeDownloadModule(offlineModule);
        if (removeDownloadModule != null) {
            if (offlineModule2 != null) {
                z = ModuleHelper.hasNewModuleVersion(removeDownloadModule, offlineModule2);
                if (Log.isDebug() && !z) {
                    Log.xLogD("ModuleConfigService", "\u6309\u9700\u89e3\u538b\u7684\u914d\u7f6e\u4e0e\u9700\u4e0b\u8f7d\u5305\u7684\u914d\u7f6e\u7248\u672c\u76f8\u540c\uff0c\u76f4\u63a5\u4f7f\u7528\u76f4\u63a5\u89e3\u538b\u7684\u6587\u4ef6(" + removeDownloadModule.getAppid() + ")\uff0c\u914d\u7f6e", removeDownloadModule);
                }
            } else {
                z = true;
            }
            if (z) {
                if (Log.isDebug()) {
                    Log.xLogD("ModuleConfigService", "\u89e6\u53d1\u6309\u9700\u4e0b\u8f7d(" + removeDownloadModule.getAppid() + ")\uff0c\u914d\u7f6e", removeDownloadModule);
                }
                removeDownloadModule.downloadFrom = "OTF";
                ModuleInstallService.installModule(removeDownloadModule, null);
            }
        }
    }

    private static String h(int i2) {
        return i2 == 1 ? "buildIn" : "net";
    }

    private OfflineModule i(String str, String str2, int i2) {
        OfflineModule byUrl = OfflineDataStore.getInstance().getByUrl(str2, i2);
        if (byUrl != null) {
            if (Log.isDebug()) {
                Log.xLogDForDev("ModuleConfigService", "(\u79bb\u7ebf\u5305)\u627e\u5230\u79bb\u7ebf\u5305\u914d\u7f6e\uff0curl: " + str2);
            }
        } else {
            byUrl = OfflineDataStore.getInstance().getByRegexpUrl(str, i2);
            if (byUrl != null && Log.isDebug()) {
                Log.xLogDForDev("ModuleConfigService", "(\u79bb\u7ebf\u5305)\u627e\u5230\u79bb\u7ebf\u5305\u914d\u7f6e\uff0curl\u6b63\u5219: " + str);
            }
        }
        return byUrl;
    }

    private TestModule j(String str, String str2, int i2) {
        TestModule byUrl = TestOfflineDataStore.getInstance().getByUrl(str2, i2);
        if (byUrl != null) {
            if (Log.isDebug()) {
                Log.xLogDForDev("ModuleConfigService", "\u3010\u6d4b\u8bd5\u5305\u3011\u627e\u5230\u79bb\u7ebf\u5305\u914d\u7f6e\uff0curl: " + str2);
            }
        } else {
            byUrl = TestOfflineDataStore.getInstance().getByRegexpUrl(str, i2);
            if (byUrl != null && Log.isDebug()) {
                Log.xLogDForDev("ModuleConfigService", "\u3010\u6d4b\u8bd5\u5305\u3011\u627e\u5230\u79bb\u7ebf\u5305\u914d\u7f6e\uff0curl\u6b63\u5219: " + str);
            }
        }
        return byUrl;
    }

    private void k(String str, final OfflineModule offlineModule, final OfflineFiles offlineFiles, final OfflineLoadController.ConfigCallback<OfflineFiles> configCallback) {
        final boolean isShared = offlineModule.isShared();
        if (!isShared) {
            t(true);
        }
        final String str2 = isShared ? "[Shared-file]" : "[Offline-file]";
        final String str3 = isShared ? "[\u516c\u5171]\u79bb\u7ebf\u5305" : "\u79bb\u7ebf\u5305";
        DatabaseExecutors.getInstance().runOnIoThread(new Runnable() { // from class: com.jd.libs.hybrid.offlineload.processor.b
            @Override // java.lang.Runnable
            public final void run() {
                ModuleConfigService.this.m(offlineModule, str3, str2, offlineFiles, configCallback, isShared);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: l  reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void m(OfflineModule offlineModule, final String str, final String str2, final OfflineFiles offlineFiles, final OfflineLoadController.ConfigCallback configCallback, final boolean z) {
        ModuleInstallService.installModulesFromBuildIn(Collections.singletonList(offlineModule), new ModuleInstallService.ProcessCallback() { // from class: com.jd.libs.hybrid.offlineload.processor.ModuleConfigService.1
            @Override // com.jd.libs.hybrid.offlineload.processor.ModuleInstallService.ProcessCallback
            public void onProcessFail(OfflineModule offlineModule2) {
                if (Log.isDebug()) {
                    Log.xLogD("ModuleConfigService", "\u79bb\u7ebf\u6587\u4ef6\u4e0d\u53ef\u7528\uff0c\u9879\u76ee(" + offlineModule2.getAppid() + ")\u5185\u7f6e" + str + "\u6309\u9700\u5b9e\u65f6\u5b89\u88c5\u9879\u76ee\u6587\u4ef6\u5931\u8d25\u3002");
                    StringBuilder sb = new StringBuilder();
                    sb.append(str2);
                    sb.append("(match) Installation on the fly fails, id: ");
                    sb.append(offlineModule2.getAppid());
                    Log.d("ModuleConfigService", sb.toString());
                }
                if (z) {
                    return;
                }
                ModuleConfigService.this.t(false);
            }

            @Override // com.jd.libs.hybrid.offlineload.processor.ModuleInstallService.ProcessCallback
            public void onProcessSuccess(OfflineModule offlineModule2) {
                if (Log.isDebug()) {
                    Log.xLogDForDev("ModuleConfigService", "\u9879\u76ee(" + offlineModule2.getAppid() + ")\u5185\u7f6e" + str + "\u6309\u9700\u5b9e\u65f6\u5b89\u88c5\u5b8c\u6bd5\u3002");
                    StringBuilder sb = new StringBuilder();
                    sb.append(str2);
                    sb.append("(match) Available offline files are ready after installation on the fly, id: ");
                    sb.append(offlineModule2.getAppid());
                    Log.d(sb.toString());
                }
                ModuleConfigService.this.u(true, offlineModule2, offlineFiles);
                OfflineLoadController.ConfigCallback configCallback2 = configCallback;
                if (configCallback2 != null) {
                    configCallback2.onFilesAvailable();
                }
                if (z) {
                    return;
                }
                ModuleConfigService.this.t(false);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void n(OfflineFiles offlineFiles, OfflineModule offlineModule, OfflineLoadController.NetConfigCallback netConfigCallback) {
        int fileVersion = offlineFiles.isAvailable() ? offlineFiles.getFileVersion() : -1;
        int minFileVerInt = offlineFiles.getMinFileVerInt();
        boolean z = fileVersion != -1 && fileVersion < minFileVerInt;
        if (Log.isDebug()) {
            StringBuilder sb = new StringBuilder();
            sb.append("[Offline-file](config) Fetching latest config time out. Local file ver = ");
            sb.append(fileVersion);
            sb.append("\uff0cminFileVer = ");
            sb.append(minFileVerInt);
            sb.append(z ? ", need to reload online page" : ", stay in current page");
            Log.e("ModuleConfigService", sb.toString());
            String str = "\u83b7\u53d6\u9879\u76ee(id:" + offlineModule.getAppid() + ")\u7684\u6700\u65b0\u914d\u7f6e\u6570\u636e\u8d85\u65f6\u3002";
            if (fileVersion != -1) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str);
                sb2.append("\u672c\u5730\u6587\u4ef6\u7248\u672c");
                sb2.append(fileVersion);
                sb2.append("\uff0c\u6700\u4f4e\u8981\u6c42\u7248\u672c");
                sb2.append(minFileVerInt);
                sb2.append(z ? "\u3002\u4e0d\u7b26\u5408\u6700\u4f4e\u7248\u672c\u8981\u6c42\uff0c\u91cd\u65b0\u52a0\u8f7d\u7ebf\u4e0aH5\u3002" : "\u3002\u7b26\u5408\u6700\u4f4e\u7248\u672c\u8981\u6c42\uff0c\u7ee7\u7eed\u4f7f\u7528\u672c\u5730\u79bb\u7ebf\u5305\u3002");
                str = sb2.toString();
            }
            Log.xLogD("ModuleConfigService", str);
        }
        if (netConfigCallback != null) {
            netConfigCallback.onNetworkCallback(null, false, z);
        }
    }

    private void o(String str, String str2, String str3) {
        if (Log.isDebug()) {
            Log.xLogD("ModuleConfigService", String.format("(%s\u79bb\u7ebf\u5305)\u672a\u627e\u5230%s\u79bb\u7ebf\u5305\u914d\u7f6e\uff0cURL\uff1a%s\uff0c\u539f\u56e0\uff1a%s", str2, str2, str, str3));
        }
    }

    public static void onFetchListFromBuildIn(Map<String, OfflineModule> map) {
        s(false);
        try {
            ModuleInstallService.installModules(q(1, map).downloadList, 1, false);
            s(true);
        } catch (Exception e2) {
            Log.e("ModuleConfigService", e2);
            OfflineExceptionUtils.reportConfigError("error", "onFetchListFromBuildIn", (String) null, e2);
        }
    }

    public static void onFetchListFromNet(JSONArray jSONArray, String str) {
        HashMap hashMap;
        if (jSONArray != null && jSONArray.length() != 0) {
            hashMap = new HashMap(jSONArray.length());
        } else {
            hashMap = new HashMap();
        }
        for (int i2 = 0; jSONArray != null && i2 < jSONArray.length(); i2++) {
            try {
                OfflineModule fromJson = new OfflineModule().fromJson(jSONArray.getJSONObject(i2));
                hashMap.put(fromJson.getAppid(), fromJson);
            } catch (JSONException e2) {
                Log.e("ModuleConfigService", e2);
                OfflineExceptionUtils.reportConfigError(IExceptionHandler.DynamicExceptionData.TYPE_PARSE, "onFetchListFromNet", (String) null, e2);
            }
        }
        onFetchListFromNet(hashMap, str);
    }

    private static void p(int i2, OfflineModule offlineModule, OfflineModule offlineModule2, Map<String, OfflineModule> map, Map<String, OfflineModule> map2) {
        boolean hasUnzipFileChanged = offlineModule.hasUnzipFileChanged();
        boolean hasNewModuleVersion = ModuleHelper.hasNewModuleVersion(offlineModule2, offlineModule);
        if (!offlineModule.isAvailable() || hasUnzipFileChanged) {
            if (offlineModule.isAvailable()) {
                String appid = offlineModule.getAppid();
                StringBuilder sb = new StringBuilder();
                sb.append("file path=");
                sb.append(offlineModule.getUnzipFile() != null ? offlineModule.getUnzipFile().getPath() : "");
                OfflineExceptionUtils.reportDownloadError(OfflineExceptionUtils.ERR_MSG_CHECK, "processExistedSingle-FileChanged", appid, (String) null, sb.toString());
                OfflineFileHelper.deleteEntityFile(offlineModule);
                map.put(offlineModule.getAppid(), offlineModule);
            }
            if (hasNewModuleVersion) {
                offlineModule2.copyLocalInfoFrom(offlineModule);
                map.put(offlineModule2.getAppid(), offlineModule2);
                offlineModule2.downloadFrom = h(i2) + ":noAvail-newVer";
                map2.put(offlineModule2.getAppid(), offlineModule2);
                if (Log.isDebug()) {
                    Log.d("ModuleConfigService", String.format(Locale.getDefault(), "[Offline-file](Config) Existed config id: %s, save new version but unzip files are unavailable, name: %s, ver: old=%d, new=%d, url: %s", offlineModule2.getAppid(), offlineModule2.getName(), Integer.valueOf(offlineModule.getModuleCode()), Integer.valueOf(offlineModule2.getModuleCode()), offlineModule2.getOriginalUrl()));
                }
            } else if (i2 == 1 && !offlineModule.isBuildInConfig()) {
                if (Log.isDebug()) {
                    Log.d("ModuleConfigService", String.format(Locale.getDefault(), "[Offline-file](Config) Existed config id: %s, same or less version, unzip files are unavailable, name: %s, url: %s", offlineModule2.getAppid(), offlineModule2.getName(), offlineModule2.getOriginalUrl()));
                }
            } else {
                offlineModule2.downloadFrom = h(i2) + ":noAvail-sameVer";
                map2.put(offlineModule.getAppid(), offlineModule);
                if (Log.isDebug()) {
                    Log.d("ModuleConfigService", String.format(Locale.getDefault(), "[Offline-file](Config) Existed config id: %s, same or less version, unzip files are unavailable, will download/unzip it, name: %s, url: %s", offlineModule2.getAppid(), offlineModule2.getName(), offlineModule2.getOriginalUrl()));
                }
            }
        } else if (hasNewModuleVersion) {
            if (!offlineModule.getMinFileVer().equals(offlineModule2.getMinFileVer())) {
                offlineModule.setMinFileVer(offlineModule2.getMinFileVer());
                map.put(offlineModule.getAppid(), offlineModule);
            }
            if (offlineModule.getUnzipFile().getVersionCode() >= offlineModule.getMinFileVerInt()) {
                offlineModule2.copyLocalInfoFrom(offlineModule);
                offlineModule2.copyLocalUnzipFileInfoFrom(offlineModule);
                offlineModule2.copyLocalZipInfoFrom(offlineModule);
                if (offlineModule.getUnzipFile().getVersionCode() != offlineModule2.getFileInfo().getVersionCode()) {
                    offlineModule2.setAvailable(false);
                    offlineModule2.downloadFrom = h(i2) + ":avail-newVer";
                    map2.put(offlineModule2.getAppid(), offlineModule2);
                    if (Log.isDebug()) {
                        Log.d("ModuleConfigService", String.format(Locale.getDefault(), "[Offline-file](Config) Existed config id: %s, found new config(v:%d) and new file-ver(v:%d), will update after file download completed, name: %s, url: %s", offlineModule2.getAppid(), Integer.valueOf(offlineModule2.getModuleCode()), Integer.valueOf(offlineModule2.getFileInfo().getVersionCode()), offlineModule2.getName(), offlineModule2.getOriginalUrl()));
                    }
                } else {
                    map.put(offlineModule2.getAppid(), offlineModule2);
                    if (Log.isDebug()) {
                        Log.d("ModuleConfigService", String.format(Locale.getDefault(), "[Offline-file](Config) Existed config id: %s, update new config(v:%d), file-ver is same, name: %s, url: %s", offlineModule2.getAppid(), Integer.valueOf(offlineModule2.getModuleCode()), offlineModule2.getName(), offlineModule2.getOriginalUrl()));
                    }
                }
            } else {
                OfflineFileHelper.deleteUnzipFile(offlineModule);
                offlineModule2.copyLocalInfoFrom(offlineModule);
                offlineModule2.copyLocalZipInfoFrom(offlineModule);
                map.put(offlineModule2.getAppid(), offlineModule2);
                offlineModule2.downloadFrom = h(i2) + ":avail-newVer-noMin";
                map2.put(offlineModule2.getAppid(), offlineModule2);
                if (Log.isDebug()) {
                    Log.d("ModuleConfigService", String.format(Locale.getDefault(), "[Offline-file](Config) Existed config id: %s, file-ver is less then minFileVer, delete unzip files, name: %s, url: %s", offlineModule2.getAppid(), offlineModule2.getName(), offlineModule2.getOriginalUrl()));
                }
            }
        } else if (Log.isDebug()) {
            Log.d("ModuleConfigService", String.format(Locale.getDefault(), "[Offline-file](Config) Existed config id: %s, is ready, nothing need to be changed, name: %s, url: %s", offlineModule2.getAppid(), offlineModule2.getName(), offlineModule2.getOriginalUrl()));
        }
        if (offlineModule2.isDownloadDegraded() != offlineModule.isDownloadDegraded()) {
            OfflineModule offlineModule3 = map.get(offlineModule.getAppid()) == null ? offlineModule : map.get(offlineModule.getAppid());
            offlineModule3.setDownloadDegraded(offlineModule2.isDownloadDegraded());
            offlineModule.setDownloadDegraded(offlineModule2.isDownloadDegraded());
            map.put(offlineModule.getAppid(), offlineModule3);
        }
    }

    private static ChangeInfo<OfflineModule> q(int i2, Map<String, OfflineModule> map) {
        boolean needCalculatePriority = ModuleHelper.needCalculatePriority();
        ChangeInfo<OfflineModule> changeInfo = new ChangeInfo<>();
        Map removeUseless = IInterfaceCheck.Companion.removeUseless(map);
        if (!removeUseless.isEmpty()) {
            String obj = removeUseless.toString();
            if (Log.isDebug()) {
                Log.e("ModuleConfigService", "[Offline-file](Config) Ignore illegal configs: " + obj);
            }
            OfflineExceptionUtils.reportConfigError(OfflineExceptionUtils.ERR_MSG_NET, "[Offline]\u53bb\u9664\u65e0\u7528\u914d\u7f6e", "", obj);
        }
        Map<String, OfflineModule> all = OfflineDataStore.getInstance().getAll();
        HashMap hashMap = new HashMap();
        int i3 = 1;
        boolean z = map == null || map.isEmpty();
        if (all != null && !all.isEmpty()) {
            HashMap hashMap2 = new HashMap();
            BreakPointHelper init = BreakPointHelper.breakPointSwitch ? BreakPointHelper.getInstance().init(HybridSettings.getAppContext()) : null;
            boolean z2 = false;
            for (OfflineModule offlineModule : all.values()) {
                boolean z3 = !z && map.containsKey(offlineModule.getAppid());
                boolean hasBuildIn = offlineModule.hasBuildIn();
                if ((!z3 && i3 == i2 && hasBuildIn) || !(z3 || i2 != 0 || hasBuildIn) || (GraySwitch.zipNewNoUse && offlineModule.getFileInfo().getUseZip())) {
                    hashMap.put(offlineModule.getAppid(), offlineModule);
                    if (init != null) {
                        init.removeId(offlineModule.getAppid());
                    }
                    if (Log.isDebug()) {
                        Log.d("ModuleConfigService", "[Offline-file](Config) Delete config and local files, because new config list doesn't contain it or it is illegal. id: " + offlineModule.getAppid());
                    }
                } else if (needCalculatePriority) {
                    offlineModule.calculateLpEveryTimeGap();
                    hashMap2.put(offlineModule.getAppid(), offlineModule);
                }
                if (offlineModule.isAvailable() && !z2) {
                    if (!OfflineFileHelper.checkDirHasFiles(OfflineFileHelper.HYBRID_OFFLINE_FILE_DIR)) {
                        OfflineExceptionUtils.reportConfigError(OfflineExceptionUtils.ERR_MSG_CHECK, "checkRootDirExist", (String) null, "local list = " + all);
                    }
                    z2 = true;
                }
                i3 = 1;
            }
            ModuleHelper.saveModules(hashMap2);
        }
        ModuleHelper.deleteModules(hashMap);
        changeInfo.deleteList = hashMap;
        if (z) {
            return changeInfo;
        }
        HashMap hashMap3 = new HashMap();
        HashMap hashMap4 = new HashMap();
        HashMap hashMap5 = new HashMap();
        for (OfflineModule offlineModule2 : map.values()) {
            ModuleHelper.resetUrl(offlineModule2);
            if (i2 == 1) {
                offlineModule2.setBuildInConfig(true);
            }
            OfflineModule offlineModule3 = all != null ? all.get(offlineModule2.getAppid()) : null;
            if (offlineModule3 == null) {
                Log.d("ModuleConfigService", "[Offline-file](Config) Add new config, id: " + offlineModule2.getAppid() + ", name: " + offlineModule2.getName() + ", url: " + offlineModule2.getOriginalUrl());
                offlineModule2.setCreateTime();
                offlineModule2.setAvailable(false);
                offlineModule2.setNewAdded(true);
                hashMap3.put(offlineModule2.getAppid(), offlineModule2);
                offlineModule2.downloadFrom = h(i2) + ":add";
                hashMap5.put(offlineModule2.getAppid(), offlineModule2);
            } else {
                p(i2, offlineModule3, offlineModule2, hashMap4, hashMap5);
            }
        }
        ModuleHelper.saveModules(hashMap3);
        ModuleHelper.saveModules(hashMap4);
        changeInfo.addList = hashMap3;
        changeInfo.updateList = hashMap4;
        changeInfo.downloadList = hashMap5;
        return changeInfo;
    }

    private void r(String str, final OfflineModule offlineModule, final OfflineFiles offlineFiles, final OfflineLoadController.NetConfigCallback<OfflineFiles> netConfigCallback) {
        Handler handler = new Handler(Looper.getMainLooper());
        Runnable runnable = new Runnable() { // from class: com.jd.libs.hybrid.offlineload.processor.c
            @Override // java.lang.Runnable
            public final void run() {
                ModuleConfigService.n(OfflineFiles.this, offlineModule, netConfigCallback);
            }
        };
        Log.d("ModuleConfigService", "[Offline-file](config) Start to fetching latest config, id: " + offlineModule.getAppid() + ", timer: " + HybridSettings.MAX_OFFLINE_FETCH_TIME + "ms.");
        handler.postDelayed(runnable, (long) HybridSettings.MAX_OFFLINE_FETCH_TIME);
        e(str, offlineModule, offlineFiles, netConfigCallback, handler, runnable);
    }

    private static void s(boolean z) {
        d.set(z);
        if (z) {
            try {
                Object obj = f6079e;
                synchronized (obj) {
                    obj.notifyAll();
                }
            } catch (Exception e2) {
                Log.e("ModuleConfigService", e2);
                OfflineExceptionUtils.reportDownloadCodeError("setBuildInFilesUpdated#lock", null, null, e2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t(boolean z) {
        this.a.set(z);
        if (z) {
            return;
        }
        try {
            synchronized (this.b) {
                this.b.notifyAll();
            }
        } catch (Exception e2) {
            Log.e("ModuleConfigService", e2);
            OfflineExceptionUtils.reportDownloadCodeError("setInstallOtfCompleted#lock", null, null, e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public OfflineFiles u(boolean z, Module module, OfflineFiles offlineFiles) {
        OfflineFiles.Builder builder = new OfflineFiles.Builder();
        builder.setAppId(module.getAppid()).setName(module.getName()).setType("4").setMinFileVer(module.getMinFileVer()).setOriginHtmlPath(module.getOriginalUrl()).setRegexpUrl(module.getOriginalUrlType() == 2).setModuleVersion(module.getModuleCode()).setExtendedVersion(module.getExtendedVersion()).setBConfig(module.getBConfig()).setHasBuildIn(module.hasBuildIn()).setBuildInConfig(module.isBuildInConfig());
        OfflineFiles build = offlineFiles == null ? builder.build() : builder.build(offlineFiles);
        if (z) {
            build.setLocalFileInfo(true, module.getUnzipFile().getPath(), module.getLocalFileListJson(), module.getUnzipFile().getVersionCode());
        }
        return build;
    }

    public Module getModuleByUrl(String str, OfflineLoadController.NetConfigCallback<OfflineFiles> netConfigCallback) {
        Module module;
        boolean z;
        boolean z2;
        OfflineModule offlineModule;
        OfflineModule removeDownloadModule;
        String trim = str != null ? str.trim() : null;
        if (Log.isDebug()) {
            Log.xLogD("ModuleConfigService", "\u79bb\u7ebf\u5305\uff1a\u6b63\u5728\u67e5\u627e\u662f\u5426\u5b58\u5728\u79bb\u7ebf\u5305\u914d\u7f6e\uff0cURL\uff1a" + trim);
        }
        try {
        } catch (Exception e2) {
            e = e2;
            module = null;
        }
        if (TextUtils.isEmpty(trim)) {
            if (Log.isDebug()) {
                o(trim, "\u65b0\u7248", "URL\u4e3a\u7a7a");
            }
            netConfigCallback.onCacheCallback(null, false);
            return null;
        }
        int urlVersion = HybridUrlUtils.getUrlVersion(trim);
        String excludeQuery = HybridUrlUtils.excludeQuery(trim);
        module = j(trim, excludeQuery, urlVersion);
        if (module == null) {
            try {
                module = i(trim, excludeQuery, urlVersion);
                z = false;
            } catch (Exception e3) {
                e = e3;
                Log.e("ModuleConfigService", e);
                OfflineExceptionUtils.reportMatchError(OfflineExceptionUtils.ERR_MSG_CODE, "getModuleByUrl", (String) null, trim, e);
                return module;
            }
        } else {
            z = true;
        }
        if (module == null) {
            if (Log.isDebug()) {
                o(trim, "\u65b0\u7248", "\u627e\u4e0d\u5230\u5bf9\u5e94\u914d\u7f6e");
                Log.d("ModuleConfigService", "[Offline-file](match) Can NOT Found config for " + excludeQuery);
            }
            if (netConfigCallback != null) {
                netConfigCallback.onCacheCallback(null, false);
            }
            return null;
        }
        Log.d("ModuleConfigService", "[Offline-file](match) Config(" + module.getAppid() + ") [Found] for " + excludeQuery);
        ModuleHelper.markVisited(module);
        if (z) {
            z2 = false;
        } else {
            z2 = ModuleHelper.hasSameLatestVersion(module);
            if (z2 && Log.isDebug()) {
                Log.xLogD("ModuleConfigService", "\u4ece\u8f85\u52a9\u6570\u636e\u5f97\u77e5\u6b64\u7248\u672c\u5df2\u662f\u6700\u65b0\u7ebf\u4e0a\u7248\u672c(" + module.getModuleCode() + ")\uff0c\u65e0\u9700\u8bf7\u6c42\u63a5\u53e3\u83b7\u53d6\u6700\u65b0\u914d\u7f6e\u3002");
                Log.d("ModuleConfigService", "[Offline-file](config) has same latest version info(" + module.getModuleCode() + "), will not fetch new info.");
            }
        }
        boolean z3 = urlVersion < 0 && module.needCheckLatest() && !z2;
        OfflineFiles u = u(false, module, null);
        if (!z && z3) {
            r(trim, (OfflineModule) module, u, netConfigCallback);
        }
        if (netConfigCallback != null) {
            netConfigCallback.onCacheCallback(u, z3);
        }
        boolean z4 = GraySwitch.fixBuildInPatch;
        if (!z4 && !z3 && (module instanceof OfflineModule) && (removeDownloadModule = OfflineDataStore.getInstance().removeDownloadModule((OfflineModule) module)) != null) {
            removeDownloadModule.downloadFrom = "getModule-old";
            ModuleInstallService.installModule(removeDownloadModule, null);
        }
        if (module.isAvailable()) {
            if (!module.hasUnzipFileChanged()) {
                int versionCode = module.getUnzipFile().getVersionCode();
                if (versionCode >= module.getMinFileVerInt()) {
                    if (Log.isDebug()) {
                        Log.d("[Offline-file](match) Available offline files are ready, id: " + module.getAppid());
                    }
                    u(true, module, u);
                    if (netConfigCallback != null) {
                        netConfigCallback.onFilesAvailable();
                    }
                } else if (Log.isDebug()) {
                    Log.xLogD("ModuleConfigService", "\u79bb\u7ebf\u6587\u4ef6\u4e0d\u53ef\u7528\uff0c\u5185\u7f6e\u5305\u6587\u4ef6\u7248\u672c\u4f4e\u4e8e\u6700\u4f4e\u8981\u6c42\u7248\u672c(\u5f53\u524d: " + versionCode + "\uff0c\u6700\u4f4e\u8981\u6c42: " + module.getMinFileVerInt() + ")");
                    Log.d("ModuleConfigService", "[Offline-file](match) File's version doesn't meet the minimum requirement (current: " + versionCode + ", min: " + module.getMinFileVerInt() + ")");
                }
            } else {
                String path = module.getUnzipFile() != null ? module.getUnzipFile().getPath() : null;
                OfflineExceptionUtils.reportMatchError(OfflineExceptionUtils.ERR_MSG_CHECK, "getModuleByUrl-FileChanged", module.getAppid(), trim, "file path=" + path);
                if (Log.isDebug()) {
                    Log.xLogD("ModuleConfigService", "\u79bb\u7ebf\u6587\u4ef6\u4e0d\u53ef\u7528\uff0c\u672c\u5730\u6587\u4ef6\u6821\u9a8c\u5931\u8d25");
                    Log.e("ModuleConfigService", "[Offline-file](match) Local files have been modified, id: " + module.getAppid());
                }
                OfflineFileHelper.deleteEntityFile(module);
                ModuleHelper.saveModule(module);
            }
        } else if (z) {
            if (Log.isDebug()) {
                Log.xLogD("ModuleConfigService", "\u6d4b\u8bd5\u5305\u7684\u79bb\u7ebf\u6587\u4ef6\u672a\u4e0b\u8f7d\u5b8c\u6210\uff0c\u8bf7\u5728\u6d4b\u8bd5\u9875\u91cd\u65b0\u4e0b\u6216\u5220\u9664\u6d4b\u8bd5\u914d\u7f6e");
                Log.d("ModuleConfigService", "[Test-offline](match) Test offline files are [NOT] ready yet, id: " + module.getAppid());
            }
            return module;
        } else {
            offlineModule = BuildInDataStore.getInstance().get(module.getAppid());
            if (offlineModule == null) {
                if (Log.isDebug()) {
                    Log.xLogD("ModuleConfigService", "\u79bb\u7ebf\u6587\u4ef6\u4e0d\u53ef\u7528\uff0c\u672a\u4e0b\u8f7d\u5b8c\u6210");
                    Log.d("ModuleConfigService", "[Offline-file](match) Offline files are [NOT] ready yet, id: " + module.getAppid());
                }
            } else {
                int versionCode2 = offlineModule.getFileInfo().getVersionCode();
                if (versionCode2 < module.getMinFileVerInt()) {
                    if (Log.isDebug()) {
                        Log.xLogD("ModuleConfigService", "\u79bb\u7ebf\u6587\u4ef6\u4e0d\u53ef\u7528\uff0c\u9879\u76ee\u6587\u4ef6\u672a\u4e0b\u8f7d\u5b8c\u6210\uff0c\u4e14\u5185\u7f6e\u5305\u7248\u672c\u4f4e\u4e8e\u6700\u4f4e\u53ef\u7528\u7248\u672c\uff0c\u4e0d\u80fd\u4f7f\u7528");
                        Log.d("ModuleConfigService", "[Offline-file](match) Offline files are [NOT] ready yet and build-in zip is too old to be used, id: " + module.getAppid());
                    }
                } else {
                    if (Log.isDebug()) {
                        Log.d("ModuleConfigService", "[Offline-file](match) Install from build-in right now, id: " + module.getAppid());
                    }
                    if (module.getFileInfo().getVersionCode() == versionCode2) {
                        offlineModule = (OfflineModule) module;
                    } else {
                        offlineModule.copyLocalInfoFrom(module);
                        offlineModule.copyLocalUnzipFileInfoFrom(module);
                        offlineModule.copyLocalZipInfoFrom(module);
                        offlineModule.setMinFileVer(module.getMinFileVer());
                    }
                    k(excludeQuery, offlineModule, u, netConfigCallback);
                    if (z4 && !z3 && (module instanceof OfflineModule)) {
                        g((OfflineModule) module, offlineModule);
                    }
                    return module;
                }
            }
        }
        offlineModule = null;
        if (z4) {
            g((OfflineModule) module, offlineModule);
        }
        return module;
    }

    public void getSharedModuleByUrl(String str, OfflineLoadController.ConfigCallback<OfflineFiles> configCallback) {
        boolean z;
        OfflineModule offlineModule;
        String trim = str != null ? str.trim() : null;
        if (Log.isDebug()) {
            Log.xLogD("ModuleConfigService", "[\u516c\u5171]\u79bb\u7ebf\u5305\uff1a\u6b63\u5728\u67e5\u627e\u662f\u5426\u5b58\u5728\u516c\u5171\u79bb\u7ebf\u5305\u914d\u7f6e\uff0cURL\uff1a" + trim);
        }
        try {
            if (TextUtils.isEmpty(trim)) {
                o(trim, "[\u516c\u5171]", "URL\u4e3a\u7a7a");
                configCallback.onCacheCallback(null, false);
                return;
            }
            String excludeQuery = HybridUrlUtils.excludeQuery(trim);
            Module sharedByRegexpUrl = TestOfflineDataStore.getInstance().getSharedByRegexpUrl(trim);
            if (sharedByRegexpUrl != null) {
                if (Log.isDebug()) {
                    Log.xLogDForDev("ModuleConfigService", "\u3010\u6d4b\u8bd5\u516c\u5171\u79bb\u7ebf\u5305\u3011\u627e\u5230\u79bb\u7ebf\u5305\u914d\u7f6e\uff0curl\u6b63\u5219: " + trim);
                }
                z = true;
            } else {
                sharedByRegexpUrl = OfflineDataStore.getInstance().getSharedByRegexpUrl(trim);
                if (sharedByRegexpUrl != null && Log.isDebug()) {
                    Log.xLogDForDev("ModuleConfigService", "([\u516c\u5171]\u79bb\u7ebf\u5305)\u627e\u5230\u79bb\u7ebf\u5305\u914d\u7f6e\uff0curl\u6b63\u5219: " + trim);
                }
                z = false;
            }
            if (sharedByRegexpUrl == null) {
                if (Log.isDebug()) {
                    o(trim, "[\u516c\u5171]", "\u627e\u4e0d\u5230\u5bf9\u5e94\u914d\u7f6e");
                    Log.d("ModuleConfigService", "[Shared-file](match) Can NOT Found configs for " + excludeQuery);
                }
                if (configCallback != null) {
                    configCallback.onCacheCallback(null, false);
                    return;
                }
                return;
            }
            Log.d("ModuleConfigService", "[Shared-file](match) Shared Config(" + sharedByRegexpUrl.getAppid() + ") [Found] for " + excludeQuery);
            ModuleHelper.markVisited(sharedByRegexpUrl);
            OfflineFiles u = u(false, sharedByRegexpUrl, null);
            if (configCallback != null) {
                configCallback.onCacheCallback(u, false);
            }
            boolean z2 = GraySwitch.fixBuildInPatch;
            if (!z2 && (sharedByRegexpUrl instanceof OfflineModule)) {
                OfflineModule removeDownloadModule = OfflineDataStore.getInstance().removeDownloadModule((OfflineModule) sharedByRegexpUrl);
                StringBuilder sb = new StringBuilder();
                sb.append("\u516c\u5171\u79bb\u7ebf\u5305\u6309\u9700\u4e0b\u8f7d\uff1a");
                sb.append(removeDownloadModule != null);
                Log.xLogD("ModuleConfigService", sb.toString());
                if (removeDownloadModule != null) {
                    removeDownloadModule.downloadFrom = "getShared-old";
                    ModuleInstallService.installModule(removeDownloadModule, null);
                }
            }
            if (sharedByRegexpUrl.isAvailable()) {
                if (!sharedByRegexpUrl.hasUnzipFileChanged()) {
                    int versionCode = sharedByRegexpUrl.getUnzipFile().getVersionCode();
                    if (versionCode >= sharedByRegexpUrl.getMinFileVerInt()) {
                        if (Log.isDebug()) {
                            Log.d("[Shared-file](match) Available offline files are ready, id: " + sharedByRegexpUrl.getAppid());
                        }
                        u(true, sharedByRegexpUrl, u);
                        if (configCallback != null) {
                            configCallback.onFilesAvailable();
                        }
                    } else if (Log.isDebug()) {
                        Log.xLogD("ModuleConfigService", "[\u516c\u5171]\u79bb\u7ebf\u6587\u4ef6\u4e0d\u53ef\u7528\uff0c\u5185\u7f6e\u5305\u6587\u4ef6\u7248\u672c\u4f4e\u4e8e\u6700\u4f4e\u8981\u6c42\u7248\u672c(\u5f53\u524d: " + versionCode + "\uff0c\u6700\u4f4e\u8981\u6c42: " + sharedByRegexpUrl.getMinFileVerInt() + ")");
                        Log.d("ModuleConfigService", "[Shared-file](match) File's version doesn't meet the minimum requirement (current: " + versionCode + ", min: " + sharedByRegexpUrl.getMinFileVerInt() + ")");
                    }
                } else {
                    String path = sharedByRegexpUrl.getUnzipFile() != null ? sharedByRegexpUrl.getUnzipFile().getPath() : null;
                    OfflineExceptionUtils.reportMatchError(OfflineExceptionUtils.ERR_MSG_CHECK, "getSharedModuleByUrl-FileChanged", sharedByRegexpUrl.getAppid(), trim, "file path=" + path);
                    if (Log.isDebug()) {
                        Log.xLogD("ModuleConfigService", "[\u516c\u5171]\u79bb\u7ebf\u6587\u4ef6\u4e0d\u53ef\u7528\uff0c\u672c\u5730\u6587\u4ef6\u6821\u9a8c\u5931\u8d25");
                        Log.e("ModuleConfigService", "[Shared-file](match) Local files have been modified, id: " + sharedByRegexpUrl.getAppid());
                    }
                    OfflineFileHelper.deleteEntityFile(sharedByRegexpUrl);
                    ModuleHelper.saveModule(sharedByRegexpUrl);
                }
            } else if (z) {
                if (Log.isDebug()) {
                    Log.xLogD("ModuleConfigService", "\u3010\u6d4b\u8bd5\u3011[\u516c\u5171]\u79bb\u7ebf\u5305\u7684\u79bb\u7ebf\u6587\u4ef6\u672a\u4e0b\u8f7d\u5b8c\u6210\uff0c\u8bf7\u5728\u6d4b\u8bd5\u9875\u91cd\u65b0\u4e0b\u6216\u5220\u9664\u6d4b\u8bd5\u914d\u7f6e");
                    Log.d("ModuleConfigService", "[Test-shared](match) Test offline files are [NOT] ready yet, id: " + sharedByRegexpUrl.getAppid());
                    return;
                }
                return;
            } else {
                offlineModule = BuildInDataStore.getInstance().get(sharedByRegexpUrl.getAppid());
                if (offlineModule == null) {
                    if (Log.isDebug()) {
                        Log.xLogD("ModuleConfigService", "[\u516c\u5171]\u79bb\u7ebf\u6587\u4ef6\u4e0d\u53ef\u7528\uff0c\u672a\u4e0b\u8f7d\u5b8c\u6210");
                        Log.d("ModuleConfigService", "[Shared-file](match) Offline files are [NOT] ready yet, id: " + sharedByRegexpUrl.getAppid());
                    }
                } else {
                    int versionCode2 = offlineModule.getFileInfo().getVersionCode();
                    if (versionCode2 < sharedByRegexpUrl.getMinFileVerInt()) {
                        if (Log.isDebug()) {
                            Log.xLogD("ModuleConfigService", "[\u516c\u5171]\u79bb\u7ebf\u6587\u4ef6\u4e0d\u53ef\u7528\uff0c\u9879\u76ee\u6587\u4ef6\u672a\u4e0b\u8f7d\u5b8c\u6210\uff0c\u4e14\u5185\u7f6e\u5305\u7248\u672c\u4f4e\u4e8e\u6700\u4f4e\u53ef\u7528\u7248\u672c\uff0c\u4e0d\u80fd\u4f7f\u7528");
                            Log.d("ModuleConfigService", "[Shared-file](match) Offline files are [NOT] ready yet and build-in zip is too old to be used, id: " + sharedByRegexpUrl.getAppid());
                        }
                    } else {
                        if (Log.isDebug()) {
                            Log.d("ModuleConfigService", "[Shared-file](match) Install from build-in right now, id: " + sharedByRegexpUrl.getAppid());
                        }
                        if (sharedByRegexpUrl.getFileInfo().getVersionCode() == versionCode2) {
                            offlineModule = (OfflineModule) sharedByRegexpUrl;
                        } else {
                            offlineModule.copyLocalInfoFrom(sharedByRegexpUrl);
                            offlineModule.copyLocalUnzipFileInfoFrom(sharedByRegexpUrl);
                            offlineModule.copyLocalZipInfoFrom(sharedByRegexpUrl);
                            offlineModule.setMinFileVer(sharedByRegexpUrl.getMinFileVer());
                        }
                        k(excludeQuery, offlineModule, u, configCallback);
                        if (z2 || !(sharedByRegexpUrl instanceof OfflineModule)) {
                        }
                        g((OfflineModule) sharedByRegexpUrl, offlineModule);
                        return;
                    }
                }
            }
            offlineModule = null;
            if (z2) {
            }
        } catch (Exception e2) {
            Log.e("ModuleConfigService", e2);
            OfflineExceptionUtils.reportMatchError(OfflineExceptionUtils.ERR_MSG_CODE, "getSharedModuleByUrl", (String) null, trim, e2);
        }
    }

    public void onFetchLatestSingleFromNet(OfflineModule offlineModule) {
        try {
            OfflineModule offlineModule2 = (OfflineModule) OfflineDataStore.getInstance().get(offlineModule.getAppid());
            if (offlineModule2 == null) {
                if (Log.isDebug()) {
                    Log.e("ModuleConfigService", "Cannot find local config when updating newer config, id: " + offlineModule.getAppid());
                    return;
                }
                return;
            }
            HashMap hashMap = new HashMap(1);
            HashMap hashMap2 = new HashMap(1);
            p(0, offlineModule2, offlineModule, hashMap, hashMap2);
            if (!hashMap.isEmpty()) {
                ModuleHelper.saveModules(hashMap);
            }
            if (hashMap2.isEmpty()) {
                return;
            }
            OfflineDataStore.getInstance().removeDownloadModule(offlineModule2);
            ModuleInstallService.installModulesFromNet(hashMap2.values(), null);
        } catch (Exception e2) {
            Log.e("ModuleConfigService", e2);
            OfflineExceptionUtils.reportConfigError("error", "onFetchLatestSingleFromNet", (String) null, e2);
        }
    }

    public static void onFetchListFromNet(Map<String, OfflineModule> map, String str) {
        boolean z = false;
        try {
            OfflineMtaUtils.sendFetchConfigMta(0, map, str);
            AtomicBoolean atomicBoolean = d;
            if (!atomicBoolean.get()) {
                try {
                    Object obj = f6079e;
                    synchronized (obj) {
                        if (!atomicBoolean.get()) {
                            Log.d("ModuleConfigService", "[Offline-file](Config) wait for loading buildIn configs before downloading new file.");
                            obj.wait(2000L);
                        }
                    }
                } catch (Exception e2) {
                    Log.e("ModuleConfigService", e2);
                    OfflineExceptionUtils.reportConfigError(JoinPoint.SYNCHRONIZATION_LOCK, "onFetchListFromNet", (String) null, e2);
                }
            }
            ChangeInfo<OfflineModule> q = q(0, map);
            synchronized (f6078c) {
                if (f6078c.intValue() == 1) {
                    f6078c = 3;
                    z = true;
                } else if (f6078c.intValue() == 0) {
                    f6078c = 2;
                }
            }
            OfflineDataStore.getInstance().classifyDownloads(q.downloadList);
            List<OfflineModule> tLevelListAndRemove = OfflineDataStore.getInstance().getTLevelListAndRemove();
            if (tLevelListAndRemove != null && tLevelListAndRemove.size() > 0) {
                Collections.sort(tLevelListAndRemove);
                ModuleInstallService.installModulesFromNet(tLevelListAndRemove, null);
            }
            if (z) {
                dispatchDownload("S");
            }
        } catch (Exception e3) {
            Log.e("ModuleConfigService", e3);
            OfflineExceptionUtils.reportConfigError("error", "onFetchListFromNet", (String) null, e3);
        }
    }
}
