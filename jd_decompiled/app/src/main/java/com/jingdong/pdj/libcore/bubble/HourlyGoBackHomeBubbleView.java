package com.jingdong.pdj.libcore.bubble;

import android.content.Context;
import android.view.ViewGroup;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.pdj.libcore.screen.HourlyLayoutSize;
import com.jingdong.pdj.libcore.watcher.HourlyGoGuideBubbleListener;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001\u001eB\u0011\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u001b\u00a2\u0006\u0004\b\u001c\u0010\u001dJI\u0010\u000e\u001a\u00020\r2\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\u0010\t\u001a\u0004\u0018\u00010\b2\b\u0010\n\u001a\u0004\u0018\u00010\b2\b\u0010\f\u001a\u0004\u0018\u00010\u000b\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u00020\rH\u0016\u00a2\u0006\u0004\b\u0010\u0010\u0011J'\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\bH\u0016\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0014\u001a\u00020\rH\u0016\u00a2\u0006\u0004\b\u0014\u0010\u0011R\u0016\u0010\u0016\u001a\u00020\u00158\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u0016\u0010\u0019\u001a\u00020\u00188\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\u001a\u00a8\u0006\u001f"}, d2 = {"Lcom/jingdong/pdj/libcore/bubble/HourlyGoBackHomeBubbleView;", "Lcom/jingdong/pdj/libcore/bubble/HourlyGoBaseBubbleView;", "Landroid/app/Activity;", AnnoConst.Constructor_Context, "Landroid/graphics/PointF;", "pointF", "", "imageUrl", "", "width", "height", "Lcom/jingdong/pdj/libcore/watcher/HourlyGoGuideBubbleListener;", "bubbleListener", "", "bindData", "(Landroid/app/Activity;Landroid/graphics/PointF;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Lcom/jingdong/pdj/libcore/watcher/HourlyGoGuideBubbleListener;)V", "dismiss", "()V", "show", "(Landroid/graphics/PointF;II)V", "showFailed", "Lcom/facebook/drawee/view/SimpleDraweeView;", "ivBubble", "Lcom/facebook/drawee/view/SimpleDraweeView;", "Lcom/jingdong/pdj/libcore/screen/HourlyLayoutSize;", "ivBubbleSize", "Lcom/jingdong/pdj/libcore/screen/HourlyLayoutSize;", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "Companion", "libCore_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes7.dex */
public final class HourlyGoBackHomeBubbleView extends HourlyGoBaseBubbleView {

    /* renamed from: c  reason: collision with root package name */
    public static final a f14650c = new a(0);
    public final SimpleDraweeView a;
    public final HourlyLayoutSize b;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006R\u0016\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\u0004\u00a8\u0006\u0007"}, d2 = {"Lcom/jingdong/pdj/libcore/bubble/HourlyGoBackHomeBubbleView$Companion;", "", "", "HOURLY_GO_BACK_HOME_SHOW_TIME", "Ljava/lang/String;", "<init>", "()V", "libCore_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes7.dex */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(byte b) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"", "run", "()V", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes7.dex */
    public static final class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            ViewGroup rootView = HourlyGoBackHomeBubbleView.this.getRootView();
            if (rootView == null) {
                Intrinsics.throwNpe();
            }
            if (rootView.indexOfChild(HourlyGoBackHomeBubbleView.this) != -1) {
                ViewGroup rootView2 = HourlyGoBackHomeBubbleView.this.getRootView();
                if (rootView2 == null) {
                    Intrinsics.throwNpe();
                }
                rootView2.removeView(HourlyGoBackHomeBubbleView.this);
            }
            HourlyGoBackHomeBubbleView.this.setRootView(null);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"", "run", "()V", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes7.dex */
    public static final class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            HourlyGoBackHomeBubbleView hourlyGoBackHomeBubbleView = HourlyGoBackHomeBubbleView.this;
            if (hourlyGoBackHomeBubbleView.d) {
                hourlyGoBackHomeBubbleView.a();
            }
        }
    }

    public HourlyGoBackHomeBubbleView(@Nullable Context context) {
        super(context);
        HourlyLayoutSize hourlyLayoutSize = new HourlyLayoutSize(272, 64);
        this.b = hourlyLayoutSize;
        SimpleDraweeView simpleDraweeView = new SimpleDraweeView(context);
        this.a = simpleDraweeView;
        addView(simpleDraweeView, hourlyLayoutSize.getRLParams(simpleDraweeView));
    }

    public final void a() {
        if (this.d) {
            setShowing(false);
            ViewGroup rootView = getRootView();
            if (rootView != null) {
                rootView.post(new b());
            }
            HourlyGoGuideBubbleListener b2 = getB();
            if (b2 != null) {
                b2.onNearByBubbleCallBack(2);
            }
        }
    }
}
