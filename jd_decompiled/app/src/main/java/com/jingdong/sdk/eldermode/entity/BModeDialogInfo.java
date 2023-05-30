package com.jingdong.sdk.eldermode.entity;

import com.jd.viewkit.templates.view.helper.JDViewKitEventHelper;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\r\b\u0086\b\u0018\u00002\u00020\u0001BI\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0002\u0012\u000e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0002\u00a2\u0006\u0004\b%\u0010&J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0005\u0010\u0004J\u0018\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006H\u00c6\u0003\u00a2\u0006\u0004\b\b\u0010\tJ\u0012\u0010\n\u001a\u0004\u0018\u00010\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\n\u0010\u0004J\u0012\u0010\u000b\u001a\u0004\u0018\u00010\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u000b\u0010\u0004J\u0012\u0010\f\u001a\u0004\u0018\u00010\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\f\u0010\u0004J^\u0010\u0013\u001a\u00020\u00002\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00022\u0010\b\u0002\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00062\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0002H\u00c6\u0001\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0010\u0010\u0015\u001a\u00020\u0002H\u00d6\u0001\u00a2\u0006\u0004\b\u0015\u0010\u0004J\u0010\u0010\u0017\u001a\u00020\u0016H\u00d6\u0001\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u001a\u0010\u001b\u001a\u00020\u001a2\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003\u00a2\u0006\u0004\b\u001b\u0010\u001cR\u001b\u0010\u0012\u001a\u0004\u0018\u00010\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0012\u0010\u001d\u001a\u0004\b\u001e\u0010\u0004R\u001b\u0010\u0011\u001a\u0004\u0018\u00010\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0011\u0010\u001d\u001a\u0004\b\u001f\u0010\u0004R!\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00068\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u000f\u0010 \u001a\u0004\b!\u0010\tR\u001b\u0010\u0010\u001a\u0004\u0018\u00010\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0010\u0010\u001d\u001a\u0004\b\"\u0010\u0004R\u001b\u0010\u000e\u001a\u0004\u0018\u00010\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u000e\u0010\u001d\u001a\u0004\b#\u0010\u0004R\u001b\u0010\r\u001a\u0004\u0018\u00010\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\r\u0010\u001d\u001a\u0004\b$\u0010\u0004\u00a8\u0006'"}, d2 = {"Lcom/jingdong/sdk/eldermode/entity/BModeDialogInfo;", "", "", "component1", "()Ljava/lang/String;", "component2", "", "Lcom/jingdong/sdk/eldermode/entity/ElderModeDialogSubTitleInfo;", "component3", "()Ljava/util/List;", "component4", "component5", "component6", "popupIcon", "popupTitle", "popupSubTitle", "popupAdviceContent", "sureButtonContent", "cancelButtonContent", JDViewKitEventHelper.ActionType_Copy, "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/jingdong/sdk/eldermode/entity/BModeDialogInfo;", "toString", "", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getCancelButtonContent", "getSureButtonContent", "Ljava/util/List;", "getPopupSubTitle", "getPopupAdviceContent", "getPopupTitle", "getPopupIcon", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "eldermodelib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes7.dex */
public final /* data */ class BModeDialogInfo {
    @Nullable
    private final String cancelButtonContent;
    @Nullable
    private final String popupAdviceContent;
    @Nullable
    private final String popupIcon;
    @Nullable
    private final List<ElderModeDialogSubTitleInfo> popupSubTitle;
    @Nullable
    private final String popupTitle;
    @Nullable
    private final String sureButtonContent;

    public BModeDialogInfo(@Nullable String str, @Nullable String str2, @Nullable List<ElderModeDialogSubTitleInfo> list, @Nullable String str3, @Nullable String str4, @Nullable String str5) {
        this.popupIcon = str;
        this.popupTitle = str2;
        this.popupSubTitle = list;
        this.popupAdviceContent = str3;
        this.sureButtonContent = str4;
        this.cancelButtonContent = str5;
    }

    public static /* synthetic */ BModeDialogInfo copy$default(BModeDialogInfo bModeDialogInfo, String str, String str2, List list, String str3, String str4, String str5, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = bModeDialogInfo.popupIcon;
        }
        if ((i2 & 2) != 0) {
            str2 = bModeDialogInfo.popupTitle;
        }
        String str6 = str2;
        List<ElderModeDialogSubTitleInfo> list2 = list;
        if ((i2 & 4) != 0) {
            list2 = bModeDialogInfo.popupSubTitle;
        }
        List list3 = list2;
        if ((i2 & 8) != 0) {
            str3 = bModeDialogInfo.popupAdviceContent;
        }
        String str7 = str3;
        if ((i2 & 16) != 0) {
            str4 = bModeDialogInfo.sureButtonContent;
        }
        String str8 = str4;
        if ((i2 & 32) != 0) {
            str5 = bModeDialogInfo.cancelButtonContent;
        }
        return bModeDialogInfo.copy(str, str6, list3, str7, str8, str5);
    }

    @Nullable
    /* renamed from: component1  reason: from getter */
    public final String getPopupIcon() {
        return this.popupIcon;
    }

    @Nullable
    /* renamed from: component2  reason: from getter */
    public final String getPopupTitle() {
        return this.popupTitle;
    }

    @Nullable
    public final List<ElderModeDialogSubTitleInfo> component3() {
        return this.popupSubTitle;
    }

    @Nullable
    /* renamed from: component4  reason: from getter */
    public final String getPopupAdviceContent() {
        return this.popupAdviceContent;
    }

    @Nullable
    /* renamed from: component5  reason: from getter */
    public final String getSureButtonContent() {
        return this.sureButtonContent;
    }

    @Nullable
    /* renamed from: component6  reason: from getter */
    public final String getCancelButtonContent() {
        return this.cancelButtonContent;
    }

    @NotNull
    public final BModeDialogInfo copy(@Nullable String popupIcon, @Nullable String popupTitle, @Nullable List<ElderModeDialogSubTitleInfo> popupSubTitle, @Nullable String popupAdviceContent, @Nullable String sureButtonContent, @Nullable String cancelButtonContent) {
        return new BModeDialogInfo(popupIcon, popupTitle, popupSubTitle, popupAdviceContent, sureButtonContent, cancelButtonContent);
    }

    public boolean equals(@Nullable Object other) {
        if (this != other) {
            if (other instanceof BModeDialogInfo) {
                BModeDialogInfo bModeDialogInfo = (BModeDialogInfo) other;
                return Intrinsics.areEqual(this.popupIcon, bModeDialogInfo.popupIcon) && Intrinsics.areEqual(this.popupTitle, bModeDialogInfo.popupTitle) && Intrinsics.areEqual(this.popupSubTitle, bModeDialogInfo.popupSubTitle) && Intrinsics.areEqual(this.popupAdviceContent, bModeDialogInfo.popupAdviceContent) && Intrinsics.areEqual(this.sureButtonContent, bModeDialogInfo.sureButtonContent) && Intrinsics.areEqual(this.cancelButtonContent, bModeDialogInfo.cancelButtonContent);
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
    public final String getPopupAdviceContent() {
        return this.popupAdviceContent;
    }

    @Nullable
    public final String getPopupIcon() {
        return this.popupIcon;
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
        String str = this.popupIcon;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.popupTitle;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        List<ElderModeDialogSubTitleInfo> list = this.popupSubTitle;
        int hashCode3 = (hashCode2 + (list != null ? list.hashCode() : 0)) * 31;
        String str3 = this.popupAdviceContent;
        int hashCode4 = (hashCode3 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.sureButtonContent;
        int hashCode5 = (hashCode4 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.cancelButtonContent;
        return hashCode5 + (str5 != null ? str5.hashCode() : 0);
    }

    @NotNull
    public String toString() {
        return "BModeDialogInfo(popupIcon=" + this.popupIcon + ", popupTitle=" + this.popupTitle + ", popupSubTitle=" + this.popupSubTitle + ", popupAdviceContent=" + this.popupAdviceContent + ", sureButtonContent=" + this.sureButtonContent + ", cancelButtonContent=" + this.cancelButtonContent + ")";
    }
}
