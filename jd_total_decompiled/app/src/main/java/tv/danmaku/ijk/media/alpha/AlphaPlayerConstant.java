package tv.danmaku.ijk.media.alpha;

/* loaded from: classes11.dex */
public class AlphaPlayerConstant {
    public static final int SUCCESS = 0;

    /* loaded from: classes11.dex */
    public interface ERR_CODE {
        public static final int TYPE_CONFIG_PLUGIN_MIX = 10006;
        public static final int TYPE_CREATE_RENDER = 10004;
        public static final int TYPE_CREATE_THREAD = 10003;
        public static final int TYPE_DECODE_EXC = 10002;
        public static final int TYPE_EXTRACTOR_EXC = 10001;
        public static final int TYPE_FILE_ERROR = 10007;
        public static final int TYPE_HEVC_NOT_SUPPORT = 10008;
        public static final int TYPE_PARSE_CONFIG = 10005;
    }

    /* loaded from: classes11.dex */
    public interface ERR_MSG {
        public static final String DETAIL_CONFIG_PLUGIN_MIX = "0x6 vapx fail";
        public static final String DETAIL_CREATE_RENDER = "0x4 render create fail";
        public static final String DETAIL_CREATE_THREAD = "0x3 thread create fail";
        public static final String DETAIL_DECODE_EXC = "0x2 MediaCodec exception";
        public static final String DETAIL_EXTRACTOR_EXC = "0x1 MediaExtractor exception";
        public static final String DETAIL_FILE_ERROR = "0x7 file can't read";
        public static final String DETAIL_HEVC_NOT_SUPPORT = "0x8 hevc not support";
        public static final String DETAIL_PARSE_CONFIG = "0x5 parse config fail";
    }

    /* loaded from: classes11.dex */
    public interface ORIENTATION {
        public static final int DEFAULT = 0;
        public static final int LANDSCAPE = 2;
        public static final int PORTRAIT = 1;
    }

    public static String getErrorMsg(int i2) {
        switch (i2) {
            case 10001:
                return ERR_MSG.DETAIL_EXTRACTOR_EXC;
            case 10002:
                return ERR_MSG.DETAIL_DECODE_EXC;
            case 10003:
                return ERR_MSG.DETAIL_CREATE_THREAD;
            case 10004:
                return ERR_MSG.DETAIL_CREATE_RENDER;
            case 10005:
                return ERR_MSG.DETAIL_PARSE_CONFIG;
            case 10006:
                return ERR_MSG.DETAIL_CONFIG_PLUGIN_MIX;
            case 10007:
                return ERR_MSG.DETAIL_FILE_ERROR;
            case 10008:
                return ERR_MSG.DETAIL_HEVC_NOT_SUPPORT;
            default:
                return "unknown";
        }
    }
}
