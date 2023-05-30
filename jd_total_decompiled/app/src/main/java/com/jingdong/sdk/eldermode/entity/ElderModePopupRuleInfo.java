package com.jingdong.sdk.eldermode.entity;

import com.jd.viewkit.templates.view.helper.JDViewKitEventHelper;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\t\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0002\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0005\u0010\u0004J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0006\u0010\u0004J4\u0010\n\u001a\u00020\u00002\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0002H\u00c6\u0001\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\r\u001a\u00020\fH\u00d6\u0001\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u0002H\u00d6\u0001\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u001a\u0010\u0013\u001a\u00020\u00122\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003\u00a2\u0006\u0004\b\u0013\u0010\u0014R\u001b\u0010\b\u001a\u0004\u0018\u00010\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\b\u0010\u0015\u001a\u0004\b\u0016\u0010\u0004R\u001b\u0010\u0007\u001a\u0004\u0018\u00010\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0007\u0010\u0015\u001a\u0004\b\u0017\u0010\u0004R\u001b\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\t\u0010\u0015\u001a\u0004\b\u0018\u0010\u0004\u00a8\u0006\u001b"}, d2 = {"Lcom/jingdong/sdk/eldermode/entity/ElderModePopupRuleInfo;", "", "", "component1", "()Ljava/lang/Integer;", "component2", "component3", "intervalTime", "totalTimes", "periodId", JDViewKitEventHelper.ActionType_Copy, "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)Lcom/jingdong/sdk/eldermode/entity/ElderModePopupRuleInfo;", "", "toString", "()Ljava/lang/String;", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/Integer;", "getTotalTimes", "getIntervalTime", "getPeriodId", "<init>", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "eldermodelib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes7.dex */
public final /* data */ class ElderModePopupRuleInfo {
    @Nullable
    private final Integer intervalTime;
    @Nullable
    private final Integer periodId;
    @Nullable
    private final Integer totalTimes;

    public ElderModePopupRuleInfo(@Nullable Integer num, @Nullable Integer num2, @Nullable Integer num3) {
        this.intervalTime = num;
        this.totalTimes = num2;
        this.periodId = num3;
    }

    public static /* synthetic */ ElderModePopupRuleInfo copy$default(ElderModePopupRuleInfo elderModePopupRuleInfo, Integer num, Integer num2, Integer num3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            num = elderModePopupRuleInfo.intervalTime;
        }
        if ((i2 & 2) != 0) {
            num2 = elderModePopupRuleInfo.totalTimes;
        }
        if ((i2 & 4) != 0) {
            num3 = elderModePopupRuleInfo.periodId;
        }
        return elderModePopupRuleInfo.copy(num, num2, num3);
    }

    @Nullable
    /* renamed from: component1  reason: from getter */
    public final Integer getIntervalTime() {
        return this.intervalTime;
    }

    @Nullable
    /* renamed from: component2  reason: from getter */
    public final Integer getTotalTimes() {
        return this.totalTimes;
    }

    @Nullable
    /* renamed from: component3  reason: from getter */
    public final Integer getPeriodId() {
        return this.periodId;
    }

    @NotNull
    public final ElderModePopupRuleInfo copy(@Nullable Integer intervalTime, @Nullable Integer totalTimes, @Nullable Integer periodId) {
        return new ElderModePopupRuleInfo(intervalTime, totalTimes, periodId);
    }

    public boolean equals(@Nullable Object other) {
        if (this != other) {
            if (other instanceof ElderModePopupRuleInfo) {
                ElderModePopupRuleInfo elderModePopupRuleInfo = (ElderModePopupRuleInfo) other;
                return Intrinsics.areEqual(this.intervalTime, elderModePopupRuleInfo.intervalTime) && Intrinsics.areEqual(this.totalTimes, elderModePopupRuleInfo.totalTimes) && Intrinsics.areEqual(this.periodId, elderModePopupRuleInfo.periodId);
            }
            return false;
        }
        return true;
    }

    @Nullable
    public final Integer getIntervalTime() {
        return this.intervalTime;
    }

    @Nullable
    public final Integer getPeriodId() {
        return this.periodId;
    }

    @Nullable
    public final Integer getTotalTimes() {
        return this.totalTimes;
    }

    public int hashCode() {
        Integer num = this.intervalTime;
        int hashCode = (num != null ? num.hashCode() : 0) * 31;
        Integer num2 = this.totalTimes;
        int hashCode2 = (hashCode + (num2 != null ? num2.hashCode() : 0)) * 31;
        Integer num3 = this.periodId;
        return hashCode2 + (num3 != null ? num3.hashCode() : 0);
    }

    @NotNull
    public String toString() {
        return "ElderModePopupRuleInfo(intervalTime=" + this.intervalTime + ", totalTimes=" + this.totalTimes + ", periodId=" + this.periodId + ")";
    }
}
