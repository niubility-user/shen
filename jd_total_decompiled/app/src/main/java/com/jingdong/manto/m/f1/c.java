package com.jingdong.manto.m.f1;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.net.Uri;
import com.jingdong.common.XView2.strategy.preDownLoadManager.PreDownLoadManager;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoStringUtils;
import com.jingdong.manto.utils.n;
import com.jingdong.manto.utils.s;
import com.jingdong.manto.utils.t;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: classes15.dex */
public final class c {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v14 */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v2, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v6 */
    public static String a(Context context, String str) {
        FileInputStream fileInputStream;
        String str2;
        boolean a;
        String str3 = null;
        str3 = null;
        str3 = null;
        str3 = null;
        InputStream inputStream = null;
        if (!MantoStringUtils.isEmpty(str)) {
            ?? startsWith = str.startsWith("content://");
            try {
                if (startsWith != 0) {
                    try {
                        FileDescriptor fileDescriptor = context.getContentResolver().openFileDescriptor(Uri.parse(str), "r").getFileDescriptor();
                        str2 = n.b + "/v/mantoMsg.tmp." + System.currentTimeMillis() + PreDownLoadManager.VIDEO_SKU_SUFFIX;
                        fileInputStream = new FileInputStream(fileDescriptor);
                    } catch (Exception e2) {
                        e = e2;
                        fileInputStream = null;
                    } catch (Throwable th) {
                        th = th;
                        t.a(inputStream);
                        throw th;
                    }
                    try {
                        a = s.a(fileInputStream, str2);
                        t.a(fileInputStream);
                    } catch (Exception e3) {
                        e = e3;
                        MantoLog.e("videoUtil", "content:// copy video file failed.", e);
                        t.a(fileInputStream);
                        startsWith = fileInputStream;
                        return str3;
                    }
                    if (a) {
                        return str2;
                    }
                    str3 = str2;
                    startsWith = fileInputStream;
                } else if (str.startsWith("file:")) {
                    return a.a(context, str);
                } else {
                    MantoLog.d("videoUtil", "selected:" + str);
                    try {
                        String str4 = n.b + "/v/mantoMsg.tmp." + System.currentTimeMillis() + PreDownLoadManager.VIDEO_SKU_SUFFIX;
                        if (s.a(str, str4)) {
                            return str4;
                        }
                    } catch (Exception e4) {
                        MantoLog.e("videoUtil", "file path copy image file failed.", e4);
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                inputStream = startsWith;
            }
        }
        return str3;
    }

    /* JADX WARN: Not initialized variable reg: 4, insn: 0x00a1: MOVE (r1 I:??[OBJECT, ARRAY]) = (r4 I:??[OBJECT, ARRAY]), block:B:39:0x00a1 */
    public static String a(String str) {
        FileOutputStream fileOutputStream;
        OutputStream outputStream;
        Bitmap createVideoThumbnail = ThumbnailUtils.createVideoThumbnail(str, 3);
        if (createVideoThumbnail == null) {
            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            try {
                try {
                    mediaMetadataRetriever.setDataSource(str);
                    createVideoThumbnail = mediaMetadataRetriever.getFrameAtTime();
                } catch (IllegalArgumentException e2) {
                    e2.printStackTrace();
                }
            } finally {
                mediaMetadataRetriever.release();
            }
        }
        OutputStream outputStream2 = null;
        if (createVideoThumbnail != null) {
            String str2 = n.b + "/photo/mantoThumb.tmp." + System.currentTimeMillis() + ".jpg";
            File file = new File(str2);
            try {
                try {
                    if (file.getParentFile() != null && !file.getParentFile().exists()) {
                        file.getParentFile().mkdirs();
                    }
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    fileOutputStream = new FileOutputStream(file);
                } catch (IOException e3) {
                    e = e3;
                    fileOutputStream = null;
                } catch (Throwable th) {
                    th = th;
                    createVideoThumbnail.recycle();
                    t.a(outputStream2);
                    throw th;
                }
                try {
                    createVideoThumbnail.compress(Bitmap.CompressFormat.JPEG, 85, fileOutputStream);
                    fileOutputStream.flush();
                    createVideoThumbnail.recycle();
                    t.a(fileOutputStream);
                    return str2;
                } catch (IOException e4) {
                    e = e4;
                    MantoLog.e("videoUtil", String.format("getThumbImage from video file: %s, failed:%e", str, e));
                    createVideoThumbnail.recycle();
                    t.a(fileOutputStream);
                    return null;
                }
            } catch (Throwable th2) {
                th = th2;
                outputStream2 = outputStream;
                createVideoThumbnail.recycle();
                t.a(outputStream2);
                throw th;
            }
        }
        return null;
    }
}
