package com.jingdong.app.mall.home.category.floor.base;

import android.content.Context;
import android.graphics.Outline;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.dynamic.DYConstants;
import com.jd.libs.jdmbridge.base.proxy.share.IShareAdapter;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.app.mall.home.category.adapter.CaAdapter;
import com.jingdong.app.mall.home.category.view.CaMoreLayout;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.n.g.u.c;
import com.jingdong.app.mall.home.n.h.e;
import com.jingdong.cleanmvp.common.BaseEvent;
import com.jingdong.common.entity.personal.PersonalConstants;
import com.jingdong.jdsdk.auraSetting.AuraConstants;
import com.jingdong.jdsdk.constant.CartConstant;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\b\u0012\u0004\u0012\u00028\u00000\u0003B\u0017\u0012\u0006\u00103\u001a\u000202\u0012\u0006\u00105\u001a\u000204\u00a2\u0006\u0004\b6\u00107J\u0017\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00028\u0000H\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\b\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00028\u0000H\u0002\u00a2\u0006\u0004\b\b\u0010\u0007J\u000f\u0010\n\u001a\u00020\tH\u0002\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0015\u0010\u000e\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\f\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u00020\u0005H\u0004\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0012\u001a\u00020\u0005H\u0004\u00a2\u0006\u0004\b\u0012\u0010\u0011J\u000f\u0010\u0013\u001a\u00020\u0005H\u0004\u00a2\u0006\u0004\b\u0013\u0010\u0011J\u000f\u0010\u0014\u001a\u00020\u0005H\u0004\u00a2\u0006\u0004\b\u0014\u0010\u0011J\u000f\u0010\u0015\u001a\u00020\u0005H\u0014\u00a2\u0006\u0004\b\u0015\u0010\u0011J\u000f\u0010\u0016\u001a\u00020\u0005H\u0014\u00a2\u0006\u0004\b\u0016\u0010\u0011J\u000f\u0010\u0017\u001a\u00020\tH\u0014\u00a2\u0006\u0004\b\u0017\u0010\u000bJ\u000f\u0010\u0002\u001a\u00020\tH\u0014\u00a2\u0006\u0004\b\u0002\u0010\u000bJ\u0017\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00028\u0000H\u0016\u00a2\u0006\u0004\b\u0018\u0010\u0007J\u000f\u0010\u0019\u001a\u00020\u0005H\u0004\u00a2\u0006\u0004\b\u0019\u0010\u0011J%\u0010\u001d\u001a\u00020\u00052\u0014\u0010\u001c\u001a\u0010\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u001aH\u0016\u00a2\u0006\u0004\b\u001d\u0010\u001eR\u0018\u0010\"\u001a\u0004\u0018\u00010\u001f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b \u0010!R\u0018\u0010&\u001a\u0004\u0018\u00010#8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b$\u0010%R\u0018\u0010(\u001a\u0004\u0018\u00010#8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b'\u0010%R\u0018\u0010*\u001a\u0004\u0018\u00010\u001f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b)\u0010!R\u0018\u0010.\u001a\u0004\u0018\u00010+8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b,\u0010-R$\u00101\u001a\u0010\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u001a8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b/\u00100\u00a8\u00068"}, d2 = {"Lcom/jingdong/app/mall/home/category/floor/base/BaseCaEventFloor;", "Lcom/jingdong/app/mall/home/n/g/u/c;", "M", "Lcom/jingdong/app/mall/home/category/floor/base/BaseCaFloor;", CartConstant.KEY_VENDOR_ITEM, "", DYConstants.LETTER_H, "(Lcom/jingdong/app/mall/home/n/g/u/c;)V", "G", "", AuraConstants.MESSAGE_COUPON_TYPE_NEW, "()Z", "Lcom/jingdong/cleanmvp/common/BaseEvent;", "event", "onEventMainThread", "(Lcom/jingdong/cleanmvp/common/BaseEvent;)V", "I", "()V", "K", "J", "L", "F", "E", IShareAdapter.SHARE_ACTION_OPEN, "i", AuraConstants.MESSAGE_COUPON_TYPE_WILL_EXPIRE, "Landroid/util/Pair;", "", "radiiRect", "k", "(Landroid/util/Pair;)V", "Lcom/jingdong/app/mall/home/floor/common/f;", "r", "Lcom/jingdong/app/mall/home/floor/common/f;", "mTopDecorateSize", "Lcom/facebook/drawee/view/SimpleDraweeView;", "q", "Lcom/facebook/drawee/view/SimpleDraweeView;", "mTopDecorateBg", "o", "mBottomDecorateBg", "p", "mBottomDecorateSize", "Landroid/view/ViewOutlineProvider;", "m", "Landroid/view/ViewOutlineProvider;", "mClipProvider", PersonalConstants.ICON_STYLE_N, "Landroid/util/Pair;", "mClipPair", "Landroid/content/Context;", AnnoConst.Constructor_Context, "Lcom/jingdong/app/mall/home/category/adapter/CaAdapter;", "adapter", "<init>", "(Landroid/content/Context;Lcom/jingdong/app/mall/home/category/adapter/CaAdapter;)V", "home_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes4.dex */
public abstract class BaseCaEventFloor<M extends com.jingdong.app.mall.home.n.g.u.c> extends BaseCaFloor<M> {

    /* renamed from: m  reason: collision with root package name and from kotlin metadata */
    private ViewOutlineProvider mClipProvider;

    /* renamed from: n  reason: collision with root package name and from kotlin metadata */
    private Pair<Integer, Integer> mClipPair;

    /* renamed from: o  reason: from kotlin metadata */
    private SimpleDraweeView mBottomDecorateBg;

    /* renamed from: p  reason: from kotlin metadata */
    private f mBottomDecorateSize;

    /* renamed from: q  reason: from kotlin metadata */
    private SimpleDraweeView mTopDecorateBg;

    /* renamed from: r  reason: from kotlin metadata */
    private f mTopDecorateSize;

    /* loaded from: classes4.dex */
    public static final class a extends com.jingdong.app.mall.home.o.a.b {

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ com.jingdong.app.mall.home.n.g.v.c f8672h;

        a(com.jingdong.app.mall.home.n.g.v.c cVar) {
            this.f8672h = cVar;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            BaseCaEventFloor.this.E();
            com.jingdong.app.mall.home.n.g.v.b.a(this.f8672h);
        }
    }

    /* loaded from: classes4.dex */
    public static final class b extends ViewOutlineProvider {
        b() {
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(@NotNull View view, @NotNull Outline outline) {
            Integer num;
            Integer num2;
            if (BaseCaEventFloor.this.mClipPair == null) {
                return;
            }
            int height = view.getHeight() - view.getPaddingBottom();
            Pair pair = BaseCaEventFloor.this.mClipPair;
            if (pair == null) {
                Intrinsics.throwNpe();
            }
            if (Intrinsics.compare(((Number) pair.second).intValue(), 0) <= 0) {
                Pair pair2 = BaseCaEventFloor.this.mClipPair;
                if (pair2 == null) {
                    Intrinsics.throwNpe();
                }
                num = (Integer) pair2.first;
            } else {
                num = 0;
            }
            Intrinsics.checkExpressionValueIsNotNull(num, "if (mClipPair!!.second <\u2026 mClipPair!!.first else 0");
            int intValue = height + num.intValue();
            int paddingTop = view.getPaddingTop();
            Pair pair3 = BaseCaEventFloor.this.mClipPair;
            if (pair3 == null) {
                Intrinsics.throwNpe();
            }
            if (Intrinsics.compare(((Number) pair3.first).intValue(), 0) <= 0) {
                Pair pair4 = BaseCaEventFloor.this.mClipPair;
                if (pair4 == null) {
                    Intrinsics.throwNpe();
                }
                num2 = (Integer) pair4.second;
            } else {
                num2 = 0;
            }
            Intrinsics.checkExpressionValueIsNotNull(num2, "if (mClipPair!!.first <=\u2026mClipPair!!.second else 0");
            int intValue2 = paddingTop - num2.intValue();
            int paddingLeft = view.getPaddingLeft();
            int width = view.getWidth() - view.getPaddingRight();
            Pair pair5 = BaseCaEventFloor.this.mClipPair;
            if (pair5 == null) {
                Intrinsics.throwNpe();
            }
            Object obj = pair5.first;
            Intrinsics.checkExpressionValueIsNotNull(obj, "mClipPair!!.first");
            int intValue3 = ((Number) obj).intValue();
            Pair pair6 = BaseCaEventFloor.this.mClipPair;
            if (pair6 == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(pair6.second, "mClipPair!!.second");
            outline.setRoundRect(paddingLeft, intValue2, width, intValue, Math.max(intValue3, ((Number) r0).intValue()));
        }
    }

    public BaseCaEventFloor(@NotNull Context context, @NotNull CaAdapter caAdapter) {
        super(context, caAdapter);
        RelativeLayout.LayoutParams layoutParams;
        RelativeLayout.LayoutParams layoutParams2 = null;
        if (O()) {
            SimpleDraweeView simpleDraweeView = new SimpleDraweeView(context);
            this.mTopDecorateBg = simpleDraweeView;
            if (simpleDraweeView != null) {
                simpleDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
            }
            f fVar = new f(-1, 90);
            this.mTopDecorateSize = fVar;
            SimpleDraweeView simpleDraweeView2 = this.mTopDecorateBg;
            if (fVar != null) {
                if (simpleDraweeView2 == null) {
                    Intrinsics.throwNpe();
                }
                layoutParams = fVar.u(simpleDraweeView2);
            } else {
                layoutParams = null;
            }
            addView(simpleDraweeView2, layoutParams);
        }
        if (M()) {
            SimpleDraweeView simpleDraweeView3 = new SimpleDraweeView(context);
            this.mBottomDecorateBg = simpleDraweeView3;
            if (simpleDraweeView3 != null) {
                simpleDraweeView3.setScaleType(ImageView.ScaleType.FIT_XY);
            }
            f fVar2 = new f(-1, 90);
            this.mBottomDecorateSize = fVar2;
            if (fVar2 != null) {
                SimpleDraweeView simpleDraweeView4 = this.mBottomDecorateBg;
                if (simpleDraweeView4 == null) {
                    Intrinsics.throwNpe();
                }
                layoutParams2 = fVar2.u(simpleDraweeView4);
            }
            if (layoutParams2 != null) {
                layoutParams2.addRule(12);
            }
            addView(this.mBottomDecorateBg, layoutParams2);
        }
        com.jingdong.app.mall.home.o.a.f.G0(this);
    }

    private final void G(M item) {
        if (this.mBottomDecorateBg == null) {
            return;
        }
        String b2 = item.b();
        if (TextUtils.isEmpty(b2)) {
            SimpleDraweeView simpleDraweeView = this.mBottomDecorateBg;
            if (simpleDraweeView != null) {
                simpleDraweeView.setVisibility(8);
                return;
            }
            return;
        }
        SimpleDraweeView simpleDraweeView2 = this.mBottomDecorateBg;
        if (simpleDraweeView2 != null) {
            simpleDraweeView2.setVisibility(0);
        }
        e.a(this.mBottomDecorateBg, com.jingdong.app.mall.home.floor.common.d.d(24) - 1);
        f.c(this.mBottomDecorateBg, this.mBottomDecorateSize);
        com.jingdong.app.mall.home.floor.ctrl.e.f(b2, this.mBottomDecorateBg, com.jingdong.app.mall.home.floor.ctrl.e.f9402h);
    }

    private final void H(M item) {
        if (this.mTopDecorateBg == null) {
            return;
        }
        if (p() + r() < com.jingdong.app.mall.home.floor.common.d.d(260)) {
            SimpleDraweeView simpleDraweeView = this.mTopDecorateBg;
            if (simpleDraweeView != null) {
                simpleDraweeView.setVisibility(8);
                return;
            }
            return;
        }
        String j2 = item.j();
        if (TextUtils.isEmpty(j2)) {
            SimpleDraweeView simpleDraweeView2 = this.mTopDecorateBg;
            if (simpleDraweeView2 != null) {
                simpleDraweeView2.setVisibility(8);
                return;
            }
            return;
        }
        SimpleDraweeView simpleDraweeView3 = this.mTopDecorateBg;
        if (simpleDraweeView3 != null) {
            simpleDraweeView3.setVisibility(0);
        }
        e.h(this.mTopDecorateBg, com.jingdong.app.mall.home.floor.common.d.d(24) - 1);
        f.c(this.mTopDecorateBg, this.mTopDecorateSize);
        com.jingdong.app.mall.home.floor.ctrl.e.f(j2, this.mTopDecorateBg, com.jingdong.app.mall.home.floor.ctrl.e.f9402h);
    }

    private final boolean N() {
        if (n() != null) {
            M n2 = n();
            if (n2 == null) {
                Intrinsics.throwNpe();
            }
            if (n2.m()) {
                return true;
            }
        }
        return false;
    }

    protected final void D() {
        M n2 = n();
        com.jingdong.app.mall.home.n.g.v.c e2 = n2 != null ? n2.e() : null;
        if (e2 != null && e2.A()) {
            e2.E();
        } else {
            com.jingdong.app.mall.home.o.a.f.u0(new a(e2));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void E() {
    }

    protected void F() {
        CaMoreLayout j2 = CaMoreLayout.j();
        if (u() && (j2 == null || j2.getVisibility() != 0)) {
            M n2 = n();
            com.jingdong.app.mall.home.n.g.v.c e2 = n2 != null ? n2.e() : null;
            if (e2 == null || e2.A()) {
                return;
            }
            E();
            com.jingdong.app.mall.home.n.g.v.b.a(e2);
        }
    }

    protected final void I() {
        F();
    }

    protected final void J() {
    }

    protected final void K() {
        F();
    }

    protected final void L() {
    }

    protected boolean M() {
        return false;
    }

    protected boolean O() {
        return false;
    }

    @Override // com.jingdong.app.mall.home.category.floor.base.BaseCaFloor
    public void i(@NotNull M item) {
        super.i(item);
        H(item);
        G(item);
        D();
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0037, code lost:
        if (kotlin.jvm.internal.Intrinsics.compare(((java.lang.Number) r3.second).intValue(), 0) > 0) goto L17;
     */
    @Override // com.jingdong.app.mall.home.category.floor.base.BaseCaFloor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void k(@Nullable Pair<Integer, Integer> radiiRect) {
        if (N() && com.jingdong.app.mall.home.o.a.f.M0()) {
            this.mClipPair = radiiRect;
            boolean z = false;
            if (radiiRect != null) {
                if (radiiRect == null) {
                    Intrinsics.throwNpe();
                }
                if (Intrinsics.compare(((Number) radiiRect.first).intValue(), 0) <= 0) {
                    Pair<Integer, Integer> pair = this.mClipPair;
                    if (pair == null) {
                        Intrinsics.throwNpe();
                    }
                }
                if (B()) {
                    z = true;
                }
            }
            if (this.mClipProvider == null && z) {
                this.mClipProvider = new b();
            }
            ViewOutlineProvider outlineProvider = getOutlineProvider();
            ViewOutlineProvider viewOutlineProvider = z ? this.mClipProvider : null;
            if (outlineProvider != viewOutlineProvider) {
                setOutlineProvider(viewOutlineProvider);
                setClipToOutline(z);
            }
        }
    }

    public final void onEventMainThread(@NotNull BaseEvent event) {
        String type;
        if ((event instanceof com.jingdong.app.mall.home.n.g.v.a) && (type = event.getType()) != null) {
            switch (type.hashCode()) {
                case -1382255763:
                    if (type.equals("ev_gone")) {
                        J();
                        return;
                    }
                    return;
                case -1381904949:
                    if (type.equals("ev_show")) {
                        K();
                        return;
                    }
                    return;
                case -635326488:
                    if (type.equals("ev_tab_change")) {
                        L();
                        return;
                    }
                    return;
                case -62846756:
                    if (type.equals("ev_more_close")) {
                        I();
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }
}
