package tv.danmaku.ijk.media.utils;

import com.jingdong.app.mall.bundle.jdrhsdk.api.JDRiskHandleError;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;
import tv.danmaku.ijk.media.player.misc.IjkMediaFormat;

/* loaded from: classes11.dex */
public class MediaInfoUtil {
    public static final String AMIME_VIDEO_AVC = "video/avc";
    public static final String AMIME_VIDEO_H264 = "video/3gpp";
    public static final String AMIME_VIDEO_HEVC = "video/hevc";
    public static final String AMIME_VIDEO_MPEG4 = "video/mp4v-es";
    public static final String AMIME_VIDEO_VP8 = "video/x-vnd.on2.vp8";
    public static final String AMIME_VIDEO_VP9 = "video/x-vnd.on2.vp9";
    public static final int NONET_ERROR = 3;
    public static final int TIMEOUT_ERROR = 4;

    public static String getDecoderTypeByMine(String str) {
        str.hashCode();
        return !str.equals(AMIME_VIDEO_HEVC) ? !str.equals(AMIME_VIDEO_AVC) ? "unknown" : IjkMediaFormat.CODEC_NAME_H264 : "h265";
    }

    public static String getErrorMessage(int i2) {
        return i2 != -1010 ? i2 != -1007 ? i2 != -1004 ? i2 != -110 ? i2 != 1 ? i2 != 100 ? i2 != 200 ? i2 != 3 ? i2 != 4 ? "video player error" : "TIME_OUT_ERROR" : "NET_UNAVAILABLE_ERROR" : "MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK" : "MEDIA_ERROR_SERVER_DIED" : "MEDIA_ERROR_UNKNOWN" : "MEDIA_ERROR_TIMED_OUT" : "MEDIA_ERROR_IO" : "MEDIA_ERROR_MALFORMED" : "MEDIA_ERROR_UNSUPPORTED";
    }

    public static String getFFmpegErrStrByCode(int i2, int i3) {
        if (i2 != -10000) {
            return "unknown";
        }
        switch (i3) {
            case -1668179714:
                return "AVERROR_OUTPUT_CHANGED";
            case -1668179713:
                return "AVERROR_INPUT_CHANGED";
            case IjkMediaPlayer.AVERROR_HTTP_SERVER_ERROR /* -1482175992 */:
                return "AVERROR_HTTP_SERVER_ERROR";
            case IjkMediaPlayer.AVERROR_HTTP_OTHER_4XX /* -1482175736 */:
                return "AVERROR_HTTP_OTHER_4XX";
            case -1481985528:
                return "AVERROR_MUXER_NOT_FOUND";
            case -1414549496:
                return "AVERROR_OPTION_NOT_FOUND";
            case -1414092869:
                return "AVERROR_EXIT";
            case -1397118274:
                return "AVERROR_BUFFER_TOO_SMALL";
            case -1381258232:
                return "AVERROR_STREAM_NOT_FOUND";
            case -1330794744:
                return "AVERROR_PROTOCOL_NOT_FOUND";
            case IjkMediaPlayer.AVERROR_UNKNOWN /* -1313558101 */:
                return "AVERROR_UNKNOWN";
            case -1296385272:
                return "AVERROR_DEMUXER_NOT_FOUND";
            case -1279870712:
                return "AVERROR_FILTER_NOT_FOUND";
            case -1179861752:
                return "AVERROR_BSF_NOT_FOUND";
            case -1163346256:
                return "AVERROR_PATCHWELCOME";
            case -1129203192:
                return "AVERROR_ENCODER_NOT_FOUND";
            case -1128613112:
                return "AVERROR_DECODER_NOT_FOUND";
            case -1094995529:
                return "AVERROR_INVALIDDATA";
            case IjkMediaPlayer.AVERROR_HTTP_NOT_FOUND /* -875574520 */:
                return "AVERROR_HTTP_NOT_FOUND";
            case IjkMediaPlayer.AVERROR_HTTP_FORBIDDEN /* -858797304 */:
                return "AVERROR_HTTP_FORBIDDEN";
            case IjkMediaPlayer.AVERROR_HTTP_UNAUTHORIZED /* -825242872 */:
                return "AVERROR_HTTP_UNAUTHORIZED";
            case IjkMediaPlayer.AVERROR_HTTP_BAD_REQUEST /* -808465656 */:
                return "AVERROR_HTTP_BAD_REQUEST";
            case -733130664:
                return "AVERROR_EXPERIMENTAL";
            case -558323010:
                return "AVERROR_BUG";
            case -542398533:
                return "AVERROR_EXTERNAL";
            case -541545794:
                return "AVERROR_BUG2";
            case -541478725:
                return "AVERROR_EOF";
            case -2005:
                return "DNS_TIMEOUT";
            case JDRiskHandleError.CODE_SDK_NOT_INIT /* -2004 */:
                return "DNS_ERROR";
            case -1009:
                return "MediaCodec decode failed";
            case -1004:
                return "IO_INTERRUPT";
            case -1003:
                return "TCP_WRITE_TIMEOUT";
            case -1002:
                return "TCP_READ_TIMEOUT";
            case -1001:
                return "TCP_CONNECT_TIMEOUT";
            case -5:
                return "I/O error";
            default:
                return "unknown";
        }
    }

    public static boolean isDnsError(int i2) {
        return i2 == -2004 || i2 == -2005;
    }
}
