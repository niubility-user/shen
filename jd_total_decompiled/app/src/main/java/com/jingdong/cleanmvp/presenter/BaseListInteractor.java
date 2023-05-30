package com.jingdong.cleanmvp.presenter;

import android.content.Context;
import com.jd.framework.json.JDJSON;
import com.jingdong.cleanmvp.common.BaseListEvent;
import com.jingdong.cleanmvp.common.NormalListener;
import com.jingdong.cleanmvp.engine.BaseListState;
import com.jingdong.cleanmvp.engine.HttpGroupUtil;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;

/* loaded from: classes4.dex */
public abstract class BaseListInteractor<T> extends BaseInteractor {
    private BaseListState<T> baseListState = createState();
    protected Context context;
    private NormalListener listener;

    protected BaseListInteractor() {
    }

    protected abstract HttpSetting createHttpSetting(int i2, int i3, HashMap hashMap);

    protected abstract BaseListState<T> createState();

    public void getData(int i2, int i3, final HashMap hashMap) {
        HttpSetting createHttpSetting = createHttpSetting(i2, i3, hashMap);
        if (createHttpSetting != null) {
            createHttpSetting.setListener(new HttpGroup.OnCommonListener() { // from class: com.jingdong.cleanmvp.presenter.BaseListInteractor.1
                {
                    BaseListInteractor.this = this;
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
                public void onEnd(HttpResponse httpResponse) {
                    try {
                        JSONObjectProxy jSONObject = httpResponse.getJSONObject();
                        Type genericSuperclass = BaseListInteractor.this.getClass().getGenericSuperclass();
                        if (genericSuperclass instanceof ParameterizedType) {
                            BaseListInteractor.this.baseListState.data = (T) JDJSON.parseObject(jSONObject.toString(), (Class) ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0]);
                            BaseListInteractor.this.onResponse(httpResponse, hashMap);
                            if (BaseListInteractor.this.listener != null) {
                                BaseListInteractor.this.listener.onResult(new BaseListEvent(BaseListEvent.SHOW_DATA));
                                return;
                            }
                            return;
                        }
                        throw new IllegalArgumentException("child class need extends BaseListInteractor with generic paradigm");
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
                public void onError(HttpError httpError) {
                    BaseListInteractor.this.baseListState.httpError = httpError;
                    if (BaseListInteractor.this.listener != null) {
                        BaseListInteractor.this.listener.onResult(new BaseListEvent(BaseListEvent.SHOW_NET_ERROR));
                    }
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
                public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
                }
            });
            Context context = this.context;
            if (context != null && (context instanceof IMyActivity)) {
                new HttpGroupUtil().getHttpGroupaAsynPool((IMyActivity) this.context, null).add(createHttpSetting);
                return;
            } else {
                new HttpGroupUtil().getHttpGroupaAsynPool().add(createHttpSetting);
                return;
            }
        }
        throw new NullPointerException("createHttpSetting() can not return null");
    }

    public NormalListener getListener() {
        return this.listener;
    }

    public BaseListState<T> getState() {
        BaseListState<T> baseListState = this.baseListState;
        if (baseListState != null) {
            return baseListState;
        }
        throw new NullPointerException("state is null ! you should create a state.");
    }

    protected void onResponse(HttpResponse httpResponse, HashMap hashMap) {
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setListener(NormalListener normalListener) {
        this.listener = normalListener;
    }
}
