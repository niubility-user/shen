package kotlin.io;

import java.io.File;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0004\u0010\u0005\u00a8\u0006\u0006"}, d2 = {"Lkotlin/io/TerminateException;", "Lkotlin/io/FileSystemException;", "Ljava/io/File;", "file", "<init>", "(Ljava/io/File;)V", "kotlin-stdlib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes11.dex */
final class TerminateException extends FileSystemException {
    public TerminateException(@NotNull File file) {
        super(file, null, null, 6, null);
    }
}
