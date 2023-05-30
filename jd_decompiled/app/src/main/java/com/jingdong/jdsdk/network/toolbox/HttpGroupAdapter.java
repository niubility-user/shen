package com.jingdong.jdsdk.network.toolbox;

import android.app.Activity;
import android.text.TextUtils;
import com.jd.framework.network.JDNetworkHelper;
import com.jd.framework.network.request.JDRequest;
import com.jingdong.jdsdk.network.JDHttpTookit;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes14.dex */
public class HttpGroupAdapter extends HttpGroup {
    private static final String TAG = "HttpGroupAdapter";
    private static JDJsonArrayRequestFactory sJDJsonArrayRequestFactory = new JDJsonArrayRequestFactory();
    private static JDJsonRequestFactory sJDJsonRequestFactory = new JDJsonRequestFactory();
    private static JDImageRequestFactory sJDImageRequestFactory = new JDImageRequestFactory();
    private static JDFileRequestFactory sJDFileRequestFactory = new JDFileRequestFactory();
    private static JDAdRequestFactory sJDAdRequestFactory = new JDAdRequestFactory();

    /* loaded from: classes14.dex */
    public class AsyncRequestTask extends RequestTask {
        public AsyncRequestTask(HttpRequest httpRequest) {
            super(httpRequest);
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroupAdapter.RequestTask
        public <T> void proceedRequest(JDRequest<T> jDRequest) {
            JDNetworkHelper.getGlobalJDRequestQueue().add(jDRequest);
        }
    }

    /* loaded from: classes14.dex */
    public abstract class RequestTask implements Comparable<RequestTask>, Runnable {
        private final HttpRequest mHttpRequest;
        private final HttpSetting mHttpSetting;

        public RequestTask(HttpRequest httpRequest) {
            this.mHttpRequest = httpRequest;
            HttpSetting httpSetting = httpRequest.getHttpSetting();
            this.mHttpSetting = httpSetting;
            if (httpSetting.getType() == 0) {
                httpSetting.setType(HttpGroupAdapter.this.type);
            }
            if (httpSetting.getPriority() == 0) {
                int type = httpSetting.getType();
                if (type == 500) {
                    httpSetting.setPriority(500);
                } else if (type == 5000) {
                    httpSetting.setPriority(5000);
                } else if (type == 1000 || type == 1001) {
                    httpSetting.setPriority(1000);
                }
            }
            if (httpSetting.isTopPriority()) {
                httpSetting.setPriority(10000);
            }
        }

        public HttpSetting getHttpSetting() {
            return this.mHttpSetting;
        }

        public abstract <T> void proceedRequest(JDRequest<T> jDRequest);

        @Override // java.lang.Runnable
        public void run() {
            Activity currentMyActivity;
            try {
                if (OKLog.D) {
                    OKLog.d(HttpGroupAdapter.TAG, "RequestTask : " + this.mHttpSetting.getId() + ", with priority : " + this.mHttpSetting.getPriority());
                }
                HttpGroup.OnReadyListener onReadyListener = this.mHttpSetting.getOnReadyListener();
                if (onReadyListener != null) {
                    onReadyListener.onReady(this.mHttpSetting);
                    if (!this.mHttpSetting.isReady()) {
                        this.mHttpSetting.onError(new HttpError(new Exception(HttpError.EXCEPTION_MESSAGE_NO_READY)));
                        return;
                    }
                }
                if (HttpGroupAdapter.this.shouldAbandonRequest()) {
                    this.mHttpSetting.onError(new HttpError(new Exception("Network is forbidden before user allow the network connection tips.")));
                    return;
                }
                try {
                    if (TextUtils.isEmpty(this.mHttpSetting.getCurrentPageName()) && (currentMyActivity = JDHttpTookit.getEngine().getAppProxy().getCurrentMyActivity()) != null) {
                        this.mHttpSetting.setCurrentPageName(currentMyActivity.toString());
                    }
                } catch (Throwable unused) {
                }
                HttpSettingTool.setupParams(this.mHttpRequest);
                if (HttpGroupAdapter.this.getHttpGroupSetting().getHttpUiHelper() != null) {
                    if (OKLog.D) {
                        OKLog.d("=======>", "tryEffect");
                    }
                    HttpGroupAdapter.this.getHttpGroupSetting().getHttpUiHelper().tryEffect(HttpGroupAdapter.this.httpGroupSetting, this.mHttpSetting);
                }
                String finalUrl = this.mHttpSetting.getFinalUrl();
                if (TextUtils.isEmpty(finalUrl)) {
                    finalUrl = this.mHttpSetting.getUrl();
                }
                if (OKLog.D) {
                    OKLog.d(HttpGroupAdapter.TAG, "id:" + this.mHttpSetting.getId() + "- ..url -->> " + finalUrl);
                    OKLog.d(HttpGroupAdapter.TAG, "id:" + this.mHttpSetting.getId() + "- ..isPost -->> " + this.mHttpSetting.isPost());
                    OKLog.d(HttpGroupAdapter.TAG, "id:" + this.mHttpSetting.getId() + "- ..cacheTime -->> " + this.mHttpSetting.getLocalFileCacheTime());
                }
                HttpGroupAdapter httpGroupAdapter = HttpGroupAdapter.this;
                if (httpGroupAdapter.httpCount < 1) {
                    httpGroupAdapter.onStart();
                }
                HttpGroupAdapter httpGroupAdapter2 = HttpGroupAdapter.this;
                httpGroupAdapter2.httpCount++;
                proceedRequest(httpGroupAdapter2.createJDRequest(this.mHttpRequest, this.mHttpSetting, finalUrl));
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }

        @Override // java.lang.Comparable
        public int compareTo(RequestTask requestTask) {
            return requestTask.getHttpSetting().getPriority() - this.mHttpSetting.getPriority();
        }
    }

    /* loaded from: classes14.dex */
    public class SyncRequestTask extends RequestTask {
        public SyncRequestTask(HttpRequest httpRequest) {
            super(httpRequest);
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroupAdapter.RequestTask
        public <T> void proceedRequest(JDRequest<T> jDRequest) {
            JDNetworkHelper.execute(jDRequest);
        }
    }

    public HttpGroupAdapter(HttpGroupSetting httpGroupSetting) {
        super(httpGroupSetting);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JDRequest createJDRequest(HttpRequest httpRequest, HttpSetting httpSetting, String str) {
        if (httpSetting.getType() == 1000) {
            return sJDJsonRequestFactory.createJDRequest(this, httpRequest, httpSetting, str);
        }
        if (httpSetting.getType() == 1001) {
            return sJDJsonArrayRequestFactory.createJDRequest(this, httpRequest, httpSetting, str);
        }
        if (httpSetting.getType() == 5000) {
            return sJDImageRequestFactory.createJDRequest(this, httpRequest, httpSetting, str);
        }
        if (httpSetting.getType() == 500) {
            return sJDFileRequestFactory.createJDRequest(this, httpRequest, httpSetting, str);
        }
        if (httpSetting.getType() == 6000) {
            return sJDAdRequestFactory.createJDRequest(this, httpRequest, httpSetting, str);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean shouldAbandonRequest() {
        return JDHttpTookit.getEngine() == null || JDNetworkHelper.getGlobalJDRequestQueue() == null || !JDHttpTookit.getEngine().getNetworkControllerImpl().isAllowNetworkConnection();
    }

    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup
    public HttpRequest add(HttpSetting httpSetting) {
        httpSetting.setId(HttpGroup.httpIdCounter.incrementAndGet());
        HttpRequest httpRequest = new HttpRequest(httpSetting);
        try {
            if (JDHttpTookit.getEngine() != null) {
                GlobalExecutorService.executorService().execute(new AsyncRequestTask(httpRequest));
            }
        } catch (Throwable unused) {
        }
        return httpRequest;
    }

    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup
    public void clearCache(String str) {
        if (JDNetworkHelper.getGlobalJDRequestQueue() == null || JDNetworkHelper.getGlobalJDRequestQueue().getCache() == null || JDNetworkHelper.getGlobalJDRequestQueue().getCache().get(str) == null) {
            return;
        }
        JDNetworkHelper.getGlobalJDRequestQueue().getCache().remove(str);
    }

    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup
    public HttpRequest execute(HttpSetting httpSetting) {
        httpSetting.setId(HttpGroup.httpIdCounter.incrementAndGet());
        HttpRequest httpRequest = new HttpRequest(httpSetting);
        if (JDHttpTookit.getEngine() != null) {
            GlobalExecutorService.executorService().execute(new SyncRequestTask(httpRequest));
        }
        return httpRequest;
    }

    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup
    public boolean isCacheExpired(String str) {
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        return JDNetworkHelper.getGlobalJDRequestQueue().isCacheExpired(str);
    }
}
