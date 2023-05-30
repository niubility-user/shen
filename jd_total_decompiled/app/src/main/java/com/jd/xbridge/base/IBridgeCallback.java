package com.jd.xbridge.base;

import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0019\u0010\u0004\u001a\u00020\u00032\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001H&\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u0019\u0010\b\u001a\u00020\u00032\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0016\u00a2\u0006\u0004\b\b\u0010\t\u00a8\u0006\n"}, d2 = {"Lcom/jd/xbridge/base/IBridgeCallback;", "", "result", "", "onSuccess", "(Ljava/lang/Object;)V", "", "errMsg", "onError", "(Ljava/lang/String;)V", "XBridge_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes18.dex */
public interface IBridgeCallback {
    void onError(@Nullable String errMsg);

    void onSuccess(@Nullable Object result);
}
