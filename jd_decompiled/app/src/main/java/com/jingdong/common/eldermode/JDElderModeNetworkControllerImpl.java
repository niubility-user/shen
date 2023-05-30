package com.jingdong.common.eldermode;

import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.sdk.eldermode.helper.IElderModeNetworkController;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes5.dex */
public class JDElderModeNetworkControllerImpl implements IElderModeNetworkController {
    @Override // com.jingdong.sdk.eldermode.helper.IElderModeNetworkController
    public void request(@NotNull String str, @Nullable Map<String, String> map, int i2, @Nullable final Function1<? super String, Unit> function1, @Nullable final Function1<? super Throwable, Unit> function12) {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId(str);
        httpSetting.setEffect(0);
        httpSetting.setCacheMode(2);
        httpSetting.setHost(Configuration.getPersonalHost());
        httpSetting.setTopPriority(true);
        httpSetting.setEnableGatewayQueue(false);
        if (i2 > 0) {
            httpSetting.setCallTimeout(i2);
        }
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                httpSetting.putJsonParam(entry.getKey(), entry.getValue());
            }
        }
        httpSetting.setListener(new HttpGroup.OnCommonListener() { // from class: com.jingdong.common.eldermode.JDElderModeNetworkControllerImpl.1
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                Function1 function13 = function1;
                if (function13 != null) {
                    function13.invoke(httpResponse.getString());
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                Function1 function13 = function12;
                if (function13 != null) {
                    function13.invoke(httpError);
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
            public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
            }
        });
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }
}
