package com.jingdong.app.mall.ad;

import android.os.SystemClock;
import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.app.mall.home.o.a.o;
import com.jingdong.common.apkcenter.ApkDownloadTable;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.utils.FileUtils;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.network.toolbox.FileGuider;
import com.jingdong.jdsdk.network.toolbox.FileService;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.utils.Md5Encrypt;
import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes19.dex */
public class a {
    public static long a;
    public static long b;

    /* renamed from: c */
    private static final AtomicInteger f7892c = new AtomicInteger();
    private static JSONArray d;

    /* renamed from: e */
    private static boolean f7893e;

    /* renamed from: f */
    private static int f7894f;

    /* renamed from: com.jingdong.app.mall.ad.a$a */
    /* loaded from: classes19.dex */
    public class C0234a implements com.jingdong.app.mall.home.t.b<File> {
        final /* synthetic */ long a;
        final /* synthetic */ String b;

        C0234a(long j2, String str) {
            this.a = j2;
            this.b = str;
        }

        @Override // com.jingdong.app.mall.home.t.b
        /* renamed from: a */
        public void onSuccess(File file) {
            a.p(SystemClock.elapsedRealtime() - this.a, file.length());
        }

        @Override // com.jingdong.app.mall.home.t.b
        public void onFail(String str) {
            ExceptionReporter.reportBitmapException(this.b, null, com.jingdong.app.mall.ad.c.class.getSimpleName(), 200);
        }
    }

    /* loaded from: classes19.dex */
    public class b implements HttpGroup.OnCommonListener {

        /* renamed from: g */
        final /* synthetic */ com.jingdong.app.mall.home.t.b f7895g;

        /* renamed from: h */
        final /* synthetic */ String f7896h;

        b(com.jingdong.app.mall.home.t.b bVar, String str) {
            this.f7895g = bVar;
            this.f7896h = str;
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            if (httpResponse == null) {
                this.f7895g.onFail(" response null");
                return;
            }
            File saveFile = httpResponse.getSaveFile();
            if (!o.b(saveFile)) {
                this.f7895g.onFail(" file un valid");
                return;
            }
            FileService.Directory directory = FileService.getDirectory(4);
            if (directory == null) {
                this.f7895g.onFail(" directory null");
                return;
            }
            FileUtils.saveToFile(directory.getPath() + File.separator + "start_image_" + this.f7896h, saveFile.getAbsolutePath());
            a.o(saveFile, this.f7896h);
            this.f7895g.onSuccess(saveFile);
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
            this.f7895g.onFail(" load Error");
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
        public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
        }
    }

    /* loaded from: classes19.dex */
    public class c implements HttpGroup.OnEndListener {

        /* renamed from: g */
        final /* synthetic */ String f7897g;

        c(String str) {
            this.f7897g = str;
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            File saveFile;
            FileService.Directory directory;
            if (httpResponse == null || (saveFile = httpResponse.getSaveFile()) == null || (directory = FileService.getDirectory(4)) == null) {
                return;
            }
            if (Log.D) {
                Log.d("AdObserver", "onEnd ===>>> " + saveFile.getAbsolutePath());
            }
            FileUtils.saveToFile(directory.getPath() + File.separator + "start_image_" + this.f7897g, saveFile.getAbsolutePath());
            a.o(saveFile, this.f7897g);
        }
    }

    public static boolean c(File file, String str) {
        if (file != null && file.exists()) {
            try {
                JSONObject jSONObject = new JSONObject(f.O("start_image_file_info", "{}"));
                String name = file.getName();
                if (TextUtils.isEmpty(name)) {
                    return false;
                }
                String replace = name.replace("start_image_", "");
                boolean z = jSONObject.has(replace) && ((long) jSONObject.getInt(replace)) == file.length();
                if (!z) {
                    com.jingdong.app.mall.home.r.c.a.v(JdSdk.getInstance().getApplicationContext(), "StartPhoto_PopupError", file.length() + CartConstant.KEY_YB_INFO_LINK + str);
                    file.delete();
                    g(file);
                }
                return z;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return false;
    }

    public static boolean d(String str) {
        if (TextUtils.isEmpty(l(str))) {
            return false;
        }
        return !c(new File(r0), str);
    }

    public static boolean e(String str, String str2) {
        String l2 = l(str);
        if (Log.D) {
            Log.d("AdObserver", str + " ::checkVideoId:: " + l2);
        }
        if (TextUtils.isEmpty(l2)) {
            return false;
        }
        if (c(new File(l2), str2)) {
            if (Log.D) {
                Log.d("AdObserver", "Video file exists ===>>> " + str);
            }
            return false;
        }
        return true;
    }

    public static void f(ArrayList<String> arrayList, ArrayList<String> arrayList2) {
        JDJSONArray n2 = n();
        if (n2 == null || n2.size() == 0) {
            return;
        }
        int size = n2.size();
        for (int i2 = 0; i2 < size; i2++) {
            JDJSONArray optJSONArray = n2.optJSONArray(i2);
            int size2 = optJSONArray.size();
            for (int i3 = 0; i3 < size2; i3++) {
                String optString = optJSONArray.optJSONObject(i3).optString("url", "");
                if (!arrayList.contains(optString)) {
                    i(optString);
                }
                String optString2 = optJSONArray.optJSONObject(i3).optString("videoId", "");
                if (!arrayList2.contains(optString2)) {
                    i(optString2);
                }
            }
        }
    }

    private static void g(File file) {
        if (file == null) {
            return;
        }
        String name = file.getName();
        if (TextUtils.isEmpty(name)) {
            return;
        }
        h(name.replace("start_image_", ""));
    }

    private static void h(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(f.O("start_image_file_info", "{}"));
            jSONObject.remove(str);
            f.A0("start_image_file_info", jSONObject.toString());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private static void i(String str) {
        String l2 = l(str);
        if (TextUtils.isEmpty(l2)) {
            return;
        }
        File file = new File(l2);
        if (file.exists()) {
            try {
                file.delete();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        g(file);
    }

    public static void j(String str) {
        if (Log.D) {
            Log.d("AdObserver", "downloadImage ===>>> " + str);
        }
        f7894f++;
        m(str, new C0234a(SystemClock.elapsedRealtime(), str));
    }

    public static void k(String str, String str2) {
        if (Log.D) {
            Log.d("AdObserver", "downloadVideo ===>>> " + str + " : " + str2);
        }
        String md5 = Md5Encrypt.md5(str);
        FileGuider fileGuider = new FileGuider();
        fileGuider.setSpace(1);
        fileGuider.setChildDirName("start_image");
        fileGuider.setImmutable(false);
        fileGuider.setFileName(md5);
        fileGuider.setMode(1);
        HttpSetting a2 = com.jingdong.app.mall.home.base.a.a.a(str2);
        a2.setSavePath(fileGuider);
        a2.setCacheMode(2);
        a2.setListener(new c(md5));
        a2.setType(500);
        a2.setBreakpointTransmission(false);
        a2.setAttempts(1);
        HttpGroupUtils.getHttpGroupaAsynPool().add(a2);
    }

    public static String l(String str) {
        FileService.Directory directory = FileService.getDirectory(4);
        if (directory == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(directory.getPath());
        stringBuffer.append(File.separator);
        stringBuffer.append("start_image_");
        stringBuffer.append(Md5Encrypt.md5(str));
        if (Log.D) {
            Log.d("AdObserver", "getPathName: " + stringBuffer.toString());
        }
        return stringBuffer.toString();
    }

    public static void m(String str, com.jingdong.app.mall.home.t.b<File> bVar) {
        if (TextUtils.isEmpty(str)) {
            bVar.onFail(" url empty");
            return;
        }
        String md5 = Md5Encrypt.md5(str);
        String str2 = File.separator + "start_image_" + md5;
        FileGuider fileGuider = new FileGuider();
        fileGuider.setSpace(1);
        fileGuider.setImmutable(false);
        fileGuider.setFileName(str2);
        fileGuider.setMode(1);
        HttpSetting a2 = com.jingdong.app.mall.home.base.a.a.a(str);
        a2.setSavePath(fileGuider);
        a2.setLocalFileCache(true);
        a2.setOnTouchEvent(true);
        a2.setType(500);
        a2.setListener(new b(bVar, md5));
        HttpGroupUtils.getHttpGroupaAsynPool().add(a2);
    }

    public static JDJSONArray n() {
        String stringFromPreference = CommonBase.getStringFromPreference("start_image_json", "");
        if (TextUtils.isEmpty(stringFromPreference)) {
            return null;
        }
        try {
            Object parse = JDJSON.parse(stringFromPreference);
            if (parse instanceof JDJSONArray) {
                return (JDJSONArray) parse;
            }
            return null;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static void o(File file, String str) {
        try {
            JSONObject jSONObject = new JSONObject(f.O("start_image_file_info", "{}"));
            jSONObject.put(str, file.length());
            f.A0("start_image_file_info", jSONObject.toString());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void p(long j2, long j3) {
        if (d == null) {
            d = com.jingdong.app.mall.home.r.c.b.d();
        }
        com.jingdong.app.mall.home.r.c.b c2 = com.jingdong.app.mall.home.r.c.b.c("");
        c2.a("apitimegap", Long.valueOf(b));
        c2.a("loadtime", Long.valueOf(j2));
        c2.a(ApkDownloadTable.FIELD_SIZE, Long.valueOf(j3));
        c2.a("apiapplytime", Long.valueOf(a));
        d.put(c2);
        if (f7892c.incrementAndGet() == f7894f) {
            q();
        }
    }

    private static void q() {
        if (f7893e) {
            return;
        }
        com.jingdong.app.mall.home.r.c.a.y("StartPhoto_load", "", d.toString());
        d.toString();
        f7893e = true;
        d = null;
        f7894f = 0;
        f7892c.set(0);
    }
}
