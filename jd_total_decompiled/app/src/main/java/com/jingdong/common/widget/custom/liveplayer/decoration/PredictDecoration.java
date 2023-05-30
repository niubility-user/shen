package com.jingdong.common.widget.custom.liveplayer.decoration;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jingdong.common.R;
import com.jingdong.common.jump.OpenAppJumpBuilder;
import com.jingdong.common.widget.custom.liveplayer.bean.LiveSmallWindowBean;
import com.jingdong.common.widget.custom.liveplayer.videosmallwindow.SmallWindow;
import com.jingdong.common.widget.custom.liveplayer.videosmallwindow.StandaloneSmallWindowManager;
import java.text.SimpleDateFormat;

/* loaded from: classes12.dex */
public class PredictDecoration implements IDecorator {
    private Context mContext;
    private LiveSmallWindowBean mLiveSmallWindowBean;
    private View mPredictDecorateView;

    public PredictDecoration(Context context, LiveSmallWindowBean liveSmallWindowBean) {
        this.mContext = context;
        this.mLiveSmallWindowBean = liveSmallWindowBean;
    }

    public void removeAndAdjustPlayView(RelativeLayout relativeLayout) {
        SmallWindow.Holder uIHolder = StandaloneSmallWindowManager.getInstance().getUIHolder();
        if (uIHolder != null) {
            relativeLayout.removeView(uIHolder.mute);
            relativeLayout.removeView(uIHolder.playingStatusContainer);
            FrameLayout frameLayout = uIHolder.videoContent;
            if (frameLayout != null) {
                frameLayout.removeAllViews();
                uIHolder.videoContent.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
                uIHolder.videoContent.setBackgroundColor(Color.parseColor("#FFFFFF"));
            }
        }
    }

    @Override // com.jingdong.common.widget.custom.liveplayer.decoration.IDecorator
    public void setDecorateView(RelativeLayout relativeLayout, RelativeLayout relativeLayout2) {
        if (relativeLayout2 == null || relativeLayout == null || this.mLiveSmallWindowBean == null) {
            return;
        }
        View findViewById = relativeLayout.findViewById(R.id.lay_smallwindow_predict);
        this.mPredictDecorateView = findViewById;
        if (findViewById == null) {
            this.mPredictDecorateView = LayoutInflater.from(this.mContext).inflate(R.layout.layout_smallwindow_predict_decoration, (ViewGroup) null);
            updatePredict();
            removeAndAdjustPlayView(relativeLayout2);
            relativeLayout.addView(this.mPredictDecorateView, new RelativeLayout.LayoutParams(-1, -1));
            return;
        }
        updatePredict();
    }

    public void updatePredict() {
        ((TextView) this.mPredictDecorateView.findViewById(R.id.tv_predict_time)).setText(new SimpleDateFormat("M\u6708dd\u65e5 HH:mm").format(Long.valueOf(this.mLiveSmallWindowBean.publishTime)));
        ((TextView) this.mPredictDecorateView.findViewById(R.id.tv_predict_type)).setText(this.mLiveSmallWindowBean.authorTagType);
        ((TextView) this.mPredictDecorateView.findViewById(R.id.tv_predict_name)).setText(this.mLiveSmallWindowBean.authorName);
        ((TextView) this.mPredictDecorateView.findViewById(R.id.tv_predict_remind)).setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.widget.custom.liveplayer.decoration.PredictDecoration.1
            {
                PredictDecoration.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                new OpenAppJumpBuilder.Builder(Uri.parse(PredictDecoration.this.mLiveSmallWindowBean.openApp)).build().jump(PredictDecoration.this.mContext);
            }
        });
    }
}
