package com.jingdong.sdk.eldermode.entity;

import com.jd.viewkit.templates.view.helper.JDViewKitEventHelper;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u000b\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0004\b\u0006\u0010\u0007J(\u0010\n\u001a\u00020\u00002\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0005H\u00c6\u0001\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\f\u001a\u00020\u0005H\u00d6\u0001\u00a2\u0006\u0004\b\f\u0010\u0007J\u0010\u0010\r\u001a\u00020\u0002H\u00d6\u0001\u00a2\u0006\u0004\b\r\u0010\u000eJ\u001a\u0010\u0011\u001a\u00020\u00102\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003\u00a2\u0006\u0004\b\u0011\u0010\u0012R\u001b\u0010\b\u001a\u0004\u0018\u00010\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\b\u0010\u0013\u001a\u0004\b\u0014\u0010\u0004R$\u0010\t\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\t\u0010\u0015\u001a\u0004\b\u0016\u0010\u0007\"\u0004\b\u0017\u0010\u0018\u00a8\u0006\u001b"}, d2 = {"Lcom/jingdong/sdk/eldermode/entity/ElderModeResponseOthers;", "", "", "component1", "()Ljava/lang/Integer;", "", "component2", "()Ljava/lang/String;", "caringSwitch", "toastInfo", JDViewKitEventHelper.ActionType_Copy, "(Ljava/lang/Integer;Ljava/lang/String;)Lcom/jingdong/sdk/eldermode/entity/ElderModeResponseOthers;", "toString", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/Integer;", "getCaringSwitch", "Ljava/lang/String;", "getToastInfo", "setToastInfo", "(Ljava/lang/String;)V", "<init>", "(Ljava/lang/Integer;Ljava/lang/String;)V", "eldermodelib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes7.dex */
public final /* data */ class ElderModeResponseOthers {
    @Nullable
    private final Integer caringSwitch;
    @Nullable
    private String toastInfo;

    public ElderModeResponseOthers(@Nullable Integer num, @Nullable String str) {
        this.caringSwitch = num;
        this.toastInfo = str;
    }

    public static /* synthetic */ ElderModeResponseOthers copy$default(ElderModeResponseOthers elderModeResponseOthers, Integer num, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            num = elderModeResponseOthers.caringSwitch;
        }
        if ((i2 & 2) != 0) {
            str = elderModeResponseOthers.toastInfo;
        }
        return elderModeResponseOthers.copy(num, str);
    }

    @Nullable
    /* renamed from: component1  reason: from getter */
    public final Integer getCaringSwitch() {
        return this.caringSwitch;
    }

    @Nullable
    /* renamed from: component2  reason: from getter */
    public final String getToastInfo() {
        return this.toastInfo;
    }

    @NotNull
    public final ElderModeResponseOthers copy(@Nullable Integer caringSwitch, @Nullable String toastInfo) {
        return new ElderModeResponseOthers(caringSwitch, toastInfo);
    }

    public boolean equals(@Nullable Object other) {
        if (this != other) {
            if (other instanceof ElderModeResponseOthers) {
                ElderModeResponseOthers elderModeResponseOthers = (ElderModeResponseOthers) other;
                return Intrinsics.areEqual(this.caringSwitch, elderModeResponseOthers.caringSwitch) && Intrinsics.areEqual(this.toastInfo, elderModeResponseOthers.toastInfo);
            }
            return false;
        }
        return true;
    }

    @Nullable
    public final Integer getCaringSwitch() {
        return this.caringSwitch;
    }

    @Nullable
    public final String getToastInfo() {
        return this.toastInfo;
    }

    public int hashCode() {
        Integer num = this.caringSwitch;
        int hashCode = (num != null ? num.hashCode() : 0) * 31;
        String str = this.toastInfo;
        return hashCode + (str != null ? str.hashCode() : 0);
    }

    public final void setToastInfo(@Nullable String str) {
        this.toastInfo = str;
    }

    @NotNull
    public String toString() {
        return "ElderModeResponseOthers(caringSwitch=" + this.caringSwitch + ", toastInfo=" + this.toastInfo + ")";
    }
}
