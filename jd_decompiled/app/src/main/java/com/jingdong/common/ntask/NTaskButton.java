package com.jingdong.common.ntask;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.mylib.R;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.sdk.utils.DPIUtil;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes5.dex */
public final class NTaskButton extends FrameLayout {
    private SimpleDraweeView img;
    private NTaskProgress progress;
    private TextView txt;

    public NTaskButton(@NonNull Context context) {
        super(context);
        init();
    }

    public void init() {
        this.img = new SimpleDraweeView(getContext());
        int widthByDesignValue750 = DPIUtil.getWidthByDesignValue750(getContext(), 52);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(widthByDesignValue750, widthByDesignValue750);
        layoutParams.gravity = 1;
        layoutParams.topMargin = DPIUtil.getWidthByDesignValue750(getContext(), 16);
        addView(this.img, layoutParams);
        this.progress = new NTaskProgress(getContext());
        int widthByDesignValue7502 = DPIUtil.getWidthByDesignValue750(getContext(), 84);
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(widthByDesignValue7502, widthByDesignValue7502);
        layoutParams2.gravity = 1;
        addView(this.progress, layoutParams2);
        this.txt = new TextView(getContext());
        int widthByDesignValue7503 = DPIUtil.getWidthByDesignValue750(getContext(), 100);
        int widthByDesignValue7504 = DPIUtil.getWidthByDesignValue750(getContext(), 34);
        this.txt.setBackgroundResource(R.drawable.bg_ntask);
        this.txt.setGravity(17);
        this.txt.setTextSize(0, DPIUtil.getWidthByDesignValue750(getContext(), 20));
        this.txt.setTextColor(-1);
        FrameLayout.LayoutParams layoutParams3 = new FrameLayout.LayoutParams(widthByDesignValue7503, widthByDesignValue7504);
        layoutParams3.gravity = 80;
        addView(this.txt, layoutParams3);
    }

    public void update(NTaskModel nTaskModel) {
        this.progress.setProgress(nTaskModel.progress);
        JDDisplayImageOptions jDDisplayImageOptions = new JDDisplayImageOptions();
        int i2 = nTaskModel.awardStyle;
        if (i2 == 5) {
            JDImageUtils.displayImage(nTaskModel.img, this.img, jDDisplayImageOptions);
        } else if (i2 == 8) {
            JDImageUtils.displayImage("res:///" + R.drawable.ntask_icon_ticket, this.img, jDDisplayImageOptions);
        } else {
            JDImageUtils.displayImage("res:///" + R.drawable.ntask_icon_bean, this.img, jDDisplayImageOptions);
        }
        this.txt.setText(nTaskModel.title);
        this.txt.setBackgroundResource(nTaskModel.completionFlag == 2 ? R.drawable.bg_ntask_over : R.drawable.bg_ntask);
    }
}
