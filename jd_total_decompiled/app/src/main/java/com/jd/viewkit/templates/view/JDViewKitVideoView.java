package com.jd.viewkit.templates.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.viewkit.dataSources.model.JDViewKitDataSourceModel;
import com.jd.viewkit.global.GlobalManage;
import com.jd.viewkit.helper.JDViewKitAutoPlayInterface;
import com.jd.viewkit.helper.JDViewKitChannelModel;
import com.jd.viewkit.jdviewkit.R;
import com.jd.viewkit.templates.JDViewKitBaseLayout;
import com.jd.viewkit.templates.model.JDViewKitVirtualView;
import com.jd.viewkit.templates.model.event.JDViewKitVirtualEvent;
import com.jd.viewkit.templates.model.jdviewkitvirtualvidelview.JDViewKitVirtualPlayerConfig;
import com.jd.viewkit.templates.model.jdviewkitvirtualvidelview.JDViewKitVirtualVideoView;
import com.jd.viewkit.templates.view.helper.JDViewKitEventHelper;
import com.jd.viewkit.thirdinterface.JDViewKitVideoService;
import com.jd.viewkit.thirdinterface.model.JDViewKitImageModel;
import com.jd.viewkit.thirdinterface.model.JDViewKitMtaModel;
import com.jd.viewkit.thirdinterface.model.JDViewKitVideoModel;
import com.jd.viewkit.tool.ExpressionParserTool;
import com.jd.viewkit.tool.JSONTool;
import com.jd.viewkit.tool.StringTool;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.Map;

/* loaded from: classes18.dex */
public class JDViewKitVideoView extends JDViewKitAbsoluteLayout {
    private LinearLayout buttonLayout;
    private JDViewKitVirtualPlayerConfig mJdViewKitVirtualPlayerConfig;
    private Handler mainHandler;
    private ImageView palyButtonImageView;
    private ImageView palyImageView;
    private TextView timeTextView;
    private View videoView;

    public JDViewKitVideoView(@NonNull Context context) {
        super(context);
        this.mainHandler = new Handler(Looper.getMainLooper());
    }

    private void initButtonLayout() {
        int realPx = GlobalManage.getInstance().getRealPx(this.virtualView.getWidth(), getChannelModel());
        int realPx2 = GlobalManage.getInstance().getRealPx(this.virtualView.getHeigh(), getChannelModel());
        int realPx3 = GlobalManage.getInstance().getRealPx(R2.anim.pop_left_bottom_in, getChannelModel());
        if (this.buttonLayout != null && this.palyImageView != null) {
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(realPx3, realPx3);
            layoutParams.topMargin = (realPx2 - realPx3) / 2;
            layoutParams.leftMargin = (realPx - realPx3) / 2;
            this.buttonLayout.setLayoutParams(layoutParams);
            this.buttonLayout.setVisibility(isShowControl() ? 0 : 8);
            FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(realPx, realPx2);
            layoutParams2.topMargin = 0;
            layoutParams2.leftMargin = 0;
            this.palyImageView.setLayoutParams(layoutParams2);
            return;
        }
        try {
            this.palyImageView = this.virtualView.getServiceModel().getImageService().getThirdImageView(((JDViewKitAbsoluteLayout) this).mContext);
        } catch (Throwable unused) {
        }
        if (this.palyImageView == null) {
            this.palyImageView = new ImageView(((JDViewKitAbsoluteLayout) this).mContext);
        }
        FrameLayout.LayoutParams layoutParams3 = new FrameLayout.LayoutParams(realPx, realPx2);
        layoutParams3.topMargin = 0;
        layoutParams3.leftMargin = 0;
        this.palyImageView.setLayoutParams(layoutParams3);
        addView(this.palyImageView);
        if (isShowControl()) {
            LinearLayout linearLayout = new LinearLayout(((JDViewKitAbsoluteLayout) this).mContext);
            this.buttonLayout = linearLayout;
            linearLayout.setOrientation(1);
            this.buttonLayout.setGravity(17);
            if (Build.VERSION.SDK_INT >= 16) {
                GradientDrawable gradientDrawable = new GradientDrawable();
                gradientDrawable.setColor(Color.argb(178, 0, 0, 0));
                gradientDrawable.setCornerRadius(21.0f);
                this.buttonLayout.setBackground(gradientDrawable);
            } else {
                this.buttonLayout.setBackgroundColor(Color.argb(178, 0, 0, 0));
            }
            FrameLayout.LayoutParams layoutParams4 = new FrameLayout.LayoutParams(realPx3, realPx3);
            layoutParams4.topMargin = (realPx2 - realPx3) / 2;
            layoutParams4.leftMargin = (realPx - realPx3) / 2;
            this.buttonLayout.setLayoutParams(layoutParams4);
            addView(this.buttonLayout);
            int realPx4 = GlobalManage.getInstance().getRealPx(42, getChannelModel());
            int realPx5 = GlobalManage.getInstance().getRealPx(57, getChannelModel());
            ImageView imageView = new ImageView(((JDViewKitAbsoluteLayout) this).mContext);
            this.palyButtonImageView = imageView;
            imageView.setImageResource(R.drawable.jdvk_video_play);
            this.palyButtonImageView.setLayoutParams(new LinearLayout.LayoutParams(realPx4, realPx5));
            this.buttonLayout.addView(this.palyButtonImageView);
            int realPx6 = GlobalManage.getInstance().getRealPx(39, getChannelModel());
            TextView textView = new TextView(((JDViewKitAbsoluteLayout) this).mContext);
            this.timeTextView = textView;
            textView.setTextSize(0, realPx6);
            this.timeTextView.setTextColor(-1);
            this.timeTextView.setText("00:00");
            this.timeTextView.setLines(1);
            this.timeTextView.setEllipsize(TextUtils.TruncateAt.END);
            this.timeTextView.setIncludeFontPadding(false);
            LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(-2, -2);
            layoutParams5.topMargin = GlobalManage.getInstance().getRealPx(20, getChannelModel());
            this.timeTextView.setLayoutParams(layoutParams5);
            this.buttonLayout.addView(this.timeTextView);
        }
        setOnClickListener(new View.OnClickListener() { // from class: com.jd.viewkit.templates.view.JDViewKitVideoView.1
            {
                JDViewKitVideoView.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (JDViewKitVideoView.this.isShowControl()) {
                    JDViewKitVideoView.this.playVideo();
                    return;
                }
                JDViewKitVirtualView jDViewKitVirtualView = JDViewKitVideoView.this.getJDViewKitVirtualView();
                JDViewKitVideoView jDViewKitVideoView = JDViewKitVideoView.this;
                JDViewKitEventHelper.click(jDViewKitVirtualView, jDViewKitVideoView.dataSourceModel, jDViewKitVideoView, jDViewKitVideoView.getChannelModel());
            }
        });
    }

    private void mta() {
        JDViewKitVirtualEvent virtualEventByType;
        JDViewKitDataSourceModel jDViewKitDataSourceModel;
        if (this.virtualView != 0 && this.dataSourceModel != null && (virtualEventByType = getJDViewKitVirtualView().getVirtualEventByType(JDViewKitVirtualEvent.typeClick)) != null && (jDViewKitDataSourceModel = this.dataSourceModel) != null) {
            try {
                Map<String, Object> jumpParams = jDViewKitDataSourceModel.getJumpParams(virtualEventByType.getEventKey());
                if (jumpParams == null) {
                    return;
                }
                this.virtualView.getServiceModel().getMtaService().click(new JDViewKitMtaModel(JSONTool.getJsonString(jumpParams), this.dataSourceModel.getParamsModel()), ((JDViewKitAbsoluteLayout) this).mContext);
            } catch (Throwable unused) {
            }
        }
    }

    public boolean playVideo() {
        T t;
        Map mapByValueRe;
        boolean z;
        JDViewKitVideoService videoService = GlobalManage.getInstance().getVideoService();
        if (videoService == null || (t = this.virtualView) == 0 || this.dataSourceModel == null || (mapByValueRe = ExpressionParserTool.getMapByValueRe(t.getValueRefer(), this.dataSourceModel.getDataMap())) == null) {
            return false;
        }
        JDViewKitVideoModel jDViewKitVideoModel = new JDViewKitVideoModel(mapByValueRe);
        JDViewKitVirtualPlayerConfig jDViewKitVirtualPlayerConfig = this.mJdViewKitVirtualPlayerConfig;
        if (jDViewKitVirtualPlayerConfig != null) {
            z = "0".equals(jDViewKitVirtualPlayerConfig.getDisplayType());
            jDViewKitVideoModel.setDefaultVoiceType(this.mJdViewKitVirtualPlayerConfig.getDefaultVoiceType());
            jDViewKitVideoModel.setIsCircularPlay(this.mJdViewKitVirtualPlayerConfig.getIsCircularPlay());
            jDViewKitVideoModel.setShowControl(this.mJdViewKitVirtualPlayerConfig.getShowControl());
        } else {
            z = false;
        }
        if (z) {
            videoService.jumpToVideoActivity(((JDViewKitAbsoluteLayout) this).mContext, jDViewKitVideoModel);
            mta();
            return false;
        }
        View view = this.videoView;
        if (view == null || view.getParent() == null) {
            View videoView = videoService.getVideoView(jDViewKitVideoModel, ((JDViewKitAbsoluteLayout) this).mContext);
            this.videoView = videoView;
            if (videoView == null) {
                return false;
            }
            ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(GlobalManage.getInstance().getRealPx(this.virtualView.getWidth(), getChannelModel()), GlobalManage.getInstance().getRealPx(this.virtualView.getHeigh(), getChannelModel()));
            marginLayoutParams.topMargin = 0;
            marginLayoutParams.leftMargin = 0;
            this.videoView.setLayoutParams(marginLayoutParams);
            addView(this.videoView);
            mta();
            return true;
        }
        return true;
    }

    private void removeVodeoView() {
        ViewGroup viewGroup;
        View view = this.videoView;
        if (view == null || (viewGroup = (ViewGroup) view.getParent()) == null) {
            return;
        }
        JDViewKitVideoService videoService = GlobalManage.getInstance().getVideoService();
        if (videoService != null) {
            videoService.releasePlayer(this.videoView);
        }
        viewGroup.removeView(this.videoView);
        this.videoView = null;
    }

    private void setTimeTextView(long j2) {
        int i2;
        TextView textView = this.timeTextView;
        if (textView == null) {
            return;
        }
        if (j2 > 0) {
            textView.setVisibility(0);
            long j3 = j2 / 3600;
            int i3 = 59;
            long j4 = 99;
            if (j3 <= 99) {
                if (j3 > 99) {
                    j3 = 99;
                }
                i3 = (int) ((j2 % 3600) / 60);
                i2 = (int) (j2 % 60);
                j4 = j3;
            } else {
                i2 = 59;
            }
            StringBuffer stringBuffer = new StringBuffer("");
            if (j4 > 0) {
                stringBuffer.append(j4 / 10);
                stringBuffer.append(j4 % 10);
                stringBuffer.append(":");
            }
            stringBuffer.append(i3 / 10);
            stringBuffer.append(i3 % 10);
            stringBuffer.append(":");
            stringBuffer.append(i2 / 10);
            stringBuffer.append(i2 % 10);
            this.timeTextView.setText(stringBuffer.toString());
            return;
        }
        textView.setVisibility(8);
    }

    public boolean handleAutoPlay() {
        return playVideo();
    }

    public boolean isShowControl() {
        JDViewKitVirtualPlayerConfig jDViewKitVirtualPlayerConfig = this.mJdViewKitVirtualPlayerConfig;
        return jDViewKitVirtualPlayerConfig == null || jDViewKitVirtualPlayerConfig.isShowControl();
    }

    @Override // com.jd.viewkit.templates.view.JDViewKitAbsoluteLayout, android.view.View.OnClickListener
    public void onClick(View view) {
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeVodeoView();
    }

    @Override // com.jd.viewkit.templates.view.JDViewKitAbsoluteLayout
    public void setDataSourceModel(JDViewKitDataSourceModel jDViewKitDataSourceModel, boolean z) {
        JDViewKitVirtualPlayerConfig jDViewKitVirtualPlayerConfig;
        JDViewKitVirtualPlayerConfig jDViewKitVirtualPlayerConfig2;
        JDViewKitDataSourceModel jDViewKitDataSourceModel2;
        super.setDataSourceModel(jDViewKitDataSourceModel, z);
        if (this.virtualView != 0 && (jDViewKitDataSourceModel2 = this.dataSourceModel) != null && jDViewKitDataSourceModel2.getDataMap() != null) {
            boolean filterValue = JDViewKitBaseLayout.getFilterValue(this.virtualView.isFilter(), this.dataSourceModel.getDataMap());
            this.isFilter = filterValue;
            if (filterValue) {
                return;
            }
        }
        final JDViewKitVideoModel jDViewKitVideoModel = new JDViewKitVideoModel(ExpressionParserTool.getMapByValueRe(this.virtualView.getValueRefer(), jDViewKitDataSourceModel.getDataMap()));
        JDViewKitChannelModel jDViewKitChannelModel = this.channelModel;
        if (jDViewKitChannelModel != null && (jDViewKitChannelModel.getRootView() instanceof JDViewKitAutoPlayInterface) && (jDViewKitVirtualPlayerConfig2 = this.mJdViewKitVirtualPlayerConfig) != null && !"0".equals(jDViewKitVirtualPlayerConfig2.getAutoPlay())) {
            this.channelModel.addVideoView(this.mJdViewKitVirtualPlayerConfig.getAutoPlay(), this);
        }
        this.mainHandler.post(new Runnable() { // from class: com.jd.viewkit.templates.view.JDViewKitVideoView.2
            {
                JDViewKitVideoView.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (!StringTool.isNotEmpty(jDViewKitVideoModel.getImageUrl()) || ((JDViewKitBaseLayout) JDViewKitVideoView.this).virtualView.getServiceModel() == null || ((JDViewKitBaseLayout) JDViewKitVideoView.this).virtualView.getServiceModel().getImageService() == null) {
                    return;
                }
                try {
                    ((JDViewKitBaseLayout) JDViewKitVideoView.this).virtualView.getServiceModel().getImageService().fillImageView(JDViewKitVideoView.this.palyImageView, new JDViewKitImageModel(jDViewKitVideoModel.getImageUrl(), true, 0, 0, JDDarkUtil.COLOR_0000000, ImageView.ScaleType.CENTER_CROP, ImageView.ScaleType.FIT_CENTER));
                } catch (Throwable unused) {
                }
            }
        });
        if (isShowControl() && ((jDViewKitVirtualPlayerConfig = this.mJdViewKitVirtualPlayerConfig) == null || !"0".equals(jDViewKitVirtualPlayerConfig.getShowDuration()))) {
            setTimeTextView(jDViewKitVideoModel.getDuration());
        } else {
            setTimeTextView(0L);
        }
    }

    public void setVirtualView(JDViewKitVirtualVideoView jDViewKitVirtualVideoView) {
        super.setVirtualView((JDViewKitVirtualView) jDViewKitVirtualVideoView);
        setDescendantFocusability(393216);
        this.mJdViewKitVirtualPlayerConfig = jDViewKitVirtualVideoView != null ? jDViewKitVirtualVideoView.getPlayerConfig() : null;
        initButtonLayout();
    }

    public JDViewKitVideoView(@NonNull Context context, @NonNull JDViewKitChannelModel jDViewKitChannelModel) {
        super(context, jDViewKitChannelModel);
        this.mainHandler = new Handler(Looper.getMainLooper());
    }

    public JDViewKitVideoView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mainHandler = new Handler(Looper.getMainLooper());
    }
}
