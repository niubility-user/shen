package kotlin.io;

import java.io.File;
import java.nio.charset.Charset;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"kotlin/io/FilesKt__FilePathComponentsKt", "kotlin/io/FilesKt__FileReadWriteKt", "kotlin/io/FilesKt__FileTreeWalkKt", "kotlin/io/FilesKt__UtilsKt"}, d2 = {}, k = 4, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public final class FilesKt extends FilesKt__UtilsKt {
    private FilesKt() {
    }

    public static /* bridge */ /* synthetic */ String readText$default(File file, Charset charset, int i2, Object obj) {
        return FilesKt__FileReadWriteKt.readText$default(file, charset, i2, obj);
    }
}
