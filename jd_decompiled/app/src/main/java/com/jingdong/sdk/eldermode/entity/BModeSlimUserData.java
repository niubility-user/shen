package com.jingdong.sdk.eldermode.entity;

import com.eclipsesource.v8.Platform;
import com.jd.viewkit.templates.view.helper.JDViewKitEventHelper;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0011\b\u0086\b\u0018\u00002\u00020\u0001Bk\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u0016\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0017\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0018\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0019\u001a\u0004\u0018\u00010\u0002\u00a2\u0006\u0004\b0\u00101J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0005\u0010\u0004J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0006\u0010\u0004J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0007\u0010\u0004J\u0012\u0010\b\u001a\u0004\u0018\u00010\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\b\u0010\u0004J\u0012\u0010\n\u001a\u0004\u0018\u00010\tH\u00c6\u0003\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0012\u0010\f\u001a\u0004\u0018\u00010\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\f\u0010\u0004J\u0012\u0010\r\u001a\u0004\u0018\u00010\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\r\u0010\u0004J\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u000e\u0010\u0004J\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u000f\u0010\u0004J\u0088\u0001\u0010\u001a\u001a\u00020\u00002\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0002H\u00c6\u0001\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u0010\u0010\u001c\u001a\u00020\u0002H\u00d6\u0001\u00a2\u0006\u0004\b\u001c\u0010\u0004J\u0010\u0010\u001e\u001a\u00020\u001dH\u00d6\u0001\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\u001a\u0010\"\u001a\u00020!2\b\u0010 \u001a\u0004\u0018\u00010\u0001H\u00d6\u0003\u00a2\u0006\u0004\b\"\u0010#R\u001b\u0010\u0015\u001a\u0004\u0018\u00010\t8\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0015\u0010$\u001a\u0004\b%\u0010\u000bR\u001b\u0010\u0018\u001a\u0004\u0018\u00010\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0018\u0010&\u001a\u0004\b'\u0010\u0004R\u001b\u0010\u0014\u001a\u0004\u0018\u00010\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0014\u0010&\u001a\u0004\b(\u0010\u0004R\u001b\u0010\u0011\u001a\u0004\u0018\u00010\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0011\u0010&\u001a\u0004\b)\u0010\u0004R\u001b\u0010\u0010\u001a\u0004\u0018\u00010\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0010\u0010&\u001a\u0004\b*\u0010\u0004R\u001b\u0010\u0019\u001a\u0004\u0018\u00010\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0019\u0010&\u001a\u0004\b+\u0010\u0004R\u001b\u0010\u0017\u001a\u0004\u0018\u00010\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0017\u0010&\u001a\u0004\b,\u0010\u0004R\u001b\u0010\u0016\u001a\u0004\u0018\u00010\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0016\u0010&\u001a\u0004\b-\u0010\u0004R\u001b\u0010\u0012\u001a\u0004\u0018\u00010\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0012\u0010&\u001a\u0004\b.\u0010\u0004R\u001b\u0010\u0013\u001a\u0004\u0018\u00010\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0013\u0010&\u001a\u0004\b/\u0010\u0004\u00a8\u00062"}, d2 = {"Lcom/jingdong/sdk/eldermode/entity/BModeSlimUserData;", "", "", "component1", "()Ljava/lang/String;", "component2", "component3", "component4", "component5", "Lcom/jingdong/sdk/eldermode/entity/BModeDialogInfo;", "component6", "()Lcom/jingdong/sdk/eldermode/entity/BModeDialogInfo;", "component7", "component8", "component9", "component10", "adviseVersion", "adviseFinalVersion", "buriedExpLabel", "adviseFinalMode", Platform.WINDOWS, "slimStandardInfo", "forbiddenVersionSwitch", "plus", "populationType", "slimReportResult", JDViewKitEventHelper.ActionType_Copy, "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/jingdong/sdk/eldermode/entity/BModeDialogInfo;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/jingdong/sdk/eldermode/entity/BModeSlimUserData;", "toString", "", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Lcom/jingdong/sdk/eldermode/entity/BModeDialogInfo;", "getSlimStandardInfo", "Ljava/lang/String;", "getPopulationType", "getWindows", "getAdviseFinalVersion", "getAdviseVersion", "getSlimReportResult", "getPlus", "getForbiddenVersionSwitch", "getBuriedExpLabel", "getAdviseFinalMode", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/jingdong/sdk/eldermode/entity/BModeDialogInfo;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "eldermodelib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes7.dex */
public final /* data */ class BModeSlimUserData {
    @Nullable
    private final String adviseFinalMode;
    @Nullable
    private final String adviseFinalVersion;
    @Nullable
    private final String adviseVersion;
    @Nullable
    private final String buriedExpLabel;
    @Nullable
    private final String forbiddenVersionSwitch;
    @Nullable
    private final String plus;
    @Nullable
    private final String populationType;
    @Nullable
    private final String slimReportResult;
    @Nullable
    private final BModeDialogInfo slimStandardInfo;
    @Nullable
    private final String windows;

    public BModeSlimUserData(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable BModeDialogInfo bModeDialogInfo, @Nullable String str6, @Nullable String str7, @Nullable String str8, @Nullable String str9) {
        this.adviseVersion = str;
        this.adviseFinalVersion = str2;
        this.buriedExpLabel = str3;
        this.adviseFinalMode = str4;
        this.windows = str5;
        this.slimStandardInfo = bModeDialogInfo;
        this.forbiddenVersionSwitch = str6;
        this.plus = str7;
        this.populationType = str8;
        this.slimReportResult = str9;
    }

    @Nullable
    /* renamed from: component1  reason: from getter */
    public final String getAdviseVersion() {
        return this.adviseVersion;
    }

    @Nullable
    /* renamed from: component10  reason: from getter */
    public final String getSlimReportResult() {
        return this.slimReportResult;
    }

    @Nullable
    /* renamed from: component2  reason: from getter */
    public final String getAdviseFinalVersion() {
        return this.adviseFinalVersion;
    }

    @Nullable
    /* renamed from: component3  reason: from getter */
    public final String getBuriedExpLabel() {
        return this.buriedExpLabel;
    }

    @Nullable
    /* renamed from: component4  reason: from getter */
    public final String getAdviseFinalMode() {
        return this.adviseFinalMode;
    }

    @Nullable
    /* renamed from: component5  reason: from getter */
    public final String getWindows() {
        return this.windows;
    }

    @Nullable
    /* renamed from: component6  reason: from getter */
    public final BModeDialogInfo getSlimStandardInfo() {
        return this.slimStandardInfo;
    }

    @Nullable
    /* renamed from: component7  reason: from getter */
    public final String getForbiddenVersionSwitch() {
        return this.forbiddenVersionSwitch;
    }

    @Nullable
    /* renamed from: component8  reason: from getter */
    public final String getPlus() {
        return this.plus;
    }

    @Nullable
    /* renamed from: component9  reason: from getter */
    public final String getPopulationType() {
        return this.populationType;
    }

    @NotNull
    public final BModeSlimUserData copy(@Nullable String adviseVersion, @Nullable String adviseFinalVersion, @Nullable String buriedExpLabel, @Nullable String adviseFinalMode, @Nullable String windows, @Nullable BModeDialogInfo slimStandardInfo, @Nullable String forbiddenVersionSwitch, @Nullable String plus, @Nullable String populationType, @Nullable String slimReportResult) {
        return new BModeSlimUserData(adviseVersion, adviseFinalVersion, buriedExpLabel, adviseFinalMode, windows, slimStandardInfo, forbiddenVersionSwitch, plus, populationType, slimReportResult);
    }

    public boolean equals(@Nullable Object other) {
        if (this != other) {
            if (other instanceof BModeSlimUserData) {
                BModeSlimUserData bModeSlimUserData = (BModeSlimUserData) other;
                return Intrinsics.areEqual(this.adviseVersion, bModeSlimUserData.adviseVersion) && Intrinsics.areEqual(this.adviseFinalVersion, bModeSlimUserData.adviseFinalVersion) && Intrinsics.areEqual(this.buriedExpLabel, bModeSlimUserData.buriedExpLabel) && Intrinsics.areEqual(this.adviseFinalMode, bModeSlimUserData.adviseFinalMode) && Intrinsics.areEqual(this.windows, bModeSlimUserData.windows) && Intrinsics.areEqual(this.slimStandardInfo, bModeSlimUserData.slimStandardInfo) && Intrinsics.areEqual(this.forbiddenVersionSwitch, bModeSlimUserData.forbiddenVersionSwitch) && Intrinsics.areEqual(this.plus, bModeSlimUserData.plus) && Intrinsics.areEqual(this.populationType, bModeSlimUserData.populationType) && Intrinsics.areEqual(this.slimReportResult, bModeSlimUserData.slimReportResult);
            }
            return false;
        }
        return true;
    }

    @Nullable
    public final String getAdviseFinalMode() {
        return this.adviseFinalMode;
    }

    @Nullable
    public final String getAdviseFinalVersion() {
        return this.adviseFinalVersion;
    }

    @Nullable
    public final String getAdviseVersion() {
        return this.adviseVersion;
    }

    @Nullable
    public final String getBuriedExpLabel() {
        return this.buriedExpLabel;
    }

    @Nullable
    public final String getForbiddenVersionSwitch() {
        return this.forbiddenVersionSwitch;
    }

    @Nullable
    public final String getPlus() {
        return this.plus;
    }

    @Nullable
    public final String getPopulationType() {
        return this.populationType;
    }

    @Nullable
    public final String getSlimReportResult() {
        return this.slimReportResult;
    }

    @Nullable
    public final BModeDialogInfo getSlimStandardInfo() {
        return this.slimStandardInfo;
    }

    @Nullable
    public final String getWindows() {
        return this.windows;
    }

    public int hashCode() {
        String str = this.adviseVersion;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.adviseFinalVersion;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.buriedExpLabel;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.adviseFinalMode;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.windows;
        int hashCode5 = (hashCode4 + (str5 != null ? str5.hashCode() : 0)) * 31;
        BModeDialogInfo bModeDialogInfo = this.slimStandardInfo;
        int hashCode6 = (hashCode5 + (bModeDialogInfo != null ? bModeDialogInfo.hashCode() : 0)) * 31;
        String str6 = this.forbiddenVersionSwitch;
        int hashCode7 = (hashCode6 + (str6 != null ? str6.hashCode() : 0)) * 31;
        String str7 = this.plus;
        int hashCode8 = (hashCode7 + (str7 != null ? str7.hashCode() : 0)) * 31;
        String str8 = this.populationType;
        int hashCode9 = (hashCode8 + (str8 != null ? str8.hashCode() : 0)) * 31;
        String str9 = this.slimReportResult;
        return hashCode9 + (str9 != null ? str9.hashCode() : 0);
    }

    @NotNull
    public String toString() {
        return "BModeSlimUserData(adviseVersion=" + this.adviseVersion + ", adviseFinalVersion=" + this.adviseFinalVersion + ", buriedExpLabel=" + this.buriedExpLabel + ", adviseFinalMode=" + this.adviseFinalMode + ", windows=" + this.windows + ", slimStandardInfo=" + this.slimStandardInfo + ", forbiddenVersionSwitch=" + this.forbiddenVersionSwitch + ", plus=" + this.plus + ", populationType=" + this.populationType + ", slimReportResult=" + this.slimReportResult + ")";
    }
}
