package com.jingdong.sdk.bmode.util;

import androidx.annotation.Nullable;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.sdk.eldermode.helper.IElderModeHelper;
import com.jingdong.sdk.eldermode.util.JDElderModeDataHelper;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import kotlin.Unit;
import kotlin.jvm.functions.Function4;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/* loaded from: classes.dex */
public class JDBModeCallFromLoginUtil {
    public static final int CALL_TIME_OUT = 2000;
    private static final CompositeSubscription subscriptions = new CompositeSubscription();

    /* loaded from: classes.dex */
    public static class RequestFunction implements Function4<IElderModeHelper, Map<String, String>, Subscriber<? super String>, Integer, Unit> {
        private static void handleCallFormLoginRequest(@Nullable IElderModeHelper iElderModeHelper, @Nullable Map<String, String> map, @Nullable Subscriber<? super String> subscriber, int i2) {
            JDBModeCallFromLoginUtil.log(iElderModeHelper, "\u767b\u5f55\u94fe\u8def\u5f00\u59cb\u8bf7\u6c42\u6570\u636e\u5f00\u59cb");
            HttpGroupUtils.getHttpGroupaAsynPool().add(JDBModeCallFromLoginUtil.getHttpSetting(iElderModeHelper, map, subscriber));
        }

        @Override // kotlin.jvm.functions.Function4
        public Unit invoke(IElderModeHelper iElderModeHelper, Map<String, String> map, Subscriber<? super String> subscriber, Integer num) {
            handleCallFormLoginRequest(iElderModeHelper, map, subscriber, num.intValue());
            return Unit.INSTANCE;
        }
    }

    public static HttpGroup.OnCommonListener getCommonListener(@Nullable final Subscriber<? super String> subscriber) {
        return new HttpGroup.OnCommonListener() { // from class: com.jingdong.sdk.bmode.util.JDBModeCallFromLoginUtil.4
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                Subscriber subscriber2 = Subscriber.this;
                if (subscriber2 == null || subscriber2.isUnsubscribed()) {
                    return;
                }
                Subscriber.this.onNext(httpResponse == null ? "" : httpResponse.getString());
                Subscriber.this.onCompleted();
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                Subscriber subscriber2 = Subscriber.this;
                if (subscriber2 != null) {
                    subscriber2.onError(httpError);
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
            public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
            }
        };
    }

    public static HttpSetting getHttpSetting(@Nullable IElderModeHelper iElderModeHelper, @Nullable Map<String, String> map, @Nullable Subscriber<? super String> subscriber) {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId(JDElderModeDataHelper.FUNCTION_ID_USER_CARING_PATTERN);
        httpSetting.setEffect(0);
        httpSetting.setCacheMode(2);
        httpSetting.setHost(Configuration.getPersonalHost());
        httpSetting.setTopPriority(true);
        httpSetting.setEnableGatewayQueue(false);
        httpSetting.setNeedRetryOnNetworkLayer(false);
        httpSetting.setCallTimeout(2000);
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                httpSetting.putJsonParam(entry.getKey(), entry.getValue());
            }
        }
        if (iElderModeHelper != null) {
            iElderModeHelper.log("\u767b\u5f55\u94fe\u8def\u5f00\u59cb\u8bf7\u6c42\u6570\u636e\uff0c\u8bf7\u6c42\u5165\u53c2\uff1a" + map);
        }
        httpSetting.setListener(getCommonListener(subscriber));
        return httpSetting;
    }

    public static void log(@Nullable IElderModeHelper iElderModeHelper, @Nullable String str) {
        if (iElderModeHelper != null) {
            iElderModeHelper.log(str);
        }
    }

    public static void request(@Nullable final IElderModeHelper iElderModeHelper, @Nullable final Map<String, String> map, @Nullable final RequestModeCallback requestModeCallback, final int i2, final Function4 function4) {
        try {
            unSubscribe();
            subscriptions.add(Observable.create(new Observable.OnSubscribe<String>() { // from class: com.jingdong.sdk.bmode.util.JDBModeCallFromLoginUtil.3
                @Override // rx.functions.Action1
                public void call(Subscriber<? super String> subscriber) {
                    Function4 function42 = Function4.this;
                    if (function42 != null) {
                        function42.invoke(iElderModeHelper, map, subscriber, Integer.valueOf(i2));
                    }
                }
            }).timeout(2000L, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.computation()).observeOn(Schedulers.immediate()).subscribe(new Action1<String>() { // from class: com.jingdong.sdk.bmode.util.JDBModeCallFromLoginUtil.1
                @Override // rx.functions.Action1
                public void call(String str) {
                    JDBModeUtils.handleMajorResponseWithLogin(str, true);
                    RequestModeCallback requestModeCallback2 = RequestModeCallback.this;
                    if (requestModeCallback2 != null) {
                        requestModeCallback2.success();
                    }
                    IElderModeHelper iElderModeHelper2 = iElderModeHelper;
                    if (iElderModeHelper2 != null) {
                        iElderModeHelper2.log("\u767b\u5f55\u94fe\u8def\u5f00\u59cb\u8bf7\u6c42\u6570\u636e\u6210\u529f");
                    }
                }
            }, new Action1<Throwable>() { // from class: com.jingdong.sdk.bmode.util.JDBModeCallFromLoginUtil.2
                @Override // rx.functions.Action1
                public void call(Throwable th) {
                    RequestModeCallback requestModeCallback2 = RequestModeCallback.this;
                    if (requestModeCallback2 != null) {
                        requestModeCallback2.fail();
                    }
                    IElderModeHelper iElderModeHelper2 = iElderModeHelper;
                    if (iElderModeHelper2 != null) {
                        iElderModeHelper2.log("\u767b\u5f55\u94fe\u8def\u5f00\u59cb\u8bf7\u6c42\u6570\u636e\u5931\u8d25\uff0c\u5f02\u5e38\u4fe1\u606f\uff1a" + th);
                    }
                }
            }));
        } catch (Throwable th) {
            if (requestModeCallback != null) {
                requestModeCallback.fail();
            }
            if (iElderModeHelper != null) {
                iElderModeHelper.log("\u767b\u5f55\u94fe\u8def\u5f00\u59cb\u8bf7\u6c42\u6570\u636e\u5931\u8d25\uff0c\u5f02\u5e38\u4fe1\u606f\uff1a" + th);
            }
        }
    }

    private static void unSubscribe() {
        CompositeSubscription compositeSubscription = subscriptions;
        if (compositeSubscription != null) {
            compositeSubscription.clear();
        }
    }
}
