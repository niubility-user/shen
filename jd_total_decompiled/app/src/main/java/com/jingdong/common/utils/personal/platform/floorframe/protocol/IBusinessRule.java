package com.jingdong.common.utils.personal.platform.floorframe.protocol;

import com.jingdong.sdk.platform.floor.entity.BaseTemplateEntity;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u001f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J'\u0010\u0006\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u00052\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002H&\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\t\u001a\u00020\bH&\u00a2\u0006\u0004\b\t\u0010\n\u00a8\u0006\u000b"}, d2 = {"Lcom/jingdong/common/utils/personal/platform/floorframe/protocol/IBusinessRule;", "", "", "Lcom/jingdong/sdk/platform/floor/entity/BaseTemplateEntity;", "floors", "", "filterInvalidFloors", "(Ljava/util/Collection;)Ljava/util/List;", "", "sortSourceDataList", "()V", "personallib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes6.dex */
public interface IBusinessRule {
    @NotNull
    List<BaseTemplateEntity> filterInvalidFloors(@Nullable Collection<BaseTemplateEntity> floors);

    void sortSourceDataList();
}
