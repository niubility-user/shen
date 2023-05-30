package com.jingdong.app.mall.bundle.productdetailcard.viewholder;

import android.content.Context;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.bundle.productdetailcard.R;
import com.jingdong.app.mall.bundle.productdetailcard.adapter.PdCardPraiseAdapter;
import com.jingdong.app.mall.bundle.productdetailcard.entity.PdCardFloorInfo;
import com.jingdong.app.mall.bundle.productdetailcard.entity.PdCardPraiseData;
import com.jingdong.sdk.utils.DPIUtil;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public class PdCardPraiseViewHolder extends RecyclerView.ViewHolder implements com.jingdong.app.mall.bundle.productdetailcard.viewholder.a {

    /* renamed from: g  reason: collision with root package name */
    private final RecyclerView f8264g;

    /* renamed from: h  reason: collision with root package name */
    private RecyclerView.ItemDecoration f8265h;

    /* renamed from: i  reason: collision with root package name */
    private int f8266i;

    /* renamed from: j  reason: collision with root package name */
    private final PdCardPraiseAdapter f8267j;

    /* renamed from: k  reason: collision with root package name */
    private final TextView f8268k;

    /* loaded from: classes3.dex */
    class a extends RecyclerView.ItemDecoration {
        a() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
            if (rect != null) {
                rect.right = PdCardPraiseViewHolder.this.f8266i;
            }
        }
    }

    public PdCardPraiseViewHolder(Context context, @NonNull View view) {
        super(view);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.productdetailcard_praise_recycler);
        this.f8264g = recyclerView;
        this.f8268k = (TextView) view.findViewById(R.id.productdetailcard_praise_title);
        PdCardPraiseAdapter pdCardPraiseAdapter = new PdCardPraiseAdapter(context);
        this.f8267j = pdCardPraiseAdapter;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(0);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(pdCardPraiseAdapter);
        this.f8266i = DPIUtil.dip2px(9.0f);
        this.f8265h = new a();
    }

    @Override // com.jingdong.app.mall.bundle.productdetailcard.viewholder.a
    public void a(PdCardFloorInfo pdCardFloorInfo) {
        PdCardPraiseData pdCardPraiseData;
        if (pdCardFloorInfo != null) {
            if (!TextUtils.isEmpty(pdCardFloorInfo.title)) {
                this.f8268k.setText(pdCardFloorInfo.title);
            }
            Object obj = pdCardFloorInfo.data;
            if (!(obj instanceof JDJSONObject) || (pdCardPraiseData = (PdCardPraiseData) JDJSON.parseObject(((JDJSONObject) obj).toJSONString(), PdCardPraiseData.class)) == null) {
                return;
            }
            ArrayList<String> arrayList = pdCardPraiseData.praiseInfo;
            this.f8264g.removeItemDecoration(this.f8265h);
            this.f8264g.addItemDecoration(this.f8265h);
            this.f8267j.a(arrayList);
        }
    }
}
