package com.jingdong.jdreact.plugin.download;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.jd.dynamic.base.interfaces.IExceptionHandler;
import com.jingdong.common.jdreactFramework.utils.CommonUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import jpbury.t;

/* loaded from: classes13.dex */
public class JDReactDownloadManagerModule extends ReactContextBaseJavaModule {
    private String dirPath;
    private String downloadType;
    private Map<Long, Callback> mCallbackMap;
    private BroadcastReceiver mDownloadStatusReceiver;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public class DownloadStatusReceiver extends BroadcastReceiver {
        private DownloadStatusReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("android.intent.action.DOWNLOAD_COMPLETE".equals(intent.getAction())) {
                long longExtra = intent.getLongExtra("extra_download_id", -1L);
                Callback callback = (Callback) JDReactDownloadManagerModule.this.mCallbackMap.remove(Long.valueOf(longExtra));
                if (callback == null || context == null) {
                    return;
                }
                Cursor cursor = null;
                try {
                    cursor = ((DownloadManager) context.getSystemService(IExceptionHandler.DynamicExceptionData.TYPE_DOWNLOAD)).query(new DownloadManager.Query().setFilterById(longExtra));
                } catch (Throwable th) {
                    try {
                        String.valueOf(th);
                        CommonUtil.invokeCallback(callback, "");
                        if (0 == 0) {
                            return;
                        }
                    } finally {
                        if (0 != 0) {
                            cursor.close();
                        }
                    }
                }
                if (cursor == null) {
                    if (cursor != null) {
                        return;
                    }
                    return;
                }
                if (cursor.moveToFirst()) {
                    int columnIndex = cursor.getColumnIndex("local_uri");
                    String string = columnIndex >= 0 ? cursor.getString(columnIndex) : "";
                    if (!TextUtils.isEmpty(string)) {
                        String uriToPath = JDReactDownloadManagerModule.uriToPath(context, Uri.parse(string));
                        if (!TextUtils.isEmpty(JDReactDownloadManagerModule.this.downloadType)) {
                            if (TextUtils.equals("video", JDReactDownloadManagerModule.this.downloadType)) {
                                SaveUtils.saveVideoToAlbum(context, uriToPath);
                            }
                        } else {
                            SaveUtils.saveImgFileToAlbum(context, uriToPath);
                        }
                        CommonUtil.invokeCallback(callback, uriToPath);
                    }
                }
                if (cursor == null) {
                    return;
                }
                cursor.close();
            }
        }
    }

    public JDReactDownloadManagerModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mCallbackMap = new ConcurrentHashMap();
        this.dirPath = "";
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0032, code lost:
        if (r8 != null) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x003c, code lost:
        if (r8 == null) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x003e, code lost:
        r8.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0041, code lost:
        return null;
     */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0046  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String getDataColumn(Context context, Uri uri, String str, String[] strArr) {
        Cursor cursor;
        Cursor cursor2 = null;
        if (context == null || uri == null) {
            return null;
        }
        try {
            cursor = context.getContentResolver().query(uri, new String[]{"_data"}, str, strArr, null);
            if (cursor != null) {
                try {
                    try {
                        if (cursor.moveToFirst()) {
                            String string = cursor.getString(cursor.getColumnIndexOrThrow("_data"));
                            if (cursor != null) {
                                cursor.close();
                            }
                            return string;
                        }
                    } catch (Throwable th) {
                        th = th;
                        cursor2 = cursor;
                        if (cursor2 != null) {
                            cursor2.close();
                        }
                        throw th;
                    }
                } catch (Exception e2) {
                    e = e2;
                    e.toString();
                }
            }
        } catch (Exception e3) {
            e = e3;
            cursor = null;
        } catch (Throwable th2) {
            th = th2;
            if (cursor2 != null) {
            }
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0047  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private String getSaveDirPath(String str) {
        String str2;
        File file;
        Activity currentActivity = getCurrentActivity();
        if (currentActivity != null) {
            if (TextUtils.isEmpty(str)) {
                str = Environment.DIRECTORY_DOWNLOADS;
            }
            try {
                str2 = currentActivity.getExternalFilesDir(str).getAbsolutePath() + File.separator + "rn_download";
            } catch (Throwable th) {
                th.toString();
            }
            file = new File(str2);
            if (!file.isDirectory()) {
                file.delete();
            }
            if (!file.exists()) {
                file.mkdirs();
            }
            return str2;
        }
        str2 = "";
        file = new File(str2);
        if (!file.isDirectory()) {
        }
        if (!file.exists()) {
        }
        return str2;
    }

    /* JADX WARN: Removed duplicated region for block: B:53:0x006b A[Catch: IOException -> 0x0067, TRY_LEAVE, TryCatch #1 {IOException -> 0x0067, blocks: (B:49:0x0063, B:53:0x006b), top: B:60:0x0063 }] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0063 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private boolean moveFile(File file, File file2) {
        FileOutputStream fileOutputStream;
        FileInputStream fileInputStream;
        if (file == null || file2 == null || !file.exists()) {
            return false;
        }
        FileInputStream fileInputStream2 = null;
        try {
            fileInputStream = new FileInputStream(file);
            try {
                fileOutputStream = new FileOutputStream(file2);
            } catch (Exception e2) {
                e = e2;
                fileOutputStream = null;
            } catch (Throwable th) {
                th = th;
                fileOutputStream = null;
            }
        } catch (Exception e3) {
            e = e3;
            fileOutputStream = null;
        } catch (Throwable th2) {
            th = th2;
            fileOutputStream = null;
        }
        try {
            byte[] bArr = new byte[4096];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                fileOutputStream.write(bArr, 0, read);
            }
            fileOutputStream.flush();
            file.delete();
            try {
                fileInputStream.close();
                fileOutputStream.close();
            } catch (IOException e4) {
                e4.printStackTrace();
            }
            return true;
        } catch (Exception e5) {
            e = e5;
            fileInputStream2 = fileInputStream;
            try {
                String.valueOf(e);
                if (fileInputStream2 != null) {
                    try {
                        fileInputStream2.close();
                    } catch (IOException e6) {
                        e6.printStackTrace();
                        return false;
                    }
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                return false;
            } catch (Throwable th3) {
                th = th3;
                if (fileInputStream2 != null) {
                    try {
                        fileInputStream2.close();
                    } catch (IOException e7) {
                        e7.printStackTrace();
                        throw th;
                    }
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            fileInputStream2 = fileInputStream;
            if (fileInputStream2 != null) {
            }
            if (fileOutputStream != null) {
            }
            throw th;
        }
    }

    private void performDownload(ReadableMap readableMap, Callback callback, Callback callback2) {
        if (readableMap != null && getReactApplicationContext() != null) {
            try {
                String string = readableMap.hasKey("url") ? readableMap.getString("url") : "";
                if (TextUtils.isEmpty(string)) {
                    CommonUtil.invokeCallback(callback2, "url is empty");
                    return;
                }
                registerReceiver();
                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(string));
                request.setVisibleInDownloadsUi(true);
                request.allowScanningByMediaScanner();
                if (Build.VERSION.SDK_INT >= 24) {
                    request.setRequiresDeviceIdle(false);
                    request.setRequiresCharging(false);
                }
                request.setNotificationVisibility(!readableMap.hasKey("notify") || readableMap.getBoolean("notify") ? 1 : 2);
                if (readableMap.hasKey("onlyWifi") && readableMap.getBoolean("onlyWifi")) {
                    request.setAllowedNetworkTypes(2);
                }
                String string2 = readableMap.hasKey("title") ? readableMap.getString("title") : "";
                if (!TextUtils.isEmpty(string2)) {
                    request.setTitle(string2);
                }
                String string3 = readableMap.hasKey("desc") ? readableMap.getString("desc") : "";
                if (!TextUtils.isEmpty(string3)) {
                    request.setDescription(string3);
                }
                String string4 = readableMap.hasKey("saveFileName") ? readableMap.getString("saveFileName") : "";
                this.dirPath = readableMap.hasKey("saveDir") ? readableMap.getString("saveDir") : "";
                if (!TextUtils.isEmpty(string4)) {
                    request.setDestinationUri(Uri.fromFile(new File(getSaveDirPath(this.dirPath), string4)));
                }
                this.downloadType = readableMap.hasKey("downloadType") ? readableMap.getString("downloadType") : "";
                this.mCallbackMap.put(Long.valueOf(((DownloadManager) getReactApplicationContext().getSystemService(IExceptionHandler.DynamicExceptionData.TYPE_DOWNLOAD)).enqueue(request)), callback);
                return;
            } catch (Throwable th) {
                String.valueOf(th);
                CommonUtil.invokeCallback(callback2, t.f20145j);
                return;
            }
        }
        CommonUtil.invokeCallback(callback2, new Object[0]);
    }

    private void registerReceiver() {
        if (getReactApplicationContext() == null || this.mDownloadStatusReceiver != null) {
            return;
        }
        this.mDownloadStatusReceiver = new DownloadStatusReceiver();
        getReactApplicationContext().registerReceiver(this.mDownloadStatusReceiver, new IntentFilter("android.intent.action.DOWNLOAD_COMPLETE"));
    }

    public static String uriToPath(Context context, Uri uri) {
        Uri uri2;
        if (context != null && uri != null) {
            if (Build.VERSION.SDK_INT < 19) {
                return getDataColumn(context, uri, null, null);
            }
            if (DocumentsContract.isDocumentUri(context, uri)) {
                try {
                    if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                        return getDataColumn(context, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.parseLong(DocumentsContract.getDocumentId(uri))), null, null);
                    } else if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                        String[] split = DocumentsContract.getDocumentId(uri).split(":");
                        String str = split[0];
                        String str2 = split[1];
                        if ("image".equals(str)) {
                            uri2 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                        } else if ("video".equals(str)) {
                            uri2 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                        } else {
                            uri2 = "audio".equals(str) ? MediaStore.Audio.Media.EXTERNAL_CONTENT_URI : null;
                        }
                        return getDataColumn(context, uri2, "_id=" + str2, null);
                    }
                } catch (Exception e2) {
                    e2.toString();
                }
            } else if ("content".equalsIgnoreCase(uri.getScheme())) {
                if ("com.google.android.apps.photos.content".equals(uri.getAuthority())) {
                    return uri.getLastPathSegment();
                }
                return getDataColumn(context, uri, null, null);
            } else if ("file".equalsIgnoreCase(uri.getScheme())) {
                return uri.getPath();
            }
        }
        return null;
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "JDReactDownloadManagerModule";
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule
    public void onCatalystInstanceDestroy() {
        super.onCatalystInstanceDestroy();
        this.mCallbackMap.clear();
        if (this.mDownloadStatusReceiver == null || getReactApplicationContext() == null) {
            return;
        }
        try {
            getReactApplicationContext().unregisterReceiver(this.mDownloadStatusReceiver);
        } catch (Exception e2) {
            String str = "JDReactDownloadManagerModule unregisterReceiver error:" + e2;
        }
    }

    @ReactMethod
    public void startDownload(ReadableMap readableMap, Callback callback, Callback callback2) {
        if (getCurrentActivity() == null) {
            CommonUtil.invokeCallback(callback2, new Object[0]);
        } else {
            performDownload(readableMap, callback, callback2);
        }
    }
}
