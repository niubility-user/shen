package com.jingdong.content.component.widget.videocontrol;

import com.jd.viewkit.templates.view.helper.JDViewKitEventHelper;
import com.jingdong.sdk.platform.business.personal.R2;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tv.danmaku.ijk.media.ext.report.ReportConstant;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0013\n\u0002\u0010\u0000\n\u0002\b\u001e\b\u0086\b\u0018\u00002\u00020\u0001Bu\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0002\u0012\b\b\u0002\u0010\u0017\u001a\u00020\t\u0012\b\b\u0002\u0010\u0018\u001a\u00020\f\u0012\b\b\u0002\u0010\u0019\u001a\u00020\t\u0012\b\b\u0002\u0010\u001a\u001a\u00020\t\u0012\b\b\u0002\u0010\u001b\u001a\u00020\t\u00a2\u0006\u0004\b<\u0010=J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0005\u0010\u0004J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0006\u0010\u0004J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\u0007\u0010\u0004J\u0012\u0010\b\u001a\u0004\u0018\u00010\u0002H\u00c6\u0003\u00a2\u0006\u0004\b\b\u0010\u0004J\u0010\u0010\n\u001a\u00020\tH\u00c6\u0003\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\r\u001a\u00020\fH\u00c6\u0003\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\tH\u00c6\u0003\u00a2\u0006\u0004\b\u000f\u0010\u000bJ\u0010\u0010\u0010\u001a\u00020\tH\u00c6\u0003\u00a2\u0006\u0004\b\u0010\u0010\u000bJ\u0010\u0010\u0011\u001a\u00020\tH\u00c6\u0003\u00a2\u0006\u0004\b\u0011\u0010\u000bJ~\u0010\u001c\u001a\u00020\u00002\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\u0017\u001a\u00020\t2\b\b\u0002\u0010\u0018\u001a\u00020\f2\b\b\u0002\u0010\u0019\u001a\u00020\t2\b\b\u0002\u0010\u001a\u001a\u00020\t2\b\b\u0002\u0010\u001b\u001a\u00020\tH\u00c6\u0001\u00a2\u0006\u0004\b\u001c\u0010\u001dJ\u0010\u0010\u001e\u001a\u00020\u0002H\u00d6\u0001\u00a2\u0006\u0004\b\u001e\u0010\u0004J\u0010\u0010\u001f\u001a\u00020\fH\u00d6\u0001\u00a2\u0006\u0004\b\u001f\u0010\u000eJ\u001a\u0010\"\u001a\u00020\t2\b\u0010!\u001a\u0004\u0018\u00010 H\u00d6\u0003\u00a2\u0006\u0004\b\"\u0010#R\"\u0010\u0017\u001a\u00020\t8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0017\u0010$\u001a\u0004\b%\u0010\u000b\"\u0004\b&\u0010'R\"\u0010\u001a\u001a\u00020\t8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u001a\u0010$\u001a\u0004\b(\u0010\u000b\"\u0004\b)\u0010'R$\u0010\u0014\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0014\u0010*\u001a\u0004\b+\u0010\u0004\"\u0004\b,\u0010-R\"\u0010\u0018\u001a\u00020\f8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0018\u0010.\u001a\u0004\b/\u0010\u000e\"\u0004\b0\u00101R$\u0010\u0015\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0015\u0010*\u001a\u0004\b2\u0010\u0004\"\u0004\b3\u0010-R$\u0010\u0013\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0013\u0010*\u001a\u0004\b4\u0010\u0004\"\u0004\b5\u0010-R$\u0010\u0012\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0012\u0010*\u001a\u0004\b6\u0010\u0004\"\u0004\b7\u0010-R\"\u0010\u0019\u001a\u00020\t8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0019\u0010$\u001a\u0004\b\u0019\u0010\u000b\"\u0004\b8\u0010'R\"\u0010\u001b\u001a\u00020\t8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u001b\u0010$\u001a\u0004\b\u001b\u0010\u000b\"\u0004\b9\u0010'R$\u0010\u0016\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0016\u0010*\u001a\u0004\b:\u0010\u0004\"\u0004\b;\u0010-\u00a8\u0006>"}, d2 = {"Lcom/jingdong/content/component/widget/videocontrol/ContentVideoInfo;", "Ljava/io/Serializable;", "", "component1", "()Ljava/lang/String;", "component2", "component3", "component4", "component5", "", "component6", "()Z", "", "component7", "()I", "component8", "component9", "component10", "videoUrl", "videoImg", "contentId", ReportConstant.CommonInfo.PLAY_TYPE, "extStr", "requestAudioFocus", "itemIndex", "isVoiceOn", "showPlayBtn", "isVideo", JDViewKitEventHelper.ActionType_Copy, "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZIZZZ)Lcom/jingdong/content/component/widget/videocontrol/ContentVideoInfo;", "toString", "hashCode", "", "other", "equals", "(Ljava/lang/Object;)Z", "Z", "getRequestAudioFocus", "setRequestAudioFocus", "(Z)V", "getShowPlayBtn", "setShowPlayBtn", "Ljava/lang/String;", "getContentId", "setContentId", "(Ljava/lang/String;)V", "I", "getItemIndex", "setItemIndex", "(I)V", "getPlayType", "setPlayType", "getVideoImg", "setVideoImg", "getVideoUrl", "setVideoUrl", "setVoiceOn", "setVideo", "getExtStr", "setExtStr", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZIZZZ)V", "content-component-widget_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes12.dex */
public final /* data */ class ContentVideoInfo implements Serializable {
    @Nullable
    private String contentId;
    @Nullable
    private String extStr;
    private boolean isVideo;
    private boolean isVoiceOn;
    private int itemIndex;
    @Nullable
    private String playType;
    private boolean requestAudioFocus;
    private boolean showPlayBtn;
    @Nullable
    private String videoImg;
    @Nullable
    private String videoUrl;

    public ContentVideoInfo() {
        this(null, null, null, null, null, false, 0, false, false, false, R2.attr.icon_font_color, null);
    }

    public ContentVideoInfo(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, boolean z, int i2, boolean z2, boolean z3, boolean z4) {
        this.videoUrl = str;
        this.videoImg = str2;
        this.contentId = str3;
        this.playType = str4;
        this.extStr = str5;
        this.requestAudioFocus = z;
        this.itemIndex = i2;
        this.isVoiceOn = z2;
        this.showPlayBtn = z3;
        this.isVideo = z4;
    }

    @Nullable
    /* renamed from: component1  reason: from getter */
    public final String getVideoUrl() {
        return this.videoUrl;
    }

    /* renamed from: component10  reason: from getter */
    public final boolean getIsVideo() {
        return this.isVideo;
    }

    @Nullable
    /* renamed from: component2  reason: from getter */
    public final String getVideoImg() {
        return this.videoImg;
    }

    @Nullable
    /* renamed from: component3  reason: from getter */
    public final String getContentId() {
        return this.contentId;
    }

    @Nullable
    /* renamed from: component4  reason: from getter */
    public final String getPlayType() {
        return this.playType;
    }

    @Nullable
    /* renamed from: component5  reason: from getter */
    public final String getExtStr() {
        return this.extStr;
    }

    /* renamed from: component6  reason: from getter */
    public final boolean getRequestAudioFocus() {
        return this.requestAudioFocus;
    }

    /* renamed from: component7  reason: from getter */
    public final int getItemIndex() {
        return this.itemIndex;
    }

    /* renamed from: component8  reason: from getter */
    public final boolean getIsVoiceOn() {
        return this.isVoiceOn;
    }

    /* renamed from: component9  reason: from getter */
    public final boolean getShowPlayBtn() {
        return this.showPlayBtn;
    }

    @NotNull
    public final ContentVideoInfo copy(@Nullable String videoUrl, @Nullable String videoImg, @Nullable String contentId, @Nullable String playType, @Nullable String extStr, boolean requestAudioFocus, int itemIndex, boolean isVoiceOn, boolean showPlayBtn, boolean isVideo) {
        return new ContentVideoInfo(videoUrl, videoImg, contentId, playType, extStr, requestAudioFocus, itemIndex, isVoiceOn, showPlayBtn, isVideo);
    }

    public boolean equals(@Nullable Object other) {
        if (this != other) {
            if (other instanceof ContentVideoInfo) {
                ContentVideoInfo contentVideoInfo = (ContentVideoInfo) other;
                return Intrinsics.areEqual(this.videoUrl, contentVideoInfo.videoUrl) && Intrinsics.areEqual(this.videoImg, contentVideoInfo.videoImg) && Intrinsics.areEqual(this.contentId, contentVideoInfo.contentId) && Intrinsics.areEqual(this.playType, contentVideoInfo.playType) && Intrinsics.areEqual(this.extStr, contentVideoInfo.extStr) && this.requestAudioFocus == contentVideoInfo.requestAudioFocus && this.itemIndex == contentVideoInfo.itemIndex && this.isVoiceOn == contentVideoInfo.isVoiceOn && this.showPlayBtn == contentVideoInfo.showPlayBtn && this.isVideo == contentVideoInfo.isVideo;
            }
            return false;
        }
        return true;
    }

    @Nullable
    public final String getContentId() {
        return this.contentId;
    }

    @Nullable
    public final String getExtStr() {
        return this.extStr;
    }

    public final int getItemIndex() {
        return this.itemIndex;
    }

    @Nullable
    public final String getPlayType() {
        return this.playType;
    }

    public final boolean getRequestAudioFocus() {
        return this.requestAudioFocus;
    }

    public final boolean getShowPlayBtn() {
        return this.showPlayBtn;
    }

    @Nullable
    public final String getVideoImg() {
        return this.videoImg;
    }

    @Nullable
    public final String getVideoUrl() {
        return this.videoUrl;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        String str = this.videoUrl;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.videoImg;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.contentId;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.playType;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.extStr;
        int hashCode5 = (hashCode4 + (str5 != null ? str5.hashCode() : 0)) * 31;
        boolean z = this.requestAudioFocus;
        int i2 = z;
        if (z != 0) {
            i2 = 1;
        }
        int i3 = (((hashCode5 + i2) * 31) + this.itemIndex) * 31;
        boolean z2 = this.isVoiceOn;
        int i4 = z2;
        if (z2 != 0) {
            i4 = 1;
        }
        int i5 = (i3 + i4) * 31;
        boolean z3 = this.showPlayBtn;
        int i6 = z3;
        if (z3 != 0) {
            i6 = 1;
        }
        int i7 = (i5 + i6) * 31;
        boolean z4 = this.isVideo;
        return i7 + (z4 ? 1 : z4 ? 1 : 0);
    }

    public final boolean isVideo() {
        return this.isVideo;
    }

    public final boolean isVoiceOn() {
        return this.isVoiceOn;
    }

    public final void setContentId(@Nullable String str) {
        this.contentId = str;
    }

    public final void setExtStr(@Nullable String str) {
        this.extStr = str;
    }

    public final void setItemIndex(int i2) {
        this.itemIndex = i2;
    }

    public final void setPlayType(@Nullable String str) {
        this.playType = str;
    }

    public final void setRequestAudioFocus(boolean z) {
        this.requestAudioFocus = z;
    }

    public final void setShowPlayBtn(boolean z) {
        this.showPlayBtn = z;
    }

    public final void setVideo(boolean z) {
        this.isVideo = z;
    }

    public final void setVideoImg(@Nullable String str) {
        this.videoImg = str;
    }

    public final void setVideoUrl(@Nullable String str) {
        this.videoUrl = str;
    }

    public final void setVoiceOn(boolean z) {
        this.isVoiceOn = z;
    }

    @NotNull
    public String toString() {
        return "ContentVideoInfo(videoUrl=" + this.videoUrl + ", videoImg=" + this.videoImg + ", contentId=" + this.contentId + ", playType=" + this.playType + ", extStr=" + this.extStr + ", requestAudioFocus=" + this.requestAudioFocus + ", itemIndex=" + this.itemIndex + ", isVoiceOn=" + this.isVoiceOn + ", showPlayBtn=" + this.showPlayBtn + ", isVideo=" + this.isVideo + ")";
    }

    public /* synthetic */ ContentVideoInfo(String str, String str2, String str3, String str4, String str5, boolean z, int i2, boolean z2, boolean z3, boolean z4, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? null : str, (i3 & 2) != 0 ? null : str2, (i3 & 4) != 0 ? null : str3, (i3 & 8) != 0 ? null : str4, (i3 & 16) == 0 ? str5 : null, (i3 & 32) != 0 ? false : z, (i3 & 64) != 0 ? 0 : i2, (i3 & 128) != 0 ? false : z2, (i3 & 256) != 0 ? false : z3, (i3 & 512) == 0 ? z4 : false);
    }
}
