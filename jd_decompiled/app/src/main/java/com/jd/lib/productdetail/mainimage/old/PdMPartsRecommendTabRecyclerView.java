package com.jd.lib.productdetail.mainimage.old;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.entitys.PreferentialRecommendTabItemEntity;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jd.lib.productdetail.mainimage.R;
import com.jd.lib.productdetail.mainimage.presenter.PdMainImagePresenter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/* loaded from: classes15.dex */
public class PdMPartsRecommendTabRecyclerView extends RecyclerView {

    /* renamed from: n  reason: collision with root package name */
    public static final /* synthetic */ int f5106n = 0;

    /* renamed from: g  reason: collision with root package name */
    public PartsRecommendTabAdapter f5107g;

    /* renamed from: h  reason: collision with root package name */
    public List<PreferentialRecommendTabItemEntity> f5108h;

    /* renamed from: i  reason: collision with root package name */
    public HashSet<String> f5109i;

    /* renamed from: j  reason: collision with root package name */
    public HashSet<String> f5110j;

    /* renamed from: k  reason: collision with root package name */
    public boolean f5111k;

    /* renamed from: l  reason: collision with root package name */
    public PdMainImagePresenter f5112l;

    /* renamed from: m  reason: collision with root package name */
    public boolean f5113m;

    /* loaded from: classes15.dex */
    public static class PartsRecommendTabAdapter extends RecyclerView.Adapter<PartsRecommendTabHolder> {
        public List<PreferentialRecommendTabItemEntity> a;
        public int b = -1;

        /* renamed from: c  reason: collision with root package name */
        public c f5114c;
        public d d;

        /* renamed from: e  reason: collision with root package name */
        public boolean f5115e;

        /* renamed from: f  reason: collision with root package name */
        public boolean f5116f;

        /* loaded from: classes15.dex */
        public class PartsRecommendTabHolder extends RecyclerView.ViewHolder {
            public final TextView a;

            public PartsRecommendTabHolder(@NonNull PartsRecommendTabAdapter partsRecommendTabAdapter, View view) {
                super(view);
                this.a = (TextView) view.findViewById(R.id.lib_pd_parts_recommend_tab_item_txt);
            }
        }

        @NonNull
        public PartsRecommendTabHolder a(@NonNull ViewGroup viewGroup) {
            LayoutInflater from = LayoutInflater.from(viewGroup.getContext());
            if (this.f5115e) {
                return new PartsRecommendTabHolder(this, from.inflate(R.layout.lib_pd_mainimage_holder_cf_recommend_tab_item, viewGroup, false));
            }
            return new PartsRecommendTabHolder(this, from.inflate(R.layout.lib_pd_mainimage_parts_recommend_tab_item, viewGroup, false));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            List<PreferentialRecommendTabItemEntity> list = this.a;
            if (list == null) {
                return 0;
            }
            return list.size();
        }

        public final void h(@NonNull PartsRecommendTabHolder partsRecommendTabHolder, int i2) {
            partsRecommendTabHolder.itemView.setTag(Integer.valueOf(i2));
            partsRecommendTabHolder.itemView.setOnClickListener(new d0(this));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(@NonNull PartsRecommendTabHolder partsRecommendTabHolder, int i2) {
            View view;
            PartsRecommendTabHolder partsRecommendTabHolder2 = partsRecommendTabHolder;
            List<PreferentialRecommendTabItemEntity> list = this.a;
            PreferentialRecommendTabItemEntity preferentialRecommendTabItemEntity = (list == null || i2 >= list.size()) ? null : this.a.get(i2);
            if (preferentialRecommendTabItemEntity == null || partsRecommendTabHolder2 == null || (view = partsRecommendTabHolder2.itemView) == null || view.getContext() == null) {
                return;
            }
            boolean z = i2 == this.b;
            boolean z2 = this.f5116f;
            partsRecommendTabHolder2.a.setText(preferentialRecommendTabItemEntity.title);
            if (z) {
                partsRecommendTabHolder2.a.setSelected(true);
                partsRecommendTabHolder2.a.setTextColor(com.jd.lib.productdetail.mainimage.utils.a.a(partsRecommendTabHolder2.itemView.getContext(), R.color.lib_pd_image_color_FA2C19, z2));
            } else {
                partsRecommendTabHolder2.a.setSelected(false);
                partsRecommendTabHolder2.a.setTextColor(com.jd.lib.productdetail.mainimage.utils.a.a(partsRecommendTabHolder2.itemView.getContext(), R.color.lib_pd_image_color_1A1A1A, z2));
            }
            partsRecommendTabHolder2.a.setBackgroundResource(z2 ? R.drawable.lib_pd_mainimage_parts_recommend_tab_item_dark_bg : R.drawable.lib_pd_mainimage_parts_recommend_tab_item_bg);
            if (partsRecommendTabHolder2.itemView.getLayoutParams() instanceof RecyclerView.LayoutParams) {
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) partsRecommendTabHolder2.itemView.getLayoutParams();
                if (getItemCount() > 0) {
                    ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin = PDUtils.dip2px(0.0f);
                    ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin = PDUtils.dip2px(12.0f);
                    if (i2 == 0) {
                        ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin = PDUtils.dip2px(20.0f);
                        ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin = PDUtils.dip2px(12.0f);
                    } else if (i2 == getItemCount() - 1) {
                        ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin = PDUtils.dip2px(0.0f);
                        ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin = PDUtils.dip2px(20.0f);
                    }
                }
            }
            h(partsRecommendTabHolder2, i2);
            d dVar = this.d;
            if (dVar != null) {
                String str = preferentialRecommendTabItemEntity.tabId;
                a aVar = (a) dVar;
                PdMPartsRecommendTabRecyclerView pdMPartsRecommendTabRecyclerView = PdMPartsRecommendTabRecyclerView.this;
                if (pdMPartsRecommendTabRecyclerView.f5111k) {
                    return;
                }
                pdMPartsRecommendTabRecyclerView.f5109i.add(str);
                PdMPartsRecommendTabRecyclerView.c(PdMPartsRecommendTabRecyclerView.this);
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        @NonNull
        public /* bridge */ /* synthetic */ PartsRecommendTabHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i2) {
            return a(viewGroup);
        }
    }

    /* loaded from: classes15.dex */
    public class a implements d {
        public a() {
        }
    }

    /* loaded from: classes15.dex */
    public class b extends RecyclerView.OnScrollListener {
        public int a = 0;
        public boolean b = false;

        public b() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int i2) {
            LinearLayoutManager linearLayoutManager;
            int findLastVisibleItemPosition;
            super.onScrollStateChanged(recyclerView, i2);
            if (this.a == 0 && i2 == 1) {
                this.b = true;
            } else if (i2 == 0) {
                PdMPartsRecommendTabRecyclerView pdMPartsRecommendTabRecyclerView = PdMPartsRecommendTabRecyclerView.this;
                int i3 = PdMPartsRecommendTabRecyclerView.f5106n;
                if (pdMPartsRecommendTabRecyclerView.getVisibility() == 0 && (pdMPartsRecommendTabRecyclerView.getLayoutManager() instanceof LinearLayoutManager) && (findLastVisibleItemPosition = (linearLayoutManager = (LinearLayoutManager) pdMPartsRecommendTabRecyclerView.getLayoutManager()).findLastVisibleItemPosition()) != -1) {
                    View childAt = pdMPartsRecommendTabRecyclerView.getChildAt(findLastVisibleItemPosition - linearLayoutManager.findFirstVisibleItemPosition());
                    if (childAt.getLeft() + ((childAt.getRight() - childAt.getLeft()) / 2) >= pdMPartsRecommendTabRecyclerView.getWidth() && findLastVisibleItemPosition > 1) {
                        findLastVisibleItemPosition--;
                    }
                    List<PreferentialRecommendTabItemEntity> list = pdMPartsRecommendTabRecyclerView.f5108h;
                    if (list != null && list.size() > findLastVisibleItemPosition) {
                        for (int i4 = 0; i4 <= findLastVisibleItemPosition; i4++) {
                            PreferentialRecommendTabItemEntity preferentialRecommendTabItemEntity = pdMPartsRecommendTabRecyclerView.f5108h.get(i4);
                            if (preferentialRecommendTabItemEntity != null) {
                                pdMPartsRecommendTabRecyclerView.f5109i.add(preferentialRecommendTabItemEntity.tabId);
                            }
                        }
                    }
                }
                PdMPartsRecommendTabRecyclerView.c(PdMPartsRecommendTabRecyclerView.this);
            }
            this.a = i2;
            PdMPartsRecommendTabRecyclerView.this.f5111k = true;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(@NonNull RecyclerView recyclerView, int i2, int i3) {
            super.onScrolled(recyclerView, i2, i3);
            if (!this.b || i2 == 0) {
                return;
            }
            this.b = false;
            PdMPartsRecommendTabRecyclerView.this.f5112l.mtaExposure("Productdetail_AccessoryToastTabSlide", new JDJSONObject().toJSONString());
        }
    }

    /* loaded from: classes15.dex */
    public interface c {
        void a(int i2, PreferentialRecommendTabItemEntity preferentialRecommendTabItemEntity);
    }

    /* loaded from: classes15.dex */
    public interface d {
    }

    public PdMPartsRecommendTabRecyclerView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f5109i = new HashSet<>();
        this.f5110j = new HashSet<>();
        this.f5111k = false;
        this.f5113m = false;
        a();
    }

    public static void c(PdMPartsRecommendTabRecyclerView pdMPartsRecommendTabRecyclerView) {
        ArrayList<String> e2 = pdMPartsRecommendTabRecyclerView.e();
        if (e2 == null || e2.size() <= 0) {
            return;
        }
        JDJSONArray jDJSONArray = new JDJSONArray();
        for (int i2 = 0; i2 < e2.size(); i2++) {
            JDJSONObject jDJSONObject = new JDJSONObject();
            jDJSONObject.put("categoryid", (Object) e2.get(i2));
            jDJSONArray.add(jDJSONObject);
            pdMPartsRecommendTabRecyclerView.f5110j.add(e2.get(i2));
        }
        pdMPartsRecommendTabRecyclerView.f5112l.mtaExposure("Productdetail_AccessoryToastTabExpo", jDJSONArray.toJSONString());
    }

    private ArrayList<String> e() {
        ArrayList<String> arrayList = new ArrayList<>();
        if (this.f5108h == null) {
            return null;
        }
        for (int i2 = 0; i2 < this.f5108h.size(); i2++) {
            String str = this.f5108h.get(i2).tabId;
            if (this.f5109i.contains(str) && !this.f5110j.contains(str)) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    private void i(d dVar) {
        PartsRecommendTabAdapter partsRecommendTabAdapter = this.f5107g;
        if (partsRecommendTabAdapter != null) {
            partsRecommendTabAdapter.d = dVar;
        }
    }

    public final void a() {
        PartsRecommendTabAdapter partsRecommendTabAdapter = new PartsRecommendTabAdapter();
        this.f5107g = partsRecommendTabAdapter;
        setAdapter(partsRecommendTabAdapter);
        setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
        setHasFixedSize(true);
        i(new a());
        addOnScrollListener(new b());
    }

    public void b(int i2) {
        int i3;
        PartsRecommendTabAdapter partsRecommendTabAdapter = this.f5107g;
        if (partsRecommendTabAdapter == null || (i3 = partsRecommendTabAdapter.b) == i2) {
            return;
        }
        if (i3 != -1) {
            partsRecommendTabAdapter.b = -1;
            partsRecommendTabAdapter.notifyItemChanged(i3);
        }
        if (i2 != -1) {
            partsRecommendTabAdapter.b = i2;
            partsRecommendTabAdapter.notifyItemChanged(i2);
        }
    }

    public void f(List<PreferentialRecommendTabItemEntity> list) {
        this.f5108h = list;
        PartsRecommendTabAdapter partsRecommendTabAdapter = this.f5107g;
        if (partsRecommendTabAdapter != null) {
            boolean z = this.f5113m;
            boolean z2 = this.f5112l.getMainImageParams().isDark;
            List<PreferentialRecommendTabItemEntity> list2 = partsRecommendTabAdapter.a;
            if (list2 != null) {
                list2.clear();
            }
            partsRecommendTabAdapter.a = list;
            partsRecommendTabAdapter.f5115e = z;
            partsRecommendTabAdapter.f5116f = z2;
            partsRecommendTabAdapter.notifyDataSetChanged();
        }
    }

    public void g(c cVar) {
        PartsRecommendTabAdapter partsRecommendTabAdapter = this.f5107g;
        if (partsRecommendTabAdapter != null) {
            partsRecommendTabAdapter.f5114c = cVar;
        }
    }

    public void h(PdMainImagePresenter pdMainImagePresenter) {
        this.f5112l = pdMainImagePresenter;
    }

    public PdMPartsRecommendTabRecyclerView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f5109i = new HashSet<>();
        this.f5110j = new HashSet<>();
        this.f5111k = false;
        this.f5113m = false;
        a();
    }
}
