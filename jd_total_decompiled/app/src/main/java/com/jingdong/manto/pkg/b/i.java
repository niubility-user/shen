package com.jingdong.manto.pkg.b;

import android.content.Context;
import android.text.TextUtils;
import com.tencent.smtt.utils.Md5Utils;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* loaded from: classes16.dex */
public interface i {

    /* loaded from: classes16.dex */
    public static class a implements i {
        private Context a;

        public a(Context context) {
            this.a = context;
        }

        @Override // com.jingdong.manto.pkg.b.i
        public InputStream a(String str) {
            try {
                return this.a.getAssets().open(str);
            } catch (Throwable unused) {
                return null;
            }
        }

        @Override // com.jingdong.manto.pkg.b.i
        public boolean a() {
            return true;
        }
    }

    /* loaded from: classes16.dex */
    public static class b implements i {
        private File a;
        private List<String> b;

        public b(File file, List<String> list) {
            this.b = list;
            this.a = file;
        }

        @Override // com.jingdong.manto.pkg.b.i
        public InputStream a(String str) {
            File[] listFiles;
            try {
                List<String> list = this.b;
                if ((list != null && list.contains(str)) && (listFiles = this.a.listFiles()) != null && listFiles.length != 0) {
                    for (File file : listFiles) {
                        if (TextUtils.equals(file.getName(), str)) {
                            return new FileInputStream(file);
                        }
                    }
                }
            } catch (Throwable unused) {
            }
            return null;
        }

        @Override // com.jingdong.manto.pkg.b.i
        public boolean a() {
            return true;
        }
    }

    /* loaded from: classes16.dex */
    public static class c implements i {
        private boolean a;
        private ZipFile b;

        /* renamed from: c  reason: collision with root package name */
        private List<String> f13987c;

        public c(File file, String str, List<String> list) {
            this.f13987c = list;
            boolean equals = TextUtils.equals(str, Md5Utils.getMD5(file));
            this.a = equals;
            if (equals) {
                try {
                    this.b = new ZipFile(file);
                } catch (Throwable unused) {
                    this.a = false;
                }
            }
        }

        @Override // com.jingdong.manto.pkg.b.i
        public InputStream a(String str) {
            List<String> list;
            if (this.a) {
                try {
                    list = this.f13987c;
                } catch (Throwable unused) {
                }
                if (list != null && list.contains(str)) {
                    Enumeration<? extends ZipEntry> entries = this.b.entries();
                    while (entries.hasMoreElements()) {
                        ZipEntry nextElement = entries.nextElement();
                        if (TextUtils.equals(nextElement.getName(), str)) {
                            return new BufferedInputStream(this.b.getInputStream(nextElement));
                        }
                    }
                    return null;
                }
                return null;
            }
            return null;
        }

        @Override // com.jingdong.manto.pkg.b.i
        public boolean a() {
            return this.a;
        }
    }

    /* loaded from: classes16.dex */
    public static class d implements i {
        e a;

        public d(String str) {
            e eVar = new e(str);
            this.a = eVar;
            if (eVar.f13979c) {
                this.a.b();
            }
        }

        @Override // com.jingdong.manto.pkg.b.i
        public InputStream a(String str) {
            if (!TextUtils.isEmpty(str) && this.a.f13979c) {
                try {
                    return this.a.a(str);
                } catch (Exception unused) {
                }
            }
            return null;
        }

        @Override // com.jingdong.manto.pkg.b.i
        public boolean a() {
            e eVar = this.a;
            return eVar != null && eVar.f13979c;
        }
    }

    InputStream a(String str);

    boolean a();
}
