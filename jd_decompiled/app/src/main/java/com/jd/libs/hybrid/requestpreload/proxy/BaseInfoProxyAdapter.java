package com.jd.libs.hybrid.requestpreload.proxy;

import android.os.Build;
import com.jd.libs.hybrid.HybridSDK;
import com.jingdong.common.widget.NavigatorHolder;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u000f\b\u0016\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0003\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0005\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u0005\u0010\u0004J\u000f\u0010\u0006\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u0006\u0010\u0004J\u000f\u0010\u0007\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u0007\u0010\u0004J\u000f\u0010\b\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\b\u0010\u0004J\u000f\u0010\t\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\t\u0010\u0004J\u000f\u0010\n\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\n\u0010\u0004J\u000f\u0010\u000b\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u000b\u0010\u0004J\u0019\u0010\r\u001a\u00020\u00022\b\u0010\f\u001a\u0004\u0018\u00010\u0002H\u0016\u00a2\u0006\u0004\b\r\u0010\u000e\u00a8\u0006\u0011"}, d2 = {"Lcom/jd/libs/hybrid/requestpreload/proxy/BaseInfoProxyAdapter;", "Lcom/jd/libs/hybrid/requestpreload/proxy/BaseInfoProxy;", "", "uuid", "()Ljava/lang/String;", "lat", HybridSDK.LNG, HybridSDK.OS_VERSION, "client", HybridSDK.APP_VERSION, "area", "token", "key", NavigatorHolder.NaviEntity.TYPE_CUSTOM, "(Ljava/lang/String;)Ljava/lang/String;", "<init>", "()V", "request-preload_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes16.dex */
public class BaseInfoProxyAdapter extends BaseInfoProxy {
    @Override // com.jd.libs.hybrid.requestpreload.proxy.BaseInfoProxy
    @NotNull
    public String area() {
        return "";
    }

    @Override // com.jd.libs.hybrid.requestpreload.proxy.BaseInfoProxy
    @NotNull
    public String client() {
        return "android";
    }

    @Override // com.jd.libs.hybrid.requestpreload.proxy.BaseInfoProxy
    @NotNull
    public String clientVersion() {
        return "";
    }

    @Override // com.jd.libs.hybrid.requestpreload.proxy.BaseInfoProxy
    @NotNull
    public String custom(@Nullable String key) {
        return "";
    }

    @Override // com.jd.libs.hybrid.requestpreload.proxy.BaseInfoProxy
    @NotNull
    public String lat() {
        return "";
    }

    @Override // com.jd.libs.hybrid.requestpreload.proxy.BaseInfoProxy
    @NotNull
    public String lng() {
        return "";
    }

    @Override // com.jd.libs.hybrid.requestpreload.proxy.BaseInfoProxy
    @NotNull
    public String osVersion() {
        String str = Build.VERSION.RELEASE;
        Intrinsics.checkExpressionValueIsNotNull(str, "Build.VERSION.RELEASE");
        return str;
    }

    @Override // com.jd.libs.hybrid.requestpreload.proxy.BaseInfoProxy
    @NotNull
    public String token() {
        return String.valueOf(System.currentTimeMillis());
    }

    @Override // com.jd.libs.hybrid.requestpreload.proxy.BaseInfoProxy
    @NotNull
    public String uuid() {
        return "";
    }
}
