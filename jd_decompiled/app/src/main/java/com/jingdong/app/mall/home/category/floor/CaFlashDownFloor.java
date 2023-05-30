package com.jingdong.app.mall.home.category.floor;

import android.content.Context;
import android.graphics.Rect;
import androidx.recyclerview.widget.PagerSnapHelper;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.app.mall.home.category.adapter.CaAdapter;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.n.g.h;
import com.jingdong.app.mall.home.n.g.u.e;
import com.jingdong.app.mall.home.n.g.v.b;
import com.jingdong.app.mall.home.n.g.v.c;
import com.jingdong.app.mall.home.n.g.x.e;
import com.jingdong.app.mall.home.n.g.x.l;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.constant.JshopConst;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\u0012\u0006\u0010&\u001a\u00020%\u0012\u0006\u0010(\u001a\u00020'\u00a2\u0006\u0004\b)\u0010*J\u0017\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0003H\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u001f\u0010\f\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\nH\u0016\u00a2\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000e\u001a\u00020\u0005H\u0014\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u0010H\u0014\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0015\u001a\u00020\u0014H\u0016\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u0017\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u0018\u0010\u0019J/\u0010\u001f\u001a\u00020\u00052\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u00102\u0006\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020\u0010H\u0014\u00a2\u0006\u0004\b\u001f\u0010 R\u0016\u0010$\u001a\u00020!8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\"\u0010#\u00a8\u0006+"}, d2 = {"Lcom/jingdong/app/mall/home/category/floor/CaFlashDownFloor;", "Lcom/jingdong/app/mall/home/category/floor/CaRecycleLinearFloor;", "Lcom/jingdong/app/mall/home/n/g/h;", "", "eventId", "", "d0", "(Ljava/lang/String;)V", "Lcom/jingdong/app/mall/home/n/g/x/l;", "moreItem", "", "isClick", "Z", "(Lcom/jingdong/app/mall/home/n/g/x/l;Z)V", "b0", "()V", "", "xOffset", "a0", "(I)V", "Lcom/jingdong/app/mall/home/floor/common/f;", "Y", "()Lcom/jingdong/app/mall/home/floor/common/f;", CartConstant.KEY_VENDOR_ITEM, "c0", "(Lcom/jingdong/app/mall/home/n/g/h;)V", "Lcom/jingdong/app/mall/home/n/g/v/c;", "expoData", "start", "end", "max", "Q", "(Lcom/jingdong/app/mall/home/n/g/v/c;III)V", "Ljava/util/concurrent/atomic/AtomicBoolean;", JshopConst.JSHOP_PROMOTIO_Y, "Ljava/util/concurrent/atomic/AtomicBoolean;", "reportScroll", "Landroid/content/Context;", AnnoConst.Constructor_Context, "Lcom/jingdong/app/mall/home/category/adapter/CaAdapter;", "adapter", "<init>", "(Landroid/content/Context;Lcom/jingdong/app/mall/home/category/adapter/CaAdapter;)V", "home_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes4.dex */
public final class CaFlashDownFloor extends CaRecycleLinearFloor<h> {

    /* renamed from: y  reason: from kotlin metadata */
    private final AtomicBoolean reportScroll;

    public CaFlashDownFloor(@NotNull Context context, @NotNull CaAdapter caAdapter) {
        super(context, caAdapter);
        this.reportScroll = new AtomicBoolean(true);
        new PagerSnapHelper().attachToRecyclerView(getMRecycleView());
        getMRecycleView().setItemViewCacheSize(4);
    }

    private final void d0(String eventId) {
        c e2;
        try {
            h hVar = (h) n();
            b.c(eventId, String.valueOf((hVar == null || (e2 = hVar.e()) == null) ? null : e2.h()));
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    @Override // com.jingdong.app.mall.home.category.floor.CaRecycleLinearFloor
    protected void Q(@NotNull c expoData, int start, int end, int max) {
        while (start <= end && start < max) {
            List<e> V = V();
            if (V == null) {
                Intrinsics.throwNpe();
            }
            e eVar = V.get(start);
            if (eVar instanceof com.jingdong.app.mall.home.n.g.x.e) {
                Iterator<e.a> it = ((com.jingdong.app.mall.home.n.g.x.e) eVar).A().iterator();
                while (it.hasNext()) {
                    expoData.a(it.next().d());
                }
            }
            start++;
        }
    }

    @Override // com.jingdong.app.mall.home.category.floor.CaRecycleLinearFloor
    @NotNull
    public f Y() {
        getMRecycleView().setClipToPadding(false);
        f fVar = new f(-1, -1);
        fVar.K(new Rect(24, 10, 24, 0));
        return fVar;
    }

    @Override // com.jingdong.app.mall.home.category.floor.CaRecycleLinearFloor
    public void Z(@NotNull l moreItem, boolean isClick) {
        super.Z(moreItem, isClick);
        JumpEntity d = moreItem.d();
        if (d != null) {
            com.jingdong.app.mall.home.n.h.b.a(getContext(), d);
            d0("Category_Flashbuy_Activitmore");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.category.floor.CaRecycleLinearFloor
    public void a0(int xOffset) {
        super.a0(xOffset);
        if (xOffset <= 0 || !this.reportScroll.getAndSet(false)) {
            return;
        }
        d0("Category_Flashbuy_Slide");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.category.floor.CaRecycleLinearFloor
    public void b0() {
        super.b0();
        this.reportScroll.set(true);
    }

    @Override // com.jingdong.app.mall.home.category.floor.CaRecycleLinearFloor, com.jingdong.app.mall.home.category.floor.base.BaseCaFloor
    /* renamed from: c0  reason: merged with bridge method [inline-methods] */
    public void j(@NotNull h item) {
        super.j(item);
        com.jingdong.app.mall.home.n.g.w.e D = item.D();
        if (D != null) {
            getMTitleView().setPadding(Intrinsics.areEqual("2", D.g()) ? d.d(24) : 0, 0, 0, 0);
        }
    }
}
