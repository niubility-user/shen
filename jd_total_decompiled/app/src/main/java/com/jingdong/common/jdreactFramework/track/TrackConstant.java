package com.jingdong.common.jdreactFramework.track;

import com.jd.libs.hybrid.offlineload.utils.OfflineExceptionUtils;

/* loaded from: classes5.dex */
public class TrackConstant {

    /* loaded from: classes5.dex */
    public interface ErrorType {
        public static final int BundlePathEmpty = 21;
        public static final int DowngradeToWeb = 7;
        public static final int DownloadFailed = 5;
        public static final int JSBundleFileNotExist = 1;
        public static final int LaunchException = 11;
        public static final int ModuleNameEmpty = 9;
        public static final int ModuleUnavailable = 8;
        public static final int PatchError = 24;
        public static final int ShowRetryView = 12;
        public static final int SoLoadFail = 13;
        public static final int Success = 0;
        public static final int SysVersionBellow16 = 15;
        public static final int UnZipFailed = 6;
        public static final int UnZipInterrupt = 16;
        public static final int UpdateInfoDataParseError = 3;
        public static final int UpdateInfoEmpty = 2;
        public static final int UpdateRequestError = 4;
        public static final int X86_OS = 14;
        public static final int signError = 23;
    }

    /* loaded from: classes5.dex */
    public interface UpdateNode {
        public static final int Download = 2;
        public static final int RequestUpdate = 1;
    }

    public static String getErrorMsg(int i2) {
        switch (i2) {
            case 0:
                return "\u6210\u529f";
            case 1:
                return "jsBundle \u6587\u4ef6\u4e0d\u5b58\u5728";
            case 2:
                return "\u5347\u7ea7\u4fe1\u606f\u4e3a\u7a7a";
            case 3:
                return "\u5347\u7ea7\u6570\u636e\u89e3\u6790\u5931\u8d25";
            case 4:
                return "\u5347\u7ea7\u63a5\u53e3\u8bf7\u6c42\u5931\u8d25";
            case 5:
                return OfflineExceptionUtils.ERR_MSG_NET;
            case 6:
                return OfflineExceptionUtils.ERR_MSG_UNZIP;
            case 7:
            case 10:
            case 12:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 22:
            default:
                return null;
            case 8:
                return "\u6a21\u5757\u914d\u7f6e\u4e86\u964d\u7ea7";
            case 9:
                return "\u6a21\u5757\u540d\u4e3a\u7a7a";
            case 11:
                return "\u542f\u52a8\u5f02\u5e38";
            case 13:
                return "so\u52a0\u8f7d\u5931\u8d25";
            case 14:
                return "x86\u7cfb\u7edf";
            case 15:
                return "API\u4f4e\u4e8e16";
            case 21:
                return "BundlePath \u4e3a\u7a7a";
            case 23:
                return "\u9a8c\u7b7e\u5931\u8d25";
        }
    }
}
