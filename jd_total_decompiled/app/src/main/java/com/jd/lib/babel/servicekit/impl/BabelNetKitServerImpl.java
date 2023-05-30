package com.jd.lib.babel.servicekit.impl;

import android.content.Context;
import com.jd.lib.babel.servicekit.networkkit.BabelError;
import com.jd.lib.babel.servicekit.networkkit.BabelNetWorkKitServer;
import com.jd.lib.babel.servicekit.networkkit.BabelResponse;
import com.jd.lib.babel.servicekit.networkkit.Request;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;

/* loaded from: classes13.dex */
public class BabelNetKitServerImpl implements BabelNetWorkKitServer {
    /* JADX INFO: Access modifiers changed from: private */
    public BabelError createError(HttpError httpError) {
        BabelError babelError = new BabelError();
        if (httpError != null) {
            babelError.setErrorCode(httpError.getErrorCode());
            babelError.setException(httpError.getException());
            babelError.setMessage(httpError.getMessage());
            babelError.setResponseCode(httpError.getResponseCode());
        }
        return babelError;
    }

    private HttpSetting createHttpSetting(Request request) {
        HttpSetting httpSetting = new HttpSetting();
        if (request.getMethod() == 1) {
            httpSetting.setPost(false);
        } else if (request.getMethod() == 0) {
            httpSetting.setPost(true);
        }
        httpSetting.setHost(Configuration.getNgwHost());
        httpSetting.setFunctionId(request.getFunctionId());
        httpSetting.setHeaderMap(request.getHeader());
        httpSetting.putJsonParam(request.getBody());
        httpSetting.setAttempts(request.getAttempts() >= 0 ? request.getAttempts() : 1);
        return httpSetting;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BabelResponse createResponse(HttpResponse httpResponse) {
        if (httpResponse == null) {
            return null;
        }
        BabelResponse babelResponse = new BabelResponse(httpResponse.getString());
        babelResponse.setMoreParams(httpResponse.getMoreParams());
        babelResponse.setCode(httpResponse.getCode());
        babelResponse.setInputData(httpResponse.getInputData());
        return babelResponse;
    }

    @Override // com.jd.lib.babel.servicekit.networkkit.BabelNetWorkKitServer
    public void addRequest(Context context, final Request request) {
        if (request == null) {
            return;
        }
        HttpSetting createHttpSetting = createHttpSetting(request);
        createHttpSetting.setListener(new HttpGroup.OnAllListener() { // from class: com.jd.lib.babel.servicekit.impl.BabelNetKitServerImpl.1
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                if (request.getListener() != null) {
                    request.getListener().onEnd(BabelNetKitServerImpl.this.createResponse(httpResponse));
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                if (request.getListener() != null) {
                    request.getListener().onError(BabelNetKitServerImpl.this.createError(httpError));
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
            public void onProgress(int i2, int i3) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
            public void onStart() {
                if (request.getListener() != null) {
                    request.getListener().onStart();
                }
            }
        });
        HttpGroupUtils.getHttpGroupaAsynPool().add(createHttpSetting);
    }
}
