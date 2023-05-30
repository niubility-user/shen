package jd.wjlogin_sdk.relinker;

import android.os.Build;
import android.text.TextUtils;
import jd.wjlogin_sdk.relinker.b;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class d implements b.InterfaceC0850b {
    @Override // jd.wjlogin_sdk.relinker.b.InterfaceC0850b
    public void a(String str) {
        System.loadLibrary(str);
    }

    @Override // jd.wjlogin_sdk.relinker.b.InterfaceC0850b
    public String b(String str) {
        return str.substring(3, str.length() - 3);
    }

    @Override // jd.wjlogin_sdk.relinker.b.InterfaceC0850b
    public void c(String str) {
        System.load(str);
    }

    @Override // jd.wjlogin_sdk.relinker.b.InterfaceC0850b
    public String d(String str) {
        return (str.startsWith("lib") && str.endsWith(".so")) ? str : System.mapLibraryName(str);
    }

    @Override // jd.wjlogin_sdk.relinker.b.InterfaceC0850b
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
