package com.jdcn.fido.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes18.dex */
public class RootUtil {
    private static final String[] SU_PATHS = {"/system/app/Superuser.apk", "/su/bin/su", "/system/bin/su", "/system/xbin/su", "/system/sbin/su", "/sbin/su", "/vendor/bin/su", "/data/local/xbin/su", "/data/local/bin/su", "/system/sd/xbin/su", "/system/bin/failsafe/su", "/data/local/su"};
    private static final String[] KNOWN_ROOT_APPS_PACKAGES = {"com.noshufou.android.su", "com.noshufou.android.su.elite", "eu.chainfire.supersu", "com.koushikdutta.superuser", "com.thirdparty.superuser", "com.yellowes.su"};
    private static final String[] KNOWN_DANGEROUS_APPS_PACKAGES = {"com.koushikdutta.rommanager", "com.dimonvideo.luckypatcher", "com.chelpus.lackypatch", "com.ramdroid.appquarantine"};
    private static final String[] KNOWN_ROOT_CLOAKING_PACKAGES = {"com.devadvance.rootcloak", "de.robv.android.xposed.installer", "com.saurik.substrate", "com.devadvance.rootcloakplus", "com.zachspong.temprootremovejb", "com.amphoras.hidemyroot", "com.formyhm.hideroot"};
    private static final String[] PATHS_THAT_SHOULD_NOT_BE_WRITABLE = {"/system", "/system/bin", "/system/sbin", "/system/xbin", "/vendor/bin", "/sbin", "/etc"};
    private static final Map<String, String> DANGEROUS_PROPERTIES = new HashMap();

    private static boolean existingDangerousProperties() {
        Map<String, String> map = DANGEROUS_PROPERTIES;
        map.put("[ro.debuggable]", "[1]");
        map.put("[ro.secure]", HttpError.VERIFY_STATUS_SUCCESS);
        try {
            String[] mountReader = mountReader("getprop");
            if (mountReader != null) {
                boolean z = false;
                for (String str : mountReader) {
                    try {
                        Iterator<String> it = DANGEROUS_PROPERTIES.keySet().iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            String next = it.next();
                            if (str.contains(next) && str.contains(DANGEROUS_PROPERTIES.get(next))) {
                                z = true;
                                break;
                            }
                        }
                        if (z) {
                            break;
                        }
                    } catch (Throwable unused) {
                    }
                }
                return z;
            }
            return false;
        } catch (Throwable unused2) {
            return false;
        }
    }

    private static boolean existingRWPaths() {
        try {
            String[] mountReader = mountReader("mount");
            if (mountReader == null || mountReader.length <= 0) {
                return false;
            }
            boolean z = false;
            for (String str : mountReader) {
                try {
                    String[] split = str.split(LangUtils.SINGLE_SPACE);
                    if (split.length >= 6) {
                        String str2 = split[2];
                        String str3 = split[5];
                        String[] strArr = PATHS_THAT_SHOULD_NOT_BE_WRITABLE;
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
                } catch (Throwable unused) {
                }
            }
            return z;
        } catch (Throwable unused2) {
            return false;
        }
    }

    private static boolean existingRootFiles() {
        try {
            for (String str : SU_PATHS) {
                if (new File(str).exists()) {
                    return true;
                }
            }
            return false;
        } catch (Throwable unused) {
            return false;
        }
    }

    private static boolean existingRootPackages(Context context) {
        try {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(Arrays.asList(KNOWN_ROOT_APPS_PACKAGES));
            arrayList.addAll(Arrays.asList(KNOWN_DANGEROUS_APPS_PACKAGES));
            arrayList.addAll(Arrays.asList(KNOWN_ROOT_CLOAKING_PACKAGES));
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
        } catch (Throwable unused2) {
            return false;
        }
    }

    private static boolean getroSecureProp() {
        String property;
        try {
            if (Build.VERSION.SDK_INT >= 28 || (property = CommandUtil.getSingleInstance().getProperty("ro.secure")) == null) {
                return false;
            }
            return "0".equals(property);
        } catch (Throwable unused) {
            return false;
        }
    }

    public static boolean isRootSystem(Context context) {
        return getroSecureProp() || existingRWPaths() || existingRootPackages(context) || existingDangerousProperties() || existingRootFiles();
    }

    private static String[] mountReader(String str) {
        try {
            String handlerCommand = CommandUtil.handlerCommand(str, true);
            if (TextUtils.isEmpty(handlerCommand)) {
                return null;
            }
            return handlerCommand.split(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        } catch (Throwable unused) {
            return null;
        }
    }
}
