package com.jingdong.aura.a.b.l;

import com.jingdong.aura.core.nativelib.AuraNative;
import java.io.File;
import java.io.IOException;

/* loaded from: classes4.dex */
public class g extends d {
    /* JADX INFO: Access modifiers changed from: package-private */
    public g(String str, int i2, File file, File file2) {
        super(str, i2, file, file2);
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
        AuraNative.a(this.f12131e.getAbsolutePath(), file.getAbsolutePath());
        a(file);
        com.jingdong.aura.core.util.a.a().b(file);
        try {
            h();
        } catch (IOException e2) {
            e2.printStackTrace();
            com.jingdong.aura.a.b.e.a(this.b, this.f12130c, "updateMetadata failed.", "optDexFile", e2);
        }
    }
}
