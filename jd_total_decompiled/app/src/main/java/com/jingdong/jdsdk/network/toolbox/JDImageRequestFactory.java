package com.jingdong.jdsdk.network.toolbox;

import com.jd.framework.network.JDResponse;
import com.jd.framework.network.JDResponseListener;
import com.jd.framework.network.request.JDRequest;
import com.jd.framework.network.request.JDVerifyCodeRequest;
import com.jingdong.jdsdk.network.JDHttpTookit;
import com.jingdong.jdsdk.network.config.RuntimeConfigHelper;
import com.jingdong.jdsdk.network.toolbox.FileService;
import com.jingdong.sdk.oklog.OKLog;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes14.dex */
public class JDImageRequestFactory extends AbstractJDRequestFactory {
    private static final String TAG = JDJsonRequestFactory.class.getSimpleName();
    protected static int duplicateImageUrlCount = 0;
    protected static String lastImageUrl = null;

    /* loaded from: classes14.dex */
    public static class ImageResponseListener extends JDResponseBaseListener<byte[]> {
        public ImageResponseListener(HttpGroup httpGroup, HttpSetting httpSetting, HttpRequest httpRequest, JDRequest<byte[]> jDRequest) {
            super(httpGroup, httpSetting, httpRequest, jDRequest);
        }

        private void checkVerifycodeResponseState(Map<String, String> map) throws HttpError {
            String str = map.get("JD-Code");
            if (str == null) {
                str = map.get("jd-code");
            }
            if (str != null) {
                String str2 = "[" + str + "]";
                if (OKLog.D) {
                    OKLog.v(JDResponseBaseListener.TAG, "VerifyCode state:" + str2);
                }
                if (HttpError.VERIFY_STATUS_SUCCESS.equals(str2)) {
                    return;
                }
                if (HttpError.VERIFY_STATUS_OVERCOUNT.equals(str2) || HttpError.VERIFY_STATUS_OVERTIME.equals(str2) || HttpError.VERIFY_STATUS_UNKNOW.equals(str2)) {
                    HttpError httpError = new HttpError();
                    httpError.setErrorCode(2);
                    httpError.setValidDataErrorCode(str2);
                    throw httpError;
                }
            }
        }

        private void saveLocalCacheImage(HttpSetting httpSetting, HttpResponse httpResponse) {
            FileService.Directory directory = FileService.getDirectory(1);
            if (directory != null) {
                String str = httpSetting.getMd5() + FileService.CACHE_EXT_NAME_IMAGE;
                if (httpResponse == null) {
                    return;
                }
                boolean saveToSDCardWithType = FileService.saveToSDCardWithType(directory, str, httpResponse.getInputData(), 1);
                if (saveToSDCardWithType) {
                    File file = new File(directory.getDir(), str);
                    if (httpSetting.isNeedShareImage()) {
                        httpResponse.setShareImagePath(FileService.saveShareImage(file));
                    }
                    httpResponse.setSaveFile(file);
                    httpResponse.setLength(file.length());
                }
                if (OKLog.D) {
                    OKLog.d(JDResponseBaseListener.TAG, "id:" + httpSetting.getId() + "- save image file -->> " + saveToSDCardWithType);
                }
            }
        }

        @Override // com.jingdong.jdsdk.network.toolbox.JDResponseBaseListener
        protected void handleResponse(HttpResponse httpResponse, JDResponse<byte[]> jDResponse) throws Exception {
            httpResponse.setInputData(jDResponse.getData());
            httpResponse.setHeader(jDResponse.getHeaders());
            if (jDResponse.isCache()) {
                if (this.httpSetting.isNeedShareImage()) {
                    httpResponse.setShareImagePath(FileService.saveShareImage(jDResponse.getData()));
                }
                if (this.httpSetting.isLocalFileCache()) {
                    File findCachesFileByMd5 = this.httpRequest.findCachesFileByMd5();
                    if (findCachesFileByMd5 != null) {
                        httpResponse.setSaveFile(findCachesFileByMd5);
                        httpResponse.setLength(findCachesFileByMd5.length());
                        return;
                    }
                    saveLocalCacheImage(this.httpSetting, httpResponse);
                }
            } else if (this.httpSetting.isLocalFileCache()) {
                saveLocalCacheImage(this.httpSetting, httpResponse);
            }
        }

        @Override // com.jingdong.jdsdk.network.toolbox.JDResponseBaseListener, com.jd.framework.network.JDResponseListener
        public void onEnd(JDResponse<byte[]> jDResponse) {
            try {
                checkVerifycodeResponseState(jDResponse.getHeaders());
                super.onEnd(jDResponse);
            } catch (HttpError e2) {
                this.httpSetting.onError(e2);
            }
        }
    }

    @Override // com.jingdong.jdsdk.network.toolbox.AbstractJDRequestFactory
    public JDRequest createJDRequest(HttpGroup httpGroup, HttpRequest httpRequest, HttpSetting httpSetting, String str) {
        if (OKLog.D) {
            OKLog.d(TAG, "downImage:id:" + httpSetting.getId() + ",url:" + str);
        }
        JDVerifyCodeRequest jDVerifyCodeRequest = new JDVerifyCodeRequest(httpSetting.isPost() ? 1 : 0, str);
        initJDRequest(httpRequest, httpSetting, str, jDVerifyCodeRequest, createResponseListener(httpGroup, httpSetting, httpRequest, jDVerifyCodeRequest));
        return jDVerifyCodeRequest;
    }

    @Override // com.jingdong.jdsdk.network.toolbox.AbstractJDRequestFactory
    public JDResponseListener createResponseListener(HttpGroup httpGroup, HttpSetting httpSetting, HttpRequest httpRequest, JDRequest jDRequest) {
        return new ImageResponseListener(httpGroup, httpSetting, httpRequest, jDRequest);
    }

    public <T> void initJDRequest(HttpRequest httpRequest, HttpSetting httpSetting, String str, JDRequest<T> jDRequest, JDResponseListener jDResponseListener) {
        if (5000 - httpSetting.getType() == 0) {
            if (str.equals(lastImageUrl)) {
                duplicateImageUrlCount++;
            } else {
                duplicateImageUrlCount = 0;
            }
            lastImageUrl = str;
            if (duplicateImageUrlCount > 10) {
                JDHttpTookit.getEngine().getExceptionReportDelegate().reportDuplicatePicException(str);
            }
        }
        jDRequest.setResponseListener(jDResponseListener);
        jDRequest.setUseCookies(httpSetting.isUseCookies());
        jDRequest.setParams(httpSetting.getPostMapParams());
        jDRequest.setCacheModel(convertCacheToJDRequestCache(httpSetting.getCacheMode()));
        if (httpSetting.getLocalFileCacheTime() > 0) {
            jDRequest.setCacheTime(httpSetting.getLocalFileCacheTime());
        }
        jDRequest.setCacheKey(httpSetting.getMd5());
        jDRequest.setMaxNumRetries(httpSetting.getAttempts() - 1);
        jDRequest.setConnectTimeoutMs(httpSetting.getConnectTimeout());
        jDRequest.setReadTimeoutMs(httpSetting.getReadTimeout());
        jDRequest.setPriority(convertPriorityToJDRequestPriority(httpSetting.getPriority()));
        jDRequest.setSequence(httpSetting.getId());
        jDRequest.setUseDomainName(!(JDHttpTookit.getEngine().getHttpDnsControllerImpl().isOpenDnsControl() && JDHttpTookit.getEngine().getHttpDnsControllerImpl().canUseHttpDns(httpSetting.getHost())));
        HashMap hashMap = new HashMap();
        hashMap.putAll(JDHttpTookit.getEngine().getStatInfoConfigImpl().getUniformHeaderField(false, httpSetting.isEnableEncryptTransmission()));
        hashMap.putAll(httpSetting.getHeaderMap());
        jDRequest.setHeader(hashMap);
        if (httpSetting.incompatibleWithOkHttp()) {
            jDRequest.setUseOkhttpFlag(false);
        } else {
            jDRequest.setUseOkhttpFlag(RuntimeConfigHelper.isUseOkhttp());
        }
        jDRequest.setForce2HttpFlag(JDHttpTookit.getEngine().getExternalDebugConfigImpl().isForceHttpDownGrade());
        httpRequest.setJDRequestTag(jDRequest.getTag());
    }
}
