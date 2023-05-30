package com.jingdong.common.entity.personal.dynamic;

import com.jd.viewkit.templates.view.helper.JDViewKitEventHelper;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u001c\u0010\u0006\u001a\u00020\u00002\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0002H\u00c6\u0001\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\t\u001a\u00020\bH\u00d6\u0001\u00a2\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u000b\u001a\u00020\u0002H\u00d6\u0001\u00a2\u0006\u0004\b\u000b\u0010\fJ\u001a\u0010\u000f\u001a\u00020\u000e2\b\u0010\r\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003\u00a2\u0006\u0004\b\u000f\u0010\u0010R\u001b\u0010\u0005\u001a\u0004\u0018\u00010\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0005\u0010\u0011\u001a\u0004\b\u0012\u0010\u0004\u00a8\u0006\u0015"}, d2 = {"Lcom/jingdong/common/entity/personal/dynamic/ISVExtEntity;", "", "", "component1", "()Ljava/lang/Integer;", "spanCount", JDViewKitEventHelper.ActionType_Copy, "(Ljava/lang/Integer;)Lcom/jingdong/common/entity/personal/dynamic/ISVExtEntity;", "", "toString", "()Ljava/lang/String;", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/Integer;", "getSpanCount", "<init>", "(Ljava/lang/Integer;)V", "personallib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes5.dex */
public final /* data */ class ISVExtEntity {
    @Nullable
    private final Integer spanCount;

    public ISVExtEntity(@Nullable Integer num) {
        this.spanCount = num;
    }

    public static /* synthetic */ ISVExtEntity copy$default(ISVExtEntity iSVExtEntity, Integer num, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            num = iSVExtEntity.spanCount;
        }
        return iSVExtEntity.copy(num);
    }

    @Nullable
    /* renamed from: component1  reason: from getter */
    public final Integer getSpanCount() {
        return this.spanCount;
    }

    @NotNull
    public final ISVExtEntity copy(@Nullable Integer spanCount) {
        return new ISVExtEntity(spanCount);
    }

    public boolean equals(@Nullable Object other) {
        if (this != other) {
            return (other instanceof ISVExtEntity) && Intrinsics.areEqual(this.spanCount, ((ISVExtEntity) other).spanCount);
        }
        return true;
    }

    @Nullable
    public final Integer getSpanCount() {
        return this.spanCount;
    }

    public int hashCode() {
        Integer num = this.spanCount;
        if (num != null) {
            return num.hashCode();
        }
        return 0;
    }

    @NotNull
    public String toString() {
        return "ISVExtEntity(spanCount=" + this.spanCount + ")";
    }
}
