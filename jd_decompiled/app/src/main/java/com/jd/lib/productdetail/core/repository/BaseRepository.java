package com.jd.lib.productdetail.core.repository;

import android.text.TextUtils;
import androidx.lifecycle.LiveData;
import com.jd.lib.productdetail.core.utils.PDManager;
import com.jingdong.common.helper.PDHelper;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.utils.JDGetWayQueueTools;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import de.greenrobot.event.EventBus;

/* loaded from: classes15.dex */
public abstract class BaseRepository {
    protected String mEventKey;
    private final ExceptionReporter reporter = new ExceptionReporter(null);

    /* loaded from: classes15.dex */
    private class HttpGroupListener implements HttpGroup.OnAllListener {
        private HttpGroupListener() {
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            String string = httpResponse.getString();
            if (TextUtils.isEmpty(string)) {
                BaseRepository.this.reporter.reportHttpBusinessException(httpResponse);
                return;
            }
            BaseRepository baseRepository = BaseRepository.this;
            baseRepository.parseResponse(string, baseRepository.reporter);
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
            BaseRepository.this.parseError(httpError);
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
        public void onProgress(int i2, int i3) {
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
        public void onStart() {
        }
    }

    private BaseRepository() {
    }

    public void addRequest(Object obj) {
        HttpGroup httpGroupaAsynPool = HttpGroupUtils.getHttpGroupaAsynPool(1000);
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setModeId(JDGetWayQueueTools.QueueMode.MODE_PRODUCT_DETAIL);
        httpSetting.setPageId(PDHelper.getPageId("com.jd.lib.productdetail.ProductDetailActivity"));
        httpSetting.setFunctionId(getFunctionId());
        httpSetting.setHost(getHost());
        httpSetting.setEffect(getEffect());
        httpSetting.setNotifyUser(isNotifyUser());
        httpSetting.setLocalFileCache(isCache());
        httpSetting.setLocalFileCacheTime(getCacheTime());
        httpSetting.setUseFastJsonParser(true);
        httpSetting.putJsonParam(obj);
        httpSetting.setListener(new HttpGroupListener());
        httpGroupaAsynPool.add(httpSetting);
    }

    protected long getCacheTime() {
        return 0L;
    }

    protected int getEffect() {
        return 1;
    }

    protected EventBus getEventBus() {
        return PDManager.getEventBus();
    }

    protected abstract String getFunctionId();

    protected String getHost() {
        return Configuration.getWareHost();
    }

    public abstract LiveData<String> getRepository();

    protected boolean isCache() {
        return false;
    }

    protected boolean isNotifyUser() {
        return false;
    }

    protected boolean isPost() {
        return false;
    }

    protected void parseError(HttpError httpError) {
    }

    protected abstract void parseResponse(String str, ExceptionReporter exceptionReporter);

    public BaseRepository(String str) {
        this.mEventKey = str;
    }
}
