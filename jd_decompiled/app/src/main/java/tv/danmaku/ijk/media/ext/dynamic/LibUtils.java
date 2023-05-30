package tv.danmaku.ijk.media.ext.dynamic;

import android.os.Build;

/* loaded from: classes11.dex */
public class LibUtils {

    /* loaded from: classes11.dex */
    public enum AbiType {
        ARMEABI("armeabi"),
        ARMEABI_V7A("armeabi-v7a"),
        ARM64_V8A("arm64-v8a"),
        X86("x86"),
        X86_64("x86_64"),
        MIPS("mips"),
        MIPS64("mips64");
        
        private String abiName;

        AbiType(String str) {
            this.abiName = str;
        }

        public static AbiType getAbi(String str) {
            if (str == null) {
                return null;
            }
            for (AbiType abiType : values()) {
                if (abiType.getAbiName().equals(str.toLowerCase())) {
                    return abiType;
                }
            }
            return null;
        }

        public String getAbiName() {
            return this.abiName;
        }

        public void setAbiName(String str) {
            this.abiName = str;
        }
    }

    public static AbiType getCurrentAbi() {
        return AbiType.getAbi(Build.CPU_ABI);
    }
}
