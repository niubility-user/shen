package com.jingdong.manto.m.f1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import androidx.core.content.FileProvider;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessMagicHeadPicInfoEntity;
import com.jingdong.manto.provider.MantoFileProvider;
import com.jingdong.manto.utils.MantoDensityUtils;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoStringUtils;
import com.jingdong.manto.utils.MantoUtils;
import com.jingdong.manto.utils.n;
import com.jingdong.manto.utils.r;
import com.jingdong.manto.utils.s;
import com.jingdong.manto.utils.t;
import com.jingdong.sdk.platform.business.personal.R2;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes15.dex */
public class b {
    public static int a(String str) {
        int attributeInt;
        try {
            attributeInt = new ExifInterface(str).getAttributeInt("Orientation", 1);
        } catch (IOException e2) {
            MantoLog.e("PhotoUtil", "" + e2.toString());
        }
        if (attributeInt != 3) {
            if (attributeInt != 6) {
                return attributeInt != 8 ? 0 : 270;
            }
            return 90;
        }
        return 180;
    }

    public static String a(int i2) {
        switch (i2) {
            case 2:
                return "up-mirrored";
            case 3:
                return "down";
            case 4:
                return "down-mirrored";
            case 5:
                return "left-mirrored";
            case 6:
                return "right";
            case 7:
                return "right-mirrored";
            case 8:
                return "left";
            default:
                return "up";
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0079 A[Catch: OutOfMemoryError -> 0x00fc, NullPointerException | Exception -> 0x0103, NullPointerException | Exception -> 0x0103, TryCatch #0 {NullPointerException | Exception -> 0x0103, blocks: (B:3:0x0001, B:7:0x0031, B:7:0x0031, B:19:0x0065, B:19:0x0065, B:21:0x0079, B:21:0x0079, B:23:0x007e, B:23:0x007e, B:24:0x0085, B:24:0x0085, B:26:0x0090, B:26:0x0090, B:29:0x0095, B:29:0x0095, B:31:0x009e, B:31:0x009e, B:33:0x00a3, B:33:0x00a3, B:32:0x00a1, B:32:0x00a1, B:34:0x00a7, B:34:0x00a7, B:36:0x00c2, B:36:0x00c2, B:38:0x00cc, B:38:0x00cc, B:39:0x00d3, B:39:0x00d3, B:41:0x00d9, B:41:0x00d9, B:42:0x00dc, B:42:0x00dc, B:44:0x00e7, B:44:0x00e7, B:46:0x00ec, B:46:0x00ec, B:45:0x00ea, B:45:0x00ea, B:22:0x007c, B:22:0x007c, B:12:0x004f, B:12:0x004f), top: B:51:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x007c A[Catch: OutOfMemoryError -> 0x00fc, NullPointerException | Exception -> 0x0103, NullPointerException | Exception -> 0x0103, TryCatch #0 {NullPointerException | Exception -> 0x0103, blocks: (B:3:0x0001, B:7:0x0031, B:7:0x0031, B:19:0x0065, B:19:0x0065, B:21:0x0079, B:21:0x0079, B:23:0x007e, B:23:0x007e, B:24:0x0085, B:24:0x0085, B:26:0x0090, B:26:0x0090, B:29:0x0095, B:29:0x0095, B:31:0x009e, B:31:0x009e, B:33:0x00a3, B:33:0x00a3, B:32:0x00a1, B:32:0x00a1, B:34:0x00a7, B:34:0x00a7, B:36:0x00c2, B:36:0x00c2, B:38:0x00cc, B:38:0x00cc, B:39:0x00d3, B:39:0x00d3, B:41:0x00d9, B:41:0x00d9, B:42:0x00dc, B:42:0x00dc, B:44:0x00e7, B:44:0x00e7, B:46:0x00ec, B:46:0x00ec, B:45:0x00ea, B:45:0x00ea, B:22:0x007c, B:22:0x007c, B:12:0x004f, B:12:0x004f), top: B:51:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x009e A[Catch: OutOfMemoryError -> 0x00fc, NullPointerException | Exception -> 0x0103, NullPointerException | Exception -> 0x0103, TryCatch #0 {NullPointerException | Exception -> 0x0103, blocks: (B:3:0x0001, B:7:0x0031, B:7:0x0031, B:19:0x0065, B:19:0x0065, B:21:0x0079, B:21:0x0079, B:23:0x007e, B:23:0x007e, B:24:0x0085, B:24:0x0085, B:26:0x0090, B:26:0x0090, B:29:0x0095, B:29:0x0095, B:31:0x009e, B:31:0x009e, B:33:0x00a3, B:33:0x00a3, B:32:0x00a1, B:32:0x00a1, B:34:0x00a7, B:34:0x00a7, B:36:0x00c2, B:36:0x00c2, B:38:0x00cc, B:38:0x00cc, B:39:0x00d3, B:39:0x00d3, B:41:0x00d9, B:41:0x00d9, B:42:0x00dc, B:42:0x00dc, B:44:0x00e7, B:44:0x00e7, B:46:0x00ec, B:46:0x00ec, B:45:0x00ea, B:45:0x00ea, B:22:0x007c, B:22:0x007c, B:12:0x004f, B:12:0x004f), top: B:51:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00a1 A[Catch: OutOfMemoryError -> 0x00fc, NullPointerException | Exception -> 0x0103, NullPointerException | Exception -> 0x0103, TryCatch #0 {NullPointerException | Exception -> 0x0103, blocks: (B:3:0x0001, B:7:0x0031, B:7:0x0031, B:19:0x0065, B:19:0x0065, B:21:0x0079, B:21:0x0079, B:23:0x007e, B:23:0x007e, B:24:0x0085, B:24:0x0085, B:26:0x0090, B:26:0x0090, B:29:0x0095, B:29:0x0095, B:31:0x009e, B:31:0x009e, B:33:0x00a3, B:33:0x00a3, B:32:0x00a1, B:32:0x00a1, B:34:0x00a7, B:34:0x00a7, B:36:0x00c2, B:36:0x00c2, B:38:0x00cc, B:38:0x00cc, B:39:0x00d3, B:39:0x00d3, B:41:0x00d9, B:41:0x00d9, B:42:0x00dc, B:42:0x00dc, B:44:0x00e7, B:44:0x00e7, B:46:0x00ec, B:46:0x00ec, B:45:0x00ea, B:45:0x00ea, B:22:0x007c, B:22:0x007c, B:12:0x004f, B:12:0x004f), top: B:51:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00d9 A[Catch: OutOfMemoryError -> 0x00fc, NullPointerException | Exception -> 0x0103, NullPointerException | Exception -> 0x0103, TryCatch #0 {NullPointerException | Exception -> 0x0103, blocks: (B:3:0x0001, B:7:0x0031, B:7:0x0031, B:19:0x0065, B:19:0x0065, B:21:0x0079, B:21:0x0079, B:23:0x007e, B:23:0x007e, B:24:0x0085, B:24:0x0085, B:26:0x0090, B:26:0x0090, B:29:0x0095, B:29:0x0095, B:31:0x009e, B:31:0x009e, B:33:0x00a3, B:33:0x00a3, B:32:0x00a1, B:32:0x00a1, B:34:0x00a7, B:34:0x00a7, B:36:0x00c2, B:36:0x00c2, B:38:0x00cc, B:38:0x00cc, B:39:0x00d3, B:39:0x00d3, B:41:0x00d9, B:41:0x00d9, B:42:0x00dc, B:42:0x00dc, B:44:0x00e7, B:44:0x00e7, B:46:0x00ec, B:46:0x00ec, B:45:0x00ea, B:45:0x00ea, B:22:0x007c, B:22:0x007c, B:12:0x004f, B:12:0x004f), top: B:51:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00e7 A[Catch: OutOfMemoryError -> 0x00fc, NullPointerException | Exception -> 0x0103, NullPointerException | Exception -> 0x0103, TryCatch #0 {NullPointerException | Exception -> 0x0103, blocks: (B:3:0x0001, B:7:0x0031, B:7:0x0031, B:19:0x0065, B:19:0x0065, B:21:0x0079, B:21:0x0079, B:23:0x007e, B:23:0x007e, B:24:0x0085, B:24:0x0085, B:26:0x0090, B:26:0x0090, B:29:0x0095, B:29:0x0095, B:31:0x009e, B:31:0x009e, B:33:0x00a3, B:33:0x00a3, B:32:0x00a1, B:32:0x00a1, B:34:0x00a7, B:34:0x00a7, B:36:0x00c2, B:36:0x00c2, B:38:0x00cc, B:38:0x00cc, B:39:0x00d3, B:39:0x00d3, B:41:0x00d9, B:41:0x00d9, B:42:0x00dc, B:42:0x00dc, B:44:0x00e7, B:44:0x00e7, B:46:0x00ec, B:46:0x00ec, B:45:0x00ea, B:45:0x00ea, B:22:0x007c, B:22:0x007c, B:12:0x004f, B:12:0x004f), top: B:51:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00ea A[Catch: OutOfMemoryError -> 0x00fc, NullPointerException | Exception -> 0x0103, NullPointerException | Exception -> 0x0103, TryCatch #0 {NullPointerException | Exception -> 0x0103, blocks: (B:3:0x0001, B:7:0x0031, B:7:0x0031, B:19:0x0065, B:19:0x0065, B:21:0x0079, B:21:0x0079, B:23:0x007e, B:23:0x007e, B:24:0x0085, B:24:0x0085, B:26:0x0090, B:26:0x0090, B:29:0x0095, B:29:0x0095, B:31:0x009e, B:31:0x009e, B:33:0x00a3, B:33:0x00a3, B:32:0x00a1, B:32:0x00a1, B:34:0x00a7, B:34:0x00a7, B:36:0x00c2, B:36:0x00c2, B:38:0x00cc, B:38:0x00cc, B:39:0x00d3, B:39:0x00d3, B:41:0x00d9, B:41:0x00d9, B:42:0x00dc, B:42:0x00dc, B:44:0x00e7, B:44:0x00e7, B:46:0x00ec, B:46:0x00ec, B:45:0x00ea, B:45:0x00ea, B:22:0x007c, B:22:0x007c, B:12:0x004f, B:12:0x004f), top: B:51:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String a(Context context, String str) {
        int round;
        Bitmap decodeFile;
        ByteArrayOutputStream byteArrayOutputStream;
        int i2;
        File file;
        try {
            try {
                BitmapFactory.Options options = new BitmapFactory.Options();
                int i3 = 1;
                options.inJustDecodeBounds = true;
                options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                BitmapFactory.decodeFile(str, options);
                StringBuilder sb = new StringBuilder();
                sb.append(n.b);
                sb.append("/photo/mantoMsg.tmp.");
                sb.append(System.currentTimeMillis());
                sb.append(b(options) ? ".jpg" : ".png");
                String sb2 = sb.toString();
                int i4 = options.outWidth;
                int i5 = options.outHeight;
                int dMWidthPixels = MantoDensityUtils.getDMWidthPixels() / 2;
                int dMHeightPixels = MantoDensityUtils.getDMHeightPixels() / 2;
                if (i5 <= dMHeightPixels && i4 <= dMWidthPixels) {
                    round = 1;
                    if (round <= 0) {
                        i3 = round;
                    }
                    options.inSampleSize = i3;
                    options.inJustDecodeBounds = false;
                    decodeFile = BitmapFactory.decodeFile(str, options);
                    byteArrayOutputStream = new ByteArrayOutputStream();
                    decodeFile.compress(!b(options) ? Bitmap.CompressFormat.JPEG : Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                    i2 = 100;
                    while (byteArrayOutputStream.toByteArray().length / 1024 > 512 && i2 - 10 > 0) {
                        byteArrayOutputStream.reset();
                        decodeFile.compress(!b(options) ? Bitmap.CompressFormat.JPEG : Bitmap.CompressFormat.PNG, i2, byteArrayOutputStream);
                    }
                    Bitmap decodeStream = BitmapFactory.decodeStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()), null, null);
                    decodeFile.recycle();
                    file = new File(sb2);
                    if (file.getParentFile() != null && !file.getParentFile().exists()) {
                        file.getParentFile().mkdirs();
                    }
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    decodeStream.compress(!b(options) ? Bitmap.CompressFormat.JPEG : Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    decodeStream.recycle();
                    s.c(str);
                    return sb2;
                }
                round = Math.round(i5 / dMHeightPixels);
                int round2 = Math.round(i4 / dMWidthPixels);
                if (round >= round2) {
                    round = round2;
                }
                if (round <= 0) {
                }
                options.inSampleSize = i3;
                options.inJustDecodeBounds = false;
                decodeFile = BitmapFactory.decodeFile(str, options);
                byteArrayOutputStream = new ByteArrayOutputStream();
                decodeFile.compress(!b(options) ? Bitmap.CompressFormat.JPEG : Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                i2 = 100;
                while (byteArrayOutputStream.toByteArray().length / 1024 > 512) {
                    byteArrayOutputStream.reset();
                    decodeFile.compress(!b(options) ? Bitmap.CompressFormat.JPEG : Bitmap.CompressFormat.PNG, i2, byteArrayOutputStream);
                }
                Bitmap decodeStream2 = BitmapFactory.decodeStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()), null, null);
                decodeFile.recycle();
                file = new File(sb2);
                if (file.getParentFile() != null) {
                    file.getParentFile().mkdirs();
                }
                if (!file.exists()) {
                }
                FileOutputStream fileOutputStream2 = new FileOutputStream(file);
                decodeStream2.compress(!b(options) ? Bitmap.CompressFormat.JPEG : Bitmap.CompressFormat.PNG, 100, fileOutputStream2);
                fileOutputStream2.flush();
                fileOutputStream2.close();
                decodeStream2.recycle();
                s.c(str);
                return sb2;
            } catch (OutOfMemoryError unused) {
                MantoLog.e("PhotoUtil", "doCompressImage: OutOfMemoryError ");
                return null;
            }
        } catch (NullPointerException | Exception unused2) {
            return null;
        }
    }

    public static String a(BitmapFactory.Options options) {
        String str = options.outMimeType;
        MantoLog.d("PhotoUtil", "mimetype: " + str);
        if (str == null) {
            return "unknown";
        }
        String lowerCase = str.toLowerCase();
        return (lowerCase.indexOf("jpg") < 0 && lowerCase.indexOf("jpeg") < 0) ? lowerCase.indexOf("png") >= 0 ? "png" : lowerCase.indexOf(WareBusinessMagicHeadPicInfoEntity.IMAGE_TYPE_GIF) >= 0 ? WareBusinessMagicHeadPicInfoEntity.IMAGE_TYPE_GIF : "unknown" : "jpeg";
    }

    public static boolean a(Activity activity, String str, int i2) {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        File file = new File(str);
        if (!file.getParentFile().exists()) {
            try {
                file.getParentFile().mkdirs();
            } catch (Throwable th) {
                MantoLog.e("PhotoUtil", "" + th);
            }
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        SharedPreferences.Editor edit = activity.getSharedPreferences("system_config_prefs", 0).edit();
        edit.putString("camera_file_path", str);
        edit.commit();
        if (file.exists()) {
            try {
                Uri fromFile = Uri.fromFile(new File(str));
                if (Build.VERSION.SDK_INT >= 24) {
                    String providerAuthorities = MantoUtils.getProviderAuthorities(MantoFileProvider.class);
                    if (TextUtils.isEmpty(providerAuthorities)) {
                        throw new Exception("no available Provider");
                    }
                    fromFile = FileProvider.getUriForFile(activity.getApplicationContext(), providerAuthorities, new File(str));
                }
                intent.putExtra("output", fromFile);
                activity.startActivityForResult(intent, i2);
                return true;
            } catch (Exception e3) {
                MantoLog.e("PhotoUtil", "" + e3);
            }
        }
        return false;
    }

    public static int b(String str) {
        if (r.a(str)) {
            return 1;
        }
        MantoLog.e("PhotoUtil", "getExifOriention err ");
        return 0;
    }

    /* JADX WARN: Type inference failed for: r2v3, types: [boolean] */
    public static String b(Context context, String str) {
        FileInputStream fileInputStream;
        String sb;
        InputStream inputStream = null;
        if (MantoStringUtils.isEmpty(str)) {
            return null;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        ?? startsWith = str.startsWith("content://");
        String str2 = ".jpg";
        try {
            if (startsWith == 0) {
                if (str.startsWith("file:")) {
                    return a.a(context, str);
                }
                MantoLog.d("PhotoUtil", "selected:" + str);
                try {
                    BitmapFactory.decodeFile(str, options);
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(n.b);
                    sb2.append("/photo/mantoMsg.tmp.");
                    sb2.append(System.currentTimeMillis());
                    if (!b(options)) {
                        str2 = ".png";
                    }
                    sb2.append(str2);
                    String sb3 = sb2.toString();
                    if (s.a(str, sb3)) {
                        return c(context, sb3);
                    }
                    return null;
                } catch (Exception e2) {
                    MantoLog.e("PhotoUtil", "file path copy image file failed.", e2);
                    return null;
                }
            }
            try {
                FileDescriptor fileDescriptor = context.getContentResolver().openFileDescriptor(Uri.parse(str), "r").getFileDescriptor();
                BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);
                StringBuilder sb4 = new StringBuilder();
                sb4.append(n.b);
                sb4.append("/photo/mantoMsg.tmp.");
                sb4.append(System.currentTimeMillis());
                if (!b(options)) {
                    str2 = ".png";
                }
                sb4.append(str2);
                sb = sb4.toString();
                fileInputStream = new FileInputStream(fileDescriptor);
            } catch (Exception e3) {
                e = e3;
                fileInputStream = null;
            } catch (Throwable th) {
                th = th;
                t.a(inputStream);
                throw th;
            }
            try {
                if (!s.a(fileInputStream, sb)) {
                    t.a(fileInputStream);
                    return sb;
                }
                String c2 = c(context, sb);
                t.a(fileInputStream);
                return c2;
            } catch (Exception e4) {
                e = e4;
                MantoLog.e("PhotoUtil", "content:// copy image file failed.", e);
                t.a(fileInputStream);
                return null;
            }
        } catch (Throwable th2) {
            th = th2;
            inputStream = startsWith;
        }
    }

    public static boolean b(BitmapFactory.Options options) {
        String str;
        if (options == null || (str = options.outMimeType) == null) {
            return false;
        }
        String lowerCase = str.toLowerCase();
        return lowerCase.indexOf("jpg") >= 0 || lowerCase.indexOf("jpeg") >= 0;
    }

    public static String c(Context context, String str) {
        int a = a(str);
        if (a == 0) {
            return str;
        }
        FileOutputStream fileOutputStream = null;
        int i2 = a % R2.attr.additionBottom;
        try {
            Bitmap decodeFile = BitmapFactory.decodeFile(str, new BitmapFactory.Options());
            if (decodeFile == null) {
                MantoLog.e("PhotoUtil", "rotate image, get null bmp");
                return str;
            }
            Matrix matrix = new Matrix();
            matrix.reset();
            matrix.setRotate(i2);
            Bitmap createBitmap = Bitmap.createBitmap(decodeFile, 0, 0, decodeFile.getWidth(), decodeFile.getHeight(), matrix, true);
            StringBuilder sb = new StringBuilder();
            sb.append(n.b);
            sb.append("/photo/mantoMsg.");
            sb.append(System.currentTimeMillis());
            sb.append(createBitmap.hasAlpha() ? ".png" : ".jpg");
            String sb2 = sb.toString();
            File file = new File(sb2);
            if (file.getParentFile() != null && file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            FileOutputStream fileOutputStream2 = new FileOutputStream(file);
            try {
                createBitmap.compress(createBitmap.hasAlpha() ? Bitmap.CompressFormat.PNG : Bitmap.CompressFormat.JPEG, 80, fileOutputStream2);
                createBitmap.recycle();
                decodeFile.recycle();
                s.c(str);
                try {
                    fileOutputStream2.close();
                    return sb2;
                } catch (IOException unused) {
                    return str;
                }
            } catch (IOException unused2) {
                fileOutputStream = fileOutputStream2;
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException unused3) {
                    }
                }
                return str;
            } catch (NullPointerException unused4) {
                fileOutputStream = fileOutputStream2;
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException unused5) {
                    }
                }
                return str;
            } catch (OutOfMemoryError unused6) {
                fileOutputStream = fileOutputStream2;
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException unused7) {
                    }
                }
                return str;
            } catch (Throwable th) {
                th = th;
                fileOutputStream = fileOutputStream2;
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException unused8) {
                        return str;
                    }
                }
                throw th;
            }
        } catch (IOException unused9) {
        } catch (NullPointerException unused10) {
        } catch (OutOfMemoryError unused11) {
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
