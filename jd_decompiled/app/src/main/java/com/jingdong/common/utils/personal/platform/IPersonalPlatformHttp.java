package com.jingdong.common.utils.personal.platform;

import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0011\u0010\u0003\u001a\u0004\u0018\u00010\u0002H&\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0011\u0010\u0006\u001a\u0004\u0018\u00010\u0005H&\u00a2\u0006\u0004\b\u0006\u0010\u0007\u00a8\u0006\b"}, d2 = {"Lcom/jingdong/common/utils/personal/platform/IPersonalPlatformHttp;", "", "Lcom/jingdong/jdsdk/network/toolbox/HttpSetting;", "getHttpSetting", "()Lcom/jingdong/jdsdk/network/toolbox/HttpSetting;", "", "getMenuStaticSource", "()Ljava/lang/String;", "personallib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes6.dex */
public interface IPersonalPlatformHttp {
    @Nullable
    HttpSetting getHttpSetting();

    @Nullable
    String getMenuStaticSource();
}
