package com.jingdong.content.component.widget.immersionbanner;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.content.component.R;
import com.jingdong.content.component.entity.ImmersionItemEntity;
import com.jingdong.content.component.widget.immersionbanner.ClickEventConstants;
import com.jingdong.jdsdk.JdSdk;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes12.dex */
public class FaXianIndicatorBannerAdapter extends FaxianTJBannerViewAdapter {
    public static final String TAG = "FaXianIndicatorBannerAdapter";
    private IBannerViewClick iViewClickListener;
    private List<ImmersionItemEntity> mBanners;
    private Context mContext;

    public FaXianIndicatorBannerAdapter(List<ImmersionItemEntity> list, Context context) {
        this.mBanners = new ArrayList();
        this.mBanners = list;
        this.mContext = context;
    }

    @Override // com.jingdong.content.component.widget.immersionbanner.FaxianTJBannerViewAdapter
    public int getItemCount() {
        List<ImmersionItemEntity> list = this.mBanners;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override // com.jingdong.content.component.widget.immersionbanner.FaxianTJBannerViewAdapter
    public View getItemView(final int i2, View view, ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(JdSdk.getInstance().getApplication()).inflate(R.layout.faxain_immersion_indicator_banner_item, (ViewGroup) null);
        final ImmersionItemEntity immersionItemEntity = this.mBanners.get(i2);
        if (immersionItemEntity == null) {
            return inflate;
        }
        inflate.findViewById(R.id.selected_view);
        ((TextView) inflate.findViewById(R.id.tv_select_title)).setText(immersionItemEntity.shortProfit);
        JDDisplayImageOptions jDDisplayImageOptions = new JDDisplayImageOptions();
        jDDisplayImageOptions.setPlaceholder(R.drawable.default_img);
        JDImageUtils.displayImage(immersionItemEntity.logoImg, (SimpleDraweeView) inflate.findViewById(R.id.sdv_selected_img), jDDisplayImageOptions);
        inflate.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.content.component.widget.immersionbanner.FaXianIndicatorBannerAdapter.1
            @Override // android.view.View.OnClickListener
            @SuppressLint({"LongLogTag"})
            public void onClick(View view2) {
                if (FaXianIndicatorBannerAdapter.this.iViewClickListener != null) {
                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put(ClickEventConstants.CLICK_TYPE_KEY, "0");
                    FaXianIndicatorBannerAdapter.this.iViewClickListener.onClick(i2, immersionItemEntity, hashMap, ClickEventConstants.ORGINAL_FROM.INDICAT_BANNER);
                }
            }
        });
        return inflate;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public float getPageWidth(int i2) {
        return 0.2f;
    }

    public void setIViewClickListener(IBannerViewClick iBannerViewClick) {
        this.iViewClickListener = iBannerViewClick;
    }
}
