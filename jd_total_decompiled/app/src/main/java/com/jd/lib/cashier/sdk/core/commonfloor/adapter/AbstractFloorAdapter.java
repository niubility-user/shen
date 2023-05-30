package com.jd.lib.cashier.sdk.core.commonfloor.adapter;

import android.view.View;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor;
import com.jd.lib.cashier.sdk.d.a.b.b;
import com.jd.lib.cashier.sdk.d.a.c.a;
import java.util.List;

/* loaded from: classes14.dex */
public abstract class AbstractFloorAdapter<FloorShareData extends a> extends BaseMultiItemQuickAdapter<com.jd.lib.cashier.sdk.d.a.e.a, RecyclerView.ViewHolder> implements com.jd.lib.cashier.sdk.d.d.a {
    private com.jd.lib.cashier.sdk.d.a.b.a<FloorShareData> A;
    private FragmentActivity x;
    private FloorShareData y;
    private b z;

    private AbstractFloorAdapter(List<com.jd.lib.cashier.sdk.d.a.e.a> list) {
        super(list);
    }

    private void q() {
        b bVar = this.z;
        if (bVar != null) {
            h(bVar.a());
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.adapter.BaseQuickAdapter
    /* renamed from: l */
    public void convert(RecyclerView.ViewHolder viewHolder, com.jd.lib.cashier.sdk.d.a.e.a aVar) {
        try {
            if (viewHolder instanceof AbstractFloor) {
                ((AbstractFloor) viewHolder).b(this.y, aVar);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.adapter.BaseQuickAdapter
    /* renamed from: m */
    public AbstractFloor createBaseViewHolder(View view) {
        com.jd.lib.cashier.sdk.d.a.b.a<FloorShareData> aVar = this.A;
        if (aVar == null) {
            return n(view);
        }
        return aVar.a(this.y, view.getId(), view);
    }

    public abstract AbstractFloor n(View view);

    public abstract com.jd.lib.cashier.sdk.d.a.b.a<FloorShareData> o();

    @Override // com.jd.lib.cashier.sdk.d.d.a
    public void onDestroy() {
        if (this.x != null) {
            this.x = null;
        }
        if (this.z != null) {
            this.z = null;
        }
        if (this.A != null) {
            this.A = null;
        }
        FloorShareData floorsharedata = this.y;
        if (floorsharedata != null) {
            floorsharedata.a();
            this.y = null;
        }
    }

    public abstract b p();

    public AbstractFloorAdapter(FragmentActivity fragmentActivity, FloorShareData floorsharedata, List<com.jd.lib.cashier.sdk.d.a.e.a> list) {
        this(list);
        this.x = fragmentActivity;
        this.y = floorsharedata;
        this.A = o();
        this.z = p();
        q();
    }
}
