package com.jingdong.manto.jsapi.webview;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import androidx.annotation.RequiresApi;
import com.jd.dynamic.base.interfaces.IExceptionHandler;
import com.jingdong.manto.utils.MantoLog;

/* loaded from: classes15.dex */
final class h {
    public static String a(Context context, Uri uri) {
        if ("content".equals(uri.getScheme())) {
            return context.getContentResolver().getType(uri);
        }
        String fileExtensionFromUrl = MimeTypeMap.getFileExtensionFromUrl(uri.toString());
        if (TextUtils.isEmpty(fileExtensionFromUrl)) {
            fileExtensionFromUrl = a(uri.toString());
        }
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtensionFromUrl.toLowerCase());
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0095  */
    /* JADX WARN: Type inference failed for: r8v0 */
    /* JADX WARN: Type inference failed for: r8v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r8v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String a(Context context, Uri uri, String str, String[] strArr) {
        Cursor cursor;
        int i2 = 0;
        ?? r8 = 0;
        try {
            try {
                cursor = context.getContentResolver().query(uri, new String[]{"_data"}, str, strArr, null);
                if (cursor != null) {
                    try {
                        if (cursor.moveToFirst()) {
                            if (cursor.getColumnIndex("_data") != -1) {
                                String string = cursor.getString(cursor.getColumnIndexOrThrow("_data"));
                                if (cursor != null) {
                                    cursor.close();
                                }
                                return string;
                            }
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
                            if (i2 == -1) {
                                String path = uri.getPath();
                                if (cursor != null) {
                                    cursor.close();
                                }
                                return path;
                            }
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
                    } catch (Exception e2) {
                        e = e2;
                        MantoLog.e("webviewUtils", e);
                        if (cursor != null) {
                            cursor.close();
                        }
                        return null;
                    }
                }
                if (cursor != null) {
                    cursor.close();
                }
                return null;
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

    public static String a(String str) {
        int lastIndexOf;
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        int lastIndexOf2 = str.lastIndexOf(35);
        if (lastIndexOf2 > 0) {
            str = str.substring(0, lastIndexOf2);
        }
        int lastIndexOf3 = str.lastIndexOf(63);
        if (lastIndexOf3 > 0) {
            str = str.substring(0, lastIndexOf3);
        }
        int lastIndexOf4 = str.lastIndexOf(47);
        if (lastIndexOf4 >= 0) {
            str = str.substring(lastIndexOf4 + 1);
        }
        return (str.isEmpty() || (lastIndexOf = str.lastIndexOf(46)) < 0) ? "" : str.substring(lastIndexOf + 1);
    }

    public static boolean a(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    @RequiresApi(api = 19)
    public static String b(Context context, Uri uri) {
        Uri uri2 = null;
        if (DocumentsContract.isDocumentUri(context, uri)) {
            if (a(uri)) {
                return a(context, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(DocumentsContract.getDocumentId(uri)).longValue()), null, null);
            }
            if (c(uri)) {
                String[] split = DocumentsContract.getDocumentId(uri).split(":");
                String str = split[0];
                if ("image".equals(str)) {
                    uri2 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(str)) {
                    uri2 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(str)) {
                    uri2 = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                return a(context, uri2, "_id=?", new String[]{split[1]});
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return b(uri) ? uri.getLastPathSegment() : a(context, uri, null, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return uri.toString();
    }

    public static boolean b(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    @RequiresApi(api = 19)
    public static String c(Context context, Uri uri) {
        String str = null;
        if (uri == null) {
            return null;
        }
        try {
            context.grantUriPermission(context.getPackageName(), uri, 1);
            str = b(context, uri);
            context.revokeUriPermission(uri, 1);
            return str;
        } catch (Exception e2) {
            MantoLog.e("webViewUtils", e2);
            return str;
        }
    }

    public static boolean c(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }
}
