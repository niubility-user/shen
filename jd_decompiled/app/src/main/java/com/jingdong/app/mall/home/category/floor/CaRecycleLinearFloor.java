package com.jingdong.app.mall.home.category.floor;

import android.content.Context;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.category.adapter.CaAdapter;
import com.jingdong.app.mall.home.category.adapter.CaItemAdapter;
import com.jingdong.app.mall.home.category.floor.base.BaseCaEventFloor;
import com.jingdong.app.mall.home.category.floor.base.a;
import com.jingdong.app.mall.home.category.floor.decoration.CaDividerDecoration;
import com.jingdong.app.mall.home.category.view.CaTitleView;
import com.jingdong.app.mall.home.category.widget.CaMoreRecyclerView;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.n.g.u.d;
import com.jingdong.app.mall.home.n.g.u.e;
import com.jingdong.app.mall.home.n.g.v.c;
import com.jingdong.app.mall.home.n.g.x.l;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.constant.JshopConst;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\b\u0012\u0004\u0012\u00028\u00000\u0003B\u0017\u0012\u0006\u0010R\u001a\u00020Q\u0012\u0006\u0010T\u001a\u00020S\u00a2\u0006\u0004\bU\u0010VJ\u000f\u0010\u0005\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\t\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0014\u00a2\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u000b\u001a\u00020\u0004H\u0014\u00a2\u0006\u0004\b\u000b\u0010\u0006J\u0017\u0010\r\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0007H\u0014\u00a2\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u0010\u001a\u00020\u000fH\u0016\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00028\u0000H\u0016\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u001f\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u0017H\u0016\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u000f\u0010\u001b\u001a\u00020\u0017H\u0014\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u000f\u0010\u001d\u001a\u00020\u0004H\u0014\u00a2\u0006\u0004\b\u001d\u0010\u0006J/\u0010#\u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020\u00072\u0006\u0010!\u001a\u00020\u00072\u0006\u0010\"\u001a\u00020\u0007H\u0014\u00a2\u0006\u0004\b#\u0010$R\"\u0010,\u001a\u00020%8\u0004@\u0004X\u0084\u000e\u00a2\u0006\u0012\n\u0004\b&\u0010'\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\"\u00104\u001a\u00020-8\u0004@\u0004X\u0084\u000e\u00a2\u0006\u0012\n\u0004\b.\u0010/\u001a\u0004\b0\u00101\"\u0004\b2\u00103R\u0016\u00107\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b5\u00106R\"\u0010?\u001a\u0002088\u0004@\u0004X\u0084\u000e\u00a2\u0006\u0012\n\u0004\b9\u0010:\u001a\u0004\b;\u0010<\"\u0004\b=\u0010>R*\u0010H\u001a\n\u0012\u0004\u0012\u00020A\u0018\u00010@8\u0004@\u0004X\u0084\u000e\u00a2\u0006\u0012\n\u0004\bB\u0010C\u001a\u0004\bD\u0010E\"\u0004\bF\u0010GR\"\u0010P\u001a\u00020I8\u0004@\u0004X\u0084\u000e\u00a2\u0006\u0012\n\u0004\bJ\u0010K\u001a\u0004\bL\u0010M\"\u0004\bN\u0010O\u00a8\u0006W"}, d2 = {"Lcom/jingdong/app/mall/home/category/floor/CaRecycleLinearFloor;", "Lcom/jingdong/app/mall/home/n/g/u/d;", "M", "Lcom/jingdong/app/mall/home/category/floor/base/BaseCaEventFloor;", "", "T", "()V", "", "velocityX", "U", "(I)I", "b0", "xOffset", "a0", "(I)V", "Lcom/jingdong/app/mall/home/floor/common/f;", "Y", "()Lcom/jingdong/app/mall/home/floor/common/f;", CartConstant.KEY_VENDOR_ITEM, "R", "(Lcom/jingdong/app/mall/home/n/g/u/d;)V", "Lcom/jingdong/app/mall/home/n/g/x/l;", "moreItem", "", "isClick", "Z", "(Lcom/jingdong/app/mall/home/n/g/x/l;Z)V", "S", "()Z", "E", "Lcom/jingdong/app/mall/home/n/g/v/c;", "expoData", "start", "end", "max", "Q", "(Lcom/jingdong/app/mall/home/n/g/v/c;III)V", "Lcom/jingdong/app/mall/home/category/adapter/CaItemAdapter;", "v", "Lcom/jingdong/app/mall/home/category/adapter/CaItemAdapter;", "getMAdapter", "()Lcom/jingdong/app/mall/home/category/adapter/CaItemAdapter;", "setMAdapter", "(Lcom/jingdong/app/mall/home/category/adapter/CaItemAdapter;)V", "mAdapter", "Lcom/jingdong/app/mall/home/category/view/CaTitleView;", JshopConst.JSHOP_PROMOTIO_X, "Lcom/jingdong/app/mall/home/category/view/CaTitleView;", "X", "()Lcom/jingdong/app/mall/home/category/view/CaTitleView;", "setMTitleView", "(Lcom/jingdong/app/mall/home/category/view/CaTitleView;)V", "mTitleView", "t", "Lcom/jingdong/app/mall/home/floor/common/f;", "mRecycleSize", "Lcom/jingdong/app/mall/home/category/widget/CaMoreRecyclerView;", "s", "Lcom/jingdong/app/mall/home/category/widget/CaMoreRecyclerView;", "W", "()Lcom/jingdong/app/mall/home/category/widget/CaMoreRecyclerView;", "setMRecycleView", "(Lcom/jingdong/app/mall/home/category/widget/CaMoreRecyclerView;)V", "mRecycleView", "", "Lcom/jingdong/app/mall/home/n/g/u/e;", JshopConst.JSHOP_PROMOTIO_W, "Ljava/util/List;", "V", "()Ljava/util/List;", "setMBindList", "(Ljava/util/List;)V", "mBindList", "Landroidx/recyclerview/widget/LinearLayoutManager;", "u", "Landroidx/recyclerview/widget/LinearLayoutManager;", "getMLayoutManager", "()Landroidx/recyclerview/widget/LinearLayoutManager;", "setMLayoutManager", "(Landroidx/recyclerview/widget/LinearLayoutManager;)V", "mLayoutManager", "Landroid/content/Context;", AnnoConst.Constructor_Context, "Lcom/jingdong/app/mall/home/category/adapter/CaAdapter;", "adapter", "<init>", "(Landroid/content/Context;Lcom/jingdong/app/mall/home/category/adapter/CaAdapter;)V", "home_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes4.dex */
public class CaRecycleLinearFloor<M extends d> extends BaseCaEventFloor<M> {
    @NotNull

    /* renamed from: s  reason: from kotlin metadata */
    private CaMoreRecyclerView mRecycleView;

    /* renamed from: t  reason: from kotlin metadata */
    private f mRecycleSize;
    @NotNull

    /* renamed from: u  reason: from kotlin metadata */
    private LinearLayoutManager mLayoutManager;
    @NotNull

    /* renamed from: v  reason: from kotlin metadata */
    private CaItemAdapter mAdapter;
    @Nullable

    /* renamed from: w  reason: from kotlin metadata */
    private List<? extends e> mBindList;
    @NotNull

    /* renamed from: x  reason: from kotlin metadata */
    private CaTitleView mTitleView;

    public CaRecycleLinearFloor(@NotNull Context context, @NotNull CaAdapter caAdapter) {
        super(context, caAdapter);
        CaTitleView caTitleView = new CaTitleView(context);
        this.mTitleView = caTitleView;
        caTitleView.setId(R.id.mallfloor_floor_title);
        f fVar = new f(-1, 0);
        CaTitleView caTitleView2 = this.mTitleView;
        addView(caTitleView2, fVar.u(caTitleView2));
        CaMoreRecyclerView caMoreRecyclerView = new CaMoreRecyclerView(context, context) { // from class: com.jingdong.app.mall.home.category.floor.CaRecycleLinearFloor.1
            {
                super(context);
            }

            @Override // com.jingdong.app.mall.home.category.widget.CaMoreRecyclerView
            protected void d(@NotNull l moreItem) {
                CaRecycleLinearFloor.this.Z(moreItem, false);
            }

            @Override // androidx.recyclerview.widget.RecyclerView
            public boolean fling(int velocityX, int velocityY) {
                return super.fling(CaRecycleLinearFloor.this.U(velocityX), velocityY);
            }
        };
        this.mRecycleView = caMoreRecyclerView;
        caMoreRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.jingdong.app.mall.home.category.floor.CaRecycleLinearFloor.2
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(@NotNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == 0) {
                    CaRecycleLinearFloor.this.T();
                    CaRecycleLinearFloor.this.b0();
                }
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(@NotNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                CaRecycleLinearFloor.this.a0(dx);
            }
        });
        this.mRecycleView.setNestedScrollingEnabled(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, context) { // from class: com.jingdong.app.mall.home.category.floor.CaRecycleLinearFloor.3
            {
                super(context);
            }

            @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
            public boolean canScrollHorizontally() {
                return CaRecycleLinearFloor.this.S() && super.canScrollHorizontally();
            }

            @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
            public boolean canScrollVertically() {
                return CaRecycleLinearFloor.this.S() && super.canScrollVertically();
            }
        };
        this.mLayoutManager = linearLayoutManager;
        linearLayoutManager.setItemPrefetchEnabled(false);
        this.mLayoutManager.setAutoMeasureEnabled(true);
        this.mLayoutManager.setOrientation(0);
        this.mRecycleView.setLayoutManager(this.mLayoutManager);
        CaItemAdapter caItemAdapter = new CaItemAdapter(context, this, this.mRecycleView);
        this.mAdapter = caItemAdapter;
        this.mRecycleView.setAdapter(caItemAdapter);
        f Y = Y();
        this.mRecycleSize = Y;
        RelativeLayout.LayoutParams u = Y.u(this.mRecycleView);
        u.addRule(14);
        u.addRule(3, this.mTitleView.getId());
        addView(this.mRecycleView, u);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void T() {
        int findFirstVisibleItemPosition = this.mLayoutManager.findFirstVisibleItemPosition();
        int findLastVisibleItemPosition = this.mLayoutManager.findLastVisibleItemPosition();
        if (this.mBindList == null || n() == 0 || findFirstVisibleItemPosition >= findLastVisibleItemPosition) {
            return;
        }
        List<? extends e> list = this.mBindList;
        if (list == null) {
            Intrinsics.throwNpe();
        }
        int size = list.size();
        d dVar = (d) n();
        c e2 = dVar != null ? dVar.e() : null;
        if (e2 == null || size <= 0) {
            return;
        }
        Q(e2, findFirstVisibleItemPosition % size, findLastVisibleItemPosition % size, size);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.category.floor.base.BaseCaEventFloor
    public void E() {
        super.E();
        T();
    }

    protected void Q(@NotNull c expoData, int start, int end, int max) {
        while (start <= end && start < max) {
            List<? extends e> list = this.mBindList;
            if (list == null) {
                Intrinsics.throwNpe();
            }
            e eVar = list.get(start);
            if (!eVar.t()) {
                expoData.a(eVar.k());
            }
            start++;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.jingdong.app.mall.home.category.floor.base.BaseCaFloor
    /* renamed from: R  reason: merged with bridge method [inline-methods] */
    public void j(@NotNull M item) {
        f.b(this.mTitleView, -1, r());
        f.c(this.mRecycleView, this.mRecycleSize);
        this.mBindList = item.I();
        this.mRecycleView.scrollToPosition(0);
        CaMoreRecyclerView caMoreRecyclerView = this.mRecycleView;
        CaDividerDecoration G = item.G();
        List<? extends e> list = this.mBindList;
        if (list == null) {
            Intrinsics.throwNpe();
        }
        a.a(caMoreRecyclerView, G, list.size());
        this.mAdapter.q(this.mBindList);
        this.mTitleView.a(item.D(), item.i());
    }

    protected boolean S() {
        return true;
    }

    protected int U(int velocityX) {
        return velocityX;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    public final List<e> V() {
        return this.mBindList;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @NotNull
    /* renamed from: W  reason: from getter */
    public final CaMoreRecyclerView getMRecycleView() {
        return this.mRecycleView;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @NotNull
    /* renamed from: X  reason: from getter */
    public final CaTitleView getMTitleView() {
        return this.mTitleView;
    }

    @NotNull
    public f Y() {
        return new f(-1, -1);
    }

    public void Z(@NotNull l moreItem, boolean isClick) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a0(int xOffset) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void b0() {
    }
}
