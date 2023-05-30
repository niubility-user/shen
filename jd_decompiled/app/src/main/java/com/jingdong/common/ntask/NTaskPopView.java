package com.jingdong.common.ntask;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.mylib.R;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import com.jingdong.sdk.utils.DPIUtil;

/* loaded from: classes5.dex */
final class NTaskPopView extends LinearLayout {
    private Handler handler;
    private TextView hintTv;
    private NTaskStateListener mListener;
    private Runnable task;

    public NTaskPopView(@NonNull Context context) {
        super(context);
        this.task = new Runnable() { // from class: com.jingdong.common.ntask.NTaskPopView.1
            @Override // java.lang.Runnable
            public void run() {
                String str = "NTaskPopView this:" + NTaskPopView.this.hashCode() + " popOverTime";
                NTaskPopView.this.setVisibility(8);
                if (NTaskPopView.this.mListener != null) {
                    NTaskPopView.this.mListener.popOverTimeClose();
                }
            }
        };
        init();
    }

    public void init() {
        this.handler = new Handler(Looper.getMainLooper());
        setOrientation(0);
        this.hintTv = new TextView(getContext());
        int widthByDesignValue750 = DPIUtil.getWidthByDesignValue750(getContext(), 120);
        int widthByDesignValue7502 = DPIUtil.getWidthByDesignValue750(getContext(), 100);
        this.hintTv.setBackgroundResource(R.drawable.ntask_bg_pop);
        this.hintTv.setGravity(17);
        this.hintTv.setTextSize(0, DPIUtil.getWidthByDesignValue750(getContext(), 22));
        this.hintTv.setTextColor(-1);
        this.hintTv.setMaxLines(2);
        this.hintTv.setEllipsize(TextUtils.TruncateAt.END);
        addView(this.hintTv, new LinearLayout.LayoutParams(widthByDesignValue750, widthByDesignValue7502));
        SimpleDraweeView simpleDraweeView = new SimpleDraweeView(getContext());
        simpleDraweeView.setImageResource(R.drawable.ntask_icon_pop_three);
        simpleDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(DPIUtil.getWidthByDesignValue750(getContext(), 14), DPIUtil.getWidthByDesignValue750(getContext(), 28));
        layoutParams.gravity = 16;
        addView(simpleDraweeView, layoutParams);
        setVisibility(8);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        String str = "NTaskPopView this:" + hashCode() + " onDetachedFromWindow";
        super.onDetachedFromWindow();
        this.handler.removeCallbacks(this.task);
    }

    public void update(NTaskModel nTaskModel, NTaskStateListener nTaskStateListener) {
        String str = "NTaskPopView this:" + hashCode() + " update";
        this.mListener = nTaskStateListener;
        this.handler.removeCallbacks(this.task);
        setVisibility(0);
        this.hintTv.setText(TextUtils.isEmpty(nTaskModel.hint) ? "\u505a\u4efb\u52a1\n\u8d62\u5927\u5956" : nTaskModel.hint);
        this.handler.postDelayed(this.task, Final.FIVE_SECOND);
    }
}
