package com.jingdong.common.entity.personal.dynamic;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\r\u0010\u000eJ\u001f\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\u000b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0016\u00a2\u0006\u0004\b\u000b\u0010\f\u00a8\u0006\u000f"}, d2 = {"Lcom/jingdong/common/entity/personal/dynamic/ISVDynFloorEntity;", "Lcom/jingdong/common/entity/personal/dynamic/ISVAbstractFloorEntity;", "Lcom/google/gson/Gson;", "gson", "Lcom/google/gson/JsonObject;", "dataJsonObject", "", "parserEncrypt", "(Lcom/google/gson/Gson;Lcom/google/gson/JsonObject;)V", "", "totalSpanSize", "getSpanSize", "(I)I", "<init>", "()V", "personallib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes5.dex */
public final class ISVDynFloorEntity extends ISVAbstractFloorEntity {
    @Override // com.jingdong.common.entity.personal.base.BaseFloorEntity, com.jingdong.common.entity.personal.base.SpanSizeLookup
    public int getSpanSize(int totalSpanSize) {
        Integer spanCount;
        ISVExtEntity dynExt = getDynExt();
        if (dynExt != null && (spanCount = dynExt.getSpanCount()) != null) {
            if (!(spanCount.intValue() != 0)) {
                spanCount = null;
            }
            if (spanCount != null) {
                return totalSpanSize / spanCount.intValue();
            }
        }
        return super.getSpanSize(totalSpanSize);
    }

    @Override // com.jingdong.common.entity.personal.base.BaseFloorEntity
    public void parserEncrypt(@NotNull Gson gson, @NotNull JsonObject dataJsonObject) {
    }
}
