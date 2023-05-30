package com.jingdong.pdj.libcore.bubble;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RelativeLayout;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.pdj.libcore.watcher.HourlyGoGuideBubbleListener;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\b&\u0018\u00002\u00020\u0001:\u0001)B\u0011\u0012\b\u0010&\u001a\u0004\u0018\u00010%\u00a2\u0006\u0004\b'\u0010(J\u000f\u0010\u0003\u001a\u00020\u0002H&\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0019\u0010\b\u001a\u0004\u0018\u00010\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0004\b\b\u0010\tJ'\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\fH&\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0011\u001a\u00020\u0002H&\u00a2\u0006\u0004\b\u0011\u0010\u0004R$\u0010\u0012\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R$\u0010\u0019\u001a\u0004\u0018\u00010\u00188\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\"\u0010 \u001a\u00020\u001f8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b \u0010!\u001a\u0004\b \u0010\"\"\u0004\b#\u0010$\u00a8\u0006*"}, d2 = {"Lcom/jingdong/pdj/libcore/bubble/HourlyGoBaseBubbleView;", "Landroid/widget/RelativeLayout;", "", "dismiss", "()V", "Landroid/app/Activity;", "activity", "Landroid/view/ViewGroup;", "getRootDecorView", "(Landroid/app/Activity;)Landroid/view/ViewGroup;", "Landroid/graphics/PointF;", "pointF", "", "width", "height", "show", "(Landroid/graphics/PointF;II)V", "showFailed", "rootView", "Landroid/view/ViewGroup;", "getRootView", "()Landroid/view/ViewGroup;", "setRootView", "(Landroid/view/ViewGroup;)V", "Lcom/jingdong/pdj/libcore/watcher/HourlyGoGuideBubbleListener;", "bubbleListener", "Lcom/jingdong/pdj/libcore/watcher/HourlyGoGuideBubbleListener;", "getBubbleListener", "()Lcom/jingdong/pdj/libcore/watcher/HourlyGoGuideBubbleListener;", "setBubbleListener", "(Lcom/jingdong/pdj/libcore/watcher/HourlyGoGuideBubbleListener;)V", "", "isShowing", "Z", "()Z", "setShowing", "(Z)V", "Landroid/content/Context;", AnnoConst.Constructor_Context, "<init>", "(Landroid/content/Context;)V", "Companion", "libCore_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes7.dex */
public abstract class HourlyGoBaseBubbleView extends RelativeLayout {

    /* renamed from: e  reason: collision with root package name */
    public static final a f14651e = new a(0);
    @Nullable
    private ViewGroup a;
    @Nullable
    private HourlyGoGuideBubbleListener b;
    public boolean d;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\t\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006R\u0016\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\u0004\u00a8\u0006\u0007"}, d2 = {"Lcom/jingdong/pdj/libcore/bubble/HourlyGoBaseBubbleView$Companion;", "", "", "ANIM_TIME", "J", "<init>", "()V", "libCore_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes7.dex */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(byte b) {
            this();
        }
    }

    public HourlyGoBaseBubbleView(@Nullable Context context) {
        super(context);
    }

    @Nullable
    public static ViewGroup a(@Nullable Activity activity) {
        Window window;
        if (activity != null && (window = activity.getWindow()) != null) {
            View peekDecorView = window != null ? window.peekDecorView() : null;
            if (peekDecorView instanceof ViewGroup) {
                return (ViewGroup) peekDecorView;
            }
        }
        return null;
    }

    @Nullable
    /* renamed from: getBubbleListener  reason: from getter */
    public final HourlyGoGuideBubbleListener getB() {
        return this.b;
    }

    @Override // android.view.View
    @Nullable
    public final ViewGroup getRootView() {
        return this.a;
    }

    public final void setBubbleListener(@Nullable HourlyGoGuideBubbleListener hourlyGoGuideBubbleListener) {
        this.b = hourlyGoGuideBubbleListener;
    }

    public final void setRootView(@Nullable ViewGroup viewGroup) {
        this.a = viewGroup;
    }

    public final void setShowing(boolean z) {
        this.d = z;
    }
}
