package com.jingdong.app.mall.home.category.floor;

import android.content.Context;
import android.graphics.Rect;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.app.mall.home.category.adapter.CaAdapter;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.n.g.i;
import com.jingdong.app.mall.home.n.g.v.b;
import com.jingdong.app.mall.home.n.g.v.c;
import com.jingdong.app.mall.home.n.g.x.l;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.jdsdk.constant.JshopConst;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\u0012\u0006\u0010\u001e\u001a\u00020\u001d\u0012\u0006\u0010 \u001a\u00020\u001f\u00a2\u0006\u0004\b!\u0010\"J\u0017\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0003H\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\t\u001a\u00020\bH\u0016\u00a2\u0006\u0004\b\t\u0010\nJ\u001f\u0010\u000f\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\rH\u0016\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0011\u001a\u00020\u0005H\u0014\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u0013H\u0014\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u000f\u0010\u0017\u001a\u00020\rH\u0014\u00a2\u0006\u0004\b\u0017\u0010\u0018R\u0016\u0010\u001c\u001a\u00020\u00198\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001a\u0010\u001b\u00a8\u0006#"}, d2 = {"Lcom/jingdong/app/mall/home/category/floor/CaFlashSaleFloor;", "Lcom/jingdong/app/mall/home/category/floor/CaRecycleLinearFloor;", "Lcom/jingdong/app/mall/home/n/g/i;", "", "eventId", "", "c0", "(Ljava/lang/String;)V", "Lcom/jingdong/app/mall/home/floor/common/f;", "Y", "()Lcom/jingdong/app/mall/home/floor/common/f;", "Lcom/jingdong/app/mall/home/n/g/x/l;", "moreItem", "", "isClick", "Z", "(Lcom/jingdong/app/mall/home/n/g/x/l;Z)V", "b0", "()V", "", "xOffset", "a0", "(I)V", "M", "()Z", "Ljava/util/concurrent/atomic/AtomicBoolean;", JshopConst.JSHOP_PROMOTIO_Y, "Ljava/util/concurrent/atomic/AtomicBoolean;", "reportScroll", "Landroid/content/Context;", AnnoConst.Constructor_Context, "Lcom/jingdong/app/mall/home/category/adapter/CaAdapter;", "adapter", "<init>", "(Landroid/content/Context;Lcom/jingdong/app/mall/home/category/adapter/CaAdapter;)V", "home_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes4.dex */
public final class CaFlashSaleFloor extends CaRecycleLinearFloor<i> {

    /* renamed from: y  reason: from kotlin metadata */
    private final AtomicBoolean reportScroll;

    public CaFlashSaleFloor(@NotNull Context context, @NotNull CaAdapter caAdapter) {
        super(context, caAdapter);
        this.reportScroll = new AtomicBoolean(true);
    }

    private final void c0(String eventId) {
        c e2;
        try {
            i iVar = (i) n();
            b.c(eventId, String.valueOf((iVar == null || (e2 = iVar.e()) == null) ? null : e2.h()));
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    @Override // com.jingdong.app.mall.home.category.floor.base.BaseCaEventFloor
    protected boolean M() {
        return true;
    }

    @Override // com.jingdong.app.mall.home.category.floor.CaRecycleLinearFloor
    @NotNull
    public f Y() {
        getMRecycleView().setClipToPadding(false);
        f fVar = new f(-1, -1);
        fVar.K(new Rect(18, 0, 18, 0));
        return fVar;
    }

    @Override // com.jingdong.app.mall.home.category.floor.CaRecycleLinearFloor
    public void Z(@NotNull l moreItem, boolean isClick) {
        super.Z(moreItem, isClick);
        JumpEntity d = moreItem.d();
        if (d != null) {
            com.jingdong.app.mall.home.n.h.b.a(getContext(), d);
            c0("Category_Main_Seckill_Productmore");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.category.floor.CaRecycleLinearFloor
    public void a0(int xOffset) {
        super.a0(xOffset);
        if (xOffset <= 0 || !this.reportScroll.getAndSet(false)) {
            return;
        }
        c0("Category_Main_Seckill_Slide");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.category.floor.CaRecycleLinearFloor
    public void b0() {
        super.b0();
        this.reportScroll.set(true);
    }
}
