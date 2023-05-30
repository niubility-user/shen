package tv.danmaku.ijk.media.domain.base;

import androidx.core.util.Consumer;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;

/* loaded from: classes11.dex */
public abstract class ConsumerSet<T> {
    Consumer<HttpGroup.HttpSettingParams> onReady = new Consumer<HttpGroup.HttpSettingParams>() { // from class: tv.danmaku.ijk.media.domain.base.ConsumerSet.1
        @Override // androidx.core.util.Consumer
        public void accept(HttpGroup.HttpSettingParams httpSettingParams) {
            ConsumerSet.this.onReady(httpSettingParams);
        }
    };
    Consumer<T> onSuccess = new Consumer<T>() { // from class: tv.danmaku.ijk.media.domain.base.ConsumerSet.2
        @Override // androidx.core.util.Consumer
        public void accept(T t) {
            ConsumerSet.this.onSuccess(t);
        }
    };
    Consumer<HttpError> onError = new Consumer<HttpError>() { // from class: tv.danmaku.ijk.media.domain.base.ConsumerSet.3
        @Override // androidx.core.util.Consumer
        public void accept(HttpError httpError) {
            ConsumerSet.this.onError(httpError);
        }
    };

    public abstract void onError(HttpError httpError);

    public abstract void onReady(HttpGroup.HttpSettingParams httpSettingParams);

    public abstract void onSuccess(T t);
}
