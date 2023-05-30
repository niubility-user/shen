package com.jingdong.common.jump;

import android.content.Context;
import android.text.TextUtils;
import android.view.ViewGroup;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes5.dex */
public class JumpNetDataProvider {
    private static final JumpNetDataProvider ourInstance = new JumpNetDataProvider();
    private ConcurrentHashMap<String, Result> datas = new ConcurrentHashMap<>();
    private List<String> requestLists = new LinkedList();
    private ConcurrentHashMap<String, JumpHttpListener> listeners = new ConcurrentHashMap<>();

    private JumpNetDataProvider() {
    }

    private void actionResult(String str, Action action) {
        Result result = getResult(str);
        if (result != null) {
            if (result.success) {
                action.onEnd(result.response);
                return;
            } else {
                action.onError(result.error);
                return;
            }
        }
        action.onError(new HttpError());
    }

    private JumpHttpListener getAction(String str) {
        ConcurrentHashMap<String, JumpHttpListener> concurrentHashMap = this.listeners;
        if (concurrentHashMap == null) {
            return null;
        }
        return concurrentHashMap.remove(str);
    }

    public static JumpNetDataProvider getInstance() {
        return ourInstance;
    }

    private Result getResult(String str) {
        ConcurrentHashMap<String, Result> concurrentHashMap = this.datas;
        if (concurrentHashMap == null) {
            return null;
        }
        return concurrentHashMap.remove(str);
    }

    public synchronized void onFail(HttpError httpError, String str) {
        JumpHttpListener action = getAction(str);
        if (action != null) {
            action.onError(httpError);
        } else {
            Result result = new Result();
            result.success = false;
            result.error = httpError;
            writeResult(str, result);
        }
        removeRequestList(str);
    }

    public synchronized void onResponse(HttpResponse httpResponse, String str) {
        JumpHttpListener action = getAction(str);
        if (action != null) {
            action.onEnd(httpResponse);
        } else {
            Result result = new Result();
            result.success = true;
            result.response = httpResponse;
            writeResult(str, result);
        }
        removeRequestList(str);
    }

    public void progress(String str, int i2, int i3) {
        JumpHttpListener jumpHttpListener = this.listeners.get(str);
        if (jumpHttpListener != null) {
            jumpHttpListener.onProgress(i2, i3);
        }
    }

    private void removeRequestList(String str) {
        if (this.requestLists == null) {
            this.requestLists = new LinkedList();
        }
        this.requestLists.remove(str);
    }

    private synchronized void removeResult(String str) {
        this.datas.remove(str);
    }

    private boolean shouldSaveAction(String str) {
        return this.requestLists.contains(str) && !this.listeners.containsKey(str);
    }

    private void writeListener(String str, JumpHttpListener jumpHttpListener) {
        if (this.listeners == null) {
            this.listeners = new ConcurrentHashMap<>();
        }
        if (this.listeners.get(str) != null) {
            jumpHttpListener.onError(null);
        } else {
            this.listeners.put(str, jumpHttpListener);
        }
    }

    private boolean writeRequestList(String str) {
        if (this.requestLists == null) {
            this.requestLists = new LinkedList();
        }
        if (this.requestLists.contains(str)) {
            return true;
        }
        this.requestLists.add(str);
        return false;
    }

    private void writeResult(String str, Result result) {
        if (this.datas == null) {
            this.datas = new ConcurrentHashMap<>();
        }
        this.datas.put(str, result);
    }

    public synchronized void register(String str, Action action) {
        if (!TextUtils.isEmpty(str) && action != null) {
            if (shouldSaveAction(str)) {
                writeListener(str, new JumpHttpListener(action));
            } else {
                actionResult(str, action);
            }
        }
    }

    public synchronized void registerWithLoading(Context context, String str, Action action) {
        if (!TextUtils.isEmpty(str) && action != null) {
            if (this.requestLists.contains(str)) {
                JumpHttpListener jumpHttpListener = new JumpHttpListener(action);
                writeListener(str, jumpHttpListener);
                jumpHttpListener.showProgressbar(context);
            } else {
                actionResult(str, action);
            }
        }
    }

    public synchronized void removeAction(String str) {
        this.listeners.remove(str);
    }

    public boolean request(final String str, Map<String, Object> map, boolean... zArr) {
        if (TextUtils.isEmpty(str) || !SwitchQueryFetcher.getSwitchBooleanValue("NetPreLoad", false) || writeRequestList(str)) {
            return false;
        }
        removeResult(str);
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setHost(Configuration.getVirtualHost());
        if (map != null && map.size() > 0) {
            for (String str2 : map.keySet()) {
                if (!TextUtils.isEmpty(str2)) {
                    httpSetting.putJsonParam(str2, map.get(str2));
                }
            }
        }
        httpSetting.setFunctionId(str);
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setEffect(0);
        httpSetting.setNeedRetryOnBusinessLayer(!SwitchQueryFetcher.isXTime());
        if (zArr != null && zArr.length > 0) {
            httpSetting.setEncryptBody(zArr[0]);
        }
        httpSetting.setListener(new HttpGroup.OnAllListener() { // from class: com.jingdong.common.jump.JumpNetDataProvider.1
            {
                JumpNetDataProvider.this = this;
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                if (httpResponse == null) {
                    JumpNetDataProvider.this.onFail(null, str);
                    ExceptionReporter.reportOpenAppJumpException("pre_response", "", "HttpResponse is null");
                    return;
                }
                JumpNetDataProvider.this.onResponse(httpResponse, str);
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                JumpNetDataProvider.this.onFail(httpError, str);
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
            public void onProgress(int i2, int i3) {
                JumpNetDataProvider.this.progress(str, i2, i3);
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
            public void onStart() {
            }
        });
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
        return true;
    }

    public synchronized void registerWithLoading(ViewGroup viewGroup, String str, Action action) {
        if (!TextUtils.isEmpty(str) && action != null) {
            if (this.requestLists.contains(str)) {
                JumpHttpListener jumpHttpListener = new JumpHttpListener(action);
                writeListener(str, jumpHttpListener);
                jumpHttpListener.showProgressbar(viewGroup);
            } else {
                actionResult(str, action);
            }
        }
    }
}
