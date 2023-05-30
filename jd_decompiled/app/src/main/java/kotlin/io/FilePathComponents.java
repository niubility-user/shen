package kotlin.io;

import com.jd.viewkit.templates.view.helper.JDViewKitEventHelper;
import com.jingdong.common.apkcenter.ApkDownloadTable;
import java.io.File;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u000f\b\u0080\b\u0018\u00002\u00020\u0001B\u001f\b\u0000\u0012\u0006\u0010\r\u001a\u00020\u0005\u0012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00050\n\u00a2\u0006\u0004\b$\u0010%J\u001d\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00020\u0005H\u00c6\u0003\u00a2\u0006\u0004\b\b\u0010\tJ\u0016\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\nH\u00c6\u0003\u00a2\u0006\u0004\b\u000b\u0010\fJ*\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\r\u001a\u00020\u00052\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00050\nH\u00c6\u0001\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010\u0012\u001a\u00020\u0011H\u00d6\u0001\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u0010\u0010\u0014\u001a\u00020\u0002H\u00d6\u0001\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u001a\u0010\u0018\u001a\u00020\u00172\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003\u00a2\u0006\u0004\b\u0018\u0010\u0019R\u0013\u0010\u001a\u001a\u00020\u00178F@\u0006\u00a2\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u001f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00050\n8\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u000e\u0010\u001c\u001a\u0004\b\u001d\u0010\fR\u0013\u0010\u001f\u001a\u00020\u00028F@\u0006\u00a2\u0006\u0006\u001a\u0004\b\u001e\u0010\u0015R\u0019\u0010\r\u001a\u00020\u00058\u0006@\u0006\u00a2\u0006\f\n\u0004\b\r\u0010 \u001a\u0004\b!\u0010\tR\u0013\u0010#\u001a\u00020\u00118F@\u0006\u00a2\u0006\u0006\u001a\u0004\b\"\u0010\u0013\u00a8\u0006&"}, d2 = {"Lkotlin/io/FilePathComponents;", "", "", "beginIndex", "endIndex", "Ljava/io/File;", "subPath", "(II)Ljava/io/File;", "component1", "()Ljava/io/File;", "", "component2", "()Ljava/util/List;", "root", "segments", JDViewKitEventHelper.ActionType_Copy, "(Ljava/io/File;Ljava/util/List;)Lkotlin/io/FilePathComponents;", "", "toString", "()Ljava/lang/String;", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "isRooted", "()Z", "Ljava/util/List;", "getSegments", "getSize", ApkDownloadTable.FIELD_SIZE, "Ljava/io/File;", "getRoot", "getRootName", "rootName", "<init>", "(Ljava/io/File;Ljava/util/List;)V", "kotlin-stdlib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public final /* data */ class FilePathComponents {
    @NotNull
    private final File root;
    @NotNull
    private final List<File> segments;

    /* JADX WARN: Multi-variable type inference failed */
    public FilePathComponents(@NotNull File file, @NotNull List<? extends File> list) {
        this.root = file;
        this.segments = list;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ FilePathComponents copy$default(FilePathComponents filePathComponents, File file, List list, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            file = filePathComponents.root;
        }
        if ((i2 & 2) != 0) {
            list = filePathComponents.segments;
        }
        return filePathComponents.copy(file, list);
    }

    @NotNull
    /* renamed from: component1  reason: from getter */
    public final File getRoot() {
        return this.root;
    }

    @NotNull
    public final List<File> component2() {
        return this.segments;
    }

    @NotNull
    public final FilePathComponents copy(@NotNull File root, @NotNull List<? extends File> segments) {
        return new FilePathComponents(root, segments);
    }

    public boolean equals(@Nullable Object other) {
        if (this != other) {
            if (other instanceof FilePathComponents) {
                FilePathComponents filePathComponents = (FilePathComponents) other;
                return Intrinsics.areEqual(this.root, filePathComponents.root) && Intrinsics.areEqual(this.segments, filePathComponents.segments);
            }
            return false;
        }
        return true;
    }

    @NotNull
    public final File getRoot() {
        return this.root;
    }

    @NotNull
    public final String getRootName() {
        String path = this.root.getPath();
        Intrinsics.checkExpressionValueIsNotNull(path, "root.path");
        return path;
    }

    @NotNull
    public final List<File> getSegments() {
        return this.segments;
    }

    public final int getSize() {
        return this.segments.size();
    }

    public int hashCode() {
        File file = this.root;
        int hashCode = (file != null ? file.hashCode() : 0) * 31;
        List<File> list = this.segments;
        return hashCode + (list != null ? list.hashCode() : 0);
    }

    public final boolean isRooted() {
        String path = this.root.getPath();
        Intrinsics.checkExpressionValueIsNotNull(path, "root.path");
        return path.length() > 0;
    }

    @NotNull
    public final File subPath(int beginIndex, int endIndex) {
        String joinToString$default;
        if (beginIndex >= 0 && beginIndex <= endIndex && endIndex <= getSize()) {
            List<File> subList = this.segments.subList(beginIndex, endIndex);
            String str = File.separator;
            Intrinsics.checkExpressionValueIsNotNull(str, "File.separator");
            joinToString$default = CollectionsKt___CollectionsKt.joinToString$default(subList, str, null, null, 0, null, null, 62, null);
            return new File(joinToString$default);
        }
        throw new IllegalArgumentException();
    }

    @NotNull
    public String toString() {
        return "FilePathComponents(root=" + this.root + ", segments=" + this.segments + ")";
    }
}
