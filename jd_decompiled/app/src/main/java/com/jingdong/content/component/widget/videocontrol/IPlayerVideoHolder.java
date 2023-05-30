package com.jingdong.content.component.widget.videocontrol;

import android.view.View;
import android.widget.FrameLayout;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0011\u0010\u0003\u001a\u0004\u0018\u00010\u0002H&\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0011\u0010\u0005\u001a\u0004\u0018\u00010\u0002H&\u00a2\u0006\u0004\b\u0005\u0010\u0004J\u0011\u0010\u0007\u001a\u0004\u0018\u00010\u0006H&\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0011\u0010\n\u001a\u0004\u0018\u00010\tH&\u00a2\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\r\u001a\u00020\fH\u0016\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0011\u0010\u000f\u001a\u0004\u0018\u00010\u0002H&\u00a2\u0006\u0004\b\u000f\u0010\u0004J\u000f\u0010\u0011\u001a\u00020\u0010H\u0016\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0013\u001a\u00020\fH\u0016\u00a2\u0006\u0004\b\u0013\u0010\u000eJ\u000f\u0010\u0015\u001a\u00020\u0014H\u0016\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u000f\u0010\u0017\u001a\u00020\fH\u0016\u00a2\u0006\u0004\b\u0017\u0010\u000e\u00a8\u0006\u0018"}, d2 = {"Lcom/jingdong/content/component/widget/videocontrol/IPlayerVideoHolder;", "", "", "getVideoUrl", "()Ljava/lang/String;", "getVideoImg", "Landroid/widget/FrameLayout;", "getPlayerContainer", "()Landroid/widget/FrameLayout;", "Landroid/view/View;", "getForeGroudView", "()Landroid/view/View;", "", "isVoiceOn", "()Z", "getContentId", "", "getIndex", "()I", "getIsRequestAudioFocus", "", "getCornerRadius", "()F", "isVideo", "content-component-widget_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes12.dex */
public interface IPlayerVideoHolder {

    @Metadata(bv = {1, 0, 3}, d1 = {}, d2 = {}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes12.dex */
    public static final class DefaultImpls {
        public static float getCornerRadius(IPlayerVideoHolder iPlayerVideoHolder) {
            return 0.0f;
        }

        public static int getIndex(IPlayerVideoHolder iPlayerVideoHolder) {
            return -1;
        }

        public static boolean getIsRequestAudioFocus(IPlayerVideoHolder iPlayerVideoHolder) {
            return false;
        }

        public static boolean isVideo(IPlayerVideoHolder iPlayerVideoHolder) {
            return true;
        }

        public static boolean isVoiceOn(IPlayerVideoHolder iPlayerVideoHolder) {
            return false;
        }
    }

    @Nullable
    String getContentId();

    float getCornerRadius();

    @Nullable
    View getForeGroudView();

    int getIndex();

    boolean getIsRequestAudioFocus();

    @Nullable
    FrameLayout getPlayerContainer();

    @Nullable
    String getVideoImg();

    @Nullable
    String getVideoUrl();

    boolean isVideo();

    boolean isVoiceOn();
}
