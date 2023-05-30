package tv.danmaku.ijk.media;

/* loaded from: classes11.dex */
public class JDPlayerConstant {
    public static int CACHE_IO_THREAD_COUNT = 2;
    public static final String HTTP_HOOK_HEAD = "ijkhttphook:";
    public static final String IJK_CACHE_HEAD = "ijkio:cache:ffio:";
    public static final String IJK_CACHE_INDEX_SUFFIX = ".i";
    public static final String IJK_CACHE_SUPPORT_TYPE = "video/mp4";
    public static final String IJK_CACHE_VIDEO_SUFFIX = ".v";
    public static final String JDCacheTag = "JDPlayerVideoCache";
    public static final String LIVE_HOOK_HEAD = "ijklivehook:";
    public static final String LIVE_QUICS_HEAD = "quics://";
    public static final String LIVE_QUIC_HEAD = "quic://";
    public static final String LIVE_REPLAY_SUFFIX = ".m3u8";
    public static final String LIVE_RTMPS_HEAD = "rtmps://";
    public static final String LIVE_RTMP_HEAD = "rtmp://";
    public static String LSQUIC_HOST = "quicpro.jd.com";
    public static String LSQUIC_PORT = "443";

    /* loaded from: classes11.dex */
    public interface NetConst {
        public static final String HEADER_REFERER = "Referer";
        public static final String HEADER_UA = "User-Agent";
    }

    /* loaded from: classes11.dex */
    public interface UniformMta {
        public static final String CHID = "1";
        public static final String TYPEID = "4";

        /* loaded from: classes11.dex */
        public interface NewJumpFrom {
            public static final int FROM_EVALUATE = 12;
            public static final int FROM_ORDER_CENTER = 13;
            public static final int FROM_PRODUCT_DETAIL = 11;
            public static final int FROM_PRODUCT_DETAIL_MAIN = 14;
        }

        /* loaded from: classes11.dex */
        public interface OldJumpFrom {
            public static final int JUMP_FROM_EVALUATE = 1;
            public static final int JUMP_FROM_ORDER_CENTER = 5;
            public static final int JUMP_FROM_PRODUCT_DETAIL = 2;
            public static final int JUMP_FROM_PRODUCT_DETAIL_COMMENT = 4;
            public static final int JUMP_FROM_PRODUCT_DETAIL_H5 = 6;
            public static final int JUMP_FROM_PRODUCT_DETAIL_MAIN_PIC = 3;
        }
    }

    /* loaded from: classes11.dex */
    public interface UniformScaleType {
        public static final String SCALETYPE_16_9 = "16:9";
        public static final String SCALETYPE_4_3 = "4:3";
        public static final String SCALETYPE_FILLPARENT = "fillParent";
        public static final String SCALETYPE_FITPARENT = "fitParent";
        public static final String SCALETYPE_FITXY = "fitXY";
        public static final String SCALETYPE_WRAPCONTENT = "wrapContent";
    }

    /* loaded from: classes11.dex */
    public interface VideoError {
        public static final int NONET_ERROR = 3;
        public static final int TIMEOUT_ERROR = 4;
    }
}
