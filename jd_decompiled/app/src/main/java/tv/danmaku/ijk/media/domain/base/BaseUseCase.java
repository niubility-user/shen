package tv.danmaku.ijk.media.domain.base;

import android.os.Handler;
import android.os.Looper;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.jdsdk.network.JDHttpTookit;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import java.util.Map;
import tv.danmaku.ijk.media.JDPlayerSdk;
import tv.danmaku.ijk.media.domain.base.BaseCallback;
import tv.danmaku.ijk.media.domain.base.BaseReq;

/* loaded from: classes11.dex */
public abstract class BaseUseCase<R extends BaseReq, C extends BaseCallback, D> {
    private static final String AUTH_HOST = "api.m.jd.com";
    private static final String AUTH_HOST_TEST = "api.m.jd.care";
    protected C mCallback;
    protected R mRequestParam;
    protected Handler mUiHandler = new Handler(Looper.getMainLooper());
    private final HttpSetting httpSetting = new HttpSetting();

    protected abstract ConsumerSet<String> buildConsumer();

    protected abstract String buildFunctionId();

    public void execute(R r) {
        execute(r, null);
    }

    public void release() {
    }

    public void execute(R r, C c2) {
        this.mCallback = c2;
        this.mRequestParam = r;
        execute();
    }

    public void execute() {
        if (JDHttpTookit.getEngine() == null) {
            if (buildConsumer() == null || buildConsumer().onError == null) {
                return;
            }
            buildConsumer().onError.accept(new HttpError(new Throwable("JDHttpTookit not initialized yet")));
            return;
        }
        if (JDPlayerSdk.debugEnable) {
            this.httpSetting.setHost("api.m.jd.care");
        } else {
            this.httpSetting.setHost("api.m.jd.com");
        }
        this.httpSetting.setFunctionId(buildFunctionId());
        if (this.mRequestParam.getRequestMethod() == 2) {
            this.httpSetting.setPost(true);
        }
        if (this.mRequestParam.getParams() != null && this.mRequestParam.getParams().size() > 0) {
            for (Map.Entry<String, Object> entry : this.mRequestParam.getParams().entrySet()) {
                if (entry != null) {
                    this.httpSetting.putJsonParam(entry.getKey(), entry.getValue());
                }
            }
        }
        this.httpSetting.setListener(new HttpGroup.OnCommonListener() { // from class: tv.danmaku.ijk.media.domain.base.BaseUseCase.1
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                if (BaseUseCase.this.buildConsumer() == null || BaseUseCase.this.buildConsumer().onSuccess == null) {
                    return;
                }
                BaseUseCase.this.buildConsumer().onSuccess.accept(httpResponse.getString());
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
            public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
            }
        });
        HttpGroupUtils.getHttpGroupaAsynPool().add(this.httpSetting);
    }
}
