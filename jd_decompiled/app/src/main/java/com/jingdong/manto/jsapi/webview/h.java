package com.jingdong.manto.jsapi.webview;

import android.content.ContentUris;
import android.content.Context;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import androidx.annotation.RequiresApi;
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String a(android.content.Context r9, android.net.Uri r10, java.lang.String r11, java.lang.String[] r12) {
        /*
            r0 = 1
            java.lang.String[] r3 = new java.lang.String[r0]
            r0 = 0
            java.lang.String r7 = "_data"
            r3[r0] = r7
            r8 = 0
            android.content.ContentResolver r1 = r9.getContentResolver()     // Catch: java.lang.Throwable -> L82 java.lang.Exception -> L84
            r6 = 0
            r2 = r10
            r4 = r11
            r5 = r12
            android.database.Cursor r9 = r1.query(r2, r3, r4, r5, r6)     // Catch: java.lang.Throwable -> L82 java.lang.Exception -> L84
            if (r9 == 0) goto L7c
            boolean r11 = r9.moveToFirst()     // Catch: java.lang.Exception -> L7a java.lang.Throwable -> L91
            if (r11 != 0) goto L1e
            goto L7c
        L1e:
            int r11 = r9.getColumnIndex(r7)     // Catch: java.lang.Exception -> L7a java.lang.Throwable -> L91
            r12 = -1
            if (r11 == r12) goto L33
            int r10 = r9.getColumnIndexOrThrow(r7)     // Catch: java.lang.Exception -> L7a java.lang.Throwable -> L91
            java.lang.String r10 = r9.getString(r10)     // Catch: java.lang.Exception -> L7a java.lang.Throwable -> L91
            if (r9 == 0) goto L32
            r9.close()
        L32:
            return r10
        L33:
            java.lang.String r11 = r10.getPath()     // Catch: java.lang.Exception -> L7a java.lang.Throwable -> L91
            java.lang.String r1 = "/"
            java.lang.String[] r11 = r11.split(r1)     // Catch: java.lang.Exception -> L7a java.lang.Throwable -> L91
        L3d:
            int r1 = r11.length     // Catch: java.lang.Exception -> L7a java.lang.Throwable -> L91
            if (r0 >= r1) goto L4e
            r1 = r11[r0]     // Catch: java.lang.Exception -> L7a java.lang.Throwable -> L91
            java.lang.String r2 = "storage"
            boolean r1 = r1.equals(r2)     // Catch: java.lang.Exception -> L7a java.lang.Throwable -> L91
            if (r1 == 0) goto L4b
            goto L4f
        L4b:
            int r0 = r0 + 1
            goto L3d
        L4e:
            r0 = -1
        L4f:
            if (r0 == r12) goto L70
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L7a java.lang.Throwable -> L91
            r10.<init>()     // Catch: java.lang.Exception -> L7a java.lang.Throwable -> L91
        L56:
            int r12 = r11.length     // Catch: java.lang.Exception -> L7a java.lang.Throwable -> L91
            if (r0 >= r12) goto L66
            r12 = 47
            r10.append(r12)     // Catch: java.lang.Exception -> L7a java.lang.Throwable -> L91
            r12 = r11[r0]     // Catch: java.lang.Exception -> L7a java.lang.Throwable -> L91
            r10.append(r12)     // Catch: java.lang.Exception -> L7a java.lang.Throwable -> L91
            int r0 = r0 + 1
            goto L56
        L66:
            java.lang.String r10 = r10.toString()     // Catch: java.lang.Exception -> L7a java.lang.Throwable -> L91
            if (r9 == 0) goto L6f
            r9.close()
        L6f:
            return r10
        L70:
            java.lang.String r10 = r10.getPath()     // Catch: java.lang.Exception -> L7a java.lang.Throwable -> L91
            if (r9 == 0) goto L79
            r9.close()
        L79:
            return r10
        L7a:
            r10 = move-exception
            goto L86
        L7c:
            if (r9 == 0) goto L81
            r9.close()
        L81:
            return r8
        L82:
            r10 = move-exception
            goto L93
        L84:
            r10 = move-exception
            r9 = r8
        L86:
            java.lang.String r11 = "webviewUtils"
            com.jingdong.manto.utils.MantoLog.e(r11, r10)     // Catch: java.lang.Throwable -> L91
            if (r9 == 0) goto L90
            r9.close()
        L90:
            return r8
        L91:
            r10 = move-exception
            r8 = r9
        L93:
            if (r8 == 0) goto L98
            r8.close()
        L98:
            goto L9a
        L99:
            throw r10
        L9a:
            goto L99
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.manto.jsapi.webview.h.a(android.content.Context, android.net.Uri, java.lang.String, java.lang.String[]):java.lang.String");
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
