package k.a.a.i;

import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.unionpay.tsmservice.mi.data.Constant;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.DosFileAttributeView;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.PosixFilePermission;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes11.dex */
public class c {
    private static void a(byte b, int i2, Set<PosixFilePermission> set, PosixFilePermission posixFilePermission) {
        if (b.a(b, i2)) {
            set.add(posixFilePermission);
        }
    }

    private static void b(Path path, byte[] bArr) {
        if (bArr[2] == 0 && bArr[3] == 0) {
            return;
        }
        try {
            HashSet hashSet = new HashSet();
            a(bArr[3], 0, hashSet, PosixFilePermission.OWNER_READ);
            a(bArr[2], 7, hashSet, PosixFilePermission.OWNER_WRITE);
            a(bArr[2], 6, hashSet, PosixFilePermission.OWNER_EXECUTE);
            a(bArr[2], 5, hashSet, PosixFilePermission.GROUP_READ);
            a(bArr[2], 4, hashSet, PosixFilePermission.GROUP_WRITE);
            a(bArr[2], 3, hashSet, PosixFilePermission.GROUP_EXECUTE);
            a(bArr[2], 2, hashSet, PosixFilePermission.OTHERS_READ);
            a(bArr[2], 1, hashSet, PosixFilePermission.OTHERS_WRITE);
            a(bArr[2], 0, hashSet, PosixFilePermission.OTHERS_EXECUTE);
            Files.setPosixFilePermissions(path, hashSet);
        } catch (IOException unused) {
        }
    }

    private static void c(Path path, byte[] bArr) {
        if (bArr[0] == 0) {
            return;
        }
        DosFileAttributeView dosFileAttributeView = (DosFileAttributeView) Files.getFileAttributeView(path, DosFileAttributeView.class, LinkOption.NOFOLLOW_LINKS);
        try {
            dosFileAttributeView.setReadOnly(b.a(bArr[0], 0));
            dosFileAttributeView.setHidden(b.a(bArr[0], 1));
            dosFileAttributeView.setSystem(b.a(bArr[0], 2));
            dosFileAttributeView.setArchive(b.a(bArr[0], 5));
        } catch (IOException unused) {
        }
    }

    public static File[] d(File file) {
        final String g2 = g(file.getName());
        File[] listFiles = file.getParentFile().listFiles(new FilenameFilter() { // from class: k.a.a.i.a
            @Override // java.io.FilenameFilter
            public final boolean accept(File file2, String str) {
                boolean startsWith;
                startsWith = str.startsWith(g2 + OrderISVUtil.MONEY_DECIMAL);
                return startsWith;
            }
        });
        if (listFiles == null) {
            return new File[0];
        }
        Arrays.sort(listFiles);
        return listFiles;
    }

    private static String e(int i2) {
        return i2 < 9 ? "00" : i2 < 99 ? "0" : "";
    }

    public static String f(File file) {
        String name = file.getName();
        return !name.contains(OrderISVUtil.MONEY_DECIMAL) ? "" : name.substring(name.lastIndexOf(OrderISVUtil.MONEY_DECIMAL) + 1);
    }

    public static String g(String str) {
        int lastIndexOf = str.lastIndexOf(OrderISVUtil.MONEY_DECIMAL);
        return lastIndexOf == -1 ? str : str.substring(0, lastIndexOf);
    }

    public static String h(int i2) {
        return OrderISVUtil.MONEY_DECIMAL + e(i2) + (i2 + 1);
    }

    public static boolean i() {
        return System.getProperty("os.name").toLowerCase().contains(Constant.KEY_MAC);
    }

    public static boolean j(File file) {
        return file.getName().endsWith(".zip.001");
    }

    public static boolean k() {
        return System.getProperty("os.name").toLowerCase().contains("nux");
    }

    public static boolean l() {
        return System.getProperty("os.name").toLowerCase().contains("win");
    }

    public static void n(Path path, byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return;
        }
        if (l()) {
            c(path, bArr);
        } else if (i() || k()) {
            b(path, bArr);
        }
    }

    public static void o(Path path, long j2) {
        if (j2 > 0 && Files.exists(path, new LinkOption[0])) {
            try {
                Files.setLastModifiedTime(path, FileTime.fromMillis(g.d(j2)));
            } catch (Exception unused) {
            }
        }
    }

    public static void p(File file, long j2) {
        file.setLastModified(g.d(j2));
    }
}
