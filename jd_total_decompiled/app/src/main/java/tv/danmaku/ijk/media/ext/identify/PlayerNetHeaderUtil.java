package tv.danmaku.ijk.media.ext.identify;

import com.jingdong.jdsdk.constant.CartConstant;

/* loaded from: classes11.dex */
public class PlayerNetHeaderUtil {
    private static HostAppInfo mHostAppInfo = null;
    public static int uaEnable = 1;

    /* loaded from: classes11.dex */
    public enum PlayerType {
        IJK_SH("ijkplayerSH"),
        IJK_BJ("ijkplayerBJ"),
        MEDIA_SH("mediaplayerSH"),
        MEDIA_BJ("mediaplayerBJ");
        
        private final String type;

        PlayerType(String str) {
            this.type = str;
        }

        public String getType() {
            return this.type;
        }
    }

    public static boolean canUse() {
        return uaEnable == 1;
    }

    public static String generateReferer(PlayerType playerType) {
        if (mHostAppInfo == null) {
            return "";
        }
        return "Play:" + playerType.getType() + CartConstant.KEY_YB_INFO_LINK + mHostAppInfo.getCurClsName();
    }

    public static String generateUA() {
        if (mHostAppInfo == null) {
            return "";
        }
        return " ffmpeg/4.0;" + mHostAppInfo.getAppName() + ";android;version/" + mHostAppInfo.getVersionName() + ";build/" + mHostAppInfo.getVersionCode();
    }

    public static void injectAppInfo(HostAppInfo hostAppInfo) {
        mHostAppInfo = hostAppInfo;
    }
}
