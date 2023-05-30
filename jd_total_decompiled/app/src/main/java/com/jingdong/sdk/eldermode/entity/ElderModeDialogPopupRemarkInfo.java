package com.jingdong.sdk.eldermode.entity;

import com.jd.viewkit.templates.view.helper.JDViewKitEventHelper;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\n\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0005\u0010\u0004J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003\u00a2\u0006\u0004\b\u0007\u0010\bJ4\u0010\f\u001a\u00020\u00002\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0006H\u00c6\u0001\u00a2\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u0002H\u00d6\u0001\u00a2\u0006\u0004\b\u000e\u0010\u0004J\u0010\u0010\u0010\u001a\u00020\u000fH\u00d6\u0001\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u001a\u0010\u0014\u001a\u00020\u00132\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003\u00a2\u0006\u0004\b\u0014\u0010\u0015R\u001b\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\t\u0010\u0016\u001a\u0004\b\u0017\u0010\u0004R\u001b\u0010\n\u001a\u0004\u0018\u00010\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\n\u0010\u0016\u001a\u0004\b\u0018\u0010\u0004R\u001b\u0010\u000b\u001a\u0004\u0018\u00010\u00068\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u000b\u0010\u0019\u001a\u0004\b\u001a\u0010\b\u00a8\u0006\u001d"}, d2 = {"Lcom/jingdong/sdk/eldermode/entity/ElderModeDialogPopupRemarkInfo;", "", "", "component1", "()Ljava/lang/String;", "component2", "Lcom/jingdong/sdk/eldermode/entity/ElderModeDialogJumpInfo;", "component3", "()Lcom/jingdong/sdk/eldermode/entity/ElderModeDialogJumpInfo;", "title", "jumpTitle", "jumpInfo", JDViewKitEventHelper.ActionType_Copy, "(Ljava/lang/String;Ljava/lang/String;Lcom/jingdong/sdk/eldermode/entity/ElderModeDialogJumpInfo;)Lcom/jingdong/sdk/eldermode/entity/ElderModeDialogPopupRemarkInfo;", "toString", "", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getTitle", "getJumpTitle", "Lcom/jingdong/sdk/eldermode/entity/ElderModeDialogJumpInfo;", "getJumpInfo", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/jingdong/sdk/eldermode/entity/ElderModeDialogJumpInfo;)V", "eldermodelib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes7.dex */
public final /* data */ class ElderModeDialogPopupRemarkInfo {
    @Nullable
    private final ElderModeDialogJumpInfo jumpInfo;
    @Nullable
    private final String jumpTitle;
    @Nullable
    private final String title;

    public ElderModeDialogPopupRemarkInfo(@Nullable String str, @Nullable String str2, @Nullable ElderModeDialogJumpInfo elderModeDialogJumpInfo) {
        this.title = str;
        this.jumpTitle = str2;
        this.jumpInfo = elderModeDialogJumpInfo;
    }

    public static /* synthetic */ ElderModeDialogPopupRemarkInfo copy$default(ElderModeDialogPopupRemarkInfo elderModeDialogPopupRemarkInfo, String str, String str2, ElderModeDialogJumpInfo elderModeDialogJumpInfo, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = elderModeDialogPopupRemarkInfo.title;
        }
        if ((i2 & 2) != 0) {
            str2 = elderModeDialogPopupRemarkInfo.jumpTitle;
        }
        if ((i2 & 4) != 0) {
            elderModeDialogJumpInfo = elderModeDialogPopupRemarkInfo.jumpInfo;
        }
        return elderModeDialogPopupRemarkInfo.copy(str, str2, elderModeDialogJumpInfo);
    }

    @Nullable
    /* renamed from: component1  reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    @Nullable
    /* renamed from: component2  reason: from getter */
    public final String getJumpTitle() {
        return this.jumpTitle;
    }

    @Nullable
    /* renamed from: component3  reason: from getter */
    public final ElderModeDialogJumpInfo getJumpInfo() {
        return this.jumpInfo;
    }

    @NotNull
    public final ElderModeDialogPopupRemarkInfo copy(@Nullable String title, @Nullable String jumpTitle, @Nullable ElderModeDialogJumpInfo jumpInfo) {
        return new ElderModeDialogPopupRemarkInfo(title, jumpTitle, jumpInfo);
    }

    public boolean equals(@Nullable Object other) {
        if (this != other) {
            if (other instanceof ElderModeDialogPopupRemarkInfo) {
                ElderModeDialogPopupRemarkInfo elderModeDialogPopupRemarkInfo = (ElderModeDialogPopupRemarkInfo) other;
                return Intrinsics.areEqual(this.title, elderModeDialogPopupRemarkInfo.title) && Intrinsics.areEqual(this.jumpTitle, elderModeDialogPopupRemarkInfo.jumpTitle) && Intrinsics.areEqual(this.jumpInfo, elderModeDialogPopupRemarkInfo.jumpInfo);
            }
            return false;
        }
        return true;
    }

    @Nullable
    public final ElderModeDialogJumpInfo getJumpInfo() {
        return this.jumpInfo;
    }

    @Nullable
    public final String getJumpTitle() {
        return this.jumpTitle;
    }

    @Nullable
    public final String getTitle() {
        return this.title;
    }

    public int hashCode() {
        String str = this.title;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.jumpTitle;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        ElderModeDialogJumpInfo elderModeDialogJumpInfo = this.jumpInfo;
        return hashCode2 + (elderModeDialogJumpInfo != null ? elderModeDialogJumpInfo.hashCode() : 0);
    }

    @NotNull
    public String toString() {
        return "ElderModeDialogPopupRemarkInfo(title=" + this.title + ", jumpTitle=" + this.jumpTitle + ", jumpInfo=" + this.jumpInfo + ")";
    }
}
