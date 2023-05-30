package com.jd.fireeye.a.d;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.jd.android.sdk.coreinfo.CoreInfo;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes13.dex */
public class e {
    private static List<String> b = Arrays.asList("Lenovo TB-J606F", "Xiaomi Pad 5 Pro", "Xiaomi Pad 5", "Lenovo TB-9707F", "AGS3-AL09HN", "AGR-AL09HN", "AGS3K-AL20", "SM-T735C", "SM-X205C", "AGM3-AL09HN", "BAH2-AL10", "CMR-AL19", "AGS2-AL00", "AGS3-AL00", "BAH2-AL10", "JDN2-AL50HN", "JDN2-AL50", "AGS2-AL00HN", "JDN2-AL00HN", "JDN2-AL00", "SM-T505C", "HDL-AL09", "CPN-AL00", "MI PAD 4 PLUS", "MI PAD 4", "SM-T725C", "BTV-DL09", "BAH-AL00", "SHT-AL09", "DBY-W09", "BAH3-W59", "MRX-W09", "MRR-W29", "SCM-W09", "MRX-W29", "SCM-W09", "MRX-W29", "SCM-W09", "MRX-W29", "SCMR-W09", "TAS-AN00", "BRT-W09", "KJR-W09", "WGR-W09", "BAH3-W09", "AGM3-W09HN", "VRD-W09", "VRD-W10", "KRJ-W09", "BTV-W09", "PA2170", "GOT-W09", "OPD2101", "AGS2-W09HN", "JDN2-W09HN", "JDN2-W09", "BAH2-W09", "CMR-W09", "BAH2-W09", "KRJ2-W09", "AGS3-W00E", "AGS2-W09", "BAH4-W29", "M2012K11AC", "BAH4-W29", "SHT-W09", "LSA-AN00", "GOT-W29", "MRX-W19", "MRX-W39", "SHT-W09", "GOT-W29", "MRX-W19", "MRX-W39", "HEY-W09");
    private Context a;

    public e(Context context) {
        this.a = context;
    }

    private boolean a() {
        try {
            String bluetoothName = CoreInfo.Device.getBluetoothName(this.a);
            if (!bluetoothName.contains("\u5e73\u677f") && !bluetoothName.contains("Pad")) {
                if (!bluetoothName.contains("Tab")) {
                    return false;
                }
            }
            return true;
        } catch (Exception e2) {
            String str = "bluetoothNameCheck\u5f02\u5e38" + e2.getMessage();
            return false;
        }
    }

    private boolean c() {
        try {
            return b.contains(BaseInfo.getDeviceModel());
        } catch (Exception e2) {
            String str = "modelListCheck\u5f02\u5e38" + e2.getMessage();
            return false;
        }
    }

    public String b() {
        return (d() || c() || a()) ? "1" : "0";
    }

    public boolean d() {
        try {
            boolean z = (this.a.getResources().getConfiguration().screenLayout & 15) >= 3;
            Display defaultDisplay = ((WindowManager) this.a.getSystemService("window")).getDefaultDisplay();
            DisplayMetrics displayMetrics = new DisplayMetrics();
            defaultDisplay.getMetrics(displayMetrics);
            return z || Math.sqrt(Math.pow((double) (((float) displayMetrics.widthPixels) / displayMetrics.xdpi), 2.0d) + Math.pow((double) (((float) displayMetrics.heightPixels) / displayMetrics.ydpi), 2.0d)) >= 7.0d;
        } catch (Exception unused) {
            return false;
        }
    }
}
