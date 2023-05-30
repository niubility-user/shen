package com.jd.dynamic.b.m.a;

import com.jd.dynamic.b.m.i;
import com.jd.dynamic.b.m.j;
import com.jd.dynamic.base.DynamicSdk;
import com.jingdong.sdk.platform.business.personal.R2;
import java.io.File;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes13.dex */
public final class a implements i {
    private final j a;

    public a(@NotNull j jVar) {
        this.a = jVar;
    }

    private final void a(File file) {
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            for (File it : listFiles) {
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                if (it.isDirectory()) {
                    a(it);
                } else {
                    it.delete();
                    DynamicSdk.handException("file", "DYBackupFileCleaner deleteFile " + it.getAbsolutePath(), (String) null, (String) null, (int) R2.attr.itemHorizontalPadding, (Exception) null);
                }
            }
            file.delete();
            DynamicSdk.handException("file", "DYBackupFileCleaner deleteDir " + file.getAbsolutePath(), (String) null, (String) null, (int) R2.attr.itemHorizontalPadding, (Exception) null);
        }
    }

    @Override // com.jd.dynamic.b.m.i
    public void a(@NotNull String str) {
        File[] listFiles;
        File file = new File(str);
        if (file.exists() && file.isDirectory() && (listFiles = file.listFiles()) != null) {
            for (File it : listFiles) {
                j jVar = this.a;
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                if (jVar.a(it)) {
                    a(it);
                }
            }
        }
    }
}
