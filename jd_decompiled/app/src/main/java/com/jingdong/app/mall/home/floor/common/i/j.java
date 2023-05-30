package com.jingdong.app.mall.home.floor.common.i;

import com.jingdong.common.jdmiaosha.utils.cache.Final;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.network.toolbox.FileGuider;
import com.jingdong.jdsdk.network.toolbox.FileService;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.utils.Md5Encrypt;
import java.io.File;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes4.dex */
public class j {
    private static CopyOnWriteArrayList<String> a = new CopyOnWriteArrayList<>();

    /* loaded from: classes4.dex */
    class a implements HttpGroup.OnCommonListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ String f9313g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ c f9314h;

        a(String str, c cVar) {
            this.f9313g = str;
            this.f9314h = cVar;
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            if (httpResponse == null) {
                c cVar = this.f9314h;
                if (cVar != null) {
                    cVar.a(false, null);
                    return;
                }
                return;
            }
            j.a.remove(this.f9313g);
            File saveFile = httpResponse.getSaveFile();
            if (saveFile == null) {
                c cVar2 = this.f9314h;
                if (cVar2 != null) {
                    cVar2.a(false, null);
                    return;
                }
                return;
            }
            if (Log.D) {
                Log.d("JDHomeVideoDownloadCommonUtil", "onEnd ===>>> " + saveFile.getAbsolutePath());
            }
            c cVar3 = this.f9314h;
            if (cVar3 != null) {
                cVar3.a(true, saveFile.getAbsolutePath());
            }
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
            c cVar = this.f9314h;
            if (cVar != null) {
                cVar.b();
            }
            j.a.remove(this.f9313g);
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
        public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
            j.a.add(this.f9313g);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b extends com.jingdong.app.mall.home.o.a.b {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ File f9315g;

        b(File file) {
            this.f9315g = file;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            try {
                j.d(this.f9315g);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class c {
        public abstract void a(boolean z, String str);

        public void b() {
        }
    }

    public static void c(File file) {
        com.jingdong.app.mall.home.w.a.a(new b(file));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d(File file) {
        File[] listFiles;
        if (file == null || !file.isDirectory() || (listFiles = file.listFiles()) == null || listFiles.length == 0) {
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        for (File file2 : listFiles) {
            if (Log.D) {
                com.jingdong.app.mall.home.o.a.f.r0("JDHomeVideoDownloadCommonUtil", "current:", Long.valueOf(currentTimeMillis), " last:", Long.valueOf(file2.lastModified()), " hold:", Long.valueOf(currentTimeMillis - file2.lastModified()), " most:", Long.valueOf((long) Final.SEV_DAY));
            }
            if (file2 != null && file2.exists() && currentTimeMillis - file2.lastModified() > Final.SEV_DAY) {
                if (Log.D) {
                    com.jingdong.app.mall.home.o.a.f.r0("JDHomeVideoDownloadCommonUtil", "delete:", file2.getAbsolutePath());
                }
                file2.delete();
            }
        }
    }

    public static void e(String str, String str2, String str3, String str4, c cVar) {
        if (Log.D) {
            Log.d("JDHomeVideoDownloadCommonUtil", "downloadVideo ===>>> " + str3 + " : " + str4);
        }
        c(f(str));
        String str5 = str3 + CartConstant.KEY_YB_INFO_LINK + str4;
        if (a.contains(str5)) {
            return;
        }
        String format = String.format("jingdong" + str, new Object[0]);
        FileGuider fileGuider = new FileGuider();
        fileGuider.setSpace(1);
        fileGuider.setChildDirName(format);
        fileGuider.setImmutable(false);
        fileGuider.setFileName(Md5Encrypt.md5(str3) + str2);
        fileGuider.setMode(1);
        HttpSetting a2 = com.jingdong.app.mall.home.base.a.a.a(str4);
        a2.setSavePath(fileGuider);
        a2.setCacheMode(0);
        a2.setType(500);
        a2.setBreakpointTransmission(true);
        a2.setAttempts(1);
        a2.setListener(new a(str5, cVar));
        a2.setType(500);
        a2.setBreakpointTransmission(false);
        a2.setAttempts(1);
        HttpGroupUtils.getHttpGroupaAsynPool().add(a2);
    }

    public static File f(String str) {
        return FileService.getInternalDirectory(str, 1, true);
    }

    public static String g(String str, String str2, String str3) {
        File f2 = f(str);
        if (f2 == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(f2.getPath());
        stringBuffer.append("/");
        stringBuffer.append(Md5Encrypt.md5(str3));
        stringBuffer.append(str2);
        return stringBuffer.toString();
    }
}
