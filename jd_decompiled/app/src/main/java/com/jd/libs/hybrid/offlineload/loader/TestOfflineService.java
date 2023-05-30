package com.jd.libs.hybrid.offlineload.loader;

import android.content.Context;
import android.text.TextUtils;
import com.jd.hybrid.downloader.FileError;
import com.jd.hybrid.downloader.FileResponse;
import com.jd.hybrid.downloader.d;
import com.jd.libs.hybrid.base.HybridSettings;
import com.jd.libs.hybrid.base.util.DatabaseExecutors;
import com.jd.libs.hybrid.base.util.Log;
import com.jd.libs.hybrid.offlineload.db.OfflineDatabase;
import com.jd.libs.hybrid.offlineload.db.TestOfflineEntityDao;
import com.jd.libs.hybrid.offlineload.entity.FileDetail;
import com.jd.libs.hybrid.offlineload.entity.OfflineEntityInfo;
import com.jd.libs.hybrid.offlineload.entity.TestOfflineEntity;
import com.jd.libs.hybrid.offlineload.jsimpl.HybridInlineJsInterface;
import com.jd.libs.hybrid.offlineload.utils.OfflineFileUtils;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Deprecated
/* loaded from: classes16.dex */
public class TestOfflineService {
    private static volatile TestOfflineService d;
    private final Context a;
    @Deprecated
    private final TestOfflineEntityDao b;

    /* renamed from: c */
    private Map<String, TestOfflineEntity> f6069c;

    /* loaded from: classes16.dex */
    public class MyDownloadsCallback extends com.jd.hybrid.downloader.b {
        private final TestOfflineEntity a;
        private final int b;

        /* renamed from: c */
        private HybridInlineJsInterface.TestCallback f6070c;

        MyDownloadsCallback(TestOfflineEntity testOfflineEntity, int i2, HybridInlineJsInterface.TestCallback testCallback) {
            TestOfflineService.this = r1;
            this.a = testOfflineEntity;
            this.b = i2;
            this.f6070c = testCallback;
        }

        /* renamed from: a */
        public /* synthetic */ void b(File file) {
            if (file != null && file.exists()) {
                this.a.setAvailable(false);
                TestOfflineEntity byId = TestOfflineService.this.b.getById(this.a.getAppid());
                if (byId != null) {
                    OfflineFileUtils.deleteEntityFile(byId);
                    TestOfflineService.this.b.delete(byId);
                }
                c(file);
                return;
            }
            Log.e("TestOfflineService", "[Test-offline] missing downloaded zip");
            HybridInlineJsInterface.TestCallback testCallback = this.f6070c;
            if (testCallback != null) {
                testCallback.callback(3);
            }
        }

        private void c(File file) {
            try {
                String testSourceDir = OfflineFileUtils.getTestSourceDir(TestOfflineService.this.a, this.a.getAppid(), null);
                if (TextUtils.isEmpty(testSourceDir)) {
                    Log.e("TestOfflineService", "[Test-offline] unzip dest path is empty");
                    HybridInlineJsInterface.TestCallback testCallback = this.f6070c;
                    if (testCallback != null) {
                        testCallback.callback(3);
                        return;
                    }
                    return;
                }
                if (d(file.getAbsolutePath(), testSourceDir, this.a.getFileInfo().getPassword())) {
                    String appid = this.a.getAppid();
                    String testSourceDir2 = OfflineFileUtils.getTestSourceDir(TestOfflineService.this.a, appid, this.a.getDocumentDir());
                    String testSourceDir3 = OfflineFileUtils.getTestSourceDir(TestOfflineService.this.a, appid, this.a.getSourceDir());
                    if (TextUtils.isEmpty(testSourceDir2)) {
                        Log.e("TestOfflineService", "[Test-offline] local html file path is empty");
                        HybridInlineJsInterface.TestCallback testCallback2 = this.f6070c;
                        if (testCallback2 != null) {
                            testCallback2.callback(3);
                            return;
                        }
                        return;
                    }
                    File file2 = new File(testSourceDir2);
                    if (TextUtils.isEmpty(testSourceDir3)) {
                        Log.e("TestOfflineService", "[Test-offline] local static file path is empty");
                        HybridInlineJsInterface.TestCallback testCallback3 = this.f6070c;
                        if (testCallback3 != null) {
                            testCallback3.callback(3);
                            return;
                        }
                        return;
                    }
                    File file3 = new File(testSourceDir3);
                    if (!(file2.exists() && (TextUtils.isEmpty(this.a.getSourceDir()) || file3.exists()))) {
                        Log.e("TestOfflineService", "[Test-offline] unzip files checking fail");
                        return;
                    }
                    this.a.setAvailable(true);
                    this.a.setFileRootPath(testSourceDir);
                    this.a.setZipFile(new FileDetail(file));
                    this.a.setDocumentFile(new FileDetail(file2));
                    this.a.setSourceFile(new FileDetail(file3));
                    TestOfflineService.this.d(this.a);
                    if (Log.isDebug()) {
                        Log.d("TestOfflineService", "[Test-offline] Download and update config success, file at " + file.getAbsolutePath() + ", url = " + this.a.getFileInfo().getUrl());
                        StringBuilder sb = new StringBuilder();
                        sb.append("\u6d4b\u8bd5\u5305(");
                        sb.append(this.a.getFileInfo().getUrl());
                        sb.append(")\u4e0b\u8f7d\u5b8c\u6bd5\uff0c\u5df2\u53ef\u7528\u3002");
                        Log.xLogDForDev("TestOfflineService", sb.toString());
                    }
                    HybridInlineJsInterface.TestCallback testCallback4 = this.f6070c;
                    if (testCallback4 != null) {
                        testCallback4.callback(1);
                        return;
                    }
                    return;
                }
                HybridInlineJsInterface.TestCallback testCallback5 = this.f6070c;
                if (testCallback5 != null) {
                    testCallback5.callback(3);
                }
            } catch (Exception e2) {
                Log.e("TestOfflineService", e2.getMessage());
                HybridInlineJsInterface.TestCallback testCallback6 = this.f6070c;
                if (testCallback6 != null) {
                    testCallback6.callback(3);
                }
            }
        }

        private boolean d(String str, String str2, String str3) {
            try {
                k.a.a.a aVar = new k.a.a.a(str);
                if (aVar.f()) {
                    if (TextUtils.isEmpty(str3)) {
                        Log.e("TestOfflineService", "[Test-offline] zip is encrypted,but password is empty");
                        return false;
                    }
                    aVar.h(str3.toCharArray());
                }
                aVar.c(str2);
                return true;
            } catch (k.a.a.c.a e2) {
                Log.e("TestOfflineService", e2.getMessage());
                return false;
            }
        }

        @Override // com.jd.hybrid.downloader.b, com.jd.hybrid.downloader.e
        public void onEnd(FileResponse<File> fileResponse) {
            fileResponse.getData();
            DatabaseExecutors.getInstance().runOnIoThread(new Runnable
            /*  JADX ERROR: Method code generation error
                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x000f: INVOKE 
                  (wrap: com.jd.libs.hybrid.base.util.DatabaseExecutors : 0x0006: INVOKE  type: STATIC call: com.jd.libs.hybrid.base.util.DatabaseExecutors.getInstance():com.jd.libs.hybrid.base.util.DatabaseExecutors A[MD:():com.jd.libs.hybrid.base.util.DatabaseExecutors (m), WRAPPED] (LINE:2))
                  (wrap: java.lang.Runnable : 0x000c: CONSTRUCTOR 
                  (r2v0 'this' com.jd.libs.hybrid.offlineload.loader.TestOfflineService$MyDownloadsCallback A[IMMUTABLE_TYPE, THIS])
                  (r3 I:java.io.File A[DONT_GENERATE, DONT_INLINE, REMOVE])
                 A[MD:(com.jd.libs.hybrid.offlineload.loader.TestOfflineService$MyDownloadsCallback, java.io.File):void (m), WRAPPED] call: com.jd.libs.hybrid.offlineload.loader.a.<init>(com.jd.libs.hybrid.offlineload.loader.TestOfflineService$MyDownloadsCallback, java.io.File):void type: CONSTRUCTOR)
                 type: VIRTUAL call: com.jd.libs.hybrid.base.util.DatabaseExecutors.runOnIoThread(java.lang.Runnable):void A[MD:(java.lang.Runnable):void (m)] (LINE:2) in method: com.jd.libs.hybrid.offlineload.loader.TestOfflineService.MyDownloadsCallback.onEnd(com.jd.hybrid.downloader.FileResponse<java.io.File>):void, file: classes16.dex
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
                */
            /*
                this = this;
                java.lang.Object r3 = r3.getData()
                java.io.File r3 = (java.io.File) r3
                com.jd.libs.hybrid.base.util.DatabaseExecutors r0 = com.jd.libs.hybrid.base.util.DatabaseExecutors.getInstance()
                com.jd.libs.hybrid.offlineload.loader.a r1 = new com.jd.libs.hybrid.offlineload.loader.a
                r1.<init>()
                r0.runOnIoThread(r1)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jd.libs.hybrid.offlineload.loader.TestOfflineService.MyDownloadsCallback.onEnd(com.jd.hybrid.downloader.FileResponse):void");
        }

        @Override // com.jd.hybrid.downloader.b, com.jd.hybrid.downloader.e
        public void onError(FileError fileError) {
            int i2 = this.b;
            if (i2 < HybridSettings.HYBRID_DOWNLOAD_RETRY) {
                TestOfflineService.this.download(this.a, i2 + 1, this.f6070c);
                RetryFailInfo.removeOverRetry(this.a);
                return;
            }
            RetryFailInfo.addToOverRetry(this.a);
            if (Log.isDebug()) {
                Log.xLogEForDev("TestOfflineService", "\u4e0b\u8f7d\u6d4b\u8bd5\u5305\u5931\u8d25");
            }
            HybridInlineJsInterface.TestCallback testCallback = this.f6070c;
            if (testCallback != null) {
                testCallback.callback(3);
            }
        }
    }

    private TestOfflineService() {
        Context appContext = HybridSettings.getAppContext();
        this.a = appContext;
        this.b = OfflineDatabase.getInstance(appContext).getTestDao();
        this.f6069c = new HashMap();
    }

    public void d(TestOfflineEntity testOfflineEntity) {
        this.b.save(testOfflineEntity);
        if (this.f6069c == null) {
            this.f6069c = new HashMap();
        }
        this.f6069c.put(testOfflineEntity.getDocumentUrl(), testOfflineEntity);
    }

    private TestOfflineEntity e(String str, String str2, int i2) {
        Map<String, TestOfflineEntity> map = this.f6069c;
        TestOfflineEntity testOfflineEntity = (map == null || !map.containsKey(str2)) ? null : this.f6069c.get(str2);
        if (testOfflineEntity == null || !testOfflineEntity.isAvailable()) {
            if (i2 != -1) {
                testOfflineEntity = this.b.getByUrl(str2, i2);
            } else {
                testOfflineEntity = this.b.getByUrl(str2);
            }
        }
        if (testOfflineEntity == null) {
            Log.d("TestOfflineService", "[Offline-Inline--Test-file] NOT Found ,url:" + str2);
        } else {
            Log.d("TestOfflineService", "[Offline-Inline--Test-file] Found config for id: " + testOfflineEntity.getAppid() + ", url: " + testOfflineEntity.getDocumentUrl());
        }
        return testOfflineEntity;
    }

    /* renamed from: f */
    public /* synthetic */ void g(HybridInlineJsInterface.TestCallback testCallback) {
        try {
            OfflineFileUtils.deleteTestFiles(this.a);
            this.b.deleteAll();
            Map<String, TestOfflineEntity> map = this.f6069c;
            if (map != null) {
                map.clear();
            }
            if (testCallback != null) {
                testCallback.callback(1);
            }
        } catch (Exception e2) {
            if (testCallback != null) {
                testCallback.callback(0);
            }
            Log.e("TestOfflineService", e2);
        }
    }

    public static TestOfflineService getInstance() {
        if (d == null) {
            synchronized (TestOfflineService.class) {
                if (d == null) {
                    d = new TestOfflineService();
                }
            }
        }
        return d;
    }

    /* renamed from: h */
    public /* synthetic */ void i(String str, HybridInlineJsInterface.TestCallback testCallback) {
        TestOfflineEntity byId = this.b.getById(str);
        if (byId == null) {
            if (testCallback != null) {
                testCallback.callback(0);
                return;
            }
            return;
        }
        OfflineFileUtils.deleteEntityFile(byId);
        OfflineFileUtils.deleteZipFile(byId);
        this.b.delete(byId);
        Map<String, TestOfflineEntity> map = this.f6069c;
        if (map != null) {
            map.remove(str);
        }
        if (testCallback != null) {
            testCallback.callback(1);
        }
    }

    public void deleteAllTestConfig(final HybridInlineJsInterface.TestCallback testCallback) {
        DatabaseExecutors.getInstance().runOnIoThread(new Runnable() { // from class: com.jd.libs.hybrid.offlineload.loader.b
            {
                TestOfflineService.this = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                TestOfflineService.this.g(testCallback);
            }
        });
    }

    public void deleteTestConfig(final String str, final HybridInlineJsInterface.TestCallback testCallback) {
        DatabaseExecutors.getInstance().runOnIoThread(new Runnable() { // from class: com.jd.libs.hybrid.offlineload.loader.c
            {
                TestOfflineService.this = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                TestOfflineService.this.i(str, testCallback);
            }
        });
    }

    public void download(TestOfflineEntity testOfflineEntity, int i2, HybridInlineJsInterface.TestCallback testCallback) {
        com.jd.hybrid.downloader.c f2 = com.jd.hybrid.downloader.c.f();
        if (f2 == null) {
            if (testCallback != null) {
                testCallback.callback(3);
                return;
            }
            return;
        }
        OfflineEntityInfo fileInfo = testOfflineEntity.getFileInfo();
        d dVar = new d("\u9879\u76ee(" + testOfflineEntity.getAppid() + ")\u6d4b\u8bd5\u79bb\u7ebf\u5305", fileInfo.getUrl(), OfflineFileUtils.getTestZipRelativeDir(), OfflineFileUtils.generateFileName(fileInfo.getUrl()), false);
        dVar.q(0);
        dVar.n(testOfflineEntity.getAppid());
        dVar.m(new com.jd.hybrid.downloader.n.b(testOfflineEntity.getFileInfo().getMd5()));
        dVar.l(new MyDownloadsCallback(testOfflineEntity, i2, testCallback));
        if (f2.b(dVar) != null || testCallback == null) {
            return;
        }
        testCallback.callback(3);
    }

    public TestOfflineEntity getEntityByUrl(String str, String str2, int i2) {
        return e(str, str2, i2);
    }
}
