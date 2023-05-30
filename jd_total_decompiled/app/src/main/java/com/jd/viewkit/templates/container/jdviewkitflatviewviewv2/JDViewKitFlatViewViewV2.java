package com.jd.viewkit.templates.container.jdviewkitflatviewviewv2;

import android.content.Context;
import android.view.View;
import androidx.annotation.NonNull;
import com.jd.viewkit.dataSources.model.JDViewKitDataSourceModel;
import com.jd.viewkit.global.GlobalManage;
import com.jd.viewkit.helper.JDViewKitAutoPlayInterface;
import com.jd.viewkit.helper.JDViewKitChannelModel;
import com.jd.viewkit.helper.JDViewKitConstant;
import com.jd.viewkit.helper.JDViewKitCountdownInterface;
import com.jd.viewkit.helper.JDViewKitLayoutInterface;
import com.jd.viewkit.templates.JDViewKitBaseLayout;
import com.jd.viewkit.templates.model.JDViewKitVirtualView;
import com.jd.viewkit.templates.view.JDViewKitAbsoluteLayout;
import com.jd.viewkit.templates.view.JDViewKitCountdownView;
import com.jd.viewkit.templates.view.JDViewKitProgressView;
import com.jd.viewkit.templates.view.factory.JDViewKitViewFactory;
import com.jd.viewkit.templates.view.helper.JDViewKitEventHelper;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* loaded from: classes18.dex */
public class JDViewKitFlatViewViewV2 extends JDViewKitBaseLayout<JDViewKitVirtualView> implements JDViewKitCountdownInterface, JDViewKitLayoutInterface, JDViewKitAutoPlayInterface {
    private static final String TAG = "JDViewKitFlatViewViewV2";
    private JDViewKitCountdownView countdownView;
    private boolean isLinearLayout;
    private boolean isOnlyUseAbsolute;
    private int itemSpace;
    private List<JDViewKitAbsoluteLayout> mAbsoluteLayoutList;
    private boolean mIsLayout;
    private int maxHeigh;
    private int maxWidth;
    private int paddingBottom;
    private int paddingLeft;
    private int paddingRight;
    private int paddingTop;
    private JDViewKitProgressView progressView;

    public JDViewKitFlatViewViewV2(@NonNull Context context) {
        super(context);
        this.mAbsoluteLayoutList = new LinkedList();
        this.mIsLayout = false;
        this.isOnlyUseAbsolute = false;
        initView();
    }

    private void initView() {
    }

    private void layoutAbsolute(int i2, int i3, int i4, int i5) {
        int childCount = getChildCount();
        int i6 = 0;
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            if (childAt == this.bgImageView) {
                childAt.layout(0, 0, i4 - i2, i5 - i3);
            } else if (JDViewKitBaseLayout.viewIsFilter(childAt)) {
                childAt.layout(i2, i3, i2, i3);
            } else {
                int measuredWidth = childAt.getMeasuredWidth() + i6;
                childAt.layout(i6, 0, measuredWidth, childAt.getMeasuredHeight());
                i6 = measuredWidth;
            }
        }
    }

    private void layoutHorizontal(int i2, int i3, int i4, int i5) {
        int i6;
        String layout_subAxis = getLayout_subAxis();
        int i7 = i5 - i3;
        int i8 = (i7 - this.paddingTop) - this.paddingBottom;
        int i9 = this.paddingLeft;
        int childCount = getChildCount();
        for (int i10 = 0; i10 < childCount; i10++) {
            View childAt = getChildAt(i10);
            if (childAt == this.bgImageView) {
                childAt.layout(0, 0, i4 - i2, i7);
            } else if (JDViewKitBaseLayout.viewIsFilter(childAt)) {
                childAt.layout(i2, i3, i2, i3);
            } else {
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                String layout_alignSelf = childAt instanceof JDViewKitLayoutInterface ? ((JDViewKitLayoutInterface) childAt).getLayout_alignSelf() : layout_subAxis;
                int i11 = this.paddingTop;
                if (layout_alignSelf.equals(JDViewKitConstant.LAYOUT_ALIGN_CENTER)) {
                    i6 = (i8 - measuredHeight) / 2;
                } else {
                    if (layout_alignSelf.equals(JDViewKitConstant.LAYOUT_ALIGN_END)) {
                        i6 = i8 - measuredHeight;
                    }
                    childAt.layout(i9, i11, i9 + measuredWidth, measuredHeight + i11);
                    i9 += measuredWidth + this.itemSpace;
                }
                i11 += i6;
                childAt.layout(i9, i11, i9 + measuredWidth, measuredHeight + i11);
                i9 += measuredWidth + this.itemSpace;
            }
        }
    }

    private int[] measureAbsolute() {
        return new int[]{this.maxWidth, this.maxHeigh};
    }

    private int[] measureHorizontal() {
        int childCount = getChildCount();
        int i2 = 0;
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            if (childAt != this.bgImageView && !JDViewKitBaseLayout.viewIsFilter(childAt)) {
                i2 += childAt.getMeasuredWidth() + this.itemSpace;
            }
        }
        if (i2 > 0) {
            i2 -= this.itemSpace;
        }
        return new int[]{Math.min(i2 + this.paddingLeft + this.paddingRight, this.maxWidth), this.maxHeigh};
    }

    private int[] measureVertical() {
        int childCount = getChildCount();
        int i2 = 0;
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            if (childAt != this.bgImageView && !JDViewKitBaseLayout.viewIsFilter(childAt)) {
                i2 += childAt.getMeasuredHeight() + this.itemSpace;
            }
        }
        if (i2 > 0) {
            i2 -= this.itemSpace;
        }
        return new int[]{this.maxWidth, Math.min(i2 + this.paddingTop + this.paddingBottom, this.maxHeigh)};
    }

    private void updateCountdownView() {
        JDViewKitAbsoluteLayout jDViewKitAbsoluteLayout;
        List<JDViewKitAbsoluteLayout> list = this.mAbsoluteLayoutList;
        if (list == null || list.size() <= 0 || (jDViewKitAbsoluteLayout = this.mAbsoluteLayoutList.get(0)) == null || !(jDViewKitAbsoluteLayout instanceof JDViewKitAbsoluteLayout)) {
            return;
        }
        this.countdownView = JDViewKitCountdownView.getCountdownView(jDViewKitAbsoluteLayout);
        this.progressView = JDViewKitProgressView.getProgressView(jDViewKitAbsoluteLayout);
    }

    @Override // com.jd.viewkit.helper.JDViewKitAutoPlayInterface
    public boolean autoPlay(boolean z, boolean z2) {
        JDViewKitChannelModel jDViewKitChannelModel = this.channelModel;
        if (jDViewKitChannelModel != null) {
            return jDViewKitChannelModel.autoPlay(z, z2);
        }
        return false;
    }

    public void clearAutoVideoViewLists() {
        JDViewKitChannelModel jDViewKitChannelModel = this.channelModel;
        if (jDViewKitChannelModel == null || jDViewKitChannelModel.getRootView() != this) {
            return;
        }
        this.channelModel.clearAutoVideoViewLists();
    }

    @Override // com.jd.viewkit.helper.JDViewKitCountdownInterface
    public int getCountType() {
        updateCountdownView();
        JDViewKitCountdownView jDViewKitCountdownView = this.countdownView;
        if (jDViewKitCountdownView == null || jDViewKitCountdownView.virtualCountdownView == null) {
            return -1;
        }
        jDViewKitCountdownView.initCountdownParamsByPlay(true);
        return this.countdownView.virtualCountdownView.getCountType();
    }

    @Override // com.jd.viewkit.helper.JDViewKitLayoutInterface
    public String getLayout_alignSelf() {
        T t = this.virtualView;
        if (t == 0) {
            return JDViewKitConstant.LAYOUT_ALIGN_START;
        }
        return t.getLayout_alignSelf();
    }

    @Override // com.jd.viewkit.helper.JDViewKitLayoutInterface
    public String getLayout_direction() {
        T t = this.virtualView;
        if (t == 0) {
            return JDViewKitConstant.LAYOUT_DIRECTION_HORIZONTAL;
        }
        return t.getLayout_direction();
    }

    @Override // com.jd.viewkit.helper.JDViewKitLayoutInterface
    public int getLayout_itemSpace() {
        T t = this.virtualView;
        if (t == 0) {
            return 0;
        }
        return t.getLayout_itemSpace();
    }

    @Override // com.jd.viewkit.helper.JDViewKitLayoutInterface
    public String getLayout_mainAxis() {
        T t = this.virtualView;
        if (t == 0) {
            return JDViewKitConstant.LAYOUT_ALIGN_START;
        }
        return t.getLayout_mainAxis();
    }

    @Override // com.jd.viewkit.helper.JDViewKitLayoutInterface
    public String getLayout_subAxis() {
        T t = this.virtualView;
        if (t == 0) {
            return JDViewKitConstant.LAYOUT_ALIGN_START;
        }
        return t.getLayout_subAxis();
    }

    @Override // com.jd.viewkit.helper.JDViewKitLayoutInterface
    public String getLayout_type() {
        T t = this.virtualView;
        if (t == 0) {
            return JDViewKitConstant.LYAOUT_TYPE_ABSOLUTE;
        }
        return t.getLayout_type();
    }

    @Override // com.jd.viewkit.helper.JDViewKitLayoutInterface
    public boolean getOnlyUseAbsolute() {
        return this.isOnlyUseAbsolute;
    }

    @Override // com.jd.viewkit.helper.JDViewKitCountdownInterface
    public int getTriggerType() {
        updateCountdownView();
        JDViewKitCountdownView jDViewKitCountdownView = this.countdownView;
        if (jDViewKitCountdownView == null || jDViewKitCountdownView.virtualCountdownView == null) {
            return -1;
        }
        jDViewKitCountdownView.initCountdownParamsByPlay(true);
        return this.countdownView.virtualCountdownView.getTriggerType();
    }

    @Override // com.jd.viewkit.helper.JDViewKitCountdownInterface
    public void handleCountdown(int i2) {
        updateCountdownView();
        JDViewKitCountdownView jDViewKitCountdownView = this.countdownView;
        if (jDViewKitCountdownView != null) {
            jDViewKitCountdownView.initCountdownParamsByPlay(true);
            this.countdownView.handleCountdown(i2);
        }
    }

    @Override // com.jd.viewkit.helper.JDViewKitCountdownInterface
    public void initCountdownParamsByPlay() {
        updateCountdownView();
        JDViewKitCountdownView jDViewKitCountdownView = this.countdownView;
        if (jDViewKitCountdownView != null) {
            jDViewKitCountdownView.initCountdownParamsByPlay(true);
        }
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        if (getVirtualView() != null && getVirtualView().getLayout_type().equals(JDViewKitConstant.LYAOUT_TYPE_LINEAR)) {
            layoutHorizontal(i2, i3, i4, i5);
        } else {
            layoutAbsolute(i2, i3, i4, i5);
        }
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i2, int i3) {
        int[] measureAbsolute;
        super.onMeasure(i2, i3);
        measureChildren(i2, i3);
        if (getVirtualView() != null && getVirtualView().getLayout_type().equals(JDViewKitConstant.LYAOUT_TYPE_LINEAR)) {
            if (getLayout_direction().equals(JDViewKitConstant.LAYOUT_DIRECTION_VERTICAL)) {
                measureAbsolute = measureVertical();
            } else {
                measureAbsolute = measureHorizontal();
            }
        } else {
            measureAbsolute = measureAbsolute();
        }
        setMeasuredDimension(measureAbsolute[0], measureAbsolute[1]);
    }

    public void sendExpo(JDViewKitDataSourceModel jDViewKitDataSourceModel) {
        Map<String, JDViewKitVirtualView> map;
        JDViewKitVirtualView jDViewKitVirtualView;
        if (jDViewKitDataSourceModel == null || (map = this.dslsMap) == null || (jDViewKitVirtualView = map.get(jDViewKitDataSourceModel.getDslId())) == null || jDViewKitVirtualView.getExpoEvent() == null) {
            return;
        }
        JDViewKitEventHelper.sendExpo(jDViewKitVirtualView, jDViewKitVirtualView.getExpoEvent(), jDViewKitDataSourceModel, this);
    }

    @Override // com.jd.viewkit.helper.JDViewKitCountdownInterface
    public void setCountdownLifeCycle(int i2) {
        updateCountdownView();
        JDViewKitCountdownView jDViewKitCountdownView = this.countdownView;
        if (jDViewKitCountdownView != null) {
            jDViewKitCountdownView.initCountdownParamsByPlay(true);
            this.countdownView.setCountdownLifeCycle(i2);
        }
        JDViewKitProgressView jDViewKitProgressView = this.progressView;
        if (jDViewKitProgressView != null) {
            jDViewKitProgressView.setCountdownLifeCycle(i2);
        }
    }

    @Override // com.jd.viewkit.templates.JDViewKitBaseLayout
    public void setDataSourceModels(List<JDViewKitDataSourceModel> list, boolean z) {
        super.setDataSourceModels(list, z);
        clearAutoVideoViewLists();
        this.mIsLayout = z;
        for (int i2 = 0; i2 < getDataSourceModels().size(); i2++) {
            JDViewKitDataSourceModel jDViewKitDataSourceModel = getDataSourceModels().get(i2);
            if (i2 >= this.mAbsoluteLayoutList.size()) {
                JDViewKitAbsoluteLayout view = JDViewKitViewFactory.getView(this.mContext, this.dslsMap.get(jDViewKitDataSourceModel.getDslId()), getChannelModel());
                if (this.isLinearLayout) {
                    view.setOnlyUseAbsolute(false);
                }
                view.setDataSourceModel(jDViewKitDataSourceModel, false);
                addView(view);
                this.mAbsoluteLayoutList.add(view);
            } else {
                this.mAbsoluteLayoutList.get(i2).setDataSourceModel(jDViewKitDataSourceModel, this.mIsLayout);
            }
            sendExpo(jDViewKitDataSourceModel);
            if (i2 > 9) {
                break;
            }
        }
        updateLayout();
    }

    @Override // com.jd.viewkit.helper.JDViewKitLayoutInterface
    public void setOnlyUseAbsolute(boolean z) {
        this.isOnlyUseAbsolute = z;
    }

    @Override // com.jd.viewkit.templates.JDViewKitBaseLayout
    public void setViewWidth(int i2) {
        super.setViewWidth(i2);
        updataParas();
    }

    @Override // com.jd.viewkit.templates.JDViewKitBaseLayout
    public void setVirtualView(JDViewKitVirtualView jDViewKitVirtualView) {
        super.setVirtualView(jDViewKitVirtualView);
        if (jDViewKitVirtualView != null) {
            updataParas();
            if (getVirtualView().getLayout_type().equals(JDViewKitConstant.LYAOUT_TYPE_LINEAR)) {
                this.isLinearLayout = true;
            } else {
                this.isLinearLayout = false;
            }
        }
    }

    public void updataParas() {
        if (this.virtualView != 0) {
            this.maxWidth = GlobalManage.getInstance().getRealPx(this.virtualView.getWidth(), getChannelModel());
            this.maxHeigh = GlobalManage.getInstance().getRealPx(this.virtualView.getHeigh(), getChannelModel());
            this.itemSpace = GlobalManage.getInstance().getRealPx(this.virtualView.getLayout_itemSpace(), getChannelModel());
            this.paddingTop = GlobalManage.getInstance().getRealPx(this.virtualView.getPaddingTop(), getChannelModel());
            this.paddingRight = GlobalManage.getInstance().getRealPx(this.virtualView.getPaddingRight(), getChannelModel());
            this.paddingBottom = GlobalManage.getInstance().getRealPx(this.virtualView.getPaddingBottom(), getChannelModel());
            this.paddingLeft = GlobalManage.getInstance().getRealPx(this.virtualView.getPaddingLeft(), getChannelModel());
        }
    }

    @Override // com.jd.viewkit.templates.JDViewKitBaseLayout
    public void updateLayout() {
        super.updateLayout();
    }

    public JDViewKitFlatViewViewV2(@NonNull Context context, @NonNull JDViewKitChannelModel jDViewKitChannelModel) {
        this(context);
        this.channelModel = jDViewKitChannelModel;
    }
}
