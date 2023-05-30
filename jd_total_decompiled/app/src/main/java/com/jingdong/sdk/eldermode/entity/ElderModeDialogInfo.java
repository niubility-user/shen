package com.jingdong.sdk.eldermode.entity;

import com.jd.viewkit.templates.view.helper.JDViewKitEventHelper;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u000e\b\u0086\b\u0018\u00002\u00020\u0001BI\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0002\u0012\u000e\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\f\u00a2\u0006\u0004\b(\u0010)J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0005\u0010\u0004J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0006\u0010\u0004J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0007\u0010\u0004J\u0018\u0010\n\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bH\u00c6\u0003\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0012\u0010\r\u001a\u0004\u0018\u00010\fH\u00c6\u0003\u00a2\u0006\u0004\b\r\u0010\u000eJ^\u0010\u0015\u001a\u00020\u00002\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00022\u0010\b\u0002\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b2\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\fH\u00c6\u0001\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u0010\u0010\u0017\u001a\u00020\u0002H\u00d6\u0001\u00a2\u0006\u0004\b\u0017\u0010\u0004J\u0010\u0010\u0019\u001a\u00020\u0018H\u00d6\u0001\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u001a\u0010\u001d\u001a\u00020\u001c2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003\u00a2\u0006\u0004\b\u001d\u0010\u001eR\u001b\u0010\u0014\u001a\u0004\u0018\u00010\f8\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0014\u0010\u001f\u001a\u0004\b \u0010\u000eR\u001b\u0010\u0010\u001a\u0004\u0018\u00010\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0010\u0010!\u001a\u0004\b\"\u0010\u0004R\u001b\u0010\u0011\u001a\u0004\u0018\u00010\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0011\u0010!\u001a\u0004\b#\u0010\u0004R!\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b8\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0013\u0010$\u001a\u0004\b%\u0010\u000bR\u001b\u0010\u000f\u001a\u0004\u0018\u00010\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u000f\u0010!\u001a\u0004\b&\u0010\u0004R\u001b\u0010\u0012\u001a\u0004\u0018\u00010\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0012\u0010!\u001a\u0004\b'\u0010\u0004\u00a8\u0006*"}, d2 = {"Lcom/jingdong/sdk/eldermode/entity/ElderModeDialogInfo;", "", "", "component1", "()Ljava/lang/String;", "component2", "component3", "component4", "", "Lcom/jingdong/sdk/eldermode/entity/ElderModeDialogSubTitleInfo;", "component5", "()Ljava/util/List;", "Lcom/jingdong/sdk/eldermode/entity/ElderModeDialogPopupRemarkInfo;", "component6", "()Lcom/jingdong/sdk/eldermode/entity/ElderModeDialogPopupRemarkInfo;", "popupTitle", "popupIcon", "sureButtonContent", "cancelButtonContent", "popupSubTitle", "popupRemark", JDViewKitEventHelper.ActionType_Copy, "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Lcom/jingdong/sdk/eldermode/entity/ElderModeDialogPopupRemarkInfo;)Lcom/jingdong/sdk/eldermode/entity/ElderModeDialogInfo;", "toString", "", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Lcom/jingdong/sdk/eldermode/entity/ElderModeDialogPopupRemarkInfo;", "getPopupRemark", "Ljava/lang/String;", "getPopupIcon", "getSureButtonContent", "Ljava/util/List;", "getPopupSubTitle", "getPopupTitle", "getCancelButtonContent", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Lcom/jingdong/sdk/eldermode/entity/ElderModeDialogPopupRemarkInfo;)V", "eldermodelib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes7.dex */
public final /* data */ class ElderModeDialogInfo {
    @Nullable
    private final String cancelButtonContent;
    @Nullable
    private final String popupIcon;
    @Nullable
    private final ElderModeDialogPopupRemarkInfo popupRemark;
    @Nullable
    private final List<ElderModeDialogSubTitleInfo> popupSubTitle;
    @Nullable
    private final String popupTitle;
    @Nullable
    private final String sureButtonContent;

    public ElderModeDialogInfo(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable List<ElderModeDialogSubTitleInfo> list, @Nullable ElderModeDialogPopupRemarkInfo elderModeDialogPopupRemarkInfo) {
        this.popupTitle = str;
        this.popupIcon = str2;
        this.sureButtonContent = str3;
        this.cancelButtonContent = str4;
        this.popupSubTitle = list;
        this.popupRemark = elderModeDialogPopupRemarkInfo;
    }

    public static /* synthetic */ ElderModeDialogInfo copy$default(ElderModeDialogInfo elderModeDialogInfo, String str, String str2, String str3, String str4, List list, ElderModeDialogPopupRemarkInfo elderModeDialogPopupRemarkInfo, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = elderModeDialogInfo.popupTitle;
        }
        if ((i2 & 2) != 0) {
            str2 = elderModeDialogInfo.popupIcon;
        }
        String str5 = str2;
        if ((i2 & 4) != 0) {
            str3 = elderModeDialogInfo.sureButtonContent;
        }
        String str6 = str3;
        if ((i2 & 8) != 0) {
            str4 = elderModeDialogInfo.cancelButtonContent;
        }
        String str7 = str4;
        List<ElderModeDialogSubTitleInfo> list2 = list;
        if ((i2 & 16) != 0) {
            list2 = elderModeDialogInfo.popupSubTitle;
        }
        List list3 = list2;
        if ((i2 & 32) != 0) {
            elderModeDialogPopupRemarkInfo = elderModeDialogInfo.popupRemark;
        }
        return elderModeDialogInfo.copy(str, str5, str6, str7, list3, elderModeDialogPopupRemarkInfo);
    }

    @Nullable
    /* renamed from: component1  reason: from getter */
    public final String getPopupTitle() {
        return this.popupTitle;
    }

    @Nullable
    /* renamed from: component2  reason: from getter */
    public final String getPopupIcon() {
        return this.popupIcon;
    }

    @Nullable
    /* renamed from: component3  reason: from getter */
    public final String getSureButtonContent() {
        return this.sureButtonContent;
    }

    @Nullable
    /* renamed from: component4  reason: from getter */
    public final String getCancelButtonContent() {
        return this.cancelButtonContent;
    }

    @Nullable
    public final List<ElderModeDialogSubTitleInfo> component5() {
        return this.popupSubTitle;
    }

    @Nullable
    /* renamed from: component6  reason: from getter */
    public final ElderModeDialogPopupRemarkInfo getPopupRemark() {
        return this.popupRemark;
    }

    @NotNull
    public final ElderModeDialogInfo copy(@Nullable String popupTitle, @Nullable String popupIcon, @Nullable String sureButtonContent, @Nullable String cancelButtonContent, @Nullable List<ElderModeDialogSubTitleInfo> popupSubTitle, @Nullable ElderModeDialogPopupRemarkInfo popupRemark) {
        return new ElderModeDialogInfo(popupTitle, popupIcon, sureButtonContent, cancelButtonContent, popupSubTitle, popupRemark);
    }

    public boolean equals(@Nullable Object other) {
        if (this != other) {
            if (other instanceof ElderModeDialogInfo) {
                ElderModeDialogInfo elderModeDialogInfo = (ElderModeDialogInfo) other;
                return Intrinsics.areEqual(this.popupTitle, elderModeDialogInfo.popupTitle) && Intrinsics.areEqual(this.popupIcon, elderModeDialogInfo.popupIcon) && Intrinsics.areEqual(this.sureButtonContent, elderModeDialogInfo.sureButtonContent) && Intrinsics.areEqual(this.cancelButtonContent, elderModeDialogInfo.cancelButtonContent) && Intrinsics.areEqual(this.popupSubTitle, elderModeDialogInfo.popupSubTitle) && Intrinsics.areEqual(this.popupRemark, elderModeDialogInfo.popupRemark);
            }
            return false;
        }
        return true;
    }

    @Nullable
    public final String getCancelButtonContent() {
        return this.cancelButtonContent;
    }

    @Nullable
    public final String getPopupIcon() {
        return this.popupIcon;
    }

    @Nullable
    public final ElderModeDialogPopupRemarkInfo getPopupRemark() {
        return this.popupRemark;
    }

    @Nullable
    public final List<ElderModeDialogSubTitleInfo> getPopupSubTitle() {
        return this.popupSubTitle;
    }

    @Nullable
    public final String getPopupTitle() {
        return this.popupTitle;
    }

    @Nullable
    public final String getSureButtonContent() {
        return this.sureButtonContent;
    }

    public int hashCode() {
        String str = this.popupTitle;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.popupIcon;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.sureButtonContent;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.cancelButtonContent;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        List<ElderModeDialogSubTitleInfo> list = this.popupSubTitle;
        int hashCode5 = (hashCode4 + (list != null ? list.hashCode() : 0)) * 31;
        ElderModeDialogPopupRemarkInfo elderModeDialogPopupRemarkInfo = this.popupRemark;
        return hashCode5 + (elderModeDialogPopupRemarkInfo != null ? elderModeDialogPopupRemarkInfo.hashCode() : 0);
    }

    @NotNull
    public String toString() {
        return "ElderModeDialogInfo(popupTitle=" + this.popupTitle + ", popupIcon=" + this.popupIcon + ", sureButtonContent=" + this.sureButtonContent + ", cancelButtonContent=" + this.cancelButtonContent + ", popupSubTitle=" + this.popupSubTitle + ", popupRemark=" + this.popupRemark + ")";
    }
}
