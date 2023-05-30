package kotlin.io;

import com.facebook.react.uimanager.events.TouchesHelper;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.common.unification.uniconfig.UnIconConfigController;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\r\u001a/\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0001\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00002\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0004\b\u0005\u0010\u0006\u001a/\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\u0001\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00002\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0004\b\u0007\u0010\u0006\u001a\u0019\u0010\t\u001a\u00020\u0000*\u00020\u00032\u0006\u0010\b\u001a\u00020\u0003\u00a2\u0006\u0004\b\t\u0010\n\u001a\u0019\u0010\u000b\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\b\u001a\u00020\u0003\u00a2\u0006\u0004\b\u000b\u0010\f\u001a\u0019\u0010\r\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\b\u001a\u00020\u0003\u00a2\u0006\u0004\b\r\u0010\f\u001a\u001b\u0010\u000e\u001a\u0004\u0018\u00010\u0003*\u00020\u00032\u0006\u0010\b\u001a\u00020\u0003\u00a2\u0006\u0004\b\u000e\u0010\f\u001a\u001d\u0010\u0010\u001a\u0004\u0018\u00010\u0000*\u00020\u00032\u0006\u0010\b\u001a\u00020\u0003H\u0002\u00a2\u0006\u0004\b\u000f\u0010\n\u001a-\u0010\u0016\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00032\b\b\u0002\u0010\u0013\u001a\u00020\u00122\b\b\u0002\u0010\u0015\u001a\u00020\u0014\u00a2\u0006\u0004\b\u0016\u0010\u0017\u001a?\u0010\u001c\u001a\u00020\u0012*\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00032\b\b\u0002\u0010\u0013\u001a\u00020\u00122\u001a\b\u0002\u0010\u001b\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u001a0\u0018\u00a2\u0006\u0004\b\u001c\u0010\u001d\u001a\u0011\u0010\u001e\u001a\u00020\u0012*\u00020\u0003\u00a2\u0006\u0004\b\u001e\u0010\u001f\u001a\u0019\u0010!\u001a\u00020\u0012*\u00020\u00032\u0006\u0010 \u001a\u00020\u0003\u00a2\u0006\u0004\b!\u0010\"\u001a\u0019\u0010!\u001a\u00020\u0012*\u00020\u00032\u0006\u0010 \u001a\u00020\u0000\u00a2\u0006\u0004\b!\u0010#\u001a\u0019\u0010$\u001a\u00020\u0012*\u00020\u00032\u0006\u0010 \u001a\u00020\u0003\u00a2\u0006\u0004\b$\u0010\"\u001a\u0019\u0010$\u001a\u00020\u0012*\u00020\u00032\u0006\u0010 \u001a\u00020\u0000\u00a2\u0006\u0004\b$\u0010#\u001a\u0011\u0010%\u001a\u00020\u0003*\u00020\u0003\u00a2\u0006\u0004\b%\u0010&\u001a\u0013\u0010%\u001a\u00020'*\u00020'H\u0002\u00a2\u0006\u0004\b(\u0010)\u001a\u001f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00030**\b\u0012\u0004\u0012\u00020\u00030*H\u0002\u00a2\u0006\u0004\b(\u0010+\u001a\u0019\u0010-\u001a\u00020\u0003*\u00020\u00032\u0006\u0010,\u001a\u00020\u0003\u00a2\u0006\u0004\b-\u0010\f\u001a\u0019\u0010-\u001a\u00020\u0003*\u00020\u00032\u0006\u0010,\u001a\u00020\u0000\u00a2\u0006\u0004\b-\u0010.\u001a\u0019\u0010/\u001a\u00020\u0003*\u00020\u00032\u0006\u0010,\u001a\u00020\u0003\u00a2\u0006\u0004\b/\u0010\f\u001a\u0019\u0010/\u001a\u00020\u0003*\u00020\u00032\u0006\u0010,\u001a\u00020\u0000\u00a2\u0006\u0004\b/\u0010.\"\u0017\u00102\u001a\u00020\u0000*\u00020\u00038F@\u0006\u00a2\u0006\u0006\u001a\u0004\b0\u00101\"\u0017\u00104\u001a\u00020\u0000*\u00020\u00038F@\u0006\u00a2\u0006\u0006\u001a\u0004\b3\u00101\"\u0017\u00106\u001a\u00020\u0000*\u00020\u00038F@\u0006\u00a2\u0006\u0006\u001a\u0004\b5\u00101\u00a8\u00067"}, d2 = {"", "prefix", "suffix", "Ljava/io/File;", "directory", "createTempDir", "(Ljava/lang/String;Ljava/lang/String;Ljava/io/File;)Ljava/io/File;", "createTempFile", UnIconConfigController.A_B_SWITCH_A, "toRelativeString", "(Ljava/io/File;Ljava/io/File;)Ljava/lang/String;", "relativeTo", "(Ljava/io/File;Ljava/io/File;)Ljava/io/File;", "relativeToOrSelf", "relativeToOrNull", "toRelativeStringOrNull$FilesKt__UtilsKt", "toRelativeStringOrNull", TouchesHelper.TARGET_KEY, "", "overwrite", "", "bufferSize", "copyTo", "(Ljava/io/File;Ljava/io/File;ZI)Ljava/io/File;", "Lkotlin/Function2;", "Ljava/io/IOException;", "Lkotlin/io/OnErrorAction;", "onError", "copyRecursively", "(Ljava/io/File;Ljava/io/File;ZLkotlin/jvm/functions/Function2;)Z", "deleteRecursively", "(Ljava/io/File;)Z", "other", "startsWith", "(Ljava/io/File;Ljava/io/File;)Z", "(Ljava/io/File;Ljava/lang/String;)Z", "endsWith", "normalize", "(Ljava/io/File;)Ljava/io/File;", "Lkotlin/io/FilePathComponents;", "normalize$FilesKt__UtilsKt", "(Lkotlin/io/FilePathComponents;)Lkotlin/io/FilePathComponents;", "", "(Ljava/util/List;)Ljava/util/List;", "relative", "resolve", "(Ljava/io/File;Ljava/lang/String;)Ljava/io/File;", "resolveSibling", "getExtension", "(Ljava/io/File;)Ljava/lang/String;", "extension", "getNameWithoutExtension", "nameWithoutExtension", "getInvariantSeparatorsPath", "invariantSeparatorsPath", "kotlin-stdlib"}, k = 5, mv = {1, 4, 0}, xs = "kotlin/io/FilesKt")
/* loaded from: classes11.dex */
class FilesKt__UtilsKt extends FilesKt__FileTreeWalkKt {
    /* JADX WARN: Removed duplicated region for block: B:194:0x00a3 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:198:0x0091 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final boolean copyRecursively(@NotNull File file, @NotNull File file2, boolean z, @NotNull final Function2<? super File, ? super IOException, ? extends OnErrorAction> function2) {
        boolean z2;
        if (!file.exists()) {
            return function2.invoke(file, new NoSuchFileException(file, null, "The source file doesn't exist.", 2, null)) != OnErrorAction.TERMINATE;
        }
        try {
            Iterator<File> it = FilesKt__FileTreeWalkKt.walkTopDown(file).onFail(new Function2<File, IOException, Unit>() { // from class: kotlin.io.FilesKt__UtilsKt$copyRecursively$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(File file3, IOException iOException) {
                    invoke2(file3, iOException);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke */
                public final void invoke2(@NotNull File file3, @NotNull IOException iOException) {
                    if (((OnErrorAction) function2.invoke(file3, iOException)) == OnErrorAction.TERMINATE) {
                        throw new TerminateException(file3);
                    }
                }
            }).iterator();
            while (it.hasNext()) {
                File next = it.next();
                if (!next.exists()) {
                    if (function2.invoke(next, new NoSuchFileException(next, null, "The source file doesn't exist.", 2, null)) == OnErrorAction.TERMINATE) {
                        return false;
                    }
                } else {
                    File file3 = new File(file2, toRelativeString(next, file));
                    if (file3.exists() && (!next.isDirectory() || !file3.isDirectory())) {
                        if (z) {
                            if (file3.isDirectory()) {
                                if (!deleteRecursively(file3)) {
                                }
                                z2 = false;
                            } else {
                                if (!file3.delete()) {
                                }
                                z2 = false;
                            }
                            if (!z2) {
                                if (function2.invoke(file3, new FileAlreadyExistsException(next, file3, "The destination file already exists.")) == OnErrorAction.TERMINATE) {
                                    return false;
                                }
                            }
                        }
                        z2 = true;
                        if (!z2) {
                        }
                    }
                    if (next.isDirectory()) {
                        file3.mkdirs();
                    } else if (copyTo$default(next, file3, z, 0, 4, null).length() != next.length() && function2.invoke(next, new IOException("Source file wasn't copied completely, length of destination file differs.")) == OnErrorAction.TERMINATE) {
                        return false;
                    }
                }
            }
            return true;
        } catch (TerminateException unused) {
            return false;
        }
    }

    public static /* synthetic */ boolean copyRecursively$default(File file, File file2, boolean z, Function2 function2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        if ((i2 & 4) != 0) {
            function2 = new Function2() { // from class: kotlin.io.FilesKt__UtilsKt$copyRecursively$1
                @Override // kotlin.jvm.functions.Function2
                @NotNull
                public final Void invoke(@NotNull File file3, @NotNull IOException iOException) {
                    throw iOException;
                }
            };
        }
        return copyRecursively(file, file2, z, function2);
    }

    @NotNull
    public static final File copyTo(@NotNull File file, @NotNull File file2, boolean z, int i2) {
        if (file.exists()) {
            if (file2.exists()) {
                if (z) {
                    if (!file2.delete()) {
                        throw new FileAlreadyExistsException(file, file2, "Tried to overwrite the destination, but failed to delete it.");
                    }
                } else {
                    throw new FileAlreadyExistsException(file, file2, "The destination file already exists.");
                }
            }
            if (file.isDirectory()) {
                if (!file2.mkdirs()) {
                    throw new FileSystemException(file, file2, "Failed to create target directory.");
                }
            } else {
                File parentFile = file2.getParentFile();
                if (parentFile != null) {
                    parentFile.mkdirs();
                }
                FileInputStream fileInputStream = new FileInputStream(file);
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(file2);
                    ByteStreamsKt.copyTo(fileInputStream, fileOutputStream, i2);
                    CloseableKt.closeFinally(fileOutputStream, null);
                    CloseableKt.closeFinally(fileInputStream, null);
                } finally {
                }
            }
            return file2;
        }
        throw new NoSuchFileException(file, null, "The source file doesn't exist.", 2, null);
    }

    public static /* synthetic */ File copyTo$default(File file, File file2, boolean z, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            z = false;
        }
        if ((i3 & 4) != 0) {
            i2 = 8192;
        }
        return copyTo(file, file2, z, i2);
    }

    @NotNull
    public static final File createTempDir(@NotNull String str, @Nullable String str2, @Nullable File file) {
        File dir = File.createTempFile(str, str2, file);
        dir.delete();
        if (dir.mkdir()) {
            Intrinsics.checkExpressionValueIsNotNull(dir, "dir");
            return dir;
        }
        throw new IOException("Unable to create temporary directory " + dir + OrderISVUtil.MONEY_DECIMAL_CHAR);
    }

    public static /* synthetic */ File createTempDir$default(String str, String str2, File file, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = "tmp";
        }
        if ((i2 & 2) != 0) {
            str2 = null;
        }
        if ((i2 & 4) != 0) {
            file = null;
        }
        return createTempDir(str, str2, file);
    }

    @NotNull
    public static final File createTempFile(@NotNull String str, @Nullable String str2, @Nullable File file) {
        File createTempFile = File.createTempFile(str, str2, file);
        Intrinsics.checkExpressionValueIsNotNull(createTempFile, "File.createTempFile(prefix, suffix, directory)");
        return createTempFile;
    }

    public static /* synthetic */ File createTempFile$default(String str, String str2, File file, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = "tmp";
        }
        if ((i2 & 2) != 0) {
            str2 = null;
        }
        if ((i2 & 4) != 0) {
            file = null;
        }
        return createTempFile(str, str2, file);
    }

    public static final boolean deleteRecursively(@NotNull File file) {
        while (true) {
            boolean z = true;
            for (File file2 : FilesKt__FileTreeWalkKt.walkBottomUp(file)) {
                if (file2.delete() || !file2.exists()) {
                    if (z) {
                        break;
                    }
                }
                z = false;
            }
            return z;
        }
    }

    public static final boolean endsWith(@NotNull File file, @NotNull File file2) {
        FilePathComponents components = FilesKt__FilePathComponentsKt.toComponents(file);
        FilePathComponents components2 = FilesKt__FilePathComponentsKt.toComponents(file2);
        if (components2.isRooted()) {
            return Intrinsics.areEqual(file, file2);
        }
        int size = components.getSize() - components2.getSize();
        if (size < 0) {
            return false;
        }
        return components.getSegments().subList(size, components.getSize()).equals(components2.getSegments());
    }

    @NotNull
    public static final String getExtension(@NotNull File file) {
        String substringAfterLast;
        String name = file.getName();
        Intrinsics.checkExpressionValueIsNotNull(name, "name");
        substringAfterLast = StringsKt__StringsKt.substringAfterLast(name, (char) OrderISVUtil.MONEY_DECIMAL_CHAR, "");
        return substringAfterLast;
    }

    @NotNull
    public static final String getInvariantSeparatorsPath(@NotNull File file) {
        String replace$default;
        if (File.separatorChar == '/') {
            String path = file.getPath();
            Intrinsics.checkExpressionValueIsNotNull(path, "path");
            return path;
        }
        String path2 = file.getPath();
        Intrinsics.checkExpressionValueIsNotNull(path2, "path");
        replace$default = StringsKt__StringsJVMKt.replace$default(path2, File.separatorChar, '/', false, 4, (Object) null);
        return replace$default;
    }

    @NotNull
    public static final String getNameWithoutExtension(@NotNull File file) {
        String substringBeforeLast$default;
        String name = file.getName();
        Intrinsics.checkExpressionValueIsNotNull(name, "name");
        substringBeforeLast$default = StringsKt__StringsKt.substringBeforeLast$default(name, OrderISVUtil.MONEY_DECIMAL, (String) null, 2, (Object) null);
        return substringBeforeLast$default;
    }

    @NotNull
    public static final File normalize(@NotNull File file) {
        String joinToString$default;
        FilePathComponents components = FilesKt__FilePathComponentsKt.toComponents(file);
        File root = components.getRoot();
        List<File> normalize$FilesKt__UtilsKt = normalize$FilesKt__UtilsKt(components.getSegments());
        String str = File.separator;
        Intrinsics.checkExpressionValueIsNotNull(str, "File.separator");
        joinToString$default = CollectionsKt___CollectionsKt.joinToString$default(normalize$FilesKt__UtilsKt, str, null, null, 0, null, null, 62, null);
        return resolve(root, joinToString$default);
    }

    private static final FilePathComponents normalize$FilesKt__UtilsKt(@NotNull FilePathComponents filePathComponents) {
        return new FilePathComponents(filePathComponents.getRoot(), normalize$FilesKt__UtilsKt(filePathComponents.getSegments()));
    }

    @NotNull
    public static final File relativeTo(@NotNull File file, @NotNull File file2) {
        return new File(toRelativeString(file, file2));
    }

    @Nullable
    public static final File relativeToOrNull(@NotNull File file, @NotNull File file2) {
        String relativeStringOrNull$FilesKt__UtilsKt = toRelativeStringOrNull$FilesKt__UtilsKt(file, file2);
        if (relativeStringOrNull$FilesKt__UtilsKt != null) {
            return new File(relativeStringOrNull$FilesKt__UtilsKt);
        }
        return null;
    }

    @NotNull
    public static final File relativeToOrSelf(@NotNull File file, @NotNull File file2) {
        String relativeStringOrNull$FilesKt__UtilsKt = toRelativeStringOrNull$FilesKt__UtilsKt(file, file2);
        return relativeStringOrNull$FilesKt__UtilsKt != null ? new File(relativeStringOrNull$FilesKt__UtilsKt) : file;
    }

    @NotNull
    public static final File resolve(@NotNull File file, @NotNull File file2) {
        boolean endsWith$default;
        if (FilesKt__FilePathComponentsKt.isRooted(file2)) {
            return file2;
        }
        String file3 = file.toString();
        Intrinsics.checkExpressionValueIsNotNull(file3, "this.toString()");
        if (!(file3.length() == 0)) {
            endsWith$default = StringsKt__StringsKt.endsWith$default((CharSequence) file3, File.separatorChar, false, 2, (Object) null);
            if (!endsWith$default) {
                return new File(file3 + File.separatorChar + file2);
            }
        }
        return new File(file3 + file2);
    }

    @NotNull
    public static final File resolveSibling(@NotNull File file, @NotNull File file2) {
        FilePathComponents components = FilesKt__FilePathComponentsKt.toComponents(file);
        return resolve(resolve(components.getRoot(), components.getSize() == 0 ? new File("..") : components.subPath(0, components.getSize() - 1)), file2);
    }

    public static final boolean startsWith(@NotNull File file, @NotNull File file2) {
        FilePathComponents components = FilesKt__FilePathComponentsKt.toComponents(file);
        FilePathComponents components2 = FilesKt__FilePathComponentsKt.toComponents(file2);
        if (!(!Intrinsics.areEqual(components.getRoot(), components2.getRoot())) == true && components.getSize() >= components2.getSize()) {
            return components.getSegments().subList(0, components2.getSize()).equals(components2.getSegments());
        }
        return false;
    }

    @NotNull
    public static final String toRelativeString(@NotNull File file, @NotNull File file2) {
        String relativeStringOrNull$FilesKt__UtilsKt = toRelativeStringOrNull$FilesKt__UtilsKt(file, file2);
        if (relativeStringOrNull$FilesKt__UtilsKt != null) {
            return relativeStringOrNull$FilesKt__UtilsKt;
        }
        throw new IllegalArgumentException("this and base files have different roots: " + file + " and " + file2 + OrderISVUtil.MONEY_DECIMAL_CHAR);
    }

    private static final String toRelativeStringOrNull$FilesKt__UtilsKt(@NotNull File file, File file2) {
        List drop;
        FilePathComponents normalize$FilesKt__UtilsKt = normalize$FilesKt__UtilsKt(FilesKt__FilePathComponentsKt.toComponents(file));
        FilePathComponents normalize$FilesKt__UtilsKt2 = normalize$FilesKt__UtilsKt(FilesKt__FilePathComponentsKt.toComponents(file2));
        if ((!Intrinsics.areEqual(normalize$FilesKt__UtilsKt.getRoot(), normalize$FilesKt__UtilsKt2.getRoot())) == true) {
            return null;
        }
        int size = normalize$FilesKt__UtilsKt2.getSize();
        int size2 = normalize$FilesKt__UtilsKt.getSize();
        int i2 = 0;
        int min = Math.min(size2, size);
        while (i2 < min && Intrinsics.areEqual(normalize$FilesKt__UtilsKt.getSegments().get(i2), normalize$FilesKt__UtilsKt2.getSegments().get(i2))) {
            i2++;
        }
        StringBuilder sb = new StringBuilder();
        int i3 = size - 1;
        if (i3 >= i2) {
            while (!Intrinsics.areEqual(normalize$FilesKt__UtilsKt2.getSegments().get(i3).getName(), "..")) {
                sb.append("..");
                if (i3 != i2) {
                    sb.append(File.separatorChar);
                }
                if (i3 != i2) {
                    i3--;
                }
            }
            return null;
        }
        if (i2 < size2) {
            if (i2 < size) {
                sb.append(File.separatorChar);
            }
            drop = CollectionsKt___CollectionsKt.drop(normalize$FilesKt__UtilsKt.getSegments(), i2);
            String str = File.separator;
            Intrinsics.checkExpressionValueIsNotNull(str, "File.separator");
            CollectionsKt___CollectionsKt.joinTo$default(drop, sb, str, null, null, 0, null, null, 124, null);
        }
        return sb.toString();
    }

    private static final List<File> normalize$FilesKt__UtilsKt(@NotNull List<? extends File> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (File file : list) {
            String name = file.getName();
            if (name != null) {
                int hashCode = name.hashCode();
                if (hashCode != 46) {
                    if (hashCode == 1472 && name.equals("..")) {
                        if (arrayList.isEmpty() || !(!Intrinsics.areEqual(((File) CollectionsKt.last((List) arrayList)).getName(), "..")) == true) {
                            arrayList.add(file);
                        } else {
                            arrayList.remove(arrayList.size() - 1);
                        }
                    }
                } else if (name.equals(OrderISVUtil.MONEY_DECIMAL)) {
                }
            }
            arrayList.add(file);
        }
        return arrayList;
    }

    @NotNull
    public static final File resolve(@NotNull File file, @NotNull String str) {
        return resolve(file, new File(str));
    }

    @NotNull
    public static final File resolveSibling(@NotNull File file, @NotNull String str) {
        return resolveSibling(file, new File(str));
    }

    public static final boolean startsWith(@NotNull File file, @NotNull String str) {
        return startsWith(file, new File(str));
    }

    public static final boolean endsWith(@NotNull File file, @NotNull String str) {
        return endsWith(file, new File(str));
    }
}
