package com.jd.lib.productdetail.mainimage.holder.cf;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.lib.productdetail.core.entitys.PreferentialRecommendTabItemEntity;
import com.jd.lib.productdetail.core.entitys.SkinRecommendInfoEntity;
import com.jd.lib.productdetail.mainimage.R;
import com.jd.lib.productdetail.mainimage.old.PdMPartsRecommendTabRecyclerView;
import com.jd.lib.productdetail.mainimage.presenter.PdMainImagePresenter;
import com.jingdong.common.deeplinkhelper.DeepLinkCommonHelper;
import com.jingdong.common.unification.dialog.UnBottomDialog;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes15.dex */
public class PdMCFRecommendLayerView extends ConstraintLayout implements View.OnClickListener {

    /* renamed from: g  reason: collision with root package name */
    public Context f4687g;

    /* renamed from: h  reason: collision with root package name */
    public AppCompatTextView f4688h;

    /* renamed from: i  reason: collision with root package name */
    public SimpleDraweeView f4689i;

    /* renamed from: j  reason: collision with root package name */
    public PdMPartsRecommendTabRecyclerView f4690j;

    /* renamed from: k  reason: collision with root package name */
    public RecyclerView f4691k;

    /* renamed from: l  reason: collision with root package name */
    public ConstraintLayout f4692l;

    /* renamed from: m  reason: collision with root package name */
    public View f4693m;

    /* renamed from: n  reason: collision with root package name */
    public TextView f4694n;
    public a o;
    public PdMCFRecommendLayerAdapter p;
    public ConstraintLayout q;
    public SkinRecommendInfoEntity.Data r;
    public PdMainImagePresenter s;

    /* loaded from: classes15.dex */
    public interface a {
    }

    public PdMCFRecommendLayerView(Context context) {
        super(context);
        this.f4687g = context;
        a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i2, PreferentialRecommendTabItemEntity preferentialRecommendTabItemEntity) {
        SkinRecommendInfoEntity.Data data;
        String str;
        if (this.p == null || (data = this.r) == null) {
            return;
        }
        if (i2 == 0) {
            str = data.completeSkusDesc;
        } else {
            List<SkinRecommendInfoEntity.Diagnosis> list = data.diagnosis;
            str = list != null ? list.get(i2 - 1).proposal : "";
        }
        PdMCFRecommendLayerAdapter pdMCFRecommendLayerAdapter = this.p;
        pdMCFRecommendLayerAdapter.b = a(this.r, i2);
        pdMCFRecommendLayerAdapter.f4676c = str;
        pdMCFRecommendLayerAdapter.notifyDataSetChanged();
        this.f4690j.b(i2);
    }

    public final void a() {
        View inflate = LayoutInflater.from(this.f4687g).inflate(R.layout.lib_pd_mainimage_holder_topimage_recommend_dialog_layout, this);
        this.q = (ConstraintLayout) inflate.findViewById(R.id.lib_pd_cf_recommend_dialog_rootview);
        this.f4688h = (AppCompatTextView) inflate.findViewById(R.id.lib_pd_cf_recommend_dialog_title);
        this.f4689i = (SimpleDraweeView) inflate.findViewById(R.id.lib_pd_cf_recommend_dialog_close_btn);
        this.f4690j = (PdMPartsRecommendTabRecyclerView) inflate.findViewById(R.id.lib_pd_cf_recommend_dialog_tab_list);
        this.f4691k = (RecyclerView) inflate.findViewById(R.id.lib_pd_cf_recommend_dialog_sku_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.f4687g);
        linearLayoutManager.setOrientation(1);
        this.f4691k.setLayoutManager(linearLayoutManager);
        this.f4691k.setHasFixedSize(true);
        PdMCFRecommendLayerAdapter pdMCFRecommendLayerAdapter = new PdMCFRecommendLayerAdapter();
        this.p = pdMCFRecommendLayerAdapter;
        this.f4691k.setAdapter(pdMCFRecommendLayerAdapter);
        this.p.d = new c(this);
        this.f4692l = (ConstraintLayout) inflate.findViewById(R.id.lib_pd_cf_recommend_dialog_bottom_layout);
        this.f4693m = inflate.findViewById(R.id.lib_pd_cf_recommend_dialog_bottom_line);
        this.f4694n = (TextView) inflate.findViewById(R.id.lib_pd_cf_recommend_dialog_cart_btn);
        this.f4689i.setOnClickListener(this);
        this.f4694n.setOnClickListener(this);
        this.f4688h.setText(this.f4687g.getString(R.string.lib_pd_image_topimage_cf_layer_title));
        PdMPartsRecommendTabRecyclerView pdMPartsRecommendTabRecyclerView = this.f4690j;
        pdMPartsRecommendTabRecyclerView.f5113m = true;
        pdMPartsRecommendTabRecyclerView.g(new PdMPartsRecommendTabRecyclerView.c() { // from class: com.jd.lib.productdetail.mainimage.holder.cf.a
            @Override // com.jd.lib.productdetail.mainimage.old.PdMPartsRecommendTabRecyclerView.c
            public final void a(int i2, PreferentialRecommendTabItemEntity preferentialRecommendTabItemEntity) {
                PdMCFRecommendLayerView.this.b(i2, preferentialRecommendTabItemEntity);
            }
        });
    }

    public void d(a aVar) {
        this.o = aVar;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        PdMainImagePresenter pdMainImagePresenter;
        UnBottomDialog unBottomDialog;
        int id = view.getId();
        if (id == R.id.lib_pd_cf_recommend_dialog_close_btn) {
            a aVar = this.o;
            if (aVar == null || (pdMainImagePresenter = ((e) aVar).a.f4654n) == null || (unBottomDialog = pdMainImagePresenter.mLayerDialog) == null) {
                return;
            }
            unBottomDialog.dismiss();
        } else if (id == R.id.lib_pd_cf_recommend_dialog_cart_btn) {
            DeepLinkCommonHelper.startShoppingCartActivity(this.f4687g, null, true);
        }
    }

    public PdMCFRecommendLayerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f4687g = context;
        a();
    }

    public final List<SkinRecommendInfoEntity.CompleteSkus> a(SkinRecommendInfoEntity.Data data, int i2) {
        if (data != null) {
            if (i2 == 0) {
                return data.completeSkus;
            }
            Map<String, List<SkinRecommendInfoEntity.CompleteSkus>> map = data.diagnosis.get(i2 - 1).exclusiveSkus;
            ArrayList arrayList = new ArrayList();
            if (map != null) {
                List<SkinRecommendInfoEntity.CompleteSkus> list = map.get("1");
                if (list != null) {
                    arrayList.addAll(list);
                }
                List<SkinRecommendInfoEntity.CompleteSkus> list2 = map.get("2");
                if (list2 != null) {
                    arrayList.addAll(list2);
                }
                List<SkinRecommendInfoEntity.CompleteSkus> list3 = map.get("3");
                if (list3 != null) {
                    arrayList.addAll(list3);
                }
            }
            return arrayList;
        }
        return null;
    }
}
