package com.jingdong.pdj.libcore.bubble;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextPaint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Space;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener;
import com.jingdong.pdj.libcore.R;
import com.jingdong.pdj.libcore.point.HourlyGoHomeMaiDianUpload;
import com.jingdong.pdj.libcore.point.PointData;
import com.jingdong.pdj.libcore.screen.HourlyGoDpi750;
import com.jingdong.pdj.libcore.screen.HourlyLayoutSize;
import com.jingdong.pdj.libcore.utils.HourlyGoColorUtil;
import com.jingdong.pdj.libcore.watcher.HourlyGoGuideBubbleListener;
import com.meizu.cloud.pushsdk.notification.model.AdvanceSetting;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0003\u001a\u0004\u0018\u000105\u00a2\u0006\u0004\b6\u00107J5\u0010\u000b\u001a\u00020\n2\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\u0010\t\u001a\u0004\u0018\u00010\b\u00a2\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\r\u001a\u00020\nH\u0016\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0015\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u000f\u00a2\u0006\u0004\b\u0011\u0010\u0012J'\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0013H\u0016\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u000f\u0010\u0018\u001a\u00020\nH\u0016\u00a2\u0006\u0004\b\u0018\u0010\u000eR\u0018\u0010\u001a\u001a\u0004\u0018\u00010\u00198\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u0018\u0010\u001c\u001a\u0004\u0018\u00010\u00198\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u001bR\u0016\u0010\u001d\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001d\u0010\u001eR\u0016\u0010 \u001a\u00020\u001f8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b \u0010!R\u0016\u0010#\u001a\u00020\"8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b#\u0010$R\u0016\u0010%\u001a\u00020\u001f8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b%\u0010!R\u0016\u0010&\u001a\u00020\"8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b&\u0010$R\u0016\u0010(\u001a\u00020'8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b(\u0010)R\u0016\u0010*\u001a\u00020\"8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b*\u0010$R\u0016\u0010,\u001a\u00020+8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b,\u0010-R\u0016\u0010.\u001a\u00020\"8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b.\u0010$R\u0016\u00100\u001a\u00020/8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b0\u00101R\u0016\u00102\u001a\u00020\"8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b2\u0010$R\u0016\u00103\u001a\u00020/8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b3\u00101R\u0016\u00104\u001a\u00020\"8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b4\u0010$\u00a8\u00068"}, d2 = {"Lcom/jingdong/pdj/libcore/bubble/HourlyGoGuideBubbleView;", "Lcom/jingdong/pdj/libcore/bubble/HourlyGoBaseBubbleView;", "Landroid/app/Activity;", AnnoConst.Constructor_Context, "Landroid/graphics/PointF;", "pointF", "Lcom/jd/framework/json/JDJSONObject;", "bubbleObject", "Lcom/jingdong/pdj/libcore/watcher/HourlyGoGuideBubbleListener;", "bubbleListener", "", "bindData", "(Landroid/app/Activity;Landroid/graphics/PointF;Lcom/jd/framework/json/JDJSONObject;Lcom/jingdong/pdj/libcore/watcher/HourlyGoGuideBubbleListener;)V", "dismiss", "()V", "", "anim", "dismissWithAnim", "(Z)V", "", "width", "height", "show", "(Landroid/graphics/PointF;II)V", "showFailed", "Landroid/animation/AnimatorSet;", "animEnd", "Landroid/animation/AnimatorSet;", "animStart", "clicked", "Z", "Lcom/facebook/drawee/view/SimpleDraweeView;", "ivBg", "Lcom/facebook/drawee/view/SimpleDraweeView;", "Lcom/jingdong/pdj/libcore/screen/HourlyLayoutSize;", "ivBgSize", "Lcom/jingdong/pdj/libcore/screen/HourlyLayoutSize;", "ivX", "ivXSize", "Landroid/widget/LinearLayout;", "llSkuParent", "Landroid/widget/LinearLayout;", "llSkuParentSize", "Landroid/widget/Space;", "space", "Landroid/widget/Space;", "spaceSize", "Landroid/widget/TextView;", "tvMainTitle", "Landroid/widget/TextView;", "tvMainTitleSize", "tvSubTitle", "tvSubTitleSize", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "libCore_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes7.dex */
public final class HourlyGoGuideBubbleView extends HourlyGoBaseBubbleView {
    public final SimpleDraweeView a;
    public final HourlyLayoutSize b;

    /* renamed from: c  reason: collision with root package name */
    public final Space f14652c;

    /* renamed from: f  reason: collision with root package name */
    public final HourlyLayoutSize f14653f;

    /* renamed from: g  reason: collision with root package name */
    public final TextView f14654g;

    /* renamed from: h  reason: collision with root package name */
    public final HourlyLayoutSize f14655h;

    /* renamed from: i  reason: collision with root package name */
    public final TextView f14656i;

    /* renamed from: j  reason: collision with root package name */
    public final HourlyLayoutSize f14657j;

    /* renamed from: k  reason: collision with root package name */
    public final SimpleDraweeView f14658k;

    /* renamed from: l  reason: collision with root package name */
    public final HourlyLayoutSize f14659l;

    /* renamed from: m  reason: collision with root package name */
    public final LinearLayout f14660m;

    /* renamed from: n  reason: collision with root package name */
    public final HourlyLayoutSize f14661n;
    public AnimatorSet o;
    private AnimatorSet p;
    private boolean q;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J-\u0010\t\u001a\u00020\b2\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0016\u00a2\u0006\u0004\b\t\u0010\n\u00a8\u0006\u000b"}, d2 = {"com/jingdong/pdj/libcore/bubble/HourlyGoGuideBubbleView$bindData$1", "Lcom/jingdong/app/util/image/listener/JDSimpleImageLoadingListener;", "", "imageUri", "Landroid/view/View;", "view", "Landroid/graphics/Bitmap;", "loadedImage", "", "onLoadingComplete", "(Ljava/lang/String;Landroid/view/View;Landroid/graphics/Bitmap;)V", "libCore_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes7.dex */
    public static final class a extends JDSimpleImageLoadingListener {
        final /* synthetic */ Activity b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ String f14662c;

        public a(Activity activity, String str) {
            this.b = activity;
            this.f14662c = str;
        }

        @Override // com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener, com.jingdong.app.util.image.listener.JDImageLoadingListener
        public final void onLoadingComplete(@Nullable String imageUri, @Nullable View view, @Nullable Bitmap loadedImage) {
            super.onLoadingComplete(imageUri, view, loadedImage);
            if (loadedImage == null || loadedImage.isRecycled()) {
                return;
            }
            Activity activity = this.b;
            BitmapDrawable bitmapDrawable = new BitmapDrawable(activity != null ? activity.getResources() : null, loadedImage);
            bitmapDrawable.mutate().setColorFilter(HourlyGoColorUtil.getColorByString(this.f14662c, -1), PorterDuff.Mode.SRC_ATOP);
            HourlyGoGuideBubbleView.this.f14658k.setImageDrawable(bitmapDrawable);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u00032\u000e\u0010\u0002\u001a\n \u0001*\u0004\u0018\u00010\u00000\u0000H\n\u00a2\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Landroid/view/View;", "kotlin.jvm.PlatformType", AdvanceSetting.NETWORK_TYPE, "", "onClick", "(Landroid/view/View;)V", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes7.dex */
    public static final class b implements View.OnClickListener {
        final /* synthetic */ HourlyGoGuideBubbleListener b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ PointData f14663c;

        public b(HourlyGoGuideBubbleListener hourlyGoGuideBubbleListener, PointData pointData) {
            this.b = hourlyGoGuideBubbleListener;
            this.f14663c = pointData;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            if (HourlyGoGuideBubbleView.this.q) {
                return;
            }
            HourlyGoGuideBubbleView.this.q = true;
            HourlyGoGuideBubbleListener hourlyGoGuideBubbleListener = this.b;
            if (hourlyGoGuideBubbleListener != null) {
                hourlyGoGuideBubbleListener.onNearByBubbleCallBack(4);
            }
            HourlyGoGuideBubbleView.this.a();
            HourlyGoHomeMaiDianUpload.onViewClickPoint(this.f14663c);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u00032\u000e\u0010\u0002\u001a\n \u0001*\u0004\u0018\u00010\u00000\u0000H\n\u00a2\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Landroid/view/View;", "kotlin.jvm.PlatformType", AdvanceSetting.NETWORK_TYPE, "", "onClick", "(Landroid/view/View;)V", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes7.dex */
    public static final class c implements View.OnClickListener {
        final /* synthetic */ HourlyGoGuideBubbleListener b;

        public c(HourlyGoGuideBubbleListener hourlyGoGuideBubbleListener) {
            this.b = hourlyGoGuideBubbleListener;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            if (HourlyGoGuideBubbleView.this.q) {
                return;
            }
            HourlyGoGuideBubbleView.this.q = true;
            HourlyGoGuideBubbleListener hourlyGoGuideBubbleListener = this.b;
            if (hourlyGoGuideBubbleListener != null) {
                hourlyGoGuideBubbleListener.onNearByBubbleCallBack(5);
            }
            HourlyGoGuideBubbleView.this.a(true);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u00032\u000e\u0010\u0002\u001a\n \u0001*\u0004\u0018\u00010\u00000\u0000H\n\u00a2\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Landroid/view/View;", "kotlin.jvm.PlatformType", AdvanceSetting.NETWORK_TYPE, "", "onClick", "(Landroid/view/View;)V", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes7.dex */
    public static final class d implements View.OnClickListener {
        final /* synthetic */ HourlyGoGuideBubbleListener b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ PointData f14664c;

        public d(HourlyGoGuideBubbleListener hourlyGoGuideBubbleListener, PointData pointData) {
            this.b = hourlyGoGuideBubbleListener;
            this.f14664c = pointData;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            if (HourlyGoGuideBubbleView.this.q) {
                return;
            }
            HourlyGoGuideBubbleView.this.q = true;
            HourlyGoGuideBubbleListener hourlyGoGuideBubbleListener = this.b;
            if (hourlyGoGuideBubbleListener != null) {
                hourlyGoGuideBubbleListener.onNearByBubbleCallBack(4);
            }
            HourlyGoGuideBubbleView.this.a();
            HourlyGoHomeMaiDianUpload.onViewClickPoint(this.f14664c);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"", "run", "()V", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes7.dex */
    public static final class e implements Runnable {
        e() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            ViewGroup rootView = HourlyGoGuideBubbleView.this.getRootView();
            if (rootView == null) {
                Intrinsics.throwNpe();
            }
            if (rootView.indexOfChild(HourlyGoGuideBubbleView.this) != -1) {
                ViewGroup rootView2 = HourlyGoGuideBubbleView.this.getRootView();
                if (rootView2 == null) {
                    Intrinsics.throwNpe();
                }
                rootView2.removeView(HourlyGoGuideBubbleView.this);
            }
            HourlyGoGuideBubbleView.this.setRootView(null);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u0007\u0010\u0006\u00a8\u0006\b"}, d2 = {"com/jingdong/pdj/libcore/bubble/HourlyGoGuideBubbleView$dismissWithAnim$1", "Landroid/animation/AnimatorListenerAdapter;", "Landroid/animation/Animator;", "animation", "", "onAnimationCancel", "(Landroid/animation/Animator;)V", "onAnimationEnd", "libCore_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes7.dex */
    public static final class f extends AnimatorListenerAdapter {
        f() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationCancel(@NotNull Animator animation) {
            super.onAnimationCancel(animation);
            HourlyGoGuideBubbleView.this.a();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(@NotNull Animator animation) {
            super.onAnimationEnd(animation);
            HourlyGoGuideBubbleView.this.a();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u0007\u0010\u0006\u00a8\u0006\b"}, d2 = {"com/jingdong/pdj/libcore/bubble/HourlyGoGuideBubbleView$show$2", "Landroid/animation/AnimatorListenerAdapter;", "Landroid/animation/Animator;", "animation", "", "onAnimationCancel", "(Landroid/animation/Animator;)V", "onAnimationEnd", "libCore_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes7.dex */
    public static final class g extends AnimatorListenerAdapter {

        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"", "run", "()V", "<anonymous>"}, k = 3, mv = {1, 4, 0})
        /* loaded from: classes7.dex */
        static final class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public final void run() {
                HourlyGoGuideBubbleView.this.a(true);
            }
        }

        public g() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationCancel(@NotNull Animator animation) {
            super.onAnimationCancel(animation);
            HourlyGoGuideBubbleView.this.a();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(@NotNull Animator animation) {
            super.onAnimationEnd(animation);
            HourlyGoGuideBubbleView hourlyGoGuideBubbleView = HourlyGoGuideBubbleView.this;
            if (hourlyGoGuideBubbleView.d) {
                hourlyGoGuideBubbleView.postDelayed(new a(), 3000L);
            }
        }
    }

    public HourlyGoGuideBubbleView(@Nullable Context context) {
        super(context);
        HourlyLayoutSize hourlyLayoutSize = new HourlyLayoutSize(406, 214);
        this.b = hourlyLayoutSize;
        HourlyLayoutSize hourlyLayoutSize2 = new HourlyLayoutSize(8, 1);
        this.f14653f = hourlyLayoutSize2;
        HourlyLayoutSize hourlyLayoutSize3 = new HourlyLayoutSize(-2, -2);
        this.f14655h = hourlyLayoutSize3;
        HourlyLayoutSize hourlyLayoutSize4 = new HourlyLayoutSize(-2, -2);
        this.f14657j = hourlyLayoutSize4;
        HourlyLayoutSize hourlyLayoutSize5 = new HourlyLayoutSize(36, 36);
        this.f14659l = hourlyLayoutSize5;
        HourlyLayoutSize hourlyLayoutSize6 = new HourlyLayoutSize(-1, 122);
        this.f14661n = hourlyLayoutSize6;
        SimpleDraweeView simpleDraweeView = new SimpleDraweeView(context);
        simpleDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
        this.a = simpleDraweeView;
        addView(simpleDraweeView, hourlyLayoutSize.getRLParams(simpleDraweeView));
        SimpleDraweeView simpleDraweeView2 = new SimpleDraweeView(context);
        simpleDraweeView2.setId(R.id.lib_hourly_core_guide_bubble_sub_x);
        simpleDraweeView2.setScaleType(ImageView.ScaleType.FIT_XY);
        this.f14658k = simpleDraweeView2;
        RelativeLayout.LayoutParams rLParams = hourlyLayoutSize5.getRLParams(simpleDraweeView2);
        hourlyLayoutSize5.setMargin(new Rect(0, 26, 14, 0), rLParams);
        rLParams.addRule(11);
        addView(simpleDraweeView2, rLParams);
        Space space = new Space(context);
        space.setId(R.id.lib_hourly_core_guide_bubble_title_space);
        this.f14652c = space;
        addView(space, hourlyLayoutSize2.getRLParams(space));
        TextView textView = new TextView(context);
        textView.setId(R.id.lib_hourly_core_guide_bubble_main_title);
        textView.setMaxLines(1);
        textView.setIncludeFontPadding(false);
        textView.setTextSize(0, HourlyGoDpi750.getSizeBy750(28));
        textView.setMaxWidth(HourlyGoDpi750.getSizeBy750(322));
        TextPaint paint = textView.getPaint();
        Intrinsics.checkExpressionValueIsNotNull(paint, "paint");
        paint.setFakeBoldText(true);
        this.f14654g = textView;
        RelativeLayout.LayoutParams rLParams2 = hourlyLayoutSize3.getRLParams(textView);
        hourlyLayoutSize3.setMargin(new Rect(8, 28, 0, 0), rLParams2);
        rLParams2.addRule(1, space.getId());
        addView(textView, rLParams2);
        TextView textView2 = new TextView(context);
        textView2.setId(R.id.lib_hourly_core_guide_bubble_sub_title);
        textView2.setMaxLines(1);
        textView2.setIncludeFontPadding(false);
        textView2.setTextSize(0, HourlyGoDpi750.getSizeBy750(22));
        this.f14656i = textView2;
        RelativeLayout.LayoutParams rLParams3 = hourlyLayoutSize4.getRLParams(textView2);
        hourlyLayoutSize4.setMargin(new Rect(8, 0, 15, 0), rLParams3);
        rLParams3.addRule(1, textView.getId());
        rLParams3.addRule(0, simpleDraweeView2.getId());
        rLParams3.addRule(4, textView.getId());
        addView(textView2, rLParams3);
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(0);
        linearLayout.setGravity(3);
        this.f14660m = linearLayout;
        RelativeLayout.LayoutParams rLParams4 = hourlyLayoutSize6.getRLParams(linearLayout);
        rLParams4.addRule(12);
        rLParams4.addRule(9);
        rLParams4.addRule(11);
        hourlyLayoutSize6.setMargin(new Rect(8, 0, 8, 16), rLParams4);
        addView(linearLayout, rLParams4);
    }

    public final void a(boolean z) {
        if (this.d) {
            if (!z) {
                a();
                return;
            }
            ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(this, PropertyValuesHolder.ofFloat(RelativeLayout.SCALE_X, 1.0f, 0.0f), PropertyValuesHolder.ofFloat(RelativeLayout.SCALE_Y, 1.0f, 0.0f));
            Intrinsics.checkExpressionValueIsNotNull(ofPropertyValuesHolder, "ObjectAnimator.ofPropert\u2026der(this, scaleX, scaleY)");
            ofPropertyValuesHolder.setDuration(450L);
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, RelativeLayout.ALPHA, 1.0f, 0.0f);
            Intrinsics.checkExpressionValueIsNotNull(ofFloat, "ObjectAnimator.ofFloat(this, ALPHA, 1f, 0f)");
            ofFloat.setDuration(200L);
            ofFloat.setStartDelay(250L);
            AnimatorSet animatorSet = new AnimatorSet();
            this.p = animatorSet;
            if (animatorSet != null) {
                animatorSet.playTogether(ofPropertyValuesHolder, ofFloat);
            }
            AnimatorSet animatorSet2 = this.p;
            if (animatorSet2 != null) {
                animatorSet2.addListener(new f());
            }
            AnimatorSet animatorSet3 = this.p;
            if (animatorSet3 != null) {
                animatorSet3.start();
            }
        }
    }

    public final void b() {
        HourlyGoGuideBubbleListener b2 = getB();
        if (b2 != null) {
            b2.onNearByBubbleCallBack(3);
        }
    }

    public final void a() {
        if (this.d) {
            setShowing(false);
            AnimatorSet animatorSet = this.p;
            if (animatorSet != null) {
                animatorSet.cancel();
            }
            AnimatorSet animatorSet2 = this.o;
            if (animatorSet2 != null) {
                animatorSet2.cancel();
            }
            ViewGroup rootView = getRootView();
            if (rootView != null) {
                rootView.post(new e());
            }
            HourlyGoGuideBubbleListener b2 = getB();
            if (b2 != null) {
                b2.onNearByBubbleCallBack(2);
            }
        }
    }
}
