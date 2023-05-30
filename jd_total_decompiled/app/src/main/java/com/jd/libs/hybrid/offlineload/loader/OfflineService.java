package com.jd.libs.hybrid.offlineload.loader;

import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Pair;
import androidx.annotation.NonNull;
import com.jd.dynamic.base.interfaces.IExceptionHandler;
import com.jd.framework.json.JDJSON;
import com.jd.hybrid.downloader.FileError;
import com.jd.hybrid.downloader.FileResponse;
import com.jd.hybrid.downloader.c;
import com.jd.hybrid.downloader.d;
import com.jd.hybrid.downloader.p.a;
import com.jd.libs.hybrid.base.HybridBase;
import com.jd.libs.hybrid.base.HybridSettings;
import com.jd.libs.hybrid.base.engine.ConfigEngine;
import com.jd.libs.hybrid.base.entity.IInterfaceCheck;
import com.jd.libs.hybrid.base.util.CommonUtils;
import com.jd.libs.hybrid.base.util.DatabaseExecutors;
import com.jd.libs.hybrid.base.util.ExceptionUtils;
import com.jd.libs.hybrid.base.util.HybridUrlUtils;
import com.jd.libs.hybrid.base.util.Log;
import com.jd.libs.hybrid.base.util.VersionUtils;
import com.jd.libs.hybrid.offlineload.OfflineLoadController;
import com.jd.libs.hybrid.offlineload.db.BuildInOfflineEntityDao;
import com.jd.libs.hybrid.offlineload.db.OfflineDatabase;
import com.jd.libs.hybrid.offlineload.db.OfflineEntityDao;
import com.jd.libs.hybrid.offlineload.entity.BuildInOfflineEntity;
import com.jd.libs.hybrid.offlineload.entity.FileDetail;
import com.jd.libs.hybrid.offlineload.entity.OfflineEntity;
import com.jd.libs.hybrid.offlineload.entity.OfflineEntityInfo;
import com.jd.libs.hybrid.offlineload.entity.OfflineFiles;
import com.jd.libs.hybrid.offlineload.entity.TestOfflineEntity;
import com.jd.libs.hybrid.offlineload.processor.ModuleConfigService;
import com.jd.libs.hybrid.offlineload.temp.BuildInDisable;
import com.jd.libs.hybrid.offlineload.temp.DownloadFileDisable;
import com.jd.libs.hybrid.offlineload.temp.OfflineSwitchSetting;
import com.jd.libs.hybrid.offlineload.utils.DateUtils;
import com.jd.libs.hybrid.offlineload.utils.FileUtils;
import com.jd.libs.hybrid.offlineload.utils.ModuleHelper;
import com.jd.libs.hybrid.offlineload.utils.OfflineExceptionUtils;
import com.jd.libs.hybrid.offlineload.utils.OfflineFileUtils;
import com.jd.viewkit.templates.view.helper.JDViewKitEventHelper;
import com.tencent.xweb.util.BSpatch;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

@Deprecated
/* loaded from: classes16.dex */
public class OfflineService {

    /* renamed from: e  reason: collision with root package name */
    private static AtomicBoolean f6036e = new AtomicBoolean(false);

    /* renamed from: f  reason: collision with root package name */
    private static ConcurrentHashMap<String, String> f6037f = null;
    private final Context a;
    private final OfflineEntityDao b;

    /* renamed from: c  reason: collision with root package name */
    private final BuildInOfflineEntityDao f6038c;
    private final Object d = new Object();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jd.libs.hybrid.offlineload.loader.OfflineService$5  reason: invalid class name */
    /* loaded from: classes16.dex */
    public class AnonymousClass5 implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ String f6046g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ OfflineLoadController.NetConfigCallback f6047h;

        AnonymousClass5(String str, OfflineLoadController.NetConfigCallback netConfigCallback) {
            this.f6046g = str;
            this.f6047h = netConfigCallback;
        }

        private void a(final String str, OfflineEntity offlineEntity, final OfflineEntity offlineEntity2) {
            HybridBase.getInstance().getConfigById(offlineEntity.getAppid(), new ConfigEngine.Callback<String>() { // from class: com.jd.libs.hybrid.offlineload.loader.OfflineService.5.1
                @Override // com.jd.libs.hybrid.base.engine.ConfigEngine.Callback
                public void onFail(int i2, String str2) {
                    if (i2 == -4) {
                        if (offlineEntity2.isBuildInBiz()) {
                            Log.d("OfflineService", "[Build-in-Offline-file] No hotfix config for " + str + ", stay using offline file.");
                            if (Log.isDebug()) {
                                Log.xLogD("OfflineService", "\u9879\u76ee(id:" + offlineEntity2.getAppid() + ")\u7684\u70ed\u66f4\u65b0\u914d\u7f6e\u5df2\u4e0b\u7ebf");
                            }
                            AnonymousClass5 anonymousClass5 = AnonymousClass5.this;
                            anonymousClass5.f6047h.onNetworkCallback(OfflineService.this.A(true, offlineEntity2), false, false);
                            return;
                        }
                        Log.d("OfflineService", "[Offline-file] Latest config is not longer valid for " + str + ", delete it.");
                        if (Log.isDebug()) {
                            Log.xLogD("OfflineService", "\u9879\u76ee(id:" + offlineEntity2.getAppid() + ")\u5df2\u4e0b\u7ebf");
                        }
                        OfflineService.this.v(offlineEntity2);
                        AnonymousClass5.this.f6047h.onNetworkCallback(null, false, false);
                        return;
                    }
                    Log.e("OfflineService", "[Offline-file] Fail: Fetch latest config failed for " + str + str2);
                    if (Log.isDebug()) {
                        Log.xLogE("OfflineService", "\u83b7\u53d6\u9879\u76ee(id:" + offlineEntity2.getAppid() + ")\u7684\u7ebf\u4e0a\u6700\u65b0\u914d\u7f6e\u6570\u636e\u5931\u8d25\uff0c\u539f\u56e0\uff1a" + str2);
                    }
                    AnonymousClass5 anonymousClass52 = AnonymousClass5.this;
                    anonymousClass52.f6047h.onNetworkCallback(OfflineService.this.A(false, offlineEntity2), true, false);
                    OfflineExceptionUtils.reportMatchError("\u5c0f\u63a5\u53e3\u5931\u8d25", "needCheck-onFail", offlineEntity2.getAppid(), AnonymousClass5.this.f6046g, str2);
                }

                @Override // com.jd.libs.hybrid.base.engine.ConfigEngine.Callback
                public void onSuccess(String str2) {
                    final OfflineEntity offlineEntity3 = (OfflineEntity) JDJSON.parseObject(str2, OfflineEntity.class);
                    if (!offlineEntity3.useful()) {
                        onFail(-3, "response is useless");
                        return;
                    }
                    Log.d("OfflineService", "[Offline-file] Succeed: Fetch latest config for " + str);
                    if (offlineEntity3.isBuildInBiz() && (offlineEntity3 = (OfflineEntity) JDJSON.parseObject(JDJSON.toJSONString(offlineEntity3), BuildInOfflineEntity.class)) == null) {
                        onFail(-3, "entity is null after parsed to build-in-entity.");
                        return;
                    }
                    int versionCode = offlineEntity3.getFileInfo().getVersionCode() - offlineEntity2.getFileInfo().getVersionCode();
                    if (!offlineEntity3.isBuildInBiz() ? versionCode == 0 : versionCode <= 0) {
                        Log.d("OfflineService", "[Offline-file] Latest config's version differs from old config's, will download new file, url: " + str);
                        if (Log.isDebug()) {
                            Log.xLogDForDev("OfflineService", "\u9879\u76ee(id:" + offlineEntity2.getAppid() + ")\u5b58\u5728\u65b0\u7248\u672c\u79bb\u7ebf\u5305\uff0c\u5c06\u4e0b\u8f7d\u65b0\u6587\u4ef6\u3002");
                        }
                        AnonymousClass5 anonymousClass5 = AnonymousClass5.this;
                        anonymousClass5.f6047h.onNetworkCallback(OfflineService.this.A(false, offlineEntity3), false, false);
                        OfflineService.this.onAvailableEntityNewVersion(offlineEntity2, offlineEntity3);
                    } else if (offlineEntity2.isBuildInBiz() && versionCode != 0) {
                        Log.d("OfflineService", "[Build-in-Offline-file] Ignore latest config because version down graded to " + offlineEntity3.getFileInfo().getVersionCode() + ", id: " + offlineEntity3.getAppid());
                        if (Log.isDebug()) {
                            Log.xLogDForDev("OfflineService", "\u9879\u76ee(id:" + offlineEntity2.getAppid() + ")\u83b7\u53d6\u5230\u4f4e\u7248\u672c(" + offlineEntity3.getFileInfo().getVersionCode() + ")\u6570\u636e\uff0c\u5ffd\u7565\u3002");
                        }
                        AnonymousClass5 anonymousClass52 = AnonymousClass5.this;
                        anonymousClass52.f6047h.onNetworkCallback(OfflineService.this.A(true, offlineEntity2), false, false);
                    } else {
                        Log.d("OfflineService", "[Offline-file] Update DB config because version changed in single-entity-api's result, id: " + offlineEntity3.getAppid());
                        OfflineEntity.resetDbUrl(offlineEntity3);
                        offlineEntity3.copyLocalInfoFromOld(offlineEntity2);
                        offlineEntity3.copyLocalFileInfoFromOld(offlineEntity2);
                        AnonymousClass5 anonymousClass53 = AnonymousClass5.this;
                        anonymousClass53.f6047h.onNetworkCallback(OfflineService.this.A(true, offlineEntity3), false, false);
                        DatabaseExecutors.getInstance().threadIO().execute(new Runnable() { // from class: com.jd.libs.hybrid.offlineload.loader.OfflineService.5.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                try {
                                    offlineEntity3.updateDb();
                                } catch (Exception e2) {
                                    Log.e("OfflineService", e2);
                                    OfflineExceptionUtils.reportMatchError(OfflineExceptionUtils.ERR_MSG_CODE, "needCheck-onSuccess-sameVersion", offlineEntity3.getAppid(), AnonymousClass5.this.f6046g, e2);
                                }
                            }
                        });
                    }
                }
            });
        }

        private void b(String str, String str2) {
            if (Log.isDebug()) {
                Log.xLogD("OfflineService", "\u79bb\u7ebf\u5305\uff1a\u672a\u627e\u5230\u79bb\u7ebf\u5305\u914d\u7f6e\uff0cURL\uff1a" + str + "\uff0c\u539f\u56e0\uff1a" + str2);
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:34:0x00d7 A[Catch: Exception -> 0x02f1, TryCatch #0 {Exception -> 0x02f1, blocks: (B:3:0x0003, B:5:0x000c, B:7:0x0012, B:8:0x0019, B:10:0x001f, B:12:0x002f, B:15:0x003f, B:17:0x004b, B:44:0x00ff, B:46:0x0105, B:51:0x0125, B:53:0x0130, B:55:0x014a, B:57:0x0161, B:58:0x0166, B:60:0x0185, B:62:0x0197, B:64:0x01a1, B:66:0x01ab, B:70:0x01b5, B:72:0x01bb, B:73:0x01da, B:75:0x01ff, B:77:0x0213, B:79:0x0219, B:80:0x021e, B:86:0x0253, B:89:0x0265, B:85:0x024f, B:90:0x029a, B:91:0x02c8, B:93:0x02ce, B:94:0x02d5, B:18:0x0067, B:20:0x006d, B:22:0x0077, B:24:0x0083, B:26:0x0087, B:28:0x00a8, B:34:0x00d7, B:36:0x00dd, B:38:0x00e3, B:39:0x00e8, B:41:0x00f3, B:29:0x00c4, B:31:0x00cf), top: B:99:0x0003 }] */
        /* JADX WARN: Removed duplicated region for block: B:42:0x00fc  */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public void run() {
            BuildInOfflineEntity buildInOfflineEntity;
            OfflineEntity offlineEntity;
            try {
                boolean z = false;
                if (TextUtils.isEmpty(this.f6046g)) {
                    if (Log.isDebug()) {
                        b(this.f6046g, "URL\u4e3a\u7a7a");
                    }
                    this.f6047h.onCacheCallback(null, false);
                    return;
                }
                int urlVersion = HybridUrlUtils.getUrlVersion(this.f6046g);
                String excludeQuery = HybridUrlUtils.excludeQuery(this.f6046g);
                if (OfflineSwitchSetting.TYPE_4_OFF || new ModuleConfigService().getModuleByUrl(this.f6046g, this.f6047h) == null) {
                    TestOfflineEntity entityByUrl = TestOfflineService.getInstance().getEntityByUrl(this.f6046g, excludeQuery, urlVersion);
                    if (entityByUrl != null) {
                        Log.d("OfflineService", "[Offline-file] Found a Test offline config, id: " + entityByUrl.getAppid());
                        buildInOfflineEntity = null;
                        offlineEntity = null;
                    } else {
                        if (!HybridSettings.isBuildInOfflineDisable()) {
                            buildInOfflineEntity = OfflineService.this.y(this.f6046g, excludeQuery, urlVersion);
                            if (buildInOfflineEntity != null && BuildInDisable.targetId.equalsIgnoreCase(buildInOfflineEntity.getAppid()) && BuildInDisable.buildInTargetDisable) {
                                Log.d("OfflineService", "[Build-in-offline-file] target build-in id(" + BuildInDisable.targetId + ") is disable by switch.");
                                if (Log.isDebug()) {
                                    Log.xLogDForDev("OfflineService", "\u7279\u5b9a\u5185\u7f6e\u5305(id:" + BuildInDisable.targetId + ")\u5df2\u88ab\u5f00\u5173\u9ed8\u8ba4\u5173\u95ed\u4f7f\u7528");
                                }
                            }
                            if (buildInOfflineEntity == null) {
                                offlineEntity = null;
                            } else if (!HybridSettings.isDownloadedOfflineDisable()) {
                                offlineEntity = OfflineService.this.z(this.f6046g, excludeQuery, urlVersion);
                            } else {
                                if (Log.isDebug()) {
                                    b(excludeQuery, "\u5df2\u5173\u95ed\u540e\u53f0\u914d\u7f6e\u4e0b\u53d1\u7684\u79bb\u7ebf\u5305\u529f\u80fd");
                                }
                                Log.d("OfflineService", "[Offline-file] Downloaded-offline function is disable.");
                                this.f6047h.onCacheCallback(null, false);
                                return;
                            }
                        } else {
                            Log.d("OfflineService", "[Offline-file] Build-in-offline function is disable.");
                            if (Log.isDebug()) {
                                Log.xLogDForDev("OfflineService", "\u5df2\u5173\u95ed\u5185\u7f6e\u79bb\u7ebf\u5305\u529f\u80fd");
                            }
                        }
                        buildInOfflineEntity = null;
                        if (buildInOfflineEntity == null) {
                        }
                    }
                    if (entityByUrl == null) {
                        entityByUrl = buildInOfflineEntity != null ? buildInOfflineEntity : offlineEntity;
                    } else if (Log.isDebug()) {
                        Log.xLogDForDev("OfflineService", "\u627e\u5230\u53ef\u7528\u7684\u6d4b\u8bd5\u79bb\u7ebf\u5305\uff0cid: " + entityByUrl.getAppid());
                    }
                    if (entityByUrl != null) {
                        OfflineService.this.C(entityByUrl);
                        if (entityByUrl.isAvailable()) {
                            Log.d("[Offline-file] Query local offline files [found] for " + excludeQuery);
                            if (entityByUrl.hasUnzipFileChanged()) {
                                OfflineExceptionUtils.reportMatchError(OfflineExceptionUtils.ERR_MSG_CHECK, "getEntityByUrl-FileChanged", entityByUrl.getAppid(), this.f6046g, entityByUrl.getFileRootPath());
                                if (Log.isDebug()) {
                                    b(excludeQuery, "\u672c\u5730\u6587\u4ef6\u6821\u9a8c\u5931\u8d25");
                                }
                                Log.d("OfflineService", "[Offline-file] Local files have been changed for " + excludeQuery);
                                OfflineService.this.v(entityByUrl);
                                this.f6047h.onCacheCallback(null, false);
                                return;
                            }
                            int versionCode = entityByUrl.getFileInfo().getVersionCode();
                            int parseInt = (TextUtils.isEmpty(entityByUrl.getMinFileVer()) || !TextUtils.isDigitsOnly(entityByUrl.getMinFileVer())) ? 0 : Integer.parseInt(entityByUrl.getMinFileVer());
                            if (!entityByUrl.isBuildInBiz() || versionCode >= parseInt) {
                                if (VersionUtils.isAppVersionBetween(OfflineService.this.a, entityByUrl.getAppMin(), entityByUrl.getAppMax())) {
                                    if (-1 == urlVersion) {
                                        z = entityByUrl.needCheckLatest();
                                    }
                                    this.f6047h.onCacheCallback(OfflineService.this.A(true, entityByUrl), z);
                                    if ((entityByUrl instanceof TestOfflineEntity) || !z) {
                                        return;
                                    }
                                    Log.d("OfflineService", "[Offline-file] Try to fetch latest config for " + excludeQuery);
                                    Log.xLogD("OfflineService", "\u9879\u76ee(id:" + entityByUrl.getAppid() + ")\u5f00\u542f\u4e86\u5b9e\u65f6\u6027\u68c0\u67e5\u529f\u80fd\uff0c\u6b63\u5728\u83b7\u53d6\u9879\u76ee\u7ebf\u4e0a\u6700\u65b0\u914d\u7f6e\u6570\u636e...");
                                    a(excludeQuery, entityByUrl, entityByUrl);
                                    return;
                                }
                                if (Log.isDebug()) {
                                    b(excludeQuery, "App\u7248\u672c\u4e0e\u914d\u7f6e\u8981\u6c42\u4e0d\u5339\u914d");
                                }
                                Log.d("OfflineService", "[Offline-file] App's version doesn't meet the offline's requirement: (" + entityByUrl.getAppMin() + "~" + entityByUrl.getAppMax() + ")");
                                this.f6047h.onCacheCallback(null, false);
                                return;
                            }
                            if (Log.isDebug()) {
                                b(excludeQuery, "\u5185\u7f6e\u5305\u6587\u4ef6\u7248\u672c\u4f4e\u4e8e\u6700\u4f4e\u8981\u6c42\u7248\u672c(\u5f53\u524d: " + versionCode + "\uff0c\u6700\u4f4e\u8981\u6c42: " + parseInt + ")");
                            }
                            Log.d("OfflineService", "[Build-in-Offline-file] File's version doesn't meet the minimum requirement (current: " + versionCode + ", min: " + parseInt + ")");
                            this.f6047h.onCacheCallback(null, false);
                            return;
                        }
                        Log.d("OfflineService", "[Offline-file] Local offline files are [NOT available], id: " + entityByUrl.getAppid() + ", url: " + this.f6046g);
                        this.f6047h.onCacheCallback(OfflineService.this.A(false, entityByUrl), false);
                        return;
                    }
                    if (Log.isDebug()) {
                        b(this.f6046g, "\u627e\u4e0d\u5230\u5bf9\u5e94\u914d\u7f6e");
                    }
                    Log.d("OfflineService", "[Offline-file] Config [NOT Found] in DB for " + this.f6046g);
                    this.f6047h.onCacheCallback(null, false);
                }
            } catch (Exception e2) {
                Log.e("OfflineService", e2);
                OfflineExceptionUtils.reportMatchError(OfflineExceptionUtils.ERR_MSG_CODE, "getEntityByUrl", (String) null, this.f6046g, e2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class BuildInDownloadCallback extends com.jd.hybrid.downloader.b {
        private final BuildInOfflineEntity a;
        private final int b;

        /* renamed from: c  reason: collision with root package name */
        private final boolean f6053c;
        private final String d;

        BuildInDownloadCallback(BuildInOfflineEntity buildInOfflineEntity, String str, boolean z, int i2) {
            this.a = buildInOfflineEntity;
            this.b = i2;
            this.f6053c = z;
            this.d = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void e() {
            if (this.f6053c) {
                this.a.setPatchVersionCode(-1);
                Log.xLogDForDev("OfflineService", "\u5373\u5c06\u91cd\u8bd5\u4e0b\u8f7d\uff0cid: " + this.a.getAppid());
                OfflineService.this.w(this.a, 0);
            } else if (this.b < HybridSettings.HYBRID_DOWNLOAD_RETRY) {
                StringBuilder sb = new StringBuilder();
                sb.append("[Build-in-Offline-file] Retry to download. App url: ");
                sb.append(this.a.isValidSSrBiz() ? this.a.getOriginalUrl() : this.a.getDocumentUrl());
                Log.d("OfflineService", sb.toString());
                Log.xLogDForDev("OfflineService", "\u5373\u5c06\u91cd\u8bd5\u4e0b\u8f7d\uff0cid: " + this.a.getAppid());
                OfflineService.this.w(this.a, this.b + 1);
                RetryFailInfo.removeOverRetry(this.a);
            } else {
                RetryFailInfo.addToOverRetry(this.a);
            }
        }

        @Override // com.jd.hybrid.downloader.b, com.jd.hybrid.downloader.e
        public void onEnd(FileResponse<File> fileResponse) {
            final File data = fileResponse.getData();
            com.jd.hybrid.downloader.c.e(data);
            DatabaseExecutors.getInstance().threadIO().execute(new Runnable
            /*  JADX ERROR: Method code generation error
                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0017: INVOKE 
                  (wrap: java.util.concurrent.Executor : 0x000e: INVOKE 
                  (wrap: com.jd.libs.hybrid.base.util.DatabaseExecutors : 0x000a: INVOKE  type: STATIC call: com.jd.libs.hybrid.base.util.DatabaseExecutors.getInstance():com.jd.libs.hybrid.base.util.DatabaseExecutors A[MD:():com.jd.libs.hybrid.base.util.DatabaseExecutors (m), WRAPPED] (LINE:3))
                 type: VIRTUAL call: com.jd.libs.hybrid.base.util.DatabaseExecutors.threadIO():java.util.concurrent.Executor A[MD:():java.util.concurrent.Executor (m), WRAPPED] (LINE:3))
                  (wrap: java.lang.Runnable : 0x0014: CONSTRUCTOR 
                  (r3v0 'this' com.jd.libs.hybrid.offlineload.loader.OfflineService$BuildInDownloadCallback A[IMMUTABLE_TYPE, THIS])
                  (r4v2 'data' java.io.File A[DONT_INLINE])
                  (r0 I:float A[DONT_GENERATE, DONT_INLINE, REMOVE])
                 A[MD:(com.jd.libs.hybrid.offlineload.loader.OfflineService$BuildInDownloadCallback, java.io.File, float):void (m), WRAPPED] call: com.jd.libs.hybrid.offlineload.loader.OfflineService.BuildInDownloadCallback.1.<init>(com.jd.libs.hybrid.offlineload.loader.OfflineService$BuildInDownloadCallback, java.io.File, float):void type: CONSTRUCTOR)
                 type: INTERFACE call: java.util.concurrent.Executor.execute(java.lang.Runnable):void A[MD:(java.lang.Runnable):void (c)] (LINE:3) in method: com.jd.libs.hybrid.offlineload.loader.OfflineService.BuildInDownloadCallback.onEnd(com.jd.hybrid.downloader.FileResponse<java.io.File>):void, file: classes16.dex
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:382)
                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:311)
                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:277)
                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                	at java.base/java.util.ArrayList.forEach(ArrayList.java:1541)
                	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                Caused by: java.lang.NullPointerException
                	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:798)
                	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:718)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:417)
                	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:144)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:120)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1105)
                	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:872)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:421)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:302)
                	... 15 more
                */
            /*
                this = this;
                java.lang.Object r4 = r4.getData()
                java.io.File r4 = (java.io.File) r4
                float r0 = com.jd.hybrid.downloader.c.e(r4)
                com.jd.libs.hybrid.base.util.DatabaseExecutors r1 = com.jd.libs.hybrid.base.util.DatabaseExecutors.getInstance()
                java.util.concurrent.Executor r1 = r1.threadIO()
                com.jd.libs.hybrid.offlineload.loader.OfflineService$BuildInDownloadCallback$1 r2 = new com.jd.libs.hybrid.offlineload.loader.OfflineService$BuildInDownloadCallback$1
                r2.<init>()
                r1.execute(r2)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jd.libs.hybrid.offlineload.loader.OfflineService.BuildInDownloadCallback.onEnd(com.jd.hybrid.downloader.FileResponse):void");
        }

        @Override // com.jd.hybrid.downloader.b, com.jd.hybrid.downloader.e
        public void onError(FileError fileError) {
            String str;
            a.C0087a c0087a = new a.C0087a();
            c0087a.a = this.a.getAppid();
            c0087a.f2700e = this.f6053c;
            c0087a.f2703h = this.d;
            c0087a.f2702g = 1;
            if (fileError instanceof c.C0086c) {
                c0087a.b = ((c.C0086c) fileError).fileSizeInKB;
                c0087a.f2701f = "-2";
                OfflineExceptionUtils.reportDownloadError(OfflineExceptionUtils.ERR_MSG_CHECK, "buildIn-downloadCallback-onError", this.a.getAppid(), this.d, fileError.getMessage());
                str = "\u6587\u4ef6\u6821\u9a8c\u9519\u8bef\uff0c" + fileError.getMessage();
            } else {
                c0087a.f2699c = (this.b != 0 || HybridSettings.HYBRID_DOWNLOAD_RETRY < 2) ? "-1" : "-2";
                c0087a.f2701f = "-2";
                OfflineExceptionUtils.reportDownloadError(fileError.getStatusCode(), "buildIn-downloadCallback-onError", OfflineExceptionUtils.ERR_MSG_NET, this.a.getAppid(), this.d, fileError.getMessage());
                str = "\u7f51\u7edc\u9519\u8bef\uff0c" + fileError.getMessage();
            }
            com.jd.hybrid.downloader.p.a.b(c0087a);
            if (Log.isDebug()) {
                Log.xLogE("OfflineService", "\u9879\u76ee(id:" + this.a.getAppid() + ", url:" + this.a.getShowUrl() + ")\u7684\u79bb\u7ebf\u6587\u4ef6\u4e0b\u8f7d/\u89e3\u538b\u5931\u8d25\uff0c\u539f\u56e0\uff1a" + str);
            }
            e();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class DownloadFileProcessor<T extends OfflineEntity> {
        T a;
        String b;

        /* renamed from: c  reason: collision with root package name */
        File f6058c;
        float d;

        /* renamed from: e  reason: collision with root package name */
        String f6059e;

        /* renamed from: f  reason: collision with root package name */
        String f6060f;

        /* renamed from: g  reason: collision with root package name */
        ProcessCallback<T> f6061g;

        /* renamed from: h  reason: collision with root package name */
        final boolean f6062h;

        /*  JADX ERROR: NullPointerException in pass: MarkMethodsForInline
            java.lang.NullPointerException
            	at jadx.core.dex.instructions.args.RegisterArg.sameRegAndSVar(RegisterArg.java:173)
            	at jadx.core.dex.instructions.args.InsnArg.isSameVar(InsnArg.java:269)
            	at jadx.core.dex.visitors.MarkMethodsForInline.isSyntheticAccessPattern(MarkMethodsForInline.java:118)
            	at jadx.core.dex.visitors.MarkMethodsForInline.inlineMth(MarkMethodsForInline.java:86)
            	at jadx.core.dex.visitors.MarkMethodsForInline.process(MarkMethodsForInline.java:53)
            	at jadx.core.dex.visitors.MarkMethodsForInline.visit(MarkMethodsForInline.java:37)
            */
        static /* synthetic */ com.jd.libs.hybrid.offlineload.loader.OfflineService.DownloadFileProcessor a(com.jd.libs.hybrid.offlineload.loader.OfflineService.DownloadFileProcessor r0, com.jd.libs.hybrid.offlineload.loader.OfflineService.ProcessCallback r1) {
            /*
                r0.h(r1)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jd.libs.hybrid.offlineload.loader.OfflineService.DownloadFileProcessor.a(com.jd.libs.hybrid.offlineload.loader.OfflineService$DownloadFileProcessor, com.jd.libs.hybrid.offlineload.loader.OfflineService$ProcessCallback):com.jd.libs.hybrid.offlineload.loader.OfflineService$DownloadFileProcessor");
        }

        /*  JADX ERROR: NullPointerException in pass: MarkMethodsForInline
            java.lang.NullPointerException
            	at jadx.core.dex.instructions.args.RegisterArg.sameRegAndSVar(RegisterArg.java:173)
            	at jadx.core.dex.instructions.args.InsnArg.isSameVar(InsnArg.java:269)
            	at jadx.core.dex.visitors.MarkMethodsForInline.isSyntheticAccessPattern(MarkMethodsForInline.java:118)
            	at jadx.core.dex.visitors.MarkMethodsForInline.inlineMth(MarkMethodsForInline.java:86)
            	at jadx.core.dex.visitors.MarkMethodsForInline.process(MarkMethodsForInline.java:53)
            	at jadx.core.dex.visitors.MarkMethodsForInline.visit(MarkMethodsForInline.java:37)
            */
        static /* synthetic */ com.jd.libs.hybrid.offlineload.loader.OfflineService.DownloadFileProcessor b(com.jd.libs.hybrid.offlineload.loader.OfflineService.DownloadFileProcessor r0, java.lang.String r1, java.lang.String r2) {
            /*
                r0.i(r1, r2)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jd.libs.hybrid.offlineload.loader.OfflineService.DownloadFileProcessor.b(com.jd.libs.hybrid.offlineload.loader.OfflineService$DownloadFileProcessor, java.lang.String, java.lang.String):com.jd.libs.hybrid.offlineload.loader.OfflineService$DownloadFileProcessor");
        }

        static /* synthetic */ void c(DownloadFileProcessor downloadFileProcessor) {
            downloadFileProcessor.g();
        }

        private boolean d(File file) {
            return new com.jd.hybrid.downloader.n.b(this.a.getFileInfo().getMd5()).a(file);
        }

        private void e(int i2, float f2, String str) {
            if (Log.isDebug()) {
                Log.xLogE("OfflineService", "\u9879\u76ee(id:" + this.a.getAppid() + ", url:" + this.a.getShowUrl() + ")\u7684\u79bb\u7ebf\u6587\u4ef6\u4e0b\u8f7d/\u89e3\u538b\u5931\u8d25\uff0c\u539f\u56e0\uff1a" + str);
            }
            if (this.a.isValidBuildInBiz()) {
                return;
            }
            a.C0087a c0087a = new a.C0087a();
            c0087a.b = f2;
            c0087a.a = this.a.getAppid();
            c0087a.f2699c = "0";
            c0087a.f2700e = this.f6062h;
            c0087a.f2702g = 1;
            c0087a.f2703h = this.f6059e;
            if (i2 == -3) {
                c0087a.f2701f = "-3";
            } else if (i2 == -2) {
                c0087a.f2701f = "-2";
            } else if (i2 == -1) {
                c0087a.f2701f = "-1";
            }
            com.jd.hybrid.downloader.p.a.b(c0087a);
        }

        private File f(boolean[] zArr) {
            Log.d("OfflineService", "[Offline-file] Start to merge patch file, id: " + this.a.getAppid());
            String path = this.a.getZipFile() == null ? "" : this.a.getZipFile().getPath();
            if (TextUtils.isEmpty(path)) {
                Log.e("OfflineService", "[Offline-file] Old zip file(to be merged)'s path is null.");
                e(-3, this.d, "\u5dee\u5206\u5305\u5408\u6210\u65f6\uff0c\u65e7zip\u5305\u7684\u76ee\u5f55\u4e3a\u7a7a");
                OfflineExceptionUtils.reportDownloadError(OfflineExceptionUtils.ERR_MSG_MERGE, "patchZipFile", this.b, this.f6059e, "Old zip file(to be merged)'s path is null.");
                return null;
            } else if (!new File(path).exists()) {
                Log.e("OfflineService", "[Offline-file] Cannot find old zip file to be merged, file path: " + path);
                e(-3, this.d, "\u5dee\u5206\u5305\u5408\u6210\u65f6\uff0c\u627e\u4e0d\u5230\u65e7zip\u5305\uff0cpath=" + path);
                OfflineExceptionUtils.reportDownloadError(OfflineExceptionUtils.ERR_MSG_MERGE, "patchZipFile", this.b, this.f6059e, "Cannot find old zip file to be merged, file path: " + path);
                return null;
            } else {
                String combinePath = OfflineFileUtils.combinePath(this.f6058c.getParent(), this.f6058c.getName() + "_m");
                if (TextUtils.isEmpty(combinePath)) {
                    Log.e("OfflineService", "[Offline-file] Temp dest dir path is null in merging patch.");
                    e(-3, this.d, "\u5dee\u5206\u5305\u5408\u6210\u65f6\uff0c\u4fdd\u5b58\u6574\u5408\u5305\u7684\u76ee\u5f55\u4e3a\u7a7a");
                    OfflineExceptionUtils.reportDownloadError(OfflineExceptionUtils.ERR_MSG_MERGE, "patchZipFile", this.b, this.f6059e, "Temp dest dir path is null in merging patch.");
                    return null;
                }
                try {
                    BSpatch.h(path, this.f6058c.getAbsolutePath(), combinePath);
                    File file = new File(combinePath);
                    if ((this.a.getFileInfo() == null || TextUtils.isEmpty(this.a.getFileInfo().getMd5())) ? false : d(file)) {
                        Log.d("OfflineService", "[Offline-file] Merged patch file successfully, merged zip: " + file.getPath() + ", old zip: " + path + ", id: " + this.a.getAppid());
                        return file;
                    }
                    if (file.exists()) {
                        file.delete();
                    }
                    Log.e("OfflineService", "[Offline-file] File check failed after patch merged. Id: " + this.a.getAppid());
                    e(-2, this.d, "\u5dee\u5206\u5408\u6210\u5305\u6587\u4ef6\u6821\u9a8c\u5931\u8d25");
                    OfflineExceptionUtils.reportDownloadError(OfflineExceptionUtils.ERR_MSG_CHECK, "patchZipFile", this.b, this.f6059e, "\u5dee\u5206\u5408\u6210\u5305\u6587\u4ef6\u6821\u9a8c\u5931\u8d25");
                    zArr[0] = true;
                    return null;
                } catch (Exception e2) {
                    e(-3, this.d, "\u5dee\u5206\u5305\u5408\u6210\u5931\u8d25 : " + e2.getMessage());
                    OfflineExceptionUtils.reportDownloadError(OfflineExceptionUtils.ERR_MSG_MERGE, "patchZipFile", this.b, this.f6059e, e2);
                    Log.e("OfflineService", "[Offline-file] Merge file patch fail. Id: " + this.a.getAppid(), e2);
                    return null;
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Removed duplicated region for block: B:86:0x01fa A[Catch: Exception -> 0x0295, TryCatch #1 {Exception -> 0x0295, blocks: (B:51:0x0128, B:55:0x0145, B:57:0x0151, B:60:0x0172, B:62:0x018e, B:64:0x0192, B:66:0x019d, B:68:0x01b9, B:70:0x01bd, B:72:0x01ca, B:74:0x01d6, B:86:0x01fa, B:88:0x0233, B:90:0x0237, B:92:0x0265, B:77:0x01de, B:79:0x01e4, B:81:0x01f0, B:93:0x026b, B:95:0x0291, B:54:0x013b), top: B:119:0x0128 }] */
        /* JADX WARN: Removed duplicated region for block: B:90:0x0237 A[Catch: Exception -> 0x0295, TryCatch #1 {Exception -> 0x0295, blocks: (B:51:0x0128, B:55:0x0145, B:57:0x0151, B:60:0x0172, B:62:0x018e, B:64:0x0192, B:66:0x019d, B:68:0x01b9, B:70:0x01bd, B:72:0x01ca, B:74:0x01d6, B:86:0x01fa, B:88:0x0233, B:90:0x0237, B:92:0x0265, B:77:0x01de, B:79:0x01e4, B:81:0x01f0, B:93:0x026b, B:95:0x0291, B:54:0x013b), top: B:119:0x0128 }] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public void g() {
            File file;
            boolean equalsIgnoreCase;
            boolean z;
            T t = this.a;
            t.setOldFileRootPath(t.getFileRootPath());
            T t2 = this.a;
            t2.setOldZipFile(t2.getZipFile());
            if (TextUtils.isEmpty(this.f6060f)) {
                e(-1, this.d, "\u83b7\u53d6\u89e3\u538b\u8def\u5f84\u4e3a\u7a7a");
                OfflineExceptionUtils.reportDownloadError(OfflineExceptionUtils.ERR_MSG_UNZIP, "processDownloadFile", this.b, this.f6059e, "\u83b7\u53d6\u89e3\u538b\u8def\u5f84\u4e3a\u7a7a");
                Log.e("OfflineService", "[Offline-file] unzip dest path is null/empty, will not try again until app next startup.");
                File file2 = this.f6058c;
                if (file2 != null && file2.exists()) {
                    this.f6058c.delete();
                }
                ProcessCallback<T> processCallback = this.f6061g;
                if (processCallback != null) {
                    processCallback.onProcessFail(false, false, null);
                    return;
                }
                return;
            }
            T t3 = this.a;
            if (t3 != null && t3.getFileInfo() != null && (file = this.f6058c) != null && file.exists()) {
                if (!"zip".equalsIgnoreCase(this.a.getFileInfo().getFileType()) && !OfflineEntityInfo.FILE_TYPE_ZIP2.equalsIgnoreCase(this.a.getFileInfo().getFileType())) {
                    e(-1, this.d, "\u4e0b\u8f7d\u6587\u4ef6\u7684\u914d\u7f6e\u4e0d\u5408\u6cd5\uff0c\u4e0d\u662f\u4e00\u4e2a\u7ea6\u5b9a\u597d\u7684\u7c7b\u578b");
                    OfflineExceptionUtils.reportDownloadError(OfflineExceptionUtils.ERR_MSG_UNZIP, "processDownloadFile", this.b, this.f6059e, "\u4e0d\u662fzip\u62162zip");
                    Log.e("OfflineService", "[Offline-file] Save fail. File type is not legal(zip/2zip), delete it.");
                    this.f6058c.delete();
                    ProcessCallback<T> processCallback2 = this.f6061g;
                    if (processCallback2 != null) {
                        processCallback2.onProcessFail(true, false, null);
                        return;
                    }
                    return;
                }
                File file3 = this.f6058c;
                boolean[] zArr = {false};
                if (this.f6062h) {
                    file3 = f(zArr);
                    this.f6058c.delete();
                    if (file3 == null) {
                        ProcessCallback<T> processCallback3 = this.f6061g;
                        if (processCallback3 != null) {
                            processCallback3.onProcessFail(true, zArr[0], null);
                            return;
                        }
                        return;
                    }
                    equalsIgnoreCase = false;
                } else {
                    equalsIgnoreCase = OfflineEntityInfo.FILE_TYPE_ZIP2.equalsIgnoreCase(this.a.getFileInfo().getFileType());
                }
                if (equalsIgnoreCase && (file3 = l(zArr)) == null) {
                    if (this.f6058c.exists()) {
                        this.f6058c.delete();
                    }
                    ProcessCallback<T> processCallback4 = this.f6061g;
                    if (processCallback4 != null) {
                        processCallback4.onProcessFail(true, zArr[0], null);
                        return;
                    }
                    return;
                }
                Log.d("OfflineService", "[Offline-file] Start to unzip, zip file at " + file3.getAbsolutePath() + ", extract to " + this.f6060f + ", id: " + this.a.getAppid());
                try {
                    if (j(file3.getAbsolutePath(), this.f6060f, this.a.getFileInfo().getFileType().equalsIgnoreCase(OfflineEntityInfo.FILE_TYPE_ZIP2) ? "" : this.a.getFileInfo().getPassword())) {
                        String combinePath = OfflineFileUtils.combinePath(this.f6060f, this.a.getDocumentDir());
                        String combinePath2 = OfflineFileUtils.combinePath(this.f6060f, this.a.getSourceDir());
                        if (TextUtils.isEmpty(combinePath)) {
                            e(-2, this.d, "\u83b7\u53d6\u79bb\u7ebf\u5305html\u6587\u4ef6\u8def\u5f84\u4e3a\u7a7a");
                            OfflineExceptionUtils.reportDownloadError(OfflineExceptionUtils.ERR_MSG_CHECK, "processDownloadFile", this.b, this.f6059e, "\u83b7\u53d6\u672c\u5730html\u6587\u4ef6\u8def\u5f84\u4e3a\u7a7a");
                            Log.e("OfflineService", "[Offline-file] local html file path is null/empty.");
                            file3.delete();
                            ProcessCallback<T> processCallback5 = this.f6061g;
                            if (processCallback5 != null) {
                                processCallback5.onProcessFail(true, false, null);
                                return;
                            }
                            return;
                        }
                        File file4 = new File(combinePath);
                        if (TextUtils.isEmpty(combinePath2)) {
                            e(-2, this.d, "\u83b7\u53d6\u79bb\u7ebf\u5305\u9759\u6001\u8d44\u6e90\u8def\u5f84\u4e3a\u7a7a");
                            OfflineExceptionUtils.reportDownloadError(OfflineExceptionUtils.ERR_MSG_CHECK, "processDownloadFile", this.b, this.f6059e, "\u83b7\u53d6\u672c\u5730\u9759\u6001\u8d44\u6e90\u8def\u5f84\u4e3a\u7a7a");
                            Log.e("OfflineService", "[Offline-file] local static file path is null/empty.");
                            file3.delete();
                            ProcessCallback<T> processCallback6 = this.f6061g;
                            if (processCallback6 != null) {
                                processCallback6.onProcessFail(true, false, null);
                                return;
                            }
                            return;
                        }
                        File file5 = new File(combinePath2);
                        if (!this.a.isValidSSrBiz() ? !file4.exists() || (!TextUtils.isEmpty(this.a.getSourceDir()) && !file5.exists()) : !TextUtils.isEmpty(this.a.getSourceDir()) && !file5.exists()) {
                            z = false;
                            if (z) {
                                e(-2, this.d, "\u79bb\u7ebf\u5305\u5185\u7f3a\u5c11\u5fc5\u8981\u6587\u4ef6");
                                OfflineExceptionUtils.reportDownloadError(OfflineExceptionUtils.ERR_MSG_CHECK, "processDownloadFile", this.b, this.f6059e, "\u7f3a\u5c11\u5fc5\u8981\u6587\u4ef6");
                                Log.e("OfflineService", "[Offline-file] Download error: missing files. Zip: " + file3.getAbsolutePath() + ", unzip path: " + this.f6060f);
                                file3.delete();
                                ProcessCallback<T> processCallback7 = this.f6061g;
                                if (processCallback7 != null) {
                                    processCallback7.onProcessFail(true, false, null);
                                    return;
                                }
                                return;
                            }
                            this.a.setAvailable(true);
                            this.a.setFileRootPath(this.f6060f);
                            this.a.setZipFile(new FileDetail(file3));
                            this.a.setDocumentFile(new FileDetail(file4));
                            this.a.setSourceFile(new FileDetail(file5));
                            ProcessCallback<T> processCallback8 = this.f6061g;
                            if (processCallback8 != null) {
                                processCallback8.onProcessSuccess(this.a);
                                return;
                            }
                            return;
                        }
                        z = true;
                        if (z) {
                        }
                    } else {
                        e(-1, this.d, OfflineExceptionUtils.ERR_MSG_UNZIP);
                        Log.e("OfflineService", "[Offline-file] Unzip fail. Id: " + this.a.getAppid());
                        file3.delete();
                        ProcessCallback<T> processCallback9 = this.f6061g;
                        if (processCallback9 != null) {
                            processCallback9.onProcessFail(true, false, null);
                        }
                    }
                } catch (Exception e2) {
                    e(-1, this.d, "\u5185\u90e8\u9519\u8bef\uff0c" + e2.getMessage());
                    OfflineExceptionUtils.reportDownloadCodeError("processDownloadFile", this.b, this.f6059e, e2);
                    Log.e("OfflineService", e2.getMessage());
                    if (file3.exists()) {
                        try {
                            file3.delete();
                        } catch (Exception e3) {
                            Log.e("OfflineService", e3);
                        }
                    }
                    ProcessCallback<T> processCallback10 = this.f6061g;
                    if (processCallback10 != null) {
                        processCallback10.onProcessFail(true, false, e2);
                    }
                }
            } else {
                Log.e("OfflineService", "[Offline-file] unzip fail because information needed is null.");
                e(-1, this.d, "\u914d\u7f6e\u4fe1\u606f\u4e3a\u7a7a\uff0c\u6216\u4e0b\u8f7d\u540e\u6587\u4ef6\u4e0d\u5b58\u5728");
                OfflineExceptionUtils.reportDownloadError(OfflineExceptionUtils.ERR_MSG_UNZIP, "processDownloadFile", this.b, this.f6059e, "\u914d\u7f6e\u4fe1\u606f\u4e3a\u7a7a\uff0c\u6216\u4e0b\u8f7d\u540e\u6587\u4ef6\u4e0d\u5b58\u5728");
                File file6 = this.f6058c;
                if (file6 != null && file6.exists()) {
                    this.f6058c.delete();
                }
                ProcessCallback<T> processCallback11 = this.f6061g;
                if (processCallback11 != null) {
                    processCallback11.onProcessFail(true, false, null);
                }
            }
        }

        private DownloadFileProcessor<T> h(ProcessCallback<T> processCallback) {
            this.f6061g = processCallback;
            return this;
        }

        private DownloadFileProcessor<T> i(String str, String str2) {
            this.f6060f = str;
            return this;
        }

        private boolean j(String str, String str2, String str3) {
            try {
                k.a.a.a aVar = new k.a.a.a(str);
                if (aVar.f()) {
                    if (TextUtils.isEmpty(str3)) {
                        Log.e("OfflineService", "[Offline-file] zip is encrypted, but password is empty.");
                        OfflineExceptionUtils.reportDownloadError(OfflineExceptionUtils.ERR_MSG_UNZIP, "unZipToFile", this.b, this.f6059e, "zip\u5df2\u52a0\u5bc6\uff0c\u4f46\u914d\u7f6e\u4e0b\u53d1\u7684\u5bc6\u7801\u4e3a\u7a7a");
                        return false;
                    }
                    aVar.h(str3.toCharArray());
                }
                aVar.c(str2);
                return true;
            } catch (k.a.a.c.a e2) {
                Log.e("OfflineService", e2.getMessage());
                OfflineExceptionUtils.reportDownloadError(OfflineExceptionUtils.ERR_MSG_UNZIP, "unZipToFile", this.b, this.f6059e, e2);
                return false;
            }
        }

        private Pair<Boolean, Object> k(String str, String str2) {
            File[] listFiles;
            try {
                if (TextUtils.isEmpty(str)) {
                    return new Pair<>(Boolean.FALSE, "\u8981\u89e3\u538b\u7684zip\u6587\u4ef6path\u4e3a\u7a7a");
                }
                k.a.a.a aVar = new k.a.a.a(str);
                if (aVar.f()) {
                    if (TextUtils.isEmpty(str2)) {
                        Log.e("OfflineService", "[Offline-file] zip is encrypted, but password is empty.");
                        return new Pair<>(Boolean.FALSE, "zip\u5df2\u52a0\u5bc6\uff0c\u4f46\u914d\u7f6e\u4e0b\u53d1\u7684\u5bc6\u7801\u4e3a\u7a7a");
                    }
                    aVar.h(str2.toCharArray());
                }
                String str3 = str + "_temp";
                aVar.c(str3);
                File file = new File(str3);
                if (file.exists() && file.isDirectory() && (listFiles = file.listFiles()) != null && listFiles.length > 0) {
                    aVar.d().delete();
                    File file2 = new File(str);
                    boolean z = false;
                    if (listFiles[0].isFile() && listFiles[0].renameTo(file2)) {
                        z = true;
                    }
                    FileUtils.deleteFile(file);
                    if (z) {
                        return new Pair<>(Boolean.TRUE, null);
                    }
                    return new Pair<>(Boolean.FALSE, "\u79fb\u52a8\u89e3\u538b\u540e\u7684zip\u6587\u4ef6\u5931\u8d25");
                }
                FileUtils.deleteFile(file);
                return new Pair<>(Boolean.FALSE, "\u89e3\u538b\u540e\u7684zip\u6587\u4ef6\u4e0d\u5b58\u5728");
            } catch (Exception e2) {
                Log.e("OfflineService", e2.getMessage());
                return new Pair<>(Boolean.FALSE, e2);
            }
        }

        private File l(boolean[] zArr) {
            Log.d("OfflineService", "[Offline-file] Start to first unzip, file at " + this.f6058c.getAbsolutePath() + ", id: " + this.a.getAppid());
            String absolutePath = this.f6058c.getAbsolutePath();
            Pair<Boolean, Object> k2 = k(absolutePath, this.a.getFileInfo().getPassword());
            if (((Boolean) k2.first).booleanValue()) {
                File file = new File(absolutePath);
                if (d(file)) {
                    return file;
                }
                Log.e("OfflineService", "[Offline-file] File check failed after first unzip. Id: " + this.a.getAppid());
                e(-2, this.d, "\u539f\u59cb\u5305\u6587\u4ef6\u6821\u9a8c\u5931\u8d25");
                OfflineExceptionUtils.reportDownloadError(OfflineExceptionUtils.ERR_MSG_CHECK, "unzipFirstTime", this.b, this.f6059e, "\u539f\u59cb\u5305\u6587\u4ef6\u6821\u9a8c\u5931\u8d25");
                zArr[0] = true;
                return null;
            }
            e(-1, this.d, OfflineExceptionUtils.ERR_MSG_UNZIP);
            Object obj = k2.second;
            if (obj instanceof Exception) {
                OfflineExceptionUtils.reportDownloadError(OfflineExceptionUtils.ERR_MSG_UNZIP, "unzipFirstTime", this.b, this.f6059e, (Exception) obj);
            } else {
                OfflineExceptionUtils.reportDownloadError(OfflineExceptionUtils.ERR_MSG_UNZIP, "unzipFirstTime", this.b, this.f6059e, (String) obj);
            }
            Log.e("OfflineService", "[Offline-file] Unzip fail. Id: " + this.a.getAppid());
            return null;
        }

        private DownloadFileProcessor(OfflineService offlineService, T t, File file, String str, boolean z, float f2) {
            this.a = t;
            this.b = t.getAppid();
            this.f6058c = file;
            this.f6059e = str;
            this.d = f2;
            this.f6062h = z;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class OfflineDownloadCallback extends com.jd.hybrid.downloader.b {
        private final OfflineEntity a;
        private final int b;

        /* renamed from: c  reason: collision with root package name */
        private final boolean f6063c;
        private final String d;

        /* renamed from: e  reason: collision with root package name */
        private long f6064e;

        OfflineDownloadCallback(OfflineEntity offlineEntity, String str, boolean z, int i2) {
            this.a = offlineEntity;
            this.b = i2;
            this.f6063c = z;
            this.d = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void f() {
            if (this.f6063c) {
                this.a.setPatchVersionCode(-1);
                Log.xLogDForDev("OfflineService", "\u5373\u5c06\u91cd\u8bd5\u4e0b\u8f7d\uff0cid: " + this.a.getAppid());
                OfflineService.this.w(this.a, 0);
            } else if (this.b < HybridSettings.HYBRID_DOWNLOAD_RETRY) {
                StringBuilder sb = new StringBuilder();
                sb.append("[Offline-file] Retry to download. App url: ");
                sb.append(this.a.isValidSSrBiz() ? this.a.getOriginalUrl() : this.a.getDocumentUrl());
                Log.d("OfflineService", sb.toString());
                Log.xLogDForDev("OfflineService", "\u5373\u5c06\u91cd\u8bd5\u4e0b\u8f7d\uff0cid: " + this.a.getAppid());
                OfflineService.this.w(this.a, this.b + 1);
                RetryFailInfo.removeOverRetry(this.a);
            } else {
                RetryFailInfo.addToOverRetry(this.a);
            }
        }

        @Override // com.jd.hybrid.downloader.b, com.jd.hybrid.downloader.e
        public void onEnd(FileResponse<File> fileResponse) {
            final File data = fileResponse.getData();
            com.jd.hybrid.downloader.c.e(data);
            DatabaseExecutors.getInstance().threadIO().execute(new Runnable
            /*  JADX ERROR: Method code generation error
                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0017: INVOKE 
                  (wrap: java.util.concurrent.Executor : 0x000e: INVOKE 
                  (wrap: com.jd.libs.hybrid.base.util.DatabaseExecutors : 0x000a: INVOKE  type: STATIC call: com.jd.libs.hybrid.base.util.DatabaseExecutors.getInstance():com.jd.libs.hybrid.base.util.DatabaseExecutors A[MD:():com.jd.libs.hybrid.base.util.DatabaseExecutors (m), WRAPPED] (LINE:3))
                 type: VIRTUAL call: com.jd.libs.hybrid.base.util.DatabaseExecutors.threadIO():java.util.concurrent.Executor A[MD:():java.util.concurrent.Executor (m), WRAPPED] (LINE:3))
                  (wrap: java.lang.Runnable : 0x0014: CONSTRUCTOR 
                  (r3v0 'this' com.jd.libs.hybrid.offlineload.loader.OfflineService$OfflineDownloadCallback A[IMMUTABLE_TYPE, THIS])
                  (r0 I:float A[DONT_GENERATE, DONT_INLINE, REMOVE])
                  (r4v2 'data' java.io.File A[DONT_INLINE])
                 A[MD:(com.jd.libs.hybrid.offlineload.loader.OfflineService$OfflineDownloadCallback, float, java.io.File):void (m), WRAPPED] call: com.jd.libs.hybrid.offlineload.loader.OfflineService.OfflineDownloadCallback.1.<init>(com.jd.libs.hybrid.offlineload.loader.OfflineService$OfflineDownloadCallback, float, java.io.File):void type: CONSTRUCTOR)
                 type: INTERFACE call: java.util.concurrent.Executor.execute(java.lang.Runnable):void A[MD:(java.lang.Runnable):void (c)] (LINE:3) in method: com.jd.libs.hybrid.offlineload.loader.OfflineService.OfflineDownloadCallback.onEnd(com.jd.hybrid.downloader.FileResponse<java.io.File>):void, file: classes16.dex
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:382)
                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:311)
                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:277)
                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                	at java.base/java.util.ArrayList.forEach(ArrayList.java:1541)
                	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                Caused by: java.lang.NullPointerException
                	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:798)
                	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:718)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:417)
                	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:144)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:120)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1105)
                	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:872)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:421)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:302)
                	... 15 more
                */
            /*
                this = this;
                java.lang.Object r4 = r4.getData()
                java.io.File r4 = (java.io.File) r4
                float r0 = com.jd.hybrid.downloader.c.e(r4)
                com.jd.libs.hybrid.base.util.DatabaseExecutors r1 = com.jd.libs.hybrid.base.util.DatabaseExecutors.getInstance()
                java.util.concurrent.Executor r1 = r1.threadIO()
                com.jd.libs.hybrid.offlineload.loader.OfflineService$OfflineDownloadCallback$1 r2 = new com.jd.libs.hybrid.offlineload.loader.OfflineService$OfflineDownloadCallback$1
                r2.<init>()
                r1.execute(r2)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jd.libs.hybrid.offlineload.loader.OfflineService.OfflineDownloadCallback.onEnd(com.jd.hybrid.downloader.FileResponse):void");
        }

        @Override // com.jd.hybrid.downloader.b, com.jd.hybrid.downloader.e
        public void onError(FileError fileError) {
            String str;
            a.C0087a c0087a = new a.C0087a();
            c0087a.a = this.a.getAppid();
            c0087a.f2702g = 1;
            c0087a.f2703h = this.d;
            c0087a.f2700e = this.f6063c;
            if (fileError instanceof c.C0086c) {
                c0087a.b = ((c.C0086c) fileError).fileSizeInKB;
                c0087a.f2701f = "-2";
                OfflineExceptionUtils.reportDownloadError(OfflineExceptionUtils.ERR_MSG_CHECK, "downloadCallback-onError", this.a.getAppid(), this.d, fileError.getMessage());
                str = "\u6587\u4ef6\u6821\u9a8c\u9519\u8bef\uff0c" + fileError.getMessage();
            } else {
                c0087a.f2699c = (this.b != 0 || HybridSettings.HYBRID_DOWNLOAD_RETRY < 2) ? "-1" : "-2";
                c0087a.f2701f = "";
                OfflineExceptionUtils.reportDownloadError(fileError.getStatusCode(), "downloadCallback-onError", OfflineExceptionUtils.ERR_MSG_NET, this.a.getAppid(), this.d, fileError.getMessage());
                str = "\u7f51\u7edc\u9519\u8bef\uff0c" + fileError.getMessage();
            }
            com.jd.hybrid.downloader.p.a.b(c0087a);
            if (Log.isDebug()) {
                Log.xLogE("OfflineService", "\u9879\u76ee(id:" + this.a.getAppid() + ", url:" + this.a.getShowUrl() + ")\u7684\u79bb\u7ebf\u6587\u4ef6\u4e0b\u8f7d/\u89e3\u538b\u5931\u8d25\uff0c\u539f\u56e0\uff1a" + str);
            }
            if (this.f6063c) {
                this.a.setZipFile(null);
                OfflineFileUtils.deleteZipFile(this.a);
                OfflineService.this.b.update(this.a);
            }
            f();
        }

        @Override // com.jd.hybrid.downloader.b, com.jd.hybrid.downloader.e
        public void onStart() {
            super.onStart();
            this.f6064e = SystemClock.elapsedRealtime();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public interface ProcessCallback<T> {
        void onProcessFail(boolean z, boolean z2, Throwable th);

        void onProcessSuccess(T t);
    }

    public OfflineService(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.a = applicationContext;
        this.b = OfflineDatabase.getInstance(applicationContext).getDao();
        this.f6038c = OfflineDatabase.getInstance(applicationContext).getBuildInDao();
        new OfflineEntityLoader();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public OfflineFiles A(boolean z, OfflineEntity offlineEntity) {
        OfflineFiles.Builder builder = new OfflineFiles.Builder();
        builder.setAvailable(z).setAppId(offlineEntity.getAppid()).setType(offlineEntity.getType()).setMinFileVer(offlineEntity.getMinFileVer()).setCanPreloadHtml(offlineEntity.getHtmlPreload() == 1).setHtmlPath(offlineEntity.getDocumentUrl()).setOriginHtmlPath(offlineEntity.getOriginalUrl()).setStaticPath(offlineEntity.getSourceRoot()).setModuleVersion(offlineEntity.getModuleCode()).setFileVersion(offlineEntity.getFileInfo().getVersionCode()).setCanPassGentoken(offlineEntity.getUngentoken() == 1).setBConfig(offlineEntity.getBConfig());
        if (z) {
            builder.setFileRootPath(offlineEntity.getFileRootPath()).setHtmlFile(offlineEntity.getDocumentFile().getPath()).setStaticDir(offlineEntity.getSourceFile().getPath());
        }
        return builder.build();
    }

    private OfflineEntity B(List<OfflineEntity> list, String str) {
        if (list != null && list.size() != 0) {
            for (OfflineEntity offlineEntity : list) {
                if (offlineEntity != null && offlineEntity.isRegexpMatch() && HybridUrlUtils.isRegexpMatched(offlineEntity.getOriginalUrl(), str)) {
                    Log.d("OfflineService", "[Offline-file] Available local ssr offline file for " + str);
                    return offlineEntity;
                }
            }
            return null;
        }
        Log.d("OfflineService", "[Offline-file] Available local ssr offline files [NOT Found] for " + str);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void C(OfflineEntity offlineEntity) {
        if (offlineEntity == null) {
            return;
        }
        offlineEntity.markVisited();
        offlineEntity.tryIncreaseLpWhenVisited();
        offlineEntity.updateDb();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public OfflineLoadController.ChangeEntityLists<BuildInOfflineEntity> D(List<BuildInOfflineEntity> list) {
        List removeUseless = IInterfaceCheck.Companion.removeUseless(list);
        if (removeUseless != null && !removeUseless.isEmpty()) {
            Log.d("OfflineService", "[Build-in-Offline-file] Remove illegal build-in config, list: " + removeUseless);
        }
        Log.d("OfflineService", "[Build-in-Offline-file] found hotfix build-in offline config list: " + list);
        List<BuildInOfflineEntity> all = this.f6038c.getAll();
        int size = all.size();
        OfflineLoadController.ChangeEntityLists<BuildInOfflineEntity> H = H(false, all, list, removeUseless);
        OfflineLoadController.ConfigMtaData configMtaData = new OfflineLoadController.ConfigMtaData();
        configMtaData.serverAll = list.size();
        configMtaData.oldLocalAll = size;
        H.configMtaData = configMtaData;
        return H;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public OfflineLoadController.ChangeEntityLists<OfflineEntity> E(@NonNull List<OfflineEntity> list) {
        List<OfflineEntity> all = this.b.getAll();
        int size = all.size();
        OfflineLoadController.ChangeEntityLists<OfflineEntity> I = I(all, list, IInterfaceCheck.Companion.removeUseless(list));
        List<OfflineEntity> list2 = I.deleteList;
        if (list2 != null && !list2.isEmpty()) {
            this.b.delete(I.deleteList);
        }
        List<OfflineEntity> list3 = I.addList;
        if (list3 != null && !list3.isEmpty()) {
            this.b.save(I.addList);
        }
        List<OfflineEntity> list4 = I.updateList;
        if (list4 != null && !list4.isEmpty()) {
            this.b.update(I.updateList);
        }
        OfflineLoadController.ConfigMtaData configMtaData = new OfflineLoadController.ConfigMtaData();
        configMtaData.serverAll = list.size();
        configMtaData.oldLocalAll = size;
        List<OfflineEntity> list5 = I.deleteList;
        configMtaData.del = list5 != null ? list5.size() : 0;
        I.configMtaData = configMtaData;
        return I;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void F(boolean z) {
        f6036e.set(z);
        if (z) {
            try {
                synchronized (this.d) {
                    this.d.notifyAll();
                }
            } catch (Exception e2) {
                Log.e("OfflineService", e2);
                OfflineExceptionUtils.reportDownloadCodeError("setAssetBuildInUpdated#lock", null, null, e2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @NonNull
    public <T extends OfflineEntity> OfflineLoadController.ChangeEntityLists<T> G(List<T> list, int i2) {
        OfflineLoadController.ChangeEntityLists<T> changeEntityLists = new OfflineLoadController.ChangeEntityLists<>();
        if (list != null && !list.isEmpty()) {
            Collections.sort(list);
            ArrayList arrayList = new ArrayList();
            int i3 = 0;
            for (T t : list) {
                i3++;
                if (i3 <= i2) {
                    if (!t.isAvailable()) {
                        if (!RetryFailInfo.hasInOverRetry(t)) {
                            arrayList.add(t);
                            if (t.isNewAdded()) {
                                changeEntityLists.newDownloadCount++;
                            } else {
                                changeEntityLists.updateDownloadCount++;
                            }
                            if (HybridSettings.isDebug()) {
                                StringBuilder sb = new StringBuilder();
                                sb.append("[Offline-file] Download: Need to download and its priority is high enough within max count(");
                                sb.append(i2);
                                sb.append("), priority = ");
                                sb.append(t.getPriority());
                                sb.append(", id: ");
                                sb.append(t.getAppid());
                                sb.append(", buildIn: ");
                                sb.append(t.isBuildInBiz());
                                sb.append(", url: ");
                                sb.append(!TextUtils.isEmpty(t.getDocumentUrl()) ? t.getDocumentUrl() : t.getOriginalUrl());
                                Log.d("OfflineService-Priority", sb.toString());
                            }
                        } else if (HybridSettings.isDebug()) {
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append("[Offline-file] Download: Cannot download because it has exceed the max retry count, priority = ");
                            sb2.append(t.getPriority());
                            sb2.append(", id: ");
                            sb2.append(t.getAppid());
                            sb2.append(", buildIn: ");
                            sb2.append(t.isBuildInBiz());
                            sb2.append(", url: ");
                            sb2.append(!TextUtils.isEmpty(t.getDocumentUrl()) ? t.getDocumentUrl() : t.getOriginalUrl());
                            Log.d("OfflineService-Priority", sb2.toString());
                        }
                    }
                } else if (t.isAvailable() && !t.isBuildInBiz()) {
                    if (HybridSettings.isDebug()) {
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append("[Offline-file] Download: Delete existed offline files because of exceeding max count(");
                        sb3.append(i2);
                        sb3.append("), priority = ");
                        sb3.append(t.getPriority());
                        sb3.append(", id: ");
                        sb3.append(t.getAppid());
                        sb3.append(", url: ");
                        sb3.append(!TextUtils.isEmpty(t.getDocumentUrl()) ? t.getDocumentUrl() : t.getOriginalUrl());
                        Log.d("OfflineService-Priority", sb3.toString());
                    }
                    OfflineFileUtils.deleteEntityFile(t);
                    t.updateDb();
                } else if (HybridSettings.isDebug()) {
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append("[Offline-file] Download: Won't download because of exceeding max count(");
                    sb4.append(i2);
                    sb4.append("), priority = ");
                    sb4.append(t.getPriority());
                    sb4.append(", id: ");
                    sb4.append(t.getAppid());
                    sb4.append(", buildIn: ");
                    sb4.append(t.isBuildInBiz());
                    sb4.append(", url: ");
                    sb4.append(!TextUtils.isEmpty(t.getDocumentUrl()) ? t.getDocumentUrl() : t.getOriginalUrl());
                    Log.d("OfflineService-Priority", sb4.toString());
                }
            }
            changeEntityLists.downloadList = arrayList;
            return changeEntityLists;
        }
        Log.d("OfflineService", "[Offline-file] Final offline config list is empty, no need to download.");
        return changeEntityLists;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <T extends BuildInOfflineEntity> OfflineLoadController.ChangeEntityLists<T> H(boolean z, List<T> list, List<T> list2, List<T> list3) {
        OfflineLoadController.ChangeEntityLists<T> changeEntityLists = new OfflineLoadController.ChangeEntityLists<>();
        HashMap hashMap = new HashMap(list2.size());
        HashMap hashMap2 = new HashMap(list.size());
        for (T t : list2) {
            OfflineEntity.resetDbUrl(t);
            hashMap.put(t.getAppid(), t);
        }
        HashSet hashSet = new HashSet(list3.size());
        for (T t2 : list3) {
            String str = "appid: " + t2.getAppid();
            try {
                str = JDJSON.toJSONString(t2);
            } catch (Exception e2) {
                Log.e("OfflineService", e2);
            }
            OfflineExceptionUtils.reportConfigError(OfflineExceptionUtils.ERR_MSG_NET, "[BuildIn]\u53bb\u9664\u65e0\u7528\u914d\u7f6e", t2.getAppid(), str);
            if (!TextUtils.isEmpty(t2.getAppid())) {
                hashSet.add(t2.getAppid());
            }
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (T t3 : list) {
            hashMap2.put(t3.getAppid(), t3);
            if (z) {
                BuildInOfflineEntity buildInOfflineEntity = (BuildInOfflineEntity) hashMap.get(t3.getAppid());
                if (hashSet.contains(t3.getAppid()) || buildInOfflineEntity == null) {
                    Log.d("OfflineService", "[Build-in-Offline-file] Delete DB config and local files, because asset's config list doesn't contain it or it is corrupted. Id: " + t3.getAppid());
                    arrayList2.add(t3);
                }
            }
        }
        changeEntityLists.deleteList = arrayList2;
        boolean z2 = false;
        for (T t4 : list2) {
            BuildInOfflineEntity buildInOfflineEntity2 = (BuildInOfflineEntity) hashMap2.get(t4.getAppid());
            if (buildInOfflineEntity2 != null) {
                if (!z2 && !OfflineFileUtils.isOldFileDir(buildInOfflineEntity2.getFileRootPath())) {
                    if (!OfflineFileUtils.checkDirHasFiles(OfflineFileUtils.HYBRID_OFFLINE_BUILDIN_DIR)) {
                        OfflineExceptionUtils.reportDownloadError(OfflineExceptionUtils.ERR_MSG_CHECK, "checkRootDirExist-BuildIn", (String) null, (String) null, "local list = " + hashMap2.toString());
                    }
                    z2 = true;
                }
                int versionCode = t4.getFileInfo().getVersionCode() - buildInOfflineEntity2.getFileInfo().getVersionCode();
                boolean hasUnzipFileChanged = buildInOfflineEntity2.hasUnzipFileChanged();
                if (versionCode > 0 || hasUnzipFileChanged) {
                    Locale locale = Locale.getDefault();
                    Object[] objArr = new Object[6];
                    objArr[0] = !hasUnzipFileChanged ? "Found new file version" : "Files changed";
                    objArr[1] = z ? JDViewKitEventHelper.ActionType_Copy : IExceptionHandler.DynamicExceptionData.TYPE_DOWNLOAD;
                    objArr[2] = buildInOfflineEntity2.getAppid();
                    objArr[3] = buildInOfflineEntity2.getName();
                    objArr[4] = Integer.valueOf(buildInOfflineEntity2.getFileInfo().getVersionCode());
                    objArr[5] = Integer.valueOf(t4.getFileInfo().getVersionCode());
                    Log.d("OfflineService", String.format(locale, "[Build-in-Offline-file] %s, waiting to %s and unzip, id: %s, name: %s, ver: old=%d, new=%d", objArr));
                    t4.setOldFileRootPath(buildInOfflineEntity2.getOldFileRootPath());
                    t4.copyLocalInfoFromOld(buildInOfflineEntity2);
                    t4.copyLocalFileInfoFromOld(buildInOfflineEntity2);
                    t4.setAvailable(false);
                    if (!z && t4.isPatchOf(buildInOfflineEntity2)) {
                        t4.setPatchVersionCode(buildInOfflineEntity2.getFileInfo().getVersionCode());
                    }
                    if (!buildInOfflineEntity2.getMinFileVer().equals(t4.getMinFileVer())) {
                        this.f6038c.updateMinFileVer(buildInOfflineEntity2.getAppid(), t4.getMinFileVer());
                    }
                    arrayList.add(t4);
                } else if (versionCode != 0) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("[Build-in-Offline-file] Do nothing for down-version(server:");
                    sb.append(t4.getFileInfo().getVersionCode());
                    sb.append(" local:");
                    sb.append(buildInOfflineEntity2.getFileInfo().getVersionCode());
                    sb.append(") config of id: ");
                    sb.append(buildInOfflineEntity2.getAppid());
                    sb.append(", name: ");
                    sb.append(buildInOfflineEntity2.getName());
                    sb.append(", url: ");
                    sb.append(!TextUtils.isEmpty(buildInOfflineEntity2.getDocumentUrl()) ? buildInOfflineEntity2.getDocumentUrl() : buildInOfflineEntity2.getOriginalUrl());
                    Log.d("OfflineService", sb.toString());
                } else if (!z) {
                    t4.copyLocalInfoFromOld(buildInOfflineEntity2);
                    t4.copyLocalFileInfoFromOld(buildInOfflineEntity2);
                    this.f6038c.update(t4);
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("[Build-in-Offline-file] Update DB config for same-version(");
                    sb2.append(t4.getFileInfo().getVersionCode());
                    sb2.append(") config of id: ");
                    sb2.append(buildInOfflineEntity2.getAppid());
                    sb2.append(", name: ");
                    sb2.append(buildInOfflineEntity2.getName());
                    sb2.append(", url: ");
                    sb2.append(!TextUtils.isEmpty(buildInOfflineEntity2.getDocumentUrl()) ? buildInOfflineEntity2.getDocumentUrl() : buildInOfflineEntity2.getOriginalUrl());
                    Log.d("OfflineService", sb2.toString());
                } else {
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("[Build-in-Offline-file] Do nothing for asset's same-version(");
                    sb3.append(t4.getFileInfo().getVersionCode());
                    sb3.append(") config of id: ");
                    sb3.append(buildInOfflineEntity2.getAppid());
                    sb3.append(", name: ");
                    sb3.append(buildInOfflineEntity2.getName());
                    sb3.append(", url: ");
                    sb3.append(!TextUtils.isEmpty(buildInOfflineEntity2.getDocumentUrl()) ? buildInOfflineEntity2.getDocumentUrl() : buildInOfflineEntity2.getOriginalUrl());
                    Log.d("OfflineService", sb3.toString());
                }
            } else if (z) {
                StringBuilder sb4 = new StringBuilder();
                sb4.append("[Build-in-Offline-file] Found new config, id: ");
                sb4.append(t4.getAppid());
                sb4.append(", name: ");
                sb4.append(t4.getName());
                sb4.append(", url: ");
                sb4.append(!TextUtils.isEmpty(t4.getDocumentUrl()) ? t4.getDocumentUrl() : t4.getOriginalUrl());
                Log.d("OfflineService", sb4.toString());
                t4.setCreateTime();
                t4.setAvailable(false);
                t4.setNewAdded(true);
                arrayList.add(t4);
            } else {
                Log.d("OfflineService", "[Build-in-Offline-file] Found new config (id: " + t4.getAppid() + "), but does not exist in asset list, ignore it.");
            }
        }
        changeEntityLists.downloadList = arrayList;
        return changeEntityLists;
    }

    /* JADX WARN: Removed duplicated region for block: B:71:0x0270  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x029b  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x02a0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private OfflineLoadController.ChangeEntityLists<OfflineEntity> I(List<OfflineEntity> list, List<OfflineEntity> list2, List<OfflineEntity> list3) {
        boolean z;
        HashMap hashMap;
        OfflineLoadController.ChangeEntityLists<OfflineEntity> changeEntityLists = new OfflineLoadController.ChangeEntityLists<>();
        HashMap hashMap2 = new HashMap(list2.size());
        HashMap hashMap3 = new HashMap(list.size());
        for (OfflineEntity offlineEntity : list2) {
            OfflineEntity.resetDbUrl(offlineEntity);
            hashMap2.put(offlineEntity.getAppid(), offlineEntity);
        }
        HashSet hashSet = new HashSet(list3.size());
        for (OfflineEntity offlineEntity2 : list3) {
            String str = "appid: " + offlineEntity2.getAppid();
            try {
                str = JDJSON.toJSONString(offlineEntity2);
            } catch (Exception e2) {
                Log.e("OfflineService", e2);
            }
            OfflineExceptionUtils.reportConfigError(OfflineExceptionUtils.ERR_MSG_NET, "[Offline]\u53bb\u9664\u65e0\u7528\u914d\u7f6e", offlineEntity2.getAppid(), str);
            if (!TextUtils.isEmpty(offlineEntity2.getAppid())) {
                hashSet.add(offlineEntity2.getAppid());
            }
        }
        ArrayList arrayList = new ArrayList();
        for (OfflineEntity offlineEntity3 : list) {
            hashMap3.put(offlineEntity3.getAppid(), offlineEntity3);
            OfflineEntity offlineEntity4 = (OfflineEntity) hashMap2.get(offlineEntity3.getAppid());
            if (hashSet.contains(offlineEntity3.getAppid()) || offlineEntity4 == null) {
                Log.d("OfflineService", "[Offline-file] Delete DB config and local files, because server's config list doesn't contain it or it is corrupted. Id: " + offlineEntity3.getAppid());
                OfflineFileUtils.deleteEntityFile(offlineEntity3);
                arrayList.add(offlineEntity3);
            }
        }
        changeEntityLists.deleteList = arrayList;
        long j2 = HybridSettings.LAST_SET_LP_TIME;
        boolean z2 = true;
        boolean z3 = false;
        if (j2 == 0 || !DateUtils.isSameDay(j2, System.currentTimeMillis())) {
            HybridSettings.setLastSetLocalPriorityTime(System.currentTimeMillis());
            Log.d("OfflineService-Priority", "[Offline-file] Need to recalculate every existed entity's priority for today. last time = " + j2);
            z = true;
        } else {
            z = false;
        }
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        boolean z4 = false;
        for (OfflineEntity offlineEntity5 : list2) {
            OfflineEntity offlineEntity6 = (OfflineEntity) hashMap3.get(offlineEntity5.getAppid());
            if (offlineEntity6 == null) {
                StringBuilder sb = new StringBuilder();
                sb.append("[Offline-file] Insert new config into DB for id: ");
                sb.append(offlineEntity5.getAppid());
                sb.append(", name: ");
                sb.append(offlineEntity5.getName());
                sb.append(", url: ");
                sb.append(!TextUtils.isEmpty(offlineEntity5.getDocumentUrl()) ? offlineEntity5.getDocumentUrl() : offlineEntity5.getOriginalUrl());
                Log.d("OfflineService", sb.toString());
                offlineEntity5.setCreateTime();
                offlineEntity5.setAvailable(z3);
                offlineEntity5.setNewAdded(z2);
                arrayList2.add(offlineEntity5);
                arrayList4.add(offlineEntity5);
                hashMap = hashMap3;
            } else {
                if (offlineEntity6.isAvailable()) {
                    if (!z4 && !OfflineFileUtils.isOldFileDir(offlineEntity6.getFileRootPath())) {
                        if (!OfflineFileUtils.checkDirHasFiles(OfflineFileUtils.HYBRID_OFFLINE_FILE_DIR)) {
                            OfflineExceptionUtils.reportDownloadError(OfflineExceptionUtils.ERR_MSG_CHECK, "checkRootDirExist-Offline", (String) null, (String) null, "local list = " + hashMap3.toString());
                        }
                        z4 = true;
                    }
                    boolean hasUnzipFileChanged = offlineEntity6.hasUnzipFileChanged();
                    if (!hasUnzipFileChanged && offlineEntity5.getFileInfo().getVersionCode() == offlineEntity6.getFileInfo().getVersionCode()) {
                        offlineEntity5.copyLocalFileInfoFromOld(offlineEntity6);
                    } else {
                        Locale locale = Locale.getDefault();
                        Object[] objArr = new Object[5];
                        objArr[0] = !hasUnzipFileChanged ? "Found new file version" : "Files changed";
                        objArr[1] = offlineEntity6.getAppid();
                        objArr[2] = offlineEntity6.getName();
                        objArr[3] = Integer.valueOf(offlineEntity6.getFileInfo().getVersionCode());
                        objArr[4] = Integer.valueOf(offlineEntity5.getFileInfo().getVersionCode());
                        hashMap = hashMap3;
                        Log.d("OfflineService", String.format(locale, "[Offline-file] %s, id: %s, name: %s, ver: old=%d, new=%d", objArr));
                        if (!hasUnzipFileChanged && offlineEntity5.isPatchOf(offlineEntity6)) {
                            OfflineFileUtils.deleteUnzipFile(offlineEntity6);
                            offlineEntity5.setFileRootPath(offlineEntity6.getFileRootPath());
                            offlineEntity5.setPatchVersionCode(offlineEntity6.getFileInfo().getVersionCode());
                            offlineEntity5.copyLocalZipInfoFromOld(offlineEntity6);
                        } else {
                            OfflineFileUtils.deleteEntityFile(offlineEntity6);
                        }
                        offlineEntity6.setAvailable(false);
                        offlineEntity5.setAvailable(false);
                        arrayList4.add(offlineEntity5);
                        offlineEntity5.copyLocalInfoFromOld(offlineEntity6);
                        if (z) {
                            offlineEntity5.calculateLpEveryTimeGap();
                        }
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("[Offline-file] Update DB config for id: ");
                        sb2.append(offlineEntity6.getAppid());
                        sb2.append(", name: ");
                        sb2.append(offlineEntity6.getName());
                        sb2.append(", url: ");
                        sb2.append(TextUtils.isEmpty(offlineEntity6.getDocumentUrl()) ? offlineEntity6.getDocumentUrl() : offlineEntity6.getOriginalUrl());
                        Log.d("OfflineService", sb2.toString());
                        arrayList3.add(offlineEntity5);
                    }
                }
                hashMap = hashMap3;
                offlineEntity5.copyLocalInfoFromOld(offlineEntity6);
                if (z) {
                }
                StringBuilder sb22 = new StringBuilder();
                sb22.append("[Offline-file] Update DB config for id: ");
                sb22.append(offlineEntity6.getAppid());
                sb22.append(", name: ");
                sb22.append(offlineEntity6.getName());
                sb22.append(", url: ");
                sb22.append(TextUtils.isEmpty(offlineEntity6.getDocumentUrl()) ? offlineEntity6.getDocumentUrl() : offlineEntity6.getOriginalUrl());
                Log.d("OfflineService", sb22.toString());
                arrayList3.add(offlineEntity5);
            }
            hashMap3 = hashMap;
            z2 = true;
            z3 = false;
        }
        changeEntityLists.addList = arrayList2;
        changeEntityLists.updateList = arrayList3;
        changeEntityLists.downloadList = arrayList4;
        return changeEntityLists;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: InlineMethods
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to process method for inline: com.jd.libs.hybrid.offlineload.loader.OfflineService.DownloadFileProcessor.a(com.jd.libs.hybrid.offlineload.loader.OfflineService$DownloadFileProcessor, com.jd.libs.hybrid.offlineload.loader.OfflineService$ProcessCallback):com.jd.libs.hybrid.offlineload.loader.OfflineService$DownloadFileProcessor
        	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:74)
        	at jadx.core.dex.visitors.InlineMethods.visit(InlineMethods.java:49)
        Caused by: java.lang.NullPointerException
        	at jadx.core.dex.instructions.args.RegisterArg.sameRegAndSVar(RegisterArg.java:173)
        	at jadx.core.dex.instructions.args.InsnArg.isSameVar(InsnArg.java:269)
        	at jadx.core.dex.visitors.MarkMethodsForInline.isSyntheticAccessPattern(MarkMethodsForInline.java:118)
        	at jadx.core.dex.visitors.MarkMethodsForInline.inlineMth(MarkMethodsForInline.java:86)
        	at jadx.core.dex.visitors.MarkMethodsForInline.process(MarkMethodsForInline.java:53)
        	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:63)
        	... 1 more
        */
    /* JADX INFO: Access modifiers changed from: private */
    public <T extends com.jd.libs.hybrid.offlineload.entity.BuildInOfflineEntity> void u(java.util.List<T> r15) {
        /*
            r14 = this;
            r0 = 1
            java.lang.String r1 = "OfflineService"
            if (r15 == 0) goto Lcf
            boolean r2 = r15.isEmpty()
            if (r2 == 0) goto Ld
            goto Lcf
        Ld:
            java.util.Iterator r15 = r15.iterator()
        L11:
            boolean r2 = r15.hasNext()
            if (r2 == 0) goto Lcb
            java.lang.Object r2 = r15.next()
            com.jd.libs.hybrid.offlineload.entity.BuildInOfflineEntity r2 = (com.jd.libs.hybrid.offlineload.entity.BuildInOfflineEntity) r2
            android.content.Context r3 = r14.a
            java.io.File r6 = com.jd.libs.hybrid.offlineload.utils.OfflineFileUtils.copyBuildInZipFromAsset(r3, r2)
            if (r6 == 0) goto L94
            boolean r3 = r6.exists()
            if (r3 != 0) goto L2c
            goto L94
        L2c:
            java.lang.String r3 = r6.getAbsolutePath()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "[Build-in-Offline-file] copy zip from asset successfully, ready to unzip, id: "
            r4.append(r5)
            java.lang.String r5 = r2.getAppid()
            r4.append(r5)
            java.lang.String r5 = ", file at: "
            r4.append(r5)
            r4.append(r3)
            java.lang.String r3 = r4.toString()
            com.jd.libs.hybrid.base.util.Log.d(r1, r3)
            android.content.Context r3 = r14.a
            java.lang.String r4 = r2.getAppid()
            java.lang.String r4 = com.jd.libs.hybrid.offlineload.utils.OfflineFileUtils.generateSaveDirName(r4)
            java.lang.String r11 = com.jd.libs.hybrid.offlineload.utils.OfflineFileUtils.getBuildInSourceDir(r3, r4)
            android.content.Context r3 = r14.a
            java.lang.String r12 = com.jd.libs.hybrid.offlineload.utils.OfflineFileUtils.getBuildInZipDir(r3)
            float r9 = com.jd.hybrid.downloader.c.e(r6)
            com.jd.libs.hybrid.offlineload.entity.OfflineEntityInfo r3 = r2.getFileInfo()
            if (r3 == 0) goto L77
            com.jd.libs.hybrid.offlineload.entity.OfflineEntityInfo r3 = r2.getFileInfo()
            java.lang.String r3 = r3.getUrl()
            goto L79
        L77:
            java.lang.String r3 = ""
        L79:
            r7 = r3
            com.jd.libs.hybrid.offlineload.loader.OfflineService$DownloadFileProcessor r13 = new com.jd.libs.hybrid.offlineload.loader.OfflineService$DownloadFileProcessor
            r8 = 0
            r10 = 0
            r3 = r13
            r4 = r14
            r5 = r2
            r3.<init>(r5, r6, r7, r8, r9)
            com.jd.libs.hybrid.offlineload.loader.OfflineService$7 r3 = new com.jd.libs.hybrid.offlineload.loader.OfflineService$7
            r3.<init>()
            com.jd.libs.hybrid.offlineload.loader.OfflineService.DownloadFileProcessor.a(r13, r3)
            com.jd.libs.hybrid.offlineload.loader.OfflineService.DownloadFileProcessor.b(r13, r11, r12)
            com.jd.libs.hybrid.offlineload.loader.OfflineService.DownloadFileProcessor.c(r13)
            goto L11
        L94:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "[Build-in-Offline-file] fail to copy zip from asset, id: "
            r3.append(r4)
            java.lang.String r4 = r2.getAppid()
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            com.jd.libs.hybrid.base.util.Log.e(r1, r3)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "zip file is null or not exist after copied, id: "
            r3.append(r4)
            java.lang.String r2 = r2.getAppid()
            r3.append(r2)
            java.lang.String r2 = r3.toString()
            java.lang.String r3 = "\u4e0b\u8f7d\u5931\u8d25"
            java.lang.String r4 = "BuildInCopyError"
            r5 = 0
            com.jd.libs.hybrid.offlineload.utils.OfflineExceptionUtils.reportDownloadError(r3, r4, r5, r5, r2)
            goto L11
        Lcb:
            r14.F(r0)
            return
        Lcf:
            java.lang.String r15 = "[Build-in-Offline-file] No new file need to be copied from asset."
            com.jd.libs.hybrid.base.util.Log.d(r1, r15)
            r14.F(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.libs.hybrid.offlineload.loader.OfflineService.u(java.util.List):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void v(final OfflineEntity offlineEntity) {
        DatabaseExecutors.getInstance().threadIO().execute(new Runnable(this) { // from class: com.jd.libs.hybrid.offlineload.loader.OfflineService.6
            @Override // java.lang.Runnable
            public void run() {
                try {
                    OfflineFileUtils.deleteEntityFile(offlineEntity);
                    offlineEntity.deleteDb();
                } catch (Exception e2) {
                    Log.e("OfflineService", e2);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w(OfflineEntity offlineEntity, int i2) {
        x(Collections.singletonList(offlineEntity), i2, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void x(List<OfflineEntity> list, int i2, boolean z) {
        d dVar;
        String str;
        if (DownloadFileDisable.offlineDownloadDisable) {
            Log.d("OfflineService", "Downloading offline file function is disable by switch.");
            Log.xLogDForDev("OfflineService", "\u4e0b\u8f7d\u7ebf\u4e0a\u79bb\u7ebf\u5305\u529f\u80fd\u5df2\u5173\u95ed\uff0c\u53ea\u66f4\u65b0\u914d\u7f6e\uff0c\u4e0d\u4e0b\u8f7d\u65b0\u6587\u4ef6\uff0c\u8bf7\u8054\u7cfb\u7ba1\u7406\u5458");
            return;
        }
        com.jd.hybrid.downloader.c f2 = com.jd.hybrid.downloader.c.f();
        if (f2 == null) {
            return;
        }
        ArrayList arrayList = new ArrayList(list.size());
        StringBuilder sb = null;
        for (OfflineEntity offlineEntity : list) {
            boolean isBuildInBiz = offlineEntity.isBuildInBiz();
            if ((isBuildInBiz && !(offlineEntity instanceof BuildInOfflineEntity)) || CommonUtils.getBinarySwitch(offlineEntity.getBConfig(), ModuleHelper.BCONFIG_DOWNLOAD_DEGRADE)) {
                if (isBuildInBiz && !(offlineEntity instanceof BuildInOfflineEntity)) {
                    Log.e("OfflineService", "Entity(" + offlineEntity.getAppid() + ") type is build-in app, but entity is not an instance of BuildInOfflineEntity! Skip downloading this one.");
                } else {
                    Log.e("OfflineService", "Entity(" + offlineEntity.getAppid() + ") type is download degrade, Skip downloading this one.");
                }
            } else {
                OfflineEntityInfo fileInfo = offlineEntity.getFileInfo();
                boolean z2 = fileInfo != null && fileInfo.getPatchVersionCode() >= 0;
                boolean z3 = (z2 || (fileInfo != null && fileInfo.getFileType().equalsIgnoreCase(OfflineEntityInfo.FILE_TYPE_ZIP2))) ? false : true;
                String patchUrl = z2 ? offlineEntity.getPatchUrl(fileInfo.getPatchVersionCode()) : fileInfo.getUrl();
                StringBuilder sb2 = new StringBuilder();
                sb2.append("\u9879\u76ee(");
                sb2.append(offlineEntity.getAppid());
                sb2.append(")");
                sb2.append(!isBuildInBiz ? "\u79bb\u7ebf\u5305" : "\u5185\u7f6e\u5305\u70ed\u66f4\u65b0");
                sb2.append("\u4e0b\u8f7d");
                d dVar2 = new d(sb2.toString(), patchUrl, !isBuildInBiz ? OfflineFileUtils.getZipRelativeDir() : OfflineFileUtils.getBuildInZipRelativeDir(), OfflineFileUtils.generateFileName(patchUrl), false, (int) (offlineEntity.getPriority() * 1000.0f), z3);
                dVar2.q(0);
                dVar2.n(offlineEntity.getAppid());
                dVar2.m(new com.jd.hybrid.downloader.n.b(offlineEntity.getFileInfo().getMd5()));
                if (!isBuildInBiz) {
                    str = patchUrl;
                    dVar2.l(new OfflineDownloadCallback(offlineEntity, str, z2, i2));
                    dVar = dVar2;
                } else {
                    dVar = dVar2;
                    str = patchUrl;
                    dVar.l(new BuildInDownloadCallback((BuildInOfflineEntity) offlineEntity, str, z2, i2));
                }
                arrayList.add(dVar);
                if (Log.isDebug()) {
                    if (sb == null) {
                        sb = new StringBuilder();
                    } else {
                        sb.setLength(0);
                    }
                    sb.append("\u9879\u76ee(id:");
                    sb.append(offlineEntity.getAppid());
                    sb.append("\uff0curl:");
                    sb.append(offlineEntity.getShowUrl());
                    sb.append(")\u9700\u4e0b\u8f7d/\u66f4\u65b0\u79bb\u7ebf\u6587\u4ef6\uff0c\u5df2\u52a0\u5165\u4e0b\u8f7d\u5217\u8868\uff0c\u4e0b\u8f7d\u5730\u5740:");
                    sb.append(str);
                    sb.append("\uff0c\u8bf7\u7b49\u5f85\u4e0b\u8f7d\u5b8c\u6bd5\u540e\u4f7f\u7528\u3002");
                    Log.xLogD("OfflineService", sb.toString());
                }
            }
        }
        f2.c(arrayList, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BuildInOfflineEntity y(String str, String str2, int i2) {
        if (i2 != -1) {
            return this.f6038c.getOneAvailableByUrl(str2, i2);
        }
        return this.f6038c.getOneAvailableByUrl(str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public OfflineEntity z(String str, String str2, int i2) {
        OfflineEntity byUrl;
        if (i2 != -1) {
            byUrl = this.b.getByUrl(str2, i2);
        } else {
            byUrl = this.b.getByUrl(str2);
        }
        if (byUrl != null && Log.isDebug()) {
            Log.xLogDForDev("OfflineService", "(\u79bb\u7ebf\u5305)\u627e\u5230\u79bb\u7ebf\u5305\u914d\u7f6e\uff0curl: " + str2);
        }
        if (byUrl == null) {
            OfflineEntityDao offlineEntityDao = this.b;
            byUrl = B(i2 != -1 ? offlineEntityDao.getAllSSrBizByVersion(i2) : offlineEntityDao.getAllSSrBiz(), str);
            if (byUrl != null && Log.isDebug()) {
                Log.xLogDForDev("OfflineService", "(\u79bb\u7ebf\u5305)\u627e\u5230\u79bb\u7ebf\u5305\u914d\u7f6e\uff0curl\u6b63\u5219: " + str);
            }
        }
        return byUrl;
    }

    public void deleteAllDownloaded() {
        DatabaseExecutors.getInstance().threadIO().execute(new Runnable() { // from class: com.jd.libs.hybrid.offlineload.loader.OfflineService.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    OfflineFileUtils.deleteDownloadedFiles(OfflineService.this.a);
                    OfflineService.this.b.deleteAll();
                } catch (Exception e2) {
                    Log.e("OfflineService", e2);
                }
            }
        });
    }

    public void getEntityByUrl(String str, OfflineLoadController.NetConfigCallback<OfflineFiles> netConfigCallback) {
        String trim = str != null ? str.trim() : null;
        if (Log.isDebug()) {
            Log.xLogD("OfflineService", "\u79bb\u7ebf\u5305\uff1a\u6b63\u5728\u67e5\u627e\u662f\u5426\u5b58\u5728\u79bb\u7ebf\u5305\u914d\u7f6e\uff0cURL\uff1a" + trim);
        }
        DatabaseExecutors.getInstance().runOnIoThread(new AnonymousClass5(trim, netConfigCallback));
    }

    public void onAllDownloadedChanged(@NonNull final List<OfflineEntity> list) {
        DatabaseExecutors.getInstance().threadIO().execute(new Runnable() { // from class: com.jd.libs.hybrid.offlineload.loader.OfflineService.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    ArrayList arrayList = new ArrayList();
                    ArrayList arrayList2 = new ArrayList();
                    for (int i2 = 0; i2 < list.size(); i2++) {
                        OfflineEntity offlineEntity = (OfflineEntity) list.get(i2);
                        if (offlineEntity != null) {
                            if (offlineEntity.isBuildInBiz()) {
                                arrayList2.add((BuildInOfflineEntity) JDJSON.parseObject(JDJSON.toJSONString(offlineEntity), BuildInOfflineEntity.class));
                            } else {
                                arrayList.add(offlineEntity);
                            }
                        }
                    }
                    if (!OfflineService.f6036e.get()) {
                        try {
                            synchronized (OfflineService.this.d) {
                                if (!OfflineService.f6036e.get()) {
                                    Log.d("OfflineService", "[Offline-file] wait for loading buildIn configs from asset before downloading new file.");
                                    OfflineService.this.d.wait(2000L);
                                }
                            }
                        } catch (Exception e2) {
                            Log.e("OfflineService", e2);
                            OfflineExceptionUtils.reportDownloadCodeError("allChanged#lock", null, null, e2);
                        }
                    }
                    OfflineLoadController.ChangeEntityLists E = OfflineService.this.E(arrayList);
                    OfflineLoadController.ChangeEntityLists D = OfflineService.this.D(arrayList2);
                    HashMap hashMap = new HashMap();
                    List<T> list2 = E.downloadList;
                    if (list2 != 0 && !list2.isEmpty()) {
                        for (T t : E.downloadList) {
                            hashMap.put(t.getAppid(), t);
                        }
                    }
                    List<T> list3 = D.downloadList;
                    if (list3 != 0 && !list3.isEmpty()) {
                        for (T t2 : D.downloadList) {
                            hashMap.put(t2.getAppid(), t2);
                        }
                    }
                    List<OfflineEntity> all = OfflineService.this.b.getAll();
                    List<BuildInOfflineEntity> all2 = OfflineService.this.f6038c.getAll();
                    LinkedList linkedList = new LinkedList();
                    linkedList.addAll(all);
                    linkedList.addAll(all2);
                    for (int i3 = 0; i3 < linkedList.size(); i3++) {
                        OfflineEntity offlineEntity2 = (OfflineEntity) hashMap.remove(((OfflineEntity) linkedList.get(i3)).getAppid());
                        if (offlineEntity2 != null) {
                            linkedList.set(i3, offlineEntity2);
                        }
                    }
                    linkedList.addAll(hashMap.values());
                    OfflineLoadController.ChangeEntityLists G = OfflineService.this.G(linkedList, HybridSettings.MAX_OFFLINE_PACK_COUNT);
                    List<T> list4 = G.downloadList;
                    if (list4 != 0 && !list4.isEmpty()) {
                        OfflineService.this.x(G.downloadList, 0, true);
                    } else {
                        Log.d("OfflineService", "[Offline-file] No new file need to download");
                    }
                } catch (Exception e3) {
                    Log.e("OfflineService", e3);
                    OfflineExceptionUtils.reportDownloadCodeError("onAllDownloadedChanged", null, null, e3);
                }
            }
        });
    }

    public void onAvailableEntityNewVersion(final OfflineEntity offlineEntity, final OfflineEntity offlineEntity2) {
        OfflineEntity.resetDbUrl(offlineEntity2);
        DatabaseExecutors.getInstance().threadIO().execute(new Runnable() { // from class: com.jd.libs.hybrid.offlineload.loader.OfflineService.3
            @Override // java.lang.Runnable
            public void run() {
                try {
                    boolean isBuildInBiz = offlineEntity.isBuildInBiz();
                    if (offlineEntity2.isPatchOf(offlineEntity)) {
                        if (!isBuildInBiz) {
                            OfflineFileUtils.deleteUnzipFile(offlineEntity);
                        }
                        offlineEntity2.setFileRootPath(offlineEntity.getFileRootPath());
                        offlineEntity2.setPatchVersionCode(offlineEntity.getFileInfo().getVersionCode());
                        offlineEntity2.copyLocalZipInfoFromOld(offlineEntity);
                    } else if (!isBuildInBiz) {
                        OfflineFileUtils.deleteEntityFile(offlineEntity);
                    }
                    offlineEntity2.copyLocalInfoFromOld(offlineEntity);
                    offlineEntity2.setAvailable(false);
                    if (!isBuildInBiz) {
                        Log.d("OfflineService", "[Offline-file] Update DB config because found new version in single-entity-api's result, id: " + offlineEntity2.getAppid());
                        offlineEntity2.updateDb();
                    } else {
                        if (!offlineEntity.getMinFileVer().equals(offlineEntity2.getMinFileVer())) {
                            OfflineService.this.f6038c.updateMinFileVer(offlineEntity.getAppid(), offlineEntity2.getMinFileVer());
                        }
                        offlineEntity2.copyLocalFileInfoFromOld(offlineEntity);
                    }
                    OfflineService.this.w(offlineEntity2, 0);
                } catch (Exception e2) {
                    Log.e("OfflineService", e2);
                    OfflineExceptionUtils.reportDownloadCodeError("onAvailableEntityNewVersion", null, null, e2);
                }
            }
        });
    }

    public void updateBuildInPackageFromAsset() {
        F(false);
        DatabaseExecutors.getInstance().threadIO().execute(new Runnable() { // from class: com.jd.libs.hybrid.offlineload.loader.OfflineService.4
            @Override // java.lang.Runnable
            public void run() {
                ArrayList arrayList;
                BuildInOfflineEntity buildInOfflineEntity;
                if (OfflineService.f6037f == null) {
                    synchronized (OfflineService.class) {
                        if (OfflineService.f6037f == null) {
                            ConcurrentHashMap unused = OfflineService.f6037f = new ConcurrentHashMap();
                            OfflineFileUtils.getBuildInConfigFromAsset(OfflineService.this.a, OfflineService.f6037f);
                        } else {
                            Log.d("OfflineService", "2. use cached build-in config: " + OfflineService.f6037f);
                        }
                    }
                } else {
                    Log.d("OfflineService", "1. use cached build-in config: " + OfflineService.f6037f);
                }
                ConcurrentHashMap concurrentHashMap = OfflineService.f6037f;
                if (concurrentHashMap == null || concurrentHashMap.isEmpty()) {
                    arrayList = null;
                } else {
                    arrayList = new ArrayList();
                    for (String str : concurrentHashMap.keySet()) {
                        String str2 = (String) concurrentHashMap.get(str);
                        if (TextUtils.isEmpty(str2)) {
                            Log.e("OfflineService", "[Build-in-Offline-file] Build-in config json is null, asset file = " + str);
                            OfflineExceptionUtils.reportConfigError(OfflineExceptionUtils.ERR_MSG_CODE, "BuildInJsonNull", (String) null, "asset file = " + str);
                        } else {
                            try {
                                buildInOfflineEntity = (BuildInOfflineEntity) JDJSON.parseObject(str2, BuildInOfflineEntity.class);
                            } catch (Exception e2) {
                                Log.e("OfflineService", "[Build-in-Offline-file] Build-in config json parsing error, asset file = " + str, e2);
                                OfflineExceptionUtils.reportConfigError(OfflineExceptionUtils.ERR_MSG_CODE, "BuildInJsonError", (String) null, "asset file = " + str + ", json = " + str2 + ", exception = " + ExceptionUtils.getStackStringFromException(e2));
                                buildInOfflineEntity = null;
                            }
                            if (buildInOfflineEntity != null) {
                                arrayList.add(buildInOfflineEntity);
                            }
                        }
                    }
                }
                if (arrayList != null) {
                    try {
                        if (!arrayList.isEmpty()) {
                            List removeUseless = IInterfaceCheck.Companion.removeUseless(arrayList);
                            if (removeUseless != null && !removeUseless.isEmpty()) {
                                Log.d("OfflineService", "[Build-in-Offline-file] Remove illegal build-in config, list: " + removeUseless);
                            }
                            Log.d("OfflineService", "[Build-in-Offline-file] found asset build-in offline config list: " + arrayList);
                            OfflineLoadController.ChangeEntityLists H = OfflineService.this.H(true, OfflineService.this.f6038c.getAll(), arrayList, removeUseless);
                            List<T> list = H.deleteList;
                            if (list != 0 && !list.isEmpty()) {
                                OfflineService.this.f6038c.delete((List<BuildInOfflineEntity>) H.deleteList);
                                for (T t : H.deleteList) {
                                    if (t != null) {
                                        OfflineFileUtils.deleteEntityFile(t);
                                    }
                                }
                            }
                            OfflineService.this.u(H.downloadList);
                            return;
                        }
                    } catch (Exception e3) {
                        Log.e("OfflineService", e3);
                        OfflineExceptionUtils.reportConfigError(OfflineExceptionUtils.ERR_MSG_CODE, "updateBuildInPackageFromAsset", (String) null, ExceptionUtils.getStackStringFromException(e3));
                        OfflineService.this.F(true);
                        return;
                    }
                }
                Log.d("OfflineService", "[Build-in-Offline-file] No build-in config found in asset folder, delete all build-in db and files.");
                OfflineService.this.f6038c.deleteAll();
                OfflineFileUtils.deleteBuildInFiles(OfflineService.this.a);
                OfflineService.this.F(true);
            }
        });
    }
}
