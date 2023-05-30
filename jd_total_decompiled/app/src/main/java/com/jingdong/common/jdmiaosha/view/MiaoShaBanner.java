package com.jingdong.common.jdmiaosha.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.jingdong.app.mall.utils.ui.view.CarouseFigureImagePagerAdapter;
import com.jingdong.common.R;
import com.jingdong.common.jdmiaosha.adapter.MiaoShaBannerAdapter;
import com.jingdong.common.jdmiaosha.entity.MiaoShaBannerEntity;
import com.jingdong.common.jdmiaosha.utils.DarkUtil;
import com.jingdong.common.jdmiaosha.utils.JDMiaoShaUtils;
import com.jingdong.common.jdmiaosha.view.CustomCarouselFigureView;
import com.jingdong.jdsdk.utils.DPIUtil;
import java.util.List;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes5.dex */
public class MiaoShaBanner extends LinearLayout {
    private int bottomMargin;
    private int leftMargin;
    private OnBannerClickedListener mOnBannerClickedListener;
    private OnBannerSelectedListener mPageSelectedListener;
    private int rightMargin;
    private CustomCarouselFigureView topBanner;
    private int topMargin;
    private static final int M_BANNER_CURSOR_WIDTH = DPIUtil.getWidthByDesignValue750(24);
    private static final int M_BANNER_CURSOR_HEIGHT = DPIUtil.getWidthByDesignValue750(6);
    private static final int M_BANNER_CURSOR_SPACE = DPIUtil.getWidthByDesignValue750(6);
    private static final int M_BANNER_LIGHT_RESOURCE = R.drawable.miaosha_banner_indicator_selected;
    private static final int M_BANNER_NORMAL_RESOURCE = R.drawable.miaosha_banner_indicator_unselected;

    /* loaded from: classes5.dex */
    public interface OnBannerClickedListener {
        void onBannerClicked(int i2, MiaoShaBannerEntity miaoShaBannerEntity);
    }

    /* loaded from: classes5.dex */
    public interface OnBannerSelectedListener {
        void onPageSelected(int i2, MiaoShaBannerEntity miaoShaBannerEntity);
    }

    public MiaoShaBanner(Context context) {
        this(context, null);
    }

    private void hideBannerItem() {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) getLayoutParams();
        layoutParams.height = 0;
        layoutParams.topMargin = 0;
        if (this.topBanner != null) {
            removeAllViews();
            this.topBanner = null;
        }
    }

    private void initViews() {
        DarkUtil.registerThemeChangeListener(new DarkUtil.IThemeListener() { // from class: com.jingdong.common.jdmiaosha.view.MiaoShaBanner.1
            @Override // com.jingdong.common.jdmiaosha.utils.DarkUtil.IThemeListener
            public void onChanged(boolean z) {
                if (MiaoShaBanner.this.topBanner != null) {
                    MiaoShaBanner.this.topBanner.postInvalidate();
                }
            }
        });
    }

    private void showBannerItem(final List<MiaoShaBannerEntity> list) {
        OnBannerSelectedListener onBannerSelectedListener;
        if (this.topBanner != null) {
            this.topBanner = null;
            removeAllViews();
        } else if (list != null && 1 == list.size() && (onBannerSelectedListener = this.mPageSelectedListener) != null) {
            onBannerSelectedListener.onPageSelected(0, list.get(0));
        }
        this.topBanner = new CustomCarouselFigureView(getContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.setMargins(this.leftMargin, this.topMargin, this.rightMargin, this.bottomMargin);
        this.topBanner.setLayoutParams(layoutParams);
        JDMiaoShaUtils.setRoundCorner(this.topBanner, com.jingdong.sdk.utils.DPIUtil.dip2px(12.0f));
        this.topBanner.init(getContext(), this, DPIUtil.getWidthByDesignValue720(258), true, true, DPIUtil.dip2px(8.0f), 100);
        this.topBanner.setCursor(M_BANNER_CURSOR_WIDTH, M_BANNER_CURSOR_HEIGHT, M_BANNER_CURSOR_SPACE, M_BANNER_LIGHT_RESOURCE, M_BANNER_NORMAL_RESOURCE);
        this.topBanner.setBannerListener(new CustomCarouselFigureView.BannerListener() { // from class: com.jingdong.common.jdmiaosha.view.MiaoShaBanner.3
            @Override // com.jingdong.common.jdmiaosha.view.CustomCarouselFigureView.BannerListener
            public void attach() {
                MiaoShaBanner.this.topBanner.onResume();
            }

            @Override // com.jingdong.common.jdmiaosha.view.CustomCarouselFigureView.BannerListener
            public void detach() {
                MiaoShaBanner.this.topBanner.onPause();
            }

            @Override // com.jingdong.common.jdmiaosha.view.CustomCarouselFigureView.BannerListener
            public void onPageSelected(int i2) {
                if (MiaoShaBanner.this.mPageSelectedListener != null) {
                    MiaoShaBanner.this.mPageSelectedListener.onPageSelected(i2, (MiaoShaBannerEntity) list.get(i2));
                }
            }
        });
        MiaoShaBannerAdapter miaoShaBannerAdapter = new MiaoShaBannerAdapter(getContext(), list);
        miaoShaBannerAdapter.setOnBannerClickListener(new MiaoShaBannerAdapter.IBannerClickListener() { // from class: com.jingdong.common.jdmiaosha.view.MiaoShaBanner.4
            @Override // com.jingdong.common.jdmiaosha.adapter.MiaoShaBannerAdapter.IBannerClickListener
            public void onBannerClick(int i2, @Nullable MiaoShaBannerEntity miaoShaBannerEntity) {
                if (MiaoShaBanner.this.mOnBannerClickedListener != null) {
                    MiaoShaBanner.this.mOnBannerClickedListener.onBannerClicked(i2, miaoShaBannerEntity);
                }
            }
        });
        this.topBanner.setAdapter(miaoShaBannerAdapter);
        addView(this.topBanner);
    }

    public void onPause() {
        CustomCarouselFigureView customCarouselFigureView = this.topBanner;
        if (customCarouselFigureView != null) {
            customCarouselFigureView.onPause();
        }
    }

    public void onResume() {
        CustomCarouselFigureView customCarouselFigureView = this.topBanner;
        if (customCarouselFigureView != null) {
            customCarouselFigureView.onResume();
        }
    }

    @Override // android.view.View
    public void onWindowVisibilityChanged(int i2) {
        super.onWindowVisibilityChanged(i2);
        CustomCarouselFigureView customCarouselFigureView = this.topBanner;
        if (customCarouselFigureView == null) {
            return;
        }
        if (i2 == 0) {
            customCarouselFigureView.onResume();
        } else if (i2 == 4 || i2 == 8) {
            customCarouselFigureView.onPause();
        }
    }

    public void setOnBannerClickedListener(OnBannerClickedListener onBannerClickedListener) {
        this.mOnBannerClickedListener = onBannerClickedListener;
    }

    public void setOnPageSelectedListener(OnBannerSelectedListener onBannerSelectedListener) {
        this.mPageSelectedListener = onBannerSelectedListener;
    }

    public void update(final List<MiaoShaBannerEntity> list) {
        if (list != null && list.size() >= 1) {
            showBannerItem(list);
            this.topBanner.setAccessibilityImageAdapterListener(new CarouseFigureImagePagerAdapter.IAccessibilityTextListener() { // from class: com.jingdong.common.jdmiaosha.view.MiaoShaBanner.2
                @Override // com.jingdong.app.mall.utils.ui.view.CarouseFigureImagePagerAdapter.IAccessibilityTextListener
                public String getAccessibilityText(int i2) {
                    return ((MiaoShaBannerEntity) list.get(i2)).title;
                }
            });
            return;
        }
        hideBannerItem();
    }

    public MiaoShaBanner(Context context, @androidx.annotation.Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MiaoShaBanner(Context context, @androidx.annotation.Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        initViews();
    }

    public MiaoShaBanner(Context context, int i2, int i3, int i4, int i5) {
        this(context);
        this.leftMargin = i2;
        this.topMargin = i3;
        this.rightMargin = i4;
        this.bottomMargin = i5;
    }
}
