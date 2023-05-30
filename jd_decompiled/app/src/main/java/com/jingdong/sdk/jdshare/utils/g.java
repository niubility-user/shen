package com.jingdong.sdk.jdshare.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;
import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.DpiUtil;
import com.jingdong.common.entity.ShareInfo;
import com.jingdong.common.utils.FileUtils;
import com.jingdong.common.utils.ShareValues;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.FileService;
import java.io.ByteArrayOutputStream;
import java.io.File;

/* loaded from: classes7.dex */
public class g {
    public static String a(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        if (byteArrayOutputStream.toByteArray() == null) {
            return null;
        }
        return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
    }

    public static String b(Context context, String str) {
        try {
            File file = new File(str);
            StringBuilder sb = new StringBuilder();
            sb.append(context.getExternalFilesDir(null));
            String str2 = File.separator;
            sb.append(str2);
            sb.append("image");
            String sb2 = sb.toString();
            if (file.exists()) {
                if (TextUtils.equals(sb2, file.getParent())) {
                    return str;
                }
                String name = file.getName();
                if (TextUtils.isEmpty(name)) {
                    return "";
                }
                String str3 = sb2 + str2 + name;
                new File(str3).deleteOnExit();
                return FileUtils.saveToFile(str3, str) ? str3 : "";
            }
            return "";
        } catch (Throwable unused) {
            return "";
        }
    }

    public static byte[] c(Bitmap bitmap, int i2) {
        byte[] bArr = new byte[0];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i3 = 95;
        while (true) {
            if ((bArr.length == 0 || bArr.length > i2) && i3 > 0) {
                byteArrayOutputStream.reset();
                bitmap.compress(Bitmap.CompressFormat.JPEG, i3, byteArrayOutputStream);
                bArr = byteArrayOutputStream.toByteArray();
                i3 -= 10;
            }
        }
        return bArr;
    }

    public static int d(Context context, int i2) {
        if (ShareValues.isIsFullScreenPhone1700()) {
            i2 = (int) (i2 * ShareValues.getFullScreenModeScaleValue());
        }
        return (int) (((DpiUtil.getAppWidth((Activity) context) * i2) / 750.0f) + 0.5f);
    }

    public static String e() {
        FileService.Directory directory = FileService.getDirectory(1);
        if (directory != null) {
            return directory.getPath() + "/share_qrcode_image.png";
        }
        return "";
    }

    public static Bitmap f(byte[] bArr, float f2) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
        options.inSampleSize = (int) Math.ceil(options.outWidth / f2);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
    }

    public static Bitmap g(String str, float f2) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        options.inSampleSize = (int) Math.ceil(options.outWidth / f2);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(str, options);
    }

    public static String h(ShareInfo shareInfo, String str) {
        JDJSONObject parseObject;
        try {
            if (TextUtils.isEmpty(str)) {
                return shareInfo != null ? shareInfo.getShareSource() : "";
            } else if (shareInfo == null || TextUtils.isEmpty(shareInfo.getShareSource()) || (parseObject = JDJSON.parseObject(shareInfo.getShareSource())) == null) {
                return str;
            } else {
                JDJSONObject parseObject2 = JDJSON.parseObject(str);
                if (parseObject2 != null) {
                    for (String str2 : parseObject2.keySet()) {
                        parseObject.put(str2, (Object) parseObject2.getString(str2));
                    }
                }
                return parseObject.toJSONString();
            }
        } catch (Exception unused) {
            return "";
        }
    }

    private static Uri i(@NonNull Context context, @NonNull File file) {
        try {
            try {
                return FileProvider.getUriForFile(context, "com.jingdong.app.mall.provider.shareprovider", file);
            } catch (Exception unused) {
                if (Build.VERSION.SDK_INT < 24) {
                    return Uri.fromFile(file);
                }
                File file2 = new File(new File(context.getCacheDir(), "huawei"), file.getName());
                file2.deleteOnExit();
                if (FileUtils.saveToFile(file2.getAbsolutePath(), file.getAbsolutePath())) {
                    return FileProvider.getUriForFile(context, "com.jingdong.app.mall.provider.shareprovider", file2);
                }
                return null;
            }
        } catch (Exception unused2) {
            return null;
        }
    }

    public static Bitmap j(Bitmap bitmap, float f2) {
        if (bitmap.getWidth() > f2) {
            float width = f2 / bitmap.getWidth();
            Matrix matrix = new Matrix();
            matrix.postScale(width, width);
            return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        }
        return bitmap;
    }

    public static void k(String str, String str2, com.jingdong.c.a.c.f fVar) {
        ShareInfo shareInfo;
        if (fVar == null || (shareInfo = fVar.b) == null) {
            return;
        }
        String url = shareInfo.getUrl();
        StringBuilder sb = new StringBuilder();
        sb.append(str2);
        sb.append(fVar.d ? "_0" : "_1");
        sb.append(fVar.b() ? "_1" : "_0");
        JDMtaUtils.sendClickDataWithExt(JdSdk.getInstance().getApplicationContext(), str, url, "", "", "ShareActivity", sb.toString(), "", fVar.b.getShareSource(), null);
    }

    public static void l(String str, String str2, String str3, String str4) {
        JDMtaUtils.sendClickDataWithExt(JdSdk.getInstance().getApplicationContext(), str, str2, "", "", "ShareActivity", str3, "", str4, null);
    }

    public static void m(String str, String str2, String str3, boolean z, String str4) {
        Context applicationContext = JdSdk.getInstance().getApplicationContext();
        StringBuilder sb = new StringBuilder();
        sb.append(str3);
        sb.append(z ? "_1" : "_0");
        JDMtaUtils.sendClickDataWithExt(applicationContext, str, str2, "", "", "ShareActivity", sb.toString(), "", str4, null);
    }

    public static void n(String str, String str2, String str3, String str4) {
        JDMtaUtils.sendExposureDataWithExt(JdSdk.getInstance().getApplicationContext(), str, str2, "", "ShareActivity", str3, str4, null);
    }

    public static String o(Context context, String str, String str2) {
        Uri i2;
        if (context == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        File file = new File(str2);
        if (file.exists() && (i2 = i(context, file)) != null) {
            try {
                context.grantUriPermission(str, i2, 1);
                return i2.toString();
            } catch (Exception unused) {
                return null;
            }
        }
        return null;
    }
}
