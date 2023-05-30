package com.jingdong.common.entity.personal.dynamic;

import com.jingdong.common.entity.personal.base.BaseFloorEntity;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b&\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\t\u0010\nR$\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b\u00a8\u0006\u000b"}, d2 = {"Lcom/jingdong/common/entity/personal/dynamic/ISVAbstractFloorEntity;", "Lcom/jingdong/common/entity/personal/base/BaseFloorEntity;", "Lcom/jingdong/common/entity/personal/dynamic/ISVExtEntity;", "dynExt", "Lcom/jingdong/common/entity/personal/dynamic/ISVExtEntity;", "getDynExt", "()Lcom/jingdong/common/entity/personal/dynamic/ISVExtEntity;", "setDynExt", "(Lcom/jingdong/common/entity/personal/dynamic/ISVExtEntity;)V", "<init>", "()V", "personallib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes5.dex */
public abstract class ISVAbstractFloorEntity extends BaseFloorEntity {
    @Nullable
    private ISVExtEntity dynExt;

    @Nullable
    public final ISVExtEntity getDynExt() {
        return this.dynExt;
    }

    public final void setDynExt(@Nullable ISVExtEntity iSVExtEntity) {
        this.dynExt = iSVExtEntity;
    }
}
