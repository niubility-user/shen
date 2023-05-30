package com.jdjr.risk.device.c;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes18.dex */
public class ad {
    private static final String[] a = {"/system/app/Superuser.apk", "/su/bin/su", "/system/bin/su", "/system/xbin/su", "/system/sbin/su", "/sbin/su", "/vendor/bin/su", "/data/local/xbin/su", "/data/local/bin/su", "/system/sd/xbin/su", "/system/bin/failsafe/su", "/data/local/su"};
    private static final String[] b = {"com.noshufou.android.su", "com.noshufou.android.su.elite", "eu.chainfire.supersu", "com.koushikdutta.superuser", "com.thirdparty.superuser", "com.yellowes.su"};

    /* renamed from: c  reason: collision with root package name */
    private static final String[] f7343c = {"com.koushikdutta.rommanager", "com.dimonvideo.luckypatcher", "com.chelpus.lackypatch", "com.ramdroid.appquarantine"};
    private static final String[] d = {"com.devadvance.rootcloak", "de.robv.android.xposed.installer", "com.saurik.substrate", "com.devadvance.rootcloakplus", "com.zachspong.temprootremovejb", "com.amphoras.hidemyroot", "com.formyhm.hideroot"};

    /* renamed from: e  reason: collision with root package name */
    private static final String[] f7344e = {"/system", "/system/bin", "/system/sbin", "/system/xbin", "/vendor/bin", "/sbin", "/etc"};

    /* renamed from: f  reason: collision with root package name */
    private static final Map<String, String> f7345f = new HashMap();

    private static boolean a() {
        String a2;
        try {
            if (Build.VERSION.SDK_INT >= 28 || (a2 = j.a().a("ro.secure")) == null) {
                return false;
            }
            return "0".equals(a2);
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean a(Context context) {
        return a() || b() || b(context) || c() || d();
    }

    private static String[] a(String str) {
        try {
            String a2 = j.a(str, true);
            if (TextUtils.isEmpty(a2)) {
                return null;
            }
            return a2.split(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        } catch (Exception unused) {
            return null;
        }
    }

    private static boolean b() {
        try {
            String[] a2 = a("mount");
            if (a2 == null || a2.length <= 0) {
                return false;
            }
            boolean z = false;
            for (String str : a2) {
                try {
                    String[] split = str.split(LangUtils.SINGLE_SPACE);
                    if (split.length >= 6) {
                        String str2 = split[2];
                        String str3 = split[5];
                        String[] strArr = f7344e;
                        int length = strArr.length;
                        int i2 = 0;
                        while (true) {
                            if (i2 >= length) {
                                break;
                            }
                            if (str2.equalsIgnoreCase(strArr[i2]) && str3.startsWith("(rw")) {
                                z = true;
                                break;
                            }
                            i2++;
                        }
                        if (z) {
                            break;
                        }
                    }
                } catch (Exception unused) {
                }
            }
            return z;
        } catch (Exception unused2) {
            return false;
        }
    }

    private static boolean b(Context context) {
        try {
            if (BaseInfo.isAgreedPrivacy()) {
                ArrayList arrayList = new ArrayList();
                arrayList.addAll(Arrays.asList(b));
                arrayList.addAll(Arrays.asList(f7343c));
                arrayList.addAll(Arrays.asList(d));
                PackageManager packageManager = context.getPackageManager();
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    try {
                        packageManager.getPackageInfo((String) it.next(), 0);
                        return true;
                    } catch (PackageManager.NameNotFoundException unused) {
                    }
                }
                return false;
            }
            return false;
        } catch (Exception unused2) {
            return false;
        }
    }

    private static boolean c() {
        String[] a2;
        Map<String, String> map = f7345f;
        map.put("[ro.debuggable]", "[1]");
        map.put("[ro.secure]", HttpError.VERIFY_STATUS_SUCCESS);
        try {
            if (BaseInfo.isAgreedPrivacy() && BaseInfo.isAppForeground() && (a2 = a("getprop")) != null) {
                boolean z = false;
                for (String str : a2) {
                    try {
                        Iterator<String> it = f7345f.keySet().iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            String next = it.next();
                            if (str.contains(next) && str.contains(f7345f.get(next))) {
                                z = true;
                                break;
                            }
                        }
                        if (z) {
                            break;
                        }
                    } catch (Exception unused) {
                    }
                }
                return z;
            }
            return false;
        } catch (Exception unused2) {
            return false;
        }
    }

    private static boolean d() {
        try {
            for (String str : a) {
                if (new File(str).exists()) {
                    return true;
                }
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }
}
