package com.jingdong.app.mall.dynamicImpl;

import com.jd.dynamic.DYConstants;
import com.jd.dynamic.base.interfaces.INetWorkRequest;
import com.jd.dynamic.entity.RequestEntity;
import com.jingdong.cleanmvp.engine.HttpGroupUtil;
import com.jingdong.common.utils.JDGetWayQueueTools;
import com.jingdong.jdsdk.network.toolbox.FileGuider;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.jdsdk.utils.NetUtils;

/* loaded from: classes3.dex */
public class t implements INetWorkRequest {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public class a implements JDGetWayQueueTools.OnQueueCancelListener {
        final /* synthetic */ INetWorkRequest.ResponseCallBack a;

        a(t tVar, INetWorkRequest.ResponseCallBack responseCallBack) {
            this.a = responseCallBack;
        }

        @Override // com.jingdong.common.utils.JDGetWayQueueTools.OnQueueCancelListener
        public void onCancel() {
            INetWorkRequest.ResponseCallBack responseCallBack = this.a;
            if (responseCallBack != null) {
                responseCallBack.onError(new INetWorkRequest.ErrorResponse(DYConstants.ERROR_CODE_N_1000, "cancel"));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public class b implements HttpGroup.OnCommonListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ INetWorkRequest.ResponseCallBack f8405g;

        b(t tVar, INetWorkRequest.ResponseCallBack responseCallBack) {
            this.f8405g = responseCallBack;
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            JSONObjectProxy jSONObject = httpResponse.getJSONObject();
            INetWorkRequest.ResponseCallBack responseCallBack = this.f8405g;
            if (responseCallBack != null) {
                responseCallBack.onSuccess(jSONObject);
            }
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
            INetWorkRequest.ResponseCallBack responseCallBack = this.f8405g;
            if (responseCallBack != null) {
                try {
                    responseCallBack.onError(new INetWorkRequest.ErrorResponse(httpError.getErrorCode(), httpError.toString()));
                } catch (Exception unused) {
                }
            }
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
        public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
            INetWorkRequest.ResponseCallBack responseCallBack = this.f8405g;
            if (responseCallBack != null) {
                responseCallBack.onStart();
            }
        }
    }

    /* loaded from: classes3.dex */
    class c implements HttpGroup.OnCommonListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ INetWorkRequest.ResponseCallBack f8406g;

        c(t tVar, INetWorkRequest.ResponseCallBack responseCallBack) {
            this.f8406g = responseCallBack;
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            JSONObjectProxy jSONObject = httpResponse.getJSONObject();
            INetWorkRequest.ResponseCallBack responseCallBack = this.f8406g;
            if (responseCallBack != null) {
                responseCallBack.onSuccess(jSONObject);
            }
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
            INetWorkRequest.ResponseCallBack responseCallBack = this.f8406g;
            if (responseCallBack != null) {
                responseCallBack.onError(new INetWorkRequest.ErrorResponse(httpError.getErrorCode(), httpError.getMessage()));
            }
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
        public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
            INetWorkRequest.ResponseCallBack responseCallBack = this.f8406g;
            if (responseCallBack != null) {
                responseCallBack.onStart();
            }
        }
    }

    /* loaded from: classes3.dex */
    class d implements JDGetWayQueueTools.OnQueueCancelListener {
        final /* synthetic */ INetWorkRequest.DownloadCallBack a;

        d(t tVar, INetWorkRequest.DownloadCallBack downloadCallBack) {
            this.a = downloadCallBack;
        }

        @Override // com.jingdong.common.utils.JDGetWayQueueTools.OnQueueCancelListener
        public void onCancel() {
            INetWorkRequest.DownloadCallBack downloadCallBack = this.a;
            if (downloadCallBack != null) {
                downloadCallBack.onError(new INetWorkRequest.ErrorResponse(DYConstants.ERROR_CODE_N_1000, "cancel"));
            }
        }
    }

    /* loaded from: classes3.dex */
    class e implements HttpGroup.OnAllAndPauseListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ INetWorkRequest.DownloadCallBack f8407g;

        e(t tVar, INetWorkRequest.DownloadCallBack downloadCallBack) {
            this.f8407g = downloadCallBack;
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            INetWorkRequest.DownloadCallBack downloadCallBack = this.f8407g;
            if (downloadCallBack != null) {
                downloadCallBack.onSuccess(httpResponse.getSaveFile());
            }
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
            INetWorkRequest.DownloadCallBack downloadCallBack = this.f8407g;
            if (downloadCallBack != null) {
                try {
                    downloadCallBack.onError(new INetWorkRequest.ErrorResponse(httpError.getErrorCode(), httpError.toString()));
                } catch (Exception unused) {
                }
            }
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnPauseListener
        public void onPause() {
            INetWorkRequest.DownloadCallBack downloadCallBack = this.f8407g;
            if (downloadCallBack != null) {
                downloadCallBack.onPause();
            }
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
        public void onProgress(int i2, int i3) {
            INetWorkRequest.DownloadCallBack downloadCallBack = this.f8407g;
            if (downloadCallBack != null) {
                downloadCallBack.onProgress(i2, i3);
            }
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
        public void onStart() {
            INetWorkRequest.DownloadCallBack downloadCallBack = this.f8407g;
            if (downloadCallBack != null) {
                downloadCallBack.onStart();
            }
        }
    }

    @Override // com.jd.dynamic.base.interfaces.INetWorkRequest
    public void downloadFile(String str, String str2, String str3, INetWorkRequest.DownloadCallBack downloadCallBack) {
        FileGuider fileGuider = new FileGuider();
        fileGuider.setSpace(1);
        fileGuider.setChildDirName(str2);
        fileGuider.setImmutable(false);
        fileGuider.setFileName(str3);
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setUrl(str);
        httpSetting.setCacheMode(0);
        httpSetting.setType(500);
        httpSetting.setSavePath(fileGuider);
        httpSetting.setBreakpointTransmission(false);
        httpSetting.setAttempts(1);
        httpSetting.setOnQueueCancelListener(new d(this, downloadCallBack));
        httpSetting.setListener(new e(this, downloadCallBack));
        new HttpGroupUtil().getHttpGroupaAsynPool().execute(httpSetting);
    }

    @Override // com.jd.dynamic.base.interfaces.INetWorkRequest
    public String getNetworkType() {
        return NetUtils.getNetworkType();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x00e2, code lost:
        if (r3.equals("JSON") == false) goto L38;
     */
    @Override // com.jd.dynamic.base.interfaces.INetWorkRequest
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void requestWithFunctionId(android.app.Activity r7, com.jd.dynamic.entity.RequestEntity r8, com.jd.dynamic.base.interfaces.INetWorkRequest.ResponseCallBack r9) {
        /*
            Method dump skipped, instructions count: 376
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.app.mall.dynamicImpl.t.requestWithFunctionId(android.app.Activity, com.jd.dynamic.entity.RequestEntity, com.jd.dynamic.base.interfaces.INetWorkRequest$ResponseCallBack):void");
    }

    @Override // com.jd.dynamic.base.interfaces.INetWorkRequest
    public void requestWithHost(String str, String str2, String str3, INetWorkRequest.ResponseCallBack responseCallBack) {
        RequestEntity requestEntity = new RequestEntity();
        requestEntity.setFunctionId(str);
        requestEntity.setBody(str2);
        requestEntity.setHost(str3);
        requestEntity.setMethod("POST");
        requestWithFunctionId(null, requestEntity, responseCallBack);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00d6, code lost:
        if (r7.equals("JSON") == false) goto L30;
     */
    @Override // com.jd.dynamic.base.interfaces.INetWorkRequest
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void sendRequest(android.app.Activity r6, com.jd.dynamic.entity.RequestEntity r7, com.jd.dynamic.base.interfaces.INetWorkRequest.ResponseCallBack r8) {
        /*
            Method dump skipped, instructions count: 332
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.app.mall.dynamicImpl.t.sendRequest(android.app.Activity, com.jd.dynamic.entity.RequestEntity, com.jd.dynamic.base.interfaces.INetWorkRequest$ResponseCallBack):void");
    }
}
