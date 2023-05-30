package com.jingdong.manto.utils;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Pair;
import android.webkit.MimeTypeMap;
import android.widget.Toast;
import com.jdpay.net.http.HTTP;
import com.jingdong.manto.R;
import java.io.File;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;
import tv.danmaku.ijk.media.JDPlayerConstant;

/* loaded from: classes16.dex */
public class z {
    private static Map<String, String> a;
    private static Map<String, String> b;

    /* loaded from: classes16.dex */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            Toast.makeText(com.jingdong.manto.c.a(), R.string.manto_file_save_into, 0).show();
        }
    }

    static {
        Pattern.compile("data:(image|img)/\\S+;base64,\\S+");
        ArrayList arrayList = new ArrayList(3);
        arrayList.add(new Pair("silk", "audio/silk"));
        arrayList.add(new Pair("jpg", "image/jpg"));
        arrayList.add(new Pair("json", HTTP.CONTENT_TYPE_JSON));
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Pair pair = (Pair) it.next();
            hashMap.put(pair.first, pair.second);
            hashMap2.put(pair.second, pair.first);
        }
        a = Collections.unmodifiableMap(hashMap);
        b = Collections.unmodifiableMap(hashMap2);
    }

    public static ContentValues a(Context context, File file, long j2, boolean z) {
        ContentValues contentValues = new ContentValues();
        if (z) {
            contentValues.put("title", file.getName());
            contentValues.put("_display_name", file.getName());
            contentValues.put("mime_type", d(file.getPath()));
            contentValues.put("relative_path", Environment.DIRECTORY_DCIM + File.separator + context.getPackageName());
        } else {
            contentValues.put("relative_path", Environment.DIRECTORY_DCIM + File.separator + context.getPackageName());
            contentValues.put("title", file.getName());
            contentValues.put("_display_name", file.getName());
            contentValues.put("mime_type", JDPlayerConstant.IJK_CACHE_SUPPORT_TYPE);
        }
        contentValues.put("datetaken", Long.valueOf(j2));
        contentValues.put("date_modified", Long.valueOf(j2));
        contentValues.put("date_added", Long.valueOf(j2));
        contentValues.put("_size", Long.valueOf(file.length()));
        return contentValues;
    }

    public static String a(Context context, String str) {
        String str2;
        if (str == null) {
            str2 = " JdMessenger/";
        } else {
            str2 = str + " Jdapp/";
        }
        return (str2 + MantoUtils.getAppVersionName(context)) + ";NetType/" + MantoUtils.getNetworkType(context);
    }

    public static String a(String str) {
        int lastIndexOf;
        if (!MantoStringUtils.isEmpty(str) && (lastIndexOf = str.lastIndexOf(46)) >= 0 && lastIndexOf < str.length() - 1) {
            return str.substring(lastIndexOf + 1);
        }
        return null;
    }

    public static void a(Context context, ContentResolver contentResolver, File file, Uri uri) {
        try {
            if (Build.VERSION.SDK_INT < 29 || context.getApplicationInfo().targetSdkVersion < 29) {
                return;
            }
            OutputStream openOutputStream = contentResolver.openOutputStream(uri);
            Files.copy(file.toPath(), openOutputStream);
            openOutputStream.close();
        } catch (Exception e2) {
            MantoLog.e(e2.getMessage(), new Object[0]);
        }
    }

    public static boolean a(String str, String str2) {
        return str != null && str2 != null && str.length() >= 0 && str2.length() >= 0 && str2.length() <= str.length() && str2.equalsIgnoreCase(str.substring(0, str2.length()));
    }

    public static boolean a(String str, boolean z) {
        try {
            ContentResolver contentResolver = com.jingdong.manto.c.a().getContentResolver();
            File file = new File(str);
            Uri insert = contentResolver.insert(z ? MediaStore.Images.Media.EXTERNAL_CONTENT_URI : MediaStore.Video.Media.EXTERNAL_CONTENT_URI, a(com.jingdong.manto.c.a(), file, System.currentTimeMillis(), z));
            a(com.jingdong.manto.c.a(), contentResolver, file, insert);
            com.jingdong.manto.c.a().sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", insert));
            com.jingdong.manto.b.d().mainThread().execute(new a());
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static boolean a(boolean z, String str) {
        return z ? !TextUtils.isEmpty(str) && str.toLowerCase().contains("image") : !TextUtils.isEmpty(str) && str.toLowerCase().contains("video");
    }

    public static String b(String str) {
        if (MantoStringUtils.isEmpty(str)) {
            return null;
        }
        String str2 = b.get(str);
        return MantoStringUtils.isEmpty(str2) ? MimeTypeMap.getSingleton().getExtensionFromMimeType(str) : str2;
    }

    public static String c(String str) {
        if (MantoStringUtils.isEmpty(str)) {
            return null;
        }
        String str2 = a.get(str.toLowerCase());
        if (MantoStringUtils.isEmpty(str2)) {
            try {
                return MimeTypeMap.getSingleton().getMimeTypeFromExtension(str);
            } catch (Throwable unused) {
                return null;
            }
        }
        return str2;
    }

    public static String d(String str) {
        return c(a(str));
    }

    public static String e(String str) {
        return o.d + String.format("%s%d.%s", "jdexp", Long.valueOf(System.currentTimeMillis()), str);
    }
}
