package com.jd.stat.common.relinker;

import android.os.Build;
import android.text.TextUtils;
import com.jd.stat.common.relinker.b;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes18.dex */
public final class d implements b.InterfaceC0220b {
    @Override // com.jd.stat.common.relinker.b.InterfaceC0220b
    public void a(String str) {
        System.loadLibrary(str);
    }

    @Override // com.jd.stat.common.relinker.b.InterfaceC0220b
    public void b(String str) {
        System.load(str);
    }

    @Override // com.jd.stat.common.relinker.b.InterfaceC0220b
    public String c(String str) {
        return (str.startsWith("lib") && str.endsWith(".so")) ? str : System.mapLibraryName(str);
    }

    @Override // com.jd.stat.common.relinker.b.InterfaceC0220b
    public String d(String str) {
        return str.substring(3, str.length() - 3);
    }

    @Override // com.jd.stat.common.relinker.b.InterfaceC0220b
    public String[] a() {
        if (Build.VERSION.SDK_INT >= 21) {
            String[] strArr = Build.SUPPORTED_ABIS;
            if (strArr.length > 0) {
                return strArr;
            }
        }
        String str = Build.CPU_ABI2;
        return !TextUtils.isEmpty(str) ? new String[]{Build.CPU_ABI, str} : new String[]{Build.CPU_ABI};
    }
}
