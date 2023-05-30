package tv.danmaku.ijk.media.domain;

import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import com.jd.framework.json.JDJSON;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import tv.danmaku.ijk.media.domain.base.BaseCallback;
import tv.danmaku.ijk.media.domain.base.BaseReq;
import tv.danmaku.ijk.media.domain.base.BaseUseCase;
import tv.danmaku.ijk.media.domain.base.ConsumerSet;
import tv.danmaku.ijk.media.domain.entity.AlphaAnimBean;
import tv.danmaku.ijk.media.domain.entity.AlphaAnimListBean;
import tv.danmaku.ijk.media.ext.auth.bean.BaseBean;

/* loaded from: classes11.dex */
public class UpdateAlphaAnimListUseCase extends BaseUseCase<UpdateAlphaAnimReq, UpdateAlphaAnimCallback, BaseBean<AlphaAnimListBean>> {
    private static final int MAX_RETRY = 3;
    private int retryCount = 0;
    private Timer mCheckTimer = new Timer();

    /* loaded from: classes11.dex */
    public interface UpdateAlphaAnimCallback extends BaseCallback {
        void onGetAnimList(List<AlphaAnimBean> list, long j2);
    }

    /* loaded from: classes11.dex */
    public static class UpdateAlphaAnimReq extends BaseReq {
        @Override // tv.danmaku.ijk.media.domain.base.BaseReq
        public Map<String, Object> getParams() {
            return null;
        }

        @Override // tv.danmaku.ijk.media.domain.base.BaseReq
        public int getRequestMethod() {
            return 1;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void releaseTimer() {
        Timer timer = this.mCheckTimer;
        if (timer != null) {
            try {
                timer.cancel();
                this.mCheckTimer = null;
                this.retryCount = 0;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void retry() {
        if (this.retryCount >= 3) {
            releaseTimer();
            return;
        }
        this.mCheckTimer.schedule(new TimerTask() { // from class: tv.danmaku.ijk.media.domain.UpdateAlphaAnimListUseCase.2
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                UpdateAlphaAnimListUseCase.this.execute();
            }
        }, 3000L);
        this.retryCount++;
    }

    @Override // tv.danmaku.ijk.media.domain.base.BaseUseCase
    protected ConsumerSet<String> buildConsumer() {
        return new ConsumerSet<String>() { // from class: tv.danmaku.ijk.media.domain.UpdateAlphaAnimListUseCase.1
            @Override // tv.danmaku.ijk.media.domain.base.ConsumerSet
            public void onError(HttpError httpError) {
                UpdateAlphaAnimListUseCase.this.retry();
            }

            @Override // tv.danmaku.ijk.media.domain.base.ConsumerSet
            public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
            }

            @Override // tv.danmaku.ijk.media.domain.base.ConsumerSet
            public void onSuccess(String str) {
                BaseBean baseBean = (BaseBean) JDJSON.parseObject(str, new TypeReference<BaseBean<AlphaAnimListBean>>() { // from class: tv.danmaku.ijk.media.domain.UpdateAlphaAnimListUseCase.1.1
                }, new Feature[0]);
                if (baseBean == null || !baseBean.isSuccess()) {
                    UpdateAlphaAnimListUseCase.this.retry();
                    return;
                }
                if (((BaseUseCase) UpdateAlphaAnimListUseCase.this).mCallback != null && baseBean.data != 0) {
                    ((UpdateAlphaAnimCallback) ((BaseUseCase) UpdateAlphaAnimListUseCase.this).mCallback).onGetAnimList(((AlphaAnimListBean) baseBean.data).getResult(), ((AlphaAnimListBean) baseBean.data).getTimestamp());
                }
                UpdateAlphaAnimListUseCase.this.releaseTimer();
            }
        };
    }

    @Override // tv.danmaku.ijk.media.domain.base.BaseUseCase
    protected String buildFunctionId() {
        return "animaConfig";
    }

    @Override // tv.danmaku.ijk.media.domain.base.BaseUseCase
    public void release() {
        super.release();
        releaseTimer();
    }
}
