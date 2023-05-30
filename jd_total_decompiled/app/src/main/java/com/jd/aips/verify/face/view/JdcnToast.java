package com.jd.aips.verify.face.view;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.Toast;
import com.jd.aips.common.utils.FsBaseInfoUtils;
import com.jdpay.system.SystemInfo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;

/* loaded from: classes12.dex */
public class JdcnToast {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public static class RomUtils {
        private static String sName;
        private static String sVersion;

        RomUtils() {
        }

        static /* synthetic */ boolean access$000() {
            return is360();
        }

        static /* synthetic */ boolean access$100() {
            return isEmui();
        }

        static /* synthetic */ boolean access$200() {
            return is1Jia();
        }

        static /* synthetic */ boolean access$300() {
            return isVivo();
        }

        private static boolean check(String str) {
            String str2 = sName;
            if (str2 != null) {
                return str2.equals(str);
            }
            String prop = getProp("ro.miui.ui.version.name");
            sVersion = prop;
            if (!TextUtils.isEmpty(prop)) {
                sName = "MIUI";
            } else {
                String prop2 = getProp("ro.build.version.emui");
                sVersion = prop2;
                if (!TextUtils.isEmpty(prop2)) {
                    sName = "EMUI";
                } else {
                    String prop3 = getProp("ro.build.version.opporom");
                    sVersion = prop3;
                    if (!TextUtils.isEmpty(prop3)) {
                        sName = "OPPO";
                    } else {
                        String prop4 = getProp("ro.vivo.os.version");
                        sVersion = prop4;
                        if (!TextUtils.isEmpty(prop4)) {
                            sName = "VIVO";
                        } else {
                            String prop5 = getProp("ro.smartisan.version");
                            sVersion = prop5;
                            if (!TextUtils.isEmpty(prop5)) {
                                sName = "SMARTISAN";
                            } else {
                                String oSName = FsBaseInfoUtils.getOSName();
                                sVersion = oSName;
                                if (oSName.toUpperCase().contains("FLYME")) {
                                    sName = "FLYME";
                                } else {
                                    sVersion = "unknown";
                                    sName = FsBaseInfoUtils.getManufacture().toUpperCase();
                                }
                            }
                        }
                    }
                }
            }
            return sName.equals(str);
        }

        /* JADX WARN: Removed duplicated region for block: B:35:0x0064  */
        /* JADX WARN: Removed duplicated region for block: B:40:0x005a A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        static String getProp(String str) {
            Throwable th;
            Process process;
            BufferedReader bufferedReader;
            BufferedReader bufferedReader2 = null;
            try {
                process = Runtime.getRuntime().exec("getprop " + str);
            } catch (IOException unused) {
                process = null;
            } catch (Throwable th2) {
                th = th2;
                process = null;
            }
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()), 1024);
                try {
                    String readLine = bufferedReader.readLine();
                    try {
                        bufferedReader.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                    process.destroy();
                    return readLine;
                } catch (IOException unused2) {
                    if (bufferedReader != null) {
                    }
                    if (process != null) {
                    }
                    return null;
                } catch (Throwable th3) {
                    th = th3;
                    bufferedReader2 = bufferedReader;
                    if (bufferedReader2 != null) {
                        try {
                            bufferedReader2.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                    if (process != null) {
                        process.destroy();
                    }
                    throw th;
                }
            } catch (IOException unused3) {
                bufferedReader = null;
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                }
                if (process != null) {
                    process.destroy();
                }
                return null;
            } catch (Throwable th4) {
                th = th4;
            }
        }

        private static boolean is1Jia() {
            return FsBaseInfoUtils.getManufacture().toUpperCase().contains(SystemInfo.ROM_1JIA);
        }

        private static boolean is360() {
            return check("QIKU") || check(SystemInfo.ROM_360);
        }

        private static boolean isEmui() {
            return check("EMUI");
        }

        private static boolean isVivo() {
            return check("VIVO");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public static class ToastHandler extends Handler {
        private Handler mProxy;

        public ToastHandler(Handler handler) {
            this.mProxy = handler;
        }

        @Override // android.os.Handler
        public void dispatchMessage(Message message) {
            try {
                Handler handler = this.mProxy;
                if (handler != null) {
                    handler.dispatchMessage(message);
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    private static void fixToastBadTokenException(Toast toast) {
        Object fieldValue;
        int i2;
        int i3;
        int i4;
        if (toast != null) {
            boolean z = true;
            boolean z2 = RomUtils.access$000() && Build.VERSION.SDK_INT == 27;
            boolean z3 = RomUtils.access$100() && ((i4 = Build.VERSION.SDK_INT) == 27 || i4 == 28);
            boolean z4 = RomUtils.access$200() && ((i3 = Build.VERSION.SDK_INT) == 27 || i3 == 28);
            if (!RomUtils.access$300() || ((i2 = Build.VERSION.SDK_INT) != 27 && i2 != 28)) {
                z = false;
            }
            if ((Build.VERSION.SDK_INT < 26 || z2 || z3 || z4 || z) && (fieldValue = getFieldValue(toast, "mTN")) != null) {
                try {
                    Handler handler = (Handler) getFieldValue(fieldValue, "mHandler");
                    if (handler == null) {
                        return;
                    }
                    try {
                        setFieldValue(fieldValue, "mHandler", new ToastHandler(handler));
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                } catch (Throwable th2) {
                    th2.printStackTrace();
                }
            }
        }
    }

    private static Field getDeclaredField(Object obj, String str) {
        for (Class<?> cls = obj.getClass(); cls != Object.class; cls = cls.getSuperclass()) {
            try {
                return cls.getDeclaredField(str);
            } catch (Exception unused) {
            }
        }
        throw new RuntimeException("getDeclaredField exception, object = " + obj + ", fieldName = " + str);
    }

    private static Object getFieldValue(Object obj, String str) {
        try {
            Field declaredField = getDeclaredField(obj, str);
            declaredField.setAccessible(true);
            return declaredField.get(obj);
        } catch (Exception unused) {
            return null;
        }
    }

    public static Toast makeText(Context context, CharSequence charSequence, int i2) {
        Toast makeText = Toast.makeText(context, charSequence, i2);
        try {
            fixToastBadTokenException(makeText);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return makeText;
    }

    private static void setFieldValue(Object obj, String str, Object obj2) throws Exception {
        try {
            Field declaredField = getDeclaredField(obj, str);
            declaredField.setAccessible(true);
            declaredField.set(obj, obj2);
        } catch (Exception e2) {
            throw new Exception("setFieldValue exception, object = " + obj + ", fieldName = " + str, e2);
        }
    }
}
