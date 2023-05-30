package com.jd.dynamic.b.m.a;

import com.jd.dynamic.DYConstants;
import com.jd.dynamic.b.m.i;
import com.jd.dynamic.b.m.j;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes13.dex */
public final class b implements i {
    private final List<j> a;

    /* JADX WARN: Multi-variable type inference failed */
    public b(@NotNull List<? extends j> list) {
        this.a = list;
    }

    @Override // com.jd.dynamic.b.m.i
    public void a(@NotNull String str) {
        File[] listFiles;
        File file = new File(str);
        if (file.exists() && file.isDirectory() && (listFiles = file.listFiles()) != null) {
            Iterator it = ArrayIteratorKt.iterator(listFiles);
            while (it.hasNext()) {
                File file2 = (File) it.next();
                Intrinsics.checkExpressionValueIsNotNull(file2, "file");
                if (!file2.isDirectory()) {
                    boolean z = true;
                    Iterator<j> it2 = this.a.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            break;
                        } else if (!it2.next().a(file2)) {
                            z = false;
                            break;
                        }
                    }
                    if (z) {
                        c.b.a(file2);
                        DYConstants.DYLog(" delete file -> file path: " + file2.getAbsolutePath() + " file name is : " + file2.getName() + " , length " + file2.length());
                        file2.delete();
                    }
                }
            }
        }
    }
}
