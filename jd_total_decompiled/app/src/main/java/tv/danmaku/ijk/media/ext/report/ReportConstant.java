package tv.danmaku.ijk.media.ext.report;

/* loaded from: classes11.dex */
public class ReportConstant {

    /* loaded from: classes11.dex */
    public interface CommonInfo {
        public static final String CH_ID = "chId";
        public static final String DECODER_TYPE = "decoderType";
        public static final String LIVE_ID = "liveId";
        public static final String OCCUR_TIME = "occurTime";
        public static final String PLAYER_TYPE = "playerType";
        public static final String PLAYER_VERSION = "playerVersion";
        public static final String PLAY_MODE = "playMode";
        public static final String PLAY_TYPE = "playType";
        public static final String PLAY_URL = "playUrl";
        public static final String SESSION_ID = "sessionId";
        public static final String STREAM_CONTAINER_TYPE = "streamContainerType";
        public static final String STREAM_IP = "streamIp";
        public static final String TYPE_ID = "typeId";
    }

    /* loaded from: classes11.dex */
    public interface FirstFrame {
        public static final String AUTO_PLAY = "autoPlay";
        public static final String BUFFER_TIME = "bufferTime";
        public static final String CACHE_MODE = "cacheMode";
        public static final String CONNECTION_OPEN_TIME = "connectionOpenTime";
        public static final String DECODE_TIME = "decodeTime";
        public static final String DNS_PARSE_TIME = "dnsParseTime";
        public static final String DNS_PREPARED_TIME = "dnsPreparedTime";
        public static final String FIRST_FRAME_ID = "1";
        public static final String PLAYER_CREATE_TIME = "playerCreateTime";
        public static final String PREPARED_TIME = "onPreparedTime";
        public static final String PROTOCOL_TYPE = "protocolType";
        public static final String REAL_USE_CACHE = "realUseCache";
        public static final String RENDER_TIME = "renderTime";
        public static final String SPEED = "speed";
        public static final String STREAM_PROBE_TIME = "streamProbeTime";
        public static final String TCP_CONNECT_TIME = "tcpConnectTime";
        public static final String TOTAL = "total";
    }

    /* loaded from: classes11.dex */
    public interface PlayStatus {
        public static final String BYTE_COUNT = "byteCount";
        public static final String DECODER_MODE = "decoderMode";
        public static final String DURATION = "duration";
        public static final String DURATION_MILL = "durationMill";
        public static final String END_TIME = "endTime";
        public static final String ERR_CODE = "errCode";
        public static final String LAS_PLAYTIME_INFO = "playTimeInfo";
        public static final String LAS_STUCK_INFO = "newStuckInfo";
        public static final String LAS_SWITCH_COUNT = "switchCnt";
        public static final String LIVE_DROP_MODE = "liveDropMode";
        public static final String PLAY_STATUS_ID = "2";
        public static final String RENDER_SWITCH = "renderSwitch";
        public static final String RENDER_TYPE = "renderType";
        public static final String START_TIME = "startTime";
        public static final String STUCK_INFO = "stuckInfo";
        public static final String VIDEO_ORIGIN_SIZE = "vOriSize";

        /* loaded from: classes11.dex */
        public interface MediaInfo {
            public static final String AUDIO_BIT_RATE = "aBitRate";
            public static final String AUDIO_CODEC_NAME = "aCodecName";
            public static final String AUDIO_CONTAINER = "aContainer";
            public static final String AUDIO_SAMPLE_RATE = "aSampleRate";
            public static final String AUDIO_TRACK = "aTrack";
            public static final String VIDEO_CODEC_NAME = "vCodecName";
        }
    }
}
