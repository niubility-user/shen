package com.jingdong.app.mall.home.category.floor;

import android.content.Context;
import android.graphics.Rect;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.app.mall.home.category.adapter.CaAdapter;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.n.g.d;
import com.jingdong.app.mall.home.n.g.v.b;
import com.jingdong.app.mall.home.n.g.v.c;
import com.jingdong.jdsdk.constant.JshopConst;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\u0012\u0006\u0010\u0015\u001a\u00020\u0014\u0012\u0006\u0010\u0017\u001a\u00020\u0016\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u000f\u0010\u0004\u001a\u00020\u0003H\u0016\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0007\u001a\u00020\u0006H\u0014\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\u000b\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\tH\u0014\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u000e\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\tH\u0014\u00a2\u0006\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0013\u001a\u00020\u00108\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\u0012\u00a8\u0006\u001a"}, d2 = {"Lcom/jingdong/app/mall/home/category/floor/CaCouponFloor;", "Lcom/jingdong/app/mall/home/category/floor/CaRecycleLinearFloor;", "Lcom/jingdong/app/mall/home/n/g/d;", "Lcom/jingdong/app/mall/home/floor/common/f;", "Y", "()Lcom/jingdong/app/mall/home/floor/common/f;", "", "b0", "()V", "", "xOffset", "a0", "(I)V", "velocityX", "U", "(I)I", "Ljava/util/concurrent/atomic/AtomicBoolean;", JshopConst.JSHOP_PROMOTIO_Y, "Ljava/util/concurrent/atomic/AtomicBoolean;", "reportScroll", "Landroid/content/Context;", AnnoConst.Constructor_Context, "Lcom/jingdong/app/mall/home/category/adapter/CaAdapter;", "adapter", "<init>", "(Landroid/content/Context;Lcom/jingdong/app/mall/home/category/adapter/CaAdapter;)V", "home_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes4.dex */
public final class CaCouponFloor extends CaRecycleLinearFloor<d> {

    /* renamed from: y  reason: from kotlin metadata */
    private final AtomicBoolean reportScroll;

    public CaCouponFloor(@NotNull Context context, @NotNull CaAdapter caAdapter) {
        super(context, caAdapter);
        this.reportScroll = new AtomicBoolean(true);
    }

    @Override // com.jingdong.app.mall.home.category.floor.CaRecycleLinearFloor
    protected int U(int velocityX) {
        return velocityX >> 1;
    }

    @Override // com.jingdong.app.mall.home.category.floor.CaRecycleLinearFloor
    @NotNull
    public f Y() {
        getMRecycleView().setClipToPadding(false);
        f fVar = new f(-1, -1);
        fVar.K(new Rect(12, 0, 12, 0));
        return fVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.category.floor.CaRecycleLinearFloor
    public void a0(int xOffset) {
        c e2;
        super.a0(xOffset);
        if (n() == 0 || xOffset <= 0 || !this.reportScroll.getAndSet(false)) {
            return;
        }
        d dVar = (d) n();
        b.c("Category_Coupon_Slide", String.valueOf((dVar == null || (e2 = dVar.e()) == null) ? null : e2.h()));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.category.floor.CaRecycleLinearFloor
    public void b0() {
        super.b0();
        this.reportScroll.set(true);
    }
}
