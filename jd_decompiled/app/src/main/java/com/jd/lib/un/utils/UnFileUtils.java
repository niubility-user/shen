package com.jd.lib.un.utils;

import com.facebook.react.animated.InterpolationAnimatedNode;
import com.google.common.net.HttpHeaders;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* loaded from: classes16.dex */
public class UnFileUtils {
    private static final int BYTE_NUM_GB = 1073741824;
    private static final int BYTE_NUM_KB = 1024;
    private static final int BYTE_NUM_MB = 1048576;

    /* loaded from: classes16.dex */
    public interface OnReplaceListener {
        boolean onReplace();
    }

    private static String byte2FitMemorySize(long j2) {
        if (j2 < 0) {
            return "shouldn't be less than zero!";
        }
        if (j2 < 1024) {
            return String.format("%.3fB", Double.valueOf(j2));
        }
        if (j2 < 1048576) {
            double d = j2;
            Double.isNaN(d);
            return String.format("%.3fKB", Double.valueOf(d / 1024.0d));
        } else if (j2 < IjkMediaMeta.AV_CH_STEREO_RIGHT) {
            double d2 = j2;
            Double.isNaN(d2);
            return String.format("%.3fMB", Double.valueOf(d2 / 1048576.0d));
        } else {
            double d3 = j2;
            Double.isNaN(d3);
            return String.format("%.3fGB", Double.valueOf(d3 / 1.073741824E9d));
        }
    }

    public static boolean copyDir(String str, String str2) {
        return copyDir(getFileByPath(str), getFileByPath(str2));
    }

    public static boolean copyFile(String str, String str2) {
        return copyFile(getFileByPath(str), getFileByPath(str2));
    }

    private static boolean copyOrMoveDir(File file, File file2, boolean z) {
        return copyOrMoveDir(file, file2, new OnReplaceListener() { // from class: com.jd.lib.un.utils.UnFileUtils.1
            @Override // com.jd.lib.un.utils.UnFileUtils.OnReplaceListener
            public boolean onReplace() {
                return true;
            }
        }, z);
    }

    private static boolean copyOrMoveFile(File file, File file2, boolean z) {
        return copyOrMoveFile(file, file2, new OnReplaceListener() { // from class: com.jd.lib.un.utils.UnFileUtils.2
            @Override // com.jd.lib.un.utils.UnFileUtils.OnReplaceListener
            public boolean onReplace() {
                return true;
            }
        }, z);
    }

    public static boolean createFileByDeleteOldFile(String str) {
        return createFileByDeleteOldFile(getFileByPath(str));
    }

    public static boolean createOrExistsDir(String str) {
        return createOrExistsDir(getFileByPath(str));
    }

    public static boolean createOrExistsFile(String str) {
        return createOrExistsFile(getFileByPath(str));
    }

    public static boolean deleteAllInDir(String str) {
        return deleteAllInDir(getFileByPath(str));
    }

    public static boolean deleteDir(String str) {
        return deleteDir(getFileByPath(str));
    }

    public static boolean deleteFile(String str) {
        return deleteFile(getFileByPath(str));
    }

    public static boolean deleteFilesInDir(String str) {
        return deleteFilesInDir(getFileByPath(str));
    }

    public static boolean deleteFilesInDirWithFilter(File file, FileFilter fileFilter) {
        if (file == null) {
            return false;
        }
        if (file.exists()) {
            if (file.isDirectory()) {
                File[] listFiles = file.listFiles();
                if (listFiles != null && listFiles.length != 0) {
                    for (File file2 : listFiles) {
                        if (fileFilter.accept(file2)) {
                            if (file2.isFile()) {
                                if (!file2.delete()) {
                                    return false;
                                }
                            } else if (file2.isDirectory() && !deleteDir(file2)) {
                                return false;
                            }
                        }
                    }
                }
                return true;
            }
            return false;
        }
        return true;
    }

    public static long getDirLength(File file) {
        long length;
        if (isDir(file)) {
            long j2 = 0;
            File[] listFiles = file.listFiles();
            if (listFiles != null && listFiles.length != 0) {
                for (File file2 : listFiles) {
                    if (file2.isDirectory()) {
                        length = getDirLength(file2);
                    } else {
                        length = file2.length();
                    }
                    j2 += length;
                }
            }
            return j2;
        }
        return -1L;
    }

    public static String getDirSize(String str) {
        return getDirSize(getFileByPath(str));
    }

    public static File getFileByPath(String str) {
        if (UnStringUtils.isSpace(str)) {
            return null;
        }
        return new File(str);
    }

    public static String getFileExtension(File file) {
        return file == null ? "" : getFileExtension(file.getPath());
    }

    public static long getFileLastModified(String str) {
        return getFileLastModified(getFileByPath(str));
    }

    public static long getFileLength(String str) {
        if (str.matches(RegexConstants.REGEX_URL)) {
            try {
                URLConnection openConnection = new URL(str).openConnection();
                if (openConnection instanceof HttpURLConnection) {
                    HttpURLConnection httpURLConnection = (HttpURLConnection) openConnection;
                    httpURLConnection.setRequestProperty(HttpHeaders.ACCEPT_ENCODING, InterpolationAnimatedNode.EXTRAPOLATE_TYPE_IDENTITY);
                    httpURLConnection.connect();
                    if (httpURLConnection.getResponseCode() == 200) {
                        return httpURLConnection.getContentLength();
                    }
                }
                return -1L;
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        return getFileLength(getFileByPath(str));
    }

    public static String getFileName(File file) {
        return file == null ? "" : getFileName(file.getAbsolutePath());
    }

    public static String getFileNameNoExtension(File file) {
        return file == null ? "" : getFileNameNoExtension(file.getPath());
    }

    public static String getFileSize(String str) {
        long fileLength = getFileLength(str);
        return fileLength == -1 ? "" : byte2FitMemorySize(fileLength);
    }

    public static boolean isDir(String str) {
        return isDir(getFileByPath(str));
    }

    public static boolean isFile(String str) {
        return isFile(getFileByPath(str));
    }

    public static boolean isFileExists(String str) {
        return isFileExists(getFileByPath(str));
    }

    public static List<File> listFilesInDir(String str) {
        return listFilesInDir(str, false);
    }

    public static List<File> listFilesInDirWithFilter(File file, FileFilter fileFilter, boolean z) {
        if (isDir(file)) {
            ArrayList arrayList = new ArrayList();
            File[] listFiles = file.listFiles();
            if (listFiles != null && listFiles.length != 0) {
                for (File file2 : listFiles) {
                    if (fileFilter.accept(file2)) {
                        arrayList.add(file2);
                    }
                    if (z && file2.isDirectory()) {
                        arrayList.addAll(listFilesInDirWithFilter(file2, fileFilter, true));
                    }
                }
            }
            return arrayList;
        }
        return null;
    }

    public static boolean moveDir(String str, String str2) {
        return moveDir(getFileByPath(str), getFileByPath(str2));
    }

    public static boolean moveFile(String str, String str2) {
        return moveFile(getFileByPath(str), getFileByPath(str2));
    }

    public static boolean rename(String str, String str2) {
        return rename(getFileByPath(str), str2);
    }

    public static boolean copyDir(File file, File file2) {
        return copyOrMoveDir(file, file2, false);
    }

    public static boolean copyFile(File file, File file2) {
        return copyOrMoveFile(file, file2, false);
    }

    private static boolean copyOrMoveDir(File file, File file2, OnReplaceListener onReplaceListener, boolean z) {
        if (file == null || file2 == null) {
            return false;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(file.getPath());
        String str = File.separator;
        sb.append(str);
        String sb2 = sb.toString();
        String str2 = file2.getPath() + str;
        if (!str2.contains(sb2) && file.exists() && file.isDirectory()) {
            if (file2.exists()) {
                if (onReplaceListener != null && !onReplaceListener.onReplace()) {
                    return true;
                }
                if (!deleteAllInDir(file2)) {
                    return false;
                }
            }
            if (createOrExistsDir(file2)) {
                File[] listFiles = file.listFiles();
                if (listFiles != null && listFiles.length > 0) {
                    for (File file3 : listFiles) {
                        File file4 = new File(str2 + file3.getName());
                        if (file3.isFile()) {
                            if (!copyOrMoveFile(file3, file4, onReplaceListener, z)) {
                                return false;
                            }
                        } else if (file3.isDirectory() && !copyOrMoveDir(file3, file4, onReplaceListener, z)) {
                            return false;
                        }
                    }
                }
                return !z || deleteDir(file);
            }
            return false;
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x0051, code lost:
        if (deleteFile(r3) != false) goto L34;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static boolean copyOrMoveFile(java.io.File r3, java.io.File r4, com.jd.lib.un.utils.UnFileUtils.OnReplaceListener r5, boolean r6) {
        /*
            r0 = 0
            if (r3 == 0) goto L7f
            if (r4 != 0) goto L7
            goto L7f
        L7:
            boolean r1 = r3.equals(r4)
            if (r1 == 0) goto Le
            return r0
        Le:
            boolean r1 = r3.exists()
            if (r1 == 0) goto L7f
            boolean r1 = r3.isFile()
            if (r1 != 0) goto L1c
            goto L7f
        L1c:
            boolean r1 = r4.exists()
            r2 = 1
            if (r1 == 0) goto L34
            if (r5 == 0) goto L2d
            boolean r5 = r5.onReplace()
            if (r5 == 0) goto L2c
            goto L2d
        L2c:
            return r2
        L2d:
            boolean r5 = r4.delete()
            if (r5 != 0) goto L34
            return r0
        L34:
            java.io.File r5 = r4.getParentFile()
            boolean r5 = createOrExistsDir(r5)
            if (r5 != 0) goto L3f
            return r0
        L3f:
            r5 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L63 java.io.FileNotFoundException -> L65
            r1.<init>(r3)     // Catch: java.lang.Throwable -> L63 java.io.FileNotFoundException -> L65
            boolean r4 = com.jd.lib.un.utils.UnFileIOUtils.writeFileFromIS(r4, r1, r0)     // Catch: java.lang.Throwable -> L5d java.io.FileNotFoundException -> L60
            if (r4 == 0) goto L54
            if (r6 == 0) goto L53
            boolean r3 = deleteFile(r3)     // Catch: java.lang.Throwable -> L5d java.io.FileNotFoundException -> L60
            if (r3 == 0) goto L54
        L53:
            r0 = 1
        L54:
            r1.close()     // Catch: java.io.IOException -> L58
            goto L5c
        L58:
            r3 = move-exception
            r3.printStackTrace()
        L5c:
            return r0
        L5d:
            r3 = move-exception
            r5 = r1
            goto L74
        L60:
            r3 = move-exception
            r5 = r1
            goto L66
        L63:
            r3 = move-exception
            goto L74
        L65:
            r3 = move-exception
        L66:
            r3.printStackTrace()     // Catch: java.lang.Throwable -> L63
            if (r5 == 0) goto L73
            r5.close()     // Catch: java.io.IOException -> L6f
            goto L73
        L6f:
            r3 = move-exception
            r3.printStackTrace()
        L73:
            return r0
        L74:
            if (r5 == 0) goto L7e
            r5.close()     // Catch: java.io.IOException -> L7a
            goto L7e
        L7a:
            r4 = move-exception
            r4.printStackTrace()
        L7e:
            throw r3
        L7f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.lib.un.utils.UnFileUtils.copyOrMoveFile(java.io.File, java.io.File, com.jd.lib.un.utils.UnFileUtils$OnReplaceListener, boolean):boolean");
    }

    public static boolean createFileByDeleteOldFile(File file) {
        if (file == null) {
            return false;
        }
        if ((!file.exists() || file.delete()) && createOrExistsDir(file.getParentFile())) {
            try {
                return file.createNewFile();
            } catch (IOException e2) {
                e2.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public static boolean createOrExistsDir(File file) {
        return file != null && (!file.exists() ? !file.mkdirs() : !file.isDirectory());
    }

    public static boolean createOrExistsFile(File file) {
        if (file == null) {
            return false;
        }
        if (file.exists()) {
            return file.isFile();
        }
        if (createOrExistsDir(file.getParentFile())) {
            try {
                return file.createNewFile();
            } catch (IOException e2) {
                e2.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public static boolean deleteAllInDir(File file) {
        return deleteFilesInDirWithFilter(file, new FileFilter() { // from class: com.jd.lib.un.utils.UnFileUtils.4
            @Override // java.io.FileFilter
            public boolean accept(File file2) {
                return true;
            }
        });
    }

    public static boolean deleteDir(File file) {
        if (file == null) {
            return false;
        }
        if (file.exists()) {
            if (file.isDirectory()) {
                File[] listFiles = file.listFiles();
                if (listFiles != null && listFiles.length != 0) {
                    for (File file2 : listFiles) {
                        if (file2.isFile()) {
                            if (!file2.delete()) {
                                return false;
                            }
                        } else if (file2.isDirectory() && !deleteDir(file2)) {
                            return false;
                        }
                    }
                }
                return file.delete();
            }
            return false;
        }
        return true;
    }

    public static boolean deleteFile(File file) {
        return file != null && (!file.exists() || (file.isFile() && file.delete()));
    }

    public static boolean deleteFilesInDir(File file) {
        return deleteFilesInDirWithFilter(file, new FileFilter() { // from class: com.jd.lib.un.utils.UnFileUtils.5
            @Override // java.io.FileFilter
            public boolean accept(File file2) {
                return file2.isFile();
            }
        });
    }

    public static String getDirSize(File file) {
        long dirLength = getDirLength(file);
        return dirLength == -1 ? "" : byte2FitMemorySize(dirLength);
    }

    public static String getFileExtension(String str) {
        if (UnStringUtils.isSpace(str)) {
            return "";
        }
        int lastIndexOf = str.lastIndexOf(46);
        return (lastIndexOf == -1 || str.lastIndexOf(File.separator) >= lastIndexOf) ? "" : str.substring(lastIndexOf + 1);
    }

    public static long getFileLastModified(File file) {
        if (file == null) {
            return -1L;
        }
        return file.lastModified();
    }

    public static String getFileName(String str) {
        if (UnStringUtils.isSpace(str)) {
            return "";
        }
        int lastIndexOf = str.lastIndexOf(File.separator);
        return lastIndexOf == -1 ? str : str.substring(lastIndexOf + 1);
    }

    public static String getFileNameNoExtension(String str) {
        if (UnStringUtils.isSpace(str)) {
            return "";
        }
        int lastIndexOf = str.lastIndexOf(46);
        int lastIndexOf2 = str.lastIndexOf(File.separator);
        if (lastIndexOf2 == -1) {
            return lastIndexOf == -1 ? str : str.substring(0, lastIndexOf);
        } else if (lastIndexOf != -1 && lastIndexOf2 <= lastIndexOf) {
            return str.substring(lastIndexOf2 + 1, lastIndexOf);
        } else {
            return str.substring(lastIndexOf2 + 1);
        }
    }

    public static boolean isDir(File file) {
        return file != null && file.exists() && file.isDirectory();
    }

    public static boolean isFile(File file) {
        return file != null && file.exists() && file.isFile();
    }

    public static boolean isFileExists(File file) {
        return file != null && file.exists();
    }

    public static List<File> listFilesInDir(String str, boolean z) {
        return listFilesInDir(getFileByPath(str), z);
    }

    public static boolean moveDir(File file, File file2) {
        return copyOrMoveDir(file, file2, true);
    }

    public static boolean moveFile(File file, File file2) {
        return copyOrMoveFile(file, file2, true);
    }

    public static boolean rename(File file, String str) {
        if (file == null || !file.exists() || UnStringUtils.isSpace(str)) {
            return false;
        }
        if (str.equals(file.getName())) {
            return true;
        }
        File file2 = new File(file.getParent() + File.separator + str);
        return !file2.exists() && file.renameTo(file2);
    }

    public static boolean copyDir(String str, String str2, OnReplaceListener onReplaceListener) {
        return copyDir(getFileByPath(str), getFileByPath(str2), onReplaceListener);
    }

    public static boolean copyFile(String str, String str2, OnReplaceListener onReplaceListener) {
        return copyFile(getFileByPath(str), getFileByPath(str2), onReplaceListener);
    }

    public static List<File> listFilesInDir(File file, boolean z) {
        return listFilesInDirWithFilter(file, new FileFilter() { // from class: com.jd.lib.un.utils.UnFileUtils.3
            @Override // java.io.FileFilter
            public boolean accept(File file2) {
                return true;
            }
        }, z);
    }

    public static boolean moveDir(String str, String str2, OnReplaceListener onReplaceListener) {
        return moveDir(getFileByPath(str), getFileByPath(str2), onReplaceListener);
    }

    public static boolean moveFile(String str, String str2, OnReplaceListener onReplaceListener) {
        return moveFile(getFileByPath(str), getFileByPath(str2), onReplaceListener);
    }

    public static boolean copyDir(File file, File file2, OnReplaceListener onReplaceListener) {
        return copyOrMoveDir(file, file2, onReplaceListener, false);
    }

    public static boolean copyFile(File file, File file2, OnReplaceListener onReplaceListener) {
        return copyOrMoveFile(file, file2, onReplaceListener, false);
    }

    public static boolean moveDir(File file, File file2, OnReplaceListener onReplaceListener) {
        return copyOrMoveDir(file, file2, onReplaceListener, true);
    }

    public static boolean moveFile(File file, File file2, OnReplaceListener onReplaceListener) {
        return copyOrMoveFile(file, file2, onReplaceListener, true);
    }

    public static List<File> listFilesInDirWithFilter(File file, FileFilter fileFilter) {
        return listFilesInDirWithFilter(file, fileFilter, false);
    }

    public static long getFileLength(File file) {
        if (isFile(file)) {
            return file.length();
        }
        return -1L;
    }
}
