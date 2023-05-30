package com.jingdong.sdk.eldermode.entity;

import com.jd.viewkit.templates.view.helper.JDViewKitEventHelper;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0002\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0005\u0010\u0004J(\u0010\b\u001a\u00020\u00002\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0002H\u00c6\u0001\u00a2\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00020\u0002H\u00d6\u0001\u00a2\u0006\u0004\b\n\u0010\u0004J\u0010\u0010\f\u001a\u00020\u000bH\u00d6\u0001\u00a2\u0006\u0004\b\f\u0010\rJ\u001a\u0010\u0010\u001a\u00020\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003\u00a2\u0006\u0004\b\u0010\u0010\u0011R\u001b\u0010\u0006\u001a\u0004\u0018\u00010\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0006\u0010\u0012\u001a\u0004\b\u0013\u0010\u0004R\u001b\u0010\u0007\u001a\u0004\u0018\u00010\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0007\u0010\u0012\u001a\u0004\b\u0014\u0010\u0004\u00a8\u0006\u0017"}, d2 = {"Lcom/jingdong/sdk/eldermode/entity/ElderModeDialogSubTitleInfo;", "", "", "component1", "()Ljava/lang/String;", "component2", "smallIcon", "content", JDViewKitEventHelper.ActionType_Copy, "(Ljava/lang/String;Ljava/lang/String;)Lcom/jingdong/sdk/eldermode/entity/ElderModeDialogSubTitleInfo;", "toString", "", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getSmallIcon", "getContent", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "eldermodelib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes7.dex */
public final /* data */ class ElderModeDialogSubTitleInfo {
    @Nullable
    private final String content;
    @Nullable
    private final String smallIcon;

    public ElderModeDialogSubTitleInfo(@Nullable String str, @Nullable String str2) {
        this.smallIcon = str;
        this.content = str2;
    }

    public static /* synthetic */ ElderModeDialogSubTitleInfo copy$default(ElderModeDialogSubTitleInfo elderModeDialogSubTitleInfo, String str, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = elderModeDialogSubTitleInfo.smallIcon;
        }
        if ((i2 & 2) != 0) {
            str2 = elderModeDialogSubTitleInfo.content;
        }
        return elderModeDialogSubTitleInfo.copy(str, str2);
    }

    @Nullable
    /* renamed from: component1  reason: from getter */
    public final String getSmallIcon() {
        return this.smallIcon;
    }

    @Nullable
    /* renamed from: component2  reason: from getter */
    public final String getContent() {
        return this.content;
    }

    @NotNull
    public final ElderModeDialogSubTitleInfo copy(@Nullable String smallIcon, @Nullable String content) {
        return new ElderModeDialogSubTitleInfo(smallIcon, content);
    }

    public boolean equals(@Nullable Object other) {
        if (this != other) {
            if (other instanceof ElderModeDialogSubTitleInfo) {
                ElderModeDialogSubTitleInfo elderModeDialogSubTitleInfo = (ElderModeDialogSubTitleInfo) other;
                return Intrinsics.areEqual(this.smallIcon, elderModeDialogSubTitleInfo.smallIcon) && Intrinsics.areEqual(this.content, elderModeDialogSubTitleInfo.content);
            }
            return false;
        }
        return true;
    }

    @Nullable
    public final String getContent() {
        return this.content;
    }

    @Nullable
    public final String getSmallIcon() {
        return this.smallIcon;
    }

    public int hashCode() {
        String str = this.smallIcon;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.content;
        return hashCode + (str2 != null ? str2.hashCode() : 0);
    }

    @NotNull
    public String toString() {
        return "ElderModeDialogSubTitleInfo(smallIcon=" + this.smallIcon + ", content=" + this.content + ")";
    }
}
