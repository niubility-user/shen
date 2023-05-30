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
    */
    private static boolean saveBitmapToAlbumAfterQ(Context context, Bitmap bitmap) {
        OutputStream outputStream;
        OutputStream outputStream2;
        if (Build.VERSION.SDK_INT >= 29) {
            Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            ContentValues imageContentValues = getImageContentValues(context);
            Uri insert = context.getContentResolver().insert(uri, imageContentValues);
            if (insert == null) {
                return false;
            }
            OutputStream outputStream3 = null;
            try {
                try {
                    outputStream = context.getContentResolver().openOutputStream(insert);
                } catch (Exception e2) {
                    e = e2;
                    outputStream = null;
                } catch (Throwable th) {
                    th = th;
                    if (outputStream3 != null) {
                    }
                    throw th;
                }
                try {
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 50, outputStream);
                    imageContentValues.clear();
                    imageContentValues.put("is_pending", (Integer) 0);
                    context.getContentResolver().update(insert, imageContentValues, null, null);
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                    return true;
                } catch (Exception e4) {
                    e = e4;
                    context.getContentResolver().delete(insert, null, null);
                    e.printStackTrace();
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (IOException e5) {
                            e5.printStackTrace();
                        }
                    }
                    return false;
                }
            } catch (Throwable th2) {
                th = th2;
                outputStream3 = outputStream2;
                if (outputStream3 != null) {
                    try {
                        outputStream3.close();
                    } catch (IOException e6) {
                        e6.printStackTrace();
                    }
                }
                throw th;
            }
        }
        return false;
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
