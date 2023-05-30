package com.jd.manto.center.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.jd.manto.center.R;
import com.jd.manto.center.model.entity.MantoDiscoveryBean;
import com.jd.manto.center.viewholder.BannerViewholder;
import com.jd.manto.center.viewholder.MantoBaseViewholder;
import com.jd.manto.center.viewholder.RecommandViewHolder;
import com.jd.manto.center.viewholder.TitleViewholder;
import com.jingdong.common.baseRecycleAdapter.BaseMultiItemQuickAdapter;
import com.jingdong.common.utils.ImageUtil;
import java.util.List;

/* loaded from: classes17.dex */
public class MantoDiscoveryAdapter extends BaseMultiItemQuickAdapter<MantoDiscoveryBean, MantoBaseViewholder> {
    private Context a;

    public MantoDiscoveryAdapter(Context context, List<MantoDiscoveryBean> list) {
        super(list);
        this.a = context;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.baseRecycleAdapter.BaseQuickAdapter
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public void convert(MantoBaseViewholder mantoBaseViewholder, MantoDiscoveryBean mantoDiscoveryBean) {
        if (mantoBaseViewholder != null) {
            mantoBaseViewholder.b(this.a, mantoDiscoveryBean);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.baseRecycleAdapter.BaseQuickAdapter
    /* renamed from: h  reason: merged with bridge method [inline-methods] */
    public MantoBaseViewholder createBaseViewHolder(View view) {
        return new MantoBaseViewholder(view);
    }

    @Override // com.jingdong.common.baseRecycleAdapter.BaseMultiItemQuickAdapter, com.jingdong.common.baseRecycleAdapter.BaseQuickAdapter
    /* renamed from: l  reason: merged with bridge method [inline-methods] */
    public MantoBaseViewholder onCreateDefViewHolder(ViewGroup viewGroup, int i2) {
        MantoBaseViewholder bannerViewholder;
        if (i2 == 0) {
            bannerViewholder = new BannerViewholder(ImageUtil.inflate(this.a, R.layout.manto_center_discovery_banner_item, viewGroup, false));
        } else if (i2 == 1) {
            bannerViewholder = new RecommandViewHolder(ImageUtil.inflate(this.a, R.layout.manto_center_discovery_selected_recommendation_item, viewGroup, false));
        } else if (i2 == 2) {
            bannerViewholder = new TitleViewholder(ImageUtil.inflate(this.a, R.layout.manto_center_discovery_title_item, viewGroup, false));
        } else if (i2 != 5) {
            return new MantoBaseViewholder(new View(this.a));
        } else {
            bannerViewholder = new MantoBaseViewholder(ImageUtil.inflate(this.a, R.layout.manto_center_bottom_nomore, viewGroup, false));
        }
        return bannerViewholder;
    }
}
