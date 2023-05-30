package com.jd.dynamic.b.m;

import com.jd.dynamic.base.DynamicSdk;
import java.io.File;

/* loaded from: classes13.dex */
public class f {

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static class b {
        private static final f a = new f();
    }

    private f() {
    }

    public static f a() {
        return b.a;
    }

    public void b() {
        if (com.jd.dynamic.b.a.b.o().M()) {
            g.a().d();
            g.a().e().a(new com.jd.dynamic.b.m.b(DynamicSdk.getEngine().getContext().getFilesDir().getAbsolutePath() + File.separator + DynamicSdk.getEngine().getCacheDir(), g.a().b(e.CLEAN_TYPE_ILLEGAL_FILE_RECURSIVE)));
            g.a().e().a(new com.jd.dynamic.b.m.b(com.jd.dynamic.b.i.a.d(), g.a().b(e.CLEAN_TYPE_BACKUP_UNZIP_FILE)));
        }
    }
}
