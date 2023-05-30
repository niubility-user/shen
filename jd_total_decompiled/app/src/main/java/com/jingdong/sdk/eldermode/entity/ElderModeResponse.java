package com.jingdong.sdk.eldermode.entity;

import com.jd.viewkit.templates.view.helper.JDViewKitEventHelper;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u001d\b\u0086\b\u0018\u0000 D2\u00020\u0001:\u0001DBk\u0012\b\u0010\u0018\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0019\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u001a\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u001b\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u001c\u001a\u0004\u0018\u00010\n\u0012\b\u0010\u001d\u001a\u0004\u0018\u00010\n\u0012\b\u0010\u001e\u001a\u0004\u0018\u00010\u000e\u0012\b\u0010\u001f\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010 \u001a\u0004\u0018\u00010\u0012\u0012\b\u0010!\u001a\u0004\u0018\u00010\u0015\u00a2\u0006\u0004\bB\u0010CJ\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0012\u0010\b\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0004\b\b\u0010\u0007J\u0012\u0010\t\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0004\b\t\u0010\u0007J\u0012\u0010\u000b\u001a\u0004\u0018\u00010\nH\u00c6\u0003\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0012\u0010\r\u001a\u0004\u0018\u00010\nH\u00c6\u0003\u00a2\u0006\u0004\b\r\u0010\fJ\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u000eH\u00c6\u0003\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u0012\u0010\u0011\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0004\b\u0011\u0010\u0007J\u0012\u0010\u0013\u001a\u0004\u0018\u00010\u0012H\u00c6\u0003\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u0012\u0010\u0016\u001a\u0004\u0018\u00010\u0015H\u00c6\u0003\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u0088\u0001\u0010\"\u001a\u00020\u00002\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u0015H\u00c6\u0001\u00a2\u0006\u0004\b\"\u0010#J\u0010\u0010$\u001a\u00020\u0002H\u00d6\u0001\u00a2\u0006\u0004\b$\u0010\u0004J\u0010\u0010%\u001a\u00020\u0005H\u00d6\u0001\u00a2\u0006\u0004\b%\u0010&J\u001a\u0010)\u001a\u00020(2\b\u0010'\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003\u00a2\u0006\u0004\b)\u0010*R\u001b\u0010\u001d\u001a\u0004\u0018\u00010\n8\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u001d\u0010+\u001a\u0004\b,\u0010\fR\u0015\u0010.\u001a\u0004\u0018\u00010\u00058F@\u0006\u00a2\u0006\u0006\u001a\u0004\b-\u0010\u0007R\u001b\u0010\u0019\u001a\u0004\u0018\u00010\u00058\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0019\u0010/\u001a\u0004\b0\u0010\u0007R\u0015\u00102\u001a\u0004\u0018\u00010\u00058F@\u0006\u00a2\u0006\u0006\u001a\u0004\b1\u0010\u0007R\u001b\u0010!\u001a\u0004\u0018\u00010\u00158\u0006@\u0006\u00a2\u0006\f\n\u0004\b!\u00103\u001a\u0004\b4\u0010\u0017R\u001b\u0010 \u001a\u0004\u0018\u00010\u00128\u0006@\u0006\u00a2\u0006\f\n\u0004\b \u00105\u001a\u0004\b6\u0010\u0014R$\u0010\u001b\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u001b\u0010/\u001a\u0004\b7\u0010\u0007\"\u0004\b8\u00109R$\u0010\u001a\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u001a\u0010/\u001a\u0004\b:\u0010\u0007\"\u0004\b;\u00109R\u001b\u0010\u0018\u001a\u0004\u0018\u00010\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0018\u0010<\u001a\u0004\b=\u0010\u0004R\u001b\u0010\u001e\u001a\u0004\u0018\u00010\u000e8\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u001e\u0010>\u001a\u0004\b?\u0010\u0010R\u001b\u0010\u001f\u001a\u0004\u0018\u00010\u00058\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u001f\u0010/\u001a\u0004\b@\u0010\u0007R\u001b\u0010\u001c\u001a\u0004\u0018\u00010\n8\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u001c\u0010+\u001a\u0004\bA\u0010\f\u00a8\u0006E"}, d2 = {"Lcom/jingdong/sdk/eldermode/entity/ElderModeResponse;", "", "", "component1", "()Ljava/lang/String;", "", "component2", "()Ljava/lang/Integer;", "component3", "component4", "Lcom/jingdong/sdk/eldermode/entity/ElderModeDialogInfo;", "component5", "()Lcom/jingdong/sdk/eldermode/entity/ElderModeDialogInfo;", "component6", "Lcom/jingdong/sdk/eldermode/entity/ElderModePopupRuleInfo;", "component7", "()Lcom/jingdong/sdk/eldermode/entity/ElderModePopupRuleInfo;", "component8", "Lcom/jingdong/sdk/eldermode/entity/ElderModeResponseOthers;", "component9", "()Lcom/jingdong/sdk/eldermode/entity/ElderModeResponseOthers;", "Lcom/jingdong/sdk/eldermode/entity/BModeSlimUserData;", "component10", "()Lcom/jingdong/sdk/eldermode/entity/BModeSlimUserData;", "code", "caringUser", "realElderUser", "caringPatternTurn", "caringInfo", "standardInfo", "popupRuleInfo", "operateResult", "others", "slimUserData", JDViewKitEventHelper.ActionType_Copy, "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Lcom/jingdong/sdk/eldermode/entity/ElderModeDialogInfo;Lcom/jingdong/sdk/eldermode/entity/ElderModeDialogInfo;Lcom/jingdong/sdk/eldermode/entity/ElderModePopupRuleInfo;Ljava/lang/Integer;Lcom/jingdong/sdk/eldermode/entity/ElderModeResponseOthers;Lcom/jingdong/sdk/eldermode/entity/BModeSlimUserData;)Lcom/jingdong/sdk/eldermode/entity/ElderModeResponse;", "toString", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Lcom/jingdong/sdk/eldermode/entity/ElderModeDialogInfo;", "getStandardInfo", "getPotentialElder", "potentialElder", "Ljava/lang/Integer;", "getCaringUser", "getElderMode", "elderMode", "Lcom/jingdong/sdk/eldermode/entity/BModeSlimUserData;", "getSlimUserData", "Lcom/jingdong/sdk/eldermode/entity/ElderModeResponseOthers;", "getOthers", "getCaringPatternTurn", "setCaringPatternTurn", "(Ljava/lang/Integer;)V", "getRealElderUser", "setRealElderUser", "Ljava/lang/String;", "getCode", "Lcom/jingdong/sdk/eldermode/entity/ElderModePopupRuleInfo;", "getPopupRuleInfo", "getOperateResult", "getCaringInfo", "<init>", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Lcom/jingdong/sdk/eldermode/entity/ElderModeDialogInfo;Lcom/jingdong/sdk/eldermode/entity/ElderModeDialogInfo;Lcom/jingdong/sdk/eldermode/entity/ElderModePopupRuleInfo;Ljava/lang/Integer;Lcom/jingdong/sdk/eldermode/entity/ElderModeResponseOthers;Lcom/jingdong/sdk/eldermode/entity/BModeSlimUserData;)V", "Companion", "eldermodelib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final /* data */ class ElderModeResponse {
    public static final int OPERATE_RESULT_FAIL = -1;
    public static final int OPERATE_RESULT_REFUSED = 3;
    public static final int OPERATE_RESULT_SUCCESS = 0;
    public static final int POTENTIAL_STATE_NO = 0;
    public static final int POTENTIAL_STATE_UNKNOWN = 2;
    public static final int POTENTIAL_STATE_YES = 1;
    public static final int REAL_ELDER_USER_NO = 0;
    public static final int REAL_ELDER_USER_UNKNOWN = 2;
    public static final int REAL_ELDER_USER_YES = 1;
    @Nullable
    private final ElderModeDialogInfo caringInfo;
    @Nullable
    private Integer caringPatternTurn;
    @Nullable
    private final Integer caringUser;
    @Nullable
    private final String code;
    @Nullable
    private final Integer operateResult;
    @Nullable
    private final ElderModeResponseOthers others;
    @Nullable
    private final ElderModePopupRuleInfo popupRuleInfo;
    @Nullable
    private Integer realElderUser;
    @Nullable
    private final BModeSlimUserData slimUserData;
    @Nullable
    private final ElderModeDialogInfo standardInfo;

    public ElderModeResponse(@Nullable String str, @Nullable Integer num, @Nullable Integer num2, @Nullable Integer num3, @Nullable ElderModeDialogInfo elderModeDialogInfo, @Nullable ElderModeDialogInfo elderModeDialogInfo2, @Nullable ElderModePopupRuleInfo elderModePopupRuleInfo, @Nullable Integer num4, @Nullable ElderModeResponseOthers elderModeResponseOthers, @Nullable BModeSlimUserData bModeSlimUserData) {
        this.code = str;
        this.caringUser = num;
        this.realElderUser = num2;
        this.caringPatternTurn = num3;
        this.caringInfo = elderModeDialogInfo;
        this.standardInfo = elderModeDialogInfo2;
        this.popupRuleInfo = elderModePopupRuleInfo;
        this.operateResult = num4;
        this.others = elderModeResponseOthers;
        this.slimUserData = bModeSlimUserData;
    }

    @Nullable
    /* renamed from: component1  reason: from getter */
    public final String getCode() {
        return this.code;
    }

    @Nullable
    /* renamed from: component10  reason: from getter */
    public final BModeSlimUserData getSlimUserData() {
        return this.slimUserData;
    }

    @Nullable
    /* renamed from: component2  reason: from getter */
    public final Integer getCaringUser() {
        return this.caringUser;
    }

    @Nullable
    /* renamed from: component3  reason: from getter */
    public final Integer getRealElderUser() {
        return this.realElderUser;
    }

    @Nullable
    /* renamed from: component4  reason: from getter */
    public final Integer getCaringPatternTurn() {
        return this.caringPatternTurn;
    }

    @Nullable
    /* renamed from: component5  reason: from getter */
    public final ElderModeDialogInfo getCaringInfo() {
        return this.caringInfo;
    }

    @Nullable
    /* renamed from: component6  reason: from getter */
    public final ElderModeDialogInfo getStandardInfo() {
        return this.standardInfo;
    }

    @Nullable
    /* renamed from: component7  reason: from getter */
    public final ElderModePopupRuleInfo getPopupRuleInfo() {
        return this.popupRuleInfo;
    }

    @Nullable
    /* renamed from: component8  reason: from getter */
    public final Integer getOperateResult() {
        return this.operateResult;
    }

    @Nullable
    /* renamed from: component9  reason: from getter */
    public final ElderModeResponseOthers getOthers() {
        return this.others;
    }

    @NotNull
    public final ElderModeResponse copy(@Nullable String code, @Nullable Integer caringUser, @Nullable Integer realElderUser, @Nullable Integer caringPatternTurn, @Nullable ElderModeDialogInfo caringInfo, @Nullable ElderModeDialogInfo standardInfo, @Nullable ElderModePopupRuleInfo popupRuleInfo, @Nullable Integer operateResult, @Nullable ElderModeResponseOthers others, @Nullable BModeSlimUserData slimUserData) {
        return new ElderModeResponse(code, caringUser, realElderUser, caringPatternTurn, caringInfo, standardInfo, popupRuleInfo, operateResult, others, slimUserData);
    }

    public boolean equals(@Nullable Object other) {
        if (this != other) {
            if (other instanceof ElderModeResponse) {
                ElderModeResponse elderModeResponse = (ElderModeResponse) other;
                return Intrinsics.areEqual(this.code, elderModeResponse.code) && Intrinsics.areEqual(this.caringUser, elderModeResponse.caringUser) && Intrinsics.areEqual(this.realElderUser, elderModeResponse.realElderUser) && Intrinsics.areEqual(this.caringPatternTurn, elderModeResponse.caringPatternTurn) && Intrinsics.areEqual(this.caringInfo, elderModeResponse.caringInfo) && Intrinsics.areEqual(this.standardInfo, elderModeResponse.standardInfo) && Intrinsics.areEqual(this.popupRuleInfo, elderModeResponse.popupRuleInfo) && Intrinsics.areEqual(this.operateResult, elderModeResponse.operateResult) && Intrinsics.areEqual(this.others, elderModeResponse.others) && Intrinsics.areEqual(this.slimUserData, elderModeResponse.slimUserData);
            }
            return false;
        }
        return true;
    }

    @Nullable
    public final ElderModeDialogInfo getCaringInfo() {
        return this.caringInfo;
    }

    @Nullable
    public final Integer getCaringPatternTurn() {
        return this.caringPatternTurn;
    }

    @Nullable
    public final Integer getCaringUser() {
        return this.caringUser;
    }

    @Nullable
    public final String getCode() {
        return this.code;
    }

    @Nullable
    public final Integer getElderMode() {
        return this.caringPatternTurn;
    }

    @Nullable
    public final Integer getOperateResult() {
        return this.operateResult;
    }

    @Nullable
    public final ElderModeResponseOthers getOthers() {
        return this.others;
    }

    @Nullable
    public final ElderModePopupRuleInfo getPopupRuleInfo() {
        return this.popupRuleInfo;
    }

    @Nullable
    public final Integer getPotentialElder() {
        return this.caringUser;
    }

    @Nullable
    public final Integer getRealElderUser() {
        return this.realElderUser;
    }

    @Nullable
    public final BModeSlimUserData getSlimUserData() {
        return this.slimUserData;
    }

    @Nullable
    public final ElderModeDialogInfo getStandardInfo() {
        return this.standardInfo;
    }

    public int hashCode() {
        String str = this.code;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        Integer num = this.caringUser;
        int hashCode2 = (hashCode + (num != null ? num.hashCode() : 0)) * 31;
        Integer num2 = this.realElderUser;
        int hashCode3 = (hashCode2 + (num2 != null ? num2.hashCode() : 0)) * 31;
        Integer num3 = this.caringPatternTurn;
        int hashCode4 = (hashCode3 + (num3 != null ? num3.hashCode() : 0)) * 31;
        ElderModeDialogInfo elderModeDialogInfo = this.caringInfo;
        int hashCode5 = (hashCode4 + (elderModeDialogInfo != null ? elderModeDialogInfo.hashCode() : 0)) * 31;
        ElderModeDialogInfo elderModeDialogInfo2 = this.standardInfo;
        int hashCode6 = (hashCode5 + (elderModeDialogInfo2 != null ? elderModeDialogInfo2.hashCode() : 0)) * 31;
        ElderModePopupRuleInfo elderModePopupRuleInfo = this.popupRuleInfo;
        int hashCode7 = (hashCode6 + (elderModePopupRuleInfo != null ? elderModePopupRuleInfo.hashCode() : 0)) * 31;
        Integer num4 = this.operateResult;
        int hashCode8 = (hashCode7 + (num4 != null ? num4.hashCode() : 0)) * 31;
        ElderModeResponseOthers elderModeResponseOthers = this.others;
        int hashCode9 = (hashCode8 + (elderModeResponseOthers != null ? elderModeResponseOthers.hashCode() : 0)) * 31;
        BModeSlimUserData bModeSlimUserData = this.slimUserData;
        return hashCode9 + (bModeSlimUserData != null ? bModeSlimUserData.hashCode() : 0);
    }

    public final void setCaringPatternTurn(@Nullable Integer num) {
        this.caringPatternTurn = num;
    }

    public final void setRealElderUser(@Nullable Integer num) {
        this.realElderUser = num;
    }

    @NotNull
    public String toString() {
        return "ElderModeResponse(code=" + this.code + ", caringUser=" + this.caringUser + ", realElderUser=" + this.realElderUser + ", caringPatternTurn=" + this.caringPatternTurn + ", caringInfo=" + this.caringInfo + ", standardInfo=" + this.standardInfo + ", popupRuleInfo=" + this.popupRuleInfo + ", operateResult=" + this.operateResult + ", others=" + this.others + ", slimUserData=" + this.slimUserData + ")";
    }
}
