package com.jingdong.jdsdk.network.toolbox;

import com.jd.framework.network.JDNetworkHelper;
import com.jd.framework.network.JDResponse;
import com.jd.framework.network.JDResponseListener;
import com.jd.framework.network.file.JDFileGuider;
import com.jd.framework.network.file.JDFileResponseListener;
import com.jd.framework.network.request.JDFileRequest;
import com.jd.framework.network.request.JDRequest;
import com.jingdong.jdsdk.network.JDHttpTookit;
import com.jingdong.jdsdk.network.config.RuntimeConfigHelper;
import com.jingdong.sdk.oklog.OKLog;
import java.io.File;
import java.io.InputStream;
import java.util.HashMap;

/* loaded from: classes14.dex */
public class JDFileRequestFactory extends AbstractJDRequestFactory {
    public static final String TAG = "JDFileRequestFactory";

    /* loaded from: classes14.dex */
    public static class JDFileResponseListenerImpl extends JDResponseBaseListener<File> implements JDFileResponseListener<File> {
        public JDFileResponseListenerImpl(HttpGroup httpGroup, HttpSetting httpSetting, HttpRequest httpRequest, JDRequest<File> jDRequest) {
            super(httpGroup, httpSetting, httpRequest, jDRequest);
        }

        @Override // com.jingdong.jdsdk.network.toolbox.JDResponseBaseListener
        protected void handleResponse(HttpResponse httpResponse, JDResponse<File> jDResponse) {
            httpResponse.setHeader(jDResponse.getHeaders());
            httpResponse.setStatusCode(jDResponse.getStatusCode());
            if (this.httpSetting.isRetrieveInputStream()) {
                httpResponse.setInputStream((InputStream) jDResponse.getData());
            } else {
                httpResponse.setSaveFile(jDResponse.getData());
            }
        }

        @Override // com.jd.framework.network.file.JDFileResponseListener
        public void onPause() {
            this.httpSetting.onPause();
        }

        @Override // com.jd.framework.network.file.JDFileResponseListener
        public void onProgress(int i2, int i3) {
            this.httpSetting.onProgress(i2, i3);
        }
    }

    private JDFileGuider convertFileGuiderToJDFileGuider(FileGuider fileGuider) {
        JDFileGuider jDFileGuider = new JDFileGuider();
        jDFileGuider.setAvailableSize(fileGuider.getAvailableSize());
        jDFileGuider.setChildDirName(fileGuider.getChildDirName());
        jDFileGuider.setChildSndDirName(fileGuider.getChildSndDirName());
        jDFileGuider.setFileName(fileGuider.getFileName());
        jDFileGuider.setImmutable(fileGuider.isImmutable());
        jDFileGuider.setInternalType(fileGuider.getInternalType());
        jDFileGuider.setMode(fileGuider.getMode());
        int space = fileGuider.getSpace();
        if (space == 1) {
            jDFileGuider.setSpace(1);
        } else if (space == 2) {
            jDFileGuider.setSpace(2);
        } else if (space == 3) {
            jDFileGuider.setSpace(3);
        }
        jDFileGuider.setTotalSize(fileGuider.getTotalSize());
        return jDFileGuider;
    }

    private void initFileRequest(HttpRequest httpRequest, HttpSetting httpSetting, JDFileRequest jDFileRequest, JDResponseListener jDResponseListener) {
        if (JDNetworkHelper.getGlobalJDRequestQueue() == null) {
            return;
        }
        jDFileRequest.setResponseListener((JDFileResponseListener) jDResponseListener);
        jDFileRequest.setSequence(httpSetting.getId());
        FileGuider savePath = httpSetting.getSavePath();
        jDFileRequest.setRetrieveInputStreamFlag(httpSetting.isRetrieveInputStream());
        if (!jDFileRequest.isRetrieveInputStream()) {
            jDFileRequest.setSavePath(convertFileGuiderToJDFileGuider(savePath));
        }
        jDFileRequest.setBreakpointTransmission(httpSetting.isBreakpointTransmission());
        jDFileRequest.setConnectTimeoutMs(httpSetting.getConnectTimeout());
        jDFileRequest.setReadTimeoutMs(httpSetting.getReadTimeout());
        jDFileRequest.setStartPosBreakpointTransmission(httpSetting.getStartPosBreakpointTransmission());
        httpRequest.setJDFileRequest(jDFileRequest);
        jDFileRequest.setMaxNumRetries(httpSetting.getAttempts() - 1);
        jDFileRequest.setAttemptsTime(httpSetting.getAttemptsTime());
        jDFileRequest.setNoAttempts(httpSetting.isNoAttempts());
        jDFileRequest.setTopPriority(httpSetting.isTopPriority());
        jDFileRequest.setPriority(convertPriorityToJDRequestPriority(httpSetting.getPriority()));
        if (httpSetting.isExclusiveTask()) {
            jDFileRequest.setPriority(convertPriorityToJDRequestPriority(10000));
        }
        HashMap hashMap = new HashMap();
        hashMap.putAll(JDHttpTookit.getEngine().getStatInfoConfigImpl().getUniformHeaderField(false, httpSetting.isEnableEncryptTransmission()));
        hashMap.putAll(httpSetting.getHeaderMap());
        jDFileRequest.setHeader(hashMap);
        jDFileRequest.setUseDomainName(!JDHttpTookit.getEngine().getHttpDnsControllerImpl().isOpenDnsControl());
        jDFileRequest.setIgnoreCharset(httpSetting.isIgnoreCharset());
        jDFileRequest.setIgnoreRedirect(httpSetting.isIgnoreRedirect());
        if (httpSetting.incompatibleWithOkHttp()) {
            jDFileRequest.setUseOkhttpFlag(false);
        } else {
            jDFileRequest.setUseOkhttpFlag(RuntimeConfigHelper.isUseOkhttp());
        }
    }

    @Override // com.jingdong.jdsdk.network.toolbox.AbstractJDRequestFactory
    public JDRequest createJDRequest(HttpGroup httpGroup, HttpRequest httpRequest, HttpSetting httpSetting, String str) {
        if (OKLog.D) {
            OKLog.d(TAG, "downFile:id:" + httpSetting.getId() + ",url:" + str);
        }
        JDFileRequest jDFileRequest = new JDFileRequest(str);
        initFileRequest(httpRequest, httpSetting, jDFileRequest, createResponseListener(httpGroup, httpSetting, httpRequest, jDFileRequest));
        return jDFileRequest;
    }

    @Override // com.jingdong.jdsdk.network.toolbox.AbstractJDRequestFactory
    public JDResponseListener createResponseListener(HttpGroup httpGroup, HttpSetting httpSetting, HttpRequest httpRequest, JDRequest jDRequest) {
        return new JDFileResponseListenerImpl(httpGroup, httpSetting, httpRequest, jDRequest);
    }
}
