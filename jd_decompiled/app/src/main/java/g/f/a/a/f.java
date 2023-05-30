package g.f.a.a;

import com.jd.stat.network.ExceptionEnum;
import com.jingdong.app.mall.bundle.jdrhsdk.api.JDRiskHandleError;
import tv.danmaku.ijk.media.player.IMediaPlayer;

/* loaded from: classes12.dex */
public class f {
    public static String a(int i2) {
        switch (i2) {
            case -2008:
                return "vpr no register";
            case -2007:
                return "vpr speech too short";
            case -2006:
                return "vpr no support model";
            case -2005:
                return "vpr username too long";
            case JDRiskHandleError.CODE_SDK_NOT_INIT /* -2004 */:
                return "vpr server error";
            case JDRiskHandleError.CODE_CHECK_HTTP_RESPONSE_ERROR /* -2003 */:
                return "speech sample rate or channel error";
            case JDRiskHandleError.CODE_CHECK_ERROR_FIND_ZERO /* -2002 */:
                return "Speech data is too long";
            case -2001:
                return "no speech data input";
            default:
                switch (i2) {
                    case -1014:
                        return "jd clound gateway error";
                    case -1013:
                        return "vad init error";
                    case -1012:
                        return "speech sample rate or channel error";
                    case -1011:
                        return "speech data file header error";
                    case IMediaPlayer.MEDIA_ERROR_UNSUPPORTED /* -1010 */:
                        return "Client authentication fail";
                    case -1009:
                        return "speech format error";
                    case -1008:
                        return "Client param error";
                    case IMediaPlayer.MEDIA_ERROR_MALFORMED /* -1007 */:
                        return "Speech server error";
                    case -1006:
                        return "no speech data input";
                    case ExceptionEnum.CANCELLED /* -1005 */:
                        return "Speech data is too long";
                    case -1004:
                        return "Network connect timeout";
                    case -1003:
                        return "Network connect failed";
                    case -1002:
                        return "Permission exception";
                    case -1001:
                        return "Recorder invalid";
                    default:
                        return "Speech recognition success";
                }
        }
    }
}
