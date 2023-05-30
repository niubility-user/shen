package tv.danmaku.ijk.media.player.threadpool;

import java.util.concurrent.ThreadFactory;

/* loaded from: classes11.dex */
public class DefaultThreadFactory implements ThreadFactory {
    private static final String DEFAULT_NAME = "VideoPlayerThreadFactory";
    private static final int DEFAULT_PRIORITY = 5;
    private boolean isDaemon;
    private volatile int mIndex;
    private String mName;
    private int mPriority;

    public DefaultThreadFactory(String str, int i2, boolean z) {
        this.mIndex = 0;
        this.mName = DEFAULT_NAME;
        this.mPriority = 5;
        this.isDaemon = true;
        if (str != null) {
            String trim = str.trim();
            if (trim.length() > 0) {
                this.mName = trim;
            }
        }
        if (i2 < 1 || i2 > 10) {
            this.mPriority = 5;
        }
        this.mPriority = i2;
        this.isDaemon = z;
    }

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(Runnable runnable) {
        this.mIndex++;
        Thread thread = new Thread(runnable, this.mName + "-" + this.mIndex);
        thread.setPriority(this.mPriority);
        thread.setDaemon(this.isDaemon);
        return thread;
    }

    public DefaultThreadFactory(String str, int i2) {
        this(str, i2, true);
    }

    public DefaultThreadFactory(String str) {
        this(str, 5);
    }

    public DefaultThreadFactory() {
        this(DEFAULT_NAME);
    }
}
