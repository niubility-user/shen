package com.jingdong.common.widget.custom.liveplayer.decoration;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.common.R;
import com.jingdong.common.widget.custom.liveplayer.bean.LiveSmallWindowBean;
import com.jingdong.common.widget.custom.livewidget.util.BorderDrawable;
import com.jingdong.sdk.utils.DPIUtil;

/* loaded from: classes12.dex */
public class SimpleDecoration implements IDecorator {
    private Context mContext;
    private View mDecorateView;
    private LiveSmallWindowBean mLiveSmallWindowBean;

    public SimpleDecoration(Context context, LiveSmallWindowBean liveSmallWindowBean) {
        this.mContext = context;
        this.mLiveSmallWindowBean = liveSmallWindowBean;
    }

    private void showSimpleStyle() {
        String str;
        int i2;
        boolean equals = "1".equals(this.mLiveSmallWindowBean.screen);
        try {
            str = JDMobileConfig.getInstance().getConfig("liveroom", "config", "smallwindowUseCusBorderDrawable");
        } catch (Exception e2) {
            e2.printStackTrace();
            str = "0";
        }
        int dip2px = DPIUtil.dip2px(2.0f);
        int[] iArr = {Color.parseColor("#FF4A39"), Color.parseColor("#FF5575")};
        BorderDrawable borderDrawable = new BorderDrawable(dip2px);
        borderDrawable.setColors(iArr);
        borderDrawable.setCornerRadius(DPIUtil.dip2px(12.0f));
        if ("1".equals(str)) {
            this.mDecorateView.setBackground(borderDrawable);
            return;
        }
        View view = this.mDecorateView;
        if (equals) {
            i2 = R.drawable.live_simple_landscape_bg;
        } else {
            i2 = R.drawable.live_simple_portrait_bg;
        }
        view.setBackgroundResource(i2);
    }

    @Override // com.jingdong.common.widget.custom.liveplayer.decoration.IDecorator
    public void setDecorateView(RelativeLayout relativeLayout, RelativeLayout relativeLayout2) {
        if (relativeLayout2 == null || relativeLayout == null || this.mLiveSmallWindowBean == null) {
            return;
        }
        View findViewById = relativeLayout.findViewById(R.id.lay_smallwindow_simple);
        this.mDecorateView = findViewById;
        if (findViewById == null) {
            this.mDecorateView = LayoutInflater.from(this.mContext).inflate(R.layout.layout_smallwindow_simple_decoration, (ViewGroup) null);
            showSimpleStyle();
            relativeLayout.addView(this.mDecorateView, new RelativeLayout.LayoutParams(-1, -1));
            return;
        }
        showSimpleStyle();
    }
}
