package com.jd.framework.network.filedown;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import com.android.volley.VolleyLog;
import com.jd.framework.network.file.JDFileGuider;
import com.jd.framework.network.request.JDFileRequest;
import com.jingdong.common.utils.LangUtils;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/* loaded from: classes13.dex */
public class JDFileService {
    public static final String APP_DIR = "/jingdong";
    public static final int EXTERNAL = 2;
    public static final int EXTERNAL_PUBLIC = 3;
    public static final String FILE_DIR = "/file";
    public static final String FILE_DIR_MODE_FOR_INTERNAL = "771";
    public static final String FILE_MODE_WORLD_ACCESS = "755";
    public static final String FILE_MODE_WORLD_READABLE = "664";
    public static final String FILE_MODE_WORLD_WRITEABLE = "662";
    public static final int INTERNAL = 1;
    public static final String SYSTEM_OPERATOR = "/";
    private static final String TAG = "JDFileService";

    public static void chModFile(String str, File file) {
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            Runtime.getRuntime().exec("chmod " + str + LangUtils.SINGLE_SPACE + file);
            if (VolleyLog.DEBUG) {
                String str2 = "change mode file : " + file.getAbsolutePath() + " with mode : " + str;
            }
        } catch (Exception e2) {
            if (VolleyLog.DEBUG) {
                e2.printStackTrace();
                String str3 = " -->> chModFile mode:" + str + " file:" + file + " error:" + e2.getMessage();
            }
        }
    }

    public static boolean externalMemoryAvailable() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    public static long getAvailableSpaceSize(Context context, int i2) {
        if (context == null) {
            return -1L;
        }
        File file = null;
        try {
            if (i2 == 1) {
                file = Environment.getDataDirectory();
            } else if (i2 == 2) {
                file = context.getExternalFilesDir(null);
            } else if (i2 == 3) {
                file = Environment.getExternalStorageDirectory();
            }
            StatFs statFs = new StatFs(file.getPath());
            return statFs.getAvailableBlocks() * statFs.getBlockSize();
        } catch (Exception e2) {
            if (VolleyLog.DEBUG) {
                String str = "getAvailableSpaceSize() -->> " + e2;
                e2.printStackTrace();
            }
            return -1L;
        }
    }

    public static File getFilePath(int i2, Context context, String str, String str2, String str3, String str4) {
        File file;
        if (i2 == 2) {
            if (externalMemoryAvailable()) {
                File externalFilesDir = context.getExternalFilesDir(str3);
                if (externalFilesDir != null) {
                    if (externalFilesDir.exists()) {
                        if (!externalFilesDir.isDirectory()) {
                            throw new IllegalStateException(externalFilesDir.getAbsolutePath() + " already exists and is not a directory");
                        }
                    } else if (!externalFilesDir.mkdirs()) {
                        throw new IllegalStateException("Unable to create directory: " + externalFilesDir.getAbsolutePath());
                    }
                    StringBuilder sb = new StringBuilder();
                    sb.append("/jingdong/file/");
                    if (str == null) {
                        str = "";
                    }
                    sb.append(str);
                    file = new File(externalFilesDir, sb.toString());
                } else {
                    throw new IllegalStateException("Failed to get external storage files directory");
                }
            } else {
                throw new IllegalStateException("external storage is not available ");
            }
        } else if (i2 == 3) {
            if (externalMemoryAvailable()) {
                File externalStorageDirectory = Environment.getExternalStorageDirectory();
                StringBuffer stringBuffer = new StringBuffer();
                if (TextUtils.isEmpty(str)) {
                    str = "";
                }
                stringBuffer.append(str);
                if (!TextUtils.isEmpty(str2)) {
                    stringBuffer.append("/");
                    stringBuffer.append(str2);
                }
                file = new File(externalStorageDirectory, stringBuffer.toString());
            } else {
                throw new IllegalStateException("external storage is not available ");
            }
        } else {
            File filesDir = context.getFilesDir();
            if (str == null) {
                str = "";
            }
            file = new File(filesDir, str);
        }
        if (!file.exists()) {
            if (file.mkdirs()) {
                chModFile(FILE_DIR_MODE_FOR_INTERNAL, file);
            } else {
                throw new IllegalStateException("Unable to create directory: " + file.getAbsolutePath());
            }
        }
        return new File(file, str4);
    }

    public static File getFileSavePath(JDFileGuider jDFileGuider, Context context) {
        long availableSize = jDFileGuider.getAvailableSize();
        boolean isImmutable = jDFileGuider.isImmutable();
        if (VolleyLog.DEBUG) {
            String str = "availableSize : " + availableSize;
        }
        if (availableSize > 0) {
            if (jDFileGuider.getSpace() == 3) {
                long availableSpaceSize = getAvailableSpaceSize(context, 3);
                if (VolleyLog.DEBUG) {
                    String str2 = "external public \u5f53\u524d\u53ef\u7528\u7a7a\u95f4\u5927\u5c0f: " + availableSpaceSize;
                }
                if (availableSpaceSize > 0 && availableSpaceSize < availableSize) {
                    if (VolleyLog.DEBUG) {
                        String str3 = "external public not enough: " + availableSpaceSize;
                    }
                    return null;
                }
            } else if (1 == jDFileGuider.getSpace()) {
                long availableSpaceSize2 = getAvailableSpaceSize(context, 1);
                if (VolleyLog.DEBUG) {
                    String str4 = "Internal\u5185\u90e8\u5b58\u50a8\u5f53\u524d\u53ef\u7528\u7a7a\u95f4\u5927\u5c0f: " + availableSpaceSize2;
                }
                if (availableSpaceSize2 > 0 && availableSpaceSize2 < availableSize) {
                    if (VolleyLog.DEBUG) {
                        String str5 = "internal not enough: " + availableSpaceSize2;
                    }
                    if (isImmutable) {
                        boolean z = VolleyLog.DEBUG;
                        jDFileGuider.setSpace(2);
                        jDFileGuider.setImmutable(false);
                        return getFileSavePath(jDFileGuider, context);
                    }
                    return null;
                }
            } else if (2 == jDFileGuider.getSpace()) {
                long availableSpaceSize3 = getAvailableSpaceSize(context, 2);
                if (VolleyLog.DEBUG) {
                    String str6 = "External\u5916\u90e8\u5b58\u50a8\u5f53\u524d\u53ef\u7528\u7a7a\u95f4\u5927\u5c0f: " + availableSpaceSize3;
                }
                if (availableSpaceSize3 > 0 && availableSpaceSize3 < availableSize) {
                    if (VolleyLog.DEBUG) {
                        String str7 = "external not enough: " + availableSpaceSize3;
                    }
                    if (isImmutable) {
                        boolean z2 = VolleyLog.DEBUG;
                        jDFileGuider.setSpace(1);
                        jDFileGuider.setImmutable(false);
                        return getFileSavePath(jDFileGuider, context);
                    }
                    return null;
                }
            }
        }
        String childDirName = jDFileGuider.getChildDirName();
        String childSndDirName = jDFileGuider.getChildSndDirName();
        if (VolleyLog.DEBUG) {
            String str8 = "childDirName:" + childDirName;
        }
        try {
            File filePath = getFilePath(jDFileGuider.getSpace(), context, childDirName, childSndDirName, null, jDFileGuider.getFileName());
            if (VolleyLog.DEBUG) {
                String str9 = "\u4e0b\u8f7d\u6587\u4ef6\u5b58\u50a8\u8def\u5f84 save file:" + filePath.getAbsolutePath();
            }
            return filePath;
        } catch (Exception unused) {
            boolean z3 = VolleyLog.DEBUG;
            return null;
        }
    }

    public static BufferedOutputStream openFileOutput(JDFileGuider jDFileGuider, File file) throws FileNotFoundException {
        if (file != null && file.isDirectory()) {
            throw new IllegalArgumentException("can't open output stream for directory!");
        }
        try {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
            setFilePermissionsFromMode(jDFileGuider, file);
            return bufferedOutputStream;
        } catch (FileNotFoundException unused) {
            File parentFile = file.getParentFile();
            parentFile.mkdir();
            chModFile(FILE_DIR_MODE_FOR_INTERNAL, parentFile);
            BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(new FileOutputStream(file));
            setFilePermissionsFromMode(jDFileGuider, file);
            return bufferedOutputStream2;
        }
    }

    public static void resetSaveFileParam(JDFileRequest jDFileRequest, Context context, JDFileGuider jDFileGuider, boolean z, int i2) {
        if (!jDFileRequest.isBreakpointTransmission() || jDFileRequest.getStartPosBreakpointTransmission() == 0) {
            return;
        }
        try {
            File filePath = getFilePath(jDFileGuider.getSpace(), context, jDFileGuider.getChildDirName(), null, null, jDFileGuider.getFileName());
            if (VolleyLog.DEBUG) {
                String str = "resetSaveFileParam file : " + filePath.getAbsolutePath();
            }
            if (filePath.exists()) {
                jDFileGuider.setImmutable(false);
            } else if (jDFileGuider.isImmutable()) {
                jDFileGuider.setSpace(jDFileGuider.getSpace() == 2 ? 1 : 2);
                jDFileGuider.setImmutable(false);
                resetSaveFileParam(jDFileRequest, context, jDFileGuider, z, i2);
            } else {
                jDFileRequest.setStartPosBreakpointTransmission(0);
                jDFileGuider.setImmutable(z);
                jDFileGuider.setSpace(i2);
            }
        } catch (Exception unused) {
            boolean z2 = VolleyLog.DEBUG;
        }
    }

    public static void setFilePermissionsFromMode(JDFileGuider jDFileGuider, File file) {
        int mode = jDFileGuider.getMode();
        if (jDFileGuider.getSpace() == 1) {
            if (mode == 1) {
                chModFile(FILE_MODE_WORLD_READABLE, file);
            } else if (mode == 2) {
                chModFile(FILE_MODE_WORLD_WRITEABLE, file);
            } else {
                chModFile("755", file);
            }
        }
    }

    public static File getFilePath(int i2, Context context, String str, String str2, String str3) {
        return getFilePath(i2, context, str, null, str2, str3);
    }
}
