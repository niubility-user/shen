package com.jd.security.jdguard.d.d.e;

import android.content.Context;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jd.security.jdguard.d.c.f;
import com.jd.security.jdguard.d.c.h;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.meizu.cloud.pushsdk.notification.model.AdvanceSetting;

/* loaded from: classes17.dex */
public class a extends com.jd.security.jdguard.d.d.a {

    /* renamed from: e */
    private static a f6934e;

    private static int m(boolean z) {
        return z ? 1 : 0;
    }

    private static String n(int i2) {
        StringBuilder sb = new StringBuilder();
        for (int i3 = 0; i3 < i2; i3++) {
            if (i3 == i2 - 1) {
                sb.append(DYConstants.DY_NULL_STR);
            } else {
                sb.append(DYConstants.DY_NULL_STR);
                sb.append("|");
            }
        }
        return sb.toString();
    }

    private static String o(String str) {
        return (TextUtils.isEmpty(str) || TextUtils.equals(str, "unknown")) ? DYConstants.DY_NULL_STR : str;
    }

    private void p(Context context, h hVar, com.jd.security.jdguard.d.c.d dVar, StringBuilder sb) {
        Integer num;
        Integer num2;
        Integer num3;
        StringBuilder sb2 = new StringBuilder();
        try {
            if (hVar.n("envi")) {
                boolean o = hVar.o("envi", "nfc");
                String str = DYConstants.DY_NULL_STR;
                if (o) {
                    boolean isNFCEnabled = BaseInfo.isNFCEnabled(context);
                    m(isNFCEnabled);
                    num = Integer.valueOf(isNFCEnabled ? 1 : 0);
                } else {
                    num = DYConstants.DY_NULL_STR;
                }
                sb2.append(num);
                sb2.append("|");
                if (hVar.o("envi", "blta")) {
                    boolean isBluetoothAvailabel = BaseInfo.isBluetoothAvailabel();
                    m(isBluetoothAvailabel);
                    num2 = Integer.valueOf(isBluetoothAvailabel ? 1 : 0);
                } else {
                    num2 = DYConstants.DY_NULL_STR;
                }
                sb2.append(num2);
                sb2.append("|");
                sb2.append(hVar.o("envi", "blte") ? o(d.a(context)) : DYConstants.DY_NULL_STR);
                sb2.append("|");
                sb2.append(DYConstants.DY_NULL_STR);
                sb2.append("|");
                sb2.append(hVar.o("envi", "neti") ? Integer.valueOf(BaseInfo.getNetworkTypeInt()) : DYConstants.DY_NULL_STR);
                sb2.append("|");
                sb2.append(DYConstants.DY_NULL_STR);
                sb2.append("|");
                sb2.append(DYConstants.DY_NULL_STR);
                sb2.append("|");
                sb2.append(DYConstants.DY_NULL_STR);
                sb2.append("|");
                if (hVar.o("envi", "qemu")) {
                    boolean isQEmuDriverFile = BaseInfo.isQEmuDriverFile();
                    m(isQEmuDriverFile);
                    num3 = Integer.valueOf(isQEmuDriverFile ? 1 : 0);
                } else {
                    num3 = DYConstants.DY_NULL_STR;
                }
                sb2.append(num3);
                sb2.append("|");
                sb2.append(hVar.o("envi", "tmz") ? c.d() : DYConstants.DY_NULL_STR);
                sb2.append("|");
                if (hVar.o("envi", "lang")) {
                    str = c.c(context);
                }
                sb2.append(str);
                String sb3 = sb2.toString();
                if (sb3.contains("\u00a7")) {
                    sb3 = sb3.replace("\u00a7", "X");
                }
                sb.append(sb3);
                return;
            }
            throw new Exception("envi sw off");
        } catch (Throwable unused) {
            sb.append(n(11));
        }
    }

    private void q(Context context, h hVar, com.jd.security.jdguard.d.c.d dVar, StringBuilder sb) {
        StringBuilder sb2 = new StringBuilder();
        try {
            if (hVar.n("eei")) {
                boolean o = hVar.o("eei", "sdid");
                Object obj = DYConstants.DY_NULL_STR;
                sb2.append(o ? o(BaseInfo.getSDCardId()) : DYConstants.DY_NULL_STR);
                sb2.append("|");
                if (hVar.o("eei", "exsz")) {
                    obj = Long.valueOf(BaseInfo.getExternalStorageSize());
                }
                sb2.append(obj);
                sb.append((CharSequence) sb2);
                return;
            }
            throw new Exception("eei sw off");
        } catch (Throwable unused) {
            sb.append(n(2));
        }
    }

    private void r(Context context, h hVar, com.jd.security.jdguard.d.c.d dVar, StringBuilder sb) {
        StringBuilder sb2 = new StringBuilder();
        try {
            if (hVar.n("hwe")) {
                sb2.append(hVar.o("hwe", "cno") ? o(BaseInfo.getCPUSerialNo()) : DYConstants.DY_NULL_STR);
                sb2.append("|");
                sb2.append(hVar.o("hwe", "cnm") ? o(BaseInfo.getCPUNum()) : DYConstants.DY_NULL_STR);
                sb2.append("|");
                sb2.append(hVar.o("hwe", "cmxf") ? o(BaseInfo.getCPUMaxFreq()) : DYConstants.DY_NULL_STR);
                sb2.append("|");
                sb2.append(DYConstants.DY_NULL_STR);
                sb2.append("|");
                sb2.append(hVar.o("hwe", "cmif") ? o(BaseInfo.getCpuMinFreq()) : DYConstants.DY_NULL_STR);
                sb2.append("|");
                sb2.append(hVar.o("hwe", "ccrf") ? o(BaseInfo.getCpuCurFreq()) : DYConstants.DY_NULL_STR);
                sb2.append("|");
                sb2.append(hVar.o("hwe", "mts") ? Long.valueOf(BaseInfo.getMemTotalSize()) : DYConstants.DY_NULL_STR);
                sb2.append("|");
                sb2.append(hVar.o("hwe", "mas") ? Long.valueOf(BaseInfo.getMemAvailSize()) : DYConstants.DY_NULL_STR);
                sb2.append("|");
                sb2.append(hVar.o("hwe", "rs") ? Long.valueOf(BaseInfo.getRomSize()) : DYConstants.DY_NULL_STR);
                sb2.append("|");
                sb2.append(hVar.o("hwe", "dst") ? Float.valueOf(BaseInfo.getDensity()) : DYConstants.DY_NULL_STR);
                sb2.append("|");
                sb2.append(hVar.o("hwe", "dpi") ? BaseInfo.getDensityDpi() : DYConstants.DY_NULL_STR);
                sb2.append("|");
                sb2.append(DYConstants.DY_NULL_STR);
                sb2.append("|");
                sb2.append(DYConstants.DY_NULL_STR);
                sb2.append("|");
                sb2.append(DYConstants.DY_NULL_STR);
                sb2.append("|");
                sb2.append(DYConstants.DY_NULL_STR);
                sb2.append("|");
                sb2.append(hVar.o("hwe", "dpm") ? BaseInfo.getDisplayMetrics() : DYConstants.DY_NULL_STR);
                sb2.append("|");
                sb2.append(DYConstants.DY_NULL_STR);
                sb.append((CharSequence) sb2);
                return;
            }
            throw new Exception("hwe sw off");
        } catch (Throwable unused) {
            sb.append(n(17));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:161:0x0118 A[Catch: all -> 0x0137, TryCatch #0 {all -> 0x0137, blocks: (B:117:0x0009, B:119:0x000f, B:122:0x0019, B:124:0x0023, B:126:0x0037, B:128:0x0041, B:130:0x0055, B:132:0x005f, B:134:0x006d, B:136:0x0077, B:138:0x0085, B:140:0x008f, B:142:0x009d, B:144:0x00a7, B:146:0x00c1, B:148:0x00cb, B:150:0x00d9, B:152:0x00e3, B:154:0x00f7, B:157:0x00ff, B:159:0x0104, B:161:0x0118, B:163:0x0122, B:166:0x012f, B:167:0x0136), top: B:170:0x0009 }] */
    /* JADX WARN: Removed duplicated region for block: B:162:0x0121  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void s(Context context, h hVar, com.jd.security.jdguard.d.c.d dVar, StringBuilder sb) {
        String str;
        StringBuilder sb2 = new StringBuilder();
        try {
            if (hVar.n("hwp")) {
                sb2.append(hVar.o("hwp", "bd") ? o(BaseInfo.getBoard()) : DYConstants.DY_NULL_STR);
                sb2.append("|");
                sb2.append(DYConstants.DY_NULL_STR);
                sb2.append("|");
                sb2.append(hVar.o("hwp", "dpn") ? o(BaseInfo.getDeviceProductName()) : DYConstants.DY_NULL_STR);
                sb2.append("|");
                sb2.append(DYConstants.DY_NULL_STR);
                sb2.append("|");
                sb2.append(hVar.o("hwp", "dmf") ? o(BaseInfo.getDeviceManufacture()) : DYConstants.DY_NULL_STR);
                sb2.append("|");
                sb2.append(hVar.o("hwp", "dm") ? o(BaseInfo.getDeviceModel()) : DYConstants.DY_NULL_STR);
                sb2.append("|");
                sb2.append(hVar.o("hwp", "av") ? o(BaseInfo.getAndroidVersion()) : DYConstants.DY_NULL_STR);
                sb2.append("|");
                sb2.append(hVar.o("hwp", "asv") ? Integer.valueOf(BaseInfo.getAndroidSDKVersion()) : DYConstants.DY_NULL_STR);
                sb2.append("|");
                sb2.append(DYConstants.DY_NULL_STR);
                sb2.append("|");
                sb2.append(DYConstants.DY_NULL_STR);
                sb2.append("|");
                sb2.append(hVar.o("hwp", AdvanceSetting.HEAD_UP_NOTIFICATION) ? o(BaseInfo.getHardwareName()) : DYConstants.DY_NULL_STR);
                sb2.append("|");
                sb2.append(hVar.o("hwp", "ot") ? o(BaseInfo.getOSVersionType()) : DYConstants.DY_NULL_STR);
                sb2.append("|");
                sb2.append(DYConstants.DY_NULL_STR);
                sb2.append("|");
                if (hVar.o("hwp", "abis")) {
                    String[] deviceSuppportedABIs = BaseInfo.getDeviceSuppportedABIs();
                    if (deviceSuppportedABIs.length != 0) {
                        str = deviceSuppportedABIs[0];
                        sb2.append(str);
                        sb2.append("|");
                        sb2.append(DYConstants.DY_NULL_STR);
                        sb2.append("|");
                        sb2.append(!hVar.o("hwp", "blv") ? o(BaseInfo.getBootloaderVersion()) : DYConstants.DY_NULL_STR);
                        sb2.append("|");
                        sb2.append(DYConstants.DY_NULL_STR);
                        sb.append((CharSequence) sb2);
                        return;
                    }
                }
                str = DYConstants.DY_NULL_STR;
                sb2.append(str);
                sb2.append("|");
                sb2.append(DYConstants.DY_NULL_STR);
                sb2.append("|");
                sb2.append(!hVar.o("hwp", "blv") ? o(BaseInfo.getBootloaderVersion()) : DYConstants.DY_NULL_STR);
                sb2.append("|");
                sb2.append(DYConstants.DY_NULL_STR);
                sb.append((CharSequence) sb2);
                return;
            }
            throw new Exception("hwp sw off");
        } catch (Throwable unused) {
            sb.append(n(17));
        }
    }

    private void t(Context context, h hVar, com.jd.security.jdguard.d.c.d dVar, StringBuilder sb) {
        Integer num;
        StringBuilder sb2 = new StringBuilder();
        try {
            if (hVar.n("hws")) {
                boolean o = hVar.o("hws", "isr");
                Object obj = DYConstants.DY_NULL_STR;
                if (o) {
                    boolean isStorageRemovable = BaseInfo.isStorageRemovable();
                    m(isStorageRemovable);
                    num = Integer.valueOf(isStorageRemovable ? 1 : 0);
                } else {
                    num = DYConstants.DY_NULL_STR;
                }
                sb2.append(num);
                sb2.append("|");
                if (hVar.o("hws", "igps")) {
                    boolean isGPSAvailable = BaseInfo.isGPSAvailable();
                    m(isGPSAvailable);
                    obj = Integer.valueOf(isGPSAvailable ? 1 : 0);
                }
                sb2.append(obj);
                sb.append((CharSequence) sb2);
                return;
            }
            throw new Exception("hws sw off");
        } catch (Throwable unused) {
            sb.append(n(2));
        }
    }

    public static a u() {
        if (f6934e == null) {
            synchronized (a.class) {
                if (f6934e == null) {
                    f6934e = new a();
                }
            }
        }
        return f6934e;
    }

    private void v(Context context, h hVar, com.jd.security.jdguard.d.c.d dVar, StringBuilder sb) {
        StringBuilder sb2 = new StringBuilder();
        try {
            if (hVar.n("lcti")) {
                sb2.append(DYConstants.DY_NULL_STR);
                sb2.append("|");
                sb2.append(hVar.o("lcti", "neto") ? o(BaseInfo.getNetworkOperator(context)) : DYConstants.DY_NULL_STR);
                sb2.append("|");
                sb2.append(DYConstants.DY_NULL_STR);
                sb2.append("|");
                sb2.append(DYConstants.DY_NULL_STR);
                sb2.append("|");
                sb2.append(DYConstants.DY_NULL_STR);
                sb2.append("|");
                sb2.append(DYConstants.DY_NULL_STR);
                sb2.append("|");
                sb2.append(DYConstants.DY_NULL_STR);
                sb.append((CharSequence) sb2);
                return;
            }
            throw new Exception("lcti sw off");
        } catch (Throwable unused) {
            sb.append(n(7));
        }
    }

    private void w(Context context, h hVar, com.jd.security.jdguard.d.c.d dVar, StringBuilder sb) {
        StringBuilder sb2 = new StringBuilder();
        try {
            if (hVar.n("plfm")) {
                boolean o = hVar.o("plfm", "romn");
                Object obj = DYConstants.DY_NULL_STR;
                sb2.append(o ? o(BaseInfo.getRomName()) : DYConstants.DY_NULL_STR);
                sb2.append("|");
                sb2.append(DYConstants.DY_NULL_STR);
                sb2.append("|");
                sb2.append(DYConstants.DY_NULL_STR);
                sb2.append("|");
                sb2.append(hVar.o("plfm", "apfi") ? Long.valueOf(BaseInfo.getAppFirstInstallTime()) : DYConstants.DY_NULL_STR);
                sb2.append("|");
                if (hVar.o("plfm", "aplu")) {
                    obj = Long.valueOf(BaseInfo.getAppLastUpdateTime());
                }
                sb2.append(obj);
                sb.append((CharSequence) sb2);
                return;
            }
            throw new Exception("plfm sw off");
        } catch (Throwable unused) {
            sb.append(n(5));
        }
    }

    private void x(Context context, h hVar, com.jd.security.jdguard.d.c.d dVar, StringBuilder sb) {
        StringBuilder sb2 = new StringBuilder();
        try {
            if (hVar.n("usra")) {
                sb2.append(DYConstants.DY_NULL_STR);
                sb2.append("|");
                sb2.append(DYConstants.DY_NULL_STR);
                sb2.append("|");
                sb2.append(hVar.o("usra", "fnts") ? Float.valueOf(c.a(context)) : DYConstants.DY_NULL_STR);
                sb2.append("|");
                sb2.append(DYConstants.DY_NULL_STR);
                sb.append((CharSequence) sb2);
                return;
            }
            throw new Exception("usra sw off");
        } catch (Throwable unused) {
            sb.append(n(4));
        }
    }

    @Override // com.jd.security.jdguard.d.d.a
    public String d() {
        return "-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-\u00a7\u00a7-|-\u00a7\u00a7-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-\u00a7\u00a7-|-\u00a7\u00a7-|-|-|-\u00a7\u00a7-|-|-|-|-|-|-|-|-|-|-\u00a7\u00a7-|-|-|-|-\u00a7\u00a7-|-|-|-|-|-|-";
    }

    @Override // com.jd.security.jdguard.d.d.a
    protected void f(Context context, f fVar, com.jd.security.jdguard.d.c.d dVar, Object obj) {
        if (obj != null && (obj instanceof StringBuilder)) {
            StringBuilder sb = new StringBuilder();
            h hVar = (h) fVar;
            s(context, hVar, dVar, sb);
            sb.append("\u00a7\u00a7");
            t(context, hVar, dVar, sb);
            sb.append("\u00a7\u00a7");
            r(context, hVar, dVar, sb);
            sb.append("\u00a7\u00a7");
            q(context, hVar, dVar, sb);
            sb.append("\u00a7\u00a7");
            x(context, hVar, dVar, sb);
            sb.append("\u00a7\u00a7");
            p(context, hVar, dVar, sb);
            sb.append("\u00a7\u00a7");
            w(context, hVar, dVar, sb);
            sb.append("\u00a7\u00a7");
            v(context, hVar, dVar, sb);
            ((StringBuilder) obj).append(sb.toString().replaceAll(DYConstants.DY_NULL_STR, "-").replaceAll("unknown", "-"));
        }
    }

    @Override // com.jd.security.jdguard.d.d.a
    protected String j(Object obj) {
        if (obj != null) {
            return ((StringBuilder) obj).toString();
        }
        return null;
    }

    @Override // com.jd.security.jdguard.d.d.a
    protected Object k() {
        return new StringBuilder();
    }
}
