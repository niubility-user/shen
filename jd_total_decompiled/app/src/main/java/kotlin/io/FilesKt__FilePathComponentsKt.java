package kotlin.io;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\t\u001a\u0013\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003\u001a\u0013\u0010\u0007\u001a\u00020\u0006*\u00020\u0005H\u0000\u00a2\u0006\u0004\b\u0007\u0010\b\u001a#\u0010\u000b\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\t\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u0001H\u0000\u00a2\u0006\u0004\b\u000b\u0010\f\"\u0017\u0010\u000e\u001a\u00020\r*\u00020\u00058F@\u0006\u00a2\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f\"\u001a\u0010\u0012\u001a\u00020\u0000*\u00020\u00058@@\u0000X\u0080\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011\"\u001a\u0010\u0015\u001a\u00020\u0005*\u00020\u00058@@\u0000X\u0080\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014\u00a8\u0006\u0016"}, d2 = {"", "", "getRootLength$FilesKt__FilePathComponentsKt", "(Ljava/lang/String;)I", "getRootLength", "Ljava/io/File;", "Lkotlin/io/FilePathComponents;", "toComponents", "(Ljava/io/File;)Lkotlin/io/FilePathComponents;", "beginIndex", "endIndex", "subPath", "(Ljava/io/File;II)Ljava/io/File;", "", "isRooted", "(Ljava/io/File;)Z", "getRootName", "(Ljava/io/File;)Ljava/lang/String;", "rootName", "getRoot", "(Ljava/io/File;)Ljava/io/File;", "root", "kotlin-stdlib"}, k = 5, mv = {1, 4, 0}, xs = "kotlin/io/FilesKt")
/* loaded from: classes11.dex */
public class FilesKt__FilePathComponentsKt {
    @NotNull
    public static final File getRoot(@NotNull File file) {
        return new File(getRootName(file));
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x001c, code lost:
        r0 = kotlin.text.StringsKt__StringsKt.indexOf$default((java.lang.CharSequence) r8, r3, 2, false, 4, (java.lang.Object) null);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static final int getRootLength$FilesKt__FilePathComponentsKt(@NotNull String str) {
        int indexOf$default;
        boolean endsWith$default;
        int indexOf$default2;
        int indexOf$default3;
        indexOf$default = StringsKt__StringsKt.indexOf$default((CharSequence) str, File.separatorChar, 0, false, 4, (Object) null);
        if (indexOf$default == 0) {
            if (str.length() > 1) {
                char charAt = str.charAt(1);
                char c2 = File.separatorChar;
                if (charAt == c2 && indexOf$default2 >= 0) {
                    indexOf$default3 = StringsKt__StringsKt.indexOf$default((CharSequence) str, File.separatorChar, indexOf$default2 + 1, false, 4, (Object) null);
                    return indexOf$default3 >= 0 ? indexOf$default3 + 1 : str.length();
                }
            }
            return 1;
        } else if (indexOf$default <= 0 || str.charAt(indexOf$default - 1) != ':') {
            if (indexOf$default == -1) {
                endsWith$default = StringsKt__StringsKt.endsWith$default((CharSequence) str, ':', false, 2, (Object) null);
                if (endsWith$default) {
                    return str.length();
                }
            }
            return 0;
        } else {
            return indexOf$default + 1;
        }
    }

    @NotNull
    public static final String getRootName(@NotNull File file) {
        String path = file.getPath();
        Intrinsics.checkExpressionValueIsNotNull(path, "path");
        String path2 = file.getPath();
        Intrinsics.checkExpressionValueIsNotNull(path2, "path");
        int rootLength$FilesKt__FilePathComponentsKt = getRootLength$FilesKt__FilePathComponentsKt(path2);
        if (path != null) {
            String substring = path.substring(0, rootLength$FilesKt__FilePathComponentsKt);
            Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
            return substring;
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    public static final boolean isRooted(@NotNull File file) {
        String path = file.getPath();
        Intrinsics.checkExpressionValueIsNotNull(path, "path");
        return getRootLength$FilesKt__FilePathComponentsKt(path) > 0;
    }

    @NotNull
    public static final File subPath(@NotNull File file, int i2, int i3) {
        return toComponents(file).subPath(i2, i3);
    }

    @NotNull
    public static final FilePathComponents toComponents(@NotNull File file) {
        List split$default;
        int collectionSizeOrDefault;
        List list;
        String path = file.getPath();
        Intrinsics.checkExpressionValueIsNotNull(path, "path");
        int rootLength$FilesKt__FilePathComponentsKt = getRootLength$FilesKt__FilePathComponentsKt(path);
        String substring = path.substring(0, rootLength$FilesKt__FilePathComponentsKt);
        Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
        String substring2 = path.substring(rootLength$FilesKt__FilePathComponentsKt);
        Intrinsics.checkExpressionValueIsNotNull(substring2, "(this as java.lang.String).substring(startIndex)");
        if (substring2.length() == 0) {
            list = CollectionsKt__CollectionsKt.emptyList();
        } else {
            split$default = StringsKt__StringsKt.split$default((CharSequence) substring2, new char[]{File.separatorChar}, false, 0, 6, (Object) null);
            collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(split$default, 10);
            ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
            Iterator it = split$default.iterator();
            while (it.hasNext()) {
                arrayList.add(new File((String) it.next()));
            }
            list = arrayList;
        }
        return new FilePathComponents(new File(substring), list);
    }
}
