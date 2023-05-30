package com.jingdong.content.component.widget.videocontrol;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.content.component.R;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tv.danmaku.ijk.media.example.widget.media.JDVideoView;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u00002\u00020\u00012\u00020\u0002B\u0011\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0004\bV\u0010WB\u001b\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0004\bV\u0010\tB#\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010X\u001a\u00020\u0019\u00a2\u0006\u0004\bV\u0010YJ!\u0010\b\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u00032\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0003\u00a2\u0006\u0004\b\b\u0010\tJ\u000f\u0010\n\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\f\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b\f\u0010\u000bJ\u0015\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\r\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u0015\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\r\u00a2\u0006\u0004\b\u0012\u0010\u0010J\u0017\u0010\u0015\u001a\u00020\u00072\b\u0010\u0014\u001a\u0004\u0018\u00010\u0013\u00a2\u0006\u0004\b\u0015\u0010\u0016J\r\u0010\u0017\u001a\u00020\u0007\u00a2\u0006\u0004\b\u0017\u0010\u000bJ\r\u0010\u0018\u001a\u00020\u0007\u00a2\u0006\u0004\b\u0018\u0010\u000bJ9\u0010 \u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u00192\b\u0010\u001e\u001a\u0004\u0018\u00010\u001d2\b\u0010\u001f\u001a\u0004\u0018\u00010\u001d\u00a2\u0006\u0004\b \u0010!J9\u0010#\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u00192\u0006\u0010\"\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u00192\b\u0010\u001e\u001a\u0004\u0018\u00010\u001d2\b\u0010\u001f\u001a\u0004\u0018\u00010\u001d\u00a2\u0006\u0004\b#\u0010!J\u000f\u0010%\u001a\u0004\u0018\u00010$\u00a2\u0006\u0004\b%\u0010&J\u000f\u0010(\u001a\u0004\u0018\u00010'\u00a2\u0006\u0004\b(\u0010)J\u0015\u0010,\u001a\u00020\u00072\u0006\u0010+\u001a\u00020*\u00a2\u0006\u0004\b,\u0010-J\u0015\u00100\u001a\u00020\u00072\u0006\u0010/\u001a\u00020.\u00a2\u0006\u0004\b0\u00101J\u000f\u0010\u000e\u001a\u00020\rH\u0016\u00a2\u0006\u0004\b\u000e\u00102J\u0011\u00103\u001a\u0004\u0018\u00010\u001dH\u0016\u00a2\u0006\u0004\b3\u00104J\u0011\u00105\u001a\u0004\u0018\u00010\u001dH\u0016\u00a2\u0006\u0004\b5\u00104J\u0011\u00106\u001a\u0004\u0018\u00010\u001dH\u0016\u00a2\u0006\u0004\b6\u00104J\u0011\u00107\u001a\u0004\u0018\u00010\u0001H\u0016\u00a2\u0006\u0004\b7\u00108J\u0011\u0010:\u001a\u0004\u0018\u000109H\u0016\u00a2\u0006\u0004\b:\u0010;J\u000f\u0010<\u001a\u00020\u0019H\u0016\u00a2\u0006\u0004\b<\u0010=J\u000f\u0010>\u001a\u00020\rH\u0016\u00a2\u0006\u0004\b>\u00102J\u000f\u0010@\u001a\u00020?H\u0016\u00a2\u0006\u0004\b@\u0010AJ\u000f\u0010B\u001a\u00020\rH\u0016\u00a2\u0006\u0004\bB\u00102R\u0018\u0010D\u001a\u0004\u0018\u00010C8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bD\u0010ER\u0018\u0010F\u001a\u0004\u0018\u00010\u00138\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bF\u0010GR\u0018\u0010\u001f\u001a\u0004\u0018\u00010\u001d8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001f\u0010HR\u0018\u0010\u001e\u001a\u0004\u0018\u00010\u001d8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001e\u0010HR\u0018\u0010/\u001a\u0004\u0018\u00010.8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b/\u0010IR\u0018\u0010J\u001a\u0004\u0018\u00010C8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bJ\u0010ER\u0016\u0010K\u001a\u00020\r8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bK\u0010LR\u0018\u0010+\u001a\u0004\u0018\u00010*8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b+\u0010MR\u0018\u0010O\u001a\u0004\u0018\u00010N8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bO\u0010PR\u0016\u0010Q\u001a\u00020?8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bQ\u0010RR\u0016\u0010S\u001a\u00020\u001d8\u0002@\u0002X\u0082D\u00a2\u0006\u0006\n\u0004\bS\u0010HR\u0018\u0010T\u001a\u0004\u0018\u00010\u00018\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bT\u0010U\u00a8\u0006Z"}, d2 = {"Lcom/jingdong/content/component/widget/videocontrol/ContentVideoHolderView;", "Landroid/widget/FrameLayout;", "Lcom/jingdong/content/component/widget/videocontrol/IPlayerVideoHolder;", "Landroid/content/Context;", AnnoConst.Constructor_Context, "Landroid/util/AttributeSet;", "attrs", "", XView2Constants.XVIEW2_ACTION_INIT, "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "bindClickListener", "()V", "bindPlayBtnClickListener", "", "isVoiceOn", "setVideoVoiceOn", "(Z)V", "show", "bindPlayBtn", "Lcom/jingdong/content/component/widget/videocontrol/ContentVideoInfo;", "mContentVideoInfo", "configVideoInfo", "(Lcom/jingdong/content/component/widget/videocontrol/ContentVideoInfo;)V", "bindVideoImg", "bindVoiceBtn", "", "t", "r", "iconSize", "", "voiceOnUrl", "voiceOffUrl", "setVoiceRightTopLocation", "(IIILjava/lang/String;Ljava/lang/String;)V", "b", "setVoiceRightBottomLocation", "Lcom/jingdong/content/component/widget/videocontrol/ContentCustomVideoView;", "getVideoView", "()Lcom/jingdong/content/component/widget/videocontrol/ContentCustomVideoView;", "Ltv/danmaku/ijk/media/example/widget/media/JDVideoView;", "getJDVideoView", "()Ltv/danmaku/ijk/media/example/widget/media/JDVideoView;", "Lcom/jingdong/content/component/widget/videocontrol/IVoiceClickListener;", "voiceClickListener", "setVoiceClickListener", "(Lcom/jingdong/content/component/widget/videocontrol/IVoiceClickListener;)V", "Lcom/jingdong/content/component/widget/videocontrol/IPlayClickListener;", "playClickListener", "setPlayClickListener", "(Lcom/jingdong/content/component/widget/videocontrol/IPlayClickListener;)V", "()Z", "getContentId", "()Ljava/lang/String;", "getVideoUrl", "getVideoImg", "getPlayerContainer", "()Landroid/widget/FrameLayout;", "Landroid/view/View;", "getForeGroudView", "()Landroid/view/View;", "getIndex", "()I", "getIsRequestAudioFocus", "", "getCornerRadius", "()F", "isVideo", "Lcom/facebook/drawee/view/SimpleDraweeView;", "voiceSdv", "Lcom/facebook/drawee/view/SimpleDraweeView;", "contentVideoInfo", "Lcom/jingdong/content/component/widget/videocontrol/ContentVideoInfo;", "Ljava/lang/String;", "Lcom/jingdong/content/component/widget/videocontrol/IPlayClickListener;", "videoImgSv", "showVoiceBtn", "Z", "Lcom/jingdong/content/component/widget/videocontrol/IVoiceClickListener;", "Landroid/widget/ImageView;", "playBtn", "Landroid/widget/ImageView;", "cornerRadius", "F", "TAG", "videoHolderFl", "Landroid/widget/FrameLayout;", "<init>", "(Landroid/content/Context;)V", "defStyleAttr", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "content-component-widget_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes12.dex */
public final class ContentVideoHolderView extends FrameLayout implements IPlayerVideoHolder {
    private final String TAG;
    private HashMap _$_findViewCache;
    private ContentVideoInfo contentVideoInfo;
    private float cornerRadius;
    private ImageView playBtn;
    private IPlayClickListener playClickListener;
    private boolean showVoiceBtn;
    private FrameLayout videoHolderFl;
    private SimpleDraweeView videoImgSv;
    private IVoiceClickListener voiceClickListener;
    private String voiceOffUrl;
    private String voiceOnUrl;
    private SimpleDraweeView voiceSdv;

    public ContentVideoHolderView(@NotNull Context context) {
        super(context);
        this.TAG = "PlayerHolderView";
        init(context, null);
    }

    private final void bindClickListener() {
        SimpleDraweeView simpleDraweeView = this.voiceSdv;
        if (simpleDraweeView != null) {
            simpleDraweeView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.content.component.widget.videocontrol.ContentVideoHolderView$bindClickListener$1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    IVoiceClickListener iVoiceClickListener;
                    boolean z = !ContentVideoHolderView.this.isVoiceOn();
                    ContentVideoHolderView.this.setVideoVoiceOn(z);
                    ContentVideoHolderView.this.bindVoiceBtn();
                    iVoiceClickListener = ContentVideoHolderView.this.voiceClickListener;
                    if (iVoiceClickListener != null) {
                        iVoiceClickListener.targetVoice(z);
                    }
                }
            });
        }
    }

    private final void bindPlayBtnClickListener() {
        ImageView imageView = this.playBtn;
        if (imageView != null) {
            imageView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.content.component.widget.videocontrol.ContentVideoHolderView$bindPlayBtnClickListener$1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    IPlayClickListener iPlayClickListener;
                    ContentVideoHolderView.this.getVideoView();
                    ContentVideoHolderView.this.bindPlayBtn(false);
                    iPlayClickListener = ContentVideoHolderView.this.playClickListener;
                    if (iPlayClickListener != null) {
                        iPlayClickListener.toPlay(ContentVideoHolderView.this);
                    }
                }
            });
        }
    }

    @SuppressLint({"CustomViewStyleable"})
    private final void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.content_custom_video_view_holder, this);
        this.videoImgSv = (SimpleDraweeView) findViewById(R.id.video_img);
        this.playBtn = (ImageView) findViewById(R.id.no_wifi_play_btn);
        this.videoHolderFl = (FrameLayout) findViewById(R.id.video_container);
        this.voiceSdv = (SimpleDraweeView) findViewById(R.id.iv_video_volume_switch);
        this.showVoiceBtn = false;
        bindClickListener();
        bindPlayBtnClickListener();
    }

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i2) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i2));
        if (view == null) {
            View findViewById = findViewById(i2);
            this._$_findViewCache.put(Integer.valueOf(i2), findViewById);
            return findViewById;
        }
        return view;
    }

    public final void bindPlayBtn(boolean show) {
        ContentVideoInfo contentVideoInfo = this.contentVideoInfo;
        if (contentVideoInfo != null) {
            contentVideoInfo.setShowPlayBtn(show);
        }
        ImageView imageView = this.playBtn;
        if (imageView != null) {
            imageView.setVisibility(!show ? 8 : 0);
        }
    }

    public final void bindVideoImg() {
        ContentVideoInfo contentVideoInfo = this.contentVideoInfo;
        if (contentVideoInfo != null) {
            String videoImg = contentVideoInfo != null ? contentVideoInfo.getVideoImg() : null;
            if (!(videoImg == null || videoImg.length() == 0)) {
                SimpleDraweeView simpleDraweeView = this.videoImgSv;
                if (simpleDraweeView != null) {
                    simpleDraweeView.setVisibility(0);
                }
                JDDisplayImageOptions placeholder = new JDDisplayImageOptions().setPlaceholder(R.drawable.loading_empty_image);
                ContentVideoInfo contentVideoInfo2 = this.contentVideoInfo;
                JDImageUtils.displayImage(contentVideoInfo2 != null ? contentVideoInfo2.getVideoImg() : null, this.videoImgSv, placeholder);
                return;
            }
        }
        SimpleDraweeView simpleDraweeView2 = this.videoImgSv;
        if (simpleDraweeView2 != null) {
            simpleDraweeView2.setVisibility(8);
        }
    }

    public final void bindVoiceBtn() {
        if (this.showVoiceBtn) {
            SimpleDraweeView simpleDraweeView = this.voiceSdv;
            if (simpleDraweeView != null) {
                simpleDraweeView.setVisibility(0);
            }
            ContentVideoInfo contentVideoInfo = this.contentVideoInfo;
            if (contentVideoInfo != null) {
                if (contentVideoInfo == null) {
                    Intrinsics.throwNpe();
                }
                if (contentVideoInfo.isVoiceOn()) {
                    JDImageUtils.displayImage(this.voiceOnUrl, this.voiceSdv, new JDDisplayImageOptions().setPlaceholder(R.drawable.loading_empty_image).showImageForEmptyUri(R.drawable.faxian_tuijian_tl_video_volume_on_icon));
                    return;
                }
            }
            JDImageUtils.displayImage(this.voiceOffUrl, this.voiceSdv, new JDDisplayImageOptions().setPlaceholder(R.drawable.loading_empty_image).showImageForEmptyUri(R.drawable.faxian_tuijian_tl_video_volume_off_icon));
            return;
        }
        SimpleDraweeView simpleDraweeView2 = this.voiceSdv;
        if (simpleDraweeView2 != null) {
            simpleDraweeView2.setVisibility(8);
        }
    }

    public final void configVideoInfo(@Nullable ContentVideoInfo mContentVideoInfo) {
        boolean z;
        this.contentVideoInfo = mContentVideoInfo;
        bindVideoImg();
        bindVoiceBtn();
        ContentVideoInfo contentVideoInfo = this.contentVideoInfo;
        if (contentVideoInfo != null) {
            if (contentVideoInfo == null) {
                Intrinsics.throwNpe();
            }
            if (contentVideoInfo.getShowPlayBtn()) {
                z = true;
                bindPlayBtn(z);
            }
        }
        z = false;
        bindPlayBtn(z);
    }

    @Override // com.jingdong.content.component.widget.videocontrol.IPlayerVideoHolder
    @Nullable
    public String getContentId() {
        ContentVideoInfo contentVideoInfo = this.contentVideoInfo;
        if (contentVideoInfo != null) {
            return contentVideoInfo.getContentId();
        }
        return null;
    }

    @Override // com.jingdong.content.component.widget.videocontrol.IPlayerVideoHolder
    public float getCornerRadius() {
        return this.cornerRadius;
    }

    @Override // com.jingdong.content.component.widget.videocontrol.IPlayerVideoHolder
    @Nullable
    public View getForeGroudView() {
        return this.videoImgSv;
    }

    @Override // com.jingdong.content.component.widget.videocontrol.IPlayerVideoHolder
    public int getIndex() {
        ContentVideoInfo contentVideoInfo = this.contentVideoInfo;
        if ((contentVideoInfo != null ? Integer.valueOf(contentVideoInfo.getItemIndex()) : null) != null) {
            ContentVideoInfo contentVideoInfo2 = this.contentVideoInfo;
            if (contentVideoInfo2 == null) {
                Intrinsics.throwNpe();
            }
            return contentVideoInfo2.getItemIndex();
        }
        return -1;
    }

    @Override // com.jingdong.content.component.widget.videocontrol.IPlayerVideoHolder
    public boolean getIsRequestAudioFocus() {
        ContentVideoInfo contentVideoInfo = this.contentVideoInfo;
        if ((contentVideoInfo != null ? Boolean.valueOf(contentVideoInfo.isVoiceOn()) : null) != null) {
            ContentVideoInfo contentVideoInfo2 = this.contentVideoInfo;
            if (contentVideoInfo2 == null) {
                Intrinsics.throwNpe();
            }
            return contentVideoInfo2.isVoiceOn();
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:29:? A[RETURN, SYNTHETIC] */
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final JDVideoView getJDVideoView() {
        ContentCustomVideoView contentCustomVideoView;
        FrameLayout frameLayout = this.videoHolderFl;
        if (frameLayout != null) {
            Integer valueOf = frameLayout != null ? Integer.valueOf(frameLayout.getChildCount()) : null;
            if (valueOf == null) {
                Intrinsics.throwNpe();
            }
            if (valueOf.intValue() > 0) {
                FrameLayout frameLayout2 = this.videoHolderFl;
                if ((frameLayout2 != null ? frameLayout2.getChildAt(0) : null) instanceof ContentCustomVideoView) {
                    FrameLayout frameLayout3 = this.videoHolderFl;
                    View childAt = frameLayout3 != null ? frameLayout3.getChildAt(0) : null;
                    if (childAt != null) {
                        contentCustomVideoView = (ContentCustomVideoView) childAt;
                        if (contentCustomVideoView == null) {
                            return contentCustomVideoView.getmVideoView();
                        }
                        return null;
                    }
                    throw new TypeCastException("null cannot be cast to non-null type com.jingdong.content.component.widget.videocontrol.ContentCustomVideoView");
                }
            }
        }
        contentCustomVideoView = null;
        if (contentCustomVideoView == null) {
        }
    }

    @Override // com.jingdong.content.component.widget.videocontrol.IPlayerVideoHolder
    @Nullable
    /* renamed from: getPlayerContainer  reason: from getter */
    public FrameLayout getVideoHolderFl() {
        return this.videoHolderFl;
    }

    @Override // com.jingdong.content.component.widget.videocontrol.IPlayerVideoHolder
    @Nullable
    public String getVideoImg() {
        ContentVideoInfo contentVideoInfo = this.contentVideoInfo;
        if (contentVideoInfo != null) {
            return contentVideoInfo.getVideoImg();
        }
        return null;
    }

    @Override // com.jingdong.content.component.widget.videocontrol.IPlayerVideoHolder
    @Nullable
    public String getVideoUrl() {
        ContentVideoInfo contentVideoInfo = this.contentVideoInfo;
        if (contentVideoInfo != null) {
            return contentVideoInfo.getVideoUrl();
        }
        return null;
    }

    @Nullable
    public final ContentCustomVideoView getVideoView() {
        FrameLayout frameLayout = this.videoHolderFl;
        if (frameLayout != null) {
            Integer valueOf = frameLayout != null ? Integer.valueOf(frameLayout.getChildCount()) : null;
            if (valueOf == null) {
                Intrinsics.throwNpe();
            }
            if (valueOf.intValue() > 0) {
                FrameLayout frameLayout2 = this.videoHolderFl;
                if ((frameLayout2 != null ? frameLayout2.getChildAt(0) : null) instanceof ContentCustomVideoView) {
                    FrameLayout frameLayout3 = this.videoHolderFl;
                    View childAt = frameLayout3 != null ? frameLayout3.getChildAt(0) : null;
                    if (childAt != null) {
                        return (ContentCustomVideoView) childAt;
                    }
                    throw new TypeCastException("null cannot be cast to non-null type com.jingdong.content.component.widget.videocontrol.ContentCustomVideoView");
                }
                return null;
            }
            return null;
        }
        return null;
    }

    @Override // com.jingdong.content.component.widget.videocontrol.IPlayerVideoHolder
    public boolean isVideo() {
        ContentVideoInfo contentVideoInfo = this.contentVideoInfo;
        if ((contentVideoInfo != null ? Boolean.valueOf(contentVideoInfo.isVideo()) : null) != null) {
            ContentVideoInfo contentVideoInfo2 = this.contentVideoInfo;
            if (contentVideoInfo2 == null) {
                Intrinsics.throwNpe();
            }
            return contentVideoInfo2.isVideo();
        }
        return false;
    }

    @Override // com.jingdong.content.component.widget.videocontrol.IPlayerVideoHolder
    public boolean isVoiceOn() {
        ContentVideoInfo contentVideoInfo = this.contentVideoInfo;
        if ((contentVideoInfo != null ? Boolean.valueOf(contentVideoInfo.isVoiceOn()) : null) != null) {
            ContentVideoInfo contentVideoInfo2 = this.contentVideoInfo;
            if (contentVideoInfo2 == null) {
                Intrinsics.throwNpe();
            }
            return contentVideoInfo2.isVoiceOn();
        }
        return false;
    }

    public final void setPlayClickListener(@NotNull IPlayClickListener playClickListener) {
        this.playClickListener = playClickListener;
    }

    public final void setVideoVoiceOn(boolean isVoiceOn) {
        ContentVideoInfo contentVideoInfo = this.contentVideoInfo;
        if (contentVideoInfo != null) {
            contentVideoInfo.setVoiceOn(isVoiceOn);
        }
        bindVoiceBtn();
        ContentCustomVideoView videoView = getVideoView();
        if (videoView != null) {
            videoView.setVoiceOn(isVoiceOn);
        }
    }

    public final void setVoiceClickListener(@NotNull IVoiceClickListener voiceClickListener) {
        this.voiceClickListener = voiceClickListener;
    }

    public final void setVoiceRightBottomLocation(int r, int b, int iconSize, @Nullable String voiceOnUrl, @Nullable String voiceOffUrl) {
        this.showVoiceBtn = true;
        SimpleDraweeView simpleDraweeView = this.voiceSdv;
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) (simpleDraweeView != null ? simpleDraweeView.getLayoutParams() : null);
        if (layoutParams != null) {
            layoutParams.gravity = 85;
        }
        if (layoutParams != null) {
            layoutParams.rightMargin = r;
        }
        if (layoutParams != null) {
            layoutParams.bottomMargin = b;
        }
        if (iconSize > 0) {
            if (layoutParams != null) {
                layoutParams.width = iconSize;
            }
            if (layoutParams != null) {
                layoutParams.height = iconSize;
            }
        }
        this.voiceOnUrl = voiceOnUrl;
        this.voiceOffUrl = voiceOffUrl;
        bindVoiceBtn();
    }

    public final void setVoiceRightTopLocation(int t, int r, int iconSize, @Nullable String voiceOnUrl, @Nullable String voiceOffUrl) {
        this.showVoiceBtn = true;
        SimpleDraweeView simpleDraweeView = this.voiceSdv;
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) (simpleDraweeView != null ? simpleDraweeView.getLayoutParams() : null);
        if (layoutParams != null) {
            layoutParams.gravity = 53;
        }
        if (layoutParams != null) {
            layoutParams.rightMargin = r;
        }
        if (layoutParams != null) {
            layoutParams.topMargin = t;
        }
        if (iconSize > 0) {
            if (layoutParams != null) {
                layoutParams.width = iconSize;
            }
            if (layoutParams != null) {
                layoutParams.height = iconSize;
            }
        }
        this.voiceOnUrl = voiceOnUrl;
        this.voiceOffUrl = voiceOffUrl;
        bindVoiceBtn();
    }

    public ContentVideoHolderView(@NotNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.TAG = "PlayerHolderView";
        init(context, attributeSet);
    }

    public ContentVideoHolderView(@NotNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.TAG = "PlayerHolderView";
        init(context, attributeSet);
    }
}
