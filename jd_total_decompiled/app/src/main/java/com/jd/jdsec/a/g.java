package com.jd.jdsec.a;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.hardware.Camera;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.PowerManager;
import android.os.StatFs;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.database.table.SignUpTable;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.tencent.smtt.sdk.ProxyConfig;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Pattern;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

@SuppressLint({"MissingPermission"})
/* loaded from: classes13.dex */
public class g<BatteryChangeReceiver> {
    private static String a = "model:%s,product:%s,brand:%s,release:%s,display:%s,locale:%s";

    /* loaded from: classes13.dex */
    class a implements FileFilter {
        a() {
        }

        @Override // java.io.FileFilter
        public boolean accept(File file) {
            return Pattern.matches("cpu[0-9]", file.getName());
        }
    }

    public static String A(Context context) {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface nextElement = networkInterfaces.nextElement();
                if (nextElement.isUp()) {
                    return nextElement.getDisplayName();
                }
            }
        } catch (SocketException e2) {
            e2.printStackTrace();
        }
        return null;
    }

    public static int B(Context context) {
        try {
            return Settings.System.getInt(context.getContentResolver(), "screen_brightness");
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    public static String C() {
        StringBuilder sb = new StringBuilder();
        List<Sensor> E = E(com.jd.jdsec.c.g.a);
        if (E != null) {
            for (int i2 = 0; i2 < E.size() && i2 < 10; i2++) {
                Sensor sensor = E.get(i2);
                sb.append(sensor.getName() + DYConstants.DY_REGEX_COMMA + sensor.getResolution() + DYConstants.DY_REGEX_COMMA + sensor.getVendor());
                sb.append(DYConstants.DY_REGEX_AT);
            }
        }
        return sb.length() > 0 ? sb.deleteCharAt(sb.length() - 1).toString() : "";
    }

    public static String D(Context context) {
        return BaseInfo.getScreenWidth() + ProxyConfig.MATCH_ALL_SCHEMES + BaseInfo.getScreenHeight();
    }

    public static List<Sensor> E(Context context) {
        return ((SensorManager) context.getSystemService("sensor")).getSensorList(-1);
    }

    public static String F(Context context) {
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            return Long.toString(statFs.getBlockCount() * statFs.getBlockSize());
        } catch (Exception unused) {
            return "unknow";
        }
    }

    public static String G(Context context) {
        long[] w = w(context);
        return w != null ? Long.toString(w[0]) : "unknow";
    }

    public static String H(Context context) {
        return "unknow";
    }

    public static Boolean I(Context context) {
        boolean z;
        try {
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (((SensorManager) context.getSystemService("sensor")).getDefaultSensor(2) != null) {
            z = true;
            return Boolean.valueOf(z);
        }
        z = false;
        return Boolean.valueOf(z);
    }

    public static boolean J(Context context) {
        try {
            return ((AudioManager) context.getSystemService("audio")).isWiredHeadsetOn();
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static boolean K(Context context) {
        try {
            return !((PowerManager) context.getSystemService("power")).isScreenOn();
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static boolean L(Context context) {
        try {
            return ((KeyguardManager) context.getSystemService("keyguard")).inKeyguardRestrictedInputMode();
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static String M(Context context) {
        boolean z = false;
        try {
            int intExtra = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED")).getIntExtra("status", -1);
            if (intExtra == 2 || intExtra == 5) {
                z = true;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return z ? "1" : "0";
    }

    public static boolean N(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService(SignUpTable.TB_COLUMN_PHONE)).getCallState() != 0;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0031, code lost:
        if (r3.getType() == 17) goto L17;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static boolean O(Context context) {
        ConnectivityManager connectivityManager;
        NetworkCapabilities networkCapabilities;
        boolean z = false;
        try {
            connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (Build.VERSION.SDK_INT < 23) {
            if (connectivityManager != null) {
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                if (activeNetworkInfo != null) {
                }
            }
            return z;
        }
        if (connectivityManager != null && (networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork())) != null && networkCapabilities.hasTransport(4)) {
            z = true;
        }
        return z;
        e2.printStackTrace();
        return z;
    }

    public static Boolean P(Context context) {
        Boolean bool = Boolean.FALSE;
        try {
            return ((ConnectivityManager) context.getSystemService("connectivity")).getNetworkInfo(1).isConnected() ? Boolean.TRUE : bool;
        } catch (Exception e2) {
            e2.printStackTrace();
            return bool;
        }
    }

    public static long a(String str) {
        long j2;
        if (str != null) {
            int indexOf = str.indexOf(58);
            if (indexOf != -1) {
                String trim = str.substring(indexOf + 1).trim();
                int lastIndexOf = trim.lastIndexOf(32);
                if (lastIndexOf != -1) {
                    String substring = trim.substring(lastIndexOf + 1);
                    try {
                        long parseLong = Long.parseLong(trim.substring(0, lastIndexOf).trim());
                        if ("kb".equalsIgnoreCase(substring)) {
                            j2 = 1024;
                        } else if ("mb".equalsIgnoreCase(substring)) {
                            j2 = 1048576;
                        } else if (!"gb".equalsIgnoreCase(substring)) {
                            if (com.jd.jdsec.a.l.b.a) {
                                com.jd.jdsec.a.l.b.f("JDSec.Security.Phone", "Unexpected mem unit format: " + trim);
                                return parseLong;
                            }
                            return parseLong;
                        } else {
                            j2 = IjkMediaMeta.AV_CH_STEREO_RIGHT;
                        }
                        return parseLong * j2;
                    } catch (Exception e2) {
                        if (com.jd.jdsec.a.l.b.a) {
                            e2.getLocalizedMessage();
                            return -1L;
                        }
                        return -1L;
                    }
                }
                com.jd.jdsec.a.l.b.b("JDSec.Security.Phone", "Unexpected mem value format: " + trim);
                return -1L;
            }
            com.jd.jdsec.a.l.b.b("JDSec.Security.Phone", "Unexpected mem format: " + str);
            return -1L;
        }
        return -1L;
    }

    public static Boolean b(Context context) {
        boolean z = false;
        try {
            Intent intent = new Intent();
            intent.setData(Uri.parse("tel:123456"));
            intent.setAction("android.intent.action.DIAL");
            if (intent.resolveActivity(context.getPackageManager()) != null) {
                z = true;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return Boolean.valueOf(z);
    }

    public static boolean c() {
        try {
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return Camera.getNumberOfCameras() > 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:10:0x0038, code lost:
        if (r6 == 0) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0057, code lost:
        if (r6 != 0) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0059, code lost:
        r6.destroy();
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x005c, code lost:
        return r0;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:44:0x004f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r6v11, types: [java.lang.Process] */
    /* JADX WARN: Type inference failed for: r6v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String d(String str) {
        IOException e2;
        BufferedReader bufferedReader;
        IOException e3;
        String str2 = "";
        BufferedReader bufferedReader2 = null;
        try {
            try {
                str = Runtime.getRuntime().exec("getprop " + str);
                try {
                    bufferedReader = new BufferedReader(new InputStreamReader(str.getInputStream()), 1024);
                } catch (IOException e4) {
                    e2 = e4;
                    IOException iOException = e2;
                    bufferedReader = null;
                    e3 = iOException;
                    e3.printStackTrace();
                    if (bufferedReader != null) {
                    }
                } catch (Throwable th) {
                    th = th;
                    if (bufferedReader2 != null) {
                        try {
                            bufferedReader2.close();
                        } catch (IOException e5) {
                            e5.printStackTrace();
                        }
                    }
                    if (str != 0) {
                        str.destroy();
                    }
                    throw th;
                }
            } catch (IOException e6) {
                e2 = e6;
                str = 0;
            } catch (Throwable th2) {
                th = th2;
                str = 0;
            }
            try {
                str2 = bufferedReader.readLine();
                try {
                    bufferedReader.close();
                } catch (IOException e7) {
                    e7.printStackTrace();
                }
            } catch (IOException e8) {
                e3 = e8;
                e3.printStackTrace();
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e9) {
                        e9.printStackTrace();
                    }
                }
            }
        } catch (Throwable th3) {
            th = th3;
            bufferedReader2 = bufferedReader;
        }
    }

    public static boolean e() {
        try {
            String[] strArr = {"/dev/socket/qemud", "/dev/qemu_pipe"};
            boolean z = false;
            for (int i2 = 0; i2 < 2; i2++) {
                if (new File(strArr[i2]).exists()) {
                    z = true;
                }
            }
            return z;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean f(Context context) {
        try {
            return context.getPackageManager().hasSystemFeature("android.hardware.camera.front");
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    private static String g(String str) {
        try {
            Class.forName(str);
            return "1";
        } catch (ClassNotFoundException unused) {
            return "0";
        } catch (Exception unused2) {
            return "c";
        }
    }

    public static boolean h() {
        File file = new File("/proc/tty/drivers");
        if (file.exists() && file.canRead()) {
            byte[] bArr = new byte[(int) file.length()];
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                fileInputStream.read(bArr);
                fileInputStream.close();
            } catch (FileNotFoundException e2) {
                e2.printStackTrace();
            } catch (IOException e3) {
                e3.printStackTrace();
            }
            return new String(bArr).indexOf("goldfish") != -1;
        }
        return false;
    }

    public static int[] i(Context context) {
        int[] iArr = {0, 0, 0, 0};
        try {
            Intent registerReceiver = context.getApplicationContext().registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            int intExtra = registerReceiver.getIntExtra("voltage", 0);
            int intExtra2 = registerReceiver.getIntExtra("level", 0);
            int intExtra3 = registerReceiver.getIntExtra("status", 0);
            int intExtra4 = registerReceiver.getIntExtra("health", 0);
            iArr[0] = intExtra2;
            iArr[1] = intExtra;
            iArr[2] = intExtra3;
            iArr[3] = intExtra4;
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return iArr;
    }

    public static String j() {
        return String.format(a, BaseInfo.getDeviceModel(), BaseInfo.getDeviceProductName(), BaseInfo.getDeviceBrand(), Build.VERSION.RELEASE, BaseInfo.getOSName(), Locale.getDefault().toString());
    }

    public static String k(Context context) {
        try {
            return context.getResources().getConfiguration().locale.getCountry();
        } catch (Exception e2) {
            e2.printStackTrace();
            return "unknow";
        }
    }

    public static int l(@NonNull Context context) {
        return ((AudioManager) context.getSystemService("audio")).getStreamVolume(1);
    }

    /*  JADX ERROR: Type inference failed with exception
        java.lang.NullPointerException
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:418)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:215)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:196)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdateForSsaVar(TypeUpdate.java:172)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:166)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:149)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:82)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:55)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.tryPossibleTypes(TypeInferenceVisitor.java:414)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.deduceType(TypeInferenceVisitor.java:472)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.tryDeduceTypes(TypeInferenceVisitor.java:452)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:109)
        */
    /* JADX WARN: Not initialized variable reg: 1, insn: 0x0094: MOVE (r6 I:??[OBJECT, ARRAY]) = (r1 I:??[OBJECT, ARRAY]), block:B:58:0x0094 */
    public static java.lang.String m() {
        /*
            r0 = 0
            java.io.FileReader r1 = new java.io.FileReader     // Catch: java.lang.Throwable -> L59 java.io.IOException -> L5e java.io.FileNotFoundException -> L75
            java.lang.String r2 = "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq"
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L59 java.io.IOException -> L5e java.io.FileNotFoundException -> L75
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L48 java.io.IOException -> L4f java.io.FileNotFoundException -> L54
            r2.<init>(r1)     // Catch: java.lang.Throwable -> L48 java.io.IOException -> L4f java.io.FileNotFoundException -> L54
            java.lang.String r0 = r2.readLine()     // Catch: java.io.IOException -> L44 java.io.FileNotFoundException -> L46 java.lang.Throwable -> L93
            if (r0 == 0) goto L1c
            java.lang.String r0 = r0.trim()     // Catch: java.io.IOException -> L44 java.io.FileNotFoundException -> L46 java.lang.Throwable -> L93
            int r0 = java.lang.Integer.parseInt(r0)     // Catch: java.io.IOException -> L44 java.io.FileNotFoundException -> L46 java.lang.Throwable -> L93
            goto L1d
        L1c:
            r0 = 0
        L1d:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.io.IOException -> L44 java.io.FileNotFoundException -> L46 java.lang.Throwable -> L93
            r3.<init>()     // Catch: java.io.IOException -> L44 java.io.FileNotFoundException -> L46 java.lang.Throwable -> L93
            double r4 = (double) r0     // Catch: java.io.IOException -> L44 java.io.FileNotFoundException -> L46 java.lang.Throwable -> L93
            java.lang.String r0 = com.jd.jdsec.a.l.e.b(r4)     // Catch: java.io.IOException -> L44 java.io.FileNotFoundException -> L46 java.lang.Throwable -> L93
            r3.append(r0)     // Catch: java.io.IOException -> L44 java.io.FileNotFoundException -> L46 java.lang.Throwable -> L93
            java.lang.String r0 = "GHz"
            r3.append(r0)     // Catch: java.io.IOException -> L44 java.io.FileNotFoundException -> L46 java.lang.Throwable -> L93
            java.lang.String r0 = r3.toString()     // Catch: java.io.IOException -> L44 java.io.FileNotFoundException -> L46 java.lang.Throwable -> L93
            r1.close()     // Catch: java.io.IOException -> L37
            goto L3b
        L37:
            r1 = move-exception
            r1.printStackTrace()
        L3b:
            r2.close()     // Catch: java.io.IOException -> L3f
            goto L43
        L3f:
            r1 = move-exception
            r1.printStackTrace()
        L43:
            return r0
        L44:
            r0 = move-exception
            goto L62
        L46:
            r0 = move-exception
            goto L79
        L48:
            r2 = move-exception
            r6 = r2
            r2 = r0
            r0 = r1
            r1 = r6
            goto L97
        L4f:
            r2 = move-exception
            r6 = r2
            r2 = r0
            r0 = r6
            goto L62
        L54:
            r2 = move-exception
            r6 = r2
            r2 = r0
            r0 = r6
            goto L79
        L59:
            r1 = move-exception
            r2 = r1
            r1 = r2
            r2 = r0
            goto L97
        L5e:
            r1 = move-exception
            r2 = r0
            r0 = r1
            r1 = r2
        L62:
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L93
            if (r1 == 0) goto L6f
            r1.close()     // Catch: java.io.IOException -> L6b
            goto L6f
        L6b:
            r0 = move-exception
            r0.printStackTrace()
        L6f:
            if (r2 == 0) goto L90
            r2.close()     // Catch: java.io.IOException -> L8c
            goto L90
        L75:
            r1 = move-exception
            r2 = r0
            r0 = r1
            r1 = r2
        L79:
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L93
            if (r1 == 0) goto L86
            r1.close()     // Catch: java.io.IOException -> L82
            goto L86
        L82:
            r0 = move-exception
            r0.printStackTrace()
        L86:
            if (r2 == 0) goto L90
            r2.close()     // Catch: java.io.IOException -> L8c
            goto L90
        L8c:
            r0 = move-exception
            r0.printStackTrace()
        L90:
            java.lang.String r0 = ""
            return r0
        L93:
            r0 = move-exception
            r6 = r1
            r1 = r0
            r0 = r6
        L97:
            if (r0 == 0) goto La1
            r0.close()     // Catch: java.io.IOException -> L9d
            goto La1
        L9d:
            r0 = move-exception
            r0.printStackTrace()
        La1:
            if (r2 == 0) goto Lab
            r2.close()     // Catch: java.io.IOException -> La7
            goto Lab
        La7:
            r0 = move-exception
            r0.printStackTrace()
        Lab:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.jdsec.a.g.m():java.lang.String");
    }

    /*  JADX ERROR: Type inference failed with exception
        java.lang.NullPointerException
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:418)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:215)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:196)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdateForSsaVar(TypeUpdate.java:172)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:166)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:149)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:82)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:55)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.tryPossibleTypes(TypeInferenceVisitor.java:414)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.deduceType(TypeInferenceVisitor.java:472)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.tryDeduceTypes(TypeInferenceVisitor.java:452)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:109)
        */
    /* JADX WARN: Not initialized variable reg: 1, insn: 0x0090: MOVE (r6 I:??[OBJECT, ARRAY]) = (r1 I:??[OBJECT, ARRAY]), block:B:54:0x0090 */
    public static java.lang.String n() {
        /*
            r0 = 0
            java.io.FileReader r1 = new java.io.FileReader     // Catch: java.lang.Throwable -> L55 java.io.IOException -> L5a java.io.FileNotFoundException -> L71
            java.lang.String r2 = "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_min_freq"
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L55 java.io.IOException -> L5a java.io.FileNotFoundException -> L71
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L44 java.io.IOException -> L4b java.io.FileNotFoundException -> L50
            r2.<init>(r1)     // Catch: java.lang.Throwable -> L44 java.io.IOException -> L4b java.io.FileNotFoundException -> L50
            java.lang.String r0 = r2.readLine()     // Catch: java.io.IOException -> L40 java.io.FileNotFoundException -> L42 java.lang.Throwable -> L8f
            java.lang.String r0 = r0.trim()     // Catch: java.io.IOException -> L40 java.io.FileNotFoundException -> L42 java.lang.Throwable -> L8f
            int r0 = java.lang.Integer.parseInt(r0)     // Catch: java.io.IOException -> L40 java.io.FileNotFoundException -> L42 java.lang.Throwable -> L8f
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.io.IOException -> L40 java.io.FileNotFoundException -> L42 java.lang.Throwable -> L8f
            r3.<init>()     // Catch: java.io.IOException -> L40 java.io.FileNotFoundException -> L42 java.lang.Throwable -> L8f
            double r4 = (double) r0     // Catch: java.io.IOException -> L40 java.io.FileNotFoundException -> L42 java.lang.Throwable -> L8f
            java.lang.String r0 = com.jd.jdsec.a.l.e.b(r4)     // Catch: java.io.IOException -> L40 java.io.FileNotFoundException -> L42 java.lang.Throwable -> L8f
            r3.append(r0)     // Catch: java.io.IOException -> L40 java.io.FileNotFoundException -> L42 java.lang.Throwable -> L8f
            java.lang.String r0 = "GHz"
            r3.append(r0)     // Catch: java.io.IOException -> L40 java.io.FileNotFoundException -> L42 java.lang.Throwable -> L8f
            java.lang.String r0 = r3.toString()     // Catch: java.io.IOException -> L40 java.io.FileNotFoundException -> L42 java.lang.Throwable -> L8f
            r1.close()     // Catch: java.io.IOException -> L33
            goto L37
        L33:
            r1 = move-exception
            r1.printStackTrace()
        L37:
            r2.close()     // Catch: java.io.IOException -> L3b
            goto L3f
        L3b:
            r1 = move-exception
            r1.printStackTrace()
        L3f:
            return r0
        L40:
            r0 = move-exception
            goto L5e
        L42:
            r0 = move-exception
            goto L75
        L44:
            r2 = move-exception
            r6 = r2
            r2 = r0
            r0 = r1
            r1 = r6
            goto L93
        L4b:
            r2 = move-exception
            r6 = r2
            r2 = r0
            r0 = r6
            goto L5e
        L50:
            r2 = move-exception
            r6 = r2
            r2 = r0
            r0 = r6
            goto L75
        L55:
            r1 = move-exception
            r2 = r1
            r1 = r2
            r2 = r0
            goto L93
        L5a:
            r1 = move-exception
            r2 = r0
            r0 = r1
            r1 = r2
        L5e:
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L8f
            if (r1 == 0) goto L6b
            r1.close()     // Catch: java.io.IOException -> L67
            goto L6b
        L67:
            r0 = move-exception
            r0.printStackTrace()
        L6b:
            if (r2 == 0) goto L8c
            r2.close()     // Catch: java.io.IOException -> L88
            goto L8c
        L71:
            r1 = move-exception
            r2 = r0
            r0 = r1
            r1 = r2
        L75:
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L8f
            if (r1 == 0) goto L82
            r1.close()     // Catch: java.io.IOException -> L7e
            goto L82
        L7e:
            r0 = move-exception
            r0.printStackTrace()
        L82:
            if (r2 == 0) goto L8c
            r2.close()     // Catch: java.io.IOException -> L88
            goto L8c
        L88:
            r0 = move-exception
            r0.printStackTrace()
        L8c:
            java.lang.String r0 = ""
            return r0
        L8f:
            r0 = move-exception
            r6 = r1
            r1 = r0
            r0 = r6
        L93:
            if (r0 == 0) goto L9d
            r0.close()     // Catch: java.io.IOException -> L99
            goto L9d
        L99:
            r0 = move-exception
            r0.printStackTrace()
        L9d:
            if (r2 == 0) goto La7
            r2.close()     // Catch: java.io.IOException -> La3
            goto La7
        La3:
            r0 = move-exception
            r0.printStackTrace()
        La7:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.jdsec.a.g.n():java.lang.String");
    }

    public static String[] o(Context context) {
        try {
            String[] s = s();
            return (s == null || s.length == 0) ? q(context) : s;
        } catch (Exception unused) {
            return new String[2];
        }
    }

    public static int p() {
        try {
            return new File("/sys/devices/system/cpu/").listFiles(new a()).length;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 1;
        }
    }

    private static String[] q(Context context) {
        ConnectivityManager connectivityManager;
        NetworkInfo activeNetworkInfo;
        LinkedList linkedList = new LinkedList();
        if (Build.VERSION.SDK_INT >= 21 && context != null && (connectivityManager = (ConnectivityManager) context.getSystemService("connectivity")) != null && (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) != null) {
            for (Network network : connectivityManager.getAllNetworks()) {
                NetworkInfo networkInfo = connectivityManager.getNetworkInfo(network);
                if (networkInfo != null && networkInfo.getType() == activeNetworkInfo.getType()) {
                    Iterator<InetAddress> it = connectivityManager.getLinkProperties(network).getDnsServers().iterator();
                    while (it.hasNext()) {
                        linkedList.add(it.next().getHostAddress());
                    }
                }
            }
        }
        return linkedList.isEmpty() ? new String[0] : (String[]) linkedList.toArray(new String[linkedList.size()]);
    }

    public static List<PackageInfo> r(Context context) {
        return null;
    }

    public static String[] s() {
        String hostAddress;
        LinkedList linkedList = new LinkedList();
        try {
            LineNumberReader lineNumberReader = new LineNumberReader(new InputStreamReader(Runtime.getRuntime().exec("getprop").getInputStream()));
            while (true) {
                String readLine = lineNumberReader.readLine();
                if (readLine == null) {
                    break;
                }
                int indexOf = readLine.indexOf("]: [");
                if (indexOf != -1) {
                    String substring = readLine.substring(1, indexOf);
                    String substring2 = readLine.substring(indexOf + 4, readLine.length() - 1);
                    if (substring.endsWith(".dns") || substring.endsWith(".dns1") || substring.endsWith(".dns2") || substring.endsWith(".dns3") || substring.endsWith(".dns4")) {
                        InetAddress byName = InetAddress.getByName(substring2);
                        if (byName != null && (hostAddress = byName.getHostAddress()) != null && hostAddress.length() != 0) {
                            linkedList.add(hostAddress);
                        }
                    }
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return linkedList.isEmpty() ? new String[0] : (String[]) linkedList.toArray(new String[linkedList.size()]);
    }

    public static long t() {
        long blockSize;
        long availableBlocks;
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            if (Build.VERSION.SDK_INT >= 18) {
                blockSize = statFs.getBlockSizeLong();
                availableBlocks = statFs.getAvailableBlocksLong();
            } else {
                blockSize = statFs.getBlockSize();
                availableBlocks = statFs.getAvailableBlocks();
            }
            return blockSize * availableBlocks;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0L;
        }
    }

    public static String[] u(Context context) {
        String[] strArr = {"", ""};
        try {
            String networkOperator = BaseInfo.getNetworkOperator(context);
            return new String[]{networkOperator.substring(0, 3), networkOperator.substring(3)};
        } catch (Exception e2) {
            e2.printStackTrace();
            return strArr;
        }
    }

    public static String v() {
        try {
            String str = "";
            for (String str2 : BaseInfo.getNetAddressesForIPv4()) {
                str = TextUtils.isEmpty(str) ? str2 + "%ipv4" : str + DYConstants.DY_REGEX_COMMA + str2 + "%ipv4";
            }
            for (String str3 : BaseInfo.getNetAddressesForIPv6()) {
                str = TextUtils.isEmpty(str) ? str3 : str + DYConstants.DY_REGEX_COMMA + str3;
            }
            return str;
        } catch (Exception unused) {
            return "";
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:57:0x008b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static long[] w(Context context) {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2 = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("/proc/meminfo"))), 1024);
            String str = null;
            String str2 = null;
            while (true) {
                try {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine != null) {
                            if (readLine.startsWith("MemTotal")) {
                                str = readLine;
                            } else if (readLine.startsWith("MemFree")) {
                                str2 = readLine;
                            }
                            if (str != null && str2 != null) {
                                break;
                            }
                        } else {
                            break;
                        }
                    } catch (Throwable th) {
                        th = th;
                        bufferedReader2 = bufferedReader;
                        if (bufferedReader2 != null) {
                            try {
                                bufferedReader2.close();
                            } catch (IOException e2) {
                                if (com.jd.jdsec.a.l.b.a) {
                                    e2.getLocalizedMessage();
                                }
                            }
                        }
                        throw th;
                    }
                } catch (Exception e3) {
                    e = e3;
                    if (com.jd.jdsec.a.l.b.a) {
                        e.getLocalizedMessage();
                    }
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e4) {
                            if (com.jd.jdsec.a.l.b.a) {
                                e4.getLocalizedMessage();
                            }
                        }
                    }
                    return null;
                }
            }
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(memoryInfo);
            long[] jArr = {a(str), a(str2), memoryInfo.availMem};
            try {
                bufferedReader.close();
            } catch (IOException e5) {
                if (com.jd.jdsec.a.l.b.a) {
                    e5.getLocalizedMessage();
                }
            }
            return jArr;
        } catch (Exception e6) {
            e = e6;
            bufferedReader = null;
        } catch (Throwable th2) {
            th = th2;
            if (bufferedReader2 != null) {
            }
            throw th;
        }
    }

    public static String x() {
        try {
            return TimeZone.getDefault().getDisplayName(false, 0);
        } catch (Exception e2) {
            e2.printStackTrace();
            return "unknow";
        }
    }

    public static String y(Context context) {
        return "";
    }

    public static String z() {
        String[] strArr = {"android.app.blob.BlobStoreManager", "android.app.role.RoleManager", "android.net.wifi.rtt.WifiRttManager", "android.companion.WifiDeviceFilter", "android.os.health.SystemHealthManager", "android.media.midi.MidiManager", "android.media.projection.MediaProjectionManager", "android.net.wifi.p2p.WifiP2pManager"};
        String str = "";
        for (int i2 = 0; i2 < 8; i2++) {
            str = String.format("%s%s", g(strArr[i2]), str);
        }
        return str;
    }
}
