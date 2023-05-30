package com.jd.libs.hybrid.offlineload.loader;

import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;
import com.jd.hybrid.downloader.FileError;
import com.jd.hybrid.downloader.FileResponse;
import com.jd.hybrid.downloader.c;
import com.jd.hybrid.downloader.d;
import com.jd.hybrid.downloader.p.a;
import com.jd.libs.hybrid.base.HybridSettings;
import com.jd.libs.hybrid.base.entity.IInterfaceCheck;
import com.jd.libs.hybrid.base.util.CommonUtils;
import com.jd.libs.hybrid.base.util.DatabaseExecutors;
import com.jd.libs.hybrid.base.util.Log;
import com.jd.libs.hybrid.offlineload.OfflineLoadController;
import com.jd.libs.hybrid.offlineload.db.CommonEntityDao;
import com.jd.libs.hybrid.offlineload.db.CommonFileDatabase;
import com.jd.libs.hybrid.offlineload.entity.CommonEntity;
import com.jd.libs.hybrid.offlineload.entity.CommonFile;
import com.jd.libs.hybrid.offlineload.temp.DownloadFileDisable;
import com.jd.libs.hybrid.offlineload.utils.CommonFileUtils;
import com.jd.libs.hybrid.offlineload.utils.OfflineExceptionUtils;
import com.jd.libs.hybrid.offlineload.utils.OfflineFileUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

/* loaded from: classes16.dex */
public class CommonFileService {
    private final Context a;
    private final CommonEntityDao b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class MyDownloadsCallback extends com.jd.hybrid.downloader.b {
        private final CommonEntity a;
        private final int b;

        /* renamed from: c  reason: collision with root package name */
        private long f6025c;

        MyDownloadsCallback(CommonEntity commonEntity, int i2) {
            this.a = commonEntity;
            this.b = i2;
        }

        @Override // com.jd.hybrid.downloader.b, com.jd.hybrid.downloader.e
        public void onEnd(FileResponse<File> fileResponse) {
            final File data = fileResponse.getData();
            com.jd.hybrid.downloader.c.e(data);
            fileResponse.getHeaders();
            DatabaseExecutors.getInstance().threadIO().execute(new Runnable
            /*  JADX ERROR: Method code generation error
                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x001b: INVOKE 
                  (wrap: java.util.concurrent.Executor : 0x0012: INVOKE 
                  (wrap: com.jd.libs.hybrid.base.util.DatabaseExecutors : 0x000e: INVOKE  type: STATIC call: com.jd.libs.hybrid.base.util.DatabaseExecutors.getInstance():com.jd.libs.hybrid.base.util.DatabaseExecutors A[MD:():com.jd.libs.hybrid.base.util.DatabaseExecutors (m), WRAPPED] (LINE:4))
                 type: VIRTUAL call: com.jd.libs.hybrid.base.util.DatabaseExecutors.threadIO():java.util.concurrent.Executor A[MD:():java.util.concurrent.Executor (m), WRAPPED] (LINE:4))
                  (wrap: java.lang.Runnable : 0x0018: CONSTRUCTOR 
                  (r4v0 'this' com.jd.libs.hybrid.offlineload.loader.CommonFileService$MyDownloadsCallback A[IMMUTABLE_TYPE, THIS])
                  (r0v1 'data' java.io.File A[DONT_INLINE])
                  (r5 I:java.util.Map A[DONT_GENERATE, DONT_INLINE, REMOVE])
                  (r1 I:float A[DONT_GENERATE, DONT_INLINE, REMOVE])
                 A[MD:(com.jd.libs.hybrid.offlineload.loader.CommonFileService$MyDownloadsCallback, java.io.File, java.util.Map, float):void (m), WRAPPED] call: com.jd.libs.hybrid.offlineload.loader.CommonFileService.MyDownloadsCallback.1.<init>(com.jd.libs.hybrid.offlineload.loader.CommonFileService$MyDownloadsCallback, java.io.File, java.util.Map, float):void type: CONSTRUCTOR)
                 type: INTERFACE call: java.util.concurrent.Executor.execute(java.lang.Runnable):void A[MD:(java.lang.Runnable):void (c)] (LINE:4) in method: com.jd.libs.hybrid.offlineload.loader.CommonFileService.MyDownloadsCallback.onEnd(com.jd.hybrid.downloader.FileResponse<java.io.File>):void, file: classes16.dex
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
                java.lang.Object r0 = r5.getData()
                java.io.File r0 = (java.io.File) r0
                float r1 = com.jd.hybrid.downloader.c.e(r0)
                java.util.Map r5 = r5.getHeaders()
                com.jd.libs.hybrid.base.util.DatabaseExecutors r2 = com.jd.libs.hybrid.base.util.DatabaseExecutors.getInstance()
                java.util.concurrent.Executor r2 = r2.threadIO()
                com.jd.libs.hybrid.offlineload.loader.CommonFileService$MyDownloadsCallback$1 r3 = new com.jd.libs.hybrid.offlineload.loader.CommonFileService$MyDownloadsCallback$1
                r3.<init>()
                r2.execute(r3)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jd.libs.hybrid.offlineload.loader.CommonFileService.MyDownloadsCallback.onEnd(com.jd.hybrid.downloader.FileResponse):void");
        }

        @Override // com.jd.hybrid.downloader.b, com.jd.hybrid.downloader.e
        public void onError(FileError fileError) {
            String str;
            a.C0087a c0087a = new a.C0087a();
            c0087a.a = this.a.getId();
            c0087a.f2700e = false;
            c0087a.f2702g = 2;
            c0087a.f2703h = this.a.getUrl();
            if (fileError instanceof c.C0086c) {
                c0087a.b = ((c.C0086c) fileError).fileSizeInKB;
                c0087a.f2699c = "0";
                c0087a.f2701f = "-2";
                OfflineExceptionUtils.reportDownloadError(OfflineExceptionUtils.ERR_MSG_CHECK, "[Common]download-onError-FileCheckError", this.a.getId(), this.a.getUrl(), fileError.getMessage());
                str = "\u6587\u4ef6\u6821\u9a8c\u9519\u8bef\uff0c" + fileError.getMessage();
            } else {
                c0087a.f2699c = (this.b != 0 || HybridSettings.HYBRID_DOWNLOAD_RETRY < 2) ? "-1" : "-2";
                c0087a.d = -1;
                c0087a.f2701f = "";
                OfflineExceptionUtils.reportDownloadError(fileError.getStatusCode(), OfflineExceptionUtils.ERR_MSG_NET, "[Common]download-onError", this.a.getId(), this.a.getUrl(), fileError.getMessage());
                str = "\u7f51\u7edc\u9519\u8bef\uff0ccode = " + fileError.getStatusCode() + ", msg = " + fileError.getMessage();
            }
            if (Log.isDebug()) {
                Log.xLogE("CommonFileService", "\u516c\u5171\u8d44\u6e90(id:" + this.a.getId() + ", url:" + this.a.getUrl() + ")\u4e0b\u8f7d/\u66f4\u65b0\u5931\u8d25\uff0c\u539f\u56e0\uff1a" + str);
            }
            com.jd.hybrid.downloader.p.a.b(c0087a);
            if (this.a.isHeaderRequest()) {
                if (Log.isDebug()) {
                    Log.xLogEForDev("CommonFileService", "\u516c\u5171\u8d44\u6e90(" + this.a.getUrl() + ")\u66f4\u65b0Header\u5931\u8d25\uff0c\u5220\u9664\u6587\u4ef6\u540e\u5c06\u91cd\u65b0\u4e0b\u8f7d\u3002");
                }
                DatabaseExecutors.getInstance().threadIO().execute(new Runnable() { // from class: com.jd.libs.hybrid.offlineload.loader.CommonFileService.MyDownloadsCallback.2
                    @Override // java.lang.Runnable
                    public void run() {
                        MyDownloadsCallback.this.a.setHeaderRequest(false);
                        CommonFileUtils.deleteEntityFile(MyDownloadsCallback.this.a);
                        CommonFileService.this.b.update(MyDownloadsCallback.this.a);
                        MyDownloadsCallback myDownloadsCallback = MyDownloadsCallback.this;
                        CommonFileService.this.i(myDownloadsCallback.a, 0);
                    }
                });
            } else if (this.b < HybridSettings.HYBRID_DOWNLOAD_RETRY) {
                if (Log.isDebug()) {
                    Log.xLogEForDev("CommonFileService", "\u516c\u5171\u8d44\u6e90(" + this.a.getUrl() + ")\u4e0b\u8f7d\u5931\u8d25\uff0c\u52a0\u5165\u91cd\u8bd5\u5217\u8868\u3002");
                }
                CommonFileService.this.i(this.a, this.b + 1);
                RetryFailInfo.removeOverRetry(this.a);
            } else {
                RetryFailInfo.addToOverRetry(this.a);
                CommonFileUtils.deleteEntityFile(this.a);
            }
        }

        @Override // com.jd.hybrid.downloader.b, com.jd.hybrid.downloader.e
        public void onStart() {
            super.onStart();
            this.f6025c = SystemClock.elapsedRealtime();
        }
    }

    public CommonFileService(Context context) {
        this.a = context.getApplicationContext();
        this.b = CommonFileDatabase.getInstance(context).getDao();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h(final CommonEntity commonEntity) {
        DatabaseExecutors.getInstance().threadIO().execute(new Runnable() { // from class: com.jd.libs.hybrid.offlineload.loader.CommonFileService.3
            @Override // java.lang.Runnable
            public void run() {
                try {
                    CommonFileUtils.deleteEntityFile(commonEntity);
                    CommonFileService.this.b.delete(commonEntity);
                } catch (Exception e2) {
                    Log.e("CommonFileService", e2);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i(CommonEntity commonEntity, int i2) {
        j(Collections.singletonList(commonEntity), i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j(List<CommonEntity> list, int i2) {
        if (DownloadFileDisable.commonDownloadDisable) {
            Log.d("CommonFileService", "Downloading common file function is disable by switch.");
            Log.xLogDForDev("CommonFileService", "\u516c\u5171\u8d44\u6e90\u4e0b\u8f7d\u529f\u80fd\u5df2\u5173\u95ed\uff0c\u53ea\u66f4\u65b0\u914d\u7f6e\uff0c\u4e0d\u4e0b\u8f7d\u65b0\u6587\u4ef6\uff0c\u8bf7\u8054\u7cfb\u7ba1\u7406\u5458");
            return;
        }
        com.jd.hybrid.downloader.c f2 = com.jd.hybrid.downloader.c.f();
        if (f2 == null) {
            return;
        }
        ArrayList arrayList = new ArrayList(list.size());
        StringBuilder sb = null;
        for (CommonEntity commonEntity : list) {
            String url = commonEntity.getUrl();
            String fileNameFromPath = commonEntity.isHeaderRequest() ? CommonFileUtils.getFileNameFromPath(commonEntity.getFileDetail().getPath()) : CommonFileUtils.generateFileName(url);
            StringBuilder sb2 = new StringBuilder();
            sb2.append("\u516c\u5171\u8d44\u6e90(");
            sb2.append(url);
            sb2.append(")");
            sb2.append(commonEntity.isHeaderRequest() ? "\u66f4\u65b0Header" : "\u4e0b\u8f7d\u6587\u4ef6");
            d dVar = new d(sb2.toString(), url, CommonFileUtils.getSaveRelativeDir(commonEntity.getId()), fileNameFromPath, false, Integer.MAX_VALUE, true);
            dVar.n(commonEntity.getId());
            dVar.q(0);
            if (commonEntity.isHeaderRequest()) {
                dVar.p();
            }
            dVar.m(new com.jd.hybrid.downloader.n.b(commonEntity.getMd5()));
            dVar.l(new MyDownloadsCallback(commonEntity, i2));
            arrayList.add(dVar);
            if (Log.isDebug()) {
                if (sb == null) {
                    sb = new StringBuilder();
                } else {
                    sb.setLength(0);
                }
                sb.append("\u516c\u5171\u8d44\u6e90(id:");
                sb.append(commonEntity.getId());
                sb.append("\uff0curl:");
                sb.append(url);
                if (commonEntity.isHeaderRequest()) {
                    sb.append(")\u9700\u66f4\u65b0Header\u4fe1\u606f\uff0c\u5df2\u52a0\u5165\u8bf7\u6c42\u5217\u8868\uff0c\u8bf7\u7b49\u5f85\u4e0b\u8f7d\u5b8c\u6bd5\u540e\u4f7f\u7528\u3002");
                } else {
                    sb.append(")\u9700\u4e0b\u8f7d\u6216\u66f4\u65b0\u79bb\u7ebf\u6587\u4ef6\uff0c\u5df2\u52a0\u5165\u4e0b\u8f7d\u5217\u8868\uff0c\u8bf7\u7b49\u5f85\u4fe1\u606f\u66f4\u65b0\u5b8c\u6bd5\u540e\u4f7f\u7528\u3002");
                }
                Log.xLogD("CommonFileService", sb.toString());
            }
        }
        f2.c(arrayList, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<CommonEntity> k(OfflineLoadController.ChangeEntityLists<CommonEntity> changeEntityLists) {
        ArrayList<CommonEntity> arrayList;
        if (changeEntityLists != null) {
            List<CommonEntity> list = changeEntityLists.addList;
            int size = list != null ? list.size() : 0;
            List<CommonEntity> list2 = changeEntityLists.updateList;
            int size2 = list2 != null ? list2.size() : 0;
            if (size > 0 || size2 > 0) {
                arrayList = new ArrayList(size + size2);
                if (size > 0) {
                    arrayList.addAll(changeEntityLists.addList);
                }
                if (size2 > 0) {
                    arrayList.addAll(changeEntityLists.updateList);
                }
                if (arrayList == null && !arrayList.isEmpty()) {
                    ArrayList arrayList2 = new ArrayList();
                    for (CommonEntity commonEntity : arrayList) {
                        if (!commonEntity.isAvailable()) {
                            if (!RetryFailInfo.hasInOverRetry(commonEntity)) {
                                arrayList2.add(commonEntity);
                                if (HybridSettings.isDebug()) {
                                    Log.d("CommonFileService", "[Common-file] Need to download, url: " + commonEntity.getUrl());
                                }
                            } else if (HybridSettings.isDebug()) {
                                Log.d("CommonFileService", "[Common-file] Need to download but it has exceed the max retry count, url: " + commonEntity.getUrl());
                            }
                        } else if (HybridSettings.isDebug()) {
                            Log.d("CommonFileService", "[Common-file] Do nothing for existed common file, url: " + commonEntity.getUrl());
                        }
                    }
                    return arrayList2;
                }
                Log.d("CommonFileService", "[Common-file] final config list is empty, no need to download.");
                return null;
            }
        }
        arrayList = null;
        if (arrayList == null) {
        }
        Log.d("CommonFileService", "[Common-file] final config list is empty, no need to download.");
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public OfflineLoadController.ChangeEntityLists<CommonEntity> l(List<CommonEntity> list, List<CommonEntity> list2) {
        OfflineLoadController.ChangeEntityLists<CommonEntity> changeEntityLists = new OfflineLoadController.ChangeEntityLists<>();
        List<CommonEntity> all = this.b.getAll();
        HashMap hashMap = new HashMap(list.size());
        HashMap hashMap2 = new HashMap(all.size());
        for (CommonEntity commonEntity : list) {
            hashMap.put(commonEntity.getId(), commonEntity);
        }
        HashSet hashSet = new HashSet(list2.size());
        for (CommonEntity commonEntity2 : list2) {
            if (!TextUtils.isEmpty(commonEntity2.getId())) {
                hashSet.add(commonEntity2.getId());
            }
        }
        ArrayList arrayList = new ArrayList();
        for (CommonEntity commonEntity3 : all) {
            hashMap2.put(commonEntity3.getId(), commonEntity3);
            CommonEntity commonEntity4 = (CommonEntity) hashMap.get(commonEntity3.getId());
            if (hashSet.contains(commonEntity3.getId()) || commonEntity4 == null) {
                Log.d("CommonFileService", "[Common-file] Delete DB config and local files, because server's config list doesn't contain it or it is corrupted. " + commonEntity3.getUrl());
                CommonFileUtils.deleteEntityFile(commonEntity3);
                arrayList.add(commonEntity3);
            }
        }
        changeEntityLists.deleteList = arrayList;
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        boolean z = false;
        for (CommonEntity commonEntity5 : list) {
            CommonEntity commonEntity6 = (CommonEntity) hashMap2.get(commonEntity5.getId());
            if (commonEntity6 == null) {
                Log.d("CommonFileService", "[Common-file] Insert new config into DB for " + commonEntity5.getUrl());
                commonEntity5.setCreateTime();
                commonEntity5.setAvailable(false);
                arrayList2.add(commonEntity5);
            } else {
                if (commonEntity6.isAvailable()) {
                    if (!z && commonEntity6.getFileDetail() != null && !OfflineFileUtils.isOldFileDir(commonEntity6.getFileDetail().getPath())) {
                        if (!OfflineFileUtils.checkDirHasFiles(CommonFileUtils.HYBRID_COMMON_FILE_DIR)) {
                            OfflineExceptionUtils.reportDownloadError(OfflineExceptionUtils.ERR_MSG_CHECK, "checkRootDirExist-Common", (String) null, (String) null, "local list = " + hashMap2.toString());
                        }
                        z = true;
                    }
                    boolean isFileChanged = commonEntity6.isFileChanged();
                    if (!isFileChanged && commonEntity5.getVersionCode() == commonEntity6.getVersionCode()) {
                        commonEntity5.copyLocalFileInfoFromOld(commonEntity6);
                    } else {
                        if (!isFileChanged && commonEntity5.getMd5().equals(commonEntity6.getMd5())) {
                            Log.d("CommonFileService", String.format(Locale.getDefault(), "[Common-file] Fetch local file's new header because versions differs but has same md5, %s, ver: old=%d, new=%d", commonEntity6.getUrl(), Integer.valueOf(commonEntity6.getVersionCode()), Integer.valueOf(commonEntity5.getVersionCode())));
                            commonEntity5.copyLocalFileInfoFromOld(commonEntity6);
                            commonEntity5.setHeaderRequest(true);
                        } else {
                            Log.d("CommonFileService", String.format(Locale.getDefault(), "[Common-file] Delete local file because versions differs or file changed, %s, ver: old=%d, new=%d", commonEntity6.getUrl(), Integer.valueOf(commonEntity6.getVersionCode()), Integer.valueOf(commonEntity5.getVersionCode())));
                            CommonFileUtils.deleteEntityFile(commonEntity6);
                        }
                        commonEntity6.setAvailable(false);
                        commonEntity5.setAvailable(false);
                    }
                }
                commonEntity5.copyLocalInfoFromOld(commonEntity6);
                Log.d("CommonFileService", "[Common-file] Update DB config for " + commonEntity6.getUrl());
                arrayList3.add(commonEntity5);
            }
        }
        changeEntityLists.addList = arrayList2;
        changeEntityLists.updateList = arrayList3;
        return changeEntityLists;
    }

    public void deleteAllDownloaded() {
        DatabaseExecutors.getInstance().threadIO().execute(new Runnable() { // from class: com.jd.libs.hybrid.offlineload.loader.CommonFileService.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    CommonFileUtils.deleteDownloadedFiles(CommonFileService.this.a);
                    CommonFileService.this.b.deleteAll();
                } catch (Exception e2) {
                    Log.e("CommonFileService", e2);
                }
            }
        });
    }

    public void getAllEntities(final OfflineLoadController.ConfigCallback<List<CommonFile>> configCallback) {
        DatabaseExecutors.getInstance().threadIO().execute(new Runnable() { // from class: com.jd.libs.hybrid.offlineload.loader.CommonFileService.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (!HybridSettings.isDownloadedOfflineDisable()) {
                        List<CommonEntity> all = CommonFileService.this.b.getAll();
                        if (all != null && !all.isEmpty()) {
                            ArrayList arrayList = new ArrayList(all.size());
                            for (CommonEntity commonEntity : all) {
                                if (commonEntity.isAvailable()) {
                                    if (commonEntity.isFileChanged()) {
                                        Log.d("CommonFileService", "[Common-file] Delete file cuz local file have been changed for " + commonEntity.getUrl());
                                        if (Log.isDebug()) {
                                            Log.xLogEForDev("CommonFileService", "\u516c\u5171\u8d44\u6e90\u65e0\u6cd5\u4f7f\u7528\uff0c\u6587\u4ef6\u6821\u9a8c\u5931\u8d25(" + commonEntity.getUrl() + ")");
                                        }
                                        CommonFileService.this.h(commonEntity);
                                    } else {
                                        Log.d("[Common-file] Local common file [found] for " + commonEntity.getUrl());
                                        arrayList.add(new CommonFile.Builder().setUrl(commonEntity.getUrl()).setFilePath(commonEntity.getFileDetail() != null ? commonEntity.getFileDetail().getPath() : null).setVersion(commonEntity.getVersionCode()).setAvailable(true).setHeaderParams(commonEntity.getHeadersMap()).setCanMatchImg(CommonUtils.getBinarySwitch(commonEntity.getBConfigCommon(), 1)).build());
                                    }
                                } else {
                                    Log.d("CommonFileService", "[Common-file] Local file NOT-Available for " + commonEntity.getUrl());
                                    arrayList.add(new CommonFile.Builder().setUrl(commonEntity.getUrl()).setFilePath(commonEntity.getFileDetail() != null ? commonEntity.getFileDetail().getPath() : null).setVersion(commonEntity.getVersionCode()).setAvailable(false).setHeaderParams(commonEntity.getHeadersMap()).setCanMatchImg(CommonUtils.getBinarySwitch(commonEntity.getBConfigCommon(), 1)).build());
                                }
                            }
                            configCallback.onCacheCallback(arrayList, false);
                            return;
                        }
                        Log.d("CommonFileService", "[Common-file] no config in DB");
                        configCallback.onCacheCallback(null, false);
                        return;
                    }
                    configCallback.onCacheCallback(null, false);
                } catch (Exception e2) {
                    Log.e("CommonFileService", e2);
                    OfflineExceptionUtils.reportDownloadCodeError("[Common]getAllEntities", null, null, e2);
                }
            }
        });
    }

    public void onAllChanged(final List<CommonEntity> list) {
        DatabaseExecutors.getInstance().threadIO().execute(new Runnable() { // from class: com.jd.libs.hybrid.offlineload.loader.CommonFileService.4
            @Override // java.lang.Runnable
            public void run() {
                try {
                    OfflineLoadController.ChangeEntityLists l2 = CommonFileService.this.l(list, IInterfaceCheck.Companion.removeUseless(list));
                    List k2 = CommonFileService.this.k(l2);
                    if (l2 != null) {
                        if (l2.deleteList != null) {
                            CommonFileService.this.b.delete((List<CommonEntity>) l2.deleteList);
                        }
                        if (l2.addList != null) {
                            CommonFileService.this.b.save((List<CommonEntity>) l2.addList);
                        }
                        if (l2.updateList != null) {
                            CommonFileService.this.b.update((List<CommonEntity>) l2.updateList);
                        }
                    }
                    if (k2 != null && !k2.isEmpty()) {
                        CommonFileService.this.j(k2, 0);
                        return;
                    }
                    Log.d("CommonFileService", "[Common-file] No new file need to download");
                } catch (Exception e2) {
                    Log.e("CommonFileService", e2);
                    OfflineExceptionUtils.reportDownloadCodeError("[Common]onAllChanged", null, null, e2);
                }
            }
        });
    }
}
