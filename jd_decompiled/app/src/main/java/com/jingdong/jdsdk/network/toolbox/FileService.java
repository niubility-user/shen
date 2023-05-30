package com.jingdong.jdsdk.network.toolbox;

import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.jdsdk.network.JDHttpTookit;
import com.jingdong.sdk.oklog.OKLog;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

/* loaded from: classes14.dex */
public class FileService {
    public static final String CACHE_EXT_NAME_IMAGE = ".image";
    public static final String CACHE_EXT_NAME_JSON = ".json";
    private static final String CAMERA_CHILD_DIR = "/camera";
    public static final int CAMERA_DIR = 3;
    public static final String CRASH_FILE_NAME = "crash";
    private static final int ERROR = -1;
    private static final String FILE_CHILD_DIR = "/file";
    public static final int FILE_DIR = 6;
    public static final String FILE_MODE_WORLD_ACCESS = "755";
    public static final String FILE_MODE_WORLD_READABLE = "644";
    public static final String FILE_MODE_WORLD_WRITEABLE = "622";
    public static final int IMAGE_DIR = 1;
    public static final int INTERNAL_TYPE_CACHE = 2;
    public static final int INTERNAL_TYPE_FILE = 1;
    public static final int JSON_DIR = 2;
    public static final int JSON_DIR_SD = 5;
    private static final int MAX_IMAGE_SAVE_NUM = 4096;
    private static final String PERSIST_CHILD_DIR = "/persist";
    public static final int PERSIST_DIR = 4;
    private static final long PERSIST_FILE_SIZE = 8388608;
    private static final String SHARE_IMAGE_NAME = "shareimage.jpg";
    public static final String SYSTEM_OPERATOR = "/";
    private static final String TAG = "FileService";
    private static final long THRESHOLD_IMAGE_CLEAR_SIZE = 52428800;
    private static final long THRESHOLD_IMAGE_SIZE = 8388608;
    private static final long THRESHOLD_JSON_SIZE = 1048576;
    private static final int UN_KNOW = -20;
    private static final String applicationDir = "/jingdong";
    private static DeleteOverImageThread deleteOverImageThread = null;
    private static int phoneMemoryImageCacheNum = -20;
    private static int sdCardImageCacheNum = -20;
    private static final String JSON_CHILD_DIR = "/json";
    private static DirectoryController jsonDirCtrl = new DirectoryController(2, JSON_CHILD_DIR);
    private static DirectoryController jsonSDDirCtrl = new DirectoryController(5, JSON_CHILD_DIR);
    public static final String IMAGE_CHILD_DIR = "/image";
    private static DirectoryController imageDirCtrl = new DirectoryController(1, IMAGE_CHILD_DIR);

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes14.dex */
    public static class DeleteOverImageThread extends Thread {
        private File imageDir;

        public DeleteOverImageThread(File file) {
            this.imageDir = file;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            try {
                FileService.deleteOverImageWithDir(this.imageDir);
            } catch (Throwable th) {
                if (OKLog.D) {
                    th.printStackTrace();
                }
            }
            FileService.resetPhoneMemoryImageNum(-20);
            FileService.resetSdcardImageNum(-20);
            DeleteOverImageThread unused = FileService.deleteOverImageThread = null;
        }
    }

    /* loaded from: classes14.dex */
    public static class Directory {
        public static final int EXTERNAL = 2;
        public static final int EXTERNAL_PUBLIC = 3;
        public static final int INTERNAL = 1;
        private File dir;
        private String path;
        private int space;

        public Directory(String str, int i2) {
            this(new File(str), i2);
        }

        public void checkExists() {
            File file = this.dir;
            if (file == null || file.exists()) {
                return;
            }
            this.dir.mkdirs();
        }

        public File getDir() {
            return this.dir;
        }

        public String getPath() {
            File file;
            if (this.path == null && (file = this.dir) != null) {
                this.path = file.getAbsolutePath();
            }
            return this.path;
        }

        public int getSpace() {
            return this.space;
        }

        public void setDir(File file) {
            this.dir = file;
        }

        public void setPath(String str) {
            if (getPath() == null || !getPath().equals(str)) {
                this.dir = new File(str);
                this.path = str;
            }
        }

        public void setSpace(int i2) {
            this.space = i2;
        }

        public Directory(File file, int i2) {
            this.dir = file;
            this.space = i2;
        }
    }

    /* loaded from: classes14.dex */
    public static class DirectoryController {
        private String childDirName;
        private Directory dir;
        private int dirState;
        private int dirType;
        public boolean isNeedReSetupStorageState = true;

        public DirectoryController(int i2, String str) {
            this.dirType = i2;
            this.childDirName = str;
        }

        private Directory createDirectory() {
            int i2 = this.dirType;
            if (i2 == 2) {
                return FileService.getDirectoryByJsonSize(this.childDirName);
            }
            if (i2 == 1) {
                return FileService.getDirectoryByImageSize(this.childDirName);
            }
            if (i2 == 5) {
                return FileService.getDirectoryByJsonSizeSD(this.childDirName);
            }
            return null;
        }

        private Directory setupStorageState() {
            if (OKLog.D) {
                OKLog.d(FileService.TAG, this.dirType + "setupStorageState() -->> ");
            }
            Directory createDirectory = createDirectory();
            if (createDirectory == null) {
                if (OKLog.D) {
                    OKLog.d(FileService.TAG, this.dirType + "setupStorageState() no free size -->> ");
                }
                this.dirState = -1;
                return null;
            }
            if (OKLog.D) {
                OKLog.d(FileService.TAG, this.dirType + "setupStorageState() has free size -->> ");
            }
            this.dir = createDirectory;
            this.dirState = createDirectory.getSpace();
            this.isNeedReSetupStorageState = false;
            return this.dir;
        }

        public Directory getDirectory() {
            if (OKLog.D) {
                OKLog.d(FileService.TAG, this.dirType + "getDirectory() dirState -->> " + this.dirState);
                OKLog.d(FileService.TAG, this.dirType + "getDirectory() dir -->> " + this.dir);
            }
            if (this.isNeedReSetupStorageState) {
                return setupStorageState();
            }
            Directory directory = this.dir;
            if (directory != null) {
                directory.checkExists();
                return this.dir;
            }
            return null;
        }

        public void needReSetupStorageState() {
            this.isNeedReSetupStorageState = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes14.dex */
    public static class FileSorter implements Comparator<File> {
        private FileSorter() {
        }

        @Override // java.util.Comparator
        public int compare(File file, File file2) {
            if (file == null || file2 == null || file == file2) {
                return 0;
            }
            long lastModified = file.lastModified();
            long lastModified2 = file2.lastModified();
            if (lastModified == lastModified2) {
                return 0;
            }
            return lastModified > lastModified2 ? 1 : -1;
        }
    }

    public static void chModFile(String str, File file) {
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            Process exec = Runtime.getRuntime().exec("chmod " + str + LangUtils.SINGLE_SPACE + file);
            if (exec != null) {
                exec.destroy();
            }
        } catch (Exception e2) {
            if (OKLog.D) {
                e2.printStackTrace();
                OKLog.d(TAG, " -->> chModFile mode:" + str + " file:" + file + " error:" + e2.getMessage());
            }
        }
    }

    public static void clearAllCacheImages() {
        if (externalMemoryAvailable()) {
            deleteAllFilseForDirectory(new Directory(getExternalDirectory(IMAGE_CHILD_DIR), 2));
            resetSdcardImageNum(-20);
        }
        deleteAllFilseForDirectory(new Directory(getInternalDirectory(IMAGE_CHILD_DIR, false), 1));
        resetPhoneMemoryImageNum(-20);
    }

    public static void clearCacheFiles() {
        JDHttpTookit.getEngine().getAppProxy().clearCacheFiles();
        resetPhoneMemoryImageNum(-20);
        resetSdcardImageNum(-20);
    }

    public static void clearInternalCacheImages() {
        Directory directory = imageDirCtrl.getDirectory();
        if (directory == null || directory.getSpace() != 1 || getAvailableInternalMemorySize() >= THRESHOLD_IMAGE_CLEAR_SIZE) {
            return;
        }
        deleteAllFilseForDirectory(directory);
        resetPhoneMemoryImageNum(-20);
    }

    private static void deleteAllFilseForDirectory(Directory directory) {
        File dir;
        String[] list;
        if (directory == null || (dir = directory.getDir()) == null || !dir.exists() || (list = dir.list()) == null) {
            return;
        }
        for (String str : list) {
            File file = new File(dir, str);
            if (file.exists()) {
                file.delete();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void deleteOverImageWithDir(File file) {
        File[] listFiles;
        int length;
        if (file == null || !file.exists() || (listFiles = file.listFiles()) == null || (length = listFiles.length) < 1) {
            return;
        }
        Arrays.sort(listFiles, new FileSorter());
        int i2 = length - 2048;
        if (OKLog.D) {
            OKLog.d(TAG, "deleteOverImageWithDir needDeleteNum:" + i2);
        }
        if (i2 < 0) {
            return;
        }
        for (int i3 = 0; i3 < i2 && i3 <= length; i3++) {
            File file2 = listFiles[i3];
            if (file2 != null && file2.exists()) {
                if (OKLog.D) {
                    OKLog.d(TAG, "deleteOverImageWithDir temp:" + file2);
                }
                file2.delete();
            }
        }
    }

    public static boolean externalMemoryAvailable() {
        try {
            return Environment.getExternalStorageState().equals("mounted");
        } catch (Throwable unused) {
            return false;
        }
    }

    public static long getAvailableInternalMemorySize() {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        return statFs.getAvailableBlocks() * statFs.getBlockSize();
    }

    private static Directory getCameraDirectory() {
        return new Directory(getExternalDirectory(CAMERA_CHILD_DIR), 2);
    }

    public static Directory getDirectory(int i2) {
        switch (i2) {
            case 1:
                return imageDirCtrl.getDirectory();
            case 2:
                return jsonDirCtrl.getDirectory();
            case 3:
                return getCameraDirectory();
            case 4:
                return getPersistDirectory();
            case 5:
                return jsonSDDirCtrl.getDirectory();
            case 6:
                return getFileDirectory();
            default:
                return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Directory getDirectoryByImageSize(String str) {
        if (OKLog.D) {
            OKLog.d(TAG, "getDirectoryByImageSize() -->> ");
        }
        if (externalMemoryAvailable()) {
            if (OKLog.D) {
                OKLog.d(TAG, "getDirectoryByImageSize() -->> EXTERNAL");
            }
            return new Directory(getExternalDirectory(str), 2);
        } else if (getAvailableInternalMemorySize() > 8388608) {
            if (OKLog.D) {
                OKLog.d(TAG, "getDirectoryByImageSize() -->> INTERNAL");
            }
            return new Directory(getInternalDirectory(str, false), 1);
        } else {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Directory getDirectoryByJsonSize(String str) {
        if (OKLog.D) {
            OKLog.d(TAG, "getDirectoryByJsonSize() -->> ");
        }
        if (getAvailableInternalMemorySize() > THRESHOLD_JSON_SIZE) {
            if (OKLog.D) {
                OKLog.d(TAG, "getDirectoryByJsonSize() -->> INTERNAL");
            }
            return new Directory(getInternalDirectory(str, false), 1);
        }
        if (OKLog.D) {
            OKLog.d(TAG, "getDirectoryByJsonSize() -->> EXTERNAL");
        }
        return new Directory(getExternalDirectory(str), 2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Directory getDirectoryByJsonSizeSD(String str) {
        if (OKLog.D) {
            OKLog.d(TAG, "getDirectoryByJsonSizeSD() -->> ");
        }
        return new Directory(getExternalDirectory(str), 2);
    }

    private static Directory getDirectoryByPersistFileSize(String str) {
        if (getAvailableInternalMemorySize() > 8388608) {
            if (OKLog.D) {
                OKLog.d(TAG, "getDirectoryByJsonSize() -->> INTERNAL");
            }
            return new Directory(getInternalDirectory(str, false), 1);
        }
        return null;
    }

    public static File getExternalCacheDir() {
        File externalCacheDir = JDHttpTookit.getEngine().getApplicationContext().getExternalCacheDir();
        if (externalCacheDir != null) {
            if (externalCacheDir.exists() && externalCacheDir.isDirectory()) {
                return externalCacheDir;
            }
            externalCacheDir.mkdirs();
            return externalCacheDir;
        }
        return JDHttpTookit.getEngine().getApplicationContext().getFilesDir();
    }

    public static File getExternalDirectory(String str) {
        return getExternalFilesDir(str);
    }

    public static File getExternalFilesDir(String str) {
        File externalFilesDir = JDHttpTookit.getEngine().getApplicationContext().getExternalFilesDir(str);
        if (externalFilesDir != null) {
            if (externalFilesDir.exists() && externalFilesDir.isDirectory()) {
                return externalFilesDir;
            }
            externalFilesDir.mkdirs();
            return externalFilesDir;
        } else if (TextUtils.isEmpty(str)) {
            return JDHttpTookit.getEngine().getApplicationContext().getFilesDir();
        } else {
            File file = new File(JDHttpTookit.getEngine().getApplicationContext().getFilesDir() + File.separator + str);
            if (file.exists() && file.isDirectory()) {
                return file;
            }
            file.mkdirs();
            return file;
        }
    }

    public static String getFileAbsolutePath(FileGuider fileGuider) {
        return JDHttpTookit.getEngine().getApplicationContext().getFilesDir().getAbsolutePath() + "/" + fileGuider.getFileName();
    }

    private static Directory getFileDirectory() {
        if (OKLog.D) {
            OKLog.d(TAG, "getDirectoryByImageSize() -->> ");
        }
        if (externalMemoryAvailable()) {
            if (OKLog.D) {
                OKLog.d(TAG, "getDirectoryByImageSize() -->> EXTERNAL");
            }
            return new Directory(getExternalDirectory("/file"), 2);
        } else if (getAvailableInternalMemorySize() > 8388608) {
            if (OKLog.D) {
                OKLog.d(TAG, "getDirectoryByImageSize() -->> INTERNAL");
            }
            return new Directory(getInternalDirectory("/file", false), 1);
        } else {
            return null;
        }
    }

    public static File getInternalDirectory(String str, int i2, boolean z) {
        if (OKLog.D) {
            OKLog.d(TAG, "getInternalDirectory() -->> ");
        }
        File file = null;
        if (i2 == 1) {
            file = JDHttpTookit.getEngine().getApplicationContext().getFilesDir();
        } else if (i2 == 2) {
            file = JDHttpTookit.getEngine().getApplicationContext().getCacheDir();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("/jingdong");
        if (str == null) {
            str = "";
        }
        sb.append(str);
        File file2 = new File(file, sb.toString());
        if (!file2.exists()) {
            file2.mkdirs();
        }
        if (z) {
            chModFile("755", new File(file, "/jingdong"));
            chModFile("755", file2);
        }
        if (OKLog.D) {
            OKLog.d(TAG, "getInternalDirectory() dir.getAbsolutePath() -->> " + file2.getAbsolutePath());
        }
        if (OKLog.D) {
            OKLog.d(TAG, "getInternalDirectory() dir.exists() -->> " + file2.exists());
        }
        return file2;
    }

    private static Directory getPersistDirectory() {
        return getDirectoryByPersistFileSize(PERSIST_CHILD_DIR);
    }

    private static int getSubFilesNum(File file) {
        String[] list;
        if (file == null || !file.exists() || (list = file.list()) == null) {
            return -20;
        }
        return list.length;
    }

    public static long getTotalInternalMemorySize() {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        long blockCount = statFs.getBlockCount() * statFs.getBlockSize();
        if (OKLog.D) {
            OKLog.d(TAG, "getTotalInternalMemorySize() -->> " + blockCount);
        }
        return blockCount;
    }

    private static synchronized void incrementImageNum(Directory directory) {
        synchronized (FileService.class) {
            if (directory == null) {
                return;
            }
            File dir = directory.getDir();
            if (dir != null && dir.exists()) {
                int i2 = directory.space;
                if (i2 == 1) {
                    if (phoneMemoryImageCacheNum == -20) {
                        phoneMemoryImageCacheNum = getSubFilesNum(dir);
                    }
                    phoneMemoryImageCacheNum++;
                } else if (i2 == 2) {
                    if (sdCardImageCacheNum == -20) {
                        sdCardImageCacheNum = getSubFilesNum(dir);
                    }
                    sdCardImageCacheNum++;
                }
            }
        }
    }

    public static boolean isReady() {
        return externalMemoryAvailable();
    }

    public static void needReSetupStorageState() {
        jsonDirCtrl.needReSetupStorageState();
        imageDirCtrl.needReSetupStorageState();
        jsonSDDirCtrl.needReSetupStorageState();
    }

    public static FileOutputStream openFileOutput(FileGuider fileGuider) throws FileNotFoundException {
        long availableSize = fileGuider.getAvailableSize();
        boolean isImmutable = fileGuider.isImmutable();
        OKLog.i(TAG, "availableSize : " + availableSize);
        if (0 != availableSize) {
            if (1 == fileGuider.getSpace() && getAvailableInternalMemorySize() < availableSize) {
                OKLog.i(TAG, "internal not enough: " + getAvailableInternalMemorySize());
                if (isImmutable) {
                    OKLog.i(TAG, "internal not enough, try external");
                    fileGuider.setSpace(2);
                    fileGuider.setImmutable(false);
                    return openFileOutput(fileGuider);
                }
                return null;
            } else if (2 == fileGuider.getSpace()) {
                if (isImmutable) {
                    OKLog.i(TAG, "external not enough, try internal");
                    fileGuider.setSpace(1);
                    fileGuider.setImmutable(false);
                    return openFileOutput(fileGuider);
                }
                return null;
            }
        }
        if (JDHttpTookit.getEngine().getRuntimeConfigImpl().getBoolean("plug_on_off", true)) {
            String absolutePath = JDHttpTookit.getEngine().getApplicationContext().getFilesDir().getAbsolutePath();
            String childDirName = fileGuider.getChildDirName();
            if (!TextUtils.isEmpty(childDirName)) {
                absolutePath = absolutePath + "/" + childDirName;
            }
            File file = new File(absolutePath);
            if (!file.exists()) {
                file.mkdirs();
            }
            File file2 = new File(file, fileGuider.getFileName());
            int mode = fileGuider.getMode();
            if (mode == 1) {
                chModFile(FILE_MODE_WORLD_READABLE, file2);
            } else if (mode == 2) {
                chModFile(FILE_MODE_WORLD_WRITEABLE, file2);
            }
            return new FileOutputStream(file2);
        }
        return new FileOutputStream(getExternalCacheDir() + File.pathSeparator + fileGuider.getFileName());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static synchronized void resetPhoneMemoryImageNum(int i2) {
        synchronized (FileService.class) {
            phoneMemoryImageCacheNum = i2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static synchronized void resetSdcardImageNum(int i2) {
        synchronized (FileService.class) {
            sdCardImageCacheNum = i2;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x006a, code lost:
        if (r3 == null) goto L30;
     */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0072 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0079 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String saveShareImage(java.io.File r6) {
        /*
            com.jingdong.jdsdk.network.toolbox.FileGuider r0 = new com.jingdong.jdsdk.network.toolbox.FileGuider
            r0.<init>()
            r1 = 1
            r0.setSpace(r1)
            r0.setImmutable(r1)
            java.lang.String r2 = "shareimage.jpg"
            r0.setFileName(r2)
            r0.setMode(r1)
            r1 = 0
            java.io.FileOutputStream r2 = openFileOutput(r0)     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L3f
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L35 java.lang.Exception -> L39
            r3.<init>(r6)     // Catch: java.lang.Throwable -> L35 java.lang.Exception -> L39
            com.jingdong.jdsdk.network.toolbox.FileService$1 r6 = new com.jingdong.jdsdk.network.toolbox.FileService$1     // Catch: java.lang.Exception -> L33 java.lang.Throwable -> L6e
            r6.<init>()     // Catch: java.lang.Exception -> L33 java.lang.Throwable -> L6e
            com.jingdong.jdsdk.utils.b.a(r3, r2, r1, r6)     // Catch: java.lang.Exception -> L33 java.lang.Throwable -> L6e
            java.lang.String r1 = getFileAbsolutePath(r0)     // Catch: java.lang.Exception -> L33 java.lang.Throwable -> L6e
            if (r2 == 0) goto L2f
            r2.close()     // Catch: java.lang.Throwable -> L2f
        L2f:
            r3.close()     // Catch: java.lang.Throwable -> L6d
            goto L6d
        L33:
            r6 = move-exception
            goto L42
        L35:
            r6 = move-exception
            r3 = r1
        L37:
            r1 = r2
            goto L70
        L39:
            r6 = move-exception
            r3 = r1
            goto L42
        L3c:
            r6 = move-exception
            r3 = r1
            goto L70
        L3f:
            r6 = move-exception
            r2 = r1
            r3 = r2
        L42:
            boolean r0 = com.jingdong.sdk.oklog.OKLog.D     // Catch: java.lang.Throwable -> L6e
            if (r0 == 0) goto L63
            r6.printStackTrace()     // Catch: java.lang.Throwable -> L6e
            java.lang.String r0 = "FileService"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L6e
            r4.<init>()     // Catch: java.lang.Throwable -> L6e
            java.lang.String r5 = " saveShareImage-->> "
            r4.append(r5)     // Catch: java.lang.Throwable -> L6e
            java.lang.String r6 = r6.getMessage()     // Catch: java.lang.Throwable -> L6e
            r4.append(r6)     // Catch: java.lang.Throwable -> L6e
            java.lang.String r6 = r4.toString()     // Catch: java.lang.Throwable -> L6e
            com.jingdong.sdk.oklog.OKLog.d(r0, r6)     // Catch: java.lang.Throwable -> L6e
        L63:
            if (r2 == 0) goto L6a
            r2.close()     // Catch: java.lang.Throwable -> L69
            goto L6a
        L69:
        L6a:
            if (r3 == 0) goto L6d
            goto L2f
        L6d:
            return r1
        L6e:
            r6 = move-exception
            goto L37
        L70:
            if (r1 == 0) goto L77
            r1.close()     // Catch: java.lang.Throwable -> L76
            goto L77
        L76:
        L77:
            if (r3 == 0) goto L7c
            r3.close()     // Catch: java.lang.Throwable -> L7c
        L7c:
            goto L7e
        L7d:
            throw r6
        L7e:
            goto L7d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.jdsdk.network.toolbox.FileService.saveShareImage(java.io.File):java.lang.String");
    }

    public static boolean saveToSDCardWithType(Directory directory, String str, String str2, int i2) {
        if (TextUtils.isEmpty(str2)) {
            return false;
        }
        return saveToSDCardWithType(directory, str, str2.getBytes(), i2);
    }

    private static synchronized void tryCleanImageDir(Directory directory) {
        synchronized (FileService.class) {
            File dir = directory.getDir();
            int i2 = 0;
            int i3 = directory.space;
            if (i3 == 1) {
                if (phoneMemoryImageCacheNum == -20) {
                    phoneMemoryImageCacheNum = getSubFilesNum(dir);
                }
                i2 = phoneMemoryImageCacheNum;
            } else if (i3 == 2) {
                if (sdCardImageCacheNum == -20) {
                    sdCardImageCacheNum = getSubFilesNum(dir);
                }
                i2 = sdCardImageCacheNum;
            }
            if (OKLog.D) {
                OKLog.d(TAG, "tryCleanImageDir currentNum:" + i2);
            }
            if (i2 > 4096 && deleteOverImageThread == null) {
                DeleteOverImageThread deleteOverImageThread2 = new DeleteOverImageThread(dir);
                deleteOverImageThread = deleteOverImageThread2;
                deleteOverImageThread2.start();
            }
        }
    }

    public String read(String str) throws Exception {
        FileInputStream fileInputStream = null;
        try {
            try {
                FileInputStream fileInputStream2 = new FileInputStream(getExternalCacheDir() + File.pathSeparator + str);
                try {
                    String str2 = new String(readInputStream(fileInputStream2));
                    fileInputStream2.close();
                    return str2;
                } catch (FileNotFoundException e2) {
                    e = e2;
                    e.printStackTrace();
                    throw e;
                } catch (Exception e3) {
                    e = e3;
                    e.printStackTrace();
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    fileInputStream = fileInputStream2;
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    throw th;
                }
            } catch (FileNotFoundException e4) {
                e = e4;
            } catch (Exception e5) {
                e = e5;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public byte[] readAsByteArray(String str) throws Exception {
        return readInputStream(new FileInputStream(getExternalCacheDir() + File.pathSeparator + str));
    }

    public byte[] readInputStream(FileInputStream fileInputStream) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int read = fileInputStream.read(bArr);
            if (read != -1) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                fileInputStream.close();
                byteArrayOutputStream.close();
                return byteArrayOutputStream.toByteArray();
            }
        }
    }

    public void save(String str, String str2) throws Exception {
        FileOutputStream fileOutputStream = null;
        try {
            try {
                FileOutputStream fileOutputStream2 = new FileOutputStream(getExternalCacheDir() + File.pathSeparator + str);
                try {
                    fileOutputStream2.write(str2.getBytes());
                    fileOutputStream2.close();
                } catch (FileNotFoundException e2) {
                    e = e2;
                    e.printStackTrace();
                    throw e;
                } catch (IOException e3) {
                    e = e3;
                    e.printStackTrace();
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = fileOutputStream2;
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    throw th;
                }
            } catch (FileNotFoundException e4) {
                e = e4;
            } catch (IOException e5) {
                e = e5;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public void saveAppend(String str, String str2) throws Exception {
        FileOutputStream fileOutputStream = new FileOutputStream(getExternalCacheDir() + File.pathSeparator + str, true);
        fileOutputStream.write(str2.getBytes());
        fileOutputStream.close();
    }

    public void saveReadable(String str, String str2) throws Exception {
        FileOutputStream fileOutputStream = new FileOutputStream(getExternalCacheDir() + File.pathSeparator + str);
        fileOutputStream.write(str2.getBytes());
        fileOutputStream.close();
    }

    public void saveReadableWriteable(String str, String str2) throws Exception {
        FileOutputStream fileOutputStream = null;
        try {
            try {
                FileOutputStream fileOutputStream2 = new FileOutputStream(getExternalCacheDir() + File.pathSeparator + str);
                try {
                    fileOutputStream2.write(str2.getBytes());
                    fileOutputStream2.close();
                } catch (FileNotFoundException e2) {
                    e = e2;
                    e.printStackTrace();
                    throw e;
                } catch (IOException e3) {
                    e = e3;
                    e.printStackTrace();
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = fileOutputStream2;
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    throw th;
                }
            } catch (FileNotFoundException e4) {
                e = e4;
            } catch (IOException e5) {
                e = e5;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public void saveToSDCard(String str, String str2) throws Exception {
        saveToSDCard((Directory) null, str, str2);
    }

    public void saveWriteable(String str, String str2) throws Exception {
        FileOutputStream fileOutputStream = new FileOutputStream(getExternalCacheDir() + File.pathSeparator + str);
        fileOutputStream.write(str2.getBytes());
        fileOutputStream.close();
    }

    public static boolean saveToSDCard(Directory directory, String str, String str2) {
        return saveToSDCard(directory, str, str2, 0);
    }

    public static boolean saveToSDCard(Directory directory, String str, String str2, int i2) {
        if (str2 == null) {
            return false;
        }
        return saveToSDCard(directory, str, str2.getBytes(), i2);
    }

    public static boolean saveToSDCardWithType(Directory directory, String str, byte[] bArr, int i2) {
        if (bArr == null || bArr.length < 1 || TextUtils.isEmpty(str) || directory == null) {
            return false;
        }
        if (OKLog.D) {
            OKLog.d(TAG, "saveToSDCardWithType phoneMemoryImageCacheNum:" + phoneMemoryImageCacheNum);
            OKLog.d(TAG, "saveToSDCardWithType sdCardImageCacheNum:" + sdCardImageCacheNum);
        }
        if (i2 == 1) {
            tryCleanImageDir(directory);
        }
        boolean saveToSDCard = saveToSDCard(directory, str, bArr);
        if (OKLog.D) {
            OKLog.d(TAG, "saveToSDCardWithType result:" + saveToSDCard);
        }
        if (saveToSDCard && i2 - 1 == 0) {
            incrementImageNum(directory);
        }
        return saveToSDCard;
    }

    public static boolean saveToSDCard(Directory directory, String str, byte[] bArr) {
        if (bArr == null) {
            return false;
        }
        return saveToSDCard(directory, str, bArr, 0);
    }

    public static boolean saveToSDCard(Directory directory, String str, byte[] bArr, int i2) {
        FileOutputStream fileOutputStream;
        Throwable th;
        Exception e2;
        if (directory == null || TextUtils.isEmpty(str) || bArr == null) {
            return false;
        }
        File file = new File(directory.getDir(), str);
        if (i2 == 1) {
            chModFile(FILE_MODE_WORLD_READABLE, file);
        } else if (i2 == 2) {
            chModFile(FILE_MODE_WORLD_WRITEABLE, file);
        }
        try {
            fileOutputStream = new FileOutputStream(file);
        } catch (Exception e3) {
            fileOutputStream = null;
            e2 = e3;
        } catch (Throwable th2) {
            fileOutputStream = null;
            th = th2;
        }
        try {
            try {
                fileOutputStream.write(bArr);
                try {
                    fileOutputStream.close();
                } catch (IOException unused) {
                }
                return true;
            } catch (Exception e4) {
                e2 = e4;
                e2.printStackTrace();
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException unused2) {
                    }
                }
                return false;
            }
        } catch (Throwable th3) {
            th = th3;
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException unused3) {
                }
            }
            throw th;
        }
    }

    public static File getInternalDirectory(String str, boolean z) {
        return getInternalDirectory(str, 2, z);
    }

    public static String saveShareImage(byte[] bArr) {
        FileGuider fileGuider = new FileGuider();
        fileGuider.setSpace(1);
        fileGuider.setImmutable(true);
        fileGuider.setFileName(SHARE_IMAGE_NAME);
        fileGuider.setMode(1);
        try {
            FileOutputStream openFileOutput = openFileOutput(fileGuider);
            openFileOutput.write(bArr);
            openFileOutput.flush();
            openFileOutput.close();
            return getFileAbsolutePath(fileGuider);
        } catch (Exception e2) {
            if (OKLog.D) {
                e2.printStackTrace();
                OKLog.d(TAG, " saveShareImage-->> " + e2.getMessage());
            }
            return null;
        }
    }
}
