package com.getkeepsafe.relinker;

import android.os.Build;
import com.getkeepsafe.relinker.c;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes12.dex */
public final class e implements c.b {
    @Override // com.getkeepsafe.relinker.c.b
    public void loadLibrary(String str) {
        System.loadLibrary(str);
    }

    @Override // com.getkeepsafe.relinker.c.b
    public void loadPath(String str) {
        System.load(str);
    }

    @Override // com.getkeepsafe.relinker.c.b
    public String mapLibraryName(String str) {
        return (str.startsWith("lib") && str.endsWith(".so")) ? str : System.mapLibraryName(str);
    }

    @Override // com.getkeepsafe.relinker.c.b
    public String[] supportedAbis() {
        if (Build.VERSION.SDK_INT >= 21) {
            String[] strArr = Build.SUPPORTED_ABIS;
            if (strArr.length > 0) {
                return strArr;
            }
        }
        String str = Build.CPU_ABI2;
        return !f.a(str) ? new String[]{Build.CPU_ABI, str} : new String[]{Build.CPU_ABI};
    }

    @Override // com.getkeepsafe.relinker.c.b
    public String unmapLibraryName(String str) {
        return str.substring(3, str.length() - 3);
    }
}
