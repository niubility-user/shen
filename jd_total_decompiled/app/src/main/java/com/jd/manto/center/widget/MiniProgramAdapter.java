package com.jd.manto.center.widget;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.jd.manto.center.R;
import com.jd.manto.center.k.h;
import com.jd.manto.center.model.MiniProgramListBean;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.display.JDFadeInBitmapDisplayer;
import com.jingdong.common.baseRecycleAdapter.BaseMultiItemQuickAdapter;
import com.jingdong.common.baseRecycleAdapter.BaseQuickAdapter;
import com.jingdong.common.baseRecycleAdapter.BaseViewHolder;
import com.jingdong.common.ui.AlphaSimpleDraweeView;
import com.jingdong.common.utils.JDImageUtils;
import java.util.List;

/* loaded from: classes17.dex */
public class MiniProgramAdapter extends BaseMultiItemQuickAdapter<MiniProgramListBean.MiniProgramBean, BaseViewHolder> {
    private Context a;
    private com.jd.manto.center.g.a b;

    /* loaded from: classes17.dex */
    class a implements BaseQuickAdapter.RequestLoadMoreListener {
        a() {
        }

        @Override // com.jingdong.common.baseRecycleAdapter.BaseQuickAdapter.RequestLoadMoreListener
        public void onLoadMoreRequested() {
            if (MiniProgramAdapter.this.b != null) {
                MiniProgramAdapter.this.b.k();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class b implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ MiniProgramListBean.MiniProgramBean f6578g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ int f6579h;

        b(MiniProgramListBean.MiniProgramBean miniProgramBean, int i2) {
            this.f6578g = miniProgramBean;
            this.f6579h = i2;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (MiniProgramAdapter.this.b != null) {
                Context context = MiniProgramAdapter.this.a;
                MiniProgramListBean.MiniProgramBean miniProgramBean = this.f6578g;
                com.jd.manto.center.h.b.m(context, miniProgramBean.appId, miniProgramBean.appName, String.valueOf(this.f6579h));
                MiniProgramAdapter.this.b.b(this.f6578g);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public static class c extends BaseViewHolder {
        private AlphaSimpleDraweeView a;
        private ImageView b;

        /* renamed from: c  reason: collision with root package name */
        private TextView f6581c;
        private TextView d;

        public c(View view) {
            super(view);
            this.a = (AlphaSimpleDraweeView) view.findViewById(R.id.image_miniprogram_logo);
            this.b = (ImageView) view.findViewById(R.id.iv_trial_icon);
            this.f6581c = (TextView) view.findViewById(R.id.txt_miniprogram_name);
            this.d = (TextView) view.findViewById(R.id.txt_miniprogram_des);
        }
    }

    public MiniProgramAdapter(Context context, List<MiniProgramListBean.MiniProgramBean> list) {
        super(list);
        this.a = context;
        addItemType(16, R.layout.manto_center_miniprogram_item);
        setLoadMoreView(new f());
        setHasStableIds(true);
        setEnableLoadMore(true);
        setOnLoadMoreListener(new a());
    }

    @Override // com.jingdong.common.baseRecycleAdapter.BaseQuickAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<T> list = this.mData;
        if (list == 0 || list.size() <= 0) {
            return 0;
        }
        return this.mData.size();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.baseRecycleAdapter.BaseQuickAdapter
    /* renamed from: l  reason: merged with bridge method [inline-methods] */
    public void convert(BaseViewHolder baseViewHolder, MiniProgramListBean.MiniProgramBean miniProgramBean) {
        if (baseViewHolder == null || miniProgramBean == null) {
            return;
        }
        int adapterPosition = baseViewHolder.getAdapterPosition();
        if (baseViewHolder instanceof c) {
            c cVar = (c) baseViewHolder;
            JDImageUtils.displayImage(miniProgramBean.logoUrl, cVar.a, new JDDisplayImageOptions().setPlaceholder(17).displayer(new JDFadeInBitmapDisplayer(300)));
            if (TextUtils.equals(miniProgramBean.appType, "2")) {
                h.l(cVar.b);
            } else {
                h.b(cVar.b);
            }
            h.i(cVar.f6581c, miniProgramBean.appName);
            h.i(cVar.d, miniProgramBean.desc);
            baseViewHolder.itemView.setOnClickListener(new b(miniProgramBean, adapterPosition));
        }
    }

    public void m(com.jd.manto.center.g.a aVar) {
        this.b = aVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.baseRecycleAdapter.BaseQuickAdapter
    public BaseViewHolder createBaseViewHolder(View view) {
        return new BaseViewHolder(view);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.baseRecycleAdapter.BaseMultiItemQuickAdapter, com.jingdong.common.baseRecycleAdapter.BaseQuickAdapter
    public BaseViewHolder onCreateDefViewHolder(ViewGroup viewGroup, int i2) {
        if (i2 != 5) {
            if (i2 != 16) {
                return new BaseViewHolder(new View(this.a));
            }
            return new c(getItemView(R.layout.manto_center_miniprogram_item, viewGroup));
        }
        return new BaseViewHolder(getItemView(R.layout.manto_center_recent_no_more, viewGroup));
    }
}
