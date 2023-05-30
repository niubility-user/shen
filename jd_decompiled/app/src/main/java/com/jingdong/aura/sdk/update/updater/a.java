package com.jingdong.aura.sdk.update.updater;

import com.jingdong.aura.sdk.update.b.g;
import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.zip.ZipFile;

/* loaded from: classes4.dex */
public final class a {
    private ExecutorService a;

    static boolean a(File file) {
        if (file.exists()) {
            try {
                g.a(new ZipFile(file));
                return true;
            } catch (Exception unused) {
                g.a((ZipFile) null);
                return false;
            } catch (Throwable th) {
                g.a((ZipFile) null);
                throw th;
            }
        }
        return false;
    }

    public final synchronized ExecutorService a() {
        if (this.a == null) {
            this.a = Executors.newSingleThreadExecutor(new ThreadFactory() { // from class: com.jingdong.aura.sdk.update.updater.a.1
                @Override // java.util.concurrent.ThreadFactory
                public final Thread newThread(Runnable runnable) {
                    return new Thread(runnable, "updateAuraBundle");
                }
            });
        }
        return this.a;
    }
}
