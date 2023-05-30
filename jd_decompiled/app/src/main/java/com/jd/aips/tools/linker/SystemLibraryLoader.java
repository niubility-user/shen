package com.jd.aips.tools.linker;

import android.annotation.SuppressLint;
import android.os.Build;
import android.text.TextUtils;
import com.jd.aips.tools.linker.SafeLinker;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes12.dex */
public final class SystemLibraryLoader implements SafeLinker.LibraryLoader {
    @Override // com.jd.aips.tools.linker.SafeLinker.LibraryLoader
    public void loadLibrary(String str) {
        System.loadLibrary(str);
    }

    @Override // com.jd.aips.tools.linker.SafeLinker.LibraryLoader
    @SuppressLint({"UnsafeDynamicallyLoadedCode"})
    public void loadPath(String str) {
        System.load(str);
    }

    @Override // com.jd.aips.tools.linker.SafeLinker.LibraryLoader
    public String mapLibraryName(String str) {
        return (str.startsWith("lib") && str.endsWith(".so")) ? str : System.mapLibraryName(str);
    }

    @Override // com.jd.aips.tools.linker.SafeLinker.LibraryLoader
    public String[] supportedAbis() {
        if (Build.VERSION.SDK_INT >= 21) {
            String[] strArr = Build.SUPPORTED_ABIS;
            if (strArr.length > 0) {
                return strArr;
            }
        }
        String str = Build.CPU_ABI2;
        return !TextUtils.isEmpty(str) ? new String[]{Build.CPU_ABI, str} : new String[]{Build.CPU_ABI};
    }

    @Override // com.jd.aips.tools.linker.SafeLinker.LibraryLoader
    public String unmapLibraryName(String str) {
        return str.substring(3, str.length() - 3);
    }
}
