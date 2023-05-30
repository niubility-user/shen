package tv.danmaku.ijk.media.alpha.download;

import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import tv.danmaku.ijk.media.domain.UpdateAlphaAnimListUseCase;
import tv.danmaku.ijk.media.domain.entity.AlphaAnimBean;
import tv.danmaku.ijk.media.utils.PlayerNetworkUtil;

/* loaded from: classes11.dex */
public class AlphaResourceManager {
    public static String DEFAULT_ANIM_CACHE_PATH = Environment.getExternalStorageState() + "/JDAlphaVideo/";
    public static long mServerTimestamp = System.currentTimeMillis();
    private final Context mContext;
    private UpdateAlphaAnimListUseCase updateAlphaAnimListUseCase = new UpdateAlphaAnimListUseCase();
    private List<AlphaAnimBean> mAnimInfoList = new CopyOnWriteArrayList();
    private final Queue<AlphaAnimBean> mNeededDownloadResList = new ConcurrentLinkedQueue();
    private Handler uiHandler = new Handler(Looper.getMainLooper());
    private final ExecutorService mPreDownloadExecutor = Executors.newSingleThreadExecutor();
    private final ExecutorService mAnimDownloadExecutor = Executors.newSingleThreadExecutor();

    /* loaded from: classes11.dex */
    public interface OnAlphaResManagerCallback {
        void onResLoaded(String str);
    }

    public AlphaResourceManager(Context context) {
        this.mContext = context;
        loadGlobalResourceInfo();
        if (context != null) {
            DEFAULT_ANIM_CACHE_PATH = context.getExternalCacheDir().getAbsolutePath() + "/JDAlphaVideo/";
        }
        new File(DEFAULT_ANIM_CACHE_PATH).mkdirs();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void downloadResIfNeed() {
        AlphaAnimBean poll;
        while (PlayerNetworkUtil.isWifiConnected(this.mContext) && !this.mNeededDownloadResList.isEmpty() && (poll = this.mNeededDownloadResList.poll()) != null) {
            FutureTask futureTask = new FutureTask(new AlphaResDownloadCallable(poll));
            this.mPreDownloadExecutor.execute(futureTask);
            try {
                String str = (String) futureTask.get();
                if (!TextUtils.isEmpty(str)) {
                    poll.setLocalCachePath(str);
                    updateAnimList(poll);
                }
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            } catch (ExecutionException e3) {
                e3.printStackTrace();
            }
        }
        this.mPreDownloadExecutor.shutdown();
    }

    private void loadGlobalResourceInfo() {
        if (this.updateAlphaAnimListUseCase == null) {
            this.updateAlphaAnimListUseCase = new UpdateAlphaAnimListUseCase();
        }
        this.updateAlphaAnimListUseCase.execute(new UpdateAlphaAnimListUseCase.UpdateAlphaAnimReq(), new UpdateAlphaAnimListUseCase.UpdateAlphaAnimCallback() { // from class: tv.danmaku.ijk.media.alpha.download.AlphaResourceManager.1
            @Override // tv.danmaku.ijk.media.domain.UpdateAlphaAnimListUseCase.UpdateAlphaAnimCallback
            public void onGetAnimList(List<AlphaAnimBean> list, long j2) {
                if (list == null || list.isEmpty()) {
                    return;
                }
                AlphaResourceManager.this.mAnimInfoList = list;
                for (AlphaAnimBean alphaAnimBean : list) {
                    if (alphaAnimBean.isPreload()) {
                        AlphaResourceManager.this.mNeededDownloadResList.add(alphaAnimBean);
                    }
                }
                AlphaResourceManager.this.downloadResIfNeed();
                if (j2 != 0) {
                    AlphaResourceManager.mServerTimestamp = j2;
                }
            }
        });
    }

    private void notifyResult(final String str, final OnAlphaResManagerCallback onAlphaResManagerCallback) {
        Handler handler = this.uiHandler;
        if (handler == null || onAlphaResManagerCallback == null) {
            return;
        }
        handler.post(new Runnable() { // from class: tv.danmaku.ijk.media.alpha.download.AlphaResourceManager.2
            @Override // java.lang.Runnable
            public void run() {
                onAlphaResManagerCallback.onResLoaded(str);
            }
        });
    }

    private void updateAnimList(AlphaAnimBean alphaAnimBean) {
        if (this.mAnimInfoList.contains(alphaAnimBean)) {
            List<AlphaAnimBean> list = this.mAnimInfoList;
            list.set(list.indexOf(alphaAnimBean), alphaAnimBean);
        }
    }

    public void loadAnimByName(String str, OnAlphaResManagerCallback onAlphaResManagerCallback) {
        List<AlphaAnimBean> list = this.mAnimInfoList;
        if (list == null || list.isEmpty()) {
            return;
        }
        AlphaAnimBean alphaAnimBean = new AlphaAnimBean();
        Iterator<AlphaAnimBean> it = this.mAnimInfoList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            AlphaAnimBean next = it.next();
            if (next.getAnimName().equals(str)) {
                alphaAnimBean = next;
                break;
            }
        }
        if (TextUtils.isEmpty(alphaAnimBean.getLocalCachePath())) {
            FutureTask futureTask = new FutureTask(new AlphaResDownloadCallable(alphaAnimBean));
            this.mAnimDownloadExecutor.execute(futureTask);
            try {
                String str2 = (String) futureTask.get();
                if (TextUtils.isEmpty(str2)) {
                    return;
                }
                alphaAnimBean.setLocalCachePath(str2);
                updateAnimList(alphaAnimBean);
                notifyResult(str2, onAlphaResManagerCallback);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            } catch (ExecutionException e3) {
                e3.printStackTrace();
            }
        } else if (TextUtils.isEmpty(alphaAnimBean.getLocalCachePath())) {
        } else {
            notifyResult(alphaAnimBean.getLocalCachePath(), onAlphaResManagerCallback);
        }
    }
}
