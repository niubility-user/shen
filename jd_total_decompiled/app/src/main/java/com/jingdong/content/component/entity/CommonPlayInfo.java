package com.jingdong.content.component.entity;

import com.jd.viewkit.templates.view.helper.JDViewKitEventHelper;
import com.jingdong.jdsdk.constant.CartConstant;
import com.tencent.connect.share.QzonePublish;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0013\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0019\b\u0086\b\u0018\u00002\u00020\u0001Bc\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0002\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0002\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0005\u00a2\u0006\u0004\b5\u00106J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0006\u001a\u00020\u0005H\u00c6\u0003\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0012\u0010\b\u001a\u0004\u0018\u00010\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\b\u0010\u0004J\u0012\u0010\t\u001a\u0004\u0018\u00010\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\t\u0010\u0004J\u0012\u0010\n\u001a\u0004\u0018\u00010\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\n\u0010\u0004J\u0012\u0010\u000b\u001a\u0004\u0018\u00010\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u000b\u0010\u0004J\u0012\u0010\f\u001a\u0004\u0018\u00010\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\f\u0010\u0004J\u0010\u0010\r\u001a\u00020\u0005H\u00c6\u0003\u00a2\u0006\u0004\b\r\u0010\u0007Jl\u0010\u0016\u001a\u00020\u00002\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\u000f\u001a\u00020\u00052\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\u0015\u001a\u00020\u0005H\u00c6\u0001\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u0010\u0010\u0018\u001a\u00020\u0002H\u00d6\u0001\u00a2\u0006\u0004\b\u0018\u0010\u0004J\u0010\u0010\u001a\u001a\u00020\u0019H\u00d6\u0001\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u001a\u0010\u001f\u001a\u00020\u001e2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001cH\u00d6\u0003\u00a2\u0006\u0004\b\u001f\u0010 R\"\u0010\u000f\u001a\u00020\u00058\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u000f\u0010!\u001a\u0004\b\"\u0010\u0007\"\u0004\b#\u0010$R$\u0010\u000e\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u000e\u0010%\u001a\u0004\b&\u0010\u0004\"\u0004\b'\u0010(R$\u0010\u0010\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0010\u0010%\u001a\u0004\b)\u0010\u0004\"\u0004\b*\u0010(R$\u0010\u0012\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0012\u0010%\u001a\u0004\b+\u0010\u0004\"\u0004\b,\u0010(R$\u0010\u0013\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0013\u0010%\u001a\u0004\b-\u0010\u0004\"\u0004\b.\u0010(R$\u0010\u0011\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0011\u0010%\u001a\u0004\b/\u0010\u0004\"\u0004\b0\u0010(R$\u0010\u0014\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0014\u0010%\u001a\u0004\b1\u0010\u0004\"\u0004\b2\u0010(R\"\u0010\u0015\u001a\u00020\u00058\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0015\u0010!\u001a\u0004\b3\u0010\u0007\"\u0004\b4\u0010$\u00a8\u00067"}, d2 = {"Lcom/jingdong/content/component/entity/CommonPlayInfo;", "Ljava/io/Serializable;", "", "component1", "()Ljava/lang/String;", "", "component2", "()J", "component3", "component4", "component5", "component6", "component7", "component8", "videoUrl", QzonePublish.PUBLISH_TO_QZONE_VIDEO_DURATION, "videoType", "videoImg", CartConstant.KEY_CART_VID, "highLightVid", "highLightUrl", "highLightDuration", JDViewKitEventHelper.ActionType_Copy, "(Ljava/lang/String;JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;J)Lcom/jingdong/content/component/entity/CommonPlayInfo;", "toString", "", "hashCode", "()I", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "J", "getVideoDuration", "setVideoDuration", "(J)V", "Ljava/lang/String;", "getVideoUrl", "setVideoUrl", "(Ljava/lang/String;)V", "getVideoType", "setVideoType", "getVid", "setVid", "getHighLightVid", "setHighLightVid", "getVideoImg", "setVideoImg", "getHighLightUrl", "setHighLightUrl", "getHighLightDuration", "setHighLightDuration", "<init>", "(Ljava/lang/String;JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;J)V", "content-component-widget_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes12.dex */
public final /* data */ class CommonPlayInfo implements Serializable {
    private long highLightDuration;
    @Nullable
    private String highLightUrl;
    @Nullable
    private String highLightVid;
    @Nullable
    private String vid;
    private long videoDuration;
    @Nullable
    private String videoImg;
    @Nullable
    private String videoType;
    @Nullable
    private String videoUrl;

    public CommonPlayInfo() {
        this(null, 0L, null, null, null, null, null, 0L, 255, null);
    }

    public CommonPlayInfo(@Nullable String str, long j2, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, long j3) {
        this.videoUrl = str;
        this.videoDuration = j2;
        this.videoType = str2;
        this.videoImg = str3;
        this.vid = str4;
        this.highLightVid = str5;
        this.highLightUrl = str6;
        this.highLightDuration = j3;
    }

    @Nullable
    /* renamed from: component1  reason: from getter */
    public final String getVideoUrl() {
        return this.videoUrl;
    }

    /* renamed from: component2  reason: from getter */
    public final long getVideoDuration() {
        return this.videoDuration;
    }

    @Nullable
    /* renamed from: component3  reason: from getter */
    public final String getVideoType() {
        return this.videoType;
    }

    @Nullable
    /* renamed from: component4  reason: from getter */
    public final String getVideoImg() {
        return this.videoImg;
    }

    @Nullable
    /* renamed from: component5  reason: from getter */
    public final String getVid() {
        return this.vid;
    }

    @Nullable
    /* renamed from: component6  reason: from getter */
    public final String getHighLightVid() {
        return this.highLightVid;
    }

    @Nullable
    /* renamed from: component7  reason: from getter */
    public final String getHighLightUrl() {
        return this.highLightUrl;
    }

    /* renamed from: component8  reason: from getter */
    public final long getHighLightDuration() {
        return this.highLightDuration;
    }

    @NotNull
    public final CommonPlayInfo copy(@Nullable String videoUrl, long videoDuration, @Nullable String videoType, @Nullable String videoImg, @Nullable String vid, @Nullable String highLightVid, @Nullable String highLightUrl, long highLightDuration) {
        return new CommonPlayInfo(videoUrl, videoDuration, videoType, videoImg, vid, highLightVid, highLightUrl, highLightDuration);
    }

    public boolean equals(@Nullable Object other) {
        if (this != other) {
            if (other instanceof CommonPlayInfo) {
                CommonPlayInfo commonPlayInfo = (CommonPlayInfo) other;
                return Intrinsics.areEqual(this.videoUrl, commonPlayInfo.videoUrl) && this.videoDuration == commonPlayInfo.videoDuration && Intrinsics.areEqual(this.videoType, commonPlayInfo.videoType) && Intrinsics.areEqual(this.videoImg, commonPlayInfo.videoImg) && Intrinsics.areEqual(this.vid, commonPlayInfo.vid) && Intrinsics.areEqual(this.highLightVid, commonPlayInfo.highLightVid) && Intrinsics.areEqual(this.highLightUrl, commonPlayInfo.highLightUrl) && this.highLightDuration == commonPlayInfo.highLightDuration;
            }
            return false;
        }
        return true;
    }

    public final long getHighLightDuration() {
        return this.highLightDuration;
    }

    @Nullable
    public final String getHighLightUrl() {
        return this.highLightUrl;
    }

    @Nullable
    public final String getHighLightVid() {
        return this.highLightVid;
    }

    @Nullable
    public final String getVid() {
        return this.vid;
    }

    public final long getVideoDuration() {
        return this.videoDuration;
    }

    @Nullable
    public final String getVideoImg() {
        return this.videoImg;
    }

    @Nullable
    public final String getVideoType() {
        return this.videoType;
    }

    @Nullable
    public final String getVideoUrl() {
        return this.videoUrl;
    }

    public int hashCode() {
        String str = this.videoUrl;
        int hashCode = (((str != null ? str.hashCode() : 0) * 31) + a.a(this.videoDuration)) * 31;
        String str2 = this.videoType;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.videoImg;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.vid;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.highLightVid;
        int hashCode5 = (hashCode4 + (str5 != null ? str5.hashCode() : 0)) * 31;
        String str6 = this.highLightUrl;
        return ((hashCode5 + (str6 != null ? str6.hashCode() : 0)) * 31) + a.a(this.highLightDuration);
    }

    public final void setHighLightDuration(long j2) {
        this.highLightDuration = j2;
    }

    public final void setHighLightUrl(@Nullable String str) {
        this.highLightUrl = str;
    }

    public final void setHighLightVid(@Nullable String str) {
        this.highLightVid = str;
    }

    public final void setVid(@Nullable String str) {
        this.vid = str;
    }

    public final void setVideoDuration(long j2) {
        this.videoDuration = j2;
    }

    public final void setVideoImg(@Nullable String str) {
        this.videoImg = str;
    }

    public final void setVideoType(@Nullable String str) {
        this.videoType = str;
    }

    public final void setVideoUrl(@Nullable String str) {
        this.videoUrl = str;
    }

    @NotNull
    public String toString() {
        return "CommonPlayInfo(videoUrl=" + this.videoUrl + ", videoDuration=" + this.videoDuration + ", videoType=" + this.videoType + ", videoImg=" + this.videoImg + ", vid=" + this.vid + ", highLightVid=" + this.highLightVid + ", highLightUrl=" + this.highLightUrl + ", highLightDuration=" + this.highLightDuration + ")";
    }

    public /* synthetic */ CommonPlayInfo(String str, long j2, String str2, String str3, String str4, String str5, String str6, long j3, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : str, (i2 & 2) != 0 ? 0L : j2, (i2 & 4) != 0 ? null : str2, (i2 & 8) != 0 ? null : str3, (i2 & 16) != 0 ? null : str4, (i2 & 32) != 0 ? null : str5, (i2 & 64) == 0 ? str6 : null, (i2 & 128) == 0 ? j3 : 0L);
    }
}
