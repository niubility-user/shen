package com.jingdong.common.utils.personal.platform.impl;

import com.jingdong.common.utils.personal.platform.IPersonalPlatformHttp;
import com.jingdong.common.utils.personal.platform.JDPersonalPlatformHttp;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\u0018\u00002\u00020\u0001B\u0013\b\u0016\u0012\b\b\u0002\u0010\f\u001a\u00020\u0005\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0011\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0016\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0011\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0016\u00a2\u0006\u0004\b\u0006\u0010\u0007R\u0018\u0010\b\u001a\u0004\u0018\u00010\u00058\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\b\u0010\tR\u0018\u0010\n\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\n\u0010\u000b\u00a8\u0006\u000f"}, d2 = {"Lcom/jingdong/common/utils/personal/platform/impl/JDPersonalPlatformHttpProxy;", "Lcom/jingdong/common/utils/personal/platform/IPersonalPlatformHttp;", "Lcom/jingdong/jdsdk/network/toolbox/HttpSetting;", "getHttpSetting", "()Lcom/jingdong/jdsdk/network/toolbox/HttpSetting;", "", "getMenuStaticSource", "()Ljava/lang/String;", "menuStaticSourceConfig", "Ljava/lang/String;", "httpSetting", "Lcom/jingdong/jdsdk/network/toolbox/HttpSetting;", "menuStaticSource", "<init>", "(Ljava/lang/String;)V", "personallib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes6.dex */
public final class JDPersonalPlatformHttpProxy implements IPersonalPlatformHttp {
    private HttpSetting httpSetting;
    private String menuStaticSourceConfig;

    public /* synthetic */ JDPersonalPlatformHttpProxy(String str, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? "0" : str);
    }

    @Override // com.jingdong.common.utils.personal.platform.IPersonalPlatformHttp
    @Nullable
    public HttpSetting getHttpSetting() {
        return this.httpSetting;
    }

    @Override // com.jingdong.common.utils.personal.platform.IPersonalPlatformHttp
    @Nullable
    /* renamed from: getMenuStaticSource  reason: from getter */
    public String getMenuStaticSourceConfig() {
        return this.menuStaticSourceConfig;
    }

    public JDPersonalPlatformHttpProxy(@NotNull String str) {
        this.menuStaticSourceConfig = str;
        this.httpSetting = new JDPersonalPlatformHttp(str).getHttpSetting();
    }
}
