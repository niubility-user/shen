package com.jingdong.app.mall.bundle.productdetailcard.viewholder;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.bundle.productdetailcard.R;
import com.jingdong.app.mall.bundle.productdetailcard.adapter.PdCardMainImageAdapter;
import com.jingdong.app.mall.bundle.productdetailcard.entity.PdCardFloorInfo;
import com.jingdong.app.mall.bundle.productdetailcard.entity.PdCardMainImageData;
import com.jingdong.sdk.utils.DPIUtil;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public class PdCardMainImageViewHolder extends RecyclerView.ViewHolder implements com.jingdong.app.mall.bundle.productdetailcard.viewholder.a {

    /* renamed from: g  reason: collision with root package name */
    private final RecyclerView f8257g;

    /* renamed from: h  reason: collision with root package name */
    private final PdCardMainImageAdapter f8258h;

    /* renamed from: i  reason: collision with root package name */
    private RecyclerView.ItemDecoration f8259i;

    /* renamed from: j  reason: collision with root package name */
    private int f8260j;

    /* renamed from: k  reason: collision with root package name */
    private int f8261k;

    /* loaded from: classes3.dex */
    class a extends RecyclerView.ItemDecoration {
        a() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
            if (recyclerView != null) {
                if (recyclerView.getAdapter() == null || recyclerView.getChildAdapterPosition(view) != recyclerView.getAdapter().getItemCount() - 1) {
                    rect.right = PdCardMainImageViewHolder.this.f8260j;
                } else {
                    rect.right = PdCardMainImageViewHolder.this.f8261k;
                }
                if (recyclerView.getChildAdapterPosition(view) == 0) {
                    rect.left = PdCardMainImageViewHolder.this.f8261k;
                } else {
                    rect.left = PdCardMainImageViewHolder.this.f8260j;
                }
            }
        }
    }

    public PdCardMainImageViewHolder(Context context, @NonNull View view) {
        super(view);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.pd_card_main_image_recycler);
        this.f8257g = recyclerView;
        PdCardMainImageAdapter pdCardMainImageAdapter = new PdCardMainImageAdapter(context);
        this.f8258h = pdCardMainImageAdapter;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(0);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(pdCardMainImageAdapter);
        this.f8260j = DPIUtil.dip2px(9.0f);
        this.f8261k = DPIUtil.dip2px(18.0f);
        this.f8259i = new a();
    }

    @Override // com.jingdong.app.mall.bundle.productdetailcard.viewholder.a
    public void a(PdCardFloorInfo pdCardFloorInfo) {
        PdCardMainImageData pdCardMainImageData;
        if (pdCardFloorInfo != null) {
            Object obj = pdCardFloorInfo.data;
            if (!(obj instanceof JDJSONObject) || (pdCardMainImageData = (PdCardMainImageData) JDJSON.parseObject(((JDJSONObject) obj).toJSONString(), PdCardMainImageData.class)) == null) {
                return;
            }
            ArrayList<String> arrayList = pdCardMainImageData.imageInfo;
            this.f8257g.removeItemDecoration(this.f8259i);
            this.f8257g.addItemDecoration(this.f8259i);
            this.f8258h.a(arrayList);
        }
    }
}
