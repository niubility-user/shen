package tv.danmaku.ijk.media.ext.config;

import android.os.Build;
import android.os.Process;
import java.io.Serializable;
import tv.danmaku.ijk.media.ext.dynamic.LibUtils;

/* loaded from: classes11.dex */
public class DynamicLibInfoBean implements Serializable {
    private ArchLibInfo arm64Arch;
    private ArchLibInfo armv7aArch;
    private boolean enable;

    /* loaded from: classes11.dex */
    public static class ArchLibInfo implements Serializable {
        private String downloadUrl;
        private String keySoMd5;
        private String zipFileMd5;

        public String getDownloadUrl() {
            return this.downloadUrl;
        }

        public String getKeySoMd5() {
            return this.keySoMd5;
        }

        public String getZipFileMd5() {
            return this.zipFileMd5;
        }

        public void setDownloadUrl(String str) {
            this.downloadUrl = str;
        }

        public void setKeySoMd5(String str) {
            this.keySoMd5 = str;
        }

        public void setZipFileMd5(String str) {
            this.zipFileMd5 = str;
        }
    }

    public ArchLibInfo getArchInfo() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (Process.is64Bit()) {
                return this.arm64Arch;
            }
            if (LibUtils.getCurrentAbi() == LibUtils.AbiType.ARMEABI_V7A) {
                return this.armv7aArch;
            }
            return null;
        }
        return null;
    }

    public boolean isEnable() {
        return this.enable;
    }

    public void setArm64Arch(ArchLibInfo archLibInfo) {
        this.arm64Arch = archLibInfo;
    }

    public void setArmv7aArch(ArchLibInfo archLibInfo) {
        this.armv7aArch = archLibInfo;
    }

    public void setEnable(boolean z) {
        this.enable = z;
    }
}
