package com.jingdong.sdk.eldermode.entity;

import com.jd.viewkit.templates.view.helper.JDViewKitEventHelper;
import com.jingdong.common.web.managers.WebPerfManager;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u000f\b\u0086\b\u0018\u0000 (2\u00020\u0001:\u0001(B[\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0004\b&\u0010'J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0012\u0010\b\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0004\b\b\u0010\u0007J\u0012\u0010\t\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0004\b\t\u0010\u0007J\u0012\u0010\n\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0004\b\n\u0010\u0007J\u0012\u0010\u000b\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0004\b\u000b\u0010\u0007J\u0012\u0010\f\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0004\b\f\u0010\u0007Jd\u0010\u0014\u001a\u00020\u00002\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0005H\u00c6\u0001\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u0010\u0010\u0016\u001a\u00020\u0005H\u00d6\u0001\u00a2\u0006\u0004\b\u0016\u0010\u0007J\u0010\u0010\u0017\u001a\u00020\u0002H\u00d6\u0001\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u001a\u0010\u001b\u001a\u00020\u001a2\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003\u00a2\u0006\u0004\b\u001b\u0010\u001cR\u001b\u0010\u000e\u001a\u0004\u0018\u00010\u00058\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u000e\u0010\u001d\u001a\u0004\b\u001e\u0010\u0007R\u001b\u0010\u0010\u001a\u0004\u0018\u00010\u00058\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0010\u0010\u001d\u001a\u0004\b\u001f\u0010\u0007R\u001b\u0010\u0012\u001a\u0004\u0018\u00010\u00058\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0012\u0010\u001d\u001a\u0004\b \u0010\u0007R\u001b\u0010\u000f\u001a\u0004\u0018\u00010\u00058\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u000f\u0010\u001d\u001a\u0004\b!\u0010\u0007R\u001b\u0010\u0011\u001a\u0004\u0018\u00010\u00058\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0011\u0010\u001d\u001a\u0004\b\"\u0010\u0007R\u001b\u0010\r\u001a\u0004\u0018\u00010\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\r\u0010#\u001a\u0004\b$\u0010\u0004R\u001b\u0010\u0013\u001a\u0004\u0018\u00010\u00058\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0013\u0010\u001d\u001a\u0004\b%\u0010\u0007\u00a8\u0006)"}, d2 = {"Lcom/jingdong/sdk/eldermode/entity/ElderModeMtaInfo;", "", "", "component1", "()Ljava/lang/Integer;", "", "component2", "()Ljava/lang/String;", "component3", "component4", "component5", "component6", "component7", "mtaType", "eventId", "eventParam", "pageId", "pageParam", WebPerfManager.PAGE_NAME, "jsonParam", JDViewKitEventHelper.ActionType_Copy, "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/jingdong/sdk/eldermode/entity/ElderModeMtaInfo;", "toString", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getEventId", "getPageId", "getPageName", "getEventParam", "getPageParam", "Ljava/lang/Integer;", "getMtaType", "getJsonParam", "<init>", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "Companion", "eldermodelib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes7.dex */
public final /* data */ class ElderModeMtaInfo {
    public static final int MTA_TYPE_CLICK = 0;
    public static final int MTA_TYPE_EXPOSURE = 1;
    @Nullable
    private final String eventId;
    @Nullable
    private final String eventParam;
    @Nullable
    private final String jsonParam;
    @Nullable
    private final Integer mtaType;
    @Nullable
    private final String pageId;
    @Nullable
    private final String pageName;
    @Nullable
    private final String pageParam;

    public ElderModeMtaInfo() {
        this(null, null, null, null, null, null, null, 127, null);
    }

    public ElderModeMtaInfo(@Nullable Integer num, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6) {
        this.mtaType = num;
        this.eventId = str;
        this.eventParam = str2;
        this.pageId = str3;
        this.pageParam = str4;
        this.pageName = str5;
        this.jsonParam = str6;
    }

    public static /* synthetic */ ElderModeMtaInfo copy$default(ElderModeMtaInfo elderModeMtaInfo, Integer num, String str, String str2, String str3, String str4, String str5, String str6, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            num = elderModeMtaInfo.mtaType;
        }
        if ((i2 & 2) != 0) {
            str = elderModeMtaInfo.eventId;
        }
        String str7 = str;
        if ((i2 & 4) != 0) {
            str2 = elderModeMtaInfo.eventParam;
        }
        String str8 = str2;
        if ((i2 & 8) != 0) {
            str3 = elderModeMtaInfo.pageId;
        }
        String str9 = str3;
        if ((i2 & 16) != 0) {
            str4 = elderModeMtaInfo.pageParam;
        }
        String str10 = str4;
        if ((i2 & 32) != 0) {
            str5 = elderModeMtaInfo.pageName;
        }
        String str11 = str5;
        if ((i2 & 64) != 0) {
            str6 = elderModeMtaInfo.jsonParam;
        }
        return elderModeMtaInfo.copy(num, str7, str8, str9, str10, str11, str6);
    }

    @Nullable
    /* renamed from: component1  reason: from getter */
    public final Integer getMtaType() {
        return this.mtaType;
    }

    @Nullable
    /* renamed from: component2  reason: from getter */
    public final String getEventId() {
        return this.eventId;
    }

    @Nullable
    /* renamed from: component3  reason: from getter */
    public final String getEventParam() {
        return this.eventParam;
    }

    @Nullable
    /* renamed from: component4  reason: from getter */
    public final String getPageId() {
        return this.pageId;
    }

    @Nullable
    /* renamed from: component5  reason: from getter */
    public final String getPageParam() {
        return this.pageParam;
    }

    @Nullable
    /* renamed from: component6  reason: from getter */
    public final String getPageName() {
        return this.pageName;
    }

    @Nullable
    /* renamed from: component7  reason: from getter */
    public final String getJsonParam() {
        return this.jsonParam;
    }

    @NotNull
    public final ElderModeMtaInfo copy(@Nullable Integer mtaType, @Nullable String eventId, @Nullable String eventParam, @Nullable String pageId, @Nullable String pageParam, @Nullable String pageName, @Nullable String jsonParam) {
        return new ElderModeMtaInfo(mtaType, eventId, eventParam, pageId, pageParam, pageName, jsonParam);
    }

    public boolean equals(@Nullable Object other) {
        if (this != other) {
            if (other instanceof ElderModeMtaInfo) {
                ElderModeMtaInfo elderModeMtaInfo = (ElderModeMtaInfo) other;
                return Intrinsics.areEqual(this.mtaType, elderModeMtaInfo.mtaType) && Intrinsics.areEqual(this.eventId, elderModeMtaInfo.eventId) && Intrinsics.areEqual(this.eventParam, elderModeMtaInfo.eventParam) && Intrinsics.areEqual(this.pageId, elderModeMtaInfo.pageId) && Intrinsics.areEqual(this.pageParam, elderModeMtaInfo.pageParam) && Intrinsics.areEqual(this.pageName, elderModeMtaInfo.pageName) && Intrinsics.areEqual(this.jsonParam, elderModeMtaInfo.jsonParam);
            }
            return false;
        }
        return true;
    }

    @Nullable
    public final String getEventId() {
        return this.eventId;
    }

    @Nullable
    public final String getEventParam() {
        return this.eventParam;
    }

    @Nullable
    public final String getJsonParam() {
        return this.jsonParam;
    }

    @Nullable
    public final Integer getMtaType() {
        return this.mtaType;
    }

    @Nullable
    public final String getPageId() {
        return this.pageId;
    }

    @Nullable
    public final String getPageName() {
        return this.pageName;
    }

    @Nullable
    public final String getPageParam() {
        return this.pageParam;
    }

    public int hashCode() {
        Integer num = this.mtaType;
        int hashCode = (num != null ? num.hashCode() : 0) * 31;
        String str = this.eventId;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.eventParam;
        int hashCode3 = (hashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.pageId;
        int hashCode4 = (hashCode3 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.pageParam;
        int hashCode5 = (hashCode4 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.pageName;
        int hashCode6 = (hashCode5 + (str5 != null ? str5.hashCode() : 0)) * 31;
        String str6 = this.jsonParam;
        return hashCode6 + (str6 != null ? str6.hashCode() : 0);
    }

    @NotNull
    public String toString() {
        return "ElderModeMtaInfo(mtaType=" + this.mtaType + ", eventId=" + this.eventId + ", eventParam=" + this.eventParam + ", pageId=" + this.pageId + ", pageParam=" + this.pageParam + ", pageName=" + this.pageName + ", jsonParam=" + this.jsonParam + ")";
    }

    public /* synthetic */ ElderModeMtaInfo(Integer num, String str, String str2, String str3, String str4, String str5, String str6, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : num, (i2 & 2) != 0 ? null : str, (i2 & 4) != 0 ? null : str2, (i2 & 8) != 0 ? null : str3, (i2 & 16) != 0 ? null : str4, (i2 & 32) != 0 ? null : str5, (i2 & 64) != 0 ? null : str6);
    }
}
