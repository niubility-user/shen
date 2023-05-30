package com.jingdong.aura.a.b.l;

import android.os.Build;
import com.jingdong.aura.core.util.k;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/* loaded from: classes4.dex */
public class f extends d {

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class a {
        static final Executor a = Executors.newSingleThreadExecutor();

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: com.jingdong.aura.a.b.l.f$a$a  reason: collision with other inner class name */
        /* loaded from: classes4.dex */
        public class RunnableC0394a implements Runnable {
            final /* synthetic */ InputStream a;

            RunnableC0394a(InputStream inputStream) {
                this.a = inputStream;
            }

            @Override // java.lang.Runnable
            public void run() {
                InputStream inputStream;
                if (this.a == null) {
                    return;
                }
                do {
                    try {
                        try {
                        } catch (IOException unused) {
                            inputStream = this.a;
                        } catch (Throwable th) {
                            try {
                                this.a.close();
                            } catch (Exception unused2) {
                            }
                            throw th;
                        }
                    } catch (Exception unused3) {
                        return;
                    }
                } while (this.a.read(new byte[256]) > 0);
                inputStream = this.a;
                inputStream.close();
            }
        }

        static void a(InputStream inputStream) {
            a.execute(new RunnableC0394a(inputStream));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public f(String str, int i2, File file, File file2) {
        super(str, i2, file, file2);
    }

    private void a(String str, String str2) {
        long currentTimeMillis = System.currentTimeMillis();
        File file = new File(str2);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add("dex2oat");
        arrayList.add("--dex-file=" + str);
        arrayList.add("--oat-file=" + str2);
        arrayList.add("--instruction-set=" + k.a());
        if (Build.VERSION.SDK_INT >= com.jingdong.aura.a.b.c.n()) {
            arrayList.add("--compiler-filter=quicken");
        } else {
            arrayList.add("--compiler-filter=interpret-only");
        }
        com.jingdong.aura.core.util.l.b bVar = d.f12129g;
        bVar.d("currentThread:" + Thread.currentThread().getName() + " dex2oat cmd: " + arrayList);
        ProcessBuilder processBuilder = new ProcessBuilder(arrayList);
        processBuilder.redirectErrorStream(true);
        Process start = processBuilder.start();
        a.a(start.getInputStream());
        a.a(start.getErrorStream());
        try {
            int waitFor = start.waitFor();
            if (waitFor == 0) {
                bVar.a("dex2oat const:" + (System.currentTimeMillis() - currentTimeMillis));
                return;
            }
            throw new IOException("dex2oat works unsuccessfully, exit code: " + waitFor);
        } catch (InterruptedException e2) {
            throw new IOException("dex2oat is interrupted, msg: " + e2.getMessage(), e2);
        }
    }

    @Override // com.jingdong.aura.a.b.l.d
    public synchronized void f() {
        if (e()) {
            return;
        }
        File file = new File(this.d, "bundle.dex");
        file.delete();
        if (!com.jingdong.aura.core.util.a.a().a(file)) {
            d.f12129g.d("Failed to get file lock for " + this.f12131e.getAbsolutePath());
        }
        if (Build.VERSION.SDK_INT >= com.jingdong.aura.a.b.c.n() && k.b().booleanValue()) {
            a(this.f12131e.getAbsolutePath(), file.getAbsolutePath());
        } else {
            a(file);
        }
        com.jingdong.aura.core.util.a.a().b(file);
        try {
            h();
        } catch (IOException e2) {
            e2.printStackTrace();
            com.jingdong.aura.a.b.e.a(this.b, this.f12130c, "updateMetadata failed.", "optDexFile", e2);
        }
    }
}
