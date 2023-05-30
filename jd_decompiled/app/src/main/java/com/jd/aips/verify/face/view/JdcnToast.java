package com.jd.aips.verify.face.view;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.Toast;
import com.jd.aips.common.utils.FsBaseInfoUtils;
import com.jdpay.system.SystemInfo;
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
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        static java.lang.String getProp(java.lang.String r5) {
            /*
                r0 = 0
                java.lang.Runtime r1 = java.lang.Runtime.getRuntime()     // Catch: java.lang.Throwable -> L43 java.io.IOException -> L56
                java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L43 java.io.IOException -> L56
                r2.<init>()     // Catch: java.lang.Throwable -> L43 java.io.IOException -> L56
                java.lang.String r3 = "getprop "
                r2.append(r3)     // Catch: java.lang.Throwable -> L43 java.io.IOException -> L56
                r2.append(r5)     // Catch: java.lang.Throwable -> L43 java.io.IOException -> L56
                java.lang.String r5 = r2.toString()     // Catch: java.lang.Throwable -> L43 java.io.IOException -> L56
                java.lang.Process r5 = r1.exec(r5)     // Catch: java.lang.Throwable -> L43 java.io.IOException -> L56
                java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L41 java.io.IOException -> L57
                java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L41 java.io.IOException -> L57
                java.io.InputStream r3 = r5.getInputStream()     // Catch: java.lang.Throwable -> L41 java.io.IOException -> L57
                r2.<init>(r3)     // Catch: java.lang.Throwable -> L41 java.io.IOException -> L57
                r3 = 1024(0x400, float:1.435E-42)
                r1.<init>(r2, r3)     // Catch: java.lang.Throwable -> L41 java.io.IOException -> L57
                java.lang.String r0 = r1.readLine()     // Catch: java.lang.Throwable -> L3a java.io.IOException -> L3f
                r1.close()     // Catch: java.io.IOException -> L32
                goto L36
            L32:
                r1 = move-exception
                r1.printStackTrace()
            L36:
                r5.destroy()
                return r0
            L3a:
                r0 = move-exception
                r4 = r1
                r1 = r0
                r0 = r4
                goto L46
            L3f:
                goto L58
            L41:
                r1 = move-exception
                goto L46
            L43:
                r5 = move-exception
                r1 = r5
                r5 = r0
            L46:
                if (r0 == 0) goto L50
                r0.close()     // Catch: java.io.IOException -> L4c
                goto L50
            L4c:
                r0 = move-exception
                r0.printStackTrace()
            L50:
                if (r5 == 0) goto L55
                r5.destroy()
            L55:
                throw r1
            L56:
                r5 = r0
            L57:
                r1 = r0
            L58:
                if (r1 == 0) goto L62
                r1.close()     // Catch: java.io.IOException -> L5e
                goto L62
            L5e:
                r1 = move-exception
                r1.printStackTrace()
            L62:
                if (r5 == 0) goto L67
                r5.destroy()
            L67:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jd.aips.verify.face.view.JdcnToast.RomUtils.getProp(java.lang.String):java.lang.String");
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
