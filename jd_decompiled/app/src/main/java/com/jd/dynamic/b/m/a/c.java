package com.jd.dynamic.b.m.a;

import com.jd.dynamic.DYConstants;
import com.jd.dynamic.b.m.i;
import com.jd.dynamic.b.m.j;
import com.jd.dynamic.base.DynamicSdk;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes13.dex */
public final class c implements i {
    public static final a b = new a(null);
    private final List<j> a;

    /* loaded from: classes13.dex */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void a(@Nullable File file) {
            if (file != null) {
                DynamicSdk.handException("file", "file name : " + file.getName() + " length: " + file.length() + " modify time: " + file.lastModified(), "", "", 1100, (Exception) null);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public c(@NotNull List<? extends j> list) {
        this.a = list;
    }

    private final void a(File file) {
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            Iterator it = ArrayIteratorKt.iterator(listFiles);
            while (it.hasNext()) {
                File file2 = (File) it.next();
                if (file2.exists()) {
                    Intrinsics.checkExpressionValueIsNotNull(file2, "file");
                    if (file2.isDirectory()) {
                        a(file2);
                    } else {
                        boolean z = true;
                        Iterator<j> it2 = this.a.iterator();
                        while (it2.hasNext()) {
                            if (!it2.next().a(file2)) {
                                z = false;
                            }
                        }
                        if (z) {
                            b.a(file2);
                            DYConstants.DYLog(" delete file -> file path: " + file2.getAbsolutePath() + " file name is : " + file2.getName() + " , length " + file2.length());
                            file2.delete();
                        }
                    }
                }
            }
        }
    }

    @Override // com.jd.dynamic.b.m.i
    public void a(@NotNull String str) {
        DYConstants.DYLog("path is : " + str);
        File file = new File(str);
        if (file.exists() && file.isDirectory()) {
            a(file);
        }
    }
}
