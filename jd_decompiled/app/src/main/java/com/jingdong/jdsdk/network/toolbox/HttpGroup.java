package com.jingdong.jdsdk.network.toolbox;

import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jingdong.jdsdk.network.JDHttpTookit;
import com.jingdong.sdk.oklog.OKLog;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONObject;

/* loaded from: classes.dex */
public abstract class HttpGroup {
    protected static AtomicInteger httpIdCounter = new AtomicInteger(0);
    protected HttpGroupSetting httpGroupSetting;
    private OnGroupCompleteListener onGroupCompleteListener;
    private OnGroupFirstRequestCompleteListener onGroupFirstRequestCompleteListener;
    private OnGroupStartListener onGroupStartListener;
    protected int priority;
    protected int type;
    protected int httpCount = 0;
    private int completesCount = 0;
    private ConcurrentHashMap<Integer, HttpRequest> requests = new ConcurrentHashMap<>();

    /* loaded from: classes.dex */
    public interface CustomOnAllListener extends OnAllListener {
    }

    /* loaded from: classes14.dex */
    public interface HttpErrorAlertControllerFactory {
        HttpErrorAlertControllerInterface createController(HttpGroupSetting httpGroupSetting, HttpSetting httpSetting, HttpRequest httpRequest);
    }

    /* loaded from: classes14.dex */
    public interface HttpErrorAlertControllerInterface {
        void throwError(HttpError httpError, HttpErrorAlertListener httpErrorAlertListener);
    }

    /* loaded from: classes14.dex */
    public interface HttpErrorAlertListener {
        void reTry();

        void sendError();
    }

    /* loaded from: classes.dex */
    public interface HttpSettingParams {
        void putJsonParam(String str, Object obj);

        void putMapParams(String str, String str2);

        void setReady(boolean z);
    }

    /* loaded from: classes.dex */
    public interface HttpTaskListener {
    }

    /* loaded from: classes14.dex */
    public interface OnAllAndPauseListener extends OnAllListener, OnPauseListener {
    }

    /* loaded from: classes.dex */
    public interface OnAllListener extends OnStartListener, OnEndListener, OnErrorListener, OnProgressListener {
    }

    /* loaded from: classes14.dex */
    public interface OnCancelListener extends HttpTaskListener {
        void onCancel();
    }

    /* loaded from: classes.dex */
    public interface OnCommonListener extends OnEndListener, OnErrorListener, OnReadyListener {
    }

    /* loaded from: classes14.dex */
    public interface OnCommonNewListener<T> extends OnJsonResponseEndLisener<T>, OnErrorListener, OnReadyListener {
    }

    /* loaded from: classes.dex */
    public interface OnEndListener extends HttpTaskListener {
        void onEnd(HttpResponse httpResponse);
    }

    /* loaded from: classes.dex */
    public interface OnErrorListener extends HttpTaskListener {
        void onError(HttpError httpError);
    }

    /* loaded from: classes14.dex */
    public interface OnGroupCompleteListener {
        void onComplete();
    }

    /* loaded from: classes14.dex */
    public interface OnGroupFirstRequestCompleteListener {
        void onFirstLoadComplete();
    }

    /* loaded from: classes14.dex */
    public interface OnGroupStartListener {
        void onStart();
    }

    /* loaded from: classes14.dex */
    public interface OnJsonResponseEndLisener<T> extends HttpTaskListener {
        void onEnd(HttpResponse httpResponse, T t);
    }

    /* loaded from: classes14.dex */
    public interface OnPauseListener extends HttpTaskListener {
        void onPause();
    }

    /* loaded from: classes.dex */
    public interface OnProgressListener extends HttpTaskListener {
        void onProgress(int i2, int i3);
    }

    /* loaded from: classes.dex */
    public interface OnReadyListener extends HttpTaskListener {
        void onReady(HttpSettingParams httpSettingParams);
    }

    /* loaded from: classes.dex */
    public interface OnStartListener extends HttpTaskListener {
        void onStart();
    }

    /* loaded from: classes.dex */
    public interface StopController {
        boolean isStop();

        void stop();
    }

    public HttpGroup(HttpGroupSetting httpGroupSetting) {
        this.httpGroupSetting = httpGroupSetting;
        this.priority = httpGroupSetting.getPriority();
        this.type = httpGroupSetting.getType();
    }

    public static void cleanCookies() {
    }

    public static String getCookie() {
        try {
            return JDHttpTookit.getEngine().getLoginUserControllerImpl().getCookie();
        } catch (Throwable unused) {
            return "";
        }
    }

    public static HttpGroup getHttpGroup(HttpGroupSetting httpGroupSetting) {
        return new HttpGroupAdapter(httpGroupSetting);
    }

    public static String mergerUrlAndParams(String str, Map<String, String> map) {
        Set<String> keySet;
        if (map == null || (keySet = map.keySet()) == null || keySet.isEmpty()) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str);
        int indexOf = str.indexOf("?");
        if (indexOf == -1) {
            sb.append("?");
        } else {
            String substring = str.substring(indexOf + 1);
            if (!TextUtils.isEmpty(substring) && !substring.endsWith(ContainerUtils.FIELD_DELIMITER)) {
                sb.append(ContainerUtils.FIELD_DELIMITER);
            }
        }
        Iterator<String> it = keySet.iterator();
        while (it.hasNext()) {
            String next = it.next();
            String str2 = map.get(next);
            if (!TextUtils.equals(next, "body")) {
                sb.append(next);
                sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
                sb.append(str2);
                if (it.hasNext()) {
                    sb.append(ContainerUtils.FIELD_DELIMITER);
                }
            }
        }
        if (sb.charAt(sb.length() - 1) == '&') {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    public static void setCookies(String str) {
    }

    public abstract HttpRequest add(HttpSetting httpSetting);

    public HttpRequest add(String str, JSONObject jSONObject, OnAllListener onAllListener) {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId(str);
        httpSetting.setJsonParams(jSONObject);
        httpSetting.setListener(onAllListener);
        return add(httpSetting);
    }

    public void addCompletesCount() {
        this.completesCount++;
        if (OKLog.I) {
            OKLog.i("HttpGroup", "addCompletesCount -->> " + this + "completesCount:" + this.completesCount + ", httpCount:" + this.httpCount);
        }
        if (this.completesCount == 1) {
            onFirstRequestComplete();
        }
        if (this.completesCount == this.httpCount) {
            onComplete();
        }
    }

    protected void addRequest(int i2, HttpRequest httpRequest) {
        ConcurrentHashMap<Integer, HttpRequest> concurrentHashMap = this.requests;
        if (concurrentHashMap != null) {
            concurrentHashMap.put(Integer.valueOf(i2), httpRequest);
        }
    }

    public abstract void clearCache(String str);

    public abstract HttpRequest execute(HttpSetting httpSetting);

    public HttpGroupSetting getHttpGroupSetting() {
        return this.httpGroupSetting;
    }

    public abstract boolean isCacheExpired(String str);

    protected void onComplete() {
        OnGroupCompleteListener onGroupCompleteListener = this.onGroupCompleteListener;
        if (onGroupCompleteListener != null) {
            onGroupCompleteListener.onComplete();
        }
    }

    public void onDestroy() {
        ConcurrentHashMap<Integer, HttpRequest> concurrentHashMap = this.requests;
        if (concurrentHashMap != null && concurrentHashMap.size() > 0) {
            Iterator<Map.Entry<Integer, HttpRequest>> it = this.requests.entrySet().iterator();
            while (it.hasNext()) {
                it.next().getValue().stop();
            }
            this.requests.clear();
        }
        HttpGroupSetting httpGroupSetting = this.httpGroupSetting;
        if (httpGroupSetting != null) {
            httpGroupSetting.setMyActivity(null);
            this.httpGroupSetting.setProgressBarRootLayout(null);
        }
    }

    protected void onFirstRequestComplete() {
        OnGroupFirstRequestCompleteListener onGroupFirstRequestCompleteListener = this.onGroupFirstRequestCompleteListener;
        if (onGroupFirstRequestCompleteListener != null) {
            onGroupFirstRequestCompleteListener.onFirstLoadComplete();
        }
    }

    public void onStart() {
        OnGroupStartListener onGroupStartListener = this.onGroupStartListener;
        if (onGroupStartListener != null) {
            onGroupStartListener.onStart();
        }
    }

    protected void removeRequest(int i2) {
        ConcurrentHashMap<Integer, HttpRequest> concurrentHashMap = this.requests;
        if (concurrentHashMap != null) {
            concurrentHashMap.remove(Integer.valueOf(i2));
        }
    }

    public void setOnGroupCompleteListener(OnGroupCompleteListener onGroupCompleteListener) {
        this.onGroupCompleteListener = onGroupCompleteListener;
    }

    public void setOnGroupFirstRequestCompleteListener(OnGroupFirstRequestCompleteListener onGroupFirstRequestCompleteListener) {
        this.onGroupFirstRequestCompleteListener = onGroupFirstRequestCompleteListener;
    }

    public void setOnGroupStartListener(OnGroupStartListener onGroupStartListener) {
        this.onGroupStartListener = onGroupStartListener;
    }
}
