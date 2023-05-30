package com.jd.libs.hybrid.requestpreload.proxy;

import com.jd.libs.hybrid.HybridSDK;
import com.jingdong.common.widget.NavigatorHolder;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0011\b&\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0007\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0003\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0005\u001a\u00020\u0002H&\u00a2\u0006\u0004\b\u0005\u0010\u0004J\u000f\u0010\u0006\u001a\u00020\u0002H&\u00a2\u0006\u0004\b\u0006\u0010\u0004J\u000f\u0010\u0007\u001a\u00020\u0002H&\u00a2\u0006\u0004\b\u0007\u0010\u0004J\u000f\u0010\b\u001a\u00020\u0002H&\u00a2\u0006\u0004\b\b\u0010\u0004J\u000f\u0010\t\u001a\u00020\u0002H&\u00a2\u0006\u0004\b\t\u0010\u0004J\u000f\u0010\n\u001a\u00020\u0002H&\u00a2\u0006\u0004\b\n\u0010\u0004J\u000f\u0010\u000b\u001a\u00020\u0002H&\u00a2\u0006\u0004\b\u000b\u0010\u0004J\u000f\u0010\f\u001a\u00020\u0002H&\u00a2\u0006\u0004\b\f\u0010\u0004J\u0019\u0010\u000e\u001a\u00020\u00022\b\u0010\r\u001a\u0004\u0018\u00010\u0002H&\u00a2\u0006\u0004\b\u000e\u0010\u000f\u00a8\u0006\u0013"}, d2 = {"Lcom/jd/libs/hybrid/requestpreload/proxy/BaseInfoProxy;", "Lcom/jd/libs/hybrid/requestpreload/proxy/IProxy;", "", "getName", "()Ljava/lang/String;", "uuid", "lat", HybridSDK.LNG, HybridSDK.OS_VERSION, "client", HybridSDK.APP_VERSION, "area", "token", "key", NavigatorHolder.NaviEntity.TYPE_CUSTOM, "(Ljava/lang/String;)Ljava/lang/String;", "<init>", "()V", "Companion", "request-preload_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes16.dex */
public abstract class BaseInfoProxy implements IProxy {
    @NotNull
    public static final String PROXY_NAME = "BaseInfoProxy";

    @NotNull
    public abstract String area();

    @NotNull
    public abstract String client();

    @NotNull
    public abstract String clientVersion();

    @NotNull
    public abstract String custom(@Nullable String key);

    @Override // com.jd.libs.hybrid.requestpreload.proxy.IProxy
    @NotNull
    public String getName() {
        return PROXY_NAME;
    }

    @NotNull
    public abstract String lat();

    @NotNull
    public abstract String lng();

    @NotNull
    public abstract String osVersion();

    @NotNull
    public abstract String token();

    @NotNull
    public abstract String uuid();
}
