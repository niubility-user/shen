package com.jingdong.manto.m.y0;

import android.os.Build;
import android.os.Bundle;
import android.system.Os;
import android.system.StructStat;
import com.jd.dynamic.base.interfaces.IExceptionHandler;
import com.jingdong.common.apkcenter.ApkDownloadTable;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.jsapi.openmodule.AbstractMantoModule;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.jsapi.openmodule.JsApiMethod;
import com.jingdong.manto.jsapi.openmodule.MantoResultCallBack;
import com.jingdong.manto.m.y0.b;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoMd5Utils;
import com.jingdong.manto.utils.MantoStringUtils;
import com.jingdong.manto.utils.f0;
import com.jingdong.manto.utils.s;
import com.jingdong.manto.utils.t;
import com.jingdong.sdk.threadpool.ThreadManager;
import com.tencent.smtt.sdk.TbsReaderView;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class a extends AbstractMantoModule {
    public long a = 10485760;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.manto.m.y0.a$a  reason: collision with other inner class name */
    /* loaded from: classes15.dex */
    public class RunnableC0635a implements Runnable {
        final /* synthetic */ boolean a;
        final /* synthetic */ InputStream b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ String f13821c;
        final /* synthetic */ CountDownLatch d;

        RunnableC0635a(a aVar, boolean z, InputStream inputStream, String str, CountDownLatch countDownLatch) {
            this.a = z;
            this.b = inputStream;
            this.f13821c = str;
            this.d = countDownLatch;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (this.a) {
                    s.a(this.b, this.f13821c, true);
                } else {
                    s.a(this.b, this.f13821c);
                }
            } finally {
                this.d.countDown();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class b implements Runnable {
        final /* synthetic */ com.jingdong.manto.t.d a;
        final /* synthetic */ com.jingdong.manto.t.d b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ CountDownLatch f13822c;

        b(a aVar, com.jingdong.manto.t.d dVar, com.jingdong.manto.t.d dVar2, CountDownLatch countDownLatch) {
            this.a = dVar;
            this.b = dVar2;
            this.f13822c = countDownLatch;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                s.a(this.a.b, this.b.b);
            } finally {
                this.f13822c.countDown();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class c implements FileFilter {
        c(a aVar) {
        }

        @Override // java.io.FileFilter
        public boolean accept(File file) {
            return file.exists() && !file.isHidden();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class d implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ File b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ String f13823c;
        final /* synthetic */ String d;

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ com.jingdong.manto.t.d f13824e;

        /* renamed from: f  reason: collision with root package name */
        final /* synthetic */ Bundle f13825f;

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ MantoResultCallBack f13826g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ MantoCore f13827h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ CountDownLatch f13828i;

        d(String str, File file, String str2, String str3, com.jingdong.manto.t.d dVar, Bundle bundle, MantoResultCallBack mantoResultCallBack, MantoCore mantoCore, CountDownLatch countDownLatch) {
            this.a = str;
            this.b = file;
            this.f13823c = str2;
            this.d = str3;
            this.f13824e = dVar;
            this.f13825f = bundle;
            this.f13826g = mantoResultCallBack;
            this.f13827h = mantoCore;
            this.f13828i = countDownLatch;
        }

        /* JADX WARN: Code restructure failed: missing block: B:26:0x008f, code lost:
            if (r0 != null) goto L34;
         */
        /* JADX WARN: Code restructure failed: missing block: B:33:0x00cd, code lost:
            if (r0 != null) goto L34;
         */
        /* JADX WARN: Code restructure failed: missing block: B:34:0x00cf, code lost:
            r0.onSuccess(r9.f13825f);
         */
        /* JADX WARN: Removed duplicated region for block: B:18:0x0059  */
        /* JADX WARN: Removed duplicated region for block: B:21:0x006b  */
        /* JADX WARN: Removed duplicated region for block: B:28:0x0092  */
        /* JADX WARN: Removed duplicated region for block: B:37:0x00d8  */
        /* JADX WARN: Removed duplicated region for block: B:42:0x0035 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:44:? A[RETURN, SYNTHETIC] */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void run() {
            /*
                r9 = this;
                java.lang.String r0 = r9.a
                boolean r0 = android.text.TextUtils.isEmpty(r0)
                r1 = 0
                if (r0 != 0) goto L26
                java.lang.String r0 = r9.a     // Catch: java.lang.Throwable -> L26
                long r1 = java.lang.Long.parseLong(r0)     // Catch: java.lang.Throwable -> L26
                java.io.File r0 = r9.b     // Catch: java.lang.Throwable -> L26
                long r3 = r0.length()     // Catch: java.lang.Throwable -> L26
                r5 = 1
                long r3 = r3 - r5
                int r0 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
                if (r0 <= 0) goto L26
                java.io.File r0 = r9.b     // Catch: java.lang.Throwable -> L26
                long r0 = r0.length()     // Catch: java.lang.Throwable -> L26
                long r0 = r0 - r5
                r5 = r0
                goto L27
            L26:
                r5 = r1
            L27:
                java.io.File r0 = r9.b
                long r0 = r0.length()
                java.lang.String r2 = r9.f13823c
                boolean r2 = android.text.TextUtils.isEmpty(r2)
                if (r2 != 0) goto L4d
                java.lang.String r2 = r9.f13823c     // Catch: java.lang.Throwable -> L4c
                long r0 = java.lang.Long.parseLong(r2)     // Catch: java.lang.Throwable -> L4c
                java.io.File r2 = r9.b     // Catch: java.lang.Throwable -> L4c
                long r2 = r2.length()     // Catch: java.lang.Throwable -> L4c
                int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
                if (r4 <= 0) goto L4d
                java.io.File r2 = r9.b     // Catch: java.lang.Throwable -> L4c
                long r0 = r2.length()     // Catch: java.lang.Throwable -> L4c
                goto L4d
            L4c:
            L4d:
                long r2 = r5 + r0
                java.io.File r4 = r9.b
                long r7 = r4.length()
                int r4 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
                if (r4 <= 0) goto L60
                java.io.File r0 = r9.b
                long r0 = r0.length()
                long r0 = r0 - r5
            L60:
                r7 = r0
                java.lang.String r0 = r9.d
                boolean r0 = android.text.TextUtils.isEmpty(r0)
                java.lang.String r1 = "data"
                if (r0 != 0) goto L92
                java.lang.String r0 = r9.d
                java.lang.String r2 = "base64"
                boolean r0 = android.text.TextUtils.equals(r2, r0)
                if (r0 == 0) goto L7e
                com.jingdong.manto.t.d r0 = r9.f13824e
                java.lang.String r0 = r0.b
                java.lang.String r0 = com.jingdong.manto.utils.s.b(r0, r5, r7)
                goto L88
            L7e:
                com.jingdong.manto.t.d r0 = r9.f13824e
                java.lang.String r3 = r0.b
                java.lang.String r4 = r9.d
                java.lang.String r0 = com.jingdong.manto.utils.s.a(r3, r4, r5, r7)
            L88:
                android.os.Bundle r2 = r9.f13825f
                r2.putString(r1, r0)
                com.jingdong.manto.jsapi.openmodule.MantoResultCallBack r0 = r9.f13826g
                if (r0 == 0) goto Ld4
                goto Lcf
            L92:
                com.jingdong.manto.t.d r0 = r9.f13824e
                java.lang.String r0 = r0.b
                java.lang.String r0 = com.jingdong.manto.utils.s.a(r0, r5, r7)
                byte[] r2 = r0.getBytes()
                java.nio.ByteBuffer r2 = java.nio.ByteBuffer.wrap(r2)
                java.util.HashMap r3 = new java.util.HashMap
                r3.<init>()
                r3.put(r1, r2)
                org.json.JSONObject r2 = new org.json.JSONObject
                r2.<init>()
                com.jingdong.manto.m.y0.a r4 = com.jingdong.manto.m.y0.a.this
                com.jingdong.manto.MantoCore r5 = r9.f13827h
                r6 = 1
                boolean r3 = r4.convertNativeBuffer(r5, r2, r3, r6)
                if (r3 == 0) goto Lc6
                android.os.Bundle r0 = r9.f13825f
                java.lang.String r1 = "__nativeBuffers__"
                java.lang.String r2 = r2.optString(r1)
                r0.putString(r1, r2)
                goto Lcb
            Lc6:
                android.os.Bundle r2 = r9.f13825f
                r2.putString(r1, r0)
            Lcb:
                com.jingdong.manto.jsapi.openmodule.MantoResultCallBack r0 = r9.f13826g
                if (r0 == 0) goto Ld4
            Lcf:
                android.os.Bundle r1 = r9.f13825f
                r0.onSuccess(r1)
            Ld4:
                com.jingdong.manto.jsapi.openmodule.MantoResultCallBack r0 = r9.f13826g
                if (r0 != 0) goto Ldd
                java.util.concurrent.CountDownLatch r0 = r9.f13828i
                r0.countDown()
            Ldd:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jingdong.manto.m.y0.a.d.run():void");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class e implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ Bundle f13830c;
        final /* synthetic */ MantoResultCallBack d;

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ String f13831e;

        e(a aVar, String str, String str2, Bundle bundle, MantoResultCallBack mantoResultCallBack, String str3) {
            this.a = str;
            this.b = str2;
            this.f13830c = bundle;
            this.d = mantoResultCallBack;
            this.f13831e = str3;
        }

        @Override // java.lang.Runnable
        public void run() {
            com.jingdong.manto.t.d g2 = com.jingdong.manto.t.c.g(this.a, this.b);
            if (g2 == null) {
                this.f13830c.putString("message", "file doesn't exist");
                this.d.onFailed(this.f13830c);
                return;
            }
            File file = new File(g2.b);
            String md5OfFile = this.f13831e.equals("md5") ? MantoMd5Utils.md5OfFile(file) : "";
            if (this.f13831e.equals("sha1")) {
                md5OfFile = a.b(file);
            }
            this.f13830c.putLong(ApkDownloadTable.FIELD_SIZE, file.length());
            this.f13830c.putString("digest", md5OfFile);
            this.d.onSuccess(this.f13830c);
        }
    }

    private final Bundle a(Bundle bundle, String str) {
        bundle.putString("message", str);
        bundle.putString(IMantoBaseModule.ERROR_CODE, "0");
        return bundle;
    }

    private final Bundle a(MantoCore mantoCore, JSONObject jSONObject, String str, MantoResultCallBack mantoResultCallBack) {
        String str2;
        Bundle bundle = new Bundle(2);
        String optString = jSONObject.optString(TbsReaderView.KEY_FILE_PATH, "");
        if (MantoStringUtils.isEmpty(optString)) {
            a(bundle, "missing param filePath");
            if (mantoResultCallBack != null) {
                mantoResultCallBack.onFailed(bundle);
            }
            return bundle;
        } else if (com.jingdong.manto.t.c.h(str, optString)) {
            com.jingdong.manto.t.d g2 = com.jingdong.manto.t.c.g(str, optString);
            if (g2 == null || (str2 = g2.b) == null || !s.d(str2)) {
                a(bundle, String.format("no such file or directory %s", optString));
                if (mantoResultCallBack != null) {
                    mantoResultCallBack.onFailed(bundle);
                }
                return bundle;
            }
            String optString2 = jSONObject.optString("encoding");
            String optString3 = jSONObject.optString("position");
            String optString4 = jSONObject.optString(CartConstant.KEY_LENGTH);
            File file = new File(g2.b);
            if (file.isFile()) {
                CountDownLatch countDownLatch = new CountDownLatch(1);
                com.jingdong.manto.v.b.a.a().a(new d(optString3, file, optString4, optString2, g2, bundle, mantoResultCallBack, mantoCore, countDownLatch));
                if (mantoResultCallBack == null) {
                    try {
                        countDownLatch.await(7L, TimeUnit.SECONDS);
                    } catch (InterruptedException unused) {
                        a(bundle, String.format("readFile timeout", new Object[0]));
                    }
                }
            } else {
                a(bundle, String.format("not a file %s", optString));
                if (mantoResultCallBack != null) {
                    mantoResultCallBack.onFailed(bundle);
                }
            }
            return bundle;
        } else {
            return a(bundle, String.format("no such file or directory %s", optString));
        }
    }

    private final Bundle a(JSONObject jSONObject, String str) {
        String str2;
        String str3;
        Bundle bundle = new Bundle(2);
        String optString = jSONObject.optString("path");
        if (MantoStringUtils.isEmpty(optString)) {
            str2 = "missing param path";
        } else if (com.jingdong.manto.t.c.h(str, optString)) {
            com.jingdong.manto.t.d g2 = com.jingdong.manto.t.c.g(str, optString);
            if (g2 != null && (str3 = g2.b) != null && s.d(str3)) {
                return bundle;
            }
            str2 = "file not exists";
        } else {
            str2 = String.format("no such file or directory %s", optString);
        }
        return a(bundle, str2);
    }

    private final Bundle a(JSONObject jSONObject, String str, boolean z) {
        String str2;
        InputStream byteArrayInputStream;
        StringBuilder sb;
        String str3;
        StringBuilder sb2;
        Map<String, com.jingdong.manto.m.y0.b> map;
        Bundle bundle = new Bundle(2);
        String optString = jSONObject.optString(TbsReaderView.KEY_FILE_PATH);
        boolean optBoolean = jSONObject.optBoolean("append", false);
        String str4 = "utf8";
        String optString2 = jSONObject.optString("encoding", "utf8");
        Object opt = jSONObject.opt("data");
        if (MantoStringUtils.isEmpty(optString) || MantoStringUtils.isEmpty(optString)) {
            str2 = "missing param filePath";
        } else {
            if (opt instanceof String) {
                if (MantoStringUtils.isEmpty(optString2)) {
                    map = b.a.a;
                } else {
                    map = b.a.a;
                    str4 = optString2.toLowerCase();
                }
                com.jingdong.manto.m.y0.b bVar = map.get(str4);
                if (bVar == null) {
                    sb2 = new StringBuilder();
                    sb2.append("invalid encoding:");
                    sb2.append(optString2);
                    str2 = sb2.toString();
                } else {
                    try {
                        byteArrayInputStream = new com.jingdong.manto.pkg.b.a(bVar.a((String) opt));
                    } catch (Exception e2) {
                        sb = new StringBuilder();
                        sb.append("fail encoding:");
                        str3 = e2.getMessage();
                    }
                }
            } else if (opt instanceof ByteBuffer) {
                byteArrayInputStream = new com.jingdong.manto.pkg.b.a((ByteBuffer) opt);
            } else if (opt != null) {
                str2 = "invalid data";
            } else if (optBoolean) {
                return bundle;
            } else {
                byteArrayInputStream = new ByteArrayInputStream(new byte[0]);
            }
            InputStream inputStream = byteArrayInputStream;
            String str5 = com.jingdong.manto.t.c.g(str, "jdfile://usr/").b;
            str3 = MantoStringUtils.deleteLeftSlash(optString.replaceFirst(Pattern.quote("jdfile://usr/"), "")).replace("\u0000", "");
            if (str3.contains("/")) {
                if (!s.d(str5 + "/" + str3.substring(0, str3.lastIndexOf("/")))) {
                    sb = new StringBuilder();
                    sb.append("permission denied, writeFile: ");
                    sb.append(str5);
                    sb.append("/");
                    sb.append(str3);
                    str2 = sb.toString();
                }
            }
            String str6 = str5 + "/" + str3;
            if (s.d(str6)) {
                if (new File(str6).isDirectory()) {
                    sb2 = new StringBuilder();
                    sb2.append("operation on a directory: ");
                    sb2.append(str6);
                    str2 = sb2.toString();
                } else {
                    MantoLog.d("FileInfo", "writeFile file existed, filePath:" + str6);
                }
            }
            try {
                if (a(str, bundle, inputStream.available())) {
                    return bundle;
                }
            } catch (Exception unused) {
            }
            CountDownLatch countDownLatch = new CountDownLatch(1);
            com.jingdong.manto.v.b.a.a().a(new RunnableC0635a(this, z, inputStream, str6, countDownLatch));
            try {
                countDownLatch.await(6L, TimeUnit.SECONDS);
                return bundle;
            } catch (InterruptedException unused2) {
                str2 = String.format("fail write was overtime", new Object[0]);
            }
        }
        return a(bundle, str2);
    }

    private JSONArray a(File file, String str, boolean z, boolean z2) {
        JSONArray jSONArray = new JSONArray();
        if (z) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("path", z2 ? "/" : str);
                jSONObject.put("stats", a(file));
                jSONArray.put(jSONObject);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        if (z2) {
            for (File file2 : s.a(file, true)) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("path", file2.getAbsolutePath().substring(str.length()));
                jSONObject2.put("stats", a(file2));
                jSONArray.put(jSONObject2);
            }
        }
        return jSONArray;
    }

    private JSONObject a(File file) {
        long lastModified;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(ApkDownloadTable.FIELD_SIZE, file.length());
            jSONObject.put("lastModifiedTime", file.lastModified());
            if (Build.VERSION.SDK_INT >= 21) {
                StructStat stat = Os.stat(file.getAbsolutePath());
                jSONObject.put("mode", String.valueOf(stat.st_mode));
                lastModified = stat.st_atime * 1000;
            } else {
                jSONObject.put("mode", file.isDirectory() ? "16877" : "33188");
                lastModified = file.lastModified();
            }
            jSONObject.put("lastAccessedTime", lastModified);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return jSONObject;
    }

    private final void a(String str, MantoResultCallBack mantoResultCallBack) {
        List<com.jingdong.manto.t.d> h2 = com.jingdong.manto.t.c.h(str);
        JSONArray jSONArray = new JSONArray();
        if (h2 != null && !h2.isEmpty()) {
            for (com.jingdong.manto.t.d dVar : h2) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put(TbsReaderView.KEY_FILE_PATH, dVar.a);
                    jSONObject.put(ApkDownloadTable.FIELD_SIZE, dVar.f14211f);
                    jSONObject.put("createTime", TimeUnit.MILLISECONDS.toSeconds(dVar.f14210e));
                    jSONArray.put(jSONObject);
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
        }
        Bundle bundle = new Bundle();
        bundle.putString("fileList", jSONArray.toString());
        mantoResultCallBack.onSuccess(bundle);
    }

    private void a(JSONObject jSONObject, String str, MantoResultCallBack mantoResultCallBack) {
        Bundle bundle = new Bundle();
        String optString = jSONObject.optString(TbsReaderView.KEY_FILE_PATH, "");
        String str2 = "sha1".equalsIgnoreCase(jSONObject.optString("digestAlgorithm", "md5")) ? "sha1" : "md5";
        if (MantoStringUtils.isEmpty(optString)) {
            bundle.putString("message", "invalid data");
            mantoResultCallBack.onFailed(bundle);
        } else if (com.jingdong.manto.t.c.h(str, optString)) {
            ThreadManager.heavy().post(new e(this, str, optString, bundle, mantoResultCallBack, str2));
        } else {
            bundle.putString("message", String.format("no such file or directory %s", optString));
            mantoResultCallBack.onFailed(bundle);
        }
    }

    private final boolean a(String str, Bundle bundle, long j2) {
        long g2 = com.jingdong.manto.t.c.g(str) + j2;
        if (g2 > this.a) {
            if (bundle != null) {
                bundle.putString("message", String.format(Locale.US, "fail:exceed quota %dM", Long.valueOf(g2 / 1048576)));
                bundle.putString(IMantoBaseModule.ERROR_CODE, "0");
            }
            return true;
        }
        return false;
    }

    private final Bundle b(JSONObject jSONObject, String str) {
        String str2;
        Bundle bundle = new Bundle(2);
        String optString = jSONObject.optString("srcPath", "");
        String optString2 = jSONObject.optString("destPath", "");
        if (MantoStringUtils.isEmpty(optString) || MantoStringUtils.isEmpty(optString2)) {
            str2 = "missing param srcPath or destPath";
        } else {
            com.jingdong.manto.t.d g2 = com.jingdong.manto.t.c.g(str, optString);
            if (g2 == null || !s.d(g2.b)) {
                str2 = String.format("fail no such file or directory, copyFile %s -> %s", optString, optString2);
            } else {
                com.jingdong.manto.t.d g3 = com.jingdong.manto.t.c.g(str, optString2);
                if (g3 != null && s.d(g3.b)) {
                    bundle.clear();
                    return bundle;
                } else if (g3 == null) {
                    str2 = String.format("fail permission denied, copyFile %s -> %s", optString, optString2);
                } else if (a(str, bundle, g2.f14211f)) {
                    return bundle;
                } else {
                    CountDownLatch countDownLatch = new CountDownLatch(1);
                    com.jingdong.manto.v.b.a.a().a(new b(this, g2, g3, countDownLatch));
                    try {
                        countDownLatch.await(5L, TimeUnit.SECONDS);
                        return bundle;
                    } catch (InterruptedException unused) {
                        str2 = String.format("fail copyFile was overtime", new Object[0]);
                    }
                }
            }
        }
        return a(bundle, str2);
    }

    /* JADX WARN: Not initialized variable reg: 2, insn: 0x004c: MOVE (r0 I:??[OBJECT, ARRAY]) = (r2 I:??[OBJECT, ARRAY]), block:B:30:0x004c */
    public static String b(File file) {
        InputStream inputStream;
        FileInputStream fileInputStream;
        MessageDigest messageDigest;
        InputStream inputStream2 = null;
        r0 = null;
        r0 = null;
        r0 = null;
        String str = null;
        if (file != null) {
            try {
                if (file.exists()) {
                    try {
                        messageDigest = MessageDigest.getInstance("SHA1");
                        fileInputStream = new FileInputStream(file);
                    } catch (IOException e2) {
                        e = e2;
                        fileInputStream = null;
                    } catch (NoSuchAlgorithmException e3) {
                        e = e3;
                        fileInputStream = null;
                    } catch (Throwable th) {
                        th = th;
                        t.a(inputStream2);
                        throw th;
                    }
                    try {
                        byte[] bArr = new byte[8192];
                        while (true) {
                            int read = fileInputStream.read(bArr);
                            if (read == -1) {
                                break;
                            }
                            messageDigest.update(bArr, 0, read);
                        }
                        str = new BigInteger(1, messageDigest.digest()).toString(16);
                    } catch (IOException e4) {
                        e = e4;
                        e.printStackTrace();
                        t.a(fileInputStream);
                        return str;
                    } catch (NoSuchAlgorithmException e5) {
                        e = e5;
                        e.printStackTrace();
                        t.a(fileInputStream);
                        return str;
                    }
                    t.a(fileInputStream);
                    return str;
                }
            } catch (Throwable th2) {
                th = th2;
                inputStream2 = inputStream;
            }
        }
        return null;
    }

    private final Bundle c(JSONObject jSONObject, String str) {
        Bundle bundle = new Bundle(2);
        String optString = jSONObject.optString("dirPath");
        boolean optBoolean = jSONObject.optBoolean("recursive", false);
        if (MantoStringUtils.isEmpty(optString)) {
            bundle.putString("message", "missing dirPath");
            bundle.putString(IMantoBaseModule.ERROR_CODE, "0");
            return bundle;
        } else if (!optString.equals("jdfile://usr/") && !optString.startsWith("jdfile://usr/")) {
            bundle.putString("message", "permission denied");
            bundle.putString(IMantoBaseModule.ERROR_CODE, "0");
            return bundle;
        } else {
            String replace = MantoStringUtils.deleteLeftSlash(optString.replaceFirst(Pattern.quote("jdfile://usr/"), "")).replace("\u0000", "");
            boolean contains = replace.contains("/");
            if (com.jingdong.manto.t.c.g(str, "jdfile://usr/") == null) {
                bundle.putString("message", "rootDir create failed.");
                bundle.putString(IMantoBaseModule.ERROR_CODE, "0");
                return bundle;
            }
            String str2 = com.jingdong.manto.t.c.g(str, "jdfile://usr/").b;
            if (contains) {
                String substring = replace.substring(0, replace.indexOf("/"));
                if (!new File(str2 + "/" + substring).exists() && !optBoolean) {
                    bundle.putString("message", "no such file or directory: " + substring);
                    bundle.putString(IMantoBaseModule.ERROR_CODE, "0");
                    return bundle;
                }
            }
            String str3 = str2 + "/" + replace;
            if (s.d(str3)) {
                bundle.putString("message", "file already exists: " + str3);
                bundle.putString(IMantoBaseModule.ERROR_CODE, "0");
                return bundle;
            }
            new File(str2 + "/" + replace).mkdirs();
            return bundle;
        }
    }

    private final void c(JSONObject jSONObject, String str, MantoResultCallBack mantoResultCallBack) {
        Bundle bundle = new Bundle();
        String optString = jSONObject.optString(TbsReaderView.KEY_FILE_PATH, "");
        if (MantoStringUtils.isEmpty(optString)) {
            bundle.putString("message", "missing param filePath");
            mantoResultCallBack.onFailed(bundle);
        } else if (!com.jingdong.manto.t.c.h(str, optString)) {
            bundle.putString("message", String.format("no such file or directory %s", optString));
            mantoResultCallBack.onFailed(bundle);
        } else {
            com.jingdong.manto.t.d g2 = com.jingdong.manto.t.c.g(str, optString);
            if (g2 == null || MantoStringUtils.isEmpty(g2.b)) {
                bundle.putString("message", "file doesn't exist");
                mantoResultCallBack.onFailed(bundle);
                return;
            }
            s.c(g2.b);
            mantoResultCallBack.onSuccess(bundle);
        }
    }

    private Bundle d(JSONObject jSONObject, String str) {
        String format;
        String str2;
        Bundle bundle = new Bundle(2);
        String optString = jSONObject.optString("dirPath");
        if (MantoStringUtils.isEmpty(optString)) {
            format = "missing param dirPath";
        } else {
            if (com.jingdong.manto.t.c.h(str, optString)) {
                com.jingdong.manto.t.d g2 = com.jingdong.manto.t.c.g(str, optString);
                if (g2 == null || (str2 = g2.b) == null || !s.d(str2)) {
                    format = String.format("no such file or directory %s", optString);
                } else {
                    File file = new File(g2.b);
                    if (file.isDirectory()) {
                        File[] listFiles = file.listFiles();
                        ArrayList<String> arrayList = new ArrayList<>();
                        if (listFiles.length > 0) {
                            for (File file2 : listFiles) {
                                arrayList.add(file2.getName());
                            }
                        }
                        bundle.putStringArrayList("files", arrayList);
                        return bundle;
                    }
                    format = String.format("not a directory %s", optString);
                }
            } else {
                format = String.format("no such file or directory %s", optString);
            }
        }
        return a(bundle, format);
    }

    private void d(JSONObject jSONObject, String str, MantoResultCallBack mantoResultCallBack) {
        String str2;
        StringBuilder sb;
        String str3;
        Bundle bundle = new Bundle(2);
        String optString = jSONObject.optString("zipFilePath");
        String optString2 = jSONObject.optString("targetPath");
        if (MantoStringUtils.isEmpty(optString) || MantoStringUtils.isEmpty(optString2)) {
            str2 = "missing params zipFilePath or targetPath";
        } else {
            com.jingdong.manto.t.d g2 = com.jingdong.manto.t.c.g(str, optString);
            if (g2 == null || !s.d(g2.b)) {
                sb = new StringBuilder();
                sb.append("zipFile is not existed, filePath: ");
                str3 = g2.b;
            } else {
                String str4 = com.jingdong.manto.t.c.g(str, "jdfile://usr/").b;
                String replace = MantoStringUtils.deleteLeftSlash(optString2.replaceFirst(Pattern.quote("jdfile://usr/"), "")).replace("\u0000", "");
                String str5 = str4 + "/" + replace;
                if (s.d(str5) || new File(str5).mkdirs()) {
                    long b2 = f0.b(g2.b);
                    MantoLog.d("FileInfo", "zipFile realSize: " + b2);
                    if (a(str, bundle, b2)) {
                        mantoResultCallBack.onFailed(bundle);
                        return;
                    }
                    List<File> a = f0.a(new File(g2.b), str4 + "/" + replace, false);
                    if (a != null && a.size() > 0) {
                        mantoResultCallBack.onSuccess(bundle);
                        return;
                    }
                    str2 = String.format("unzip failed or file isn't a zip, zipFile: %s", g2.b);
                } else {
                    sb = new StringBuilder();
                    sb.append("create dir ");
                    sb.append(str5);
                    str3 = " failed.";
                }
            }
            sb.append(str3);
            str2 = sb.toString();
        }
        a(bundle, str2);
        mantoResultCallBack.onFailed(bundle);
    }

    private final Bundle e(JSONObject jSONObject, String str) {
        String str2;
        String str3;
        StringBuilder sb;
        Bundle bundle = new Bundle(2);
        String optString = jSONObject.optString("oldPath");
        String optString2 = jSONObject.optString("newPath");
        if (MantoStringUtils.isEmpty(optString) || MantoStringUtils.isEmpty(optString2)) {
            str2 = "missing params oldPath or newPath";
        } else {
            com.jingdong.manto.t.d g2 = com.jingdong.manto.t.c.g(str, optString);
            if (g2 != null && s.d(g2.b)) {
                String str4 = com.jingdong.manto.t.c.g(str, "jdfile://usr/").b;
                MantoLog.d("FileInfo", "rootPath:" + str4);
                String str5 = "";
                if (optString2.startsWith("jdfile://usr/")) {
                    str3 = MantoStringUtils.deleteLeftSlash(optString2.replaceFirst(Pattern.quote("jdfile://usr/"), "")).replace("\u0000", "");
                    if (s.d(str4 + "/" + str3)) {
                        sb = new StringBuilder();
                        sb.append("newFile already existed: ");
                        sb.append(optString2);
                        str2 = sb.toString();
                    } else if (str3.contains("/")) {
                        str5 = str3.substring(0, str3.lastIndexOf("/"));
                        str3 = str3.substring(str3.lastIndexOf("/"));
                        MantoLog.d("FileInfo", "tarFName:" + str3);
                    }
                } else {
                    str3 = "";
                }
                if (!MantoStringUtils.isEmpty(str5)) {
                    if (!s.d(str4 + "/" + str5)) {
                        sb = new StringBuilder();
                        sb.append("no such file or directory, rename: ");
                        sb.append(optString2);
                        str2 = sb.toString();
                    }
                }
                File file = new File(g2.b);
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str4);
                sb2.append("/");
                sb2.append(str5);
                sb2.append("/");
                if (MantoStringUtils.isEmpty(str3)) {
                    str3 = file.getName();
                }
                sb2.append(str3);
                if (!file.renameTo(new File(sb2.toString()))) {
                    bundle.putString("message", "rename failed.");
                    bundle.putString(IMantoBaseModule.ERROR_CODE, "0");
                }
                return bundle;
            }
            str2 = "no such file or directory, rename: " + optString;
        }
        bundle.putString("message", str2);
        bundle.putString(IMantoBaseModule.ERROR_CODE, "0");
        return bundle;
    }

    private final Bundle f(JSONObject jSONObject, String str) {
        File[] listFiles;
        String str2;
        Bundle bundle = new Bundle(2);
        String optString = jSONObject.optString("dirPath");
        boolean optBoolean = jSONObject.optBoolean("recursive", false);
        if (MantoStringUtils.isEmpty(optString)) {
            str2 = "missing dirPath";
        } else if (!optString.startsWith("jdfile://usr/")) {
            str2 = "permission denied";
        } else if (optString.equals("jdfile://usr/")) {
            str2 = "can't remove root directory";
        } else {
            String replace = MantoStringUtils.deleteLeftSlash(optString.replaceFirst(Pattern.quote("jdfile://usr/"), "")).replace("\u0000", "");
            replace.contains("/");
            if (com.jingdong.manto.t.c.g(str, "jdfile://usr/") == null) {
                str2 = "rootDir not existed";
            } else {
                String str3 = com.jingdong.manto.t.c.g(str, "jdfile://usr/").b;
                File file = new File(str3 + "/" + replace);
                if (!file.exists()) {
                    str2 = "no such file or directory: " + replace;
                } else if (!file.isDirectory() || optBoolean || (listFiles = file.listFiles(new c(this))) == null || listFiles.length <= 0) {
                    s.c(str3 + "/" + replace);
                    return bundle;
                } else {
                    str2 = "directory not empty";
                }
            }
        }
        return a(bundle, str2);
    }

    private final Bundle g(JSONObject jSONObject, String str) {
        String str2;
        Bundle bundle = new Bundle(2);
        String optString = jSONObject.optString("tempFilePath");
        String optString2 = jSONObject.optString(TbsReaderView.KEY_FILE_PATH);
        if (MantoStringUtils.isEmpty(optString)) {
            str2 = "missing tempFilePath";
        } else if (com.jingdong.manto.t.c.h(str, optString)) {
            com.jingdong.manto.t.d g2 = com.jingdong.manto.t.c.g(str, optString);
            if (g2 == null || MantoStringUtils.isEmpty(g2.b)) {
                str2 = "file doesn't exist";
            } else if (g2.f14212g) {
                bundle.clear();
                return bundle;
            } else if (a(str, bundle, new File(g2.b).length())) {
                return bundle;
            } else {
                if (MantoStringUtils.isEmpty(optString2)) {
                    com.jingdong.manto.t.d a = com.jingdong.manto.t.c.a(str, g2);
                    if (a != null && !MantoStringUtils.isEmpty(a.a) && !MantoStringUtils.isEmpty(a.b)) {
                        bundle.putString("savedFilePath", a.a);
                        bundle.putString(IMantoBaseModule.ERROR_CODE, "1");
                        return bundle;
                    }
                    str2 = "unknown err";
                } else if (optString2.startsWith("jdfile://usr/")) {
                    String replace = MantoStringUtils.deleteLeftSlash(optString2.replaceFirst(Pattern.quote("jdfile://usr/"), "")).replace("\u0000", "");
                    if (com.jingdong.manto.t.c.g(str, "jdfile://usr/") == null) {
                        str2 = "rootDir create failed.";
                    } else {
                        boolean contains = replace.contains("/");
                        String str3 = com.jingdong.manto.t.c.g(str, "jdfile://usr/").b;
                        if (contains) {
                            replace.substring(replace.lastIndexOf("/"));
                            if (!s.d(str3 + "/" + replace.substring(0, replace.lastIndexOf("/")))) {
                                str2 = "no such file or directory: " + str3 + "/" + replace;
                            }
                        }
                        if (new File(g2.b).renameTo(new File(str3 + "/" + replace))) {
                            bundle.putString("savedFilePath", optString2);
                            return bundle;
                        }
                        str2 = "saveFile failed, filePath: " + optString2;
                    }
                } else {
                    str2 = "permission denied";
                }
            }
        } else {
            str2 = String.format("no such file or directory %s", optString);
        }
        return a(bundle, str2);
    }

    private final Bundle h(JSONObject jSONObject, String str) {
        String format;
        String str2;
        Bundle bundle = new Bundle(2);
        String optString = jSONObject.optString("path");
        if (MantoStringUtils.isEmpty(optString)) {
            format = "missing param path";
        } else if (com.jingdong.manto.t.c.h(str, optString)) {
            com.jingdong.manto.t.d g2 = com.jingdong.manto.t.c.g(str, optString);
            if (g2 != null && (str2 = g2.b) != null && s.d(str2)) {
                boolean optBoolean = jSONObject.optBoolean("recursive", false);
                File file = new File(g2.b);
                bundle.putString("stats", file.isDirectory() ? a(file, file.getAbsolutePath(), true, optBoolean).toString() : a(file).toString());
                return bundle;
            }
            format = String.format("no such file or directory %s", optString);
        } else {
            format = String.format("no such file or directory %s", optString);
        }
        return a(bundle, format);
    }

    private final Bundle i(JSONObject jSONObject, String str) {
        String str2;
        Bundle bundle = new Bundle(2);
        String optString = jSONObject.optString(TbsReaderView.KEY_FILE_PATH, "");
        if (MantoStringUtils.isEmpty(optString)) {
            str2 = "missing param filePath";
        } else if (com.jingdong.manto.t.c.h(str, optString)) {
            com.jingdong.manto.t.d g2 = com.jingdong.manto.t.c.g(str, optString);
            if (g2 != null && !MantoStringUtils.isEmpty(g2.b)) {
                s.c(g2.b);
                return bundle;
            }
            str2 = "file doesn't exist, filePath: " + optString;
        } else {
            str2 = String.format("no such file or directory %s", optString);
        }
        return a(bundle, str2);
    }

    public void b(JSONObject jSONObject, String str, MantoResultCallBack mantoResultCallBack) {
        Bundle bundle = new Bundle();
        String optString = jSONObject.optString(TbsReaderView.KEY_FILE_PATH, "");
        if (MantoStringUtils.isEmpty(optString)) {
            bundle.putString("message", "missing param filePath");
            mantoResultCallBack.onFailed(bundle);
        } else if (!com.jingdong.manto.t.c.h(str, optString)) {
            bundle.putString("message", String.format("no such file or directory %s", optString));
            mantoResultCallBack.onFailed(bundle);
        } else {
            com.jingdong.manto.t.d g2 = com.jingdong.manto.t.c.g(str, optString);
            if (g2 == null || !s.d(g2.b)) {
                bundle.putString("message", "file doesn't exist");
                mantoResultCallBack.onFailed(bundle);
                return;
            }
            File file = new File(g2.b);
            bundle.putLong(ApkDownloadTable.FIELD_SIZE, file.length());
            bundle.putLong("createTime", TimeUnit.MILLISECONDS.toSeconds(file.lastModified()));
            mantoResultCallBack.onSuccess(bundle);
        }
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public String getModuleName() {
        return "FileInfo";
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule, com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public void handleMethod(String str, MantoCore mantoCore, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
        JSONObject jSONObject;
        String string = bundle.getString("json");
        String string2 = bundle.getString(IMantoBaseModule.APP_UNIQUEID_ID_KEY, "");
        Bundle bundle2 = null;
        try {
            jSONObject = new JSONObject(string);
        } catch (JSONException e2) {
            e2.printStackTrace();
            jSONObject = null;
        }
        str.hashCode();
        char c2 = '\uffff';
        switch (str.hashCode()) {
            case -2139808842:
                if (str.equals("appendFile")) {
                    c2 = 0;
                    break;
                }
                break;
            case -2073025383:
                if (str.equals("saveFile")) {
                    c2 = 1;
                    break;
                }
                break;
            case -1995982721:
                if (str.equals("removeSavedFile")) {
                    c2 = 2;
                    break;
                }
                break;
            case -1851209669:
                if (str.equals("getSavedFileInfo")) {
                    c2 = 3;
                    break;
                }
                break;
            case -1851124693:
                if (str.equals("getSavedFileList")) {
                    c2 = 4;
                    break;
                }
                break;
            case -1423461020:
                if (str.equals("access")) {
                    c2 = 5;
                    break;
                }
                break;
            case -1406748165:
                if (str.equals("writeFile")) {
                    c2 = 6;
                    break;
                }
                break;
            case -867956686:
                if (str.equals("readFile")) {
                    c2 = 7;
                    break;
                }
                break;
            case -840447469:
                if (str.equals("unlink")) {
                    c2 = '\b';
                    break;
                }
                break;
            case -145518141:
                if (str.equals("fs_copyFile")) {
                    c2 = '\t';
                    break;
                }
                break;
            case 3540564:
                if (str.equals("stat")) {
                    c2 = '\n';
                    break;
                }
                break;
            case 103950895:
                if (str.equals("mkdir")) {
                    c2 = 11;
                    break;
                }
                break;
            case 108628082:
                if (str.equals("rmdir")) {
                    c2 = '\f';
                    break;
                }
                break;
            case 111449576:
                if (str.equals(IExceptionHandler.DynamicExceptionData.TYPE_UNZIP)) {
                    c2 = '\r';
                    break;
                }
                break;
            case 656840432:
                if (str.equals("fs_rename")) {
                    c2 = 14;
                    break;
                }
                break;
            case 1080408887:
                if (str.equals("readdir")) {
                    c2 = 15;
                    break;
                }
                break;
            case 1342041536:
                if (str.equals("getFileInfo")) {
                    c2 = 16;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                bundle2 = a(jSONObject, string2, true);
                break;
            case 1:
                bundle2 = g(jSONObject, string2);
                break;
            case 2:
                c(jSONObject, string2, mantoResultCallBack);
                break;
            case 3:
                b(jSONObject, string2, mantoResultCallBack);
                break;
            case 4:
                a(string2, mantoResultCallBack);
                break;
            case 5:
                bundle2 = a(jSONObject, string2);
                break;
            case 6:
                bundle2 = a(jSONObject, string2, false);
                break;
            case 7:
                a(mantoCore, jSONObject, string2, mantoResultCallBack);
                return;
            case '\b':
                bundle2 = i(jSONObject, string2);
                break;
            case '\t':
                bundle2 = b(jSONObject, string2);
                break;
            case '\n':
                bundle2 = h(jSONObject, string2);
                break;
            case 11:
                bundle2 = c(jSONObject, string2);
                break;
            case '\f':
                bundle2 = f(jSONObject, string2);
                break;
            case '\r':
                d(jSONObject, string2, mantoResultCallBack);
                break;
            case 14:
                bundle2 = e(jSONObject, string2);
                break;
            case 15:
                bundle2 = d(jSONObject, string2);
                break;
            case 16:
                a(jSONObject, string2, mantoResultCallBack);
                break;
        }
        if (bundle2 != null) {
            if ("0".equals(bundle2.getString(IMantoBaseModule.ERROR_CODE, ""))) {
                mantoResultCallBack.onFailed(bundle2);
            } else {
                mantoResultCallBack.onSuccess(bundle2);
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x009c, code lost:
        if (r8.equals("accessSync") == false) goto L6;
     */
    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule, com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.os.Bundle handleMethodSync(java.lang.String r8, com.jingdong.manto.MantoCore r9, android.os.Bundle r10) {
        /*
            Method dump skipped, instructions count: 336
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.manto.m.y0.a.handleMethodSync(java.lang.String, com.jingdong.manto.MantoCore, android.os.Bundle):android.os.Bundle");
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public Bundle initData(String str, MantoCore mantoCore, JSONObject jSONObject) {
        Bundle bundle = new Bundle();
        bundle.putString("json", jSONObject.toString());
        MantoLog.e("FileInfo", "initData json params:" + jSONObject);
        return bundle;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule
    protected void injectJsApiMethod(List<JsApiMethod> list) {
        list.add(new JsApiMethod("getFileInfo", 1));
        list.add(new JsApiMethod("getSavedFileInfo", 1));
        list.add(new JsApiMethod("getSavedFileList", 1));
        list.add(new JsApiMethod("removeSavedFile", 1));
        list.add(new JsApiMethod("saveFile", 1));
        list.add(new JsApiMethod("access", 1));
        list.add(new JsApiMethod("accessSync", 3));
        list.add(new JsApiMethod("readdir", 1));
        list.add(new JsApiMethod("readdirSync", 3));
        list.add(new JsApiMethod("readFile", 1));
        list.add(new JsApiMethod("readFileSync", 3));
        list.add(new JsApiMethod("stat", 1));
        list.add(new JsApiMethod("statSync", 3));
        list.add(new JsApiMethod("saveFileSync", 3));
        list.add(new JsApiMethod("mkdir", 1));
        list.add(new JsApiMethod("mkdirSync", 3));
        list.add(new JsApiMethod("rmdir", 1));
        list.add(new JsApiMethod("rmdirSync", 3));
        list.add(new JsApiMethod("fs_rename", 1));
        list.add(new JsApiMethod("fs_renameSync", 3));
        list.add(new JsApiMethod("unlinkSync", 3));
        list.add(new JsApiMethod("fs_copyFileSync", 3));
        list.add(new JsApiMethod("fs_copyFile", 1));
        list.add(new JsApiMethod("writeFile", 1));
        list.add(new JsApiMethod("writeFileSync", 3));
        list.add(new JsApiMethod(IExceptionHandler.DynamicExceptionData.TYPE_UNZIP, 1));
        list.add(new JsApiMethod("appendFileSync", 3));
        list.add(new JsApiMethod("appendFile", 1));
    }
}
