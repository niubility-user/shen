package com.jingdong.common.imagegifexpand;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Animatable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;
import androidx.viewpager.widget.ViewPager;
import com.facebook.drawee.interfaces.DraweeController;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.listener.JDImageLoadingListener;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.utils.JDImageUtils;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0016\u0018\u00002\u00020\u00012\u00020\u0002B)\b\u0007\u0012\b\u0010H\u001a\u0004\u0018\u00010G\u0012\n\b\u0002\u0010J\u001a\u0004\u0018\u00010I\u0012\b\b\u0002\u0010K\u001a\u00020\u001f\u00a2\u0006\u0004\bL\u0010MJ\u000f\u0010\u0004\u001a\u00020\u0003H\u0002\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u0019\u0010\b\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0002\u00a2\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0019\u0010\u000f\u001a\u00020\n2\b\u0010\u000e\u001a\u0004\u0018\u00010\rH\u0002\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0011\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b\u0011\u0010\fJ\u000f\u0010\u0012\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b\u0012\u0010\fJ\u0015\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u0013\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u0015\u0010\u0019\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\u0017\u00a2\u0006\u0004\b\u0019\u0010\u001aJK\u0010$\u001a\u00020\n2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010 \u001a\u00020\u001f2\b\b\u0002\u0010!\u001a\u00020\u001f2\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\"2\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0007\u00a2\u0006\u0004\b$\u0010%J\u000f\u0010&\u001a\u00020\nH\u0014\u00a2\u0006\u0004\b&\u0010\fJ\u000f\u0010'\u001a\u00020\nH\u0014\u00a2\u0006\u0004\b'\u0010\fJ'\u0010,\u001a\u00020\n2\u0006\u0010(\u001a\u00020\u001f2\u0006\u0010*\u001a\u00020)2\u0006\u0010+\u001a\u00020\u001fH\u0016\u00a2\u0006\u0004\b,\u0010-J\u0017\u0010.\u001a\u00020\n2\u0006\u0010(\u001a\u00020\u001fH\u0016\u00a2\u0006\u0004\b.\u0010/J\r\u00100\u001a\u00020\n\u00a2\u0006\u0004\b0\u0010\fJ\r\u00101\u001a\u00020\n\u00a2\u0006\u0004\b1\u0010\fJ\r\u00102\u001a\u00020\n\u00a2\u0006\u0004\b2\u0010\fJ\u0017\u00104\u001a\u00020\n2\u0006\u00103\u001a\u00020\u001fH\u0016\u00a2\u0006\u0004\b4\u0010/R\u0016\u00106\u001a\u0002058\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b6\u00107R\u0016\u00108\u001a\u00020\u00178\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b8\u00109R\u0016\u0010:\u001a\u00020\u001d8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b:\u0010;R\"\u0010<\u001a\u00020\u001d8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b<\u0010;\u001a\u0004\b=\u0010>\"\u0004\b?\u0010@R\u0018\u0010A\u001a\u0004\u0018\u00010\u001f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bA\u0010BR\u0018\u0010C\u001a\u0004\u0018\u00010\u00138\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bC\u0010DR\u0018\u0010E\u001a\u0004\u0018\u00010\u00038\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bE\u0010F\u00a8\u0006N"}, d2 = {"Lcom/jingdong/common/imagegifexpand/BannerGifImageView;", "Lcom/jingdong/common/imagegifexpand/AnimateImageView;", "Landroidx/viewpager/widget/ViewPager$OnPageChangeListener;", "Ljava/lang/Runnable;", "getDelayRunnable", "()Ljava/lang/Runnable;", "Lcom/jingdong/app/util/image/listener/JDImageLoadingListener;", "loadingListener", "createLoadingListener", "(Lcom/jingdong/app/util/image/listener/JDImageLoadingListener;)Lcom/jingdong/app/util/image/listener/JDImageLoadingListener;", "", "checkViewPager", "()V", "Landroid/view/ViewParent;", "view", "tryFindViewPager", "(Landroid/view/ViewParent;)V", "playOrPause", "removeDelayMessage", "Landroidx/viewpager/widget/ViewPager;", "viewPager", "bindViewPager", "(Landroidx/viewpager/widget/ViewPager;)V", "", "delay", "setDelayPlayTime", "(J)V", "", "bannerImage", "", "forcePlay", "", "realPosition", "defaultSelect", "Lcom/jingdong/app/util/image/JDDisplayImageOptions;", "imageOptions", "displayImage", "(Ljava/lang/String;ZIILcom/jingdong/app/util/image/JDDisplayImageOptions;Lcom/jingdong/app/util/image/listener/JDImageLoadingListener;)V", "onAttachedToWindow", "onDetachedFromWindow", "position", "", "positionOffset", "positionOffsetPixels", "onPageScrolled", "(IFI)V", "onPageSelected", "(I)V", "pause", "play", "playAtOnce", XView2Constants.STATE, "onPageScrollStateChanged", "Landroid/os/Handler;", "mHandler", "Landroid/os/Handler;", "mDelayPlayTime", "J", "mSelect", "Z", "autoBindViewPager", "getAutoBindViewPager", "()Z", "setAutoBindViewPager", "(Z)V", "mRealPosition", "Ljava/lang/Integer;", "mViewPager", "Landroidx/viewpager/widget/ViewPager;", "mDelayRunnable", "Ljava/lang/Runnable;", "Landroid/content/Context;", AnnoConst.Constructor_Context, "Landroid/util/AttributeSet;", "attrs", "defStyle", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "AndroidJD-Lib_androidRelease"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes5.dex */
public class BannerGifImageView extends AnimateImageView implements ViewPager.OnPageChangeListener {
    private HashMap _$_findViewCache;
    private boolean autoBindViewPager;
    private long mDelayPlayTime;
    private Runnable mDelayRunnable;
    private Handler mHandler;
    private Integer mRealPosition;
    private boolean mSelect;
    private ViewPager mViewPager;

    @JvmOverloads
    public BannerGifImageView(@Nullable Context context) {
        this(context, null, 0, 6, null);
    }

    @JvmOverloads
    public BannerGifImageView(@Nullable Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
    }

    public /* synthetic */ BannerGifImageView(Context context, AttributeSet attributeSet, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? null : attributeSet, (i3 & 4) != 0 ? 0 : i2);
    }

    private final void checkViewPager() {
        if (this.autoBindViewPager && this.mViewPager == null) {
            ViewParent parent = getParent();
            if (parent instanceof ViewPager) {
                this.mViewPager = (ViewPager) parent;
            } else {
                tryFindViewPager(parent);
            }
        }
    }

    private final JDImageLoadingListener createLoadingListener(final JDImageLoadingListener loadingListener) {
        return new JDImageLoadingListener() { // from class: com.jingdong.common.imagegifexpand.BannerGifImageView$createLoadingListener$1
            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingCancelled(@Nullable String p0, @Nullable View p1) {
                JDImageLoadingListener jDImageLoadingListener = loadingListener;
                if (jDImageLoadingListener != null) {
                    jDImageLoadingListener.onLoadingCancelled(p0, p1);
                }
            }

            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingComplete(@NotNull String s, @NotNull View view, @NotNull Bitmap bitmap) {
                JDImageLoadingListener jDImageLoadingListener = loadingListener;
                if (jDImageLoadingListener != null) {
                    jDImageLoadingListener.onLoadingComplete(s, view, bitmap);
                }
                BannerGifImageView.this.playOrPause();
            }

            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingFailed(@Nullable String p0, @Nullable View p1, @Nullable JDFailReason p2) {
                JDImageLoadingListener jDImageLoadingListener = loadingListener;
                if (jDImageLoadingListener != null) {
                    jDImageLoadingListener.onLoadingFailed(p0, p1, p2);
                }
            }

            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingStarted(@Nullable String p0, @Nullable View p1) {
                JDImageLoadingListener jDImageLoadingListener = loadingListener;
                if (jDImageLoadingListener != null) {
                    jDImageLoadingListener.onLoadingStarted(p0, p1);
                }
            }
        };
    }

    public static /* synthetic */ void displayImage$default(BannerGifImageView bannerGifImageView, String str, boolean z, int i2, int i3, JDDisplayImageOptions jDDisplayImageOptions, JDImageLoadingListener jDImageLoadingListener, int i4, Object obj) {
        if (obj == null) {
            bannerGifImageView.displayImage(str, z, i2, (i4 & 8) != 0 ? 0 : i3, (i4 & 16) != 0 ? null : jDDisplayImageOptions, (i4 & 32) != 0 ? null : jDImageLoadingListener);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: displayImage");
    }

    private final Runnable getDelayRunnable() {
        Runnable runnable = this.mDelayRunnable;
        if (runnable != null) {
            return runnable;
        }
        Runnable runnable2 = new Runnable() { // from class: com.jingdong.common.imagegifexpand.BannerGifImageView$getDelayRunnable$delayRunnable$1
            @Override // java.lang.Runnable
            public final void run() {
                BannerGifImageView.this.playAtOnce();
            }
        };
        this.mDelayRunnable = runnable2;
        return runnable2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void playOrPause() {
        if (this.mSelect) {
            play();
        } else {
            pause();
        }
    }

    private final void removeDelayMessage() {
        Runnable runnable = this.mDelayRunnable;
        if (runnable != null) {
            this.mHandler.removeCallbacks(runnable);
        }
    }

    private final void tryFindViewPager(ViewParent view) {
        if (view != null) {
            ViewParent parent = view.getParent();
            if (parent instanceof ViewPager) {
                this.mViewPager = (ViewPager) parent;
            } else {
                tryFindViewPager(parent);
            }
        }
    }

    @Override // com.jingdong.common.imagegifexpand.AnimateImageView
    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    @Override // com.jingdong.common.imagegifexpand.AnimateImageView
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

    public final void bindViewPager(@NotNull ViewPager viewPager) {
        this.mViewPager = viewPager;
    }

    @JvmOverloads
    public final void displayImage(@Nullable String str, boolean z, int i2) {
        displayImage$default(this, str, z, i2, 0, null, null, 56, null);
    }

    @JvmOverloads
    public final void displayImage(@Nullable String str, boolean z, int i2, int i3) {
        displayImage$default(this, str, z, i2, i3, null, null, 48, null);
    }

    @JvmOverloads
    public final void displayImage(@Nullable String str, boolean z, int i2, int i3, @Nullable JDDisplayImageOptions jDDisplayImageOptions) {
        displayImage$default(this, str, z, i2, i3, jDDisplayImageOptions, null, 32, null);
    }

    @JvmOverloads
    public final void displayImage(@Nullable String bannerImage, boolean forcePlay, int realPosition, int defaultSelect, @Nullable JDDisplayImageOptions imageOptions, @Nullable JDImageLoadingListener loadingListener) {
        this.mRealPosition = Integer.valueOf(realPosition);
        this.mSelect = realPosition == defaultSelect;
        if (imageOptions == null) {
            imageOptions = new JDDisplayImageOptions().setPlaceholder(4);
            if (Build.VERSION.SDK_INT < 21) {
                imageOptions.setForceStaticImage(true);
            }
        }
        Intrinsics.checkExpressionValueIsNotNull(imageOptions, "jdDisplayImageOptions");
        imageOptions.setAutoPlayAnimations(forcePlay);
        JDImageUtils.displayImage(bannerImage, this, imageOptions, createLoadingListener(loadingListener));
    }

    public final boolean getAutoBindViewPager() {
        return this.autoBindViewPager;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.drawee.view.DraweeView, android.widget.ImageView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        checkViewPager();
        ViewPager viewPager = this.mViewPager;
        if (viewPager != null) {
            viewPager.addOnPageChangeListener(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.drawee.view.DraweeView, android.widget.ImageView, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ViewPager viewPager = this.mViewPager;
        if (viewPager != null) {
            viewPager.removeOnPageChangeListener(this);
        }
        removeDelayMessage();
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int state) {
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageSelected(int position) {
        Integer num = this.mRealPosition;
        this.mSelect = num != null && position == num.intValue();
        playOrPause();
    }

    public final void pause() {
        DraweeController controller = getController();
        Animatable animatable = controller != null ? controller.getAnimatable() : null;
        if (animatable != null) {
            removeDelayMessage();
            animatable.stop();
        }
    }

    public final void play() {
        DraweeController controller = getController();
        if ((controller != null ? controller.getAnimatable() : null) != null) {
            removeDelayMessage();
            if (this.mDelayPlayTime > 0) {
                this.mHandler.postDelayed(getDelayRunnable(), this.mDelayPlayTime);
            } else {
                playAtOnce();
            }
        }
    }

    public final void playAtOnce() {
        DraweeController controller = getController();
        Animatable animatable = controller != null ? controller.getAnimatable() : null;
        if (animatable == null || animatable.isRunning()) {
            return;
        }
        animatable.start();
    }

    public final void setAutoBindViewPager(boolean z) {
        this.autoBindViewPager = z;
    }

    public final void setDelayPlayTime(long delay) {
        this.mDelayPlayTime = delay;
    }

    @JvmOverloads
    public BannerGifImageView(@Nullable Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mHandler = new Handler(Looper.getMainLooper());
        this.autoBindViewPager = true;
    }
}
