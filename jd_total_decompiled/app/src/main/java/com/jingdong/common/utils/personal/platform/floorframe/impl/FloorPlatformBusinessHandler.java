package com.jingdong.common.utils.personal.platform.floorframe.impl;

import com.jingdong.common.utils.personal.platform.floorframe.protocol.IBusinessRule;
import com.jingdong.common.utils.personal.platform.floorframe.protocol.IDataAssembly;
import com.jingdong.sdk.platform.floor.entity.BaseTemplateEntity;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0010\u001f\n\u0002\b\u0007\b\u0016\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00028\u00000\u00022\u00020\u0003B\u0007\u00a2\u0006\u0004\b\u0014\u0010\u0013JK\u0010\f\u001a\u00020\b2\b\u0010\u0004\u001a\u0004\u0018\u00018\u00002\u001a\u0010\t\u001a\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0004\u0012\u00020\b\u0018\u00010\u00052\u0014\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0005H\u0016\u00a2\u0006\u0004\b\f\u0010\rJ'\u0010\u0010\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00070\u00062\u000e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u000eH\u0016\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0012\u001a\u00020\bH\u0016\u00a2\u0006\u0004\b\u0012\u0010\u0013\u00a8\u0006\u0015"}, d2 = {"Lcom/jingdong/common/utils/personal/platform/floorframe/impl/FloorPlatformBusinessHandler;", "T", "Lcom/jingdong/common/utils/personal/platform/floorframe/protocol/IDataAssembly;", "Lcom/jingdong/common/utils/personal/platform/floorframe/protocol/IBusinessRule;", "response", "Lkotlin/Function1;", "", "Lcom/jingdong/sdk/platform/floor/entity/BaseTemplateEntity;", "", "callbackSuccess", "", "callbackError", "handlePlatformResponse", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "", "floors", "filterInvalidFloors", "(Ljava/util/Collection;)Ljava/util/List;", "sortSourceDataList", "()V", "<init>", "personallib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes6.dex */
public class FloorPlatformBusinessHandler<T> implements IDataAssembly<T>, IBusinessRule {
    @Override // com.jingdong.common.utils.personal.platform.floorframe.protocol.IBusinessRule
    @NotNull
    public List<BaseTemplateEntity> filterInvalidFloors(@Nullable Collection<BaseTemplateEntity> floors) {
        List<BaseTemplateEntity> emptyList;
        emptyList = CollectionsKt__CollectionsKt.emptyList();
        return emptyList;
    }

    @Override // com.jingdong.common.utils.personal.platform.floorframe.protocol.IDataAssembly
    public void handlePlatformResponse(@Nullable T response, @Nullable Function1<? super List<? extends BaseTemplateEntity>, Unit> callbackSuccess, @Nullable Function1<? super Throwable, Unit> callbackError) {
    }

    @Override // com.jingdong.common.utils.personal.platform.floorframe.protocol.IBusinessRule
    public void sortSourceDataList() {
    }
}
