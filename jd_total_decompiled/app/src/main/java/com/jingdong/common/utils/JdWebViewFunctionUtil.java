package com.jingdong.common.utils;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
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
import com.jd.dynamic.base.interfaces.IExceptionHandler;
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
    */
    public static String getDataColumn(Context context, Uri uri, String str, String[] strArr) {
        Cursor cursor;
        int i2 = 0;
        ?? r8 = 0;
        try {
            try {
                cursor = context.getContentResolver().query(uri, new String[]{"_data"}, str, strArr, null);
                if (cursor != null) {
                    try {
                        if (cursor.moveToFirst()) {
                            if (cursor.getColumnIndex("_data") == -1) {
                                String[] split = uri.getPath().split("/");
                                while (true) {
                                    if (i2 >= split.length) {
                                        i2 = -1;
                                        break;
                                    } else if (split[i2].equals(IExceptionHandler.DynamicExceptionData.TYPE_STORAGE)) {
                                        break;
                                    } else {
                                        i2++;
                                    }
                                }
                                if (i2 != -1) {
                                    StringBuilder sb = new StringBuilder();
                                    while (i2 < split.length) {
                                        sb.append('/');
                                        sb.append(split[i2]);
                                        i2++;
                                    }
                                    String sb2 = sb.toString();
                                    if (cursor != null) {
                                        cursor.close();
                                    }
                                    return sb2;
                                }
                                String path = uri.getPath();
                                if (cursor != null) {
                                    cursor.close();
                                }
                                return path;
                            }
                            String string = cursor.getString(cursor.getColumnIndexOrThrow("_data"));
                            if (cursor != null) {
                                cursor.close();
                            }
                            return string;
                        }
                    } catch (Exception e2) {
                        e = e2;
                        OKLog.e(TAG, e);
                    }
                }
            } catch (Exception e3) {
                e = e3;
                cursor = null;
            } catch (Throwable th) {
                th = th;
                if (r8 != 0) {
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            r8 = context;
            if (r8 != 0) {
                r8.close();
            }
            throw th;
        }
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
