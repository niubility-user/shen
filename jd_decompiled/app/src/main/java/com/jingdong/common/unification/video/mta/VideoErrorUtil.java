package com.jingdong.common.unification.video.mta;

/* loaded from: classes6.dex */
public class VideoErrorUtil {
    public static final int NONET_ERROR = 3;
    public static final int TIMEOUT_ERROR = 4;

    public static String getErrorMessage(int i2) {
        return i2 != -1010 ? i2 != -1007 ? i2 != -1004 ? i2 != -110 ? i2 != 1 ? i2 != 100 ? i2 != 200 ? i2 != 3 ? i2 != 4 ? "video player error" : "TIME_OUT_ERROR" : "NET_UNAVAILABLE_ERROR" : "MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK" : "MEDIA_ERROR_SERVER_DIED" : "MEDIA_ERROR_UNKNOWN" : "MEDIA_ERROR_TIMED_OUT" : "MEDIA_ERROR_IO" : "MEDIA_ERROR_MALFORMED" : "MEDIA_ERROR_UNSUPPORTED";
    }
}
