package com.jingdong.sdk.lib.isvmonitor.b;

import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes8.dex */
public final class a {
    public static final a a = new a();

    /* renamed from: com.jingdong.sdk.lib.isvmonitor.b.a$a  reason: collision with other inner class name */
    /* loaded from: classes8.dex */
    public static final class C0738a implements HttpGroup.OnCommonListener {
        C0738a() {
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(@Nullable HttpResponse httpResponse) {
            com.jingdong.sdk.lib.isvmonitor.c.a.a.a("onEnd");
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(@Nullable HttpError httpError) {
            com.jingdong.sdk.lib.isvmonitor.c.a.a.a("onError");
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
        public void onReady(@Nullable HttpGroup.HttpSettingParams httpSettingParams) {
            com.jingdong.sdk.lib.isvmonitor.c.a.a.a("onReady");
        }
    }

    private a() {
    }

    public final void a(@NotNull HttpSetting httpSetting, @NotNull Map<String, ? extends Object> map) {
        for (Map.Entry<String, ? extends Object> entry : map.entrySet()) {
            httpSetting.putJsonParam(entry.getKey(), entry.getValue());
        }
    }

    public final void b(@NotNull Map<String, ? extends Object> map) {
        if (map.isEmpty()) {
            return;
        }
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId("isvclierr");
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setHost("api.m.jd.com");
        a.a(httpSetting, map);
        httpSetting.setListener(new C0738a());
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }
}
