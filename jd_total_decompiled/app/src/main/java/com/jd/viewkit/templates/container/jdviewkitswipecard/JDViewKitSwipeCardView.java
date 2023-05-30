package com.jd.viewkit.templates.container.jdviewkitswipecard;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jd.dynamic.DYConstants;
import com.jd.viewkit.dataSources.model.JDViewKitDataSourceModel;
import com.jd.viewkit.global.GlobalManage;
import com.jd.viewkit.helper.JDViewKitChannelModel;
import com.jd.viewkit.templates.JDViewKitBaseLayout;
import com.jd.viewkit.templates.container.jdviewkitbannerview.indicators.JDBannerIndicatorView;
import com.jd.viewkit.templates.container.jdviewkitbannerview.indicators.JDIndicatorBiluder;
import com.jd.viewkit.templates.container.jdviewkitswipecard.swipecard.CustomSwipeFlingAdapterView;
import com.jd.viewkit.templates.container.jdviewkitswipecard.swipecard.SwipeFlingAdapterView;
import com.jd.viewkit.templates.model.JDViewKitVirtualBannerView.JDViewKitVirtualBannerDotConfig;
import com.jd.viewkit.templates.model.JDViewKitVirtualBannerView.JDViewKitVirtualBannerView;
import com.jd.viewkit.templates.model.JDViewKitVirtualView;
import com.jd.viewkit.templates.view.helper.JDViewKitEventHelper;
import com.jd.viewkit.tool.StringTool;
import java.util.List;
import java.util.Map;

/* loaded from: classes18.dex */
public class JDViewKitSwipeCardView extends JDViewKitBaseLayout<JDViewKitVirtualBannerView> implements SwipeFlingAdapterView.onFlingListener, SwipeFlingAdapterView.OnItemClickListener {
    private static final int VIEW_CHANGE_INTERVAL = 4000;
    private boolean autoPlay;
    private boolean circle;
    private Handler handler;
    private JDBannerIndicatorView indicatorView;
    private int interval;
    private boolean isPause;
    private JDViewKitSwipeCardAdapter mJDViewKitSwipeCardAdapter;
    private CustomSwipeFlingAdapterView mSwipeFlingAdapterView;
    private long token;

    public JDViewKitSwipeCardView(@NonNull Context context) {
        this(context, null, 0);
    }

    private synchronized void autoChangeViewPagerPosition(int i2) {
        if (this.mSwipeFlingAdapterView == null) {
            return;
        }
        this.token = System.currentTimeMillis();
        Message obtain = Message.obtain();
        obtain.obj = Long.valueOf(this.token);
        this.handler.sendMessageDelayed(obtain, i2);
    }

    private void indicatorConfig(List<JDViewKitDataSourceModel> list, boolean z) {
        JDViewKitVirtualBannerDotConfig bannerDotConfig = ((JDViewKitVirtualBannerView) this.virtualView).getBannerDotConfig();
        if (bannerDotConfig != null && bannerDotConfig.isShowDot()) {
            JDIndicatorBiluder jDIndicatorBiluder = new JDIndicatorBiluder();
            jDIndicatorBiluder.setActiveColor(bannerDotConfig.getActiveColor()).setNormalColor(bannerDotConfig.getNormalColor()).setDotType(((JDViewKitVirtualBannerView) this.virtualView).getDotType()).setDotSubType(1).setTotalNum(list.size());
            JDBannerIndicatorView jDBannerIndicatorView = this.indicatorView;
            if (jDBannerIndicatorView == null) {
                this.indicatorView = jDIndicatorBiluder.build(getContext());
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
                if (StringTool.isNotEmpty(bannerDotConfig.getAlign()) && bannerDotConfig.getAlign().equals(DYConstants.DY_CENTER)) {
                    layoutParams.gravity = 1;
                } else {
                    layoutParams.leftMargin = GlobalManage.getInstance().getRealPx(bannerDotConfig.getDotOriginX(), getChannelModel());
                }
                layoutParams.topMargin = GlobalManage.getInstance().getRealPx(bannerDotConfig.getDotOriginY(), getChannelModel());
                addView(this.indicatorView, layoutParams);
                return;
            } else if (z) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) jDBannerIndicatorView.getLayoutParams();
                if (StringTool.isNotEmpty(bannerDotConfig.getAlign()) && bannerDotConfig.getAlign().equals(DYConstants.DY_CENTER) && marginLayoutParams != null && (marginLayoutParams instanceof FrameLayout.LayoutParams)) {
                    ((FrameLayout.LayoutParams) marginLayoutParams).gravity = 1;
                } else {
                    marginLayoutParams.leftMargin = GlobalManage.getInstance().getRealPx(bannerDotConfig.getDotOriginX(), getChannelModel());
                }
                marginLayoutParams.topMargin = GlobalManage.getInstance().getRealPx(bannerDotConfig.getDotOriginY(), getChannelModel());
                this.indicatorView.requestLayout();
                return;
            } else {
                return;
            }
        }
        View view = this.indicatorView;
        if (view != null) {
            removeView(view);
        }
    }

    private void init(Context context) {
        CustomSwipeFlingAdapterView customSwipeFlingAdapterView = new CustomSwipeFlingAdapterView(context);
        this.mSwipeFlingAdapterView = customSwipeFlingAdapterView;
        addView(customSwipeFlingAdapterView);
        this.mSwipeFlingAdapterView.setIsNeedSwipe(true);
        this.mSwipeFlingAdapterView.setFlingListener(this);
        this.mSwipeFlingAdapterView.setOnItemClickListener(this);
        this.mSwipeFlingAdapterView.setMaxVisible(4);
        this.mJDViewKitSwipeCardAdapter = new JDViewKitSwipeCardAdapter();
    }

    private void swipeConfig() {
        if (((JDViewKitVirtualBannerView) this.virtualView).getBannerConfig() != null) {
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(GlobalManage.getInstance().getRealPx(((JDViewKitVirtualBannerView) this.virtualView).getWidth(), getChannelModel()), GlobalManage.getInstance().getRealPx(((JDViewKitVirtualBannerView) this.virtualView).getBannerConfig().getCenterSizeHeigh(), getChannelModel()));
            layoutParams.topMargin = GlobalManage.getInstance().getRealPx(((JDViewKitVirtualBannerView) this.virtualView).getBannerConfig().getMarginTop(), getChannelModel());
            layoutParams.leftMargin = GlobalManage.getInstance().getRealPx(((JDViewKitVirtualBannerView) this.virtualView).getBannerConfig().getMarginLeft(), getChannelModel());
            this.mSwipeFlingAdapterView.setLayoutParams(layoutParams);
            float scale = 1.0f - ((JDViewKitVirtualBannerView) this.virtualView).getBannerConfig().getScale();
            if (scale < 0.0f) {
                scale = 0.0f;
            }
            int centerSizeWidth = (int) ((((JDViewKitVirtualBannerView) this.virtualView).getBannerConfig().getCenterSizeWidth() * scale * 0.5f) + GlobalManage.getInstance().getRealPx(((JDViewKitVirtualBannerView) this.virtualView).getBannerConfig().getSpace(), getChannelModel()));
            int i2 = centerSizeWidth * 2;
            this.mSwipeFlingAdapterView.setOffsetSteps(new int[]{0, centerSizeWidth, i2, i2});
            float f2 = scale * 2.0f;
            this.mSwipeFlingAdapterView.setScaleSteps(new float[]{0.0f, scale, f2, f2});
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.token = -1L;
        } else if (action == 1 || action == 3) {
            autoChangeViewPagerPosition(this.interval);
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // com.jd.viewkit.templates.container.jdviewkitswipecard.swipecard.SwipeFlingAdapterView.onFlingListener
    public void onAdapterAboutToEmpty(int i2) {
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        onResume();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        onPause();
    }

    @Override // com.jd.viewkit.templates.container.jdviewkitswipecard.swipecard.SwipeFlingAdapterView.OnItemClickListener
    public void onItemClicked(MotionEvent motionEvent, View view, Object obj) {
    }

    @Override // com.jd.viewkit.templates.container.jdviewkitswipecard.swipecard.SwipeFlingAdapterView.onFlingListener
    public void onLeftCardExit(Object obj) {
    }

    public void onPause() {
        this.token = System.currentTimeMillis();
        this.isPause = true;
        this.handler.removeCallbacksAndMessages(null);
    }

    public void onResume() {
        this.isPause = false;
        autoChangeViewPagerPosition(this.interval);
    }

    @Override // com.jd.viewkit.templates.container.jdviewkitswipecard.swipecard.SwipeFlingAdapterView.onFlingListener
    public void onRightCardExit(Object obj) {
    }

    @Override // com.jd.viewkit.templates.container.jdviewkitswipecard.swipecard.SwipeFlingAdapterView.onFlingListener
    public void onScroll(float f2, float f3) {
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            onResume();
        } else {
            onPause();
        }
    }

    @Override // com.jd.viewkit.templates.container.jdviewkitswipecard.swipecard.SwipeFlingAdapterView.onFlingListener
    public void removeFirstObjectInAdapter(boolean z) {
        if (z) {
            this.mJDViewKitSwipeCardAdapter.removeFirstToBottom();
        } else {
            this.mJDViewKitSwipeCardAdapter.removeBottomToFirst();
        }
        JDBannerIndicatorView jDBannerIndicatorView = this.indicatorView;
        if (jDBannerIndicatorView != null) {
            jDBannerIndicatorView.update(this.mJDViewKitSwipeCardAdapter.getFirstIndex());
        }
        sendExpo(this.mJDViewKitSwipeCardAdapter.getCurJDViewKitDataSourceModel());
        if (this.autoPlay) {
            autoChangeViewPagerPosition(this.interval);
        }
    }

    public void sendExpo(JDViewKitDataSourceModel jDViewKitDataSourceModel) {
        Map<String, JDViewKitVirtualView> map;
        JDViewKitVirtualView jDViewKitVirtualView;
        if (jDViewKitDataSourceModel == null || (map = this.dslsMap) == null || (jDViewKitVirtualView = map.get(jDViewKitDataSourceModel.getDslId())) == null || jDViewKitVirtualView.getExpoEvent() == null) {
            return;
        }
        JDViewKitEventHelper.sendExpo(jDViewKitVirtualView, jDViewKitVirtualView.getExpoEvent(), jDViewKitDataSourceModel, this);
    }

    @Override // com.jd.viewkit.templates.JDViewKitBaseLayout
    public void setDataSourceModels(List<JDViewKitDataSourceModel> list, boolean z) {
        super.setDataSourceModels(list, z);
        if (list == null || list.size() <= 0) {
            return;
        }
        this.interval = ((JDViewKitVirtualBannerView) this.virtualView).getInterval();
        this.autoPlay = ((JDViewKitVirtualBannerView) this.virtualView).isAutoPlay();
        this.circle = ((JDViewKitVirtualBannerView) this.virtualView).isCircle();
        this.mJDViewKitSwipeCardAdapter.setDslsMap(getDslsMap());
        this.mJDViewKitSwipeCardAdapter.setChannelModel(this.channelModel);
        this.mJDViewKitSwipeCardAdapter.setConfig(((JDViewKitVirtualBannerView) this.virtualView).getBannerConfig());
        this.mJDViewKitSwipeCardAdapter.setDatas(getDataSourceModels());
        this.mSwipeFlingAdapterView.setAdapter(this.mJDViewKitSwipeCardAdapter);
        swipeConfig();
        indicatorConfig(list, z);
        if (!StringTool.isEmpty(((JDViewKitVirtualBannerView) this.virtualView).getHidden())) {
            setVisibility(8);
        } else {
            sendExpo(this.mJDViewKitSwipeCardAdapter.getCurJDViewKitDataSourceModel());
        }
    }

    public JDViewKitSwipeCardView(@NonNull Context context, @NonNull JDViewKitChannelModel jDViewKitChannelModel) {
        this(context, null, 0);
        this.channelModel = jDViewKitChannelModel;
    }

    public JDViewKitSwipeCardView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public JDViewKitSwipeCardView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.interval = 4000;
        this.autoPlay = false;
        this.circle = false;
        this.token = -1L;
        this.handler = new Handler(Looper.getMainLooper()) { // from class: com.jd.viewkit.templates.container.jdviewkitswipecard.JDViewKitSwipeCardView.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (!JDViewKitSwipeCardView.this.isPause && JDViewKitSwipeCardView.this.autoPlay && JDViewKitSwipeCardView.this.mSwipeFlingAdapterView != null && JDViewKitSwipeCardView.this.mSwipeFlingAdapterView.getChildCount() > 1 && JDViewKitSwipeCardView.this.mSwipeFlingAdapterView.getAdapter() != null) {
                    try {
                        if (JDViewKitSwipeCardView.this.token - ((Long) message.obj).longValue() != 0) {
                            return;
                        }
                        JDViewKitSwipeCardView.this.mSwipeFlingAdapterView.swipeLeft();
                    } catch (Exception unused) {
                    }
                }
            }
        };
        init(context);
    }
}
