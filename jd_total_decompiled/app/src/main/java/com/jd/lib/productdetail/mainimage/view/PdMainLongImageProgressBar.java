package com.jd.lib.productdetail.mainimage.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jd.lib.productdetail.mainimage.R;

/* loaded from: classes15.dex */
public class PdMainLongImageProgressBar extends RelativeLayout {
    private int barWidth;
    private ImageView progressBar;
    private View rootView;
    private int totalNum;
    private int viewWidth;

    public PdMainLongImageProgressBar(Context context) {
        super(context);
        this.barWidth = PDUtils.dip2px(80.0f);
        initView(context);
    }

    private float getBarXByIndex(int i2) {
        return this.barWidth * i2;
    }

    private void initView(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.lib_pd_mainimage_big_image_long_progress_bar, this);
        this.rootView = inflate;
        this.progressBar = (ImageView) inflate.findViewById(R.id.detail_long_image_pager_progress_bar);
    }

    public void isLongImageBar(boolean z) {
        if (z) {
            return;
        }
        setBackgroundResource(R.drawable.lib_pd_image_normal_progress_bg);
        this.progressBar.setBackgroundResource(R.drawable.lib_pd_image_normal_progress_bar_bg);
    }

    public void onSelectIndex(int i2) {
        this.progressBar.setX(getBarXByIndex(i2));
    }

    public void setProgressNum(int i2, int i3) {
        this.totalNum = i2;
        this.barWidth = i3 / i2;
        ViewGroup.LayoutParams layoutParams = this.progressBar.getLayoutParams();
        layoutParams.width = this.barWidth;
        this.progressBar.setLayoutParams(layoutParams);
    }

    public PdMainLongImageProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.barWidth = PDUtils.dip2px(80.0f);
        initView(context);
    }
}
