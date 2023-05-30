package com.sina.weibo.sdk.share;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import com.jingdong.sdk.platform.business.personal.R2;
import com.sina.weibo.sdk.b.a;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Calendar;

/* loaded from: classes9.dex */
public final class a {
    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:104:0x013a A[EDGE_INSN: B:104:0x013a->B:51:0x013a BREAK  A[LOOP:0: B:48:0x012f->B:50:0x0136], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00c8 A[Catch: all -> 0x015d, Exception -> 0x0160, TryCatch #11 {Exception -> 0x0160, all -> 0x015d, blocks: (B:3:0x0005, B:5:0x000d, B:6:0x000f, B:9:0x0017, B:24:0x009a, B:34:0x00c2, B:36:0x00c8, B:40:0x00df, B:41:0x00e6, B:32:0x00be, B:69:0x0159, B:70:0x015c), top: B:102:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0123 A[Catch: all -> 0x014e, Exception -> 0x0152, TryCatch #12 {Exception -> 0x0152, all -> 0x014e, blocks: (B:42:0x00fe, B:44:0x0123, B:45:0x0126), top: B:100:0x00fe }] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0136 A[Catch: all -> 0x014a, Exception -> 0x014c, LOOP:0: B:48:0x012f->B:50:0x0136, LOOP_END, TryCatch #13 {Exception -> 0x014c, all -> 0x014a, blocks: (B:47:0x012d, B:48:0x012f, B:50:0x0136, B:51:0x013a), top: B:98:0x012d }] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0159 A[Catch: all -> 0x015d, Exception -> 0x0160, TRY_ENTER, TryCatch #11 {Exception -> 0x0160, all -> 0x015d, blocks: (B:3:0x0005, B:5:0x000d, B:6:0x000f, B:9:0x0017, B:24:0x009a, B:34:0x00c2, B:36:0x00c8, B:40:0x00df, B:41:0x00e6, B:32:0x00be, B:69:0x0159, B:70:0x015c), top: B:102:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0188 A[Catch: Exception -> 0x0184, TRY_LEAVE, TryCatch #8 {Exception -> 0x0184, blocks: (B:79:0x0180, B:83:0x0188), top: B:94:0x0180 }] */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0180 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String a(Context context, Uri uri, int i2) {
        FileOutputStream fileOutputStream;
        Cursor cursor;
        String str;
        BufferedInputStream bufferedInputStream;
        File file;
        byte[] bArr;
        int read;
        BufferedInputStream bufferedInputStream2 = null;
        try {
            a.C0775a e2 = com.sina.weibo.sdk.b.a.e(context);
            String str2 = e2 != null ? e2.packageName : "";
            if (TextUtils.isEmpty(str2)) {
                str2 = "com.sina.weibo";
            }
            String str3 = "/Android/data/" + str2 + "/files/.composerTem/";
            new File(Environment.getExternalStorageDirectory().getAbsolutePath() + str3).mkdirs();
            Calendar calendar = Calendar.getInstance();
            try {
                if ("file".equals(uri.getScheme())) {
                    str = calendar.getTimeInMillis() + uri.getLastPathSegment();
                    cursor = null;
                } else {
                    cursor = context.getContentResolver().query(uri, new String[]{"_display_name"}, null, null, null);
                    if (cursor != null) {
                        try {
                            try {
                                if (cursor.moveToFirst()) {
                                    str = cursor.getString(cursor.getColumnIndex("_display_name"));
                                }
                            } catch (Throwable th) {
                                th = th;
                                if (cursor != null) {
                                    cursor.close();
                                }
                                throw th;
                            }
                        } catch (Exception e3) {
                            e = e3;
                            com.sina.weibo.sdk.b.c.b("WBShareTag", "share util and exception is " + e.getMessage());
                            e.printStackTrace();
                            if (cursor != null) {
                                cursor.close();
                            }
                            str = null;
                            if (TextUtils.isEmpty(str)) {
                            }
                            bufferedInputStream = new BufferedInputStream(new FileInputStream(context.getContentResolver().openFileDescriptor(uri, "r").getFileDescriptor()));
                            file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + str3 + str);
                            if (file.exists()) {
                            }
                            fileOutputStream = new FileOutputStream(file);
                            bArr = new byte[R2.attr.motionPath];
                            while (true) {
                                read = bufferedInputStream.read(bArr);
                                if (read != -1) {
                                }
                                fileOutputStream.write(bArr, 0, read);
                            }
                            String path = file.getPath();
                            bufferedInputStream.close();
                            fileOutputStream.close();
                            return path;
                        }
                    }
                    str = null;
                }
                if (cursor != null) {
                    cursor.close();
                }
            } catch (Exception e4) {
                e = e4;
                cursor = null;
            } catch (Throwable th2) {
                th = th2;
                cursor = null;
                if (cursor != null) {
                }
                throw th;
            }
            if (TextUtils.isEmpty(str)) {
                StringBuilder sb = new StringBuilder();
                sb.append(Calendar.getInstance().getTimeInMillis());
                sb.append(i2 == 0 ? "_sdk_temp.mp4" : "_sdk_temp.jpg");
                str = sb.toString();
            }
            bufferedInputStream = new BufferedInputStream(new FileInputStream(context.getContentResolver().openFileDescriptor(uri, "r").getFileDescriptor()));
            try {
                file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + str3 + str);
                if (file.exists()) {
                    file.delete();
                }
                fileOutputStream = new FileOutputStream(file);
            } catch (Exception e5) {
                e = e5;
                fileOutputStream = null;
            } catch (Throwable th3) {
                th = th3;
                fileOutputStream = null;
            }
        } catch (Exception e6) {
            e = e6;
            fileOutputStream = null;
        } catch (Throwable th4) {
            th = th4;
            fileOutputStream = null;
        }
        try {
            bArr = new byte[R2.attr.motionPath];
            while (true) {
                read = bufferedInputStream.read(bArr);
                if (read != -1) {
                    break;
                }
                fileOutputStream.write(bArr, 0, read);
            }
            String path2 = file.getPath();
            try {
                bufferedInputStream.close();
                fileOutputStream.close();
            } catch (Exception e7) {
                e7.printStackTrace();
            }
            return path2;
        } catch (Exception e8) {
            e = e8;
            bufferedInputStream2 = bufferedInputStream;
            try {
                com.sina.weibo.sdk.b.c.b("WBShareTag", "share util and error is " + e.getMessage());
                throw new Throwable(e);
            } catch (Throwable th5) {
                th = th5;
                if (bufferedInputStream2 != null) {
                    try {
                        bufferedInputStream2.close();
                    } catch (Exception e9) {
                        e9.printStackTrace();
                        throw th;
                    }
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                throw th;
            }
        } catch (Throwable th6) {
            th = th6;
            bufferedInputStream2 = bufferedInputStream;
            if (bufferedInputStream2 != null) {
            }
            if (fileOutputStream != null) {
            }
            throw th;
        }
    }
}
