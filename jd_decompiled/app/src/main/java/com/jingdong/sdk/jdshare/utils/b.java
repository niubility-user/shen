package com.jingdong.sdk.jdshare.utils;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import cn.com.union.fido.common.MIMEType;
import com.jingdong.common.web.managers.PerformanceManager;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.network.utils.JDNetworkConstant;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import jpbury.t;
import tv.danmaku.ijk.media.JDPlayerConstant;

/* loaded from: classes7.dex */
public class b {
    public static String a = "JdShare_savePictureFile_Error";
    private static String b = "";

    /* renamed from: c  reason: collision with root package name */
    private static String f15023c = "";

    private static void a(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[4096];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                outputStream.write(bArr, 0, read);
            } else {
                outputStream.flush();
                return;
            }
        }
    }

    private static Uri b(Context context, File file, boolean z) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("_display_name", System.currentTimeMillis() + CartConstant.KEY_YB_INFO_LINK + file.getName());
        contentValues.put("mime_type", z ? MIMEType.MIME_TYPE_JPEG : JDPlayerConstant.IJK_CACHE_SUPPORT_TYPE);
        if (Build.VERSION.SDK_INT >= 29) {
            contentValues.put("datetaken", Long.valueOf(file.lastModified()));
        }
        try {
            return context.getContentResolver().insert(z ? MediaStore.Images.Media.EXTERNAL_CONTENT_URI : MediaStore.Video.Media.EXTERNAL_CONTENT_URI, contentValues);
        } catch (Exception e2) {
            b = "insertFileIntoMediaStore exception";
            f15023c = e2.toString();
            e2.printStackTrace();
            return null;
        }
    }

    private static void c(String str) {
        if (str == null) {
        }
    }

    private static boolean d(Context context, File file, Uri uri) {
        if (uri == null) {
            c("url is null");
            b = "url is null";
            return false;
        }
        c("SaveFile: " + file.getName());
        ParcelFileDescriptor parcelFileDescriptor = null;
        try {
            parcelFileDescriptor = context.getContentResolver().openFileDescriptor(uri, JshopConst.JSHOP_PROMOTIO_W);
        } catch (FileNotFoundException e2) {
            b = "saveFile1 Exception";
            f15023c = e2.toString();
            e2.printStackTrace();
        }
        if (parcelFileDescriptor == null) {
            c("parcelFileDescriptor is null");
            b = "parcelFileDescriptor is null";
            return false;
        }
        FileOutputStream fileOutputStream = new FileOutputStream(parcelFileDescriptor.getFileDescriptor());
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            try {
                try {
                    a(fileInputStream, fileOutputStream);
                    try {
                        fileOutputStream.close();
                    } catch (IOException e3) {
                        c(e3.toString());
                    }
                    try {
                        fileInputStream.close();
                        return true;
                    } catch (IOException e4) {
                        c(e4.toString());
                        return true;
                    }
                } catch (IOException e5) {
                    c(e5.toString());
                    b = "copy Exception";
                    f15023c = e5.toString();
                    try {
                        fileOutputStream.close();
                    } catch (IOException e6) {
                        c(e6.toString());
                    }
                    try {
                        fileInputStream.close();
                    } catch (IOException e7) {
                        c(e7.toString());
                    }
                    return false;
                }
            } catch (Throwable th) {
                try {
                    fileOutputStream.close();
                } catch (IOException e8) {
                    c(e8.toString());
                }
                try {
                    fileInputStream.close();
                } catch (IOException e9) {
                    c(e9.toString());
                }
                throw th;
            }
        } catch (FileNotFoundException e10) {
            c(e10.toString());
            b = "saveFile2 Exception";
            f15023c = e10.toString();
            try {
                fileOutputStream.close();
            } catch (IOException e11) {
                c(e11.toString());
            }
            return false;
        }
    }

    public static boolean e(Context context, File file) {
        if (file == null) {
            b = "file is null";
            return false;
        }
        return d(context, file, b(context, file, true));
    }

    public static void f(Context context) {
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("function", a);
            hashMap.put("errCode", JDNetworkConstant.START_UP_TIME_ERRCODE);
            hashMap.put(PerformanceManager.ERR_TYPE, "2");
            hashMap.put("occurTime", ExceptionReporter.getCurrentMicrosecond());
            hashMap.put("errMsg", b);
            hashMap.put(t.f20145j, f15023c);
            ExceptionReporter.sendExceptionData(context, hashMap);
        } catch (Throwable unused) {
        }
    }
}
