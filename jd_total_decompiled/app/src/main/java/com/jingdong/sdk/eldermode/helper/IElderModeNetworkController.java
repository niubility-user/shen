package com.jingdong.sdk.eldermode.helper;

import com.jingdong.app.mall.e;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\u0085\u0001\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0003\u001a\u00020\u00022\u0014\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062%\u0010\r\u001a!\u0012\u0015\u0012\u0013\u0018\u00010\u0002\u00a2\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f\u0018\u00010\b2%\u0010\u0010\u001a!\u0012\u0015\u0012\u0013\u0018\u00010\u000e\u00a2\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\f\u0018\u00010\bH&\u00a2\u0006\u0004\b\u0011\u0010\u0012\u00a8\u0006\u0013"}, d2 = {"Lcom/jingdong/sdk/eldermode/helper/IElderModeNetworkController;", "", "", "functionId", "", "params", "", "callTimeout", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "response", "", "success", "", e.a, "fail", "request", "(Ljava/lang/String;Ljava/util/Map;ILkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "eldermodelib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public interface IElderModeNetworkController {

    @Metadata(bv = {1, 0, 3}, d1 = {}, d2 = {}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes7.dex */
    public static final class DefaultImpls {
        public static /* synthetic */ void request$default(IElderModeNetworkController iElderModeNetworkController, String str, Map map, int i2, Function1 function1, Function1 function12, int i3, Object obj) {
            if (obj == null) {
                iElderModeNetworkController.request(str, map, (i3 & 4) != 0 ? -1 : i2, function1, function12);
                return;
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: request");
        }
    }

    void request(@NotNull String functionId, @Nullable Map<String, String> params, int callTimeout, @Nullable Function1<? super String, Unit> success, @Nullable Function1<? super Throwable, Unit> fail);
}
