package com.jingdong.common.messagecenter;

import android.text.TextUtils;
import com.jingdong.manto.sdk.api.IMantoServerRequester;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.oklog.OKLog;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Pattern;

/* loaded from: classes5.dex */
public class RomUtil {
    private static final String KEY_EMUI_VERSION_CODE = "ro.build.version.emui";
    private static final String KEY_MAGICUI_VERSION = "ro.build.version.magic";
    private static final String KEY_MIUI_INTERNAL_STORAGE = "ro.miui.internal.storage";
    private static final String KEY_MIUI_VERSION_CODE = "ro.miui.ui.version.code";
    private static final String KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name";
    private static final String KEY_OPPO_VERSION = "ro.build.version.opporom";
    private static final String KEY_VERSION_VIVO = "ro.vivo.os.version";

    public static int getDevice() {
        if (isMagicUI()) {
            return 12;
        }
        if (isEMUI()) {
            return 2;
        }
        if (isMIUI()) {
            return 1;
        }
        if (isFlyme()) {
            return 3;
        }
        return isOPPOOs() ? 6 : 0;
    }

    private static String getEmuiVersion() {
        Class<?>[] clsArr = {String.class};
        Object[] objArr = {KEY_EMUI_VERSION_CODE};
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            String str = (String) cls.getDeclaredMethod(IMantoServerRequester.GET, clsArr).invoke(cls, objArr);
            OKLog.d("RomUtil", "get EMUI version is:" + str);
            return !TextUtils.isEmpty(str) ? str : "";
        } catch (ClassNotFoundException unused) {
            OKLog.e("RomUtil", " getEmuiVersion wrong, ClassNotFoundException");
            return "";
        } catch (LinkageError unused2) {
            OKLog.e("RomUtil", " getEmuiVersion wrong, LinkageError");
            return "";
        } catch (NoSuchMethodException unused3) {
            OKLog.e("RomUtil", " getEmuiVersion wrong, NoSuchMethodException");
            return "";
        } catch (NullPointerException unused4) {
            OKLog.e("RomUtil", " getEmuiVersion wrong, NullPointerException");
            return "";
        } catch (Exception unused5) {
            OKLog.e("RomUtil", " getEmuiVersion wrong");
            return "";
        }
    }

    /* JADX WARN: Not initialized variable reg: 2, insn: 0x0059: MOVE (r0 I:??[OBJECT, ARRAY]) = (r2 I:??[OBJECT, ARRAY]), block:B:99:0x0059 */
    /* JADX WARN: Removed duplicated region for block: B:112:0x005c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static String getProp(String str) {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2;
        BufferedReader bufferedReader3 = null;
        try {
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("getprop " + str).getInputStream()), 1024);
            } catch (IOException unused) {
                bufferedReader = null;
            } catch (Throwable th) {
                th = th;
                if (bufferedReader3 != null) {
                }
                throw th;
            }
            try {
                String readLine = bufferedReader.readLine();
                bufferedReader.close();
                try {
                    bufferedReader.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
                return readLine;
            } catch (IOException unused2) {
                String str2 = "Unable to read prop " + str;
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
                return null;
            }
        } catch (Throwable th2) {
            th = th2;
            bufferedReader3 = bufferedReader2;
            if (bufferedReader3 != null) {
                try {
                    bufferedReader3.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
            }
            throw th;
        }
    }

    private static String getProperty(String str) {
        try {
            try {
                return getSystemProperty(str, "");
            } catch (Exception e2) {
                e2.printStackTrace();
                return null;
            }
        } catch (Throwable unused) {
            return null;
        }
    }

    private static String getSystemProperty(String str, String str2) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod(IMantoServerRequester.GET, String.class, String.class).invoke(cls, str, str2);
        } catch (Exception unused) {
            return null;
        }
    }

    public static boolean isEMUI() {
        return !TextUtils.isEmpty(getEmuiVersion());
    }

    public static boolean isFlyme() {
        try {
            if ("meizu".equalsIgnoreCase(BaseInfo.getDeviceBrand())) {
                String oSName = BaseInfo.getOSName();
                if (TextUtils.isEmpty(oSName)) {
                    return true;
                }
                ArrayList arrayList = new ArrayList();
                String replaceAll = Pattern.compile("[^0-9]").matcher(oSName).replaceAll("");
                int i2 = 0;
                while (i2 < replaceAll.length()) {
                    int i3 = i2 + 1;
                    arrayList.add(replaceAll.substring(i2, i3));
                    i2 = i3;
                }
                OKLog.d("rom", oSName + "---" + ((String) arrayList.get(0)));
                return Integer.valueOf((String) arrayList.get(0)).intValue() > 3;
            }
        } catch (Exception unused) {
        }
        return false;
    }

    public static boolean isMIUI() {
        try {
            if (TextUtils.isEmpty(getProperty(KEY_MIUI_VERSION_CODE))) {
                if (TextUtils.isEmpty(getProperty(KEY_MIUI_VERSION_NAME))) {
                    return false;
                }
            }
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean isMagicUI() {
        try {
            return !TextUtils.isEmpty(getProperty(KEY_MAGICUI_VERSION));
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean isMeizuFlymeOS() {
        String oSName = BaseInfo.getOSName();
        if (TextUtils.isEmpty(oSName)) {
            return false;
        }
        return oSName.contains("flyme") || oSName.toLowerCase().contains("flyme");
    }

    public static boolean isOPPOOs() {
        try {
            if ("OPPO".equalsIgnoreCase(BaseInfo.getDeviceBrand())) {
                String prop = getProp(KEY_OPPO_VERSION);
                if (TextUtils.isEmpty(prop)) {
                    return false;
                }
                ArrayList arrayList = new ArrayList();
                String replaceAll = Pattern.compile("[^0-9]").matcher(prop).replaceAll("");
                int i2 = 0;
                while (i2 < replaceAll.length()) {
                    int i3 = i2 + 1;
                    arrayList.add(replaceAll.substring(i2, i3));
                    i2 = i3;
                }
                StringBuilder sb = new StringBuilder();
                sb.append((String) arrayList.get(0));
                sb.append((String) arrayList.get(1));
                return Integer.valueOf(sb.toString()).intValue() >= 31;
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean isVIVO() {
        try {
            if (TextUtils.isEmpty(getProperty(KEY_VERSION_VIVO))) {
                if (TextUtils.isEmpty(getProperty(KEY_VERSION_VIVO))) {
                    return false;
                }
            }
            return true;
        } catch (Exception unused) {
            return false;
        }
    }
}
