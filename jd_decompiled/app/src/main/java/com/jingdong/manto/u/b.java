package com.jingdong.manto.u;

import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoProcessUtil;
import com.jingdong.manto.utils.b0;
import com.jingdong.manto.utils.e0;
import com.jingdong.manto.utils.f0;
import com.jingdong.manto.utils.s;
import java.io.File;
import java.util.List;

/* loaded from: classes16.dex */
public class b {

    /* renamed from: c  reason: collision with root package name */
    private static final String f14217c = "com.jingdong.manto.u.b";
    private static b d;
    private boolean a = false;
    private String b = b0.a(com.jingdong.a.g());

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class a implements Runnable {
        final /* synthetic */ c a;

        a(c cVar) {
            this.a = cVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            b.this.a(this.a);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.manto.u.b$b  reason: collision with other inner class name */
    /* loaded from: classes16.dex */
    public class C0675b extends com.jingdong.manto.network.common.b {
        long a = System.currentTimeMillis();
        final /* synthetic */ File b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ c f14218c;

        C0675b(File file, c cVar) {
            this.b = file;
            this.f14218c = cVar;
        }

        private void a() {
            s.b(this.b);
        }

        @Override // com.jingdong.manto.network.common.b
        public void a(long j2, long j3, boolean z) {
            if (System.currentTimeMillis() - this.a >= 150 || j2 == j3) {
                MantoLog.d(b.f14217c, "update: ", "" + j2 + ", " + j3 + DYConstants.DY_REGEX_COMMA + z);
                this.a = System.currentTimeMillis();
            }
        }

        @Override // com.jingdong.manto.network.common.b
        public void a(com.jingdong.manto.network.mantorequests.b bVar) {
            long currentTimeMillis = System.currentTimeMillis();
            int i2 = 0;
            if (!TextUtils.equals(b.this.h() ? "064b8fd0f03b05d88cfa6cdbc091ab66" : "3f421b4143076d6668532b7c7e180ee0", s.e(this.b.getPath()))) {
                MantoLog.e("download file md5 don't match", new Object[0]);
                this.b.delete();
                this.f14218c.onFail();
                return;
            }
            File file = this.b;
            List<File> a = f0.a(file, file.getParent(), true);
            if (a == null || a.size() <= 0) {
                return;
            }
            File file2 = null;
            while (true) {
                if (i2 >= a.size()) {
                    break;
                }
                File file3 = a.get(i2);
                if (file3.getName().endsWith(".so")) {
                    file2 = new File(b.this.e());
                    file3.renameTo(file2);
                    break;
                }
                i2++;
            }
            if (file2 == null || !b.this.a(file2)) {
                a();
                c cVar = this.f14218c;
                if (cVar != null) {
                    cVar.onFail();
                    return;
                }
                return;
            }
            b.this.a = e0.a(com.jingdong.a.g(), b.this.d());
            if (this.f14218c != null) {
                if (b.this.a) {
                    this.f14218c.onSuccess();
                } else {
                    this.f14218c.onFail();
                }
            }
            MantoLog.d(b.f14217c, "V8 onSuccess, unZip, cost: " + (System.currentTimeMillis() - currentTimeMillis));
        }

        @Override // com.jingdong.manto.network.common.b
        public void a(Throwable th) {
            super.a(th);
            MantoLog.e(b.f14217c, "onError: ", th);
            a();
            c cVar = this.f14218c;
            if (cVar != null) {
                cVar.onFail();
            }
        }
    }

    /* loaded from: classes16.dex */
    public interface c {
        void onFail();

        void onSuccess();
    }

    private b() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(c cVar) {
        String str = h() ? "https://storage.360buyimg.com/jd-manto/arm64-v8a/j2v8-64-064b8fd0f03b05d88cfa6cdbc091ab66.zip" : "https://storage.360buyimg.com/jd-manto/armeabi-v7a/j2v8-32-3f421b4143076d6668532b7c7e180ee0.zip";
        File file = new File(f());
        com.jingdong.manto.network.common.c.a(new com.jingdong.manto.network.mantorequests.a(str, file.getParent(), file.getName(), false), false, "miniAppV8", false, new C0675b(file, cVar));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(File file) {
        return TextUtils.equals(s.e(file.getPath()), h() ? "7e4abc50e4f29676f8383fbe6c8ba4e9" : "b1dd4540f8adbdd5f57f669763fa0241");
    }

    private boolean b() {
        return new File(com.jingdong.a.g().getApplicationInfo().nativeLibraryDir, "libj2v8.so").exists();
    }

    public static b c() {
        if (d == null) {
            synchronized (b.class) {
                if (d == null) {
                    d = new b();
                }
            }
        }
        return d;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String d() {
        File file = new File(com.jingdong.manto.c.a().getFilesDir(), "manto");
        if (!file.exists() && !file.isDirectory()) {
            file.mkdirs();
        }
        File file2 = new File(file, h() ? "share_lib_64" : "share_lib");
        if (!file2.exists() && !file2.isDirectory()) {
            file2.mkdirs();
        }
        return file2.getAbsolutePath();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String e() {
        return d() + File.separator + "libj2v8.so";
    }

    private String f() {
        return d() + File.separator + "libj2v8.zip";
    }

    public void b(c cVar) {
        if (b()) {
            this.a = true;
            cVar.onSuccess();
        } else if (!a(new File(e()))) {
            if (MantoProcessUtil.isMainProcess()) {
                com.jingdong.manto.b.d().networkIO().execute(new a(cVar));
            }
        } else {
            boolean a2 = e0.a(com.jingdong.a.g(), d());
            this.a = a2;
            if (cVar != null) {
                if (a2) {
                    cVar.onSuccess();
                } else {
                    cVar.onFail();
                }
            }
        }
    }

    public boolean g() {
        if (a(new File(e()))) {
            boolean a2 = e0.a(com.jingdong.a.g(), d());
            this.a = a2;
            return a2;
        }
        return false;
    }

    public boolean h() {
        return TextUtils.equals(this.b, "arm64-v8a");
    }

    public boolean i() {
        return this.a;
    }
}
