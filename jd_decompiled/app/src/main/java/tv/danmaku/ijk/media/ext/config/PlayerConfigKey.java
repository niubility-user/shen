package tv.danmaku.ijk.media.ext.config;

/* loaded from: classes11.dex */
public interface PlayerConfigKey {
    public static final String KEY_GLOBAL_CONFIG = "JDVideoPlayer";

    /* loaded from: classes11.dex */
    public interface AlphaDlConfigKey {
        public static final String KEY_ALPHA_CONFIG = "AlphaPlayConfig";
        public static final String KEY_ENABLE_DL = "enableDL";
    }

    /* loaded from: classes11.dex */
    public interface AudioConfigKey {
        public static final String KEY_AUDIO_CONFIG = "AudioConfig";
        public static final String KEY_ENABLE_EQ = "enableEq";
        public static final String KEY_ENHANCE_RATIO = "enhanceRatio";
        public static final String KEY_MAX_FREQ = "maxFreq";
        public static final String KEY_MIN_FREQ = "minFreq";
    }

    /* loaded from: classes11.dex */
    public interface AuthConfigKey {
        public static final String KEY_AUTH_CONFIG = "AuthConfig";
        public static final String KEY_ENABLE = "enableAuth";
    }

    /* loaded from: classes11.dex */
    public interface BusinessConfigKey {
        public static final String KEY_BUSINESS_CONFIG = "BusinessConfig";
        public static final String KEY_ENABLE_SHARE = "enableSharePlayer";
    }

    /* loaded from: classes11.dex */
    public interface CacheConfigKey {
        public static final String KEY_CACHE_CONFIG = "CacheConfig";
        public static final String KEY_FORCE_CLOSE = "forceClose";
        public static final String KEY_FORCE_CLOSE_PRELOAD = "forceClosePreload";
        public static final String KEY_IO_THREAD_COUNT = "ioThreadCount";
        public static final String KEY_MAX_CACHE_SIZE = "maxCacheSize";
        public static final String KEY_MAX_CACHE_TIME = "maxCacheTime";
        public static final String KEY_PRELOAD_SIZE = "preloadSize";
    }

    /* loaded from: classes11.dex */
    public interface NetConfigKey {
        public static final String KEY_HTTPDNS_ENABLE = "httpDnsEnable";
        public static final String KEY_MCDN_INFO = "mcdnLibInfo";
        public static final String KEY_NET_CONFIG = "NetConfig";
        public static final String KEY_QUIC_BLACKLIST = "quicBlacklist";
        public static final String KEY_QUIC_ENABLE = "quicEnable";
    }

    /* loaded from: classes11.dex */
    public interface OptionsConfigKey {
        public static final String KEY_OPTIONS_COMMON_CONFIG = "commonConfig";
        public static final String KEY_OPTIONS_CONFIG = "AvOptionsConfig";
        public static final String KEY_OPTIONS_LAS_CONFIG = "lasConfig";
        public static final String KEY_OPTIONS_LIVE_CONFIG = "liveConfig";
        public static final String KEY_OPTIONS_QUIC_CONFIG = "quicConfig";
        public static final String KEY_OPTIONS_VOD_CONFIG = "vodConfig";
        public static final String KEY_OPT_CATEGORY = "optCategory";
        public static final String KEY_OPT_CONFIGS = "configs";
    }

    /* loaded from: classes11.dex */
    public interface PlayerGlobalKey {
        public static final String KEY_GLOBAL_CONFIG = "PlayerGlobalConfig";
        public static final String KEY_LOAD_LIB_ONCE = "loadLibOnce";
    }

    /* loaded from: classes11.dex */
    public interface UAConfigKey {
        public static final String KEY_UA_CONFIG = "UAConfig";
        public static final String KEY_UA_SWITCH = "playUASwitch";
    }

    /* loaded from: classes11.dex */
    public interface VsrConfigKey {
        public static final String KEY_ENABLE = "enable";
        public static final String KEY_VSR_CONFIG = "VsrConfig";
    }
}
