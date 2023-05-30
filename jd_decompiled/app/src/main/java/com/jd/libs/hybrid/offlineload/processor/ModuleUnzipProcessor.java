package com.jd.libs.hybrid.offlineload.processor;

import android.text.TextUtils;
import android.util.Pair;
import com.jd.hybrid.downloader.p.a;
import com.jd.libs.hybrid.base.HybridSettings;
import com.jd.libs.hybrid.base.util.Log;
import com.jd.libs.hybrid.offlineload.entity.Module;
import com.jd.libs.hybrid.offlineload.entity.OfflineEntityInfo;
import com.jd.libs.hybrid.offlineload.utils.FileUtils;
import com.jd.libs.hybrid.offlineload.utils.GraySwitch;
import com.jd.libs.hybrid.offlineload.utils.OfflineExceptionUtils;
import com.jd.libs.hybrid.offlineload.utils.OfflineFileHelper;
import com.tencent.xweb.util.BSpatch;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/* loaded from: classes16.dex */
public class ModuleUnzipProcessor<T extends Module> {
    private final T a;
    private final String b;

    /* renamed from: c */
    private final File f6091c;
    private final float d;

    /* renamed from: e */
    private final String f6092e;

    /* renamed from: f */
    private final boolean f6093f;

    /* renamed from: g */
    private String f6094g;

    /* renamed from: h */
    private ProcessCallback<T> f6095h;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public interface ProcessCallback<T extends Module> {
        void onProcessFail(boolean z, boolean z2, Throwable th);

        void onProcessSuccess(T t);
    }

    public ModuleUnzipProcessor(T t, File file, String str, boolean z, float f2) {
        this.a = t;
        this.b = t.getAppid();
        this.f6091c = file;
        this.f6092e = str;
        this.d = f2;
        this.f6093f = z;
    }

    private String a(File file) {
        String[] list;
        if (file.exists() && file.isDirectory() && (list = file.list()) != null && list.length > 0) {
            return FileUtils.getStringFromFile(HybridSettings.getAppContext(), new File(file, "resource.json"));
        }
        return null;
    }

    private boolean b(File file) {
        return new com.jd.hybrid.downloader.n.b(this.a.getFileInfo().getMd5()).a(file);
    }

    private void c(int i2, float f2, String str) {
        if (Log.isDebug()) {
            Log.xLogE("ModuleUnzipProcessor", "\u9879\u76ee(id:" + this.a.getAppid() + ", url:" + this.a.getOriginalUrl() + ")\u7684\u79bb\u7ebf\u6587\u4ef6\u89e3\u538b\u5931\u8d25\uff0c\u539f\u56e0\uff1a" + str);
        }
        a.C0087a c0087a = new a.C0087a();
        c0087a.b = f2;
        c0087a.a = this.a.getAppid();
        c0087a.f2699c = "0";
        c0087a.f2700e = this.f6093f;
        c0087a.f2702g = 1;
        c0087a.f2703h = this.f6092e;
        if (i2 == -3) {
            c0087a.f2701f = "-3";
        } else if (i2 == -2) {
            c0087a.f2701f = "-2";
        } else if (i2 == -1) {
            c0087a.f2701f = "-1";
        }
        com.jd.hybrid.downloader.p.a.b(c0087a);
    }

    private File d(boolean[] zArr) {
        Log.d("ModuleUnzipProcessor", "[Offline-file](unzip) Start to merge patch file, id: " + this.a.getAppid());
        String path = this.a.getZipFile() == null ? "" : this.a.getZipFile().getPath();
        if (TextUtils.isEmpty(path)) {
            Log.e("ModuleUnzipProcessor", "[Offline-file](unzip) Old zip file(to be merged)'s path is null.");
            c(-3, this.d, "\u5dee\u5206\u5305\u5408\u6210\u65f6\uff0c\u65e7zip\u5305\u7684\u76ee\u5f55\u4e3a\u7a7a");
            OfflineExceptionUtils.reportDownloadError(OfflineExceptionUtils.ERR_MSG_MERGE, "patchZipFile", this.b, this.f6092e, "Old zip file(to be merged)'s path is null.");
            return null;
        } else if (!new File(path).exists()) {
            Log.e("ModuleUnzipProcessor", "[Offline-file](unzip) Cannot find old zip file to be merged, file path: " + path);
            c(-3, this.d, "\u5dee\u5206\u5305\u5408\u6210\u65f6\uff0c\u627e\u4e0d\u5230\u65e7zip\u5305\uff0cpath=" + path);
            OfflineExceptionUtils.reportDownloadError(OfflineExceptionUtils.ERR_MSG_MERGE, "patchZipFile", this.b, this.f6092e, "Cannot find old zip file to be merged, file path: " + path);
            return null;
        } else {
            String combinePath = OfflineFileHelper.combinePath(this.f6091c.getParent(), this.f6091c.getName() + "_m");
            if (TextUtils.isEmpty(combinePath)) {
                Log.e("ModuleUnzipProcessor", "[Offline-file](unzip) Temp dest dir path is null in merging patch.");
                c(-3, this.d, "\u5dee\u5206\u5305\u5408\u6210\u65f6\uff0c\u4fdd\u5b58\u6574\u5408\u5305\u7684\u76ee\u5f55\u4e3a\u7a7a");
                OfflineExceptionUtils.reportDownloadError(OfflineExceptionUtils.ERR_MSG_MERGE, "patchZipFile", this.b, this.f6092e, "Temp dest dir path is null in merging patch.");
                return null;
            }
            try {
                BSpatch.h(path, this.f6091c.getAbsolutePath(), combinePath);
                File file = new File(combinePath);
                if ((this.a.getFileInfo() == null || TextUtils.isEmpty(this.a.getFileInfo().getMd5())) ? false : b(file)) {
                    Log.d("ModuleUnzipProcessor", "[Offline-file](unzip) Merged patch file successfully, merged zip: " + file.getPath() + ", old zip: " + path + ", id: " + this.a.getAppid());
                    return file;
                }
                if (file.exists()) {
                    FileUtils.deleteFile(file);
                }
                Log.e("ModuleUnzipProcessor", "[Offline-file](unzip) File check failed after patch merged. Id: " + this.a.getAppid());
                c(-2, this.d, "\u5dee\u5206\u5408\u6210\u5305\u6587\u4ef6\u6821\u9a8c\u5931\u8d25");
                OfflineExceptionUtils.reportDownloadError(OfflineExceptionUtils.ERR_MSG_CHECK, "patchZipFile", this.b, this.f6092e, "\u5dee\u5206\u5408\u6210\u5305\u6587\u4ef6\u6821\u9a8c\u5931\u8d25");
                zArr[0] = true;
                return null;
            } catch (Exception e2) {
                c(-3, this.d, "\u5dee\u5206\u5305\u5408\u6210\u5931\u8d25 : " + e2.getMessage());
                OfflineExceptionUtils.reportDownloadError(OfflineExceptionUtils.ERR_MSG_MERGE, "patchZipFile", this.b, this.f6092e, e2);
                Log.e("ModuleUnzipProcessor", "[Offline-file](unzip) Merge file patch fail. Id: " + this.a.getAppid(), e2);
                return null;
            }
        }
    }

    private boolean g(String str, String str2) {
        FileInputStream fileInputStream;
        BufferedInputStream bufferedInputStream = null;
        try {
            try {
                fileInputStream = new FileInputStream(str);
                try {
                    BufferedInputStream bufferedInputStream2 = new BufferedInputStream(fileInputStream);
                    try {
                        ZipInputStream zipInputStream = new ZipInputStream(bufferedInputStream2);
                        new File(str2).mkdirs();
                        byte[] bArr = new byte[8192];
                        while (true) {
                            ZipEntry nextEntry = zipInputStream.getNextEntry();
                            if (nextEntry == null) {
                                break;
                            }
                            String name = nextEntry.getName();
                            if (!name.contains("../")) {
                                FileOutputStream fileOutputStream = new FileOutputStream(new File(str2, name), false);
                                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream, 8192);
                                while (true) {
                                    int read = zipInputStream.read(bArr);
                                    if (read == -1) {
                                        break;
                                    }
                                    bufferedOutputStream.write(bArr, 0, read);
                                }
                                bufferedOutputStream.flush();
                                bufferedOutputStream.close();
                                fileOutputStream.close();
                                zipInputStream.closeEntry();
                            }
                        }
                        try {
                            bufferedInputStream2.close();
                            fileInputStream.close();
                        } catch (Exception unused) {
                        }
                        return true;
                    } catch (Exception e2) {
                        e = e2;
                        bufferedInputStream = bufferedInputStream2;
                        Log.e("ModuleUnzipProcessor", e);
                        OfflineExceptionUtils.reportDownloadError(OfflineExceptionUtils.ERR_MSG_UNZIP, "unZipToFile", this.b, this.f6092e, e);
                        try {
                            bufferedInputStream.close();
                            fileInputStream.close();
                        } catch (Exception unused2) {
                        }
                        return false;
                    } catch (Throwable th) {
                        th = th;
                        bufferedInputStream = bufferedInputStream2;
                        try {
                            bufferedInputStream.close();
                            fileInputStream.close();
                        } catch (Exception unused3) {
                        }
                        throw th;
                    }
                } catch (Exception e3) {
                    e = e3;
                }
            } catch (Exception e4) {
                e = e4;
                fileInputStream = null;
            } catch (Throwable th2) {
                th = th2;
                fileInputStream = null;
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    private boolean h(String str, String str2, String str3) {
        try {
            k.a.a.a aVar = new k.a.a.a(str);
            if (aVar.f()) {
                if (TextUtils.isEmpty(str3)) {
                    Log.e("ModuleUnzipProcessor", "[Offline-file](unzip) zip is encrypted, but password is empty.");
                    OfflineExceptionUtils.reportDownloadError(OfflineExceptionUtils.ERR_MSG_UNZIP, "unZipToFile", this.b, this.f6092e, "zip\u5df2\u52a0\u5bc6\uff0c\u4f46\u914d\u7f6e\u4e0b\u53d1\u7684\u5bc6\u7801\u4e3a\u7a7a");
                    return false;
                }
                aVar.h(str3.toCharArray());
                aVar.c(str2);
                return true;
            } else if (GraySwitch.useZipInputStream) {
                Log.d("ModuleUnzipProcessor", "\u8be5zip\u5305\u7531\u539f\u751f\u89e3\u538b" + str);
                return g(str, str2);
            } else {
                Log.d("ModuleUnzipProcessor", "\u8be5zip\u5305\u7531zip4j\u89e3\u538b" + str);
                aVar.c(str2);
                return true;
            }
        } catch (k.a.a.c.a e2) {
            Log.e("ModuleUnzipProcessor", e2);
            OfflineExceptionUtils.reportDownloadError(OfflineExceptionUtils.ERR_MSG_UNZIP, "unZipToFile", this.b, this.f6092e, e2);
            return false;
        }
    }

    private Pair<Boolean, Object> i(String str, String str2) {
        File[] listFiles;
        String str3 = null;
        try {
            if (TextUtils.isEmpty(str)) {
                return new Pair<>(Boolean.FALSE, "\u8981\u89e3\u538b\u7684zip\u6587\u4ef6path\u4e3a\u7a7a");
            }
            k.a.a.a aVar = new k.a.a.a(str);
            if (aVar.f()) {
                if (TextUtils.isEmpty(str2)) {
                    Log.e("ModuleUnzipProcessor", "[Offline-file](unzip) zip is encrypted, but password is empty.");
                    return new Pair<>(Boolean.FALSE, "zip\u5df2\u52a0\u5bc6\uff0c\u4f46\u914d\u7f6e\u4e0b\u53d1\u7684\u5bc6\u7801\u4e3a\u7a7a");
                }
                aVar.h(str2.toCharArray());
            }
            String str4 = str + "_temp";
            try {
                aVar.c(str4);
                File file = new File(str4);
                if (file.exists() && file.isDirectory() && (listFiles = file.listFiles()) != null && listFiles.length > 0) {
                    FileUtils.deleteFile(aVar.d());
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
                e = e2;
                str3 = str4;
                Log.e("ModuleUnzipProcessor", e.getMessage());
                if (!TextUtils.isEmpty(str3)) {
                    FileUtils.deleteFile(str3);
                }
                return new Pair<>(Boolean.FALSE, e);
            }
        } catch (Exception e3) {
            e = e3;
        }
    }

    private File j(boolean[] zArr) {
        Log.d("ModuleUnzipProcessor", "[Offline-file](unzip) Start to unzip the first time, source zip file at " + this.f6091c.getAbsolutePath() + ", id: " + this.a.getAppid());
        String absolutePath = this.f6091c.getAbsolutePath();
        Pair<Boolean, Object> i2 = i(absolutePath, this.a.getFileInfo().getPassword());
        if (((Boolean) i2.first).booleanValue()) {
            File file = new File(absolutePath);
            if (b(file)) {
                return file;
            }
            Log.e("ModuleUnzipProcessor", "[Offline-file](unzip) File check failed after first unzip. Id: " + this.a.getAppid());
            c(-2, this.d, "\u539f\u59cb\u5305\u6587\u4ef6\u6821\u9a8c\u5931\u8d25");
            OfflineExceptionUtils.reportDownloadError(OfflineExceptionUtils.ERR_MSG_CHECK, "unzipFirstTime", this.b, this.f6092e, "\u539f\u59cb\u5305\u6587\u4ef6\u6821\u9a8c\u5931\u8d25");
            if (zArr != null) {
                zArr[0] = true;
            }
            return null;
        }
        c(-1, this.d, OfflineExceptionUtils.ERR_MSG_UNZIP);
        Object obj = i2.second;
        if (obj instanceof Exception) {
            OfflineExceptionUtils.reportDownloadError(OfflineExceptionUtils.ERR_MSG_UNZIP, "unzipFirstTime", this.b, this.f6092e, (Exception) obj);
        } else {
            OfflineExceptionUtils.reportDownloadError(OfflineExceptionUtils.ERR_MSG_UNZIP, "unzipFirstTime", this.b, this.f6092e, (String) obj);
        }
        Log.e("ModuleUnzipProcessor", "[Offline-file](unzip) Unzip fail. Id: " + this.a.getAppid());
        return null;
    }

    public ModuleUnzipProcessor<T> e(ProcessCallback<T> processCallback) {
        this.f6095h = processCallback;
        return this;
    }

    public ModuleUnzipProcessor<T> f(String str) {
        this.f6094g = str;
        return this;
    }

    /* JADX WARN: Removed duplicated region for block: B:148:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x00c2  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x0124  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x0127  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x0166  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x0167 A[Catch: Exception -> 0x022a, TryCatch #1 {Exception -> 0x022a, blocks: (B:173:0x0156, B:177:0x0171, B:179:0x017d, B:181:0x018e, B:183:0x01ca, B:185:0x01ce, B:187:0x01fa, B:188:0x0200, B:190:0x0226, B:176:0x0167), top: B:214:0x0156 }] */
    /* JADX WARN: Removed duplicated region for block: B:179:0x017d A[Catch: Exception -> 0x022a, TryCatch #1 {Exception -> 0x022a, blocks: (B:173:0x0156, B:177:0x0171, B:179:0x017d, B:181:0x018e, B:183:0x01ca, B:185:0x01ce, B:187:0x01fa, B:188:0x0200, B:190:0x0226, B:176:0x0167), top: B:214:0x0156 }] */
    /* JADX WARN: Removed duplicated region for block: B:188:0x0200 A[Catch: Exception -> 0x022a, TryCatch #1 {Exception -> 0x022a, blocks: (B:173:0x0156, B:177:0x0171, B:179:0x017d, B:181:0x018e, B:183:0x01ca, B:185:0x01ce, B:187:0x01fa, B:188:0x0200, B:190:0x0226, B:176:0x0167), top: B:214:0x0156 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void processZipFile() {
        /*
            Method dump skipped, instructions count: 659
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.libs.hybrid.offlineload.processor.ModuleUnzipProcessor.processZipFile():void");
    }

    public File unzipFirstForBuildIn() {
        File file;
        T t = this.a;
        if (t != null && t.getFileInfo() != null && (file = this.f6091c) != null && file.exists()) {
            File file2 = this.f6091c;
            if ((this.a.getFileInfo().getUseZip() ? false : OfflineEntityInfo.FILE_TYPE_ZIP2.equalsIgnoreCase(this.a.getFileInfo().getFileType())) && (file2 = j(null)) == null) {
                FileUtils.deleteFile(this.f6091c);
            }
            return file2;
        }
        Log.e("ModuleUnzipProcessor", "[BuildIn-Offline-file](unzip) unzip fail because information needed is null.");
        c(-1, this.d, "\u914d\u7f6e\u4fe1\u606f\u4e3a\u7a7a\uff0c\u6216\u4e0b\u8f7d\u540e\u6587\u4ef6\u4e0d\u5b58\u5728");
        OfflineExceptionUtils.reportDownloadError(OfflineExceptionUtils.ERR_MSG_UNZIP, "unzipForBuildIn", this.b, this.f6092e, "\u914d\u7f6e\u4fe1\u606f\u4e3a\u7a7a\uff0c\u6216\u590d\u5236\u540e\u6587\u4ef6\u4e0d\u5b58\u5728");
        File file3 = this.f6091c;
        if (file3 != null && file3.exists()) {
            FileUtils.deleteFile(this.f6091c);
        }
        return null;
    }
}
