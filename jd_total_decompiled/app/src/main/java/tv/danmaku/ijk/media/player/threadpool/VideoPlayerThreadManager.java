package tv.danmaku.ijk.media.player.threadpool;

import android.os.Handler;
import android.os.HandlerThread;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes11.dex */
public final class VideoPlayerThreadManager {
    private static final int CORE_POOL_SIZE = 4;
    private static final int KEEP_ALIVE_TIME = 20;
    private static final int MAX_POOL_SIZE = 8;
    private static final int WORK_QUEUE_SIZE = 16;
    private static final Handler mPlayerReleaseHandler;
    private static final ThreadPoolExecutor sThreadPool = new ThreadPoolExecutor(4, 8, 20, TimeUnit.SECONDS, new ArrayBlockingQueue(16), new DefaultThreadFactory("Player_Thread_Pool", 2));

    static {
        HandlerThread handlerThread = new HandlerThread("PlayerReleaseThread");
        handlerThread.start();
        mPlayerReleaseHandler = new Handler(handlerThread.getLooper());
    }

    private VideoPlayerThreadManager() {
    }

    public static void addTask(Runnable runnable) {
        Handler handler = mPlayerReleaseHandler;
        if (handler == null || runnable == null) {
            return;
        }
        handler.post(runnable);
    }

    public static ThreadPoolExecutor getThreadPool() {
        return sThreadPool;
    }
}
