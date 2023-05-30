package com.jd.viewkit.templates.container.jdviewkitdynamicbanner.gradient;

import android.content.Context;
import android.os.Handler;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.annotation.NonNull;
import com.jd.viewkit.dataSources.model.JDViewKitDataSourceModel;
import com.jd.viewkit.global.GlobalManage;
import com.jd.viewkit.helper.JDViewKitChannelModel;
import com.jd.viewkit.templates.JDViewKitBaseLayout;
import com.jd.viewkit.templates.model.JDViewKitVirtualView;
import com.jd.viewkit.templates.model.jdviewkitdynamicbanner.JDViewKitVirtualDynamicBanner;
import com.jd.viewkit.templates.view.JDViewKitAbsoluteLayout;
import com.jd.viewkit.templates.view.factory.JDViewKitViewFactory;
import com.jd.viewkit.templates.view.helper.JDViewKitEventHelper;
import com.jd.viewkit.tool.animator.LinerObjectAnimator;
import java.util.List;
import java.util.Map;

/* loaded from: classes18.dex */
public class JDViewKitGradientDynamicBanner extends JDViewKitBaseLayout<JDViewKitVirtualDynamicBanner> {
    private static final String TAG = "JDViewKitGradientDynami";
    private int contentPage;
    private JDViewKitAbsoluteLayout firstView;
    private int heigh;
    private int interval;
    private boolean isAutoPlay;
    private boolean isStart;
    private LinerObjectAnimator linerObjectAnimator;
    private Handler mHandler;
    private JDViewKitAbsoluteLayout sedView;
    private final Runnable task;

    public JDViewKitGradientDynamicBanner(@NonNull Context context, @NonNull JDViewKitChannelModel jDViewKitChannelModel) {
        super(context, jDViewKitChannelModel);
        this.interval = 3000;
        this.isAutoPlay = false;
        this.mHandler = new Handler();
        this.task = new Runnable() { // from class: com.jd.viewkit.templates.container.jdviewkitdynamicbanner.gradient.JDViewKitGradientDynamicBanner.1
            @Override // java.lang.Runnable
            public void run() {
                if (JDViewKitGradientDynamicBanner.this.isStart && JDViewKitGradientDynamicBanner.this.isAutoPlay) {
                    JDViewKitGradientDynamicBanner.this.startAin();
                    JDViewKitGradientDynamicBanner.this.mHandler.postDelayed(this, JDViewKitGradientDynamicBanner.this.interval);
                }
            }
        };
    }

    static /* synthetic */ int access$708(JDViewKitGradientDynamicBanner jDViewKitGradientDynamicBanner) {
        int i2 = jDViewKitGradientDynamicBanner.contentPage;
        jDViewKitGradientDynamicBanner.contentPage = i2 + 1;
        return i2;
    }

    private void setParentClipChildren() {
        ViewParent parent = getParent();
        if (parent == null || !(parent instanceof ViewGroup)) {
            return;
        }
        ViewGroup viewGroup = (ViewGroup) parent;
        viewGroup.setClipChildren(false);
        viewGroup.setClipToPadding(false);
    }

    private void start() {
        if (this.isStart || !this.isAutoPlay) {
            return;
        }
        this.isStart = true;
        this.mHandler.postDelayed(this.task, this.interval);
    }

    private void stop() {
        this.mHandler.removeCallbacks(this.task);
        this.isStart = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updataData() {
        if (getDataSourceModels() == null || getDataSourceModels().size() <= 1) {
            return;
        }
        JDViewKitDataSourceModel jDViewKitDataSourceModel = getDataSourceModels().get(this.contentPage % getDataSourceModels().size());
        this.firstView.setDataSourceModel(jDViewKitDataSourceModel, false);
        this.sedView.bringToFront();
        JDViewKitAbsoluteLayout jDViewKitAbsoluteLayout = this.firstView;
        this.firstView = this.sedView;
        this.sedView = jDViewKitAbsoluteLayout;
        sendExpo(jDViewKitDataSourceModel);
    }

    private void updataView(int i2, JDViewKitDataSourceModel jDViewKitDataSourceModel, boolean z) {
        if (jDViewKitDataSourceModel == null) {
            return;
        }
        if (i2 == 0) {
            JDViewKitAbsoluteLayout jDViewKitAbsoluteLayout = this.firstView;
            if (jDViewKitAbsoluteLayout == null) {
                JDViewKitAbsoluteLayout view = JDViewKitViewFactory.getView(this.mContext, jDViewKitDataSourceModel, this.dslsMap.get(jDViewKitDataSourceModel.getDslId()), getChannelModel());
                this.firstView = view;
                addView(view);
            } else {
                jDViewKitAbsoluteLayout.setDataSourceModel(jDViewKitDataSourceModel, z);
            }
        } else {
            JDViewKitAbsoluteLayout jDViewKitAbsoluteLayout2 = this.sedView;
            if (jDViewKitAbsoluteLayout2 == null) {
                JDViewKitAbsoluteLayout view2 = JDViewKitViewFactory.getView(this.mContext, jDViewKitDataSourceModel, this.dslsMap.get(jDViewKitDataSourceModel.getDslId()), getChannelModel());
                this.sedView = view2;
                addView(view2);
            } else {
                jDViewKitAbsoluteLayout2.setDataSourceModel(jDViewKitDataSourceModel, z);
            }
        }
        sendExpo(jDViewKitDataSourceModel);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        start();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stop();
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
        this.isAutoPlay = false;
        if (list == null || list.size() == 0) {
            return;
        }
        setParentClipChildren();
        if (list.size() == 1) {
            updataView(0, list.get(0), z);
        } else {
            updataView(0, list.get(0), z);
            updataView(1, list.get(1), z);
            this.firstView.bringToFront();
            this.isAutoPlay = true;
            start();
        }
        this.contentPage = 1;
    }

    public void startAin() {
        if (this.linerObjectAnimator == null) {
            LinerObjectAnimator linerObjectAnimator = new LinerObjectAnimator(500L, 1000);
            this.linerObjectAnimator = linerObjectAnimator;
            linerObjectAnimator.setAnimatorListener(new LinerObjectAnimator.LinerObjectAnimatorListener() { // from class: com.jd.viewkit.templates.container.jdviewkitdynamicbanner.gradient.JDViewKitGradientDynamicBanner.2
                int count = 0;

                @Override // com.jd.viewkit.tool.animator.LinerObjectAnimator.LinerObjectAnimatorListener
                public void end(int i2) {
                    if (JDViewKitGradientDynamicBanner.this.isAutoPlay) {
                        JDViewKitGradientDynamicBanner.this.firstView.setTranslationY(0.0f);
                        JDViewKitGradientDynamicBanner.this.firstView.setScaleX(0.8f);
                        JDViewKitGradientDynamicBanner.this.firstView.setScaleY(0.8f);
                        JDViewKitGradientDynamicBanner.this.sedView.setAlpha(1.0f);
                        JDViewKitGradientDynamicBanner.this.sedView.setScaleX(1.0f);
                        JDViewKitGradientDynamicBanner.this.sedView.setScaleY(1.0f);
                        JDViewKitGradientDynamicBanner.access$708(JDViewKitGradientDynamicBanner.this);
                        JDViewKitGradientDynamicBanner.this.updataData();
                        this.count = 0;
                    }
                }

                @Override // com.jd.viewkit.tool.animator.LinerObjectAnimator.LinerObjectAnimatorListener
                public void updata(int i2) {
                    this.count += i2;
                    if (JDViewKitGradientDynamicBanner.this.isAutoPlay) {
                        double d = this.count;
                        Double.isNaN(d);
                        float f2 = (float) ((d * 1.0d) / 1000.0d);
                        float f3 = 1.0f - f2;
                        JDViewKitGradientDynamicBanner.this.firstView.setTranslationY((-f2) * JDViewKitGradientDynamicBanner.this.heigh * 0.3f);
                        JDViewKitGradientDynamicBanner.this.firstView.setAlpha(f3);
                        float f4 = (f3 * 0.2f) + 0.8f;
                        JDViewKitGradientDynamicBanner.this.firstView.setScaleX(f4);
                        JDViewKitGradientDynamicBanner.this.firstView.setScaleY(f4);
                        JDViewKitGradientDynamicBanner.this.sedView.setAlpha(f2);
                        float f5 = (f2 * 0.2f) + 0.8f;
                        JDViewKitGradientDynamicBanner.this.sedView.setScaleX(f5);
                        JDViewKitGradientDynamicBanner.this.sedView.setScaleY(f5);
                    }
                }
            });
        }
        this.linerObjectAnimator.start();
    }

    @Override // com.jd.viewkit.templates.JDViewKitBaseLayout
    public void setVirtualView(JDViewKitVirtualDynamicBanner jDViewKitVirtualDynamicBanner) {
        super.setVirtualView((JDViewKitGradientDynamicBanner) jDViewKitVirtualDynamicBanner);
        if (jDViewKitVirtualDynamicBanner != null) {
            int interval = jDViewKitVirtualDynamicBanner.getInterval();
            this.interval = interval;
            if (interval == 0) {
                interval = 3000;
            }
            this.interval = interval;
            this.heigh = GlobalManage.getInstance().getRealPx(jDViewKitVirtualDynamicBanner.getHeigh(), getChannelModel());
        }
    }
}
