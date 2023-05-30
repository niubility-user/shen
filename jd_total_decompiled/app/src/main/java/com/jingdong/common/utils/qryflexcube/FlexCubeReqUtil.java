package com.jingdong.common.utils.qryflexcube;

import android.os.Handler;
import android.os.Looper;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.utils.FlexCubeUtil;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class FlexCubeReqUtil {
    private static Handler sHandler = new Handler(Looper.getMainLooper());

    /* renamed from: com.jingdong.common.utils.qryflexcube.FlexCubeReqUtil$1  reason: invalid class name */
    /* loaded from: classes6.dex */
    class AnonymousClass1 implements HttpGroup.OnAllListener {
        final /* synthetic */ IHttpFlexCubeCallBack val$callBack;

        AnonymousClass1(IHttpFlexCubeCallBack iHttpFlexCubeCallBack) {
            this.val$callBack = iHttpFlexCubeCallBack;
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            FlexCubeReqUtil.this.handleOnFlexCubeEnd(this.val$callBack, FlexCubeUtil.filterToFlexCubeData(httpResponse.getFastJsonObject()));
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(final HttpError httpError) {
            if (this.val$callBack == null) {
                return;
            }
            Handler handler = FlexCubeReqUtil.sHandler;
            final IHttpFlexCubeCallBack iHttpFlexCubeCallBack = this.val$callBack;
            handler.post(new Runnable() { // from class: com.jingdong.common.utils.qryflexcube.a
                @Override // java.lang.Runnable
                public final void run() {
                    IHttpFlexCubeCallBack.this.onError(httpError);
                }
            });
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
        public void onProgress(int i2, int i3) {
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
        public void onStart() {
        }
    }

    /* loaded from: classes6.dex */
    private static class InnerClass {
        private static FlexCubeReqUtil mInstance = new FlexCubeReqUtil(null);

        private InnerClass() {
        }
    }

    /* synthetic */ FlexCubeReqUtil(AnonymousClass1 anonymousClass1) {
        this();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void a(List list, IHttpFlexCubeCallBack iHttpFlexCubeCallBack) {
        if (list != null) {
            iHttpFlexCubeCallBack.onEnd(list);
        }
    }

    public static FlexCubeReqUtil getInstance() {
        return InnerClass.mInstance;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleOnFlexCubeEnd(final IHttpFlexCubeCallBack iHttpFlexCubeCallBack, final List<JDJSONObject> list) {
        if (iHttpFlexCubeCallBack == null) {
            return;
        }
        sHandler.post(new Runnable() { // from class: com.jingdong.common.utils.qryflexcube.b
            @Override // java.lang.Runnable
            public final void run() {
                FlexCubeReqUtil.a(list, iHttpFlexCubeCallBack);
            }
        });
    }

    public void fetchFlexCubeData(BaseActivity baseActivity, Map<String, String> map, IHttpFlexCubeCallBack iHttpFlexCubeCallBack) {
        if (baseActivity == null || map == null) {
            return;
        }
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId("qryChannelNativeFloors");
        for (String str : map.keySet()) {
            httpSetting.putJsonParam(str, map.get(str));
        }
        httpSetting.setHost(Configuration.getPortalHost());
        httpSetting.setOnTouchEvent(false);
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setNeedRetryOnBusinessLayer(false);
        httpSetting.setListener(new AnonymousClass1(iHttpFlexCubeCallBack));
        baseActivity.getHttpGroupaAsynPool().add(httpSetting);
    }

    private FlexCubeReqUtil() {
    }
}
