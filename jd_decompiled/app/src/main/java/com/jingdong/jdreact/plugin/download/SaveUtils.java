package com.jingdong.jdreact.plugin.download;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import tv.danmaku.ijk.media.JDPlayerConstant;

/* loaded from: classes13.dex */
public class SaveUtils {
    private static final String TAG = "SaveUtils";

    private static void copyFileAfterQ(Context context, ContentResolver contentResolver, File file, Uri uri) throws IOException {
        if (Build.VERSION.SDK_INT < 29 || context.getApplicationInfo().targetSdkVersion < 29) {
            return;
        }
        OutputStream openOutputStream = contentResolver.openOutputStream(uri);
        Files.copy(file.toPath(), openOutputStream);
        openOutputStream.close();
        file.delete();
    }

    public static ContentValues getImageContentValues(Context context) {
        ContentValues contentValues = new ContentValues();
        if (Build.VERSION.SDK_INT >= 29) {
            contentValues.put("_display_name", System.currentTimeMillis() + ".jpg");
            contentValues.put("mime_type", "image/*");
            contentValues.put("relative_path", Environment.DIRECTORY_DCIM + File.separator + context.getPackageName());
            contentValues.put("is_pending", (Integer) 1);
            contentValues.put("datetaken", Long.valueOf(System.currentTimeMillis()));
            contentValues.put("date_modified", Long.valueOf(System.currentTimeMillis()));
            contentValues.put("date_added", Long.valueOf(System.currentTimeMillis()));
            return contentValues;
        }
        return null;
    }

    public static ContentValues getVideoContentValues(Context context, File file, long j2) {
        ContentValues contentValues = new ContentValues();
        if (Build.VERSION.SDK_INT >= 29) {
            contentValues.put("relative_path", Environment.DIRECTORY_DCIM + File.separator + context.getPackageName());
        }
        contentValues.put("title", file.getName());
        contentValues.put("_display_name", file.getName());
        contentValues.put("mime_type", JDPlayerConstant.IJK_CACHE_SUPPORT_TYPE);
        contentValues.put("datetaken", Long.valueOf(j2));
        contentValues.put("date_modified", Long.valueOf(j2));
        contentValues.put("date_added", Long.valueOf(j2));
        contentValues.put("_size", Long.valueOf(file.length()));
        return contentValues;
    }

    public static boolean saveBitmapToAlbum(Context context, Bitmap bitmap) {
        if (bitmap != null && Build.VERSION.SDK_INT >= 29) {
            return saveBitmapToAlbumAfterQ(context, bitmap);
        }
        return false;
    }

    /* JADX WARN: Not initialized variable reg: 4, insn: 0x0063: MOVE (r3 I:??[OBJECT, ARRAY]) = (r4 I:??[OBJECT, ARRAY]), block:B:31:0x0063 */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0066 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static boolean saveBitmapToAlbumAfterQ(android.content.Context r7, android.graphics.Bitmap r8) {
        /*
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 0
            r2 = 29
            if (r0 < r2) goto L6f
            android.net.Uri r0 = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            android.content.ContentValues r2 = getImageContentValues(r7)
            android.content.ContentResolver r3 = r7.getContentResolver()
            android.net.Uri r0 = r3.insert(r0, r2)
            if (r0 != 0) goto L18
            return r1
        L18:
            r3 = 0
            android.content.ContentResolver r4 = r7.getContentResolver()     // Catch: java.lang.Throwable -> L49 java.lang.Exception -> L4b
            java.io.OutputStream r4 = r4.openOutputStream(r0)     // Catch: java.lang.Throwable -> L49 java.lang.Exception -> L4b
            android.graphics.Bitmap$CompressFormat r5 = android.graphics.Bitmap.CompressFormat.JPEG     // Catch: java.lang.Exception -> L47 java.lang.Throwable -> L62
            r6 = 50
            r8.compress(r5, r6, r4)     // Catch: java.lang.Exception -> L47 java.lang.Throwable -> L62
            r2.clear()     // Catch: java.lang.Exception -> L47 java.lang.Throwable -> L62
            java.lang.String r8 = "is_pending"
            java.lang.Integer r5 = java.lang.Integer.valueOf(r1)     // Catch: java.lang.Exception -> L47 java.lang.Throwable -> L62
            r2.put(r8, r5)     // Catch: java.lang.Exception -> L47 java.lang.Throwable -> L62
            android.content.ContentResolver r8 = r7.getContentResolver()     // Catch: java.lang.Exception -> L47 java.lang.Throwable -> L62
            r8.update(r0, r2, r3, r3)     // Catch: java.lang.Exception -> L47 java.lang.Throwable -> L62
            r7 = 1
            if (r4 == 0) goto L46
            r4.close()     // Catch: java.io.IOException -> L42
            goto L46
        L42:
            r8 = move-exception
            r8.printStackTrace()
        L46:
            return r7
        L47:
            r8 = move-exception
            goto L4d
        L49:
            r7 = move-exception
            goto L64
        L4b:
            r8 = move-exception
            r4 = r3
        L4d:
            android.content.ContentResolver r7 = r7.getContentResolver()     // Catch: java.lang.Throwable -> L62
            r7.delete(r0, r3, r3)     // Catch: java.lang.Throwable -> L62
            r8.printStackTrace()     // Catch: java.lang.Throwable -> L62
            if (r4 == 0) goto L61
            r4.close()     // Catch: java.io.IOException -> L5d
            goto L61
        L5d:
            r7 = move-exception
            r7.printStackTrace()
        L61:
            return r1
        L62:
            r7 = move-exception
            r3 = r4
        L64:
            if (r3 == 0) goto L6e
            r3.close()     // Catch: java.io.IOException -> L6a
            goto L6e
        L6a:
            r8 = move-exception
            r8.printStackTrace()
        L6e:
            throw r7
        L6f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.jdreact.plugin.download.SaveUtils.saveBitmapToAlbumAfterQ(android.content.Context, android.graphics.Bitmap):boolean");
    }

    public static boolean saveImgFileToAlbum(Context context, String str) {
        String str2 = "saveImgToAlbum() imageFile = [" + str + "]";
        try {
            return saveBitmapToAlbum(context, BitmapFactory.decodeFile(str));
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public static boolean saveVideoToAlbum(Context context, String str) {
        String str2 = "saveVideoToAlbum() videoFile = [" + str + "]";
        if (Build.VERSION.SDK_INT < 29) {
            return false;
        }
        return saveVideoToAlbumAfterQ(context, str);
    }

    private static boolean saveVideoToAlbumAfterQ(Context context, String str) {
        if (Build.VERSION.SDK_INT >= 29) {
            try {
                ContentResolver contentResolver = context.getContentResolver();
                File file = new File(str);
                ContentValues videoContentValues = getVideoContentValues(context, file, System.currentTimeMillis());
                Uri insert = contentResolver.insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, videoContentValues);
                copyFileAfterQ(context, contentResolver, file, insert);
                videoContentValues.clear();
                videoContentValues.put("is_pending", (Integer) 0);
                context.getContentResolver().update(insert, videoContentValues, null, null);
                context.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", insert));
                return true;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return false;
    }
}
