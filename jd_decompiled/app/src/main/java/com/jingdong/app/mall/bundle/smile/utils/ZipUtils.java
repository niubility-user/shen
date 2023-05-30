package com.jingdong.app.mall.bundle.smile.utils;

import android.text.TextUtils;
import android.util.Pair;
import com.jingdong.app.mall.bundle.updownload.utils.FileUtils;
import com.jingdong.jdsdk.network.toolbox.FileService;
import com.jingdong.sdk.oklog.OKLog;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/* loaded from: classes3.dex */
public class ZipUtils {
    private static final String TAG = "ZipUtils";

    /* JADX WARN: Removed duplicated region for block: B:73:0x00ec A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00e2 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean fromSmile(android.content.Context r6, java.lang.String r7, java.lang.String r8, java.lang.String r9, boolean r10) {
        /*
            Method dump skipped, instructions count: 247
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.app.mall.bundle.smile.utils.ZipUtils.fromSmile(android.content.Context, java.lang.String, java.lang.String, java.lang.String, boolean):boolean");
    }

    public static Pair<String, String> upZipSmile(String str, String str2) throws Exception {
        String str3 = TAG;
        OKLog.d(str3, "upZipSmiley>>>  zipFilePath: " + str + " fileString: " + str2);
        String str4 = null;
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(str));
            String str5 = null;
            while (true) {
                ZipEntry nextEntry = zipInputStream.getNextEntry();
                if (nextEntry != null) {
                    String name = nextEntry.getName();
                    if (!TextUtils.equals("../", name)) {
                        String str6 = str2 + File.separator + name;
                        if (nextEntry.isDirectory()) {
                            OKLog.d(TAG, "isDirectory filePath:" + str6);
                            FileUtils.newFile(str6).mkdirs();
                            str5 = str6;
                        } else {
                            OKLog.d(TAG, "is not DirectoryfilePath:" + str6);
                            File newFile = FileUtils.newFile(str6);
                            if (!newFile.getParentFile().exists()) {
                                newFile.getParentFile().mkdirs();
                            }
                            newFile.createNewFile();
                            FileOutputStream fileOutputStream = new FileOutputStream(newFile);
                            byte[] bArr = new byte[1024];
                            while (true) {
                                int read = zipInputStream.read(bArr);
                                if (read == -1) {
                                    break;
                                }
                                fileOutputStream.write(bArr, 0, read);
                                fileOutputStream.flush();
                            }
                            fileOutputStream.close();
                        }
                        if (!TextUtils.isEmpty(str6) && str6.endsWith(FileService.CACHE_EXT_NAME_JSON) && TextUtils.isEmpty(str4)) {
                            str4 = str6;
                        }
                    }
                } else {
                    zipInputStream.close();
                    return new Pair<>(str4, str5);
                }
            }
        } else {
            OKLog.d(str3, "upZipSmiley>>>  zipFilePath is null  or fileString is null");
            return null;
        }
    }
}
