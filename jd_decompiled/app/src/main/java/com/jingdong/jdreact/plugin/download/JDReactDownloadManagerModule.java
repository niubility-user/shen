package com.jingdong.jdreact.plugin.download;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String getDataColumn(android.content.Context r8, android.net.Uri r9, java.lang.String r10, java.lang.String[] r11) {
        /*
            java.lang.String r0 = "_data"
            r1 = 0
            if (r8 == 0) goto L4a
            if (r9 != 0) goto L8
            goto L4a
        L8:
            android.content.ContentResolver r2 = r8.getContentResolver()     // Catch: java.lang.Throwable -> L35 java.lang.Exception -> L37
            r8 = 1
            java.lang.String[] r4 = new java.lang.String[r8]     // Catch: java.lang.Throwable -> L35 java.lang.Exception -> L37
            r8 = 0
            r4[r8] = r0     // Catch: java.lang.Throwable -> L35 java.lang.Exception -> L37
            r7 = 0
            r3 = r9
            r5 = r10
            r6 = r11
            android.database.Cursor r8 = r2.query(r3, r4, r5, r6, r7)     // Catch: java.lang.Throwable -> L35 java.lang.Exception -> L37
            if (r8 == 0) goto L32
            boolean r9 = r8.moveToFirst()     // Catch: java.lang.Exception -> L30 java.lang.Throwable -> L42
            if (r9 == 0) goto L32
            int r9 = r8.getColumnIndexOrThrow(r0)     // Catch: java.lang.Exception -> L30 java.lang.Throwable -> L42
            java.lang.String r9 = r8.getString(r9)     // Catch: java.lang.Exception -> L30 java.lang.Throwable -> L42
            if (r8 == 0) goto L2f
            r8.close()
        L2f:
            return r9
        L30:
            r9 = move-exception
            goto L39
        L32:
            if (r8 == 0) goto L41
            goto L3e
        L35:
            r9 = move-exception
            goto L44
        L37:
            r9 = move-exception
            r8 = r1
        L39:
            r9.toString()     // Catch: java.lang.Throwable -> L42
            if (r8 == 0) goto L41
        L3e:
            r8.close()
        L41:
            return r1
        L42:
            r9 = move-exception
            r1 = r8
        L44:
            if (r1 == 0) goto L49
            r1.close()
        L49:
            throw r9
        L4a:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.jdreact.plugin.download.JDReactDownloadManagerModule.getDataColumn(android.content.Context, android.net.Uri, java.lang.String, java.lang.String[]):java.lang.String");
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0047  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String getSaveDirPath(java.lang.String r3) {
        /*
            r2 = this;
            android.app.Activity r0 = r2.getCurrentActivity()
            if (r0 == 0) goto L31
            boolean r1 = android.text.TextUtils.isEmpty(r3)
            if (r1 == 0) goto Le
            java.lang.String r3 = android.os.Environment.DIRECTORY_DOWNLOADS
        Le:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L2d
            r1.<init>()     // Catch: java.lang.Throwable -> L2d
            java.io.File r3 = r0.getExternalFilesDir(r3)     // Catch: java.lang.Throwable -> L2d
            java.lang.String r3 = r3.getAbsolutePath()     // Catch: java.lang.Throwable -> L2d
            r1.append(r3)     // Catch: java.lang.Throwable -> L2d
            java.lang.String r3 = java.io.File.separator     // Catch: java.lang.Throwable -> L2d
            r1.append(r3)     // Catch: java.lang.Throwable -> L2d
            java.lang.String r3 = "rn_download"
            r1.append(r3)     // Catch: java.lang.Throwable -> L2d
            java.lang.String r3 = r1.toString()     // Catch: java.lang.Throwable -> L2d
            goto L33
        L2d:
            r3 = move-exception
            r3.toString()
        L31:
            java.lang.String r3 = ""
        L33:
            java.io.File r0 = new java.io.File
            r0.<init>(r3)
            boolean r1 = r0.isDirectory()
            if (r1 != 0) goto L41
            r0.delete()
        L41:
            boolean r1 = r0.exists()
            if (r1 != 0) goto L4a
            r0.mkdirs()
        L4a:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.jdreact.plugin.download.JDReactDownloadManagerModule.getSaveDirPath(java.lang.String):java.lang.String");
    }

    /* JADX WARN: Removed duplicated region for block: B:53:0x006b A[Catch: IOException -> 0x0067, TRY_LEAVE, TryCatch #1 {IOException -> 0x0067, blocks: (B:49:0x0063, B:53:0x006b), top: B:60:0x0063 }] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0063 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean moveFile(java.io.File r6, java.io.File r7) {
        /*
            r5 = this;
            r0 = 0
            if (r6 == 0) goto L73
            if (r7 == 0) goto L73
            boolean r1 = r6.exists()
            if (r1 != 0) goto Ld
            goto L73
        Ld:
            r1 = 0
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L46 java.lang.Exception -> L49
            r2.<init>(r6)     // Catch: java.lang.Throwable -> L46 java.lang.Exception -> L49
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L3e java.lang.Exception -> L42
            r3.<init>(r7)     // Catch: java.lang.Throwable -> L3e java.lang.Exception -> L42
            r7 = 4096(0x1000, float:5.74E-42)
            byte[] r7 = new byte[r7]     // Catch: java.lang.Throwable -> L3a java.lang.Exception -> L3c
        L1c:
            int r1 = r2.read(r7)     // Catch: java.lang.Throwable -> L3a java.lang.Exception -> L3c
            r4 = -1
            if (r1 == r4) goto L27
            r3.write(r7, r0, r1)     // Catch: java.lang.Throwable -> L3a java.lang.Exception -> L3c
            goto L1c
        L27:
            r3.flush()     // Catch: java.lang.Throwable -> L3a java.lang.Exception -> L3c
            r6.delete()     // Catch: java.lang.Throwable -> L3a java.lang.Exception -> L3c
            r6 = 1
            r2.close()     // Catch: java.io.IOException -> L35
            r3.close()     // Catch: java.io.IOException -> L35
            goto L39
        L35:
            r7 = move-exception
            r7.printStackTrace()
        L39:
            return r6
        L3a:
            r6 = move-exception
            goto L40
        L3c:
            r6 = move-exception
            goto L44
        L3e:
            r6 = move-exception
            r3 = r1
        L40:
            r1 = r2
            goto L61
        L42:
            r6 = move-exception
            r3 = r1
        L44:
            r1 = r2
            goto L4b
        L46:
            r6 = move-exception
            r3 = r1
            goto L61
        L49:
            r6 = move-exception
            r3 = r1
        L4b:
            java.lang.String.valueOf(r6)     // Catch: java.lang.Throwable -> L60
            if (r1 == 0) goto L56
            r1.close()     // Catch: java.io.IOException -> L54
            goto L56
        L54:
            r6 = move-exception
            goto L5c
        L56:
            if (r3 == 0) goto L5f
            r3.close()     // Catch: java.io.IOException -> L54
            goto L5f
        L5c:
            r6.printStackTrace()
        L5f:
            return r0
        L60:
            r6 = move-exception
        L61:
            if (r1 == 0) goto L69
            r1.close()     // Catch: java.io.IOException -> L67
            goto L69
        L67:
            r7 = move-exception
            goto L6f
        L69:
            if (r3 == 0) goto L72
            r3.close()     // Catch: java.io.IOException -> L67
            goto L72
        L6f:
            r7.printStackTrace()
        L72:
            throw r6
        L73:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.jdreact.plugin.download.JDReactDownloadManagerModule.moveFile(java.io.File, java.io.File):boolean");
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
