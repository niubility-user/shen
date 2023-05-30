package tv.danmaku.ijk.media.utils;

import android.content.Context;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/* loaded from: classes11.dex */
public class PlayerFileUtils {
    static File findFile;

    /* loaded from: classes11.dex */
    public interface CopyAssetsCallback {
        void onCopyResult(boolean z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void a(String str, String str2, CopyAssetsCallback copyAssetsCallback, Context context) {
        byte[] bArr = new byte[4096];
        try {
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        if (new File(str + "/" + str2).exists()) {
            if (copyAssetsCallback != null) {
                copyAssetsCallback.onCopyResult(false);
                return;
            }
            return;
        }
        InputStream open = context.getAssets().open(str2);
        FileOutputStream fileOutputStream = new FileOutputStream(str + "/" + str2);
        for (int read = open.read(bArr); read > 0; read = open.read(bArr)) {
            fileOutputStream.write(bArr, 0, read);
        }
        fileOutputStream.close();
        open.close();
        if (copyAssetsCallback != null) {
            copyAssetsCallback.onCopyResult(true);
        }
    }

    public static void copyAssetsToStorage(final Context context, final String str, final String str2, final CopyAssetsCallback copyAssetsCallback) {
        new Thread(new Runnable() { // from class: tv.danmaku.ijk.media.utils.a
            @Override // java.lang.Runnable
            public final void run() {
                PlayerFileUtils.a(str, str2, copyAssetsCallback, context);
            }
        }).start();
    }

    public static void createFileDirs(String str) {
        String str2 = "";
        for (String str3 : str.split("\\/")) {
            str2 = str2 + "/" + str3;
            File file = new File(str2);
            if (!file.exists()) {
                file.mkdir();
            }
        }
    }

    public static void deleteFile(File file, boolean z) {
        if (file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                deleteFile(file2, z);
            }
            if (z) {
                file.delete();
            }
        } else if (file.exists()) {
            file.delete();
        }
    }

    public static void getDeclareDir(File file, String str) {
        File[] listFiles = file.listFiles();
        if (listFiles == null || findFile != null) {
            return;
        }
        for (File file2 : listFiles) {
            if (file2.isDirectory() && !file2.getName().startsWith("__")) {
                if (str.equals(file2.getName())) {
                    findFile = file2;
                    return;
                }
                getDeclareDir(file2, str);
            }
        }
    }

    public static boolean unzip(String str, String str2) {
        File file = new File(str2);
        if (!file.exists()) {
            file.mkdirs();
        }
        byte[] bArr = new byte[1024];
        try {
            FileInputStream fileInputStream = new FileInputStream(str);
            ZipInputStream zipInputStream = new ZipInputStream(fileInputStream);
            while (true) {
                ZipEntry nextEntry = zipInputStream.getNextEntry();
                if (nextEntry != null) {
                    File file2 = new File(str2, nextEntry.getName());
                    if (nextEntry.isDirectory()) {
                        file2.mkdirs();
                    } else {
                        file2.getParentFile().mkdirs();
                        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file2));
                        while (true) {
                            int read = zipInputStream.read(bArr);
                            if (read == -1) {
                                break;
                            }
                            bufferedOutputStream.write(bArr, 0, read);
                        }
                        bufferedOutputStream.close();
                        String str3 = "Unzipping to " + file2.getAbsolutePath();
                    }
                } else {
                    zipInputStream.closeEntry();
                    zipInputStream.close();
                    fileInputStream.close();
                    return true;
                }
            }
        } catch (IOException e2) {
            e2.printStackTrace();
            return false;
        }
    }
}
