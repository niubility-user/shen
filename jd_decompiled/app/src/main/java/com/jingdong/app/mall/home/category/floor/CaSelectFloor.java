package com.jingdong.app.mall.home.category.floor;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.RelativeLayout;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.app.mall.e;
import com.jingdong.app.mall.home.category.adapter.CaAdapter;
import com.jingdong.app.mall.home.category.floor.base.BaseCaEventFloor;
import com.jingdong.app.mall.home.category.floor.base.c;
import com.jingdong.app.mall.home.category.view.CaContentLayout;
import com.jingdong.app.mall.home.category.widget.CaSelectView;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.n.g.s;
import com.jingdong.app.mall.home.o.a.b;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.constant.JshopConst;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0017\u0012\u0006\u0010%\u001a\u00020$\u0012\u0006\u0010'\u001a\u00020&\u00a2\u0006\u0004\b(\u0010)J\u001f\u0010\b\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\b\u0010\tJ\u0017\u0010\u000b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\u000e\u001a\u00020\rH\u0016\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0014\u001a\u00020\u0013H\u0016\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u0017\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u0016H\u0016\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u0017\u0010\u001a\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u0016H\u0016\u00a2\u0006\u0004\b\u001a\u0010\u0019J\u0017\u0010\u001b\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u0016H\u0016\u00a2\u0006\u0004\b\u001b\u0010\u0019R\u0018\u0010\u001f\u001a\u0004\u0018\u00010\u001c8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001d\u0010\u001eR\u0016\u0010#\u001a\u00020 8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b!\u0010\"\u00a8\u0006*"}, d2 = {"Lcom/jingdong/app/mall/home/category/floor/CaSelectFloor;", "Lcom/jingdong/app/mall/home/category/floor/base/BaseCaEventFloor;", "Lcom/jingdong/app/mall/home/n/g/s;", "Lcom/jingdong/app/mall/home/category/floor/base/c;", "Landroid/view/ViewGroup;", "removeParent", "addParent", "", "Q", "(Landroid/view/ViewGroup;Landroid/view/ViewGroup;)V", CartConstant.KEY_VENDOR_ITEM, "R", "(Lcom/jingdong/app/mall/home/n/g/s;)V", "", "f", "()Z", "attachParent", JshopConst.JSHOP_PROMOTIO_H, "(Landroid/view/ViewGroup;)Z", "", "b", "()I", "Landroid/widget/RelativeLayout;", "floatParent", com.jingdong.jdsdk.a.a.a, "(Landroid/widget/RelativeLayout;)V", e.a, "g", "Lcom/jingdong/app/mall/home/category/widget/CaSelectView;", "s", "Lcom/jingdong/app/mall/home/category/widget/CaSelectView;", "mSelectView", "Lcom/jingdong/app/mall/home/floor/common/f;", "t", "Lcom/jingdong/app/mall/home/floor/common/f;", "mSelectSize", "Landroid/content/Context;", AnnoConst.Constructor_Context, "Lcom/jingdong/app/mall/home/category/adapter/CaAdapter;", "adapter", "<init>", "(Landroid/content/Context;Lcom/jingdong/app/mall/home/category/adapter/CaAdapter;)V", "home_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes4.dex */
public final class CaSelectFloor extends BaseCaEventFloor<s> implements c {
    private static final Handler u = new Handler(Looper.getMainLooper());

    /* renamed from: s  reason: from kotlin metadata */
    private CaSelectView mSelectView;

    /* renamed from: t  reason: from kotlin metadata */
    private final f mSelectSize;

    /* loaded from: classes4.dex */
    public static final class a extends b {

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ ViewGroup f8665h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ ViewGroup f8666i;

        a(ViewGroup viewGroup, ViewGroup viewGroup2) {
            this.f8665h = viewGroup;
            this.f8666i = viewGroup2;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            this.f8665h.removeView(CaSelectFloor.this.mSelectView);
            ViewGroup viewGroup = this.f8665h;
            if (viewGroup != CaSelectFloor.this) {
                viewGroup.setVisibility(8);
            }
            ViewGroup viewGroup2 = this.f8666i;
            if (viewGroup2 != CaSelectFloor.this) {
                viewGroup2.setVisibility(0);
            }
            m.b(this.f8666i, CaSelectFloor.this.mSelectView, this.f8666i != CaSelectFloor.this ? 1 : 0);
        }
    }

    public CaSelectFloor(@NotNull Context context, @NotNull CaAdapter caAdapter) {
        super(context, caAdapter);
        this.mSelectSize = new f(-1, 96);
    }

    private final void Q(ViewGroup removeParent, ViewGroup addParent) {
        Handler handler = u;
        handler.removeCallbacksAndMessages(null);
        handler.post(new a(removeParent, addParent));
    }

    @Override // com.jingdong.app.mall.home.category.floor.base.BaseCaFloor
    /* renamed from: R  reason: merged with bridge method [inline-methods] */
    public void j(@NotNull s item) {
        com.jingdong.app.mall.home.category.adapter.a mParentAdapter = getMParentAdapter();
        ViewGroup c2 = mParentAdapter != null ? mParentAdapter.c() : null;
        if (this.mSelectView == null && (c2 instanceof CaContentLayout)) {
            this.mSelectView = new CaSelectView(getContext(), (CaContentLayout) c2);
        }
        CaSelectView caSelectView = this.mSelectView;
        if (caSelectView == null) {
            return;
        }
        if (caSelectView != null) {
            f fVar = this.mSelectSize;
            if (caSelectView == null) {
                Intrinsics.throwNpe();
            }
            caSelectView.setLayoutParams(fVar.u(caSelectView));
        }
        CaSelectView caSelectView2 = this.mSelectView;
        if (caSelectView2 != null) {
            caSelectView2.a(item);
        }
        m.b(this, this.mSelectView, 0);
    }

    @Override // com.jingdong.app.mall.home.category.floor.base.c
    public void a(@NotNull RelativeLayout floatParent) {
        CaSelectView caSelectView = this.mSelectView;
        if (caSelectView == null) {
            return;
        }
        ViewParent parent = caSelectView != null ? caSelectView.getParent() : null;
        int top = getParent() == null ? 0 : getTop();
        if (top <= 0 && parent != floatParent) {
            CaSelectView caSelectView2 = this.mSelectView;
            if (caSelectView2 != null) {
                caSelectView2.c(true);
            }
            Q(this, floatParent);
        }
        if (top <= 0 || parent == this) {
            return;
        }
        g(floatParent);
        Q(floatParent, this);
    }

    @Override // com.jingdong.app.mall.home.category.floor.base.c
    public int b() {
        return this.mSelectSize.h();
    }

    @Override // com.jingdong.app.mall.home.category.floor.base.c
    public void e(@NotNull RelativeLayout floatParent) {
        g(floatParent);
    }

    @Override // com.jingdong.app.mall.home.category.floor.base.c
    public boolean f() {
        return getParent() != null && getTop() <= 0;
    }

    @Override // com.jingdong.app.mall.home.category.floor.base.c
    public void g(@NotNull RelativeLayout floatParent) {
        CaSelectView caSelectView = this.mSelectView;
        if (caSelectView != null) {
            caSelectView.c(false);
        }
        Q(floatParent, this);
    }

    @Override // com.jingdong.app.mall.home.category.floor.base.c
    public boolean h(@NotNull ViewGroup attachParent) {
        CaSelectView caSelectView = this.mSelectView;
        if (caSelectView == null) {
            Intrinsics.throwNpe();
        }
        return caSelectView.getParent() == attachParent;
    }
}
