package com.jingdong.common.utils.personal.platform.floorframe.protocol;

import com.jingdong.sdk.platform.floor.entity.BaseTemplateEntity;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0004\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002JM\u0010\u000b\u001a\u00020\u00072\b\u0010\u0003\u001a\u0004\u0018\u00018\u00002\u001c\u0010\b\u001a\u0018\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u0005\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00042\u0014\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0004H&\u00a2\u0006\u0004\b\u000b\u0010\f\u00a8\u0006\r"}, d2 = {"Lcom/jingdong/common/utils/personal/platform/floorframe/protocol/IDataAssembly;", "T", "", "response", "Lkotlin/Function1;", "", "Lcom/jingdong/sdk/platform/floor/entity/BaseTemplateEntity;", "", "callbackSuccess", "", "callbackError", "handlePlatformResponse", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "personallib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes6.dex */
public interface IDataAssembly<T> {
    void handlePlatformResponse(@Nullable T response, @Nullable Function1<? super List<? extends BaseTemplateEntity>, Unit> callbackSuccess, @Nullable Function1<? super Throwable, Unit> callbackError);
}
