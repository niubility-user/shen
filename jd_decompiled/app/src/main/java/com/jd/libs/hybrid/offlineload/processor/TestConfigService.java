package com.jd.libs.hybrid.offlineload.processor;

import androidx.annotation.NonNull;
import com.jd.framework.json.JDJSON;
import com.jd.hybrid.downloader.FileError;
import com.jd.hybrid.downloader.FileResponse;
import com.jd.hybrid.downloader.c;
import com.jd.libs.hybrid.base.HybridSettings;
import com.jd.libs.hybrid.base.util.DatabaseExecutors;
import com.jd.libs.hybrid.base.util.Log;
import com.jd.libs.hybrid.offlineload.db.TestOfflineDataStore;
import com.jd.libs.hybrid.offlineload.entity.OfflineEntityInfo;
import com.jd.libs.hybrid.offlineload.entity.TestModule;
import com.jd.libs.hybrid.offlineload.entity.TestOfflineEntity;
import com.jd.libs.hybrid.offlineload.jsimpl.HybridInlineJsInterface;
import com.jd.libs.hybrid.offlineload.loader.TestOfflineLoader;
import com.jd.libs.hybrid.offlineload.loader.TestOfflineService;
import com.jd.libs.hybrid.offlineload.processor.ModuleUnzipProcessor;
import com.jd.libs.hybrid.offlineload.processor.TestConfigService;
import com.jd.libs.hybrid.offlineload.utils.ModuleHelper;
import com.jd.libs.hybrid.offlineload.utils.OfflineFileHelper;
import java.io.File;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public class TestConfigService {

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes16.dex */
    public static class ModuleDownloadCallback extends com.jd.hybrid.downloader.b {
        private final TestModule a;
        private final String b;

        /* renamed from: c  reason: collision with root package name */
        private ProcessCallback f6096c;

        ModuleDownloadCallback(TestModule testModule, String str, ProcessCallback processCallback) {
            this.a = testModule;
            this.b = str;
            this.f6096c = processCallback;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: c  reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void d(File file, float f2) {
            ModuleUnzipProcessor.ProcessCallback<TestModule> processCallback = new ModuleUnzipProcessor.ProcessCallback<TestModule>() { // from class: com.jd.libs.hybrid.offlineload.processor.TestConfigService.ModuleDownloadCallback.1
                @Override // com.jd.libs.hybrid.offlineload.processor.ModuleUnzipProcessor.ProcessCallback
                public void onProcessFail(boolean z, boolean z2, Throwable th) {
                    if (ModuleDownloadCallback.this.f6096c != null) {
                        ModuleDownloadCallback.this.f6096c.onProcessFail(ModuleDownloadCallback.this.a);
                    }
                }

                @Override // com.jd.libs.hybrid.offlineload.processor.ModuleUnzipProcessor.ProcessCallback
                public void onProcessSuccess(TestModule testModule) {
                    if (ModuleDownloadCallback.this.f6096c != null) {
                        ModuleDownloadCallback.this.f6096c.onProcessSuccess(testModule);
                    }
                }
            };
            String testSourceDir = OfflineFileHelper.getTestSourceDir(OfflineFileHelper.generateSaveDirName(this.a.getAppid()));
            ModuleUnzipProcessor moduleUnzipProcessor = new ModuleUnzipProcessor(this.a, file, this.b, false, f2);
            moduleUnzipProcessor.e(processCallback);
            moduleUnzipProcessor.f(testSourceDir);
            moduleUnzipProcessor.processZipFile();
        }

        @Override // com.jd.hybrid.downloader.b, com.jd.hybrid.downloader.e
        public void onEnd(FileResponse<File> fileResponse) {
            final File data = fileResponse.getData();
            final float e2 = com.jd.hybrid.downloader.c.e(data);
            DatabaseExecutors.getInstance().runOnIoThread(new Runnable() { // from class: com.jd.libs.hybrid.offlineload.processor.g
                @Override // java.lang.Runnable
                public final void run() {
                    TestConfigService.ModuleDownloadCallback.this.d(data, e2);
                }
            });
        }

        @Override // com.jd.hybrid.downloader.b, com.jd.hybrid.downloader.e
        public void onError(FileError fileError) {
            String str;
            if (fileError instanceof c.C0086c) {
                str = "\u6587\u4ef6\u6821\u9a8c\u9519\u8bef\uff0c" + fileError.getMessage();
            } else {
                str = "\u7f51\u7edc\u9519\u8bef\uff0c" + fileError.getMessage();
            }
            if (Log.isDebug()) {
                Log.xLogE("TestConfigService", "\u9879\u76ee(id:" + this.a.getAppid() + ", url:" + this.a.getOriginalUrl() + ")\u7684\u6d4b\u8bd5\u5305\u4e0b\u8f7d\u5931\u8d25\uff0c\u539f\u56e0\uff1a" + str);
            }
            ProcessCallback processCallback = this.f6096c;
            if (processCallback != null) {
                processCallback.onProcessFail(this.a);
            }
        }

        @Override // com.jd.hybrid.downloader.b, com.jd.hybrid.downloader.e
        public void onStart() {
            super.onStart();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public interface ProcessCallback {
        void onProcessFail(TestModule testModule);

        void onProcessSuccess(TestModule testModule);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(TestModule testModule, ProcessCallback processCallback) {
        com.jd.hybrid.downloader.c f2 = com.jd.hybrid.downloader.c.f();
        if (f2 == null) {
            return;
        }
        OfflineEntityInfo fileInfo = testModule.getFileInfo();
        boolean z = fileInfo != null && fileInfo.getFileType().equalsIgnoreCase(OfflineEntityInfo.FILE_TYPE_ZIP2);
        String url = fileInfo.getUrl();
        com.jd.hybrid.downloader.d dVar = new com.jd.hybrid.downloader.d("\u9879\u76ee(" + testModule.getAppid() + ")\u6d4b\u8bd5\u5305\u4e0b\u8f7d", url, OfflineFileHelper.getTestZipRelativeDir(), OfflineFileHelper.generateFileName(url), false, (int) (testModule.getPriority() * 1000.0f), !z);
        dVar.n(testModule.getAppid());
        dVar.m(new com.jd.hybrid.downloader.n.b(testModule.getFileInfo().getMd5()));
        dVar.l(new ModuleDownloadCallback(testModule, url, processCallback));
        if (Log.isDebug()) {
            Log.xLogD("TestConfigService", "\u9879\u76ee(id:" + testModule.getAppid() + "\uff0curl:" + testModule.getOriginalUrl() + ")\u9700\u4e0b\u8f7d/\u66f4\u65b0\u79bb\u7ebf\u6587\u4ef6\uff0c\u5df2\u52a0\u5165\u4e0b\u8f7d\u5217\u8868\uff0c\u4e0b\u8f7d\u5730\u5740:" + url + "\uff0c\u8bf7\u7b49\u5f85\u4e0b\u8f7d\u5b8c\u6bd5\u540e\u4f7f\u7528\u3002");
        }
        f2.c(Collections.singletonList(dVar), true);
    }

    public static void deleteAllTestConfig(HybridInlineJsInterface.TestCallback testCallback) {
        Map<String, TestModule> all = TestOfflineDataStore.getInstance().getAll();
        if (all != null && !all.isEmpty()) {
            Iterator<TestModule> it = all.values().iterator();
            while (it.hasNext()) {
                OfflineFileHelper.deleteEntityFile(it.next());
            }
            TestOfflineDataStore.getInstance().deleteAll();
        }
        TestOfflineService.getInstance().deleteAllTestConfig(testCallback);
    }

    public static void deleteTestConfig(@NonNull String str, HybridInlineJsInterface.TestCallback testCallback) {
        TestModule testModule = (TestModule) TestOfflineDataStore.getInstance().get(str);
        if (testModule != null) {
            ModuleHelper.deleteModule(testModule);
            if (testCallback != null) {
                testCallback.callback(1);
                return;
            }
            return;
        }
        TestOfflineService.getInstance().deleteTestConfig(str, testCallback);
    }

    public static void requestTestOffline(String str, final HybridInlineJsInterface.TestCallback testCallback) {
        new TestOfflineLoader(HybridSettings.getAppContext()).requestDebugData(str, new TestOfflineLoader.ConfigCallback() { // from class: com.jd.libs.hybrid.offlineload.processor.TestConfigService.1
            @Override // com.jd.libs.hybrid.offlineload.loader.TestOfflineLoader.ConfigCallback
            public void onFail(String str2) {
                if (Log.isDebug()) {
                    Log.e("TestConfigService", str2);
                    Log.xLogEForDev("TestConfigService", "\u83b7\u53d6\u6d4b\u8bd5\u5305\u6570\u636e\u5931\u8d25\uff0c\u539f\u56e0: " + str2);
                }
                HybridInlineJsInterface.TestCallback testCallback2 = HybridInlineJsInterface.TestCallback.this;
                if (testCallback2 != null) {
                    testCallback2.callback(-3);
                }
            }

            @Override // com.jd.libs.hybrid.offlineload.loader.TestOfflineLoader.ConfigCallback
            public void onSuccess(String str2) {
                TestOfflineEntity testOfflineEntity;
                if (Log.isDebug()) {
                    Log.xLogD("TestConfigService", "\u6210\u529f\u83b7\u53d6\u5230\u6d4b\u8bd5\u5305\u6570\u636e", str2);
                }
                try {
                    JSONObject jSONObject = new JSONObject(str2);
                    int optInt = jSONObject.optInt("type", 0);
                    TestModule testModule = null;
                    if (optInt == 4) {
                        TestModule fromJson = new TestModule().fromJson(jSONObject);
                        ModuleHelper.resetUrl(fromJson);
                        testModule = fromJson;
                        testOfflineEntity = null;
                    } else {
                        if (optInt != 3 && optInt != 2 && optInt != 1) {
                            throw new IllegalArgumentException("Type is illegal, type = " + optInt);
                        }
                        testOfflineEntity = (TestOfflineEntity) JDJSON.parseObject(str2, TestOfflineEntity.class);
                    }
                    if (testModule != null) {
                        if (testModule.useful()) {
                            TestConfigService.b(testModule, new ProcessCallback() { // from class: com.jd.libs.hybrid.offlineload.processor.TestConfigService.1.1
                                @Override // com.jd.libs.hybrid.offlineload.processor.TestConfigService.ProcessCallback
                                public void onProcessFail(TestModule testModule2) {
                                    if (Log.isDebug()) {
                                        Log.d("TestConfigService", "[Test-offline](install) Fail to download and update module config for id: " + testModule2.getAppid() + ", url: " + testModule2.getOriginalUrl());
                                    }
                                    HybridInlineJsInterface.TestCallback testCallback2 = HybridInlineJsInterface.TestCallback.this;
                                    if (testCallback2 != null) {
                                        testCallback2.callback(3);
                                    }
                                }

                                @Override // com.jd.libs.hybrid.offlineload.processor.TestConfigService.ProcessCallback
                                public void onProcessSuccess(TestModule testModule2) {
                                    OfflineFileHelper.deleteEntityOldFile(testModule2);
                                    if (Log.isDebug()) {
                                        Log.xLogD("TestConfigService", "\u9879\u76ee(id:" + testModule2.getAppid() + ", url:" + testModule2.getOriginalUrl() + ")\u7684\u6d4b\u8bd5\u5305\u5df2\u6210\u529f\u4e0b\u8f7d\u548c\u89e3\u538b\uff0c\u5df2\u53ef\u4f7f\u7528\u3002");
                                        StringBuilder sb = new StringBuilder();
                                        sb.append("[Test-offline](install) Download and update module config success for id: ");
                                        sb.append(testModule2.getAppid());
                                        sb.append(", url: ");
                                        sb.append(testModule2.getOriginalUrl());
                                        Log.d("TestConfigService", sb.toString());
                                    }
                                    ModuleHelper.saveModule(testModule2);
                                    HybridInlineJsInterface.TestCallback testCallback2 = HybridInlineJsInterface.TestCallback.this;
                                    if (testCallback2 != null) {
                                        testCallback2.callback(1);
                                    }
                                }
                            });
                        } else {
                            onFail("\u914d\u7f6e\u4e0d\u5408\u6cd5");
                        }
                    } else if (testOfflineEntity != null) {
                        if (!testOfflineEntity.useful()) {
                            onFail("\u914d\u7f6e\u4e0d\u5408\u6cd5");
                        } else {
                            TestOfflineService.getInstance().download(testOfflineEntity, 0, HybridInlineJsInterface.TestCallback.this);
                        }
                    }
                } catch (Exception e2) {
                    Log.e("TestConfigService", e2);
                    onFail("\u5185\u90e8\u9519\u8bef\uff1a" + e2.getMessage());
                }
            }
        });
    }
}
