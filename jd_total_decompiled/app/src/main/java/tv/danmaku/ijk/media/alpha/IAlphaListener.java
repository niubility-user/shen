package tv.danmaku.ijk.media.alpha;

/* loaded from: classes11.dex */
public interface IAlphaListener {

    /* loaded from: classes11.dex */
    public interface OnEventListener {
        public static final int EVENT_COMPLETED = 2;
        public static final int EVENT_CONFIG_READY = 0;
        public static final int EVENT_DESTROY = 3;
        public static final int EVENT_DOWNLOAD_COMPLETE = 11;
        public static final int EVENT_DOWNLOAD_COMPLETE_LOCAL = 12;
        public static final int EVENT_DOWNLOAD_ERROR = 13;
        public static final int EVENT_DOWNLOAD_START = 10;
        public static final int EVENT_START = 1;

        void onEvent(int i2);
    }

    void onFailed(int i2, String str);

    void onVideoComplete();

    boolean onVideoConfigReady(AlphaConfig alphaConfig);

    void onVideoDestroy();

    void onVideoRender(int i2, AlphaConfig alphaConfig);

    void onVideoStart();
}
