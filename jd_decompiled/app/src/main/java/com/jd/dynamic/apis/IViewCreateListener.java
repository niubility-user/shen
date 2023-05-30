package com.jd.dynamic.apis;

import androidx.annotation.Keep;
import com.jd.dynamic.lib.error.DynamicException;
import jpbury.t;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Keep
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bg\u0018\u00002\u00020\u0001J\u000f\u0010\u0003\u001a\u00020\u0002H&\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0017\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0005H&\u00a2\u0006\u0004\b\u0007\u0010\b\u00a8\u0006\t"}, d2 = {"Lcom/jd/dynamic/apis/IViewCreateListener;", "", "", "onDynamicViewCreate", "()V", "Lcom/jd/dynamic/lib/error/DynamicException;", t.f20145j, "onError", "(Lcom/jd/dynamic/lib/error/DynamicException;)V", "lib_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes13.dex */
public interface IViewCreateListener {
    void onDynamicViewCreate();

    void onError(@NotNull DynamicException exception);
}
