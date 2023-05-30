package com.jdjr.risk.device.c;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.jingdong.common.utils.LangUtils;
import java.io.File;
import java.util.Locale;

/* loaded from: classes18.dex */
public class aj {
    private static String[] a = {"com.bly.dkplat", "com.lbe.parallel", "com.excelliance.dualaid", "com.lody.virtual", "com.qihoo.magic"};

    /* JADX WARN: Removed duplicated region for block: B:27:0x006e A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:38:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static boolean a() {
        int i2;
        String b;
        String[] split;
        try {
            b = b();
        } catch (Exception unused) {
        }
        if (!TextUtils.isEmpty(b)) {
            String a2 = j.a("ps", true);
            if (!TextUtils.isEmpty(a2) && (split = a2.split(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE)) != null) {
                if (split.length > 0) {
                    i2 = 0;
                    for (int i3 = 0; i3 < split.length; i3++) {
                        try {
                            if (split[i3].contains(b)) {
                                int lastIndexOf = split[i3].lastIndexOf(LangUtils.SINGLE_SPACE);
                                if (new File(String.format("/data/data/%s", split[i3].substring(lastIndexOf <= 0 ? 0 : lastIndexOf + 1, split[i3].length()), Locale.CHINA)).exists()) {
                                    i2++;
                                }
                            }
                        } catch (Exception unused2) {
                        }
                    }
                    return i2 <= 1;
                }
            }
        }
        i2 = 0;
        if (i2 <= 1) {
        }
    }

    public static boolean a(Context context) {
        if (context != null) {
            boolean b = b(context);
            if (b) {
                return b;
            }
            if (Build.VERSION.SDK_INT >= 24) {
                b = a(context.getDataDir().getAbsolutePath());
            }
            if (b) {
                return b;
            }
            boolean d = d(context);
            if (d) {
                return d;
            }
            boolean c2 = c(context);
            return !c2 ? a() : c2;
        }
        return false;
    }

    private static boolean a(String str) {
        return new File(str + File.separator + "..").canRead();
    }

    private static String b() {
        String str = "";
        try {
            String b = j.b("cat /proc/self/cgroup");
            try {
                if (TextUtils.isEmpty(b)) {
                    return b;
                }
                int lastIndexOf = b.lastIndexOf("uid");
                int lastIndexOf2 = b.lastIndexOf("/pid");
                if (lastIndexOf >= 0) {
                    if (lastIndexOf2 <= 0) {
                        lastIndexOf2 = b.length();
                    }
                    String substring = b.substring(lastIndexOf + 4, lastIndexOf2);
                    return b(substring.replaceAll(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE, "")) ? String.format("u0_a%d", Integer.valueOf(Integer.valueOf(r0).intValue() - 10000)) : substring;
                }
                return b;
            } catch (Exception unused) {
                str = b;
                return str;
            }
        } catch (Exception unused2) {
        }
    }

    private static boolean b(Context context) {
        File filesDir = context.getFilesDir();
        if (filesDir != null) {
            String path = filesDir.getPath();
            for (String str : a) {
                if (path.contains(str)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean b(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        for (int i2 = 0; i2 < str.length(); i2++) {
            if (!Character.isDigit(str.charAt(i2))) {
                return false;
            }
        }
        return true;
    }

    private static boolean c(Context context) {
        return false;
    }

    private static boolean d(Context context) {
        return context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).uid / 100000 != 0;
    }
}
