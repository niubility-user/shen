package com.jingdong.common.utils;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.widget.Toast;
import androidx.documentfile.provider.DocumentFile;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.R;
import com.jingdong.common.permission.PermissionHelper;
import com.jingdong.jdsdk.utils.DPIUtil;
import com.jingdong.sdk.oklog.OKLog;
import com.tencent.smtt.sdk.CookieManager;
import com.tencent.smtt.sdk.CookieSyncManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;

/* loaded from: classes6.dex */
public class JdWebViewFunctionUtil {
    static final long LIMIT_IMAGE_SIZE = 524288;
    private static final String TAG = "JdWebViewFunctionUtil";
    public static final boolean isKitKat;
    private static Uri mUri;

    static {
        isKitKat = Build.VERSION.SDK_INT >= 19;
    }

    /* JADX WARN: Not initialized variable reg: 3, insn: 0x00c2: IF  (r3 I:??[int, boolean, OBJECT, ARRAY, byte, short, char]) == (0 ??[int, boolean, OBJECT, ARRAY, byte, short, char])  -> B:55:0x00ce, block:B:50:0x00c2 */
    public static Bitmap getBitmapFromPath(String str, boolean z) {
        FileInputStream fileInputStream;
        InputStream inputStream;
        Bitmap decodeStream;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = getFileSampleSize(str);
        int readPictureDegree = ExifUtil.readPictureDegree(str);
        try {
            try {
                fileInputStream = new FileInputStream(str);
                try {
                    if (z) {
                        decodeStream = Bitmap.createScaledBitmap(BitmapFactory.decodeStream(fileInputStream, null, options), DPIUtil.dip2px(100.0f), DPIUtil.dip2px(100.0f), false);
                    } else {
                        decodeStream = BitmapFactory.decodeStream(fileInputStream, null, options);
                    }
                    if (readPictureDegree != 0) {
                        Matrix matrix = new Matrix();
                        matrix.setRotate(readPictureDegree);
                        decodeStream = Bitmap.createBitmap(decodeStream, 0, 0, decodeStream.getWidth(), decodeStream.getHeight(), matrix, true);
                    }
                    try {
                        fileInputStream.close();
                    } catch (IOException e2) {
                        OKLog.e(TAG, e2);
                    }
                    return decodeStream;
                } catch (Exception e3) {
                    e = e3;
                    OKLog.e(TAG, e);
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e4) {
                            OKLog.e(TAG, e4);
                        }
                    }
                    return null;
                } catch (Throwable th) {
                    th = th;
                    OKLog.e(TAG, th);
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e5) {
                            OKLog.e(TAG, e5);
                        }
                    }
                    return null;
                }
            } catch (Exception e6) {
                e = e6;
                fileInputStream = null;
            } catch (Throwable th2) {
                th = th2;
                fileInputStream = null;
            }
        } catch (Throwable th3) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e7) {
                    OKLog.e(TAG, e7);
                }
            }
            throw th3;
        }
    }

    public static Uri getCaptureImageUri() {
        return mUri;
    }

    /* JADX WARN: Code restructure failed: missing block: B:36:0x007b, code lost:
        if (r9 != null) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x007d, code lost:
        r9.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x008a, code lost:
        if (r9 == null) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x008d, code lost:
        return null;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0092  */
    /* JADX WARN: Type inference failed for: r8v0 */
    /* JADX WARN: Type inference failed for: r8v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r8v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String getDataColumn(android.content.Context r9, android.net.Uri r10, java.lang.String r11, java.lang.String[] r12) {
        /*
            r0 = 1
            java.lang.String[] r3 = new java.lang.String[r0]
            r0 = 0
            java.lang.String r7 = "_data"
            r3[r0] = r7
            r8 = 0
            android.content.ContentResolver r1 = r9.getContentResolver()     // Catch: java.lang.Throwable -> L81 java.lang.Exception -> L83
            r6 = 0
            r2 = r10
            r4 = r11
            r5 = r12
            android.database.Cursor r9 = r1.query(r2, r3, r4, r5, r6)     // Catch: java.lang.Throwable -> L81 java.lang.Exception -> L83
            if (r9 == 0) goto L7b
            boolean r11 = r9.moveToFirst()     // Catch: java.lang.Exception -> L79 java.lang.Throwable -> L8e
            if (r11 == 0) goto L7b
            int r11 = r9.getColumnIndex(r7)     // Catch: java.lang.Exception -> L79 java.lang.Throwable -> L8e
            r12 = -1
            if (r11 != r12) goto L6b
            java.lang.String r11 = r10.getPath()     // Catch: java.lang.Exception -> L79 java.lang.Throwable -> L8e
            java.lang.String r1 = "/"
            java.lang.String[] r11 = r11.split(r1)     // Catch: java.lang.Exception -> L79 java.lang.Throwable -> L8e
        L2e:
            int r1 = r11.length     // Catch: java.lang.Exception -> L79 java.lang.Throwable -> L8e
            if (r0 >= r1) goto L3f
            r1 = r11[r0]     // Catch: java.lang.Exception -> L79 java.lang.Throwable -> L8e
            java.lang.String r2 = "storage"
            boolean r1 = r1.equals(r2)     // Catch: java.lang.Exception -> L79 java.lang.Throwable -> L8e
            if (r1 == 0) goto L3c
            goto L40
        L3c:
            int r0 = r0 + 1
            goto L2e
        L3f:
            r0 = -1
        L40:
            if (r0 == r12) goto L61
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L79 java.lang.Throwable -> L8e
            r10.<init>()     // Catch: java.lang.Exception -> L79 java.lang.Throwable -> L8e
        L47:
            int r12 = r11.length     // Catch: java.lang.Exception -> L79 java.lang.Throwable -> L8e
            if (r0 >= r12) goto L57
            r12 = 47
            r10.append(r12)     // Catch: java.lang.Exception -> L79 java.lang.Throwable -> L8e
            r12 = r11[r0]     // Catch: java.lang.Exception -> L79 java.lang.Throwable -> L8e
            r10.append(r12)     // Catch: java.lang.Exception -> L79 java.lang.Throwable -> L8e
            int r0 = r0 + 1
            goto L47
        L57:
            java.lang.String r10 = r10.toString()     // Catch: java.lang.Exception -> L79 java.lang.Throwable -> L8e
            if (r9 == 0) goto L60
            r9.close()
        L60:
            return r10
        L61:
            java.lang.String r10 = r10.getPath()     // Catch: java.lang.Exception -> L79 java.lang.Throwable -> L8e
            if (r9 == 0) goto L6a
            r9.close()
        L6a:
            return r10
        L6b:
            int r10 = r9.getColumnIndexOrThrow(r7)     // Catch: java.lang.Exception -> L79 java.lang.Throwable -> L8e
            java.lang.String r10 = r9.getString(r10)     // Catch: java.lang.Exception -> L79 java.lang.Throwable -> L8e
            if (r9 == 0) goto L78
            r9.close()
        L78:
            return r10
        L79:
            r10 = move-exception
            goto L85
        L7b:
            if (r9 == 0) goto L8d
        L7d:
            r9.close()
            goto L8d
        L81:
            r10 = move-exception
            goto L90
        L83:
            r10 = move-exception
            r9 = r8
        L85:
            java.lang.String r11 = com.jingdong.common.utils.JdWebViewFunctionUtil.TAG     // Catch: java.lang.Throwable -> L8e
            com.jingdong.sdk.oklog.OKLog.e(r11, r10)     // Catch: java.lang.Throwable -> L8e
            if (r9 == 0) goto L8d
            goto L7d
        L8d:
            return r8
        L8e:
            r10 = move-exception
            r8 = r9
        L90:
            if (r8 == 0) goto L95
            r8.close()
        L95:
            goto L97
        L96:
            throw r10
        L97:
            goto L96
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.utils.JdWebViewFunctionUtil.getDataColumn(android.content.Context, android.net.Uri, java.lang.String, java.lang.String[]):java.lang.String");
    }

    public static int getExifOrientation(String str) {
        ExifInterface exifInterface;
        try {
            exifInterface = new ExifInterface(str);
        } catch (IOException unused) {
            exifInterface = null;
        }
        if (exifInterface != null) {
            return exifInterface.getAttributeInt("Orientation", -1);
        }
        return 1;
    }

    private static int getFileSampleSize(String str) {
        if (TextUtils.isEmpty(str)) {
            return 4;
        }
        File file = new File(str);
        if (file.isFile() && file.exists()) {
            return (int) (file.length() / LIMIT_IMAGE_SIZE);
        }
        return 4;
    }

    @Deprecated
    public static String getPath(Context context, Uri uri) {
        Uri uri2 = null;
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            if (isExternalStorageDocument(uri)) {
                String[] split = DocumentsContract.getDocumentId(uri).split(":");
                if ("primary".equalsIgnoreCase(split[0])) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            } else if (isDownloadsDocument(uri)) {
                return getDataColumn(context, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(DocumentsContract.getDocumentId(uri)).longValue()), null, null);
            } else {
                if (isMediaDocument(uri)) {
                    String[] split2 = DocumentsContract.getDocumentId(uri).split(":");
                    String str = split2[0];
                    if ("image".equals(str)) {
                        uri2 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                    } else if ("video".equals(str)) {
                        uri2 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                    } else if ("audio".equals(str)) {
                        uri2 = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                    }
                    return getDataColumn(context, uri2, "_id=?", new String[]{split2[1]});
                }
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            if (isGooglePhotosUri(uri)) {
                return uri.getLastPathSegment();
            }
            return getDataColumn(context, uri, null, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }

    @Deprecated
    public static String getPathFromUri(Context context, Uri uri) {
        String str = null;
        if (uri == null) {
            return null;
        }
        try {
            context.grantUriPermission(context.getPackageName(), uri, 1);
            str = getPath(context, uri);
            context.revokeUriPermission(uri, 1);
            return str;
        } catch (Exception e2) {
            OKLog.e(TAG, e2);
            return str;
        }
    }

    public static Uri getUriFromFilePath(File file) {
        return DocumentFile.fromFile(file).getUri();
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static void setWebViewCookie(BaseActivity baseActivity, String str, String str2) {
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.setCookie(str, str2);
        if (Build.VERSION.SDK_INT < 21) {
            CookieSyncManager.createInstance(baseActivity);
            CookieSyncManager.getInstance().sync();
            return;
        }
        CookieManager.getInstance().flush();
    }

    public static boolean startCamera(Activity activity, int i2) {
        if (OKLog.D) {
            OKLog.d(TAG, "getFromCamera -->> ");
        }
        if (CommonBase.checkSDcard()) {
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            Uri fromFile = Uri.fromFile(new File(PermissionHelper.getExternalCacheDir(), System.currentTimeMillis() + ".jpg"));
            mUri = fromFile;
            intent.putExtra("output", fromFile);
            if (OKLog.D) {
                OKLog.d(TAG, "getFromCamera uri-->> " + intent);
            }
            activity.startActivityForResult(intent, i2);
            return true;
        }
        Toast.makeText(activity, R.string.camera_hint_message, 0).show();
        return false;
    }

    public static String tryFixEncodedUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (str.toLowerCase().startsWith("http%3a%2f%2f") || str.toLowerCase().startsWith("https%3a%2f%2f")) {
            try {
                return URLDecoder.decode(str, "utf-8");
            } catch (Exception e2) {
                OKLog.e(TAG, e2);
            }
        }
        return str;
    }
}
